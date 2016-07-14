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
import org.kuali.kra.award.home.*;

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
    @JsonProperty(value="awardComments")
    @CollectionProperty(source="awardComments", itemClass=AwardCommentDto.class)
    private List<AwardCommentDto> awardComments;

    @JsonProperty(value="awardSponsorContacts")
    @CollectionProperty(source="awardSponsorContacts", itemClass=AwardSponsorContactDto.class)
    private List<AwardSponsorContactDto> sponsorContacts;

    @JsonProperty(value="awardCostShares")
    @CollectionProperty(source="awardCostShares", itemClass=AwardCostShareDto.class)
    private List<AwardCostShareDto> awardCostShares;

    @JsonProperty(value="awardFandaRate")
    @CollectionProperty(source="awardFandaRate", itemClass= AwardFandARateDto.class)
    private List<AwardFandARateDto> awardFandaRate;

    @JsonProperty(value="awardDirectFandADistributions")
    @CollectionProperty(source="awardDirectFandADistributions", itemClass=AwardFandADistributionDto.class)
    private List<AwardFandADistributionDto> awardDirectFandADistributions;

    @JsonProperty(value="awardUnitContacts")
    @CollectionProperty(source="awardUnitContacts", itemClass=AwardUnitContactDto.class)
    private List<AwardUnitContactDto> awardUnitContacts;

    @JsonProperty(value="approvedEquipmentItems")
    @CollectionProperty(source="approvedEquipmentItems", itemClass=AwardApprovedEquipmentDto.class)
    private List<AwardApprovedEquipmentDto> approvedEquipmentItems;

    @JsonProperty(value="approvedForeignTravelTrips")
    @CollectionProperty(source="approvedForeignTravelTrips", itemClass=AwardApprovedForeignTravelDto.class)
    private List<AwardApprovedForeignTravelDto> approvedForeignTravelTrips;

    @JsonProperty(value="paymentScheduleItems")
    @CollectionProperty(source="paymentScheduleItems", itemClass=AwardPaymentScheduleDto.class)
    private List<AwardPaymentScheduleDto> paymentScheduleItems;

    @JsonProperty(value="awardTransferringSponsors")
    @CollectionProperty(source="awardTransferringSponsors", itemClass=AwardTransferringSponsorDto.class)
    private List<AwardTransferringSponsorDto> awardTransferringSponsors;

    @JsonProperty(value="awardAmountInfos")
    @CollectionProperty(source = "awardAmountInfos", itemClass=AwardAmountInfoDto.class)
    private List<AwardAmountInfoDto> awardAmountInfos;

    @JsonProperty(value="awardCloseoutItems")
    @CollectionProperty(source = "awardCloseoutItems", itemClass=AwardCloseoutDto.class)
    private List<AwardCloseoutDto> awardCloseoutItems;

    @JsonProperty(value="awardCloseoutNewItems")
    @CollectionProperty(source = "awardCloseoutNewItems", itemClass=AwardCloseoutDto.class)
    private List<AwardCloseoutDto> awardCloseoutNewItems;

    @JsonProperty(value="fundingProposals")
    @CollectionProperty(source = "fundingProposals", itemClass=AwardFundingProposalDto.class)
    private List<AwardFundingProposalDto> fundingProposals;

    @JsonProperty(value="allFundingProposals")
    @CollectionProperty(source = "allFundingProposals", itemClass=AwardFundingProposalDto.class)
    private List<AwardFundingProposalDto> allFundingProposals;

    @JsonProperty(value="awardBudgetLimits")
    @CollectionProperty(source = "awardBudgetLimits", itemClass=AwardBudgetLimitDto.class)
    private List<AwardBudgetLimitDto> awardBudgetLimits;

    @JsonProperty(value="budgets")
    @CollectionProperty(source = "budgets", itemClass=AwardBudgetExtDto.class)
    private List<AwardBudgetExtDto> budgets;

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

    public List<AwardCommentDto> getAwardComments() {
        return awardComments;
    }

    public void setAwardComments(List<AwardCommentDto> awardComments) {
        this.awardComments = awardComments;
    }

    public List<AwardSponsorContactDto> getSponsorContacts() {
        return sponsorContacts;
    }

    public void setSponsorContacts(List<AwardSponsorContactDto> sponsorContacts) {
        this.sponsorContacts = sponsorContacts;
    }

    public List<AwardCostShareDto> getAwardCostShares() {
        return awardCostShares;
    }

    public void setAwardCostShares(List<AwardCostShareDto> awardCostShares) {
        this.awardCostShares = awardCostShares;
    }

    public List<AwardFandARateDto> getAwardFandaRate() {
        return awardFandaRate;
    }

    public void setAwardFandaRate(List<AwardFandARateDto> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
    }

    public List<AwardFandADistributionDto> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }

    public void setAwardDirectFandADistributions(List<AwardFandADistributionDto> awardDirectFandADistributions) {
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }

    public List<AwardUnitContactDto> getAwardUnitContacts() {
        return awardUnitContacts;
    }

    public void setAwardUnitContacts(List<AwardUnitContactDto> awardUnitContacts) {
        this.awardUnitContacts = awardUnitContacts;
    }

    public List<AwardApprovedEquipmentDto> getApprovedEquipmentItems() {
        return approvedEquipmentItems;
    }

    public void setApprovedEquipmentItems(List<AwardApprovedEquipmentDto> approvedEquipmentItems) {
        this.approvedEquipmentItems = approvedEquipmentItems;
    }

    public List<AwardApprovedForeignTravelDto> getApprovedForeignTravelTrips() {
        return approvedForeignTravelTrips;
    }

    public void setApprovedForeignTravelTrips(List<AwardApprovedForeignTravelDto> approvedForeignTravelTrips) {
        this.approvedForeignTravelTrips = approvedForeignTravelTrips;
    }

    public List<AwardPaymentScheduleDto> getPaymentScheduleItems() {
        return paymentScheduleItems;
    }

    public void setPaymentScheduleItems(List<AwardPaymentScheduleDto> paymentScheduleItems) {
        this.paymentScheduleItems = paymentScheduleItems;
    }

    public List<AwardTransferringSponsorDto> getAwardTransferringSponsors() {
        return awardTransferringSponsors;
    }

    public void setAwardTransferringSponsors(List<AwardTransferringSponsorDto> awardTransferringSponsors) {
        this.awardTransferringSponsors = awardTransferringSponsors;
    }

    public List<AwardAmountInfoDto> getAwardAmountInfos() {
        return awardAmountInfos;
    }

    public void setAwardAmountInfos(List<AwardAmountInfoDto> awardAmountInfos) {
        this.awardAmountInfos = awardAmountInfos;
    }

    public List<AwardCloseoutDto> getAwardCloseoutItems() {
        return awardCloseoutItems;
    }

    public void setAwardCloseoutItems(List<AwardCloseoutDto> awardCloseoutItems) {
        this.awardCloseoutItems = awardCloseoutItems;
    }

    public List<AwardCloseoutDto> getAwardCloseoutNewItems() {
        return awardCloseoutNewItems;
    }

    public void setAwardCloseoutNewItems(List<AwardCloseoutDto> awardCloseoutNewItems) {
        this.awardCloseoutNewItems = awardCloseoutNewItems;
    }

    public List<AwardFundingProposalDto> getFundingProposals() {
        return fundingProposals;
    }

    public void setFundingProposals(List<AwardFundingProposalDto> fundingProposals) {
        this.fundingProposals = fundingProposals;
    }

    public List<AwardFundingProposalDto> getAllFundingProposals() {
        return allFundingProposals;
    }

    public void setAllFundingProposals(List<AwardFundingProposalDto> allFundingProposals) {
        this.allFundingProposals = allFundingProposals;
    }

    public List<AwardBudgetLimitDto> getAwardBudgetLimits() {
        return awardBudgetLimits;
    }

    public void setAwardBudgetLimits(List<AwardBudgetLimitDto> awardBudgetLimits) {
        this.awardBudgetLimits = awardBudgetLimits;
    }

    public List<AwardBudgetExtDto> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<AwardBudgetExtDto> budgets) {
        this.budgets = budgets;
    }
}
