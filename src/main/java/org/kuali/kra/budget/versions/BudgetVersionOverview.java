/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.versions;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.infrastructure.DeepCopyIgnore;
import org.kuali.kra.infrastructure.DeepCopyIgnoreScopes;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Class representation of a Budget Overview Business Object.  This BO maps to
 * the BudgetDocument table but excludes most references.
 * 
 * @author kra-developers-l@indiana.edu
 */
public class BudgetVersionOverview extends KraPersistableBusinessObjectBase implements Comparable<BudgetVersionOverview> {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4997453399414404715L;
    private Integer budgetVersionNumber;
    @DeepCopyIgnore
    private Long budgetId;
    private String documentNumber;
    private String documentDescription;
    private BudgetDecimal costSharingAmount;
    private Date endDate;
    private Date startDate;
    private boolean finalVersionFlag;
    private String ohRateTypeCode;
    private String ohRateClassCode;
    private BudgetDecimal residualFunds;
    private BudgetDecimal totalCost;
    private BudgetDecimal totalDirectCost;
    private BudgetDecimal totalIndirectCost;
    private BudgetDecimal totalCostLimit;
    private BudgetDecimal underrecoveryAmount;
    private String comments;
    private boolean descriptionUpdatable;
    private RateClass rateClass;
    
    private String name;
    private String budgetStatus;
    
    
    private Boolean modularBudgetFlag;
    private String urRateClassCode;
    private String onOffCampusFlag;
    
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount == null ?  BudgetDecimal.ZERO : costSharingAmount;
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public boolean isFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    public String getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(String ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public String getOhRateClassCode() {
        return ohRateClassCode;
    }

    public void setOhRateClassCode(String ohRateClassCode) {
        this.ohRateClassCode = ohRateClassCode;
    }

    public RateClass getRateClass() {
        return this.rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

//    public String getProposalNumber() {
//        return proposalNumber;
//    }
//    
//    public void setProposalNumber(String proposalNumber) {
//        this.proposalNumber = proposalNumber;
//    }
    
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BudgetDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(BudgetDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public BudgetDecimal getTotalCost() {
        return totalCost == null ?  BudgetDecimal.ZERO : totalCost;
    }

    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }


    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }


    public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BudgetDecimal getTotalCostLimit() {
        return totalCostLimit==null?BudgetDecimal.ZERO:totalCostLimit;
    }

    public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }
    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost == null ?  new BudgetDecimal(0) : totalDirectCost;
    }
    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ?  new BudgetDecimal(0) : totalIndirectCost;
    }
    public BudgetDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ?  new BudgetDecimal(0) : underrecoveryAmount;
    }


    
    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public boolean isDescriptionUpdatable() {
        return descriptionUpdatable;
    }
    public String getDescriptionUpdatable() {
        return descriptionUpdatable?"Yes":"No";
    }

    public void setDescriptionUpdatable(boolean descriptionUpdatable) {
        this.descriptionUpdatable = descriptionUpdatable;
    }

    /**
     * @see org.kuali.rice.kns.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        // The purpose of this lookup is to get the document description from the doc header,
        // without mapping the enire doc header (which can be large) in ojb.
        super.afterLookup(persistenceBroker);
        DocumentHeader docHeader = getDocHeader();
        if (docHeader != null) {
            this.documentDescription = docHeader.getDocumentDescription();
        }
    }
    
    protected DocumentHeader getDocHeader() {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", this.documentNumber);
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        return docHeader;
    }
    
    /**
     * 
     * @see java.lang.Comparable
     */
    public int compareTo(BudgetVersionOverview otherVersion) {
        return this.budgetVersionNumber.compareTo(otherVersion.getBudgetVersionNumber());
    }

    /**
     * Gets the budgetId attribute. 
     * @return Returns the budgetId.
     */
    public Long getBudgetId() {
        return budgetId;
    }

    /**
     * Sets the budgetId attribute value.
     * @param budgetId The budgetId to set.
     */
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    /**
     * Gets the modularBudgetFlag attribute. 
     * @return Returns the modularBudgetFlag.
     */
    public Boolean getModularBudgetFlag() {
        return modularBudgetFlag;
    }

    /**
     * Sets the modularBudgetFlag attribute value.
     * @param modularBudgetFlag The modularBudgetFlag to set.
     */
    public void setModularBudgetFlag(Boolean modularBudgetFlag) {
        this.modularBudgetFlag = modularBudgetFlag;
    }

    /**
     * Gets the urRateClassCode attribute. 
     * @return Returns the urRateClassCode.
     */
    public String getUrRateClassCode() {
        return urRateClassCode;
    }

    /**
     * Sets the urRateClassCode attribute value.
     * @param urRateClassCode The urRateClassCode to set.
     */
    public void setUrRateClassCode(String urRateClassCode) {
        this.urRateClassCode = urRateClassCode;
    }

    /**
     * Gets the onOffCampusFlag attribute. 
     * @return Returns the onOffCampusFlag.
     */
    public String getOnOffCampusFlag() {
        return onOffCampusFlag;
    }

    /**
     * Sets the onOffCampusFlag attribute value.
     * @param onOffCampusFlag The onOffCampusFlag to set.
     */
    public void setOnOffCampusFlag(String onOffCampusFlag) {
        this.onOffCampusFlag = onOffCampusFlag;
    }
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();        
        hashMap.put("budgetVersionNumber", budgetVersionNumber);
        hashMap.put("comments", comments);
        hashMap.put("costSharingAmount", costSharingAmount);
        hashMap.put("endDate", endDate);
        hashMap.put("finalVersionFlag", finalVersionFlag);
        hashMap.put("modularBudgetFlag", modularBudgetFlag);
        hashMap.put("ohRateClassCode", ohRateClassCode);
        hashMap.put("ohRateTypeCode", ohRateTypeCode);
        hashMap.put("residualFunds", residualFunds);
        hashMap.put("startDate", startDate);
        hashMap.put("totalCost", totalCost);
        hashMap.put("totalCostLimit", totalCostLimit);
        hashMap.put("totalDirectCost", totalDirectCost);
        hashMap.put("totalIndirectCost", totalIndirectCost);
        hashMap.put("underrecoveryAmount", underrecoveryAmount);
        hashMap.put("urRateClassCode", urRateClassCode);
        return hashMap;
    }
    
}
