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
package org.kuali.coeus.award.dto;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Property;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.kuali.coeus.award.finance.*;
import org.kuali.coeus.instprop.impl.api.customSerializers.CustomSqlDateSerializer;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.budget.AwardBudgetLimit;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.home.*;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.timeandmoney.transactions.AwardTransactionType;

import java.sql.Date;
import java.util.List;

public class AwardDto {
    @Property(translate = false)
    private String docNbr;
    @Property(translate = false)
    String docStatus;
    private Long awardId;
    private String awardNumber;
    private Integer sequenceNumber;
    private String sponsorCode;
    private Integer statusCode;
    private String accountNumber;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date awardEffectiveDate;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date awardExecutionDate;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date beginDate;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date projectEndDate;
    private String costSharingIndicator;
    private String indirectCostIndicator;
    private String nsfCode;
    private String paymentScheduleIndicator;
    private String scienceCodeIndicator;
    private String specialReviewIndicator;
    private String sponsorAwardNumber;
    private String transferSponsorIndicator;
    private Integer accountTypeCode;
    private String activityTypeCode;
    private String primeSponsorCode;
    private Integer awardTypeCode;
    private String cfdaNumber;
    private String methodOfPaymentCode;
    private String proposalNumber;
    private String title;
    private String basisOfPaymentCode;
    private Integer awardTransactionTypeCode;
    @JsonDeserialize(using = CustomSqlDateSerializer.class)
    private Date noticeDate;
    private String leadUnitNumber;
    private String awardSequenceStatus;
    private String unitNumber;
    @JsonProperty(value = "projectPersons")
    @CollectionProperty(itemClass=AwardPersonDto.class)
    private List<AwardPersonDto> projectPersons;
    @JsonProperty(value="awardCustomDataList")
    @CollectionProperty(itemClass=AwardCustomDataDto.class)
    private List<AwardCustomDataDto> awardCustomDataList;
    @JsonProperty(value="awardSponsorTerms")
    @CollectionProperty(itemClass=AwardSponsorTermDto.class)
    private List<AwardSponsorTermDto> awardSponsorTerms;
    @JsonProperty(value="awardReportTerms")
    @CollectionProperty(source="awardReportTermItems", itemClass=AwardReportTermDto.class)
    private List<AwardReportTermDto> awardReportTerms;

    private String approvedEquipmentIndicator;
    private String approvedForeignTripIndicator;
    private String subContractIndicator;
    private String modificationNumber;
    private String documentFundingId;
    private ScaleTwoDecimal preAwardAuthorizedAmount;
    private Date preAwardEffectiveDate;
    private ScaleTwoDecimal preAwardInstitutionalAuthorizedAmount;
    private Date preAwardInstitutionalEffectiveDate;
    private String procurementPriorityCode;
    private ScaleTwoDecimal specialEbRateOffCampus;
    private ScaleTwoDecimal specialEbRateOnCampus;
    private String subPlanFlag;
    private String archiveLocation;
    private Date closeoutDate;
    private String currentActionComments;
    private String awardSequenceStatusResult;
    private Integer templateCode;

    private AwardBasisOfPayment awardBasisOfPayment;
    private AwardMethodOfPayment awardMethodOfPayment;
    private AwardTransactionType awardTransactionType;
    @JsonProperty(value="awardComments")
    @CollectionProperty(source="awardComments", itemClass=AwardCommentDto.class)
    private List<AwardComment> awardComments;

    @JsonProperty(value="awardSponsorContacts")
    @CollectionProperty(source="awardSponsorContacts", itemClass=AwardSponsorContactDto.class)
    private List<AwardSponsorContact> sponsorContacts;

    @JsonProperty(value="awardCostShares")
    @CollectionProperty(source="awardCostShares", itemClass=AwardCostShareDto.class)
    private List<AwardCostShare> awardCostShares;

    @JsonProperty(value="awardFandaRate")
    @CollectionProperty(source="awardFandaRate", itemClass= AwardFandARateDto.class)
    private List<AwardFandaRate> awardFandaRate;

    @JsonProperty(value="awardDirectFandADistributions")
    @CollectionProperty(source="awardDirectFandADistributions", itemClass=AwardFandADistributionDto.class)
    private List<AwardDirectFandADistribution> awardDirectFandADistributions;

