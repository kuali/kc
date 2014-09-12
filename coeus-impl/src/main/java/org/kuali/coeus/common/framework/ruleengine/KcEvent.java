package org.kuali.coeus.common.framework.ruleengine;

/**
 * KcEvents are used to pass data to KcBusinessRule classes declaring
 * KcEventMethod methods. Eventname is required as this is the key
 * to determine what rules to execute.
 */
public interface KcEvent {

	/**
	 * The events name that will be used to register rules and
	 * later determine which rules to be run. Should be namespaced in
	 * some fashion to avoid conflicts. Similar to KC-PD:saveDocument or
	 * KC-B:addPeriod
	 */
	public String getEventName();
}
