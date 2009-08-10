/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationService;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationServiceImpl;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetPrintService;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

public class BudgetActionsAction extends BudgetAction {
    private static final String CONTENT_TYPE_XML = "text/xml";
    private static final String XML_FILE_EXTENSION = "xml";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private BudgetJustificationService budgetJustificationService;
    private static final Log LOG = LogFactory.getLog(BudgetActionsAction.class);
    

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
            GlobalVariables.getErrorMap().putError("budgetJustificationWrapper.justificationText", "error.custom", "There are no line item budget justifications");            
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
        if (docForm.getDocumentActions().containsKey(KNSConstants.KUALI_ACTION_CAN_SAVE)) {
            Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
            KualiConfigurationService kualiConfiguration = KNSServiceLocator.getKualiConfigurationService();

            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, kualiConfiguration.getPropertyString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), KNSConstants.CONFIRMATION_QUESTION, KNSConstants.MAPPING_CLOSE, "");
            }
            else {
                BudgetForm budgetForm = (BudgetForm)form;
                Budget budget = budgetForm.getBudgetDocument().getBudget();

                Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                if ((KNSConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
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
    
    @SuppressWarnings("unchecked")
    public ActionForward translateXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards newBudgetSubAward = budgetForm.getNewSubAward();
        boolean success = true;
        if(newBudgetSubAward.getOrganizationName()==null || newBudgetSubAward.getOrganizationName().equals("")){
            GlobalVariables.getErrorMap().putError(Constants.SUBAWARD_ORG_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        }
        
        FormFile subAwardFile = budgetForm.getSubAwardFile();
        String contentType = subAwardFile.getContentType();
        byte[] subAwardData = subAwardFile.getFileData();
        if(subAwardData==null || subAwardData.length==0 || !contentType.equals(Constants.PDF_REPORT_CONTENT_TYPE)){
            GlobalVariables.getErrorMap().putError(Constants.SUBAWARD_FILE, Constants.SUBAWARD_FILE_REQUIERED);
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
            GlobalVariables.getErrorMap().putError(Constants.SUBAWARD_FILE, Constants.SUBAWARD_FILE_NOT_EXTRACTED);
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

    public ActionForward printBudgetForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        BudgetPrintService budgetPrintService = KraServiceLocator.getService(BudgetPrintService.class);
        ActionForward forward = null;
        if (budgetForm.getSelectedBudgetPrintFormId() != null) {
            boolean reportOK = budgetPrintService.printBudgetForms(budget, budgetForm.getSelectedBudgetPrintFormId(), response);
            budgetForm.setSelectedBudgetPrintFormId(null);
            if (!reportOK) {
                forward = mapping.findForward(MAPPING_BASIC);                
            }
        } else {
            forward = mapping.findForward(MAPPING_BASIC);
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
}
