import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.User;
import util.utilDB;

@WebServlet("/CreateRestaurantInformation")
public class CreateRestaurantInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateRestaurantInformation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("resName");
		String desc = request.getParameter("resDesc");
		String addr = request.getParameter("resAddr");
		String city = request.getParameter("resCity");
		String state = request.getParameter("resState");
		String hours = request.getParameter("resHours");
		String usernameReg = request.getParameter("usernameReg");
	    String passwordReg = request.getParameter("passwordReg");
	    List<User> users = utilDB.getRestaurantUserList();
	    PrintWriter out = response.getWriter();
	    boolean found = false;
	    int ID = 0;
	    
		for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
	    	User user = (User) iterator.next();
	    	if (user.getUsername().equals(usernameReg) && user.getPassword().equals(passwordReg)) {
	    		user.getId();
	    		found = true;
	    		break;
	    	}
	    }
		
		if (found) {
			utilDB.createRes(ID, name, desc, addr, city, state, hours);
			request.getRequestDispatcher("HomePage.html").include(request, response);
		} else {
			out.print("Error: Invalid information.");
	    	request.getRequestDispatcher("SignIn.html").include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}