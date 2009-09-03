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
package org.kuali.kra.irb;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipType;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.CommitteeScheduleAttendance;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
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
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * 
 * This class is Protocol Business Object.
 */
public class Protocol extends KraPersistableBusinessObjectBase implements SpecialReviewHandler<ProtocolSpecialReview>, SequenceOwner<Protocol> {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1461551957662921433L;
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

    // this is a transient filed for validation purposes only.
    private ProtocolUnit leadUnitForValidation;

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

    private List<ProtocolAction> protocolActions;
    private List<ProtocolSubmission> protocolSubmissions;
    
    private ProtocolSubmission protocolSubmission;
    
    /*
     * There should only be zero or one entry in the protocolAmendRenewals
     * list.  It is because of OJB that a list is used instead of a single item.
     */
    private List<ProtocolAmendRenewal> protocolAmendRenewals;
    
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
        initializeProtocolLocation();
        protocolFundingSources = new ArrayList<ProtocolFundingSource>();        
        specialReviews = new ArrayList<ProtocolSpecialReview>();
        setProtocolActions(new ArrayList<ProtocolAction>());
        setProtocolSubmissions(new ArrayList<ProtocolSubmission>());
        protocolAmendRenewals = new ArrayList<ProtocolAmendRenewal>();
        // set statuscode default
        setProtocolStatusCode(Constants.DEFAULT_PROTOCOL_STATUS_CODE);
        this.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        
        populateTempViewDate();
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
        Timestamp submissionDate = null;
        if (protocolSubmissions.size() > 0) {
            ProtocolSubmission submission = protocolSubmissions.get(protocolSubmissions.size() - 1);
            submissionDate = submission.getSubmissionDate();
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
        
        //the attachment personnels must get added to the managed list
        //BEFORE the ProtocolPersons otherwise deleting a ProtocolPerson
        //may cause a DB constraint violation.
        managedLists.add(getAttachmentPersonnels());
        
        managedLists.add(getProtocolPersons());
        managedLists.add(getSpecialReviews());
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

    public ProtocolUnit getLeadUnitForValidation() {
        if (leadUnitForValidation == null && getPrincipalInvestigator() != null) {
            for (ProtocolUnit protocolUnit : getPrincipalInvestigator().getProtocolUnits()) {
                if (protocolUnit.getLeadUnitFlag()) {
                    leadUnitForValidation = protocolUnit;
                    break;
                }                
            }
        }
        return leadUnitForValidation;
    }

    public void setLeadUnitForValidation(ProtocolUnit leadUnitForValidation) {
        this.leadUnitForValidation = leadUnitForValidation;
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
    
    private void populateTempViewDate() {
        this.protocolSubmission = new ProtocolSubmission();
        
        Committee committee = new Committee();
        committee.setId(1L);
        committee.setCommitteeId("Comm-1");
        committee.setCommitteeName("Test By Kiltesh");
        this.protocolSubmission.setCommittee(committee);
        
        this.protocolSubmission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        
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
        Person p1 = new Person();
        p1.setLastName("Mendez");
        p1.setFirstName("Tom");
        committeeScheduleAttendance.setPerson(p1);
        List<CommitteeScheduleAttendance> cslist = new ArrayList<CommitteeScheduleAttendance>();
        cslist.add(committeeScheduleAttendance);
        committeeSchedule.setCommitteeScheduleAttendances(cslist);        
        this.protocolSubmission.setCommitteeSchedule(committeeSchedule);
    }
    
    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
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
    }

    public void setAttachmentPersonnels(List<ProtocolAttachmentPersonnel> attachmentPersonnels) {
        this.attachmentPersonnels = attachmentPersonnels;
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
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.ADD_MODIFY_ATTACHMENTS)) {
                mergeAttachments(amendment);
            }
            else if (StringUtils.equals(module.getProtocolModuleTypeCode(), ProtocolModule.PROTOCOL_PERSONNEL)) {
                mergePersonnel(amendment);
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
        setAttachmentProtocols((List<ProtocolAttachmentProtocol>) deepCopy(amendment.getAttachmentProtocols()));
        setAttachmentPersonnels((List<ProtocolAttachmentPersonnel>) deepCopy(amendment.getAttachmentPersonnels()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergeSpecialReview(Protocol amendment) {
        setSpecialReviews((List<ProtocolSpecialReview>) deepCopy(amendment.getSpecialReviews()));
    }
    
    @SuppressWarnings("unchecked")
    private void mergePersonnel(Protocol amendment) {
        setProtocolPersons((List<ProtocolPerson>) deepCopy(amendment.getProtocolPersons()));
    }
    
    private Object deepCopy(Object obj) {
        return ObjectUtils.deepCopy((Serializable) obj);
    }
}
