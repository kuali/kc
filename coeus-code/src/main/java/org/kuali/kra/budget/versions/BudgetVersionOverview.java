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
package org.kuali.kra.budget.versions;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.kra.budget.deepcopy.DeepCopyIgnore;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.service.LegacyDataAdapter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representation of a Budget Overview Business Object.  This BO maps to
 * the BudgetDocument table but excludes most references.
 * 
 * @author kra-developers-l@indiana.edu
 */
@Entity
@Table(name = "BUDGET")
public class BudgetVersionOverview extends KcPersistableBusinessObjectBase implements Comparable<BudgetVersionOverview> {


    private static final long serialVersionUID = -4997453399414404715L;

    @Column(name = "VERSION_NUMBER")
    private Integer budgetVersionNumber;

    @DeepCopyIgnore
    @PortableSequenceGenerator(name = "SEQ_BUDGET_ID")
    @GeneratedValue(generator = "SEQ_BUDGET_ID")
    @Id
    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "COST_SHARING_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal costSharingAmount;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "FINAL_VERSION_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean finalVersionFlag = Boolean.FALSE;

    @Column(name = "OH_RATE_TYPE_CODE")
    private String ohRateTypeCode;

    @Column(name = "OH_RATE_CLASS_CODE")
    private String ohRateClassCode;

    @Column(name = "RESIDUAL_FUNDS")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal residualFunds;

