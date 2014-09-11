package org.kuali.coeus.common.framework.ruleengine;

public class KcEventBase implements KcEvent {

	private String eventName;
	
	public KcEventBase(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}
