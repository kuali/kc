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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.ApprovedDisclosureDocument.ApprovedDisclosure.DisclosureStatus;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.certification.CertifyDisclosureEvent;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.print.CoiReportType;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.kra.questionnaire.print.QuestionnairePrintingService;
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
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.CollectionUtils;

public class CoiDisclosureAction extends CoiAction {
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String ATTACHMENT_PATH = "document.coiDisclosureList[0].attachmentCoiDisclosures[";
    private static final String CONFIRM_NO_DELETE = "";
    private static final String UPDATE_DISCLOSURE = "updateDisclosure";
    private static final String DEFAULT_EVENT_ID_STRING = "label.coi.disclosure.type.id";
    private static final String DEFAULT_EVENT_TITLE_STRING = "label.coi.disclosure.type.title";

    
    public ActionForward addDisclosurePersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        if (checkRule(new AddDisclosureReporterUnitEvent("disclosureHelper.newDisclosurePersonUnit",
            disclosureHelper.getNewDisclosurePersonUnit(), ((CoiDisclosureDocument) coiDisclosureForm.getDocument())
                    .getCoiDisclosure().getDisclosureReporter().getDisclosurePersonUnits()))) {
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
        getCoiDisclosureService().deleteDisclosureReporterUnit(
                ((CoiDisclosureDocument) coiDisclosureForm.getDocument()).getCoiDisclosure().getDisclosureReporter(),
                disclosureHelper.getDeletedUnits(), getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        // notes and attachments
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();        
        helper.fixReloadedAttachments(request.getParameterMap());
        
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        if (coiDisclosure.getCoiDisclosureId() == null) {
            coiDisclosure.initRequiredFields();            
        }
        else {
            getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
        }
        if (coiDisclosure.isUpdateEvent() ||(coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            getCoiDisclosureService().setDisclProjectForSave(coiDisclosure, coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean());
        } else {
            //getCoiDisclosureService().setDisclProjectForSave(coiDisclosure);
        }
        /************ Begin --- Save (if valid) document and questionnaire data ************/
        // TODO factor out the different versions of this doc and questionnaire data save block from various actions in this class
        // and centralize it in a helper method
        // First validate the questionnaire data
        // TODO maybe add a COI questionnaire specific rule event to the condition below
        List<AnswerHeader> answerHeaders = coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders();
        if ( applyRules(new SaveQuestionnaireAnswerEvent(coiDisclosureDocument, answerHeaders))) {
            // since Questionnaire data is OK we try to save doc
            actionForward = super.save(mapping, form, request, response);
            // check if doc save went OK
            // TODO Any validation errors during the doc save will cause an exception to be thrown, so perhaps the checking of
            // message map below is redundant
            if(GlobalVariables.getMessageMap().hasNoErrors()) {
                // now save questionnaire data for the disclosure
                coiDisclosureForm.getDisclosureQuestionnaireHelper().preSave();
                getBusinessObjectService().save(answerHeaders);
            }
        }
        /************ End --- Save (if valid) document and questionnaire data ************/
        
        if (KRADConstants.SAVE_METHOD.equals(coiDisclosureForm.getMethodToCall()) && coiDisclosureForm.isAuditActivated() 
                && GlobalVariables.getMessageMap().hasNoErrors()) {
            actionForward = mapping.findForward("disclosureActions");
        }
        else if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            actionForward = mapping.findForward(UPDATE_DISCLOSURE);
        }

        return actionForward;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String command = request.getParameter("command");
        //KCCOI-278 added the load line above to fix master disclosure issues but this should really be done 
        // in all cases.
        if (StringUtils.isNotBlank(command) && !StringUtils.containsIgnoreCase(command, KewApiConstants.INITIATE_COMMAND)) {
            // 'view' in master disclosure's 'Disclosures' list
            super.loadDocument((KualiDocumentFormBase) form);
        }
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
       
        // We reset the "audit activated" flag because the current semantics of <<renderMultipart="true">> 
        // cause the action form to be simply picked up from the user session (and not recreated) for each new request. 
        // Thus any flags set on the form during the processing of one request are persistent and visible in any subsequent request.
        coiDisclosureForm.setAuditActivated(false);
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        // we will populate questionnaire data after the execution of any dispatched ("methodTocall") methods. This point, right
        // after the
        // above super.execute() call works well for that because any such dispatched method has finished executing at the end of
        // the call.
        // we will populate questionnaire data after the execution of any dispatched "methodTocall" methods. This point, right
        // after the above super.execute() call works well for that.
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();

        // specify conditions to narrow down the range of the execution paths in which questionnaire data is populated
        if ((coiDisclosureDocument.getDocumentHeader().hasWorkflowDocument())
                && ((!coiDisclosure.isManualEvent()) || (!CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())))) {
            boolean forceQnnrReload = false;
            // TODO : this is pretty hacky to refresh qn
            if ((StringUtils.equals("reload", coiDisclosureForm.getMethodToCall()) && !coiDisclosure.isApprovedDisclosure() && !coiDisclosure.isAnnualUpdate() && !coiDisclosure.isUpdateEvent())
                    || (StringUtils.equals("addManualProject", coiDisclosureForm.getMethodToCall()))) {
                forceQnnrReload = true;
            }            
            coiDisclosureForm.getDisclosureQuestionnaireHelper().prepareView(forceQnnrReload);
        }
        
        // now the rest of subclass-specific custom logic for execute()
        coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
        // TODO : 'checkToLoadDisclosureDetails' should not need to be executed for every action.  need to make it somewhere ?
        // checkToLoadDisclosureDetails(coiDisclosureDocument.getCoiDisclosure(), ((CoiDisclosureForm) form).getMethodToCall(),
        // coiDisclosureForm.getDisclosureHelper().getNewProjectId());
        if ((StringUtils.equals("reload", coiDisclosureForm.getMethodToCall())
                || StringUtils.equals("updateAttachmentFilter", coiDisclosureForm.getMethodToCall())
                || StringUtils.equals("headerTab", coiDisclosureForm.getMethodToCall()) || StringUtils.equals("docHandler",
                coiDisclosureForm.getMethodToCall())) && coiDisclosureDocument.getCoiDisclosure().isApprovedDisclosure()) {
            coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                    getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
            setQuestionnaireStatuses(coiDisclosureForm);
            actionForward = mapping.findForward(MASTER_DISCLOSURE);
        }
        else {
//            String command = request.getParameter("command");
            if (StringUtils.isNotBlank(command) && MASTER_DISCLOSURE.equals(command)) {
                // 'view' in master disclosure's 'Disclosures' list
//                super.loadDocument((KualiDocumentFormBase) form);
                coiDisclosureDocument = (CoiDisclosureDocument) coiDisclosureForm.getDocument();
                coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
                coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(
                        getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
                setQuestionnaireStatuses(coiDisclosureForm);
                actionForward = mapping.findForward(MASTER_DISCLOSURE);
            }

        }
        // if (coiDisclosureDocument.getCoiDisclosure().isUpdateEvent() && !StringUtils.equals("performLookup",
        // coiDisclosureForm.getMethodToCall())) {
        //            actionForward = mapping.findForward(UPDATE_DISCLOSURE);
        //        }

        if (coiDisclosure.isManualEvent() && !CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
            coiDisclosure.getCoiDisclProjects().get(0).initHeaderItems();
       }

        // initialize the permissions for notes and attachments helper
        //coiDisclosure was becoming null here, so loading the document above.
        //KCCOI-278 added the line above to fix master disclosure issues but this should really be done 
        // in all cases.
        coiDisclosureForm.getCoiNotesAndAttachmentsHelper().prepareView();

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
            }
            else if (command.endsWith(CoiDisclosure.PROTOCOL_DISCL_MODULE_CODE)) {
                eventTypeCode = CoiDisclosureEventType.IRB_PROTOCOL;
            }
            else if (command.endsWith(CoiDisclosure.AWARD_DISCL_MODULE_CODE)) {
                eventTypeCode = CoiDisclosureEventType.AWARD;
            }
            else if (command.endsWith(CoiDisclosure.MANUAL_DISCL_MODULE_CODE)) {
                // this will be reset when 'addmanualproject', and the event type will be selected at that time
                eventTypeCode = CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL;
            }
            else if (command.endsWith("_6")) {
                // this is to update master disclosure. 
                // also treated annual event when master disclosure exist
                eventTypeCode = CoiDisclosureEventType.UPDATE;
            }
            
            // check to see if there's an existing update master that is not approved/disapproved
            CoiDisclosure coiDisclosure = null;
            if (StringUtils.equals(eventTypeCode, CoiDisclosureEventType.UPDATE)) {
                coiDisclosure = getExistingUpdateMasterDisclosure();
            }
            
            // if an existing update master exists, let's load it, otherwise initiate
            if (coiDisclosure != null) {
                coiDisclosureForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
                coiDisclosureForm.setDocId(coiDisclosure.getCoiDisclosureDocument().getDocumentNumber());
            } else {
                coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND);
                coiDisclosure = getCoiDisclosureService().versionCoiDisclosure();                
            }

