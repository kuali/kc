/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.controller.SysFrameworkControllerConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.actions.CoiDisclosureActionService;
import org.kuali.kra.coi.certification.CertifyDisclosureEvent;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.coi.print.CoiReportType;
import org.kuali.kra.coi.questionnaire.DisclosureModuleQuestionnaireBean;
import org.kuali.kra.coi.questionnaire.DisclosureQuestionnaireHelper;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.common.questionnaire.framework.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoiDisclosureAction extends CoiAction {
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String ATTACHMENT_PATH = "document.coiDisclosureList[0].attachmentCoiDisclosures[";
    private static final String CONFIRM_NO_DELETE = "";
    private static final String DEFAULT_EVENT_ID_STRING = "label.coi.disclosure.type.id";
    private static final String DEFAULT_EVENT_TITLE_STRING = "label.coi.disclosure.type.title";
    protected static final String SCREENING_QUESTIONNAIRE_FAILURE_QUESTION = "CoiDisclosureScreeningQuestionnaireFailureQuestion";

    
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
        boolean isValid = true;
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        // notes and attachments
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();        
       
        
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        helper.setOriginalDisclosureIdsIfNecessary(coiDisclosure);
        
        if (coiDisclosure.getCoiDisclosureId() == null) {
            coiDisclosure.initRequiredFields();            
        }
        else {
            getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
        }
        if (coiDisclosure.isUpdateEvent() ||(coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            isValid &= getCoiDisclosureService().setDisclProjectForSave(coiDisclosure, coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean());
        }
        getCoiDisclosureService().updateDisclosureAndProjectDisposition(coiDisclosure);
        
        /************ Begin --- Save (if valid) document and questionnaire data ************/
        // First validate the questionnaire data
        if (coiDisclosure.getCoiDisclProjects() != null || !coiDisclosure.getCoiDisclProjects().isEmpty()) {
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                if (!new CoiDisclosureAdministratorActionRule().isValidDispositionStatus(coiDisclProject.getDisclosureDispositionCode())) {
                    isValid = false;
                }
            }
        }
        if (validateQuestionnaires(coiDisclosureForm)) {
            // since Questionnaire data is OK we try to save doc
            if (isValid) {
                actionForward = super.save(mapping, form, request, response);
                saveQuestionnaires(coiDisclosureForm);
            }
            
            helper.fixReloadedAttachments(request.getParameterMap());
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
    
    protected boolean validateQuestionnaires(CoiDisclosureForm coiDisclosureForm) {
        List<AnswerHeader> answerHeaders = generateListOfQuestionnaires(coiDisclosureForm);
        return applyRules(new SaveQuestionnaireAnswerEvent(coiDisclosureForm.getCoiDisclosureDocument(), answerHeaders));        
    }
    
    protected List<AnswerHeader> generateListOfQuestionnaires(CoiDisclosureForm coiDisclosureForm) {
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders.addAll(coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders());
        answerHeaders.addAll(coiDisclosureForm.getScreeningQuestionnaireHelper().getAnswerHeaders());
        if (coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean() != null) {
            List<List<CoiDisclosureProjectBean>> allProjects = coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getProjectLists();
            for (List<CoiDisclosureProjectBean> projectList : allProjects) {
                for (CoiDisclosureProjectBean bean : projectList) {
                    answerHeaders.addAll(bean.getAnswerHeaders());
                }
            }
        }
        return answerHeaders;
    }
    
    protected void saveQuestionnaires(CoiDisclosureForm coiDisclosureForm) {
        List<AnswerHeader> answerHeaders = generateListOfQuestionnaires(coiDisclosureForm);
        
        coiDisclosureForm.getDisclosureQuestionnaireHelper().preSave();
        coiDisclosureForm.getScreeningQuestionnaireHelper().preSave();
        if (coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean() != null) {
            List<List<CoiDisclosureProjectBean>> allProjects = coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getProjectLists();                
            for (List<CoiDisclosureProjectBean> projectList : allProjects) {
                for (CoiDisclosureProjectBean bean : projectList) {
                    bean.getProjectQuestionnaireHelper().preSave(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure());
                }
            }
        }
        getBusinessObjectService().save(answerHeaders);
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
                && !(coiDisclosure.isManualEvent() && CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects()))) {
            boolean forceQnnrReload = false;
            // TODO : this is pretty hacky to refresh qn
            if ((StringUtils.equals("reload", coiDisclosureForm.getMethodToCall()) && !coiDisclosure.isApprovedDisclosure() && !coiDisclosure.isAnnualUpdate() && !coiDisclosure.isUpdateEvent())
                    || (StringUtils.equals("addManualProject", coiDisclosureForm.getMethodToCall()))) {
                forceQnnrReload = true;
            }            
            coiDisclosureForm.getDisclosureQuestionnaireHelper().prepareView(forceQnnrReload);
            coiDisclosureForm.getScreeningQuestionnaireHelper().prepareView(forceQnnrReload);
        }
        
        // now the rest of subclass-specific custom logic for execute()
        coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
        if ((StringUtils.equals("reload", coiDisclosureForm.getMethodToCall())
                || StringUtils.equals("updateAttachmentFilter", coiDisclosureForm.getMethodToCall())
                || StringUtils.equals("headerTab", coiDisclosureForm.getMethodToCall()) || StringUtils.equals("docHandler",
                coiDisclosureForm.getMethodToCall())) && coiDisclosureDocument.getCoiDisclosure().isApprovedDisclosure()) {
            coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
            setQuestionnaireStatuses(coiDisclosureForm);
            actionForward = mapping.findForward(MASTER_DISCLOSURE);
        }
        else {
            if (StringUtils.isNotBlank(command) && MASTER_DISCLOSURE.equals(command)) {
                coiDisclosureDocument = (CoiDisclosureDocument) coiDisclosureForm.getDocument();
                coiDisclosureDocument.getCoiDisclosure().initSelectedUnit();
                coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureDocument.getCoiDisclosure()));
                setQuestionnaireStatuses(coiDisclosureForm);
                actionForward = mapping.findForward(MASTER_DISCLOSURE);
            }

        }
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
            String[] parts = command.split("_");
            if (parts.length > 1) {
                eventTypeCode = parts[1];
            }
            
            // check to see if there's an existing update master or annual that is not approved/disapproved
            CoiDisclosure coiDisclosure = null;
            if (StringUtils.equals(eventTypeCode, CoiDisclosureEventType.UPDATE) 
                    || StringUtils.equals(eventTypeCode, CoiDisclosureEventType.ANNUAL)) {
                coiDisclosure = getExistingDisclosure(eventTypeCode);
            }
            
            // if an existing update master exists, let's load it, otherwise initiate
            if (coiDisclosure != null) {
                coiDisclosureForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
                coiDisclosureForm.setDocId(coiDisclosure.getCoiDisclosureDocument().getDocumentNumber());
            } else {
                coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND);
                coiDisclosure = getCoiDisclosureService().versionCoiDisclosure();
                // quick-fix: resetting the annual update flag value inherited via above versioning
                if(coiDisclosure != null) {
                    coiDisclosure.setAnnualUpdate(false);                 
                }
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
                        coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosure));  
                        
                        if (coiDisclosure != null) {
                            coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
                            coiDisclosure.setCoiDisclosureDocument(coiDisclosureForm.getCoiDisclosureDocument());
                        }
                        setQuestionnaireStatuses(coiDisclosureForm, coiDisclosure);
                    }else {
                        coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosure));                    
                    }
                    forward = mapping.findForward(UPDATE_DISCLOSURE);
                }
            }

            if (coiDisclosure != null) {
                coiDisclosureForm.getCoiDisclosureDocument().setCoiDisclosure(coiDisclosure);
                coiDisclosure.setCoiDisclosureDocument(coiDisclosureForm.getCoiDisclosureDocument());
            }
            coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setEventTypeCode(eventTypeCode);
            if (coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclosureId() == null) {
                coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().initRequiredFields();            
            }
            else {
                getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
            }
        }
        else {
            coiDisclosureForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
            super.docHandler(mapping, form, request, response);
            CoiDisclosure coiDisclosure = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure();
            if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
                coiDisclosureForm.getDisclosureHelper().setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure())); 
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
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().refreshReferenceObject("coiDispositionStatus");
        coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().setCoiDisclosureAttachmentFilter(coiDisclosureForm.getCoiNotesAndAttachmentsHelper().getNewAttachmentFilter());
        return forward;
    }

    private void setQuestionnaireStatuses(CoiDisclosureForm coiDisclosureForm) {
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerHeaders(
                coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders());
        coiDisclosureForm.getDisclosureQuestionnaireHelper().resetHeaderLabels();
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerQuestionnaire(false);
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setQuestionnaireActiveStatuses();        
        for (AnswerHeader answerHeader : coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders()) {
            getQuestionnaireAnswerService().setupChildAnswerIndicator(answerHeader);
        }
    }

    private void setQuestionnaireStatuses(CoiDisclosureForm coiDisclosureForm, CoiDisclosure coiDisclosure) {
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerHeaders(
                coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders());
        List<AnswerHeader> answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(
                new DisclosureModuleQuestionnaireBean(coiDisclosure));
        if (CollectionUtils.isEmpty(coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders())) {
            coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders().addAll(answerHeaders);
        } else {
            for (AnswerHeader answerHeader : answerHeaders) {
                boolean exists = false;
                for (AnswerHeader existingHeader : coiDisclosureForm.getDisclosureQuestionnaireHelper().getAnswerHeaders()) {
                    if (StringUtils.equals(existingHeader.getModuleSubItemCode(), answerHeader.getModuleSubItemCode())
                            && existingHeader.getQuestionnaireId().equals(answerHeader.getQuestionnaireId())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean().getAnswerHeaders().add(answerHeader);
                }
            }
        }
        coiDisclosureForm.getDisclosureQuestionnaireHelper().resetHeaderLabels();
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setAnswerQuestionnaire(false);
        coiDisclosureForm.getDisclosureQuestionnaireHelper().setQuestionnaireActiveStatuses();
    }
    
    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
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
            String forward = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(SysFrameworkControllerConstants.CONFIG_KUALI_DOCHANDLER_URL_PREFIX)
                    + "/financialEntityEditNew.do?methodToCall=addNewCoiDiscFinancialEntity&coiDocId="
                    + ((CoiDisclosureForm) form).getDocument().getDocumentNumber() + "&financialEntityHelper.reporterId="
                    + coiDisclosure.getPersonId();
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
            String forward = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(SysFrameworkControllerConstants.CONFIG_KUALI_DOCHANDLER_URL_PREFIX)
                    + "/financialEntityEditList.do?methodToCall=editActiveFinancialEntity&coiDocId="
                    + ((CoiDisclosureForm) form).getDocument().getDocumentNumber() + "&financialEntityHelper.editCoiEntityId="
                    + coiDisclosure.getCoiDisclProjects().get(0).getCoiDiscDetails().get(getSelectedLine(request)).getPersonFinIntDisclosureId();
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
        disclosureHelper.getNewCoiDisclProject().setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
        disclosureHelper.getNewCoiDisclProject().setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
        disclosureHelper.getNewCoiDisclProject().refreshReferenceObject("coiDispositionStatus");
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
        getCoiDisclosureService().populateProposalsAndAwardToCompleteDisclosure(userId, disclosureHelper);
        disclosureHelper.setNewProtocols(getCoiDisclosureService().getProtocols(userId));
        disclosureHelper.setNewIacucProtocols(getCoiDisclosureService().getIacucProtocols(userId));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward newProjectDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        coiDisclosureForm.setCommand(KewApiConstants.INITIATE_COMMAND + "_" + coiDisclosureForm.getDisclosureHelper().getEventTypeCode());
        ActionForward forward = docHandler(mapping, form, request, response);
        // Currently the way docHandler() is implemented in this class guarantees that setting the form command to 'initiate' will
        // result in a disclosure
        // version being created and set on the document in the form. This means the following code for versioning the disclosure
        // and setting it on the
        // form is redundant as it has already happened in the doc handler invocation above, hence its being commented out.
        
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
            coiDisclosureForm.setUnitRulesMessages(getUnitRulesMessages(coiDisclosureForm.getCoiDisclosureDocument()));
            AuditHelper auditHelper = KcServiceLocator.getService(AuditHelper.class);
            if (auditHelper.auditUnconditionally(coiDisclosureDocument) && !coiDisclosureForm.isUnitRulesErrorsExist()) {
                // Certification occurs after the audit rules pass.
                if (coiDisclosure.getCoiDisclosureId() == null) {
                    coiDisclosure.initRequiredFields();            
                }
                else {
                    getCoiDisclosureService().resetLeadUnit(coiDisclosure.getDisclosureReporter());
                }
                              
                /************ Begin --- Save (if valid) document and questionnaire data ************/
                // TODO factor out the different versions of this doc and questionnaire data save block from various actions in this
                // class and centralize it in a helper method
                // First validate the questionnaire data
                // TODO maybe add a COI questionnaire specific rule event to the condition below
                if (validateQuestionnaires(coiDisclosureForm)) {
                    if (!getCoiDisclosureService().checkScreeningQuestionnaireRule(coiDisclosureDocument)) {
                        return promptForScreeningQuestionnaireFailure(mapping, form, request, response);
                    }
                    // since Questionnaire data is OK we try to save doc
                    getDocumentService().saveDocument(coiDisclosureDocument);
                    saveQuestionnaires(coiDisclosureForm);
                    // set the disclosure codes
                    coiDisclosure.setDisclosureDispositionCode(CoiDispositionStatus.SUBMITTED_FOR_REVIEW);
                    coiDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.ROUTED_FOR_REVIEW);

                    // Update the corresponding discl project
                    getCoiDisclosureActionService().updateCoiDisclProjectStatus(coiDisclosure, CoiDisclosureStatus.ROUTED_FOR_REVIEW);
                    getCoiDisclosureActionService().updateCoiDisclProjectDisposition(coiDisclosure, CoiDispositionStatus.NO_CONFLICT_EXISTS);
                    
                    // Certification occurs after the audit rules pass, and the document and the questionnaire data have been
                    // saved successfully
                    coiDisclosure.certifyDisclosure();
                    GlobalVariables.getMessageMap().putInfo("datavalidation", KeyConstants.MESSAGE_COI_CERT_SUBMITTED,  new String[] {});
                    forward = submitForReviewAndRedirect(mapping, form, request, response, coiDisclosureForm, coiDisclosure,
                            coiDisclosureDocument);
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
    
    protected ActionForward promptForScreeningQuestionnaireFailure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        if(question == null){
            return this.performQuestionWithoutInput(mapping, form, request, response, SCREENING_QUESTIONNAIRE_FAILURE_QUESTION, "Based on answers to the screening questionnaire you are required to have at least one active financial entity to submit this disclosure. Would you like add a financial entity at this time?", KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
        } else if(SCREENING_QUESTIONNAIRE_FAILURE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
            return newFinancialEntity(mapping, form, request, response);
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }    

    }

    public ActionForward printDisclosureCertification(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CoiDisclosure coiDisclosure = ((CoiDisclosureForm)form).getCoiDisclosureDocument().getCoiDisclosure();
        coiDisclosure.setCertificationText(new String(coiDisclosure.getAcknowledgementStatement()));
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        AbstractPrint printable;
        printable = getCoiPrintingService().getCoiPrintable(CoiReportType.COI_APPROVED_DISCLOSURE);
        printable.setPrintableBusinessObject(coiDisclosure);
        printableArtifactList.add(printable);
        AttachmentDataSource dataStream = getCoiPrintingService().print(printableArtifactList);
        streamToResponse(dataStream, response);
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
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }

    public ActionForward updateAttachmentFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CoiNotesAndAttachmentsHelper helper = ((CoiDisclosureForm) form).getCoiNotesAndAttachmentsHelper();
        helper.addNewCoiDisclosureAttachmentFilter();
        return mapping.findForward(Constants.MAPPING_BASIC);
    } 

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
        return  KcServiceLocator.getService(CoiPrintingService.class);
    }
    
    protected WatermarkService getWatermarkService() {
        return  KcServiceLocator.getService(WatermarkService.class);
    }
    
    protected CoiDisclosureActionService getDisclosureActionService() {
        return  KcServiceLocator.getService(CoiDisclosureActionService.class);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ActionForward viewMasterDisclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        return viewMasterDisclosure(GlobalVariables.getUserSession().getPrincipalId(), coiDisclosureForm, mapping);
    }
    
    public ActionForward viewMasterDisclosure(String personId, CoiDisclosureForm coiDisclosureForm, ActionMapping mapping) throws WorkflowException {
        DisclosureHelper disclosureHelper = coiDisclosureForm.getDisclosureHelper();
        Map fieldValues = new HashMap();
        fieldValues.put("personId", personId);
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) getBusinessObjectService().findMatching(CoiDisclosure.class, fieldValues);
        disclosureHelper.prepareView();
        if (CollectionUtils.isEmpty(disclosures)) {
            return mapping.findForward("masterDisclosureNotAvailable");
        }
        else {
            coiDisclosureForm.setDocId(disclosures.get(0).getCoiDisclosureDocument().getDocumentNumber());
            loadDocument(coiDisclosureForm);
            disclosureHelper.setMasterDisclosureBean(getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure()));
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
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);

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
                disclosureEventType.setProjectIdLabel(CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(DEFAULT_EVENT_ID_STRING));
            }
            if (StringUtils.isEmpty(disclosureEventType.getProjectTitleLabel())) {
                disclosureEventType.setProjectTitleLabel(CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(DEFAULT_EVENT_TITLE_STRING));
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
        CoiDisclosure coiDisclosure = ((CoiDisclosureDocument) document).getCoiDisclosure();
        boolean isValid = true;
        if (coiDisclosure.getCoiDisclProjects() != null || !coiDisclosure.getCoiDisclProjects().isEmpty()) {
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                if (!new CoiDisclosureAdministratorActionRule().isValidStatus(
                        coiDisclosure.getCoiDisclosureStatus().getCoiDisclosureStatusCode(), coiDisclProject.getDisclosureDispositionCode())) {
                    isValid = false;
                }
            }
        }
        /************ Begin --- Save (if valid) document and questionnaire data ************/
        // TODO factor out the different versions of this doc and questionnaire data save block from various actions in this class
        // and centralize it in a helper method
        // First validate the questionnaire data
        // TODO maybe add a COI questionnaire specific rule event to the condition below
        if (validateQuestionnaires(coiDisclosureForm)) {
            // since Questionnaire data is OK we try to save doc
        	if (isValid) {
        	    actionForward = super.saveOnClose(mapping, form, request, response);
        	    saveQuestionnaires(coiDisclosureForm);
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
        String methodToCall = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String formProperty = StringUtils.substringBetween(methodToCall, ".printQuestionnaireAnswer.", ".line");
        DisclosureQuestionnaireHelper helper = (DisclosureQuestionnaireHelper) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(form, formProperty);
        AnswerHeader answerHeader = helper.getAnswerHeaders().get(answerHeaderIndex);
        // TODO : a flag to check whether to print answer or not
        // for release 3 : if questionnaire questions has answer, then print answer. 
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME,
                answerHeader.getQuestionnaire()
                        .getQuestionnaireSeqIdAsInteger());
        reportParameters.put("template",
                answerHeader.getQuestionnaire()
                        .getTemplate());
        reportParameters.put("coeusModuleSubItemCode", answerHeader.getModuleSubItemCode());
        
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(disclosure, reportParameters);
        if (dataStream.getData() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    protected QuestionnairePrintingService getQuestionnairePrintingService() {
        return KcServiceLocator.getService(QuestionnairePrintingService.class);
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
        String methodToCallStart = "methodToCall.updateAnswerToNewVersion.";
        String methodToCallEnd = ".line";
        String methodToCall = ((String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE));
        String questionnaireHelperPath = methodToCall.substring(methodToCallStart.length(), methodToCall.indexOf(methodToCallEnd));
        QuestionnaireHelperBase helper = (QuestionnaireHelperBase) PropertyUtils.getNestedProperty(form, questionnaireHelperPath);
        helper.updateQuestionnaireAnswer(getLineToDelete(request));
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
    
    private CoiDisclosure getExistingDisclosure(String eventTypeCode) {
        CoiDisclosure updateMaster = null;
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());
        fieldValues.put("eventTypeCode", eventTypeCode);

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
    
    public ActionForward viewDisclosureNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) form;
        String notificationId = request.getParameter("notificationId");
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("notificationId", notificationId);
        List<CoiNotification> notifications = (List<CoiNotification>) getBusinessObjectService().findMatching(CoiNotification.class, fieldValues);
        coiDisclosureForm.getDisclosureHelper().setViewNotification(notifications.get(0));
        return mapping.findForward("viewNotification");
    }
    
}
