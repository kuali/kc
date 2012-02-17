/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.UnitAclLoadable;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipType;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentFilter;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentStatus;
import org.kuali.kra.irb.noteattachment.ProtocolNotepad;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolPersonnelService;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption;
import org.kuali.kra.irb.summary.AdditionalInfoSummary;
import org.kuali.kra.irb.summary.AttachmentSummary;
import org.kuali.kra.irb.summary.FundingSourceSummary;
import org.kuali.kra.irb.summary.OrganizationSummary;
import org.kuali.kra.irb.summary.ParticipantSummary;
import org.kuali.kra.irb.summary.PersonnelSummary;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.irb.summary.ResearchAreaSummary;
import org.kuali.kra.irb.summary.SpecialReviewSummary;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * 
 * This class is Protocol Business Object.
 */
public class Protocol extends KraPersistableBusinessObjectBase implements SequenceOwner<Protocol>, 
                                                                          Permissionable,
                                                                          UnitAclLoadable,
                                                                          Disclosurable {

    private static final long serialVersionUID = 4396393806439396971L;
    
    private static final CharSequence AMENDMENT_LETTER = "A";
    private static final CharSequence RENEWAL_LETTER = "R";
    private static final String DEFAULT_PROTOCOL_TYPE_CODE = "1";
    private static final String NEXT_ACTION_ID_KEY = "actionId";
    
    private Long protocolId; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private boolean active = true;
    private String protocolTypeCode; 
    private String protocolStatusCode; 
    private String title; 
    private String description;
    private Date initialSubmissionDate;
    private Date approvalDate; 
    private Date expirationDate; 
    private Date lastApprovalDate; 
    private String fdaApplicationNumber; 
    private String referenceNumber1; 
    private String referenceNumber2; 
    private String specialReviewIndicator = "Y"; 
    private String vulnerableSubjectIndicator; 
    private String keyStudyPersonIndicator; 
    private String fundingSourceIndicator; 
    private String correspondentIndicator; 
    private String referenceIndicator; 
    private String relatedProjectsIndicator; 
    private ProtocolDocument protocolDocument;
    
    private ProtocolStatus protocolStatus; 
    private ProtocolType protocolType; 

    private List<ProtocolRiskLevel> protocolRiskLevels;
    private List<ProtocolParticipant> protocolParticipants;
    
    private List<ProtocolResearchArea> protocolResearchAreas;
    
    private List<ProtocolReference> protocolReferences;
    private List<ProtocolLocation> protocolLocations;
        
    //Is transient, used for lookup select option in UI by KNS 
    private String newDescription;
    
    private boolean nonEmployeeFlag;
    
    private List<ProtocolFundingSource> protocolFundingSources; 

    private String leadUnitNumber;
    private String principalInvestigatorId;
    
    // lookup field
    private String keyPerson;
    private String investigator;
    private String fundingSource;
    
    private String performingOrganizationId;
    private String researchAreaCode;
    private String leadUnitName;

    private List<ProtocolPerson> protocolPersons; 
    
    private List<ProtocolSpecialReview> specialReviews;
    
    //these are the m:m attachment protocols that that a protocol has
    private List<ProtocolAttachmentProtocol> attachmentProtocols;
    
    private List<ProtocolNotepad> notepads;

    private List<ProtocolAction> protocolActions;
    private List<ProtocolSubmission> protocolSubmissions;
  
    private ProtocolSubmission protocolSubmission;
    

    
    /*
     * There should only be zero or one entry in the protocolAmendRenewals
     * list.  It is because of OJB that a list is used instead of a single item.
     */
    private List<ProtocolAmendRenewal> protocolAmendRenewals;
    
    private transient boolean correctionMode = false;
    
    private transient DateTimeService dateTimeService;
    private transient SequenceAccessorService sequenceAccessorService;
    
    //Used to filter protocol attachments
    private transient ProtocolAttachmentFilter protocolAttachmentFilter;    

    // passed in req param submissionid.  used to check if irb ack is needed
    // this link is from protocosubmission or notify irb message
    private transient Long notifyIrbSubmissionId;
    
    // transient for protocol header combined label
    private transient String initiatorLastUpdated;
    private transient String protocolSubmissionStatus;
    
    // Custom Protocol lookup fields
    private transient boolean lookupPendingProtocol;
    private transient boolean lookupPendingPIActionProtocol;
    private transient boolean lookupActionAmendRenewProtocol;
    private transient boolean lookupActionNotifyIRBProtocol;
    private transient boolean lookupActionRequestProtocol;
    private transient boolean lookupProtocolPersonId;
    private transient boolean mergeAmendment;
    
    public String getInitiatorLastUpdated() {
        return initiatorLastUpdated;
    }

    public String getProtocolSubmissionStatus() {
        return protocolSubmissionStatus;
    }


    /**
     * 
     * Constructs an Protocol BO.
     */
    public Protocol() {
        super();
        sequenceNumber = new Integer(0);
        //billable = false;
        protocolRiskLevels = new ArrayList<ProtocolRiskLevel>();
        protocolParticipants = new ArrayList<ProtocolParticipant>();
        protocolResearchAreas = new ArrayList<ProtocolResearchArea>();
        protocolReferences = new ArrayList<ProtocolReference>(); 
        newDescription = getDefaultNewDescription();
        protocolStatus = new ProtocolStatus();
        protocolStatusCode = protocolStatus.getProtocolStatusCode();
        protocolLocations = new ArrayList<ProtocolLocation>(); 
        protocolPersons = new ArrayList<ProtocolPerson>();
        // set the default protocol type
        protocolTypeCode = DEFAULT_PROTOCOL_TYPE_CODE;
//        initializeProtocolLocation();
        protocolFundingSources = new ArrayList<ProtocolFundingSource>();        
        specialReviews = new ArrayList<ProtocolSpecialReview>();
        setProtocolActions(new ArrayList<ProtocolAction>());
        setProtocolSubmissions(new ArrayList<ProtocolSubmission>());
        protocolAmendRenewals = new ArrayList<ProtocolAmendRenewal>();
        // set statuscode default
        setProtocolStatusCode(Constants.DEFAULT_PROTOCOL_STATUS_CODE);
        this.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        initializeProtocolAttachmentFilter();
        
        // TODO : not sure why this method is here.  It looks like a temp method.  commented out to see if it is ok.
        // I had to remove the comment in front of the following statement. 
        // By adding the comment, a null pointer exception occurs when navigating to the Protocol Actions tab.
        //populateTempViewDate();

    }
    
    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getProtocolStatusCode() {
        return protocolStatusCode;
    }

    public void setProtocolStatusCode(String protocolStatusCode) {
        this.protocolStatusCode = protocolStatusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the submission date.  If the submission date is the last
     * submission for the protocol.  If the protocol has not been submitted,
     * null is returned.
     * @return the submission date or null if not yet submitted
     */
    public Date getSubmissionDate() {
        // TODO : the last one in the list may not be the last one submitted
        // getProtocolSubmission will get the last one.  SO, this method may not needed.
        // Also, this method only referenced in test once.
        Date submissionDate = null;
        if (protocolSubmissions.size() > 0) {
//            ProtocolSubmission submission = protocolSubmissions.get(protocolSubmissions.size() - 1);
//            submissionDate = submission.getSubmissionDate();
            submissionDate = getProtocolSubmission().getSubmissionDate();
        }
        return submissionDate;
    }
    
    public Date getInitialSubmissionDate() {
        return initialSubmissionDate;
    }
    
    public void setInitialSubmissionDate(Date initialSubmissionDate) {
        this.initialSubmissionDate = initialSubmissionDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public String getFdaApplicationNumber() {
        return fdaApplicationNumber;
    }

    public void setFdaApplicationNumber(String fdaApplicationNumber) {
        this.fdaApplicationNumber = fdaApplicationNumber;
    }

    public String getReferenceNumber1() {
        return referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getReferenceNumber2() {
        return referenceNumber2;
    }

    public void setReferenceNumber2(String referenceNumber2) {
        this.referenceNumber2 = referenceNumber2;
    }

    public String getSpecialReviewIndicator() {
        return specialReviewIndicator;
    }

    public void setSpecialReviewIndicator(String specialReviewIndicator) {
        this.specialReviewIndicator = specialReviewIndicator;
    }

    public String getVulnerableSubjectIndicator() {
        return vulnerableSubjectIndicator;
    }

    public void setVulnerableSubjectIndicator(String vulnerableSubjectIndicator) {
        this.vulnerableSubjectIndicator = vulnerableSubjectIndicator;
    }

    public String getKeyStudyPersonIndicator() {
        return keyStudyPersonIndicator;
    }

    public void setKeyStudyPersonIndicator(String keyStudyPersonIndicator) {
        this.keyStudyPersonIndicator = keyStudyPersonIndicator;
    }

    public String getFundingSourceIndicator() {
        return fundingSourceIndicator;
    }

    public void setFundingSourceIndicator(String fundingSourceIndicator) {
        this.fundingSourceIndicator = fundingSourceIndicator;
    }

    public String getCorrespondentIndicator() {
        return correspondentIndicator;
    }

    public void setCorrespondentIndicator(String correspondentIndicator) {
        this.correspondentIndicator = correspondentIndicator;
    }

    public String getReferenceIndicator() {
        return referenceIndicator;
    }

    public void setReferenceIndicator(String referenceIndicator) {
        this.referenceIndicator = referenceIndicator;
    }

    public String getRelatedProjectsIndicator() {
        return relatedProjectsIndicator;
    }

    public void setRelatedProjectsIndicator(String relatedProjectsIndicator) {
        this.relatedProjectsIndicator = relatedProjectsIndicator;
    }

    
    /**
     * Collects and returns all online reviews for all submissions for this protocol.
     * @return all online reviews for this protocol
     */
    public List<ProtocolOnlineReview> getProtocolOnlineReviews() {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();

        for (ProtocolSubmission submission : getProtocolSubmissions()) {
            reviews.addAll(submission.getProtocolOnlineReviews());
        }
        
        return reviews;
    }

    public ProtocolStatus getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(ProtocolStatus protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public List<ProtocolRiskLevel> getProtocolRiskLevels() {
        return protocolRiskLevels;
    }

    public void setProtocolRiskLevels(List<ProtocolRiskLevel> protocolRiskLevels) {
        this.protocolRiskLevels = protocolRiskLevels;
        for (ProtocolRiskLevel riskLevel : protocolRiskLevels) {
            riskLevel.init(this);
        }
    }
    
    public List<ProtocolParticipant> getProtocolParticipants() {
        return protocolParticipants;
    }

    public void setProtocolParticipants(List<ProtocolParticipant> protocolParticipants) {
        this.protocolParticipants = protocolParticipants;
        for (ProtocolParticipant participant : protocolParticipants) {
            participant.init(this);
        }
    }
    
    /**
     * Gets index i from the protocol participant list.
     * 
     * @param index
     * @return protocol participant at index i
     */
    public ProtocolParticipant getProtocolParticipant(int index) {
        return getProtocolParticipants().get(index);
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
    public void setProtocolResearchAreas(List<ProtocolResearchArea> protocolResearchAreas) {
        this.protocolResearchAreas = protocolResearchAreas;
        for (ProtocolResearchArea researchArea : protocolResearchAreas) {
            researchArea.init(this);
        }
    }

    public List<ProtocolResearchArea> getProtocolResearchAreas() {
        return protocolResearchAreas;
    }
    
    public void addProtocolResearchAreas(ProtocolResearchArea protocolResearchArea) {
        getProtocolResearchAreas().add(protocolResearchArea);
    }

    public ProtocolResearchArea getProtocolResearchAreas(int index) {
        while (getProtocolResearchAreas().size() <= index) {
            getProtocolResearchAreas().add(new ProtocolResearchArea());
        }
        return getProtocolResearchAreas().get(index);
    }

    public void setProtocolReferences(List<ProtocolReference> protocolReferences) {
        this.protocolReferences = protocolReferences;
        for (ProtocolReference reference : protocolReferences) {
            reference.init(this);
        }
    }

    public List<ProtocolReference> getProtocolReferences() {
        return protocolReferences;
    }

    public ProtocolDocument getProtocolDocument() {
        return protocolDocument;
    }

    public void setProtocolDocument(ProtocolDocument protocolDocument) {
        this.protocolDocument = protocolDocument;
    }

    public void setProtocolLocations(List<ProtocolLocation> protocolLocations) {
        this.protocolLocations = protocolLocations;
        for (ProtocolLocation location : protocolLocations) {
            location.init(this);
        }
    }

    public List<ProtocolLocation> getProtocolLocations() {
        return protocolLocations;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(this.protocolResearchAreas);
        managedLists.add(this.protocolReferences);
        managedLists.add(getProtocolFundingSources());
        managedLists.add(getProtocolLocations());
        managedLists.add(getProtocolRiskLevels());
        managedLists.add(getProtocolParticipants());
        managedLists.add(getProtocolAttachmentPersonnel());
        managedLists.add(getProtocolUnits());
        managedLists.add(getAttachmentProtocols());
        managedLists.add(getProtocolPersons());
        managedLists.add(getProtocolActions());
        managedLists.add(getProtocolSubmissions());
        if (getProtocolAmendRenewal() != null) {
            managedLists.add(getProtocolAmendRenewal().getModules());
        } else {
            // needed to ensure that the OjbCollectionHelper receives constant list size. 
            managedLists.add(new ArrayList<ProtocolAmendRenewModule>());
        }
        managedLists.add(getProtocolAmendRenewals());
        
        List<ProtocolSpecialReviewExemption> specialReviewExemptions = new ArrayList<ProtocolSpecialReviewExemption>();
        for (ProtocolSpecialReview specialReview : getSpecialReviews()) {
            specialReviewExemptions.addAll(specialReview.getSpecialReviewExemptions());
        }
        managedLists.add(specialReviewExemptions);
        managedLists.add(getSpecialReviews());
        managedLists.add(getNotepads());
        
        return managedLists;
    }
    
    /**
     * This method is to return all attachments for each person.
     * Purpose of this method is to use the list in buildListOfDeletionAwareLists.
     * Looks like OJB is not searching beyond the first level. It doesn't delete
     * from collection under ProtocolPerson.
     * @return List<ProtocolAttachmentPersonnel>
     */
    private List<ProtocolAttachmentPersonnel> getProtocolAttachmentPersonnel() {
        List<ProtocolAttachmentPersonnel> protocolAttachmentPersonnel = new ArrayList<ProtocolAttachmentPersonnel>();
        for (ProtocolPerson protocolPerson : getProtocolPersons()) {
            protocolAttachmentPersonnel.addAll(protocolPerson.getAttachmentPersonnels());
        }
        return protocolAttachmentPersonnel;
    }

    /**
     * This method is to return all protocol units for each person.
     * Purpose of this method is to use the list in buildListOfDeletionAwareLists.
     * Looks like OJB is not searching beyond the first level. It doesn't delete
     * from collection under ProtocolPerson.
     * @return List<ProtocolUnit>
     */
    private List<ProtocolUnit> getProtocolUnits() {
        List<ProtocolUnit> protocolUnits = new ArrayList<ProtocolUnit>();
        for (ProtocolPerson protocolPerson : getProtocolPersons()) {
            protocolUnits.addAll(protocolPerson.getProtocolUnits());
        }
        return protocolUnits;
    }

    /**
     * This method is to find Principal Investigator from ProtocolPerson list
     * @return ProtocolPerson
     */
    public ProtocolPerson getPrincipalInvestigator() {
        return getProtocolPersonnelService().getPrincipalInvestigator(getProtocolPersons());
    }

    public String getPrincipalInvestigatorName() {
        ProtocolPerson pi = getPrincipalInvestigator();
        return pi != null ? pi.getFullName() : null;
    }

    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        if (getPrincipalInvestigator() != null) {
            for ( ProtocolUnit unit : getPrincipalInvestigator().getProtocolUnits() ) {
                if (unit.getLeadUnitFlag()) {
                    leadUnit = unit;
                }
            }
        }
        return leadUnit;
    }    
    
    public String getLeadUnitNumber() {
        if (StringUtils.isBlank(leadUnitNumber)) {
            if (getLeadUnit() != null) {
                setLeadUnitNumber(getLeadUnit().getUnitNumber());
            }
        }
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber; 
    }

    public String getPrincipalInvestigatorId() {       
        if (StringUtils.isBlank(principalInvestigatorId)) {
            if (getPrincipalInvestigator() != null) {
                ProtocolPerson principalInvestigator = getPrincipalInvestigator();
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    setPrincipalInvestigatorId(principalInvestigator.getPersonId());
                } else {
                    //ProtocolUnit leadUnit = new ProtocolUnit();
                    //leadUnit.setUnitNumber(leadUnitNumber);
                    //leadUnit.setPersonId(getPrincipalInvestigator().getPersonId());  
                    //leadUnit.setLeadUnitFlag(true); 
                    // TODO : rice upgrade temp fix
                    //leadUnit.setProtocolNumber("0");
                    //leadUnit.setSequenceNumber(0);
                    //getPrincipalInvestigator().getProtocolUnits().add(leadUnit);
                    if (principalInvestigator.getRolodexId() != null) {
                        setPrincipalInvestigatorId(principalInvestigator.getRolodexId().toString());
                    }
                }
            }
        }
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }


    public boolean isNonEmployeeFlag() {
        return this.nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;   
    }

    /**
     * This method is to get protocol location service
     * @return ProtocolLocationService
     */
    protected ProtocolLocationService getProtocolLocationService() {
        ProtocolLocationService protocolLocationService = (ProtocolLocationService)KraServiceLocator.getService("protocolLocationService");
        return protocolLocationService;
    }

    /*
     * Initialize protocol location.
     * Add default organization.
     */
    private void initializeProtocolLocation() {
        getProtocolLocationService().addDefaultProtocolLocation(this);
    }
    
    public List<ProtocolPerson> getProtocolPersons() {
        return protocolPersons;
    }

    public void setProtocolPersons(List<ProtocolPerson> protocolPersons) {
        this.protocolPersons = protocolPersons;
        for (ProtocolPerson person : protocolPersons) {
            person.init(this);
        }
    }

    /**
     * Gets index i from the protocol person list.
     * 
     * @param index
     * @return protocol person at index i
     */
    public ProtocolPerson getProtocolPerson(int index) {
        return getProtocolPersons().get(index);
    }

    /**
     * This method is to get protocol personnel service
     * @return protocolPersonnelService
     */
    private ProtocolPersonnelService getProtocolPersonnelService() {
        ProtocolPersonnelService protocolPersonnelService = (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
        return protocolPersonnelService;
    }

    public List<ProtocolFundingSource> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSource> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
        for (ProtocolFundingSource fundingSource : protocolFundingSources) {
            fundingSource.init(this);
        }
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public String getFundingSource() {
        return fundingSource;
    }

    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public String getPerformingOrganizationId() {
        return performingOrganizationId;
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        this.performingOrganizationId = performingOrganizationId;
    }

   public String getLeadUnitName() {
        if (StringUtils.isBlank(leadUnitName)) {
            if (getLeadUnit() != null) {
                setLeadUnitName(getLeadUnit().getUnitName());
            }
        }
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }

    public void setSpecialReviews(List<ProtocolSpecialReview> specialReviews) {
        this.specialReviews = specialReviews;
    }
    
    /**
     * @see org.kuali.kra.document.SpecialReviewHandler#addSpecialReview(org.kuali.kra.bo.AbstractSpecialReview)
     */
    public void addSpecialReview(ProtocolSpecialReview specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);
    }

    /**
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReview(int)
     */
    public ProtocolSpecialReview getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    /**
     * @see org.kuali.kra.document.SpecialReviewHandler#getSpecialReviews()
     */
    public List<ProtocolSpecialReview> getSpecialReviews() {
        return specialReviews;
    }
    
    /**
     * Gets the attachment protocols. Cannot return {@code null}.
     * @return the attachment protocols
     */
    public List<ProtocolAttachmentProtocol> getAttachmentProtocols() {
        
        if (this.attachmentProtocols == null) {
            this.attachmentProtocols = new ArrayList<ProtocolAttachmentProtocol>();
        }
        return this.attachmentProtocols;
    }
    
    /**
     * Gets an attachment protocol.
     * @param index the index
     * @return an attachment protocol
     */
    public ProtocolAttachmentProtocol getAttachmentProtocol(int index) {
        return this.attachmentProtocols.get(index);
    }
    
    /**
     * Gets the notepads. Cannot return {@code null}.
     * @return the notepads
     */
    public List<ProtocolNotepad> getNotepads() {
        
        if (this.notepads == null) {
            this.notepads = new ArrayList<ProtocolNotepad>();
        }
        Collections.sort(notepads, Collections.reverseOrder());
        return this.notepads;
    }
    
    /**
     * Gets an attachment protocol.
     * @param index the index
     * @return an attachment protocol
     */
    public ProtocolNotepad getNotepad(int index) {
        return this.notepads.get(index);
    }
    
    /**
     * adds an attachment protocol.
     * @param attachmentProtocol the attachment protocol
     * @throws IllegalArgumentException if attachmentProtocol is null
     */
    private void addAttachmentProtocol(ProtocolAttachmentProtocol attachmentProtocol) {
        ProtocolAttachmentBase.addAttachmentToCollection(attachmentProtocol, this.getAttachmentProtocols());
    }
    
    /**
     * removes an attachment protocol.
     * @param attachmentProtocol the attachment protocol
     * @throws IllegalArgumentException if attachmentProtocol is null
     */
    private void removeAttachmentProtocol(ProtocolAttachmentProtocol attachmentProtocol) {
        ProtocolAttachmentBase.removeAttachmentFromCollection(attachmentProtocol, this.getAttachmentProtocols());
    }

    /**
     * @deprecated
     * Gets the attachment personnels. Cannot return {@code null}.
     * @return the attachment personnels
     */
    @Deprecated
    public List<ProtocolAttachmentPersonnel> getAttachmentPersonnels() {
        return getProtocolAttachmentPersonnel();
    }
    
    private void updateUserFields(KraPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
    }
    
    /**
     * Adds a attachment to a Protocol where the type of attachment is used to determine
     * where to add the attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null or if an unsupported attachment is found
     */
    public <T extends ProtocolAttachmentBase> void addAttachmentsByType(T attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        updateUserFields(attachment);
        attachment.setProtocolId(getProtocolId());
        if (attachment instanceof ProtocolAttachmentProtocol) {
            this.addAttachmentProtocol((ProtocolAttachmentProtocol) attachment);
        } else {
            throw new IllegalArgumentException("unsupported type: " + attachment.getClass().getName());
        }
    }
    
    /**
     * removes an attachment to a Protocol where the type of attachment is used to determine
     * where to add the attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null or if an unsupported attachment is found
     */
    public <T extends ProtocolAttachmentBase> void removeAttachmentsByType(T attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        if (attachment instanceof ProtocolAttachmentProtocol) {
            this.removeAttachmentProtocol((ProtocolAttachmentProtocol) attachment);
        } else {
            throw new IllegalArgumentException("unsupported type: " + attachment.getClass().getName());
        }
    }

    public String getKeyPerson() {
        return keyPerson;
    }

    public void setKeyPerson(String keyPerson) {
        this.keyPerson = keyPerson;
    }

    public String getInvestigator() {
        if (StringUtils.isBlank(principalInvestigatorId)) {
            if (getPrincipalInvestigator() != null) {
                investigator = getPrincipalInvestigator().getPersonName();
            }
        }
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }
    
    /* TODO : why this temp method is in Protocol method */
    private void populateTempViewDate() {
        this.protocolSubmission = new ProtocolSubmission();
        
        Committee committee = new Committee();
        committee.setId(1L);
        committee.setCommitteeId("Comm-1");
        committee.setCommitteeName("Test By Kiltesh");
        this.protocolSubmission.setCommittee(committee);
        
        this.protocolSubmission.setSubmissionNumber(1);
        this.protocolSubmission.setSubmissionDate(new Date(System.currentTimeMillis()));
        this.protocolSubmission.setSubmissionStatusCode("100");
        
        ProtocolSubmissionType submissionType = new ProtocolSubmissionType();
        submissionType.setDescription("Initial Protocol Application for Approval ");
        this.protocolSubmission.setProtocolSubmissionType(submissionType);
        
        ProtocolReviewType review = new ProtocolReviewType();
        review.setDescription("Full");
        this.protocolSubmission.setProtocolReviewType(review);
        
        ProtocolSubmissionQualifierType qual = new ProtocolSubmissionQualifierType();
        qual.setDescription("Lorem Ipsum Dolor ");
        this.protocolSubmission.setProtocolSubmissionQualifierType(qual);
        
        ProtocolSubmissionStatus substatus = new ProtocolSubmissionStatus();
        substatus.setDescription("Approved");
        this.protocolSubmission.setSubmissionStatus(substatus);
        
        CommitteeMembershipType committeeMembershipType1 = new CommitteeMembershipType();
        committeeMembershipType1.setDescription("Primary");
        CommitteeMembershipType committeeMembershipType2 = new CommitteeMembershipType();
        committeeMembershipType2.setDescription("Secondary");
        
        CommitteeMembership committeeMembership1 = new CommitteeMembership();
        committeeMembership1.setPersonName("Bryan Hutchinson");
        committeeMembership1.setMembershipType(committeeMembershipType1);
        CommitteeMembership committeeMembership2 = new CommitteeMembership();
        committeeMembership2.setPersonName("Kiltesh Patel");
        committeeMembership2.setMembershipType(committeeMembershipType2);
        
        List<CommitteeMembership>  list = new ArrayList<CommitteeMembership>();
        list.add(committeeMembership1);
        list.add(committeeMembership2);
        
        committee.setCommitteeMemberships(list);
        
        this.protocolSubmission.setYesVoteCount(2);
        this.protocolSubmission.setNoVoteCount(3);
        this.protocolSubmission.setAbstainerCount(1);
        this.protocolSubmission.setVotingComments("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
        		"Suspendisse purus. Nullam et justo. In volutpat odio sit amet pede. Pellentesque ipsum dui, convallis in, mollis a, lacinia vel, diam. " +
        		"Phasellus molestie neque at sapien condimentum massa nunc" +
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Suspendisse purus. Nullam et justo. In volutpat odio sit amet pede. Pellentesque ipsum dui, convallis in, mollis a, lacinia vel, diam. " +
                "Phasellus molestie neque at sapien condimentum massa nunc");
        
        CommitteeSchedule committeeSchedule= new CommitteeSchedule();        
        CommitteeScheduleAttendance committeeScheduleAttendance = new CommitteeScheduleAttendance();        
        List<CommitteeScheduleAttendance> cslist = new ArrayList<CommitteeScheduleAttendance>();
        cslist.add(committeeScheduleAttendance);
        committeeSchedule.setCommitteeScheduleAttendances(cslist);        
        this.protocolSubmission.setCommitteeSchedule(committeeSchedule);
    }
    
    public ProtocolSubmission getProtocolSubmission() {
        // TODO : this is a fix to remove 'populateTempViewDate'

        // if (protocolSubmission == null) {
        if (!protocolSubmissions.isEmpty()) {
            // sorted by ojb
            if (protocolSubmission == null
                    || protocolSubmission.getSubmissionNumber() == null
                    || protocolSubmissions.get(protocolSubmissions.size() - 1).getSubmissionNumber() > protocolSubmission
                            .getSubmissionNumber()) {
                protocolSubmission = protocolSubmissions.get(protocolSubmissions.size() - 1);
            }
            // for (ProtocolSubmission submission : protocolSubmissions) {
            // if (protocolSubmission == null || protocolSubmission.getSubmissionNumber() == null
            // || submission.getSubmissionNumber() > protocolSubmission.getSubmissionNumber()) {
            // protocolSubmission = submission;
            // }
            // }

        }
        else {
            protocolSubmission = new ProtocolSubmission();
            // TODO : the update protocol rule may not like null
            protocolSubmission.setProtocolSubmissionType(new ProtocolSubmissionType());
            protocolSubmission.setSubmissionStatus(new ProtocolSubmissionStatus());
        }
        // }
        refreshReferenceObject(protocolSubmission);
        return protocolSubmission;
    }

    
    private void refreshReferenceObject(ProtocolSubmission submission) {
        // if submission just added, then these probably are empty
        if (StringUtils.isNotBlank(submission.getProtocolReviewTypeCode()) 
                &&  submission.getProtocolReviewType() == null) {
            submission.refreshReferenceObject("protocolReviewType");
        }
        if (StringUtils.isNotBlank(submission.getSubmissionStatusCode()) 
                &&  submission.getSubmissionStatus() == null) {
            submission.refreshReferenceObject("submissionStatus");
        }
        if (StringUtils.isNotBlank(submission.getSubmissionTypeCode()) 
                &&  submission.getProtocolSubmissionType() == null) {
            submission.refreshReferenceObject("protocolSubmissionType");
        }
        if (StringUtils.isNotBlank(submission.getSubmissionTypeQualifierCode()) 
                &&  submission.getProtocolSubmissionQualifierType() == null) {
            submission.refreshReferenceObject("protocolSubmissionQualifierType");
        }
        
    }
    
    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public void setProtocolActions(List<ProtocolAction> protocolActions) {
        this.protocolActions = protocolActions;
    }

    public List<ProtocolAction> getProtocolActions() {
        return protocolActions;
    }
    
    public ProtocolAction getLastProtocolAction() {
        if (protocolActions.size() == 0) {
            return null;
        }
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
        return protocolActions.get(0);
    }

    public void setProtocolSubmissions(List<ProtocolSubmission> protocolSubmissions) {
        this.protocolSubmissions = protocolSubmissions;
    }

    public List<ProtocolSubmission> getProtocolSubmissions() {
        return protocolSubmissions;
    }
    
   /**
    * Get the next value in a sequence.
    * @param key the unique key of the sequence
    * @return the next value
    */
   public Integer getNextValue(String key) {
       return protocolDocument.getDocumentNextValue(key);
   }

    public void setAttachmentProtocols(List<ProtocolAttachmentProtocol> attachmentProtocols) {
        this.attachmentProtocols = attachmentProtocols;
        for (ProtocolAttachmentProtocol attachment : attachmentProtocols) {
            attachment.resetPersistenceState();
            attachment.setSequenceNumber(0);
        }
    }
    
    public void setNotepads(List<ProtocolNotepad> notepads) {
        this.notepads = notepads;
    }
    
    public void setProtocolAmendRenewal(ProtocolAmendRenewal amendRenewal) {
        protocolAmendRenewals.add(amendRenewal);
    }
    
    public ProtocolAmendRenewal getProtocolAmendRenewal() {
        if (protocolAmendRenewals.size() == 0) return null;
        return protocolAmendRenewals.get(0);
    }

    public List<ProtocolAmendRenewal> getProtocolAmendRenewals() {
        return protocolAmendRenewals;
    }

    public void setProtocolAmendRenewals(List<ProtocolAmendRenewal> protocolAmendRenewals) {
        this.protocolAmendRenewals = protocolAmendRenewals;
    }
    
    /** {@inheritDoc} */
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    /**
     * @see org.kuali.kra.SequenceOwner#getVersionNameField()
     */
    public String getVersionNameField() {
        return "protocolNumber";
    }

    /** {@inheritDoc} */
    public void incrementSequenceNumber() {
        this.sequenceNumber++; 
    }

    /** {@inheritDoc} */
    public Protocol getSequenceOwner() {
        return this;
    }

    /** {@inheritDoc} */
    public void setSequenceOwner(Protocol newOwner) {
       //no-op
    }
    
    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.protocolId = null;
    }
    
    /**
     * 
     * This method merges the data of the amended protocol into this protocol.
     * @param amendment
     */
    public void merge(Protocol amendment) {
        merge(amendment, true);
    }
    
    public void merge(Protocol amendment, boolean mergeActions) {
        List<ProtocolAmendRenewModule> modules = amendment.getProtocolAmendRenewal().getModules();
        for (ProtocolAmendRenewModule module : modules) {
            merge(amendment, module.getProtocolModuleTypeCode());
        }
        if (amendment.isRenewalWithoutAmendment() && isRenewalWithNewAttachment(amendment)) {
            merge(amendment, ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        }
        mergeProtocolSubmission(amendment);
        if (mergeActions) {
            mergeProtocolAction(amendment);
        }
    }

    private boolean isRenewalWithNewAttachment(Protocol renewal) {
        boolean hasNewAttachment = false;
        for (ProtocolAttachmentProtocol attachment : renewal.getAttachmentProtocols()) {
            if (attachment.isDraft()) {
                hasNewAttachment = true;
                break;
            }
        }
        return hasNewAttachment;
    }
    
    /**
     * 
     * This method merges the data of a specific module of the amended protocol into this protocol.
     * @param amendment
     * @param protocolModuleTypeCode
     */
    public void merge(Protocol amendment, String protocolModuleTypeCode) {
        if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.GENERAL_INFO)) {
            mergeGeneralInfo(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.AREAS_OF_RESEARCH)) {
            mergeResearchAreas(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.FUNDING_SOURCE)) {
            mergeFundingSources(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_ORGANIZATIONS)) {
            mergeOrganizations(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_PERSONNEL)) {
            mergePersonnel(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.ADD_MODIFY_ATTACHMENTS)) {
            if (amendment.isAmendment() || amendment.isRenewal()
                    || (!amendment.getAttachmentProtocols().isEmpty() && this.getAttachmentProtocols().isEmpty())) {
                mergeAttachments(amendment);
            }
            else {
                restoreAttachments(this);
            }
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_REFERENCES)) {
            mergeReferences(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.SPECIAL_REVIEW)) {
            mergeSpecialReview(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.SUBJECTS)) {
            mergeSubjects(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.OTHERS)) {
            mergeOthers(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.PROTOCOL_PERMISSIONS)) {
            mergeProtocolPermissions(amendment);
        }
        else if (StringUtils.equals(protocolModuleTypeCode, ProtocolModule.QUESTIONNAIRE)) {
            mergeProtocolQuestionnaire(amendment);
        }
    }

    private void mergeProtocolQuestionnaire(Protocol amendment) {
        // TODO : what if user did not edit questionnaire at all, then questionnaire will be wiped out ?
        removeOldQuestionnaire();
        amendQuestionnaire(amendment);
    }

    /*
     * remove existing questionnaire from current 
     */
    private void removeOldQuestionnaire() {

        List <AnswerHeader> answerHeaders = getAnswerHeaderForProtocol(this);
        if (!answerHeaders.isEmpty() && answerHeaders.get(0).getAnswerHeaderId() != null){
            getBusinessObjectService().delete(answerHeaders);
        }
    }
    
    /*
     * add questionnaire answer from amendment to protocol
     */
    private void amendQuestionnaire(Protocol amendment) {

        List <AnswerHeader> answerHeaders = getAnswerHeaderForProtocol(amendment);
        if (!answerHeaders.isEmpty()){
            for (AnswerHeader answerHeader : answerHeaders) {
                for (Answer answer : answerHeader.getAnswers()) {
                    answer.setAnswerHeaderIdFk(null);
                    answer.setId(null);
                }
                answerHeader.setAnswerHeaderId(null);
                answerHeader.setModuleItemKey(this.getProtocolNumber());
                answerHeader.setModuleSubItemKey(this.getSequenceNumber().toString());
            }
            getBusinessObjectService().save(answerHeaders);
        }
 
    }
    
    /*
     * get submit for review questionnaire answerheaders
     */
    private List <AnswerHeader> getAnswerHeaderForProtocol(Protocol protocol) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(protocol);
        moduleQuestionnaireBean.setModuleSubItemCode("0");
        List <AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
        return answerHeaders;
    }
    
    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    @SuppressWarnings("unchecked")
    private void mergeProtocolSubmission(Protocol amendment) {
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>) deepCopy(amendment.getProtocolSubmissions());  
        for (ProtocolSubmission submission : submissions) {
            submission.setProtocolNumber(this.getProtocolNumber());
            submission.setSubmissionId(null);
            submission.setSequenceNumber(sequenceNumber);
            submission.setProtocolId(this.getProtocolId());
          //  submission.setSubmissionNumber(this.getProtocolSubmissions().size() + 1);
            this.getProtocolSubmissions().add(submission);
            //how about meting data
            // online review data
        }
    }
    
    /*
     * merge amendment/renewal protocol action to original protocol when A/R is approved
     */
    @SuppressWarnings("unchecked")
    private void mergeProtocolAction(Protocol amendment) {
        List<ProtocolAction> protocolActions = (List<ProtocolAction>) deepCopy(amendment.getProtocolActions());  
        Collections.sort(protocolActions, new Comparator<ProtocolAction>() {
            public int compare(ProtocolAction action1, ProtocolAction action2) {
                return action1.getActionId().compareTo(action2.getActionId());
            }
        });
        // the first 1 'protocol created is already added to original protocol
        // the last one is 'approve'
        protocolActions.remove(0);
        protocolActions.remove(protocolActions.size() - 1);
        for (ProtocolAction protocolAction : protocolActions) {
            protocolAction.setProtocolNumber(this.getProtocolNumber());
            protocolAction.setProtocolActionId(null);
            protocolAction.setSequenceNumber(sequenceNumber);
            protocolAction.setProtocolId(this.getProtocolId());
            String index = amendment.getProtocolNumber().substring(11);
            protocolAction.setActionId(getNextValue(NEXT_ACTION_ID_KEY));
            String type = "Amendment";
            if (amendment.isRenewal()) {
                type = "Renewal";
            }
            if (StringUtils.isNotBlank(protocolAction.getComments())) {
                protocolAction.setComments(type + "-" + index + ": " + protocolAction.getComments());
            } else {
                protocolAction.setComments(type + "-" + index + ": ");
            }
            this.getProtocolActions().add(protocolAction);
        }
    }
    
    public void mergeGeneralInfo(Protocol amendment) {
        this.protocolTypeCode = amendment.getProtocolTypeCode();
        this.title = amendment.getTitle();
        this.initialSubmissionDate = amendment.getInitialSubmissionDate();
        this.approvalDate = amendment.getApprovalDate(); 
        this.expirationDate = amendment.getExpirationDate(); 
        this.lastApprovalDate = amendment.getLastApprovalDate();
        this.description = amendment.getDescription();
        this.fdaApplicationNumber = amendment.getFdaApplicationNumber();
        //this.billable = amendment.isBillable();
        this.referenceNumber1 = amendment.getReferenceNumber1();
        this.referenceNumber2 = amendment.getReferenceNumber2();
    }
    
    @SuppressWarnings("unchecked")
    private void mergeResearchAreas(Protocol amendment) {
        setProtocolResearchAreas((List<ProtocolResearchArea>) deepCopy(amendment.getProtocolResearchAreas()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergeFundingSources(Protocol amendment) {
        setProtocolFundingSources((List<ProtocolFundingSource>) deepCopy(amendment.getProtocolFundingSources()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergeReferences(Protocol amendment) {
        setProtocolReferences((List<ProtocolReference>) deepCopy(amendment.getProtocolReferences()));
        
        this.fdaApplicationNumber = amendment.getFdaApplicationNumber();
        this.referenceNumber1 = amendment.getReferenceNumber1();
        this.referenceNumber2 = amendment.getReferenceNumber2();
        this.description = amendment.getDescription();
    }
    
    @SuppressWarnings("unchecked")
    private void mergeOrganizations(Protocol amendment) {
        setProtocolLocations((List<ProtocolLocation>) deepCopy(amendment.getProtocolLocations()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergeSubjects(Protocol amendment) {
        setProtocolParticipants((List<ProtocolParticipant>) deepCopy(amendment.getProtocolParticipants()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergeAttachments(Protocol amendment) {
        // TODO : may need to set protocolnumber
        // personnel attachment may have protocol person id issue
        // how about sequence number ?
        // need to change documentstatus to 2 if it is 1
        // what the new protocol's protocol_status should be ?
        List<ProtocolAttachmentProtocol> attachmentProtocols = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment : (List<ProtocolAttachmentProtocol>) deepCopy(amendment.getAttachmentProtocols())) {
            attachment.setProtocolNumber(this.getProtocolNumber());
            attachment.setSequenceNumber(this.getSequenceNumber());
            attachment.setProtocolId(this.getProtocolId());
            attachment.setId(null);
            if (attachment.getFile() != null ) { 
                attachment.getFile().setId(null);
            }
            if (attachment.isDraft()) {
                attachment.setDocumentStatusCode(ProtocolAttachmentStatus.FINALIZED);
                attachmentProtocols.add(attachment);
                attachment.setProtocol(this);
            }
            if (attachment.isDeleted() && KraServiceLocator.getService(ProtocolAttachmentService.class).isNewAttachmentVersion((ProtocolAttachmentProtocol) attachment)) {
                attachmentProtocols.add(attachment);
                attachment.setProtocol(this);
            }
        }
        getAttachmentProtocols().addAll(attachmentProtocols);
        removeDeletedAttachment();
        mergeNotepads(amendment);
    }

    /*
     * the deleted attachment will not be merged
     */
    private void removeDeletedAttachment() {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocol> attachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment : this.getAttachmentProtocols()) {
            attachment.setProtocol(this);
            if (attachment.isDeleted()) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocol attachment : this.getAttachmentProtocols()) {
                attachment.setProtocol(this);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                } 
            }
            this.setAttachmentProtocols(new ArrayList<ProtocolAttachmentProtocol>());
            this.getAttachmentProtocols().addAll(attachments);
        }
    }

    
    /*
     * This is to restore attachments from protocol to amendment when 'attachment' section is unselected.
     * The attachment in amendment may have been modified.
     * delete 'attachment' need to be careful.
     *  - if the 'file' is also used in other 'finalized' attachment, then should remove this file reference from attachment
     *    otherwise, the delete will also delete any attachment that reference to this file
     */
    private void restoreAttachments(Protocol protocol) {
        List<ProtocolAttachmentProtocol> attachmentProtocols = new ArrayList<ProtocolAttachmentProtocol>();
        List<ProtocolAttachmentProtocol> deleteAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        List<AttachmentFile> deleteFiles = new ArrayList<AttachmentFile>();
        
        for (ProtocolAttachmentProtocol attachment : this.getAttachmentProtocols()) {
            if (attachment.isFinal()) {
                attachmentProtocols.add(attachment);
           // } else if (attachment.isDraft()) {
            } else {
                // in amendment, DRAFT & DELETED must be new attachment because DELETED
                // will not be copied from original protocol
                deleteAttachments.add(attachment);
                if (!fileIsReferencedByOther(attachment.getFileId())) {
                    deleteFiles.add(attachment.getFile());
                }
                attachment.setFileId(null);
            }
        }
        if (!deleteAttachments.isEmpty()) {
            getBusinessObjectService().save(deleteAttachments);
            if (!deleteFiles.isEmpty()) {
                getBusinessObjectService().delete(deleteFiles);
            }
            getBusinessObjectService().delete(deleteAttachments);
        }
        this.getAttachmentProtocols().clear();
        this.getAttachmentProtocols().addAll(attachmentProtocols);
        
        mergeNotepads(protocol);
    }

    private boolean fileIsReferencedByOther(Long fileId) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("fileId", fileId.toString());
        return getBusinessObjectService().countMatching(ProtocolAttachmentProtocol.class, fieldValues) > 1;
        
    }
    
    private void mergeNotepads(Protocol amendment) {
        List <ProtocolNotepad> notepads = new ArrayList<ProtocolNotepad>();
        if (amendment.getNotepads() != null) {
            for (ProtocolNotepad notepad : (List<ProtocolNotepad>) deepCopy(amendment.getNotepads())) {
                notepad.setProtocolNumber(this.getProtocolNumber());
                notepad.setSequenceNumber(this.getSequenceNumber());
                notepad.setProtocolId(this.getProtocolId());
                notepad.setId(null);
                notepad.setProtocol(this);
                notepads.add(notepad);
            }
        }
        this.setNotepads(notepads);
    }
    
    private ProtocolPerson findMatchingPerson(ProtocolPerson person) {
        ProtocolPerson matchingPerson = null;
        for (ProtocolPerson newPerson : this.getProtocolPersons()) {
            if (newPerson.getProtocolPersonRoleId().equals(person.getProtocolPersonRoleId())) {
                if ((StringUtils.isNotBlank(newPerson.getPersonId()) && StringUtils.isNotBlank(person.getPersonId())
                    && newPerson.getPersonId().equals(person.getPersonId())) 
                    || (newPerson.getRolodexId() != null && person.getRolodexId() != null
                    && newPerson.getRolodexId().equals(person.getRolodexId()))) {
                    matchingPerson = newPerson;
                    break;
                    }
            }
        }
        return matchingPerson;
    }
    
    @SuppressWarnings("unchecked")
    private void mergeSpecialReview(Protocol amendment) {
        setSpecialReviews((List<ProtocolSpecialReview>) deepCopy(amendment.getSpecialReviews()));
        cleanupSpecialReviews(amendment);
    }
    
    @SuppressWarnings("unchecked")
    private void mergePersonnel(Protocol amendment) {
        setProtocolPersons((List<ProtocolPerson>) deepCopy(amendment.getProtocolPersons()));
        for (ProtocolPerson person : protocolPersons) {
            Integer nextPersonId = getSequenceAccessorService().getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID").intValue();
            person.setProtocolPersonId(nextPersonId);
            for (ProtocolAttachmentPersonnel protocolAttachmentPersonnel : person.getAttachmentPersonnels()) {
                protocolAttachmentPersonnel.setId(null);
                protocolAttachmentPersonnel.setPersonId(person.getProtocolPersonId());
                protocolAttachmentPersonnel.setProtocolId(getProtocolId());
                protocolAttachmentPersonnel.setProtocolNumber(getProtocolNumber());
            }
        }
    }

    private void mergeOthers(Protocol amendment) {
        if (protocolDocument.getCustomAttributeDocuments() == null ||
            protocolDocument.getCustomAttributeDocuments().isEmpty()) {
            protocolDocument.initialize();
        }
        if (amendment.getProtocolDocument().getCustomAttributeDocuments() == null ||
            amendment.getProtocolDocument().getCustomAttributeDocuments().isEmpty()) {
            amendment.getProtocolDocument().initialize();
        }
        for (Entry<String, CustomAttributeDocument> entry : protocolDocument.getCustomAttributeDocuments().entrySet()) {
            CustomAttributeDocument cad = amendment.getProtocolDocument().getCustomAttributeDocuments().get(entry.getKey());
            entry.getValue().getCustomAttribute().setValue(cad.getCustomAttribute().getValue());
        }
    }
    
    private void mergeProtocolPermissions(Protocol amendment) {
        // ToDo: merge permissions
    }
    
    private Object deepCopy(Object obj) {
        return ObjectUtils.deepCopy((Serializable) obj);
    }
    
    public ProtocolSummary getProtocolSummary() {
        ProtocolSummary protocolSummary = createProtocolSummary();
        addPersonnelSummaries(protocolSummary);
        addResearchAreaSummaries(protocolSummary);
        addAttachmentSummaries(protocolSummary);
        addFundingSourceSummaries(protocolSummary);
        addParticipantSummaries(protocolSummary);
        addOrganizationSummaries(protocolSummary);
        addSpecialReviewSummaries(protocolSummary);
        addAdditionalInfoSummary(protocolSummary);
        return protocolSummary;
    }
    
    private void addAdditionalInfoSummary(ProtocolSummary protocolSummary) {
        AdditionalInfoSummary additionalInfoSummary = new AdditionalInfoSummary();
        additionalInfoSummary.setFdaApplicationNumber(this.getFdaApplicationNumber());
        //additionalInfoSummary.setBillable(isBillable());
        additionalInfoSummary.setReferenceId1(this.getReferenceNumber1());
        additionalInfoSummary.setReferenceId2(this.getReferenceNumber2());
        additionalInfoSummary.setDescription(getDescription());
        protocolSummary.setAdditionalInfo(additionalInfoSummary);
    }

    private void addSpecialReviewSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolSpecialReview specialReview : getSpecialReviews()) {
            SpecialReviewSummary specialReviewSummary = new SpecialReviewSummary();
            if (specialReview.getSpecialReviewType() == null) {
                specialReview.refreshReferenceObject("specialReviewType");
            }
            specialReviewSummary.setType(specialReview.getSpecialReviewType().getDescription());
            if (specialReview.getApprovalType() == null) {
                specialReview.refreshReferenceObject("approvalType");
            }
            specialReviewSummary.setApprovalStatus(specialReview.getApprovalType().getDescription());
            specialReviewSummary.setProtocolNumber(specialReview.getProtocolNumber());
            specialReviewSummary.setApplicationDate(specialReview.getApplicationDate());
            specialReviewSummary.setApprovalDate(specialReview.getApprovalDate());
            specialReviewSummary.setExpirationDate(specialReview.getExpirationDate());
            if (specialReview.getSpecialReviewExemptions() == null) {
                specialReview.refreshReferenceObject("specialReviewExemptions");
            }
            specialReviewSummary.setExemptionNumbers(specialReview.getSpecialReviewExemptions());
            specialReviewSummary.setComment(specialReview.getComments());
            protocolSummary.add(specialReviewSummary);
        }
    }

    private void addOrganizationSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolLocation organization : this.getProtocolLocations()) {
             OrganizationSummary organizationSummary = new OrganizationSummary();
             organizationSummary.setId(organization.getOrganizationId());
             organizationSummary.setOrganizationId(organization.getOrganizationId());
             organizationSummary.setName(organization.getOrganization().getOrganizationName());
             organizationSummary.setType(organization.getProtocolOrganizationType().getDescription());
             organizationSummary.setContactId(organization.getRolodexId());
             organizationSummary.setContact(organization.getRolodex());
             organizationSummary.setFwaNumber(organization.getOrganization().getHumanSubAssurance());
             protocolSummary.add(organizationSummary);
        }
    }

    private void addParticipantSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolParticipant participant : this.getProtocolParticipants()) {
            ParticipantSummary participantSummary = new ParticipantSummary();
            participantSummary.setDescription(participant.getParticipantType().getDescription());
            participantSummary.setCount(participant.getParticipantCount());
            protocolSummary.add(participantSummary);
        }
    }

    private void addFundingSourceSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolFundingSource source : getProtocolFundingSources()) {
            FundingSourceSummary fundingSourceSummary = new FundingSourceSummary();
            fundingSourceSummary.setFundingSourceType(source.getFundingSourceType().getDescription());
            fundingSourceSummary.setFundingSource(source.getFundingSourceNumber());
            fundingSourceSummary.setFundingSourceNumber(source.getFundingSourceNumber());
            fundingSourceSummary.setFundingSourceName(source.getFundingSourceName());
            fundingSourceSummary.setFundingSourceTitle(source.getFundingSourceTitle());
            protocolSummary.add(fundingSourceSummary);
        }
    }

    private void addAttachmentSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolAttachmentProtocol attachment : getActiveAttachmentProtocols()) {
            if (!attachment.isDeleted()) {
                AttachmentSummary attachmentSummary = new AttachmentSummary();
                attachmentSummary.setAttachmentId(attachment.getId());
                attachmentSummary.setFileType(attachment.getFile().getType());
                attachmentSummary.setFileName(attachment.getFile().getName());
                attachmentSummary.setAttachmentType("Protocol: " + attachment.getType().getDescription());
                attachmentSummary.setDescription(attachment.getDescription());
                attachmentSummary.setDataLength(attachment.getFile().getData() == null ? 0 : attachment.getFile().getData().length);
                attachmentSummary.setUpdateTimestamp(attachment.getUpdateTimestamp());
                attachmentSummary.setUpdateUser(attachment.getUpdateUser());
                protocolSummary.add(attachmentSummary);
            }
        }
        for (ProtocolPerson person : getProtocolPersons()) {
            for (ProtocolAttachmentPersonnel attachment : person.getAttachmentPersonnels()) {
                AttachmentSummary attachmentSummary = new AttachmentSummary();
                attachmentSummary.setAttachmentId(attachment.getId());
                attachmentSummary.setFileType(attachment.getFile().getType());
                attachmentSummary.setFileName(attachment.getFile().getName());
                attachmentSummary.setAttachmentType(person.getPersonName() + ": " + attachment.getType().getDescription());
                attachmentSummary.setDescription(attachment.getDescription());
                attachmentSummary.setDataLength(attachment.getFile().getData() == null ? 0 : attachment.getFile().getData().length);
                attachmentSummary.setUpdateTimestamp(attachment.getUpdateTimestamp());
                attachmentSummary.setUpdateUser(attachment.getUpdateUser());                
                protocolSummary.add(attachmentSummary);
            }
        }
    }

    private void addResearchAreaSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolResearchArea researchArea : getProtocolResearchAreas()) {
            ResearchAreaSummary researchAreaSummary = new ResearchAreaSummary();
            researchAreaSummary.setResearchAreaCode(researchArea.getResearchAreaCode());
            researchAreaSummary.setDescription(researchArea.getResearchAreas().getDescription());
            protocolSummary.add(researchAreaSummary);
        }
    }

    private void addPersonnelSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolPerson person : getProtocolPersons()) {
            PersonnelSummary personnelSummary = new PersonnelSummary();
            personnelSummary.setPersonId(person.getPersonId());
            personnelSummary.setName(person.getPersonName());
            personnelSummary.setRoleName(person.getProtocolPersonRole().getDescription());
            if (person.getAffiliationTypeCode() == null) {
                personnelSummary.setAffiliation("");
            }
            else {
                if (person.getAffiliationType() == null) {
                    person.refreshReferenceObject("affiliationType");
                }
                personnelSummary.setAffiliation(person.getAffiliationType().getDescription());
            }
            for (ProtocolUnit unit : person.getProtocolUnits()) {
                personnelSummary.addUnit(unit.getUnitNumber(), unit.getUnitName());
            }
            protocolSummary.add(personnelSummary);
        }
    }

    private ProtocolSummary createProtocolSummary() {
        ProtocolSummary summary = new ProtocolSummary();
        summary.setLastProtocolAction(getLastProtocolAction());
        summary.setProtocolNumber(getProtocolNumber().toString());
        summary.setPiName(getPrincipalInvestigator().getPersonName());
        summary.setPiProtocolPersonId(getPrincipalInvestigator().getProtocolPersonId());
        summary.setInitialSubmissionDate(getInitialSubmissionDate());
        summary.setApprovalDate(getApprovalDate());
        summary.setLastApprovalDate(getLastApprovalDate());
        summary.setExpirationDate(getExpirationDate());
        if (getProtocolType() == null) {
            refreshReferenceObject("protocolType");
        }
        summary.setType(getProtocolType().getDescription());
        if (getProtocolStatus() == null) {
            refreshReferenceObject("protocolStatus");
        }
        summary.setStatus(getProtocolStatus().getDescription());
        summary.setTitle(getTitle());
        return summary;
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentKey()
     */
    public String getDocumentKey() {
        return Permissionable.PROTOCOL_KEY;
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentNumberForPermission()
     */
    public String getDocumentNumberForPermission() {
        return protocolNumber;
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getRoleNames()
     */
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        roleNames.add(RoleConstants.PROTOCOL_AGGREGATOR);
        roleNames.add(RoleConstants.PROTOCOL_VIEWER);

        return roleNames;
    }
    
    public void resetForeignKeys() {
        for (ProtocolAction action : protocolActions) {
            action.resetForeignKeys();
        }
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }
    
    /**
     * 
     * @see org.kuali.kra.UnitAclLoadable#getUnitNumberOfDocument()
     */
    public String getDocumentUnitNumber() {
        return getLeadUnitNumber();
    }

    /**
     * 
     * @see org.kuali.kra.UnitAclLoadable#getDocumentRoleTypeCode()
     */
    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROTOCOL_ROLE_TYPE;
    }

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        return;
    }
    
    public boolean isNew() {
        return !isAmendment() && !isRenewal();
    }
    
    public boolean isAmendment() {
        return protocolNumber.contains(AMENDMENT_LETTER);
    }
    
    public boolean isRenewal() {
        return protocolNumber.contains(RENEWAL_LETTER);
    }
    
    public boolean isRenewalWithoutAmendment() {
        return isRenewal() && CollectionUtils.isEmpty(this.getProtocolAmendRenewal().getModules());
    }
    
    /**
     * 
     * If the protocol document is an amendment or renewal the parent protocol number is being returned.
     * (i.e. the protocol number of the protocol that is being amended or renewed).
     * 
     * Null will be returned if the protocol is not an amendment or renewal.
     * 
     * @return protocolNumber of the Protocol that is being amended/renewed
     */
    public String getAmendedProtocolNumber() {
        if (isAmendment()) {
            return StringUtils.substringBefore(getProtocolNumber(), AMENDMENT_LETTER.toString());
            
        } else if (isRenewal()) {
            return StringUtils.substringBefore(getProtocolNumber(), RENEWAL_LETTER.toString());
                
        } else {
            return null;
        }
    }
    
    /**
     * Decides whether or not the Protocol is in a state where changes will require versioning.  For example: has the protocol
     * had a change in status and not been versioned yet?
     * @return true if versioning required false if not.
     */
    public boolean isVersioningRequired() {
        // TODO : why need this. it's always true
        return true;
    }
    
    /**
     * This method will return the list of all active attachments for this protocol; an attachment A is active for a 
     * protocol if either A has a doc status code of 'draft' or 
     * if for all other attachments for that protocol having the same doc id as A's doc id, none have a version number
     * greater than A's version number. 
     * is defined as the o 
     * @return
     */
    public List<ProtocolAttachmentProtocol> getActiveAttachmentProtocols() {
        List<ProtocolAttachmentProtocol> activeAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment1 : getAttachmentProtocols()) {
            if (attachment1.isDraft()) {
                activeAttachments.add(attachment1);
            } else if (attachment1.isFinal() || attachment1.isDeleted()) {
            //else if (attachment1.isFinal())) {
                boolean isActive = true;
                for (ProtocolAttachmentProtocol attachment2 : getAttachmentProtocols()) {
                    if (attachment1.getDocumentId().equals(attachment2.getDocumentId()) 
                            && attachment1.getAttachmentVersion() < attachment2.getAttachmentVersion()) {
                        isActive = false;
                        break;
                    }
                }
                if (isActive) {
                    activeAttachments.add(attachment1);
                } else {
                    attachment1.setActive(isActive);
                }
            } else {
                attachment1.setActive(false);
            }
        }
        return activeAttachments;
    }
    
    /**
     * This method will return the list of undeleted attachments that are still active for this protocol.
     * Essentially it filters out all the deleted elements from the list of active attachments.
     * See getActiveAttachmentProtocols() for a specification of what is an 'active attachment'. 
     * 
     * 
     * @return
     */
    // TODO the method code below could be restructured to combine the two for loops into one loop.
    public List<ProtocolAttachmentProtocol> getActiveAttachmentProtocolsNoDelete() {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocol> activeAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment : getActiveAttachmentProtocols()) {
            if (attachment.isDeleted()) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        for (ProtocolAttachmentProtocol attachment : getActiveAttachmentProtocols()) {
            if (documentIds.isEmpty() || !documentIds.contains(attachment.getDocumentId())) {
                activeAttachments.add(attachment);
            } else {
                attachment.setActive(false);
            }
        }
        return activeAttachments;
    }
    

    public boolean isCorrectionMode() {
        return correctionMode;
    }

    public void setCorrectionMode(boolean correctionMode) {
        this.correctionMode = correctionMode;
    }
    
    protected DateTimeService getDateTimeService() {
        if(dateTimeService == null) {
            dateTimeService = (DateTimeService) KraServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        if(sequenceAccessorService == null) {
            sequenceAccessorService = (SequenceAccessorService) KraServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public Long getNotifyIrbSubmissionId() {
        return notifyIrbSubmissionId;
    }

    public void setNotifyIrbSubmissionId(Long notifyIrbSubmissionId) {
        this.notifyIrbSubmissionId = notifyIrbSubmissionId;
    }

    public boolean isLookupPendingProtocol() {
        return lookupPendingProtocol;
    }

    public void setLookupPendingProtocol(boolean lookupPendingProtocol) {
        this.lookupPendingProtocol = lookupPendingProtocol;
    }

    public boolean isLookupPendingPIActionProtocol() {
        return lookupPendingPIActionProtocol;
    }

    public void setLookupPendingPIActionProtocol(boolean lookupPendingPIActionProtocol) {
        this.lookupPendingPIActionProtocol = lookupPendingPIActionProtocol;
    }

    public boolean isLookupActionAmendRenewProtocol() {
        return lookupActionAmendRenewProtocol;
    }

    public void setLookupActionAmendRenewProtocol(boolean lookupActionAmendRenewProtocol) {
        this.lookupActionAmendRenewProtocol = lookupActionAmendRenewProtocol;
    }

    public boolean isLookupActionNotifyIRBProtocol() {
        return lookupActionNotifyIRBProtocol;
    }

    public void setLookupActionNotifyIRBProtocol(boolean lookupActionNotifyIRBProtocol) {
        this.lookupActionNotifyIRBProtocol = lookupActionNotifyIRBProtocol;
    }

    public boolean isLookupActionRequestProtocol() {
        return lookupActionRequestProtocol;
    }

    public void setLookupActionRequestProtocol(boolean lookupActionRequestProtocol) {
        this.lookupActionRequestProtocol = lookupActionRequestProtocol;
    }

    public boolean isLookupProtocolPersonId() {
        return lookupProtocolPersonId;
    }

    public void setLookupProtocolPersonId(boolean lookupProtocolPersonId) {
        this.lookupProtocolPersonId = lookupProtocolPersonId;
    }

    /**
     * 
     * This method is to check if the actiontypecode is a followup action.
     * @param actionTypeCode
     * @return
     */
    public boolean isFollowupAction(String actionTypeCode) {
        return (getLastProtocolAction() == null || StringUtils.isBlank(getLastProtocolAction().getFollowupActionCode())) ? false 
                : actionTypeCode.equals(getLastProtocolAction().getFollowupActionCode());
    }

    public boolean isMergeAmendment() {
        return mergeAmendment;
    }

    public void setMergeAmendment(boolean mergeAmendment) {
        this.mergeAmendment = mergeAmendment;
    }

    public ProtocolAttachmentFilter getProtocolAttachmentFilter() {
        return protocolAttachmentFilter;
    }

    public void setProtocolAttachmentFilter(ProtocolAttachmentFilter protocolAttachmentFilter) {
        this.protocolAttachmentFilter = protocolAttachmentFilter;
    }
    
    
    /**
     * 
     * This method returns a list of attachments which respect the sort filter
     * @return a filtered list of protocol attachments
     */
    public List<ProtocolAttachmentProtocol> getFilteredAttachmentProtocols() {
        List<ProtocolAttachmentProtocol> filteredAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        ProtocolAttachmentFilter attachmentFilter = getProtocolAttachmentFilter();
        if (attachmentFilter != null && StringUtils.isNotBlank(attachmentFilter.getFilterBy())) {            
            for (ProtocolAttachmentProtocol attachment1 : getAttachmentProtocols()) {
                if ((attachment1.getTypeCode()).equals(attachmentFilter.getFilterBy())) {
                    filteredAttachments.add(attachment1);
                }
            }
        } else {
            filteredAttachments = getAttachmentProtocols();
        }
        
        if (attachmentFilter != null && StringUtils.isNotBlank(attachmentFilter.getSortBy())) {
            Collections.sort(filteredAttachments, attachmentFilter.getProtocolAttachmentComparator()); 
        }   
        
        return filteredAttachments;
    }
    
    public void initializeProtocolAttachmentFilter() {
        protocolAttachmentFilter = new ProtocolAttachmentFilter();
        
        //Lets see if there is a default set for the attachment sort
        try {
            String defaultSortBy = getParameterService().getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_PROTOCOL_ATTACHMENT_DEFAULT_SORT);
            if (StringUtils.isNotBlank(defaultSortBy)) {
                protocolAttachmentFilter.setSortBy(defaultSortBy);
            }
        } catch (Exception e) {
            //No default found, do nothing.
        }        
    }
    
    public ParameterService getParameterService() {
        return (ParameterService)KraServiceLocator.getService(ParameterService.class);

    }

    public void cleanupSpecialReviews(Protocol srcProtocol) {
        List<ProtocolSpecialReview> srcSpecialReviews = srcProtocol.getSpecialReviews();
        List<ProtocolSpecialReview> dstSpecialReviews = getSpecialReviews();
        for (int i=0; i < srcSpecialReviews.size(); i++) {
            ProtocolSpecialReview srcSpecialReview = srcSpecialReviews.get(i);
            ProtocolSpecialReview dstSpecialReview = dstSpecialReviews.get(i);
            // copy exemption codes, since they are transient and ignored by deepCopy()
            if (srcSpecialReview.getExemptionTypeCodes() != null) {
                List<String> exemptionCodeCopy = new ArrayList<String>();
                for (String s: srcSpecialReview.getExemptionTypeCodes()) {
                    exemptionCodeCopy.add(new String(s));
                }
                dstSpecialReview.setExemptionTypeCodes(exemptionCodeCopy);
            }
            // force new table entry
            dstSpecialReview.resetPersistenceState();
        }
    }
    
    
    
   
    /**
     * This method encapsulates the logic to decide if a committee member appears in the list of protocol personnel. 
     * It will first try to match by personIds and if personIds are not available then it will try matching by rolodexIds.
     * @param member
     * @return
     */
    public boolean isMemberInProtocolPersonnel(CommitteeMembership member) {
        boolean retValue = false;
        for(ProtocolPerson protocolPerson: this.protocolPersons) {
            if( StringUtils.isNotBlank(member.getPersonId()) && StringUtils.isNotBlank(protocolPerson.getPersonId()) ) {
                if(member.getPersonId().equals(protocolPerson.getPersonId())){
                    retValue = true;
                    break;
                }
            }
            else if( StringUtils.isBlank(member.getPersonId()) && StringUtils.isBlank(protocolPerson.getPersonId()) ) {
                // in this case check rolodex id
                if( (null != member.getRolodexId()) && (null != protocolPerson.getRolodexId()) ) {
                    if(member.getRolodexId().equals(protocolPerson.getRolodexId())) {
                        retValue = true;
                        break;
                    }
                }
            }
        }
        return retValue;
    }

    /**
     * This method will filter out this protocol's personnel from the given list of committee memberships
     * @param members
     * @return the filtered list of members
     */
    public List<CommitteeMembership> filterOutProtocolPersonnel(List<CommitteeMembership> members) {
        List<CommitteeMembership> filteredMemebers = new ArrayList<CommitteeMembership>();
        for (CommitteeMembership member : members) {
            if(!isMemberInProtocolPersonnel(member)) {
                filteredMemebers.add(member);
            }
        }
        
        return filteredMemebers;
    }
    
    /**
     * 
     * This method is to return the first submission date as application date. see kccoi-36 comment
     * @return
     */
    public Date getApplicationDate() {
        if (CollectionUtils.isNotEmpty(this.protocolSubmissions)) {
            return this.protocolSubmissions.get(0).getSubmissionDate();
        } else {
            return null;
        }
    }

    @Override
    public String getProjectName() {
        // TODO Auto-generated method stub
        return getTitle();
    }

    @Override
    public String getProjectId() {
        // TODO Auto-generated method stub
        return getProtocolNumber();
    }
}
