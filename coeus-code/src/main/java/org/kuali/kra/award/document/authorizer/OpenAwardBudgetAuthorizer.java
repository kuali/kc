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
package org.kuali.kra.award.document.authorizer;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;

/**
 * The OpenAwardBudgetAuthorizer checks to see if the user has 
 * the necessary permission to open budgets.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class OpenAwardBudgetAuthorizer extends AwardAuthorizer {

    private KcWorkflowService kraWorkflowService;

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.award.document.authorizer.AwardAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.award.document.authorization.AwardTask)
     */
    public boolean isAuthorized(String userId, AwardTask task) {
        AwardDocument doc = task.getAward().getAwardDocument();
        
        return hasUnitPermission(userId, doc.getLeadUnitNumber(), Constants.MODULE_NAMESPACE_AWARD_BUDGET, 
                    AwardPermissionConstants.MODIFY_AWARD_BUDGET.getAwardPermission())
            || hasUnitPermission(userId, doc.getLeadUnitNumber(), Constants.MODULE_NAMESPACE_AWARD_BUDGET, 
                    AwardPermissionConstants.VIEW_AWARD_BUDGET.getAwardPermission())
            || kraWorkflowService.hasWorkflowPermission(userId, doc);       
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}