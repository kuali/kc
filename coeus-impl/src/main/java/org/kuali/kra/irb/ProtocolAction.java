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
package org.kuali.kra.irb;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondenceDetail;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.IrbProtocolActionRequestService;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionBeanBase;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.personnel.ProtocolPersonTrainingService;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolActionBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

/**
 * The ProtocolAction is the base class for all Protocol actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolAction extends ProtocolActionBase {
    
    public static final String PROTOCOL_NAME_HOOK = "protocol";
    public static final String PROTOCOL_QUESTIONNAIRE_HOOK = "questionnaire";
    public static final String PROTOCOL_PERSONNEL_HOOK = "personnel";
    public static final String PROTOCOL_SPECIAL_REVIEW_HOOK = "specialReview";
    public static final String PROTOCOL_NOTE_ATTACHMENT_HOOK = "noteAndAttachment";
    public static final String PROTOCOL_ACTIONS_HOOK = "protocolActions";
    public static final String PROTOCOL_ONLINE_REVIEW_HOOK = Constants.MAPPING_PROTOCOL_ONLINE_REVIEW;
    public static final String PROTOCOL_PERMISSIONS_HOOK = "permissions";
    public static final String PROTOCOL_CUSTOM_DATA_HOOK = "customData";
    public static final String PROTOCOL_MEDUSA = "medusa";
    public static final String PROTOCOL_HISTORY_HOOK = "protocolHistory";

    private static final Log LOG = LogFactory.getLog(ProtocolAction.class);
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final String SUFFIX_T = "T";
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    public static final String TEMPLATE = "template";
    public static final String SEQUENCE_NUMBER = "sequenceNumber";

    private transient ProjectRetrievalService projectRetrievalService;
    private transient ProtocolAmendRenewService protocolAmendRenewService;

    protected ProtocolSubmissionBeanBase getSubmissionBean(ActionForm form, String submissionActionType) {
        ProtocolSubmissionBeanBase submissionBean = null;
                 
        if (ProtocolActionType.NOTIFY_IRB.equals(submissionActionType)) {
            submissionBean = ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean();
        } else {
            submissionBean = ((ProtocolForm) form).getActionHelper().getRequestBean(submissionActionType);
        }
        return submissionBean;
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getCustomDataHelper().prepareCustomData();
        return branchToPanelOrNotificationEditor(mapping, (ProtocolFormBase)form, getCustomDataForwardNameHook());
    }
    
    public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (protocolForm.getProtocolDocument().getDocumentNumber() == null) {
            loadDocument(protocolForm);
        }
        protocolForm.getMedusaBean().setMedusaViewRadio("0");
        protocolForm.getMedusaBean().setModuleName("irb");
        protocolForm.getMedusaBean().setModuleIdentifier(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        protocolForm.getMedusaBean().generateParentNodes();
        return branchToPanelOrNotificationEditor(mapping, protocolForm, PROTOCOL_MEDUSA);
    }    

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        String command = protocolForm.getCommand();
        String detailId;


        if (command.startsWith(KewApiConstants.DOCSEARCH_COMMAND+"detailId")) {
            detailId = command.substring((KewApiConstants.DOCSEARCH_COMMAND+"detailId").length());
            protocolForm.setDetailId(detailId);
            viewBatchCorrespondence(mapping, protocolForm, request, response);
            return RESPONSE_ALREADY_HANDLED;
        }
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            protocolForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(forward.getPath()+ "?" + KRADConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
        } else if (Constants.MAPPING_PROTOCOL_ACTIONS.equals(command) || Constants.MAPPING_PROTOCOL_ONLINE_REVIEW.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            protocolForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            loadDocument(protocolForm);
        } else {
            forward = super.docHandler(mapping, form, request, response);
        }

        if (KewApiConstants.INITIATE_COMMAND.equals(protocolForm.getCommand())) {
            protocolForm.getProtocolDocument().initialize();
        } else {
            protocolForm.initialize();
        }

        String protocolNumber = protocolForm.getProtocolDocument().getProtocol().getProtocolNumber();
        if (protocolNumber != null) {
            getProtocolAmendRenewService().refreshCacheForProtocol(protocolNumber);
        }


        if (Constants.MAPPING_PROTOCOL_ACTIONS.equals(command)) {
            forward = protocolActions(mapping, protocolForm, request, response);
        }
        if (Constants.MAPPING_PROTOCOL_ONLINE_REVIEW.equals(command)) {
            forward = onlineReview(mapping, protocolForm, request, response);
        }
        if (Constants.MAPPING_PROTOCOL_HISTORY.equals(command)) {
            forward = protocolHistory(mapping, protocolForm, request, response);
        }
        
        return forward;
    }

    protected String getProtocolHistoryForwardNameHook() {
        return PROTOCOL_HISTORY_HOOK;
    }
    
    /**
     * This method is to get protocol personnel training service
     * @return ProtocolPersonTrainingService
     */
    protected ProtocolPersonTrainingService getProtocolPersonTrainingService() {
        return (ProtocolPersonTrainingService) KcServiceLocator.getService("protocolPersonTrainingService");
    }
    
    /**
     * This method is to get protocol personnel service
     * @return ProtocolPersonnelService
     */
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return (ProtocolPersonnelService) KcServiceLocator.getService("protocolPersonnelService");
    }
    
    /*
     * Get the ProtocolOnlineReviewService
     * @return ProtocolOnlineReviewService
     */
    
    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KcServiceLocator.getService(ProtocolOnlineReviewService.class);
    }
        
    /**
     * 
     * This method is to print submission questionnaire answer
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */                  
    public ActionForward printSubmissionQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        AnswerHeader answerHeader = getAnswerHeader(request);
        // for release 3 : if questionnaire questions has answer, then print answer.
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, answerHeader.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
        reportParameters.put(TEMPLATE, answerHeader.getQuestionnaire().getTemplate());
        Protocol protocol;
        if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode())) {
            reportParameters.put(PROTOCOL_NUMBER, answerHeader.getModuleItemKey());
            reportParameters.put(SUBMISSION_NUMBER, answerHeader.getModuleSubItemKey());
            protocol = (Protocol) getProtocolFinder().findCurrentProtocolByNumber(getProtocolNumber(answerHeader));
        } else {
            Map keyValues= new HashMap();
            keyValues.put(PROTOCOL_NUMBER, answerHeader.getModuleItemKey());
            keyValues.put(SEQUENCE_NUMBER, answerHeader.getModuleSubItemKey());
            protocol = ((List<Protocol>)getBusinessObjectService().findMatching(Protocol.class, keyValues)).get(0);
        }

        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(
                protocol, reportParameters);
        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    /*
     * This is to retrieve answer header based on answerheaderid
     */
    private AnswerHeader getAnswerHeader(HttpServletRequest request) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("id", Integer.toString(this.getSelectedLine(request)));
        return  (AnswerHeader)getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, fieldValues);
    }

    protected QuestionnairePrintingService getQuestionnairePrintingService() {
        return KcServiceLocator.getService(QuestionnairePrintingService.class);
    }

    /*
     * get protocolnumber for answerheader moduleitemkey
     * a saved but not submitted answer has "T" at the end of protocolnumber
     */
    private String getProtocolNumber(AnswerHeader answerHeader) {
        String protocolNumber = answerHeader.getModuleItemKey();
        if (protocolNumber.endsWith(SUFFIX_T)) {
            protocolNumber = protocolNumber.substring(0, protocolNumber.length() - 1);
        }
        return protocolNumber;
    }
    
    private ProtocolFinderDao getProtocolFinder() {
        return KcServiceLocator.getService(ProtocolFinderDao.class);
    }

    private void viewBatchCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        Map primaryKeys = new HashMap();
        primaryKeys.put("committeeBatchCorrespondenceDetailId", protocolForm.getDetailId());
        CommitteeBatchCorrespondenceDetail batchCorrespondenceDetail = (CommitteeBatchCorrespondenceDetail) getBusinessObjectService()
                .findByPrimaryKey(CommitteeBatchCorrespondenceDetail.class, primaryKeys);
        primaryKeys.clear();
        primaryKeys.put("id", batchCorrespondenceDetail.getProtocolCorrespondenceId());
        ProtocolCorrespondence attachment = (ProtocolCorrespondence) getBusinessObjectService().findByPrimaryKey(
                ProtocolCorrespondence.class, primaryKeys);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + "detailID: " + protocolForm.getDetailId());

        }
        else {

            this.streamToResponse(attachment.getCorrespondence(), StringUtils.replace(attachment.getProtocolCorrespondenceType()
                    .getDescription(), " ", "")
                    + ".pdf", Constants.PDF_REPORT_CONTENT_TYPE, response);
        }
    }

    protected void sendNotification(ProtocolFormBase protocolForm) {
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.PROTOCOL_CREATED_NOTIFICATION, "Created", renderer);
        KcNotificationService notificationService = KcServiceLocator.getService(KcNotificationService.class);
        notificationService.sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
    }

    @Override
    protected String getProtocolForwardNameHook() {
        return PROTOCOL_NAME_HOOK;
    }

    @Override
    protected String getQuestionnaireForwardNameHook() {
        return PROTOCOL_QUESTIONNAIRE_HOOK;
    }

    @Override
    protected String getPersonnelForwardNameHook() {
        return PROTOCOL_PERSONNEL_HOOK;
    }

    @Override
    protected String getNoteAndAttachmentForwardNameHook() {
        return PROTOCOL_NOTE_ATTACHMENT_HOOK;
    }

    @Override
    protected String getProtocolActionsForwardNameHook() {
        return PROTOCOL_ACTIONS_HOOK;
    }

    @Override
    protected String getProtocolOnlineReviewForwardNameHook() {
        return PROTOCOL_ONLINE_REVIEW_HOOK;
    }

    @Override
    protected String getProtocolPermissionsForwardNameHook() {
        return PROTOCOL_PERMISSIONS_HOOK;
    }

    @Override
    protected String getSpecialReviewForwardNameHook() {
        return PROTOCOL_SPECIAL_REVIEW_HOOK;
    }

    @Override
    protected String getCustomDataForwardNameHook() {
        return PROTOCOL_CUSTOM_DATA_HOOK;
    }
    
    @Override
    protected ProtocolNotification getProtocolNotificationHook() {
        return new IRBProtocolNotification();
    }
    
    @Override
    protected ProtocolTaskBase createNewModifyProtocolTaskInstanceHook(ProtocolBase protocol) {
        return new ProtocolTask(TaskName.MODIFY_PROTOCOL, (Protocol) protocol);
    }

    @Override
    protected void initialDocumentSaveAddRolesHook(String userId, ProtocolBase protocol) {
        KcAuthorizationService kraAuthService = getKraAuthorizationService();
        kraAuthService.addDocumentLevelRole(userId, RoleConstants.PROTOCOL_AGGREGATOR, protocol);
        kraAuthService.addDocumentLevelRole(userId, RoleConstants.PROTOCOL_APPROVER, protocol);
        
    }

    @Override
    protected String getProtocolOnlineReviewMappingNameHoook() {
        return Constants.MAPPING_PROTOCOL_ONLINE_REVIEW;
    }

    @Override
    protected String getProtocolActionsMappingNameHoook() {
        return Constants.MAPPING_PROTOCOL_ACTIONS;
    }
    protected String getProtocolNotificationEditorHook() {
        return "protocolNotificationEditor";
    }
    
    protected ProtocolNotificationContextBase getProtocolInitialSaveNotificationContextHook(ProtocolBase protocol) {
        IRBNotificationRenderer renderer = new IRBNotificationRenderer((Protocol)protocol);
        return new IRBNotificationContext((Protocol)protocol, ProtocolActionType.PROTOCOL_CREATED_NOTIFICATION, "Protocol Created", renderer, PROTOCOL_NAME_HOOK);
    }

    protected IrbProtocolActionRequestService getProtocolActionRequestService() {
        return KcServiceLocator.getService(IrbProtocolActionRequestService.class);
    }
    
    protected ProtocolCorrespondence getProtocolCorrespondence (ProtocolForm protocolForm, String forwardName, ProtocolNotificationRequestBean notificationRequestBean, boolean holdingPage) {
        boolean result = false;
        
        Map<String,Object> keyValues = new HashMap<String, Object>();
        // actionid <-> action.actionid  actionidfk<->action.protocolactionid
        keyValues.put("actionIdFk", protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction().getProtocolActionId());
        List<ProtocolCorrespondence> correspondences = (List<ProtocolCorrespondence>)getBusinessObjectService().findMatching(ProtocolCorrespondence.class, keyValues);
        if (correspondences.isEmpty()) {
            return null;
        } else {
            ProtocolCorrespondence correspondence = correspondences.get(0);
            correspondence.setForwardName(forwardName);
            correspondence.setNotificationRequestBean(notificationRequestBean);
            correspondence.setHoldingPage(holdingPage);
            return correspondence;
            
        }
    }
    
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.postSave(mapping, form, request, response);
        ProtocolForm protocolForm = (ProtocolForm) form;
        String lastProtocolActionTypeCode = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction().getProtocolActionTypeCode();
        ProtocolCorrespondence protocolCorrespondence = getProtocolCorrespondence(protocolForm, "", null, false);
        if (protocolCorrespondence == null && lastProtocolActionTypeCode.equalsIgnoreCase(ProtocolActionType.PROTOCOL_CREATED)) {
            getProtocolActionRequestService().createProtocol(protocolForm);
        }
    }

    @Override
    public ProjectRetrievalService getProjectRetrievalService() {
        if (projectRetrievalService == null) {
            projectRetrievalService = KcServiceLocator.getService("irbProjectRetrievalService");
        }

        return projectRetrievalService;
    }

    public void setProjectRetrievalService(ProjectRetrievalService projectRetrievalService) {
        this.projectRetrievalService = projectRetrievalService;
    }

    public ProtocolAmendRenewService getProtocolAmendRenewService() {
        if (protocolAmendRenewService == null) {
            protocolAmendRenewService = KcServiceLocator.getService("protocolAmendRenewService");
        }

        return protocolAmendRenewService;
    }

    public void setProtocolAmendRenewService(ProtocolAmendRenewService protocolAmendRenewService) {
        this.protocolAmendRenewService = protocolAmendRenewService;
    }
}