            forward = super.docHandler(mapping, form, request, response);

            if (CoiDisclosureEventType.UPDATE.equals(eventTypeCode) || (KewApiConstants.INITIATE_COMMAND.startsWith(command)
                    && KRADConstants.DOC_HANDLER_METHOD.equals(coiDisclosureForm.getMethodToCall()) && isMasterDisclosureExist())) {
                // update master disclosure or annual event with master disclosure exist
                if (!isMasterDisclosureExist()) {
                    // minor hack to force CoiDisclosureForm to hide Actions tab
                    coiDisclosureForm.setMethodToCall("viewMasterDisclosure");
                    forward = mapping.findForward("masterDisclosureNotAvailable");
                }
                else {
                    if (StringUtils.equals(coiDisclosureForm.getCommand(), KewApiConstants.INITIATE_COMMAND)) {
                        getCoiDisclosureService().initDisclosureFromMasterDisclosure(coiDisclosure);
                        if (StringUtils.equals(eventTypeCode, CoiDisclosureEventType.ANNUAL)) {
                            coiDisclosure.setAnnualUpdate(true);
                        }
                        coiDisclosure.setEventTypeCode(eventTypeCode);

                        ((CoiDisclosureForm) form).getDisclosureHelper().setMasterDisclosureBean(
                                getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosure));  
                        
                        // this coiDisclosure is not in coidisclosureform yet
                        setQuestionnaireStatuses(coiDisclosureForm, coiDisclosure);
                    }
                    ((CoiDisclosureForm) form).getDisclosureHelper().setMasterDisclosureBean(
                            getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosure));                    
                    forward = mapping.findForward(UPDATE_DISCLOSURE);
                    //((CoiDisclosureForm)form).getCoiNotesAndAttachmentsHelper().prepareView();
                }
            }

            if (coiDisclosure != null) {
                coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
                coiDisclosure.setCoiDisclosureDocument(coiDisclosureForm.getCoiDisclosureDocument());
            }
            coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setEventTypeCode(eventTypeCode);
        }
        else {
            coiDisclosureForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
            super.docHandler(mapping, form, request, response);
            CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
            if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
                ((CoiDisclosureForm) form).getDisclosureHelper().setMasterDisclosureBean(
                        getCoiDisclosureService().getMasterDisclosureDetail(
                        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure())); 
                setQuestionnaireStatuses(coiDisclosureForm);
                forward = mapping.findForward("updateDisclosure");
           } 
        }
        ((CoiDisclosureForm)form).getDisclosureHelper().prepareView();
        ((CoiDisclosureForm)form).getCoiNotesAndAttachmentsHelper().prepareView();

        if (!coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().isUpdateEvent() 
                && (coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isInitiated() || coiDisclosureForm.getCoiDisclosureDocument().getDocumentHeader().getWorkflowDocument().isSaved())) {
            checkToLoadDisclosureDetails(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(),
                    ((CoiDisclosureForm) form).getMethodToCall(), coiDisclosureForm.getDisclosureHelper().getNewProjectId(),
                    coiDisclosureForm.getDisclosureHelper().getNewModuleItemKey());
        }
        //((CoiDisclosureForm)form).getCoiNotesAndAttachmentsHelper().prepareView();

        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().refreshReferenceObject("coiDispositionStatus");

        return forward;
    }

    private void setQuestionnaireStatuses(CoiDisclosureForm coiDisclosureForm) {
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerHeaders(
                coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders());
        coiDisclosureForm.getDisclosureQuestionnaireHelper().resetHeaderLabels();
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerQuestionnaire(false);
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setQuestionnaireActiveStatuses();        
        for (AnswerHeader answerHeader : coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders()) {
            getQuestionnaireAnswerService().setupChildAnswerIndicator(answerHeader.getAnswers());
        }
    }

    private void setQuestionnaireStatuses(CoiDisclosureForm coiDisclosureForm, CoiDisclosure coiDisclosure) {
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerHeaders(
                coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders());
        List<String> questionnaireIds = getExistingQnaire(coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders());
        if (coiDisclosure.isAnnualUpdate()) {
            List<AnswerHeader> answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(
                    new ModuleQuestionnaireBean (CoeusModule.COI_DISCLOSURE_MODULE_CODE, coiDisclosure.getCoiDisclosureNumber(), coiDisclosure.getEventTypeCode(), coiDisclosure.getSequenceNumber().toString(), 
                            false));
            if (CollectionUtils.isEmpty(questionnaireIds)) {
                coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders().addAll(answerHeaders);
            } else {
                for (AnswerHeader answerHeader : answerHeaders) {
                    if (!questionnaireIds.contains(answerHeader.getQuestionnaire().getQuestionnaireId())) {
                        coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders().add(answerHeader);         }
                }
            }
        }
        coiDisclosureForm.getDisclosureQuestionnaireHelper().resetHeaderLabels();
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerQuestionnaire(false);
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setQuestionnaireActiveStatuses();
    }
    
    private List<String> getExistingQnaire(List<AnswerHeader> answerHeaders) {
        List<String> questionnaireIds  = new ArrayList<String>();
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.getModuleSubItemCode().equals("14")) {
                // only the annual one
                questionnaireIds.add(answerHeader.getQuestionnaire().getQuestionnaireId());
            }
        }
        return questionnaireIds;
    }
    
    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);    
    }
    
    private void checkToLoadDisclosureDetails(CoiDisclosure coiDisclosure, String methodToCall, String projectId,
            String moduleItemKey) {
        // TODO : load FE disclosure when creating coi disc
        // still need more clarification on whether there is any other occasion this need to be loaded
        if (coiDisclosure.getCoiDisclosureId() == null && !hasDisclosureDetails(coiDisclosure)) {
            if (StringUtils.equals("newProjectDisclosure", methodToCall) && projectId != null) {
                getCoiDisclosureService().initializeDisclosureProject(coiDisclosure, projectId);
                coiDisclosure.setModuleItemKey(moduleItemKey);
            }
            else {
                getCoiDisclosureService().initializeDisclosureDetails(coiDisclosure);
                coiDisclosure.setModuleItemKey(projectId);
            }
        }
        else {
            if (!StringUtils.equals("addProposal", methodToCall) && !StringUtils.equals("save", methodToCall) && !CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
                for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                    // TODO : need to look into this condition further
                    if (!StringUtils.equals("addProposal", methodToCall) && !StringUtils.equals("save", methodToCall) && coiDisclProject.getCoiDisclProjectsId() != null) {
                        getCoiDisclosureService().updateDisclosureDetails(coiDisclProject);
                    }
                }                
                //getCoiDisclosureService().updateDisclosureDetails(coiDisclosure);
            }
        }
        
        // TODO : for manual proposal project
        if (coiDisclosure.isManualEvent() && !CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                // TODO : need to look into this condition further
                if (!StringUtils.equals("addProposal", methodToCall) && !StringUtils.equals("save", methodToCall)
                        && coiDisclProject.getCoiDisclProjectsId() != null) {
                    getCoiDisclosureService().updateDisclosureDetails(coiDisclProject);
                }
            }
        }
    }
    
    public ActionForward newFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = save(mapping, form, request, response);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
            CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
            String forward = ConfigContext.getCurrentContextConfig().getProperty("kuali.docHandler.url.prefix")
                    + "/financialEntityEditNew.do?methodToCall=addNewCoiDiscFinancialEntity&coiDocId="
                    + ((CoiDisclosureForm) form).getDocument().getDocumentNumber() + "&financialEntityHelper.reporterId="
                    + coiDisclosure.getPersonId();
            // "/portal.do?channelTitle=Financial%20Entity&channelUrl=financialEntityManagement.do?methodToCall=editNew&coiDocId="+((CoiDisclosureForm)
            // form).getDocument().getDocumentNumber();
            return new ActionForward(forward, true);
        }
        return actionForward;

    }

    public ActionForward editFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = save(mapping, form, request, response);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
            CoiDisclosure coiDisclosure = ((CoiDisclosureDocument)coiDisclosureForm.getDocument()).getCoiDisclosure();
            String forward = ConfigContext.getCurrentContextConfig().getProperty("kuali.docHandler.url.prefix")
                    + "/financialEntityEditList.do?methodToCall=editActiveFinancialEntity&coiDocId="
                    + ((CoiDisclosureForm) form).getDocument().getDocumentNumber() + "&financialEntityHelper.editCoiEntityId="
                    + coiDisclosure.getCoiDisclProjects().get(0).getCoiDiscDetails().get(getSelectedLine(request)).getPersonFinIntDisclosureId();
            // "/portal.do?channelTitle=Financial%20Entity&channelUrl=financialEntityManagement.do?methodToCall=editList&coiDocId="+((CoiDisclosureForm)
            // form).getDocument().getDocumentNumber()+"&financialEntityHelper.editEntityIndex"+getSelectedLine(request);
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
        disclosureHelper.getNewCoiDisclProject().setModuleItemKey(disclosureHelper.getNewCoiDisclProject().getCoiProjectId());
        if (checkRule(new AddManualProjectEvent("disclosureHelper.newCoiDisclProject", disclosureHelper.getNewCoiDisclProject()))) {
            getCoiDisclosureService().initializeDisclosureDetails(disclosureHelper.getNewCoiDisclProject());
            disclosureHelper.getNewCoiDisclProject().setSequenceNumber(coiDisclosure.getSequenceNumber());
            disclosureHelper.getNewCoiDisclProject().initHeaderItems();
            coiDisclosure.getCoiDisclProjects().add(disclosureHelper.getNewCoiDisclProject());
            coiDisclosure.setModuleItemKey(disclosureHelper.getNewCoiDisclProject().getProjectId());
            coiDisclosure.setEventTypeCode(disclosureHelper.getNewCoiDisclProject().getDisclosureEventType());
            disclosureHelper.setNewCoiDisclProject(new CoiDisclProject(coiDisclosure.getCoiDisclosureNumber(), coiDisclosure
                    .getSequenceNumber()));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward getDisclosuresToComplete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userId = getUserId();
        DisclosureHelper disclosureHelper = ((CoiDisclosureForm) form).getDisclosureHelper(); 

        disclosureHelper.setNewProposals(getCoiDisclosureService().getProposals(userId));
        disclosureHelper.setNewInstitutionalProposals(getCoiDisclosureService().getInstitutionalProposals(userId));
        disclosureHelper.setNewAwards(getCoiDisclosureService().getAwards(userId));
        disclosureHelper.setNewProtocols(getCoiDisclosureService().getProtocols(userId));

        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward newProjectDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND);
        ActionForward forward = docHandler(mapping, form, request, response);
        // Currently the way docHandler() is implemented in this class guarantees that setting the form command to 'initiate' will
        // result in a disclosure
        // version being created and set on the document in the form. This means the following code for versioning the disclosure
        // and setting it on the
        // form is redundant as it has already happened in the doc handler invocation above, hence its being commented out.
        
        //  CoiDisclosure coiDisclosure = getCoiDisclosureService().versionCoiDisclosure();
        //      if (coiDisclosure != null) {
        //      coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
        //      coiDisclosure.setCoiDisclosureDocument(coiDisclosureForm.getCoiDisclosureDocument());
        //  }
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure()
                .setEventTypeCode(coiDisclosureForm.getDisclosureHelper().getEventTypeCode());
        // dochandler may populate discdetails for new doc.  here is just to reset to reload it again.
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setCoiDisclProjects(null);
        checkToLoadDisclosureDetails(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure(),
                ((CoiDisclosureForm) form).getMethodToCall(), coiDisclosureForm.getDisclosureHelper().getNewProjectId(),
                coiDisclosureForm.getDisclosureHelper().getNewModuleItemKey());
        return forward;
    }

    public ActionForward submitDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        save(mapping, coiDisclosureForm, request, response);
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument)coiDisclosureForm.getDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
       
        if (checkRule(new CertifyDisclosureEvent("disclosureHelper.certifyDisclosure", coiDisclosure))) {
            coiDisclosureForm.setAuditActivated(true);
            AuditActionHelper auditActionHelper = new AuditActionHelper();
            if (auditActionHelper.auditUnconditionally(coiDisclosureDocument)) {
                // Certification occurs after the audit rules pass.
                if (coiDisclosure.getCoiDisclosureId() == null) {
                    coiDisclosure.initRequiredFields();            
                }
                else {
                    getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
                }
                //getCoiDisclosureService().setDisclProjectForSave(coiDisclosure);
                              
                /************ Begin --- Save (if valid) document and questionnaire data ************/
                // TODO factor out the different versions of this doc and questionnaire data save block from various actions in this
                // class and centralize it in a helper method
                // First validate the questionnaire data
                // TODO maybe add a COI questionnaire specific rule event to the condition below
                List<AnswerHeader> answerHeaders = coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders();
                if (applyRules(new SaveQuestionnaireAnswerEvent(coiDisclosureDocument, answerHeaders))) {
                    // since Questionnaire data is OK we try to save doc
                    getDocumentService().saveDocument(coiDisclosureDocument);
                    // check if doc save went OK
                    // TODO Any validation errors during the doc save will cause an exception to be thrown, so perhaps the checking
                    // of message map below is redundant
                    if(GlobalVariables.getMessageMap().hasNoErrors()) {
                        // now save questionnaire data for the disclosure
                        coiDisclosureForm.getDisclosureQuestionnaireHelper().preSave();
                        getBusinessObjectService().save(answerHeaders);
                        
                        // set the disclosure codes
                        coiDisclosure.setDisclosureDispositionCode(CoiDispositionStatus.SUBMITTED_FOR_REVIEW);
                        coiDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.ROUTED_FOR_REVIEW);
                        // Certification occurs after the audit rules pass, and the document and the questionnaire data have been
                        // saved successfully
                        coiDisclosure.certifyDisclosure();
                        forward = submitForReviewAndRedirect(mapping, form, request, response, coiDisclosureForm, coiDisclosure,
                                coiDisclosureDocument);
                    }
                }
                /************ End --- Save (if valid) document and questionnaire data ************/    

            }
            else {
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
            }
        }
        return forward;
    }

    //TODO: This will need some work...
    public ActionForward printDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosure coiDisclosure = ((CoiDisclosureForm)form).getCoiDisclosureDocument().getCoiDisclosure();
        CoiPrintingService printService = KraServiceLocator.getService(CoiPrintingService.class);
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        AbstractPrint printable;
        // AttachmentDataSource dataStream = printService.printDisclosureCertification(coiDisclosure,
        // CoiPrintingService.PRINT_CERTIFICATION, reportParameters);
        printable = getCoiPrintingService().getCoiPrintable(CoiReportType.COI_APPROVED_DISCLOSURE);
        printable.setPrintableBusinessObject(coiDisclosure);
        printableArtifactList.add(printable);
        AttachmentDataSource dataStream = getCoiPrintingService().print(printableArtifactList);
        streamToResponse(dataStream, response);
        //return mapping.findForward(Constants.MAPPING_BASIC);
        actionForward = RESPONSE_ALREADY_HANDLED;
        return actionForward;
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
        }
        else {
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
        final StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmMethod,
                KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, attachment.getDescription(), attachment.getFile().getName());
        
        return confirm(confirm, confirmMethod, CONFIRM_NO_DELETE);
    }

    public ActionForward confirmDeleteCoiDisclosureAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.deleteAttachment(mapping, (CoiDisclosureForm) form, request, response, CoiDisclosureAttachment.class);

    }
    
    private ActionForward deleteAttachment(ActionMapping mapping, CoiDisclosureForm form, HttpServletRequest request,
            HttpServletResponse response, Class<CoiDisclosureAttachment> attachmentType) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        if (!helper.deleteExistingAttachmentByType(selection)) {
            //may want to tell the user the selection was invalid.
        }
        save(mapping, form, request, response);

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
        /*
        if (attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile = getCoiDisclosureAttachmentFile(form,attachment);
            if (attachmentFile != null){
                this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()), getValidHeaderString(file.getType()),
                        response);
            }
            return RESPONSE_ALREADY_HANDLED;
        } 
        */       
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }

    private byte[] getCoiDisclosureAttachmentFile(ActionForm form, CoiDisclosureAttachment attachment) {
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
                            attachmentFile = getWatermarkService().applyWatermark(file.getData(),
                                    printableArtifacts.getWatermarkable().getWatermark());
                        }
                    }
                }
                else {
                    attachmentFile = getWatermarkService().applyWatermark(file.getData(),
                            printableArtifacts.getWatermarkable().getWatermark());
                }
            }
        }
        catch (Exception e) {
        }        
        return attachmentFile;
    }

    public ActionForward updateAttachmentFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        helper.addNewCoiDisclosureAttachmentFilter();
        return mapping.findForward(Constants.MAPPING_BASIC);
    } 

    /*
     * public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse
     * response) throws Exception { CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm)
     * form).getCoiNotesAndAttachmentsHelper(); helper.fixReloadedAttachments(request.getParameterMap()); super.save(mapping, form,
     * request, response); return mapping.findForward(Constants.MAPPING_BASIC);
     * 
     * }
     */

    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        super.postSave(mapping, form, request, response);
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();

        if (!(helper.getFilesToDelete().isEmpty())) {
            getBusinessObjectService().delete((helper.getFilesToDelete()));
            helper.getFilesToDelete().clear();
        }

        for (CoiDisclosureAttachment attachment : ((CoiDisclosureForm) form).getCoiDisclosureDocument().getCoiDisclosure()
                .getCoiDisclosureAttachments()) {
            // for some reason, change and save, this list is not updated 
            attachment.getCoiDisclosure().refreshReferenceObject("coiDisclosureAttachments");
        }
    }
    
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper(); 
        helper.addNewNote();
        save(mapping, form, request, response);
        helper.setManageNotesOpen();
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward editNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final int selection = this.getSelectedLine(request);
        CoiDisclosureForm disclosureForm = (CoiDisclosureForm) form;   
        CoiNotesAndAttachmentsHelper helper = disclosureForm.getCoiNotesAndAttachmentsHelper();   
        // add authorization here
        helper.editNote(selection);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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
    
