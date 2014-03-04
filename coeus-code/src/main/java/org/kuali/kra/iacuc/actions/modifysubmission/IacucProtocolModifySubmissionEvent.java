/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.modifysubmission;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class IacucProtocolModifySubmissionEvent extends KcDocumentEventBase {
    private  IacucProtocolModifySubmissionBean actionBean;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(IacucProtocolModifySubmissionEvent.class);

    public IacucProtocolModifySubmissionEvent(ProtocolDocumentBase document, IacucProtocolModifySubmissionBean actionBean) {
        super("Modifying submission " + getDocumentId(document), "", document);
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

    public Class<? extends BusinessRule> getRuleInterfaceClass() {
        return IacucProtocolModifySubmissionRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((IacucProtocolModifySubmissionRule) rule).processModifySubmissionRule((ProtocolDocumentBase) getDocument(), actionBean);
    }


}
