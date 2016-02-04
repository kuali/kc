/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.home;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.common.framework.keyword.KeywordsManager;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.noo.NoticeOfOpportunity;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.ProposalIpReviewJoin;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.attachments.InstitutionalProposalAttachment;
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
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class InstitutionalProposal extends KcPersistableBusinessObjectBase implements
        KeywordsManager<InstitutionalProposalScienceKeyword>, SequenceOwner<InstitutionalProposal>, Sponsorable, Negotiable {

    public static final String PROPOSAL_ID_PROPERTY_STRING = "proposalId";
    public static final String PROPOSAL_NUMBER_PROPERTY_STRING = "proposalNumber";
    public static final String PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING = "proposalSequenceStatus";
    public static final String PROPOSAL_NUMBER_TEST_DEFAULT_STRING = "1234";
    private static final long serialVersionUID = 1L;
    private static final Integer PROPOSAL_PENDING_STATUS_CODE = 1;
    private static final Integer PROPOSAL_FUNDED_STATUS_CODE = 2;
    public static final String PROPOSAL_STATUS = "proposalStatus";
    public static final String PROPOSAL_TYPE = "proposalType";
    public static final String SPONSOR = "sponsor";
    public static final String PRIME_SPONSOR = "primeSponsor";
    public static final String PROPOSAL_TYPE_CODE = "proposalTypeCode";
    public static final String COMMENT_TYPE = "commentType";
    public static final String ACTIVITY_TYPE = "activityType";
    public static final String ACTIVITY_CODE = "code";
    public static final String SELECT = "(select)";
    private static final Log LOG = LogFactory.getLog(InstitutionalProposal.class);

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
    private ScaleTwoDecimal gradStudPersonMonths;
    private boolean typeOfAccount;
    private String activityTypeCode;
    private Date requestedStartDateInitial;
    private Date requestedStartDateTotal;
    private Date requestedEndDateInitial;
    private Date requestedEndDateTotal;
    private String fiscalMonth;
    private String fiscalYear;
    private ScaleTwoDecimal totalDirectCostInitial;
    private ScaleTwoDecimal totalDirectCostTotal;
    private ScaleTwoDecimal totalIndirectCostInitial;
    private ScaleTwoDecimal totalIndirectCostTotal;
    private String numberOfCopies;
    private Date deadlineDate;
    private String deadlineTime;
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
    private ActivityType activityType;
    private AwardType awardType;
    private ProposalStatus proposalStatus;
    private Unit leadUnit;
    private KcPerson ospAdministrator;
    private InstitutionalProposalScienceKeyword proposalScienceKeyword;
    private InstitutionalProposalCostShare proposalCostSharing;
    private InstitutionalProposalPersonCreditSplit proposalPerCreditSplit;
    private ProposalUnitCreditSplit proposalUnitCreditSplit;
    private List<InstitutionalProposalComment> proposalComments;
    private IntellectualPropertyReview intellectualPropertyReview;

    private static final String DEFAULT_VALUE = "0";
    private static final int INITIAL_SEQUENCE_NUMBER = 1;
    private static final String ACTIVE = "A";
    private static final String INSTITUTIONAL_PROPOSAL_DOCUMENT = "institutionalProposalDocument";

    private List<ProposalIpReviewJoin> proposalIpReviewJoins;
    private List<InstitutionalProposalPerson> projectPersons;
    private List<InstitutionalProposalUnitContact> institutionalProposalUnitContacts = new ArrayList<>();
    private List<InstitutionalProposalCustomData> institutionalProposalCustomDataList;
    private List<InstitutionalProposalNotepad> institutionalProposalNotepads;
    private List<InstitutionalProposalSpecialReview> specialReviews;
    private List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords;
    private List<InstitutionalProposalCostShare> institutionalProposalCostShares;
    private List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs;
    @SkipVersioning
    private List<AwardFundingProposal> awardFundingProposals;
    @SkipVersioning
    private List<AwardFundingProposal> allFundingProposals;
    private Map<String, InstitutionalProposalComment> commentMap;
    private boolean sponsorNihMultiplePi;

    private transient Unit lookupUnit;
    private transient String lookupUnitName;
    private transient String lookupUnitNumber;
    private transient String lookupPersonNumber;
    private transient FiscalYearMonthService fiscalYearMonthService;
    private transient ProposalLogService proposalLogService;
    private transient BusinessObjectService businessObjectService;
    private transient UnitService unitService;
    
    private List<InstitutionalProposalAttachment> instProposalAttachments;
    
    private transient boolean allowUpdateTimestampToBeReset = true;
    private transient KcPersonService kcPersonService;

    public InstitutionalProposal() {
        super();
        initializeInstitutionalProposalWithDefaultValues();
        initializeCollections();
        calculateFiscalMonthAndYearFields();
    }

    /**
     * This method sets the default values for initial persistence as
     * part of skeleton. As various panels are developed;
     * corresponding field initializations should be removed from this method.
     */
    private void initializeInstitutionalProposalWithDefaultValues() {
        setSequenceNumber(INITIAL_SEQUENCE_NUMBER);
        setCostSharingIndicator(DEFAULT_VALUE);
        setIdcRateIndicator(DEFAULT_VALUE);
        setSpecialReviewIndicator(DEFAULT_VALUE);
        setScienceCodeIndicator(DEFAULT_VALUE);
        ipReviewActivityIndicator = ACTIVE;
        Calendar cl = Calendar.getInstance();
        setCreateTimeStamp(new Date(cl.getTime().getTime()));
        setTotalDirectCostInitial(ScaleTwoDecimal.ZERO);
        setTotalDirectCostTotal(ScaleTwoDecimal.ZERO);
        setTotalIndirectCostInitial(ScaleTwoDecimal.ZERO);
        setTotalIndirectCostTotal(ScaleTwoDecimal.ZERO);
        newDescription = getDefaultNewDescription();
        setProposalSequenceStatus(VersionStatus.PENDING.toString());
        setStatusCode(1);// default value for all IP's
        projectPersons = new ArrayList<>();
        showReturnLink = true; // we usually show proposal in lookup
    }

    protected void initializeCollections() {
        institutionalProposalCustomDataList = new ArrayList<>();
        specialReviews = new ArrayList<>();
        institutionalProposalScienceKeywords = new ArrayList<>();
        institutionalProposalCostShares = new ArrayList<>();
        institutionalProposalUnrecoveredFandAs = new ArrayList<>();
        proposalIpReviewJoins = new ArrayList<>();
        proposalIpReviewJoins.add(new ProposalIpReviewJoin());
        awardFundingProposals = new ArrayList<>();
        allFundingProposals = new ArrayList<>();
        institutionalProposalUnitContacts = new ArrayList<>();
        proposalComments = new ArrayList<>();
    }

    public void setDefaultInitialContractAdmin() {
        if (!StringUtils.isBlank(getUnitNumber())) {
            List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(getUnitNumber());
            unitAdministrators.stream()
                    .filter(unitAdministrator -> UnitAdministratorType.OSP_ADMINISTRATOR_TYPE_CODE.equals(unitAdministrator.getUnitAdministratorTypeCode()))
                    .forEach(unitAdministrator -> {
                        this.setInitialContractAdmin(unitAdministrator.getPersonId());
                    });
        }
    }

    public void deactivateFundingProposals() {
        this.getAwardFundingProposals().forEach(fundingProposal -> fundingProposal.setActive(false));
    }

    public void activateFundingProposals() {
        this.getAwardFundingProposals().forEach(fundingProposal -> fundingProposal.setActive(true));
    }

    public boolean isActiveVersion() {
        return this.getProposalSequenceStatus().equals(VersionStatus.ACTIVE.toString());
    }
    
    public boolean isCancelled() {
        return this.getProposalSequenceStatus().equals(VersionStatus.CANCELED.toString());
    }

    /**
     * Is this Proposal funded by the given Award number and version?
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
    protected void calculateFiscalMonthAndYearFields() {
        String monthString = this.getFiscalYearMonthService().getCurrentFiscalMonthForDisplay().toString();
        if (monthString.length() == 1) {
            monthString = DEFAULT_VALUE + monthString;
        }
        setFiscalMonth(monthString);
        setFiscalYear(this.getFiscalYearMonthService().getCurrentFiscalYear().toString());
    }

    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        if (institutionalProposalDocument == null) {
            this.refreshReferenceObject(INSTITUTIONAL_PROPOSAL_DOCUMENT);
        }

        if (institutionalProposalDocument != null && institutionalProposalDocument.getDocumentHeader() != null 
        		&& institutionalProposalDocument.getDocumentNumber() != null && !institutionalProposalDocument.getDocumentHeader().hasWorkflowDocument()) {
            institutionalProposalDocument.getDocumentHeader().setWorkflowDocument(WorkflowDocumentFactory.loadDocument(GlobalVariables.getUserSession().getPrincipalId(), institutionalProposalDocument.getDocumentNumber()));
        }

        return institutionalProposalDocument;
    }

    public void setInstitutionalProposalDocument(InstitutionalProposalDocument institutionalProposalDocument) {
        this.institutionalProposalDocument = institutionalProposalDocument;
    }

    /**
     * The Award "owns" the relationship, so this method should not be called directly. Instead, this method will be called when an
     * InstitutionalProposal is linked to an Award from Award maintenance.
     */
    public void add(AwardFundingProposal afp) {
        awardFundingProposals.add(afp);
    }


    public void add(InstitutionalProposalNotepad institutionalProposalNotepad) {
        institutionalProposalNotepad.setEntryNumber(getInstitutionalProposalNotepads().size() + 1);
        institutionalProposalNotepad.setProposalNumber(this.getProposalNumber());
        getInstitutionalProposalNotepads().add(institutionalProposalNotepad);
        institutionalProposalNotepad.setInstitutionalProposal(this);
    }


    public void add(InstitutionalProposalCostShare institutionalProposalCostShare) {
        institutionalProposalCostShare.setInstitutionalProposal(this);
        institutionalProposalCostShares.add(institutionalProposalCostShare);
    }


    public void add(InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA) {
        institutionalProposalUnrecoveredFandA.setInstitutionalProposal(this);
        institutionalProposalUnrecoveredFandAs.add(institutionalProposalUnrecoveredFandA);
    }

    public ScaleTwoDecimal getTotalInitialCost() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;
        returnValue = returnValue.add(totalDirectCostInitial);
        returnValue = returnValue.add(totalIndirectCostInitial);
        return returnValue;
    }

    public ScaleTwoDecimal getTotalCost() {
        ScaleTwoDecimal returnValue = ScaleTwoDecimal.ZERO;
        returnValue = returnValue.add(totalDirectCostTotal);
        returnValue = returnValue.add(totalIndirectCostTotal);
        return returnValue;
    }

    /**
     * This method calculates the total value of a list of ValuableItems
     */
    ScaleTwoDecimal getTotalAmount(List<? extends ValuableItem> valuableItems) {
        ScaleTwoDecimal returnVal = ScaleTwoDecimal.ZERO;
        for (ValuableItem item : valuableItems) {
            ScaleTwoDecimal amount = item.getAmount() != null ? item.getAmount() : ScaleTwoDecimal.ZERO;
            returnVal = returnVal.add(amount);
        }
        return returnVal;
    }

    public ScaleTwoDecimal getTotalCostShareAmount() {
        return getTotalAmount(institutionalProposalCostShares);
    }

    public ScaleTwoDecimal getTotalUnrecoveredFandAAmount() {
        return getTotalAmount(institutionalProposalUnrecoveredFandAs);
    }

    public List<InstitutionalProposalSpecialReview> getSpecialReviews() {
        return specialReviews;
    }

    public void setSpecialReviews(List<InstitutionalProposalSpecialReview> specialReviews) {
        this.specialReviews = specialReviews;
    }

    public List<InstitutionalProposalCustomData> getInstitutionalProposalCustomDataList() {
        return institutionalProposalCustomDataList;
    }

    public List<InstitutionalProposalNotepad> getInstitutionalProposalNotepads() {

        if (institutionalProposalNotepads == null) {
            institutionalProposalNotepads = new ArrayList<>();
        }
        return institutionalProposalNotepads;
    }

    public void setInstitutionalProposalNotepads(List<InstitutionalProposalNotepad> institutionalProposalNotepads) {
        this.institutionalProposalNotepads = institutionalProposalNotepads;
    }

    public void setInstitutionalProposalCustomDataList(List<InstitutionalProposalCustomData> institutionalProposalCustomDataList) {
        this.institutionalProposalCustomDataList = institutionalProposalCustomDataList;
    }

    public List<InstitutionalProposalScienceKeyword> getInstitutionalProposalScienceKeywords() {
        return institutionalProposalScienceKeywords;
    }

    public void setInstitutionalProposalScienceKeywords(
            List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }

    public void add(InstitutionalProposalPerson projectPerson) {
        projectPersons.add(projectPerson);
        projectPerson.setInstitutionalProposal(this);
    }

    public void add(InstitutionalProposalUnitContact institutionalProposalUnitContact) {
        institutionalProposalUnitContacts.add(institutionalProposalUnitContact);
        institutionalProposalUnitContact.setInstitutionalProposal(this);
    }


    public KcPerson getOspAdministrator() {
        for (InstitutionalProposalUnitContact contact : getInstitutionalProposalUnitContacts()) {
            if (contact.isOspAdministrator()) {
                ospAdministrator = contact.getPerson();
                break;
            }
        }
        return ospAdministrator;
    }

    public void setInstitutionalProposalUnitContacts(List<InstitutionalProposalUnitContact> institutionalProposalUnitContacts) {
        this.institutionalProposalUnitContacts = institutionalProposalUnitContacts;
    }


    public List<InstitutionalProposalUnitContact> getInstitutionalProposalUnitContacts() {
        return institutionalProposalUnitContacts;
    }


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

    public ScaleTwoDecimal getGradStudPersonMonths() {
        return gradStudPersonMonths;
    }

    public void setGradStudPersonMonths(ScaleTwoDecimal gradStudPersonMonths) {
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

    public ScaleTwoDecimal getTotalDirectCostInitial() {
        return totalDirectCostInitial;
    }

    public void setTotalDirectCostInitial(ScaleTwoDecimal totalDirectCostInitial) {
        if (totalDirectCostInitial == null) {
            this.totalDirectCostInitial = ScaleTwoDecimal.ZERO;
        }
        else {
            this.totalDirectCostInitial = totalDirectCostInitial;
        }
    }

    public ScaleTwoDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public void setTotalDirectCostTotal(ScaleTwoDecimal totalDirectCostTotal) {
        if (totalDirectCostTotal == null) {
            this.totalDirectCostTotal = ScaleTwoDecimal.ZERO;
        }
        else {
            this.totalDirectCostTotal = totalDirectCostTotal;
        }
    }

    public ScaleTwoDecimal getTotalIndirectCostInitial() {
        return totalIndirectCostInitial;
    }

    public void setTotalIndirectCostInitial(ScaleTwoDecimal totalIndirectCostInitial) {
        if (totalIndirectCostInitial == null) {
            this.totalIndirectCostInitial = ScaleTwoDecimal.ZERO;
        }
        else {
            this.totalIndirectCostInitial = totalIndirectCostInitial;
        }
    }

    public ScaleTwoDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public void setTotalIndirectCostTotal(ScaleTwoDecimal totalIndirectCostTotal) {
        if (totalIndirectCostTotal == null) {
            this.totalIndirectCostTotal = ScaleTwoDecimal.ZERO;
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

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
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

    public String getMailDescription() {
        return mailDescription;
    }

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
            this.refreshReferenceObject(PROPOSAL_STATUS);
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
            this.refreshReferenceObject(PROPOSAL_TYPE);
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

    public Sponsor getSponsor() {
        if (outOfSync(sponsorCode, sponsor)) {
            this.refreshReferenceObject(SPONSOR);
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        this.sponsorCode = sponsor != null ? sponsor.getSponsorCode() : null;
    }

    public Sponsor getPrimeSponsor() {
        if (outOfSync(primeSponsorCode, primeSponsor)) {
            this.refreshReferenceObject(PRIME_SPONSOR);
        }
        return primeSponsor;
    }

    /**
     * checks if a sponsor code needs refreshing.
     */
    private static boolean outOfSync(String code, Sponsor sponsor) {
        return sponsor == null && !StringUtils.isEmpty(code) || (sponsor != null && !StringUtils.equals(sponsor.getSponsorCode(), code))
                && !StringUtils.isEmpty(code);
    }

    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
        this.primeSponsorCode = primeSponsor != null ? primeSponsor.getSponsorCode() : null;
    }

    public InstitutionalProposalPerson getPrincipalInvestigator() {
        return this.getProjectPersons().stream().filter(InstitutionalProposalPerson::isPrincipalInvestigator).findFirst().orElse(null);
    }

    public void setPrincipalInvestigator(InstitutionalProposalPerson proposalPerson) {
        proposalPerson.setRoleCode(ContactRole.PI_CODE);
        this.getProjectPersons().add(proposalPerson);
    }

    public String getSponsorName() {
        Sponsor tempSponsor = getSponsor();
        return tempSponsor != null ? tempSponsor.getSponsorName() : null;
    }

    public ActivityType getActivityType() {
        if (activityType == null && activityTypeCode != null) {
            this.refreshReferenceObject(ACTIVITY_TYPE);
        }
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public AwardType getAwardType() {
        return awardType;
    }

    public List<AwardFundingProposal> getAwardFundingProposals() {
        return awardFundingProposals;
    }

    public AwardFundingProposal getAwardFundingProposal(int index) {
        return getAwardFundingProposals().get(index);
    }

    public boolean getAwardFundingProposalsExist() {
        return getAllFundingProposals().size() > 0;
    }

    public List<AwardFundingProposal> getActiveAwardFundingProposals() {
        return this.getAwardFundingProposals().stream().filter(AwardFundingProposal::isActive).collect(Collectors.toList());
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }

    public String getFiscalMonth() {
        return fiscalMonth;
    }

    public void setFiscalMonth(String fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }


    public String getLeadUnitNumber() {
        return getUnitNumber();
    }


    public void setLeadUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

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

    public List<InstitutionalProposalPerson> getProjectPersons() {
        return projectPersons; 
    }

    public List<InstitutionalProposalPerson> getSortedProjectPersons() {
        if (CollectionUtils.isNotEmpty(getProjectPersons())) {
            List<InstitutionalProposalPerson> copy = new ArrayList<>(getProjectPersons());
            Collections.sort(copy, new ProjectPersonComparator());
            return copy;
        }
        return Collections.emptyList();
    }

    static class ProjectPersonComparator implements Comparator<InstitutionalProposalPerson>
    {
        
        public int compare(InstitutionalProposalPerson ipp1, InstitutionalProposalPerson ipp2)
        {
            String lastName1 = ipp1.getContact() != null ? ipp1.getContact().getLastName() != null ?
                    ipp1.getContact().getLastName().toUpperCase() : StringUtils.EMPTY : StringUtils.EMPTY;
            String lastName2 = ipp2.getContact() != null ? ipp2.getContact().getLastName() != null ?
                    ipp2.getContact().getLastName().toUpperCase() : StringUtils.EMPTY : StringUtils.EMPTY;
            String contactRoleCode1 = ipp1.getContactRole() != null ? ipp1.getContactRole().getRoleCode() : StringUtils.EMPTY;
            String contactRoleCode2 = ipp2.getContactRole() != null ? ipp2.getContactRole().getRoleCode() : StringUtils.EMPTY;
                
            if (contactRoleCode1.equals(contactRoleCode2)) {
                return lastName1.compareTo(lastName2);
            }
            else {
                if (contactRoleCode1.equals(ContactRole.PI_CODE)) {
                    return -1;
                }
                if (contactRoleCode2.equals(ContactRole.PI_CODE)) {
                    return 1;
                }
                if (contactRoleCode1.equals(ContactRole.COI_CODE)) {
                    return -1;
                } else  {
                    return 1;
                }              
            }
        }
        
    }

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
        if (!CollectionUtils.isEmpty(this.proposalIpReviewJoins)) {
            return this.proposalIpReviewJoins.get(0);
        }
        return null;
    }

    public void setProposalIpReviewJoin(ProposalIpReviewJoin proposalIpReviewJoin) {
        this.proposalIpReviewJoins.add(0, proposalIpReviewJoin);
    }

    public List<InstitutionalProposalCostShare> getInstitutionalProposalCostShares() {
        return institutionalProposalCostShares;
    }

    public void setInstitutionalProposalCostShares(List<InstitutionalProposalCostShare> institutionalProposalCostShares) {
        this.institutionalProposalCostShares = institutionalProposalCostShares;
    }

    public List<InstitutionalProposalUnrecoveredFandA> getInstitutionalProposalUnrecoveredFandAs() {
        return institutionalProposalUnrecoveredFandAs;
    }

    public void setInstitutionalProposalUnrecoveredFandAs(
            List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs) {
        this.institutionalProposalUnrecoveredFandAs = institutionalProposalUnrecoveredFandAs;
    }

    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public String getDefaultNewDescription() {
        return SELECT;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getProposalSequenceStatus() {
        return proposalSequenceStatus;
    }

    public void setProposalSequenceStatus(String proposalSequenceStatus) {
        this.proposalSequenceStatus = proposalSequenceStatus;
    }

    public void addSpecialReview(InstitutionalProposalSpecialReview specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);

    }

    public InstitutionalProposalSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    @Override
    public List<InstitutionalProposalScienceKeyword> getKeywords() {
        return institutionalProposalScienceKeywords;
    }

    public void setKeywords(List<InstitutionalProposalScienceKeyword> institutionalProposalScienceKeywords) {
        this.institutionalProposalScienceKeywords = institutionalProposalScienceKeywords;
    }

    public void addKeyword(ScienceKeyword scienceKeyword) {
        InstitutionalProposalScienceKeyword institutionalProposalScienceKeyword = new InstitutionalProposalScienceKeyword(this,
            scienceKeyword);
        getKeywords().add(institutionalProposalScienceKeyword);
    }

    public InstitutionalProposalScienceKeyword getKeyword(int index) {
        return getKeywords().get(index);
    }

    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++;
    }

    @Override
    public InstitutionalProposal getSequenceOwner() {
        return this;
    }

    @Override
    public void setSequenceOwner(InstitutionalProposal newOwner) {
        // no-op
    }

    /**
     * Since Award "owns" the relationship, this method should not be called except from Award
     */
    public void remove(AwardFundingProposal afp) {
        awardFundingProposals.remove(afp);
        updateFundingStatus();
    }

    @Override
    public void resetPersistenceState() {
        this.proposalId = null;
    }

    @Override
    public String getVersionNameField() {
        return PROPOSAL_NUMBER_PROPERTY_STRING;
    }

    @Override
    public String getVersionNameFieldValue() {
        return proposalNumber;
    }

    @Override
    protected void postPersist() {
        super.postPersist();
        updateProposalIpReviewJoin();
        // This method links the institutional proposal with the merged proposal log
        if (proposalId != null && proposalNumber != null)
            updateMergedInstitutionalProposal();
    }

    private void updateMergedInstitutionalProposal() {
        getProposalLogService().updateMergedInstProposal(proposalId, proposalNumber);
    }

    @Override
    protected void postLoad() {
        super.postLoad();
    }
    
    public List<InstitutionalProposalAttachment> getInstProposalAttachments() {
        if (this.instProposalAttachments == null) {
            this.instProposalAttachments = new ArrayList<>();
        }

        return this.instProposalAttachments;
    }
    
    public InstitutionalProposalAttachment getInstProposalAttachment(int index) {
        return this.instProposalAttachments.get(index);
    }

    public void addAttachment(InstitutionalProposalAttachment attachment) {
        this.getInstProposalAttachments().add(attachment);
        attachment.setInstitutionalProposal(this);
    }

    protected void updateProposalIpReviewJoin() {
        ProposalIpReviewJoin proposalIpReviewJoin = this.getProposalIpReviewJoin();
        if (proposalIpReviewJoin != null) {
	        if (proposalIpReviewJoin.getProposalIpReviewJoinId() != null) {
	            if (proposalIpReviewJoin.getIntellectualPropertyReview() != null)
	                proposalIpReviewJoin.setProposalIpReviewJoinId(null);
	        } else {
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
    }


    /**
     * This method lazy inits ActivityType
     */
    public ActivityType getActivityTypeFromCode() {
        if (activityType == null) {
            if (activityTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<>();
                identifiers.put(ACTIVITY_CODE, activityTypeCode);
                activityType = getBusinessObjectService().findByPrimaryKey(ActivityType.class, identifiers);
            }
        }
        return activityType;
    }

    /**
     * This method lazy inits ProposalType
     */
    public ProposalType getProposalTypeFromCode() {
        if (proposalType == null) {
            if (proposalTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<>();
                identifiers.put(PROPOSAL_TYPE_CODE, proposalTypeCode);
                proposalType = getBusinessObjectService().findByPrimaryKey(ProposalType.class, identifiers);
            }
        }
        return proposalType;
    }

    /**
     * Populate properties on this InstitutionalProposal with the respective properties from ProposalLog.
     */
    public void doProposalLogDataFeed(ProposalLog proposalLog) {
        this.setProposalNumber(proposalLog.getProposalNumber());
        this.setDeadlineDate(proposalLog.getDeadlineDate());
        this.setDeadlineTime(proposalLog.getDeadlineTime());
        this.calculateFiscalMonthAndYearFields();
        this.setProposalTypeCode(Integer.parseInt(proposalLog.getProposalTypeCode()));
        this.setStatusCode(1);
        this.setSponsorCode(proposalLog.getSponsorCode());
        this.setTitle(proposalLog.getTitle());
        this.setLeadUnit(getUnitService().getUnit(proposalLog.getLeadUnit()));
        this.setLeadUnitNumber(proposalLog.getLeadUnit());
        this.setDefaultInitialContractAdmin();
        InstitutionalProposalPerson ipPerson = new InstitutionalProposalPerson();
        if (StringUtils.isNotBlank(proposalLog.getPiId()) && proposalLog.getPerson() != null) {
            ipPerson.setPerson(proposalLog.getPerson());
            ipPerson.setFaculty(proposalLog.getPerson().getFacultyFlag());
        }
        else if (proposalLog.getRolodexId() != null && proposalLog.getRolodex() != null) {
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
        if(unitService == null) {
            unitService = KcServiceLocator.getService(UnitService.class);
        }
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null){
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
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
            } catch (RuntimeException e) {
                LOG.warn(e.getMessage(), e);
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
            ipComment.refreshReferenceObject(COMMENT_TYPE);
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
            commentMap = new HashMap<>();
            for (InstitutionalProposalComment ipc : proposalComments) {
                commentMap.put(ipc.getCommentType().getCommentTypeCode(), ipc);
            }
        }
        return commentMap;
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
        return getLeadUnit() == null ? StringUtils.EMPTY : getLeadUnit().getUnitName();
    }
    
    @Override
    public String getPiName() {
        return getPiEmployeeName();
    }

    @Override
    public String getPiEmployeeName() {
        return getPrincipalInvestigator() == null ? StringUtils.EMPTY : getPrincipalInvestigator().getFullName();
    }

    @Override
    public String getPiNonEmployeeName() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getAdminPersonName() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getPrimeSponsorName() {
        return getPrimeSponsor() == null ? StringUtils.EMPTY : getPrimeSponsor().getSponsorName();
    }

    @Override
    public String getSponsorAwardNumber() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getSubAwardOrganizationName() {
        return StringUtils.EMPTY;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        return getProjectPersons().stream().map(person -> new NegotiationPersonDTO(person.getPerson(), person.getRoleCode())).collect(Collectors.toList());
    }

    @Override
    public String getAssociatedDocumentId() {
        return getProposalNumber();
    }
    
    @Override
    public String getNegotiableProposalTypeCode() {
        return getProposalTypeCode() != null ? getProposalTypeCode().toString() : StringUtils.EMPTY;
    }

    @Override
    public ProposalType getNegotiableProposalType() {
        return this.getProposalType();
    }

    @Override
    public String getSubAwardRequisitionerName() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getSubAwardRequisitionerUnitNumber() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getSubAwardRequisitionerUnitName() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getSubAwardRequisitionerId() {
        return StringUtils.EMPTY;
    }
    
    public FiscalYearMonthService getFiscalYearMonthService() {
        if (this.fiscalYearMonthService == null) {
            this.fiscalYearMonthService = KcServiceLocator.getService(FiscalYearMonthService.class);
        }
        return this.fiscalYearMonthService;
    }
    
    public boolean isAllowUpdateTimestampToBeReset() {
        return allowUpdateTimestampToBeReset;
    }
    
    /**
     * 
     * Setting this value to false will prevent the update timestamp field from being upddate just once.
     * After that, the update timestamp field will update as regular.
     */
    public void setAllowUpdateTimestampToBeReset(boolean allowUpdateTimestampToBeReset) {
        this.allowUpdateTimestampToBeReset = allowUpdateTimestampToBeReset;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (isAllowUpdateTimestampToBeReset()) {
            super.setUpdateTimestamp(updateTimestamp);
        } else {
            setAllowUpdateTimestampToBeReset(true);
        }
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public List<AwardFundingProposal> getAllFundingProposals() {
        return allFundingProposals;
    }

    public void setAllFundingProposals(List<AwardFundingProposal> allFundingProposals) {
        this.allFundingProposals = allFundingProposals;
    }

    public ProposalLogService getProposalLogService() {
        if (proposalLogService == null) {
            proposalLogService = KcServiceLocator.getService(ProposalLogService.class);
        }

        return proposalLogService;
    }

    public void setProposalLogService(ProposalLogService proposalLogService) {
        this.proposalLogService = proposalLogService;
    }
}
