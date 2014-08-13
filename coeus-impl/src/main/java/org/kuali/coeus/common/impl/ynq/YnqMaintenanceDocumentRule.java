/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.ynq;

import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class YnqMaintenanceDocumentRule  extends MaintenanceDocumentRuleBase {


    public YnqMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    public boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkYNQ(document);
    }
    
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
        Ynq newYnq = (Ynq) maintenanceDocument.getNewMaintainableObject().getDataObject();
        
        /* check if answer is YES / NO and explanation / date required for NA */
        
        if(newYnq.getNoOfAnswers() < Constants.ANSWER_YES_NO_NA) {
            if(newYnq.getExplanationRequiredFor() != null && newYnq.getExplanationRequiredFor().contains(Constants.ANSWER_NA)) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.explanationRequiredFor", KeyConstants.INVALID_EXPLANATION_REQUIRED_FOR);
                valid = false;
            }
            if(newYnq.getDateRequiredFor() != null && newYnq.getDateRequiredFor().contains(Constants.ANSWER_NA)) {
                GlobalVariables.getMessageMap().putError("document.newMaintainableObject.dateRequiredFor", KeyConstants.INVALID_DATE_REQUIRED_FOR);
                valid = false;
            }
        }
        return valid;
    }

}
