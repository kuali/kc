/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.kuali.core.bo.user.AuthenticationUserId;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.core.document.authorization.PessimisticLock;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.workflow.DocumentInitiator;
import org.kuali.core.workflow.KualiDocumentXmlMaterializer;
import org.kuali.core.workflow.KualiTransactionalDocumentInformation;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.service.impl.KimDao;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.InstituteNarrative;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalNarrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.kra.service.YnqService;
import org.kuali.kra.workflow.KraDocumentXMLMaterializer;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.jpa.annotations.Sequence;

@Entity
//@AttributeOverride(name="documentNumber", column=@Column(name="DOCUMENT_NUMBER"))
@Table(name="EPS_PROPOSAL")
@Sequence(name="SEQ_PROPOSAL_NUMBER_KRA", property="proposalNumber")
public class ProposalDevelopmentDocument extends ResearchDocumentBase implements Copyable, SessionDocument {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDocument.class);
    
//  @GeneratedValue(generator="propNum")
//  @SequenceGenerator(name="propNum",sequenceName="SEQ_PROPOSAL_NUMBER_KRA", allocationSize=2)
//    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
//    @OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})
//    @JoinColumn(name="DOCUMENT_NUMBER", insertable=false, updatable=false)
//    protected DocumentHeader documentHeader;
    
    @Column(name="PROPOSAL_TYPE_CODE")
	private String proposalTypeCode;
    
    @Column(name="CONTINUED_FROM")
	private String continuedFrom;
    
    @Column(name="SPONSOR_CODE")
	private String sponsorCode;
    
    @Column(name="ACTIVITY_TYPE_CODE")
	private String activityTypeCode;
    
    @Column(name="OWNED_BY_UNIT")
	private String ownedByUnitNumber;
    
    @Column(name="REQUESTED_START_DATE_INITIAL")
	private Date requestedStartDateInitial;
    
    @Column(name="REQUESTED_END_DATE_INITIAL")
	private Date requestedEndDateInitial;
    
    @Column(name="TITLE")
	private String title;
    
    @Column(name="CURRENT_AWARD_NUMBER")
	private String currentAwardNumber;
    
    @Column(name="DEADLINE_DATE")
	private Date deadlineDate;
    
    @Column(name="NOTICE_OF_OPPORTUNITY_CODE")
	private String noticeOfOpportunityCode;
    
    @Column(name="DEADLINE_TYPE")
	private String deadlineType;
    
    @Column(name="CFDA_NUMBER")
	private String cfdaNumber;
    
    @Column(name="PROGRAM_ANNOUNCEMENT_NUMBER")
	private String programAnnouncementNumber;
    
    @Column(name="PRIME_SPONSOR_CODE")
	private String primeSponsorCode;
    
    @Column(name="SPONSOR_PROPOSAL_NUMBER")
	private String sponsorProposalNumber;
    
    @Column(name="NSF_CODE")
	private String nsfCode;
    
    @Column(name="SUBCONTRACT_FLAG")
	private Boolean subcontracts;
    
    @Column(name="AGENCY_DIVISION_CODE")
	private String agencyDivisionCode;
    
    @Column(name="AGENCY_PROGRAM_CODE")
	private String agencyProgramCode;
    
    @Column(name="PROGRAM_ANNOUNCEMENT_TITLE")
	private String programAnnouncementTitle;
    
    @Column(name="MAIL_BY")
	private String mailBy;
    
    @Column(name="MAIL_TYPE")
	private String mailType;
    
    @Column(name="MAIL_ACCOUNT_NUMBER")
	private String mailAccountNumber;
    
    @Column(name="MAIL_DESCRIPTION")
	private String mailDescription;
    
    @Column(name="MAILING_ADDRESS_ID")
	private Integer mailingAddressId;
    
    @Column(name="NUMBER_OF_COPIES")
	private String numberOfCopies;
    
    @Column(name="STATUS_CODE")
	private String proposalStateTypeCode;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="STATUS_CODE", insertable=false, updatable=false)
    private ProposalState proposalState = null;
    
    @Column(name="ORGANIZATION_ID")
	private String organizationId;
    
    @Column(name="PERFORMING_ORGANIZATION_ID")
	private String performingOrganizationId;
    
    // if need bi-directional, then 'proposaldevelopmentdocument' need to be added to proposallocation to reference PD
//    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
//            targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalLocation.class, mappedBy="ERROR: See log")
    @Transient
	private List<ProposalLocation> proposalLocations;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization organization;
    
    // TODO: just for organization panel. not a real reference
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PERFORMING_ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization performingOrganization;
    
    // TODO: just for delivery panel. not a real reference
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="MAILING_ADDRESS_ID", insertable=false, updatable=false)
	private Rolodex rolodex;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalSpecialReview> propSpecialReviews;
    
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword.class, mappedBy="proposalDevelopmentDocument")
	private List<PropScienceKeyword> propScienceKeywords;
    
//    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
//           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPerson.class, mappedBy="proposalDevelopmentDocument")
    @Transient
    private List<ProposalPerson> proposalPersons;
    
    @Transient
    private List<S2sOppForms> s2sOppForms;    
    
    @Transient
    private ProposalPerson principalInvestigator;
    
