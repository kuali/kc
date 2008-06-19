/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.RiceKeyConstants;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.WebUtils;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetJustificationService;
import org.kuali.kra.budget.service.BudgetPrintService;
import org.kuali.kra.budget.service.impl.BudgetJustificationServiceImpl;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.budget.web.struts.form.BudgetJustificationWrapper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.KNSServiceLocator;

public class BudgetActionsAction extends BudgetAction {
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
        try {
            budgetJustificationService.consolidateExpenseJustifications(getBudgetDocument(form), getBudgetJusticationWrapper(form));
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
        budgetJustificationService.preSave(getBudgetDocument(form), getBudgetJusticationWrapper(form));
        
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
        if (docForm.getDocumentActionFlags().getCanSave()) {
            Object question = request.getParameter(RiceConstants.QUESTION_INST_ATTRIBUTE_NAME);
            KualiConfigurationService kualiConfiguration = KNSServiceLocator.getKualiConfigurationService();

            // logic for close question
            if (question == null) {
                // ask question if not already asked
                return this.performQuestionWithoutInput(mapping, form, request, response, RiceConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION, kualiConfiguration.getPropertyString(RiceKeyConstants.QUESTION_SAVE_BEFORE_CLOSE), RiceConstants.CONFIRMATION_QUESTION, RiceConstants.MAPPING_CLOSE, "");
            }
            else {
                Object buttonClicked = request.getParameter(RiceConstants.QUESTION_CLICKED_BUTTON);
                if ((RiceConstants.DOCUMENT_SAVE_BEFORE_CLOSE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                    // if yes button clicked - save the doc
                    budgetJustificationService.preSave(getBudgetDocument(form), getBudgetJusticationWrapper(form));
                }
                // else go to close logic below
            }
        }

        return super.close(mapping, form, request, response);
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.reload(mapping, form, request, response);
    }
    
    public ActionForward translateXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetSubAwards newBudgetSubAward = budgetForm.getNewSubAward();
        DocumentNextvalue documentNextValue = new DocumentNextvalue();
        if(budgetDocument.getHackedDocumentNextValue("subAwardNumber")!=null){
            newBudgetSubAward.setSubAwardNumber(budgetDocument.getHackedDocumentNextValue("subAwardNumber"));
        }else{
            newBudgetSubAward.setSubAwardNumber(1);
        }
        //TODO: SubAwardStatus Code = should it be updated as per attachment value?
        newBudgetSubAward.setSubAwardStatusCode(1);
        newBudgetSubAward.setSubAwardXfdFileName(newBudgetSubAward.getSubAwardXfdFile().getFileName());
        newBudgetSubAward.setSubAwardXfdFileData(newBudgetSubAward.getSubAwardXfdFile().getFileData());
        newBudgetSubAward.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
        //TODO: To Call Geo's translate service and update BudgetSubAward BO accordingly.
        budgetDocument.getBudgetSubAwards().add(newBudgetSubAward);
        budgetForm.setNewSubAward(new BudgetSubAwards());                
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward viewXFD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewXML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward printBudgetForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetPrintService budgetPrintService = KraServiceLocator.getService(BudgetPrintService.class);
        try{
            AttachmentDataSource dataStream = budgetPrintService.readBudgetPrintStream(budgetDocument, budgetForm.getSelectedBudgetPrintFormId());
            streamToResponse(dataStream,response);
        }catch(Exception ex){
            LOG.warn(ex);
        }
        return budgetForm.getSelectedBudgetPrintFormId() == null ? mapping.findForward(MAPPING_BASIC) : null;
    }

    public void setBudgetJustificationService(BudgetJustificationService budgetJustificationService) {
        this.budgetJustificationService = budgetJustificationService;
    }

    private BudgetDocument getBudgetDocument(ActionForm form) {
        return ((BudgetForm) form).getBudgetDocument();
    }

    private BudgetJustificationWrapper getBudgetJusticationWrapper(ActionForm form) {
        return ((BudgetForm) form).getBudgetJustification();
    }
    
}
