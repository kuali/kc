/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.impl.print.BudgetPrintForm;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardAttachment;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardFiles;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetNumberOfMonthsService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * This class represent Budget BO
 */
public class Budget extends BudgetVersionOverview {

    private static final String PARAM_VALUE_ENABLED = "1";


    private static final long serialVersionUID = -252470308729741085L;


    private static final String FALSE_FLAG = "N";

    private static final String TRUE_FLAG = "Y";


    private static final Log LOG = LogFactory.getLog(Budget.class);

    private BudgetDocument budgetDocument;

    private String budgetJustification;

    private RateClass rateClass;

    private List<BudgetRate> budgetRates;

    private List<BudgetLaRate> budgetLaRates;

    private List<BudgetPeriod> budgetPeriods;

    private List<BudgetProjectIncome> budgetProjectIncomes;

    private List<BudgetCostShare> budgetCostShares;

    private List<BudgetUnrecoveredFandA> budgetUnrecoveredFandAs;

    private String activityTypeCode = "x";

    private boolean budgetLineItemDeleted;

    private boolean rateClassTypesReloaded = false;
    private String budgetAdjustmentDocumentNumber;


    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;

    private List<BudgetPerson> budgetPersons;

    private Date summaryPeriodStartDate;

    private Date summaryPeriodEndDate;

    private List<InstituteRate> instituteRates;

    private List<InstituteLaRate> instituteLaRates;

    private List<RateClass> rateClasses;

    private List<RateClassType> rateClassTypes;

    private SortedMap<CostElement, List<ScaleTwoDecimal>> objectCodeTotals;

    private SortedMap<RateType, List<ScaleTwoDecimal>> calculatedExpenseTotals;

    private SortedMap<RateType, List<ScaleTwoDecimal>> personnelCalculatedExpenseTotals;

    private SortedMap<RateType, List<ScaleTwoDecimal>> nonPersonnelCalculatedExpenseTotals;

    private List<KeyValue> budgetCategoryTypeCodes;

    private SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType;

    private SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodePersonnelList;

    private SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelSalaryTotals;

    private SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelFringeTotals;

    private SortedMap<String, List<ScaleTwoDecimal>> budgetSummaryTotals;

    private List<BudgetPrintForm> budgetPrintForms;

    private List<BudgetSubAwards> budgetSubAwards;

    private boolean rateSynced;

    private transient ParameterService parameterService;

    public Budget() {
        super();
        budgetCostShares = new ArrayList<BudgetCostShare>();
        budgetProjectIncomes = new ArrayList<BudgetProjectIncome>();
        budgetRates = new ArrayList<BudgetRate>();
        budgetLaRates = new ArrayList<BudgetLaRate>();
        budgetPeriods = new ArrayList<BudgetPeriod>();
        budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        budgetUnrecoveredFandAs = new ArrayList<BudgetUnrecoveredFandA>();
        instituteRates = new ArrayList<InstituteRate>();
        instituteLaRates = new ArrayList<InstituteLaRate>();
        rateClasses = new ArrayList<RateClass>();
        rateClassTypes = new ArrayList<RateClassType>();
        budgetPersons = new ArrayList<BudgetPerson>();
        budgetCategoryTypeCodes = new ArrayList<KeyValue>();
        budgetPrintForms = new ArrayList<BudgetPrintForm>();
        budgetSubAwards = new ArrayList<BudgetSubAwards>();
        setOnOffCampusFlag("D");
    }

    public Boolean getFinalVersionFlag() {
        return isFinalVersionFlag();
    }

    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    /**
     * This method handles the database relationship between {@link BudgetPeriod BudgetPeriods}
     * and {@link BudgetProjectIncome BudgetProjectIncomes}.  This method is needed to ensure
     * that a database constraint is not violated where a budget period needs to be deleted
     * but a budget project income still exists referencing that period.  This relationship
     * is not handled by our O/R M solution; therefore, it is handled here, manually.
     */
    public void handlePeriodToProjectIncomeRelationship() {
        final Collection<Long> periodIncomesToDelete = new ArrayList<Long>();
        for (final BudgetPeriod persistedPeriod : this.getPersistedBudgetPeriods()) {
            if (!this.containsBudgetPeriod(persistedPeriod.getBudgetPeriodId())) {
                periodIncomesToDelete.add(persistedPeriod.getBudgetPeriodId());
            }
        }
        this.deletePersistedProjectIncomes(periodIncomesToDelete);
        this.deleteLocalProjectIncomes(periodIncomesToDelete);
    }

    /**
     * Checks if this budget document contains a budget period with a specific period id.
     * 
     * @param periodId the budget period id
     * @return true if it contains the budget period with the matching id.
     */
    private boolean containsBudgetPeriod(final Long periodId) {
        assert periodId != null : "the periodId is null";
        for (final BudgetPeriod localPeriod : getBudgetPeriods()) {
            if (periodId.equals(localPeriod.getBudgetPeriodId())) {
                return true;
            }
        }
        return false;
    }

    public String getBudgetAdjustmentDocumentNumber() {
        return budgetAdjustmentDocumentNumber;
    }

    public void setBudgetAdjustmentDocumentNumber(String budgetAdjustmentDocumentNumber) {
        this.budgetAdjustmentDocumentNumber = budgetAdjustmentDocumentNumber;
    }

    /**
     * Gets all persisted {@link BudgetPeriod BudgetPeriods} for the current proposal
     * as an immutable collection.
     * 
     * @return {@link BudgetPeriod BudgetPeriods} if no periods are found this method returns
     * an empty {@link Collection Collection}
     */
    private Collection<BudgetPeriod> getPersistedBudgetPeriods() {
        final BusinessObjectService service = getService(BusinessObjectService.class);
        final Map<String, Object> matchCriteria = new HashMap<String, Object>();
        matchCriteria.put("budgetId", this.getBudgetId());
        @SuppressWarnings("unchecked") final Collection<BudgetPeriod> periods = service.findMatching(BudgetPeriod.class, matchCriteria);
        return periods != null ? Collections.unmodifiableCollection(periods) : Collections.<BudgetPeriod>emptyList();
    }

    /**
     * Deletes the {@link BudgetProjectIncome BudgetProjectIncomes} matching the passed in period ids from the database.
     */
    private void deletePersistedProjectIncomes(final Collection<Long> periodIds) {
        assert periodIds != null : "the periodIds are null";
        if (periodIds.isEmpty()) {
            return;
        }
        final BusinessObjectService service = getService(BusinessObjectService.class);
        final Map<String, Collection<Long>> matchCriteria = new HashMap<String, Collection<Long>>();
        matchCriteria.put("budgetPeriodId", periodIds);
        service.deleteMatching(BudgetProjectIncome.class, matchCriteria);
    }

