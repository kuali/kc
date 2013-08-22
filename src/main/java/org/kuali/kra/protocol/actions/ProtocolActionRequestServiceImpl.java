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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
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
    private PersonService personService;
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

    /**
     * This method is to verify whether current user authorized to perform the given task
     * @param taskName
     * @param protocol
     * @return
     */
    protected boolean hasPermission(String taskName, ProtocolBase protocol) {
        ProtocolTaskBase task = getProtocolTaskInstanceHook(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    /**
     * This method is to verify whether current user authorized to perform the generic action task
     * @param genericActionName
     * @param protocol
     * @return
     */
    protected boolean hasGenericPermission(String genericActionName, ProtocolBase protocol) {
        ProtocolTaskBase task = getProtocolGenericActionTaskInstanceHook(TaskName.GENERIC_PROTOCOL_ACTION, genericActionName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    /**
     * This method is to generate correspondence for every action performed
     * Check whether trigger correspondence is enabled.
     * @param protocolActionTypeCode
     * @param protocol
     * @throws Exception
     */
    protected void generateActionCorrespondence(String protocolActionTypeCode, ProtocolBase protocol) throws Exception {
        if(isCorrespondenceRequired(protocolActionTypeCode)) {
            ProtocolActionsCorrespondenceBase correspondence = getNewProtocolActionsCorrespondence(protocolActionTypeCode);
            correspondence.setPrintableBusinessObject(protocol);
            correspondence.setProtocol(protocol);
            getProtocolActionCorrespondenceGenerationService().generateCorrespondenceDocumentAndAttach(correspondence);
        }
    }

    protected void updateDocumentStatusChangedMessage() {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
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
    
    protected boolean hasDocumentStateChanged(ProtocolFormBase protocolForm) {
        boolean result = false;
        Map<String,Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("protocolId", protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        ProtocolBase dbProtocol = (ProtocolBase)getBusinessObjectService().findByPrimaryKey(getProtocolBOClassHook(), primaryKeys);
        
        //First lets check the protocol status & submission status
        if (dbProtocol != null) {
            if (!StringUtils.equals(dbProtocol.getProtocolStatusCode(), 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolStatusCode())) {
                result = true;
            }
            if (dbProtocol.getProtocolSubmission() != null && 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode() != null) {
                if (!StringUtils.equals(dbProtocol.getProtocolSubmission().getSubmissionStatusCode(), 
                        protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode())) {
                    result = true;
                }
            }
        }
        
        //If no changes in the protocol, lets check the document for workflow changes
        if (!result) {
           result = !isDocumentPostprocessingComplete(protocolForm.getProtocolDocument());
        }
        return result;
    }
    
    private boolean isDocumentPostprocessingComplete(ProtocolDocumentBase document) {
        return document.getDocumentHeader().hasWorkflowDocument() && !isPessimisticallyLocked(document);
    }
    
    private boolean isPessimisticallyLocked(Document document) {
        boolean isPessimisticallyLocked = false;
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName(KewApiConstants.SYSTEM_USER);
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(pessimisticLockHolder)) {
                isPessimisticallyLocked = true;
                break;
            }
        }
        return isPessimisticallyLocked;
    }
    
    
    protected abstract ProtocolTaskBase getProtocolTaskInstanceHook(String taskName, ProtocolBase protocol);
    protected abstract ProtocolTaskBase getProtocolGenericActionTaskInstanceHook(String taskName, String genericActionName, ProtocolBase protocol);
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode);
    protected abstract Class<? extends ProtocolActionTypeBase> getProtocolActionTypeBOClassHook();
    protected abstract String getProtocolCreatedActionTypeHook();
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();

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

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