    @JsonProperty(value="awardUnitContacts")
    @CollectionProperty(source="awardUnitContacts", itemClass=AwardUnitContactDto.class)
    private List<AwardUnitContact> awardUnitContacts;

    @JsonProperty(value="approvedEquipmentItems")
    @CollectionProperty(source="approvedEquipmentItems", itemClass=AwardApprovedEquipmentDto.class)
    private List<AwardApprovedEquipment> approvedEquipmentItems;

    @JsonProperty(value="approvedForeignTravelTrips")
    @CollectionProperty(source="approvedForeignTravelTrips", itemClass=AwardApprovedForeignTravelDto.class)
    private List<AwardApprovedForeignTravel> approvedForeignTravelTrips;

    @JsonProperty(value="paymentScheduleItems")
    @CollectionProperty(source="paymentScheduleItems", itemClass=AwardPaymentScheduleDto.class)
    private List<AwardPaymentSchedule> paymentScheduleItems;

    @JsonProperty(value="awardTransferringSponsors")
    @CollectionProperty(source="awardTransferringSponsors", itemClass=AwardTransferringSponsorDto.class)
    private List<AwardTransferringSponsor> awardTransferringSponsors;

    @JsonProperty(value="awardAmountInfos")
    @CollectionProperty(source = "awardAmountInfos", itemClass=AwardAmountInfoDto.class)
    private List<AwardAmountInfo> awardAmountInfos;

    @JsonProperty(value="awardCloseoutItems")
    @CollectionProperty(source = "awardCloseoutItems", itemClass=AwardCloseoutDto.class)
    private List<AwardCloseout> awardCloseoutItems;

    @JsonProperty(value="awardCloseoutNewItems")
    @CollectionProperty(source = "awardCloseoutNewItems", itemClass=AwardCloseoutDto.class)
    private List<AwardCloseout> awardCloseoutNewItems;

    @JsonProperty(value="fundingProposals")
    @CollectionProperty(source = "fundingProposals", itemClass=AwardFundingProposalDto.class)
    private List<AwardFundingProposal> fundingProposals;

    @JsonProperty(value="allFundingProposals")
    @CollectionProperty(source = "allFundingProposals", itemClass=AwardFundingProposalDto.class)
    private List<AwardFundingProposal> allFundingProposals;

    @JsonProperty(value="awardBudgetLimits")
    @CollectionProperty(source = "awardBudgetLimits", itemClass=AwardBudgetLimitDto.class)
    private List<AwardBudgetLimit> awardBudgetLimits;

    private String fainId;
    private Integer fedAwardYear;
    private Date fedAwardDate;
    private Boolean posted;

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getIndirectCostIndicator() {
        return indirectCostIndicator;
    }

    public void setIndirectCostIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
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

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAwardTransactionTypeCode() {
        return awardTransactionTypeCode;
    }

