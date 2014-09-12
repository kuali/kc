package org.kuali.coeus.common.framework.ruleengine;

import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.MessageMap;

public class KcEventResult {
	
	private Boolean success;
	private MessageMap messageMap;
	private Map<String, AuditCluster> auditMap;
	
	public KcEventResult() {
		success = Boolean.TRUE;
		messageMap = new MessageMap();
		auditMap = new HashMap<>();
	}
	
	public KcEventResult(Boolean success) {
		this();
		this.success = success;
	}

	public MessageMap getMessageMap() {
		return messageMap;
	}
	public void setMessageMap(MessageMap messageMap) {
		this.messageMap = messageMap;
	}
	public Map<String, AuditCluster> getAuditMap() {
		return auditMap;
	}
	public void setAuditMap(Map<String, AuditCluster> auditMap) {
		this.auditMap = auditMap;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
