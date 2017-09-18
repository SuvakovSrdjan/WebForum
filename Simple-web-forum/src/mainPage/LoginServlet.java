package mainPage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesPackage.Data;
import classesPackage.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String redirect = "Login.jsp";
	Boolean comesFromPost = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(comesFromPost == false) {
			HttpSession session = request.getSession();
			if(session.getAttribute("user") != null) {
				session.removeAttribute("user");
				redirect = "MainPageStuff";
			}
		}
			
		
		response.sendRedirect(redirect);
		redirect="Login.jsp";
		comesFromPost = false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comesFromPost = true;
			if (request.getParameter("login") != null) {
				//Check to see if username exists
				Data allData = Data.readData();
				Boolean boo = false;
				if(request.getParameter("Username") == null || request.getParameter("Username") == ""){
					boo=true;
				}
				if(!boo) {
					for(User tmp: allData.getUsers()) {
						if(tmp.getUsername().equals(request.getParameter("Username"))) {
							if(tmp.getPassword().equals(request.getParameter("Password1"))) {
								HttpSession session = request.getSession();
								redirect="MainPageStuff";
								session.setAttribute("user", tmp);
							}
						}
							
					}
				}
				
			}
			if(request.getParameter("cancel") != null) {
				redirect = "MainPageStuff";
			}
		
		doGet(request, response);
	}

}
