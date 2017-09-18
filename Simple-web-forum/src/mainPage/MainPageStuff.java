package mainPage;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import classesPackage.Data;
import classesPackage.Message;
import classesPackage.Subforum;
import classesPackage.User;

/**
 * Servlet implementation class MainPageStuff
 */
public class MainPageStuff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean fromPost = false;

    /**
     * Default constructor. 
     */
    public MainPageStuff() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// WRITE DATA TEST SUCCESFULL
		/*
		Message message = new Message();
		message.setSender("Rajko");
		message.setReciever("Vlajko");
		message.setMessageContent("Oj tebra si vido evangelion?");
		message.setMessageRead(false);
		
		User user = new User();
		user.setAccountType("ADMINISTRATOR");
		user.setDateOfRegistry("12/08/2001");
		user.setFirstname("Vlajko");
		user.setPassword("IMTHEADMINBITCH123");
		user.setPhoneNumber("0900-SCAM-229");
		user.setSavedComentIds(null);
		user.setSavedTopicIds(null);
		user.setSubscribedSubforumNames(null);
		user.setSurname("Vlajkovic");
		user.setUsername("Vlajko");
		
		User user2 = new User();
		user2.setAccountType("USER");
		user2.setDateOfRegistry("22/08/2001");
		user2.setFirstname("Rajko");
		user2.setPassword("123");
		user2.setPhoneNumber("0900-1111-229");
		user2.setSavedComentIds(null);
		user2.setSavedTopicIds(null);
		user2.setSubscribedSubforumNames(null);
		user2.setSurname("Vlajkovic");
		user2.setUsername("Rajko");
		
		User user3 = new User();
		user3.setAccountType("USER");
		user3.setDateOfRegistry("15/08/2001");
		user3.setFirstname("Marko");
		user3.setPassword("124");
		user3.setPhoneNumber("0900-2211-229");
		user3.setSavedComentIds(null);
		user3.setSavedTopicIds(null);
		user3.setSubscribedSubforumNames(null);
		user3.setSurname("Piskovic");
		user3.setUsername("Marko");
		
		Data allData = new Data();
		List<User> users = new ArrayList<User>();
		List<Message>messages = new ArrayList<Message>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		messages.add(message);
		
		allData.setUsers(users);
		allData.setMessages(messages);
		allData.setComments(null);
		allData.setSubforums(null);
		allData.setTopics(null);
				
		Data.writeData(allData);
		
		
		####READ DATA TEST SUCCESFULL!
		
		
		
		for(User tmp: allData.getUsers()) {
		System.out.println(tmp.getUsername());
		}*/
		//request.getRequestDispatcher("/DaiTengoku.jsp").forward(request, response);
		HttpSession session = request.getSession();
		Data allData = Data.readData();
		//request.setAttribute("ADMIN", arg1);
		if(!fromPost) {
			
			
			if(allData == null) {
				allData = new Data();
			}
			session.setAttribute("allSubforums", allData.getSubforums());
		}
		
		session.setAttribute("CurrentForum", null);
		response.sendRedirect("Subforums.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("searchSubmit") != null) {
			fromPost = true;
			HttpSession session = request.getSession();
			Data allData = Data.readData();
			String searchItem = request.getParameter("search").toLowerCase();
			List<Subforum> allSubforums = new ArrayList<Subforum>();
			for(Subforum tmp: allData.getSubforums() ) {
				if(tmp.getName().toLowerCase().contains(searchItem)) {
					allSubforums.add(tmp);
				}
			}
			session.setAttribute("allSubforums", allSubforums);
		}
		doGet(request, response);
	}
}
