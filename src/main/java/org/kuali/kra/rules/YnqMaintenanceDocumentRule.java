/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import org.kuali.kra.bo.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class YnqMaintenanceDocumentRule  extends MaintenanceDocumentRuleBase {

    /**
     * Constructs a YnqMaintenanceDocumentRule.java.
     */
    public YnqMaintenanceDocumentRule() {
        super();
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */ 
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkYNQ(document);
    }
    
    /**
     * 
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    public boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkYNQ(document);
    }

    /**
     * 
     * This method to check Ynq's explanation and review date is required field based on answer.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkYNQ(MaintenanceDocument maintenanceDocument) {

        boolean valid = true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        Ynq newYnq = (Ynq) maintenanceDocument.getNewMaintainableObject().getBusinessObject();
        
        /* check if answer is YES / NO and explanation / date required for NA */
        
        if(newYnq.getNoOfAnswers() < Constants.ANSWER_YES_NO_NA) {
            if(newYnq.getExplanationRequiredFor() != null && newYnq.getExplanationRequiredFor().contains(Constants.ANSWER_NA)) {
                GlobalVariables.getErrorMap().putError("document.newMaintainableObject.explanationRequiredFor", KeyConstants.INVALID_EXPLANATION_REQUIRED_FOR);
                valid = false;
            }
            if(newYnq.getDateRequiredFor() != null && newYnq.getDateRequiredFor().contains(Constants.ANSWER_NA)) {
                GlobalVariables.getErrorMap().putError("document.newMaintainableObject.dateRequiredFor", KeyConstants.INVALID_DATE_REQUIRED_FOR);
                valid = false;
            }
        }
        return valid;
    }

}
