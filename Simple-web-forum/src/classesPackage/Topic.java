package classesPackage;

import java.util.List;
import java.util.Map;

public class Topic {
	String name;
	String parentSubforum;
	String topicType;
	String imageLink;
	String author;
	String content;
	String dateCreated;
	List<Integer> commentIds;
	Map<String, Boolean> likesMap; // <["pera"] , true = like, false  = dislike>
	int id;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentSubforum() {
		return parentSubforum;
	}
	public void setParentSubforum(String parentSubforum) {
		this.parentSubforum = parentSubforum;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public List<Integer> getCommentIds() {
		return commentIds;
	}
	public void setCommentIds(List<Integer> commentIds) {
		this.commentIds = commentIds;
	}
	public Map<String, Boolean> getLikesMap() {
		return likesMap;
	}
	public void setLikesMap(Map<String, Boolean> likesMap) {
		this.likesMap = likesMap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