//    @ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="PROPOSAL_NUMBER", insertable=false, updatable=false)
    @Transient
    private S2sOpportunity s2sOpportunity;
    
    @OneToMany(cascade={CascadeType.PERSIST}, 
           targetEntity=org.kuali.kra.s2s.bo.S2sAppSubmission.class, mappedBy="proposalDevelopmentDocument")
	private List<S2sAppSubmission> s2sAppSubmission;
    
    @Transient
    private String newScienceKeywordCode;
    
    @Transient
    private String newDescription;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="SPONSOR_CODE", insertable=false, updatable=false)
	private Sponsor sponsor;
    
    @Transient
    private Integer nextProposalPersonNumber;
    
    @Transient
    private String budgetStatus;
    
    @Column(name="POST_SUB_STATUS_CODE")
	private Integer postSubmissionStatusCode;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalNarrative.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalNarrative> narratives;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalAbstract.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalAbstract> proposalAbstracts;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.InstituteNarrative.class, mappedBy="proposalDevelopmentDocument") 
	private List<InstituteNarrative> instituteAttachments;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalPersonBiography> propPersonBios;
    
    @Transient
    private List<ProposalPerson> investigators;
    
    @Transient
    private Collection<InvestigatorCreditType> investigatorCreditTypes;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="OWNED_BY_UNIT", insertable=false, updatable=false)
	private Unit ownedByUnit;
    
    transient private NarrativeService narrativeService;
    
    transient private ProposalPersonBiographyService proposalPersonBiographyService;
    
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ACTIVITY_TYPE_CODE", insertable=false, updatable=false)
	private ActivityType activityType;

    private transient Boolean allowsNoteAttachments;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalYnq.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalYnq> proposalYnqs;
    
    @Transient
    private List<YnqGroupName> ynqGroupNames;
    
    @OneToMany(cascade={CascadeType.PERSIST}, 
           targetEntity=org.kuali.kra.budget.bo.BudgetVersionOverview.class, mappedBy="proposalDevelopmentDocument")
	private List<BudgetVersionOverview> budgetVersionOverviews;
    
    @Column(name="CREATION_STATUS_CODE")
	private String creationStatusCode;
    
    @OneToMany(cascade={CascadeType.PERSIST}, 
           targetEntity=org.kuali.kra.s2s.bo.S2sSubmissionHistory.class, mappedBy="proposalDevelopmentDocument")
	private List<S2sSubmissionHistory> s2sSubmissionHistory;
    
    @Transient
    private boolean nih=false;
    
    
    @Transient
    HashMap<String, String> nihDescription ;
    
    @OneToMany(cascade={CascadeType.PERSIST}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalChangedData.class, mappedBy="proposalDevelopmentDocument")
	private List<ProposalChangedData> proposalChangedDataList;
    
    @Transient
    private Map<String, List<ProposalChangedData>> proposalChangeHistory;

    
    @Type(type="yes_no")
    @Column(name="SUBMIT_FLAG")
    private Boolean submitFlag = false;

    
    @SuppressWarnings("unchecked")
    public ProposalDevelopmentDocument() {
        super();
        setProposalStateTypeCode(ProposalState.IN_PROGRESS);
        propScienceKeywords = new TypedArrayList(PropScienceKeyword.class);
        newDescription = getDefaultNewDescription();
        proposalLocations = new ArrayList<ProposalLocation>();
        propSpecialReviews = new TypedArrayList(ProposalSpecialReview.class);
        proposalPersons = new ArrayList<ProposalPerson>();
        nextProposalPersonNumber = new Integer(1);
        narratives = new ArrayList<ProposalNarrative>();
        proposalAbstracts = new ArrayList<ProposalAbstract>();
        instituteAttachments = new ArrayList<InstituteNarrative>();
        propPersonBios = new ArrayList<ProposalPersonBiography>();
        proposalYnqs = new ArrayList<ProposalYnq>();
        ynqGroupNames = new ArrayList<YnqGroupName>();
        budgetVersionOverviews = new TypedArrayList(BudgetVersionOverview.class);
        investigators = new ArrayList<ProposalPerson>();
        s2sOppForms = new ArrayList<S2sOppForms>();        
        s2sAppSubmission = new ArrayList<S2sAppSubmission>();
        s2sSubmissionHistory = new ArrayList<S2sSubmissionHistory>();
        proposalChangedDataList = new TypedArrayList(ProposalChangedData.class);
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();

    }

    public void initialize() {
        super.initialize();
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        List<Unit> userUnits = proposalDevelopmentService.getDefaultModifyProposalUnitsForUser(GlobalVariables.getUserSession()
                .getLoggedInUserNetworkId());
        if (userUnits.size() == 1) {
            this.setOwnedByUnitNumber(userUnits.get(0).getUnitNumber());
            proposalDevelopmentService.initializeUnitOrganzationLocation(this);
        }
    }
    
    /**
     * Gets the value of proposalPersons
     * 
     * @return the value of proposalPersons
     */
    public List<ProposalPerson> getProposalPersons() {
        evaluateMoveOptions();
        return this.proposalPersons;
    }
    
    private void evaluateMoveOptions() {
        for(int i = 0; i < proposalPersons.size(); i++) {
            ProposalPerson person = proposalPersons.get(i);
            person.setMoveUpAllowed(i > 0 && person.getProposalPersonRoleId().equals(proposalPersons.get(i-1).getProposalPersonRoleId()));
            person.setMoveDownAllowed(i < (proposalPersons.size() - 1) && person.getProposalPersonRoleId().equals(proposalPersons.get(i+1).getProposalPersonRoleId()));
        }
    }
    
    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }

    public List<ProposalPerson> getInvestigators() {
        return investigators;
    }
    
    /**
     * Sets the value of proposalPersons
     *
     * @param argProposalPersons Value to assign to this.proposalPersons
     */
    public void setProposalPersons(List<ProposalPerson> argProposalPersons) {
        this.proposalPersons = argProposalPersons;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getOwnedByUnitNumber() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumber(String ownedByUnit) {
        this.ownedByUnitNumber = ownedByUnit;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    /**
     * Gets the continuedFrom attribute.
     * @return Returns the continuedFrom.
     */
    public String getContinuedFrom() {
        return continuedFrom;
    }

    /**
     * Sets the continuedFrom attribute value.
     * @param continuedFrom The continuedFrom to set.
     */
    public void setContinuedFrom(String continuedFrom) {
        this.continuedFrom = continuedFrom;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    /**
     * Gets the agencyDivisionCode attribute.
     *
     * @return Returns the agencyDivisionCode.
     */
    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }

    /**
     * Sets the agencyDivisionCode attribute value.
     *
     * @param agencyDivisionCode The agencyDivisionCode to set.
     */
    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }

    /**
     * Gets the agencyProgramCode attribute.
     *
     * @return Returns the agencyProgramCode.
     */
    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }

    /**
     * Sets the agencyProgramCode attribute value.
     *
     * @param agencyProgramCode The agencyProgramCode to set.
     */
    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }

    /**
     * Gets the cfdaNumber attribute.
     *
     * @return Returns the cfdaNumber.
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * Sets the cfdaNumber attribute value.
     *
     * @param cfdaNumber The cfdaNumber to set.
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    /**
     * Gets the deadlineDate attribute.
     *
     * @return Returns the deadlineDate.
     */
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Sets the deadlineDate attribute value.
     *
     * @param deadlineDate The deadlineDate to set.
     */
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Gets the deadlineType attribute.
     *
     * @return Returns the deadlineType.
     */
    public String getDeadlineType() {
        return deadlineType;
    }

    /**
     * Sets the deadlineType attribute value.
     *
     * @param deadlineType The deadlineType to set.
     */
    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    /**
     * Gets the noticeOfOpportunityCode attribute.
     *
     * @return Returns the noticeOfOpportunityCode.
     */
    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    /**
     * Sets the noticeOfOpportunityCode attribute value.
     *
     * @param noticeOfOpportunityCode The noticeOfOpportunityCode to set.
     */
    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    /**
     * Gets the nsfCode attribute.
     *
     * @return Returns the nsfCode.
     */
    public String getNsfCode() {
        return nsfCode;
    }

    /**
     * Sets the nsfCode attribute value.
     *
     * @param nsfCode The nsfCode to set.
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    /**
     * Gets the primeSponsorCode attribute.
     *
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     * Sets the primeSponsorCode attribute value.
     *
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    /**
     * Gets the programAnnouncementNumber attribute.
     *
     * @return Returns the programAnnouncementNumber.
     */
    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }

    /**
     * Sets the programAnnouncementNumber attribute value.
     *
     * @param programAnnouncementNumber The programAnnouncementNumber to set.
     */
    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }

    /**
     * Gets the programAnnouncementTitle attribute.
     *
     * @return Returns the programAnnouncementTitle.
     */
    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }

    /**
     * Sets the programAnnouncementTitle attribute value.
     *
     * @param programAnnouncementTitle The programAnnouncementTitle to set.
     */
    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }

    /**
     * Gets the sponsorProposalNumber attribute.
     *
     * @return Returns the sponsorProposalNumber.
     */
    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    /**
     * Sets the sponsorProposalNumber attribute value.
     *
     * @param sponsorProposalNumber The sponsorProposalNumber to set.
     */
    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    /**
     * Gets the subcontracts attribute.
     *
     * @return Returns the subcontracts.
     */
    public Boolean getSubcontracts() {
        return subcontracts;
    }

    /**
     * Sets the subcontracts attribute value.
     *
     * @param subcontracts The subcontracts to set.
     */
    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
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

    public Integer getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPerformingOrganizationId() {
        return performingOrganizationId;
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        // TODO : not sure yet
        if ((performingOrganizationId==null || performingOrganizationId.trim().equals("")) && organizationId!=null) {
            this.performingOrganizationId = organizationId;
        } else {
            this.performingOrganizationId = performingOrganizationId;
        }
    }

    public List<ProposalLocation> getProposalLocations() {
        return proposalLocations;
    }

    public void setProposalLocations(List<ProposalLocation> proposalLocations) {
        this.proposalLocations = proposalLocations;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getPerformingOrganization() {
        return performingOrganization;
    }

    public void setPerformingOrganization(Organization performingOrganization) {
        this.performingOrganization = performingOrganization;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
        this.propScienceKeywords = propScienceKeywords;
    }

    public List<PropScienceKeyword> getPropScienceKeywords() {
        return propScienceKeywords;
    }

    public void addPropScienceKeyword(PropScienceKeyword propScienceKeyword) {
        getPropScienceKeywords().add(propScienceKeyword);
    }

    public String getNewScienceKeywordCode() {
        return newScienceKeywordCode;
    }
    public void setNewScienceKeywordCode(String newScienceKeywordCode) {
        this.newScienceKeywordCode = newScienceKeywordCode;
    }
    public String getNewDescription() {
        return newDescription;
    }
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getDefaultNewDescription() {
        return "(select)";
    }

    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }

    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }
    
    private ProposalDevelopmentDocument getPersistedCopy() {
        ProposalDevelopmentDocument copy = null;
        
        try {
            copy = (ProposalDevelopmentDocument) KNSServiceLocator.getDocumentService().getByDocumentHeaderId(this.getDocumentNumber());
        }
        catch (Exception e) {
        }
        
        return copy;
    }

    private boolean isNotLatest(ProposalDevelopmentDocument copy) {
        if(copy.getVersionNumber().longValue() > this.getVersionNumber().longValue()) 
            return false;
        
        return true;
    }
    
    private void refreshNarrativesFromUpdatedCopy(List managedLists) {
        ProposalDevelopmentDocument retrievedDocument = getPersistedCopy();
        if(retrievedDocument == null)
            return;
        
        List<ProposalNarrative> narratives = retrievedDocument.getProposalNarratives();
        for (ProposalNarrative narrative : narratives) {
            managedLists.add(narrative.getNarrativeUserRights());
        }
        managedLists.add(narratives);

        if(isNotLatest(retrievedDocument)) {
            //The same document has been updated by someone else
            //Refresh Narratives related collections
            if(narratives.size() >= this.getProposalNarratives().size()) {
                this.setProposalNarratives(narratives);
            }
        } 
    }
    
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        UniversalUser currentUser = GlobalVariables.getUserSession().getUniversalUser();
        
        //Pessimistic Lock Impl - building DeletionAwareLists based on the Active Locks held by the current user for this document
        List<PessimisticLock> locksOwnedByCurrentUser = new ArrayList<PessimisticLock>();
        ProposalDevelopmentDocument retrievedDocument = null;
        
        for(PessimisticLock lock: getPessimisticLocks()) {
            if(lock.isOwnedByUser(currentUser) && lock.getLockDescriptor().contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL)) {
                refreshNarrativesFromUpdatedCopy(managedLists);
                break;  
            } 
        } 
        List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
        for (ProposalPerson person : getProposalPersons()) {
        units.addAll(person.getUnits());
        }

        managedLists.add(units);
        managedLists.add(getProposalLocations());
        managedLists.add(getPropSpecialReviews());
        managedLists.add(getProposalPersons());
        managedLists.add(getPropScienceKeywords());
        managedLists.add(getProposalAbstracts());
        managedLists.add(getPropPersonBios());
        managedLists.add(getS2sAppSubmission());
        managedLists.add(getS2sSubmissionHistory());
        /*
         * This is really bogus, but OJB doesn't delete a BO component from the
         * database after it is set to null, i.e. the S2S Opportunity.  It is
         * the same issue as deleting items from a list.  To get around OJB's
         * stupidity, we will construct a list which will contain the Opportunity
         * if it is present.  The Kuali Core logic will then force the deletion.
         */
        List<S2sOpportunity> opportunities = new ArrayList<S2sOpportunity>();
        S2sOpportunity opportunity = this.getS2sOpportunity();
        if (opportunity != null) {
            opportunities.add(opportunity);
            s2sOppForms = opportunity.getS2sOppForms();
        }        
        if(s2sOppForms!=null && s2sOppForms.size()>0){
            managedLists.add(s2sOppForms);
        }else{
            managedLists.add(new ArrayList<S2sOppForms>());
        }
        managedLists.add(opportunities);
        return managedLists;
    }

    /**
     * Gets the sponsor attribute.
     * @return Returns the sponsor.
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * Sets the sponsor attribute value.
     * @param sponsor The sponsor to set.
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


    /**
     * Accessor for the principal investigator of this proposal
     *
     * @return ProposalPerson
     */
    public ProposalPerson getPrincipalInvestigator() {
        return principalInvestigator;
    }

    /**
     * Accessor for the principal investigator of this proposal
     *
     * @param person
     */
    public void setPrincipalInvestigator(ProposalPerson person) {
        principalInvestigator = person;
    }

    public void setNextProposalPersonNumber(Integer n) {
        nextProposalPersonNumber = n;
    }
    
    public Integer getNextProposalPersonNumber() {
        return nextProposalPersonNumber;
    }
    
    /**
     * Adds a new proposal person to the collection in the document
     *
     * @param p person to add
     */
    public void addProposalPerson(ProposalPerson p) {
        p.setProposalPersonNumber(this.getDocumentNextValue(Constants.PROPOSAL_PERSON_NUMBER));
        getProposalPersons().add(p);
    }

    public ProposalPerson getProposalPerson(int index) {
        while (getProposalPersons().size() <= index) {
            getProposalPersons().add(new ProposalPerson());
        }
        
        return getProposalPersons().get(index);
    }

    /**
     * Get the list of Proposal Attachments (Narratives) for this Proposal.
     * @return the proposal's list of narratives.
     */
    public List<ProposalNarrative> getProposalNarratives() {
        return narratives;
    }
    /**
     * Set the list of Proposal Attachments (Narratives) for this Proposal.
     * @param narratives the proposal's new list of narratives.
     */
    public void setProposalNarratives(List<ProposalNarrative> narratives) {
        this.narratives = narratives;
    }

    /**
     * Get the list of Abstracts for this Proposal.
     * @return the proposal's list of abstracts.
     */
    public List<ProposalAbstract> getProposalAbstracts() {
        return proposalAbstracts;
    }

    /**
     * Set the list of Abstracts for this Proposal.
     * @param proposalAbstracts the proposal's new list of abstracts.
     */
    public void setProposalAbstracts(List<ProposalAbstract> proposalAbstracts) {
        this.proposalAbstracts = proposalAbstracts;
    }

    public List<InstituteNarrative> getInstituteAttachments() {
        return instituteAttachments;
    }

    public void setInstituteAttachments(List<InstituteNarrative> instituteAttachments) {
        this.instituteAttachments = instituteAttachments;
    }

    public List<ProposalPersonBiography> getPropPersonBios() {
        return propPersonBios;
    }

    public void setPropPersonBios(List<ProposalPersonBiography> propPersonBios) {
        this.propPersonBios = propPersonBios;
    }

    /**
     * Gets the ownedByUnit attribute. 
     * @return Returns the ownedByUnit.
     */
    public Unit getOwnedByUnit() {
        return ownedByUnit;
    }

    /**
     * Sets the ownedByUnit attribute value.
     * @param ownedByUnit The ownedByUnit to set.
     */
    public void setOwnedByUnit(Unit ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }

    /**
     * 
     * This method adds new personnel attachment to biography and biography attachment bo with proper set up.
     * @param proposalPersonBiography
     * @throws Exception
     */
    public void addProposalPersonBiography(ProposalPersonBiography proposalPersonBiography) throws Exception {
        getProposalPersonBiographyService().addProposalPersonBiography(this, proposalPersonBiography);
    }
    
    /**
     * 
     * This method delete the attachment for the deleted personnel.
     * @param proposalPerson
     * @throws Exception
     */
    public void removePersonnelAttachmentForDeletedPerson(ProposalPerson proposalPerson) throws Exception {
        getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(this, proposalPerson);
    }
        
    /**
     * 
     * Method to delete a personnel attachment from personnel attachment list
     * @param narrative
     */
    public void deleteProposalPersonBiography(int lineToDelete) {
        getProposalPersonBiographyService().deleteProposalPersonBiography(this, lineToDelete);
    }

    /**
     * 
     * Method to add a new narrative to narratives list
     * @param narrative
     */
    public void addNarrative(ProposalNarrative narrative) {
        getNarrativeService().addNarrative(this, narrative);
    }
    /**
     * 
     * Method to delete a narrative from narratives list
     * @param narrative
     */
    public void deleteProposalAttachment(int lineToDelete) {
        getNarrativeService().deleteProposalAttachment(this, lineToDelete);
    }

    /**
     * 
     * Method to add a new institute attachment to institute attachment list
     * @param narrative
     */
    public void addInstituteAttachment(InstituteNarrative narrative) {
        getNarrativeService().addInstituteAttachment(this, narrative);
    }

    /**
     * 
     * Method to delete a narrative from narratives list
     * @param narrative
     */
    public void deleteInstitutionalAttachment(int lineToDelete) {
        getNarrativeService().deleteInstitutionalAttachment(this, lineToDelete);
    }

    public void populatePersonNameForNarrativeUserRights(int lineNumber) {
        if(!getProposalNarratives().isEmpty()){
            ProposalNarrative narrative = getProposalNarratives().get(lineNumber);
            getNarrativeService().populatePersonNameForNarrativeUserRights(this, narrative);
        }
    }

    public void populatePersonNameForInstituteAttachmentUserRights(int lineNumber) {
        if(!getInstituteAttachments().isEmpty()){
            InstituteNarrative narrative = getInstituteAttachments().get(lineNumber);
            getNarrativeService().populatePersonNameForNarrativeUserRights(this, narrative);
        }
    }

    public void replaceAttachment(int selectedLine) {
        ProposalNarrative narrative = getProposalNarratives().get(selectedLine);
        getNarrativeService().replaceAttachment(narrative);
    }
    
    public void replaceInstituteAttachment(int selectedLine) {
        InstituteNarrative narrative = getInstituteAttachments().get(selectedLine);
        getNarrativeService().replaceAttachment(narrative);
    }


    public void populateNarrativeRightsForLoggedinUser() {
        getNarrativeService().populateNarrativeRightsForLoggedinUser(this);
    }

    /**
     * Gets the narrativeService attribute. 
     * @return Returns the narrativeService.
     */
    public NarrativeService getNarrativeService() {
        if(narrativeService==null){
            narrativeService = getService(NarrativeService.class);
        }
        return narrativeService;
    }
    
    /**
     * Sets the narrativeService attribute value.
     * @param narrativeService The narrativeService to set.
     */
    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        if(proposalPersonBiographyService==null){
            proposalPersonBiographyService = getService(ProposalPersonBiographyService.class);
        }
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }

    /**
     * Accessor method to locally store credit types
     *
     * @param creditTypes a <code>{@link Collection}</code> of credit types
     */
    public void setInvestigatorCreditTypes(Collection<InvestigatorCreditType> creditTypes) {
        investigatorCreditTypes = creditTypes;
    }
    
    /**
     * Accessor method to locally store credit types
     *
     * @return <code>{@link Collection}</code> of credit types
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        return investigatorCreditTypes;
    }
    
    
    public List<ProposalYnq> getProposalYnqs() {
        return proposalYnqs;
    }

    public void setProposalYnqs(List<ProposalYnq> proposalYnqs) {
        this.proposalYnqs = proposalYnqs;
    }

    public List<YnqGroupName> getYnqGroupNames() {
        if(ynqGroupNames.isEmpty()) {
            getYnqService().populateProposalQuestions(this.proposalYnqs, this.ynqGroupNames, this);
        }
        Collections.sort(ynqGroupNames, new YnqGroupName());
        return ynqGroupNames;
    }
    
    public void setYnqGroupNames(List<YnqGroupName> ynqGroupNames) {
        this.ynqGroupNames = ynqGroupNames;
    }

    
    // getters to auto-grow list to prevent arrayindexoutofbound exception
    // also used in JSP
    /**
     * Gets index i from the propSpecialReviews list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalSpecialReview getPropSpecialReview(int index) {
        while (getPropSpecialReviews().size() <= index) {
            getPropSpecialReviews().add(new ProposalSpecialReview());
        }
        return (ProposalSpecialReview) getPropSpecialReviews().get(index);
    }
    
    /**
     * Gets index i from the propScienceKeywords list.
     * 
     * @param index
     * @return Question at index i
     */
    public PropScienceKeyword getPropScienceKeyword(int index) {
        while (getPropScienceKeywords().size() <= index) {
            getPropScienceKeywords().add(new PropScienceKeyword());
        }
        return (PropScienceKeyword) getPropScienceKeywords().get(index);
    }

    /**
     * Gets index i from the proposalLocations list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalLocation getProposalLocation(int index) {
        while (getProposalLocations().size() <= index) {
            getProposalLocations().add(new ProposalLocation());
        }
        return (ProposalLocation) getProposalLocations().get(index);
    }

    /**
     * Gets index i from the narratives list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalNarrative getProposalNarrative(int index) {
        while (getProposalNarratives().size() <= index) {
            getProposalNarratives().add(new ProposalNarrative());
        }
        return (ProposalNarrative) getProposalNarratives().get(index);
    }

    /**
     * Gets index i from the institute attachment list.
     * 
     * @param index
     * @return Question at index i
     */
    public InstituteNarrative getInstituteAttachment(int index) {
        while (getInstituteAttachments().size() <= index) {
            getInstituteAttachments().add(new InstituteNarrative());
        }
        return (InstituteNarrative) getInstituteAttachments().get(index);
    }

    /**
     * Gets index i from the proposalAbstracts list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalAbstract getProposalAbstract(int index) {
        while (getProposalAbstracts().size() <= index) {
            getProposalAbstracts().add(new ProposalAbstract());
        }
        return (ProposalAbstract) getProposalAbstracts().get(index);
    }

    /**
     * Gets index i from the proposalAbstracts list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPersonBiography getPropPersonBio(int index) {
        while (getPropPersonBios().size() <= index) {
            getPropPersonBios().add(new ProposalPersonBiography());
        }
        return (ProposalPersonBiography) getPropPersonBios().get(index);
    }

    
    /**
     * Gets index i from the investigators list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalPerson getInvestigator(int index) {
        while (getInvestigators().size() <= index) {
            getInvestigators().add(new ProposalPerson());
        }
        
        return getInvestigators().get(index);
    }

    /**
     * Gets index i from the proposalYnqs list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalYnq getProposalYnq(int index) {
        while (getProposalYnqs().size() <= index) {
            getProposalYnqs().add(new ProposalYnq());
        }
        
        return (ProposalYnq)getProposalYnqs().get(index);
    }
    
    /**
     * Gets index i from the ynqGroupNames list.
     * 
     * @param index
     * @return Question at index i
     */
    public YnqGroupName getYnqGroupName(int index) {
        while (getYnqGroupNames().size() <= index) {
            getYnqGroupNames().add(new YnqGroupName());
        }
        
        return (YnqGroupName)getYnqGroupNames().get(index);
    }

    /**
     * Gets the ynqService attribute. 
     * @return Returns the ynqService.
     */
    public YnqService getYnqService() {
        return getService(YnqService.class);
    }
    
    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public List<BudgetVersionOverview> getBudgetVersionOverviews() {
        return budgetVersionOverviews;
    }

    public void setBudgetVersionOverviews(List<BudgetVersionOverview> budgetVersionOverviews) {
        this.budgetVersionOverviews = budgetVersionOverviews;
    }
    
    /**
     * This method gets the next budget version number for this proposal.
     * We can't use documentNextVersionNumber because that requires a save and we don't
     * want to save the proposal development document before forwarding to the budget document.
     * @return Integer
     */
    public Integer getNextBudgetVersionNumber() {
        List<BudgetVersionOverview> versions = getBudgetVersionOverviews();
        if (versions.isEmpty()) {
            return 1;
        }
        Collections.sort(versions);
        BudgetVersionOverview lastVersion = versions.get(versions.size() - 1);
        return lastVersion.getBudgetVersionNumber() + 1;
    }
    
    /**
     * Gets index i from the budget versions list.
     * 
     * @param index
     * @return BudgetVersionOverview at index i
     */
    public BudgetVersionOverview getBudgetVersionOverview(int index) {
        while (getBudgetVersionOverviews().size() <= index) {
            getBudgetVersionOverviews().add(new BudgetVersionOverview());
        }
        return (BudgetVersionOverview) getBudgetVersionOverviews().get(index);
    }
    
    public List<S2sOppForms> getS2sOppForms() {
        return s2sOppForms;
    }

    public void setS2sOppForms(List<S2sOppForms> oppForms) {
        s2sOppForms = oppForms;
    }

    public void setS2sOpportunity(S2sOpportunity s2sOpportunity) {
        this.s2sOpportunity = s2sOpportunity;
    }

    public S2sOpportunity getS2sOpportunity() {
        return s2sOpportunity;
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if(s2sOpportunity!=null && s2sOpportunity.getOpportunityId()==null){
            s2sOpportunity = null;
        }
        
        KraServiceLocator.getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);
        // temp way to set proposalnumber
        for (ProposalLocation loc : getProposalLocations()) {
            loc.setProposalNumber(getProposalNumber());
        }
        for (ProposalYnq ynq : getProposalYnqs()) {
            ynq.setProposalNumber(getProposalNumber());
        }

        if (this.getBudgetVersionOverviews() != null) {
            updateDocumentDescriptions(this.getBudgetVersionOverviews());
        }
        if (StringUtils.isBlank(getProposalNumber())) {
            setProposalNumber(getSequenceNumber());
        }
    }
    
    private String getSequenceNumber() {
        KimDao personRoleDao = KraServiceLocator.getService(KimDao.class);
        Sequence sequence = ProposalDevelopmentDocument.class.getAnnotation(Sequence.class); 
        return personRoleDao.getNextAutoIncValue(sequence);
    }
    
    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(this);
        
        proposalChangeHistory = new TreeMap<String, List<ProposalChangedData>>();
        //Arranging Proposal Change History
        if(CollectionUtils.isNotEmpty(this.getProposalChangedDataList())) {
            for(ProposalChangedData proposalChangedData : this.getProposalChangedDataList()) {
                if(this.getProposalChangeHistory().get(proposalChangedData.getEditableColumn().getColumnLabel()) == null) {
                    this.getProposalChangeHistory().put(proposalChangedData.getEditableColumn().getColumnLabel(), new ArrayList<ProposalChangedData>());
                } 
                
                this.getProposalChangeHistory().get(proposalChangedData.getEditableColumn().getColumnLabel()).add(proposalChangedData);
            }
        }

    }
    
    public ProposalPerson getProposalEmployee(String personId) {
        for (ProposalPerson proposalPerson: getProposalPersons()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson;
            }
        }
        return null;
    }
    
    public ProposalPerson getProposalNonEmployee(Integer rolodexId) {
        for (ProposalPerson proposalPerson: getProposalPersons()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson;
            }
        }
        return null;
    }
    
    public ProposalPersonRole getProposalEmployeeRole(String personId) {
        if (principalInvestigator != null && personId.equals(principalInvestigator.getPersonId())) {
            return principalInvestigator.getRole();
        }
        for (ProposalPerson proposalPerson: getInvestigators()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson.getRole();
            }
        }
        for (ProposalPerson proposalPerson: getProposalPersons()) {
            if (personId.equals(proposalPerson.getPersonId())) {
                return proposalPerson.getRole();
            }
        }
        return null;
    }
    
    public ProposalPersonRole getProposalNonEmployeeRole(Integer rolodexId) {
        if (principalInvestigator != null && rolodexId.equals(principalInvestigator.getRolodexId())) {
            return principalInvestigator.getRole();
        }
        for (ProposalPerson proposalPerson: getInvestigators()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson.getRole();
            }
        }
        for (ProposalPerson proposalPerson: getProposalPersons()) {
            if (rolodexId.equals(proposalPerson.getRolodexId())) {
                return proposalPerson.getRole();
            }
        }
        return null;
    }
    
    public BudgetVersionOverview getFinalBudgetVersion() {
        for (BudgetVersionOverview version: this.getBudgetVersionOverviews()) {
            if (version.isFinalVersionFlag()) {
                return version;
            }
        }
        return null;
    }
    
    public Boolean getAllowsNoteAttachments() {
        if(allowsNoteAttachments == null) {
            DataDictionary dataDictionary = KNSServiceLocator.getDataDictionaryService().getDataDictionary();
            DocumentEntry entry = dataDictionary.getDocumentEntry(getClass().getName());
            allowsNoteAttachments = entry.getAllowsNoteAttachments();
        }
        
        return allowsNoteAttachments;
    }

    public void setAllowsNoteAttachments(boolean allowsNoteAttachments) {
        this.allowsNoteAttachments = allowsNoteAttachments;
    }
    
    public void addNewBudgetVersion(BudgetDocument budgetDocument, String name, boolean isDescriptionUpdatable) {
        BudgetVersionOverview budgetVersion = new BudgetVersionOverview();
        budgetVersion.setDocumentNumber(budgetDocument.getDocumentNumber());
        budgetVersion.setProposalNumber(this.getProposalNumber());
        budgetVersion.setDocumentDescription(name);
        budgetVersion.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
        budgetVersion.setStartDate(budgetDocument.getStartDate());
        budgetVersion.setEndDate(budgetDocument.getEndDate());
        budgetVersion.setOhRateTypeCode(budgetDocument.getOhRateTypeCode());
        budgetVersion.setVersionNumber(budgetDocument.getVersionNumber());
        budgetVersion.setDescriptionUpdatable(isDescriptionUpdatable);
        
        String budgetStatusIncompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
        budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);
        
        this.getBudgetVersionOverviews().add(budgetVersion);
    }

    /**
     * Gets the creationStatusCode attribute. 
     * @return Returns the creationStatusCode.
     */
    public String getCreationStatusCode() {
        return creationStatusCode;
    }

    /**
     * Sets the creationStatusCode attribute value.
     * @param creationStatusCode The creationStatusCode to set.
     */
    public void setCreationStatusCode(String creationStatusCode) {
        this.creationStatusCode = creationStatusCode;
    }
    public final ActivityType getActivityType() {
        return activityType;
    }

    public final void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
    
    public boolean isProposalComplete() {
        String budgetStatusCompleteCode = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_COMPLETE_CODE);
        
        if (this.getBudgetStatus() != null && budgetStatusCompleteCode != null && this.getBudgetStatus().equals(budgetStatusCompleteCode)) {
            return true;
        }
        return false;
    }
    
    public int getNumberOfVersions() {
        return this.getBudgetVersionOverviews().size();
    }

    /**
     * Wraps a document in an instance of KualiDocumentXmlMaterializer, that provides additional metadata for serialization
     * 
     * @see org.kuali.core.document.Document#wrapDocumentWithMetadataForXmlSerialization()
     */
    @Override
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        ProposalAuthorizationService proposalauthservice=(ProposalAuthorizationService)KraServiceLocator.getService(ProposalAuthorizationService.class); 
        KualiTransactionalDocumentInformation transInfo = new KualiTransactionalDocumentInformation();
        DocumentInitiator initiatior = new DocumentInitiator();
        String initiatorNetworkId = getDocumentHeader().getWorkflowDocument().getInitiatorNetworkId();
        try {
            UniversalUser initiatorUser = KNSServiceLocator.getUniversalUserService().getUniversalUser(new AuthenticationUserId(initiatorNetworkId));
            initiatorUser.getModuleUsers(); // init the module users map for serialization
            initiatior.setUniversalUser(initiatorUser);
        }
        catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        transInfo.setDocumentInitiator(initiatior);
        KraDocumentXMLMaterializer xmlWrapper=new KraDocumentXMLMaterializer(); 
        //KualiDocumentXmlMaterializer xmlWrapper = new KualiDocumentXmlMaterializer(); 
        xmlWrapper.setDocument(this); 
        xmlWrapper.setKualiTransactionalDocumentInformation(transInfo); 
        xmlWrapper.setRolepersons(proposalauthservice.getAllRolePersons(this)); 
        return xmlWrapper; 

    } 
  
    public boolean isNih() {
        return nih;
    }

    public void setNih(boolean nih) {
        this.nih = nih;
    }

    public HashMap getNihDescription() {
        return nihDescription;
    }

    public void setNihDescription(HashMap nihDescription) {
        this.nihDescription = nihDescription;
       
    }

    public List<S2sAppSubmission> getS2sAppSubmission() {
        return s2sAppSubmission;
    }
   
    public void setS2sAppSubmission(List<S2sAppSubmission> appSubmission) {
        s2sAppSubmission = appSubmission;
    }

    public List<S2sSubmissionHistory> getS2sSubmissionHistory() {
        return s2sSubmissionHistory;
    }

    public void setS2sSubmissionHistory(List<S2sSubmissionHistory> submissionHistory) {
        s2sSubmissionHistory = submissionHistory;
    }

    public List<ProposalChangedData> getProposalChangedDataList() {
        return proposalChangedDataList;
    }

    public void setProposalChangedDataList(List<ProposalChangedData> proposalChangedDataList) {
        this.proposalChangedDataList = proposalChangedDataList;
    }

    public Map<String, List<ProposalChangedData>> getProposalChangeHistory() {
        return proposalChangeHistory;
    }

    public void setProposalChangeHistory(Map<String, List<ProposalChangedData>> proposalChangeHistory) {
        this.proposalChangeHistory = proposalChangeHistory;
    }

    public void setSubmitFlag(Boolean submitFlag) {
        this.submitFlag = submitFlag;
    }
    
    public Boolean getSubmitFlag() {
        return this.submitFlag;
    }

    public String getProposalStateTypeCode() {
        return proposalStateTypeCode;
    }

    public void setProposalStateTypeCode(String proposalStateTypeCode) {
        this.proposalStateTypeCode = proposalStateTypeCode;
        if (proposalStateTypeCode == null) {
            this.proposalState = null;
        } else {
            try {
                refreshReferenceObject("proposalState");
            } catch (NullPointerException ex) {
                // do nothing: this will happen when
                // creating a proposal from a unit test
                // without access to a database.
            }
        }
    }

    public ProposalState getProposalState() {
        return proposalState;
    }
    
    @Override
    public void handleRouteStatusChange() {
        super.handleRouteStatusChange();
        
        ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
        setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(this, true));
    }

    public Integer getPostSubmissionStatusCode() {
        return postSubmissionStatusCode;
    }

    public void setPostSubmissionStatusCode(Integer postSubmissionStatusCode) {
        this.postSubmissionStatusCode = postSubmissionStatusCode;
    }

    public List<ProposalNarrative> getNarratives() {
        return narratives;
    }

    public void setNarratives(List<ProposalNarrative> narratives) {
        this.narratives = narratives;
    }

    
}

