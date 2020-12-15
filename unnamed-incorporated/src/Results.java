
import util.utilDB;
import java.util.List;

import datamodel.Resturant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Results
 */
@WebServlet("/Results")
public class Results extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Results() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Integer cID;
		boolean isRest = false;
		if(session != null) {
			cID = (Integer) session.getAttribute("ID");
			isRest= (boolean) session.getAttribute("rest");
		} else {
			cID = -1;
		}
		
		response.setContentType("text/html");
		String search = request.getParameter("keyword");
		if(search == null || search.equals("")) search = "";
		response.getWriter().append(getStyle() + getBody(search));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static String getStyle() {
		return "<head>\r\n" + 
				"    <link rel=\"stylesheet\" href=\"basic.css\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"basic.css\">\r\n" + 
				"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n" + 
				"    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap\" rel=\"stylesheet\">\r\n" + 
				"    <style>\r\n" + 
				"        body {\r\n" + 
				"            font-family: 'Montserrat', sans-serif;\r\n" + 
				"        }\r\n" + 
				"        ul {\r\n" + 
				"            list-style-type: none;\r\n" + 
				"            width: 60%;\r\n" + 
				"            margin: auto;\r\n" + 
				"            \r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .res {\r\n" + 
				"            background-color: #d2d2d2;\r\n" + 
				"            overflow: hidden;\r\n" + 
				"            margin-bottom: 20px;\r\n" + 
				"            padding: 10px 10px;\r\n" + 
				"            box-shadow: 6px 6px 8px 2px #888888;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .res a {\r\n" + 
				"            float: left;\r\n" + 
				"            display: block;\r\n" + 
				"            text-align: center;\r\n" + 
				"            color: #131313;\r\n" + 
				"            font-weight:bold;\r\n" + 
				"            font-size: larger;\r\n" + 
				"            padding: 10px;\r\n" + 
				"\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        .res a.right {\r\n" + 
				"            float: right;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"</head>";
		
	}
	private static String getBody(String search) {
		String first = "<body>\r\n" + 
				"    <div class=\"topSign\">\r\n" + 
				"        <a href=\"/unnamed-incorporated/SignIn.html\">Sign In</a> <br>\r\n" + 
				"    </div>\r\n" + 
				"\r\n" + 
				"    <div class=\"center title\">Search Results</div>\r\n" + 
				"    <ul>\r\n";
		String last="    </ul>\r\n" + 
				"\r\n" + 
				"    <div class=\"nav\">\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/HomePage.html\">Home</a>\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/UserReservation\">Info</a>\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/ResturantPage\">Restaurants</a>\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/LogOut\">Log Out</a>\r\n" + 
				"\r\n" + 
				"    </div>\r\n" + 
				"</body>\r\n</html>";
		List<Resturant> resturants = utilDB.getSearchResults(search);
		String list= "";
		for(Resturant r: resturants) {
			list += "<form id=\"select\" action=\"ResturantPage\" method=\"POST\">\r\n" + 
					"            <input type=\"hidden\" id=rest name=rest value="+ r.getrID()  +">\r\n" + 
					"            <li onclick=\"select.submit();\">\r\n" + 
					"                <div href class=res>\r\n" + 
					"                    <a>"+ r.getRname() + "</a>\r\n" + 
					"                    <a class=right>"+String.format("%s %s, %s", r.getAddr(),r.getCity(),r.getState())+"</a>\r\n" + 
					"                </div>\r\n" + 
					"            </li>\r\n" + 
					"        </form>";
		}
		
		return first + list + last;
	}

}
