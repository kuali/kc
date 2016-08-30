/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.budget.framework.core;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.api.core.BudgetContract;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.common.budget.framework.rate.AbstractBudgetRate;
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
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetRateAndBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelRateAndBase;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.framework.impl.Period;
import org.kuali.coeus.common.framework.person.PersonRolodexComparator;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetNumberOfMonthsService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@NAMESPACE(namespace = Constants.MODULE_NAMESPACE_BUDGET)
@COMPONENT(component = ParameterConstants.DOCUMENT_COMPONENT)
@MappedSuperclass
@Entity
@Table(name = "BUDGET")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="PARENT_DOCUMENT_TYPE_CODE", discriminatorType = DiscriminatorType.STRING)
public class Budget extends AbstractBudget implements BudgetContract {

    private static final String PARAM_VALUE_ENABLED = "1";

    private static final long serialVersionUID = -252470308729741085L;

    private static final String FALSE_FLAG = "N";

    private static final String TRUE_FLAG = "Y";

    private static final Log LOG = LogFactory.getLog(Budget.class);

	@Column(name = "PARENT_DOCUMENT_TYPE_CODE")
    private String parentDocumentTypeCode;

	@Column(name = "BUDGET_JUSTIFICATION")
    @Lob
    private String budgetJustification;

    @Column(name = "CREATE_TIMESTAMP")
    private Timestamp createTimestamp;

