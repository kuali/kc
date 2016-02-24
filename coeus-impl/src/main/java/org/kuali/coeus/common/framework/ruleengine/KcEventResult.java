/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.framework.ruleengine;

import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.krad.util.AuditCluster;
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
