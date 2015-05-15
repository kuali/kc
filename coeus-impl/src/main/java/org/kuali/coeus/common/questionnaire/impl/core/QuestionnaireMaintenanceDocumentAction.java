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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.questionnaire.framework.core.*;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

/*
 * Big issue is that questionnairequestions and usages can't be included in xmldoccontent because maintframework - questions &amp;
 * usages are not defined in maint DD's 'maintsections'. Current work around is using KraMaintenanceDocument to make rice
 * maintenance framework to think that QnQuestions &amp; QnUsages are defined in maintenance section. So, they will be saved in
 * xmldoccontent.
 * 
 * The hierarchical nature of data and heavily depending on js also needs some manipulation, so make these a little
 * complicated..
 */
/**
 * This is the maintenance action class is for questionnaire.
 */
public class QuestionnaireMaintenanceDocumentAction extends KualiMaintenanceDocumentAction {
    private static final String PCP = "#;#";
    private static final String PQP = "#q#";
    private static final String PUP = "#u#";
    private static final String PFP = "#f#";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    public static final String ID = "id";
    
    public static final String DOCUMENT_NUMBER = "documentNumber";
    public static final String QUESTIONNAIRE = "questionnaire";
    public static final String TEMPLATE = "template";
    public static final String SEQ_QUESTIONNAIRE_REF_ID = "SEQ_QUESTIONNAIRE_REF_ID";
    public static final String MODULE_CODE = "moduleCode";
    public static final String SUB_MODULE_CODE = "subModuleCode";
    public static final String N = "N";
    public static final String QUESTION = "question";

    private VersioningService versioningService;

