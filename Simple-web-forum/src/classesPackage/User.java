package classesPackage;

import java.util.ArrayList;

public class User {
	
	String username;
	String password;
	String firstname;
	String surname;
	String accountType;
	String phoneNumber;
	String dateOfRegistry;
	ArrayList<String> subscribedSubforumNames;
	ArrayList<Integer> savedTopicIds;
	ArrayList<Integer> savedComentIds;
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfRegistry() {
		return dateOfRegistry;
	}
	public void setDateOfRegistry(String dateOfRegistry) {
		this.dateOfRegistry = dateOfRegistry;
	}
	public ArrayList<String> getSubscribedSubforumNames() {
		return subscribedSubforumNames;
	}
	public void setSubscribedSubforumNames(ArrayList<String> subscribedSubforumNames) {
		this.subscribedSubforumNames = subscribedSubforumNames;
	}
	public ArrayList<Integer> getSavedTopicIds() {
		return savedTopicIds;
	}
	public void setSavedTopicIds(ArrayList<Integer> savedTopicIds) {
		this.savedTopicIds = savedTopicIds;
	}
	public ArrayList<Integer> getSavedComentIds() {
		return savedComentIds;
	}
	public void setSavedComentIds(ArrayList<Integer> savedComentIds) {
		this.savedComentIds = savedComentIds;
	}
}
