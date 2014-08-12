/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.IacucProtocolActionRequestService;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.iacuc.infrastructure.IacucConstants;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.iacuc.procedures.IacucProcedureNavigation;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolActionBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.rice.kew.api.exception.WorkflowException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IacucProtocolAction extends ProtocolActionBase {
   
    public static final String IACUC_PROTOCOL_NAME_HOOK = "iacucProtocol";
    public static final String IACUC_PROTOCOL_QUESTIONNAIRE_HOOK = "iacucQuestionnaire";
    public static final String IACUC_PROTOCOL_PERSONNEL_HOOK = "iacucPersonnel";
    public static final String IACUC_PROTOCOL_CUSTOM_DATA_HOOK = "iacucCustomData";
    public static final String IACUC_PROTOCOL_SPECIAL_REVIEW_HOOK = "iacucSpecialReview";
    public static final String IACUC_PROTOCOL_NOTE_ATTACHMENT_HOOK = "iacucProtocolNoteAndAttachment";
    public static final String IACUC_PROTOCOL_ACTIONS_HOOK = "iacucProtocolActions";
    public static final String IACUC_PROTOCOL_ONLINE_REVIEW_HOOK = "iacucProtocolOnlineReview";
    public static final String IACUC_PROTOCOL_PERMISSIONS_HOOK = "iacucProtocolPermissions";
    public static final String IACUC_PROTOCOL_THREE_RS = "iacucProtocolThreeRs";
    public static final String IACUC_PROTOCOL_SPECIES = "iacucSpeciesAndGroups";
    public static final String IACUC_PROTOCOL_EXCEPTION = "iacucProtocolException";
    public static final String IACUC_PROTOCOL_MEDUSA = "medusa";
    public static final String IACUC_PROTOCOL_PROCEDURES = "iacucProtocolProcedures";

    public ActionForward threeRs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return branchToPanelOrNotificationEditor(mapping, (ProtocolFormBase)form, IACUC_PROTOCOL_THREE_RS);
    }    

    public ActionForward speciesAndGroups(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return branchToPanelOrNotificationEditor(mapping, (ProtocolFormBase)form, IACUC_PROTOCOL_SPECIES);
    }    

    public ActionForward protocolException(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return branchToPanelOrNotificationEditor(mapping, (ProtocolFormBase)form, IACUC_PROTOCOL_EXCEPTION);
    }    
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        protocolForm.getCustomDataHelper().prepareCustomData();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, IACUC_PROTOCOL_CUSTOM_DATA_HOOK);
    }
    
    public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws WorkflowException {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        if (protocolForm.getProtocolDocument().getDocumentNumber() == null) {
            loadDocument(protocolForm);
        }
        protocolForm.getMedusaBean().setMedusaViewRadio("0");
        protocolForm.getMedusaBean().setModuleName("iacuc");
        protocolForm.getMedusaBean().setModuleIdentifier(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        protocolForm.getMedusaBean().generateParentNodes();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, IACUC_PROTOCOL_MEDUSA);
    }

    public ActionForward procedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucProtocol iacucProtocol = getIacucProtocol(form);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        iacucProtocol.setIacucProtocolStudyGroupBeans(getIacucProtocolProcedureService().getRevisedStudyGroupBeans(iacucProtocol, 
                protocolForm.getIacucProtocolProceduresHelper().getAllProcedures()));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        return branchToPanelOrNotificationEditor(mapping, protocolForm, IACUC_PROTOCOL_PROCEDURES);
    }

    protected String getProtocolForwardNameHook() {
        return  IACUC_PROTOCOL_NAME_HOOK;
    }

    protected String getQuestionnaireForwardNameHook() {
        return IACUC_PROTOCOL_QUESTIONNAIRE_HOOK;
    }

    protected String getPersonnelForwardNameHook() {
        return IACUC_PROTOCOL_PERSONNEL_HOOK;
    }

    protected String getCustomDataForwardNameHook() {
        return IACUC_PROTOCOL_CUSTOM_DATA_HOOK;
    }

    protected String getSpecialReviewForwardNameHook() {
        return IACUC_PROTOCOL_SPECIAL_REVIEW_HOOK;
    }

    protected String getNoteAndAttachmentForwardNameHook() {
        return IACUC_PROTOCOL_NOTE_ATTACHMENT_HOOK;
    }

    protected String getProtocolActionsForwardNameHook() {
        return IACUC_PROTOCOL_ACTIONS_HOOK;
    }

    protected String getProtocolOnlineReviewForwardNameHook() {
        return IACUC_PROTOCOL_ONLINE_REVIEW_HOOK;
    }
    
    protected String getProtocolPermissionsForwardNameHook() {
        return IACUC_PROTOCOL_PERMISSIONS_HOOK;
    }

    protected ProtocolNotification getProtocolNotificationHook() {
        return new IacucProtocolNotification();
    }
    
    @Override
    protected void initialDocumentSaveAddRolesHook(String userId, ProtocolBase protocol) {
        KcAuthorizationService kraAuthService = getKraAuthorizationService();
        kraAuthService.addRole(userId, RoleConstants.IACUC_PROTOCOL_AGGREGATOR, protocol);
        kraAuthService.addRole(userId, RoleConstants.IACUC_PROTOCOL_APPROVER, protocol);         
    }

    @Override
    protected void sendNotification(ProtocolFormBase protocolForm) {
      IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
      IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
      IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.IACUC_PROTOCOL_CREATED, "Created", renderer);
      KcNotificationService notificationService = KcServiceLocator.getService(KcNotificationService.class);
      notificationService.sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
    }
    
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    @Override
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService) KcServiceLocator.getService("iacucProtocolPersonnelService");
    }
    
    protected ProtocolNotificationContextBase getProtocolInitialSaveNotificationContextHook(ProtocolBase protocol) {
        IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer((IacucProtocol)protocol);
        return new IacucProtocolNotificationContext((IacucProtocol)protocol, IacucProtocolActionType.IACUC_PROTOCOL_CREATED, "Protocol Created", renderer, IACUC_PROTOCOL_NAME_HOOK);
    }
    
    /**
     * This method is to get protocol personnel training service
     * @return ProtocolPersonTrainingService
     */
    @Override
    protected ProtocolPersonTrainingService getProtocolPersonTrainingService() {
        return (ProtocolPersonTrainingService) KcServiceLocator.getService("iacucProtocolPersonTrainingService");
    }     

    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KcServiceLocator.getService(IacucProtocolOnlineReviewService.class);
    }


    @Override
    protected ProtocolTaskBase createNewModifyProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected String getProtocolOnlineReviewMappingNameHoook() {
        return Constants.MAPPING_PROTOCOL_ONLINE_REVIEW;
    }

    @Override
    protected String getProtocolActionsMappingNameHoook() {
        return Constants.MAPPING_PROTOCOL_ACTIONS;
    }

    protected IacucProtocol getIacucProtocol(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        return protocolForm.getIacucProtocolDocument().getIacucProtocol();
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService) KcServiceLocator.getService("iacucProtocolProcedureService");
    }

    protected String getProtocolNotificationEditorHook() {
        return IacucConstants.NOTIFICATION_EDITOR;
    }
    
    protected IacucProtocolCorrespondence getProtocolCorrespondence (ProtocolFormBase protocolForm, String forwardName, ProtocolNotificationRequestBeanBase notificationRequestBean, boolean holdingPage) {
        return (IacucProtocolCorrespondence)getProtocolActionRequestService().getProtocolCorrespondence(protocolForm, forwardName, notificationRequestBean, holdingPage);
    }
    
    protected IacucProtocolActionRequestService getProtocolActionRequestService() {
        return KcServiceLocator.getService(IacucProtocolActionRequestService.class);
    }
    
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        super.postSave(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        String lastProtocolActionTypeCode = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction().getProtocolActionTypeCode();
        IacucProtocolCorrespondence protocolCorrespondence = getProtocolCorrespondence(protocolForm, "", null, false);
        if (protocolCorrespondence == null && lastProtocolActionTypeCode.equalsIgnoreCase(IacucProtocolActionType.IACUC_PROTOCOL_CREATED)) {
            getProtocolActionRequestService().createProtocol(protocolForm);
        }
    }
    
}
