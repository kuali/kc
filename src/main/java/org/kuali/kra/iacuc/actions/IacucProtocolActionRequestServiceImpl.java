/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveBean;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveEvent;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveService;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRequestBean;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionRequestServiceImpl;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucProtocolActionRequestServiceImpl extends ProtocolActionRequestServiceImpl implements IacucProtocolActionRequestService {

    private IacucProtocolApproveService protocolApproveService;
    
    public boolean isFullApprovalAuthorized(IacucProtocolForm protocolForm) {
        boolean requestAuthorized = false;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocolApproveBean actionBean = (IacucProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        if (hasPermission(TaskName.APPROVE_PROTOCOL, (IacucProtocol) document.getProtocol())) {
            requestAuthorized = applyRules(new IacucProtocolApproveEvent(document, actionBean));
        }
        return requestAuthorized;
    }
    
    public void grantFullApproval(IacucProtocolForm protocolForm) throws Exception {
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        IacucProtocolApproveBean actionBean = (IacucProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        
        getProtocolApproveService().grantFullApproval(protocol, actionBean);
        generateActionCorrespondence(IacucProtocolActionType.IACUC_APPROVED, protocolForm.getProtocolDocument().getProtocol());
        saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
        IacucProtocolSubmission submission = (IacucProtocolSubmission)protocol.getProtocolSubmission();

        IacucProtocolNotificationRequestBean notificationBean;
        String actionType;
        String actionDescription;
        String actionDescription2;
        if (StringUtils.equals(submission.getProtocolReviewTypeCode(),IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW)) {
            actionType = IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL;
            actionDescription = "Designated Member Approval";
            actionDescription2 = "Designated Member Approved";
        }
        else {
            actionType = IacucProtocolActionType.IACUC_APPROVED;
            actionDescription = "Full Approval";
            actionDescription2 = "Approved";
        }
        recordProtocolActionSuccess(actionDescription);
        notificationBean = new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), actionType, actionDescription);
        // issue : protocolcorrespondence is reset after loading correspondence ? more work
        // somehow docforkey is not in session for this case ?
        // hack this for now
        protocolForm.getProtocolHelper().prepareView();
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            // TODO : this is hack
            // may need to add it back when save/close corr ?
            
            GlobalVariables.getUserSession().addObject("approvalComplCorrespondence", GlobalVariables.getUserSession().retrieveObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY));
            // temporarily remove this key which is generated by super.approve
            GlobalVariables.getUserSession().removeObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY);
        } else {
            IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer((IacucProtocol) document.getProtocol());
            IacucProtocolNotificationContext context = new IacucProtocolNotificationContext((IacucProtocol) document.getProtocol(), actionType, actionDescription2, renderer);
            getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
        }
        
    }

    @Override
    protected ProtocolTaskBase getProtocolTaskInstanceHook(String taskName, ProtocolBase protocol) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, (IacucProtocol)protocol);
        return task;
    }

    @Override
    protected ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode) {
        return new IacucProtocolActionsCorrespondence(protocolActionTypeCode);
    }

    @Override
    protected Class<? extends ProtocolActionTypeBase> getProtocolActionTypeBOClassHook() {
        return IacucProtocolActionType.class;
    }

    @Override
    protected String getProtocolCreatedActionTypeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
    }

    public IacucProtocolApproveService getProtocolApproveService() {
        return protocolApproveService;
    }

    public void setProtocolApproveService(IacucProtocolApproveService protocolApproveService) {
        this.protocolApproveService = protocolApproveService;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected ProtocolTaskBase getProtocolGenericActionTaskInstanceHook(String taskName, String genericActionName,
            ProtocolBase protocol) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, (IacucProtocol)protocol, genericActionName);
        return task;
    }

    @Override
    protected String getProtocolRejectedInRoutingActionTypeHook() {
        return IacucProtocolActionType.REJECTED_IN_ROUTING;
    }

    @Override
    protected String getProtocolRecalledInRoutingActionTypeHook() {
        //not supported action type
        return null;
    }

}
