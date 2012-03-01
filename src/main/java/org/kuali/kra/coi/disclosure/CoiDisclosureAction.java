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
package org.kuali.kra.coi.disclosure;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.certification.CertifyDisclosureEvent;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.questionnaire.SaveProtocolQuestionnaireEvent;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.kra.util.watermark.WatermarkConstants;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.CollectionUtils;

public class CoiDisclosureAction extends CoiAction {
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String ATTACHMENT_PATH = "document.coiDisclosureList[0].attachmentCoiDisclosures[";
    private static final String CONFIRM_NO_DELETE = "";
    private static final String UPDATE_DISCLOSURE = "updateDisclosure";
   
    public ActionForward addDisclosurePersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        if (checkRule(new AddDisclosureReporterUnitEvent("disclosureHelper.newDisclosurePersonUnit", disclosureHelper.getNewDisclosurePersonUnit(),
                ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter().getDisclosurePersonUnits()))) {
            getCoiDisclosureService().addDisclosureReporterUnit(
                   ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter(),
                   disclosureHelper.getNewDisclosurePersonUnit());
            disclosureHelper.setNewDisclosurePersonUnit(new DisclosurePersonUnit());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteDisclosurePersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        getCoiDisclosureService().deleteDisclosureReporterUnit(((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter(), disclosureHelper.getDeletedUnits(), getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        // save questionnaire data first
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        Document document = coiDisclosureForm.getDocument();
        List<AnswerHeader> answerHeaders = coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders();
        // TODO add a COI specific rule event to the condition below
        if ( applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders))) {
            coiDisclosureForm.getDisclosureQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
        }
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        // notes and attachments
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();        
        helper.fixReloadedAttachments(request.getParameterMap());
        
        CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)document).getCoiDisclosure();
        if (coiDisclosure.getCoiDisclosureId() == null) {
            coiDisclosure.initRequiredFields();            
        } else {
            getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
        }
        if (coiDisclosure.isUpdateEvent()) {
            getCoiDisclosureService().setDisclDetailsForSave(coiDisclosure, coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean());
        } else {
            getCoiDisclosureService().setDisclDetailsForSave(coiDisclosure);
        }
        actionForward = super.save(mapping, form, request, response);
        if (KRADConstants.SAVE_METHOD.equals(coiDisclosureForm.getMethodToCall()) && coiDisclosureForm.isAuditActivated() 
                && GlobalVariables.getMessageMap().hasNoErrors()) {
            actionForward = mapping.findForward("disclosureActions");
        } else if (coiDisclosure.isUpdateEvent()) {
            actionForward = mapping.findForward(UPDATE_DISCLOSURE);
        }

