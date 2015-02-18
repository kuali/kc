package org.kuali.coeus.award.contacts;

import com.codiform.moo.annotation.Optionality;
import com.codiform.moo.annotation.Property;

public class InvestigatorDto {

	private String personId;
	private String fullName;
	private String emailAddress;
	@Property(source = "mvel:person.?userName", optionality=Optionality.OPTIONAL)
	private String userName;
	private String roleCode;
	private String projectRole;
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getProjectRole() {
		return projectRole;
	}
	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}
}
