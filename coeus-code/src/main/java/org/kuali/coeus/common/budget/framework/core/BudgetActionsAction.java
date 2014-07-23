/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except inputStream compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to inputStream writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardsRule;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper.ValidationState;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetForm;
import org.kuali.kra.award.budget.AwardBudgetLimit;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetJustificationServiceImpl;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.budget.external.budget.BudgetAdjustmentClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class BudgetActionsAction extends BudgetAction implements AuditModeAction {
    private static final String CONTENT_TYPE_XML = "text/xml";
    private static final String XML_FILE_EXTENSION = "xml";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String SUBAWARD_BUDGET_EDIT_LINE_STARTER = "document.budget.budgetSubAwards[";
    private static final String SUBAWARD_BUDGET_EDIT_LINE_ENDER = "]";
    private static final String UPDATE_COST_LIMITS_QUESTION = "UpdateCostLimitsQuestion";
    private BudgetJustificationService budgetJustificationService;
    private PropDevBudgetSubAwardService propDevBudgetSubAwardService;
    private static final Log LOG = LogFactory.getLog(BudgetActionsAction.class);
    private BudgetAdjustmentClient budgetAdjustmentClient = null;

    


    public BudgetActionsAction() {
        super();
        setBudgetJustificationService(new BudgetJustificationServiceImpl());
    }
    
    /**
     * This method consolidates Budget Line Item Justification text into a single Justification field, with last user/time update meta data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward consolidateExpenseJustifications(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        try {
            budgetJustificationService.consolidateExpenseJustifications(budget, getBudgetJusticationWrapper(form));
        } catch (BudgetException exc) {
            GlobalVariables.getMessageMap().putError("budgetJustificationWrapper.justificationText", "error.custom", "There are no line item budget justifications");            
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Override to set the update time and user, then convert to String 
     * @see BudgetAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        budgetJustificationService.preSave(budget, getBudgetJusticationWrapper(form));
        return super.save(mapping, form, request, response);
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        for (BudgetSubAwards subAward : budgetForm.getBudgetDocument().getBudget().getBudgetSubAwards()) {
            subAward.computePeriodDetails();
        }
        return super.execute(mapping, budgetForm, request, response);
    }
    
    /**
     * Close the document and take the user back to the index; only after asking the user if they want to save the document first.
     * Only users who have the "canSave()" permission are given this option.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase docForm = (KualiDocumentFormBase) form;
        // only want to prompt them to save if they already can save
        if (docForm.getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_SAVE)) {
            Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
            ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();

            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response, KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, kualiConfiguration.getPropertyValueAsString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KRADConstants.CONFIRMATION_QUESTION, KRADConstants.MAPPING_CLOSE, "");
            }
            else {
                BudgetForm budgetForm = (BudgetForm)form;
                Budget budget = budgetForm.getBudgetDocument().getBudget();

                Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                if ((KRADConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    // if yes button clicked - save the doc
                    budgetJustificationService.preSave(budget, getBudgetJusticationWrapper(form));
                }
                // else go to close logic below
            }
        }

        return super.close(mapping, form, request, response);
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        getPropDevBudgetSubAwardService().populateBudgetSubAwardAttachments(budget);
        return forward;
    }

    public ActionForward addSubAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards newBudgetSubAward = budgetForm.getNewSubAward();
        newBudgetSubAward.setBudgetId(budgetDocument.getBudget().getBudgetId());
        newBudgetSubAward.setSubAwardNumber(generateSubAwardNumber(budgetDocument));
        newBudgetSubAward.setBudgetVersionNumber(budgetDocument.getBudget().getBudgetVersionNumber());
        newBudgetSubAward.setSubAwardStatusCode(1);
        newBudgetSubAward.getBudgetSubAwardPeriodDetails().clear();
        for (BudgetPeriod period : budgetDocument.getBudget().getBudgetPeriods()) {
            newBudgetSubAward.getBudgetSubAwardPeriodDetails().add(new BudgetSubAwardPeriodDetail(newBudgetSubAward, period));
        }
        boolean success = true;
        if (newBudgetSubAward.getNewSubAwardFile() != null) {
            String fileName = newBudgetSubAward.getNewSubAwardFile().getFileName();
            byte[] fileData = newBudgetSubAward.getNewSubAwardFile().getFileData(); 
            success = updateBudgetAttachment(budgetDocument.getBudget(), newBudgetSubAward, fileName, fileData, "newSubAward");
        }
        String contentType = newBudgetSubAward.getNewSubAwardFile().getContentType();
        if (success && contentType.equalsIgnoreCase(Constants.PDF_REPORT_CONTENT_TYPE)) {
            budgetForm.setNewSubAward(new BudgetSubAwards());
            budgetDocument.getBudget().getBudgetSubAwards().add(newBudgetSubAward);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        subAward.refreshNonUpdateableReferences();
        if(!subAward.getBudgetSubAwardFiles().isEmpty()){
            BudgetSubAwardFiles subAwardFiles = subAward.getBudgetSubAwardFiles().get(0);
            downloadFile(form, request, response, subAwardFiles.getSubAwardXfdFileData(), subAward.getSubAwardXfdFileName(), CONTENT_TYPE_PDF);
        }else{
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        return null;
    }
    
    public ActionForward viewXML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        subAward.refreshNonUpdateableReferences();
        if(!subAward.getBudgetSubAwardFiles().isEmpty()){
            BudgetSubAwardFiles subAwardFiles = subAward.getBudgetSubAwardFiles().get(0);
            downloadFile(form, request, response, subAwardFiles.getSubAwardXmlFileData().getBytes(), createXMLFileName(subAward), CONTENT_TYPE_XML);
        }
        return null;
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int selectedLineNumber = getSelectedLine(request);
        BudgetSubAwards subAward = budgetDocument.getBudget().getBudgetSubAwards().get(selectedLineNumber);
        for (BudgetPeriod period : budgetDocument.getBudget().getBudgetPeriods()) {
            Iterator<BudgetLineItem> iter = period.getBudgetLineItems().iterator();
            while (iter.hasNext()) {
                BudgetLineItem item = iter.next();
                if (org.apache.commons.lang3.ObjectUtils.equals(subAward.getSubAwardNumber(), item.getSubAwardNumber())) {
                    iter.remove();
                }
            }
        }
        budgetDocument.getBudget().getBudgetSubAwards().remove(selectedLineNumber);
        Collections.sort(budgetDocument.getBudget().getBudgetSubAwards());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteSubAwardAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int selectedLineNumber = getSelectedLine(request);
        BudgetSubAwards subAward = budgetDocument.getBudget().getBudgetSubAwards().get(selectedLineNumber);
        getPropDevBudgetSubAwardService().removeSubAwardAttachment(subAward);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward updateBudgetAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        FormFile subAwardFile = subAward.getNewSubAwardFile();
        byte[] subAwardData = subAwardFile.getFileData();
        String subAwardFileName = subAwardFile.getFileName();
        updateBudgetAttachment(budgetDocument.getBudget(), subAward, subAwardFileName, subAwardData, 
                SUBAWARD_BUDGET_EDIT_LINE_STARTER + getSelectedLine(request) + SUBAWARD_BUDGET_EDIT_LINE_ENDER);
        Collections.sort(budgetDocument.getBudget().getBudgetSubAwards());
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward syncFromBudgetAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        String errorPath = SUBAWARD_BUDGET_EDIT_LINE_STARTER + getSelectedLine(request) + SUBAWARD_BUDGET_EDIT_LINE_ENDER;
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        updateSubAwardBudgetDetails(budgetDocument.getBudget(), subAward);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    protected boolean updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards subAward) throws Exception {
        List<String[]> errorMessages = new ArrayList<String[]>();
        BudgetSubAwardsRule rule = new BudgetSubAwardsRule(subAward);
        boolean success = true;
        if (subAward.getNewSubAwardFile().getFileData().length == 0) {
            success = false;
            }
        if (rule.processXFDAttachment()) {
            success = getPropDevBudgetSubAwardService().updateSubAwardBudgetDetails(budget, subAward, errorMessages);
        }
        if (!errorMessages.isEmpty()) {
            for (String[] message : errorMessages) {
                String[] messageParameters = null;
                if (message.length > 1) {
                    messageParameters = Arrays.copyOfRange(message, 1, message.length);
                }
                if (success) {
                    GlobalVariables.getMessageMap().putWarning(Constants.SUBAWARD_FILE_FIELD_NAME, message[0], messageParameters);
                } else {
                    GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, message[0], messageParameters);
                }
            }
        }
        if (success && errorMessages.isEmpty() && rule.processNonXFDAttachment()) {
            GlobalVariables.getMessageMap().putInfo(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_DETAILS_UPDATED);
        }
        return success;
    }
    
    protected boolean updateBudgetAttachment(Budget budget, BudgetSubAwards subAward, String fileName, byte[] fileData, String errorPath) throws Exception {

        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        subAward.setSubAwardXmlFileData(null);
        subAward.setFormName(null);
        subAward.setNamespace(null);
        if (subAward.getNewSubAwardFile().getContentType().equalsIgnoreCase(Constants.PDF_REPORT_CONTENT_TYPE)) {
            getPropDevBudgetSubAwardService().populateBudgetSubAwardFiles(budget, subAward, fileName, fileData);
        }
        boolean success = updateSubAwardBudgetDetails(budget, subAward);
        BudgetSubAwardsRule rule = new BudgetSubAwardsRule(subAward);
        success &= rule.processXFDAttachment();
        if (success) {
            if (rule.checkSpecialCharacters(subAward.getSubAwardXmlFileData().toString())) {
                subAward.getBudgetSubAwardFiles().get(0).setSubAwardXmlFileData(KcServiceLocator.getService(KcAttachmentService.class).
                        checkAndReplaceSpecialCharacters(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData().toString()));
                subAward.setSubAwardXmlFileData(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData());
            }
        }
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        if(rule.processNonXFDAttachment()){
             success = rule.processNonXFDAttachment();
        }
        return success;
    }

    public ActionForward printBudgetForm(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        Integer selectedLine = getSelectedLine(request);
        String budgetFormToPrint = budget.getBudgetPrintForms().get(selectedLine).getBudgetReportId();
        if(budgetForm.getSelectedToPrintComment()!=null && budgetFormToPrint !=null){
            String forms[]=budgetForm.getSelectedToPrintComment();
            if(forms[0].equals(budgetFormToPrint)){
                budget.setPrintBudgetCommentFlag("true");
            }
        }
        budgetForm.setSelectedToPrintComment(null);
        BudgetPrintService budgetPrintService = KcServiceLocator
                .getService(BudgetPrintService.class);
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        if (budgetFormToPrint != null) {
                AttachmentDataSource dataStream = budgetPrintService.readBudgetPrintStream(budget,budgetFormToPrint);
                if(dataStream.getData()!=null){
                    streamToResponse(dataStream, response);
                    forward = null;
                }
        }
        return forward;
    }
    
    
    public void setBudgetJustificationService(BudgetJustificationService budgetJustificationService) {
        this.budgetJustificationService = budgetJustificationService;
    }

    private String createXMLFileName(BudgetSubAwards subAward) {
        return subAward.getSubAwardXfdFileName().substring(0, subAward.getSubAwardXfdFileName().lastIndexOf(".") + 1) + XML_FILE_EXTENSION;
    }
    
    private Integer generateSubAwardNumber(BudgetDocument budgetDocument) {
        return budgetDocument.getHackedDocumentNextValue("subAwardNumber") != null ? budgetDocument.getHackedDocumentNextValue("subAwardNumber") : 1;
    }

    private BudgetJustificationWrapper getBudgetJusticationWrapper(ActionForm form) {
        return ((BudgetForm) form).getBudgetJustification();
    }
    
    private BudgetSubAwards getSelectedBudgetSubAward(ActionForm form, HttpServletRequest request) {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        int selectedLineNumber = getSelectedLine(request);
        return budget.getBudgetSubAwards().get(selectedLineNumber);
    }
    
    private void downloadFile(ActionForm form, HttpServletRequest request, HttpServletResponse response, byte[] bytesToDownload, String fileName, String contentType) throws Exception {
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(bytesToDownload.length);
            baos.write(bytesToDownload);
            WebUtils.saveMimeOutputStreamAsFile(response, contentType, baos, fileName);
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }
    
    /**
     * route the document using the document service
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm) form).getAwardBudgetDocument();
        Award currentAward = getAwardBudgetService().getActiveOrNewestAward(((AwardDocument) awardBudgetDocument.getBudget().getBudgetParent().getDocument()).getAward().getAwardNumber());
        ScaleTwoDecimal newCostLimit = getAwardBudgetService().getTotalCostLimit(currentAward.getAwardDocument());
        if (!newCostLimit.equals(awardBudgetDocument.getBudget().getTotalCostLimit())
                || !limitsMatch(currentAward.getAwardBudgetLimits(), awardBudgetDocument.getAwardBudget().getAwardBudgetLimits())) {
            Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            String methodToCall = ((KualiForm) form).getMethodToCall();
            if(question == null){
                ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();                                  
                return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, UPDATE_COST_LIMITS_QUESTION,
                        KeyConstants.QUESTION_TOTALCOSTLIMIT_CHANGED), 
                        methodToCall, methodToCall);
            } else if(UPDATE_COST_LIMITS_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                getAwardBudgetService().setBudgetLimits(awardBudgetDocument, currentAward.getAwardDocument());
                return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            } else {
                //do nothing and continue with route
            }    
        }
        ((AwardBudgetForm) form).setAuditActivated(true);
        ValidationState state = KcServiceLocator.getService(AuditHelper.class).isValidSubmission((AwardBudgetForm) form, true);
        if (state != ValidationState.ERROR) {
            getAwardBudgetService().processSubmision(awardBudgetDocument);
            return super.route(mapping, form, request, response);
        }
        else {
            GlobalVariables.getMessageMap().clearErrorMessages(); 
            GlobalVariables.getMessageMap().putError("datavalidation",KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});

            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
    }
    
    /**
     * 
     * Compares the budget limit lists to make sure they match.
     * @param awardLimits
     * @param budgetLimits
     * @return
     */
    protected boolean limitsMatch(List<AwardBudgetLimit> awardLimits, List<AwardBudgetLimit> budgetLimits) {
        if (awardLimits.size() < budgetLimits.size()) {
            return false;
        }
        
        for (AwardBudgetLimit limit : awardLimits) {
            AwardBudgetLimit budgetLimit = getBudgetLimit(limit.getLimitType(), budgetLimits);
            if (!org.apache.commons.lang3.ObjectUtils.equals(limit.getLimit(), budgetLimit.getLimit())) {
                return false;
            }
        }
        return true;
    }    
    
    /**
     * 
     * Get the specific budget limit from the budget list
     * @param type
     * @param budgetLimits
     * @return
     */
    protected AwardBudgetLimit getBudgetLimit(AwardBudgetLimit.LIMIT_TYPE type, List<AwardBudgetLimit> budgetLimits) {
        for (AwardBudgetLimit limit : budgetLimits) {
            if (limit.getLimitType() == type) {
                return limit;
            }
        }
        //return empty budget limit to simplify logic above
        return new AwardBudgetLimit(type);
    }    

    private AwardBudgetService getAwardBudgetService() {
        return KcServiceLocator.getService(AwardBudgetService.class);
    }

    /**
     * route the document using the document service
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm)form).getAwardBudgetDocument();
        boolean success = isValidForSubmission(awardBudgetDocument);
        ActionForward actionForward = super.approve(mapping, form, request, response);
        getAwardBudgetService().processApproval(awardBudgetDocument);   
        return actionForward;
    }

    /**
     * route the document using the document service
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm)form).getAwardBudgetDocument();
        boolean auditPassed = KcServiceLocator.getService(AuditHelper.class).auditUnconditionally(awardBudgetDocument);
        getAwardBudgetService().processDisapproval(awardBudgetDocument);   
        return super.disapprove(mapping, form, request, response);
    }
    
    /**
     * Cancel that calls superUserCancel if the document is in route and the current user is the routed by user of the document.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm)form).getAwardBudgetDocument();
        WorkflowDocument workflowDoc = awardBudgetDocument.getDocumentHeader().getWorkflowDocument();
        if (workflowDoc.isEnroute()
                && StringUtils.equals(GlobalVariables.getUserSession().getPrincipalId(), workflowDoc.getRoutedByPrincipalId())) {
            workflowDoc.superUserCancel("Cancelled by Routed By User");
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            return super.cancel(mapping, form, request, response);
        }
    }
    
    private boolean isValidForSubmission(AwardBudgetDocument awardBudgetDocument) {
       return false;
    }

    /**
     * If the financial system integration param is ON, then this method calls 
     * the budgetAdjustmentClient to create a Budget adjustment document on the financial system.
     * route the document using the document service
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward postAwardBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm) form).getAwardBudgetDocument();
   
        if (isFinancialIntegrationOn(awardBudgetDocument)) {
            if (isValidForPostingToFinancialSystem(awardBudgetDocument)) {
                BudgetAdjustmentClient client = getBudgetAdjustmentClient();
                client.createBudgetAdjustmentDocument(awardBudgetDocument);
                if (!isValidForPostingToFinancialSystem(awardBudgetDocument)) {
                    getAwardBudgetService().post(awardBudgetDocument);
                    String docNumber = awardBudgetDocument.getBudget().getBudgetAdjustmentDocumentNumber();
                    KNSGlobalVariables.getMessageList().add(KeyConstants.BUDGET_POSTED, docNumber);
                }
            } else {
                String budgetAdjustmentDocNbr = awardBudgetDocument.getBudget().getBudgetAdjustmentDocumentNumber();
                GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.BUDGET_ADJUSTMENT_DOC_EXISTS, budgetAdjustmentDocNbr);
                LOG.info("Cannot post budget. There is already a budget adjustment document linked to this budget.");
            }
            return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);           
        } else {
            getAwardBudgetService().post(awardBudgetDocument);   
        }
        setupDocumentExit();
        return returnToSender(request, mapping, (AwardBudgetForm) form);
    }
  
    protected BudgetAdjustmentClient getBudgetAdjustmentClient() {
        if (budgetAdjustmentClient == null) {
            budgetAdjustmentClient = KcServiceLocator.getService("budgetAdjustmentClient");
        }
        return budgetAdjustmentClient;
    }

    /**
     * This method checks if the budget adjustment document has alredy been created and if the integration parameters is on.
     * @param awardBudgetDocument
     * @return
     */
    protected boolean isValidForPostingToFinancialSystem(AwardBudgetDocument awardBudgetDocument) {
        //check if budget adjustment doc nbr has been created here, if so do not post
        String budgetAdjustmentDocumentNumber = awardBudgetDocument.getBudget().getBudgetAdjustmentDocumentNumber();
        // Need to check empty string also because of KCINFR-363. MySQL treats '' and null different and awardBudget documents 
        // initially seem to store the BA doc nbr as ''.
        if (ObjectUtils.isNull(budgetAdjustmentDocumentNumber) || StringUtils.isEmpty(budgetAdjustmentDocumentNumber)) {
            return true;
        }
        
        return false;
    }
    
    protected boolean isFinancialIntegrationOn(AwardBudgetDocument awardBudgetDocument) {
        String parameterValue = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD, 
                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        if (StringUtils.containsIgnoreCase(parameterValue, Constants.FIN_SYSTEM_INTEGRATION_ON)) {
            return true;
        }
        return false;
    }
    
    public ActionForward toggleAwardBudgetStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardBudgetDocument awardBudgetDocument = ((AwardBudgetForm)form).getAwardBudgetDocument();
        getAwardBudgetService().toggleStatus(awardBudgetDocument);   
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        if (form instanceof AwardBudgetForm) {
            forward = KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (AwardBudgetForm) form, true);
        } else {
            forward = KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (BudgetForm) form, true);
        }
        return forward;
    }

    @Override
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        if (form instanceof AwardBudgetForm) {
            forward = KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (AwardBudgetForm) form, false);
        } else {
            forward = KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (BudgetForm) form, false);
        }
        return forward;
    }

    /**
     * audit link method
     */
    public ActionForward budgetDistributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return super.distributionAndIncome(mapping, form, request, response);
    }

    /**
     * audit link method
     */
    public ActionForward summary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return super.parameters(mapping, form, request, response);
    }

    /**
     * audit link method
     */
    public ActionForward budgetExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return super.expenses(mapping, form, request, response);
    }
    
    /**
     * audit link method
     */
    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return super.personnel(mapping, form, request, response);
    }

    /**
     * audit link method
     */
    public ActionForward parameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return super.parameters(mapping, form, request, response);
    }
    
    /**
     * audit link method
     */
    public ActionForward budgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return super.rates(mapping, form, request, response);
    }

    public ActionForward syncBudgetToParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        DevelopmentProposal childProposal = (DevelopmentProposal) budgetForm.getBudgetDocument().getBudget().getBudgetParent();
        getHierarchyHelper().syncBudgetToParent((ProposalDevelopmentBudgetExt) budgetForm.getBudgetDocument().getBudget(), childProposal, false);
        if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
            return doEndDateConfirmation(mapping, form, request, response, "syncBudgetToParent", "syncToHierarchyParentConfirm");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    private ActionForward doEndDateConfirmation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String questionId, String yesMethodName) throws Exception {
        List<ErrorMessage> errors = GlobalVariables.getMessageMap().getErrorMessagesForProperty(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER);
        List<String> proposalNumbers = new ArrayList<String>();
        for (ErrorMessage error : errors) {
            if (error.getErrorKey().equals(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
                proposalNumbers.add(error.getMessageParameters()[0]);
            }
        }
        String proposalNumberList = StringUtils.join(proposalNumbers, ',');
        StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response, questionId, ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM, proposalNumberList);
        GlobalVariables.getMessageMap().getErrorMessages().clear();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        GlobalVariables.getMessageMap().getInfoMessages().clear();
        return confirm(question, yesMethodName, "hierarchyActionCanceled");
    }
    
    public ActionForward syncBudgetToParentConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        DevelopmentProposal childProposal = (DevelopmentProposal) budgetForm.getBudgetDocument().getBudget().getBudgetParent();
        getHierarchyHelper().syncBudgetToParent((ProposalDevelopmentBudgetExt) budgetForm.getBudgetDocument().getBudget(), childProposal, true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public PropDevBudgetSubAwardService getPropDevBudgetSubAwardService() {
        if (propDevBudgetSubAwardService == null) {
            propDevBudgetSubAwardService = KcServiceLocator.getService(PropDevBudgetSubAwardService.class);
        }
        return propDevBudgetSubAwardService;
    }

    public void setPropDevBudgetSubAwardService(PropDevBudgetSubAwardService propDevBudgetSubAwardService) {
        this.propDevBudgetSubAwardService = propDevBudgetSubAwardService;
    }  
    
    public ActionForward budgetVersions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        final BudgetForm budgetForm = (BudgetForm) form;
        final String headerTabCall = getHeaderTabDispatch(request);
        if(StringUtils.isEmpty(headerTabCall)) {
            budgetForm.getDocument().refreshPessimisticLocks();
        }  
        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        return mapping.findForward(Constants.BUDGET_VERSIONS_PAGE);
    }
    
}
