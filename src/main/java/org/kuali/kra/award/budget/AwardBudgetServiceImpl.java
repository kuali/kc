/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.budget;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetParent;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.AddBudgetVersionEvent;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;
import org.kuali.kra.service.DeepCopyPostProcessor;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.springframework.beans.BeanUtils;

/**
 * This class is to process all basic services required for AwardBudget
 */
public class AwardBudgetServiceImpl implements AwardBudgetService {

    private final static String BUDGET_VERSION_ERROR_PREFIX = "document.parentDocument.budgetDocumentVersion";
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private BudgetService<Award> budgetService;
    private BudgetSummaryService budgetSummaryService;
    private BudgetCalculationService budgetCalculationService;
    private AwardBudgetCalculationService awardBudgetCalculationService;
    private VersionHistoryService versionHistoryService;

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#post(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
    public void post(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
        saveDocument(awardBudgetDocument);
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#post(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
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

    /**
     * This method...
     * @param awardBudgetDocument
     */
    protected void saveDocument(AwardBudgetDocument awardBudgetDocument) {
        try {
            getDocumentService().saveDocument(awardBudgetDocument);
        }catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#processApproval(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
    public void processApproval(AwardBudgetDocument awardBudgetDocument) {
        KualiWorkflowDocument workFlowDocument = getWorkflowDocument(awardBudgetDocument);
        if(workFlowDocument.stateIsFinal()){
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        }
        saveDocument(awardBudgetDocument);
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#processDisapproval(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
    public void processDisapproval(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_REJECTED);
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#processSubmision(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
    public void processSubmision(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_SUBMITTED);
    }
    
    protected void processStatusChange(AwardBudgetDocument awardBudgetDocument,String routingStatus){
        KualiWorkflowDocument workflowDocument = getWorkflowDocument(awardBudgetDocument);
        String submittedStatusCode = getParameterValue(routingStatus);
        String submittedStatus = findStatusDescription(submittedStatusCode);
        awardBudgetDocument.getAwardBudget().setAwardBudgetStatusCode(submittedStatusCode);
        workflowDocument.getRouteHeader().setAppDocStatus(submittedStatus);
    }

    protected String getParameterValue(String awardBudgetParameter) {
        return  getParameterService().getParameterValue(AwardBudgetDocument.class, awardBudgetParameter);
    }


    protected String findStatusDescription(String statusCode) {
        AwardBudgetStatus budgetStatus = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetStatus.class, statusCode);
        return budgetStatus.getDescription();
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#rebudget(org.kuali.kra.award.budget.document.AwardBudgetDocument)
     */
    public AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException{
        AwardBudgetDocument rebudgetDocument = createNewBudgetDocument(documentDescription, awardDocument, true);
        return rebudgetDocument;
    }

    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected KualiWorkflowDocument getWorkflowDocument(Document doc) {
        KualiWorkflowDocument workflowDocument = null;
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
    public void setDocumentService(DocumentService documentservice) {
        this.documentService = documentservice;
    }


    /**
     * 
     * @see org.kuali.kra.budget.core.BudgetCommonService#getNewBudgetVersion(org.kuali.kra.budget.document.BudgetParentDocument, java.lang.String)
     */
    public BudgetDocument<Award> getNewBudgetVersion(BudgetParentDocument<Award> parentBudgetDocument, String documentDescription)
    throws WorkflowException {
        
        if (checkForOutstandingBudgets(parentBudgetDocument)) {
            return null;
        }
        
        AwardDocument parentDocument = (AwardDocument)parentBudgetDocument;
        AwardBudgetDocument awardBudgetDocument = createNewBudgetDocument(documentDescription, parentDocument,false);

        return awardBudgetDocument;
    }

    protected AwardBudgetDocument copyPostedBudgetVersion(AwardDocument parentDocument) {
        AwardBudgetExt previousPostedBudget = getLatestPostedBudget(parentDocument);
        return (AwardBudgetDocument)previousPostedBudget.getBudgetDocument();
    }

    /**
     * This method...
     * @param documentDescription
     * @param parentDocument
     * @return
     * @throws WorkflowException
     */
    protected AwardBudgetDocument createNewBudgetDocument(String documentDescription, AwardDocument parentDocument,boolean rebudget)
            throws WorkflowException {
        boolean success = new AwardBudgetVersionRule().processAddBudgetVersion(
                new AddBudgetVersionEvent(BUDGET_VERSION_ERROR_PREFIX,
                        parentDocument,documentDescription));
        if(!success)
            return null;        
        Integer budgetVersionNumber = parentDocument.getNextBudgetVersionNumber();
        AwardBudgetDocument awardBudgetDocument;
        if(isPostedBudgetExist(parentDocument)){
            BudgetDecimal obligatedChangeAmount = getTotalCostLimit(parentDocument);
            AwardBudgetExt previousPostedBudget = getLatestPostedBudget(parentDocument);
            BudgetDocument<Award> postedBudgetDocument = (AwardBudgetDocument)previousPostedBudget.getBudgetDocument();
            awardBudgetDocument =  (AwardBudgetDocument)copyBudgetVersion(postedBudgetDocument);
            copyObligatedAmountToLineItems(awardBudgetDocument,obligatedChangeAmount);
        }else{
            awardBudgetDocument = (AwardBudgetDocument) documentService.getNewDocument(AwardBudgetDocument.class);
        }
        
        awardBudgetDocument.setParentDocument(parentDocument);
        awardBudgetDocument.setParentDocumentKey(parentDocument.getDocumentNumber());
        awardBudgetDocument.setParentDocumentTypeCode(parentDocument.getDocumentTypeCode());
        awardBudgetDocument.getDocumentHeader().setDocumentDescription(documentDescription);
        
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setBudgetVersionNumber(budgetVersionNumber);
        awardBudget.setBudgetDocument(awardBudgetDocument);
        BudgetVersionOverview lastBudgetVersion = getLastBudgetVersion(parentDocument);
        awardBudget.setOnOffCampusFlag(lastBudgetVersion==null?Constants.DEFALUT_CAMUS_FLAG:lastBudgetVersion.getOnOffCampusFlag());
        if(awardBudgetDocument.getDocumentHeader() != null && awardBudgetDocument.getDocumentHeader().hasWorkflowDocument()){
            awardBudget.setBudgetInitiator(awardBudgetDocument.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
        }
        
        BudgetParent budgetParent = parentDocument.getBudgetParent();
        awardBudget.setStartDate(budgetParent.getRequestedStartDateInitial());
        awardBudget.setEndDate(budgetParent.getRequestedEndDateInitial());
        if(awardBudget.getOhRatesNonEditable()){
            awardBudget.setOhRateClassCode(this.parameterService.getParameterValue(AwardBudgetDocument.class, Constants.AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE));
            awardBudget.setUrRateClassCode(this.parameterService.getParameterValue(AwardBudgetDocument.class, Constants.AWARD_BUDGET_DEFAULT_UNDERRECOVERY_RATE_CLASS_CODE));
        }else{
            awardBudget.setOhRateClassCode(this.parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
            awardBudget.setUrRateClassCode(this.parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        }
        awardBudget.setOhRateTypeCode(this.parameterService.getParameterValue(BudgetDocument.class, Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        awardBudget.setModularBudgetFlag(this.parameterService.getIndicatorParameter(BudgetDocument.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        awardBudget.setBudgetStatus(this.parameterService.getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));
        // do not want the Budget adjustment doc number to be copied over to the new budget.
        // this should be null so the budget can be posted again to the financial system.
        awardBudget.setBudgetAdjustmentDocumentNumber("");
        awardBudget.setRateClassTypesReloaded(true);
        setBudgetLimits(awardBudgetDocument, parentDocument);
        if (isPostedBudgetExist(parentDocument)) {
            if (awardBudget.getTotalCostLimit().equals(BudgetDecimal.ZERO)) {
                rebudget = true;
            }else{
                Budget budget = awardBudgetDocument.getBudget();
                budget.getBudgetPeriods().clear();
            }
        }
        recalculateBudget(awardBudgetDocument.getBudget());
        saveBudgetDocument(awardBudgetDocument,rebudget);
        awardBudgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(awardBudgetDocument.getDocumentNumber());
        parentDocument.refreshBudgetDocumentVersions();

        return awardBudgetDocument;
    }
    
    public void setBudgetLimits(AwardBudgetDocument awardBudgetDocument, AwardDocument parentDocument) {
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setTotalCostLimit(getTotalCostLimit(parentDocument));
        awardBudget.setObligatedTotal(new BudgetDecimal(parentDocument.getAward().getBudgetTotalCostLimit().bigDecimalValue()));
        awardBudget.getAwardBudgetLimits().clear();
        for (AwardBudgetLimit limit : parentDocument.getAward().getAwardBudgetLimits()) {
            awardBudget.getAwardBudgetLimits().add(new AwardBudgetLimit(limit));
        }
    }
    
    protected void copyObligatedAmountToLineItems(AwardBudgetDocument awardBudgetDocument,BudgetDecimal obligatedChangeAmount) {
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
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCost(BudgetDecimal.ZERO);
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCostSharing(BudgetDecimal.ZERO);
                    }
                    awardBudgetPersonnelDetails.setObligatedAmount(
                         awardBudgetPersonnelDetails.getObligatedAmount().add(
                            awardBudgetPersonnelDetails.getSalaryRequested().add(
                                    awardBudgetPersonnelDetails.getCostSharingAmount())));
                    awardBudgetPersonnelDetails.setPercentCharged(BudgetDecimal.ZERO);
                    awardBudgetPersonnelDetails.setPercentEffort(BudgetDecimal.ZERO);
                    awardBudgetPersonnelDetails.setSalaryRequested(BudgetDecimal.ZERO);
                    awardBudgetPersonnelDetails.setCostSharingAmount(BudgetDecimal.ZERO);
                }
                List<AwardBudgetLineItemCalculatedAmountExt> calcAmounts = budgetLineItem.getBudgetCalculatedAmounts();
                for (AwardBudgetLineItemCalculatedAmountExt budgetLineItemCalculatedAmount : calcAmounts) {
                    budgetLineItemCalculatedAmount = (AwardBudgetLineItemCalculatedAmountExt)budgetLineItemCalculatedAmount;
                    budgetLineItemCalculatedAmount.setObligatedAmount(
                            budgetLineItemCalculatedAmount.getObligatedAmount().add(
                            budgetLineItemCalculatedAmount.getCalculatedCost().add(
                                    budgetLineItemCalculatedAmount.getCalculatedCostSharing())));
                    budgetLineItemCalculatedAmount.setCalculatedCost(BudgetDecimal.ZERO);
                    budgetLineItemCalculatedAmount.setCalculatedCostSharing(BudgetDecimal.ZERO);
                }
                awardBudgetLineItem.setObligatedAmount(
                        awardBudgetLineItem.getObligatedAmount().add(
                        awardBudgetLineItem.getLineItemCost().add(
                                awardBudgetLineItem.getCostSharingAmount())));
                awardBudgetLineItem.setLineItemCost(BudgetDecimal.ZERO);
                awardBudgetLineItem.setCostSharingAmount(BudgetDecimal.ZERO);
            }
            awardBudgetPeriod.setObligatedAmount(awardBudgetPeriod.getObligatedAmount().add(awardBudgetPeriod.getTotalCost()));
            awardBudgetPeriod.setTotalCost(BudgetDecimal.ZERO);
            awardBudgetPeriod.setTotalDirectCost(BudgetDecimal.ZERO);
            awardBudgetPeriod.setTotalIndirectCost(BudgetDecimal.ZERO);
            awardBudgetPeriod.setTotalCostLimit(obligatedChangeAmount);
        }
//        getBudgetSummaryService().calculateBudget(newAwardBudgetFromPosted);
    }

    protected AwardBudgetExt getLatestPostedBudget(AwardDocument awardDocument) {
        List<BudgetDocumentVersion> documentVersions = awardDocument.getBudgetDocumentVersions();
        QueryList<AwardBudgetVersionOverviewExt> awardBudgetVersionOverviews = new QueryList<AwardBudgetVersionOverviewExt>();
        for (BudgetDocumentVersion budgetDocumentVersion : documentVersions) {
            awardBudgetVersionOverviews.add((AwardBudgetVersionOverviewExt)budgetDocumentVersion.getBudgetVersionOverview());
        }
        
        Equals eqPostedStatus = new Equals("awardBudgetStatusCode",getAwardPostedStatusCode()); 
        QueryList<AwardBudgetVersionOverviewExt> postedVersions = awardBudgetVersionOverviews.filter(eqPostedStatus);
        AwardBudgetExt postedBudget = null;
        if(!postedVersions.isEmpty()){
            postedVersions.sort("budgetVersionNumber",false);
            AwardBudgetVersionOverviewExt postedVersion = postedVersions.get(0);
            try {
                AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument)documentService.getByDocumentHeaderId(postedVersion.getDocumentNumber());
                postedBudget = awardBudgetDocument.getAwardBudget();
            }
            catch (WorkflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return postedBudget;
    }

    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#getTotalCostLimit(org.kuali.kra.award.document.AwardDocument)
     */
    public BudgetDecimal getTotalCostLimit(AwardDocument awardDocument) {
        KualiDecimal obligatedTotal = awardDocument.getAward().getObligatedDistributableTotal();
        KualiDecimal costLimit = awardDocument.getAward().getTotalCostBudgetLimit(); 
        BudgetDecimal postedTotalAmount = getPostedTotalAmount(awardDocument);
        if (costLimit == null || costLimit.isGreaterEqual(obligatedTotal)) {
            return new BudgetDecimal(obligatedTotal.bigDecimalValue()).subtract(postedTotalAmount);
        } else {
            return new BudgetDecimal(costLimit.bigDecimalValue()).subtract(postedTotalAmount);
        }
    }
    
    /**
     * Gets the total posted amount from previously posted budgets
     * @param awardDocument
     * @return
     */
    protected BudgetDecimal getPostedTotalAmount(AwardDocument awardDocument) {
        List<BudgetDocumentVersion> documentVersions = awardDocument.getBudgetDocumentVersions();
        String postedStatusCode = getAwardPostedStatusCode();
        BudgetDecimal postedTotalAmount = BudgetDecimal.ZERO;
        for (BudgetDocumentVersion budgetDocumentVersion : documentVersions) {
            AwardBudgetVersionOverviewExt budget = (AwardBudgetVersionOverviewExt)budgetDocumentVersion.getBudgetVersionOverview();
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
    protected boolean isPostedBudgetExist(AwardDocument awardDocument) {
        boolean exist = false;
        List<BudgetDocumentVersion> documentVersions = awardDocument.getBudgetDocumentVersions();
        String postedStatusCode = getAwardPostedStatusCode();
        for (BudgetDocumentVersion budgetDocumentVersion : documentVersions) {
            AwardBudgetVersionOverviewExt budget = (AwardBudgetVersionOverviewExt)budgetDocumentVersion.getBudgetVersionOverview();
            if(budget.getAwardBudgetStatusCode().equals(postedStatusCode)){
                exist = true;
                break;
            }
        }
        return exist;
    }

    protected String getAwardPostedStatusCode() {
        return this.parameterService.getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }

    protected BudgetVersionOverview getLastBudgetVersion(AwardDocument award) {
        List<BudgetDocumentVersion> awardBudgetDocumentVersions = award.getBudgetDocumentVersions();
        BudgetVersionOverview budgetVersionOverview = null;
        int versionSize = awardBudgetDocumentVersions.size();
        if(versionSize>0){
            budgetVersionOverview = awardBudgetDocumentVersions.get(versionSize-1).getBudgetVersionOverview();
        }
        return budgetVersionOverview;
    }

    /**
     * This method...
     * @param budgetDocument
     * @param isProposalBudget
     * @param budget
     * @param budgetParent
     * @throws WorkflowException
     */
    protected void saveBudgetDocument(BudgetDocument<Award> budgetDocument,boolean rebudget) throws WorkflowException {
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) budgetDocument;
        Budget budget = budgetDocument.getBudget();
        AwardBudgetExt budgetExt = (AwardBudgetExt) budget;

        String awardBudgetTypeID = getParameterValue(rebudget ? KeyConstants.AWARD_BUDGET_TYPE_REBUDGET : KeyConstants.AWARD_BUDGET_TYPE_NEW);
        AwardBudgetType awardBudgetType = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetType.class, awardBudgetTypeID);
        budgetExt.setAwardBudgetTypeCode(awardBudgetType.getAwardBudgetTypeCode());
        budgetExt.setDescription(awardBudgetType.getDescription());
        budgetExt.setAwardBudgetType(awardBudgetType);
        
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS);
        saveDocument(awardBudgetDocument);
    }

    @SuppressWarnings("unchecked")
    protected void copyProposalBudgetLineItemsToAwardBudget(BudgetPeriod awardBudgetPeriod, BudgetPeriod proposalBudgetPeriod) {
        List awardBudgetLineItems = awardBudgetPeriod.getBudgetLineItems();
        List<BudgetLineItem> lineItems = proposalBudgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            String[] ignoreProperties = {"budgetId","budgetLineItemId","budgetPeriodId","submitCostSharingFlag",
                        "budgetLineItemCalculatedAmounts","budgetPersonnelDetailsList","budgetRateAndBaseList"};
            AwardBudgetLineItemExt awardBudgetLineItem = new AwardBudgetLineItemExt(); 
            BeanUtils.copyProperties(budgetLineItem, awardBudgetLineItem, ignoreProperties);
            awardBudgetLineItem.setLineItemNumber(awardBudgetPeriod.getBudget().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
            awardBudgetLineItem.setBudgetId(awardBudgetPeriod.getBudgetId());
            awardBudgetLineItem.setStartDate(awardBudgetPeriod.getStartDate());
            awardBudgetLineItem.setEndDate(awardBudgetPeriod.getEndDate());
            List<BudgetPersonnelDetails> awardBudgetPersonnelLineItems = awardBudgetLineItem.getBudgetPersonnelDetailsList();
            List<BudgetPersonnelDetails> budgetPersonnelLineItems = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelLineItems) {
                budgetPersonnelDetails.setBudgetLineItemId(budgetLineItem.getBudgetLineItemId());
                AwardBudgetPersonnelDetailsExt awardBudgetPerDetails = new AwardBudgetPersonnelDetailsExt();
                BeanUtils.copyProperties(budgetPersonnelDetails, awardBudgetPerDetails, 
                        new String[]{"budgetPersonnelLineItemId","budgetLineItemId","budgetId","submitCostSharingFlag",
                        "budgetPersonnelCalculatedAmounts","budgetPersonnelRateAndBaseList","validToApplyInRate"});
                awardBudgetPerDetails.setPersonNumber(awardBudgetPeriod.getBudget().getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
                BudgetPerson oldBudgetPerson = budgetPersonnelDetails.getBudgetPerson();
                BudgetPerson currentBudgetPerson = findMatchingPersonInBudget(awardBudgetPeriod.getBudget(), 
                		oldBudgetPerson, budgetPersonnelDetails.getJobCode());
                if (currentBudgetPerson == null) {
                	currentBudgetPerson = new BudgetPerson();
                	BeanUtils.copyProperties(oldBudgetPerson, currentBudgetPerson, new String[]{"budgetId", "personSequenceNumber"});
                	currentBudgetPerson.setBudgetId(awardBudgetPeriod.getBudgetId());
                	currentBudgetPerson.setPersonSequenceNumber(awardBudgetPeriod.getBudget().getBudgetDocument().getHackedDocumentNextValue(Constants.PERSON_SEQUENCE_NUMBER));
                	awardBudgetPeriod.getBudget().getBudgetPersons().add(currentBudgetPerson);
                }
                awardBudgetPerDetails.setBudgetPerson(currentBudgetPerson);
                awardBudgetPerDetails.setPersonSequenceNumber(currentBudgetPerson.getPersonSequenceNumber());
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

    protected DeepCopyPostProcessor getDeepCopyPostProcessor() {
        return KraServiceLocator.getService(DeepCopyPostProcessor.class);
    }

    public BudgetDocument<Award> copyBudgetVersion(BudgetDocument<Award> budgetDocument) throws WorkflowException {
        
        //clear awardbudgetlimits before copy as they should always be copied from
        //award document
        ((AwardBudgetExt) budgetDocument.getBudget()).getAwardBudgetLimits().clear();
        BudgetDocument<Award> newBudgetDocument = getBudgetService().copyBudgetVersion(budgetDocument);
        setBudgetLimits((AwardBudgetDocument) newBudgetDocument, (AwardDocument) newBudgetDocument.getParentDocument());
        return newBudgetDocument;
    }

    /**
     * Sets the budgetService attribute value.
     * @param budgetService The budgetService to set.
     */
    public void setBudgetService(BudgetService<Award> budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Gets the budgetService attribute. 
     * @return Returns the budgetService.
     */
    public BudgetService<Award> getBudgetService() {
        return budgetService;
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
        awardBudgetPeriod.getBudgetLineItems().clear();
        Iterator iter = rawValues.iterator();
        while (iter.hasNext()) {
            BudgetPeriod proposalPeriod = (BudgetPeriod)iter.next();
            copyProposalBudgetLineItemsToAwardBudget(awardBudgetPeriod, proposalPeriod);
        }
        getDocumentService().saveDocument(awardBudgetPeriod.getBudget().getBudgetDocument());        
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
    
    /**
     * @see org.kuali.kra.award.budget.AwardBudgetService#findBudgetPeriodsFromLinkedProposal(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardNumber) {
        BusinessObjectService businessObjectService = getBusinessObjectService();
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
                            DevelopmentProposal proposalDevelopmentDocument = businessObjectService.findBySinglePrimaryKey(
                                                                                    DevelopmentProposal.class, developmentProposalNumber);
                            List<BudgetDocumentVersion> budgetDocumentVersions =  
                                findObjectsWithSingleKey(BudgetDocumentVersion.class, 
                                        "parentDocumentKey", proposalDevelopmentDocument.getProposalDocument().getDocumentNumber());
                            for (BudgetDocumentVersion budgetDocumentVersion : budgetDocumentVersions) {
                                Budget budget = getBusinessObjectService().findBySinglePrimaryKey(ProposalDevelopmentBudgetExt.class, 
                                                                                budgetDocumentVersion.getBudgetVersionOverview().getBudgetId());
                                if (budget.isFinalVersionFlag()) {
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
            }
        }
        return budgetPeriods;
    }
    

    public boolean checkForOutstandingBudgets(BudgetParentDocument parentDoc) {
        boolean result = false;
        
        for (BudgetDocumentVersion budgetVersion : parentDoc.getBudgetDocumentVersions()) {
            BudgetVersionOverview version = budgetVersion.getBudgetVersionOverview();
            AwardBudgetExt awardBudget = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, version.getBudgetId());
            if (!(StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getPostedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getRejectedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getCancelledBudgetStatus()))) {
                result = true;
                GlobalVariables.getMessageMap().putError(BUDGET_VERSION_ERROR_PREFIX, 
                        KeyConstants.ERROR_AWARD_UNFINALIZED_BUDGET_EXISTS, awardBudget.getDocumentDescription());
            }
        }
        
        return result;
    }
    
    /**
     * 
     * @see org.kuali.kra.award.budget.AwardBudgetService#getInactiveBudgetStatus()
     */
    public List<String> getInactiveBudgetStatus() {
        List<String> result = new ArrayList<String>();
        result.add(getRejectedBudgetStatus());
        result.add(getCancelledBudgetStatus());
        result.add(getDoNotPostBudgetStatus());
        result.add(getDisapprovedBudgetStatus());
        return result;
    }
    
    /**
     * 
     * @see org.kuali.kra.award.budget.AwardBudgetService#populateBudgetLimitSummary(org.kuali.kra.award.budget.BudgetLimitSummaryHelper, org.kuali.kra.award.document.AwardDocument)
     */
    public void populateBudgetLimitSummary(BudgetLimitSummaryHelper summary, AwardDocument awardDocument) {
        
        AwardBudgetExt currentBudget = getCurrentBudget(awardDocument);
        if (summary.getCurrentBudget() == null 
                || !ObjectUtils.equals(summary.getCurrentBudget(), currentBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(currentBudget, false);
            summary.setCurrentBudget(currentBudget);
        }
        AwardBudgetExt prevBudget = getPreviousBudget(awardDocument);
        if (summary.getPreviousBudget() == null
                || !ObjectUtils.equals(summary.getPreviousBudget(), prevBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(prevBudget, true);
            summary.setPreviousBudget(prevBudget);
        }
    }

    /**
     * Returns the current budget for the award. Must be inprogress, submitted or to be posted
     * to be the current budget.
     * @param awardDocument
     * @return
     */
    protected AwardBudgetExt getCurrentBudget(AwardDocument awardDocument) {
        return getNewestBudgetByStatus(awardDocument, 
                Arrays.asList(new String[]{Constants.BUDGET_STATUS_CODE_IN_PROGRESS, Constants.BUDGET_STATUS_CODE_SUBMITTED, Constants.BUDGET_STATUS_CODE_TO_BE_POSTED}));
    }
    
    /**
     * Returns the previous budget for this award document which will be the newest posted budget
     * @param awardDocument
     * @return
     */
    protected AwardBudgetExt getPreviousBudget(AwardDocument awardDocument) {
        return getNewestBudgetByStatus(awardDocument, Arrays.asList(new String[]{getPostedBudgetStatus()}));
    }         
    
    protected AwardBudgetExt getNewestBudgetByStatus(AwardDocument awardDocument, List<String> statuses) { 
        AwardBudgetVersionOverviewExt budgetVersion = null;
        for (BudgetDocumentVersion version : awardDocument.getBudgetDocumentVersions()) {
            AwardBudgetVersionOverviewExt curVersion = (AwardBudgetVersionOverviewExt) version.getBudgetVersionOverview();
            if (statuses.contains(curVersion.getAwardBudgetStatusCode())) {
                if (budgetVersion == null || curVersion.getBudgetVersionNumber() > budgetVersion.getBudgetVersionNumber()) {
                    budgetVersion = curVersion;
                }
            }
        }
        AwardBudgetExt result = null;
        if (budgetVersion != null) {
            result = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, budgetVersion.getBudgetId());
        }
        if (result == null) {
            result = new AwardBudgetExt();
        }
        return result;        
    }
    
    public List<BudgetDocumentVersion> getAllBudgetsForAward(AwardDocument awardDocument) {
        HashSet<BudgetDocumentVersion> result = new HashSet<BudgetDocumentVersion>();
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, awardDocument.getAward().getAwardNumber());
        for (VersionHistory version : versions) {
            if (version.getSequenceOwnerSequenceNumber() <= awardDocument.getAward().getSequenceNumber() && !(version.getSequenceOwner() == null) && !(((Award) version.getSequenceOwner()).getAwardDocument() == null)) {
                result.addAll(((Award) version.getSequenceOwner()).getAwardDocument().getActualBudgetDocumentVersions());
            }
        }
        List<BudgetDocumentVersion> listResult = new ArrayList<BudgetDocumentVersion>(result);
        Collections.sort(listResult);
        return listResult;
    }
    
    public Award getActiveOrNewestAward(String awardNumber) {
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, awardNumber);
        VersionHistory newest = null;
        for (VersionHistory version: versions) {
            if (version.getStatus() == VersionStatus.ACTIVE) {
                newest = version;
                break;
            } else if (newest == null || (version.getStatus() != VersionStatus.CANCELED && version.getSequenceOwnerSequenceNumber() > newest.getSequenceOwnerSequenceNumber())) {
                newest = version;
            }  
        }
        if (newest != null) {
            return (Award) newest.getSequenceOwner();
        } else {
            return null;
        }
        
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

    /**
     * This method...
     * @param budgetPeriod
     * @param budget
     * @return
     */
    private BudgetDecimal getPeriodFringeTotal(BudgetPeriod budgetPeriod, Budget budget) {
        if(budget.getBudgetSummaryTotals()==null || budget.getBudgetSummaryTotals().get("personnelFringeTotals")==null) return BudgetDecimal.ZERO;
        BudgetDecimal periodFringeTotal = budget.getBudgetSummaryTotals().get("personnelFringeTotals").get(budgetPeriod.getBudgetPeriod()-1);
        return periodFringeTotal;
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
            BudgetDecimal periodFringeTotal = getPeriodFringeTotal(budgetPeriod, budget);
            if(!periodFringeTotal.equals(BudgetDecimal.ZERO) || !budgetPeriod.getTotalFringeAmount().equals(BudgetDecimal.ZERO)){
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
            Map<String,List<BudgetDecimal>> objectCodePersonnelFringe = budget.getObjectCodePersonnelFringeTotals();
            if(objectCodePersonnelFringe!=null){
                Iterator<String> objectCodes = objectCodePersonnelFringe.keySet().iterator();
                while (objectCodes.hasNext()) {
                    String costElement =  objectCodes.next();
                    String[] costElementAndPersonId = costElement.split(",");

                    List<BudgetDecimal> fringeTotals = objectCodePersonnelFringe.get(costElement);;
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
     * This method returns the query list after filtering all eb rates
     * @param AwardBudgetPeriodSummaryCalculatedAmounts
     * @return
     */
    private QueryList<AwardBudgetPeriodSummaryCalculatedAmount> filterEBRates(AwardBudgetPeriodExt budgetPeriod) {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> qlAwardBudgetPeriodSummaryCalculatedAmounts = 
                                                        new QueryList<AwardBudgetPeriodSummaryCalculatedAmount>(budgetPeriod.getAwardBudgetPeriodFringeAmounts());
        Equals ebClassType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = qlAwardBudgetPeriodSummaryCalculatedAmounts.filter(ebClassType);
        return ebCalculatedAmounts;
    }
    
    private AwardBudgetPeriodSummaryCalculatedAmount createNewAwardBudgetPeriodSummaryCalculatedAmount(AwardBudgetPeriodExt budgetPeriodExt,
                                            String costElement,String rateClassType,BudgetDecimal calculatedCost) {
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

    public boolean isRateOverridden(Budget budget) {
        List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            if(isRateOverridden(budgetPeriod)){
                return true;
            }
        }
        return false;
    }

    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod) {
        AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
        awardBudgetPeriod.setTotalFringeAmount(null);
        awardBudgetPeriod.getAwardBudgetPeriodFringeAmounts().clear();
        awardBudgetPeriod.getAwardBudgetPeriodFnAAmounts().clear();
        awardBudgetPeriod.setRateOverrideFlag(false);
        

    }

    /**
     * 
     * @see org.kuali.kra.budget.core.BudgetCommonService#validateAddingNewBudget(org.kuali.kra.budget.document.BudgetParentDocument)
     */
    public boolean validateAddingNewBudget(BudgetParentDocument<Award> parentDocument) {
        return !checkForOutstandingBudgets(parentDocument);
    }

}
