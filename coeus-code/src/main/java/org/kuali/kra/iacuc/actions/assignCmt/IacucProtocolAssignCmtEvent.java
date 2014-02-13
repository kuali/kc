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
package org.kuali.kra.iacuc.actions.assignCmt;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class IacucProtocolAssignCmtEvent extends KcDocumentEventBase {
 private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(IacucProtocolAssignCmtEvent.class);
    
    private IacucProtocolAssignCmtBean actionBean;
    
    public IacucProtocolAssignCmtEvent(ProtocolDocumentBase document, IacucProtocolAssignCmtBean actionBean) {
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

    public Class<IacucProtocolAssignCmtRule> getRuleInterfaceClass() {
        return IacucProtocolAssignCmtRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((IacucProtocolAssignCmtRule) rule).processAssignToCommittee((ProtocolDocumentBase) getDocument(), actionBean);
    }

  
}
