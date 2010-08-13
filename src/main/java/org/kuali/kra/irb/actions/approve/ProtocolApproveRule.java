/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.approve;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Validate the approval of a protocol document.
 */
public class ProtocolApproveRule extends ResearchDocumentRuleBase implements ExecuteProtocolApproveRule {
   
    public boolean processApproveRule(ProtocolDocument document, ProtocolApproveBean actionBean) {
        boolean valid = true;
        if(actionBean.getApprovalDate() == null) {
            valid = false;
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_APPROVE_ACTION_PROPERTY_KEY + ".approvalDate", 
                                                   KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);  
        }
        if(actionBean.getExpirationDate() == null) {
            valid = false;
            GlobalVariables.getErrorMap().putError(Constants.PROTOCOL_APPROVE_ACTION_PROPERTY_KEY + ".expirationDate", 
                                                   KeyConstants.ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED);  
        }
        return valid;
    }
}
