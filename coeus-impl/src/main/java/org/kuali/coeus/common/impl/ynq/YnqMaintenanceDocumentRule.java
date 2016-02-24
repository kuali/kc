/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.impl.ynq;

import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

public class YnqMaintenanceDocumentRule  extends KcMaintenanceDocumentRuleBase {


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
