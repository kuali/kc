/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class Award extends KraPersistableBusinessObjectBase {
    private Integer awardId;
    private String awardNumber;
    private String sequenceNumber;
    private String sponsorCode;
    private Integer statusCode;
    private Integer templateCode;
    private String accountNumber;
    private String approvedEquipmentIndicator;
    private String approvedForeignTripIndicator;
    private String subContractIndicator;
    private Date awardEffectiveDate;
    private Date awardExecutionDate;
    private Date beginDate;
    private String costSharingIndicator;
    private String idcIndicator;
    private String modificationNumber;
    private String nsfCode;
    private String paymentScheduleIndicator;
    private String scienceCodeIndicator;
    private String specialReviewIndicator;
    private String sponsorAwardNumber;
    private String transferSponsorIndicator;
    private Integer accountTypeCode;
    private Integer activityTypeCode;
    private Integer awardTypeCode;
    private String primeSponsorCode;
    private Integer basisOfPaymentCode;
    private String cfdaNumber;
    private Integer competingRenewalProposalDue;
    private String dfafsNumber;
    private Integer finalInvoiceDue;
    private Integer invoiceNumberOfCopies;
    private Integer methodOfPaymentCode;
    private Integer nonCompetingContProposalDue;
    private Integer paymentInvoiceFrequencyCode;
    private KualiDecimal preAwardAuthorizedAmount;
    private Date preAwardEffectiveDate;
    private String procurementPriorityCode;
    private String proposalNumber;
    private Integer specialEbRateOffCampus;
    private Integer specialEbRateOnCampus;
    private String subPlanFlag;
    private String title;
    
    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    
    public String getAwardNumber() {
        return awardNumber;
    }
    
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }


    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    
    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getApprovedEquipmentIndicator() {
        return approvedEquipmentIndicator;
    }

    public void setApprovedEquipmentIndicator(String approvedEquipmentIndicator) {
        this.approvedEquipmentIndicator = approvedEquipmentIndicator;
    }


    public String getApprovedForeignTripIndicator() {
        return approvedForeignTripIndicator;
    }

    public void setApprovedForeignTripIndicator(String approvedForeignTripIndicator) {
        this.approvedForeignTripIndicator = approvedForeignTripIndicator;
    }


    public String getSubContractIndicator() {
        return subContractIndicator;
    }

    public void setSubContractIndicator(String subContractIndicator) {
        this.subContractIndicator = subContractIndicator;
    }


    public Date getAwardEffectiveDate() {
        return awardEffectiveDate;
    }

    public void setAwardEffectiveDate(Date awardEffectiveDate) {
        this.awardEffectiveDate = awardEffectiveDate;
    }


    public Date getAwardExecutionDate() {
        return awardExecutionDate;
    }

    public void setAwardExecutionDate(Date awardExecutionDate) {
        this.awardExecutionDate = awardExecutionDate;
    }


    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    public void setCostSharingIndicator(String costSharingIndicator) {
        this.costSharingIndicator = costSharingIndicator;
    }


    public String getIdcIndicator() {
        return idcIndicator;
    }

    public void setIdcIndicator(String idcIndicator) {
        this.idcIndicator = idcIndicator;
    }


    public String getModificationNumber() {
        return modificationNumber;
    }

    public void setModificationNumber(String modificationNumber) {
        this.modificationNumber = modificationNumber;
    }


    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }


    public String getPaymentScheduleIndicator() {
        return paymentScheduleIndicator;
    }

    public void setPaymentScheduleIndicator(String paymentScheduleIndicator) {
        this.paymentScheduleIndicator = paymentScheduleIndicator;
    }


    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }


    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }


    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }


    public String getTransferSponsorIndicator() {
        return transferSponsorIndicator;
    }

    public void setTransferSponsorIndicator(String transferSponsorIndicator) {
        this.transferSponsorIndicator = transferSponsorIndicator;
    }


    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }


    public Integer getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(Integer activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }


    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }


    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }


    public Integer getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(Integer basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }


    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }


    public Integer getCompetingRenewalProposalDue() {
        return competingRenewalProposalDue;
    }

    public void setCompetingRenewalProposalDue(Integer competingRenewalProposalDue) {
        this.competingRenewalProposalDue = competingRenewalProposalDue;
    }


    public String getDfafsNumber() {
        return dfafsNumber;
    }

    public void setDfafsNumber(String dfafsNumber) {
        this.dfafsNumber = dfafsNumber;
    }


    public Integer getFinalInvoiceDue() {
        return finalInvoiceDue;
    }

    public void setFinalInvoiceDue(Integer finalInvoiceDue) {
        this.finalInvoiceDue = finalInvoiceDue;
    }


    public Integer getInvoiceNumberOfCopies() {
        return invoiceNumberOfCopies;
    }

    public void setInvoiceNumberOfCopies(Integer invoiceNumberOfCopies) {
        this.invoiceNumberOfCopies = invoiceNumberOfCopies;
    }


    public Integer getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    public void setMethodOfPaymentCode(Integer methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }


    public Integer getNonCompetingContProposalDue() {
        return nonCompetingContProposalDue;
    }

    public void setNonCompetingContProposalDue(Integer nonCompetingContProposalDue) {
        this.nonCompetingContProposalDue = nonCompetingContProposalDue;
    }


    public Integer getPaymentInvoiceFrequencyCode() {
        return paymentInvoiceFrequencyCode;
    }

    public void setPaymentInvoiceFrequencyCode(Integer paymentInvoiceFrequencyCode) {
        this.paymentInvoiceFrequencyCode = paymentInvoiceFrequencyCode;
    }


    public KualiDecimal getPreAwardAuthorizedAmount() {
        return preAwardAuthorizedAmount;
    }

    public void setPreAwardAuthorizedAmount(KualiDecimal preAwardAuthorizedAmount) {
        this.preAwardAuthorizedAmount = preAwardAuthorizedAmount;
    }


    public Date getPreAwardEffectiveDate() {
        return preAwardEffectiveDate;
    }

    public void setPreAwardEffectiveDate(Date preAwardEffectiveDate) {
        this.preAwardEffectiveDate = preAwardEffectiveDate;
    }


    public String getProcurementPriorityCode() {
        return procurementPriorityCode;
    }

    public void setProcurementPriorityCode(String procurementPriorityCode) {
        this.procurementPriorityCode = procurementPriorityCode;
    }


    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }


    public Integer getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    public void setSpecialEbRateOffCampus(Integer specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }


    public Integer getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    public void setSpecialEbRateOnCampus(Integer specialEbRateOnCampus) {
        this.specialEbRateOnCampus = specialEbRateOnCampus;
    }


    public String getSubPlanFlag() {
        return subPlanFlag;
    }

    public void setSubPlanFlag(String subPlanFlag) {
        this.subPlanFlag = subPlanFlag;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    protected LinkedHashMap toStringMapper() {        
        LinkedHashMap hashMap = new LinkedHashMap();        
        hashMap.put("awardId", getAwardId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("sponsorCode", getSponsorCode());
        hashMap.put("templateCode", getTemplateCode());
        hashMap.put("accountNumber", getAccountNumber());
        hashMap.put("approvedEquipmentIndicator", getApprovedEquipmentIndicator());
        hashMap.put("approvedForeignTripIndicator", getApprovedForeignTripIndicator());
        hashMap.put("subContractIndicator", getSubContractIndicator());
        hashMap.put("awardEffectiveDate", getAwardEffectiveDate());
        hashMap.put("awardExecutionDate", getAwardExecutionDate());
        hashMap.put("beginDate", getBeginDate());
        hashMap.put("costSharingIndicator", getCostSharingIndicator());
        hashMap.put("idcIndicator", getIdcIndicator());
        hashMap.put("modificationNumber", getModificationNumber());
        hashMap.put("nsfCode", getNsfCode());
        hashMap.put("paymentScheduleIndicator", getPaymentScheduleIndicator());
        hashMap.put("scienceCodeIndicator", getScienceCodeIndicator());
        hashMap.put("specialReviewIndicator", getSpecialReviewIndicator());
        hashMap.put("sponsorAwardNumber", getSponsorAwardNumber());
        hashMap.put("accountTypeCode", getAccountTypeCode());
        hashMap.put("activityTypeCode", getActivityTypeCode());
        hashMap.put("awardTypeCode", getAwardTypeCode());
        hashMap.put("primeSponsorCode", getPrimeSponsorCode());
        hashMap.put("basisOfPaymentCode", getBasisOfPaymentCode());
        hashMap.put("cfdaNumber", getCfdaNumber());
        hashMap.put("competingRenewalProposalDue", getCompetingRenewalProposalDue());
        hashMap.put("dfafsNumber", getDfafsNumber());
        hashMap.put("finalInvoiceDue", getFinalInvoiceDue());
        hashMap.put("invoiceNumberOfCopies", getInvoiceNumberOfCopies());
        hashMap.put("methodOfPaymentCode", getMethodOfPaymentCode());
        hashMap.put("nonCompetingContProposalDue", getNonCompetingContProposalDue());
        hashMap.put("paymentInvoiceFrequencyCode", getPaymentInvoiceFrequencyCode());
        hashMap.put("preAwardAuthorizedAmount", getPreAwardAuthorizedAmount());
        hashMap.put("preAwardEffectiveDate", getPreAwardEffectiveDate());
        hashMap.put("procurementPriorityCode", getProcurementPriorityCode());
        hashMap.put("procurementPriorityCode", getProcurementPriorityCode());
        hashMap.put("proposalNumber", getProposalNumber());        
        hashMap.put("specialEbRateOffCampus", getSpecialEbRateOffCampus());
        hashMap.put("specialEbRateOnCampus", getSpecialEbRateOnCampus());
        hashMap.put("subPlanFlag", getSubPlanFlag());
        hashMap.put("title", getTitle());
        return hashMap;
    }

}
