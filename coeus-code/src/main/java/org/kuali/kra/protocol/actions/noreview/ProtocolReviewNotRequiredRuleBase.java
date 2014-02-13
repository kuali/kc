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
package org.kuali.kra.protocol.actions.noreview;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class managed the business rules of marking a protocol as not required.
 */
public abstract class ProtocolReviewNotRequiredRuleBase extends KcTransactionalDocumentRuleBase implements ExecuteProtocolReviewNotRequiredRule {

    /**
     * @see org.kuali.kra.irb.actions.noreview.ExecuteProtocolReviewNotRequiredRule#processReviewNotRequiredRule(org.kuali.kra.irb.ProtocolDocumentBase, org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean)
     */
    public boolean processReviewNotRequiredRule(ProtocolDocumentBase document, ProtocolReviewNotRequiredBean actionBean) {
        boolean valid = true;
        String fieldNameStarter = "actionHelper.protocolReviewNotRequiredBean.";
        if (actionBean.getActionDate() == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldNameStarter + "actionDate", KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
        }
        
        if (actionBean.getDecisionDate() == null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(fieldNameStarter + "decisionDate", KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
        }
        
        return valid;
    }


}
