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
package org.kuali.kra.timeandmoney;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class AwardHierarchyNode extends AwardHierarchy { 
    
    private Date currentFundEffectiveDate;
    private Date obligationExpirationDate;
    private Date finalExpirationDate;
    private Date projectStartDate;
    private ScaleTwoDecimal anticipatedTotalAmount;
    private ScaleTwoDecimal anticipatedTotalDirect;
    private ScaleTwoDecimal anticipatedTotalIndirect;
    private ScaleTwoDecimal antDistributableAmount;
    private ScaleTwoDecimal amountObligatedToDate;
    private ScaleTwoDecimal obligatedTotalDirect;
    private ScaleTwoDecimal obligatedTotalIndirect;
    private ScaleTwoDecimal obliDistributableAmount;
    private String leadUnitName;
    private String principalInvestigatorName;
    private String accountNumber;
    private Integer awardStatusCode;
    private String title;
    private Long awardId;
    private Boolean awardDocumentFinalStatus;
    private String awardDocumentNumber;
    private Boolean hasChildren;
    
    //transient
    private boolean populatedFromClient;
    

    public AwardHierarchyNode() {
        anticipatedTotalAmount = new ScaleTwoDecimal("0");
        antDistributableAmount = new ScaleTwoDecimal("0");
        amountObligatedToDate = new ScaleTwoDecimal("0");
        obliDistributableAmount = new ScaleTwoDecimal("0");
        anticipatedTotalDirect = new ScaleTwoDecimal("0");
        anticipatedTotalIndirect = new ScaleTwoDecimal("0");
        obligatedTotalDirect = new ScaleTwoDecimal("0");
        obligatedTotalIndirect = new ScaleTwoDecimal("0");
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
    public ScaleTwoDecimal getAnticipatedTotalAmount() {
        if(anticipatedTotalAmount == null){
            setAnticipatedTotalAmount(new ScaleTwoDecimal(0));
        }
        return anticipatedTotalAmount;
    }

    /**
     * Sets the anticipatedTotalAmount attribute value.
     * @param anticipatedTotalAmount The anticipatedTotalAmount to set.
     */
    public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
    }

    /**
     * Gets the antDistributableAmount attribute. 
     * @return Returns the antDistributableAmount.
     */
    public ScaleTwoDecimal getAntDistributableAmount() {
        if(antDistributableAmount == null){
            setAntDistributableAmount(new ScaleTwoDecimal(0));
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
    public void setAntDistributableAmount(ScaleTwoDecimal antDistributableAmount) {
        this.antDistributableAmount = antDistributableAmount;
    }

    /**
     * Gets the amountObligatedToDate attribute. 
     * @return Returns the amountObligatedToDate.
     */
    public ScaleTwoDecimal getAmountObligatedToDate() {
        if(amountObligatedToDate == null){
            setAmountObligatedToDate(new ScaleTwoDecimal(0));
        }
        return amountObligatedToDate;
    }

    /**
     * Sets the amountObligatedToDate attribute value.
     * @param amountObligatedToDate The amountObligatedToDate to set.
     */
    public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
    }

    /**
     * Gets the obliDistributableAmount attribute. 
     * @return Returns the obliDistributableAmount.
     */
    public ScaleTwoDecimal getObliDistributableAmount() {
        if(obliDistributableAmount == null){
            setObliDistributableAmount(new ScaleTwoDecimal(0));
        }
        return obliDistributableAmount;
        //return getAward().calculateObligatedDistributedAmountTotal();
    }

    /**
     * Sets the obliDistributableAmount attribute value.
     * @param obliDistributableAmount The obliDistributableAmount to set.
     */
    public void setObliDistributableAmount(ScaleTwoDecimal obliDistributableAmount) {
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
    public ScaleTwoDecimal getAnticipatedTotalDirect() {
        return (anticipatedTotalDirect != null) ? anticipatedTotalDirect : new ScaleTwoDecimal(0.0);
    }

    /**
     * Sets the anticipatedTotalDirect attribute value.
     * @param anticipatedTotalDirect The anticipatedTotalDirect to set.
     */
    public void setAnticipatedTotalDirect(ScaleTwoDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    /**
     * Gets the anticipatedTotalIndirect attribute. 
     * @return Returns the anticipatedTotalIndirect.
     */
    public ScaleTwoDecimal getAnticipatedTotalIndirect() {
        return (anticipatedTotalIndirect != null) ? anticipatedTotalIndirect : new ScaleTwoDecimal(0.0);
    }

    /**
     * Sets the anticipatedTotalIndirect attribute value.
     * @param anticipatedTotalIndirect The anticipatedTotalIndirect to set.
     */
    public void setAnticipatedTotalIndirect(ScaleTwoDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    /**
     * Gets the obligatedTotalDirect attribute. 
     * @return Returns the obligatedTotalDirect.
     */
    public ScaleTwoDecimal getObligatedTotalDirect() {
        return (obligatedTotalDirect != null) ? obligatedTotalDirect : new ScaleTwoDecimal(0.0);
    }

    /**
     * Sets the obligatedTotalDirect attribute value.
     * @param obligatedTotalDirect The obligatedTotalDirect to set.
     */
    public void setObligatedTotalDirect(ScaleTwoDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    /**
     * Gets the obligatedTotalIndirect attribute. 
     * @return Returns the obligatedTotalIndirect.
     */
    public ScaleTwoDecimal getObligatedTotalIndirect() {
        return (obligatedTotalIndirect != null) ? obligatedTotalIndirect : new ScaleTwoDecimal(0.0);
    }

    /**
     * Sets the obligatedTotalIndirect attribute value.
     * @param obligatedTotalIndirect The obligatedTotalIndirect to set.
     */
    public void setObligatedTotalIndirect(ScaleTwoDecimal obligatedTotalIndirect) {
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
    
    @Override
    public boolean equals(Object other) {
        return equals((AwardHierarchyNode) other);
    }
    
    /**
     * 
     * This method implements equals using ObjectUtils.equals method for each member variable.
     * @param other
     * @return
     */
    public boolean equals(AwardHierarchyNode other) {  
        boolean retVal = other != null && ObjectUtils.equals(getCurrentFundEffectiveDate(), other.getCurrentFundEffectiveDate()) 
            && ObjectUtils.equals(obligationExpirationDate, other.getObligationExpirationDate())
            && ObjectUtils.equals(finalExpirationDate, other.getFinalExpirationDate())
            && ObjectUtils.equals(projectStartDate, other.getProjectStartDate())
            && ObjectUtils.equals(anticipatedTotalAmount, other.getAnticipatedTotalAmount())
            && ObjectUtils.equals(anticipatedTotalIndirect, other.getAnticipatedTotalIndirect())
            && ObjectUtils.equals(anticipatedTotalDirect, other.getAnticipatedTotalDirect())
            && ObjectUtils.equals(antDistributableAmount, other.getAntDistributableAmount())
            && ObjectUtils.equals(amountObligatedToDate, other.getAmountObligatedToDate())
            && ObjectUtils.equals(obligatedTotalDirect, other.getObligatedTotalDirect())
            && ObjectUtils.equals(obligatedTotalIndirect, other.getObligatedTotalIndirect())
            && ObjectUtils.equals(obliDistributableAmount, other.getObliDistributableAmount())
            && ObjectUtils.equals(leadUnitName, other.getLeadUnitName())
            && ObjectUtils.equals(principalInvestigatorName, other.getPrincipalInvestigatorName())
            && ObjectUtils.equals(accountNumber, other.getAccountNumber())
            && ObjectUtils.equals(awardStatusCode, other.getAwardStatusCode())
            && ObjectUtils.equals(title, other.getTitle())
            && ObjectUtils.equals(awardId, other.getAwardId())
            && ObjectUtils.equals(awardDocumentFinalStatus, other.isAwardDocumentFinalStatus())
            && ObjectUtils.equals(awardDocumentNumber, other.getAwardDocumentNumber())
            && ObjectUtils.equals(getHasChildren(), other.getHasChildren());
        return retVal;
    }

    public boolean isPopulatedFromClient() {
        return populatedFromClient;
    }

    public void setPopulatedFromClient(boolean populatedFromClient) {
        this.populatedFromClient = populatedFromClient;
    }
}
