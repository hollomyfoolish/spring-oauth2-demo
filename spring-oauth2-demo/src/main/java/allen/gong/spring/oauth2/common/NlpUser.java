package allen.gong.spring.oauth2.common;

import java.util.List;
import java.util.Map;

public class NlpUser {
	private String userId;
	private Map<String, List<String>> attributes;
	
	public NlpUser(){
		
	}
	
	public NlpUser(String userId){
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
	}
	public Map<String, List<String>> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, List<String>> attr) {
		this.attributes = attr;
	}
}
