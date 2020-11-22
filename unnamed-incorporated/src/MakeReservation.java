

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.utilDB;

/**
 * Servlet implementation class MakeReservation
 */
@WebServlet("/MakeReservation")
public class MakeReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int restID = Integer.parseInt(request.getParameter("restID"));
		int custID = Integer.parseInt(request.getParameter("custID"));
		String personName = request.getParameter("personName");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		int numPeople = Integer.parseInt(request.getParameter("numPeople"));
		
		utilDB.createReservation(restID, custID, personName, date, time, numPeople);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
