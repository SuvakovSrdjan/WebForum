package classesPackage;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Message {
	String sender;
	String reciever;
	String messageContent;
	Boolean messageRead;
	
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Boolean getMessageRead() {
		return messageRead;
	}
	public void setMessageRead(Boolean messageRead) {
		this.messageRead = messageRead;
	}
	
	
	public static synchronized void writeData(Message allData) {
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

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(allData);
			System.out.println(jsonInString);
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
