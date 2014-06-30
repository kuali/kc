/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatusService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryTypeValuesFinder;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.lock.BudgetLockService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarcyActionHelper;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.datadictionary.KNSDocumentEntry;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.rules.rule.event.DocumentAuditEvent;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BudgetAction extends BudgetActionBase {
    private static final Log LOG = LogFactory.getLog(BudgetAction.class);
    
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    protected static final String CONFIRM_SYNCH_BUDGET_RATE = "confirmSynchBudgetRate";
    protected static final String NO_SYNCH_BUDGET_RATE = "noSynchBudgetRate";
    protected static final String CONFIRM_SYNCH_AWARD_RATES = "confirmSynchAwardRates";
    protected static final String NO_SYNCH_AWARD_RATES = "noSynchAwardRates";

    private ProposalHierarcyActionHelper hierarchyHelper;
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        if (KewApiConstants.INITIATE_COMMAND.equals(budgetForm.getCommand())) {
            budgetForm.getBudgetDocument().initialize();
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
            budget.setActivityTypeCode(KcServiceLocator.getService(BudgetService.class).getActivityTypeForBudget(budgetDocument));
        }

        if(budget.getOhRateClassCode()!=null && ((BudgetForm)KNSGlobalVariables.getKualiForm())!=null){
            ((BudgetForm)KNSGlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(budget.getOhRateClassCode());
        }        
        if(budget.getUrRateClassCode()!=null && ((BudgetForm)KNSGlobalVariables.getKualiForm())!=null){
            ((BudgetForm)KNSGlobalVariables.getKualiForm()).setUrRateClassCodePrevValue(budget.getUrRateClassCode());
        }
        
        if (isAwardBudget(budgetDocument) && StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            getBudgetRatesService().syncParentDocumentRates(budget);
            getBudgetCommonService(budget.getBudgetParent()).recalculateBudget(budget);
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
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        String routeHeaderId = budgetDoc.getDocumentHeader().getWorkflowDocument().getDocumentId();
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
        return !Boolean.parseBoolean(budgetDocument.getBudget().getBudgetParent().getDocument().getProposalBudgetFlag());
    }

    
    private BudgetRatesService<BudgetParent> getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }
    public List<HeaderNavigation> getBudgetHeaderNavigatorList(){
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KcServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        KNSDocumentEntry docEntry = (KNSDocumentEntry) dataDictionaryService.getDataDictionary().getDocumentEntry(BudgetDocument.class.getName());
        return docEntry.getHeaderNavigationList();
      }
    
    /**
     * Need to suppress buttons here when 'Totals' tab is clicked.
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
            KcServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetForm.getBudgetDocument()));
        }
        
        return actionForward; 
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        final BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        
        Budget budget = budgetDoc.getBudget();
        getBudgetCommonService(budget.getBudgetParent()).calculateBudgetOnSave(budget);
        ActionForward forward = super.save(mapping, form, request, response);
        BudgetForm savedBudgetForm = (BudgetForm) form;
        BudgetDocument savedBudgetDoc = savedBudgetForm.getBudgetDocument();
               

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        if (budgetForm.toBudgetVersionsPage()
            || "BudgetVersionsAction".equals(budgetForm.getActionName())) {
            GlobalVariables.getMessageMap().addToErrorPath(KRADConstants.DOCUMENT_PROPERTY_NAME + ".proposal");
            tdcValidator.validateGeneratingErrorsAndWarnings(budgetDoc.getBudget().getBudgetParent().getDocument());
        } else {
            tdcValidator.validateGeneratingWarnings(budgetDoc.getBudget().getBudgetParent().getDocument());
        }

        if (budgetForm.getMethodToCall().equals("save") && budgetForm.isAuditActivated()) {
            forward = mapping.findForward("budgetActions");
        }

        return forward;
    }

    protected BudgetSummaryService getBudgetSummaryService() {
        return KcServiceLocator.getService(BudgetSummaryService.class);
    }

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        final ActionForward forward = super.reload(mapping, form, request, response);
        updateBudgetAttributes(form, request);
        return forward;
    }
    
    @Override
    public ActionForward reloadWithoutWarning(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        final ActionForward forward = super.reloadWithoutWarning(mapping, form, request, response);
        updateBudgetAttributes(form, request);
        return forward;
    }
    
    @SuppressWarnings("rawtypes")
    protected void updateBudgetAttributes(ActionForm form, HttpServletRequest request) {
        final BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetParentDocument parentDocument = budgetDocument.getBudget().getBudgetParent().getDocument();

        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(parentDocument.getBudgetDocumentVersions()));
        setBudgetStatuses(budgetDocument.getBudget().getBudgetParent());

        final BudgetTDCValidator tdcValidator = new BudgetTDCValidator(request);
        tdcValidator.validateGeneratingWarnings(budgetDocument.getBudget().getBudgetParent().getDocument());

        populateBudgetPrintForms(budgetDocument.getBudget());
    }
    
    public ActionForward versions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetParentDocument parentDocument = budgetDocument.getBudget().getBudgetParent().getDocument();
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(parentDocument.getBudgetDocumentVersions()));
        setBudgetStatuses(budgetDocument.getBudget().getBudgetParent());
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
            KcServiceLocator.getService(BudgetPersonService.class).synchBudgetPersonsToProposal(budgetForm.getBudgetDocument().getBudget());
        }
        reconcilePersonnelRoles(budgetForm.getBudgetDocument());
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
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        String enableBudgetSalaryByPeriod = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.ENABLE_BUDGET_CALCULATED_SALARY);
        budgetForm.setEnableBudgetSalaryByPeriod(enableBudgetSalaryByPeriod);
        return mapping.findForward(Constants.BUDGET_PERSONNEL_PAGE);
    }
    
    protected void populatePersonnelHierarchySummary(BudgetForm budgetForm) {
        if (budgetForm.getBudgetDocument().getBudget().isProposalBudget()) {
            DevelopmentProposal parent = (DevelopmentProposal) budgetForm.getBudgetDocument().getBudget().getBudgetParent();
            String proposalNumber = parent.getProposalNumber();
            budgetForm.setHierarchyPersonnelSummaries(getHierarchyHelper().getHierarchyPersonnelSummaries(proposalNumber));
            for (HierarchyPersonnelSummary hierarchyPersonnelSummary : budgetForm.getHierarchyPersonnelSummaries()) {
                for (Budget budget : hierarchyPersonnelSummary.getHierarchyBudgets()) {
                    reconcilePersonnelRoles(budgetForm.getBudgetDocument());
                }
            }
        }
    }

    private String getPersonnelBudgetCategoryTypeCode() {
        return this.getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }
    
    protected void populatePersonnelCategoryTypeCodes(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyValue> budgetCategoryTypes = new ArrayList<KeyValue>();   
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyValue budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
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
        List<KeyValue> budgetCategoryTypes = new ArrayList<KeyValue>();      
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyValue budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
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
        
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        budget.refreshReferenceObject("budgetPeriods");       
        
        return mapping.findForward(Constants.BUDGET_EXPENSES_PAGE);
    }

    public ActionForward rates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.BUDGET_RATES_PAGE);
    }
   
    public ActionForward distributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetDistributionService budgetDistributionService = KcServiceLocator.getService(BudgetDistributionService.class);
        budgetDistributionService.initializeCollectionDefaults(((BudgetForm) form).getBudgetDocument().getBudget());
        
        return mapping.findForward(Constants.BUDGET_DIST_AND_INCOME_PAGE);
    }

    public ActionForward modularBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetModularService budgetModularService = KcServiceLocator.getService(BudgetModularService.class);
        budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budgetForm.getBudgetDocument().getBudget()));
        return mapping.findForward(Constants.BUDGET_MODULAR_PAGE);
    }

    protected void populatePersonnelRoles(BudgetDocument budgetDocument) {
        BudgetParent budgetParent = budgetDocument.getBudget().getBudgetParent().getDocument().getBudgetParent();
        
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
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
        budgetForm.setProposalHierarchyIndirectObjectCode(getParameterService().getParameterValueAsString(BudgetDocument.class, "proposalHierarchySubProjectIndirectCostElement"));
        return mapping.findForward(Constants.BUDGET_SUMMARY_TOTALS_PAGE);
    }

    public ActionForward proposalHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.PROPOSAL_HIERARCHY_PAGE);
    }
    public ActionForward hierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm)form;
        DevelopmentProposal pd = (DevelopmentProposal) budgetForm.getBudgetDocument().getBudget().getBudgetParent();
        
        budgetForm.setHierarchyProposalSummaries(getHierarchyHelper().getHierarchyProposalSummaries(pd.getProposalNumber()));
        return mapping.findForward(Constants.HIERARCHY_PAGE);
    }
    
    public ActionForward budgetActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        populateBudgetPrintForms(budget);
        KcServiceLocator.getService(PropDevBudgetSubAwardService.class).prepareBudgetSubAwards(budget);
        return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);
    }

    protected ProposalHierarcyActionHelper getHierarchyHelper() {
        if (hierarchyHelper == null) {
            hierarchyHelper = new ProposalHierarcyActionHelper();
        }
        return hierarchyHelper;
    }


    private void populateBudgetPrintForms(Budget budget) {
        if(budget.getBudgetPrintForms().isEmpty()){
            BudgetPrintService budgetPrintService = KcServiceLocator.getService(BudgetPrintService.class);
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
        
        if (forward == null || !forward.getPath().contains(KRADConstants.QUESTION_ACTION)) {
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
        
        if (forward == null || !forward.getPath().contains(KRADConstants.QUESTION_ACTION)) {
            return this.getReturnToAwardForward(budgetForm);
        }
        
        return forward;
    }
    
    private ActionForward getReturnToAwardForward(BudgetForm budgetForm) throws Exception{
        assert budgetForm != null : "the form is null";
        
        final DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        Award award = (Award) budgetForm.getBudgetDocument().getBudget().getBudgetParent();
        
        //find the newest, uncanceled award document to return to
        String docNumber = award.getAwardDocument().getDocumentNumber();
        List<VersionHistory> versions = KcServiceLocator.getService(VersionHistoryService.class).loadVersionHistory(Award.class, award.getAwardNumber());
        for (VersionHistory version : versions) {
            if (version.getSequenceOwnerSequenceNumber() > award.getSequenceNumber() &&
                    version.getStatus() != VersionStatus.CANCELED) {
                docNumber = ((Award) version.getSequenceOwner()).getAwardDocument().getDocumentNumber();
            }
        }
        final AwardDocument awardDocument = (AwardDocument) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
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
        final DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        final String docNumber = form.getBudgetDocument().getBudget().getBudgetParent().getDocument().getDocumentNumber();
        
        final ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getDocumentId());
        if(form.isAuditActivated()) {
            forwardUrl = StringUtils.replace(forwardUrl, "Proposal.do?", "Actions.do?auditActivated=true&");
        }
        forwardUrl += "&methodToCallAttribute=methodToCall.reload";
        return new ActionForward(forwardUrl, true);
    }
    
    public void reconcilePersonnelRoles(BudgetDocument budgetDocument) {
//      Populate the person's proposal roles, if they exist
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budget.getBudgetParent();
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetParent budgetParent = budgetDocument.getBudget().getBudgetParent().getDocument().getBudgetParent();
        if (budgetParent instanceof DevelopmentProposal) {
            DevelopmentProposal proposal = (DevelopmentProposal)budgetParent;
            KcServiceLocator.getService(ProposalBudgetStatusService.class).loadBudgetStatus(proposal);
        }
        if (budget.getFinalVersionFlag() != null && Boolean.TRUE.equals(budget.getFinalVersionFlag())) {
            budget.setBudgetStatus(budgetParent.getBudgetStatus());
        } else {
            String budgetStatusIncompleteCode = this.getParameterService().getParameterValueAsString(
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
        byte[] xbts = attachmentDataSource.getData();
        
        ByteArrayOutputStream baos = null;
        if(xbts!=null)
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getType(), baos, attachmentDataSource.getName());
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

    
    @Override
    protected PessimisticLockService getPessimisticLockService() {
        return KcServiceLocator.getService(BudgetLockService.class);
    }
    
    public ActionForward reject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        final String questionText = "Are you sure you want to reject this document?";
        ActionForward forward;
        if (question == null) {
            forward =  this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,
                    questionText , KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
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
                        questionText, KRADConstants.CONFIRMATION_QUESTION, methodToCall, context, reason, errorKey, errorPropertyName, 
                        errorParameter);
            } else {
                //reject the document using the service.
                BudgetDocument document = ((BudgetForm)form).getBudgetDocument();
                document.documentHasBeenRejected(reason);
                KcServiceLocator.getService(KcDocumentRejectionService.class).reject(document.getDocumentNumber(), reason,
                        GlobalVariables.getUserSession().getPrincipalId());
                //tell the document it is being rejected and returned to the initial node.
                forward = super.returnToSender(request, mapping, kualiDocumentFormBase);
            }
            
        }
        return forward;
    }
    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParent budgetParent) {
        return BudgetCommonServiceFactory.createInstance(budgetParent);
    }
    /**
     * This method is to recalculate the budget period
     * @param budgetForm
     * @param budget
     * @param budgetPeriod
     */
    protected void recalculateBudgetPeriod(BudgetForm budgetForm, Budget budget, BudgetPeriod budgetPeriod) {
        getBudgetCommonService(budget.getBudgetParent()).recalculateBudgetPeriod(budget, budgetPeriod);
    }  

    protected void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        getCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
    }
    /**
     * Locates the {@link BudgetCalculationService]
     *
     * @return {@link BudgetCalculationService} singleton instance
     */ 
    protected BudgetCalculationService getCalculationService() {
        return KcServiceLocator.getService(BudgetCalculationService.class);
    }
}
