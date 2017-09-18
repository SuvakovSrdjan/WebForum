package classesPackage;

import java.util.List;
import java.util.Map;

public class Comment {
	Integer parentTopicId;
	String author;
	String dateCreated;
	int parentComment;
	String commentContent;
	Map<String,Boolean> commentLikesMap;
	List<Integer> childComments;
	Boolean commentEdited;
	int id;
	
	
	
	public List<Integer> getChildComments() {
		return childComments;
	}
	public void setChildComments(List<Integer> childComments) {
		this.childComments = childComments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getParentTopicId() {
		return parentTopicId;
	}
	public void setParentTopicId(Integer parentTopicName) {
		this.parentTopicId = parentTopicName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getParentComment() {
		return parentComment;
	}
	public void setParentComment(int parentComment) {
		this.parentComment = parentComment;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Map<String, Boolean> getCommentLikesMap() {
		return commentLikesMap;
	}
	public void setCommentLikesMap(Map<String, Boolean> commentLikesMap) {
		this.commentLikesMap = commentLikesMap;
	}
	public Boolean getCommentEdited() {
		return commentEdited;
	}
	public void setCommentEdited(Boolean commentEdited) {
		this.commentEdited = commentEdited;
	}
	
}
