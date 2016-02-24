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
package org.kuali.kra.protocol.actions.decision;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public abstract class CommitteeDecisionRecuserEventBase<CD extends CommitteeDecision<?>> extends KcDocumentEventBase {
    
    private CD actionBean;
    
    /**
     * 
     * Constructs a CommitteeDecisionAbstainerEventBase.java.
     * @param document
     * @param decision
     */
    public CommitteeDecisionRecuserEventBase(ProtocolDocumentBase document, CD decision) {
        super("Recording Committee Decision " + getDocumentId(document), "", document);
        this.actionBean = decision;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.actionBean == null) {
            logMessage.append("null actionBean");
        } else {
            logMessage.append(actionBean.toString());
        }

        getLOGHook().debug(logMessage);

    }
    
    protected abstract Log getLOGHook();

    public Class<ExecuteCommitteeDecisionRecuserRule> getRuleInterfaceClass() {
        return ExecuteCommitteeDecisionRecuserRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteCommitteeDecisionRecuserRule<CD>) rule).proccessCommitteeDecisionRecuserRule((ProtocolDocumentBase) this.getDocument(), this.actionBean);
    }

}
