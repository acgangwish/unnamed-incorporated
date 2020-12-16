

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.User;
import util.utilDB;

/**
 * Servlet implementation class CreateUserAccount
 */
@WebServlet("/CreateUserAccount")
public class CreateUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public int id = 0;
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
	    List<User> custUsers = utilDB.getCustomerUserList();
	    List<User> restUsers = utilDB.getRestaurantUserList();
	    
	    boolean passCorrect = false;
	    boolean err = false;
	    
	    if (passwordReg.equals(confirmPass)) {
	    	passCorrect = true;
	    } else {
	    	out.print("Error: Passwords do not match.");
	    	request.getRequestDispatcher("SignIn.html").include(request, response);
	    	err = true;
	    }
	    
	    if (passCorrect && outputReg.equals("cust") && err != true) {
	    	for (User u: custUsers) {
	    		if (usernameReg.equals(u.getUsername())) {
	    			out.print("That username is already taken. Please select a different Username.");
	    			request.getRequestDispatcher("SignIn.html").include(request, response);
	    			err = true;
		    		break;
	    		}
	    	}
	    	if (err != true) {
	    		utilDB.createUser(usernameReg, passwordReg, false);
	    		request.getRequestDispatcher("SignIn.html").include(request, response);
	    	}
	    }
	    
	    boolean dup = false;
	    if (err != true) {
	    	for (User u: restUsers) {
	    		if (usernameReg.equals(u.getUsername())) {
	    			out.print("That username is already taken. Please select a different Username.");
	    			request.getRequestDispatcher("SignIn.html").include(request, response);
	    			err = true;
		    		break;
	    		}
	    	}
	    }
	    
	    if (passCorrect && outputReg.equals("rest") && err != true ) {
	    	utilDB.createUser(usernameReg, passwordReg, true);
	    	restUsers = utilDB.getRestaurantUserList();
	    	for (Iterator<?> iterator = restUsers.iterator(); iterator.hasNext();) {
		    	User user = (User) iterator.next();
		    	if (user.getUsername().equals(usernameReg)) {
		    		id = user.getId();
		    		break;
		    	}
		    }
	    	
	    	response.getWriter().print(
	    			"<!DOCTYPE html>\n" + 
	    			"<html>\n" + 
	    			"<head>\n" + 
	    			"<body>\n" + 
	    			"<form action=\"CreateRestaurantInformation\" method=\"POST\">\r\n" + 
	    		    "   <input type=\"hidden\" id=\"restID\" name=\"ID\" value=\"" + id + "\">\r\n" +
	    			"	Restaurant Name: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resName\"> <br> <br>\r\n" + 
	    			"	Description: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resDesc\"> <br> <br>\r\n" + 
	    			"	Address: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resAddr\"> <br> <br>\r\n" + 
	    			"	City: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resCity\"> <br> <br>\r\n" + 
	    			"	State: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resState\"> <br> <br>\r\n" + 
	    			"	Hours: <br>\r\n" + 
	    			"	<input type=\"text\" name=\"resHours\"> <br> <br>\r\n" + 
	    			"	<input type=\"submit\" value=\"Register Information\">\r\n" + 
	    			"</form>\r\n" + 
	    			"</body>\r\n" + 
	    			"</head>\r\n" + 
	    			"</html>");
	    }
	}

	public int getID() {
		return id;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
