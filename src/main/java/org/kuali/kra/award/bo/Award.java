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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.document.KeywordsManager;
import org.kuali.kra.document.SpecialReviewManager;

/**
 * 
 * This class is Award Business Object.
 * It implements ProcessKeywords to process all operations related to AwardScenceKeywords.
 */
public class Award extends KraPersistableBusinessObjectBase implements KeywordsManager<AwardScienceKeyword>,SpecialReviewManager<AwardSpecialReview>{
    
    private static final String ONE = "1";
    private static final String AWARD_TITLE = "Award";
    private static final String YES_FLAG = "Y";
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3797220122448310165L;
    private Long awardId;
    private AwardDocument awardDocument;
    private String awardNumber;
    private Integer sequenceNumber;
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
    private String indirectCostIndicator;
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
    private String documentFundingId;
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
    
    private List<AwardComment> awardComments;
    private Map<Integer, AwardComment> commentMap;
    private List<AwardCostShare> awardCostShares;
    private List<AwardFandaRate> awardFandaRate;
    private List<AwardReportTerm> awardReportTerms;
       

    private List<AwardApprovedSubaward> awardApprovedSubawards;
    
    private List<AwardScienceKeyword> keywords;
    private List<AwardSpecialReview> specialReviews;

    /**
     * 
     * Constructs an Award BO.
     */
    public Award() {
        super();
        initializeAwardWithDefaultValues();       
        setAwardCostShares(new ArrayList<AwardCostShare>());
        setAwardComments(new ArrayList<AwardComment>());
        awardApprovedSubawards = (new ArrayList<AwardApprovedSubaward>());
        setAwardFandaRate(new ArrayList<AwardFandaRate>());
        setAwardReportTerms(new ArrayList<AwardReportTerm>());
        keywords = new ArrayList<AwardScienceKeyword>();
        specialReviews = new ArrayList<AwardSpecialReview>();
    }
    
    /**
     * 
     * This method sets the default values for initial persistence as part of skeleton.
     * As various panels are developed; corresponding field initializations should be removed from
     * this method.  
     */
    private void initializeAwardWithDefaultValues(){
        setAwardNumber(ONE);
        setSequenceNumber(1);
        setSponsorCode(ONE);
        setStatusCode(1);
        setApprovedEquipmentIndicator(YES_FLAG);
        setApprovedForeignTripIndicator(YES_FLAG);
        setSubContractIndicator(YES_FLAG);
        setCostSharingIndicator(YES_FLAG);
        setIdcIndicator(YES_FLAG);
        setPaymentScheduleIndicator(YES_FLAG);
        setScienceCodeIndicator(YES_FLAG);
        setSpecialReviewIndicator(YES_FLAG);
        setTransferSponsorIndicator(YES_FLAG);
        setActivityTypeCode(1);
        setAwardTypeCode(1);
        setBasisOfPaymentCode(1);
        setMethodOfPaymentCode(1);
        setTitle(AWARD_TITLE);   
    }
    
    /**
    *
    * @return
    */
    private Map<Integer, AwardComment> getCommentMap(){
        if(commentMap == null){
            commentMap = new HashMap<Integer, AwardComment>();
            for(AwardComment ac : awardComments){
                commentMap.put(ac.getCommentType().getCommentTypeCode(), ac);
            }
        }
        return commentMap;
    }
    
