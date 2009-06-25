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
package org.kuali.kra.award.home;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.KeywordsManager;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.infrastructure.AwardRoleConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * 
 * This class is Award Business Object.
 * It implements ProcessKeywords to process all operations related to AwardScenceKeywords.
 */
public class Award extends KraPersistableBusinessObjectBase implements KeywordsManager<AwardScienceKeyword>,
                                                                        SpecialReviewHandler<AwardSpecialReview>, 
                                                                        Permissionable, SequenceOwner {
    public static final String AWARD_NAMESPACE_CODE = "KC-AWARD";
    public static final String DEFAULT_AWARD_NUMBER = "1";
    
    private static final String YES_FLAG = "Y";
    private static final int TOTAL_STATIC_REPORTS = 4;
    
    private static final long serialVersionUID = 3797220122448310165L;
    private Long awardId;
    private AwardDocument awardDocument;
    private String awardNumber;
    private Integer sequenceNumber;
    private String sponsorCode;
    private Integer statusCode;
    private AwardStatus awardStatus;
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
    private String cfdaNumber;
    private String documentFundingId;
    private KualiDecimal preAwardAuthorizedAmount;
    private Date preAwardEffectiveDate;
    private KualiDecimal preAwardInstitutionalAuthorizedAmount;
    private Date preAwardInstitutionalEffectiveDate;
    private String procurementPriorityCode;
    private String proposalNumber;
    private KualiDecimal specialEbRateOffCampus;
    private KualiDecimal specialEbRateOnCampus;
    private String subPlanFlag;
    private String title;
    private String archiveLocation; 
    private Date closeoutDate;
    
    @AwardSyncable private Integer templateCode; 
    @AwardSyncable private String primeSponsorCode; 
    @AwardSyncable private String nonCompetingContPrpslDueCode; 
    @AwardSyncable private String competingRenewalPrpslDueCode; 
    @AwardSyncable private String basisOfPaymentCode; 
    @AwardSyncable private String methodOfPaymentCode; 
    @AwardSyncable private String paymentInvoiceFreqCode; 
    @AwardSyncable private Integer invoiceNumberOfCopies; 
    @AwardSyncable private Integer finalInvoiceDue;
    
    private AwardTemplate awardTemplate;
    private Frequency nonCompetingContPrpslDue;
    private Frequency competingRenewalPrpslDue;
    private Frequency paymentInvoiceFrequency;
    private AwardBasisOfPayment awardBasisOfPayment;
    private AwardMethodOfPayment awardMethodOfPayment;
    
    
    
    private Sponsor sponsor;
    private Sponsor primeSponsor;

    private Unit leadUnit;
    @AwardSyncableList(syncClass = AwardComment.class,syncMethodName="syncAwardComments")
    public List<AwardComment> awardComments;
    @AwardSyncableList(syncClass = AwardReportTerm.class)
    private List<AwardReportTerm> awardReportTermItems;
    @AwardSyncableList(syncClass = AwardSponsorTerm.class,syncMethodName="syncAwardSponsorTerms")
    private List<AwardSponsorTerm> awardSponsorTerms;
    @AwardSyncableList(syncClass = AwardSponsorContact.class)
    private List<AwardSponsorContact> sponsorContacts;

    private List<AwardCustomData> awardCustomDataList;
    
    private Map<String, AwardComment> commentMap;
    private List<AwardCostShare> awardCostShares;
    private List<AwardFandaRate> awardFandaRate;    
    private List<AwardDirectFandADistribution> awardDirectFandADistributions;

    private List<AwardApprovedSubaward> awardApprovedSubawards;
    
    private List<AwardScienceKeyword> keywords;
    
    private List<AwardPerson> projectPersons;
    private List<AwardUnitContact> awardUnitContacts;
    
    private List<AwardSpecialReview> specialReviews;
    private List<AwardApprovedEquipment> approvedEquipmentItems;
    private List<AwardApprovedForeignTravel> approvedForeignTravelTrips;
    private List<AwardPaymentSchedule> paymentScheduleItems;
    private List<AwardTransferringSponsor> awardTransferringSponsors;
    private List<AwardAmountInfo> awardAmountInfos;
    private List<AwardCloseout> awardCloseoutItems;
    
    // Additional fields for lookup
    private String leadUnitName;
    private String leadUnitNumber;
    private Person ospAdministrator;
    private String ospAdministratorName;
    private String principalInvestigatorName;
    private String statusDescription;
    private String sponsorName;
    
    /**
     * 
     * Constructs an Award BO.
     */
    public Award() {
        super();
        initializeAwardWithDefaultValues();
        initializeCollections();
    }
    
    /**
     * 
     * This method sets the default values for initial persistence as part of skeleton.
     * As various panels are developed; corresponding field initializations should be removed from
     * this method.  
     */
    private void initializeAwardWithDefaultValues(){
        setAwardNumber(DEFAULT_AWARD_NUMBER);
        setSequenceNumber(1);
        setApprovedEquipmentIndicator(YES_FLAG);
        setApprovedForeignTripIndicator(YES_FLAG);
        setSubContractIndicator(YES_FLAG);
        setCostSharingIndicator(YES_FLAG);
        setIdcIndicator(YES_FLAG);
        setPaymentScheduleIndicator(YES_FLAG);
        setScienceCodeIndicator(YES_FLAG);
        setSpecialReviewIndicator(YES_FLAG);
        setTransferSponsorIndicator(YES_FLAG);
    }
    
    /**
    *
    * @return
    */
    private Map<String, AwardComment> getCommentMap() {
        if (commentMap == null) {
            commentMap = new HashMap<String, AwardComment>();
            for (AwardComment ac : awardComments) {
                commentMap.put(ac.getCommentType().getCommentTypeCode(), ac);
            }
        }
        return commentMap;
    }

    /**
     * Gets the templateCode attribute. 
     * @return Returns the templateCode.
     */
    public Integer getTemplateCode() {
        return templateCode;
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
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
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
    public List<AwardApprovedEquipment> getApprovedEquipmentItems() {
        return approvedEquipmentItems;
    }
    
    /**
     * @return
     */
    public List<AwardUnitContact> getAwardUnitContacts() {
        return awardUnitContacts;
    }
    
    /**
     * @param index
     * @return
     */
    public AwardPerson getProjectPerson(int index) {
        return projectPersons.get(index);
    }
    
    /**
     * @return
     */
    public List<AwardPerson> getProjectPersons() {
        return projectPersons;
    }
    
    /**
     * @return
     */
    public int getAwardContactsCount() {
        return awardUnitContacts.size();
    }
    
    /**
     * @return
     */
    public int getApprovedEquipmentItemCount() {
        return approvedEquipmentItems.size();
    }
    
    /**
     * @return
     */
    public int getApprovedForeignTravelTripCount() {
        return approvedForeignTravelTrips.size();
    }
    
    /**
     * @return
     */
    public List<AwardApprovedForeignTravel> getApprovedForeignTravelTrips() {
        return approvedForeignTravelTrips;
    }
    
    /**
     * @param awardUnitContacts
     */
    public void setAwardUnitContacts(List<AwardUnitContact> awardUnitContacts) {
        this.awardUnitContacts = awardUnitContacts;
    }
    
    /**
     * 
     */
    public void setApprovedEquipmentItems(List<AwardApprovedEquipment> awardApprovedEquipmentItems) {
       this.approvedEquipmentItems = awardApprovedEquipmentItems;
    }

    /**
     * 
     */
    public void setApprovedForeignTravelTrips(List<AwardApprovedForeignTravel> approvedForeignTravelTrips) {
       this.approvedForeignTravelTrips = approvedForeignTravelTrips;
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
     * This method returns the project end date which is housed in the Amount Info list index[0] on the award.
     * @return
     */
    public Date getProjectEndDate() {
        return awardAmountInfos.get(0).getFinalExpirationDate();
    }
    
    /**
     * This method sets the project end date which is housed in the Amount Info list index[0] on the award.
     * @return
     */
    public void setProjectEndDate(Date date) {
        this.awardAmountInfos.get(0).setFinalExpirationDate(date);
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
     * This method finds the lead unit name, if any
     * @return
     */
    public String getUnitName() {
        Unit unit = getLeadUnit();
        if(unit != null) {
            leadUnitName = unit.getUnitName(); 
        }
        return leadUnitName;
    }
    
    /**
     * 
     * This method finds the lead unit number, if any
     * @return
     */
    public String getUnitNumber() {
        Unit leadUnit = getLeadUnit();
        if(leadUnit != null) {
            leadUnitNumber = leadUnit.getUnitNumber(); 
        }
        return leadUnitNumber;
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
//    public Integer getCompetingRenewalProposalDue() {
//        return Integer.parseInt(getCompetingRenewalPrpslDueCode());
//    }

    /**
     *
     * @param competingRenewalProposalDue
     */
//    public void setCompetingRenewalProposalDue(Integer competingRenewalProposalDue) {
//        setCompetingRenewalPrpslDueCode(competingRenewalProposalDue.toString());
//    }


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
     * @return
     */
    public Person getOspAdministrator() {
        for(AwardUnitContact contact: getAwardUnitContacts()) {
            if(contact.isOspAdministrator()) {
                ospAdministrator = contact.getPerson();
                break;
            }
        }
        return ospAdministrator;
    }
    
    /**
     * @return
     */
    public String getOspAdministratorName() {
        Person ospAdministrator = getOspAdministrator();
        ospAdministratorName = ospAdministrator != null ? ospAdministrator.getFullName() : null;
        return ospAdministratorName;
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
    public KualiDecimal getSpecialEbRateOffCampus() {
        return specialEbRateOffCampus;
    }

    /**
     *
     * @param specialEbRateOffCampus
     */
    public void setSpecialEbRateOffCampus(KualiDecimal specialEbRateOffCampus) {
        this.specialEbRateOffCampus = specialEbRateOffCampus;
    }


    /**
     *
     * @return
     */
    public KualiDecimal getSpecialEbRateOnCampus() {
        return specialEbRateOnCampus;
    }

    /**
     *
     * @param specialEbRateOnCampus
     */
    public void setSpecialEbRateOnCampus(KualiDecimal specialEbRateOnCampus) {
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
        hashMap.put("documentFundingId", getDocumentFundingId());
        hashMap.put("finalInvoiceDue", getFinalInvoiceDue());
        hashMap.put("invoiceNumberOfCopies", getInvoiceNumberOfCopies());
        hashMap.put("methodOfPaymentCode", getMethodOfPaymentCode());
        hashMap.put("preAwardAuthorizedAmount", getPreAwardAuthorizedAmount());
        hashMap.put("preAwardEffectiveDate", getPreAwardEffectiveDate());
        hashMap.put("preAwardInstitutionalAuthorizedAmount", getPreAwardInstitutionalAuthorizedAmount());
        hashMap.put("preAwardInstitutionalEffectiveDate", getPreAwardInstitutionalEffectiveDate());
        hashMap.put("procurementPriorityCode", getProcurementPriorityCode());
        hashMap.put("proposalNumber", getProposalNumber());        
        hashMap.put("specialEbRateOffCampus", getSpecialEbRateOffCampus());
        hashMap.put("specialEbRateOnCampus", getSpecialEbRateOnCampus());
        hashMap.put("subPlanFlag", getSubPlanFlag());
        hashMap.put("title", getTitle());
        hashMap.put("awardCostShares", getAwardCostShares());
        hashMap.put("awardComments", getAwardComments());
        hashMap.put("archiveLocation", this.getArchiveLocation());
        hashMap.put("closeoutDate", this.getCloseoutDate());
        return hashMap;
    }    

    /**
     * This method...
     * @return
     */
    public AwardDocument getAwardDocument() {
        if(awardDocument == null) {
            this.refreshReferenceObject("awardDocument");
        }
        return awardDocument;
    }

    /**
     * This method...
     * @param awardDocument
     */
    public void setAwardDocument(AwardDocument awardDocument) {
        this.awardDocument = awardDocument;
    }

    /**
     * This method...
     * @return
     */
    public List<AwardComment> getAwardComments() {
        return awardComments;
    }

    /**
     * This method...
     * @param awardComments
     */
    public void setAwardComments(List<AwardComment> awardComments) {
        this.awardComments = awardComments;
    }

    /**
     * This method...
     * @return
     */
    public List<AwardCostShare> getAwardCostShares() {
        return awardCostShares;
    }

    /**
     * This method...
     * @param awardCostShares
     */
    public void setAwardCostShares(List<AwardCostShare> awardCostShares) {
        this.awardCostShares = awardCostShares;
    }
    
    /**
     * This method...
     * @return
     */
    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        return awardApprovedSubawards;
    }

    /**
     * This method...
     * @param awardApprovedSubawards
     */
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
            awardComment = awardCommentFactory.createCostShareComment();  //if null initialize in factory class
            add(awardComment);  //add the new CostShareComment to the awardComments list.
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award PreAward Sponsor Authorizations comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getawardPreAwardSponsorAuthorizationComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.PREAWARD_SPONSOR_AUTHORIZATION_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createPreAwardSponsorAuthorizationComment();  
            add(awardComment);  
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award PreAward Institutional Authorizations comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getawardPreAwardInstitutionalAuthorizationComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.PREAWARD_INSTITUTIONAL_AUTHORIZATION_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createPreAwardInstitutionalAuthorizationComment();  //if null initialize in factory class
            add(awardComment);  //add the new CostShareComment to the awardComments list.
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
            awardComment = awardCommentFactory.createFandaRateComment();  //if null initialize in factory class
            add(awardComment);  //add the new CostShareComment to the awardComments list.
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award AwardPaymentAndInvoiceRequirementsComments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardPaymentAndInvoiceRequirementsComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.PAYMENT_AND_INVOICES_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createPaymentAndInvoiceComment();  //if null initialize in factory class
            add(awardComment);  //add the new Payment And Invoice to the awardComments list.
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Benefits Rate comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardBenefitsRateComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.BENEFITS_RATES_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createBenefitsRateComment();  //if null initialize in factory class
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);  //add to Map
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award General Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardGeneralComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.GENERAL_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createGeneralComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award fiscal report comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardFiscalReportComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.FISCAL_REPORT_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createFiscalReportComment();  
            add(awardComment);  
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Intellectual Property comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardIntellectualPropertyComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.INTELLECTUAL_PROPERTY_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createIntellecutalPropertyComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Procurement Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardProcurementComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.PROCUREMENT_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createProcurementComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Award Property Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardPropertyComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.PROPERTY_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createPropertyComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Special Rate comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardSpecialRate(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.SPECIAL_RATE_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createSpecialRateComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Special Review Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardSpecialReviewComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();  //create Factory class
        AwardComment awardComment = getCommentMap().get(Constants.SPECIAL_REVIEW_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createSpecialReviewComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Proposal Summary comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getawardProposalSummary(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createProposalSummaryComment();  
            add(awardComment);  
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Proposal comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getawardProposalComments(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.PROPOSAL_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createProposalComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    /**
    *
    * Get the award Proposal IP Review Comments.  If the comment has not been set...... initialize and return new Comment.
    */
    public AwardComment getAwardProposalIPReviewComment(){
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        AwardComment awardComment = getCommentMap().get(Constants.PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE);
        if(awardComment == null){
            awardComment = awardCommentFactory.createProposalIPReviewComment();
            add(awardComment);
            commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
        }
        return awardComment;
    }
    
    
    /**
     * This method calls getTotalAmount to calculate the total of all Commitment Amounts.
     * @return
     */
     public KualiDecimal getTotalCostShareCommitmentAmount() {
        return getTotalAmount(awardCostShares);
    }
     
     /**
      * This method calculates the total Cost Share Met amount for all Award Cost Shares.
      * @param valuableItems
      * @return The total value
      */
     public KualiDecimal getTotalCostShareMetAmount() {
         KualiDecimal returnVal = new KualiDecimal(0.00);
         for(AwardCostShare awardCostShare : awardCostShares) {
             KualiDecimal amount = awardCostShare.getCostShareMet() != null ? awardCostShare.getCostShareMet() : new KualiDecimal(0.00);
             returnVal = returnVal.add(amount);
         }
         return returnVal;
     }
     
     /**
      * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
      * @return The total value
      */
     public KualiDecimal getTotalDirectFandADistributionDirectCostAmount() {
         KualiDecimal returnVal = new KualiDecimal(0.00);
         for(AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
             KualiDecimal amount;
             if(awardDirectFandADistribution.getDirectCost() != null) {
                 amount = awardDirectFandADistribution.getDirectCost();
             }else {
                 amount = new KualiDecimal(0.00);
             }
             returnVal = returnVal.add(amount);
         }
         return returnVal;
     }
     
     /**
      * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
      * @return The total value
      */
     public KualiDecimal getTotalDirectFandADistributionIndirectCostAmount() {
         KualiDecimal returnVal = new KualiDecimal(0.00);
         for(AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
             KualiDecimal amount;
             if(awardDirectFandADistribution.getIndirectCost() != null) {
                 amount = awardDirectFandADistribution.getIndirectCost();
             }else {
                 amount = new KualiDecimal(0.00);
             }
             returnVal = returnVal.add(amount);
         }
         return returnVal;
     }
     
     /**
      * This method calculates the total Direct Cost Amount for all Direct F and A Distributions.
      * @return The total value
      */
     public KualiDecimal getTotalDirectFandADistributionAnticipatedCostAmount() {
         KualiDecimal returnVal = new KualiDecimal(0.00);
         returnVal = returnVal.add(getTotalDirectFandADistributionDirectCostAmount());
         returnVal = returnVal.add(getTotalDirectFandADistributionIndirectCostAmount());
         return returnVal;
     }
    
    /**
     * This method totals Approved SubAward amounts
     * @return
     */
    public KualiDecimal getTotalApprovedSubawardAmount() {
        return getTotalAmount(awardApprovedSubawards);
    }
    
     /**
     * This method totals Approved Equipment amounts
     * @return
     */
    public KualiDecimal getTotalApprovedEquipmentAmount(){
        return getTotalAmount(approvedEquipmentItems);
    }
    
    /**
     * This method Approved Foreign Travel trip amounts
     * @return
     */
    public KualiDecimal getTotalApprovedApprovedForeignTravelAmount() {
        return getTotalAmount(approvedForeignTravelTrips);
    }
    
    /**
     * This method...
     * @return
     */
    public List<AwardFandaRate> getAwardFandaRate() {
        return awardFandaRate;
    }

    /**
     * This method...
     * @param awardFandaRate
     */
    public void setAwardFandaRate(List<AwardFandaRate> awardFandaRate) {
        this.awardFandaRate = awardFandaRate;
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
     * @see org.kuali.kra.document.SpecialReviewHandler#addSpecialReview(java.lang.Object)
     */
    public void addSpecialReview(AwardSpecialReview specialReview) {
        specialReview.setAward(this);
        getSpecialReviews().add(specialReview);
    }

    /**
     * Get AwardSpecialReview from special review list
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReview(int)
     */
    public AwardSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    /**
     * Get special review list
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReviews()
     */
    public List<AwardSpecialReview> getSpecialReviews() {
        return specialReviews;
    }

    /**
     * Add an ApprovedEquipment item
     * @param newAwardApprovedEquipment
     */
    public void add(AwardApprovedEquipment approvedEquipmentItem) {
        approvedEquipmentItems.add(0, approvedEquipmentItem);
        approvedEquipmentItem.setAward(this);
    }
    
    /**
     * Add an AwardFandaRate
     * @param fandaRate
     */
    public void add(AwardFandaRate fandaRate) {
        awardFandaRate.add(fandaRate);
        fandaRate.setAward(this);
    }
    
    /**
     * @param awardSpecialReview
     */
    public void add(AwardSpecialReview awardSpecialReview) {
       specialReviews.add(awardSpecialReview);
       awardSpecialReview.setAward(this);
    }
    
    public void add(AwardSponsorContact awardSponsorContact) {
        sponsorContacts.add(awardSponsorContact);
        awardSponsorContact.setAward(this);
    }
    
    public void add(AwardReportTerm awardReportTerm) {        
        awardReportTermItems.add(awardReportTerm);
        awardReportTerm.setAward(this);
    }
    
    public void add(AwardCloseout awardCloseoutItem) {
        awardCloseoutItems.add(TOTAL_STATIC_REPORTS, awardCloseoutItem);        
        awardCloseoutItem.setAward(this);
    }
    
    public void addStaticCloseout(AwardCloseout awardCloseoutItem) {
        awardCloseoutItems.add(awardCloseoutItem);
        awardCloseoutItem.setAward(this);
    }
    
    /**
     * Add an Award Unit or Central Administration contact
     * @param newAwardApprovedEquipment
     */
    public void add(AwardUnitContact awardUnitContact) {
        awardUnitContacts.add(awardUnitContact);
        awardUnitContact.setAward(this);
    }
    
    /**
     * @param awardSponsorContact
     */
    public void addSponsorContact(AwardSponsorContact awardSponsorContact) {
        sponsorContacts.add(awardSponsorContact);
        awardSponsorContact.setAward(this);
    }
    
    /**
     * This method adds a Project Person to the award
     * @param projectPerson
     */
    public void add(AwardPerson projectPerson) {
        projectPersons.add(projectPerson);
        projectPerson.setAward(this);
    }
    
    /**
     * Add an
     * @param newAwardPaymentSchedule
     */
    public void add(AwardPaymentSchedule paymentScheduleItem) {
        paymentScheduleItems.add(paymentScheduleItem);
        paymentScheduleItem.setAward(this);
    }
    
    public void addAwardTransferringSponsor(Sponsor sponsor) {
        AwardTransferringSponsor awardTransferringSponsor = new AwardTransferringSponsor(this, sponsor);
        awardTransferringSponsors.add(0, awardTransferringSponsor);
    }
    
    protected void initializeCollections() {
        setAwardCostShares(new ArrayList<AwardCostShare>());
        setAwardComments(new ArrayList<AwardComment>());
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        setAwardFandaRate(new ArrayList<AwardFandaRate>());      
        setAwardReportTermItems(new ArrayList<AwardReportTerm>());
        keywords = new ArrayList<AwardScienceKeyword>();
        specialReviews = new ArrayList<AwardSpecialReview>();
        approvedEquipmentItems = new ArrayList<AwardApprovedEquipment>();
        approvedForeignTravelTrips = new ArrayList<AwardApprovedForeignTravel>();
        setAwardSponsorTerms(new ArrayList<AwardSponsorTerm>());
        paymentScheduleItems = new ArrayList<AwardPaymentSchedule>();
        awardTransferringSponsors = new ArrayList<AwardTransferringSponsor>();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        awardCustomDataList = new ArrayList<AwardCustomData>();
        awardCloseoutItems = new ArrayList<AwardCloseout>();
        
        projectPersons = new ArrayList<AwardPerson>();
        awardUnitContacts = new ArrayList<AwardUnitContact>();
        sponsorContacts = new ArrayList<AwardSponsorContact>();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        awardAmountInfos = new ArrayList<AwardAmountInfo>();
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setAward(this);
        awardAmountInfo.setAmountSequenceNumber(1);
        awardAmountInfos.add(awardAmountInfo);
        
        //customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
    }

    /**
     * This method...
     * @return
     */
    public KualiDecimal getPreAwardInstitutionalAuthorizedAmount() {
        return preAwardInstitutionalAuthorizedAmount;
    }

    /**
     * This method...
     * @param preAwardInstitutionalAuthorizedAmount
     */
    public void setPreAwardInstitutionalAuthorizedAmount(KualiDecimal preAwardInstitutionalAuthorizedAmount) {
        this.preAwardInstitutionalAuthorizedAmount = preAwardInstitutionalAuthorizedAmount;
    }

    /**
     * This method...
     * @return
     */
    public Date getPreAwardInstitutionalEffectiveDate() {
        return preAwardInstitutionalEffectiveDate;
    }

    /**
     * This method...
     * @param preAwardInstitutionalEffectiveDate
     */
    public void setPreAwardInstitutionalEffectiveDate(Date preAwardInstitutionalEffectiveDate) {
        this.preAwardInstitutionalEffectiveDate = preAwardInstitutionalEffectiveDate;
    }
    
    /**
     * This method...
     * @param awardCostShare
     */
    public void add(AwardCostShare awardCostShare) {
        awardCostShares.add(awardCostShare);
        awardCostShare.setAward(this);
    }
    
    /**
     * This method...
     * @param awardApprovedSubaward
     */
    public void add(AwardApprovedSubaward awardApprovedSubaward) {
        awardApprovedSubawards.add(awardApprovedSubaward);
        awardApprovedSubaward.setAward(this);
    }
    
    /**
     * This method...
     * @param awardComment
     */
    public void add(AwardComment awardComment) {
        awardComments.add(awardComment);
        awardComment.setAward(this);
    }
    
    /**
     * This method adds template comments to award when sync to template is being applied.
     * @param awardComment
     */
    public void addTemplateComments(List<AwardTemplateComment> awardTemplateComments) {
        AwardCommentFactory awardCommentFactory = new AwardCommentFactory();
        for(AwardTemplateComment awardTemplateComment : awardTemplateComments) {
            AwardComment testAwardComment = getCommentMap().get(awardTemplateComment.getCommentTypeCode());
            if(testAwardComment == null){
                AwardComment awardComment = awardCommentFactory.createAwardComment(awardTemplateComment.getCommentTypeCode(), 
                                                                                         awardTemplateComment.getChecklistPrintFlag());
                awardComment.setComments(awardTemplateComment.getComments());
                add(awardComment);
                commentMap.put(awardComment.getCommentType().getCommentTypeCode(), awardComment);
            }else {
                testAwardComment.setComments(awardTemplateComment.getComments());
            }
        }
    }
    
    /**
     * This method...
     * @param awardSponsorTerm
     */
    public void add(AwardSponsorTerm awardSponsorTerm) {
        awardSponsorTerms.add(awardSponsorTerm);
        awardSponsorTerm.setAward(this);
    }
    
    /**
     * This method adds template sponsor terms to award when sync to template is being applied.
     * @param awardTemplateTerms
     */
    public void addTemplateTerms (List<AwardTemplateTerm> awardTemplateTerms) {
        List<AwardSponsorTerm> tempAwardSponsorTerms = new ArrayList<AwardSponsorTerm>();
        for (AwardTemplateTerm awardTemplateTerm : awardTemplateTerms) {
            tempAwardSponsorTerms.add(new AwardSponsorTerm(awardTemplateTerm.getSponsorTermId(), awardTemplateTerm.getSponsorTerm()));
        }
        setAwardSponsorTerms(tempAwardSponsorTerms);
    }
    
    /**
     * This method adds AwardDirectFandADistribution to end of list.
     * @param awardDirectFandADistribution
     */
    public void add(AwardDirectFandADistribution awardDirectFandADistribution) {
        awardDirectFandADistributions.add(awardDirectFandADistribution);
        awardDirectFandADistribution.setAward(this);
        awardDirectFandADistribution.setBudgetPeriod(awardDirectFandADistributions.size());
    }
    
    /**
     * This method adds AwardDirectFandADistribution to the given index in the list.
     * @param awardDirectFandADistribution
     */
    public void add(int index, AwardDirectFandADistribution awardDirectFandADistribution) {
        awardDirectFandADistributions.add(index, awardDirectFandADistribution);
        awardDirectFandADistribution.setAward(this);
        awardDirectFandADistribution.setBudgetPeriod(index + 1);
        updateDirectFandADistributionBudgetPeriods(index + 1);
    }
    
    /**
     * This method updates the budget periods in the Award after insertion of new Award Direct F and A Distribution into list.
     * @param index
     */
    public void updateDirectFandADistributionBudgetPeriods(int index) {
        for(int newIndex = index; newIndex < awardDirectFandADistributions.size(); newIndex++){
            awardDirectFandADistributions.get(newIndex).setBudgetPeriod(newIndex + 1);
        }
    }
    
    
    
    /**
     * This method calculates the total value of a list of ValuableItems
     * @param valuableItems
     * @return The total value
     */
    KualiDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
        KualiDecimal returnVal = new KualiDecimal(0.00);
        for(ValuableItem item : valuableItems) {
            KualiDecimal amount = item.getAmount() != null ? item.getAmount() : new KualiDecimal(0.00);
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * Gets the awardSponsorTerms attribute. 
     * @return Returns the awardSponsorTerms.
     */
    public List<AwardSponsorTerm> getAwardSponsorTerms() {
        return awardSponsorTerms;
    }
    
    /**
     * @return
     */
    public AwardStatus getAwardStatus() {
        if(awardStatus == null && statusCode != null) {
            refreshReferenceObject("awardStatus");
        }
        return awardStatus;
    }

    /**
     * Sets the awardSponsorTerms attribute value.
     * @param awardSponsorTerms The awardSponsorTerms to set.
     */
    public void setAwardSponsorTerms(List<AwardSponsorTerm> awardSponsorTerms) {
        this.awardSponsorTerms = awardSponsorTerms;
    }
    
    /**
     * This method violates our policy of not calling a service in a getter.
     * This will only call the service once to set a sponsor when a sponsor code exists, 
     * but no sponsor was fetched
     * 
     * Seems like a persistence design issue to me. Why wouldn't Sponsor:Award be a 1:M 
     * relationship handled automagically by the persistence framework? 
     * 
     * @return
     */
    
    public Sponsor getSponsor() {
        if(sponsor == null && !StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }
    
    /**
     * @return
     */
    public List<AwardSponsorContact> getSponsorContacts() {
        return sponsorContacts;
    }
    
    /**
     * @return
     */
    public void setSponsorContacts(List<AwardSponsorContact> awardSponsorContacts) {
        this.sponsorContacts = awardSponsorContacts;
    }
    
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        this.sponsorCode = sponsor != null ? sponsor.getSponsorCode() : null;
    }
    
    public String getSponsorName() {
        Sponsor sponsor = getSponsor();
        sponsorName = sponsor != null ? sponsor.getSponsorName() : null;
        return sponsorName;
    }

    /**
     * This method adds an approved foreign travel trip
     * @param approvedForeignTravelTrip
     */
    public void add(AwardApprovedForeignTravel approvedForeignTravelTrip) {
        approvedForeignTravelTrips.add(approvedForeignTravelTrip);
        approvedForeignTravelTrip.setAward(this);
    }

    /**
     * Gets the paymentScheduleItems attribute. 
     * @return Returns the paymentScheduleItems.
     */
    public List<AwardPaymentSchedule> getPaymentScheduleItems() {
        return paymentScheduleItems;
    }

    /**
     * Sets the paymentScheduleItems attribute value.
     * @param paymentScheduleItems The paymentScheduleItems to set.
     */
    public void setPaymentScheduleItems(List<AwardPaymentSchedule> paymentScheduleItems) {
        this.paymentScheduleItems = paymentScheduleItems;
    }

    // Note: following the pattern of Sponsor, this getter indirectly calls a service.
    // Is there a better way?
    public Sponsor getPrimeSponsor() {
      if(primeSponsor == null && !StringUtils.isEmpty(getPrimeSponsorCode())) {
            this.refreshReferenceObject("primeSponsor");
        }
        return primeSponsor;
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }

    public List<AwardTransferringSponsor> getAwardTransferringSponsors() {
        return awardTransferringSponsors;
    }

    /**
     * @param awardStatus
     */
    public void setAwardStatus(AwardStatus awardStatus) {
        this.awardStatus = awardStatus;
    }
    
    public void setAwardTransferringSponsors(List<AwardTransferringSponsor> awardTransferringSponsors) {
        this.awardTransferringSponsors = awardTransferringSponsors;
    }

    /**
     * Gets the awardDirectFandADistribution attribute. 
     * @return Returns the awardDirectFandADistribution.
     */
    public List<AwardDirectFandADistribution> getAwardDirectFandADistributions() {
        return awardDirectFandADistributions;
    }

    /**
     * Sets the awardDirectFandADistribution attribute value.
     * @param awardDirectFandADistribution The awardDirectFandADistribution to set.
     */
    public void setAwardDirectFandADistributions(List<AwardDirectFandADistribution> awardDirectFandADistributions){
        for(AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions){
            awardDirectFandADistribution.setAward(this);
        }
        this.awardDirectFandADistributions = awardDirectFandADistributions;
    }

    /**
     * Gets the indirectCostIndicator attribute. 
     * @return Returns the indirectCostIndicator.
     */
    public String getIndirectCostIndicator() {
        return indirectCostIndicator;
    }

    /**
     * Sets the indirectCostIndicator attribute value.
     * @param indirectCostIndicator The indirectCostIndicator to set.
     */
    public void setIndirectCostIndicator(String indirectCostIndicator) {
        this.indirectCostIndicator = indirectCostIndicator;
    }


    /**
     * Gets the obligatedTotal attribute. 
     * @return Returns the obligatedTotal.
     */
    public KualiDecimal getObligatedTotal() {
        KualiDecimal returnValue = new KualiDecimal(0.00);
        returnValue = returnValue.add(awardAmountInfos.get(0).getObligatedTotalDirect());
        returnValue = returnValue.add(awardAmountInfos.get(0).getObligatedTotalIndirect());
        return returnValue;
    }

    /**
     * Gets the anticipatedTotal attribute. 
     * @return Returns the anticipatedTotal.
     */
    public KualiDecimal getAnticipatedTotal() {
        KualiDecimal returnValue = new KualiDecimal(0.00);
        returnValue = returnValue.add(awardAmountInfos.get(0).getAnticipatedTotalDirect());
        returnValue = returnValue.add(awardAmountInfos.get(0).getAnticipatedTotalIndirect());
        return returnValue;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentNumberForPermission()
     */
    public String getDocumentNumberForPermission(){
        return awardNumber;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentKey()
     */
    public String getDocumentKey(){
        return Permissionable.AWARD_KEY;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getRoleNames()
     */
    public List<String> getRoleNames(){        
        List<String> roles = new ArrayList<String>();
        
        for(AwardRoleConstants awardRoleConstants:AwardRoleConstants.values()){            
            roles.add(awardRoleConstants.getAwardRole());
        }
        
        return roles;        
    }

    public List<AwardAmountInfo> getAwardAmountInfos() {
        return awardAmountInfos;
    }

    public void setAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos) {
        this.awardAmountInfos = awardAmountInfos;
    }

    /**
     * Find the lead unit for the award
     * @return
     */
    public Unit getLeadUnit() {
        Unit leadUnit = null;
OUTER:  for(AwardPerson p: getProjectPersons()) {
            for(AwardPersonUnit apu: p.getUnits()) {
                if(apu.isLeadUnit()) {
                    leadUnit = apu.getUnit();
                    break OUTER;
                }
            }
        }
        this.leadUnit = leadUnit;
        return leadUnit;
    }
    
    public boolean isNew(){
        return awardId == null;
    }

    /**
     * Gets the awardReportTermItems attribute. 
     * @return Returns the awardReportTermItems.
     */
    public List<AwardReportTerm> getAwardReportTermItems() {
        return awardReportTermItems;
    }

    /**
     * Sets the awardReportTermItems attribute value.
     * @param awardReportTermItems The awardReportTermItems to set.
     */
    public void setAwardReportTermItems(List<AwardReportTerm> awardReportTermItems) {
        this.awardReportTermItems = awardReportTermItems;
    }

    /**
     * Find principle investigator, if any
     * @return Principle investigator. May return null
     */
    public AwardPerson getPrincipalInvestigator() {
        AwardPerson principleInvestigator = null;
        for(AwardPerson person: projectPersons) {
            if(person.isPrincipalInvestigator()) {
                principleInvestigator = person;
                break;
            }
        }
        return principleInvestigator;
    }
    
    /**
     * This method find PI name
     * @return PI name; may return null
     */
    public String getPrincipalInvestigatorName() {
        AwardPerson pi = getPrincipalInvestigator();
        principalInvestigatorName = pi != null ? pi.getFullName() : null;
        return principalInvestigatorName;
    }
    
    /**
     * @param principalInvestigatorName
     */
    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }
    
    /**
     * This method returns the status description
     * @return
     */
    public String getStatusDescription() {
        AwardStatus status = getAwardStatus();
        statusDescription = status != null ? status.getDescription() : null;
        return statusDescription;
    }

    /**
     * Gets the awardCustomDataList attribute. 
     * @return Returns the awardCustomDataList.
     */
    public List<AwardCustomData> getAwardCustomDataList() {
        return awardCustomDataList;
    }

    /**
     * Sets the awardCustomDataList attribute value.
     * @param awardCustomDataList The awardCustomDataList to set.
     */
    public void setAwardCustomDataList(List<AwardCustomData> awardCustomDataList) {
        this.awardCustomDataList = awardCustomDataList;
    }
    
    /**
     * Gets the awardCloseoutItems attribute. 
     * @return Returns the awardCloseoutItems.
     */
    public List<AwardCloseout> getAwardCloseoutItems() {
        return awardCloseoutItems;
    }

    /**
     * Sets the awardCloseoutItems attribute value.
     * @param awardCloseoutItems The awardCloseoutItems to set.
     */
    public void setAwardCloseoutItems(List<AwardCloseout> awardCloseoutItems) {
        this.awardCloseoutItems = awardCloseoutItems;
    }

    
    /**
     * Sets the templateCode attribute value.
     * @param templateCode The templateCode to set.
     */
    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * Gets the primeSponsorCode attribute. 
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     * Sets the primeSponsorCode attribute value.
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    /**
     * Gets the nonCompetingContPrpslDueCode attribute. 
     * @return Returns the nonCompetingContPrpslDueCode.
     */
    public String getNonCompetingContPrpslDueCode() {
        return nonCompetingContPrpslDueCode;
    }

    /**
     * Sets the nonCompetingContPrpslDueCode attribute value.
     * @param nonCompetingContPrpslDueCode The nonCompetingContPrpslDueCode to set.
     */
    public void setNonCompetingContPrpslDueCode(String nonCompetingContPrpslDueCode) {
        this.nonCompetingContPrpslDueCode = nonCompetingContPrpslDueCode;
    }

    /**
     * Gets the competingRenewalPrpslDueCode attribute. 
     * @return Returns the competingRenewalPrpslDueCode.
     */
    public String getCompetingRenewalPrpslDueCode() {
        return competingRenewalPrpslDueCode;
    }

    /**
     * Sets the competingRenewalPrpslDueCode attribute value.
     * @param competingRenewalPrpslDueCode The competingRenewalPrpslDueCode to set.
     */
    public void setCompetingRenewalPrpslDueCode(String competingRenewalPrpslDueCode) {
        this.competingRenewalPrpslDueCode = competingRenewalPrpslDueCode;
    }

    /**
     * Gets the basisOfPaymentCode attribute. 
     * @return Returns the basisOfPaymentCode.
     */
    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    /**
     * Sets the basisOfPaymentCode attribute value.
     * @param basisOfPaymentCode The basisOfPaymentCode to set.
     */
    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    /**
     * Gets the methodOfPaymentCode attribute. 
     * @return Returns the methodOfPaymentCode.
     */
    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    /**
     * Sets the methodOfPaymentCode attribute value.
     * @param methodOfPaymentCode The methodOfPaymentCode to set.
     */
    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    /**
     * Gets the paymentInvoiceFreqCode attribute. 
     * @return Returns the paymentInvoiceFreqCode.
     */
    public String getPaymentInvoiceFreqCode() {
        return paymentInvoiceFreqCode;
    }

    /**
     * Sets the paymentInvoiceFreqCode attribute value.
     * @param paymentInvoiceFreqCode The paymentInvoiceFreqCode to set.
     */
    public void setPaymentInvoiceFreqCode(String paymentInvoiceFreqCode) {
        this.paymentInvoiceFreqCode = paymentInvoiceFreqCode;
    }

    /**
     * Gets the invoiceNumberOfCopies attribute. 
     * @return Returns the invoiceNumberOfCopies.
     */
    public Integer getInvoiceNumberOfCopies() {
        return invoiceNumberOfCopies;
    }

    /**
     * Sets the invoiceNumberOfCopies attribute value.
     * @param invoiceNumberOfCopies The invoiceNumberOfCopies to set.
     */
    public void setInvoiceNumberOfCopies(Integer invoiceNumberOfCopies) {
        this.invoiceNumberOfCopies = invoiceNumberOfCopies;
    }

    /**
     * Gets the finalInvoiceDue attribute. 
     * @return Returns the finalInvoiceDue.
     */
    public Integer getFinalInvoiceDue() {
        return finalInvoiceDue;
    }

    /**
     * Sets the finalInvoiceDue attribute value.
     * @param finalInvoiceDue The finalInvoiceDue to set.
     */
    public void setFinalInvoiceDue(Integer finalInvoiceDue) {
        this.finalInvoiceDue = finalInvoiceDue;
    }

    /**
     * Gets the awardTemplate attribute. 
     * @return Returns the awardTemplate.
     */
    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }

    /**
     * Sets the awardTemplate attribute value.
     * @param awardTemplate The awardTemplate to set.
     */
    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }

    /**
     * Gets the nonCompetingContPrpslDue attribute. 
     * @return Returns the nonCompetingContPrpslDue.
     */
    public Frequency getNonCompetingContPrpslDue() {
        return nonCompetingContPrpslDue;
    }

    /**
     * Sets the nonCompetingContPrpslDue attribute value.
     * @param nonCompetingContPrpslDue The nonCompetingContPrpslDue to set.
     */
    public void setNonCompetingContPrpslDue(Frequency nonCompetingContPrpslDue) {
        this.nonCompetingContPrpslDue = nonCompetingContPrpslDue;
    }

    /**
     * Gets the competingRenewalPrpslDue attribute. 
     * @return Returns the competingRenewalPrpslDue.
     */
    public Frequency getCompetingRenewalPrpslDue() {
        return competingRenewalPrpslDue;
    }

    /**
     * Sets the competingRenewalPrpslDue attribute value.
     * @param competingRenewalPrpslDue The competingRenewalPrpslDue to set.
     */
    public void setCompetingRenewalPrpslDue(Frequency competingRenewalPrpslDue) {
        this.competingRenewalPrpslDue = competingRenewalPrpslDue;
    }

    /**
     * Gets the paymentInvoiceFrequency attribute. 
     * @return Returns the paymentInvoiceFrequency.
     */
    public Frequency getPaymentInvoiceFrequency() {
        return paymentInvoiceFrequency;
    }

    /**
     * Sets the paymentInvoiceFrequency attribute value.
     * @param paymentInvoiceFrequency The paymentInvoiceFrequency to set.
     */
    public void setPaymentInvoiceFrequency(Frequency paymentInvoiceFrequency) {
        this.paymentInvoiceFrequency = paymentInvoiceFrequency;
    }

    /**
     * Gets the awardBasisOfPayment attribute. 
     * @return Returns the awardBasisOfPayment.
     */
    public AwardBasisOfPayment getAwardBasisOfPayment() {
        return awardBasisOfPayment;
    }

    /**
     * Sets the awardBasisOfPayment attribute value.
     * @param awardBasisOfPayment The awardBasisOfPayment to set.
     */
    public void setAwardBasisOfPayment(AwardBasisOfPayment awardBasisOfPayment) {
        this.awardBasisOfPayment = awardBasisOfPayment;
    }

    /**
     * Gets the awardMethodOfPayment attribute. 
     * @return Returns the awardMethodOfPayment.
     */
    public AwardMethodOfPayment getAwardMethodOfPayment() {
        return awardMethodOfPayment;
    }

    /**
     * Sets the awardMethodOfPayment attribute value.
     * @param awardMethodOfPayment The awardMethodOfPayment to set.
     */
    public void setAwardMethodOfPayment(AwardMethodOfPayment awardMethodOfPayment) {
        this.awardMethodOfPayment = awardMethodOfPayment;
    }
    /**
     * @see org.kuali.kra.SequenceOwner#getOwnerSequenceNumber()
     */
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    /**
     * @see org.kuali.kra.SequenceOwner#incrementSequenceNumber()
     */
    public void incrementSequenceNumber() {
       this.sequenceNumber++; 
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return this;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newOwner) {
       // no-op
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardId = null;
    }

}