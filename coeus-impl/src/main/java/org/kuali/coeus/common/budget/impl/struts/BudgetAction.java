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
package org.kuali.coeus.common.budget.impl.struts;

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
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonServiceFactory;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryTypeValuesFinder;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.lock.BudgetLockService;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.propdev.impl.budget.subaward.PropDevBudgetSubAwardService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
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
    public static final String BUDGET_SUMMARY_TOTALS_ACTION = "BudgetSummaryTotalsAction";
    public static final String BUDGET_PERSON = "budgetPerson";
    public static final String SAVE = "save";
    public static final String BUDGET_ACTIONS = "budgetActions";
    public static final String RATE_CLASS = "rateClass";
    public static final String BUDGET_PERIODS = "budgetPeriods";
    public static final String TRUE = "TRUE";
    public static final String PROPOSAL_HIERARCHY_SUB_PROJECT_INDIRECT_COST_ELEMENT = "proposalHierarchySubProjectIndirectCostElement";
    public static final String SUMMARY_TOTALS = "summaryTotals";
    public static final String ROUTE = "route";
    public static final String SYNC_QUESTION_ASKED = "syncQuestionAsked";

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        if (KewApiConstants.INITIATE_COMMAND.equals(budgetForm.getCommand())) {
            budgetForm.getBudgetDocument().initialize();
        }else{
            budgetForm.initialize();
        }
        AwardBudgetDocument awardBudgetDocument = budgetForm.getBudgetDocument();

        Budget budget = awardBudgetDocument.getBudget();
        copyLineItemToPersonnelDetails(awardBudgetDocument);
        if (budget.getActivityTypeCode().equals("x")) {
            budget.setActivityTypeCode(KcServiceLocator.getService(BudgetService.class).getActivityTypeForBudget(budget));
        }

        if(budget.getOhRateClassCode()!=null && (KNSGlobalVariables.getKualiForm())!=null){
            ((BudgetForm)KNSGlobalVariables.getKualiForm()).setOhRateClassCodePrevValue(budget.getOhRateClassCode());
        }        
        if(budget.getUrRateClassCode()!=null && (KNSGlobalVariables.getKualiForm())!=null){
            ((BudgetForm)KNSGlobalVariables.getKualiForm()).setUrRateClassCodePrevValue(budget.getUrRateClassCode());
        }
        
        budget.setStartDate(budget.getBudgetParent().getRequestedStartDateInitial());
        budget.setEndDate(budget.getBudgetParent().getRequestedEndDateInitial());
        
        if (isAwardBudget(budget) && StringUtils.isNotBlank(budgetForm.getSyncBudgetRate()) && budgetForm.getSyncBudgetRate().equals("Y")) {
            getBudgetRatesService().syncParentDocumentRates(budget);
            getBudgetCommonService(budget.getBudgetParent()).recalculateBudget(budget);
        }
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
        return synchAwardBudgetRate(form, true);
    }

    public ActionForward noSynchAwardRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return synchAwardBudgetRate(form, false);
    }

    private ActionForward synchAwardBudgetRate(ActionForm form, boolean confirm) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        AwardBudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        String routeHeaderId = budgetDoc.getDocumentHeader().getWorkflowDocument().getDocumentId();
        String forward = buildForwardUrl(routeHeaderId);

        if (confirm) {
            forward = forward.replace(Constants.AWARD_BUDGET_VERSIONS_ACTION, Constants.AWARD_BUDGET_PARAMETERS_ACTION + "syncBudgetRate=Y&");
         } else {
            forward = forward.replace(Constants.AWARD_BUDGET_VERSIONS_ACTION, Constants.AWARD_BUDGET_VERSIONS_ACTION + SYNC_QUESTION_ASKED + "=Y&");
        }

        return new ActionForward(forward, true);
    }
    
    /**
     * This method returns true if the BudgetDocument is an AwardBudgetDocument instance
     */
    protected boolean isAwardBudget(Budget budget) {
        return budget instanceof AwardBudgetExt;
    }

    
    private BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }
    public List<HeaderNavigation> getBudgetHeaderNavigatorList(){
        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        KNSDocumentEntry docEntry = (KNSDocumentEntry) dataDictionaryService.getDataDictionary().getDocumentEntry(Budget.class.getName());
        return docEntry.getHeaderNavigationList();
      }
    
    /**
     * Need to suppress buttons here when 'Totals' tab is clicked.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = null;
        final BudgetForm budgetForm = (BudgetForm) form;


        if(budgetForm != null) {
        if(budgetForm.getMethodToCall().equals(Constants.MAPPING_CLOSE)){
            setupDocumentExit();
        }
        
        actionForward = super.execute(mapping, budgetForm, request, response);    
        
        if (actionForward != null) {
            if (SUMMARY_TOTALS.equals(actionForward.getName())) {
                budgetForm.suppressButtonsForTotalPage();
            }               
        }
        // check if audit rule check is done from PD
        if (budgetForm.isAuditActivated() && !ROUTE.equals(((KualiForm) form).getMethodToCall())) {
            KcServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(budgetForm.getBudgetDocument()));
        }
        } else {
          setupDocumentExit();
        }
        
        return actionForward; 
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        final AwardBudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        
        //take care of OJB caching issues when in the awardbudgetdocument.
        budgetForm.getDocument().prepareForSave();
        
        Budget budget = budgetDoc.getBudget();
        if (budgetForm.getActionName().equals(BUDGET_SUMMARY_TOTALS_ACTION)) {
        	getBudgetCommonService(budget.getBudgetParent()).calculateBudgetOnSave(budget);
        }else{
        	getBudgetCalculationService().calculateBudget(budget);
        }
        ActionForward forward = super.save(mapping, form, request, response);


        if (budgetForm.getMethodToCall().equals(SAVE) && budgetForm.isAuditActivated()) {
            forward = mapping.findForward(BUDGET_ACTIONS);
        }

        return forward;
    }

    private BudgetCalculationService getBudgetCalculationService() {
		return KcServiceLocator.getService(AwardBudgetCalculationService.class);
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
    
    protected void updateBudgetAttributes(ActionForm form, HttpServletRequest request) {
        final BudgetForm budgetForm = (BudgetForm) form;
        AwardBudgetDocument awardBudgetDocument = budgetForm.getBudgetDocument();
        populateBudgetPrintForms(awardBudgetDocument.getBudget());
    }
    
    public ActionForward versions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.BUDGET_VERSIONS_PAGE);
    }

    public ActionForward parameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        AwardBudgetDocument awardBudgetDocument = ((BudgetForm)form).getBudgetDocument();

        getBudgetSummaryService().setupOldStartEndDate(awardBudgetDocument.getBudget(),false);
        return mapping.findForward(Constants.BUDGET_PERIOD_PAGE);
    }

    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        populatePersonnelCategoryTypeCodes(budgetForm);
        if (budgetForm.getBudgetDocument().getBudget().getBudgetPersons().isEmpty()) {
            KcServiceLocator.getService(BudgetPersonService.class).synchBudgetPersonsToProposal(budgetForm.getBudgetDocument().getBudget());
        }
        reconcilePersonnelRoles(budget);
        for(BudgetPeriod period : budget.getBudgetPeriods()) {
            for(BudgetLineItem lineItem : period.getBudgetLineItems()) {
                for(BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
                    budgetPersonnelDetails.refreshReferenceObject(BUDGET_PERSON);
                    ObjectUtils.materializeObjects(budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts());
                    for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                        if(budgetPersonnelCalculatedAmount.getRateClass() == null) {
                            budgetPersonnelCalculatedAmount.refreshReferenceObject(RATE_CLASS);
                        }
                    }
                }
                
                for(BudgetLineItemCalculatedAmount lineItemCalculatedAmount:lineItem.getBudgetLineItemCalculatedAmounts()){
                    if(lineItemCalculatedAmount.getRateClass() == null) {
                        lineItemCalculatedAmount.refreshReferenceObject(RATE_CLASS);
                    }
                }
            }
        }
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        String enableBudgetSalaryByPeriod = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, BudgetConstants.ENABLE_BUDGET_CALCULATED_SALARY);
        budgetForm.setEnableBudgetSalaryByPeriod(enableBudgetSalaryByPeriod);
        return mapping.findForward(Constants.BUDGET_PERSONNEL_PAGE);
    }

    private String getPersonnelBudgetCategoryTypeCode() {
        return this.getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_CATEGORY_TYPE_PERSONNEL);
    }
    
    protected void populatePersonnelCategoryTypeCodes(BudgetForm budgetForm) {
        Budget budget = budgetForm.getBudget();
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyValue> budgetCategoryTypes = new ArrayList<>();
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyValue budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
            String budgetCategoryTypeCode = budgetCategoryType.getKey();
            if(StringUtils.isNotBlank(budgetCategoryTypeCode) && StringUtils.equalsIgnoreCase(budgetCategoryTypeCode, personnelBudgetCategoryTypeCode)) {
                budgetCategoryTypes.add(budgetCategoryType);
                BudgetLineItem newBudgetLineItem = budget.getNewBudgetLineItem();
                if (budgetForm.getNewBudgetLineItems() == null) {
                    budgetForm.setNewBudgetLineItems(new ArrayList<>());
                }
                budgetForm.getNewBudgetLineItems().add(newBudgetLineItem);
            }
        }
        budget.setBudgetCategoryTypeCodes(budgetCategoryTypes); 
    }

    protected void populateNonPersonnelCategoryTypeCodes(BudgetForm budgetForm) {
        Budget budget = budgetForm.getBudget();
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyValue> budgetCategoryTypes = new ArrayList<>();
        String personnelBudgetCategoryTypeCode = getPersonnelBudgetCategoryTypeCode();
        
        for(KeyValue budgetCategoryType: budgetCategoryTypeValuesFinder.getKeyValues()){
            String budgetCategoryTypeCode = budgetCategoryType.getKey();
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
        
        Budget budget = budgetForm.getBudget();
        budget.refreshReferenceObject(BUDGET_PERIODS);
        
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
        budgetForm.setBudgetModularSummary(budgetModularService.processModularSummary(budgetForm.getBudgetDocument().getBudget(),false));
        return mapping.findForward(Constants.BUDGET_MODULAR_PAGE);
    }

    protected void populatePersonnelRoles(Budget budget) {
        BudgetParent budgetParent = budget.getBudgetParent().getDocument().getBudgetParent();
        
        List<BudgetPerson> budgetPersons = budget.getBudgetPersons();
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
        Budget budget = budgetForm.getBudget();
        populatePersonnelRoles(budget);
        for(BudgetPeriod period : budget.getBudgetPeriods()) {
        	
        	for(BudgetLineItem lineItem : period.getBudgetLineItems()) {
        		for(BudgetPersonnelDetails budgetPersonnelDetails : lineItem.getBudgetPersonnelDetailsList()) {
        			budgetPersonnelDetails.refreshReferenceObject(BUDGET_PERSON);
        		}
        	}
        }
        
        budget.getBudgetTotals();
        budgetForm.setProposalHierarchyIndirectObjectCode(getParameterService().getParameterValueAsString(AwardBudgetDocument.class, PROPOSAL_HIERARCHY_SUB_PROJECT_INDIRECT_COST_ELEMENT));
        return mapping.findForward(Constants.BUDGET_SUMMARY_TOTALS_PAGE);
    }
    
    public ActionForward budgetActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        populateBudgetPrintForms(budget);
        KcServiceLocator.getService(PropDevBudgetSubAwardService.class).prepareBudgetSubAwards(budget);
        return mapping.findForward(Constants.BUDGET_ACTIONS_PAGE);
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
        
        if (!StringUtils.equalsIgnoreCase((String)budgetForm.getEditingMode().get(AuthorizationConstants.EditMode.VIEW_ONLY), TRUE)) {
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
        Award award = budgetForm.getBudgetDocument().getBudget().getBudgetParent();
        
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
    
    public void reconcilePersonnelRoles(Budget budget) {
//      Populate the person's proposal roles, if they exist
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

    /**
     * 
     * Handy method to stream the byte array to response object.
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
    
    private void copyLineItemToPersonnelDetails(AwardBudgetDocument awardBudgetDocument) {
        for (BudgetPeriod budgetPeriod : awardBudgetDocument.getBudget().getBudgetPeriods()) {
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
                AwardBudgetDocument document = ((BudgetForm)form).getBudgetDocument();
                document.documentHasBeenRejected(reason);
                KcServiceLocator.getService(KcDocumentRejectionService.class).reject(document.getDocumentHeader().getWorkflowDocument(), reason,
                        GlobalVariables.getUserSession().getPrincipalId(), null);
                //tell the document it is being rejected and returned to the initial node.
                forward = super.returnToSender(request, mapping, kualiDocumentFormBase);
            }
            
        }
        return forward;
    }
    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParent budgetParent) {
        return BudgetCommonServiceFactory.createInstance(budgetParent);
    }

    protected void recalculateBudgetPeriod(BudgetForm budgetForm, Budget budget, BudgetPeriod budgetPeriod) {
        getBudgetCommonService(budget.getBudgetParent()).recalculateBudgetPeriod(budget, budgetPeriod);
    }  

    protected void calculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod) {
        getCalculationService().calculateBudgetPeriod(budget, budgetPeriod);
    }

    protected BudgetCalculationService getCalculationService() {
        return KcServiceLocator.getService(BudgetCalculationService.class);
    }
}
