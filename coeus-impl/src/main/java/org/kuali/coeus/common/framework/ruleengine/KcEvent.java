package org.kuali.coeus.common.framework.ruleengine;

/**
 * KcEvents are used to pass data to KcBusinessRule classes declaring
 * KcEventMethod methods. Eventname is required as this is the key
 * to determine what rules to execute.
 */
public class KcEvent {

	private String eventName;
	
	public KcEvent(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}
