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
package org.kuali.kra.subaward.bo;

import java.sql.Date;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class SubAwardTemplateInfo extends KcPersistableBusinessObjectBase {
    
    private String subAwardCode;
    
    private Integer sequenceNumber;
    
    private Long subAwardId;
  
    private String sowOrSubProposalBudget="N";
    
    private Date subProposalDate;
    
    private Integer invoiceOrPaymentContact;
    
    private Integer finalStmtOfCostscontact;
    
    private Integer changeRequestsContact;
    
    private Integer terminationContact;
   
    private Integer noCostExtensionContact;
    
    private String perfSiteDiffFromOrgAddr="N";
    
    private String perfSiteSameAsSubPiAddr="N";
    
    private String subRegisteredInCcr="N";
    
    private String subExemptFromReportingComp="N";
    
    private String parentDunsNumber;
    
    private String parentCongressionalDistrict;
    
    private String exemptFromRprtgExecComp="N";
    
    private Integer copyRightType;
    
    private String automaticCarryForward="N";
    
    private Integer carryForwardRequestsSentTo;
    
    private String treatmentPrgmIncomeAdditive="N";
    
    private String applicableProgramRegulations;
    
    private Date applicableProgramRegsDate;

    /**
     * Gets the subAwardCode attribute. 
     * @return Returns the subAwardCode.
     */
    public String getSubAwardCode() {
        return subAwardCode;
    }

    /**
     * Sets the subAwardCode attribute value.
     * @param subAwardCode The subAwardCode to set.
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the subAwardId attribute. 
     * @return Returns the subAwardId.
     */
    public Long getSubAwardId() {
        return subAwardId;
    }

    /**
     * Sets the subAwardId attribute value.
     * @param subAwardId The subAwardId to set.
     */
    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    /**
     * Gets the sowOrSubProposalBudget attribute. 
     * @return Returns the sowOrSubProposalBudget.
     */
    public String getSowOrSubProposalBudget() {
        return sowOrSubProposalBudget;
    }

    /**
     * Sets the sowOrSubProposalBudget attribute value.
     * @param sowOrSubProposalBudget The sowOrSubProposalBudget to set.
     */
    public void setSowOrSubProposalBudget(String sowOrSubProposalBudget) {
        this.sowOrSubProposalBudget = sowOrSubProposalBudget;
    }

    /**
     * Gets the subProposalDate attribute. 
     * @return Returns the subProposalDate.
     */
    public Date getSubProposalDate() {
        return subProposalDate;
    }

    /**
     * Sets the subProposalDate attribute value.
     * @param subProposalDate The subProposalDate to set.
     */
    public void setSubProposalDate(Date subProposalDate) {
        this.subProposalDate = subProposalDate;
    }

    /**
     * Gets the invoiceOrPaymentContact attribute. 
     * @return Returns the invoiceOrPaymentContact.
     */
    public Integer getInvoiceOrPaymentContact() {
        return invoiceOrPaymentContact;
    }

    /**
     * Sets the invoiceOrPaymentContact attribute value.
     * @param invoiceOrPaymentContact The invoiceOrPaymentContact to set.
     */
    public void setInvoiceOrPaymentContact(Integer invoiceOrPaymentContact) {
        this.invoiceOrPaymentContact = invoiceOrPaymentContact;
    }

    /**
     * Gets the finalStmtOfCostscontact attribute. 
     * @return Returns the finalStmtOfCostscontact.
     */
    public Integer getFinalStmtOfCostscontact() {
        return finalStmtOfCostscontact;
    }

    /**
     * Sets the finalStmtOfCostscontact attribute value.
     * @param finalStmtOfCostscontact The finalStmtOfCostscontact to set.
     */
    public void setFinalStmtOfCostscontact(Integer finalStmtOfCostscontact) {
        this.finalStmtOfCostscontact = finalStmtOfCostscontact;
    }

    /**
     * Gets the changeRequestsContact attribute. 
     * @return Returns the changeRequestsContact.
     */
    public Integer getChangeRequestsContact() {
        return changeRequestsContact;
    }

    /**
     * Sets the changeRequestsContact attribute value.
     * @param changeRequestsContact The changeRequestsContact to set.
     */
    public void setChangeRequestsContact(Integer changeRequestsContact) {
        this.changeRequestsContact = changeRequestsContact;
    }

    /**
     * Gets the terminationContact attribute. 
     * @return Returns the terminationContact.
     */
    public Integer getTerminationContact() {
        return terminationContact;
    }

    /**
     * Sets the terminationContact attribute value.
     * @param terminationContact The terminationContact to set.
     */
    public void setTerminationContact(Integer terminationContact) {
        this.terminationContact = terminationContact;
    }

    /**
     * Gets the noCostExtensionContact attribute. 
     * @return Returns the noCostExtensionContact.
     */
    public Integer getNoCostExtensionContact() {
        return noCostExtensionContact;
    }

    /**
     * Sets the noCostExtensionContact attribute value.
     * @param noCostExtensionContact The noCostExtensionContact to set.
     */
    public void setNoCostExtensionContact(Integer noCostExtensionContact) {
        this.noCostExtensionContact = noCostExtensionContact;
    }

    /**
     * Gets the perfSiteDiffFromOrgAddr attribute. 
     * @return Returns the perfSiteDiffFromOrgAddr.
     */
    public String getPerfSiteDiffFromOrgAddr() {
        return perfSiteDiffFromOrgAddr;
    }

    /**
     * Sets the perfSiteDiffFromOrgAddr attribute value.
     * @param perfSiteDiffFromOrgAddr The perfSiteDiffFromOrgAddr to set.
     */
    public void setPerfSiteDiffFromOrgAddr(String perfSiteDiffFromOrgAddr) {
        this.perfSiteDiffFromOrgAddr = perfSiteDiffFromOrgAddr;
    }

    /**
     * Gets the perfSiteSameAsSubPiAddr attribute. 
     * @return Returns the perfSiteSameAsSubPiAddr.
     */
    public String getPerfSiteSameAsSubPiAddr() {
        return perfSiteSameAsSubPiAddr;
    }

    /**
     * Sets the perfSiteSameAsSubPiAddr attribute value.
     * @param perfSiteSameAsSubPiAddr The perfSiteSameAsSubPiAddr to set.
     */
    public void setPerfSiteSameAsSubPiAddr(String perfSiteSameAsSubPiAddr) {
        this.perfSiteSameAsSubPiAddr = perfSiteSameAsSubPiAddr;
    }

    /**
     * Gets the subRegisteredInCcr attribute. 
     * @return Returns the subRegisteredInCcr.
     */
    public String getSubRegisteredInCcr() {
        return subRegisteredInCcr;
    }

    /**
     * Sets the subRegisteredInCcr attribute value.
     * @param subRegisteredInCcr The subRegisteredInCcr to set.
     */
    public void setSubRegisteredInCcr(String subRegisteredInCcr) {
        this.subRegisteredInCcr = subRegisteredInCcr;
    }

    /**
     * Gets the subExemptFromReportingComp attribute. 
     * @return Returns the subExemptFromReportingComp.
     */
    public String getSubExemptFromReportingComp() {
        return subExemptFromReportingComp;
    }

    /**
     * Sets the subExemptFromReportingComp attribute value.
     * @param subExemptFromReportingComp The subExemptFromReportingComp to set.
     */
    public void setSubExemptFromReportingComp(String subExemptFromReportingComp) {
        this.subExemptFromReportingComp = subExemptFromReportingComp;
    }

    /**
     * Gets the parentDunsNumber attribute. 
     * @return Returns the parentDunsNumber.
     */
    public String getParentDunsNumber() {
        return parentDunsNumber;
    }

    /**
     * Sets the parentDunsNumber attribute value.
     * @param parentDunsNumber The parentDunsNumber to set.
     */
    public void setParentDunsNumber(String parentDunsNumber) {
        this.parentDunsNumber = parentDunsNumber;
    }

    /**
     * Gets the parentCongressionalDistrict attribute. 
     * @return Returns the parentCongressionalDistrict.
     */
    public String getParentCongressionalDistrict() {
        return parentCongressionalDistrict;
    }

    /**
     * Sets the parentCongressionalDistrict attribute value.
     * @param parentCongressionalDistrict The parentCongressionalDistrict to set.
     */
    public void setParentCongressionalDistrict(String parentCongressionalDistrict) {
        this.parentCongressionalDistrict = parentCongressionalDistrict;
    }

    /**
     * Gets the exemptFromRprtgExecComp attribute. 
     * @return Returns the exemptFromRprtgExecComp.
     */
    public String getExemptFromRprtgExecComp() {
        return exemptFromRprtgExecComp;
    }

    /**
     * Sets the exemptFromRprtgExecComp attribute value.
     * @param exemptFromRprtgExecComp The exemptFromRprtgExecComp to set.
     */
    public void setExemptFromRprtgExecComp(String exemptFromRprtgExecComp) {
        this.exemptFromRprtgExecComp = exemptFromRprtgExecComp;
    }

    /**
     * Gets the copyRightType attribute. 
     * @return Returns the copyRightType.
     */
    public Integer getCopyRightType() {
        return copyRightType;
    }

    /**
     * Sets the copyRightType attribute value.
     * @param copyRightType The copyRightType to set.
     */
    public void setCopyRightType(Integer copyRightType) {
        this.copyRightType = copyRightType;
    }

    /**
     * Gets the automaticCarryForward attribute. 
     * @return Returns the automaticCarryForward.
     */
    public String getAutomaticCarryForward() {
        return automaticCarryForward;
    }

    /**
     * Sets the automaticCarryForward attribute value.
     * @param automaticCarryForward The automaticCarryForward to set.
     */
    public void setAutomaticCarryForward(String automaticCarryForward) {
        this.automaticCarryForward = automaticCarryForward;
    }

    /**
     * Gets the carryForwardRequestsSentTo attribute. 
     * @return Returns the carryForwardRequestsSentTo.
     */
    public Integer getCarryForwardRequestsSentTo() {
        return carryForwardRequestsSentTo;
    }

    /**
     * Sets the carryForwardRequestsSentTo attribute value.
     * @param carryForwardRequestsSentTo The carryForwardRequestsSentTo to set.
     */
    public void setCarryForwardRequestsSentTo(Integer carryForwardRequestsSentTo) {
        this.carryForwardRequestsSentTo = carryForwardRequestsSentTo;
    }

    /**
     * Gets the treatmentPrgmIncomeAdditive attribute. 
     * @return Returns the treatmentPrgmIncomeAdditive.
     */
    public String getTreatmentPrgmIncomeAdditive() {
        return treatmentPrgmIncomeAdditive;
    }

    /**
     * Sets the treatmentPrgmIncomeAdditive attribute value.
     * @param treatmentPrgmIncomeAdditive The treatmentPrgmIncomeAdditive to set.
     */
    public void setTreatmentPrgmIncomeAdditive(String treatmentPrgmIncomeAdditive) {
        this.treatmentPrgmIncomeAdditive = treatmentPrgmIncomeAdditive;
    }

    /**
     * Gets the applicableProgramRegulations attribute. 
     * @return Returns the applicableProgramRegulations.
     */
    public String getApplicableProgramRegulations() {
        return applicableProgramRegulations;
    }

    /**
     * Sets the applicableProgramRegulations attribute value.
     * @param applicableProgramRegulations The applicableProgramRegulations to set.
     */
    public void setApplicableProgramRegulations(String applicableProgramRegulations) {
        this.applicableProgramRegulations = applicableProgramRegulations;
    }

    /**
     * Gets the applicableProgramRegsDate attribute. 
     * @return Returns the applicableProgramRegsDate.
     */
    public Date getApplicableProgramRegsDate() {
        return applicableProgramRegsDate;
    }

    /**
     * Sets the applicableProgramRegsDate attribute value.
     * @param applicableProgramRegsDate The applicableProgramRegsDate to set.
     */
    public void setApplicableProgramRegsDate(Date applicableProgramRegsDate) {
        this.applicableProgramRegsDate = applicableProgramRegsDate;
    }
     
}