    protected VersioningService getVersioningService (){
        if (versioningService == null) {
         versioningService = KcServiceLocator.getService(VersioningService.class);
        }
        return versioningService;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        ActionForward forward = super.execute(mapping, qnForm, request, response);
        //check for deleted usages after the execute as execute is what populates the form inputs. Should be fine here as page is not
        //rendered until later. Check for same in save as should happen before save though.
        if (qnForm.getDocument() != null && ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject() != null) {
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getDataObject();
            removeDeletedUsages(newQuestionnaire);
        }
        return forward;
    }
    
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getDataObject();
        removeDeletedUsages(newQuestionnaire);
        if (validateTemplateField(qnForm)) {
            if (newQuestionnaire.getSequenceNumber() == null) {
                newQuestionnaire.setSequenceNumber(1);
            }
            setupQuestionAndUsage(form);
            if (qnForm.getTemplateFile() != null && StringUtils.isNotBlank(qnForm.getTemplateFile().getFileName())) {
                newQuestionnaire.setFileName(qnForm.getTemplateFile().getFileName());
                newQuestionnaire.setTemplate(qnForm.getTemplateFile().getFileData());
            }
            qnForm.setNewQuestionnaireUsage(new QuestionnaireUsage());
            newQuestionnaire.setDocumentNumber(((MaintenanceDocumentBase) qnForm.getDocument()).getDocumentNumber());
            ActionForward forward = super.save(mapping, form, request, response);
            
            checkAndSetAllQuestionsAreUpToDate(qnForm);
            return forward; 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    private boolean validateTemplateField(QuestionnaireMaintenanceForm qnForm) {
        boolean retVal = true;
        final String fieldName = "document.newMaintainableObject.businessObject.fileName";
        try {
            if (ObjectUtils.isNotNull(qnForm.getTemplateFile()) && !StringUtils.isBlank(qnForm.getTemplateFile().getFileName()) && (qnForm.getTemplateFile().getFileData() == null || qnForm.getTemplateFile().getFileData().length <= 0)) {
                GlobalVariables.getMessageMap().putError(fieldName, KeyConstants.ERROR_QUESTIONNAIRE_FILENAME_INVALID);
                retVal = false;
            }
        }
        catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(fieldName, KeyConstants.ERROR_QUESTIONNAIRE_FILENAME_INVALID);
            retVal = false;
        }
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(fieldName, KeyConstants.ERROR_QUESTIONNAIRE_FILENAME_INVALID);
            retVal = false;
        }
        return retVal;
    }


    /*
     * set up question and usage data for JS to parse and create QnQuestion tree nodes and usage list items
     */
    private void setupQuestionAndUsage(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages(questionnaire);
        qnForm.setEditData(questions + PCP + usages);
        
    }
    
    private void checkAndSetAllQuestionsAreUpToDate(QuestionnaireMaintenanceForm qnForm) {
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject());
        qnForm.setAllQuestionsAreUpToDate(checkIfAllQuestionsAreUpToDate(questionnaire));
    }

    private boolean checkIfAllQuestionsAreUpToDate(Questionnaire questionnaire) {
        boolean retVal = true;
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getQuestion() == null || !question.getQuestionId().equals(question.getQuestion().getId())) {
                question.refreshReferenceObject("question");
            }
            if( !("N".equals(getVersionedQuestion(question))) ) {
                retVal = false;
                break;
            }
        }
        return retVal;
    }


    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        permissionCheckForDocHandler(form);
        ActionForward forward = super.docHandler(mapping, form, request, response);
        setupQuestionAndUsage(form);
        
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        checkAndSetAllQuestionsAreUpToDate(qnForm);
        
        return forward;
    }

    /*
     * check if user has modify or view questionnaire permission.
     */
    private void permissionCheckForDocHandler(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (!getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE)) {
            if (!getQuestionnaireAuthorizationService().hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Edit/View", "Questionnaire");
            }
            else {
                if (!qnForm.isReadOnly()) {
                    // if user only has VIEW_QUESTIONNAIRE permission
                    qnForm.setReadOnly(true);

                }
            }
        }

    }

    /*
     * loop thru qnquestion and assemble results. Also find the max questionnumber.
     */
    private String getQuestionReturnResult(QuestionnaireMaintenanceForm questionnaireForm, Questionnaire questionnaire) {
        Collections.sort(questionnaire.getQuestionnaireQuestions(), new QuestionnaireQuestionComparator());
        String result = "parent-0";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (!question.getParentQuestionNumber().equals(0)) {
                remainQuestions.add((QuestionnaireQuestion) ObjectUtils.deepCopy(question));
            }
        }
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getQuestionNumber() > questionnaireForm.getQuestionNumber()) {
                questionnaireForm.setQuestionNumber(question.getQuestionNumber());
            }
            if (question.getParentQuestionNumber().equals(0)) {
                result = result + PQP + getQnReturnfields(question);
                String childrenResult = getChildrenQuestions(question, remainQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }

            }
        }
        questionnaireForm.setQuestionNumber(questionnaireForm.getQuestionNumber() + 1);
        return result;

    }

    /*
     * get all the questions data needed for JS to manipulate.
     */
    private String assembleQuestions(QuestionnaireMaintenanceForm questionnaireForm) {

        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) questionnaireForm.getDocument())
                .getNewMaintainableObject().getDataObject();
        questionnaireForm.setQuestionNumber(0);
        return getQuestionReturnResult(questionnaireForm, questionnaire);


    }

    /*
     * get the question properties related to response
     */
    private String getQeustionResponse(Question question) {
        // for 'lookup', there is no maxlength set up.  so, use this field for lookup return type
        String retString = "";
        if (question.getQuestionTypeId().equals(new Integer(6))) {
            String className = question.getLookupClass();
            if(className!=null){
                className = className.substring(className.lastIndexOf(".") + 1);
                retString = className + PFP + question.getMaxAnswers() + PFP + getLookupReturnType(question.getLookupClass(), question.getLookupReturn()) ;
            }
        }
        else {
            retString = question.getDisplayedAnswers() + PFP + question.getMaxAnswers() + PFP + question.getAnswerMaxLength();
        }
        return retString;
    }

    /*
     * get the lookup return field type if possible
     * This will be passed in the 'maxlength' field, and js will use this to validate branching condition
     */
    private String getLookupReturnType(String className, String lookReturn) {
        String retVal = "0";
        String lookupReturnType = "";
        try {
            lookupReturnType = Class.forName(className).getDeclaredField(lookReturn).getType().getSimpleName();
        } catch (Exception e) {
            
        }
        if ("String".equals(lookupReturnType)) {
            retVal= "5";
        } else if ("Date".equals(lookupReturnType)) {
            retVal= "4";
        } else if ("Integer".equals(lookupReturnType) || "Long".equals(lookupReturnType)) {
            retVal= "3";
        }
        return retVal;
    }
    
    /*
     * get the children questions data
     */
    private String getChildrenQuestions(QuestionnaireQuestion questionnaireQuestion, List<QuestionnaireQuestion> questionnaireQuestions) {
        String result = "";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaireQuestions) {
            if (question.getParentQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())) {
                result = result + PQP + getQnReturnfields(question);
                String childrenResult = getChildrenQuestions(question, questionnaireQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }
            }
        }
        return result;
    }

    /*
     * this method is to get the property from questionnairequestion and question and concatenate them with "#f#" as separator. This
     * will be parsed by js to construct question node
     */
    private String getQnReturnfields(QuestionnaireQuestion question) {

        if (question.getQuestion() == null || !question.getQuestionId().equals(question.getQuestion().getId())) {
            question.refreshReferenceObject(QUESTION);
        }
        String desc = question.getQuestion().getQuestion();
        if (desc.indexOf("\"") > 0) {
            desc = desc.replace("\"", "&#034;");
        }
        return question.getId() + PFP + question.getQuestionId() + PFP + question.getQuestionSeqNumber()
                + PFP + desc + PFP + question.getQuestion().getQuestionTypeId() + PFP + question.getQuestionNumber() + PFP
                + question.getCondition() + PFP + question.getConditionValue() + PFP + question.getParentQuestionNumber() + PFP
                + question.getQuestion().getSequenceNumber() + PFP + getQeustionResponse(question.getQuestion()) + PFP
                + question.getVersionNumber() + PFP + (question.getConditionFlag() ? "Y" : "N") + PFP + getVersionedQuestion(question)+
                PFP+question.getRuleId();

    }

    @SuppressWarnings("unchecked")
    private String getVersionedQuestion(QuestionnaireQuestion qnQuestion) {
        
        String results = N;
        Map<String, Integer> fieldValues = new HashMap<>();
        fieldValues.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, qnQuestion.getQuestion().getQuestionSeqId());
        Question question = ((List<Question>)getBusinessObjectService().findMatchingOrderBy(Question.class, fieldValues, "sequenceNumber", false)).get(0);
        if (!question.getSequenceNumber().equals(qnQuestion.getQuestion().getSequenceNumber())) {
            results = question.getId().toString();
        } 
        return results;

    }
    
    /*
     * Create a concatenated usage properties string.
     */
    private String assembleUsages(Questionnaire questionnaire) {
        String result = "";
        for (QuestionnaireUsage questionnaireUsage : questionnaire.getQuestionnaireUsages()) {
            // get the module description
            String moduleDescription = questionnaireUsage.getCoeusModule() != null ? questionnaireUsage.getCoeusModule().getDescription() : "";
            // get the sub module description
            CoeusSubModule subModule = getSubModule(questionnaireUsage.getModuleItemCode(), questionnaireUsage.getModuleSubItemCode());
            String subModuleDescription = subModule != null ? subModule.getDescription() : "";
            
            result = result + questionnaireUsage.getId() + PFP + questionnaireUsage.getModuleItemCode() + PFP
                    + questionnaireUsage.getQuestionnaireLabel() + PFP + questionnaireUsage.getQuestionnaireSequenceNumber() + PFP
                    + questionnaireUsage.getModuleSubItemCode() + PFP + questionnaireUsage.getRuleId() + PFP
                    + questionnaireUsage.getVersionNumber()  + PFP + (questionnaireUsage.isMandatory() ? "Y" : "N") + PFP 
                    + moduleDescription + PFP + subModuleDescription + PUP;
        }
        if (StringUtils.isNotBlank(result)) {
            result = result.substring(0, result.length() - 3);
        }
        return result;

    }

    private CoeusSubModule getSubModule(String moduleCode, String subModuleCode) {
        CoeusSubModule retVal = null;
        
        List<CoeusSubModule> subModules = new ArrayList<CoeusSubModule>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_CODE, moduleCode);
        fieldValues.put(SUB_MODULE_CODE, subModuleCode);
        subModules.addAll(getBusinessObjectService().findMatching(CoeusSubModule.class, fieldValues));
        
        if(!subModules.isEmpty()) {
            retVal = subModules.get(0);           
        }
        return retVal;
    }


    @Override
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.edit(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (qnForm.isReadOnly()) {
            qnForm.getDocument().getDocumentHeader().setDocumentDescription("questionnaire - bootstrap data");
         } 
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        Questionnaire oldQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getOldMaintainableObject().getDataObject();
        versionQuestionnaire(questionnaire, oldQuestionnaire);
        Long questionnaireRefId = KcServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber(
                SEQ_QUESTIONNAIRE_REF_ID, questionnaire.getClass());
        questionnaire.setQuestionnaireRefIdFromLong(questionnaireRefId);
        oldQuestionnaire.setQuestionnaireRefIdFromLong(questionnaireRefId);
        String questions = assembleQuestions(qnForm);
        String usages = assembleUsages(((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject()));
        qnForm.setEditData(questions + PCP + usages);
        
        checkAndSetAllQuestionsAreUpToDate(qnForm);

        return forward;
    }

    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KRADConstants.MAINTENANCE_COPY_ACTION)) {
            preRouteCopy(form);
        }

        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
        .setDocumentNumber(((MaintenanceDocumentBase) qnForm.getDocument()).getDocumentNumber());