    public void setAwardTransactionTypeCode(Integer awardTransactionTypeCode) {
        this.awardTransactionTypeCode = awardTransactionTypeCode;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getAwardSequenceStatus() {
        return awardSequenceStatus;
    }

    public void setAwardSequenceStatus(String awardSequenceStatus) {
        this.awardSequenceStatus = awardSequenceStatus;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getDocNbr() {
        return docNbr;
    }

    public void setDocNbr(String docNbr) {
        this.docNbr = docNbr;
    }

    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public List<AwardPersonDto> getProjectPersons() {
        return projectPersons;
    }

    public void setProjectPersons(List<AwardPersonDto> projectPersons) {
        this.projectPersons = projectPersons;
    }

    public List<AwardCustomDataDto> getAwardCustomDataList() {
        return awardCustomDataList;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public List<AwardSponsorTermDto> getAwardSponsorTerms() {
        return awardSponsorTerms;
    }

    public void setAwardSponsorTerms(List<AwardSponsorTermDto> awardSponsorTerms) {
        this.awardSponsorTerms = awardSponsorTerms;
    }

    public List<AwardReportTermDto> getAwardReportTerms() {
        return awardReportTerms;
    }

    public void setAwardReportTerms(List<AwardReportTermDto> awardReportTerms) {
        this.awardReportTerms = awardReportTerms;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public void setAwardCustomDataList(List<AwardCustomDataDto> awardCustomDataList) {
        this.awardCustomDataList = awardCustomDataList;
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

    public String getModificationNumber() {
        return modificationNumber;
    }

    public void setModificationNumber(String modificationNumber) {
        this.modificationNumber = modificationNumber;
    }

    public String getDocumentFundingId() {
        return documentFundingId;
    }

    public void setDocumentFundingId(String documentFundingId) {
        this.documentFundingId = documentFundingId;
    }

    public ScaleTwoDecimal getPreAwardAuthorizedAmount() {
        return preAwardAuthorizedAmount;
    }

    public void setPreAwardAuthorizedAmount(ScaleTwoDecimal preAwardAuthorizedAmount) {
        this.preAwardAuthorizedAmount = preAwardAuthorizedAmount;
    }

    public Date getPreAwardEffectiveDate() {
        return preAwardEffectiveDate;
    }

    public void setPreAwardEffectiveDate(Date preAwardEffectiveDate) {
        this.preAwardEffectiveDate = preAwardEffectiveDate;
    }

    public ScaleTwoDecimal getPreAwardInstitutionalAuthorizedAmount() {
        return preAwardInstitutionalAuthorizedAmount;
    }

    public void setPreAwardInstitutionalAuthorizedAmount(ScaleTwoDecimal preAwardInstitutionalAuthorizedAmount) {
        this.preAwardInstitutionalAuthorizedAmount = preAwardInstitutionalAuthorizedAmount;
    }

    public Date getPreAwardInstitutionalEffectiveDate() {
        return preAwardInstitutionalEffectiveDate;
    }

    public void setPreAwardInstitutionalEffectiveDate(Date preAwardInstitutionalEffectiveDate) {
        this.preAwardInstitutionalEffectiveDate = preAwardInstitutionalEffectiveDate;
    }

    public String getProcurementPriorityCode() {
        return procurementPriorityCode;
    }

    public void setProcurementPriorityCode(String procurementPriorityCode) {
        this.procurementPriorityCode = procurementPriorityCode;
    }

    public ScaleTwoDecimal getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    public void setSpecialEbRateOffCampus(ScaleTwoDecimal specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }

    public ScaleTwoDecimal getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    public void setSpecialEbRateOnCampus(ScaleTwoDecimal specialEbRateOnCampus) {
        this.specialEbRateOnCampus = specialEbRateOnCampus;
    }

    public String getSubPlanFlag() {
        return subPlanFlag;
    }

    public void setSubPlanFlag(String subPlanFlag) {
        this.subPlanFlag = subPlanFlag;
    }

    public String getArchiveLocation() {
        return archiveLocation;
    }

    public void setArchiveLocation(String archiveLocation) {
        this.archiveLocation = archiveLocation;
    }

    public Date getCloseoutDate() {
        return closeoutDate;
    }

    public void setCloseoutDate(Date closeoutDate) {
        this.closeoutDate = closeoutDate;
    }

    public String getCurrentActionComments() {
        return currentActionComments;
    }

    public void setCurrentActionComments(String currentActionComments) {
        this.currentActionComments = currentActionComments;
    }

    public String getAwardSequenceStatusResult() {
        return awardSequenceStatusResult;
    }

    public void setAwardSequenceStatusResult(String awardSequenceStatusResult) {
        this.awardSequenceStatusResult = awardSequenceStatusResult;
    }

    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public AwardBasisOfPayment getAwardBasisOfPayment() {
        return awardBasisOfPayment;
    }

    public void setAwardBasisOfPayment(AwardBasisOfPayment awardBasisOfPayment) {
        this.awardBasisOfPayment = awardBasisOfPayment;
    }

    public AwardMethodOfPayment getAwardMethodOfPayment() {
        return awardMethodOfPayment;
    }

    public void setAwardMethodOfPayment(AwardMethodOfPayment awardMethodOfPayment) {
        this.awardMethodOfPayment = awardMethodOfPayment;
    }

    public AwardTransactionType getAwardTransactionType() {
        return awardTransactionType;
    }

    public void setAwardTransactionType(AwardTransactionType awardTransactionType) {
        this.awardTransactionType = awardTransactionType;
    }

    public List<AwardComment> getAwardComments() {
        return awardComments;
    }

    public void setAwardComments(List<AwardComment> awardComments) {
        this.awardComments = awardComments;
    }

    public List<AwardSponsorContact> getSponsorContacts() {
        return sponsorContacts;
    }

    public void setSponsorContacts(List<AwardSponsorContact> sponsorContacts) {
        this.sponsorContacts = sponsorContacts;
    }

    public List<AwardCostShare> getAwardCostShares() {
        return awardCostShares;
    }

    public void setAwardCostShares(List<AwardCostShare> awardCostShares) {
        this.awardCostShares = awardCostShares;
    }

    public List<AwardFandaRate> getAwardFandaRate() {
        return awardFandaRate;
    }

    public void setAwardFandaRate(List<AwardFandaRate> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
    }

    public List<AwardDirectFandADistribution> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }

    public void setAwardDirectFandADistributions(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }

    public List<AwardUnitContact> getAwardUnitContacts() {
        return awardUnitContacts;
    }

    public void setAwardUnitContacts(List<AwardUnitContact> awardUnitContacts) {
        this.awardUnitContacts = awardUnitContacts;
    }

    public List<AwardApprovedEquipment> getApprovedEquipmentItems() {
        return approvedEquipmentItems;
    }

    public void setApprovedEquipmentItems(List<AwardApprovedEquipment> approvedEquipmentItems) {
        this.approvedEquipmentItems = approvedEquipmentItems;
    }

    public List<AwardApprovedForeignTravel> getApprovedForeignTravelTrips() {
        return approvedForeignTravelTrips;
    }

    public void setApprovedForeignTravelTrips(List<AwardApprovedForeignTravel> approvedForeignTravelTrips) {
        this.approvedForeignTravelTrips = approvedForeignTravelTrips;
    }

    public List<AwardPaymentSchedule> getPaymentScheduleItems() {
        return paymentScheduleItems;
    }

    public void setPaymentScheduleItems(List<AwardPaymentSchedule> paymentScheduleItems) {
        this.paymentScheduleItems = paymentScheduleItems;
    }

    public List<AwardTransferringSponsor> getAwardTransferringSponsors() {
        return awardTransferringSponsors;
    }

    public void setAwardTransferringSponsors(List<AwardTransferringSponsor> awardTransferringSponsors) {
        this.awardTransferringSponsors = awardTransferringSponsors;
    }

    public List<AwardAmountInfo> getAwardAmountInfos() {
        return awardAmountInfos;
    }

    public void setAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos) {
        this.awardAmountInfos = awardAmountInfos;
    }

    public List<AwardCloseout> getAwardCloseoutItems() {
        return awardCloseoutItems;
    }

    public void setAwardCloseoutItems(List<AwardCloseout> awardCloseoutItems) {
        this.awardCloseoutItems = awardCloseoutItems;
    }

    public List<AwardCloseout> getAwardCloseoutNewItems() {
        return awardCloseoutNewItems;
    }

    public void setAwardCloseoutNewItems(List<AwardCloseout> awardCloseoutNewItems) {
        this.awardCloseoutNewItems = awardCloseoutNewItems;
    }

    public List<AwardFundingProposal> getFundingProposals() {
        return fundingProposals;
    }

    public void setFundingProposals(List<AwardFundingProposal> fundingProposals) {
        this.fundingProposals = fundingProposals;
    }

    public List<AwardFundingProposal> getAllFundingProposals() {
        return allFundingProposals;
    }

    public void setAllFundingProposals(List<AwardFundingProposal> allFundingProposals) {
        this.allFundingProposals = allFundingProposals;
    }

    public List<AwardBudgetLimit> getAwardBudgetLimits() {
        return awardBudgetLimits;
    }

    public void setAwardBudgetLimits(List<AwardBudgetLimit> awardBudgetLimits) {
        this.awardBudgetLimits = awardBudgetLimits;
    }

    public String getFainId() {
        return fainId;
    }

    public void setFainId(String fainId) {
        this.fainId = fainId;
    }

    public Integer getFedAwardYear() {
        return fedAwardYear;
    }

    public void setFedAwardYear(Integer fedAwardYear) {
        this.fedAwardYear = fedAwardYear;
    }

    public Date getFedAwardDate() {
        return fedAwardDate;
    }

    public void setFedAwardDate(Date fedAwardDate) {
        this.fedAwardDate = fedAwardDate;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }
}
