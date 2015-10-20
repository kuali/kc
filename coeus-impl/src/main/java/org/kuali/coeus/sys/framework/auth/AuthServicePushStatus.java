package org.kuali.coeus.sys.framework.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AuthServicePushStatus {

	private int numberOfUsers;
	private int numberAdded;
	private int numberUpdated;
	private int numberSame;
	private int numberRemoved;
	private List<String> errors = new ArrayList<>();
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
	public int getNumberAdded() {
		return numberAdded;
	}
	public void setNumberAdded(int numberAdded) {
		this.numberAdded = numberAdded;
	}
	public void addNumberAdded() {
		this.numberAdded++;
	}
	public int getNumberUpdated() {
		return numberUpdated;
	}
	public void setNumberUpdated(int numberUpdated) {
		this.numberUpdated = numberUpdated;
	}
	public void addNumberUpdated() {
		this.numberUpdated++;
	}
	public int getNumberSame() {
		return numberSame;
	}
	public void setNumberSame(int numberSame) {
		this.numberSame = numberSame;
	}
	public void addNumberSame() {
		this.numberSame++;
	}
	public int getNumberRemoved() {
		return numberRemoved;
	}
	public void setNumberRemoved(int numberRemoved) {
		this.numberRemoved = numberRemoved;
	}
	public void addNumberRemoved() {
		this.numberRemoved++;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
