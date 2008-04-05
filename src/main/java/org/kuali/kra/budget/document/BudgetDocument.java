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
package org.kuali.kra.budget.document;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.format.Formatter;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetCategoryType;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetDistributionAndIncomeComponent;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetModularIdc;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RateDecimalFormatter;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;

import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetDocument extends ResearchDocumentBase implements Copyable, SessionDocument {
    private static final String FALSE_FLAG = "N";
    private static final String TRUE_FLAG = "Y";

    private static final String DEFAULT_FISCAL_YEAR_START = "01/01/2000";

    private static final Log LOG = LogFactory.getLog(BudgetDocument.class);
    
    private String proposalNumber;
    private Integer budgetVersionNumber;
    private String comments;
    private BudgetDecimal costSharingAmount; // = new BudgetDecimal(0);
    private Date endDate;
    private boolean finalVersionFlag;
    private String modularBudgetFlag;
    private String ohRateClassCode;
    private String ohRateTypeCode;
    private BudgetDecimal residualFunds;
    private Date startDate;
    private BudgetDecimal totalCost;
    private BudgetDecimal totalCostLimit; // = new BudgetDecimal(0);
    private BudgetDecimal totalDirectCost;
    private BudgetDecimal totalIndirectCost; // = new BudgetDecimal(0);
    private BudgetDecimal underrecoveryAmount; // = new BudgetDecimal(0);
    private String urRateClassCode;
    private ProposalDevelopmentDocument proposal;
    private RateClass rateClass;
    private List<BudgetProposalRate> budgetProposalRates;
    private List<BudgetProposalLaRate> budgetProposalLaRates;
    private List<BudgetPeriod> budgetPeriods;
    private List<BudgetProjectIncome> budgetProjectIncomes;
    private List<BudgetCostShare> budgetCostShares;
    private List<BudgetUnrecoveredFandA> budgetUnrecoveredFandAs;
    
    private String activityTypeCode="1";
    private List<BudgetLineItem> budgetLineItems;
    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;
    private List<BudgetPerson> budgetPersons;
    
    private Date summaryPeriodStartDate;
    private Date summaryPeriodEndDate;    
    
    private List<InstituteRate> instituteRates;
    private List<InstituteLaRate> instituteLaRates;
    private List<RateClass> rateClasses;
    private List<RateClassType> rateClassTypes;
    private List<BudgetCategoryType> budgetCategoryTypes;
    private SortedMap <CostElement, List> objectCodeTotals ;
    private SortedMap <RateType, List> calculatedExpenseTotals ;
    private List<KeyLabelPair> budgetCategoryTypeCodes;
    
    public BudgetDocument(){
        super();
        budgetCostShares = new ArrayList<BudgetCostShare>();
        budgetProjectIncomes = new ArrayList<BudgetProjectIncome>();
        budgetProposalRates = new ArrayList<BudgetProposalRate>();
        budgetProposalLaRates = new ArrayList<BudgetProposalLaRate>();
        budgetPeriods = new ArrayList<BudgetPeriod>();
        budgetLineItems = new ArrayList<BudgetLineItem>();
        budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        budgetUnrecoveredFandAs = new ArrayList<BudgetUnrecoveredFandA>();
        instituteRates = new ArrayList<InstituteRate>();
        instituteLaRates = new ArrayList<InstituteLaRate>();
        rateClasses = new ArrayList<RateClass>();
        rateClassTypes = new ArrayList<RateClassType>();       
        budgetPersons = new ArrayList<BudgetPerson>();
        Formatter.registerFormatter(BudgetDecimal.class, BudgetDecimalFormatter.class);
        Formatter.registerFormatter(RateDecimal.class, RateDecimalFormatter.class);
        budgetCategoryTypeCodes = new ArrayList<KeyLabelPair>();
        budgetCategoryTypes = new ArrayList<BudgetCategoryType>();
    }

    public void initialize() {
    }
    
    public Integer getHackedDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        
        // search for property and get the latest number - increment for next call
        for(Iterator iter = getDocumentNextvalues().iterator(); iter.hasNext();) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)iter.next();
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }
        //TODO: need to solve the refresh issue. 
        //workaround till then
        /*****BEGIN BLOCK *****/
        if(propNextValue==1){
            BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String, String> pkMap = new HashMap<String,String>();
            pkMap.put("documentKey", getProposalNumber());
            pkMap.put("propertyName", propertyName);
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)bos.findByPrimaryKey(DocumentNextvalue.class, pkMap);
            if(documentNextvalue!=null) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
                getDocumentNextvalues().add(documentNextvalue);
            }
        }
        /*****END BLOCK********/
        
        // property does not exist - set initial value and increment for next call
        if(propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            documentNextvalue.setDocumentKey(getDocumentNumber());
            getDocumentNextvalues().add(documentNextvalue);
        }
        setDocumentNextvalues(getDocumentNextvalues());
        return propNextValue;
    }
    
    
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        KraServiceLocator.getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);
    }
    
    @Override
    public void toCopy() throws WorkflowException, IllegalStateException {
        super.toCopy();
        setBudgetVersionNumber(proposal.getNextBudgetVersionNumber());
    }
    
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getComments() {
        return comments;
    }

    public List<KualiDecimal> getProjectIncomePeriodTotals() {
        Map<Integer, KualiDecimal> incomes = new TreeMap<Integer, KualiDecimal>();
        for(BudgetProjectIncome budgetProjectIncome: budgetProjectIncomes) {
            Integer budgetPeriodNumber = budgetProjectIncome.getBudgetPeriodNumber();
            KualiDecimal amount = incomes.get(budgetPeriodNumber);
            amount = (amount == null) ? budgetProjectIncome.getProjectIncome() : amount.add(budgetProjectIncome.getProjectIncome());
            
            incomes.put(budgetPeriodNumber, amount);
        }
                
        List<KualiDecimal> periodIncomeTotals = new ArrayList<KualiDecimal>(incomes.size());
        for(Integer periodNo: incomes.keySet()) {
            KualiDecimal periodIncomeTotal = incomes.get(periodNo);
            periodIncomeTotals.add(periodIncomeTotal);
        }
        
        return periodIncomeTotals;
    }
     
    public List<FiscalYearSummary> getFiscalYearCostShareTotals() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = mapBudgetPeriodsToFiscalYears();
        return calculateFiscalYearTotals(budgetPeriodFiscalYears);
    }
    
    public List<FiscalYearSummary> getFiscalYearUnrecoveredFandATotals() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = mapBudgetPeriodsToFiscalYears();
        return calculateFiscalYearTotals(budgetPeriodFiscalYears); 
    }
    
    public Boolean isCostSharingApplicable() {
        return loadCostSharingApplicability();
    }
    
    public boolean isCostSharingAvailable() {
        boolean costSharingAvailable = false;
        for(BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            costSharingAvailable = budgetPeriod.getCostSharingAmount().isPositive();
            if(costSharingAvailable) { break; }
        }
        return costSharingAvailable;
    }

    public Boolean isUnrecoveredFandAApplicable() {
        return loadUnrecoveredFandAApplicability();
    }
    
    public boolean isUnrecoveredFandAAvailable() {
        boolean unrecoveredFandAAvailable = false;
        for(BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            unrecoveredFandAAvailable = budgetPeriod.getUnderrecoveryAmount().isPositive();
            if(unrecoveredFandAAvailable) { break; }
        }
        return unrecoveredFandAAvailable;
    };
    
    public BudgetDecimal getAllocatedCostSharing() {
        BudgetDecimal costShareTotal = new BudgetDecimal(0.0);
        for(BudgetCostShare budgetCostShare: getBudgetCostShares()) {
            costShareTotal = costShareTotal.add(budgetCostShare.getShareAmount()); 
        }
        return costShareTotal;
    }
    
    public BudgetDecimal getAllocatedUnrecoveredFandA() {
        BudgetDecimal allocatedUnrecoveredFandA = BudgetDecimal.ZERO;
        for(BudgetUnrecoveredFandA unrecoveredFandA: getBudgetUnrecoveredFandAs()) {
            allocatedUnrecoveredFandA = allocatedUnrecoveredFandA.add(unrecoveredFandA.getAmount()); 
        }
        return allocatedUnrecoveredFandA;
    }
    
    public KualiDecimal getProjectIncomeTotal() {
        KualiDecimal projectIncomeTotal = new KualiDecimal(0.0);
        for(BudgetProjectIncome budgetProjectIncome: budgetProjectIncomes) {
            projectIncomeTotal = projectIncomeTotal.add(budgetProjectIncome.getProjectIncome()); 
        }
        return projectIncomeTotal;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount == null ?  new BudgetDecimal(0) : costSharingAmount;
    }

    public BudgetDecimal getUnallocatedCostSharing() {
        return getAvailableCostSharing().subtract(getAllocatedCostSharing());
    }
    
    public BudgetDecimal getUnallocatedUnrecoveredFandA() {
        return getAvailableUnrecoveredFandA().subtract(getAllocatedUnrecoveredFandA());
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    public String getModularBudgetFlag() {
        return modularBudgetFlag;
    }

    public void setModularBudgetFlag(String modularBudgetFlag) {
        this.modularBudgetFlag = modularBudgetFlag;
    }

    public String getOhRateClassCode() {
        return ohRateClassCode;
    }

    public void setOhRateClassCode(String ohRateClassCode) {
        this.ohRateClassCode = ohRateClassCode;
    }

    public String getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(String ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public BudgetDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(BudgetDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BudgetDecimal getTotalCost() {
        return totalCost == null ?  new BudgetDecimal(0) : totalCost;
    }

    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BudgetDecimal getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost == null ?  new BudgetDecimal(0) : totalDirectCost;
    }

    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ?  new BudgetDecimal(0) : totalIndirectCost;
    }

    public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public BudgetDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ?  new BudgetDecimal(0) : underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }

    public String getUrRateClassCode() {
        return urRateClassCode;
    }

    public void setUrRateClassCode(String urRateClassCode) {
        this.urRateClassCode = urRateClassCode;
    }

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public ProposalDevelopmentDocument getProposal() {
        return proposal;
    }

    public void setProposal(ProposalDevelopmentDocument proposal) {
        this.proposal = proposal;
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public List<BudgetPeriod> getBudgetPeriods() {
        /* check for new budget version - if new, generate budget periods */
        if(budgetPeriods.isEmpty() && getStartDate() != null) {
            getBudgetSummaryService().generateBudgetPeriods(budgetPeriods, getStartDate(), getEndDate());
        }
        return budgetPeriods;
    }
    /**
     * Gets the BudgetSummary attribute. 
     * @return Returns the BudgetSummary.
     */
    public BudgetSummaryService getBudgetSummaryService() {
        return getService(BudgetSummaryService.class);
    }
    
    public void setBudgetPeriods(List<BudgetPeriod> budgetPeriods) {
        this.budgetPeriods = budgetPeriods;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getBudgetPeriods());
        managedLists.add(getBudgetProjectIncomes());
        managedLists.add(getBudgetCostShares());
        managedLists.add(getBudgetUnrecoveredFandAs());
        managedLists.add(getBudgetPersons());
        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
        List<BudgetPersonnelDetails> budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts = new ArrayList<BudgetPersonnelCalculatedAmount>();
        boolean modularNotNull = false;
        
        for (BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            if (ObjectUtils.isNotNull(budgetPeriod.getBudgetModular())) {
                managedLists.add(budgetPeriod.getBudgetModular().getBudgetModularIdcs());
                modularNotNull = true;
            }
            List<BudgetLineItem> tempLIs = budgetPeriod.getBudgetLineItems();
            budgetLineItems.addAll(tempLIs);
            for (BudgetLineItem budgetLineItem : tempLIs) {
                budgetLineItemCalculatedAmounts.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                List<BudgetPersonnelDetails> tempPerList = budgetLineItem.getBudgetPersonnelDetailsList();
                budgetPersonnelDetailsList.addAll(tempPerList);
                for (BudgetPersonnelDetails budgetPersonnelDetails : tempPerList) {
                    budgetPersonnelCalculatedAmounts.addAll(budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts());
                }
            }
        }
        
        /* Code below is added to get rid of size mismatch error */
        if(!modularNotNull) {
            managedLists.add(new ArrayList<BudgetModularIdc>()); // otherwise it complains
        }
        
        managedLists.add(budgetLineItems);
//        managedLists.add(budgetLineItemCalculatedAmounts);
        managedLists.add(budgetPersonnelDetailsList);
//        managedLists.add(budgetPersonnelCalculatedAmounts);
        return managedLists;
    }

    /**
     * Gets index i from the budgetPeriods list.
     * 
     * @param index
     * @return Budget Period at index i
     */
    public BudgetPeriod getBudgetPeriod(int index) {
        while (getBudgetPeriods().size() <= index) {
            getBudgetPeriods().add(new BudgetPeriod());
        }
        return (BudgetPeriod) getBudgetPeriods().get(index);
    }

    public List<BudgetLineItem> getBudgetLineItems() {
        return budgetLineItems;
    }

    public void setBudgetLineItems(List<BudgetLineItem> budgetLineItems) {
        this.budgetLineItems = budgetLineItems;
    }

    public Date getSummaryPeriodStartDate() {
        summaryPeriodStartDate = getBudgetPeriods().get(0).getStartDate();
        if(summaryPeriodStartDate == null) {
            summaryPeriodStartDate = startDate;
        }
        return summaryPeriodStartDate;
    }

    public Date getSummaryPeriodEndDate() {
        summaryPeriodEndDate = getBudgetPeriods().get(budgetPeriods.size()-1).getEndDate();
        if(summaryPeriodEndDate == null) {
            summaryPeriodEndDate = endDate;
        }
        return summaryPeriodEndDate;
    }

    /**
     * Gets the budgetProposalRates attribute. 
     * @return Returns the budgetProposalRates.
     */
    public List<BudgetProposalRate> getBudgetProposalRates() {
        return budgetProposalRates;
    }

    /**
     * Sets the budgetProposalRates attribute value.
     * @param budgetProposalRates The budgetProposalRates to set.
     */
    public void setBudgetProposalRates(List<BudgetProposalRate> budgetProposalRates) {
        this.budgetProposalRates = budgetProposalRates;
    }

    /**
     * Gets the budgetProposalLaRates attribute. 
     * @return Returns the budgetProposalLaRates.
     */
    public List<BudgetProposalLaRate> getBudgetProposalLaRates() {
        return budgetProposalLaRates;
    }

    /**
     * Sets the budgetProposalLaRates attribute value.
     * @param budgetProposalLaRates The budgetProposalLaRates to set.
     */
    public void setBudgetProposalLaRates(List<BudgetProposalLaRate> budgetProposalLaRates) {
        this.budgetProposalLaRates = budgetProposalLaRates;
    }

    /**
     * Gets the activityTypeCode attribute. 
     * @return Returns the activityTypeCode.
     */
    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    /**
     * Sets the activityTypeCode attribute value.
     * @param activityTypeCode The activityTypeCode to set.
     */
    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public List<InstituteRate> getInstituteRates() {
        return instituteRates;
    }

    public void setInstituteRates(List<InstituteRate> instituteRates) {
        this.instituteRates = instituteRates;
    }

    /**
     * Gets the BudgetRates attribute. 
     * @return Returns the BudgetRates.
     */
    public BudgetRatesService getBudgetRatesService() {
        return getService(BudgetRatesService.class);
    }

    public List<RateClass> getRateClasses() {
        return rateClasses;
    }

    public void setRateClasses(List<RateClass> rateClasses) {
        this.rateClasses = rateClasses;
    }

    public List<RateClassType> getRateClassTypes() {
        /* check budget rates - if empty get all budget rates */
        if(rateClassTypes.isEmpty()) {
            getBudgetRatesService().getBudgetRates(this.rateClassTypes, this);
        }
        return rateClassTypes;
    }

    public void setRateClassTypes(List<RateClassType> rateClassTypes) {
        this.rateClassTypes = rateClassTypes;
    }

    public List<BudgetProjectIncome> getBudgetProjectIncomes() {
        return budgetProjectIncomes;
    }

    public void setBudgetProjectIncomes(List<BudgetProjectIncome> budgetProjectIncomes) {
        this.budgetProjectIncomes = budgetProjectIncomes;
    }

    public void add(BudgetCostShare budgetCostShare) {
        addBudgetDistributionAndIncomeComponent(getBudgetCostShares(), budgetCostShare);
    }
    
    public void add(BudgetProjectIncome budgetProjectIncome) {
        addBudgetDistributionAndIncomeComponent(getBudgetProjectIncomes(), budgetProjectIncome);        
    }
    
    public void add(BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        addBudgetDistributionAndIncomeComponent(getBudgetUnrecoveredFandAs(), budgetUnrecoveredFandA);
    }

    public List<BudgetPerson> getBudgetPersons() {
        return budgetPersons;
    }

    public void setBudgetPersons(List<BudgetPerson> budgetPersons) {
        this.budgetPersons = budgetPersons;
    }
    
    /**
     * Gets index i from the budgetPeriods list.
     * 
     * @param index
     * @return Budget Period at index i
     */
    public BudgetPerson getBudgetPerson(int index) {
        while (getBudgetPersons().size() <= index) {
            getBudgetPersons().add(new BudgetPerson());
        }
        return (BudgetPerson) getBudgetPersons().get(index);
    }
    
    public void addBudgetPerson(BudgetPerson budgetPerson) {
        getBudgetPersons().add(budgetPerson);
    }
    
    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(this.getProposal());
    }

    public void add(BudgetPeriod budgetPeriod) {
        budgetPeriod.setBudgetVersionNumber(getBudgetVersionNumber());
        getBudgetPeriods().add(budgetPeriod);        
    }

    public void remove(BudgetDistributionAndIncomeComponent budgetCostShare) {
        getBudgetCostShares().remove(budgetCostShare);
    }
    
    public void remove(BudgetProjectIncome budgetProjectIncome) {
        getBudgetProjectIncomes().remove(budgetProjectIncome);
    }
    
    public void remove(BudgetUnrecoveredFandA unrecoveredFandA) {
        getBudgetUnrecoveredFandAs().remove(unrecoveredFandA);
    }

    public final List<InstituteLaRate> getInstituteLaRates() {
        return instituteLaRates;
    }

    public final void setInstituteLaRates(List<InstituteLaRate> instituteLaRates) {
        this.instituteLaRates = instituteLaRates;
    }

    public List<BudgetCostShare> getBudgetCostShares() {
        return budgetCostShares;
    }
    
    public List<BudgetUnrecoveredFandA> getBudgetUnrecoveredFandAs() {
        return budgetUnrecoveredFandAs;
    }

    public void getBudgetTotals() {
        KraServiceLocator.getService(BudgetCalculationService.class).calculateBudgetTotals(this);
    }

    public SortedMap<CostElement, List> getObjectCodeTotals() {
        return objectCodeTotals;
    }

    public void setObjectCodeTotals(SortedMap<CostElement, List> objectCodeTotals) {
        this.objectCodeTotals = objectCodeTotals;
    }

    public SortedMap<RateType, List> getCalculatedExpenseTotals() {
        return calculatedExpenseTotals;
    }

    public void setCalculatedExpenseTotals(SortedMap<RateType, List> calculatedExpenseTotals) {
        this.calculatedExpenseTotals = calculatedExpenseTotals;
    }
    
    public BudgetDecimal getAvailableCostSharing() {
        BudgetDecimal availableCostShare = BudgetDecimal.ZERO;
        for(BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            availableCostShare = availableCostShare.add(budgetPeriod.getCostSharingAmount());
        }
        return availableCostShare;
    }
    
    public BudgetDecimal getAvailableUnrecoveredFandA() {
        BudgetDecimal availableUnrecoveredFandA = BudgetDecimal.ZERO;
        for(BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            availableUnrecoveredFandA = availableUnrecoveredFandA.add(budgetPeriod.getUnderrecoveryAmount());
        }
        return availableUnrecoveredFandA;
    }
    
    public BudgetDecimal findCostSharingForFiscalYear(Integer fiscalYear) {
        BudgetDecimal costSharing = BudgetDecimal.ZERO;
        
        List<FiscalYearSummary> costShareFiscalYears = calculateFiscalYearTotals(mapBudgetPeriodsToFiscalYears());
        for(FiscalYearSummary costShareFiscalYear: costShareFiscalYears) {
            if(costShareFiscalYear.fiscalYear == fiscalYear) {
                costSharing = costShareFiscalYear.costShare;
                break;
            }
        }
        
        return costSharing;
    }
    
    public BudgetDecimal findUnrecoveredFandAForFiscalYear(Integer fiscalYear) {
        BudgetDecimal unrecoveredFandA = BudgetDecimal.ZERO;
        
        List<FiscalYearSummary> fiscalYearSummaries = calculateFiscalYearTotals(mapBudgetPeriodsToFiscalYears());
        for(FiscalYearSummary fiscalYearSummary: fiscalYearSummaries) {
            if(fiscalYearSummary.getFiscalYear() == fiscalYear) {
                unrecoveredFandA = fiscalYearSummary.getUnrecoveredFandA();
                break;
            }
        }
        
        return unrecoveredFandA;
    }
    
    /**
     * This method loads the fiscal year start from the database. Protected to allow mocking out service call
     * @return
     */
    protected Date loadFiscalYearStart() {
        KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        return createDateFromString(kualiConfigurationService.getParameterValue("KRA-B", "D", Constants.BUDGET_CURRENT_FISCAL_YEAR));        
    }
    
    /**
     * This method loads the cost sharing applicability flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadCostSharingApplicability() {
        return getApplicabilityValue(Constants.BUDGET_COST_SHARING_APPLICABILITY_FLAG);        
    }
    
    /**
     * This method loads the unrecovered F&A applicability flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadUnrecoveredFandAApplicability() {
        return getApplicabilityValue(Constants.BUDGET_UNRECOVERED_F_AND_A_APPLICABILITY_FLAG);        
    }

    protected Date createDateFromString(String budgetFiscalYearStart) {
        if (budgetFiscalYearStart == null) {
            return null;
        }
        String[] dateParts = budgetFiscalYearStart.split("/"); // mm/dd/yyyy
        
        // using Calendar her because org.kuali.core.util.DateUtils.newdate(...) has hour value in time fields (UT?)
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Integer.valueOf(dateParts[2]), Integer.valueOf(dateParts[0]) - 1, Integer.valueOf(dateParts[1]), 0, 0, 0);
        return new Date(calendar.getTimeInMillis());
    }
    
    @SuppressWarnings("unchecked")
    private void addBudgetDistributionAndIncomeComponent(List distributionAndIncomeComponents, BudgetDistributionAndIncomeComponent distributionAndIncomeComponent) {
        if(distributionAndIncomeComponent != null) {
            distributionAndIncomeComponent.setProposalNumber(getProposalNumber());
            distributionAndIncomeComponent.setBudgetVersionNumber(getBudgetVersionNumber());            
            distributionAndIncomeComponent.setDocumentComponentId(getDocumentNextValue(BudgetCostShare.DOCUMENT_COMPONENT_ID_KEY));            
            distributionAndIncomeComponents.add(distributionAndIncomeComponent);
        } else {
            LOG.warn("Attempt to add null distributionAndIncomeComponent was ignored.");
        }
    }
    
    private List<FiscalYearSummary> calculateFiscalYearTotals(Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears) {
        List<FiscalYearSummary> fiscalYearSummaries = new ArrayList<FiscalYearSummary>();
        for(Integer fiscalYear: budgetPeriodFiscalYears.keySet()) {            
            BudgetDecimal fiscalYearCostShareAmount = BudgetDecimal.ZERO;
            BudgetDecimal fiscalYearUnrecoveredFandA = BudgetDecimal.ZERO;
            List<BudgetPeriod> budgetPeriodsInFiscalYear = budgetPeriodFiscalYears.get(fiscalYear);
            for(BudgetPeriod budgetPeriod: budgetPeriodsInFiscalYear) {
                fiscalYearCostShareAmount = fiscalYearCostShareAmount.add(budgetPeriod.getCostSharingAmount());
                fiscalYearUnrecoveredFandA = fiscalYearUnrecoveredFandA.add(budgetPeriod.getUnderrecoveryAmount());
            }
            fiscalYearSummaries.add(new FiscalYearSummary(budgetPeriodsInFiscalYear.get(0), fiscalYear, fiscalYearCostShareAmount, fiscalYearUnrecoveredFandA, findApplicableRatesForFiscalYear(fiscalYear)));
        }
        return fiscalYearSummaries;
    }

    private FiscalYearRates findApplicableRatesForFiscalYear(Integer fiscalYear) {
        String unrecoveredFandARateClassCode = getUrRateClassCode();
        if(unrecoveredFandARateClassCode == null || unrecoveredFandARateClassCode.trim().length() == 0) {
            return new FiscalYearRates(fiscalYear, RateDecimal.ZERO_RATE, RateDecimal.ZERO_RATE);
        } else {
            RateDecimal offCampusRate = findApplicableRateForRateClassCode(fiscalYear, unrecoveredFandARateClassCode, false);
            RateDecimal onCampusRate = findApplicableRateForRateClassCode(fiscalYear, unrecoveredFandARateClassCode, true);
            return new FiscalYearRates(fiscalYear, onCampusRate, offCampusRate);
        }
    }

    private RateDecimal findApplicableRateForRateClassCode(Integer fiscalYear, String unrecoveredFandARateClassCode, boolean findOnCampusRate) {
        RateDecimal applicableRate = RateDecimal.ZERO_RATE;
        for(BudgetProposalRate budgetProposalRate: getBudgetProposalRates()) {
            if(Integer.valueOf(budgetProposalRate.getFiscalYear()).equals(fiscalYear) && budgetProposalRate.getRateClassCode().equalsIgnoreCase(unrecoveredFandARateClassCode) && findOnCampusRate == budgetProposalRate.getOnOffCampusFlag()) {
                applicableRate = new RateDecimal(budgetProposalRate.getApplicableRate().bigDecimalValue());
                break;
            }
        }
        return applicableRate;
    }

    private Map<Integer, List<BudgetPeriod>> mapBudgetPeriodsToFiscalYears() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = new TreeMap<Integer, List<BudgetPeriod>>();
        
        for(BudgetPeriod budgetPeriod: getBudgetPeriods()) {
            Integer fiscalYear = budgetPeriod.calculateFiscalYear(getFiscalYearStart());
            
            List<BudgetPeriod> budgetPeriodsInFiscalYear = budgetPeriodFiscalYears.get(fiscalYear);
            if(budgetPeriodsInFiscalYear == null) {
                budgetPeriodsInFiscalYear = new ArrayList<BudgetPeriod>();
                budgetPeriodFiscalYears.put(fiscalYear, budgetPeriodsInFiscalYear);
            }
            budgetPeriodsInFiscalYear.add(budgetPeriod);
            Collections.sort(budgetPeriodsInFiscalYear, new BudgetPeriodComparator());
        }
        return budgetPeriodFiscalYears;
    }

    /**
     * This method returns the fiscalYearStart, loading it from the database if needed
     *  
     * @return
     */
    private Date getFiscalYearStart() {
        return loadFiscalYearStart();
    }
    
    private Boolean getApplicabilityValue(String parmName) {
        String parmValue;
        
        try {
            KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
            parmValue = kualiConfigurationService.getParameterValue("KRA-B", "D", parmName);
        } catch(Exception exc) {
            parmValue = FALSE_FLAG;
        }
        
        return parmValue.equalsIgnoreCase(TRUE_FLAG);
    }
    
    public class FiscalYearRates {
        private Integer fiscalYear;
        private RateDecimal onCampusApplicableRate;
        private RateDecimal offCampusApplicableRate;
        
        public FiscalYearRates(Integer fiscalYear, RateDecimal onCampusApplicableRate, RateDecimal offCampusApplicableRate) {
            this.fiscalYear = fiscalYear;
            this.onCampusApplicableRate = onCampusApplicableRate;
            this.offCampusApplicableRate = offCampusApplicableRate;
        }
        
        public Integer getFiscalYear() {
            return fiscalYear;
        }
        
        public RateDecimal getOnCampusApplicableRate() {
            return onCampusApplicableRate;
        }

        public RateDecimal getOffCampusApplicableRate() {
            return offCampusApplicableRate;
        }
    }
    
    public class FiscalYearSummary {
        private int fiscalYear;
        private BudgetPeriod assignedBudgetPeriod;
        private BudgetDecimal costShare;
        private BudgetDecimal unrecoveredFandA;
        private FiscalYearRates fiscalYearRates;
        
        public FiscalYearSummary(BudgetPeriod assignedBudgetPeriod, int fiscalYear, BudgetDecimal costShare, BudgetDecimal unrecoveredFandA,
                                    FiscalYearRates fiscalYearRates) {
            super();
            this.assignedBudgetPeriod = assignedBudgetPeriod;
            this.fiscalYear = fiscalYear;
            this.costShare = costShare;
            this.unrecoveredFandA = unrecoveredFandA;
            this.fiscalYearRates = fiscalYearRates;
        }

        public int getFiscalYear() {
            return fiscalYear;
        }

        public BudgetPeriod getAssignedBudgetPeriod() {
            return assignedBudgetPeriod;
        }

        public BudgetDecimal getCostShare() {
            return costShare;
        }        
        
        public FiscalYearRates getFiscalYearRates() {
            return fiscalYearRates;
        }
        
        public BudgetDecimal getUnrecoveredFandA() {
            return unrecoveredFandA;
        }

        
    }
    /**
     * This class compares two BudgetPeriods to determine which should be considered earlier.
     */
    private class BudgetPeriodComparator implements Comparator<BudgetPeriod> {
        private final static int FIRST_EQUALS_SECOND = 0;
        private final static int FIRST_LESS_THAN_SECOND = -1;
        
        public int compare(BudgetPeriod bp1, BudgetPeriod bp2) {
            int result = compareDates(bp1.getStartDate(), bp2.getStartDate());
            if(result == FIRST_EQUALS_SECOND) {
                result = compareDates(bp1.getEndDate(), bp2.getEndDate());
            }
            return result;
        }
        
        private int compareDates(Date d1, Date d2) {
            if(d1 != null) {
                return d1.compareTo(d2);                
            } else {
                return (d2 != null) ? FIRST_LESS_THAN_SECOND : FIRST_EQUALS_SECOND;
            }
        }
    }
    public List<BudgetCategoryType> getBudgetCategoryTypes() {
        return budgetCategoryTypes;
    }

    public void setBudgetCategoryTypes(List<BudgetCategoryType> budgetCategoryTypes) {
        this.budgetCategoryTypes = budgetCategoryTypes;
    }

    public List<KeyLabelPair> getBudgetCategoryTypeCodes() {
        return budgetCategoryTypeCodes;
    }

    public void setBudgetCategoryTypeCodes(List<KeyLabelPair> budgetCategoryTypeCodes) {
        this.budgetCategoryTypeCodes = budgetCategoryTypeCodes;
    }

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }
}