    /**
     * Deletes the {@link BudgetProjectIncome BudgetProjectIncomes} matching the passed in period ids from local budget document.
     */
    private void deleteLocalProjectIncomes(final Collection<Long> periodIds) {
        assert periodIds != null : "the periodIds are null";
        if (periodIds.isEmpty()) {
            return;
        }
        for (final Iterator<BudgetProjectIncome> i = this.getBudgetProjectIncomes().iterator(); i.hasNext(); ) {
            final BudgetProjectIncome budgetProjectIncome = i.next();
            if (periodIds.contains(budgetProjectIncome.getBudgetPeriodId())) {
                i.remove();
            }
        }
    }

    /**
     * This method does what its name says
     * @return List of project totals for each budget period, where budget period 1 total is stored in list's 0th element
     */
    public List<ScaleTwoDecimal> getProjectIncomePeriodTotalsForEachBudgetPeriod() {
        Map<Integer, ScaleTwoDecimal> incomes = mapProjectIncomeTotalsToBudgetPeriodNumbers();
        return findProjectIncomeTotalsForBudgetPeriods(incomes);
    }


    public List<FiscalYearSummary> getFiscalYearCostShareTotals() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = mapBudgetPeriodsToFiscalYears();
        return findCostShareTotalsForBudgetPeriods(budgetPeriodFiscalYears);
    }


    public List<FiscalYearSummary> getFiscalYearUnrecoveredFandATotals() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = mapBudgetPeriodsToFiscalYears();
        return findCostShareTotalsForBudgetPeriods(budgetPeriodFiscalYears);
    }

    /**
     * This method reveals applicability of Cost Sharing to this budget
     * @return
     */
    public Boolean isCostSharingApplicable() {
        return loadCostSharingApplicability();
    }

    /**
     * This method reveals enforcement of Cost Sharing to this budget
     * @return
     */
    public Boolean isCostSharingEnforced() {
        return loadCostSharingEnforcement();
    }

    /**
     * This method reveals availability of Cost Sharing in this budget
     * @return
     */
    public boolean isCostSharingAvailable() {
        boolean costSharingAvailable = false;
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            costSharingAvailable = budgetPeriod.getCostSharingAmount().isPositive();
            if (costSharingAvailable) {
                break;
            }
        }
        return costSharingAvailable;
    }

    public Boolean isUnrecoveredFandAEnforced() {
        return loadUnrecoveredFandAEnforcement();
    }

    public Boolean isUnrecoveredFandAApplicable() {
        return loadUnrecoveredFandAApplicability();
    }

    public boolean isUnrecoveredFandAAvailable() {
        return (getAvailableUnrecoveredFandA().doubleValue() > 0.00);
    }


    public ScaleTwoDecimal getAllocatedCostSharing() {
        ScaleTwoDecimal costShareTotal = new ScaleTwoDecimal(0.0);
        for (BudgetCostShare budgetCostShare : getBudgetCostShares()) {
            if (budgetCostShare.getShareAmount() != null) {
                costShareTotal = costShareTotal.add(budgetCostShare.getShareAmount());
            }
        }
        return costShareTotal;
    }


    public ScaleTwoDecimal getAllocatedUnrecoveredFandA() {
        ScaleTwoDecimal allocatedUnrecoveredFandA = ScaleTwoDecimal.ZERO;
        for (BudgetUnrecoveredFandA unrecoveredFandA : getBudgetUnrecoveredFandAs()) {
            if (unrecoveredFandA.getAmount() != null) {
                allocatedUnrecoveredFandA = allocatedUnrecoveredFandA.add(unrecoveredFandA.getAmount());
            }
        }
        return allocatedUnrecoveredFandA;
    }


    public ScaleTwoDecimal getProjectIncomeTotal() {
        ScaleTwoDecimal projectIncomeTotal = new ScaleTwoDecimal(0.0);
        for (BudgetProjectIncome budgetProjectIncome : budgetProjectIncomes) {
            if (budgetProjectIncome.getProjectIncome() != null) {
                projectIncomeTotal = projectIncomeTotal.add(budgetProjectIncome.getProjectIncome());
            }
        }
        return projectIncomeTotal;
    }

    public ScaleTwoDecimal getUnallocatedCostSharing() {
        return getAvailableCostSharing().subtract(getAllocatedCostSharing());
    }

    public ScaleTwoDecimal getUnallocatedUnrecoveredFandA() {
        return getAvailableUnrecoveredFandA().subtract(getAllocatedUnrecoveredFandA());
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public List<BudgetPeriod> getBudgetPeriods() {
        if (budgetPeriods.isEmpty() && getStartDate() != null) {
            getBudgetSummaryService().generateBudgetPeriods(this, budgetPeriods);
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
        managedLists.add(getBudgetProjectIncomes());
        managedLists.add(getBudgetCostShares());
        managedLists.add(getBudgetUnrecoveredFandAs());
        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
        List<BudgetFormulatedCostDetail> budgetFormulatedCosts = new ArrayList<BudgetFormulatedCostDetail>();
        List<BudgetRateAndBase> budgetRateAndBaseList = new ArrayList<BudgetRateAndBase>();
        List<BudgetPersonnelDetails> bPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts = new ArrayList<BudgetPersonnelCalculatedAmount>();
        List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList = new ArrayList<BudgetPersonnelRateAndBase>();
        List<BudgetModularIdc> budgetModularIdcs = new ArrayList<BudgetModularIdc>();
        List<BudgetModular> budgetModular = new ArrayList<BudgetModular>();
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            if (ObjectUtils.isNotNull(budgetPeriod.getBudgetModular())) {
                budgetModularIdcs.addAll(budgetPeriod.getBudgetModular().getBudgetModularIdcs());
                budgetModular.add(budgetPeriod.getBudgetModular());
            }
            List<BudgetLineItem> tempLIs = budgetPeriod.getBudgetLineItems();
            budgetLineItems.addAll(tempLIs);
            for (BudgetLineItem budgetLineItem : tempLIs) {
                budgetFormulatedCosts.addAll(budgetLineItem.getBudgetFormulatedCosts());
                budgetLineItemCalculatedAmounts.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                budgetRateAndBaseList.addAll(budgetLineItem.getBudgetRateAndBaseList());
                List<BudgetPersonnelDetails> tempPerList = budgetLineItem.getBudgetPersonnelDetailsList();
                bPersonnelDetailsList.addAll(tempPerList);
                for (BudgetPersonnelDetails budgetPersonnelDetails : tempPerList) {
                    budgetPersonnelCalculatedAmounts.addAll(budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts());
                    budgetPersonnelRateAndBaseList.addAll(budgetPersonnelDetails.getBudgetPersonnelRateAndBaseList());
                }
            }
        }
        //        for (BudgetPeriod budgetPeriod: getBudgetPeriods()) { 
        //            managedLists.addAll(budgetPeriod.buildListOfDeletionAwareLists()); 
        //        } 
        List<BudgetSubAwardFiles> subAwardFiles = new ArrayList<BudgetSubAwardFiles>();
        List<BudgetSubAwardAttachment> subAwardAttachments = new ArrayList<BudgetSubAwardAttachment>();
        List<BudgetSubAwardPeriodDetail> subAwardPeriodDetails = new ArrayList<BudgetSubAwardPeriodDetail>();
        for (BudgetSubAwards budgetSubAward : getBudgetSubAwards()) {
            subAwardFiles.addAll(budgetSubAward.getBudgetSubAwardFiles());
            subAwardAttachments.addAll(budgetSubAward.getBudgetSubAwardAttachments());
            subAwardPeriodDetails.addAll(budgetSubAward.getBudgetSubAwardPeriodDetails());
        }
        managedLists.add(budgetModularIdcs);
        managedLists.add(budgetModular);
        managedLists.add(budgetPersonnelRateAndBaseList);
        managedLists.add(budgetPersonnelCalculatedAmounts);
        managedLists.add(bPersonnelDetailsList);
        managedLists.add(budgetRateAndBaseList);
        managedLists.add(budgetLineItemCalculatedAmounts);
        managedLists.add(budgetFormulatedCosts);
        managedLists.add(budgetLineItems);
        managedLists.add(getBudgetPersons());
        managedLists.add(getBudgetPeriods());
        managedLists.add(getBudgetLaRates());
        managedLists.add(getBudgetRates());
        managedLists.add(subAwardAttachments);
        managedLists.add(subAwardFiles);
        managedLists.add(subAwardPeriodDetails);
        managedLists.add(getBudgetSubAwards());
        return managedLists;
    }

    /**
     * This method checks if any BudgetPeriod LineItem's have Justification.
     */
    public boolean areLineItemJustificationsPresent() {
        boolean justificationFound = false;
        OUTER: for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                justificationFound = !StringUtils.isEmpty(lineItem.getBudgetJustification());
                if (justificationFound) {
                    break OUTER;
                }
            }
        }
        return justificationFound;
    }

    /**
     * Gets index i from the budgetPeriods list.
     * 
     * @param index
     * @return Budget Period at index i
     */
    public BudgetPeriod getBudgetPeriod(int index) {
        while (getBudgetPeriods().size() <= index) {
            BudgetPeriod budgetPeriod = getNewBudgetPeriod();
            budgetPeriod.setBudget(this);
            getBudgetPeriods().add(budgetPeriod);
        }
        return getBudgetPeriods().get(index);
    }

    public Date getSummaryPeriodStartDate() {
        summaryPeriodStartDate = getBudgetPeriods().get(0).getStartDate();
        if (summaryPeriodStartDate == null) {
            summaryPeriodStartDate = getStartDate();
        }
        return summaryPeriodStartDate;
    }

    public Date getSummaryPeriodEndDate() {
        summaryPeriodEndDate = getBudgetPeriods().get(budgetPeriods.size() - 1).getEndDate();
        if (summaryPeriodEndDate == null) {
            summaryPeriodEndDate = getEndDate();
        }
        return summaryPeriodEndDate;
    }

    /**
     * Gets the budgetRates attribute. 
     * @return Returns the budgetRates.
     */
    public List<BudgetRate> getBudgetRates() {
        return budgetRates;
    }

    /**
     * Sets the budgetRates attribute value.
     * @param budgetRates The budgetRates to set.
     */
    public void setBudgetRates(List<BudgetRate> budgetRates) {
        this.budgetRates = budgetRates;
    }

    /**
     * Gets the budgetLaRates attribute. 
     * @return Returns the budgetLaRates.
     */
    public List<BudgetLaRate> getBudgetLaRates() {
        return budgetLaRates;
    }

    /**
     * Sets the budgetLaRates attribute value.
     * @param budgetLaRates The budgetLaRates to set.
     */
    public void setBudgetLaRates(List<BudgetLaRate> budgetLaRates) {
        this.budgetLaRates = budgetLaRates;
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
        if (rateClassTypes.isEmpty() && !rateClassTypesReloaded && (!this.getBudgetRates().isEmpty() || !this.getBudgetLaRates().isEmpty())) {
            getBudgetRatesService().syncBudgetRateCollectionsToExistingRates(this.rateClassTypes, getBudgetDocument());
        } else if (rateClassTypesReloaded) {
            if (!rateClassTypes.isEmpty()) {
                rateClassTypes.clear();
            }
            rateClassTypesReloaded = false;
            getBudgetRatesService().getBudgetRates(this.rateClassTypes, getBudgetDocument());
        }
        Collections.sort(rateClassTypes, new RateClassTypeComparator());
        return rateClassTypes;
    }

    public void setRateClassTypes(List<RateClassType> rateClassTypes) {
        this.rateClassTypes = rateClassTypes;
    }


    public int getBudgetProjectIncomeCount() {
        return getCollectionSize(budgetProjectIncomes);
    }

    public List<BudgetProjectIncome> getBudgetProjectIncomes() {
        return budgetProjectIncomes;
    }

    public BudgetProjectIncome getBudgetProjectIncome(int index) {
        while (getBudgetProjectIncomes().size() <= index) {
            getBudgetProjectIncomes().add(new BudgetProjectIncome());
        }
        return getBudgetProjectIncomes().get(index);
    }

    public void setBudgetProjectIncomes(List<BudgetProjectIncome> budgetProjectIncomes) {
        this.budgetProjectIncomes = budgetProjectIncomes;
    }

    /**
     * This method adds an item to its collection
     * @param budgetCostShare
     */
    public void add(BudgetCostShare budgetCostShare) {
        addBudgetDistributionAndIncomeComponent(getBudgetCostShares(), budgetCostShare);
    }

    /**
     * This method adds an item to its collection
     * @param budgetProjectIncome
     */
    public void add(BudgetProjectIncome budgetProjectIncome) {
        budgetProjectIncome.setBudgetPeriodId(getBudgetPeriodId(budgetProjectIncome));
        addBudgetDistributionAndIncomeComponent(getBudgetProjectIncomes(), budgetProjectIncome);
    }

    private Long getBudgetPeriodId(BudgetProjectIncome budgetProjectIncome) {
        //BudgetPeriod budgetPeriod = getBudgetPeriod(budgetProjectIncome.getBudgetPeriodNumber()); 
        List<BudgetPeriod> bPeriods = getBudgetPeriods();
        if (bPeriods != null && bPeriods.size() > 0) {
            for (BudgetPeriod bPeriod : bPeriods) {
                if (bPeriod.getBudgetPeriod() != null && budgetProjectIncome.getBudgetPeriodNumber() != null && bPeriod.getBudgetPeriod().intValue() == budgetProjectIncome.getBudgetPeriodNumber().intValue()) {
                    return bPeriod.getBudgetPeriodId();
                }
            }
        }
        return null;
    }

    /**
     * This method adds an item to its collection
     * @param budgetRate
     */
    public void add(BudgetRate budgetRate) {
        if (budgetRate != null) {
            getBudgetRates().add(budgetRate);
        }
    }

    /**
     * This method adds an item to its collection
     * @param budgetLaRate
     */
    public void add(BudgetLaRate budgetLaRate) {
        if (budgetLaRate != null) {
            getBudgetLaRates().add(budgetLaRate);
        }
    }

    /**
     * This method adds an item to its collection
     * @return
     */
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
        return getBudgetPersons().get(index);
    }

    public void addBudgetPerson(BudgetPerson budgetPerson) {
        getBudgetPersons().add(budgetPerson);
    }

    /**
     * This method adds an item to its collection
     * @return
     */
    public void add(BudgetPeriod budgetPeriod) {
        getBudgetPeriods().add(budgetPeriod);
    }

    /**
     * This method does what its name says
     * @param index
     * @return Object reference that was deleted
     */
    public BudgetCostShare removeBudgetCostShare(int index) {
        return getBudgetCostShares().remove(index);
    }

    /**
     * This method does what its name says
     * @param index
     * @return Object reference that was deleted
     */
    public BudgetProjectIncome removeBudgetProjectIncome(int index) {
        return getBudgetProjectIncomes().remove(index);
    }

    /**
     * This method does what its name says
     * @param index
     * @return Object reference that was deleted
     */
    public BudgetUnrecoveredFandA removeBudgetUnrecoveredFandA(int index) {
        return getBudgetUnrecoveredFandAs().remove(index);
    }

    public final List<InstituteLaRate> getInstituteLaRates() {
        return instituteLaRates;
    }

    public final void setInstituteLaRates(List<InstituteLaRate> instituteLaRates) {
        this.instituteLaRates = instituteLaRates;
    }

    public BudgetCostShare getBudgetCostShare(int index) {
        while (getBudgetCostShares().size() <= index) {
            getBudgetCostShares().add(new BudgetCostShare());
        }
        return getBudgetCostShares().get(index);
    }

    public List<BudgetCostShare> getBudgetCostShares() {
        return budgetCostShares;
    }


    public int getBudgetCostShareCount() {
        return getCollectionSize(budgetCostShares);
    }

    public BudgetUnrecoveredFandA getBudgetUnrecoveredFandA(int index) {
        while (getBudgetUnrecoveredFandAs().size() <= index) {
            getBudgetUnrecoveredFandAs().add(new BudgetUnrecoveredFandA());
        }
        return getBudgetUnrecoveredFandAs().get(index);
    }


    public List<BudgetUnrecoveredFandA> getBudgetUnrecoveredFandAs() {
        return budgetUnrecoveredFandAs;
    }


    public int getBudgetUnrecoveredFandACount() {
        return getCollectionSize(budgetUnrecoveredFandAs);
    }

    public void getBudgetTotals() {
        getService(BudgetCalculationService.class).calculateBudgetSummaryTotals(this);
    }

    public SortedMap<CostElement, List<ScaleTwoDecimal>> getObjectCodeTotals() {
        return objectCodeTotals;
    }

    public void setObjectCodeTotals(SortedMap<CostElement, List<ScaleTwoDecimal>> objectCodeTotals) {
        this.objectCodeTotals = objectCodeTotals;
    }

    public SortedMap<RateType, List<ScaleTwoDecimal>> getCalculatedExpenseTotals() {
        return calculatedExpenseTotals;
    }

    public void setCalculatedExpenseTotals(SortedMap<RateType, List<ScaleTwoDecimal>> calculatedExpenseTotals) {
        this.calculatedExpenseTotals = calculatedExpenseTotals;
    }


    public ScaleTwoDecimal getAvailableCostSharing() {
        ScaleTwoDecimal availableCostShare = ScaleTwoDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            if (budgetPeriod.getCostSharingAmount() != null) {
                availableCostShare = availableCostShare.add(budgetPeriod.getCostSharingAmount());
            }
        }
        return availableCostShare;
    }


    public ScaleTwoDecimal getAvailableUnrecoveredFandA() {
        ScaleTwoDecimal availableUnrecoveredFandA = ScaleTwoDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            if (budgetPeriod.getUnderrecoveryAmount() != null) {
                availableUnrecoveredFandA = availableUnrecoveredFandA.add(budgetPeriod.getUnderrecoveryAmount());
            }
        }
        return availableUnrecoveredFandA;
    }

    /**
     * This method does what its name says
     * @param fiscalYear
     * @return
     */
    public ScaleTwoDecimal findCostSharingForFiscalYear(Integer fiscalYear) {
        ScaleTwoDecimal costSharing = ScaleTwoDecimal.ZERO;
        List<FiscalYearSummary> costShareFiscalYears = findCostShareTotalsForBudgetPeriods(mapBudgetPeriodsToFiscalYears());
        for (FiscalYearSummary costShareFiscalYear : costShareFiscalYears) {
            if (costShareFiscalYear.fiscalYear == fiscalYear) {
                costSharing = costShareFiscalYear.costShare;
                break;
            }
        }
        return costSharing;
    }

    /**
     * This method does what its name says
     * @param fiscalYear
     * @return
     */
    public ScaleTwoDecimal findUnrecoveredFandAForFiscalYear(Integer fiscalYear) {
        ScaleTwoDecimal unrecoveredFandA = ScaleTwoDecimal.ZERO;
        List<FiscalYearSummary> fiscalYearSummaries = findCostShareTotalsForBudgetPeriods(mapBudgetPeriodsToFiscalYears());
        for (FiscalYearSummary fiscalYearSummary : fiscalYearSummaries) {
            if (fiscalYearSummary.getFiscalYear() == fiscalYear) {
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
    public Date loadFiscalYearStart() {
        return createDateFromString(getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_CURRENT_FISCAL_YEAR));
    }
    
    public boolean getSalaryInflationEnabled(){
        return getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.ENABLE_SALARY_INFLATION_ANNIV_DATE).equals(PARAM_VALUE_ENABLED);
    }
    
    /**
     * This method loads the cost sharing applicability flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadCostSharingApplicability() {
        return getBooleanValue(Constants.BUDGET_COST_SHARING_APPLICABILITY_FLAG);
    }

    /**
     * This method loads the unrecovered F&A applicability flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadUnrecoveredFandAApplicability() {
        return getBooleanValue(Constants.BUDGET_UNRECOVERED_F_AND_A_APPLICABILITY_FLAG);
    }

    /**
     * This method loads the cost sharing enforcement flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadCostSharingEnforcement() {
        return getBooleanValue(Constants.BUDGET_COST_SHARING_ENFORCEMENT_FLAG);
    }

    /**
     * This method loads the unrecovered F&A enforcement flag from the database. Protected to allow mocking out service call
     * @return
     */
    protected Boolean loadUnrecoveredFandAEnforcement() {
        return getBooleanValue(Constants.BUDGET_UNRECOVERED_F_AND_A_ENFORCEMENT_FLAG);
    }

    /**
     * 
     * This method should be in DateUtils, but wasn't found there
     * @param budgetFiscalYearStart
     * @return
     */
    public Date createDateFromString(String budgetFiscalYearStart) {
        if (budgetFiscalYearStart == null) {
            return null;
        }
        String[] dateParts = budgetFiscalYearStart.split("/");
        // mm/dd/yyyy 
        // using Calendar her because org.kuali.core.util.DateUtils.newdate(...) has hour value in time fields (UT?) 
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(dateParts[2]), Integer.valueOf(dateParts[0]) - 1, Integer.valueOf(dateParts[1]), 0, 0, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * This method adds a BudgetDistributionAndIncomeComponent to the specified list after setting its key field values
     * @param distributionAndIncomeComponents
     * @param distributionAndIncomeComponent
     */
    @SuppressWarnings("unchecked")
    private void addBudgetDistributionAndIncomeComponent(List distributionAndIncomeComponents, BudgetDistributionAndIncomeComponent distributionAndIncomeComponent) {
        if (distributionAndIncomeComponent != null) {
            distributionAndIncomeComponent.setBudgetId(getBudgetId());
            distributionAndIncomeComponent.setDocumentComponentId(getHackedDocumentNextValue(distributionAndIncomeComponent.getDocumentComponentIdKey()));
            distributionAndIncomeComponents.add(distributionAndIncomeComponent);
        } else {
            LOG.warn("Attempt to add null distributionAndIncomeComponent was ignored.");
        }
    }

    public Integer getHackedDocumentNextValue(String documentComponentIdKey) {
        return budgetDocument.getHackedDocumentNextValue(documentComponentIdKey);
    }

    /**
     * This method does what its name says
     * @param fiscalYear
     * @return
     */
    private FiscalYearApplicableRate findApplicableRatesForFiscalYear(Integer fiscalYear) {
        String unrecoveredFandARateClassCode = getUrRateClassCode();
        if (unrecoveredFandARateClassCode == null || unrecoveredFandARateClassCode.trim().length() == 0) {
            return new FiscalYearApplicableRate(fiscalYear, ScaleThreeDecimal.ZERO, ScaleThreeDecimal.ZERO);
        } else {
            ScaleThreeDecimal offCampusRate = findApplicableRateForRateClassCode(fiscalYear, unrecoveredFandARateClassCode, false);
            ScaleThreeDecimal onCampusRate = findApplicableRateForRateClassCode(fiscalYear, unrecoveredFandARateClassCode, true);
            return new FiscalYearApplicableRate(fiscalYear, onCampusRate, offCampusRate);
        }
    }

    /**
     * This method does what its name says
     * @param fiscalYear
     * @param unrecoveredFandARateClassCode
     * @param findOnCampusRate
     * @return
     */
    private ScaleThreeDecimal findApplicableRateForRateClassCode(Integer fiscalYear, String unrecoveredFandARateClassCode, boolean findOnCampusRate) {
        ScaleThreeDecimal applicableRate = ScaleThreeDecimal.ZERO;
        for (BudgetRate budgetRate : getBudgetRates()) {
            if (Integer.valueOf(budgetRate.getFiscalYear()).equals(fiscalYear) && budgetRate.getRateClassCode().equalsIgnoreCase(unrecoveredFandARateClassCode) && findOnCampusRate == budgetRate.getOnOffCampusFlag()) {
                applicableRate = new ScaleThreeDecimal(budgetRate.getApplicableRate().bigDecimalValue());
                break;
            }
        }
        return applicableRate;
    }

    /**
     * This method does what its name says
     * @param budgetPeriodFiscalYears
     * @return
     */
    private List<FiscalYearSummary> findCostShareTotalsForBudgetPeriods(Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears) {
        List<FiscalYearSummary> fiscalYearSummaries = new ArrayList<FiscalYearSummary>();
        for (Map.Entry<Integer, List<BudgetPeriod>> entry : budgetPeriodFiscalYears.entrySet()) {
            ScaleTwoDecimal fiscalYearCostShareAmount = ScaleTwoDecimal.ZERO;
            ScaleTwoDecimal fiscalYearUnrecoveredFandA = ScaleTwoDecimal.ZERO;
            List<BudgetPeriod> budgetPeriodsInFiscalYear = entry.getValue();
            for (BudgetPeriod budgetPeriod : budgetPeriodsInFiscalYear) {
                fiscalYearCostShareAmount = fiscalYearCostShareAmount.add(budgetPeriod.getCostSharingAmount());
                fiscalYearUnrecoveredFandA = fiscalYearUnrecoveredFandA.add(budgetPeriod.getUnderrecoveryAmount());
            }
            fiscalYearSummaries.add(new FiscalYearSummary(budgetPeriodsInFiscalYear.get(0), entry.getKey(), fiscalYearCostShareAmount, fiscalYearUnrecoveredFandA, findApplicableRatesForFiscalYear(entry.getKey())));
        }
        return fiscalYearSummaries;
    }

    /**
     * This method does what its name says
     * @param incomes
     * @return
     */
    private List<ScaleTwoDecimal> findProjectIncomeTotalsForBudgetPeriods(Map<Integer, ScaleTwoDecimal> incomes) {
        List<ScaleTwoDecimal> periodIncomeTotals = new ArrayList<ScaleTwoDecimal>(budgetPeriods.size());
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            ScaleTwoDecimal periodIncomeTotal = incomes.get(budgetPeriod.getBudgetPeriod());
            if (periodIncomeTotal == null) {
                periodIncomeTotal = ScaleTwoDecimal.ZERO;
            }
            periodIncomeTotals.add(periodIncomeTotal);
        }
        return periodIncomeTotals;
    }

    /**
     * This method returns a collection if the collection is not null; otherwise, zero is returned 
     * @param collection
     * @return
     */
    @SuppressWarnings("unchecked")
    private int getCollectionSize(Collection collection) {
        return collection != null ? collection.size() : 0;
    }

    /**
     * This method returns the fiscalYearStart, loading it from the database if needed
     *  
     * @return
     */
    private Date getFiscalYearStart() {
        return loadFiscalYearStart();
    }

    /**
     * This method looks up the applicability flag
     * @param parmName
     * @return
     */
    protected Boolean getBooleanValue(String parmName) {
        String parmValue;
        if (!getParameterService().parameterExists(BudgetDocument.class, parmName)) {
            parmValue = FALSE_FLAG;
        } else {
            parmValue = getParameterService().getParameterValueAsString(BudgetDocument.class, parmName);
        }
        return parmValue.equalsIgnoreCase(TRUE_FLAG);
    }


    private Map<Integer, ScaleTwoDecimal> mapProjectIncomeTotalsToBudgetPeriodNumbers() {
        Map<Integer, ScaleTwoDecimal> budgetPeriodProjectIncomeMap = new TreeMap<Integer, ScaleTwoDecimal>();
        for (BudgetProjectIncome budgetProjectIncome : budgetProjectIncomes) {
            Integer budgetPeriodNumber = budgetProjectIncome.getBudgetPeriodNumber();
            ScaleTwoDecimal amount = budgetPeriodProjectIncomeMap.get(budgetPeriodNumber);
            amount = (amount == null) ? budgetProjectIncome.getProjectIncome() : amount.add(budgetProjectIncome.getProjectIncome());
            budgetPeriodProjectIncomeMap.put(budgetPeriodNumber, amount);
        }
        return budgetPeriodProjectIncomeMap;
    }


    private Map<Integer, List<BudgetPeriod>> mapBudgetPeriodsToFiscalYears() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = new TreeMap<Integer, List<BudgetPeriod>>();
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            Integer fiscalYear = budgetPeriod.calculateFiscalYear(getFiscalYearStart());
            List<BudgetPeriod> budgetPeriodsInFiscalYear = budgetPeriodFiscalYears.get(fiscalYear);
            if (budgetPeriodsInFiscalYear == null) {
                budgetPeriodsInFiscalYear = new ArrayList<BudgetPeriod>();
                budgetPeriodFiscalYears.put(fiscalYear, budgetPeriodsInFiscalYear);
            }
            budgetPeriodsInFiscalYear.add(budgetPeriod);
            Collections.sort(budgetPeriodsInFiscalYear, BudgetPeriod.getBudgetPeriodDateComparator());
        }
        return budgetPeriodFiscalYears;
    }

    /**
     * This class wraps fiscal year, on and off campus rate
     */
    public static class FiscalYearApplicableRate {

        private Integer fiscalYear;

        private ScaleThreeDecimal onCampusApplicableRate;

        private ScaleThreeDecimal offCampusApplicableRate;

        /**
         * Constructs a FiscalYearApplicableRate.java.
         * @param fiscalYear
         * @param onCampusApplicableRate
         * @param offCampusApplicableRate
         */
        public FiscalYearApplicableRate(Integer fiscalYear, ScaleThreeDecimal onCampusApplicableRate, ScaleThreeDecimal offCampusApplicableRate) {
            this.fiscalYear = fiscalYear;
            this.onCampusApplicableRate = onCampusApplicableRate;
            this.offCampusApplicableRate = offCampusApplicableRate;
        }

        public Integer getFiscalYear() {
            return fiscalYear;
        }

        public ScaleThreeDecimal getOnCampusApplicableRate() {
            return onCampusApplicableRate;
        }

        public ScaleThreeDecimal getOffCampusApplicableRate() {
            return offCampusApplicableRate;
        }
    }

    /**
     * This class wraps the fiscal year, assignedBudgetPeriod, fiscl year applicable rate, and the fiscal year totals for cost share and unrecovered
     */
    public static class FiscalYearSummary {

        private int fiscalYear;

        private BudgetPeriod assignedBudgetPeriod;

        private ScaleTwoDecimal costShare;

        private ScaleTwoDecimal unrecoveredFandA;

        private FiscalYearApplicableRate fiscalYearRates;

        /**
         * 
         * Constructs a FiscalYearSummary.
         * @param assignedBudgetPeriod
         * @param fiscalYear
         * @param costShare
         * @param unrecoveredFandA
         * @param fiscalYearRates
         */
        public FiscalYearSummary(BudgetPeriod assignedBudgetPeriod, int fiscalYear, ScaleTwoDecimal costShare, ScaleTwoDecimal unrecoveredFandA, FiscalYearApplicableRate fiscalYearRates) {
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

        public ScaleTwoDecimal getCostShare() {
            return costShare;
        }

        public FiscalYearApplicableRate getFiscalYearRates() {
            return fiscalYearRates;
        }

        public ScaleTwoDecimal getUnrecoveredFandA() {
            return unrecoveredFandA;
        }
    }

    public List<KeyValue> getBudgetCategoryTypeCodes() {
        return budgetCategoryTypeCodes;
    }

    public void setBudgetCategoryTypeCodes(List<KeyValue> budgetCategoryTypeCodes) {
        this.budgetCategoryTypeCodes = budgetCategoryTypeCodes;
    }

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

    public String getBudgetJustification() {
        return budgetJustification;
    }

    public void setBudgetJustification(String budgetJustification) {
        this.budgetJustification = budgetJustification;
    }

    public String getOnOffCampusFlagDescription() {
        return getBudgetSummaryService().getOnOffCampusFlagDescription(getOnOffCampusFlag());
    }

    /**
    * Gets the budgetLineItemDeleted attribute. 
    * @return Returns the budgetLineItemDeleted.
    */
    public boolean isBudgetLineItemDeleted() {
        return budgetLineItemDeleted;
    }

    /**
     * Sets the budgetLineItemDeleted attribute value.
     * @param budgetLineItemDeleted The budgetLineItemDeleted to set.
     */
    public void setBudgetLineItemDeleted(boolean budgetLineItemDeleted) {
        this.budgetLineItemDeleted = budgetLineItemDeleted;
    }

    /**
     * Gets the budgetPrintForms attribute. 
     * @return Returns the budgetPrintForms.
     */
    public List<BudgetPrintForm> getBudgetPrintForms() {
        return budgetPrintForms;
    }

    /**
     * Sets the budgetPrintForms attribute value.
     * @param budgetPrintForms The budgetPrintForms to set.
     */
    public void setBudgetPrintForms(List<BudgetPrintForm> budgetPrintForms) {
        this.budgetPrintForms = budgetPrintForms;
    }

    public boolean isRateClassTypesReloaded() {
        return rateClassTypesReloaded;
    }

    public void setRateClassTypesReloaded(boolean rateClassTypesReloaded) {
        this.rateClassTypesReloaded = rateClassTypesReloaded;
    }

    public List<BudgetSubAwards> getBudgetSubAwards() {
        return budgetSubAwards;
    }

    public void setBudgetSubAwards(List<BudgetSubAwards> budgetSubAwards) {
        this.budgetSubAwards = budgetSubAwards;
    }

    public boolean isRateSynced() {
        return rateSynced;
    }

    public void setRateSynced(boolean rateSynced) {
        this.rateSynced = rateSynced;
    }

    public SortedMap<BudgetCategoryType, List<CostElement>> getObjectCodeListByBudgetCategoryType() {
        return objectCodeListByBudgetCategoryType;
    }

    public void setObjectCodeListByBudgetCategoryType(SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType) {
        this.objectCodeListByBudgetCategoryType = objectCodeListByBudgetCategoryType;
    }

    public SortedMap<CostElement, List<BudgetPersonnelDetails>> getObjectCodePersonnelList() {
        return objectCodePersonnelList;
    }

    public void setObjectCodePersonnelList(SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodePersonnelList) {
        this.objectCodePersonnelList = objectCodePersonnelList;
    }

    public SortedMap<String, List<ScaleTwoDecimal>> getObjectCodePersonnelSalaryTotals() {
        return objectCodePersonnelSalaryTotals;
    }

    public void setObjectCodePersonnelSalaryTotals(SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelSalaryTotals) {
        this.objectCodePersonnelSalaryTotals = objectCodePersonnelSalaryTotals;
    }

    public SortedMap<String, List<ScaleTwoDecimal>> getObjectCodePersonnelFringeTotals() {
        return objectCodePersonnelFringeTotals;
    }

    public void setObjectCodePersonnelFringeTotals(SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelFringeTotals) {
        this.objectCodePersonnelFringeTotals = objectCodePersonnelFringeTotals;
    }

    public SortedMap<RateType, List<ScaleTwoDecimal>> getPersonnelCalculatedExpenseTotals() {
        return personnelCalculatedExpenseTotals;
    }

    public void setPersonnelCalculatedExpenseTotals(SortedMap<RateType, List<ScaleTwoDecimal>> personnelCalculatedExpenseTotals) {
        this.personnelCalculatedExpenseTotals = personnelCalculatedExpenseTotals;
    }

    public SortedMap<RateType, List<ScaleTwoDecimal>> getNonPersonnelCalculatedExpenseTotals() {
        return nonPersonnelCalculatedExpenseTotals;
    }

    public void setNonPersonnelCalculatedExpenseTotals(SortedMap<RateType, List<ScaleTwoDecimal>> nonPersonnelCalculatedExpenseTotals) {
        this.nonPersonnelCalculatedExpenseTotals = nonPersonnelCalculatedExpenseTotals;
    }

    public SortedMap<String, List<ScaleTwoDecimal>> getBudgetSummaryTotals() {
        return budgetSummaryTotals;
    }

    public void setBudgetSummaryTotals(SortedMap<String, List<ScaleTwoDecimal>> budgetSummaryTotals) {
        this.budgetSummaryTotals = budgetSummaryTotals;
    }

    /**
     * Gets the Underreovery Amount for all budget periods.
     * @return the amount.
     */
    public final ScaleTwoDecimal getSumUnderreoveryAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getUnderrecoveryAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the CostSharing Amount for all budget periods.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumCostSharingAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getCostSharingAmount());
        }
        return amount;
    }

    /**
     * Gets the sum of the Direct Cost Amount for all budget periods.
     * @return the amount
     */
    public ScaleTwoDecimal getSumDirectCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalDirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Indirect Cost Amount for all budget periods.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumIndirectCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalIndirectCost());
        }
        return amount;
    }

    /**
     * Gets the sum of the Total Cost Amount for all budget periods.
     * @return the amount
     */
    public final ScaleTwoDecimal getSumTotalCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalCost());
        }
        return amount;
    }

    /**
     * Gets the budgetDocument attribute. 
     * @return Returns the budgetDocument.
     */
    public BudgetDocument getBudgetDocument() {
        return budgetDocument;
    }

    /**
     * Sets the budgetDocument attribute value.
     * @param budgetDocument The budgetDocument to set.
     */
    public void setBudgetDocument(BudgetDocument budgetDocument) {
        setDocumentNumber(budgetDocument.getDocumentNumber());
        this.budgetDocument = budgetDocument;
    }

    /**
     * Gets the ohRatesNonEditable attribute. 
     * @return Returns the ohRatesNonEditable.
     */
    public boolean getOhRatesNonEditable() {
        return false;
    }

    /**
     * Gets the ebRatesNonEditable attribute. 
     * @return Returns the ebRatesNonEditable.
     */
    public boolean getEbRatesNonEditable() {
        return false;
    }

    public BudgetPeriod getNewBudgetPeriod() {
        return new BudgetPeriod();
    }

    public BudgetLineItem getNewBudgetLineItem() {
        return new BudgetLineItem();
    }

    public BudgetParent getBudgetParent() {
        return getBudgetDocument().getParentDocument().getBudgetParent();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((activityTypeCode == null) ? 0 : activityTypeCode.hashCode());
        result = prime * result + ((budgetCategoryTypeCodes == null) ? 0 : budgetCategoryTypeCodes.hashCode());
        result = prime * result + ((budgetCostShares == null) ? 0 : budgetCostShares.hashCode());
        result = prime * result + ((budgetDocument == null) ? 0 : budgetDocument.hashCode());
        result = prime * result + ((budgetJustification == null) ? 0 : budgetJustification.hashCode());
        result = prime * result + ((budgetLaRates == null) ? 0 : budgetLaRates.hashCode());
        result = prime * result + (budgetLineItemDeleted ? 1231 : 1237);
        result = prime * result + ((budgetPeriods == null) ? 0 : budgetPeriods.hashCode());
        result = prime * result + ((budgetPersonnelDetailsList == null) ? 0 : budgetPersonnelDetailsList.hashCode());
        result = prime * result + ((budgetPersons == null) ? 0 : budgetPersons.hashCode());
        result = prime * result + ((budgetPrintForms == null) ? 0 : budgetPrintForms.hashCode());
        result = prime * result + ((budgetProjectIncomes == null) ? 0 : budgetProjectIncomes.hashCode());
        result = prime * result + ((budgetRates == null) ? 0 : budgetRates.hashCode());
        result = prime * result + ((budgetSubAwards == null) ? 0 : budgetSubAwards.hashCode());
        result = prime * result + ((budgetSummaryTotals == null) ? 0 : budgetSummaryTotals.hashCode());
        result = prime * result + ((budgetUnrecoveredFandAs == null) ? 0 : budgetUnrecoveredFandAs.hashCode());
        result = prime * result + ((calculatedExpenseTotals == null) ? 0 : calculatedExpenseTotals.hashCode());
        result = prime * result + ((instituteLaRates == null) ? 0 : instituteLaRates.hashCode());
        result = prime * result + ((instituteRates == null) ? 0 : instituteRates.hashCode());
        result = prime * result + ((nonPersonnelCalculatedExpenseTotals == null) ? 0 : nonPersonnelCalculatedExpenseTotals.hashCode());
        result = prime * result + ((objectCodeListByBudgetCategoryType == null) ? 0 : objectCodeListByBudgetCategoryType.hashCode());
        result = prime * result + ((objectCodePersonnelFringeTotals == null) ? 0 : objectCodePersonnelFringeTotals.hashCode());
        result = prime * result + ((objectCodePersonnelList == null) ? 0 : objectCodePersonnelList.hashCode());
        result = prime * result + ((objectCodePersonnelSalaryTotals == null) ? 0 : objectCodePersonnelSalaryTotals.hashCode());
        result = prime * result + ((objectCodeTotals == null) ? 0 : objectCodeTotals.hashCode());
        result = prime * result + ((personnelCalculatedExpenseTotals == null) ? 0 : personnelCalculatedExpenseTotals.hashCode());
        result = prime * result + ((rateClass == null) ? 0 : rateClass.hashCode());
        result = prime * result + ((rateClassTypes == null) ? 0 : rateClassTypes.hashCode());
        result = prime * result + (rateClassTypesReloaded ? 1231 : 1237);
        result = prime * result + ((rateClasses == null) ? 0 : rateClasses.hashCode());
        result = prime * result + (rateSynced ? 1231 : 1237);
        result = prime * result + ((summaryPeriodEndDate == null) ? 0 : summaryPeriodEndDate.hashCode());
        result = prime * result + ((summaryPeriodStartDate == null) ? 0 : summaryPeriodStartDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Budget other = (Budget) obj;
        if (activityTypeCode == null) {
            if (other.activityTypeCode != null) return false;
        } else if (!activityTypeCode.equals(other.activityTypeCode)) return false;
        if (budgetCategoryTypeCodes == null) {
            if (other.budgetCategoryTypeCodes != null) return false;
        } else if (!budgetCategoryTypeCodes.equals(other.budgetCategoryTypeCodes)) return false;
        if (budgetCostShares == null) {
            if (other.budgetCostShares != null) return false;
        } else if (!budgetCostShares.equals(other.budgetCostShares)) return false;
        if (budgetDocument == null) {
            if (other.budgetDocument != null) return false;
        } else if (!budgetDocument.equals(other.budgetDocument)) return false;
        if (budgetJustification == null) {
            if (other.budgetJustification != null) return false;
        } else if (!budgetJustification.equals(other.budgetJustification)) return false;
        if (budgetLaRates == null) {
            if (other.budgetLaRates != null) return false;
        } else if (!budgetLaRates.equals(other.budgetLaRates)) return false;
        if (budgetLineItemDeleted != other.budgetLineItemDeleted) return false;
        if (budgetPeriods == null) {
            if (other.budgetPeriods != null) return false;
        } else if (!budgetPeriods.equals(other.budgetPeriods)) return false;
        if (budgetPersonnelDetailsList == null) {
            if (other.budgetPersonnelDetailsList != null) return false;
        } else if (!budgetPersonnelDetailsList.equals(other.budgetPersonnelDetailsList)) return false;
        if (budgetPersons == null) {
            if (other.budgetPersons != null) return false;
        } else if (!budgetPersons.equals(other.budgetPersons)) return false;
        if (budgetPrintForms == null) {
            if (other.budgetPrintForms != null) return false;
        } else if (!budgetPrintForms.equals(other.budgetPrintForms)) return false;
        if (budgetProjectIncomes == null) {
            if (other.budgetProjectIncomes != null) return false;
        } else if (!budgetProjectIncomes.equals(other.budgetProjectIncomes)) return false;
        if (budgetRates == null) {
            if (other.budgetRates != null) return false;
        } else if (!budgetRates.equals(other.budgetRates)) return false;
        if (budgetSubAwards == null) {
            if (other.budgetSubAwards != null) return false;
        } else if (!budgetSubAwards.equals(other.budgetSubAwards)) return false;
        if (budgetSummaryTotals == null) {
            if (other.budgetSummaryTotals != null) return false;
        } else if (!budgetSummaryTotals.equals(other.budgetSummaryTotals)) return false;
        if (budgetUnrecoveredFandAs == null) {
            if (other.budgetUnrecoveredFandAs != null) return false;
        } else if (!budgetUnrecoveredFandAs.equals(other.budgetUnrecoveredFandAs)) return false;
        if (calculatedExpenseTotals == null) {
            if (other.calculatedExpenseTotals != null) return false;
        } else if (!calculatedExpenseTotals.equals(other.calculatedExpenseTotals)) return false;
        if (instituteLaRates == null) {
            if (other.instituteLaRates != null) return false;
        } else if (!instituteLaRates.equals(other.instituteLaRates)) return false;
        if (instituteRates == null) {
            if (other.instituteRates != null) return false;
        } else if (!instituteRates.equals(other.instituteRates)) return false;
        if (nonPersonnelCalculatedExpenseTotals == null) {
            if (other.nonPersonnelCalculatedExpenseTotals != null) return false;
        } else if (!nonPersonnelCalculatedExpenseTotals.equals(other.nonPersonnelCalculatedExpenseTotals)) return false;
        if (objectCodeListByBudgetCategoryType == null) {
            if (other.objectCodeListByBudgetCategoryType != null) return false;
        } else if (!objectCodeListByBudgetCategoryType.equals(other.objectCodeListByBudgetCategoryType)) return false;
        if (objectCodePersonnelFringeTotals == null) {
            if (other.objectCodePersonnelFringeTotals != null) return false;
        } else if (!objectCodePersonnelFringeTotals.equals(other.objectCodePersonnelFringeTotals)) return false;
        if (objectCodePersonnelList == null) {
            if (other.objectCodePersonnelList != null) return false;
        } else if (!objectCodePersonnelList.equals(other.objectCodePersonnelList)) return false;
        if (objectCodePersonnelSalaryTotals == null) {
            if (other.objectCodePersonnelSalaryTotals != null) return false;
        } else if (!objectCodePersonnelSalaryTotals.equals(other.objectCodePersonnelSalaryTotals)) return false;
        if (objectCodeTotals == null) {
            if (other.objectCodeTotals != null) return false;
        } else if (!objectCodeTotals.equals(other.objectCodeTotals)) return false;
        if (personnelCalculatedExpenseTotals == null) {
            if (other.personnelCalculatedExpenseTotals != null) return false;
        } else if (!personnelCalculatedExpenseTotals.equals(other.personnelCalculatedExpenseTotals)) return false;
        if (rateClass == null) {
            if (other.rateClass != null) return false;
        } else if (!rateClass.equals(other.rateClass)) return false;
        if (rateClassTypes == null) {
            if (other.rateClassTypes != null) return false;
        } else if (!rateClassTypes.equals(other.rateClassTypes)) return false;
        if (rateClassTypesReloaded != other.rateClassTypesReloaded) return false;
        if (rateClasses == null) {
            if (other.rateClasses != null) return false;
        } else if (!rateClasses.equals(other.rateClasses)) return false;
        if (rateSynced != other.rateSynced) return false;
        if (summaryPeriodEndDate == null) {
            if (other.summaryPeriodEndDate != null) return false;
        } else if (!summaryPeriodEndDate.equals(other.summaryPeriodEndDate)) return false;
        if (summaryPeriodStartDate == null) {
            if (other.summaryPeriodStartDate != null) return false;
        } else if (!summaryPeriodStartDate.equals(other.summaryPeriodStartDate)) return false;
        return true;
    }

    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new BudgetPersonnelDetails();
    }

    public boolean isCostSharingSubmissionEnabled() {
        return getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.ENABLE_COST_SHARE_SUBMIT).equals(PARAM_VALUE_ENABLED);
    }
    
    public String getSummaryNumberOfMonths() {
        return String.valueOf(this.getProposalBudgetNumberOfMonthsService().getNumberOfMonth(this.getSummaryPeriodStartDate(), this.getSummaryPeriodEndDate()));
    }
    
    protected ProposalBudgetNumberOfMonthsService getProposalBudgetNumberOfMonthsService() {
        return KcServiceLocator.getService(ProposalBudgetNumberOfMonthsService.class);
    }
}

class RateClassTypeComparator implements Comparator<RateClassType>, Serializable {

    private static final long serialVersionUID = 8230902362851330642L;

    public int compare(RateClassType rateClassType1, RateClassType rateClassType2) {
        RateClassType r1 = rateClassType1;
        RateClassType r2 = rateClassType2;
        return r1.getSortId().compareTo(r2.getSortId());
    }
}