    @Column(name = "CREATE_USER")
    private String createUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OH_RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @CollectionProperty(update = false)
    @Ignore
    @OneToMany(mappedBy="budget", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetRate> budgetRates;

    @CollectionProperty(update = false)
    @Ignore
    @OneToMany(mappedBy="budget", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetLaRate> budgetLaRates;


    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    @OrderBy("budgetPeriodNumber")
    private List<BudgetProjectIncome> budgetProjectIncomes;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    @OrderBy("projectPeriod")
    private List<BudgetCostShare> budgetCostShares;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    @OrderBy("fiscalYear")
    private List<BudgetUnrecoveredFandA> budgetUnrecoveredFandAs;

    @Column(name = "BUDGET_ADJUSTMENT_DOC_NBR")
    private String budgetAdjustmentDocumentNumber;

    @OneToMany(mappedBy="budget", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetPerson> budgetPersons;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    @OrderBy("subAwardNumber")
    private List<BudgetSubAwards> budgetSubAwards;

    @OneToMany(mappedBy="budget", orphanRemoval = true, cascade = { CascadeType.ALL })
    @OrderBy("budgetPeriod")
    private List<BudgetPeriod> budgetPeriods;
    
    @Transient
    private List<Period> budgetSummaryDetails;
    
    @Transient
    private Date summaryPeriodStartDate;

    @Transient
    private Date summaryPeriodEndDate;

    @Transient
    private List<InstituteRate> instituteRates;

    @Transient
    private List<InstituteLaRate> instituteLaRates;

    @Transient
    private List<RateClass> rateClasses;

    @Transient
    private List<RateClassType> rateClassTypes;

    @Transient
    private SortedMap<CostElement, List<ScaleTwoDecimal>> objectCodeTotals;

    @Transient
    private SortedMap<RateType, List<ScaleTwoDecimal>> calculatedExpenseTotals;

    @Transient
    private SortedMap<RateType, List<ScaleTwoDecimal>> personnelCalculatedExpenseTotals;

    @Transient
    private SortedMap<RateType, List<ScaleTwoDecimal>> nonPersonnelCalculatedExpenseTotals;

    @Transient
    private List<KeyValue> budgetCategoryTypeCodes;

    @Transient
    private SortedMap<BudgetCategoryType, List<CostElement>> objectCodeListByBudgetCategoryType;

    @Transient
    private SortedMap<CostElement, List<BudgetPersonnelDetails>> objectCodePersonnelList;

    @Transient
    private SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelSalaryTotals;

    @Transient
    private SortedMap<String, List<ScaleTwoDecimal>> objectCodePersonnelFringeTotals;

    @Transient
    private SortedMap<String, List<ScaleTwoDecimal>> budgetSummaryTotals;

    @Transient
    private List<BudgetPrintForm> budgetPrintForms;

    //should be abstract but due to limitations in KRAD-Data and DD validations this isn't currently possible.
    public BudgetParent getBudgetParent() {
		throw new UnsupportedOperationException("Not defined in parent class.");
	}
    
    public String getParentDocumentKey() {
    	throw new UnsupportedOperationException("Not defined in parent class.");
    }

    public String getParentDocumentGroupName() {
    	throw new UnsupportedOperationException("Not defined in parent class.");
    }
    
    @Transient
    private boolean rateSynced;

    @Transient
    private transient ParameterService parameterService;

    @Transient
    private transient BudgetCalculationService budgetCalculationService;
    
    @Transient
    private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;

    @Transient
    private String activityTypeCode = "x";

    @Transient
    private boolean budgetLineItemDeleted;

    @Transient
    private boolean rateClassTypesReloaded = false;
    
    @Transient
    private Boolean viewOnly = Boolean.FALSE;

    @Transient
    private transient BusinessObjectService businessObjectService;

    public Budget() {
        super();
        budgetProjectIncomes = new ArrayList<>();
        budgetRates = new ArrayList<>();
        budgetLaRates = new ArrayList<>();
        budgetPeriods = new ArrayList<>();
        budgetPersonnelDetailsList = new ArrayList<>();
        budgetUnrecoveredFandAs = new ArrayList<>();
        instituteRates = new ArrayList<>();
        instituteLaRates = new ArrayList<>();
        rateClasses = new ArrayList<>();
        rateClassTypes = new ArrayList<>();
        budgetPersons = new ArrayList<>();
        budgetCategoryTypeCodes = new ArrayList<>();
        budgetPrintForms = new ArrayList<>();
        budgetSubAwards = new ArrayList<>();
        budgetSummaryDetails = new ArrayList<>();
        budgetCostShares = new ArrayList<>();
        setOnOffCampusFlag("D");
    }

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
        final Collection<Long> periodIncomesToDelete = new ArrayList<>();
        for (final BudgetPeriod persistedPeriod : this.getPersistedBudgetPeriods()) {
            if (!this.containsBudgetPeriod(persistedPeriod.getBudgetPeriodId())) {
                periodIncomesToDelete.add(persistedPeriod.getBudgetPeriodId());
            }
        }
        this.deletePersistedProjectIncomes(periodIncomesToDelete);
        this.deleteLocalProjectIncomes(periodIncomesToDelete);
    }

    private boolean containsBudgetPeriod(final Long periodId) {
        assert periodId != null : "the periodId is null";
        for (final BudgetPeriod localPeriod : getBudgetPeriods()) {
            if (periodId.equals(localPeriod.getBudgetPeriodId())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getBudgetAdjustmentDocumentNumber() {
        return budgetAdjustmentDocumentNumber;
    }

    public void setBudgetAdjustmentDocumentNumber(String budgetAdjustmentDocumentNumber) {
        this.budgetAdjustmentDocumentNumber = budgetAdjustmentDocumentNumber;
    }

    private Collection<BudgetPeriod> getPersistedBudgetPeriods() {
        final Map<String, Object> matchCriteria = new HashMap<>();
        matchCriteria.put("budgetId", this.getBudgetId());
        final Collection<BudgetPeriod> periods = getBusinessObjectService().findMatching(BudgetPeriod.class, matchCriteria);
        return periods != null ? Collections.unmodifiableCollection(periods) : Collections.emptyList();
    }

    private void deletePersistedProjectIncomes(final Collection<Long> periodIds) {
        assert periodIds != null : "the periodIds are null";
        if (periodIds.isEmpty()) {
            return;
        }

        final Map<String, Collection<Long>> matchCriteria = new HashMap<>();
        matchCriteria.put("budgetPeriodId", periodIds);
        getBusinessObjectService().deleteMatching(BudgetProjectIncome.class, matchCriteria);
    }

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

    public Boolean isCostSharingApplicable() {
        return loadCostSharingApplicability();
    }

    public Boolean isCostSharingEnforced() {
        return loadCostSharingEnforcement();
    }

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

    @Override
    public RateClass getRateClass() {
        return rateClass;
    }

    @Override
    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    @Override
    public List<BudgetPeriod> getBudgetPeriods() {
        return budgetPeriods;
    }

    public BudgetSummaryService getBudgetSummaryService() {
        return KcServiceLocator.getService(BudgetSummaryService.class);
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
        List<BudgetLineItem> budgetLineItems = new ArrayList<>();
        List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = new ArrayList<>();
        List<BudgetFormulatedCostDetail> budgetFormulatedCosts = new ArrayList<>();
        List<BudgetRateAndBase> budgetRateAndBaseList = new ArrayList<>();
        List<BudgetPersonnelDetails> bPersonnelDetailsList = new ArrayList<>();
        List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts = new ArrayList<>();
        List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList = new ArrayList<>();
        List<BudgetModularIdc> budgetModularIdcs = new ArrayList<>();
        List<BudgetModular> budgetModular = new ArrayList<>();
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
        List<BudgetSubAwardFiles> subAwardFiles = new ArrayList<>();
        List<BudgetSubAwardAttachment> subAwardAttachments = new ArrayList<>();
        List<BudgetSubAwardPeriodDetail> subAwardPeriodDetails = new ArrayList<>();
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

    @Override
    public List<BudgetRate> getBudgetRates() {
        return budgetRates;
    }

    public void setBudgetRates(List<BudgetRate> budgetRates) {
        this.budgetRates = budgetRates;
    }

    @Override
    public List<BudgetLaRate> getBudgetLaRates() {
        return budgetLaRates;
    }

    public void setBudgetLaRates(List<BudgetLaRate> budgetLaRates) {
        this.budgetLaRates = budgetLaRates;
    }

    public List<AbstractBudgetRate> getAllBudgetRates() {
    	ArrayList<AbstractBudgetRate> result = new ArrayList<>();
    	result.addAll(getBudgetRates());
    	result.addAll(getBudgetLaRates());
    	return result;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public List<InstituteRate> getInstituteRates() {
        return instituteRates;
    }

    public void setInstituteRates(List<InstituteRate> instituteRates) {
        this.instituteRates = instituteRates;
    }

    public BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }

    public List<RateClass> getRateClasses() {
        return rateClasses;
    }

    public void setRateClasses(List<RateClass> rateClasses) {
        this.rateClasses = rateClasses;
    }

    public List<RateClassType> getRateClassTypes() {
        if (rateClassTypes.isEmpty() && !rateClassTypesReloaded && (!this.getBudgetRates().isEmpty() || !this.getBudgetLaRates().isEmpty())) {
            getBudgetRatesService().syncBudgetRateCollectionsToExistingRates(this.rateClassTypes, this);
        } else if (rateClassTypesReloaded) {
            if (!rateClassTypes.isEmpty()) {
                rateClassTypes.clear();
            }
            rateClassTypesReloaded = false;
            getBudgetRatesService().getBudgetRates(this.rateClassTypes, this);
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

    @Override
    public List<BudgetProjectIncome> getBudgetProjectIncomes() {
        return budgetProjectIncomes;
    }

    public BudgetProjectIncome getBudgetProjectIncome(int index) {
        while (getBudgetProjectIncomes().size() <= index) {
            getBudgetProjectIncomes().add(new BudgetProjectIncome());
        }
        return getBudgetProjectIncomes().get(index);
    }

    public void setBudgetCostShares(List<BudgetCostShare> budgetCostShares) {
        this.budgetCostShares = budgetCostShares;
    }

    public void setBudgetUnrecoveredFandAs(List<BudgetUnrecoveredFandA> budgetUnrecoveredFandAs) {
        this.budgetUnrecoveredFandAs = budgetUnrecoveredFandAs;
    }

    public void setBudgetProjectIncomes(List<BudgetProjectIncome> budgetProjectIncomes) {
        this.budgetProjectIncomes = budgetProjectIncomes;
    }

    public void add(BudgetCostShare budgetCostShare) {
        if (budgetCostShare != null) {
            budgetCostShare.setBudgetId(getBudgetId());
            budgetCostShare.setBudget(this);
            budgetCostShare.setDocumentComponentId(getNextValue(budgetCostShare.getDocumentComponentIdKey()));
            budgetCostShares.add(budgetCostShare);
        } else {
            LOG.warn("Attempt to add null budgetCostShare was ignored.");
        }
    }

    public List<DocumentNextvalue> getNextValues() {
    	throw new UnsupportedOperationException("Not supported in Budget parent class");
    }

    public DocumentNextvalue getNewNextValue() {
    	throw new UnsupportedOperationException("Not supported in Budget parent class");
    }

    public void add(DocumentNextvalue nextValue) {
    	throw new UnsupportedOperationException("Not supported in Budget parent class");
    }

    public Integer getNextValue(String propertyName) {
        Integer propNextValue = 1;
        // search for property and get the latest number - increment for next call 
        for (Object element : getNextValues()) {
            DocumentNextvalue nextValue = (DocumentNextvalue) element;
            if (nextValue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = nextValue.getNextValue();
                nextValue.setNextValue(propNextValue + 1);
            }
        }

        // property does not exist - set initial value and increment for next call 
        if (propNextValue == 1) {
            DocumentNextvalue nextValue = getNewNextValue();
        	nextValue.setNextValue(propNextValue + 1);
        	nextValue.setPropertyName(propertyName);
            add(nextValue);
        }
        return propNextValue;
    }
    
    
    public boolean isProposalBudget() {
        return getBudgetParent().isProposalBudget();
    }

    public void add(BudgetProjectIncome budgetProjectIncome) {

        if (budgetProjectIncome != null) {
            budgetProjectIncome.setBudgetId(getBudgetId());
            budgetProjectIncome.setDocumentComponentId(getNextValue(budgetProjectIncome.getDocumentComponentIdKey()));
            budgetProjectIncomes.add(budgetProjectIncome);

            budgetProjectIncome.setBudgetPeriod(getBudgetPeriod(budgetProjectIncome));
        } else {
            LOG.warn("Attempt to add null budgetProjectIncome was ignored.");
        }
    }

    public BudgetPeriod getBudgetPeriod(BudgetProjectIncome budgetProjectIncome) {
        List<BudgetPeriod> bPeriods = getBudgetPeriods();
        if (bPeriods != null && bPeriods.size() > 0) {
            for (BudgetPeriod bPeriod : bPeriods) {
                if (bPeriod.getBudgetPeriod() != null && budgetProjectIncome.getBudgetPeriodNumber() != null && bPeriod.getBudgetPeriod().intValue() == budgetProjectIncome.getBudgetPeriodNumber().intValue()) {
                    return bPeriod;
                }
            }
        }
        return null;
    }

    public void add(BudgetRate budgetRate) {
        if (budgetRate != null) {
            getBudgetRates().add(budgetRate);
        }
    }

    public void add(BudgetLaRate budgetLaRate) {
        if (budgetLaRate != null) {
            getBudgetLaRates().add(budgetLaRate);
        }
    }

    public void add(BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        if (budgetUnrecoveredFandA != null) {
            budgetUnrecoveredFandA.setBudgetId(getBudgetId());
            budgetUnrecoveredFandA.setBudget(this);
            budgetUnrecoveredFandA.setDocumentComponentId(getNextValue(budgetUnrecoveredFandA.getDocumentComponentIdKey()));
            budgetUnrecoveredFandAs.add(budgetUnrecoveredFandA);
        } else {
            LOG.warn("Attempt to add null budgetUnrecoveredFandA was ignored.");
        }
    }

    @Override
    public List<BudgetPerson> getBudgetPersons() {
    	Collections.sort(budgetPersons, new PersonRolodexComparator());
        return budgetPersons;
    }

    public void setBudgetPersons(List<BudgetPerson> budgetPersons) {
        this.budgetPersons = budgetPersons;
    }

    public BudgetPerson getBudgetPerson(int index) {
        while (getBudgetPersons().size() <= index) {
            getBudgetPersons().add(new BudgetPerson());
        }
        return getBudgetPersons().get(index);
    }

    public void addBudgetPerson(BudgetPerson budgetPerson) {
        getBudgetPersons().add(budgetPerson);
        budgetPerson.setBudget(this);
    }

    public void add(BudgetPeriod budgetPeriod) {
        getBudgetPeriods().add(budgetPeriod);
    }

    public BudgetCostShare removeBudgetCostShare(int index) {
        return getBudgetCostShares().remove(index);
    }

    public BudgetProjectIncome removeBudgetProjectIncome(int index) {
        return getBudgetProjectIncomes().remove(index);
    }

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

    @Override
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

    @Override
    public List<BudgetUnrecoveredFandA> getBudgetUnrecoveredFandAs() {
        return budgetUnrecoveredFandAs;
    }

    public int getBudgetUnrecoveredFandACount() {
        return getCollectionSize(budgetUnrecoveredFandAs);
    }

    public void getBudgetTotals() {
    	getBudgetCalculationService().calculateBudgetSummaryTotals(this);
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

    public Date loadFiscalYearStart() {
        return createDateFromString(getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_CURRENT_FISCAL_YEAR));
    }

    public boolean getSalaryInflationEnabled() {
        return getParameterService().getParameterValueAsString(Budget.class, Constants.ENABLE_SALARY_INFLATION_ANNIV_DATE).equals(PARAM_VALUE_ENABLED);
    }

    protected Boolean loadCostSharingApplicability() {
        return getBooleanValue(Constants.BUDGET_COST_SHARING_APPLICABILITY_FLAG);
    }

    protected Boolean loadUnrecoveredFandAApplicability() {
        return getBooleanValue(Constants.BUDGET_UNRECOVERED_F_AND_A_APPLICABILITY_FLAG);
    }

    protected Boolean loadCostSharingEnforcement() {
        return getBooleanValue(Constants.BUDGET_COST_SHARING_ENFORCEMENT_FLAG);
    }

    protected Boolean loadUnrecoveredFandAEnforcement() {
        return getBooleanValue(Constants.BUDGET_UNRECOVERED_F_AND_A_ENFORCEMENT_FLAG);
    }

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

    private List<ScaleTwoDecimal> findProjectIncomeTotalsForBudgetPeriods(Map<Integer, ScaleTwoDecimal> incomes) {
        List<ScaleTwoDecimal> periodIncomeTotals = new ArrayList<>(budgetPeriods.size());
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            ScaleTwoDecimal periodIncomeTotal = incomes.get(budgetPeriod.getBudgetPeriod());
            if (periodIncomeTotal == null) {
                periodIncomeTotal = ScaleTwoDecimal.ZERO;
            }
            periodIncomeTotals.add(periodIncomeTotal);
        }
        return periodIncomeTotals;
    }

    private int getCollectionSize(Collection collection) {
        return collection != null ? collection.size() : 0;
    }

    private Date getFiscalYearStart() {
        return loadFiscalYearStart();
    }

    protected Boolean getBooleanValue(String parmName) {
        String parmValue;
        if (!getParameterService().parameterExists(Budget.class, parmName)) {
            parmValue = FALSE_FLAG;
        } else {
            parmValue = getParameterService().getParameterValueAsString(Budget.class, parmName);
        }
        return parmValue.equalsIgnoreCase(TRUE_FLAG);
    }

    public Map<Integer, ScaleTwoDecimal> mapProjectIncomeTotalsToBudgetPeriodNumbers() {
        Map<Integer, ScaleTwoDecimal> budgetPeriodProjectIncomeMap = new TreeMap<>();
        for (BudgetProjectIncome budgetProjectIncome : budgetProjectIncomes) {
            Integer budgetPeriodNumber = budgetProjectIncome.getBudgetPeriodNumber();
            ScaleTwoDecimal amount = budgetPeriodProjectIncomeMap.get(budgetPeriodNumber);
            amount = (amount == null) ? budgetProjectIncome.getProjectIncome() : amount.add(budgetProjectIncome.getProjectIncome());
            budgetPeriodProjectIncomeMap.put(budgetPeriodNumber, amount);
        }
        return budgetPeriodProjectIncomeMap;
    }

    private Map<Integer, List<BudgetPeriod>> mapBudgetPeriodsToFiscalYears() {
        Map<Integer, List<BudgetPeriod>> budgetPeriodFiscalYears = new TreeMap<>();
        for (BudgetPeriod budgetPeriod : getBudgetPeriods()) {
            Integer fiscalYear = budgetPeriod.calculateFiscalYear(getFiscalYearStart());
            List<BudgetPeriod> budgetPeriodsInFiscalYear = budgetPeriodFiscalYears.get(fiscalYear);
            if (budgetPeriodsInFiscalYear == null) {
                budgetPeriodsInFiscalYear = new ArrayList<>();
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

    public static class FiscalYearSummary {

        private int fiscalYear;

        private BudgetPeriod assignedBudgetPeriod;

        private ScaleTwoDecimal costShare;

        private ScaleTwoDecimal unrecoveredFandA;

        private FiscalYearApplicableRate fiscalYearRates;

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

    @Override
    public String getBudgetJustification() {
        return budgetJustification;
    }

    public void setBudgetJustification(String budgetJustification) {
        this.budgetJustification = budgetJustification;
    }

    public String getOnOffCampusFlagDescription() {
        return getBudgetSummaryService().getOnOffCampusFlagDescription(getOnOffCampusFlag());
    }

    public boolean isBudgetLineItemDeleted() {
        return budgetLineItemDeleted;
    }

    public void setBudgetLineItemDeleted(boolean budgetLineItemDeleted) {
        this.budgetLineItemDeleted = budgetLineItemDeleted;
    }

    public List<BudgetPrintForm> getBudgetPrintForms() {
        return budgetPrintForms;
    }

    public void setBudgetPrintForms(List<BudgetPrintForm> budgetPrintForms) {
        this.budgetPrintForms = budgetPrintForms;
    }

    public boolean isRateClassTypesReloaded() {
        return rateClassTypesReloaded;
    }

    public void setRateClassTypesReloaded(boolean rateClassTypesReloaded) {
        this.rateClassTypesReloaded = rateClassTypesReloaded;
    }

    @Override
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

    public final ScaleTwoDecimal getSumUnderreoveryAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getUnderrecoveryAmount());
        }
        return amount;
    }

    public final ScaleTwoDecimal getSumCostSharingAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getCostSharingAmount());
        }
        return amount;
    }

    public ScaleTwoDecimal getSumDirectCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalDirectCost());
        }
        return amount;
    }

    public final ScaleTwoDecimal getSumIndirectCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalIndirectCost());
        }
        return amount;
    }

    public final ScaleTwoDecimal getSumTotalCostAmountFromPeriods() {
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (final BudgetPeriod period : this.getBudgetPeriods()) {
            amount = amount.add(period.getTotalCost());
        }
        return amount;
    }

    public boolean getOhRatesNonEditable() {
        return false;
    }

    public boolean getEbRatesNonEditable() {
        return false;
    }

    public BudgetPeriod getNewBudgetPeriod() {
        return new BudgetPeriod();
    }

    public BudgetLineItem getNewBudgetLineItem() {
        return new BudgetLineItem();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((activityTypeCode == null) ? 0 : activityTypeCode.hashCode());
        result = prime * result + ((budgetCategoryTypeCodes == null) ? 0 : budgetCategoryTypeCodes.hashCode());
        result = prime * result + ((budgetCostShares == null) ? 0 : budgetCostShares.hashCode());
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
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Budget other = (Budget) obj;
        if (activityTypeCode == null) {
            if (other.activityTypeCode != null)
                return false;
        } else if (!activityTypeCode.equals(other.activityTypeCode))
            return false;
        if (budgetCategoryTypeCodes == null) {
            if (other.budgetCategoryTypeCodes != null)
                return false;
        } else if (!budgetCategoryTypeCodes.equals(other.budgetCategoryTypeCodes))
            return false;
        if (budgetCostShares == null) {
            if (other.budgetCostShares != null)
                return false;
        } else if (!budgetCostShares.equals(other.budgetCostShares))
            return false;
        if (budgetJustification == null) {
            if (other.budgetJustification != null)
                return false;
        } else if (!budgetJustification.equals(other.budgetJustification))
            return false;
        if (budgetLaRates == null) {
            if (other.budgetLaRates != null)
                return false;
        } else if (!budgetLaRates.equals(other.budgetLaRates))
            return false;
        if (budgetLineItemDeleted != other.budgetLineItemDeleted)
            return false;
        if (budgetPeriods == null) {
            if (other.budgetPeriods != null)
                return false;
        } else if (!budgetPeriods.equals(other.budgetPeriods))
            return false;
        if (budgetPersonnelDetailsList == null) {
            if (other.budgetPersonnelDetailsList != null)
                return false;
        } else if (!budgetPersonnelDetailsList.equals(other.budgetPersonnelDetailsList))
            return false;
        if (budgetPersons == null) {
            if (other.budgetPersons != null)
                return false;
        } else if (!budgetPersons.equals(other.budgetPersons))
            return false;
        if (budgetPrintForms == null) {
            if (other.budgetPrintForms != null)
                return false;
        } else if (!budgetPrintForms.equals(other.budgetPrintForms))
            return false;
        if (budgetProjectIncomes == null) {
            if (other.budgetProjectIncomes != null)
                return false;
        } else if (!budgetProjectIncomes.equals(other.budgetProjectIncomes))
            return false;
        if (budgetRates == null) {
            if (other.budgetRates != null)
                return false;
        } else if (!budgetRates.equals(other.budgetRates))
            return false;
        if (budgetSubAwards == null) {
            if (other.budgetSubAwards != null)
                return false;
        } else if (!budgetSubAwards.equals(other.budgetSubAwards))
            return false;
        if (budgetSummaryTotals == null) {
            if (other.budgetSummaryTotals != null)
                return false;
        } else if (!budgetSummaryTotals.equals(other.budgetSummaryTotals))
            return false;
        if (budgetUnrecoveredFandAs == null) {
            if (other.budgetUnrecoveredFandAs != null)
                return false;
        } else if (!budgetUnrecoveredFandAs.equals(other.budgetUnrecoveredFandAs))
            return false;
        if (calculatedExpenseTotals == null) {
            if (other.calculatedExpenseTotals != null)
                return false;
        } else if (!calculatedExpenseTotals.equals(other.calculatedExpenseTotals))
            return false;
        if (instituteLaRates == null) {
            if (other.instituteLaRates != null)
                return false;
        } else if (!instituteLaRates.equals(other.instituteLaRates))
            return false;
        if (instituteRates == null) {
            if (other.instituteRates != null)
                return false;
        } else if (!instituteRates.equals(other.instituteRates))
            return false;
        if (nonPersonnelCalculatedExpenseTotals == null) {
            if (other.nonPersonnelCalculatedExpenseTotals != null)
                return false;
        } else if (!nonPersonnelCalculatedExpenseTotals.equals(other.nonPersonnelCalculatedExpenseTotals))
            return false;
        if (objectCodeListByBudgetCategoryType == null) {
            if (other.objectCodeListByBudgetCategoryType != null)
                return false;
        } else if (!objectCodeListByBudgetCategoryType.equals(other.objectCodeListByBudgetCategoryType))
            return false;
        if (objectCodePersonnelFringeTotals == null) {
            if (other.objectCodePersonnelFringeTotals != null)
                return false;
        } else if (!objectCodePersonnelFringeTotals.equals(other.objectCodePersonnelFringeTotals))
            return false;
        if (objectCodePersonnelList == null) {
            if (other.objectCodePersonnelList != null)
                return false;
        } else if (!objectCodePersonnelList.equals(other.objectCodePersonnelList))
            return false;
        if (objectCodePersonnelSalaryTotals == null) {
            if (other.objectCodePersonnelSalaryTotals != null)
                return false;
        } else if (!objectCodePersonnelSalaryTotals.equals(other.objectCodePersonnelSalaryTotals))
            return false;
        if (objectCodeTotals == null) {
            if (other.objectCodeTotals != null)
                return false;
        } else if (!objectCodeTotals.equals(other.objectCodeTotals))
            return false;
        if (personnelCalculatedExpenseTotals == null) {
            if (other.personnelCalculatedExpenseTotals != null)
                return false;
        } else if (!personnelCalculatedExpenseTotals.equals(other.personnelCalculatedExpenseTotals))
            return false;
        if (rateClass == null) {
            if (other.rateClass != null)
                return false;
        } else if (!rateClass.equals(other.rateClass))
            return false;
        if (rateClassTypes == null) {
            if (other.rateClassTypes != null)
                return false;
        } else if (!rateClassTypes.equals(other.rateClassTypes))
            return false;
        if (rateClassTypesReloaded != other.rateClassTypesReloaded)
            return false;
        if (rateClasses == null) {
            if (other.rateClasses != null)
                return false;
        } else if (!rateClasses.equals(other.rateClasses))
            return false;
        if (rateSynced != other.rateSynced)
            return false;
        if (summaryPeriodEndDate == null) {
            if (other.summaryPeriodEndDate != null)
                return false;
        } else if (!summaryPeriodEndDate.equals(other.summaryPeriodEndDate))
            return false;
        if (summaryPeriodStartDate == null) {
            if (other.summaryPeriodStartDate != null)
                return false;
        } else if (!summaryPeriodStartDate.equals(other.summaryPeriodStartDate))
            return false;
        return true;
    }

    public BudgetPersonnelDetails getNewBudgetPersonnelLineItem() {
        return new BudgetPersonnelDetails();
    }

    public boolean isCostSharingSubmissionEnabled() {
        return getParameterService().getParameterValueAsString(Budget.class, Constants.ENABLE_COST_SHARE_SUBMIT).equals(PARAM_VALUE_ENABLED);
    }

    public String getSummaryNumberOfMonths() {
        return String.valueOf(this.getProposalBudgetNumberOfMonthsService().getNumberOfMonth(this.getSummaryPeriodStartDate(), this.getSummaryPeriodEndDate()));
    }

    protected ProposalBudgetNumberOfMonthsService getProposalBudgetNumberOfMonthsService() {
        return KcServiceLocator.getService(ProposalBudgetNumberOfMonthsService.class);
    }

    private static class RateClassTypeComparator implements Comparator<RateClassType>, Serializable {

        private static final long serialVersionUID = 8230902362851330642L;

        public int compare(RateClassType rateClassType1, RateClassType rateClassType2) {
            return rateClassType1.getSortId().compareTo(rateClassType2.getSortId());
        }
    }
    
    public java.util.Date getBudgetStartDate() {
    	throw new UnsupportedOperationException();
    }

    public java.util.Date getBudgetEndDate() {
    	throw new UnsupportedOperationException();
    }

	public Boolean getViewOnly() {
		return viewOnly;
	}
	
	public Boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(Boolean viewOnly) {
		this.viewOnly = viewOnly;
	}
	
	public List<BudgetPersonnelDetails> getBudgetPersonnelDetails() {
		String personnelBudgetCategoryTypeCode = getBudgetCalculationService().getPersonnelBudgetCategoryTypeCode();
		List<BudgetPersonnelDetails> budgetPersonnelDetailsList = new ArrayList<>();
		for(BudgetPeriod budgetPeriod : getBudgetPeriods()) {
			for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
				if(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(personnelBudgetCategoryTypeCode)) {
					if(budgetLineItem.getBudgetPersonnelDetailsList().size() > 0) {
						budgetPersonnelDetailsList.addAll(budgetLineItem.getBudgetPersonnelDetailsList());
					}else {
						budgetPersonnelDetailsList.add(new BudgetPersonnelDetails(budgetLineItem));
					}
				}
			}
		}
		return budgetPersonnelDetailsList;
	}

	public List<BudgetLineItem> getBudgetLineItems() {
		String personnelBudgetCategoryTypeCode = getBudgetCalculationService().getPersonnelBudgetCategoryTypeCode();
		List<BudgetLineItem> budgetLineItems = new ArrayList<>();
		for(BudgetPeriod budgetPeriod : getBudgetPeriods()) {
		    for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
				if(!budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(personnelBudgetCategoryTypeCode)) {
					budgetLineItems.add(budgetLineItem);
		    	}
		    }
		}
		return budgetLineItems;
	}

	public List<Period> getBudgetSummaryDetails() {
		return budgetSummaryDetails;
	}

	public void setBudgetSummaryDetails(List<Period> budgetSummaryDetails) {
		this.budgetSummaryDetails = budgetSummaryDetails;
	}

    protected BudgetCalculationService getBudgetCalculationService() {
        if (this.budgetCalculationService == null) {
            this.budgetCalculationService = KcServiceLocator.getService(BudgetCalculationService.class);
        }
        return this.budgetCalculationService;
    }

    @Override
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getParentDocumentTypeCode() {
        return parentDocumentTypeCode;
    }

    public void setParentDocumentTypeCode(String parentDocumentTypeCode) {
        this.parentDocumentTypeCode = parentDocumentTypeCode;
    }


    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }

        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
