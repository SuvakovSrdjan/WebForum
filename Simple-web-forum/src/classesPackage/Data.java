package classesPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Data {
	List<Comment> comments;
	List<Message> messages;
	List<Subforum> subforums;
	List<Topic> topics;
	List<User> users;
	
	public Data(){
		this.comments = new ArrayList<Comment>();
		this.messages = new ArrayList<Message>();
		this.subforums = new ArrayList<Subforum>();
		this.topics = new ArrayList<Topic>();
		this.users = new ArrayList<User>();
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Subforum> getSubforums() {
		return subforums;
	}

	public void setSubforums(List<Subforum> subforums) {
		this.subforums = subforums;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static synchronized Data readData() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Data allData = mapper.readValue(new File("/home/airjetsrka/Documents/ForumData.json"), Data.class);
		return allData;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public static synchronized void writeData(Data allData) {
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in file
		try {
			mapper.writeValue(new File("/home/airjetsrka/Documents/ForumData.json"), allData);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
