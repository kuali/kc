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
package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.document.KeywordsManager;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.ProposalIpReviewJoin;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalUnitContact;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.service.Sponsorable;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.util.ObjectUtils;

public class InstitutionalProposal extends KraPersistableBusinessObjectBase implements
        KeywordsManager<InstitutionalProposalScienceKeyword>, SequenceOwner<InstitutionalProposal>, Sponsorable, Negotiable {

    public static final String PROPOSAL_ID_PROPERTY_STRING = "proposalId";
    public static final String PROPOSAL_NUMBER_PROPERTY_STRING = "proposalNumber";
    public static final String PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING = "proposalSequenceStatus";
    public static final String PROPOSAL_NUMBER_TEST_DEFAULT_STRING = "1234";
    private static final long serialVersionUID = 1L;
    private static final Integer PROPOSAL_PENDING_STATUS_CODE = 1;
    private static final Integer PROPOSAL_FUNDED_STATUS_CODE = 2;

    private InstitutionalProposalDocument institutionalProposalDocument;
    private Long proposalId;
    private String proposalNumber;
    private String sponsorProposalNumber;
    private Integer sequenceNumber;
    private Integer proposalTypeCode;
    private String currentAccountNumber;
    private String title;
    private String sponsorCode;
    private Integer rolodexId;
    private String noticeOfOpportunityCode;
    private Integer gradStudHeadcount;
    private KualiDecimal gradStudPersonMonths;
    private boolean typeOfAccount;
    private String activityTypeCode;
    private Date requestedStartDateInitial;
    private Date requestedStartDateTotal;
    private Date requestedEndDateInitial;
    private Date requestedEndDateTotal;
    private String fiscalMonth;
    private String fiscalYear;
    private KualiDecimal totalDirectCostInitial;
    private KualiDecimal totalDirectCostTotal;
    private KualiDecimal totalIndirectCostInitial;
    private KualiDecimal totalIndirectCostTotal;
    private String numberOfCopies;
    private Date deadlineDate;
    private Date createTimeStamp;
    private String deadlineType;
    private String mailBy;
    private String mailType;
    private String mailAccountNumber;
    private String mailDescription;
    private Boolean subcontractFlag;
    private String costSharingIndicator;
    private String idcRateIndicator;
    private String specialReviewIndicator;
    private Integer statusCode;
    private String unitNumber;
    private String scienceCodeIndicator;
    private String nsfCode;
    private NsfCode nsfCodeBo;
    private String primeSponsorCode;
    private String initialContractAdmin;
    private String ipReviewActivityIndicator;
    private String currentAwardNumber;
    private String cfdaNumber;
    private String opportunity;
    private Integer awardTypeCode;
    private String newDescription;
    private String proposalSequenceStatus;
    private boolean showReturnLink;
    private String instProposalNumber;

    private NoticeOfOpportunity noticeOfOpportunity;
    private ProposalType proposalType;
    private Rolodex rolodex;
    private Sponsor sponsor;
    private Sponsor primeSponsor;
    private String sponsorName;
    private ActivityType activityType;
    private AwardType awardType;
    private ProposalStatus proposalStatus;
    private Unit leadUnit;
    private KcPerson ospAdministrator;
    private InstitutionalProposalScienceKeyword proposalScienceKeyword;
    private InstitutionalProposalCostShare proposalCostSharing;
    // private AwardFundingProposals awardFundingProposals;
    private InstitutionalProposalPersonCreditSplit proposalPerCreditSplit;
    private ProposalUnitCreditSplit proposalUnitCreditSplit;
    private List<InstitutionalProposalComment> proposalComments;
    private IntellectualPropertyReview intellectualPropertyReview;

    private List<ProposalIpReviewJoin> proposalIpReviewJoins;
    private List<InstitutionalProposalPerson> projectPersons;
    private List<InstitutionalProposalUnitContact> institutionalProposalUnitContacts;
    private List<InstitutionalProposalCustomData> institutionalProposalCustomDataList;
    private List<InstitutionalProposalNotepad> institutionalProposalNotepads;
    private List<InstitutionalProposalSpecialReview> specialReviews;
    private List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords;
    private List<InstitutionalProposalCostShare> institutionalProposalCostShares;
    private List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs;
    @SkipVersioning
    private List<AwardFundingProposal> awardFundingProposals;
    private Map<String, InstitutionalProposalComment> commentMap;
    private boolean sponsorNihMultiplePi;

    private transient Unit lookupUnit;
    private transient String lookupUnitName;
    private transient String lookupUnitNumber;
    private transient String lookupPersonNumber;

    public InstitutionalProposal() {
        super();
        initializeInstitutionalProposalWithDefaultValues();
        initializeCollections();
        calculateFiscalMonthAndYearFields();
    }

    /**
     * 
     * This method sets the default values for initial persistence as part of skeleton. As various panels are developed;
     * corresponding field initializations should be removed from this method.
     */
    private void initializeInstitutionalProposalWithDefaultValues() {
        setSequenceNumber(1);
        // setSponsorCode("005852");
        setCostSharingIndicator("0");
        setIdcRateIndicator("0");
        setSpecialReviewIndicator("0");
        setScienceCodeIndicator("0");
        ipReviewActivityIndicator = "A";
        Calendar cl = Calendar.getInstance();
        setCreateTimeStamp(new Date(cl.getTime().getTime()));
        // setProposalNumber("1");
        setTotalDirectCostInitial(new KualiDecimal(0));
        setTotalDirectCostTotal(new KualiDecimal(0));
        setTotalIndirectCostInitial(new KualiDecimal(0));
        setTotalIndirectCostTotal(new KualiDecimal(0));
        newDescription = getDefaultNewDescription();
        setProposalSequenceStatus(VersionStatus.PENDING.toString());
        setStatusCode(1);// default value for all IP's
        projectPersons = new ArrayList<InstitutionalProposalPerson>();
        showReturnLink = true; // we usually show proposal in lookup
    }

    protected void initializeCollections() {
        institutionalProposalCustomDataList = new ArrayList<InstitutionalProposalCustomData>();
        institutionalProposalNotepads = new ArrayList<InstitutionalProposalNotepad>();
        specialReviews = new ArrayList<InstitutionalProposalSpecialReview>();
        institutionalProposalScienceKeywords = new ArrayList<InstitutionalProposalScienceKeyword>();
        institutionalProposalCostShares = new ArrayList<InstitutionalProposalCostShare>();
        institutionalProposalUnrecoveredFandAs = new ArrayList<InstitutionalProposalUnrecoveredFandA>();
        proposalIpReviewJoins = new ArrayList<ProposalIpReviewJoin>();
        proposalIpReviewJoins.add(new ProposalIpReviewJoin());
        awardFundingProposals = new ArrayList<AwardFundingProposal>();
        institutionalProposalUnitContacts = new ArrayList<InstitutionalProposalUnitContact>();
        proposalComments = new ArrayList<InstitutionalProposalComment>();
    }

    public void setDefaultInitialContractAdmin() {
        if (!StringUtils.isBlank(getUnitNumber())) {
            List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(getUnitNumber());
            for (UnitAdministrator unitAdministrator : unitAdministrators) {
                if (UnitAdministratorType.OSP_ADMINISTRATOR_TYPE_CODE.equals(unitAdministrator.getUnitAdministratorTypeCode())) {
                    this.setInitialContractAdmin(unitAdministrator.getPersonId());
                }
            }
        }
    }

    public void deactivateFundingProposals() {
        for (AwardFundingProposal fundingProposal : this.getAwardFundingProposals()) {
            fundingProposal.setActive(false);
        }
    }

    public void activateFundingProposals() {
        for (AwardFundingProposal fundingProposal : this.getAwardFundingProposals()) {
            fundingProposal.setActive(true);
        }
    }

    public boolean isActiveVersion() {
        return this.getProposalSequenceStatus().equals(VersionStatus.ACTIVE.toString());
    }

    /**
     * Is this Proposal funded by the given Award number and version?
     * @param awardNumber String
     * @param awardSequence Integer
     * @return boolean
     */
    public boolean isFundedByAward(String awardNumber, Integer awardSequence) {
        for (AwardFundingProposal awardFundingProposal : this.getAwardFundingProposals()) {
            if (awardFundingProposal.getAward().getAwardNumber().equals(awardNumber)
                    && awardFundingProposal.getAward().getSequenceNumber().equals(awardSequence)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method calculates fiscal Month and fiscal Year fields. It also adds leading 0 to Month if needed.
     */
    private void calculateFiscalMonthAndYearFields() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MONTH, 6);
        String monthString = Integer.toString(cl.get(Calendar.MONTH) + 1);
        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        }
        setFiscalMonth(monthString);
        setFiscalYear(Integer.toString(cl.get(Calendar.YEAR)));

    }

    /**
     * This method returns a business object service
     * @return
     */
    protected BusinessObjectService getKraBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
    }
    
    /**
     * Gets the institutionalProposalDocument attribute. 
     * @return Returns the institutionalProposalDocument.
     */
    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        if (institutionalProposalDocument == null) {
            this.refreshReferenceObject("institutionalProposalDocument");
        }
        return institutionalProposalDocument;
    }

    /**
     * Sets the institutionalProposalDocument attribute value.
     * @param institutionalProposalDocument The institutionalProposalDocument to set.
     */
    public void setInstitutionalProposalDocument(InstitutionalProposalDocument institutionalProposalDocument) {
        this.institutionalProposalDocument = institutionalProposalDocument;
    }

    /**
     * Add an AwardFundingProposal
     * 
     * The Award "owns" the relationship, so this method should not be called directly. Instead, this method will be called when an
     * InstitutionalProposal is linked to an Award from Award maintenance.
     * 
     * @param afp
     */
    public void add(AwardFundingProposal afp) {
        awardFundingProposals.add(afp);
    }

    /**
     * This method...
     * @param institutionaProposalNotepad
     */
    public void add(InstitutionalProposalNotepad institutionalProposalNotepad) {
        institutionalProposalNotepad.setEntryNumber(institutionalProposalNotepads.size() + 1);
        institutionalProposalNotepad.setProposalNumber(this.getProposalNumber());
        institutionalProposalNotepads.add(institutionalProposalNotepad);
        institutionalProposalNotepad.setInstitutionalProposal(this);
    }

    /**
     * This method...
     * @param institutionalProposalCostShare
     */
    public void add(InstitutionalProposalCostShare institutionalProposalCostShare) {
        institutionalProposalCostShare.setInstitutionalProposal(this);
        institutionalProposalCostShares.add(institutionalProposalCostShare);
    }

    /**
     * This method...
     * @param institutionalProposalUnrecoveredFandA
     */
    public void add(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA) {
        institutionalProposalUnrecoveredFandA.setInstitutionalProposal(this);
        institutionalProposalUnrecoveredFandAs.add(institutionalProposalUnrecoveredFandA);
    }

    /**
     * This method 
     * @return
     */
    public KualiDecimal getTotalInitialCost() {
        KualiDecimal returnValue = new KualiDecimal(0);
        returnValue = returnValue.add(totalDirectCostInitial);
        returnValue = returnValue.add(totalIndirectCostInitial);
        return returnValue;
    }

    /**
     * This method
     * 
     * @return
     */
    public KualiDecimal getTotalCost() {
        KualiDecimal returnValue = new KualiDecimal(0);
        returnValue = returnValue.add(totalDirectCostTotal);
        returnValue = returnValue.add(totalIndirectCostTotal);
        return returnValue;
    }

    /**
     * This method calculates the total value of a list of ValuableItems
     * 
     * @param valuableItems
     * @return The total value
     */
    KualiDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
        KualiDecimal returnVal = new KualiDecimal(0.00);
        for (ValuableItem item : valuableItems) {
            KualiDecimal amount = item.getAmount() != null ? item.getAmount() : new KualiDecimal(0.00);
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    /**
     * This method calls getTotalAmount to calculate the total of all Commitment Amounts.
     * 
     * @return
     */
    public KualiDecimal getTotalCostShareAmount() {
        return getTotalAmount(institutionalProposalCostShares);
    }

    /**
     * This method calls getTotalAmount to calculate the total of all Unrecovered FandAs.
     * 
     * @return
     */
    public KualiDecimal getTotalUnrecoveredFandAAmount() {
        return getTotalAmount(institutionalProposalUnrecoveredFandAs);
    }

    /**
     * Gets the specialReviews attribute.
     * 
     * @return Returns the specialReviews.
     */
    public List<InstitutionalProposalSpecialReview> getSpecialReviews() {
        return specialReviews;
    }

    /**
     * Sets the specialReviews attribute value.
     * 
     * @param specialReviews The specialReviews to set.
     */
    public void setSpecialReviews(List<InstitutionalProposalSpecialReview> specialReviews) {
        this.specialReviews = specialReviews;
    }

    /**
     * Gets the institutionalProposalCustomDataList attribute.
     * 
     * @return Returns the institutionalProposalCustomDataList.
     */
    public List<InstitutionalProposalCustomData> getInstitutionalProposalCustomDataList() {
        return institutionalProposalCustomDataList;
    }


    /**
     * Gets the institutionalProposalNotepads attribute. 
     * @return Returns the institutionalProposalNotepads.
     */
    public List<InstitutionalProposalNotepad> getInstitutionalProposalNotepads() {
        return institutionalProposalNotepads;
    }

    /**
     * Sets the institutionalProposalNotepads attribute value.
     * @param institutionalProposalNotepads The institutionalProposalNotepads to set.
     */
    public void setInstitutionalProposalNotepads(List<InstitutionalProposalNotepad> institutionalProposalNotepads) {
        this.institutionalProposalNotepads = institutionalProposalNotepads;
    }


    /**
     * Sets the institutionalProposalCustomDataList attribute value.
     * @param institutionalProposalCustomDataList The institutionalProposalCustomDataList to set.
     */
    public void setInstitutionalProposalCustomDataList(List<InstitutionalProposalCustomData> institutionalProposalCustomDataList) {
        this.institutionalProposalCustomDataList = institutionalProposalCustomDataList;
    }


    /**
     * Gets the institutionalProposalScienceKeywords attribute. 
     * @return Returns the institutionalProposalScienceKeywords.
     */
    public List<InstitutionalProposalScienceKeyword> getInstitutionalProposalScienceKeywords() {
        return institutionalProposalScienceKeywords;
    }

    /**
     * Sets the institutionalProposalScienceKeywords attribute value.
     * @param institutionalProposalScienceKeywords The institutionalProposalScienceKeywords to set.
     */
    public void setInstitutionalProposalScienceKeywords(
            List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }

    /**
     * This method adds a Project Person to the institutionalProposal
     * @param projectPerson
     */
    public void add(InstitutionalProposalPerson projectPerson) {
        projectPersons.add(projectPerson);
        projectPerson.setInstitutionalProposal(this);
    }

    /**
     * Add an Institutional Proposal Unit or Central Administration contact
     * @param awardUnitContact
     */
    public void add(InstitutionalProposalUnitContact institutionalProposalUnitContact) {
        institutionalProposalUnitContacts.add(institutionalProposalUnitContact);
        institutionalProposalUnitContact.setInstitutionalProposal(this);
    }

    /**
     * @return
     */
    public KcPerson getOspAdministrator() {
        for (InstitutionalProposalUnitContact contact : getInstitutionalProposalUnitContacts()) {
            if (contact.isOspAdministrator()) {
                ospAdministrator = contact.getPerson();
                break;
            }
        }
        return ospAdministrator;
    }

    /**
     * @param institutionalProposalUnitContacts
     */
    public void setInstitutionalProposalUnitContacts(List<InstitutionalProposalUnitContact> institutionalProposalUnitContacts) {
        this.institutionalProposalUnitContacts = institutionalProposalUnitContacts;
    }

    /**
     * @return
     */
    public List<InstitutionalProposalUnitContact> getInstitutionalProposalUnitContacts() {
        return institutionalProposalUnitContacts;
    }

    /**
     * @return
     */
    public int getInstitutionalProposalContactsCount() {
        return institutionalProposalUnitContacts.size();
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(Integer proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    public void setCurrentAccountNumber(String currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    public Integer getGradStudHeadcount() {
        return gradStudHeadcount;
    }

    public void setGradStudHeadcount(Integer gradStudHeadcount) {
        this.gradStudHeadcount = gradStudHeadcount;
    }

    public KualiDecimal getGradStudPersonMonths() {
        return gradStudPersonMonths;
    }

    public void setGradStudPersonMonths(KualiDecimal gradStudPersonMonths) {
        this.gradStudPersonMonths = gradStudPersonMonths;
    }

    public boolean getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(boolean typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    /**
     * Sets the leadUnit attribute value.
     * @param leadUnit The leadUnit to set.
     */
    public void setLeadUnit(Unit leadUnit) {
        this.leadUnit = leadUnit;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public Date getRequestedStartDateTotal() {
        return requestedStartDateTotal;
    }

    public void setRequestedStartDateTotal(Date requestedStartDateTotal) {
        this.requestedStartDateTotal = requestedStartDateTotal;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public Date getRequestedEndDateTotal() {
        return requestedEndDateTotal;
    }

    public void setRequestedEndDateTotal(Date requestedEndDateTotal) {
        this.requestedEndDateTotal = requestedEndDateTotal;
    }

    public KualiDecimal getTotalDirectCostInitial() {
        return totalDirectCostInitial;
    }

    public void setTotalDirectCostInitial(KualiDecimal totalDirectCostInitial) {
        if (totalDirectCostInitial == null) {
            this.totalDirectCostInitial = KualiDecimal.ZERO;
        }
        else {
            this.totalDirectCostInitial = totalDirectCostInitial;
        }
    }

    public KualiDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public void setTotalDirectCostTotal(KualiDecimal totalDirectCostTotal) {
        if (totalDirectCostTotal == null) {
            this.totalDirectCostTotal = KualiDecimal.ZERO;
        }
        else {
            this.totalDirectCostTotal = totalDirectCostTotal;
        }
    }

    public KualiDecimal getTotalIndirectCostInitial() {
        return totalIndirectCostInitial;
    }

    public void setTotalIndirectCostInitial(KualiDecimal totalIndirectCostInitial) {
        if (totalIndirectCostInitial == null) {
            this.totalIndirectCostInitial = KualiDecimal.ZERO;
        }
        else {
            this.totalIndirectCostInitial = totalIndirectCostInitial;
        }
    }

    public KualiDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public void setTotalIndirectCostTotal(KualiDecimal totalIndirectCostTotal) {
        if (totalIndirectCostTotal == null) {
            this.totalIndirectCostTotal = KualiDecimal.ZERO;
        }
        else {
            this.totalIndirectCostTotal = totalIndirectCostTotal;
        }
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineType() {
        return deadlineType;
    }

    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    public String getMailBy() {
        return mailBy;
    }

    public void setMailBy(String mailBy) {
        this.mailBy = mailBy;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public Unit getLeadUnit() {
        return leadUnit;
    }

    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }


    /**
     * Gets the mailDescription attribute. 
     * @return Returns the mailDescription.
     */
    public String getMailDescription() {
        return mailDescription;
    }

    /**
     * Sets the mailDescription attribute value.
     * @param mailDescription The mailDescription to set.
     */
    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public boolean getSubcontractFlag() {
        return subcontractFlag;
    }

    public void setSubcontractFlag(boolean subcontractFlag) {
        this.subcontractFlag = subcontractFlag;
    }

    public String getCostSharingIndicator() {
        return costSharingIndicator;
    }

    public void setCostSharingIndicator(String costSharingIndicator) {
        this.costSharingIndicator = costSharingIndicator;
    }

    public String getIdcRateIndicator() {
        return idcRateIndicator;
    }

    public void setIdcRateIndicator(String idcRateIndicator) {
        this.idcRateIndicator = idcRateIndicator;
    }

    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getScienceCodeIndicator() {
        return scienceCodeIndicator;
    }

    public void setScienceCodeIndicator(String scienceCodeIndicator) {
        this.scienceCodeIndicator = scienceCodeIndicator;
    }

    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    public String getInitialContractAdmin() {
        return initialContractAdmin;
    }

    public void setInitialContractAdmin(String initialContractAdmin) {
        this.initialContractAdmin = initialContractAdmin;
    }

    public String getIpReviewActivityIndicator() {
        return ipReviewActivityIndicator;
    }

    public void setIpReviewActivityIndicator(String ipReviewActivityIndicator) {
        this.ipReviewActivityIndicator = ipReviewActivityIndicator;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public Integer getAwardTypeCode() {
        return awardTypeCode;
    }

    public void setAwardTypeCode(Integer awardTypeCode) {
        this.awardTypeCode = awardTypeCode;
    }

    public ProposalStatus getProposalStatus() {
        if (proposalStatus == null && statusCode != null) {
            this.refreshReferenceObject("proposalStatus");
        }
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public NoticeOfOpportunity getNoticeOfOpportunity() {
        return noticeOfOpportunity;
    }

    public void setNoticeOfOpportunity(NoticeOfOpportunity noticeOfOpportunity) {
        this.noticeOfOpportunity = noticeOfOpportunity;
    }

    public ProposalType getProposalType() {
        if (proposalType == null && proposalTypeCode != null) {
            this.refreshReferenceObject("proposalType");
        }
        return proposalType;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    /**
     * This method violates our policy of not calling a service in a getter. This will only call the service once to set a sponsor
     * when a sponsor code exists, but no sponsor was fetched
     * 
     * Seems like a persistence design issue to me. Why wouldn't Sponsor:Award be a 1:M relationship handled automagically by the
     * persistence framework?
     * 
     * @return
     */
    public Sponsor getSponsor() {
        if (outOfSync(sponsorCode, sponsor)) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        this.sponsorCode = sponsor != null ? sponsor.getSponsorCode() : null;
    }

    // Note: following the pattern of Sponsor, this getter indirectly calls a service.
    // Is there a better way?
    public Sponsor getPrimeSponsor() {
        if (outOfSync(primeSponsorCode, primeSponsor)) {
            this.refreshReferenceObject("primeSponsor");
        }
        return primeSponsor;
    }

    /**
     * checks if a sponsor code needs refreshing.
     * @param code the code
     * @param spon the sponsor to refresh
     * @return true if needs refreshing
     */
    private static boolean outOfSync(String code, Sponsor spon) {
        return spon == null && !StringUtils.isEmpty(code) || (spon != null && !StringUtils.equals(spon.getSponsorCode(), code))
                && !StringUtils.isEmpty(code);
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
        this.primeSponsorCode = primeSponsor != null ? primeSponsor.getSponsorCode() : null;
    }

    public InstitutionalProposalPerson getPrincipalInvestigator() {
        for (InstitutionalProposalPerson proposalPerson : this.getProjectPersons()) {
            if (proposalPerson.isPrincipalInvestigator()) {
                return proposalPerson;
            }
        }
        return null;
    }

    public void setPrincipalInvestigator(InstitutionalProposalPerson proposalPerson) {
        proposalPerson.setRoleCode(ContactRole.PI_CODE);
        this.getProjectPersons().add(proposalPerson);
    }

    public String getSponsorName() {
        Sponsor tempSponsor = getSponsor();
        sponsorName = tempSponsor != null ? tempSponsor.getSponsorName() : null;
        return sponsorName;
    }

    public ActivityType getActivityType() {
        if (activityType == null && activityTypeCode != null) {
            this.refreshReferenceObject("activityType");
        }
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public AwardType getAwardType() {
        return awardType;
    }

    /**
     * @return Returns the awards.
     */
    public List<AwardFundingProposal> getAwardFundingProposals() {
        return awardFundingProposals;
    }

    public AwardFundingProposal getAwardFundingProposal(int index) {
        return getAwardFundingProposals().get(index);
    }

    public boolean getAwardFundingProposalsExist() {
        return getAwardFundingProposals().size() > 0;
    }

    /**
     * Get the list of only Active AwardFundingProposals.
     * @return List<AwardFundingProposal> the list.
     */
    public List<AwardFundingProposal> getActiveAwardFundingProposals() {
        List<AwardFundingProposal> activeAfps = new ArrayList<AwardFundingProposal>();
        for (AwardFundingProposal awardFundingProposal : this.getAwardFundingProposals()) {
            if (awardFundingProposal.isActive()) {
                activeAfps.add(awardFundingProposal);
            }
        }
        return activeAfps;
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }

    /**
     * Gets the fiscalMonth attribute. 
     * @return Returns the fiscalMonth.
     */
    public String getFiscalMonth() {
        return fiscalMonth;
    }

    /**
     * Sets the fiscalMonth attribute value.
     * @param fiscalMonth The fiscalMonth to set.
     */
    public void setFiscalMonth(String fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    /**
     * Gets the fiscalYear attribute. 
     * @return Returns the fiscalYear.
     */
    public String getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the fiscalYear attribute value.
     * @param fiscalYear The fiscalYear to set.
     */
    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * This method finds the lead unit number, if any
     * @return
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * Sets the unitNumber attribute value.
     * @param unitNumber The unitNumber to set.
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * @return
     */
    public String getLeadUnitNumber() {
        return getUnitNumber();
    }

    /**
     * This method...
     * @param unitNumber
     */
    public void setLeadUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * @param awards The awards to set.
     */
    public void setAwardFundingProposals(List<AwardFundingProposal> awardFundingProposals) {
        this.awardFundingProposals = awardFundingProposals;
    }

    public InstitutionalProposalScienceKeyword getProposalScienceKeyword() {
        return proposalScienceKeyword;
    }

    public void setProposalScienceKeyword(InstitutionalProposalScienceKeyword proposalScienceKeyword) {
        this.proposalScienceKeyword = proposalScienceKeyword;
    }

    public InstitutionalProposalCostShare getProposalCostSharing() {
        return proposalCostSharing;
    }

    public void setProposalCostSharing(InstitutionalProposalCostShare proposalCostSharing) {
        this.proposalCostSharing = proposalCostSharing;
    }


    /*
     * public AwardFundingProposals getAwardFundingProposals() { return awardFundingProposals; }
     * 
     * public void setAwardFundingProposals(AwardFundingProposals awardFundingProposals) { this.awardFundingProposals =
     * awardFundingProposals; }
     */

    /**
     * Gets the projectPersons attribute. 
     * @return Returns the projectPersons.
     */
    public List<InstitutionalProposalPerson> getProjectPersons() {
        return projectPersons;
    }

    /**
     * Sets the projectPersons attribute value.
     * @param projectPersons The projectPersons to set.
     */
    public void setProjectPersons(List<InstitutionalProposalPerson> projectPersons) {
        this.projectPersons = projectPersons;
    }

    public InstitutionalProposalPersonCreditSplit getProposalPerCreditSplit() {
        return proposalPerCreditSplit;
    }

    public void setProposalPerCreditSplit(InstitutionalProposalPersonCreditSplit proposalPerCreditSplit) {
        this.proposalPerCreditSplit = proposalPerCreditSplit;
    }

    public ProposalUnitCreditSplit getProposalUnitCreditSplit() {
        return proposalUnitCreditSplit;
    }

    public void setProposalUnitCreditSplit(ProposalUnitCreditSplit proposalUnitCreditSplit) {
        this.proposalUnitCreditSplit = proposalUnitCreditSplit;
    }

    public List<InstitutionalProposalComment> getProposalComments() {
        return proposalComments;
    }

    public void setProposalComments(List<InstitutionalProposalComment> proposalComments) {
        this.proposalComments = proposalComments;
    }

    public IntellectualPropertyReview getIntellectualPropertyReview() {
        return intellectualPropertyReview;
    }

    public void setIntellectualPropertyReview(IntellectualPropertyReview intellectualPropertyReview) {
        this.intellectualPropertyReview = intellectualPropertyReview;
    }

    public List<ProposalIpReviewJoin> getProposalIpReviewJoins() {
        return proposalIpReviewJoins;
    }

    public void setProposalIpReviewJoins(List<ProposalIpReviewJoin> proposalIpReviewJoins) {
        this.proposalIpReviewJoins = proposalIpReviewJoins;
    }

    public ProposalIpReviewJoin getProposalIpReviewJoin() {
        if (ObjectUtils.isNotNull(this.proposalIpReviewJoins != null)) {
            return this.proposalIpReviewJoins.get(0);
        }
        return null;
    }

    public void setProposalIpReviewJoin(ProposalIpReviewJoin proposalIpReviewJoin) {
        this.proposalIpReviewJoins.add(0, proposalIpReviewJoin);
    }

    /**
     * Gets the institutionalProposalCostShares attribute. 
     * @return Returns the institutionalProposalCostShares.
     */
    public List<InstitutionalProposalCostShare> getInstitutionalProposalCostShares() {
        return institutionalProposalCostShares;
    }

    /**
     * Sets the institutionalProposalCostShares attribute value.
     * @param institutionalProposalCostShares The institutionalProposalCostShares to set.
     */
    public void setInstitutionalProposalCostShares(List<InstitutionalProposalCostShare> institutionalProposalCostShares) {
        this.institutionalProposalCostShares = institutionalProposalCostShares;
    }

    /**
     * Gets the institutionalProposalUnrecoveredFandAs attribute. 
     * @return Returns the institutionalProposalUnrecoveredFandAs.
     */
    public List<InstitutionalProposalUnrecoveredFandA> getInstitutionalProposalUnrecoveredFandAs() {
        return institutionalProposalUnrecoveredFandAs;
    }

    /**
     * Sets the institutionalProposalUnrecoveredFandAs attribute value.
     * @param institutionalProposalUnrecoveredFandAs The institutionalProposalUnrecoveredFandAs to set.
     */
    public void setInstitutionalProposalUnrecoveredFandAs(
            List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs) {
        this.institutionalProposalUnrecoveredFandAs = institutionalProposalUnrecoveredFandAs;
    }

    /**
     * Gets the createTimeStamp attribute. 
     * @return Returns the createTimeStamp.
     */
    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    /**
     * Sets the createTimeStamp attribute value.
     * @param createTimeStamp The createTimeStamp to set.
     */
    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getDefaultNewDescription() {
        return "(select)";
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    // public Timestamp getUpdateTimestamp() {
    // return super.getUpdateTimestamp();
    // }
    // public void setUpdateTimestamp(Timestamp updateTimestamp) {
    // super.setUpdateTimestamp(updateTimestamp);
    // }//
    // public String getUpdateUser() {
    // return super.getUpdateUser();
    // }
    // public void setUpdateUser(String updateUser) {
    // super.setUpdateUser(updateUser);
    // }

    public String getProposalSequenceStatus() {
        return proposalSequenceStatus;
    }

    public void setProposalSequenceStatus(String proposalSequenceStatus) {
        this.proposalSequenceStatus = proposalSequenceStatus;
    }

    /** {@inheritDoc} */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("instProposalNumber", this.getInstProposalNumber());
        hashMap.put("sponsorProposalNumber", this.getSponsorProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("proposalTypeCode", this.getProposalTypeCode());
        hashMap.put("currentAccountNumber", this.getCurrentAccountNumber());
        hashMap.put("title", this.getTitle());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("rolodexId", this.getRolodexId());
        hashMap.put("noticeOfOpportunityCode", this.getNoticeOfOpportunityCode());
        hashMap.put("gradStudHeadcount", this.getGradStudHeadcount());
        hashMap.put("gradStudPersonMonths", this.getGradStudPersonMonths());
        hashMap.put("typeOfAccount", this.getTypeOfAccount());
        hashMap.put("activityTypeCode", this.getActivityTypeCode());
        hashMap.put("requestedStartDateInitial", this.getRequestedStartDateInitial());
        hashMap.put("requestedStartDateTotal", this.getRequestedStartDateTotal());
        hashMap.put("requestedEndDateInitial", this.getRequestedEndDateInitial());
        hashMap.put("requestedEndDateTotal", this.getRequestedEndDateTotal());
        hashMap.put("totalDirectCostInitial", this.getTotalDirectCostInitial());
        hashMap.put("totalDirectCostTotal", this.getTotalDirectCostTotal());
        hashMap.put("totalIndirectCostInitial", this.getTotalIndirectCostInitial());
        hashMap.put("totalIndirectCostTotal", this.getTotalIndirectCostTotal());
        hashMap.put("numberOfCopies", this.getNumberOfCopies());
        hashMap.put("deadlineDate", this.getDeadlineDate());
        hashMap.put("deadlineType", this.getDeadlineType());
        hashMap.put("mailBy", this.getMailBy());
        hashMap.put("mailType", this.getMailType());
        hashMap.put("mailAccountNumber", this.getMailAccountNumber());
        hashMap.put("subcontractFlag", this.getSubcontractFlag());
        hashMap.put("costSharingIndicator", this.getCostSharingIndicator());
        hashMap.put("idcRateIndicator", this.getIdcRateIndicator());
        hashMap.put("specialReviewIndicator", this.getSpecialReviewIndicator());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("scienceCodeIndicator", this.getScienceCodeIndicator());
        hashMap.put("nsfCode", this.getNsfCode());
        hashMap.put("primeSponsorCode", this.getPrimeSponsorCode());
        hashMap.put("initialContractAdmin", this.getInitialContractAdmin());
        hashMap.put("ipReviewActivityIndicator", this.getIpReviewActivityIndicator());
        hashMap.put("currentAwardNumber", this.getCurrentAwardNumber());
        hashMap.put("cfdaNumber", this.getCfdaNumber());
        hashMap.put("opportunity", this.getOpportunity());
        hashMap.put("awardTypeCode", this.getAwardTypeCode());
        return hashMap;
    }

    public void addSpecialReview(InstitutionalProposalSpecialReview specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);

    }

    public InstitutionalProposalSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    /**
     * Gets the keywords attribute. 
     * @return Returns the keywords.
     */
    public List<InstitutionalProposalScienceKeyword> getKeywords() {
        return institutionalProposalScienceKeywords;
    }

    /**
     * Sets the keywords attribute value.
     * @param keywords The keywords to set.
     */
    public void setKeywords(List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }

    /**
     * Add selected science keyword to award science keywords list.
     * @see org.kuali.kra.document.KeywordsManager#addKeyword(org.kuali.kra.bo.ScienceKeyword)
     */
    public void addKeyword(ScienceKeyword scienceKeyword) {
        InstitutionalProposalScienceKeyword institutionalProposalScienceKeyword = new InstitutionalProposalScienceKeyword(this,
            scienceKeyword);
        getKeywords().add(institutionalProposalScienceKeyword);
    }

    /**
     * It returns the ScienceKeyword object from keywords list
     * @see org.kuali.kra.document.KeywordsManager#getKeyword(int)
     */
    public InstitutionalProposalScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
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
    public InstitutionalProposal getSequenceOwner() {
        return this;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(InstitutionalProposal newOwner) {
        // no-op
    }

    /**
     * This method removes an AwardFundingProposal
     * 
     * Since Award "owns" the relationship, this method should not be called except from Award
     * 
     * @param afp
     */
    public void remove(AwardFundingProposal afp) {
        awardFundingProposals.remove(afp);
        updateFundingStatus();
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalId = null;
    }

    /**
     * @see org.kuali.kra.SequenceOwner#getName()
     */
    public String getVersionNameField() {
        return "proposalNumber";
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void afterInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.afterInsert(persistenceBroker);
        updateProposalIpReviewJoin();
        // This method links the institutional proposal with the merged proposal log
        if (proposalId != null && proposalNumber != null)
        {
            KraServiceLocator.getService(ProposalLogService.class).updateMergedInstProposal(proposalId, proposalNumber);
        }
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.afterLookup(persistenceBroker);
    }

    protected void updateProposalIpReviewJoin() {
        ProposalIpReviewJoin proposalIpReviewJoin = this.getProposalIpReviewJoin();
        if (ObjectUtils.isNotNull(proposalIpReviewJoin.getProposalIpReviewJoinId())) {
            proposalIpReviewJoin.setProposalIpReviewJoinId(null);
        }
        else {
            IntellectualPropertyReview ipReview = new IntellectualPropertyReview();
            ipReview.setSequenceNumber(0);
            ipReview.setProposalNumber(this.getProposalNumber());
            ipReview.setIpReviewSequenceStatus(VersionStatus.ACTIVE.toString());
            getBusinessObjectService().save(ipReview);
            proposalIpReviewJoin = new ProposalIpReviewJoin();
            proposalIpReviewJoin.setIpReviewId(ipReview.getIpReviewId());
        }
        proposalIpReviewJoin.setProposalId(this.getProposalId());
        getBusinessObjectService().save(proposalIpReviewJoin);
        this.setProposalIpReviewJoin(proposalIpReviewJoin);
    }


    /**
     * This method lazy inits ActivityType
     * @return
     */
    public ActivityType getActivityTypeFromCode() {
        if (activityType == null) {
            if (activityTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<String, Object>();
                identifiers.put("activityTypeCode", activityTypeCode);
                activityType = (ActivityType) getBusinessObjectService().findByPrimaryKey(ActivityType.class, identifiers);
            }
        }
        return activityType;
    }

    /**
     * This method lazy inits ProposalType
     * @return
     */
    public ProposalType getProposalTypeFromCode() {
        if (proposalType == null) {
            if (proposalTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<String, Object>();
                identifiers.put("proposalTypeCode", proposalTypeCode);
                proposalType = (ProposalType) getBusinessObjectService().findByPrimaryKey(ProposalType.class, identifiers);
            }
        }
        return proposalType;
    }

    /**
     * Populate properties on this InstitutionalProposal with the respective properties from ProposalLog.
     * 
     * @param proposalLog ProposalLog
     */
    public void doProposalLogDataFeed(ProposalLog proposalLog) {
        this.setProposalNumber(proposalLog.getProposalNumber());
        this.setDeadlineDate(proposalLog.getDeadlineDate());
        /**
         * per KRACOEUS-4647 we don't want to pull the log's month/year, we want to calculate it fresh.
         */
        this.calculateFiscalMonthAndYearFields();
        //this.setFiscalMonth(proposalLog.getFiscalMonth().toString());
        //this.setFiscalYear(proposalLog.getFiscalYear().toString());
        this.setProposalTypeCode(Integer.parseInt(proposalLog.getProposalTypeCode()));
        this.setStatusCode(1);
        this.setSponsorCode(proposalLog.getSponsorCode());
        this.setTitle(proposalLog.getTitle());
        this.setLeadUnit(getUnitService().getUnit(proposalLog.getLeadUnit()));
        this.setLeadUnitNumber(proposalLog.getLeadUnit());
        this.setDefaultInitialContractAdmin();
        InstitutionalProposalPerson ipPerson = new InstitutionalProposalPerson();
        if (proposalLog.getPerson() != null) {
            ipPerson.setPerson(proposalLog.getPerson());
        }
        else if (proposalLog.getRolodex() != null) {
            ipPerson.setRolodex(proposalLog.getRolodex());
        }
        initializeDefaultPrincipalInvestigator(ipPerson);
        this.setPrincipalInvestigator(ipPerson);
    }

    private void initializeDefaultPrincipalInvestigator(InstitutionalProposalPerson ipPerson) {
        ipPerson.setProposalNumber(this.getProposalNumber());
        ipPerson.setSequenceNumber(this.getSequenceNumber());
        ipPerson.initializeDefaultCreditSplits();
        InstitutionalProposalPersonUnit ipPersonUnit = new InstitutionalProposalPersonUnit();
        ipPersonUnit.setUnit(this.getLeadUnit());
        ipPersonUnit.setLeadUnit(true);
        ipPersonUnit.initializeDefaultCreditSplits();
        ipPerson.add(ipPersonUnit);
        ipPerson.setInstitutionalProposal(this);
    }

    public UnitService getUnitService() {
        return (UnitService) KraServiceLocator.getService(UnitService.class);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }

    private void updateFundingStatus() {
        setStatusCode(awardFundingProposals.size() > 0 ? PROPOSAL_FUNDED_STATUS_CODE : PROPOSAL_PENDING_STATUS_CODE);
    }

    public NsfCode getNsfCodeBo() {
        return nsfCodeBo;
    }

    public void setNsfCodeBo(NsfCode nsfCodeBo) {
        this.nsfCodeBo = nsfCodeBo;
    }

    public KcPerson getInitialContractAdminUser() {
        if (!StringUtils.isBlank(this.getInitialContractAdmin())) {
            try {
                return this.getKcPersonService().getKcPersonByPersonId(this.getInitialContractAdmin());
            }
            catch (Exception e) {
                // TODO temp unit test fix
            }
        }
        return null;
    }

    /* Comments methods. ORM treats comments as a list, so we lazy-copy them to a map for faster access from the getters. */

    public InstitutionalProposalComment getSummaryComment() {
        return getInstitutionalProposalCommentByType(Constants.PROPOSAL_SUMMARY_COMMENT_TYPE_CODE, true);
    }

    public InstitutionalProposalComment getDeliveryComment() {
        return getInstitutionalProposalCommentByType(Constants.PROPOSAL_COMMENT_TYPE_CODE, true);
    }

    public InstitutionalProposalComment getCostShareComment() {
        return getInstitutionalProposalCommentByType(Constants.COST_SHARE_COMMENT_TYPE_CODE, true);
    }

    public InstitutionalProposalComment getUnrecoveredFandAComment() {
        return getInstitutionalProposalCommentByType(Constants.FANDA_RATE_COMMENT_TYPE_CODE, true);
    }

    public InstitutionalProposalComment getGeneralComment() {
        return getInstitutionalProposalCommentByType(Constants.PROPOSAL_IP_REVIEW_COMMENT_TYPE_CODE, true);
    }

    public InstitutionalProposalComment getInstitutionalProposalCommentByType(String commentTypeCode, boolean createNew) {
        InstitutionalProposalComment ipComment = getCommentMap().get(commentTypeCode);
        if (ipComment == null && createNew) {
            ipComment = new InstitutionalProposalComment(commentTypeCode);
            ipComment.refreshReferenceObject("commentType");
            add(ipComment);
            commentMap.put(ipComment.getCommentType().getCommentTypeCode(), ipComment);
        }
        return ipComment;
    }

    public void add(InstitutionalProposalComment ipComment) {
        proposalComments.add(ipComment);
        ipComment.setInstitutionalProposal(this);
    }

    private Map<String, InstitutionalProposalComment> getCommentMap() {
        if (commentMap == null) {
            commentMap = new HashMap<String, InstitutionalProposalComment>();
            for (InstitutionalProposalComment ipc : proposalComments) {
                commentMap.put(ipc.getCommentType().getCommentTypeCode(), ipc);
            }
        }
        return commentMap;
    }

    /**
     * 
     * This method retrieves PROPOSAL_LOG.INST_PROPOSAL_NUMBER
     * from the DB for the current proposal number
     */
    void retrieveInstProposalNumberFromDB()
    {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        ProposalLog proposalLog = (ProposalLog)getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        if (proposalLog == null)
        {
            instProposalNumber = null;
        }
        else
        {
            instProposalNumber = proposalLog.getInstProposalNumber();
        }
    }
    
    public void setLookupUnit(Unit lookupUnit) {
        this.lookupUnit = lookupUnit;
    }

    public Unit getLookupUnit() {
        return lookupUnit;
    }

    public void setLookupUnitName(String lookupUnitName) {
        this.lookupUnitName = lookupUnitName;
    }

    public String getLookupUnitName() {
        return lookupUnitName;
    }

    public void setLookupUnitNumber(String lookupUnitNumber) {
        this.lookupUnitNumber = lookupUnitNumber;
    }

    public String getLookupUnitNumber() {
        return lookupUnitNumber;
    }

    public void setLookupPersonNumber(String lookupPersonNumber) {
        this.lookupPersonNumber = lookupPersonNumber;
    }

    public String getLookupPersonNumber() {
        return lookupPersonNumber;
    }

    public void setShowReturnLink(boolean val) {
        showReturnLink = val;
    }

    public boolean getShowReturnLink() {
        return showReturnLink;
    }
    
    public String getInstProposalNumber(){
        //retrieveInstProposalNumberFromDB()
        if(instProposalNumber == null){
            instProposalNumber = proposalNumber;
        }
        return instProposalNumber;
    }
    
    public void setInstProposalNumber(String instProposalNumber){
        this.instProposalNumber = instProposalNumber;
    }
   

    /**
     * This method returns the combined number of units for all project personnel.
     * 
     * @return
     */
    public int getTotalUnitCount() {
        int count = 0;
        for (InstitutionalProposalPerson person : projectPersons)
            count += person.getUnits().size();
        return count;
    }

    public boolean isSponsorNihMultiplePi() {
        return sponsorNihMultiplePi;
    }

    public void setSponsorNihMultiplePi(boolean sponsorNihMultiplePi) {
        this.sponsorNihMultiplePi = sponsorNihMultiplePi;
    }

    @Override
    public String getLeadUnitName() {
        String name = getLeadUnit() == null ? EMPTY_STRING : getLeadUnit().getUnitName();
        return name;
    }
    
    @Override
    public String getPiName() {
        return getPiEmployeeName();
    }

    @Override
    public String getPiEmployeeName() {
        String name = getPrincipalInvestigator() == null ? EMPTY_STRING : getPrincipalInvestigator().getFullName();
        return name;
    }

    @Override
    public String getPiNonEmployeeName() {
        return EMPTY_STRING;
    }

    @Override
    public String getAdminPersonName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorName() {
        String name = getPrimeSponsor() == null ? EMPTY_STRING : getPrimeSponsor().getSponsorName();
        return name;
    }

    @Override
    public String getSponsorAwardNumber() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardOrganizationName() {
        return EMPTY_STRING;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        List<NegotiationPersonDTO> kcPeople = new ArrayList<NegotiationPersonDTO>();
        for (InstitutionalProposalPerson person : getProjectPersons()) {
            kcPeople.add(new NegotiationPersonDTO(person.getPerson(), person.getRoleCode()));
        }
        return kcPeople;
    }

    @Override
    public String getAssociatedDocumentId() {
        return getProposalNumber();
    }
    
    @Override
    public String getNegotiableProposalTypeCode() {
        if (getProposalTypeCode() != null) {
            return getProposalTypeCode().toString();
        } else {
            return EMPTY_STRING;
        }
    }

    @Override
    public ProposalType getNegotiableProposalType() {
        return this.getProposalType();
    }
}