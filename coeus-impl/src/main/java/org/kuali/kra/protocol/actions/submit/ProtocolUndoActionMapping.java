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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.kra.protocol.drools.brms.FactBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

/*
 * This is the post condition attributes for a protocol action
 */
public class ProtocolUndoActionMapping implements FactBean {
    
    String actionTypeCode;
    String submissionTypeCode;
    String protocolStatusCode;
    
    boolean protocolSubmissionToBeDeleted = false;
    
    ProtocolBase protocol;
    
    ProtocolSubmissionBase protocolSubmission;
    
    ProtocolActionBase protocolAction;
    
    public ProtocolUndoActionMapping(String actionTypeCode, String submissionTypeCode, String protocolStatusCode) {
        super();
        this.actionTypeCode=actionTypeCode;
        this.submissionTypeCode = submissionTypeCode;
        this.protocolStatusCode = protocolStatusCode;
    }
    
    public ProtocolSubmissionBase getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmissionBase protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }
    
    public String getActionTypeCode() {
        return actionTypeCode;
    }
    
    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }
    
    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }
    
    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }
    
    public ProtocolActionBase getProtocolAction() {
        return protocolAction;
    }

    public void setProtocolAction(ProtocolActionBase protocolAction) {
        this.protocolAction = protocolAction;
    }

    public boolean isProtocolSubmissionToBeDeleted() {
        return protocolSubmissionToBeDeleted;
    }

    public void setProtocolSubmissionToBeDeleted(boolean protocolSubmissionToBeDeleted) {
        this.protocolSubmissionToBeDeleted = protocolSubmissionToBeDeleted;
    }
    
}
