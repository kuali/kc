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
package org.kuali.coeus.common.committee.impl.document.authorization;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is the CommitteeBase Document Authorizer.  It determines the edit modes and
 * document actions for all committee documents.
 */
public abstract class CommitteeDocumentAuthorizerBase extends KcTransactionalDocumentAuthorizerBase {

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        String userId = user.getPrincipalId();
        CommitteeDocumentBase committeeDocument = (CommitteeDocumentBase) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            if (canCreateCommittee(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteCommitteeTask(userId, committeeDocument, TaskName.MODIFY_COMMITTEE)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteCommitteeTask(userId, committeeDocument, TaskName.VIEW_COMMITTEE)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }


        }

        if (canModify(committeeDocument, user)) {
            editModes.add(KraAuthorizationConstants.CAN_MODIFY);
        }

        return editModes;
    }
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) { 
        return canCreateCommittee(user);
    }
    
    @Override
    public boolean canOpen(Document document, Person user) {
        CommitteeDocumentBase committeeDocument = (CommitteeDocumentBase) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            return canCreateCommittee(user);
        }
        return canExecuteCommitteeTask(user.getPrincipalId(), (CommitteeDocumentBase) document, TaskName.VIEW_COMMITTEE);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return !isFinal(document) &&
               canExecuteCommitteeTask(user.getPrincipalId(), (CommitteeDocumentBase) document, TaskName.MODIFY_COMMITTEE);
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return !isFinal(document) && super.canRoute(document, user)
                && !((CommitteeDocumentBase) document).isViewOnly();
    }
    
    @Override
    public boolean canBlanketApprove(Document document, Person user) {
        return !isFinal(document) && super.canBlanketApprove(document, user)
                && !((CommitteeDocumentBase) document).isViewOnly();
    }
    
    @Override
    public boolean canCancel(Document document, Person user) {
        return !isFinal(document) && super.canCancel(document, user)
                && !((CommitteeDocumentBase) document).isViewOnly();
    }
    
    @Override
    public boolean canAcknowledge(Document document, Person user) {
        return false;
    }
    
    
    @Override
    public boolean canApprove(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canDisapprove(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canReload(Document document, Person user) {
        return isFinal(document);
    }
    
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * Does the user have permission to create a committee?
     * @param user the user
     * @return true if the user can create a committee; otherwise false
     */
    private boolean canCreateCommittee(Person user) {
        ApplicationTask task = new ApplicationTask(getAddCommitteeTaskNameHook());
        return getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), task);
    }
    
    protected abstract String getAddCommitteeTaskNameHook();

    /**
     * Does the user have permission to execute the given task for a committee?
     * @param username the user's username
     * @param doc the committee document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteCommitteeTask(String userId, CommitteeDocumentBase doc, String taskName) {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(taskName, doc.getCommittee());       
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }

    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);

    protected abstract String getPermissionNameForModifyCommitteeHook();
    /*
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }
    */
    
    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }

    public boolean canModify(CommitteeDocumentBase document, Person user) {
        return getAuthorizationService().hasPermission(user.getPrincipalId(), document.getCommittee(), getPermissionNameForModifyCommitteeHook());
    }

    protected KcWorkflowService getWorkFlowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
    }

    protected KcAuthorizationService getAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }

}
