/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.authorization.BudgetDocumentAuthorizer;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.service.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.service.BudgetModularService;
import org.kuali.kra.budget.service.BudgetPrintService;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.service.BudgetSubAwardService;
import org.kuali.kra.budget.service.impl.BudgetDistributionAndIncomeServiceImpl;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.ProposalActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

public class BudgetAction extends ProposalActionBase {
    private static final Log LOG = LogFactory.getLog(BudgetAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;

        if (KEWConstants.INITIATE_COMMAND.equals(budgetForm.getCommand())) {
            budgetForm.getBudgetDocument().initialize();
        }else{
            budgetForm.initialize();
        }
        
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        // populate costelement and other shared field to personnel detail
        // primarily to prevent sync modular budget in case 'reload' on modular budget page
        copyLineItemToPersonnelDetails(budgetDocument);
        if (budgetDocument.getActivityTypeCode().equals("x")) {
            budgetDocument.setActivityTypeCode(KraServiceLocator.getService(BudgetService.class).getActivityTypeForBudget(budgetDocument));
        }
        if(budgetDocument.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null){
            // this is to prevent item calamts to be regenerated again when load doc from doc search
            // getting uglier.  definitely candidate for refactoring
            ((BudgetForm)GlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(budgetDocument.getOhRateClassCode());
        }        

        reconcileBudgetStatus(budgetForm);
        return forward;
    }

    /**
     * Need to suppress buttons here when 'Totals' tab is clicked.
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        
        if (actionForward != null) {
            if (("totals").equals(actionForward.getName())) { 
                ((BudgetForm)form).suppressButtonsForTotalPage();
            }               
        }
        BudgetForm budgetForm = (BudgetForm) form;
        new AuditActionHelper().auditConditionally(budgetForm);
        
        
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(budgetDocument != null) {
            budgetDocument.setRateClassTypesReloaded(false);
        }
        
        return actionForward; 
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        // jira 1288
        budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(((BudgetForm) form).getBudgetDocument());
        ActionForward forward = super.save(mapping, form, request, response);

        if (budgetForm.getMethodToCall().equals("save") && budgetForm.isAuditActivated()) {
            DocumentService docService = KraServiceLocator.getService(DocumentService.class);
            ProposalDevelopmentDocument pdDoc = 
                (ProposalDevelopmentDocument) docService.getByDocumentHeaderId(budgetForm.getBudgetDocument().getProposal().getDocumentNumber());
            String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
            forwardUrl = StringUtils.replace(forwardUrl, "Proposal.do?", "Actions.do?auditActivated=true&");
            forward = new ActionForward(forwardUrl, true);
        }

        return forward;
    } 

    public ActionForward versions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.getBudgetDocument().getProposal().refreshReferenceObject(Constants.BUDGET_VERSION_OVERVIEWS);
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews()));
        setBudgetStatuses(budgetForm.getBudgetDocument().getProposal());
        return mapping.findForward("versions");
    }

    public ActionForward summary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        reconcileBudgetStatus((BudgetForm) form);
        ((BudgetForm) form).getBudgetDocument().getBudgetSummaryService().setupOldStartEndDate(((BudgetForm) form).getBudgetDocument(),false);
        return mapping.findForward("summary");
    }

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        reconcilePersonnelRoles(budgetForm.getBudgetDocument());
        
        return mapping.findForward("personnel");
    }

    public ActionForward expenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
        for(int i=0;i<budgetCategoryTypes.size();i++){
            budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
        }
        budgetForm.getBudgetDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
        BudgetDocument budgetDocument = (BudgetDocument) budgetForm.getBudgetDocument();
        budgetDocument.refreshReferenceObject("budgetPeriods");       
        
        return mapping.findForward("expenses");
    }

    public ActionForward rates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("rates");
    }

    public ActionForward distributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetDistributionAndIncomeService budgetDistributionAndIncomeService = new BudgetDistributionAndIncomeServiceImpl();
        budgetDistributionAndIncomeService.initializeCollectionDefaults(((BudgetForm) form).getBudgetDocument());
        
        return mapping.findForward("distributionAndIncome");
    }

    public ActionForward modularBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetModularService budgetModularService = KraServiceLocator.getService(BudgetModularService.class);
        budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budgetForm.getBudgetDocument()));
        return mapping.findForward("modularBudget");
    }

    public ActionForward totals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.getBudgetDocument().getBudgetTotals();
        return mapping.findForward("totals");
    }

    public ActionForward proposalHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("proposalHierarchy");
    }

    public ActionForward budgetActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(budgetDocument.getBudgetPrintForms().isEmpty()){
            BudgetPrintService budgetPrintService = KraServiceLocator.getService(BudgetPrintService.class);
            budgetPrintService.populateBudgetPrintForms(budgetDocument);
        }
        KraServiceLocator.getService(BudgetSubAwardService.class).populateBudgetSubAwardAttachments(budgetDocument);
        return mapping.findForward("budgetActions");
    }
    
    public ActionForward returnToProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        String docNumber = budgetForm.getBudgetDocument().getProposal().getDocumentNumber();
        ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) docService.getByDocumentHeaderId(docNumber);
        
        // JIRA KRACOEUS-1441
        save(mapping, form, request, response);
        
        String forward = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        return new ActionForward(forward, true);
    }
    
    public void reconcilePersonnelRoles(BudgetDocument budgetDocument) {
//      Populate the person's proposal roles, if they exist
        List<BudgetPerson> budgetPersons = budgetDocument.getBudgetPersons();
        for (BudgetPerson budgetPerson: budgetPersons) {
            if (budgetPerson.getRolodexId() != null) {
                ProposalPersonRole role = budgetDocument.getProposal().getProposalNonEmployeeRole(budgetPerson.getRolodexId());
                if (role != null) { budgetPerson.setRole(role.getDescription()); }
            } else if (budgetPerson.getPersonId() != null) {
                ProposalPersonRole role = budgetDocument.getProposal().getProposalEmployeeRole(budgetPerson.getPersonId());
                if (role != null) { budgetPerson.setRole(role.getDescription()); }
            }
        }
    }
    
    protected void reconcileBudgetStatus(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if (budgetDocument.getFinalVersionFlag() != null && budgetDocument.getFinalVersionFlag()) {
            budgetDocument.setBudgetStatus(budgetDocument.getProposal().getBudgetStatus());
        } else {
            String budgetStatusIncompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
            budgetDocument.setBudgetStatus(budgetStatusIncompleteCode);
        }
    }
    
    
    /**
     * 
     * Sets periodTypeCode in BudgetPersonnelDetails to system variable BUDGET_PERSON_DEFAULT_PERIOD_TYPE
     */
    public void setBudgetPersonDefaultPeriodTypeCode(BudgetForm budgetForm){
        KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
        budgetForm.getNewBudgetPersonnelDetails().setPeriodTypeCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_PERSON_DEFAULT_PERIOD_TYPE));
        
    }

    /**
     * 
     * Handy method to stream the byte array to response object
     * @param attachmentDataSource
     * @param response
     * @throws Exception
     */
    public void streamToResponse(AttachmentDataSource attachmentDataSource,HttpServletResponse response) throws Exception{
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
            
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
    
    private void copyLineItemToPersonnelDetails(BudgetDocument budgetDocument) {
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
        
                    if (budgetLineItem.getBudgetPersonnelDetailsList() != null && !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                            budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
                            budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
                            budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
                            budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
                            budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
                            budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
                       }
                    }
                }
            }
        }
    }

    @Override
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase){
        super.populateAuthorizationFields(formBase);
        BudgetDocumentAuthorizer documentAuthorizer = new BudgetDocumentAuthorizer();
        formBase.setEditingMode(documentAuthorizer.getEditMode(formBase.getDocument(), 
                new UniversalUser(GlobalVariables.getUserSession().getPerson())));
    }

}