//        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
//        .setIsFinal(true);
        setupQuestionAndUsage(form);
        qnForm.setNewQuestionnaireUsage(new QuestionnaireUsage());
        if (qnForm.getDocument() != null && ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject() != null) {
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getDataObject();
            removeDeletedUsages(newQuestionnaire);
        }
        ActionForward forward = super.route(mapping, form, request, response);
        
        checkAndSetAllQuestionsAreUpToDate(qnForm);
        return forward;

    }


    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getMaintenanceAction().equals(
                KRADConstants.MAINTENANCE_COPY_ACTION)) {
            preRouteCopy(form);
        }
        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
        .setDocumentNumber(((MaintenanceDocumentBase) qnForm.getDocument()).getDocumentNumber());
//        ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
//        .setIsFinal(true);
        setupQuestionAndUsage(form);
        if (qnForm.getDocument() != null && ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject() != null) {
            Questionnaire newQuestionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getDataObject();
            removeDeletedUsages(newQuestionnaire);
        }
        ActionForward forward = super.blanketApprove(mapping, form, request, response);
        
        checkAndSetAllQuestionsAreUpToDate(qnForm);
        return forward;
    }

    /*
     * For 'copy' action : create the copied questionnaire
     */
    private void preRouteCopy(ActionForm form) {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Map fieldValues = new HashMap<String, Object>();
        fieldValues.put(QuestionnaireConstants.QUESTIONNAIRE_ID_PARAMETER_NAME, ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getOldMaintainableObject().getDataObject()).getId());
        Questionnaire oldQuestionnaire = (Questionnaire) getBusinessObjectService().findByPrimaryKey(Questionnaire.class,
                fieldValues);
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        KcServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(oldQuestionnaire, questionnaire);
        questionnaire.setSequenceNumber(1); // just in case
    }

    /*
     * Create new version of questionnaire
     */
    private void versionQuestionnaire(Questionnaire questionnaire, Questionnaire oldQuestionnaire) {
        try {
            Questionnaire newQuestionnaire = getVersioningService().createNewVersion(oldQuestionnaire);
            questionnaire.setId(null);
            questionnaire.setSequenceNumber(newQuestionnaire.getSequenceNumber());
            for (QuestionnaireQuestion qnaireQuestion : questionnaire.getQuestionnaireQuestions()) {
                qnaireQuestion.setQuestionnaireId(null);
                qnaireQuestion.setId(null);
            }
            for (QuestionnaireUsage qnaireUsage : questionnaire.getQuestionnaireUsages()) {
                qnaireUsage.setId(null);
                qnaireUsage.setQuestionnaireId(null);
            }
            questionnaire.setDocumentNumber("");
        }
        catch (Exception e) {
            throw new RuntimeException("Error creating new questionnaire version",e);
        }

    }

    @Override
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.start(mapping, form, request, response);
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (qnForm.getMaintenanceAction().equals(KRADConstants.MAINTENANCE_NEW_ACTION)
                && ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
                        .getSequenceNumber() == null) {
            ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject().getDataObject())
                    .setSequenceNumber(1);
        }
        
        checkAndSetAllQuestionsAreUpToDate(qnForm);
        return forward;
    }

    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = null;
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        if (buttonClicked != null && ConfirmationQuestion.YES.equals(buttonClicked)) {
            Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getDataObject());
            questionnaire.setQuestionnaireUsages(qnForm.getQuestionnaireUsages());
            removeDeletedUsages(questionnaire);
            setupQuestionAndUsage(qnForm);
            if (qnForm.getTemplateFile() != null && StringUtils.isNotBlank(qnForm.getTemplateFile().getFileName())) {
                questionnaire.setFileName(qnForm.getTemplateFile().getFileName());
                questionnaire.setTemplate(qnForm.getTemplateFile().getFileData());
            }
        }
        forward = super.close(mapping, form, request, response);
        if (buttonClicked == null || ConfirmationQuestion.NO.equals(buttonClicked)) {
            Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                    .getNewMaintainableObject().getDataObject();
            qnForm.setQuestionnaireUsages(questionnaire.getQuestionnaireUsages());
            // also need to save usages data in form to be restored if 'yes' is clicked
            // this is processed twice, so questionnaireUsage will be reset in qnform.reset
            // that's why we need to save this for restoration later
            questionnaire.setQuestionnaireUsages(new ArrayList<QuestionnaireUsage>());
           
        }
        return forward;
    }
    
    @Override
    protected boolean canSave(ActionForm form) {
    	QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
    	return !qnForm.isReadOnly() && super.canSave(qnForm);
    }


    /**
     * 
     * This method is to print Questionnaire
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : this is only available after questionnaire is saved ?
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        reportParameters.put(DOCUMENT_NUMBER, qnForm.getDocument().getDocumentNumber());
        Questionnaire questionnaire = ((Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument())
                .getNewMaintainableObject().getDataObject());
        reportParameters.put(QUESTIONNAIRE, questionnaire);
        if (qnForm.getTemplateFile() != null && qnForm.getTemplateFile().getFileData().length > 0) {
            reportParameters.put(TEMPLATE, qnForm.getTemplateFile().getFileData());
            
        } else {
           reportParameters.put(TEMPLATE, questionnaire.getTemplate());
        }
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, questionnaire.getId());
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaire(null, reportParameters);
        if (dataStream.getData() != null) {
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }

    public ActionForward addTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        questionnaire.setFileName(qnForm.getTemplateFile().getFileName());
        questionnaire.setTemplate(qnForm.getTemplateFile().getFileData());
            
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

    public ActionForward viewTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
        .getBusinessObject();

        if (qnForm.getTemplateFile() != null && StringUtils.isNotBlank(qnForm.getTemplateFile().getFileName())) {
            this.streamToResponse(qnForm.getTemplateFile().getFileData(), qnForm.getTemplateFile().getFileName(),
                Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1, response);
        } else {
            this.streamToResponse(questionnaire.getTemplate(), questionnaire.getFileName(),
                    Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1, response);
        }
        return RESPONSE_ALREADY_HANDLED;
    }

    public ActionForward deleteTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        Questionnaire questionnaire = (Questionnaire) ((MaintenanceDocumentBase) qnForm.getDocument()).getNewMaintainableObject()
                .getBusinessObject();
        questionnaire.setFileName(null);
        questionnaire.setTemplate(null);
        qnForm.setTemplateFile(null);

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward getSubModuleCodeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward("ajaxQuestionnaire");
    }
    
    public ActionForward getQuestionMaintainTable(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (StringUtils.isNotBlank(qnForm.getQuestionId())) {
            Map pkMap = new HashMap();
            pkMap.put(ID, qnForm.getQuestionId());
            qnForm.setQuestion((Question)getBusinessObjectService().findByPrimaryKey(Question.class, pkMap));
            
            //lets check for a more current version
            pkMap.clear();
            pkMap.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, qnForm.getQuestion().getQuestionSeqId());
            List<Question> questions = ((List<Question>)getBusinessObjectService().findMatchingOrderBy(Question.class, pkMap, "sequenceNumber", false));
            if (CollectionUtils.isNotEmpty(questions)) {
                if (!StringUtils.equals(questions.get(0).getId().toString(), qnForm.getQuestionId())) {
                    qnForm.setQuestionCurrentVersion(false);
                }
            }
        }
        return mapping.findForward("ajaxQuestionMaintainTable");
    }
    
    public ActionForward getQuestionCurrentVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) form;
        if (StringUtils.isNotBlank(qnForm.getQuestionId())) {
            Map pkMap = new HashMap();
            pkMap.put(ID, qnForm.getQuestionId());
            Question oldQ = (Question)getBusinessObjectService().findByPrimaryKey(Question.class, pkMap);
            if (oldQ != null) {
                pkMap.clear();
                pkMap.put(QuestionnaireConstants.QUESTION_SEQEQUENCE_ID, oldQ.getQuestionSeqId());
                List<Question> questions = ((List<Question>)getBusinessObjectService().findMatchingOrderBy(Question.class, pkMap, "sequenceNumber", false));
                if (CollectionUtils.isNotEmpty(questions)) {
                    qnForm.setQuestion(questions.get(0));
                }
            }
        }
        return mapping.findForward("ajaxQuestionCurrentVersion");
    }
    
    private QuestionnaireAuthorizationService getQuestionnaireAuthorizationService() {
        return KcServiceLocator.getService(QuestionnaireAuthorizationService.class);
    }
    
    private QuestionnairePrintingService getQuestionnairePrintingService() {
        return KcServiceLocator.getService(QuestionnairePrintingService.class);
    }

    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase){
        QuestionnaireMaintenanceForm qnForm = (QuestionnaireMaintenanceForm) formBase;
        // for 'view' questionnaire.  which is using 'edit. 
        boolean isReadOnly = qnForm.isReadOnly();

        // populateAuthorizationFields will override the isReadOnly property of the form. if it is 'view'
        super.populateAuthorizationFields(formBase);
        if (isReadOnly && StringUtils.equals(qnForm.getMethodToCall(), "edit")) {
            qnForm.setReadOnly(isReadOnly);
        }
        
        if (qnForm.isReadOnly() && formBase.getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_CLOSE)) {
            Map<String, String> documentActions = new HashMap<String, String>();
            documentActions.put(KRADConstants.KUALI_ACTION_CAN_CLOSE, "TRUE");
            qnForm.setDocumentActions(documentActions);
            
        }
    }
    
    protected void removeDeletedUsages(Questionnaire questionnaire) {
        if (questionnaire != null && questionnaire.getQuestionnaireUsages() != null) {
            Iterator<QuestionnaireUsage> iter = questionnaire.getQuestionnaireUsages().iterator();
            while (iter.hasNext()) {
                QuestionnaireUsage usage = iter.next();
                if (usage.isDelete()) {
                    iter.remove();
                }
            }
        }
    }

}
