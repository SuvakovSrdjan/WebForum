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
 * Servlet implementation class InfiniteCommentServlet
 */
public class InfiniteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String redirect = "InfiniteComment.jsp";
	 Boolean comesFromPost = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfiniteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int clickedComment = -1;
		List<Integer>commentLikesList = new ArrayList<Integer>();
		List<Integer>commentDisLikesList = new ArrayList<Integer>();
		HttpSession session = request.getSession();
		if(!comesFromPost) {
		clickedComment = Integer.parseInt(request.getParameter("comment"));
		} else {
			Comment dispComment = (Comment) session.getAttribute("pinnedComment");
			clickedComment = dispComment.getId();
		}
		Data allData = Data.readData();
		Comment pinnedComment = new Comment();
		
		int likes = 0, dislikes = 0, commentLikes = 0, commentDislikes = 0;
		List<Comment> commentsToDisplayINF = new ArrayList<Comment>();
		for(Comment tmp : allData.getComments()) {
			if(tmp.getId() == clickedComment) {
				pinnedComment = tmp;
				for(Comment temp : allData.getComments()) {
					if(temp.getParentComment() == pinnedComment.getId()) {
							commentsToDisplayINF.add(temp);
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
				
				for(Map.Entry<String,Boolean> temporary : tmp.getCommentLikesMap().entrySet()) {
					if(temporary.getValue() == true) {
						likes++;
					}else if(temporary.getValue() == false) {
						dislikes++;
					}
				}
			}
		
		}
		session.setAttribute("pinnedComment", pinnedComment);
		session.setAttribute("pinnedCommentLikes",likes);
		session.setAttribute("pinnedCommentDislikes",dislikes);
		session.setAttribute("commentLikes",commentLikesList);
		session.setAttribute("commentDislikes",commentDisLikesList);
		session.setAttribute("commentsToDisplayINF", commentsToDisplayINF);
		response.sendRedirect(redirect);
		comesFromPost = false;
		redirect = "InfiniteComment.jsp";
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
			Comment parentComment =(Comment) session.getAttribute("pinnedComment");
			User commentAuthor = (User) session.getAttribute("user");
			Comment lastComment;
			int id = 0;
			
			Calendar cal = new GregorianCalendar();
			SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY");
			String dateOfCreation= sp.format(cal.getTime());
			
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
			newComment.setParentComment(parentComment.getId());
			newComment.setParentTopicId(dispTopic.getId());
			
			comments.add(newComment);
			allData.setComments(comments);
			Data.writeData(allData);
			redirect = "InfiniteComment.jsp";
			
		}
		
		if(request.getParameter("Like") != null) {
			Comment dispComment = (Comment) session.getAttribute("pinnedComment");
			int i = 0;
			User loggedUser = (User) session.getAttribute("user");
			List<Comment> allComment = allData.getComments();
			for(Comment tmp : allComment) {
				i++;
				
				if(dispComment.getId() == tmp.getId()) {
					if(!(tmp.getCommentLikesMap().containsKey(loggedUser.getUsername()))){
						tmp.getCommentLikesMap().put(loggedUser.getUsername(), true);
						allComment.set(i-1, tmp);
					}
				}
			}
			allData.setComments(allComment);
			Data.writeData(allData);
			redirect = "InfiniteComment.jsp";
			
		}
		
		if(request.getParameter("disLike") != null) {
			Comment dispComment = (Comment) session.getAttribute("pinnedComment");
			int i = 0;
			User loggedUser = (User) session.getAttribute("user");
			List<Comment> allComment = allData.getComments();
			for(Comment tmp : allComment) {
				i++;
				
				if(dispComment.getId() == tmp.getId()) {
					if(!(tmp.getCommentLikesMap().containsKey(loggedUser.getUsername()))){
						tmp.getCommentLikesMap().put(loggedUser.getUsername(), false);
						allComment.set(i-1, tmp);
					}
				}
			}
			allData.setComments(allComment);
			Data.writeData(allData);
			redirect = "InfiniteComment.jsp";
			
		}
		
		if(request.getParameter("follow") != null) {
			User loggedUser = (User) session.getAttribute("user");
			Comment pinnedComment = (Comment) session.getAttribute("pinnedComment");
			int i = 0;
			List<User> userList = allData.getUsers();
			if(loggedUser.getSavedComentIds() != null) {
				if(!(loggedUser.getSavedComentIds().contains(pinnedComment.getId()))) {
					ArrayList<Integer> savedCommentIDs = loggedUser.getSavedComentIds();
					savedCommentIDs.add(pinnedComment.getId());
					loggedUser.setSavedComentIds(savedCommentIDs);
				}
			}else {
				ArrayList<Integer> savedCommentIDs = new ArrayList<Integer>();
				savedCommentIDs.add(pinnedComment.getId());
				loggedUser.setSavedComentIds(savedCommentIDs);
			}
			for(User tmp: userList) {
				if(tmp.getUsername().equals(loggedUser.getUsername())) {
					userList.set(i, loggedUser);
				}
				i++;
			}
			allData.setUsers(userList);
			Data.writeData(allData);
			redirect = "InfiniteComment.jsp";
			
		}
		
		if(request.getParameter("edit") != null) {
			Comment sentComment = (Comment) session.getAttribute("pinnedComment");
			redirect = "EditCommentServlet?comment="+sentComment.getId();
		}
		
		if(request.getParameter("delete") != null) {
			User loggedUser =(User) session.getAttribute("user");
			Comment pinnedComment = (Comment) session.getAttribute("pinnedComment");
			Subforum currentSubforum = (Subforum) session.getAttribute("CurrentForum");
			int currentTopicID = pinnedComment.getParentTopicId();
			List<Comment> allComments = allData.getComments();
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
			
			if(loggedUser.getUsername().equals(pinnedComment.getAuthor())) {
				allowedToDelete = true;
			}
			if(loggedUser.getAccountType().equals("ADMIN")) {
				allowedToDelete = true;
			}
			if(allowedToDelete) {
				for(Comment tmp : allData.getComments()) {
					if(tmp.getId()==pinnedComment.getId()) {
						j=i;
					}
					i++;
				}
				allComments.remove(j);
				allData.setComments(allComments);
				Data.writeData(allData);
				redirect = "TopicServlet?topic="+ currentTopicID;
			}
			
		}
		doGet(request, response);
	}

}
