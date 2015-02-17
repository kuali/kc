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
package org.kuali.kra.award.budget;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.And;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionRule;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * This class is to process all basic services required for AwardBudget
 */
public class AwardBudgetServiceImpl extends AbstractBudgetService<Award> implements AwardBudgetService {
    private static final Log LOG = LogFactory.getLog(AwardBudgetServiceImpl.class);
    private final static String BUDGET_VERSION_ERROR_PREFIX = "document.parentDocument.budgetDocumentVersion";
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private BudgetSummaryService budgetSummaryService;
    private BudgetCalculationService budgetCalculationService;
    private AwardBudgetCalculationService awardBudgetCalculationService;
    private VersionHistoryService versionHistoryService;
    private AwardService awardService;
    private BudgetVersionRule addBudgetVersionRule;

    @Override
    public void post(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
        saveDocument(awardBudgetDocument);
    }
    
    /**
     * Need to move this to AwardBudgetService service
     */
    @SuppressWarnings("unchecked")
    protected AwardBudgetDocument copyBudgetVersion(AwardBudgetDocument budgetDocument, boolean onlyOnePeriod) throws WorkflowException {
        AwardDocument awardDocument = (AwardDocument)budgetDocument.getBudget().getBudgetParent().getDocument();
		String parentDocumentNumber = awardDocument.getDocumentNumber();
        budgetDocument.toCopy();
        awardDocument.getDocumentHeader().setDocumentNumber(parentDocumentNumber);
        awardDocument.setDocumentNumber(parentDocumentNumber);
        if(budgetDocument.getBudget() == null) {
            throw new RuntimeException("Not able to find any Budget Version associated with this document");
        }
        Budget budget = budgetDocument.getBudget();
        AwardBudgetExt copiedBudget = (AwardBudgetExt) copyBudgetVersion(budget,onlyOnePeriod);
        budgetDocument.getBudgets().add(copiedBudget);
        budgetDocument = (AwardBudgetDocument) documentService.saveDocument(budgetDocument);
        budgetDocument = (AwardBudgetDocument) saveBudgetDocument(budgetDocument, false);
        AwardDocument savedAwardDocument = (AwardDocument)budgetDocument.getBudget().getBudgetParent().getDocument();
        savedAwardDocument.refreshBudgetDocumentVersions();
    	return budgetDocument;
    }
    
