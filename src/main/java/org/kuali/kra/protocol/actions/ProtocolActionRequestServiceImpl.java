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
package org.kuali.kra.protocol.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class ProtocolActionRequestServiceImpl implements ProtocolActionRequestService {

    private KualiRuleService kualiRuleService;
    private TaskAuthorizationService taskAuthorizationService;
    private BusinessObjectService businessObjectService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private KcNotificationService notificationService;
    
    private ReviewCommentsService<? extends ProtocolReviewAttachmentBase> reviewCommentsService;
    
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public TaskAuthorizationService getTaskAuthorizationService() {
        return taskAuthorizationService;
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected boolean hasPermission(String taskName, ProtocolBase protocol) {
        ProtocolTaskBase task = getProtocolTaskInstanceHook(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    protected void generateActionCorrespondence(String protocolActionTypeCode, ProtocolBase protocol) throws Exception {
        if(isCorrespondenceRequired(protocolActionTypeCode)) {
            ProtocolActionsCorrespondenceBase correspondence = getNewProtocolActionsCorrespondence(protocolActionTypeCode);
            correspondence.setPrintableBusinessObject(protocol);
            correspondence.setProtocol(protocol);
            getProtocolActionCorrespondenceGenerationService().generateCorrespondenceDocumentAndAttach(correspondence);
        }
    }
    
    private boolean isCorrespondenceRequired(String protocolActionTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolActionTypeCode", protocolActionTypeCode);
        ProtocolActionTypeBase protocolActionType = (ProtocolActionTypeBase) getBusinessObjectService().findByPrimaryKey(getProtocolActionTypeBOClassHook(), fieldValues);
        return protocolActionType.getTriggerCorrespondence();
    }

    public void createProtocol(ProtocolFormBase protocolForm) throws Exception {
        generateActionCorrespondence(getProtocolCreatedActionTypeHook(), protocolForm.getProtocolDocument().getProtocol());
    }

    protected void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }
    
    protected void saveReviewComments(ProtocolFormBase protocolForm, ReviewCommentsBeanBase actionBean) throws Exception { 
        getReviewCommentsService().saveReviewComments(actionBean.getReviewComments(), actionBean.getDeletedReviewComments());           
        actionBean.setDeletedReviewComments(new ArrayList<CommitteeScheduleMinuteBase>());
        protocolForm.getActionHelper().prepareCommentsView();
    }
    
    protected abstract ProtocolTaskBase getProtocolTaskInstanceHook(String taskName, ProtocolBase protocol);
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode);
    protected abstract Class<? extends ProtocolActionTypeBase> getProtocolActionTypeBOClassHook();
    protected abstract String getProtocolCreatedActionTypeHook();

    public ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return protocolActionCorrespondenceGenerationService;
    }

    public void setProtocolActionCorrespondenceGenerationService(
            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    public KcNotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ReviewCommentsService<? extends ProtocolReviewAttachmentBase> getReviewCommentsService() {
        return reviewCommentsService;
    }

    public void setReviewCommentsService(ReviewCommentsService<? extends ProtocolReviewAttachmentBase> reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

}
