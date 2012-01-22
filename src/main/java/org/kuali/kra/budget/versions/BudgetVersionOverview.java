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
package org.kuali.kra.budget.versions;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.infrastructure.DeepCopyIgnore;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;

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

    private BudgetDecimal totalDirectCostLimit;

    private BudgetDecimal underrecoveryAmount;

    private String comments;

    private boolean descriptionUpdatable;

    private RateClass rateClass;

    private String name;

    private String budgetStatus;

    private Boolean modularBudgetFlag;

    private String urRateClassCode;

    private String onOffCampusFlag;

    private String printBudgetCommentFlag;

    private Boolean submitCostSharingFlag = Boolean.TRUE;

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount == null ? BudgetDecimal.ZERO : costSharingAmount;
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
        return totalCost == null ? BudgetDecimal.ZERO : totalCost;
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
        return totalCostLimit == null ? BudgetDecimal.ZERO : totalCostLimit;
    }

    public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost == null ? new BudgetDecimal(0) : totalDirectCost;
    }

    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ? new BudgetDecimal(0) : totalIndirectCost;
    }

    public BudgetDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ? new BudgetDecimal(0) : underrecoveryAmount;
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
        return descriptionUpdatable ? "Yes" : "No";
    }

    public void setDescriptionUpdatable(boolean descriptionUpdatable) {
        this.descriptionUpdatable = descriptionUpdatable;
    }

    /**
     * @see org.kuali.rice.krad.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    protected void postLoad() {
        // The purpose of this lookup is to get the document description from the doc header, 
        // without mapping the enire doc header (which can be large) in ojb. 
        super.postLoad();
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((budgetId == null) ? 0 : budgetId.hashCode());
        result = prime * result + ((budgetStatus == null) ? 0 : budgetStatus.hashCode());
        result = prime * result + ((budgetVersionNumber == null) ? 0 : budgetVersionNumber.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((costSharingAmount == null) ? 0 : costSharingAmount.hashCode());
        result = prime * result + (descriptionUpdatable ? 1231 : 1237);
        result = prime * result + ((documentDescription == null) ? 0 : documentDescription.hashCode());
        result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + (finalVersionFlag ? 1231 : 1237);
        result = prime * result + ((modularBudgetFlag == null) ? 0 : modularBudgetFlag.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((ohRateClassCode == null) ? 0 : ohRateClassCode.hashCode());
        result = prime * result + ((ohRateTypeCode == null) ? 0 : ohRateTypeCode.hashCode());
        result = prime * result + ((onOffCampusFlag == null) ? 0 : onOffCampusFlag.hashCode());
        result = prime * result + ((rateClass == null) ? 0 : rateClass.hashCode());
        result = prime * result + ((residualFunds == null) ? 0 : residualFunds.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
        result = prime * result + ((totalCostLimit == null) ? 0 : totalCostLimit.hashCode());
        result = prime * result + ((totalDirectCost == null) ? 0 : totalDirectCost.hashCode());
        result = prime * result + ((totalIndirectCost == null) ? 0 : totalIndirectCost.hashCode());
        result = prime * result + ((underrecoveryAmount == null) ? 0 : underrecoveryAmount.hashCode());
        result = prime * result + ((urRateClassCode == null) ? 0 : urRateClassCode.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BudgetVersionOverview other = (BudgetVersionOverview) obj;
        if (budgetId == null) {
            if (other.budgetId != null) return false;
        } else if (!budgetId.equals(other.budgetId)) return false;
        if (budgetStatus == null) {
            if (other.budgetStatus != null) return false;
        } else if (!budgetStatus.equals(other.budgetStatus)) return false;
        if (budgetVersionNumber == null) {
            if (other.budgetVersionNumber != null) return false;
        } else if (!budgetVersionNumber.equals(other.budgetVersionNumber)) return false;
        if (comments == null) {
            if (other.comments != null) return false;
        } else if (!comments.equals(other.comments)) return false;
        if (costSharingAmount == null) {
            if (other.costSharingAmount != null) return false;
        } else if (!costSharingAmount.equals(other.costSharingAmount)) return false;
        if (descriptionUpdatable != other.descriptionUpdatable) return false;
        if (documentDescription == null) {
            if (other.documentDescription != null) return false;
        } else if (!documentDescription.equals(other.documentDescription)) return false;
        if (documentNumber == null) {
            if (other.documentNumber != null) return false;
        } else if (!documentNumber.equals(other.documentNumber)) return false;
        if (endDate == null) {
            if (other.endDate != null) return false;
        } else if (!endDate.equals(other.endDate)) return false;
        if (finalVersionFlag != other.finalVersionFlag) return false;
        if (modularBudgetFlag == null) {
            if (other.modularBudgetFlag != null) return false;
        } else if (!modularBudgetFlag.equals(other.modularBudgetFlag)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (ohRateClassCode == null) {
            if (other.ohRateClassCode != null) return false;
        } else if (!ohRateClassCode.equals(other.ohRateClassCode)) return false;
        if (ohRateTypeCode == null) {
            if (other.ohRateTypeCode != null) return false;
        } else if (!ohRateTypeCode.equals(other.ohRateTypeCode)) return false;
        if (onOffCampusFlag == null) {
            if (other.onOffCampusFlag != null) return false;
        } else if (!onOffCampusFlag.equals(other.onOffCampusFlag)) return false;
        if (rateClass == null) {
            if (other.rateClass != null) return false;
        } else if (!rateClass.equals(other.rateClass)) return false;
        if (residualFunds == null) {
            if (other.residualFunds != null) return false;
        } else if (!residualFunds.equals(other.residualFunds)) return false;
        if (startDate == null) {
            if (other.startDate != null) return false;
        } else if (!startDate.equals(other.startDate)) return false;
        if (totalCost == null) {
            if (other.totalCost != null) return false;
        } else if (!totalCost.equals(other.totalCost)) return false;
        if (totalCostLimit == null) {
            if (other.totalCostLimit != null) return false;
        } else if (!totalCostLimit.equals(other.totalCostLimit)) return false;
        if (totalDirectCost == null) {
            if (other.totalDirectCost != null) return false;
        } else if (!totalDirectCost.equals(other.totalDirectCost)) return false;
        if (totalIndirectCost == null) {
            if (other.totalIndirectCost != null) return false;
        } else if (!totalIndirectCost.equals(other.totalIndirectCost)) return false;
        if (underrecoveryAmount == null) {
            if (other.underrecoveryAmount != null) return false;
        } else if (!underrecoveryAmount.equals(other.underrecoveryAmount)) return false;
        if (urRateClassCode == null) {
            if (other.urRateClassCode != null) return false;
        } else if (!urRateClassCode.equals(other.urRateClassCode)) return false;
        return true;
    }

    /**
     * Gets the totalDirectCostLimit attribute. 
     * @return Returns the totalDirectCostLimit.
     */
    public BudgetDecimal getTotalDirectCostLimit() {
        return totalDirectCostLimit == null ? BudgetDecimal.ZERO : totalDirectCostLimit;
    }

    /**
     * Sets the totalDirectCostLimit attribute value.
     * @param totalDirectCostLimit The totalDirectCostLimit to set.
     */
    public void setTotalDirectCostLimit(BudgetDecimal totalDirectCostLimit) {
        this.totalDirectCostLimit = totalDirectCostLimit;
    }

    public void setPrintBudgetCommentFlag(String printBudgetCommentFlag) {
        this.printBudgetCommentFlag = printBudgetCommentFlag;
    }

    public String getPrintBudgetCommentFlag() {
        return printBudgetCommentFlag;
    }

    /**
     * Gets the submitCostSharingFlag attribute. 
     * @return Returns the submitCostSharingFlag.
     */
    public Boolean getSubmitCostSharingFlag() {
        return submitCostSharingFlag;
    }

    /**
     * Sets the submitCostSharingFlag attribute value.
     * @param submitCostSharingFlag The submitCostSharingFlag to set.
     */
    public void setSubmitCostSharingFlag(Boolean submitCostSharingFlag) {
        this.submitCostSharingFlag = submitCostSharingFlag;
    }
}
