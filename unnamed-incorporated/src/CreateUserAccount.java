

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.utilDB;

/**
 * Servlet implementation class CreateUserAccount
 */
@WebServlet("/CreateUserAccount")
public class CreateUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //This is for the registering a new customer/restaurant
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    String usernameReg = request.getParameter("usernameReg");
	    String passwordReg = request.getParameter("passwordReg");
	    String confirmPass = request.getParameter("passwordRegConfirm");
	    String outputReg = request.getParameter("registerType");
	    
	    boolean passCorrect = false;
	    
	    if (passwordReg.equals(confirmPass)) {
	    	passCorrect = true;
	    } else {
	    	out.print("Error: Passwords do not match.");
	    	request.getRequestDispatcher("SignIn.html").include(request, response);
	    }
	    
	    if (passCorrect && outputReg.equals("cust")) {
	    	utilDB.createUser(usernameReg, passwordReg, false);
	    	request.getRequestDispatcher("SignIn.html").include(request, response);
	    }
	    
	    if (passCorrect && outputReg.equals("rest")) {
	    	utilDB.createUser(usernameReg, passwordReg, true);
	    	request.getRequestDispatcher("CreateRestaurantInformation.html").include(request, response);
	    }
	    
	    else {
	    	out.print("Unknown Error.");
	    	request.getRequestDispatcher("SignIn.html").include(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
