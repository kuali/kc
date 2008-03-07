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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.format.Formatter;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.service.BudgetRatesService;
import org.kuali.kra.budget.service.BudgetSummaryService;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalStatus;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetDocument extends ResearchDocumentBase implements Copyable, SessionDocument {
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
    
    public BudgetDocument(){
        super();
        budgetProjectIncomes = new ArrayList<BudgetProjectIncome>();
        budgetProposalRates = new ArrayList<BudgetProposalRate>();
        budgetProposalLaRates = new ArrayList<BudgetProposalLaRate>();
        budgetPeriods = new ArrayList<BudgetPeriod>();
        budgetLineItems = new ArrayList<BudgetLineItem>();
        budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
        instituteRates = new ArrayList<InstituteRate>();
        instituteLaRates = new ArrayList<InstituteLaRate>();
        rateClasses = new ArrayList<RateClass>();
        rateClassTypes = new ArrayList<RateClassType>();
        Formatter.registerFormatter(BudgetDecimal.class, BudgetDecimalFormatter.class);
        budgetPersons = new ArrayList<BudgetPerson>();
    }

    public void initialize() {
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
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getBudgetPeriods());
//        managedLists.add(getBudgetProjectIncomes());
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

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
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

    public void add(BudgetProjectIncome budgetProjectIncome) {
        if(budgetProjectIncome != null) {
            budgetProjectIncome.setProposalNumber(getProposalNumber());
            budgetProjectIncome.setBudgetVersionNumber(getBudgetVersionNumber());
            this.getBudgetProjectIncomes().add(budgetProjectIncome);
        } else {
            LOG.warn("Attempt to add a null budgetProjectIncome was ignored");
        }
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
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeUpdate(persistenceBroker);
        
        // Update the status.
        ProposalStatus proposalStatus = getProposalStatus(getProposalNumber());
        if (proposalStatus != null) {
            proposalStatus.setBudgetStatusCode(getProposal().getBudgetStatus());
            KraServiceLocator.getService(BusinessObjectService.class).save(proposalStatus);
        }
    }
    
    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        
//      Retrieve the status.
        ProposalStatus proposalStatus = getProposalStatus(getProposalNumber());
        if (proposalStatus != null) {
            getProposal().setBudgetStatus(proposalStatus.getBudgetStatusCode());
        }
    }

    public void add(BudgetPeriod budgetPeriod) {
        budgetPeriod.setBudgetVersionNumber(getBudgetVersionNumber());
        getBudgetPeriods().add(budgetPeriod);        
    }

    public void remove(BudgetProjectIncome budgetProjectIncome) {
        getBudgetProjectIncomes().remove(budgetProjectIncome);
    }

    public final List<InstituteLaRate> getInstituteLaRates() {
        return instituteLaRates;
    }

    public final void setInstituteLaRates(List<InstituteLaRate> instituteLaRates) {
        this.instituteLaRates = instituteLaRates;
    }

}
