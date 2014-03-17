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
package org.kuali.kra.iacuc.actions.noreview;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.noreview.ExecuteProtocolReviewNotRequiredRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class IacucProtocolReviewNotRequiredEvent extends KcDocumentEventBase {
    
    private IacucProtocolReviewNotRequiredBean actionBean;
    
    /**
     * 
     * Constructs a ProtocolReviewNotRequiredEvent.java.
     * @param document
     * @param actionBean
     */
    public IacucProtocolReviewNotRequiredEvent(ProtocolDocumentBase document, IacucProtocolReviewNotRequiredBean actionBean) {
        super("review not required for document " + getDocumentId(document), "", document);
        this.actionBean = actionBean;
        logEvent();
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ExecuteProtocolReviewNotRequiredRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ExecuteProtocolReviewNotRequiredRule) rule).processReviewNotRequiredRule((ProtocolDocumentBase) getDocument(), actionBean);
    }

    @Override
    protected void logEvent() {

        
    }

}
