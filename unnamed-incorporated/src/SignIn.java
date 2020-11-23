

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.User;
import util.utilDB;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		List<User> users;
		PrintWriter out = response.getWriter();
		String username = request.getParameter("usernameIn");
		String password = request.getParameter("passwordIn");
	    String output = request.getParameter("type");
	    boolean type;
	    if(output.equals("cust")) {
	    	type = false;
	    	users = utilDB.getCustomerUserList();
	    } else {
	    	type = true;
	    	users = utilDB.getRestaurantUserList();
	    }
	    
	    boolean found = false;
	    for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	    	User user = (User) iterator.next();
	    	if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	    		HttpSession session = request.getSession();
	    		session.setAttribute("ID", user.getId());
	    		session.setAttribute("rest", user.isRestaurant());
	    		found = true;
	    		break;
	    	}
	    }
	    if (found) {
	    	request.getRequestDispatcher("HomePage.html").include(request, response);
	    	
	    }else {
	    	out.print("Error: Invalid username and/or password.");
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