    @Override
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
    	AwardBudgetExt copy = (AwardBudgetExt) super.copyBudgetVersion(budget, onlyOnePeriod);
    	for (BudgetPeriod period : copy.getBudgetPeriods()) {
    		AwardBudgetPeriodExt awardPeriod = (AwardBudgetPeriodExt) period;
    		awardPeriod.getAwardBudgetPeriodFnAAmounts().clear();
    		awardPeriod.getAwardBudgetPeriodFringeAmounts().clear();
    	}
    	return copy;
    }

    @Override
    public void toggleStatus(AwardBudgetDocument awardBudgetDocument) {
        String currentStatusCode = awardBudgetDocument.getAwardBudget().getAwardBudgetStatusCode();
        if (currentStatusCode.equals(getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED))) {
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST);
        }
        else if (currentStatusCode.equals(getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST))) {
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        }
        saveDocument(awardBudgetDocument);
    }

    protected void saveDocument(AwardBudgetDocument awardBudgetDocument) {
        try {
            getDocumentService().saveDocument(awardBudgetDocument);
        }catch (WorkflowException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void processApproval(AwardBudgetDocument awardBudgetDocument) {
        WorkflowDocument workFlowDocument = getWorkflowDocument(awardBudgetDocument);
        if(workFlowDocument.isFinal()){
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        }
        saveDocument(awardBudgetDocument);
    }

    @Override
    public void processDisapproval(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_REJECTED);
    }

    @Override
    public void processSubmision(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_SUBMITTED);
    }
    
    protected void processStatusChange(AwardBudgetDocument awardBudgetDocument,String routingStatus){
        WorkflowDocument workflowDocument = getWorkflowDocument(awardBudgetDocument);
        String submittedStatusCode = getParameterValue(routingStatus);
        String submittedStatus = findStatusDescription(submittedStatusCode);
        awardBudgetDocument.getAwardBudget().setAwardBudgetStatusCode(submittedStatusCode);
        workflowDocument.setApplicationDocumentStatus(submittedStatus);
    }

    protected String getParameterValue(String awardBudgetParameter) {
        return  getParameterService().getParameterValueAsString(AwardBudgetDocument.class, awardBudgetParameter);
    }


    protected String findStatusDescription(String statusCode) {
        AwardBudgetStatus budgetStatus = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetStatus.class, statusCode);
        return budgetStatus.getDescription();
    }

    @Override
    public AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException{
        AwardBudgetDocument rebudgetDocument = createNewBudgetDocument(documentDescription, awardDocument.getAward(), true);
        return rebudgetDocument;
    }

    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }

    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return parameterService;
    }

    /**
     * Sets the parameterService attribute value.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the documentService attribute. 
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * Sets the documentService attribute value.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    @Override
    public Budget getNewBudgetVersion(BudgetParentDocument parentBudgetDocument, String documentDescription, Map options){
    	AwardDocument awardDocument = (AwardDocument) parentBudgetDocument;
    	AwardBudgetDocument budgetDocument;
		try {
			budgetDocument = getNewBudgetVersionDocument(parentBudgetDocument, documentDescription, options);
		} catch (WorkflowException e) {
			throw new RuntimeException(e);
		}
		if (budgetDocument != null) {
			AwardBudgetExt budget = budgetDocument.getBudget();
			if (budget.getStartDate() != null) {
	            budget.setBudgetPeriods(getBudgetSummaryService().generateBudgetPeriods(budget));
	        }
			awardDocument.getAward().getBudgets().add(budget);
            try {
                budgetDocument = (AwardBudgetDocument) documentService.saveDocument(budgetDocument);
            } catch (WorkflowException e) {
                throw new RuntimeException(e);
            }

			return budgetDocument.getBudget();
		} else {
			return null;
		}
    }

    @Override
    public AwardBudgetDocument getNewBudgetVersionDocument(BudgetParentDocument<Award> parentBudgetDocument, String documentDescription, Map<String, Object> options)
    throws WorkflowException {
        
        AwardDocument parentDocument = (AwardDocument)parentBudgetDocument;
        if (checkForOutstandingBudgets(parentDocument.getAward())) {
            return null;
        }
        
        AwardBudgetDocument awardBudgetDocument = createNewBudgetDocument(documentDescription, parentDocument.getAward(),false);

        return awardBudgetDocument;
    }

    protected AwardBudgetDocument createNewBudgetDocument(String documentDescription, Award award,boolean rebudget)
            throws WorkflowException {
        boolean success = new AwardBudgetVersionRule().processAddBudgetVersion(
                new AddBudgetVersionEvent(BUDGET_VERSION_ERROR_PREFIX, award, documentDescription));
        if (!success) {
            return null;
        }
        Integer budgetVersionNumber = award.getNextBudgetVersionNumber();
        AwardBudgetDocument awardBudgetDocument;
        if (isPostedBudgetExist(award)) {
            ScaleTwoDecimal obligatedChangeAmount = getTotalCostLimit(award);
            AwardBudgetExt previousPostedBudget = getLatestPostedBudget(award);
            AwardBudgetDocument postedBudgetDocument = (AwardBudgetDocument) previousPostedBudget.getBudgetDocument();
            awardBudgetDocument =  (AwardBudgetDocument) copyBudgetVersion(postedBudgetDocument);
            copyObligatedAmountToLineItems(awardBudgetDocument,obligatedChangeAmount);
        } else {
            awardBudgetDocument = (AwardBudgetDocument) documentService.getNewDocument(AwardBudgetDocument.class);
        }

        awardBudgetDocument.getDocumentHeader().setDocumentDescription(documentDescription);
        
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setBudgetVersionNumber(budgetVersionNumber);
        awardBudget.setBudgetDocument(awardBudgetDocument);
        awardBudget.setAward(award);
        awardBudget.setAwardId(award.getAwardId());
        awardBudget.setName(documentDescription);
        awardBudget.setDocumentNumber(awardBudgetDocument.getDocumentNumber());
        AwardBudgetExt lastBudgetVersion = getLastBudgetVersion(award);
        awardBudget.setOnOffCampusFlag(lastBudgetVersion == null ? BudgetConstants.DEFAULT_CAMPUS_FLAG : lastBudgetVersion.getOnOffCampusFlag());
        if (awardBudgetDocument.getDocumentHeader() != null && awardBudgetDocument.getDocumentHeader().hasWorkflowDocument()) {
            awardBudget.setBudgetInitiator(awardBudgetDocument.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
        }
        
        awardBudget.setStartDate(award.getRequestedStartDateInitial());
        awardBudget.setEndDate(award.getRequestedEndDateInitial());
        if(awardBudget.getOhRatesNonEditable()){
            awardBudget.setOhRateClassCode(getAwardParameterValue(Constants.AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE));
            awardBudget.setUrRateClassCode(getAwardParameterValue( Constants.AWARD_BUDGET_DEFAULT_UNDERRECOVERY_RATE_CLASS_CODE));
        }else{
            awardBudget.setOhRateClassCode(getBudgetParameterValue(Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
            awardBudget.setUrRateClassCode(getBudgetParameterValue( Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        }
        awardBudget.setOhRateTypeCode(getBudgetParameterValue( Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        awardBudget.setModularBudgetFlag(parameterService.getParameterValueAsBoolean(Budget.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        awardBudget.setAwardBudgetStatusCode(getAwardParameterValue( KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));
        // do not want the Budget adjustment doc number to be copied over to the new budget.
        // this should be null so the budget can be posted again to the financial system.
        awardBudget.setBudgetAdjustmentDocumentNumber("");
        awardBudget.setRateClassTypesReloaded(true);
        setBudgetLimits(awardBudgetDocument, award);
        if (isPostedBudgetExist(award)) {
            if (awardBudget.getTotalCostLimit().equals(ScaleTwoDecimal.ZERO)) {
                rebudget = true;
            }
        }
        recalculateBudget(awardBudgetDocument.getBudget());
        saveBudgetDocument(awardBudgetDocument,rebudget);
        awardBudgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(awardBudgetDocument.getDocumentNumber());

        return awardBudgetDocument;
    }

    private String getBudgetParameterValue(String parameter) {
        return parameterService.getParameterValueAsString(Budget.class, parameter);
    }

    private String getAwardParameterValue(String parameter) {
        return parameterService.getParameterValueAsString(AwardBudgetDocument.class, parameter);
    }
    
    public void setBudgetLimits(AwardBudgetDocument awardBudgetDocument, Award award) {
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setTotalCostLimit(getTotalCostLimit(award));
        awardBudget.setObligatedTotal(new ScaleTwoDecimal(award.getBudgetTotalCostLimit().bigDecimalValue()));
        awardBudget.getAwardBudgetLimits().clear();
        for (AwardBudgetLimit limit : award.getAwardBudgetLimits()) {
            awardBudget.getAwardBudgetLimits().add(new AwardBudgetLimit(limit));
        }
    }
    
    protected void copyObligatedAmountToLineItems(AwardBudgetDocument awardBudgetDocument,ScaleTwoDecimal obligatedChangeAmount) {
        AwardBudgetExt newAwardBudgetFromPosted = awardBudgetDocument.getAwardBudget();
        List<BudgetPeriod> awardBudgetPeriods = newAwardBudgetFromPosted.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : awardBudgetPeriods) {
            AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
            List<BudgetLineItem> lineItems = awardBudgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : lineItems) {
                AwardBudgetLineItemExt awardBudgetLineItem = (AwardBudgetLineItemExt)budgetLineItem;
                List<BudgetPersonnelDetails> personnelDetailsList = awardBudgetLineItem.getBudgetPersonnelDetailsList();
                for (BudgetPersonnelDetails budgetPersonnelDetails : personnelDetailsList) {
                    AwardBudgetPersonnelDetailsExt awardBudgetPersonnelDetails = (AwardBudgetPersonnelDetailsExt)budgetPersonnelDetails;
                    List<AwardBudgetPersonnelCalculatedAmountExt> personnelCalcAmounts = awardBudgetPersonnelDetails.getBudgetCalculatedAmounts();
                    for (AwardBudgetPersonnelCalculatedAmountExt awardBudgetPersonnelCalculatedAmountExt : personnelCalcAmounts) {
                        awardBudgetPersonnelCalculatedAmountExt.setObligatedAmount(
                                awardBudgetPersonnelCalculatedAmountExt.getObligatedAmount().add(
                                        awardBudgetPersonnelCalculatedAmountExt.getCalculatedCost().add(
                                                awardBudgetPersonnelCalculatedAmountExt.getCalculatedCostSharing())));
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCost(ScaleTwoDecimal.ZERO);
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
                    }
                    awardBudgetPersonnelDetails.setObligatedAmount(
                         awardBudgetPersonnelDetails.getObligatedAmount().add(
                            awardBudgetPersonnelDetails.getSalaryRequested().add(
                                    awardBudgetPersonnelDetails.getCostSharingAmount())));
                    awardBudgetPersonnelDetails.setPercentCharged(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setPercentEffort(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setSalaryRequested(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setCostSharingAmount(ScaleTwoDecimal.ZERO);
                }
                List<AwardBudgetLineItemCalculatedAmountExt> calcAmounts = budgetLineItem.getBudgetCalculatedAmounts();
                for (AwardBudgetLineItemCalculatedAmountExt budgetLineItemCalculatedAmount : calcAmounts) {
                    budgetLineItemCalculatedAmount.setObligatedAmount(
                            budgetLineItemCalculatedAmount.getObligatedAmount().add(
                            budgetLineItemCalculatedAmount.getCalculatedCost().add(
                                    budgetLineItemCalculatedAmount.getCalculatedCostSharing())));
                    budgetLineItemCalculatedAmount.setCalculatedCost(ScaleTwoDecimal.ZERO);
                    budgetLineItemCalculatedAmount.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
                }
                awardBudgetLineItem.setObligatedAmount(
                        awardBudgetLineItem.getObligatedAmount().add(
                        awardBudgetLineItem.getLineItemCost().add(
                                awardBudgetLineItem.getCostSharingAmount())));
                awardBudgetLineItem.setLineItemCost(ScaleTwoDecimal.ZERO);
                awardBudgetLineItem.setCostSharingAmount(ScaleTwoDecimal.ZERO);
            }
            awardBudgetPeriod.setObligatedAmount(awardBudgetPeriod.getObligatedAmount().add(awardBudgetPeriod.getTotalCost()));
            awardBudgetPeriod.setTotalCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalDirectCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalCostLimit(obligatedChangeAmount);
        }
    }

    protected AwardBudgetExt getLatestPostedBudget(Award award) {
        QueryList<AwardBudgetExt> budgets = new QueryList<>();
        for (AwardBudgetExt budget : award.getBudgets()) {
        	budgets.add(budget);
        }
        
        Equals eqPostedStatus = new Equals("awardBudgetStatusCode",getAwardPostedStatusCode()); 
        QueryList<AwardBudgetExt> postedVersions = budgets.filter(eqPostedStatus);
        if(!postedVersions.isEmpty()){
            postedVersions.sort("budgetVersionNumber",false);
            return postedVersions.get(0);
        }
        return null;
    }

    @Override
    public ScaleTwoDecimal getTotalCostLimit(Award award) {
        ScaleTwoDecimal obligatedTotal = award.getObligatedDistributableTotal();
        ScaleTwoDecimal costLimit = award.getTotalCostBudgetLimit();
        ScaleTwoDecimal postedTotalAmount = getPostedTotalAmount(award);
        if (costLimit == null || costLimit.isGreaterEqual(obligatedTotal)) {
            return new ScaleTwoDecimal(obligatedTotal.bigDecimalValue()).subtract(postedTotalAmount);
        } else {
            return new ScaleTwoDecimal(costLimit.bigDecimalValue()).subtract(postedTotalAmount);
        }
    }
    
    /**
     * Gets the total posted amount from previously posted budgets
     */
    protected ScaleTwoDecimal getPostedTotalAmount(Award award) {
        String postedStatusCode = getAwardPostedStatusCode();
        ScaleTwoDecimal postedTotalAmount = ScaleTwoDecimal.ZERO;
        for (AwardBudgetExt budget : award.getBudgets()) {
            if(budget.getAwardBudgetStatusCode().equals(postedStatusCode)){
                postedTotalAmount = postedTotalAmount.add(budget.getTotalCost());
            }
        }
        return postedTotalAmount;
    }

    /*
     * A utility method to check whther a budget has been posted for this award, then it can be 
     * used as one of the condition to set rebudget type.
     */
    protected boolean isPostedBudgetExist(Award award) {
        boolean exist = false;
        String postedStatusCode = getAwardPostedStatusCode();
        for (AwardBudgetExt budget : award.getBudgets()) {
            if(budget.getAwardBudgetStatusCode().equals(postedStatusCode)){
                exist = true;
                break;
            }
        }
        return exist;
    }

    protected String getAwardPostedStatusCode() {
        return this.parameterService.getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }

    protected AwardBudgetExt getLastBudgetVersion(Award award) {
        List<AwardBudgetExt> awardBudgetDocumentVersions = award.getBudgets();
        AwardBudgetExt latestBudget = null;
        int versionSize = awardBudgetDocumentVersions.size();
        if(versionSize>0){
        	latestBudget = awardBudgetDocumentVersions.get(versionSize-1);
        }
        return latestBudget;
    }

    protected AwardBudgetDocument saveBudgetDocument(AwardBudgetDocument awardBudgetDocument,boolean rebudget) throws WorkflowException {
        AwardBudgetExt budgetExt = awardBudgetDocument.getAwardBudget();
//        AwardBudgetExt budgetExt = (AwardBudgetExt) budget;

        String awardBudgetTypeID = getParameterValue(rebudget ? KeyConstants.AWARD_BUDGET_TYPE_REBUDGET : KeyConstants.AWARD_BUDGET_TYPE_NEW);
        AwardBudgetType awardBudgetType = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetType.class, awardBudgetTypeID);
        budgetExt.setAwardBudgetTypeCode(awardBudgetType.getAwardBudgetTypeCode());
        budgetExt.setDescription(awardBudgetType.getDescription());
        budgetExt.setAwardBudgetType(awardBudgetType);
        
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS);
        saveDocument(awardBudgetDocument);
        return awardBudgetDocument;
    }

    @SuppressWarnings("unchecked")
    protected void copyProposalBudgetLineItemsToAwardBudget(BudgetPeriod awardBudgetPeriod, BudgetPeriod proposalBudgetPeriod) {
        List awardBudgetLineItems = awardBudgetPeriod.getBudgetLineItems();
        List<BudgetLineItem> lineItems = proposalBudgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            String[] ignoreProperties = {"budgetId","budgetLineItemId","budgetPeriodId","submitCostSharingFlag",
                        "budgetLineItemCalculatedAmounts","budgetPersonnelDetailsList","budgetRateAndBaseList", "budgetSubAward"};
            AwardBudgetLineItemExt awardBudgetLineItem = new AwardBudgetLineItemExt(); 
            BeanUtils.copyProperties(budgetLineItem, awardBudgetLineItem, ignoreProperties);
            awardBudgetLineItem.setLineItemNumber(awardBudgetPeriod.getBudget().getNextValue(Constants.BUDGET_LINEITEM_NUMBER));
            awardBudgetLineItem.setBudgetId(awardBudgetPeriod.getBudgetId());
            awardBudgetLineItem.setStartDate(awardBudgetPeriod.getStartDate());
            awardBudgetLineItem.setEndDate(awardBudgetPeriod.getEndDate());
            List<BudgetPersonnelDetails> awardBudgetPersonnelLineItems = awardBudgetLineItem.getBudgetPersonnelDetailsList();
            List<BudgetPersonnelDetails> budgetPersonnelLineItems = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelLineItems) {
            	budgetPersonnelDetails.setBudgetLineItemId(budgetLineItem.getBudgetLineItemId());
                budgetPersonnelDetails.setBudgetLineItem(budgetLineItem);
                AwardBudgetPersonnelDetailsExt awardBudgetPerDetails = new AwardBudgetPersonnelDetailsExt();
                BeanUtils.copyProperties(budgetPersonnelDetails, awardBudgetPerDetails, 
                        new String[]{"budgetPersonnelLineItemId","budgetLineItemId","budgetId","submitCostSharingFlag",
                        "budgetPersonnelCalculatedAmounts","budgetPersonnelRateAndBaseList","validToApplyInRate"});
                awardBudgetPerDetails.setPersonNumber(awardBudgetPeriod.getBudget().getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
                BudgetPerson oldBudgetPerson = budgetPersonnelDetails.getBudgetPerson();
                BudgetPerson currentBudgetPerson = findMatchingPersonInBudget(awardBudgetPeriod.getBudget(), 
                		oldBudgetPerson, budgetPersonnelDetails.getJobCode());
                if (currentBudgetPerson == null) {
                	currentBudgetPerson = new BudgetPerson();
                	BeanUtils.copyProperties(oldBudgetPerson, currentBudgetPerson, new String[]{"budgetId", "personSequenceNumber"});
                	currentBudgetPerson.setBudgetId(awardBudgetPeriod.getBudgetId());
                	currentBudgetPerson.setPersonSequenceNumber(awardBudgetPeriod.getBudget().getNextValue(Constants.PERSON_SEQUENCE_NUMBER));
                	awardBudgetPeriod.getBudget().getBudgetPersons().add(currentBudgetPerson);
                }
                awardBudgetPerDetails.setBudgetPerson(currentBudgetPerson);
                awardBudgetPerDetails.setPersonSequenceNumber(currentBudgetPerson.getPersonSequenceNumber());
                awardBudgetPerDetails.setBudget(awardBudgetPeriod.getBudget());
                awardBudgetPerDetails.setBudgetId(awardBudgetPeriod.getBudgetId());
                awardBudgetPerDetails.setCostElement(awardBudgetLineItem.getCostElement());
                awardBudgetPerDetails.setStartDate(awardBudgetLineItem.getStartDate());
                awardBudgetPerDetails.setEndDate(awardBudgetLineItem.getEndDate());
                awardBudgetPersonnelLineItems.add(awardBudgetPerDetails);
            }
            awardBudgetLineItems.add(awardBudgetLineItem);
            getAwardBudgetCalculationService().populateCalculatedAmount(awardBudgetPeriod.getBudget(), awardBudgetLineItem);
        }
    }
    
    protected BudgetPerson findMatchingPersonInBudget(Budget budget, BudgetPerson oldBudgetPerson, String jobCode) {
        for (BudgetPerson budgetPerson : budget.getBudgetPersons()) {
        	if (budgetPerson.isSamePerson(oldBudgetPerson) && StringUtils.equals(budgetPerson.getJobCode(), jobCode)) {
        		return budgetPerson;
        	}
        }
        return null;
    }

    /**
     * Copies budget version from previous one
     */
    public AwardBudgetDocument copyBudgetVersion(AwardBudgetDocument budgetDocument) throws WorkflowException {
        return copyBudgetVersion(budgetDocument, false);
    }

	@Override
	public Budget copyBudgetVersion(Budget budget) {
		return copyBudgetVersion(budget, false);
	}         

    /**
     * Gets the budgetSummaryService attribute. 
     * @return Returns the budgetSummaryService.
     */
    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    /**
     * Sets the budgetSummaryService attribute value.
     * @param budgetSummaryService The budgetSummaryService to set.
     */
    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    public void copyLineItemsFromProposalPeriods(Collection rawValues, BudgetPeriod awardBudgetPeriod) throws WorkflowException {
    	//calling awardBudgetPeriod.getBudget() will load Budget.class instead of AwardBudgetExt.class
        //this will cause classcastexceptions later as the budget with that id is technically an AwardBudgetExt
        //this is all due to an ojb bug. So here we make sure OJB caches the budget as an AwardBudgetExt correctly.
        AwardBudgetExt budget = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, awardBudgetPeriod.getBudgetId());
        awardBudgetPeriod.getBudgetLineItems().clear();
        Iterator iter = rawValues.iterator();
        while (iter.hasNext()) {
            BudgetPeriod proposalPeriod = (BudgetPeriod)iter.next();
            copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod, proposalPeriod);
        }
        getDocumentService().saveDocument(((AwardBudgetExt)awardBudgetPeriod.getBudget()).getBudgetDocument());        
        getBudgetSummaryService().calculateBudget(awardBudgetPeriod.getBudget());
    }
    
    /**
     * Use the business object service to match the criteria passed in
     * @param clazz
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List findObjectsWithSingleKey(Class clazz,String key, Object value){
        Map<String,Object> fieldValues = new HashMap<String,Object>();
        fieldValues.put(key, value);
        return (List) getBusinessObjectService().findMatching(clazz, fieldValues);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardNumber) {
        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        List<Award> awardVersions = findObjectsWithSingleKey(Award.class, "awardNumber", awardNumber);
        for (Award award : awardVersions) {
            List<AwardFundingProposal> fundingProposals = findObjectsWithSingleKey(AwardFundingProposal.class, "awardId",award.getAwardId());
            for (AwardFundingProposal fundingProposal : fundingProposals) {
                if (fundingProposal.isActive()) {
                    List<InstitutionalProposal> instProposals = 
                        findObjectsWithSingleKey(InstitutionalProposal.class, "proposalNumber", fundingProposal.getProposal().getProposalNumber());
                    for (InstitutionalProposal instProp : instProposals) {
                        List<ProposalAdminDetails> proposalAdminDetails = findObjectsWithSingleKey(ProposalAdminDetails.class, 
                                                                                        "instProposalId",instProp.getProposalId());
                        for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                            String developmentProposalNumber = proposalAdminDetail.getDevProposalNumber();
                            DevelopmentProposal proposalDevelopment = 
                            		getDataObjectService().find(DevelopmentProposal.class, developmentProposalNumber);
                            ProposalDevelopmentBudgetExt budget = proposalDevelopment.getFinalBudget();
                            //if this result set is being used by @see org.kuali.kra.lookup.BudgetPeriodLookupableHelperServiceImpl
                            //we need to populate these additional fields so always populate them.
                            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                                budgetPeriod.setInstitutionalProposalNumber(instProp.getProposalNumber());
                                budgetPeriod.setInstitutionalProposalVersion(instProp.getSequenceNumber());
                                budgetPeriods.add(budgetPeriod);
                            }
                        }
                    }
                }
            }
        }
        return budgetPeriods;
    }
    

    public boolean checkForOutstandingBudgets(Award award) {
        boolean result = false;
        
        for (AwardBudgetExt awardBudget : award.getBudgets()) {
            if (!(StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getPostedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getRejectedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getCancelledBudgetStatus()))) {
                result = true;
                GlobalVariables.getMessageMap().putError(BUDGET_VERSION_ERROR_PREFIX, 
                        KeyConstants.ERROR_AWARD_UNFINALIZED_BUDGET_EXISTS, (StringUtils.isBlank(awardBudget.getName()) ? "UNNAMED" : awardBudget.getName()));
            }
        }
        
        return result;
    }
    
    @Override
    public List<String> getInactiveBudgetStatus() {
        List<String> result = new ArrayList<String>();
        result.add(getRejectedBudgetStatus());
        result.add(getCancelledBudgetStatus());
        result.add(getDoNotPostBudgetStatus());
        result.add(getDisapprovedBudgetStatus());
        return result;
    }
    
    @Override
    public void populateBudgetLimitSummary(BudgetLimitSummaryHelper summary, Award award) {
        
        AwardBudgetExt currentBudget = getCurrentBudget(award);
        if (summary.getCurrentBudget() == null 
                || !ObjectUtils.equals(summary.getCurrentBudget(), currentBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(currentBudget, false);
            summary.setCurrentBudget(currentBudget);
        }
        AwardBudgetExt prevBudget = getPreviousBudget(award);
        if (summary.getPreviousBudget() == null
                || !ObjectUtils.equals(summary.getPreviousBudget(), prevBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(prevBudget, true);
            summary.setPreviousBudget(prevBudget);
        }
    }

    /**
     * Returns the current budget for the award. Must be inprogress, submitted or to be posted
     * to be the current budget.
     */
    protected AwardBudgetExt getCurrentBudget(Award award) {
        return getNewestBudgetByStatus(award, 
                Arrays.asList(new String[]{Constants.BUDGET_STATUS_CODE_IN_PROGRESS, Constants.BUDGET_STATUS_CODE_SUBMITTED, Constants.BUDGET_STATUS_CODE_TO_BE_POSTED}));
    }
    
    /**
     * Returns the previous budget for this award document which will be the newest posted budget
     */
    protected AwardBudgetExt getPreviousBudget(Award award) {
        return getNewestBudgetByStatus(award, Arrays.asList(new String[]{getPostedBudgetStatus()}));
    }         
    
    protected AwardBudgetExt getNewestBudgetByStatus(Award award, List<String> statuses) { 
        AwardBudgetExt result = null;
        for (AwardBudgetExt version : award.getBudgets()) {
            if (statuses.contains(version.getAwardBudgetStatusCode())) {
                if (result == null || version.getBudgetVersionNumber() > result.getBudgetVersionNumber()) {
                    result = version;
                }
            }
        }

        if (result == null) {
            result = new AwardBudgetExt();
        }
        return result;        
    }
    
    public List<AwardBudgetExt> getAllBudgetsForAward(Award award) {
        HashSet<AwardBudgetExt> result = new HashSet<>();
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, award.getAwardNumber());
        for (VersionHistory version : versions) {
            if (version.getSequenceOwnerSequenceNumber() <= award.getSequenceNumber() && !(version.getSequenceOwner() == null) && !(((Award) version.getSequenceOwner()).getAwardDocument() == null)) {
                result.addAll(((Award) version.getSequenceOwner()).getBudgets());
            }
        }
        List<AwardBudgetExt> listResult = new ArrayList<AwardBudgetExt>(result);
        Collections.sort(listResult);
        return listResult;
    }
    
    public Award getActiveOrNewestAward(String awardNumber) {
        return awardService.getActiveOrNewestAward(awardNumber);
    }
    
    protected String getPostedBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }
    
    protected String getRejectedBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_REJECTED);
    }
    
    protected String getCancelledBudgetStatus() {
        return Constants.BUDGET_STATUS_CODE_CANCELLED;    
    }
    
    protected String getDisapprovedBudgetStatus() {
        return Constants.BUDGET_STATUS_CODE_DISAPPROVED;
    }
    
    protected String getDoNotPostBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST);
    }
    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected AwardBudgetCalculationService getAwardBudgetCalculationService() {
        return awardBudgetCalculationService;
    }

    public void setAwardBudgetCalculationService(AwardBudgetCalculationService awardBudgetCalculationService) {
        this.awardBudgetCalculationService = awardBudgetCalculationService;
    }
    
    protected BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return ((AwardBudgetPeriodExt)budgetPeriod).getRateOverrideFlag();
    }

    private ScaleTwoDecimal getPeriodFringeTotal(BudgetPeriod budgetPeriod, Budget budget) {
        if (budget == null ||
                budget.getBudgetSummaryTotals() == null ||
                budget.getBudgetSummaryTotals().get("personnelFringeTotals") == null ||
                budgetPeriod == null ||
                CollectionUtils.validIndexForList(budgetPeriod.getBudgetPeriod() - 1, budget.getBudgetSummaryTotals().get("personnelFringeTotals"))) {
            return ScaleTwoDecimal.ZERO;
        }
        return budget.getBudgetSummaryTotals().get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod() - 1);
    }

    public void recalculateBudget(Budget budget) {
        List<BudgetPeriod> awardBudgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : awardBudgetPeriods) {
            removeBudgetSummaryPeriodCalcAmounts(budgetPeriod);
        }
        budgetCalculationService.calculateBudget(budget);
        budgetCalculationService.calculateBudgetSummaryTotals(budget);
    }
    public void recalculateBudgetPeriod(Budget budget,BudgetPeriod budgetPeriod) {
        removeBudgetSummaryPeriodCalcAmounts(budgetPeriod);
        budgetCalculationService.calculateBudgetPeriod(budget,budgetPeriod);
    }

    public void calculateBudgetOnSave(Budget budget) {
        budgetCalculationService.calculateBudget(budget);
        budgetCalculationService.calculateBudgetSummaryTotals(budget);
        List<BudgetPeriod> awardBudgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod awardBudgetPeriod : awardBudgetPeriods) {
            AwardBudgetPeriodExt budgetPeriod = (AwardBudgetPeriodExt)awardBudgetPeriod;
            ScaleTwoDecimal periodFringeTotal = getPeriodFringeTotal(budgetPeriod, budget);
            if(!periodFringeTotal.equals(ScaleTwoDecimal.ZERO) || !budgetPeriod.getTotalFringeAmount().equals(ScaleTwoDecimal.ZERO)){
                budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().subtract(periodFringeTotal).add(budgetPeriod.getTotalFringeAmount()));
                budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
            }
        }
        setBudgetCostsFromPeriods(budget);
    }
    
    public void populateSummaryCalcAmounts(Budget budget,BudgetPeriod budgetPeriod) {
        AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
        List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts = awardBudgetPeriod.getAwardBudgetPeriodFringeAmounts();
        awardBudgetPeriodFringeAmounts.clear();
        if(awardBudgetPeriodFringeAmounts.isEmpty()){
            Map<String,List<ScaleTwoDecimal>> objectCodePersonnelFringe = budget.getObjectCodePersonnelFringeTotals();
            if(objectCodePersonnelFringe!=null){
                Iterator<String> objectCodes = objectCodePersonnelFringe.keySet().iterator();
                while (objectCodes.hasNext()) {
                    String costElement =  objectCodes.next();
                    String[] costElementAndPersonId = costElement.split(",");

                    List<ScaleTwoDecimal> fringeTotals = objectCodePersonnelFringe.get(costElement);
                    AwardBudgetPeriodSummaryCalculatedAmount oldAwardBudgetPeriodSummaryCalculatedAmount = 
                            getSummaryCalculatedAmountFromList(awardBudgetPeriodFringeAmounts,costElementAndPersonId[0]);
                    if(oldAwardBudgetPeriodSummaryCalculatedAmount==null){
                        AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = 
                            createNewAwardBudgetPeriodSummaryCalculatedAmount(awardBudgetPeriod,costElementAndPersonId[0],RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                                                fringeTotals.get(budgetPeriod.getBudgetPeriod()-1));
                        awardBudgetPeriodFringeAmounts.add(awardBudgetPeriodSummaryCalculatedAmount);
                    }else{
                        oldAwardBudgetPeriodSummaryCalculatedAmount.setCalculatedCost(
                                oldAwardBudgetPeriodSummaryCalculatedAmount.getCalculatedCost().add(fringeTotals.get(budgetPeriod.getBudgetPeriod()-1)));
                    }
                }
            }
            QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = filterEBRates(awardBudgetPeriod);
            awardBudgetPeriod.setTotalFringeAmount(ebCalculatedAmounts.sumObjects("calculatedCost"));
        }
    }
    private AwardBudgetPeriodSummaryCalculatedAmount getSummaryCalculatedAmountFromList(List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts,
                                                                                            String costElement) {
        for (AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount : awardBudgetPeriodFringeAmounts) {
            if(awardBudgetPeriodSummaryCalculatedAmount.getCostElement().equals(costElement)){
                return awardBudgetPeriodSummaryCalculatedAmount;
            }
        }
        return null;
    }

    /**
     * This method returns the query list after filtering all eb rates.
     */
    private QueryList<AwardBudgetPeriodSummaryCalculatedAmount> filterEBRates(AwardBudgetPeriodExt budgetPeriod) {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> qlAwardBudgetPeriodSummaryCalculatedAmounts = 
                                                        new QueryList<AwardBudgetPeriodSummaryCalculatedAmount>(budgetPeriod.getAwardBudgetPeriodFringeAmounts());
        Equals ebClassType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = qlAwardBudgetPeriodSummaryCalculatedAmounts.filter(ebClassType);
        return ebCalculatedAmounts;
    }
    
    private AwardBudgetPeriodSummaryCalculatedAmount createNewAwardBudgetPeriodSummaryCalculatedAmount(AwardBudgetPeriodExt budgetPeriodExt,
                                            String costElement,String rateClassType,ScaleTwoDecimal calculatedCost) {
        AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = new AwardBudgetPeriodSummaryCalculatedAmount();
        awardBudgetPeriodSummaryCalculatedAmount.setBudgetPeriodId(budgetPeriodExt.getBudgetPeriodId());
        awardBudgetPeriodSummaryCalculatedAmount.setCalculatedCost(calculatedCost);
        awardBudgetPeriodSummaryCalculatedAmount.setCostElement(costElement);
        awardBudgetPeriodSummaryCalculatedAmount.setRateClassType(rateClassType);
        return awardBudgetPeriodSummaryCalculatedAmount;
    }
    
    
    /**
     * 
     * This method sets the budget document's costs from the budget periods' costs.
     * This method modifies the passed in budget document.
     * 
     * @param budget the budget document to set the costs on.
     */
    protected void setBudgetCostsFromPeriods(final Budget budget) {
        assert budget != null : "The document is null";

        budget.setTotalDirectCost(budget.getSumDirectCostAmountFromPeriods());
        budget.setTotalIndirectCost(budget.getSumIndirectCostAmountFromPeriods());
        budget.setTotalCost(budget.getSumTotalCostAmountFromPeriods());
        budget.setUnderrecoveryAmount(budget.getSumUnderreoveryAmountFromPeriods());
        budget.setCostSharingAmount(budget.getSumCostSharingAmountFromPeriods());
    }

    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod) {
        AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
        awardBudgetPeriod.setTotalFringeAmount(null);
        awardBudgetPeriod.getAwardBudgetPeriodFringeAmounts().clear();
        awardBudgetPeriod.getAwardBudgetPeriodFnAAmounts().clear();
        awardBudgetPeriod.setRateOverrideFlag(false);
        

    }

    @Override
    public boolean validateAddingNewBudget(BudgetParentDocument<Award> parentDocument) {
    	AwardDocument awardDocument = (AwardDocument)parentDocument;
        return !checkForOutstandingBudgets(awardDocument.getAward());
    }
    
    @Override
    public boolean checkRateChange(Collection<BudgetRate> savedBudgetRates,Award award){
        award.refreshReferenceObject("awardFandaRate");
        List<AwardFandaRate> awardFandaRates = award.getAwardFandaRate();
        boolean changeFlag = false;
        for (AwardFandaRate budgetFnARate : awardFandaRates) {
            RateType fnaRateType = budgetFnARate.getFandaRateType();
            Equals eqRateClasCode = new Equals("rateClassCode", fnaRateType.getRateClassCode());
            Equals eqRateTypeCode = new Equals("rateTypeCode", fnaRateType.getRateTypeCode());
            Equals eqCampusFlag = new Equals("onOffCampusFlag", budgetFnARate.getOnOffCampusFlag());
            And rateClassAndRateType = new And(eqRateClasCode,eqRateTypeCode);
            And rateClassAndRateTypeAndCampusFlag = new And(rateClassAndRateType,eqCampusFlag);
            QueryList<BudgetRate> matchAwardFnARate = new QueryList<BudgetRate>(savedBudgetRates).filter(rateClassAndRateTypeAndCampusFlag);
            if(matchAwardFnARate.isEmpty() || matchAwardFnARate.size()>1 ||
                    !matchAwardFnARate.get(0).getApplicableRate().equals(budgetFnARate.getApplicableFandaRate())) {
                changeFlag = true;
            }
        }
        Equals eqRateClasCode = new Equals("rateClassCode", getBudgetParameterValue(Constants.AWARD_BUDGET_EB_RATE_CLASS_CODE));
        Equals eqRateTypeCode = new Equals("rateTypeCode", getBudgetParameterValue(Constants.AWARD_BUDGET_EB_RATE_TYPE_CODE));
        And rateClassAndRateType = new And(eqRateClasCode,eqRateTypeCode);
        QueryList<BudgetRate> matchAwardEBCampusRates = new QueryList<BudgetRate>(savedBudgetRates).filter(rateClassAndRateType);
        for (BudgetRate budgetEBRate : matchAwardEBCampusRates) {
            if(budgetEBRate.getOnOffCampusFlag()) {
                if(award.getSpecialEbRateOnCampus()!=null && !award.getSpecialEbRateOnCampus().equals(budgetEBRate.getApplicableRate())){
                    changeFlag = true;
                }
            }else{
                if(award.getSpecialEbRateOffCampus()!=null && !award.getSpecialEbRateOffCampus().equals(budgetEBRate.getApplicableRate())){
                    changeFlag = true;
                }
            }
        }
        if((award.getSpecialEbRateOnCampus()!=null ||  award.getSpecialEbRateOffCampus()!=null) && matchAwardEBCampusRates.isEmpty()){
            changeFlag = true;
        }
        return changeFlag;
    }
    
    @Override
    public boolean isBudgetVersionNameValid(BudgetParent parent, String name) {
    	try {
			return getAddBudgetVersionRule().processAddBudgetVersion(new AddBudgetVersionEvent(BUDGET_VERSION_ERROR_PREFIX, parent, name));
		} catch (WorkflowException e) {
			LOG.error(e);
			return false;
		}
    }

    protected AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

	public BudgetVersionRule getAddBudgetVersionRule() {
		if (addBudgetVersionRule == null) {
			addBudgetVersionRule = new AwardBudgetVersionRule();
		}
		return addBudgetVersionRule;
	}

	public void setAddBudgetVersionRule(BudgetVersionRule addBudgetVersionRule) {
		this.addBudgetVersionRule = addBudgetVersionRule;
	}

}
