package classesPackage;

import java.util.ArrayList;

public class Subforum {
	
	String name;
	String description;
	String iconURL;
	ArrayList<String> rules;
	String moderatorInChief;
	ArrayList<String> plebModerators;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public ArrayList<String> getRules() {
		return rules;
	}
	public void setRules(ArrayList<String> rules) {
		this.rules = rules;
	}
	public String getModeratorInChief() {
		return moderatorInChief;
	}
	public void setModeratorInChief(String moderatorInChief) {
		this.moderatorInChief = moderatorInChief;
	}
	public ArrayList<String> getPlebModerators() {
		return plebModerators;
	}
	public void setPlebModerators(ArrayList<String> plebModerators) {
		this.plebModerators = plebModerators;
	}
}