    /**
     *
     * @return
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     *
     * @param awardId
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    
    /**
     *
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
    }
    
    /**
     *
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }


    /**
     *
     * @return
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     *
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    
    /**
     *
     * @return
     */
    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     *
     * @param sponsorCode
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }


    /**
     *
     * @return
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    /**
     *
     * @return
     */
    public Integer getTemplateCode() {
        return templateCode;
    }

    /**
     *
     * @param templateCode
     */
    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }


    /**
     *
     * @return
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @param accountNumber
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     *
     * @return
     */
    public String getApprovedEquipmentIndicator() {
        return approvedEquipmentIndicator;
    }

    /**
     *
     * @param approvedEquipmentIndicator
     */
    public void setApprovedEquipmentIndicator(String approvedEquipmentIndicator) {
        this.approvedEquipmentIndicator = approvedEquipmentIndicator;
    }


    /**
     *
     * @return
     */
    public String getApprovedForeignTripIndicator() {
        return approvedForeignTripIndicator;
    }

    /**
     *
     * @param approvedForeignTripIndicator
     */
    public void setApprovedForeignTripIndicator(String approvedForeignTripIndicator) {
        this.approvedForeignTripIndicator = approvedForeignTripIndicator;
    }


    /**
     *
     * @return
     */
    public String getSubContractIndicator() {
        return subContractIndicator;
    }

    /**
     *
     * @param subContractIndicator
     */
    public void setSubContractIndicator(String subContractIndicator) {
        this.subContractIndicator = subContractIndicator;
    }


    /**
     *
     * @return
     */
    public Date getAwardEffectiveDate() {
        return awardEffectiveDate;
    }

    /**
     *
     * @param awardEffectiveDate
     */
    public void setAwardEffectiveDate(Date awardEffectiveDate) {
        this.awardEffectiveDate = awardEffectiveDate;
    }


    /**
     *
     * @return
     */
    public Date getAwardExecutionDate() {
        return awardExecutionDate;
    }

    /**
     *
     * @param awardExecutionDate
     */
    public void setAwardExecutionDate(Date awardExecutionDate) {
        this.awardExecutionDate = awardExecutionDate;
    }


    /**
     *
     * @return
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     *
     * @param beginDate
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


    /**
     *
     * @return
     */
    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    /**
     *
     * @param costSharingIndicator
     */
    public void setCostSharingIndicator(String costSharingIndicator) {
        this.costSharingIndicator = costSharingIndicator;
    }


    /**
     * 
     * For ease of use in JSP and tag files; the getter method uses acronym instead of full meaning.
     * idcIndicator is an acronym. Its full meaning is Indirect Cost Indicator 
     * @return
     */
    public String getIdcIndicator() {
        return indirectCostIndicator;
    }

    /**
     * 
     * For ease of use in JSP and tag files; the setter method uses acronym instead of full meaning.
     * idcIndicator is an acronym. Its full meaning is Indirect Cost Indicator
     * @param indirectCostIndicator
     */
    public void setIdcIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }


    /**
     *
     * @return
     */
    public String getModificationNumber() {
        return modificationNumber;
    }

    /**
     *
     * @param modificationNumber
     */
    public void setModificationNumber(String modificationNumber) {
        this.modificationNumber = modificationNumber;
    }


    /**
     * NSFCode is an acronym. Its full meaning is National Science Foundation.
     * @return
     */
    public String getNsfCode() {
        return nsfCode;
    }

    /**
     * NSFCode is an acronym. Its full meaning is National Science Foundation.
     * @param nsfCode
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }


    /**
     *
     * @return
     */
    public String getPaymentScheduleIndicator() {
        return paymentScheduleIndicator;
    }

    /**
     *
     * @param paymentScheduleIndicator
     */
    public void setPaymentScheduleIndicator(String paymentScheduleIndicator) {
        this.paymentScheduleIndicator = paymentScheduleIndicator;
    }


    /**
     *
     * @return
     */
    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    /**
     *
     * @param scienceCodeIndicator
     */
    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }


    /**
     *
     * @return
     */
    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    /**
     *
     * @param specialReviewIndicator
     */
    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }


    /**\
     *
     * @return
     */
    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    /**
     *
     * @param sponsorAwardNumber
     */
    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }


    /**
     *
     * @return
     */
    public String getTransferSponsorIndicator() {
        return transferSponsorIndicator;
    }

    /**
     * 
     * @param transferSponsorIndicator
     */
    public void setTransferSponsorIndicator(String transferSponsorIndicator) {
        this.transferSponsorIndicator = transferSponsorIndicator;
    }


    /**
     *
     * @return
     */
    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    /**
     *
     * @param accountTypeCode
     */
    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }


    /**
     * 
     * @return
     */
    public Integer getActivityTypeCode() {
        return activityTypeCode;
    }

    /**
     *
     * @param activityTypeCode
     */
    public void setActivityTypeCode(Integer activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }


    /**
     *
     * @return
     */
    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    /**
     *
     * @param awardTypeCode
     */
    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }


    /**
     *
     * @return
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     *
     * @param primeSponsorCode
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }


    /**
     *
     * @return
     */
    public Integer getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    /**
     *
     * @param basisOfPaymentCode
     */
    public void setBasisOfPaymentCode(Integer basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }


    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     * @return
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * 
     * cfdaNumber is an acronym. Its full meaning is Catalog of Federal Domestic Assistance
     * @param cfdaNumber
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }


    /**
     *
     * @return
     */
    public Integer getCompetingRenewalProposalDue() {
        return competingRenewalProposalDue;
    }

    /**
     *
     * @param competingRenewalProposalDue
     */
    public void setCompetingRenewalProposalDue(Integer competingRenewalProposalDue) {
        this.competingRenewalProposalDue = competingRenewalProposalDue;
    }


    /**
     * 
     * @return
     */
    public String getDocumentFundingId() {
        return documentFundingId;
    }

    /**
     * 
     * This method...
     * @param documentFundingId
     */
    public void setDocumentFundingId(String documentFundingId) {
        this.documentFundingId = documentFundingId;
    }


    /**
     *
     * @return
     */
    public Integer getFinalInvoiceDue() {
        return finalInvoiceDue;
    }

    /**
     *
     * @param finalInvoiceDue
     */
    public void setFinalInvoiceDue(Integer finalInvoiceDue) {
        this.finalInvoiceDue = finalInvoiceDue;
    }


    /**
     *
     * @return
     */
    public Integer getInvoiceNumberOfCopies() {
        return invoiceNumberOfCopies;
    }

    /**
     *
     * @param invoiceNumberOfCopies
     */
    public void setInvoiceNumberOfCopies(Integer invoiceNumberOfCopies) {
        this.invoiceNumberOfCopies = invoiceNumberOfCopies;
    }


    /**
     *
     * @return
     */
    public Integer getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    /**
     *
     * @param methodOfPaymentCode
     */
    public void setMethodOfPaymentCode(Integer methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }


    /**
     *
     * @return
     */
    public Integer getNonCompetingContProposalDue() {
        return nonCompetingContProposalDue;
    }

    /**
     *
     * @param nonCompetingContProposalDue
     */
    public void setNonCompetingContProposalDue(Integer nonCompetingContProposalDue) {
        this.nonCompetingContProposalDue = nonCompetingContProposalDue;
    }


    /**
     *
     * @return
     */
    public Integer getPaymentInvoiceFrequencyCode() {
        return paymentInvoiceFrequencyCode;
    }

    /**
     *
     * @param paymentInvoiceFrequencyCode
     */
    public void setPaymentInvoiceFrequencyCode(Integer paymentInvoiceFrequencyCode) {
        this.paymentInvoiceFrequencyCode = paymentInvoiceFrequencyCode;
    }


    /**
     *
     * @return
     */
    public KualiDecimal getPreAwardAuthorizedAmount() {
        return preAwardAuthorizedAmount;
    }

    /**
     *
     * @param preAwardAuthorizedAmount
     */
    public void setPreAwardAuthorizedAmount(KualiDecimal preAwardAuthorizedAmount) {
        this.preAwardAuthorizedAmount = preAwardAuthorizedAmount;
    }


    /**
     *
     * @return
     */
    public Date getPreAwardEffectiveDate() {
        return preAwardEffectiveDate;
    }

    /**
     *
     * @param preAwardEffectiveDate
     */
    public void setPreAwardEffectiveDate(Date preAwardEffectiveDate) {
        this.preAwardEffectiveDate = preAwardEffectiveDate;
    }


    /**
     *
     * @return
     */
    public String getProcurementPriorityCode() {
        return procurementPriorityCode;
    }

    /**
     *
     * @param procurementPriorityCode
     */
    public void setProcurementPriorityCode(String procurementPriorityCode) {
        this.procurementPriorityCode = procurementPriorityCode;
    }


    /**
     *
     * @return
     */
    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     *
     * @param proposalNumber
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }


    /**
     *
     * @return
     */
    public Integer getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    /**
     *
     * @param specialEbRateOffCampus
     */
    public void setSpecialEbRateOffCampus(Integer specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }


    /**
     *
     * @return
     */
    public Integer getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    /**
     *
     * @param specialEbRateOnCampus
     */
    public void setSpecialEbRateOnCampus(Integer specialEbRateOnCampus) {
        this.specialEbRateOnCampus = specialEbRateOnCampus;
    }


    /**
     *
     * @return
     */
    public String getSubPlanFlag() {
        return subPlanFlag;
    }

    /**
     *
     * @param subPlanFlag
     */
    public void setSubPlanFlag(String subPlanFlag) {
        this.subPlanFlag = subPlanFlag;
    }


    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    

    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();        
        hashMap.put("awardId", getAwardId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("sponsorCode", getSponsorCode());
        hashMap.put("statusCode", getStatusCode());
        hashMap.put("templateCode", getTemplateCode());
        hashMap.put("accountNumber", getAccountNumber());
        hashMap.put("approvedEquipmentIndicator", getApprovedEquipmentIndicator());
        hashMap.put("approvedForeignTripIndicator", getApprovedForeignTripIndicator());
        hashMap.put("subContractIndicator", getSubContractIndicator());
        hashMap.put("awardEffectiveDate", getAwardEffectiveDate());
        hashMap.put("awardExecutionDate", getAwardExecutionDate());
        hashMap.put("beginDate", getBeginDate());
        hashMap.put("costSharingIndicator", getCostSharingIndicator());
        hashMap.put("indirectCostIndicator", getIdcIndicator());
        hashMap.put("modificationNumber", getModificationNumber());
        hashMap.put("nsfCode", getNsfCode());
        hashMap.put("paymentScheduleIndicator", getPaymentScheduleIndicator());
        hashMap.put("scienceCodeIndicator", getScienceCodeIndicator());
        hashMap.put("specialReviewIndicator", getSpecialReviewIndicator());
        hashMap.put("sponsorAwardNumber", getSponsorAwardNumber());
        hashMap.put("transferSponsorIndicator", getTransferSponsorIndicator());
        hashMap.put("accountTypeCode", getAccountTypeCode());
        hashMap.put("activityTypeCode", getActivityTypeCode());
        hashMap.put("awardTypeCode", getAwardTypeCode());
        hashMap.put("primeSponsorCode", getPrimeSponsorCode());
        hashMap.put("basisOfPaymentCode", getBasisOfPaymentCode());
        hashMap.put("cfdaNumber", getCfdaNumber());
        hashMap.put("competingRenewalProposalDue", getCompetingRenewalProposalDue());
        hashMap.put("documentFundingId", getDocumentFundingId());
        hashMap.put("finalInvoiceDue", getFinalInvoiceDue());
        hashMap.put("invoiceNumberOfCopies", getInvoiceNumberOfCopies());
        hashMap.put("methodOfPaymentCode", getMethodOfPaymentCode());
        hashMap.put("nonCompetingContProposalDue", getNonCompetingContProposalDue());
        hashMap.put("paymentInvoiceFrequencyCode", getPaymentInvoiceFrequencyCode());
        hashMap.put("preAwardAuthorizedAmount", getPreAwardAuthorizedAmount());
        hashMap.put("preAwardEffectiveDate", getPreAwardEffectiveDate());
        hashMap.put("procurementPriorityCode", getProcurementPriorityCode());
        hashMap.put("proposalNumber", getProposalNumber());        
        hashMap.put("specialEbRateOffCampus", getSpecialEbRateOffCampus());
        hashMap.put("specialEbRateOnCampus", getSpecialEbRateOnCampus());
        hashMap.put("subPlanFlag", getSubPlanFlag());
        hashMap.put("title", getTitle());
        hashMap.put("awardCostShares", getAwardCostShares());
        hashMap.put("awardComments", getAwardComments());
        return hashMap;
    }    

    public AwardDocument getAwardDocument() {
        return awardDocument;
    }

    public void setAwardDocument(AwardDocument awardDocument) {
        this.awardDocument = awardDocument;
    }

    public List<AwardComment> getAwardComments() {
        return awardComments;
    }

    public void setAwardComments(List<AwardComment> awardComments) {
        this.awardComments = awardComments;
    }

    public List<AwardCostShare> getAwardCostShares() {
        return awardCostShares;
    }

    public void setAwardCostShares(List<AwardCostShare> awardCostShares) {
        this.awardCostShares = awardCostShares;
    }
    
    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        return awardApprovedSubawards;
    }

    public void setAwardApprovedSubawards(List<AwardApprovedSubaward> awardApprovedSubawards) {
        this.awardApprovedSubawards = awardApprovedSubawards;
    }

    /**
    *
    * Get the award Cost Share Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardCostShareComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.COST_SHARE_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createCostShareComment(this);  //if null initialize in factory class
            awardComments.add(awardComment);  //add the new CostShareComment to the awardComments list.
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award F & A Rates Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardFandaRateComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.FANDA_RATE_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createFandaRateComment(this);  //if null initialize in factory class
            awardComments.add(awardComment);  //add the new CostShareComment to the awardComments list.
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    public KualiDecimal getTotalCostShareCommitmentAmount(){
        KualiDecimal returnVal = new KualiDecimal(0);
        for(AwardCostShare awardCostShare : awardCostShares){
            returnVal = returnVal.add(awardCostShare.getCommitmentAmount());
        }
        return returnVal;
    }
    
    public KualiDecimal getTotalApprovedSubawardAmount(){
        KualiDecimal returnVal = new KualiDecimal(0);
        for(AwardApprovedSubaward awardApprovedSubaward : awardApprovedSubawards){
            returnVal = returnVal.add(awardApprovedSubaward.getAmount());
        }
        return returnVal;
    }
    
    

    public List<AwardFandaRate> getAwardFandaRate() {
        return awardFandaRate;
    }

    public void setAwardFandaRate(List<AwardFandaRate> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
    }
    
    public List<AwardReportTerm> getAwardReportTerms() {
        return awardReportTerms;
    }

    public void setAwardReportTerms(List<AwardReportTerm> awardReportTerms) {
        this.awardReportTerms = awardReportTerms;
    }

    /**
     * Gets the keywords attribute. 
     * @return Returns the keywords.
     */
    public List<AwardScienceKeyword> getKeywords() {
        return keywords;
    }

    /**
     * Sets the keywords attribute value.
     * @param keywords The keywords to set.
     */
    public void setKeywords(List<AwardScienceKeyword> keywords) {
        this.keywords = keywords;
    }
    /**
     * Add selected science keyword to award science keywords list.
     * @see org.kuali.kra.document.KeywordsManager#addKeyword(org.kuali.kra.bo.ScienceKeyword)
     */
    public void addKeyword(ScienceKeyword scienceKeyword) {
        AwardScienceKeyword awardScienceKeyword = new AwardScienceKeyword(getAwardId(), scienceKeyword);
        getKeywords().add(awardScienceKeyword);
    }

    /**
     * It returns the ScienceKeyword object from keywords list
     * @see org.kuali.kra.document.KeywordsManager#getKeyword(int)
     */
    public AwardScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
    }

    /**
     * Sets the awardSpecialReviews attribute value.
     * @param awardSpecialReviews The awardSpecialReviews to set.
     */
    public void setSpecialReviews(List<AwardSpecialReview> awardSpecialReviews) {
        this.specialReviews = awardSpecialReviews;
    }

    /**
     * Add AwardSpecialReview to the AwardSpecialReview list
     * @see org.kuali.kra.document.SpecialReviewManager#addSpecialReview(java.lang.Object)
     */
    public void addSpecialReview(AwardSpecialReview specialReview) {
        specialReview.setAward(this);
        getSpecialReviews().add(specialReview);
    }

    /**
     * Get AwardSpecialReview from special review list
     * @see org.kuali.kra.document.SpecialReviewManager#getSpecialReview(int)
     */
    public AwardSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    /**
     * Get special review list
     * @see org.kuali.kra.document.SpecialReviewManager#getSpecialReviews()
     */
    public List<AwardSpecialReview> getSpecialReviews() {
        return specialReviews;
    }
}
