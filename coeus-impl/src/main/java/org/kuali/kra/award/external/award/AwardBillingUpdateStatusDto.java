package org.kuali.kra.award.external.award;

import java.util.ArrayList;
import java.util.List;

public class AwardBillingUpdateStatusDto {

	private List<String> errorMessages;
	private String awardNumber;
	private boolean success = false;
	
	public AwardBillingUpdateStatusDto() {
		errorMessages = new ArrayList<String>();
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public String getAwardNumber() {
		return awardNumber;
	}
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
