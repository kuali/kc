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
import org.kuali.coeus.instprop.impl.api.customSerializers.CustomSqlDateSerializer;

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
}