//    protected BusinessObjectService getBusinessObjectService() {
//        return  KraServiceLocator.getService(BusinessObjectService.class);  
//    }
    
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
        }
        else {
            coiDisclosureForm.setDocId(disclosures.get(0).getCoiDisclosureDocument().getDocumentNumber());
            loadDocument(coiDisclosureForm);
            disclosureHelper.setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(
                    coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure()));
            setQuestionnaireStatuses(coiDisclosureForm);
            return mapping.findForward("masterDisclosure");
        }
    }

    private ActionForward submitForReviewAndRedirect(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, CoiDisclosureForm coiDisclosureForm, CoiDisclosure coiDisclosure,
                                                     CoiDisclosureDocument coiDisclosureDocument) throws Exception {
    
        SubmitDisclosureAction submitAction = coiDisclosureForm.getDisclosureActionHelper().getSubmitDisclosureAction();
        ActionForward action = getDisclosureActionService().sendCertificationNotifications(coiDisclosureDocument,
                coiDisclosureForm, submitAction, mapping);
        if (action != null) {
            return action;
        }
        getDisclosureActionService().submitToWorkflow(coiDisclosureDocument, coiDisclosureForm, submitAction);
        
        //Since we are exiting the disclosure through a non-standard method, lets release
        //any pessimistic locks
        this.attemptLockRelease(coiDisclosureDocument, KRADConstants.CLOSE_METHOD);
        
        return routeDisclosureToHoldingPage(mapping, coiDisclosureForm);
    }
    
    private ActionForward routeDisclosureToHoldingPage(ActionMapping mapping, CoiDisclosureForm coiDisclosureForm) {
        String routeHeaderId = coiDisclosureForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_BASIC, "CoiDisclosureDocument");
        
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

        List<CoiDisclosureEventType> disclosureEventTypes = (List<CoiDisclosureEventType>) getBusinessObjectService().findMatching(
                CoiDisclosureEventType.class, fieldValues);
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
                }
                catch (Exception e) {
                    //Failed to load select box 
                }
            }
            
            // disclosure ID and label are always required, so put in a default
            if (StringUtils.isEmpty(disclosureEventType.getProjectIdLabel())) {
                disclosureEventType.setProjectIdLabel(KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(DEFAULT_EVENT_ID_STRING));
            }
            if (StringUtils.isEmpty(disclosureEventType.getProjectTitleLabel())) {
                disclosureEventType.setProjectTitleLabel(KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(DEFAULT_EVENT_TITLE_STRING));
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, disclosureEventTypeAjaxBean);
            
            response.setContentType("application/json");
            ServletOutputStream out = response.getOutputStream();

            try {
                out.write(writer.getBuffer().toString().getBytes());
                out.flush();
                out.close();
            }
            catch (Exception e) {
                e.printStackTrace(new PrintWriter(out));
            }
        
        }        
        return null;
    }
    
    private boolean hasDisclosureDetails(CoiDisclosure coiDisclosure) {
        boolean result = false;
        if (!CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
            for (CoiDisclProject project : coiDisclosure.getCoiDisclProjects()) {
                if (!CollectionUtils.isEmpty(project.getCoiDiscDetails())) {
                    result = true;
                    break;
                }
            }
        }
        
        return result;
    }

    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        Document document = coiDisclosureForm.getDocument();
        
        /************ Begin --- Save (if valid) document and questionnaire data ************/
        // TODO factor out the different versions of this doc and questionnaire data save block from various actions in this class
        // and centralize it in a helper method
        // First validate the questionnaire data
        // TODO maybe add a COI questionnaire specific rule event to the condition below
        List<AnswerHeader> answerHeaders = coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders();
        if ( applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders))) {
            // since Questionnaire data is OK we try to save doc
            actionForward = super.saveOnClose(mapping, form, request, response);
            // check if doc save went OK
            // TODO Any validation errors during the doc save will cause an exception to be thrown, so perhaps the checking of
            // message map below is redundant
            if(GlobalVariables.getMessageMap().hasNoErrors()) {
                // now save questionnaire data for the disclosure
                coiDisclosureForm.getDisclosureQuestionnaireHelper().preSave();
                getBusinessObjectService().save(answerHeaders);
            }
        }
        /************ End --- Save (if valid) document and questionnaire data ************/
         
        return actionForward;
    }
    
    
    /**
     * Questionnaire related actions below, should perhaps eventually be moved to a separate class for the sake of coherence of this
     * action class
     **/
    
    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : this is only available after questionnaire is saved ?
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        CoiDisclosure disclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
        final int answerHeaderIndex = this.getSelectedLine(request);
        // TODO : a flag to check whether to print answer or not
        // for release 3 : if questionnaire questions has answer, then print answer. 
        reportParameters.put("questionnaireId",
                coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire()
                        .getQuestionnaireIdAsInteger());
        reportParameters.put("template",
                coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire()
                        .getTemplate());
        // get the submodule item code from the module questionnaire bean and put it in the report params
        // set up sequencenumber for masterdisclosure qn.  so, it can be pulled from original disclosure
        //  String moduleSubItemCode = (new DisclosureModuleQuestionnaireBean(disclosure)).getModuleSubItemCode();
        AnswerHeader answerHeader = coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex);
        String moduleSubItemCode = answerHeader.getModuleSubItemCode();
        if (answerHeader.getOriginalCoiDisclosureId() != null) {
            answerHeader.refreshReferenceObject("originalCoiDisclosure");
            reportParameters.put("sequenceNumber", answerHeader.getOriginalCoiDisclosure().getSequenceNumber().toString());
        }
        
        reportParameters.put("coeusModuleSubItemCode", moduleSubItemCode);
        
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(disclosure, reportParameters);
        if (dataStream.getContent() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    protected QuestionnairePrintingService getQuestionnairePrintingService() {
        return KraServiceLocator.getService(QuestionnairePrintingService.class);
    }
    
    
    /**
     * 
     * This method is for the 'update' button to update questionnaire answer to new version
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateAnswerToNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((CoiDisclosureForm) form).getDisclosureQuestionnaireHelper().updateQuestionnaireAnswer(getLineToDelete(request));
        getBusinessObjectService().save(
                ((CoiDisclosureForm) form).getDisclosureQuestionnaireHelper().getAnswerHeaders().get(getLineToDelete(request)));
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward =  super.refresh(mapping, form, request, response);
        if (request.getParameter("refreshCaller") != null
                && request.getParameter("refreshCaller").toString().equals("kualiLookupable")) {
            // Lookup field 'onchange' is not working if it is return a value from 'lookup', so do it on server side
            for (Object obj : request.getParameterMap().keySet()) {
                if (StringUtils.indexOf((String) obj, ((CoiDisclosureForm) form).getQuestionnaireFieldStarter()) == 0) {
                    ((CoiDisclosureForm) form).getDisclosureQuestionnaireHelper().updateChildIndicator(
                            Integer.parseInt(StringUtils.substringBetween((String) obj,
                                    ((CoiDisclosureForm) form).getQuestionnaireFieldStarter(), "].answers[")));
                }
            }
        }
        return forward;
    }
    
    private boolean isMasterDisclosureExist() {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) getBusinessObjectService().findMatching(CoiDisclosure.class,
                fieldValues);
        return !CollectionUtils.isEmpty(disclosures);

    }
    
    private CoiDisclosure getExistingUpdateMasterDisclosure() {
        CoiDisclosure updateMaster = null;
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());
        fieldValues.put("eventTypeCode", CoiDisclosureEventType.UPDATE);

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) getBusinessObjectService().findMatchingOrderBy(CoiDisclosure.class,
                fieldValues, "sequenceNumber", false);
        
        if (!CollectionUtils.isEmpty(disclosures)) {
            for (CoiDisclosure disc : disclosures) {
                if (!StringUtils.equals(disc.getDisclosureStatusCode(), CoiDisclosureStatus.APPROVED) &&
                    !StringUtils.equals(disc.getDisclosureStatusCode(), CoiDisclosureStatus.DISAPPROVED)) {
                    updateMaster = disc;
                    break;
                }
            }
        }
        
        return updateMaster;
    }
}
