/*
 * Copyright 2005-2013 The Kuali Foundation
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolActionBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.api.exception.WorkflowException;

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
        return branchToPanelOrNotificationEditor(mapping, protocolForm, IACUC_PROTOCOL_PROCEDURES);
    }

    private ActionForward checkForPendingNotificationEdit(ActionMapping mapping, ProtocolFormBase form, String originalForwardName) {
        
        return mapping.findForward("iacucProtocolProcedures");
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
        KraAuthorizationService kraAuthService = getKraAuthorizationService();
        kraAuthService.addRole(userId, RoleConstants.IACUC_PROTOCOL_AGGREGATOR, protocol);
        kraAuthService.addRole(userId, RoleConstants.IACUC_PROTOCOL_APPROVER, protocol);         
    }

    @Override
    protected void sendNotification(ProtocolFormBase protocolForm) {
      IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
      IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
      IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.IACUC_PROTOCOL_CREATED, "Created", renderer);
      KcNotificationService notificationService = KraServiceLocator.getService(KcNotificationService.class);
      notificationService.sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
    }
    
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    @Override
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService)KraServiceLocator.getService("iacucProtocolPersonnelService");
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
        return (ProtocolPersonTrainingService)KraServiceLocator.getService("iacucProtocolPersonTrainingService");
    }     

    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KraServiceLocator.getService(IacucProtocolOnlineReviewService.class);
    }


    @Override
    protected ProtocolTaskBase createNewModifyProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected String getProtocolOnlineReviewMappingNameHoook() {
        return IACUC_PROTOCOL_ONLINE_REVIEW_HOOK;
    }

    @Override
    protected String getProtocolActionsMappingNameHoook() {
        return IACUC_PROTOCOL_ACTIONS_HOOK;
    }

    protected IacucProtocol getIacucProtocol(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        return protocolForm.getIacucProtocolDocument().getIacucProtocol();
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService)KraServiceLocator.getService("iacucProtocolProcedureService");
    }

    protected String getProtocolNotificationEditorHook() {
        return "iacucProtocolNotificationEditor";
    }
}
