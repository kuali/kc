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

    @Override
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