        return actionForward;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward actionForward = super.execute(mapping, form, request, response);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
        // TODO : 'checkToLoadDisclosureDetails' should not need to be executed for every action.  need to make it somewhere ?
//        checkToLoadDisclosureDetails(coiDisclosureDocument.getCoiDisclosure(), ((CoiDisclosureForm) form).getMethodToCall(), coiDisclosureForm.getDisclosureHelper().getNewProjectId());
        if ((StringUtils.equals("reload", coiDisclosureForm.getMethodToCall()) || StringUtils.equals("updateAttachmentFilter", coiDisclosureForm.getMethodToCall()) 
                || StringUtils.equals("headerTab", coiDisclosureForm.getMethodToCall()) || StringUtils.equals("docHandler",
                coiDisclosureForm.getMethodToCall())) && coiDisclosureDocument.getCoiDisclosure().isApprovedDisclosure()) {
            coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                    getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
            actionForward = mapping.findForward(MASTER_DISCLOSURE);
        }
        else {
            String command = request.getParameter("command");
            if (StringUtils.isNotBlank(command) && MASTER_DISCLOSURE.equals(command)) {
                // 'view' in master disclosure's 'Disclosures' list
                super.loadDocument((KualiDocumentFormBase) form);
                coiDisclosureDocument = (CoiDisclosureDocument) coiDisclosureForm.getDocument();
                coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
                coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                        getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
                actionForward = mapping.findForward(MASTER_DISCLOSURE);
            }

        }
//        if (coiDisclosureDocument.getCoiDisclosure().isUpdateEvent() && !StringUtils.equals("performLookup", coiDisclosureForm.getMethodToCall())) {
//            actionForward = mapping.findForward(UPDATE_DISCLOSURE);
//        }
        return actionForward;

    }

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        String command = coiDisclosureForm.getCommand();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String eventTypeCode = CoiDisclosureEventType.ANNUAL; 
        if (command.startsWith(KewApiConstants.INITIATE_COMMAND)) {
            if (command.endsWith(CoiDisclosure.PROPOSAL_DISCL_MODULE_CODE)) {
                eventTypeCode = CoiDisclosureEventType.DEVELOPMENT_PROPOSAL;
            } else if (command.endsWith(CoiDisclosure.PROTOCOL_DISCL_MODULE_CODE)) {
                eventTypeCode = CoiDisclosureEventType.IRB_PROTOCOL;
            } else if (command.endsWith(CoiDisclosure.AWARD_DISCL_MODULE_CODE)) {
                eventTypeCode = CoiDisclosureEventType.AWARD;
            } else if (command.endsWith(CoiDisclosure.MANUAL_DISCL_MODULE_CODE)) {
                // this will be reset when 'addmanualproject', and the event type will be selected at that time
                eventTypeCode = CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL;
            } else if (command.endsWith("_6")) {
                // this is to update master disclosure
                eventTypeCode = CoiDisclosureEventType.UPDATE;
            }
            coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND);
            forward = super.docHandler(mapping, form, request, response);
            CoiDisclosure coiDisclosure = getCoiDisclosureService().versionCoiDisclosure();
            if (CoiDisclosureEventType.UPDATE.equals(eventTypeCode)) {
                 getCoiDisclosureService().initDisclosureFromMasterDisclosure(coiDisclosure);
                 coiDisclosure.setEventTypeCode(CoiDisclosureEventType.UPDATE);
                 ((CoiDisclosureForm)form).getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(
                         coiDisclosure)); 
                 forward = mapping.findForward(UPDATE_DISCLOSURE);
            } 

            if (coiDisclosure != null) {
                coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
            }
            coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setEventTypeCode(eventTypeCode);
        } else {
            coiDisclosureForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
            super.docHandler(mapping, form, request, response);
            if (coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().isUpdateEvent()) {
                ((CoiDisclosureForm)form).getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(
                        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure())); 
                forward = mapping.findForward("updateDisclosure");
           } 
        }
        ((CoiDisclosureForm)form).getDisclosureHelper().prepareView();
        if (!coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().isUpdateEvent()) {
            checkToLoadDisclosureDetails(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(), ((CoiDisclosureForm) form).getMethodToCall(), coiDisclosureForm.getDisclosureHelper().getNewProjectId(), coiDisclosureForm.getDisclosureHelper().getNewModuleItemKey());
        }

        return forward;
    }

    private void checkToLoadDisclosureDetails(CoiDisclosure coiDisclosure, String methodToCall, String projectId, String moduleItemKey) {
        // TODO : load FE disclosure when creating coi disc
        // still need more clarification on whether there is any other occasion this need to be loaded
        if (coiDisclosure.getCoiDisclosureId() == null && CollectionUtils.isEmpty(coiDisclosure.getCoiDiscDetails())) {
            if (StringUtils.equals("newProjectDisclosure", methodToCall) && projectId != null) {
                getCoiDisclosureService().initializeDisclosureDetails(coiDisclosure, projectId);
                coiDisclosure.setModuleItemKey(moduleItemKey);
            } else {
                getCoiDisclosureService().initializeDisclosureDetails(coiDisclosure);
                coiDisclosure.setModuleItemKey(projectId);
            }
        } else {
            if (!StringUtils.equals("addProposal", methodToCall) && !StringUtils.equals("save", methodToCall)) {
                getCoiDisclosureService().updateDisclosureDetails(coiDisclosure);
            }
        }
        
        // TODO : for manual proposal project
        if (coiDisclosure.isManualEvent() && !CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                // TODO : need to look into this condition further
                if (!StringUtils.equals("addProposal", methodToCall) && !StringUtils.equals("save", methodToCall) && coiDisclProject.getCoiDisclProjectsId() != null) {
                    getCoiDisclosureService().updateDisclosureDetails(coiDisclProject);
                }
            }
        }
    }
    
    public ActionForward newFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = save(mapping, form, request, response);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
            CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
            String forward = ConfigContext.getCurrentContextConfig().getProperty("kuali.docHandler.url.prefix") + 
                    "/financialEntityEditNew.do?methodToCall=addNewCoiDiscFinancialEntity&coiDocId="+((CoiDisclosureForm) form).getDocument().getDocumentNumber()+"&financialEntityHelper.reporterId="+coiDisclosure.getPersonId();
