package mainPage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesPackage.Comment;
import classesPackage.Data;
import classesPackage.Subforum;
import classesPackage.Topic;
import classesPackage.User;

/**
 * Servlet implementation class TopicServlet
 */
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String redirect = "TopicView.jsp";
    Boolean comesFromPost = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int clickedTopic = -1;
		List<Integer>commentLikesList = new ArrayList<Integer>();
		List<Integer>commentDisLikesList = new ArrayList<Integer>();
		HttpSession session = request.getSession();
		if(!comesFromPost) {
		clickedTopic = Integer.parseInt(request.getParameter("topic"));
		} else {
			Topic dispTopic = (Topic) session.getAttribute("topic");
			clickedTopic = dispTopic.getId();
		}
		Data allData = Data.readData();
		Topic topicToDisplay = new Topic();
		
		int likes = 0, dislikes = 0, commentLikes = 0, commentDislikes = 0;
		List<Comment> commentsToDisplay = new ArrayList<Comment>();
		for(Topic tmp : allData.getTopics()) {
			if(tmp.getId() == clickedTopic) {
				topicToDisplay = tmp;
				for(Comment temp : allData.getComments()) {
					if(temp.getParentTopicId() == topicToDisplay.getId()) {
						if(temp.getParentComment()==0) {
							commentsToDisplay.add(temp);
							for(Map.Entry<String,Boolean> temporary : temp.getCommentLikesMap().entrySet()) {
								if(temporary.getValue() == true) {
									commentLikes++;
								}else if(temporary.getValue() == false) {
									commentDislikes++;
								}
						}
							commentLikesList.add(commentLikes);
							commentDisLikesList.add(commentDislikes);
						
					}
				}
				}
				for(Map.Entry<String,Boolean> temp : tmp.getLikesMap().entrySet()) {
					if(temp.getValue() == true) {
						likes++;
					}else if(temp.getValue() == false) {
						dislikes++;
					}
				}
			}
		}
		session.setAttribute("topic", topicToDisplay);
		session.setAttribute("topicLikes",likes);
		session.setAttribute("topicDislikes",dislikes);
		session.setAttribute("commentLikes",commentLikesList);
		session.setAttribute("commentDislikes",commentDisLikesList);
		session.setAttribute("commentsToDisplay", commentsToDisplay);
		response.sendRedirect(redirect);
		comesFromPost = false;
		redirect = "TopicView.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comesFromPost = true;
		HttpSession session = request.getSession();
		Data allData = Data.readData();
		if(request.getParameter("addComment") != null) {
			
			
			String comment = request.getParameter("commentText");
			String parentComment = new String();
			User commentAuthor = (User) session.getAttribute("user");
			Comment lastComment;
			int id = 0;
			
			Calendar cal = new GregorianCalendar();
			SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY");
			String dateOfCreation= sp.format(cal.getTime());
			
			if(request.getParameter("parrentComment") == null) {
				parentComment = "0";
			} else {
				parentComment = request.getParameter("parrentComment");
			}
			List<Comment> comments = allData.getComments();
			if(comments.size() > 0) {
				lastComment = comments.get(comments.size() - 1);
				if(lastComment != null) {
					id = lastComment.getId() +1;
				}
			}else {
				id = 0;
			}
			
			Topic dispTopic = (Topic) session.getAttribute("topic");
			Comment newComment = new Comment();
			newComment.setAuthor(commentAuthor.getUsername());
			newComment.setChildComments(null);
			newComment.setCommentContent(comment);
			newComment.setCommentEdited(false);
			newComment.setCommentLikesMap(Collections.<String,Boolean>emptyMap());
			newComment.setDateCreated(dateOfCreation);
			newComment.setId(id);
			newComment.setParentComment(Integer.parseInt(parentComment));
			newComment.setParentTopicId(dispTopic.getId());
			
			comments.add(newComment);
			allData.setComments(comments);
			Data.writeData(allData);
			redirect = "TopicView.jsp";
			
		}
		
		if(request.getParameter("Like") != null) {
			Topic dispTopic = (Topic) session.getAttribute("topic");
			int i = 0;
			User loggedUser = (User) session.getAttribute("user");
			List<Topic> allTopics = allData.getTopics();
			for(Topic tmp : allTopics) {
				i++;
				
				if(dispTopic.getId() == tmp.getId()) {
					if(!(tmp.getLikesMap().containsKey(loggedUser.getUsername()))){
						tmp.getLikesMap().put(loggedUser.getUsername(), true);
						allTopics.set(i-1, tmp);
					}
				}
			}
			allData.setTopics(allTopics);
			Data.writeData(allData);
			redirect = "TopicView.jsp";
			
		}
		
		if(request.getParameter("disLike") != null) {
			Topic dispTopic = (Topic) session.getAttribute("topic");
			int i = 0;
			User loggedUser = (User) session.getAttribute("user");
			List<Topic> allTopics = allData.getTopics();
			for(Topic tmp : allTopics) {
				i++;
				
				if(dispTopic.getId() == tmp.getId()) {
					if(!(tmp.getLikesMap().containsKey(loggedUser.getUsername()))){
						tmp.getLikesMap().put(loggedUser.getUsername(), false);
						allTopics.set(i-1, tmp);
					}
				}
			}
			allData.setTopics(allTopics);
			Data.writeData(allData);
			redirect = "TopicView.jsp";
			
		}
		
		if(request.getParameter("follow") != null) {
			User loggedUser = (User) session.getAttribute("user");
			Topic dispTopic = (Topic) session.getAttribute("topic");
			int i = 0;
			List<User> userList = allData.getUsers();
			if(loggedUser.getSavedTopicIds() != null) {
				if(!(loggedUser.getSavedTopicIds().contains(dispTopic.getId()))) {
					ArrayList<Integer> savedTopicIds = loggedUser.getSavedTopicIds();
					savedTopicIds.add(dispTopic.getId());
					loggedUser.setSavedTopicIds(savedTopicIds);
				}
			}else {
				ArrayList<Integer> savedTopicIds = new ArrayList<Integer>();
				savedTopicIds.add(dispTopic.getId());
				loggedUser.setSavedTopicIds(savedTopicIds);
			}
			for(User tmp: userList) {
				if(tmp.getUsername().equals(loggedUser.getUsername())) {
					userList.set(i, loggedUser);
				}
				i++;
			}
			allData.setUsers(userList);
			Data.writeData(allData);
			redirect = "TopicView.jsp";
			
		}
		if(request.getParameter("delete") != null) {
			User loggedUser =(User) session.getAttribute("user");
			Subforum currentSubforum = (Subforum) session.getAttribute("CurrentForum");
			Topic currentTopic = (Topic) session.getAttribute("topic");
			List<Topic> allTopics = allData.getTopics();
			int i = 0;
			int j = 0;
			Boolean allowedToDelete = false;
			for(String tmp : currentSubforum.getPlebModerators()) {
				if(loggedUser.getUsername().equals(tmp)) {
					allowedToDelete = true;
				}
			}
			if(loggedUser.getUsername().equals(currentSubforum.getModeratorInChief())) {
				allowedToDelete = true;
			}
			
			if(loggedUser.getUsername().equals(currentTopic.getAuthor())) {
				allowedToDelete = true;
			}
			if(loggedUser.getAccountType().equals("ADMIN")) {
				allowedToDelete = true;
			}
			if(allowedToDelete) {
				for(Topic tmp : allData.getTopics()) {
					if(tmp.getId()==currentTopic.getId()) {
						j=i;
					}
					i++;
				}
				allTopics.remove(j);
				allData.setTopics(allTopics);
				Data.writeData(allData);
				redirect = "SpecificSubforumServlet?subforum="+ currentSubforum.getName();
			}
		}
		if(request.getParameter("edit") != null) {
			Topic currentTopic = (Topic) session.getAttribute("topic");
			redirect = "AddTopicServlet?topic=" + currentTopic.getId()+"&action=Edit";
		}
		
		doGet(request, response);
	}

}
