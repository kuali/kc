/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRule;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

public class ProposalLogMaintenanceDocumentRules extends MaintenanceDocumentRuleBase
    implements MaintenanceDocumentRule {
    
    /**
     * Checks to see if document is in valid state to save.
     * 
     * @see org.kuali.rice.kns.rules.MaintenanceDocumentRuleBase#isDocumentValidForSave(
     * org.kuali.rice.kns.document.MaintenanceDocument)
     
     * @param document the MaintenanceDocument to check
     * @return boolean
     */
    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument document) {
        boolean valid = super.isDocumentValidForSave(document);
        
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getBusinessObject();
        
        if (!isProposalStatusChangeValid(document)) {
            GlobalVariables.getMessageMap().putError(
                    "document.newMaintainableObject.logStatus", 
                    KeyConstants.ERROR_INVALID_STATUS_CHANGE, 
                    proposalLog.getProposalLogStatus().getDescription());
            valid = false;
        }
        
        if (ObjectUtils.isNotNull(proposalLog.getPiId()) && ObjectUtils.isNotNull(proposalLog.getRolodexId())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.rolodexId", 
                    KeyConstants.ERROR_MULTIPLE_PRINCIPAL_INVESTIGATORS, "");
            valid = false;
        }
        
        proposalLog.refreshReferenceObject("unit");
        if (StringUtils.isNotBlank(proposalLog.getLeadUnit()) && proposalLog.getUnit() == null) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.leadUnit", 
                    KeyConstants.ERROR_INVALID_LEADUNIT, "");
            
            valid = false;
        }
        
        return valid;
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = super.processCustomRouteDocumentBusinessRules(document);
        
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getBusinessObject();
        
        if (ObjectUtils.isNull(proposalLog.getPiId()) && ObjectUtils.isNull(proposalLog.getRolodexId())) {
            GlobalVariables.getMessageMap().putError("document.newMaintainableObject.piId", 
                    KeyConstants.ERROR_MISSING_PRINCIPAL_INVESTIGATOR, "");
            valid = false;
        }
        
        return valid;
    }
    
    private boolean isProposalStatusChangeValid(MaintenanceDocument document) {
        
        ProposalLog oldProposalLog = (ProposalLog) document.getOldMaintainableObject().getBusinessObject();
        ProposalLog newProposalLog = (ProposalLog) document.getNewMaintainableObject().getBusinessObject();
        if (oldProposalLog.getProposalNumber() != null
                && oldProposalLog.getLogStatus() != newProposalLog.getLogStatus() 
                && !(ProposalLogUtils.getProposalLogVoidStatusCode().equals(newProposalLog.getLogStatus()) 
                        || ProposalLogUtils.getProposalLogPendingStatusCode().equals(newProposalLog.getLogStatus()))) {
            return false;
        }
        
        return true;
    }
    
}
