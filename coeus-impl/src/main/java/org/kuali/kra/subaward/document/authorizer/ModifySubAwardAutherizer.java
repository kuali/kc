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

package org.kuali.kra.subaward.document.authorizer;

import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.document.authorization.SubAwardTask;

/**
 * This class checks the authorization for modifySubAward...
 */
public class ModifySubAwardAutherizer extends SubAwardAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, SubAwardTask task) {

        SubAwardDocument subAwardDocument = task.getSubAwardDocument();
        if (subAwardDocument.getSubAward().getSubAwardId() != null) {

            if (hasPermission(userId, task.getSubAwardDocument().
            getSubAward(), PermissionConstants.MODIFY_SUBAWARD)) {
                subAwardDocument.getSubAward().setEditSubAward(true);
            }
            return !subAwardDocument.isViewOnly()
            && hasPermission(userId, task.getSubAwardDocument().
            getSubAward(), PermissionConstants.MODIFY_SUBAWARD)
           && !kraWorkflowService.isInWorkflow(subAwardDocument);
        } else {
            return canUserCreateSubAward(
            userId, subAwardDocument.getSubAward());

        }
    }

    /**.
     * This method is for checking whether user can create subAward
     *@param award the SubAward
     *@param userId the userId
     *@return boolean
     */
 protected boolean canUserCreateSubAward(String userId, SubAward award) {
        return hasUnitPermission(userId, Constants.
        MODULE_NAMESPACE_SUBAWARD, PermissionConstants.CREATE_SUBAWARD);
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
