/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.core.BudgetCommonServiceFactory;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonService;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.HierarchyPersonnelSummary;
import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetLockService;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kew.KraDocumentRejectionService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularService;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetPrintService;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.web.struts.action.BudgetActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.rule.event.DocumentAuditEvent;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.ui.HeaderField;

public class BudgetAction extends BudgetActionBase {
    private static final Log LOG = LogFactory.getLog(BudgetAction.class);
    
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    protected static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    protected static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";
    protected static final String CONFIRM_SYNCH_AWARD_RATES = "confirmSynchAwardRates";
    protected static final String NO_SYNCH_AWARD_RATES = "noSynchAwardRates";

    private ProposalHierarcyActionHelper hierarchyHelper;
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        if (KEWConstants.INITIATE_COMMAND.equals(budgetForm.getCommand())) {
            budgetForm.getDocument().initialize();
        }else{
            budgetForm.initialize();
        }
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if (budgetDocument.isBudgetDeleted()) {
            return mapping.findForward("deleted");
        }
        Budget budget = budgetDocument.getBudget();
        copyLineItemToPersonnelDetails(budgetDocument);
        if (budget.getActivityTypeCode().equals("x")) {
            budget.setActivityTypeCode(KraServiceLocator.getService(BudgetService.class).getActivityTypeForBudget(budgetDocument));
        }

        if(budget.getOhRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null){
            ((BudgetForm)GlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(budget.getOhRateClassCode());
        }        
        if(budget.getUrRateClassCode()!=null && ((BudgetForm)GlobalVariables.getKualiForm())!=null){
            ((BudgetForm)GlobalVariables.getKualiForm()).setUrRateClassCodePrevValue(budget.getUrRateClassCode());
        }
        
        if (isAwardBudget(budgetDocument) && StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            getBudgetRatesService().syncParentDocumentRates(budgetDocument);
            getBudgetCommonService(budgetDocument.getParentDocument()).recalculateBudget(budget);
        }
        
        reconcileBudgetStatus(budgetForm);
        
        if ("Personnel".equals(budgetForm.getActivePanelName())) {
            forward = personnel(mapping, budgetForm, request, response);
        }
        return forward;
    }

