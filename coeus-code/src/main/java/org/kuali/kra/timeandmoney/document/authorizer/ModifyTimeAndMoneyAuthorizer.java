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
package org.kuali.kra.timeandmoney.document.authorizer;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.document.authorization.TimeAndMoneyTask;

/**
 * The Modify Time and Money Authorizer checks to see if the user has 
 * permission to modify a award. Authorization depends upon whether
 * the award is being created or modified.  For creation, the
 * user needs the CREATE_AWARD permission.  If the award is being
 * modified, the user only needs to have the MODIFY_AWARD permission 
 * for that award.
 */
public class ModifyTimeAndMoneyAuthorizer extends TimeAndMoneyAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, TimeAndMoneyTask task) {
        
        TimeAndMoneyDocument timeAndMoneyDocument = task.getTimeAndMoneyDocument();
        return timeAndMoneyDocument.isNew() ? canUserCreateAward(userId, timeAndMoneyDocument) : canUserModifyAward(userId, timeAndMoneyDocument);
    }
    
    /**
     * 
     * We have to consider the case when we are saving the award for the first time.
     * 
     * @param username
     * @param award
     * @return
     */
    protected boolean canUserCreateAward(String userId, TimeAndMoneyDocument timeAndMoneyDocument){
        return true;
        /*boolean hasPermission = Boolean.FALSE;
        if (timeAndMoneyDocument.getDocumentNumber() == null) {
            
            // We have to consider the case when we are saving the document for the first time.
            
            String unitNumber = null;
            
            // If the unit number is not specified, we will let the save operation continue because it
            // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
            // we will indicate that the user does not have permission to do that.
            
            if (unitNumber != null) {
                UnitAuthorizationService auth = KraServiceLocator.getService(UnitAuthorizationService.class);
                hasPermission = auth.hasPermission(userId, unitNumber, AwardPermissionConstants.CREATE_AWARD.getAwardPermission());
            }
        }
        
        
        return hasPermission;*/
        //return hasUnitPermission(userId, AwardPermissionConstants.CREATE_AWARD.getAwardPermission());
    }
    
    /**
     * 
     * After the initial save, the award can only be modified has the required permission.
     * 
     * @param username
     * @param award
     * @return
     */
    protected boolean canUserModifyAward(String userId, TimeAndMoneyDocument doc){
        //return true;
        /*
         * After the initial save, the proposal can only be modified if it is not in workflow
         * and the user has the require permission.
         */
         
        return !doc.isViewOnly() &&
                        hasPermission(userId, doc, AwardPermissionConstants.MODIFY_AWARD.getAwardPermission()) &&
                        !kraWorkflowService.isInWorkflow(doc);
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
