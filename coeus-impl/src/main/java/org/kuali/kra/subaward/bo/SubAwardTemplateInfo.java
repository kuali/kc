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

    private Boolean rAndD;

    private Boolean includesCostSharing;

    private Boolean fcio;

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSowOrSubProposalBudget() {
        return sowOrSubProposalBudget;
    }

    public void setSowOrSubProposalBudget(String sowOrSubProposalBudget) {
        this.sowOrSubProposalBudget = sowOrSubProposalBudget;
    }

    public Date getSubProposalDate() {
        return subProposalDate;
    }

    public void setSubProposalDate(Date subProposalDate) {
        this.subProposalDate = subProposalDate;
    }

    public Integer getInvoiceOrPaymentContact() {
        return invoiceOrPaymentContact;
    }

    public void setInvoiceOrPaymentContact(Integer invoiceOrPaymentContact) {
        this.invoiceOrPaymentContact = invoiceOrPaymentContact;
    }

    public Integer getFinalStmtOfCostscontact() {
        return finalStmtOfCostscontact;
    }

    public void setFinalStmtOfCostscontact(Integer finalStmtOfCostscontact) {
        this.finalStmtOfCostscontact = finalStmtOfCostscontact;
    }

    public Integer getChangeRequestsContact() {
        return changeRequestsContact;
    }

    public void setChangeRequestsContact(Integer changeRequestsContact) {
        this.changeRequestsContact = changeRequestsContact;
    }

    public Integer getTerminationContact() {
        return terminationContact;
    }

    public void setTerminationContact(Integer terminationContact) {
        this.terminationContact = terminationContact;
    }

    public Integer getNoCostExtensionContact() {
        return noCostExtensionContact;
    }

    public void setNoCostExtensionContact(Integer noCostExtensionContact) {
        this.noCostExtensionContact = noCostExtensionContact;
    }

    public String getPerfSiteDiffFromOrgAddr() {
        return perfSiteDiffFromOrgAddr;
    }

    public void setPerfSiteDiffFromOrgAddr(String perfSiteDiffFromOrgAddr) {
        this.perfSiteDiffFromOrgAddr = perfSiteDiffFromOrgAddr;
    }

    public String getPerfSiteSameAsSubPiAddr() {
        return perfSiteSameAsSubPiAddr;
    }

    public void setPerfSiteSameAsSubPiAddr(String perfSiteSameAsSubPiAddr) {
        this.perfSiteSameAsSubPiAddr = perfSiteSameAsSubPiAddr;
    }

    public String getSubRegisteredInCcr() {
        return subRegisteredInCcr;
    }

    public void setSubRegisteredInCcr(String subRegisteredInCcr) {
        this.subRegisteredInCcr = subRegisteredInCcr;
    }

    public String getSubExemptFromReportingComp() {
        return subExemptFromReportingComp;
    }

    public void setSubExemptFromReportingComp(String subExemptFromReportingComp) {
        this.subExemptFromReportingComp = subExemptFromReportingComp;
    }

    public String getParentDunsNumber() {
        return parentDunsNumber;
    }

    public void setParentDunsNumber(String parentDunsNumber) {
        this.parentDunsNumber = parentDunsNumber;
    }

    public String getParentCongressionalDistrict() {
        return parentCongressionalDistrict;
    }

    public void setParentCongressionalDistrict(String parentCongressionalDistrict) {
        this.parentCongressionalDistrict = parentCongressionalDistrict;
    }

    public String getExemptFromRprtgExecComp() {
        return exemptFromRprtgExecComp;
    }

    public void setExemptFromRprtgExecComp(String exemptFromRprtgExecComp) {
        this.exemptFromRprtgExecComp = exemptFromRprtgExecComp;
    }

    public Integer getCopyRightType() {
        return copyRightType;
    }

    public void setCopyRightType(Integer copyRightType) {
        this.copyRightType = copyRightType;
    }

    public String getAutomaticCarryForward() {
        return automaticCarryForward;
    }

    public void setAutomaticCarryForward(String automaticCarryForward) {
        this.automaticCarryForward = automaticCarryForward;
    }

    public Integer getCarryForwardRequestsSentTo() {
        return carryForwardRequestsSentTo;
    }

    public void setCarryForwardRequestsSentTo(Integer carryForwardRequestsSentTo) {
        this.carryForwardRequestsSentTo = carryForwardRequestsSentTo;
    }

    public String getTreatmentPrgmIncomeAdditive() {
        return treatmentPrgmIncomeAdditive;
    }

    public void setTreatmentPrgmIncomeAdditive(String treatmentPrgmIncomeAdditive) {
        this.treatmentPrgmIncomeAdditive = treatmentPrgmIncomeAdditive;
    }

    public String getApplicableProgramRegulations() {
        return applicableProgramRegulations;
    }

    public void setApplicableProgramRegulations(String applicableProgramRegulations) {
        this.applicableProgramRegulations = applicableProgramRegulations;
    }

    public Date getApplicableProgramRegsDate() {
        return applicableProgramRegsDate;
    }

    public void setApplicableProgramRegsDate(Date applicableProgramRegsDate) {
        this.applicableProgramRegsDate = applicableProgramRegsDate;
    }

    public Boolean getrAndD() {
        return rAndD;
    }

    public void setrAndD(Boolean rAndD) {
        this.rAndD = rAndD;
    }

    public Boolean getIncludesCostSharing() {
        return includesCostSharing;
    }

    public void setIncludesCostSharing(Boolean includesCostSharing) {
        this.includesCostSharing = includesCostSharing;
    }

    public Boolean getFcio() {
        return fcio;
    }

    public void setFcio(Boolean fcio) {
        this.fcio = fcio;
    }
}
