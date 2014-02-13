/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol.actions.decision;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * This class...
 */
public abstract class CommitteeDecisionAbstainerEventBase<CD extends CommitteeDecision<?>> extends KcDocumentEventBase {
    
    private CD actionBean;
    
    /**
     * 
     * Constructs a CommitteeDecisionAbstainerEventBase.java.
     * @param document
     * @param decision
     */
    public CommitteeDecisionAbstainerEventBase(ProtocolDocumentBase document, CD decision) {
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

    public Class<ExecuteCommitteeDecisionAbstainerRule> getRuleInterfaceClass() {
        return ExecuteCommitteeDecisionAbstainerRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteCommitteeDecisionAbstainerRule<CD>) rule).proccessCommitteeDecisionAbstainerRule((ProtocolDocumentBase) this.getDocument(), this.actionBean);
    }

}
