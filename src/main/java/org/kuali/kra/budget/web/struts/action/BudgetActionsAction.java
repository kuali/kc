/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.award.budget.AwardBudgetForm;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationService;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationServiceImpl;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.external.budget.BudgetAdjustmentClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetPrintService;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.AuditActionHelper.ValidationState;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

public class BudgetActionsAction extends BudgetAction implements AuditModeAction {
    private static final String CONTENT_TYPE_XML = "text/xml";
    private static final String XML_FILE_EXTENSION = "xml";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String UPDATE_COST_LIMITS_QUESTION = "UpdateCostLimitsQuestion";
    private BudgetJustificationService budgetJustificationService;
    private static final Log LOG = LogFactory.getLog(BudgetActionsAction.class);
    private BudgetAdjustmentClient budgetAdjustmentClient = null;

    

    /**
     * Constructs a BudgetActionsAction, injecting a BudgetJustificationService implementation
     */
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
     * @see org.kuali.kra.budget.web.struts.action.BudgetAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        budgetJustificationService.preSave(budget, getBudgetJusticationWrapper(form));
        return super.save(mapping, form, request, response);
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
            ConfigurationService kualiConfiguration = KRADServiceLocator.getKualiConfigurationService();

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
        KraServiceLocator.getService(BudgetSubAwardService.class).populateBudgetSubAwardAttachments(budget);
        return forward;
    }
    
    /**
     * Adds a non XFD file to the Sub Award for manual (non-Grants.gov) budgets.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward addNonXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards newBudgetSubAward = budgetForm.getNewSubAward();
        FormFile newBudgetSubAwardFile = budgetForm.getSubAwardFile();
        
        boolean success = true;
        if (StringUtils.isBlank(newBudgetSubAward.getOrganizationName())) {
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_ORG_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        }
        if (ArrayUtils.isEmpty(newBudgetSubAwardFile.getFileData())) {
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE, Constants.SUBAWARD_FILE_REQUIERED);
            success = false;
        }
        
        if (success) {
            newBudgetSubAward.setBudgetId(budgetDocument.getBudget().getBudgetId());
            newBudgetSubAward.setSubAwardNumber(generateSubAwardNumber(budgetDocument));
            newBudgetSubAward.setBudgetVersionNumber(budgetDocument.getBudget().getBudgetVersionNumber());
            newBudgetSubAward.setSubAwardStatusCode(1);
            
            BudgetSubAwardFiles newBudgetSubAwardFiles = new BudgetSubAwardFiles();
            newBudgetSubAwardFiles.setSubAwardXfdFileName(newBudgetSubAwardFile.getFileName());
            newBudgetSubAwardFiles.setSubAwardXfdFileData(newBudgetSubAwardFile.getFileData());
            newBudgetSubAward.setSubAwardXfdFileName(newBudgetSubAwardFile.getFileName());
            newBudgetSubAward.setSubAwardXfdFileData(newBudgetSubAwardFile.getFileData());
            newBudgetSubAward.getBudgetSubAwardFiles().add(newBudgetSubAwardFiles);
            
            List listToBeSaved = new ArrayList();
            listToBeSaved.add(newBudgetSubAward);
            listToBeSaved.addAll(newBudgetSubAward.getBudgetSubAwardFiles());
            listToBeSaved.addAll(newBudgetSubAward.getBudgetSubAwardAttachments());
            getBusinessObjectService().save(listToBeSaved);
            
            budgetDocument.getBudget().getBudgetSubAwards().add(newBudgetSubAward);
            
            budgetForm.setNewSubAward(new BudgetSubAwards()); 
        }
        
        newBudgetSubAward.getBudgetSubAwardFiles().clear();
        List<BudgetSubAwardAttachment> attList = newBudgetSubAward.getBudgetSubAwardAttachments();
        for (BudgetSubAwardAttachment budgetSubAwardAttachment : attList) {
            budgetSubAwardAttachment.setAttachment(null);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    @SuppressWarnings("unchecked")
    public ActionForward translateXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards newBudgetSubAward = budgetForm.getNewSubAward();
        boolean success = true;
        if(newBudgetSubAward.getOrganizationName()==null || newBudgetSubAward.getOrganizationName().equals("")){
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_ORG_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        }
        
        FormFile subAwardFile = budgetForm.getSubAwardFile();
        String contentType = subAwardFile.getContentType();
        byte[] subAwardData = subAwardFile.getFileData();
        if(subAwardData==null || subAwardData.length==0 || !contentType.equals(Constants.PDF_REPORT_CONTENT_TYPE)){
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE, Constants.SUBAWARD_FILE_REQUIERED);
            success = false;
        }
        String subAwardFileName = subAwardFile.getFileName();
        
//        newBudgetSubAward.setProposalNumber(budgetDocument.getParentDocument().getDevelopmentProposal().getProposalNumber());
        newBudgetSubAward.setBudgetId(budgetDocument.getBudget().getBudgetId());
        newBudgetSubAward.setSubAwardNumber(generateSubAwardNumber(budgetDocument));
        newBudgetSubAward.setBudgetVersionNumber(budgetDocument.getBudget().getBudgetVersionNumber());
        newBudgetSubAward.setSubAwardStatusCode(1);
        BudgetSubAwardFiles newBudgetsubAwardFiles = new BudgetSubAwardFiles();
        newBudgetsubAwardFiles.setSubAwardXfdFileData(subAwardData);
        newBudgetSubAward.getBudgetSubAwardFiles().add(newBudgetsubAwardFiles);
        KraServiceLocator.getService(BudgetSubAwardService.class).populateBudgetSubAwardFiles(newBudgetSubAward);
        if(newBudgetSubAward.getBudgetSubAwardFiles().isEmpty() || newBudgetSubAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData()==null){
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE, Constants.SUBAWARD_FILE_NOT_EXTRACTED);
            success = false;
        }
        if(success){
            newBudgetsubAwardFiles.setSubAwardXfdFileName(subAwardFileName);
            newBudgetSubAward.setSubAwardXfdFileName(subAwardFileName);
    //        new BudgetSubAwardReader().populateSubAward(budgetSubAwardBean)
            budgetForm.setNewSubAward(new BudgetSubAwards());   
            List listToBeSaved = new ArrayList();
            listToBeSaved.add(newBudgetSubAward);
            listToBeSaved.addAll(newBudgetSubAward.getBudgetSubAwardFiles());
            listToBeSaved.addAll(newBudgetSubAward.getBudgetSubAwardAttachments());
            
            KraServiceLocator.getService(BusinessObjectService.class).save(listToBeSaved);
            budgetDocument.getBudget().getBudgetSubAwards().add(newBudgetSubAward);
        }
        newBudgetSubAward.getBudgetSubAwardFiles().clear();
        List<BudgetSubAwardAttachment> attList = newBudgetSubAward.getBudgetSubAwardAttachments();
        for (BudgetSubAwardAttachment budgetSubAwardAttachment : attList) {
            budgetSubAwardAttachment.setAttachment(null);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward viewXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        subAward.refreshReferenceObject("budgetSubAwardFiles");
        if(!subAward.getBudgetSubAwardFiles().isEmpty()){
            BudgetSubAwardFiles subAwardFiles = subAward.getBudgetSubAwardFiles().get(0);
            downloadFile(form, request, response, subAwardFiles.getSubAwardXfdFileData(), subAward.getSubAwardXfdFileName(), CONTENT_TYPE_PDF);
        }else{
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
//        downloadFile(form, request, response, subAward.getSubAwardXfdFileData(), subAward.getSubAwardXfdFileName(), CONTENT_TYPE_PDF);
        return null;
    }
    
    public ActionForward viewXML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetSubAwards subAward = getSelectedBudgetSubAward(form, request);
        subAward.refreshReferenceObject("budgetSubAwardFiles");
        if(!subAward.getBudgetSubAwardFiles().isEmpty()){
            BudgetSubAwardFiles subAwardFiles = subAward.getBudgetSubAwardFiles().get(0);
            downloadFile(form, request, response, subAwardFiles.getSubAwardXmlFileData().getBytes(), createXMLFileName(subAward), CONTENT_TYPE_XML);
        }
//        downloadFile(form, request, response, new String(subAward.getSubAwardXmlFileData()).getBytes(), createXMLFileName(subAward), CONTENT_TYPE_XML);
        return null;
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int selectedLineNumber = getSelectedLine(request);
        budgetDocument.getBudget().getBudgetSubAwards().remove(selectedLineNumber);
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        BudgetPrintService budgetPrintService = KraServiceLocator
                .getService(BudgetPrintService.class);
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        if (budgetFormToPrint != null) {
                AttachmentDataSource dataStream = budgetPrintService.readBudgetPrintStream(budget,budgetFormToPrint);
                if(dataStream.getContent()!=null){
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
    
    private BudgetDocument getDocument(ActionForm form) {
        return ((BudgetForm)form).getBudgetDocument();
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
        Award currentAward = getAwardBudgetService().getActiveOrNewestAward(((AwardDocument) awardBudgetDocument.getParentDocument()).getAward().getAwardNumber());
        BudgetDecimal newCostLimit = getAwardBudgetService().getTotalCostLimit(currentAward.getAwardDocument());
        if (!newCostLimit.equals(awardBudgetDocument.getBudget().getTotalCostLimit())) {
            Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            String methodToCall = ((KualiForm) form).getMethodToCall();
            if(question == null){
                ConfigurationService kualiConfiguration = KRADServiceLocator.getKualiConfigurationService();
                return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, UPDATE_COST_LIMITS_QUESTION,
                        KeyConstants.QUESTION_TOTALCOSTLIMIT_CHANGED, 
                        new String[]{awardBudgetDocument.getBudget().getTotalCostLimit().toString(), newCostLimit.toString()}), 
                        methodToCall, methodToCall);
            } else if(UPDATE_COST_LIMITS_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                getAwardBudgetService().setBudgetLimits(awardBudgetDocument, currentAward.getAwardDocument());
                return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            } else {
                //do nothing and continue with route
            }    
        }
        ((AwardBudgetForm) form).setAuditActivated(true);
        ValidationState state = new AuditActionHelper().isValidSubmission((AwardBudgetForm) form, true);
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

    private AwardBudgetService getAwardBudgetService() {
        return KraServiceLocator.getService(AwardBudgetService.class);
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
       // boolean success = isValidForSubmission(awardBudgetDocument);
        boolean auditPassed = new AuditActionHelper().auditUnconditionally(awardBudgetDocument);
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
            
        } else {
            getAwardBudgetService().post(awardBudgetDocument);   
        }

        return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);
    }
  
    protected BudgetAdjustmentClient getBudgetAdjustmentClient() {
        if (budgetAdjustmentClient == null) {
            budgetAdjustmentClient = KraServiceLocator.getService("budgetAdjustmentClient");
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
    
    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardBudgetForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardBudgetForm) form, false);
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

}