//            "/portal.do?channelTitle=Financial%20Entity&channelUrl=financialEntityManagement.do?methodToCall=editNew&coiDocId="+((CoiDisclosureForm) form).getDocument().getDocumentNumber();
            return new ActionForward(forward, true);
        }
        return actionForward;

    }

    public ActionForward editFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = save(mapping, form, request, response);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
            CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
            String forward = ConfigContext.getCurrentContextConfig().getProperty("kuali.docHandler.url.prefix") + 
                    "/financialEntityEditList.do?methodToCall=editActiveFinancialEntity&coiDocId="+((CoiDisclosureForm) form).getDocument().getDocumentNumber()+"&financialEntityHelper.editCoiEntityId="+coiDisclosure.getCoiDiscDetails().get(getSelectedLine(request)).getPersonFinIntDisclosureId() ;
           // "/portal.do?channelTitle=Financial%20Entity&channelUrl=financialEntityManagement.do?methodToCall=editList&coiDocId="+((CoiDisclosureForm) form).getDocument().getDocumentNumber()+"&financialEntityHelper.editEntityIndex"+getSelectedLine(request);
            return new ActionForward(forward, true);
        }
        return actionForward;
    }

    public ActionForward addManualProject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        disclosureHelper.getNewCoiDisclProject().setCoiDisclosure(coiDisclosure);
        disclosureHelper.getNewCoiDisclProject().setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        if (checkRule(new AddManualProjectEvent("disclosureHelper.newCoiDisclProject", disclosureHelper.getNewCoiDisclProject()))) {
            getCoiDisclosureService().initializeDisclosureDetails(disclosureHelper.getNewCoiDisclProject());
            disclosureHelper.getNewCoiDisclProject().setSequenceNumber(coiDisclosure.getSequenceNumber());
            coiDisclosure.getCoiDisclProjects().add(disclosureHelper.getNewCoiDisclProject());
            coiDisclosure.setModuleItemKey(disclosureHelper.getNewCoiDisclProject().getProjectId());
            coiDisclosure.setEventTypeCode(disclosureHelper.getNewCoiDisclProject().getDisclosureEventType());
            disclosureHelper.setNewCoiDisclProject(new CoiDisclProject(coiDisclosure.getCoiDisclosureNumber(), coiDisclosure.getSequenceNumber()));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward getDisclosuresToComplete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String userId = getUserId();
        DisclosureHelper disclosureHelper = ((CoiDisclosureForm) form).getDisclosureHelper(); 

        disclosureHelper.setNewProposals(getCoiDisclosureService().getProposals(userId));
        disclosureHelper.setNewInstitutionalProposals(getCoiDisclosureService().getInstitutionalProposals(userId));
        disclosureHelper.setNewAwards(getCoiDisclosureService().getAwards(userId));
        disclosureHelper.setNewProtocols(getCoiDisclosureService().getProtocols(userId));

        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward newProjectDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND);
        ActionForward forward = docHandler(mapping, form, request, response);
        CoiDisclosure coiDisclosure = getCoiDisclosureService().versionCoiDisclosure();
        if (coiDisclosure != null) {
            coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
            coiDisclosure.setCoiDisclosureDocument(coiDisclosureForm.getCoiDisclosureDocument());
        }
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setEventTypeCode(coiDisclosureForm.getDisclosureHelper().getEventTypeCode());
        // dochandler may populate discdetails for new doc.  here is just to reset to reload it again.
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setCoiDiscDetails(null);
        checkToLoadDisclosureDetails(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(), ((CoiDisclosureForm) form).getMethodToCall(), coiDisclosureForm.getDisclosureHelper().getNewProjectId(), coiDisclosureForm.getDisclosureHelper().getNewModuleItemKey());
        //populate Questionnaires and answers here
        coiDisclosureForm.getDisclosureQuestionnaireHelper().prepareView();
        return forward;
    }

    public ActionForward submitDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        if (checkRule(new CertifyDisclosureEvent("disclosureHelper.certifyDisclosure", coiDisclosure))) {
            coiDisclosureForm.setAuditActivated(true);
            AuditActionHelper auditActionHelper = new AuditActionHelper();
            if (auditActionHelper.auditUnconditionally(coiDisclosureDocument)) {
                // Certification occurs after the audit rules pass.
                coiDisclosure.certifyDisclosure();
                if (coiDisclosure.getCoiDisclosureId() == null) {
                    coiDisclosure.initRequiredFields();            
                } else {
                    getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
                }
                getCoiDisclosureService().setDisclDetailsForSave(coiDisclosure);
                forward = submitForReviewAndRedirect(mapping, form, request, response, coiDisclosureForm, coiDisclosure, coiDisclosureDocument);
            } else {
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
            }
    }

        return forward;
    }

    //TODO: This will need some work...
    public ActionForward printDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiDisclosure coiDisclosure = ((CoiDisclosureForm)form).getCoiDisclosureDocument().getCoiDisclosure();
        CoiPrintingService printService = KraServiceLocator.getService(CoiPrintingService.class);
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        AttachmentDataSource dataStream = printService.printDisclosureCertification(coiDisclosure, CoiPrintingService.PRINT_CERTIFICATION, reportParameters);
        streamToResponse(dataStream, response);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private String getUserId() {
    	return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    
    
    /****
     * COI NOTES AND ATTACHMENTS
     * **/
    public ActionForward replaceAttachmentCoi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        CoiDisclosureAttachment attachment = helper.retrieveExistingAttachmentByType(selection);
       // attachment.populateAttachment();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addAttachmentCoi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        save(mapping, form, request, response);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();

        helper.addNewCoiDisclosureAttachment();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteCoiDisclosureAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        final CoiDisclosureAttachment attachment = helper.retrieveExistingAttachmentByType(selection);
        if (isValidContactData(attachment, ATTACHMENT_PATH + selection + "]")) {
            return confirmDeleteAttachment(mapping, (CoiDisclosureForm) form, request, response);
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    protected boolean isValidContactData(CoiDisclosureAttachment attachment, String errorPath) {
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(errorPath);
        getDictionaryValidationService().validateBusinessObject(attachment);
        errorMap.removeFromErrorPath(errorPath);
        return errorMap.hasNoErrors();
    }

    protected DictionaryValidationService getDictionaryValidationService() {
        return KNSServiceLocator.getKNSDictionaryValidationService();
    }
    
    protected ActionForward confirmDeleteAttachment(ActionMapping mapping, CoiDisclosureForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        final CoiDisclosureAttachment attachment = helper.retrieveExistingAttachmentByType(selection);
               
        if (attachment == null) {
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final String confirmMethod = helper.retrieveConfirmMethodByType();
        final StrutsConfirmation confirm 
        = buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmMethod, 
                KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, attachment.getDescription(), attachment.getFile().getName());
        
        return confirm(confirm, confirmMethod, CONFIRM_NO_DELETE);
    }

    public ActionForward confirmDeleteCoiDisclosureAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.deleteAttachment(mapping, (CoiDisclosureForm) form, request, response, ProtocolAttachmentProtocol.class);
    }
    
    private ActionForward deleteAttachment(ActionMapping mapping, CoiDisclosureForm form, HttpServletRequest request,
            HttpServletResponse response, Class<? extends ProtocolAttachmentBase> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        if (!helper.deleteExistingAttachmentByType(selection)) {
            //may want to tell the user the selection was invalid.
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
   
    public ActionForward viewAttachmentCoi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        final int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        final CoiDisclosureAttachment attachment = helper.retrieveExistingAttachmentByType(selection);

        if (attachment == null) {
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        final AttachmentFile file = attachment.getFile();
        byte[] attachmentFile = null;
        String attachmentFileType = file.getType().replace("\"", "");
        if (attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile = getCoiDisclosureAttachmentFile(form,attachment);
            if (attachmentFile != null){
                this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);}
            return RESPONSE_ALREADY_HANDLED;
        }        
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }

    private byte[] getCoiDisclosureAttachmentFile(ActionForm form, CoiDisclosureAttachment attachment){
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();

        byte[] attachmentFile = null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getCoiPrintingService().getCoiPrintArtifacts(helper.getCoiDisclosure());
        try {
            if (printableArtifacts.isWatermarkEnabled()){
                Integer attachmentDocumentId = attachment.getDocumentId();
                List<CoiDisclosureAttachment> coiDisclosureAttachmentList = helper.getCoiDisclosure().getCoiDisclosureAttachments();
                if (coiDisclosureAttachmentList.size()>0){
                    for (CoiDisclosureAttachment coiDisclosureAttachment : coiDisclosureAttachmentList) {
                        if (attachmentDocumentId.equals(coiDisclosureAttachment.getDocumentId())){
                            //if(getProtocolAttachmentService().isNewAttachmentVersion(coiDisclosureAttachment)){
                            attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                            /*}else{
                                attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getInvalidWatermark());
                                LOG.info(INVALID_ATTACHMENT + attachmentDocumentId);
                            }*/
                        }
                    }
                } else {
                    attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                }
            }
        } catch (Exception e) {
        }        
        return attachmentFile;
    }

    public ActionForward updateAttachmentFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        helper.addNewCoiDisclosureAttachmentFilter();
        return mapping.findForward(Constants.MAPPING_BASIC);
    } 

   /* public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();        
        helper.fixReloadedAttachments(request.getParameterMap());
        super.save(mapping, form, request, response);
        return mapping.findForward(Constants.MAPPING_BASIC);

    }*/

    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        super.postSave(mapping, form, request, response);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();

        if (!(helper.getFilesToDelete().isEmpty())) {
            getBusinessObjectService().delete((helper.getFilesToDelete()));
            helper.getFilesToDelete().clear();
        }

        for (CoiDisclosureAttachment attachment : ((CoiDisclosureForm) form).getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclosureAttachments()) {
            // for some reason, change and save, this list is not updated 
            attachment.getCoiDisclosure().refreshReferenceObject("coiDisclosureAttachments");
        }
    }
    
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();   
        // add authorization here       
        helper.addNewNote();
        helper.setManageNotesOpen();
        super.save(mapping, form, request, response);
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward deleteNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();   
        // add authorization here
        return confirmDeleteNote(mapping, (CoiDisclosureForm) form, request, response);        
    }
    
    protected ActionForward confirmDeleteNote(ActionMapping mapping, CoiDisclosureForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        final String confirmMethod = "deleteNoteConfirmed";
        final StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmMethod, 
                                                                                  KeyConstants.QUESTION_DELETE_NOTE_CONFIRMATION);
        return confirm(confirm, confirmMethod, CONFIRM_NO_DELETE);
    }
    
    public ActionForward deleteNoteConfirmed(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        
        if (!((CoiDisclosureForm)form).getCoiNotesAndAttachmentsHelper().deleteNote(selection)) {
            //may want to tell the user the selection was invalid.
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected CoiPrintingService getCoiPrintingService() {
        return  KraServiceLocator.getService(CoiPrintingService.class);  
    }
    
    protected WatermarkService getWatermarkService() {
        return  KraServiceLocator.getService(WatermarkService.class);  
    }
    
    protected CoiDisclosureActionService getDisclosureActionService() {
        return  KraServiceLocator.getService(CoiDisclosureActionService.class);  
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return  KraServiceLocator.getService(BusinessObjectService.class);  
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ActionForward viewMasterDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) getBusinessObjectService().findMatching(CoiDisclosure.class,
                fieldValues);
        disclosureHelper.prepareView();
        if (CollectionUtils.isEmpty(disclosures)) {
            return mapping.findForward("masterDisclosureNotAvailable");
        } else {
            coiDisclosureForm.setDocId(disclosures.get(0).getCoiDisclosureDocument().getDocumentNumber());
            loadDocument(coiDisclosureForm);
            disclosureHelper.setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(
                    coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure()));
            return mapping.findForward("masterDisclosure");
        }
    }

    private ActionForward submitForReviewAndRedirect(ActionMapping mapping, ActionForm form, 
                                                     HttpServletRequest request, HttpServletResponse response,
                                                     CoiDisclosureForm coiDisclosureForm, CoiDisclosure coiDisclosure, 
                                                     CoiDisclosureDocument coiDisclosureDocument) throws Exception {
    
        SubmitDisclosureAction submitAction = coiDisclosureForm.getDisclosureActionHelper().getSubmitDisclosureAction();
        ActionForward action = getDisclosureActionService().sendCertificationNotifications(coiDisclosureDocument, coiDisclosureForm, submitAction, mapping);
        if (action != null) {
            return action;
        }
        getDisclosureActionService().submitToWorkflow(coiDisclosureDocument, coiDisclosureForm, submitAction);
        return routeDisclosureToHoldingPage(mapping, coiDisclosureForm);
    }
    
    private ActionForward routeDisclosureToHoldingPage(ActionMapping mapping, CoiDisclosureForm coiDisclosureForm) {
        String routeHeaderId = coiDisclosureForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_BASIC, "ProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

    }
    
    /**
     * 
     * This method is for use with a JSON/AJAX call and should not be used as a post method
     * 
     */
    public ActionForward getDisclosureEventTypeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
      
        String eventType = request.getParameter("eventType");
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventType);

        List<CoiDisclosureEventType> disclosureEventTypes = 
            (List<CoiDisclosureEventType>) getBusinessObjectService().findMatching(CoiDisclosureEventType.class, fieldValues);
        StringWriter writer = new StringWriter();
        if (!CollectionUtils.isEmpty(disclosureEventTypes)) {
            CoiDisclosureEventType disclosureEventType = disclosureEventTypes.get(0);
            CoiDisclosureEventTypeAjaxBean disclosureEventTypeAjaxBean = new CoiDisclosureEventTypeAjaxBean();
            disclosureEventTypeAjaxBean.setDisclosureEventType(disclosureEventType);
            
            //Special code to handle select box
            if (disclosureEventType.isUseSelectBox1()) {
                try {
                    String valuesFinder = disclosureEventType.getSelectBox1ValuesFinder();
                    if (StringUtils.isNotBlank(valuesFinder)) {
                        Class valuesFinderClass = Class.forName(valuesFinder);
                        KeyValuesFinder keyValuesFinder = (KeyValuesFinder)valuesFinderClass.newInstance();
                        List<KeyValue> keyValues = keyValuesFinder.getKeyValues();
                        if (!CollectionUtils.isEmpty(keyValues)) {
                            disclosureEventTypeAjaxBean.setKeyValues(keyValues);
                        }
                    }
                } catch (Exception e) { 
                    //Failed to load select box 
                }
            }
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, disclosureEventTypeAjaxBean);
            
            response.setContentType("application/json");
            ServletOutputStream out = response.getOutputStream();

            try {
                out.write(writer.getBuffer().toString().getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace(new PrintWriter(out));
            }
        
        }        
        return null;
    }


    
//    private boolean isApprovedDisclosure(CoiDisclosure coiDisclosure) {
//
//        Map fieldValues = new HashMap();
//        fieldValues.put("coiDisclosureId", coiDisclosure.getCoiDisclosureId());
//        fieldValues.put("disclosureStatus", CoiDisclosureStatus.APPROVE_DISCLOSURE_CODES);
//        return getBusinessObjectService().countMatching(CoiDisclosureHistory.class, fieldValues) > 0;
//    }
    
}
