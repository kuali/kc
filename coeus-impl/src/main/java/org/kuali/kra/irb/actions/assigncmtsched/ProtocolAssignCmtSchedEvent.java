/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.actions.assigncmtsched;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * The event that occurs when the IRB Administrator assigns a protocol
 * to a committee/schedule.
 */
public class ProtocolAssignCmtSchedEvent extends KcDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolAssignCmtSchedEvent.class);
    
    private ProtocolAssignCmtSchedBean actionBean;
    
    public ProtocolAssignCmtSchedEvent(ProtocolDocument document, ProtocolAssignCmtSchedBean actionBean) {
        super("Submitting for review for document " + getDocumentId(document), "", document);
        this.actionBean = actionBean;
        logEvent();
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.actionBean == null) {
            logMessage.append("null actionBean");
        }
        else {
            logMessage.append(actionBean.toString());
        }

        LOG.debug(logMessage);
    }

    public Class getRuleInterfaceClass() {
        return ExecuteProtocolAssignCmtSchedRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteProtocolAssignCmtSchedRule) rule).processAssignToCommitteeSchedule((ProtocolDocument) getDocument(), actionBean);
    }
}
