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
package org.kuali.kra.irb.actions.responseapproval;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Validates the response approval of a protocol document.
 */
@SuppressWarnings("unchecked")
public class ProtocolResponseApprovalRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<ProtocolResponseApprovalEvent>  {

    private static final String APPROVAL_DATE_FIELD = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".approvalDate";
    private static final String EXPIRATION_DATE_FIELD = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".expirationDate";
    private static final String ACTION_DATE = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".actionDate";
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(ProtocolResponseApprovalEvent event) {
        boolean isValid = true;
        
        isValid &= validateDates(event.getProtocolApproveBean());
        
        return isValid;
    }
    
    private boolean validateDates(ProtocolApproveBean actionBean) {
        boolean isValid = true;
        
        if (actionBean.getApprovalDate() == null) {
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
            isValid = false;
        }
        
        if (actionBean.getExpirationDate() == null) {
            reportError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED);  
            isValid = false;
        }
        
        if (actionBean.getActionDate() == null) {
            reportError(ACTION_DATE, KeyConstants.ERROR_PROTOCOL_APPROVAL_ACTION_DATE_REQUIRED);  
            isValid = false;
        }
        
        return isValid;
    }
}
