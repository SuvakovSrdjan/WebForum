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

import classesPackage.Data;
import classesPackage.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String redirect = new String("UserRegistration.jsp");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(redirect);
		redirect = "UserRegistration.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("addUser") != null) {
			//Check to see if username exists
			Data allData = Data.readData();
			Boolean boo = false;
			if(request.getParameter("Username") == null || request.getParameter("Username") == ""){
				boo=true;
			}
			for(User tmp: allData.getUsers()) {
				if(tmp.getUsername().equals(request.getParameter("Username"))) {
					boo = true;
				}
					
			}
			if(!boo) {
				//If not go ahead and make a new user
					User newUser = new User();
					
					//First Last and User name
					newUser.setFirstname((String) request.getParameter("FirstName"));
					newUser.setSurname((String) request.getParameter("Lastname"));
					newUser.setUsername((String) request.getParameter("Username"));
					
					//Password and phone number
					newUser.setPassword((String) request.getParameter("Password1"));
					newUser.setPhoneNumber((String) request.getParameter("PhoneNum"));
					
					//AccType
					String accType = (String) request.getParameter("AccType");
					int accTypero = Integer.parseInt(accType);
					switch(accTypero) {
					case 0: accType = "ADMIN"; break;
					case 1: accType = "MODERATOR";break;
					default: accType = "USER";break;
					
					}
					newUser.setAccountType(accType);
					
					
					//Date registered
					Calendar cal = new GregorianCalendar();
					SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY");
					String dateReg= sp.format(cal.getTime());
					newUser.setDateOfRegistry(dateReg);
					
					
					//Write to file
					List<User> userList = allData.getUsers();
					userList.add(newUser);
					allData.setUsers(userList);
					Data.writeData(allData);
					redirect="MainPageStuff";
					
					
				}
			
		}
		
		if(request.getParameter("cancel") != null) {
			redirect = "MainPageStuff";
		}
	
	doGet(request,response);
	}
}
