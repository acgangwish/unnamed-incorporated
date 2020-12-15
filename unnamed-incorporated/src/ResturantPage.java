
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datamodel.Resturant;
import util.utilDB;

/**
 * Servlet implementation class ResturantPage
 */
@WebServlet("/ResturantPage")
public class ResturantPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResturantPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Integer cID;
		if(session != null) {
			if((boolean) session.getAttribute("rest")) {
				cID = -1;
			} else {
				cID = (Integer) session.getAttribute("ID");
			}
		} else {
			cID = -1;
		}
		System.out.println(cID);
		int rID = Integer.parseInt(request.getParameter("rest"));
		response.setContentType("text/html");
		response.getWriter().append("<!DOCTYPE html><html>" + displayStyle() + displayBody(rID, cID) + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static String displayBody(Integer rID, Integer cID) {
		Resturant r = null;
		String rname;
		String desc;
		String addr;
		String hours;
		if ((r = utilDB.getResturant(1)) != null) {
			rname = r.getRname();
			desc = r.getDesc();
			addr = r.getAddr() + " " + r.getCity() + " " + r.getState();
			hours = r.getHours();
		} else {
			rname ="N/A";
			desc ="N/A";
			addr  ="N/A";
			hours ="N/A";
		}
		return "<body>\r\n" + "\r\n" + "    <div class=\"center title\">"+ rname +"</div>\r\n"
				+ "    <div class=\"topSign\">\r\n" + "\r\n"
				+ "        <a href=\"/unnamed-incorporated/SignIn.html\">Sign In</a>\r\n" + "    </div>\r\n" + "\r\n"
				+ "    <div class=\"info\">\r\n"
				+ "        <a class=\"desc\">" + desc +"</a>\r\n"
				+ "        <a class=\"menu\">\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n"
				+ "            Item - Some Description of what the item is - $Price<br>\r\n" + "\r\n"
				+ "        </a>\r\n" + "        <a class=\"rest\">\r\n"
				+ "            Address: "+ addr + "<br>\r\n"
				+ "            Phone: 402-555-5555<br><br>\r\n" + "            <b>Hours</b><br>\r\n"
				+ "            " + hours
				+ "</a>\r\n" + "    </div>\r\n" + "\r\n"
				+ "    <div class=\"res a center\">\r\n" + "        <strong>Make a Reservation</strong>\r\n"
				+ "        <form name=\"reservationForm\" method=\"post\" action=\"MakeReservation\">\r\n"
				+ "        	<input type=\"hidden\" id=\"restID\" name=\"restID\" value=\"" + rID + "\">\r\n"
				+ "        	<input type=\"hidden\" id=\"custID\" name=\"custID\" value=\"" + cID + "\">\r\n"
				+ "         <label for=\"date\"> Name on Reservation: </label>\r\n"
				+ "         <input type=\"text\" id=\"personName\" name=\"personName\">\r\n"
				+ "        	<label for=\"date\"> Date: </label>\r\n"
				+ "        	<input type=\"date\" id=\"date\" name=\"date\">\r\n"
				+ "        	<label for=\"time\"> Time: </label>\r\n"
				+ "        	<input type=\"time\" id=\"time\" name=\"time\">\r\n"
				+ "        	<label for=\"numPeople\"> Number of People: </label>\r\n"
				+ "        	<input type=\"number\" id=\"numPeople\" name=\"numPeople\">\r\n"
				+ "        	<input type=\"submit\" value=\"Submit Reservation\">\r\n" + "        </form>\r\n"
				+ "    </div>\r\n" + "\r\n" + "\r\n" + "    <div class=\"nav\">\r\n" + "\r\n"
				+ "        <a href=\"/unnamed-incorporated/HomePage.html\">Home</a>\r\n" + "\r\n"
				+ "        <a href=\"/unnamed-incorporated/UserReservation\">Info</a>\r\n" + "\r\n"
				+ "        <a class=\"active\" href=\"/unnamed-incorporated/ResturantPage\">Restaurants</a>\r\n" + "\r\n"
				+ "        <a href=\"/unnamed-incorporated/LogOut\">Log Out</a>\r\n"
				+ "\r\n" + "    </div>\r\n" + "\r\n" + "</body>";
	}

	private static String displayStyle() {
		return "<head>\r\n" + "    <link rel=\"stylesheet\" href=\"basic.css\">\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap\" rel=\"stylesheet\">\r\n"
				+ "    <style>\r\n" + "        body{\r\n" + "            font-family: 'Montserrat', sans-serif;\r\n"
				+ "        }\r\n" + "        \r\n" + "        input {\r\n" + "        	display: block;\r\n"
				+ "        	margin: auto;\r\n" + "        }\r\n" + "        \r\n" + "        label{\r\n"
				+ "        	font-family: 'Montserrat', sans-serif;\r\n" + "        	font-size: 20px\r\n"
				+ "        }\r\n" + "        \r\n" + "        strong{\r\n" + "        	font-size: 40px\r\n"
				+ "        }\r\n" + "        .info {\r\n" + "            width: 95%;\r\n"
				+ "            display: flex;\r\n" + "            margin-left: auto;\r\n"
				+ "            margin-right: auto;\r\n" + "            margin-bottom: 50px;\r\n"
				+ "            box-shadow: 6px 6px 8px 2px #888888;\r\n" + "\r\n" + "        }\r\n" + "\r\n"
				+ "        .info a {\r\n" + "            float: left;\r\n" + "            height: auto;\r\n"
				+ "            flex: 1;\r\n" + "            border-style: solid;\r\n"
				+ "            padding: 5px 5px;\r\n" + "        \r\n" + "\r\n" + "        }\r\n" + "\r\n"
				+ "        .info a.top {\r\n" + "            height: 2px;\r\n" + "            width: 100%;\r\n"
				+ "            color: lightgray;\r\n" + "\r\n" + "        }\r\n" + "\r\n" + "        .info a.desc {\r\n"
				+ "            background-color: rgb(200, 200, 200);\r\n" + "            max-width: 20%;\r\n"
				+ "            border-right: 0px;\r\n" + "\r\n" + "        }\r\n" + "\r\n"
				+ "        .info a.menu {\r\n" + "\r\n" + "            background-color: rgb(150, 150, 150);\r\n"
				+ "            max-width: 50%;\r\n" + "            border-right: 0px;\r\n" + "\r\n" + "        }\r\n"
				+ "\r\n" + "        .info a.rest {\r\n" + "            background-color: rgb(200, 200, 200);\r\n"
				+ "            max-width: 30%;\r\n" + "        }\r\n" + "\r\n" + "        .res {\r\n"
				+ "            bottom: 40px;\r\n" + "            padding: 10px 10px;\r\n"
				+ "            margin-left: auto;\r\n" + "            margin-right: auto;\r\n"
				+ "            width: fit-content\r\n" + "        }\r\n" + "\r\n" + "        .res a {\r\n"
				+ "            background: #020202;\r\n" + "\r\n" + "            color: white;\r\n"
				+ "            display: block;\r\n" + "            padding: 10px 20px;\r\n"
				+ "            text-decoration: none;\r\n" + "            text-align: center;\r\n" + "        }\r\n"
				+ "    </style>\r\n" + "</head>";
	}

}
