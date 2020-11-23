

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Reservation;
import util.utilDB;
import datamodel.Resturant;

/**
 * Servlet implementation class UserReservation
 */
@WebServlet("/UserReservation")
public class UserReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReservation() {
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
		if(session != null) {
			cID = (Integer) session.getAttribute("ID");
		} else {
			cID = -1;
		}
		System.out.println(cID);
		boolean isRest = (boolean) session.getAttribute("rest");
		response.setContentType("text/html");
		response.getWriter().append("<!DOCTYPE html><html>" + displayStyle() + displayBody(cID,isRest) + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static String displayStyle() {
		return "<head>\r\n" + 
				"    <link rel=\"stylesheet\" href=\"basic.css\">\r\n" + 
				"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n" + 
				"    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap\" rel=\"stylesheet\">\r\n" + 
				"    <style>\r\n" + 
				"        body {\r\n" + 
				"            font-family: 'Montserrat', sans-serif;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
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
	private static String displayBody(Integer cID, boolean isRest) {
		String whole ="";
		String beginning;
		String end;
		
		
		beginning = "<body>\r\n" + 
				"    <div class=\"center title\">Your Reservations</div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    <ul>\r\n" + 
				"        \r\n" +
				"        <li>\r\n" + 
				"            <div class=res>\r\n" + 
				"                <a>Restaurant Name</a>\r\n" + 
				"                <a>Reservation Name</a>\r\n" + 
				"                <a>Num Of People</a>\r\n" + 
				"                <a class=right>Time</a>\r\n" + 
				"                <a class=right>Date</a>\r\n" + 
				"                \r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" ;
		whole += beginning;
		if(cID != -1) {
			
			List<Reservation> rs = isRest?utilDB.getReservationRest(cID):utilDB.getReservationCust(cID);
			for (Reservation r: rs)
			{
				Resturant a = utilDB.getResturant(r.getRestaurantId());
				whole += "<li>\r\n" + 
				"            <div class=res>\r\n" + 
				"                <a>" + a.getRname() + "</a>\r\n" + 
				"                <a>" + r.getReservationName() + "</a>\r\n" + 
				"                <a>"+r.getNumPeople()+" People</a>\r\n" + 
				"                <a class=right>"+r.getTime() +"</a>\r\n" + 
				"                <a class=right>"+r.getDate() + "</a>\r\n" + 
				"                \r\n" + 
				"            </div>\r\n" + 
				"        </li>\r\n" ;
			}
		}
			
		
		
				end = "</ul>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    <div class=\"nav\">\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/HomePage.html\">Home</a>\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/UserReservation\">Info</a>\r\n" + 
				"\r\n" + 
				"        <a href=\"/unnamed-incorporated/ResturantPage\">Restaurants</a>\r\n" + 
				"\r\n" + 
				"        <a style=\"float: right;\" href=\"/unnamed-incorporated/LogOut\">Log Out</a>\r\n" + 
				"\r\n" + 
				"    </div>\r\n" + 
				"</body>";
		return whole + end;
	}

}
