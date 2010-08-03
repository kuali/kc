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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.UnitAclLoadable;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipType;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.SpecialReviewHandler;
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
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
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
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
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
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * 
 * This class is Protocol Business Object.
 */
public class Protocol extends KraPersistableBusinessObjectBase implements SpecialReviewHandler<ProtocolSpecialReview>, 
                                                                          SequenceOwner<Protocol>, 
                                                                          Permissionable,
                                                                          UnitAclLoadable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1461551957662921433L;

    private static final CharSequence AMENDMENT_LETTER = "A";
    private static final CharSequence RENEWAL_LETTER = "R";
    
    private Long protocolId; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private boolean active = true;
    private String protocolTypeCode; 
    private String protocolStatusCode; 
    private String title; 
    private String description; 
    private Date applicationDate; 
    private Date approvalDate; 
    private Date expirationDate; 
    private Date lastApprovalDate; 
    private String fdaApplicationNumber; 
    private String referenceNumber1; 
    private String referenceNumber2; 
    private Boolean billable; 
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
    private List<ProtocolAttachmentPersonnel> attachmentPersonnels;
    
    private List<ProtocolNotepad> notepads;

    private List<ProtocolAction> protocolActions;
    private List<ProtocolSubmission> protocolSubmissions;
    @SkipVersioning
    transient private List<ProtocolOnlineReview> protocolOnlineReviews;
  
    private ProtocolSubmission protocolSubmission;
    
    /*
     * There should only be zero or one entry in the protocolAmendRenewals
     * list.  It is because of OJB that a list is used instead of a single item.
     */
    private List<ProtocolAmendRenewal> protocolAmendRenewals;
    
    private boolean correctionMode = false;
    
    private transient DateTimeService dateTimeService;
    
    /**
     * 
     * Constructs an Protocol BO.
     */
    public Protocol() {
        super();
        sequenceNumber = new Integer(0);
        billable = false;
        protocolRiskLevels = new ArrayList<ProtocolRiskLevel>();
        protocolParticipants = new TypedArrayList(ProtocolParticipant.class);
        protocolResearchAreas = new ArrayList<ProtocolResearchArea>();
        protocolReferences = new ArrayList<ProtocolReference>(); 
        newDescription = getDefaultNewDescription();
        protocolStatus = new ProtocolStatus();
        protocolStatusCode = protocolStatus.getProtocolStatusCode();
        protocolLocations = new ArrayList<ProtocolLocation>(); 
        protocolPersons = new ArrayList<ProtocolPerson>(); 
//        initializeProtocolLocation();
        protocolFundingSources = new ArrayList<ProtocolFundingSource>();        
        specialReviews = new ArrayList<ProtocolSpecialReview>();
        setProtocolActions(new ArrayList<ProtocolAction>());
        setProtocolSubmissions(new ArrayList<ProtocolSubmission>());
        setProtocolOnlineReviews( new ArrayList<ProtocolOnlineReview>() );
        protocolAmendRenewals = new ArrayList<ProtocolAmendRenewal>();
        // set statuscode default
        setProtocolStatusCode(Constants.DEFAULT_PROTOCOL_STATUS_CODE);
        this.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        
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
    public Timestamp getSubmissionDate() {
        // TODO : the last one in the list may not be the last one submitted
        // getProtocolSubmission will get the last one.  SO, this method may not needed.
        // Also, this method only referenced in test once.
        Timestamp submissionDate = null;
        if (protocolSubmissions.size() > 0) {
//            ProtocolSubmission submission = protocolSubmissions.get(protocolSubmissions.size() - 1);
//            submissionDate = submission.getSubmissionDate();
            submissionDate = getProtocolSubmission().getSubmissionDate();
        }
        return submissionDate;
    }
    
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
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

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
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
     * Gets the protocolOnlineReviews attribute. 
     * @return Returns the protocolOnlineReviews.
     */
    public List<ProtocolOnlineReview> getProtocolOnlineReviews() {
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolOnlineReviews attribute value.
     * @param protocolOnlineReviews The protocolOnlineReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReview> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }


    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();        
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("active", isActive());
        hashMap.put("protocolTypeCode", getProtocolTypeCode());
        hashMap.put("protocolStatusCode", getProtocolStatusCode());
        hashMap.put("title", getTitle());
        hashMap.put("description", getDescription());
        hashMap.put("applicationDate", getApplicationDate());
        hashMap.put("approvalDate", getApprovalDate());
        hashMap.put("expirationDate", getExpirationDate());
        hashMap.put("lastApprovalDate", getLastApprovalDate());
        hashMap.put("fdaApplicationNumber", getFdaApplicationNumber());
        hashMap.put("referenceNumber1", getReferenceNumber1());
        hashMap.put("referenceNumber2", getReferenceNumber2());
        hashMap.put("isBillable", isBillable());
        hashMap.put("specialReviewIndicator", getSpecialReviewIndicator());
        hashMap.put("vulnerableSubjectIndicator", getVulnerableSubjectIndicator());
        hashMap.put("keyStudyPersonIndicator", getKeyStudyPersonIndicator());
        hashMap.put("fundingSourceIndicator", getFundingSourceIndicator());
        hashMap.put("correspondentIndicator", getCorrespondentIndicator());
        hashMap.put("referenceIndicator", getReferenceIndicator());
        hashMap.put("relatedProjectsIndicator", getRelatedProjectsIndicator());
        hashMap.put("specialReviews", getSpecialReviews());
        hashMap.put("attachmentProtocols", getAttachmentProtocols());
        hashMap.put("attachmentPersonnels", getAttachmentPersonnels());
        return hashMap;
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
        managedLists.add(getProtocolUnits());
        managedLists.add(getAttachmentProtocols());
        managedLists.add(getProtocolOnlineReviews());
        //the attachment personnels must get added to the managed list
        //BEFORE the ProtocolPersons otherwise deleting a ProtocolPerson
        //may cause a DB constraint violation.
        managedLists.add(getAttachmentPersonnels());
        
        managedLists.add(getProtocolPersons());
        managedLists.add(getSpecialReviews());
        managedLists.add(getProtocolActions());
        managedLists.add(getProtocolSubmissions());
        return managedLists;
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
        for (ProtocolSpecialReview specialReview : specialReviews) {
            specialReview.init(this);
        }
    }
    
    /**
     * @see org.kuali.kra.document.SpecialReviewHandler#addSpecialReview(org.kuali.kra.bo.AbstractSpecialReview)
     */
    public void addSpecialReview(ProtocolSpecialReview specialReview) {
        specialReview.setProtocol(this);
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
     * Gets the attachment personnels. Cannot return {@code null}.
     * @return the attachment personnels
     */
    public List<ProtocolAttachmentPersonnel> getAttachmentPersonnels() {
        if (this.attachmentPersonnels == null) {
            this.attachmentPersonnels = new ArrayList<ProtocolAttachmentPersonnel>();
        }
        
        return this.attachmentPersonnels;
    }
    
    /**
     * Gets an attachment personnel.
     * @param index the index
     * @return an attachment personnel
     */
    public ProtocolAttachmentPersonnel getAttachmentPersonnel(int index) {
        return this.attachmentPersonnels.get(index);
    }
    
    /**
     * add an attachment personnel.
     * @param attachmentPersonnel the attachment personnel
     * @throws IllegalArgumentException if attachmentPersonnel is null
     */
    private void addAttachmentPersonnel(ProtocolAttachmentPersonnel attachmentPersonnel) {
        ProtocolAttachmentBase.addAttachmentToCollection(attachmentPersonnel, this.getAttachmentPersonnels());
    }

    /**
     * remove an attachment personnel.
     * @param attachmentPersonnel the attachment personnel
     * @throws IllegalArgumentException if attachmentPersonnel is null
     */
    private void removeAttachmentPersonnel(ProtocolAttachmentPersonnel attachmentPersonnel) {
        ProtocolAttachmentBase.removeAttachmentFromCollection(attachmentPersonnel, this.getAttachmentPersonnels());
    }
    
    private void updateUserFields(KraPersistableBusinessObjectBase bo) {
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
    
        // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
        if (updateUser.length() > 60) {
            updateUser = updateUser.substring(0, 60);
        }
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        bo.setUpdateUser(updateUser);
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
        if (attachment instanceof ProtocolAttachmentProtocol) {
            this.addAttachmentProtocol((ProtocolAttachmentProtocol) attachment);
        } else if (attachment instanceof ProtocolAttachmentPersonnel) {
            this.addAttachmentPersonnel((ProtocolAttachmentPersonnel) attachment);
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
        } else if (attachment instanceof ProtocolAttachmentPersonnel) {
            this.removeAttachmentPersonnel((ProtocolAttachmentPersonnel) attachment);
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
        this.protocolSubmission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
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

    public void setAttachmentPersonnels(List<ProtocolAttachmentPersonnel> attachmentPersonnels) {
        this.attachmentPersonnels = attachmentPersonnels;
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
    
    public void merge(Protocol amendment) {
        List<ProtocolAmendRenewModule> modules = amendment.getProtocolAmendRenewal().getModules();
        for (ProtocolAmendRenewModule module : modules) {
            if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.GENERAL_INFO)) {
                mergeGeneralInfo(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.AREAS_OF_RESEARCH)) {
                mergeResearchAreas(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.FUNDING_SOURCE)) {
                mergeFundingSources(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.PROTOCOL_ORGANIZATIONS)) {
                mergeOrganizations(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.PROTOCOL_PERSONNEL)) {
                mergePersonnel(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.ADD_MODIFY_ATTACHMENTS)) {
                mergeAttachments(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.PROTOCOL_REFERENCES)) {
                mergeReferences(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.SPECIAL_REVIEW)) {
                mergeSpecialReview(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.SUBJECTS)) {
                mergeSubjects(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.OTHERS)) {
                mergeOthers(amendment);
            }
        }
    }

    private void mergeGeneralInfo(Protocol amendment) {
        this.protocolTypeCode = amendment.getProtocolTypeCode();
        this.title = amendment.getTitle();
        this.description = amendment.getDescription();
        this.applicationDate = amendment.getApplicationDate();
        this.fdaApplicationNumber = amendment.getFdaApplicationNumber();
        this.billable = amendment.isBillable();
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
            if ("1".equals(attachment.getDocumentStatusCode())) {
                attachment.setDocumentStatusCode("2");
                attachmentProtocols.add(attachment);
                attachment.setProtocol(this);
            }
            if ("3".equals(attachment.getDocumentStatusCode()) 
                    && KraServiceLocator.getService(ProtocolAttachmentService.class).isNewAttachmentVersion((ProtocolAttachmentProtocol) attachment)) {
                attachmentProtocols.add(attachment);
                attachment.setProtocol(this);
            }
           // attachmentProtocols.add(attachment);
        }
        getAttachmentProtocols().addAll(attachmentProtocols);
        //setAttachmentProtocols(attachmentProtocols);
        
        //setAttachmentProtocols((List<ProtocolAttachmentProtocol>) deepCopy(amendment.getAttachmentProtocols()));
        mergeAttachmentPersonnels(amendment) ;
//        setAttachmentPersonnels((List<ProtocolAttachmentPersonnel>) deepCopy(amendment.getAttachmentPersonnels()));
        mergeNotepads(amendment);
    }
    

    private void mergeAttachmentPersonnels(Protocol amendment) {
        List <ProtocolAttachmentPersonnel> attachments = new ArrayList<ProtocolAttachmentPersonnel>();
        if (amendment.getProtocolPersons() != null) {
            for (ProtocolPerson person : amendment.getProtocolPersons()) {
                List <ProtocolAttachmentPersonnel> personAttachments = new ArrayList<ProtocolAttachmentPersonnel>();
                ProtocolPerson matchingPerson = findMatchingPerson(person);
                for (ProtocolAttachmentPersonnel attachment : (List<ProtocolAttachmentPersonnel>) deepCopy(person.getAttachmentPersonnels())) {
                    
                    attachment.setProtocolNumber(this.getProtocolNumber());
                    attachment.setSequenceNumber(this.getSequenceNumber());
                    attachment.setProtocolId(this.getProtocolId());
                    attachment.setId(null);
                    // TODO : at this point, is it possible that matching person not found ?
                    // if amendment are modifying person and attachment modules
                    attachment.setPerson(matchingPerson);
                    attachment.setPersonId(matchingPerson.getProtocolPersonId());
                    attachment.setId(null);
                    attachment.setProtocol(this);
                    personAttachments.add(attachment);
                    attachments.add(attachment);
                }
                matchingPerson.setAttachmentPersonnels(personAttachments);
            }
            this.setAttachmentPersonnels(attachments);
        }

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
    }
    
    @SuppressWarnings("unchecked")
    private void mergePersonnel(Protocol amendment) {
        setProtocolPersons((List<ProtocolPerson>) deepCopy(amendment.getProtocolPersons()));
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
        additionalInfoSummary.setBillable(isBillable());
        additionalInfoSummary.setReferenceId1(this.getReferenceNumber1());
        additionalInfoSummary.setReferenceId2(this.getReferenceNumber2());
        additionalInfoSummary.setDescription(getDescription());
        protocolSummary.setAdditionalInfo(additionalInfoSummary);
    }

    private void addSpecialReviewSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolSpecialReview specialReview : getSpecialReviews()) {
            SpecialReviewSummary specialReviewSummary = new SpecialReviewSummary();
            if (specialReview.getSpecialReview() == null) {
                specialReview.refreshReferenceObject("specialReview");
            }
            specialReviewSummary.setType(specialReview.getSpecialReview().getDescription());
            if (specialReview.getSpecialReviewApprovalType() == null) {
                specialReview.refreshReferenceObject("specialReviewApprovalType");
            }
            specialReviewSummary.setApprovalStatus(specialReview.getSpecialReviewApprovalType().getDescription());
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
            fundingSourceSummary.setFundingSource(source.getFundingSource());
            fundingSourceSummary.setFundingSourceNumber(source.getFundingSourceNumber());
            fundingSourceSummary.setFundingSourceName(source.getFundingSourceName());
            fundingSourceSummary.setFundingSourceTitle(source.getFundingSourceTitle());
            protocolSummary.add(fundingSourceSummary);
        }
    }

    private void addAttachmentSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolAttachmentProtocol attachment : getActiveAttachmentProtocols()) {
            AttachmentSummary attachmentSummary = new AttachmentSummary();
            attachmentSummary.setAttachmentId(attachment.getId());
            attachmentSummary.setFileType(attachment.getFile().getType());
            attachmentSummary.setFileName(attachment.getFile().getName());
            attachmentSummary.setAttachmentType(attachment.getType().getDescription());
            attachmentSummary.setDescription(attachment.getDescription());
            attachmentSummary.setDataLength(attachment.getFile().getData() == null ? 0 : attachment.getFile().getData().length);
            protocolSummary.add(attachmentSummary);
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
        summary.setApplicationDate(getApplicationDate());
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
    public boolean isAmendment() {
        return protocolNumber.contains(AMENDMENT_LETTER);
    }
    
    public boolean isRenewal() {
        return protocolNumber.contains(RENEWAL_LETTER);
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
    
    public List<ProtocolAttachmentProtocol> getActiveAttachmentProtocols() {
        List<ProtocolAttachmentProtocol> activeAttachments = new ArrayList<ProtocolAttachmentProtocol>();
        for (ProtocolAttachmentProtocol attachment1 : getAttachmentProtocols()) {
            if ("1".equals(attachment1.getDocumentStatusCode())) {
                activeAttachments.add(attachment1);
            }
            else if ("2".equals(attachment1.getDocumentStatusCode()) || "3".equals(attachment1.getDocumentStatusCode())) {
            //else if ("2".equals(attachment1.getDocumentStatusCode())) {
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
}
