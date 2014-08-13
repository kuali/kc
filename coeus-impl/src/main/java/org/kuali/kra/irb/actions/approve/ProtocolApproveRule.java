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
package org.kuali.kra.irb.actions.approve;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Encapsulates the rules for approving a Protocol.
 */
public class ProtocolApproveRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<ProtocolApproveEvent> {
    
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String ACTION_DATE_FIELD = "actionDate";
    
    @Override
    public boolean processRules(ProtocolApproveEvent event) {
        boolean isValid = true;
        
        if (event.getProtocolApproveBean().getApprovalDate() == null) {
            isValid = false;
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);  
        }
        
        if (event.getProtocolApproveBean().getExpirationDate() == null) {
            isValid = false;
            reportError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED);  
        }
        
        if (event.getProtocolApproveBean().getActionDate() == null) {
            isValid = false;
            reportError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_GENERIC_ACTION_DATE_REQUIRED);  
        }
        
        return isValid;
    }
}
