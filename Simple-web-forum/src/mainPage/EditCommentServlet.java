package mainPage;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesPackage.Comment;
import classesPackage.Data;

/**
 * Servlet implementation class EditCommentServlet
 */
public class EditCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String redirect = "EditComment.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Data allData = Data.readData();
		Comment commentToEdit = new Comment();
		if(request.getParameter("comment")!=null) {
			String commentIdString = request.getParameter("comment");
			Integer commentId = Integer.parseInt(commentIdString);
			for(Comment tmp: allData.getComments()) {
				if(tmp.getId() == commentId) {
					commentToEdit = tmp;
				}
			}
		}
		session.setAttribute("commentToEdit", commentToEdit);
		response.sendRedirect(redirect);
		redirect = "EditComment.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Data allData = Data.readData();
		Comment commentToEdit = new Comment();
		List<Comment> allComments = allData.getComments();
		if(request.getParameter("addComment")!=null) {
			Comment dispComment = (Comment) session.getAttribute("pinnedComment");
			int i=0,j=0;
			commentToEdit = (Comment) session.getAttribute("commentToEdit");
			commentToEdit.setCommentContent(request.getParameter("commentText"));
			commentToEdit.setCommentEdited(true);
			for(Comment tmp: allData.getComments()) {
				if(tmp.getId() == commentToEdit.getId()) {
					j=i;
				}
				i++;
			}
			allComments.set(j,commentToEdit);
			allData.setComments(allComments);
			Data.writeData(allData);
			redirect = "InfiniteCommentServlet?comment="+dispComment.getId();
		}
		doGet(request, response);
	}

}
