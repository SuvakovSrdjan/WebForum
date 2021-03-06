package mainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesPackage.Comment;
import classesPackage.Data;
import classesPackage.Message;
import classesPackage.Subforum;
import classesPackage.Topic;
import classesPackage.User;

/**
 * Servlet implementation class UserPageServlet
 */
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String redirect = "UserPage.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Data allData = Data.readData();
		List<Message> messagesForDisplay= new ArrayList<Message>(); 
		List<Message> messagesToReturnToData = new ArrayList<Message>();
		List<Subforum> subforumsForDisp = new  ArrayList<Subforum>();
		List<Topic> topicsForDisp = new  ArrayList<Topic>();
		List<Comment> commentsForDisp = new  ArrayList<Comment>();
		User loggedUser = (User) session.getAttribute("user");
		for(Message tmp : allData.getMessages()) {
			if(tmp.getReciever().equals((loggedUser.getUsername()))) {
				tmp.setMessageRead(true);
				messagesForDisplay.add(tmp);
			}
			messagesToReturnToData.add(tmp);
		}
		for(Subforum tmp : allData.getSubforums()) {
			if(loggedUser.getSubscribedSubforumNames().contains(tmp.getName())) {
				subforumsForDisp.add(tmp);
			}
		}
		
		for(Topic tmp : allData.getTopics()) {
			if(loggedUser.getSavedTopicIds().contains(tmp.getId())) {
				topicsForDisp.add(tmp);
			}
		}
		for(Comment tmp : allData.getComments()) {
			if(loggedUser.getSavedComentIds().contains(tmp.getId())) {
				commentsForDisp.add(tmp);
			}
		}
		session.setAttribute("dispMsg", messagesForDisplay);
		session.setAttribute("dispForum", subforumsForDisp);
		session.setAttribute("dispTopic", topicsForDisp);
		session.setAttribute("dispComment", commentsForDisp);
		allData.setMessages(messagesToReturnToData);
		Data.writeData(allData);
		response.sendRedirect(redirect);
		redirect = "UserPage.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("sendMsg") != null) {
			String message =request.getParameter("MsgText");
			String sendto = request.getParameter("UsernameForMsg");
			HttpSession session = request.getSession();
			User sender = (User) session.getAttribute("user");
			Data allData = Data.readData();
			Message newMsg = new Message();
			
			newMsg.setMessageContent(message);
			newMsg.setMessageRead(false);
			newMsg.setReciever(sendto);
			newMsg.setSender(sender.getUsername());
			
			List<Message> messageList= allData.getMessages();
			messageList.add(newMsg);
			allData.setMessages(messageList);
			Data.writeData(allData);
		}
		doGet(request, response);
	}

}
