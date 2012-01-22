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
package org.kuali.kra.timeandmoney;

import java.sql.Date;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class AwardHierarchyNode extends AwardHierarchy { 
    
    private Date currentFundEffectiveDate;
    private Date obligationExpirationDate;
    private Date finalExpirationDate;
    private Date projectStartDate;
    private KualiDecimal anticipatedTotalAmount; 
    private KualiDecimal anticipatedTotalDirect; 
    private KualiDecimal anticipatedTotalIndirect; 
    private KualiDecimal antDistributableAmount;
    private KualiDecimal amountObligatedToDate; 
    private KualiDecimal obligatedTotalDirect; 
    private KualiDecimal obligatedTotalIndirect; 
    private KualiDecimal obliDistributableAmount;
    private String leadUnitName;
    private String principalInvestigatorName;
    private String accountNumber;
    private Integer awardStatusCode;
    private String title;
    private Long awardId;
    private Boolean awardDocumentFinalStatus;
    private String awardDocumentNumber;
    private Boolean hasChildren;
    

    public AwardHierarchyNode() {
        anticipatedTotalAmount = new KualiDecimal("0");
        antDistributableAmount = new KualiDecimal("0");
        amountObligatedToDate = new KualiDecimal("0");
        obliDistributableAmount = new KualiDecimal("0");
        anticipatedTotalDirect = new KualiDecimal("0");
        anticipatedTotalIndirect = new KualiDecimal("0");
        obligatedTotalDirect = new KualiDecimal("0");
        obligatedTotalIndirect = new KualiDecimal("0");
    }

    /**
     * Gets the currentFundEffectiveDate attribute. 
     * @return Returns the currentFundEffectiveDate.
     */
    public Date getCurrentFundEffectiveDate() {
        return currentFundEffectiveDate;
    }

    /**
     * Sets the currentFundEffectiveDate attribute value.
     * @param currentFundEffectiveDate The currentFundEffectiveDate to set.
     */
    public void setCurrentFundEffectiveDate(Date currentFundEffectiveDate) {
        this.currentFundEffectiveDate = currentFundEffectiveDate;
    }

    /**
     * Gets the obligationExpirationDate attribute. 
     * @return Returns the obligationExpirationDate.
     */
    public Date getObligationExpirationDate() {
        return obligationExpirationDate;
    }

    /**
     * Sets the obligationExpirationDate attribute value.
     * @param obligationExpirationDate The obligationExpirationDate to set.
     */
    public void setObligationExpirationDate(Date obligationExpirationDate) {
        this.obligationExpirationDate = obligationExpirationDate;
    }

    /**
     * Gets the finalExpirationDate attribute. 
     * @return Returns the finalExpirationDate.
     */
    public Date getFinalExpirationDate() {
        return finalExpirationDate;
    }

    /**
     * Sets the finalExpirationDate attribute value.
     * @param finalExpirationDate The finalExpirationDate to set.
     */
    public void setFinalExpirationDate(Date finalExpirationDate) {
        this.finalExpirationDate = finalExpirationDate;
    }

    /**
     * Gets the anticipatedTotalAmount attribute. 
     * @return Returns the anticipatedTotalAmount.
     */
    public KualiDecimal getAnticipatedTotalAmount() {
        if(anticipatedTotalAmount == null){
            setAnticipatedTotalAmount(new KualiDecimal(0));
        }
        return anticipatedTotalAmount;
    }

    /**
     * Sets the anticipatedTotalAmount attribute value.
     * @param anticipatedTotalAmount The anticipatedTotalAmount to set.
     */
    public void setAnticipatedTotalAmount(KualiDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
    }

    /**
     * Gets the antDistributableAmount attribute. 
     * @return Returns the antDistributableAmount.
     */
    public KualiDecimal getAntDistributableAmount() {
        if(antDistributableAmount == null){
            setAntDistributableAmount(new KualiDecimal(0));
        }
        return antDistributableAmount;
    }
    
    public String getAwardNumber() {
        return super.getAwardNumber();
    }

    /**
     * Sets the antDistributableAmount attribute value.
     * @param antDistributableAmount The antDistributableAmount to set.
     */
    public void setAntDistributableAmount(KualiDecimal antDistributableAmount) {
        this.antDistributableAmount = antDistributableAmount;
    }

    /**
     * Gets the amountObligatedToDate attribute. 
     * @return Returns the amountObligatedToDate.
     */
    public KualiDecimal getAmountObligatedToDate() {
        if(amountObligatedToDate == null){
            setAmountObligatedToDate(new KualiDecimal(0));
        }
        return amountObligatedToDate;
    }

    /**
     * Sets the amountObligatedToDate attribute value.
     * @param amountObligatedToDate The amountObligatedToDate to set.
     */
    public void setAmountObligatedToDate(KualiDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
    }

    /**
     * Gets the obliDistributableAmount attribute. 
     * @return Returns the obliDistributableAmount.
     */
    public KualiDecimal getObliDistributableAmount() {
        if(obliDistributableAmount == null){
            setObliDistributableAmount(new KualiDecimal(0));
        }
        return obliDistributableAmount;
        //return getAward().calculateObligatedDistributedAmountTotal();
    }

    /**
     * Sets the obliDistributableAmount attribute value.
     * @param obliDistributableAmount The obliDistributableAmount to set.
     */
    public void setObliDistributableAmount(KualiDecimal obliDistributableAmount) {
        this.obliDistributableAmount = obliDistributableAmount;
    }

    /**
     * Gets the leadUnitName attribute. 
     * @return Returns the leadUnitName.
     */
    public String getLeadUnitName() {
        return leadUnitName;
    }

    /**
     * Sets the leadUnitName attribute value.
     * @param leadUnitName The leadUnitName to set.
     */
    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }

    /**
     * Gets the principalInvestigatorName attribute. 
     * @return Returns the principalInvestigatorName.
     */
    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    /**
     * Sets the principalInvestigatorName attribute value.
     * @param principalInvestigatorName The principalInvestigatorName to set.
     */
    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    /**
     * Gets the accountNumber attribute. 
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute value.
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the awardStatusCode attribute. 
     * @return Returns the awardStatusCode.
     */
    public Integer getAwardStatusCode() {
        return awardStatusCode;
    }

    /**
     * Sets the awardStatusCode attribute value.
     * @param awardStatusCode The awardStatusCode to set.
     */
    public void setAwardStatusCode(Integer awardStatusCode) {
        this.awardStatusCode = awardStatusCode;
    }

    /**
     * Gets the projectStartDate attribute. 
     * @return Returns the projectStartDate.
     */
    public Date getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * Sets the projectStartDate attribute value.
     * @param projectStartDate The projectStartDate to set.
     */
    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
    
    

    /**
     * Gets the anticipatedTotalDirect attribute. 
     * @return Returns the anticipatedTotalDirect.
     */
    public KualiDecimal getAnticipatedTotalDirect() {
        return anticipatedTotalDirect;
    }

    /**
     * Sets the anticipatedTotalDirect attribute value.
     * @param anticipatedTotalDirect The anticipatedTotalDirect to set.
     */
    public void setAnticipatedTotalDirect(KualiDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    /**
     * Gets the anticipatedTotalIndirect attribute. 
     * @return Returns the anticipatedTotalIndirect.
     */
    public KualiDecimal getAnticipatedTotalIndirect() {
        return anticipatedTotalIndirect;
    }

    /**
     * Sets the anticipatedTotalIndirect attribute value.
     * @param anticipatedTotalIndirect The anticipatedTotalIndirect to set.
     */
    public void setAnticipatedTotalIndirect(KualiDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    /**
     * Gets the obligatedTotalDirect attribute. 
     * @return Returns the obligatedTotalDirect.
     */
    public KualiDecimal getObligatedTotalDirect() {
        return obligatedTotalDirect;
    }

    /**
     * Sets the obligatedTotalDirect attribute value.
     * @param obligatedTotalDirect The obligatedTotalDirect to set.
     */
    public void setObligatedTotalDirect(KualiDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    /**
     * Gets the obligatedTotalIndirect attribute. 
     * @return Returns the obligatedTotalIndirect.
     */
    public KualiDecimal getObligatedTotalIndirect() {
        return obligatedTotalIndirect;
    }

    /**
     * Sets the obligatedTotalIndirect attribute value.
     * @param obligatedTotalIndirect The obligatedTotalIndirect to set.
     */
    public void setObligatedTotalIndirect(KualiDecimal obligatedTotalIndirect) {
        this.obligatedTotalIndirect = obligatedTotalIndirect;
    }

    /**
     * Gets the title attribute. 
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title attribute value.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the awardId attribute. 
     * @return Returns the awardId.
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     * Sets the awardId attribute value.
     * @param awardId The awardId to set.
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }
    
    public Boolean isAwardDocumentFinalStatus() {
        if(awardDocumentFinalStatus == null){
            setAwardDocumentFinalStatus(new Boolean(false)); 
        }
        return awardDocumentFinalStatus;
    }
    
    public void setAwardDocumentFinalStatus(Boolean awardDocumentStatus) {
        this.awardDocumentFinalStatus = awardDocumentStatus;
    }

    public String getAwardDocumentNumber() {
        return awardDocumentNumber;
    }

    public void setAwardDocumentNumber(String awardDocumentNumber) {
        this.awardDocumentNumber = awardDocumentNumber;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
