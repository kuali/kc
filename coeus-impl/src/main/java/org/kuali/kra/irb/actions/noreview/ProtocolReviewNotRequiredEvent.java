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
package org.kuali.kra.irb.actions.noreview;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class ProtocolReviewNotRequiredEvent extends KcDocumentEventBase {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolReviewNotRequiredEvent.class);
    
    private ProtocolReviewNotRequiredBean actionBean;
    
    /**
     * 
     * Constructs a ProtocolReviewNotRequiredEvent.java.
     * @param document
     * @param actionBean
     */
    public ProtocolReviewNotRequiredEvent(ProtocolDocument document, ProtocolReviewNotRequiredBean actionBean) {
        super("review not required for document " + getDocumentId(document), "", document);
        this.actionBean = actionBean;
        logEvent();
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        if (this.actionBean == null) {
            logMessage.append("null actionBean");
        } else {
            logMessage.append(actionBean.toString());
        }
        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ExecuteProtocolReviewNotRequiredRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteProtocolReviewNotRequiredRule) rule).processReviewNotRequiredRule((ProtocolDocument) getDocument(), actionBean);
    }

}