    @Column(name = "TOTAL_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalCost;

    @Column(name = "TOTAL_DIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalDirectCost;

    @Column(name = "TOTAL_INDIRECT_COST")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalIndirectCost;

    @Column(name = "TOTAL_COST_LIMIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalCostLimit;

    @Column(name = "TOTAL_DIRECT_COST_LIMIT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal totalDirectCostLimit;

    @Column(name = "UNDERRECOVERY_AMOUNT")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal underrecoveryAmount;

    @Column(name = "COMMENTS")
    @Lob
    private String comments;

    @Column(name = "MODULAR_BUDGET_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean modularBudgetFlag;

    @Column(name = "UR_RATE_CLASS_CODE")
    private String urRateClassCode;

    @Column(name = "ON_OFF_CAMPUS_FLAG")
    private String onOffCampusFlag;

    @Column(name = "SUBMIT_COST_SHARING")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean submitCostSharingFlag = Boolean.TRUE;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "UR_RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass urRateClass;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "OH_RATE_CLASS_CODE", referencedColumnName = "RATE_CLASS_CODE", insertable = false, updatable = false)
    private RateClass rateClass;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER", insertable = false, updatable = false)
    private BudgetDocumentVersion budgetDocumentVersion;

    @Transient
    private boolean descriptionUpdatable;

    @Transient
    private String name;

    @Transient
    private String budgetStatus;

    @Transient
    private String printBudgetCommentFlag;

    @Transient
    private String documentDescription;

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount == null ? ScaleTwoDecimal.ZERO : costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public Boolean isFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(Boolean finalVersionFlag) {
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public ScaleTwoDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(ScaleTwoDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public ScaleTwoDecimal getTotalCost() {
        return totalCost == null ? ScaleTwoDecimal.ZERO : totalCost;
    }

    public void setTotalCost(ScaleTwoDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalDirectCost(ScaleTwoDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public void setTotalIndirectCost(ScaleTwoDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public void setUnderrecoveryAmount(ScaleTwoDecimal underrecoveryAmount) {
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

    public ScaleTwoDecimal getTotalCostLimit() {
        return totalCostLimit == null ? ScaleTwoDecimal.ZERO : totalCostLimit;
    }

    public void setTotalCostLimit(ScaleTwoDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }

    public ScaleTwoDecimal getTotalDirectCost() {
        return totalDirectCost == null ? new ScaleTwoDecimal(0) : totalDirectCost;
    }

    public ScaleTwoDecimal getTotalIndirectCost() {
        return totalIndirectCost == null ? new ScaleTwoDecimal(0) : totalIndirectCost;
    }

    public ScaleTwoDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount == null ? new ScaleTwoDecimal(0) : underrecoveryAmount;
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
        LegacyDataAdapter boService = KcServiceLocator.getService(LegacyDataAdapter.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", this.documentNumber);
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        return docHeader;
    }

    @Override
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BudgetVersionOverview other = (BudgetVersionOverview) obj;
        if (budgetId == null) {
            if (other.budgetId != null)
                return false;
        } else if (!budgetId.equals(other.budgetId))
            return false;
        if (budgetStatus == null) {
            if (other.budgetStatus != null)
                return false;
        } else if (!budgetStatus.equals(other.budgetStatus))
            return false;
        if (budgetVersionNumber == null) {
            if (other.budgetVersionNumber != null)
                return false;
        } else if (!budgetVersionNumber.equals(other.budgetVersionNumber))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (costSharingAmount == null) {
            if (other.costSharingAmount != null)
                return false;
        } else if (!costSharingAmount.equals(other.costSharingAmount))
            return false;
        if (descriptionUpdatable != other.descriptionUpdatable)
            return false;
        if (documentDescription == null) {
            if (other.documentDescription != null)
                return false;
        } else if (!documentDescription.equals(other.documentDescription))
            return false;
        if (documentNumber == null) {
            if (other.documentNumber != null)
                return false;
        } else if (!documentNumber.equals(other.documentNumber))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (finalVersionFlag != other.finalVersionFlag)
            return false;
        if (modularBudgetFlag == null) {
            if (other.modularBudgetFlag != null)
                return false;
        } else if (!modularBudgetFlag.equals(other.modularBudgetFlag))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (ohRateClassCode == null) {
            if (other.ohRateClassCode != null)
                return false;
        } else if (!ohRateClassCode.equals(other.ohRateClassCode))
            return false;
        if (ohRateTypeCode == null) {
            if (other.ohRateTypeCode != null)
                return false;
        } else if (!ohRateTypeCode.equals(other.ohRateTypeCode))
            return false;
        if (onOffCampusFlag == null) {
            if (other.onOffCampusFlag != null)
                return false;
        } else if (!onOffCampusFlag.equals(other.onOffCampusFlag))
            return false;
        if (rateClass == null) {
            if (other.rateClass != null)
                return false;
        } else if (!rateClass.equals(other.rateClass))
            return false;
        if (residualFunds == null) {
            if (other.residualFunds != null)
                return false;
        } else if (!residualFunds.equals(other.residualFunds))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (totalCost == null) {
            if (other.totalCost != null)
                return false;
        } else if (!totalCost.equals(other.totalCost))
            return false;
        if (totalCostLimit == null) {
            if (other.totalCostLimit != null)
                return false;
        } else if (!totalCostLimit.equals(other.totalCostLimit))
            return false;
        if (totalDirectCost == null) {
            if (other.totalDirectCost != null)
                return false;
        } else if (!totalDirectCost.equals(other.totalDirectCost))
            return false;
        if (totalIndirectCost == null) {
            if (other.totalIndirectCost != null)
                return false;
        } else if (!totalIndirectCost.equals(other.totalIndirectCost))
            return false;
        if (underrecoveryAmount == null) {
            if (other.underrecoveryAmount != null)
                return false;
        } else if (!underrecoveryAmount.equals(other.underrecoveryAmount))
            return false;
        if (urRateClassCode == null) {
            if (other.urRateClassCode != null)
                return false;
        } else if (!urRateClassCode.equals(other.urRateClassCode))
            return false;
        return true;
    }

    /**
     * Gets the totalDirectCostLimit attribute. 
     * @return Returns the totalDirectCostLimit.
     */
    public ScaleTwoDecimal getTotalDirectCostLimit() {
        return totalDirectCostLimit == null ? ScaleTwoDecimal.ZERO : totalDirectCostLimit;
    }

    /**
     * Sets the totalDirectCostLimit attribute value.
     * @param totalDirectCostLimit The totalDirectCostLimit to set.
     */
    public void setTotalDirectCostLimit(ScaleTwoDecimal totalDirectCostLimit) {
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

    /**
     * Gets the urRateClass attribute. 
     * @return Returns the urRateClass.
     */
    public RateClass getUrRateClass() {
        return urRateClass;
    }

    /**
     * Sets the urRateClass attribute value.
     * @param urRateClass The urRateClass to set.
     */
    public void setUrRateClass(RateClass urRateClass) {
        this.urRateClass = urRateClass;
    }

    public BudgetDocumentVersion getBudgetDocumentVersion() {
        return budgetDocumentVersion;
    }

    public void setBudgetDocumentVersion(BudgetDocumentVersion budgetDocumentVersion) {
        this.budgetDocumentVersion = budgetDocumentVersion;
    }
}