    protected StrutsConfirmation syncAwardBudgetRateConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String message) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNCH_AWARD_RATES,
                message, "");
    }
    public ActionForward confirmSynchAwardRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchAwardBudgetRate(mapping, form, request, response, true);
    }
    public ActionForward noSynchAwardRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchAwardBudgetRate(mapping, form, request, response, false);
    }
    private ActionForward synchAwardBudgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, boolean confirm) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getDocument();
        BudgetParentDocument pdDoc = budgetDoc.getParentDocument();
        Long routeHeaderId = budgetDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        if (confirm) {
            forward = forward.replace("awardBudgetParameters.do?", "awardBudgetParameters.do?syncBudgetRate=Y&");
         }
        return new ActionForward(forward, true);
    }

    /**
     * This method returns true if the BudgetDocument is an AwardBudgetDocument instance
     * @param budgetDocument
     * @return
     */
    protected boolean isAwardBudget(BudgetDocument budgetDocument) {
        return !Boolean.parseBoolean(budgetDocument.getParentDocument().getProposalBudgetFlag());
    }

    
    private BudgetRatesService<BudgetParent> getBudgetRatesService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
    }
    public List<HeaderNavigation> getBudgetHeaderNavigatorList(){
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary().getDocumentEntry(BudgetDocument.class.getName());
        return docEntry.getHeaderNavigationList();
      }
    
    /**
     * Need to suppress buttons here when 'Totals' tab is clicked.
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final BudgetForm budgetForm = (BudgetForm) form;
        if(budgetForm.getMethodToCall().equals("close")){
            setupDocumentExit();
        }
        ActionForward actionForward = null;
        
        actionForward = super.execute(mapping, budgetForm, request, response);    
        
        if (actionForward != null) {
            if ("summaryTotals".equals(actionForward.getName())) { 
                budgetForm.suppressButtonsForTotalPage();
            }               
        }
        // check if audit rule check is done from PD
        if (budgetForm.isAuditActivated() && !"route".equals(((KualiForm)form).getMethodToCall())) {
            KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetForm.getDocument()));
        }
        
        return actionForward; 
    }

    /**
     * This method sets additional document information in the document header of the budget form.
     * If the document contained in the budget form is null then empty values will be placed for the
     * additional information.
     * @param budgetForm the budget form
     */
    private void setAdditionalDocumentHeaderInfo(final BudgetForm budgetForm) {
        assert budgetForm != null : "the budgetForm is null";
        
        final BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
        if (budget != null && parentDocument != null && parentDocument.getBudgetDocumentVersions() != null) {
            boolean setAdditionalInfo = false;
            for (final BudgetDocumentVersion budgetVersion: parentDocument.getBudgetDocumentVersions()) {
                if (budgetVersion.getBudgetVersionOverview().getBudgetVersionNumber().equals(budget.getBudgetVersionNumber())) {
                    budgetForm.getDocInfo().add(new HeaderField(BudgetForm.BUDGET_NAME_KEY, budgetVersion.getBudgetVersionOverview().getDocumentDescription()));
                    setAdditionalInfo = true;
                    break;
                }
                else {
                    budgetForm.getDocInfo().add(new HeaderField(BudgetForm.BUDGET_NAME_KEY, Constants.EMPTY_STRING));
                }
            }
            if(!setAdditionalInfo){
                budgetForm.getDocInfo().add(new HeaderField(BudgetForm.BUDGET_NAME_KEY, Constants.EMPTY_STRING));
            }

            if (budget.getBudgetVersionNumber() != null) {
                budgetForm.getDocInfo().add(new HeaderField(BudgetForm.VERSION_NUMBER_KEY, budget.getBudgetVersionNumber().toString()));
            } else {
                budgetForm.getDocInfo().add(new HeaderField(BudgetForm.VERSION_NUMBER_KEY, Constants.EMPTY_STRING));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        final BudgetDocument budgetDoc = budgetForm.getDocument();
        fixDocHeaderVersion(budgetDoc);
        Budget budget = budgetDoc.getBudget();
        getBudgetCommonService(budgetDoc.getParentDocument()).calculateBudgetOnSave(budget);
        ActionForward forward = super.save(mapping, form, request, response);
        BudgetForm savedBudgetForm = (BudgetForm) form;
        BudgetDocument savedBudgetDoc = savedBudgetForm.getDocument();
        refreshBudgetDocumentVersion(savedBudgetDoc);
        getBusinessObjectService().save(savedBudgetDoc.getParentDocument().getBudgetDocumentVersions());       

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        if (budgetForm.toBudgetVersionsPage()
            || "BudgetVersionsAction".equals(budgetForm.getActionName())) {
            GlobalVariables.getErrorMap().addToErrorPath(KNSConstants.DOCUMENT_PROPERTY_NAME + ".proposal");
            tdcValidator.validateGeneratingErrorsAndWarnings(budgetDoc.getParentDocument());
        } else {
            tdcValidator.validateGeneratingWarnings(budgetDoc.getParentDocument());
        }

        if (budgetForm.getMethodToCall().equals("save") && budgetForm.isAuditActivated()) {
            if (Boolean.valueOf(savedBudgetDoc.getParentDocument().getProposalBudgetFlag())) {
                forward = this.getReturnToProposalForward(budgetForm);
            }
            else {
                forward = mapping.findForward("budgetActions");
            }
        }

        return forward;
    }

    private void refreshBudgetDocumentVersion(BudgetDocument savedBudgetDoc) {
        Budget budget = savedBudgetDoc.getBudget();
        for (BudgetDocumentVersion documentVersion: savedBudgetDoc.getParentDocument().getBudgetDocumentVersions()) {
            documentVersion.refreshReferenceObject("documentHeader");
            BudgetVersionOverview version = documentVersion.getBudgetVersionOverview();
            if (budget.getBudgetVersionNumber().equals(version.getBudgetVersionNumber())) {
                documentVersion.refreshReferenceObject("budgetVersionOverviews");
            }
        }
    }
    
    /**
     * Load the document again and ensure that the document header version is the same to avoid optimistic lock exceptions
     * given that the proposal might save this doc header.
     * @param budgetDoc
     * @throws WorkflowException
     */
    protected void fixDocHeaderVersion(BudgetDocument budgetDoc) throws WorkflowException {
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument updatedDoc = (BudgetDocument) docService.getByDocumentHeaderId(budgetDoc.getDocumentNumber());
        budgetDoc.getDocumentHeader().setVersionNumber(updatedDoc.getDocumentHeader().getVersionNumber());
        //update all budget versions doc headers as we will try to save them all and can be saved by proposal under separate lock as well
        for (int i = 0; i < budgetDoc.getParentDocument().getBudgetDocumentVersions().size(); i++) {
            BudgetDocumentVersion bdVersion = budgetDoc.getParentDocument().getBudgetDocumentVersion(i);
            BudgetDocumentVersion updatedVersion = updatedDoc.getParentDocument().getBudgetDocumentVersion(i);
            if (bdVersion != null && updatedVersion != null) {
                if (bdVersion.getDocumentHeader().getVersionNumber() < updatedVersion.getDocumentHeader().getVersionNumber()) {
                    bdVersion.getDocumentHeader().setVersionNumber(updatedVersion.getDocumentHeader().getVersionNumber());
                }
            }
        }
    }


    protected BudgetSummaryService getBudgetSummaryService() {
        return KraServiceLocator.getService(BudgetSummaryService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        final ActionForward forward = super.reload(mapping, form, request, response);
        final BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();

        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(parentDocument.getBudgetDocumentVersions()));
        setBudgetStatuses(budgetDocument.getParentDocument());

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(budgetDocument.getParentDocument());

        populateBudgetPrintForms(budgetDocument.getBudget());
        return forward;
    }
    
    public ActionForward versions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
//        proposal.refreshReferenceObject("budgetDocumentVersions");
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(parentDocument.getBudgetDocumentVersions()));
        setBudgetStatuses(parentDocument);
        return mapping.findForward(Constants.BUDGET_VERSIONS_PAGE);
    }

    public ActionForward parameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        reconcileBudgetStatus((BudgetForm) form);
        BudgetDocument budgetDocument = ((BudgetForm)form).getBudgetDocument();

        getBudgetSummaryService().setupOldStartEndDate(budgetDocument.getBudget(),false);
        return mapping.findForward(Constants.BUDGET_PERIOD_PAGE);
    }

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        populatePersonnelHierarchySummary(budgetForm);
        populatePersonnelCategoryTypeCodes(budgetForm);
        if (budgetForm.getBudgetDocument().getBudget().getBudgetPersons().isEmpty()) {
            KraServiceLocator.getService(BudgetPersonService.class).synchBudgetPersonsToProposal(budgetForm.getBudgetDocument().getBudget());
        }
        reconcilePersonnelRoles(budgetForm.getDocument());
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        for(BudgetPeriod period : budget.getBudgetPeriods()) {
            for(BudgetLineItem lineItem : period.getBudgetLineItems()) {
                for(BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                    budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
                    ObjectUtils.materializeObjects(budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts());
                    for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                        if(budgetPersonnelCalculatedAmount.getRateClass() == null) {
                            budgetPersonnelCalculatedAmount.refreshReferenceObject("rateClass");
                        }
                    }
                }
                
                for(BudgetLineItemCalculatedAmount lineItemCalculatedAmount:lineItem.getBudgetLineItemCalculatedAmounts()){
                    if(lineItemCalculatedAmount.getRateClass() == null) {
                        lineItemCalculatedAmount.refreshReferenceObject("rateClass");
                    }
                }
            }
        }
        return mapping.findForward(Constants.BUDGET_PERSONNEL_PAGE);
    }
    
    protected void populatePersonnelHierarchySummary(BudgetForm budgetForm) {
        if (Boolean.valueOf(budgetForm.getDocument().getParentDocument().getProposalBudgetFlag())) {
            ProposalDevelopmentDocument parentDocument = (ProposalDevelopmentDocument) budgetForm.getDocument().getParentDocument();
            String proposalNumber = parentDocument.getDevelopmentProposal().getProposalNumber();
            budgetForm.setHierarchyPersonnelSummaries(getHierarchyHelper().getHierarchyPersonnelSummaries(proposalNumber));
            for (HierarchyPersonnelSummary hierarchyPersonnelSummary : budgetForm.getHierarchyPersonnelSummaries()) {
                for (Budget budget : hierarchyPersonnelSummary.getHierarchyBudgets()) {
                    reconcilePersonnelRoles(budget.getBudgetDocument());
                }
            }
        }
    }

    private String getPersonnelBudgetCategoryTypeCode() {
        return this.getParameterService().getParameterValue(BudgetDocument.class, Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }
    
    protected void populatePersonnelCategoryTypeCodes(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();   
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyLabelPair budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
            String budgetCategoryTypeCode = (String) budgetCategoryType.getKey();
            if(StringUtils.isNotBlank(budgetCategoryTypeCode) && StringUtils.equalsIgnoreCase(budgetCategoryTypeCode, personnelBudgetCategoryTypeCode)) {
                budgetCategoryTypes.add(budgetCategoryType);
                BudgetLineItem newBudgetLineItem = budget.getNewBudgetLineItem();
                if (budgetForm.getNewBudgetLineItems() == null) {
                    budgetForm.setNewBudgetLineItems(new ArrayList<BudgetLineItem>());
                }
                budgetForm.getNewBudgetLineItems().add(newBudgetLineItem);
            }
        }
        budget.setBudgetCategoryTypeCodes(budgetCategoryTypes); 
    }

    protected void populateNonPersonnelCategoryTypeCodes(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();      
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyLabelPair budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
            String budgetCategoryTypeCode = (String) budgetCategoryType.getKey();
            if(StringUtils.isNotBlank(budgetCategoryTypeCode) && !StringUtils.equalsIgnoreCase(budgetCategoryTypeCode, personnelBudgetCategoryTypeCode)) {
                budgetCategoryTypes.add(budgetCategoryType);
                BudgetLineItem newBudgetLineItem = budget.getNewBudgetLineItem();
                budgetForm.getNewBudgetLineItems().add(newBudgetLineItem);
            }
        }
        budget.setBudgetCategoryTypeCodes(budgetCategoryTypes); 
    }
    
    public ActionForward expenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        
        populateNonPersonnelCategoryTypeCodes(budgetForm);
        
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        budget.refreshReferenceObject("budgetPeriods");       
        
        return mapping.findForward(Constants.BUDGET_EXPENSES_PAGE);
    }

    public ActionForward rates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.BUDGET_RATES_PAGE);
    }

    public ActionForward distributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetDistributionAndIncomeService budgetDistributionAndIncomeService = KraServiceLocator.getService(BudgetDistributionAndIncomeService.class);
        budgetDistributionAndIncomeService.initializeCollectionDefaults(((BudgetForm) form).getDocument().getBudget());
        
        return mapping.findForward(Constants.BUDGET_DIST_AND_INCOME_PAGE);
    }

    public ActionForward modularBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetModularService budgetModularService = KraServiceLocator.getService(BudgetModularService.class);
        budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budgetForm.getDocument().getBudget()));
        return mapping.findForward(Constants.BUDGET_MODULAR_PAGE);
    }

    protected void populatePersonnelRoles(BudgetDocument budgetDocument) {
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        
        List<BudgetPerson> budgetPersons = budgetDocument.getBudget().getBudgetPersons();
        for (BudgetPerson budgetPerson: budgetPersons) {
            String roleDesc = "";
            if (budgetPerson.getRolodexId() != null) {
                PersonRolodex person = budgetParent.getProposalNonEmployee(budgetPerson.getRolodexId());
                ContactRole role = budgetParent.getProposalNonEmployeeRole(budgetPerson.getRolodexId());
                if (role != null) { 
                    roleDesc = person.getInvestigatorRoleDescription();
                    if(person != null && StringUtils.equals(Constants.KEY_PERSON_ROLE, role.getRoleCode()) && StringUtils.isNotEmpty(person.getProjectRole())) {
                        roleDesc = person.getProjectRole();
                    }
                }
            } else if (budgetPerson.getPersonId() != null) {
                PersonRolodex person = budgetParent.getProposalEmployee(budgetPerson.getPersonId());  
                ContactRole role = budgetParent.getProposalEmployeeRole(budgetPerson.getPersonId());
                if (role != null) { 
                    roleDesc = person.getInvestigatorRoleDescription();
                    if(person != null && StringUtils.equals(Constants.KEY_PERSON_ROLE, role.getRoleCode()) && StringUtils.isNotEmpty(person.getProjectRole())) {
                        roleDesc = person.getProjectRole();
                    }
                }
            }
            
            budgetPerson.setRole(roleDesc);
        }
    }
    
    public ActionForward summaryTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        populatePersonnelRoles(budgetDocument);
        Budget budget = budgetDocument.getBudget();
        for(BudgetPeriod period : budget.getBudgetPeriods()) {
            for(BudgetLineItem lineItem : period.getBudgetLineItems()) {
                for(BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                    budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
                }
            }
        }
        
        budget.getBudgetTotals();
        budgetForm.setProposalHierarchyIndirectObjectCode(getParameterService().getParameterValue(BudgetDocument.class, "proposalHierarchySubProjectIndirectCostElement"));
        return mapping.findForward(Constants.BUDGET_SUMMARY_TOTALS_PAGE);
    }

    public ActionForward proposalHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PROPOSAL_HIERARCHY_PAGE);
    }

    public ActionForward hierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm)form;
        ProposalDevelopmentDocument aDoc = (ProposalDevelopmentDocument) budgetForm.getDocument().getParentDocument();
        
        budgetForm.setHierarchyProposalSummaries(getHierarchyHelper().getHierarchyProposalSummaries(aDoc.getDevelopmentProposal().getProposalNumber()));
        return mapping.findForward(Constants.HIERARCHY_PAGE);
    }
    
    public ActionForward budgetActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        populateBudgetPrintForms(budget);
        KraServiceLocator.getService(BudgetSubAwardService.class).populateBudgetSubAwardAttachments(budget);
        return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);
    }

    protected ProposalHierarcyActionHelper getHierarchyHelper() {
        if (hierarchyHelper == null) {
            hierarchyHelper = new ProposalHierarcyActionHelper();
        }
        return hierarchyHelper;
    }

    /**
     * This method...
     * @param budget
     */
    private void populateBudgetPrintForms(Budget budget) {
        if(budget.getBudgetPrintForms().isEmpty()){
            BudgetPrintService budgetPrintService = KraServiceLocator.getService(BudgetPrintService.class);
            budgetPrintService.populateBudgetPrintForms(budget);
        }
    }
    
    public ActionForward returnToProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final BudgetForm budgetForm = (BudgetForm) form;
        ActionForward forward = null;
        
        if (!StringUtils.equalsIgnoreCase((String)budgetForm.getEditingMode().get(AuthorizationConstants.EditMode.VIEW_ONLY), "TRUE")) {
            forward = this.save(mapping, form, request, response);
        }
       
        setupDocumentExit();
        
        if (forward == null || !forward.getPath().contains(KNSConstants.QUESTION_ACTION)) {
            return this.getReturnToProposalForward(budgetForm);
        }
        
        return forward;
    }
    public ActionForward returnToAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final BudgetForm budgetForm = (BudgetForm) form;
        ActionForward forward = null;
        
        if (!"true".equals(budgetForm.getEditingMode().get(AuthorizationConstants.EditMode.VIEW_ONLY))) {
            forward = this.save(mapping, form, request, response);
        }

        setupDocumentExit();
        
        if (forward == null || !forward.getPath().contains(KNSConstants.QUESTION_ACTION)) {
            return this.getReturnToAwardForward(budgetForm);
        }
        
        return forward;
    }
    
    private ActionForward getReturnToAwardForward(BudgetForm budgetForm) throws Exception{
        assert budgetForm != null : "the form is null";
        
        final DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        Award award = ((AwardDocument) budgetForm.getBudgetDocument().getParentDocument()).getAward();
        
        //find the newest, uncanceled award document to return to
        String docNumber = budgetForm.getBudgetDocument().getParentDocument().getDocumentNumber();
        List<VersionHistory> versions = KraServiceLocator.getService(VersionHistoryService.class).loadVersionHistory(Award.class, award.getAwardNumber());
        for (VersionHistory version : versions) {
            if (version.getSequenceOwnerSequenceNumber() > award.getSequenceNumber() &&
                    version.getStatus() != VersionStatus.CANCELED) {
                docNumber = ((Award) version.getSequenceOwner()).getAwardDocument().getDocumentNumber();
            }
        }
        final AwardDocument awardDocument = (AwardDocument) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(awardDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        if(budgetForm.isAuditActivated()) {
            forwardUrl = StringUtils.replace(forwardUrl, "Award.do?", "Actions.do?");
        }
        //add showAllBudgetVersion to the url to persist that flag until they leave the document
        forwardUrl = StringUtils.replace(forwardUrl, ".do?", ".do?showAllBudgetVersions=" + budgetForm.isShowAllBudgetVersions() + "&");
        
        return new ActionForward(forwardUrl, true);
    }

    /**
     * Gets the correct return-to-proposal action forward.
     * 
     * @param form the budget form
     * @return the action forward
     * @throws WorkflowException if there is a problem interacting with workflow
     */
    private ActionForward getReturnToProposalForward(final BudgetForm form) throws WorkflowException {
        assert form != null : "the form is null";
        final DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        final String docNumber = form.getDocument().getParentDocument().getDocumentNumber();
        
        final ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        if(form.isAuditActivated()) {
            forwardUrl = StringUtils.replace(forwardUrl, "Proposal.do?", "Actions.do?auditActivated=true&");
        }
        forwardUrl += "&methodToCallAttribute=methodToCall.reload";
        return new ActionForward(forwardUrl, true);
    }
    
    public void reconcilePersonnelRoles(BudgetDocument budgetDocument) {
//      Populate the person's proposal roles, if they exist
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        List<BudgetPerson> budgetPersons = budget.getBudgetPersons();
        
        for (BudgetPerson budgetPerson: budgetPersons) {
            if (budgetPerson.getRolodexId() != null) {
                PersonRolodex person = budgetParent.getProposalNonEmployee(budgetPerson.getRolodexId());
                if (person != null) { budgetPerson.setRole(person.getInvestigatorRoleDescription()); }
            } else if (budgetPerson.getPersonId() != null) {
                PersonRolodex person = budgetParent.getProposalEmployee(budgetPerson.getPersonId());
                if (person != null) { budgetPerson.setRole(person.getInvestigatorRoleDescription()); }
            }
        }
    }
    
    protected void reconcileBudgetStatus(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getParentDocument().getBudgetParent();
        if (budgetParent instanceof DevelopmentProposal) {
            DevelopmentProposal proposal = (DevelopmentProposal)budgetParent;
            KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(proposal);
        }
        if (budget.getFinalVersionFlag() != null && Boolean.TRUE.equals(budget.getFinalVersionFlag())) {
            budget.setBudgetStatus(budgetParent.getBudgetStatus());
        } else {
            String budgetStatusIncompleteCode = this.getParameterService().getParameterValue(
                    BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
            budget.setBudgetStatus(budgetStatusIncompleteCode);
        }        
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
        if(xbts!=null)
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
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudget().getBudgetPeriods()) {
            if (budgetPeriod.getBudgetLineItems() != null && !budgetPeriod.getBudgetLineItems().isEmpty()) {
                for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
        
                    if (budgetLineItem.getBudgetPersonnelDetailsList() != null && !budgetLineItem.getBudgetPersonnelDetailsList().isEmpty()) {
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
//                            budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
//                            budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
                            budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
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

    
    /**
     * @see org.kuali.kra.web.struts.action.BudgetActionBase#getPessimisticLockService()
     */
    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KraServiceLocator.getService(BudgetLockService.class);
    }
    
    public ActionForward reject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        final String questionText = "Are you sure you want to reject this document?";
        ActionForward forward;
        if (question == null) {
            forward =  this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,
                    questionText , KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
        } else if ((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            forward =  mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            if (StringUtils.isEmpty(reason)) {
                String context = "";
                String errorKey = KeyConstants.ERROR_BUDGET_REJECT_NO_REASON;
                String errorPropertyName = DOCUMENT_REJECT_QUESTION;
                String errorParameter = "";
                reason = reason == null ? "" : reason;
                forward = this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, DOCUMENT_REJECT_QUESTION, 
                        questionText, KNSConstants.CONFIRMATION_QUESTION, methodToCall, context, reason, errorKey, errorPropertyName, 
                        errorParameter);
            } else {
                //reject the document using the service.
                BudgetDocument document = ((BudgetForm)form).getDocument();
                document.documentHasBeenRejected(reason);
                KraServiceLocator.getService(KraDocumentRejectionService.class).reject(document.getDocumentNumber(), reason, 
                        GlobalVariables.getUserSession().getPrincipalId());
                //tell the document it is being rejected and returned to the initial node.
                forward = super.returnToSender(request, mapping, kualiDocumentFormBase);
            }
            
        }
        return forward;
    }
    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParentDocument parentBudgetDocument) {
        return BudgetCommonServiceFactory.createInstance(parentBudgetDocument);
    }
    /**
     * This method is to recalculate the budget period
     * @param budgetForm
     * @param budget
     * @param budgetPeriod
     */
    protected void recalculateBudgetPeriod(BudgetForm budgetForm, Budget budget, BudgetPeriod budgetPeriod) {
        getBudgetCommonService(budgetForm.getBudgetDocument().getParentDocument()).recalculateBudgetPeriod(budget, budgetPeriod);
    }  
    /**
     * This method...
     * @param budget
     * @param budgetPeriod
     */
    protected void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        getCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
    }
    /**
     * Locates the {@link BudgetCalculationService]
     *
     * @return {@link BudgetCalculationService} singleton instance
     */ 
    protected BudgetCalculationService getCalculationService() {
        return KraServiceLocator.getService(BudgetCalculationService.class);
    }
}
