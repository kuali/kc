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
package org.kuali.kra.protocol;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.auth.UnitAclLoadable;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.krms.KcKrmsContextBo;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewalBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionStatusBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.kra.protocol.noteattachment.*;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.protocol.protocol.ProtocolTypeBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationService;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewExemption;
import org.kuali.kra.protocol.summary.*;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;



public abstract class ProtocolBase extends KcPersistableBusinessObjectBase implements SequenceOwner<ProtocolBase>, Permissionable, UnitAclLoadable, Disclosurable, KcKrmsContextBo {
   
    private static final long serialVersionUID = -5556152547067349988L;
    
    protected static final String NEXT_ACTION_ID_KEY = "actionId";
     
    
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
    
    private String keyStudyPersonIndicator; 
    private String fundingSourceIndicator; 
    private String correspondentIndicator; 
    private String referenceIndicator;
    
 
    private String relatedProjectsIndicator; 
    private ProtocolDocumentBase protocolDocument;
    
    private ProtocolStatusBase protocolStatus;  
    private ProtocolTypeBase protocolType; 
    
    private List<ProtocolResearchAreaBase> protocolResearchAreas;
    
    private List<ProtocolReferenceBase> protocolReferences;
    private List<ProtocolLocationBase> protocolLocations;
        
    //Is transient, used for lookup select option in UI by KNS 
    private String newDescription;
    
    private boolean nonEmployeeFlag;
    
    private List<ProtocolFundingSourceBase> protocolFundingSources; 
 
    
    private String leadUnitNumber;
    private String principalInvestigatorId;
    
    
    // lookup field
    private String keyPerson;
    private String investigator;
    private String fundingSource;
    
    private String performingOrganizationId;
    private String researchAreaCode;
     
 
    private String leadUnitName;
    private List<ProtocolPersonBase> protocolPersons;

    private List<ProtocolSpecialReviewBase> specialReviews;
    
    
    //these are the m:m attachment protocols that that a protocol has
    private List<ProtocolAttachmentProtocolBase> attachmentProtocols;
    
    private List<ProtocolNotepadBase> notepads;

    private List<ProtocolActionBase> protocolActions;
    private List<ProtocolSubmissionBase> protocolSubmissions;
  
    private ProtocolSubmissionBase protocolSubmission;
    private transient List<ProtocolActionBase> sortedActions;
      
    /*
     * There should only be zero or one entry in the protocolAmendRenewals
     * list.  It is because of OJB that a list is used instead of a single item.
     */
    private List<ProtocolAmendRenewalBase> protocolAmendRenewals;
    
    private transient boolean correctionMode = false;
    
    private transient DateTimeService dateTimeService;
    private transient SequenceAccessorService sequenceAccessorService;
    
    //Used to filter protocol attachments
    private transient ProtocolAttachmentFilterBase protocolAttachmentFilter;    

    // passed in req param submissionid.  used to check if irb ack is needed
    // this link is from protocosubmission or notify irb message
    private transient Long notifyIrbSubmissionId;
    
    // transient for protocol header combined label
    private transient String initiatorLastUpdated;
    private transient String protocolSubmissionStatus;
    
    // Custom ProtocolBase lookup fields
    private transient boolean lookupPendingProtocol;
    private transient boolean lookupPendingPIActionProtocol;
    private transient boolean lookupActionAmendRenewProtocol;
 
    private transient boolean lookupActionRequestProtocol;
    private transient boolean lookupProtocolPersonId;
    private transient boolean mergeAmendment;

    private String createUser;
    private Timestamp createTimestamp;
    
    public String getInitiatorLastUpdated() {
        return initiatorLastUpdated;
    }

    public String getProtocolSubmissionStatus() {
        return protocolSubmissionStatus;
    }

     

       
    /**
     * 
     * Constructs an ProtocolBase BO.
     */
    public ProtocolBase() {
        super();
        sequenceNumber = new Integer(0);          
        protocolResearchAreas = new ArrayList<ProtocolResearchAreaBase>();
        protocolReferences = new ArrayList<ProtocolReferenceBase>(); 
        newDescription = getDefaultNewDescription();
        protocolStatus = getProtocolStatusNewInstanceHook();
        protocolStatusCode = getProtocolStatus().getProtocolStatusCode();
        protocolLocations = new ArrayList<ProtocolLocationBase>(); 
        protocolPersons = new ArrayList<ProtocolPersonBase>();
        
        // set the default protocol type
        protocolTypeCode = getDefaultProtocolTypeCodeHook();

        protocolFundingSources = new ArrayList<ProtocolFundingSourceBase>();        
        specialReviews = new ArrayList<ProtocolSpecialReviewBase>();
        setProtocolActions(new ArrayList<ProtocolActionBase>());
        setProtocolSubmissions(new ArrayList<ProtocolSubmissionBase>());
        protocolAmendRenewals = new ArrayList<ProtocolAmendRenewalBase>();
        
        // set statuscode default
        setProtocolStatusCode(getDefaultProtocolStatusCodeHook());
        refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        initializeProtocolAttachmentFilter();
    }
    
    
    protected abstract ProtocolStatusBase getProtocolStatusNewInstanceHook();

    protected abstract String getDefaultProtocolStatusCodeHook();

    protected abstract String getDefaultProtocolTypeCodeHook();
    

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
//            ProtocolSubmissionBase submission = protocolSubmissions.get(protocolSubmissions.size() - 1);
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
    public List<ProtocolOnlineReviewBase> getProtocolOnlineReviews() {
        List<ProtocolOnlineReviewBase> reviews = new ArrayList<ProtocolOnlineReviewBase>();
        for (ProtocolSubmissionBase submission : getProtocolSubmissions()) {
            reviews.addAll(submission.getProtocolOnlineReviews());
        }        
        return reviews;
    }

    public ProtocolStatusBase getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(ProtocolStatusBase protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    public ProtocolTypeBase getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolTypeBase protocolType) {
        this.protocolType = protocolType;
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
    public void setProtocolResearchAreas(List<ProtocolResearchAreaBase> protocolResearchAreas) {
        this.protocolResearchAreas = protocolResearchAreas;
        for (ProtocolResearchAreaBase researchArea : protocolResearchAreas) {
            researchArea.init(this);
        }
    }

    public List<ProtocolResearchAreaBase> getProtocolResearchAreas() {
        return protocolResearchAreas;
    }
    
    public void addProtocolResearchAreas(ProtocolResearchAreaBase protocolResearchArea) {
        getProtocolResearchAreas().add(protocolResearchArea);
    }

    public ProtocolResearchAreaBase getProtocolResearchAreas(int index) {
        while (getProtocolResearchAreas().size() <= index) {
            getProtocolResearchAreas().add(getNewProtocolResearchAreaInstance());
        }
        return getProtocolResearchAreas().get(index);
    }

    protected abstract ProtocolResearchAreaBase getNewProtocolResearchAreaInstance();

    public void setProtocolReferences(List<ProtocolReferenceBase> protocolReferences) {
        this.protocolReferences = protocolReferences;
        for (ProtocolReferenceBase reference : protocolReferences) {
            reference.init(this);
        }
    }

    public List<ProtocolReferenceBase> getProtocolReferences() {
        return protocolReferences;
    }

    
    public ProtocolDocumentBase getProtocolDocument() {
    	loadWorkflowDocument(this.protocolDocument);
        return protocolDocument;
    }

    protected void loadWorkflowDocument(ProtocolDocumentBase protocolDocument) {
    	if (protocolDocument != null && protocolDocument.getDocumentHeader() != null && !protocolDocument.getDocumentHeader().hasWorkflowDocument()) {
		    protocolDocument.getDocumentHeader().setWorkflowDocument(WorkflowDocumentFactory.loadDocument(GlobalVariables.getUserSession().getPrincipalId(), 
		    		protocolDocument.getDocumentNumber()));
		}
    }
    
    public void setProtocolDocument(ProtocolDocumentBase protocolDocument) {
        this.protocolDocument = protocolDocument;
    }

    public void setProtocolLocations(List<ProtocolLocationBase> protocolLocations) {
        this.protocolLocations = protocolLocations;
        for (ProtocolLocationBase location : protocolLocations) {
            location.init(this);
        }
    }

    public List<ProtocolLocationBase> getProtocolLocations() {
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
            managedLists.add(new ArrayList<ProtocolAmendRenewModuleBase>());
        }
        managedLists.add(getProtocolAmendRenewals());        
        List<ProtocolSpecialReviewExemption> specialReviewExemptions = new ArrayList<ProtocolSpecialReviewExemption>();
        for (ProtocolSpecialReviewBase specialReview : getSpecialReviews()) {
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
     * from collection under ProtocolPersonBase.
     * @return List&lt;ProtocolAttachmentPersonnelBase&gt;
     */
    private List<ProtocolAttachmentPersonnelBase> getProtocolAttachmentPersonnel() {
        List<ProtocolAttachmentPersonnelBase> protocolAttachmentPersonnel = new ArrayList<ProtocolAttachmentPersonnelBase>();
        for (ProtocolPersonBase protocolPerson : getProtocolPersons()) {
            protocolAttachmentPersonnel.addAll(protocolPerson.getAttachmentPersonnels());
        }
        return protocolAttachmentPersonnel;
    }
  
    
    
    /**
     * This method is to return all protocol units for each person.
     * Purpose of this method is to use the list in buildListOfDeletionAwareLists.
     * Looks like OJB is not searching beyond the first level. It doesn't delete
     * from collection under ProtocolPersonBase.
     * @return List&lt;ProtocolUnitBase&gt;
     */
    private List<ProtocolUnitBase> getProtocolUnits() {
        List<ProtocolUnitBase> protocolUnits = new ArrayList<ProtocolUnitBase>();
        for (ProtocolPersonBase protocolPerson : getProtocolPersons()) {
            protocolUnits.addAll(protocolPerson.getProtocolUnits());
        }
        return protocolUnits;
    }

    /**
     * This method is to find Principal Investigator from ProtocolPersonBase list
     * @return ProtocolPersonBase
     */
    public ProtocolPersonBase getPrincipalInvestigator() {
        return getProtocolPersonnelService().getPrincipalInvestigator(getProtocolPersons());
    }

    public String getPrincipalInvestigatorName() {
        ProtocolPersonBase pi = getPrincipalInvestigator();
        return pi != null ? pi.getFullName() : null;
    }

    public ProtocolUnitBase getLeadUnit() {
        ProtocolUnitBase leadUnit = null;
        if (getPrincipalInvestigator() != null) {
            for ( ProtocolUnitBase unit : getPrincipalInvestigator().getProtocolUnits() ) {
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
                ProtocolPersonBase principalInvestigator = getPrincipalInvestigator();
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    setPrincipalInvestigatorId(principalInvestigator.getPersonId());
                } else {
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
        ProtocolLocationService protocolLocationService = (ProtocolLocationService) KcServiceLocator.getService("protocolLocationService");
        return protocolLocationService;
    }
    

    public List<ProtocolPersonBase> getProtocolPersons() {
        return protocolPersons;
    }

    public void setProtocolPersons(List<ProtocolPersonBase> protocolPersons) {
        this.protocolPersons = protocolPersons;
        for (ProtocolPersonBase person : protocolPersons) {
            person.init(this);
        }
    }

    

    /**
     * Gets index i from the protocol person list.
     * 
     * @param index
     * @return protocol person at index i
     */
    public ProtocolPersonBase getProtocolPerson(int index) {
        return getProtocolPersons().get(index);
    }
 
    
    
    
    /**
     * This method is a hook to get actual protocol personnel service impl corresponding to the protocol type
     * @return protocolPersonnelService
     */
    protected abstract ProtocolPersonnelService getProtocolPersonnelService();

    
    
 
    public List<ProtocolFundingSourceBase> getProtocolFundingSources() {
        return protocolFundingSources;
    }

    public void setProtocolFundingSources(List<ProtocolFundingSourceBase> protocolFundingSources) {
        this.protocolFundingSources = protocolFundingSources;
        for (ProtocolFundingSourceBase fundingSource : protocolFundingSources) {
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

    
 
    public void setSpecialReviews(List<ProtocolSpecialReviewBase> specialReviews) {
        this.specialReviews = specialReviews;
    }
    
    public void addSpecialReview(ProtocolSpecialReviewBase specialReview) {
        specialReview.setSequenceOwner(this);
        getSpecialReviews().add(specialReview);
    }

    public ProtocolSpecialReviewBase getSpecialReview(int index) {
        return getSpecialReviews().get(index);
    }

    public List<ProtocolSpecialReviewBase> getSpecialReviews() {
        return specialReviews;
    }
    
    /**
     * Gets the attachment protocols. Cannot return {@code null}.
     * @return the attachment protocols
     */
    public List<ProtocolAttachmentProtocolBase> getAttachmentProtocols() {
        
        if (this.attachmentProtocols == null) {
            this.attachmentProtocols = new ArrayList<ProtocolAttachmentProtocolBase>();
        }
        return this.attachmentProtocols;
    }
    
    /**
     * Gets an attachment protocol.
     * @param index the index
     * @return an attachment protocol
     */
    public ProtocolAttachmentProtocolBase getAttachmentProtocol(int index) {
        return this.attachmentProtocols.get(index);
    }
    
    /**
     * Gets the notepads. Cannot return {@code null}.
     * @return the notepads
     */
    public List<ProtocolNotepadBase> getNotepads() {
        
        if (this.notepads == null) {
            this.notepads = new ArrayList<ProtocolNotepadBase>();
        }

        return this.notepads;
    }
    
    /**
     * Gets an attachment protocol.
     * @param index the index
     * @return an attachment protocol
     */
    public ProtocolNotepadBase getNotepad(int index) {
        return this.notepads.get(index);
    }
    
    /**
     * adds an attachment protocol.
     * @param attachmentProtocol the attachment protocol
     * @throws IllegalArgumentException if attachmentProtocol is null
     */
    private void addAttachmentProtocol(ProtocolAttachmentProtocolBase attachmentProtocol) {
        ProtocolAttachmentBase.addAttachmentToCollection(attachmentProtocol, this.getAttachmentProtocols());
    }
    
    /**
     * removes an attachment protocol.
     * @param attachmentProtocol the attachment protocol
     * @throws IllegalArgumentException if attachmentProtocol is null
     */
    private void removeAttachmentProtocol(ProtocolAttachmentProtocolBase attachmentProtocol) {
        ProtocolAttachmentBase.removeAttachmentFromCollection(attachmentProtocol, this.getAttachmentProtocols());
    }

    /**
     * @deprecated
     * Gets the attachment personnels. Cannot return {@code null}.
     * @return the attachment personnels
     */
    @Deprecated
    public List<ProtocolAttachmentPersonnelBase> getAttachmentPersonnels() {
        return getProtocolAttachmentPersonnel();
    }
    
    
    
    private void updateUserFields(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
    }
    
    
    /**
     * Adds a attachment to a ProtocolBase where the type of attachment is used to determine
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
        if (attachment instanceof ProtocolAttachmentProtocolBase) {
            this.addAttachmentProtocol((ProtocolAttachmentProtocolBase) attachment);
        } else {
            throw new IllegalArgumentException("unsupported type: " + attachment.getClass().getName());
        }
    }
    
    /**
     * removes an attachment to a ProtocolBase where the type of attachment is used to determine
     * where to add the attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null or if an unsupported attachment is found
     */
    public <T extends ProtocolAttachmentBase> void removeAttachmentsByType(T attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("the attachment is null");
        }
        
        if (attachment instanceof ProtocolAttachmentProtocolBase) {
            this.removeAttachmentProtocol((ProtocolAttachmentProtocolBase) attachment);
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
    
    public ProtocolSubmissionBase getProtocolSubmission() {
        if (!protocolSubmissions.isEmpty()) {
            // sorted by ojb
            if (protocolSubmission == null
                    || protocolSubmission.getSubmissionNumber() == null
                    || protocolSubmissions.get(protocolSubmissions.size() - 1).getSubmissionNumber() > protocolSubmission
                            .getSubmissionNumber()) {
                protocolSubmission = protocolSubmissions.get(protocolSubmissions.size() - 1);
            }
        }
        else {
            protocolSubmission = getProtocolSubmissionNewInstanceHook();
            // TODO : the update protocol rule may not like null
            protocolSubmission.setProtocolSubmissionType(getProtocolSubmissionTypeNewInstanceHook());
            protocolSubmission.setSubmissionStatus(getProtocolSubmissionStatusNewInstanceHook());
        }
        // }
        refreshReferenceObject(protocolSubmission);
        return protocolSubmission;
    }

    
    protected abstract ProtocolSubmissionStatusBase getProtocolSubmissionStatusNewInstanceHook();

    protected abstract ProtocolSubmissionTypeBase getProtocolSubmissionTypeNewInstanceHook();

    protected abstract ProtocolSubmissionBase getProtocolSubmissionNewInstanceHook();
    

    private void refreshReferenceObject(ProtocolSubmissionBase submission) {
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
    
    public void setProtocolSubmission(ProtocolSubmissionBase protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public void setProtocolActions(List<ProtocolActionBase> protocolActions) {
        this.protocolActions = protocolActions;
    }

    public List<ProtocolActionBase> getProtocolActions() {
        return protocolActions;
    }
    
    public ProtocolActionBase getLastProtocolAction() {
        if (protocolActions.size() == 0) {
            return null;
        }
        Collections.sort(protocolActions, new Comparator<ProtocolActionBase>() {
            public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
                return action2.getActualActionDate().compareTo(action1.getActualActionDate());
            }
        });
        return protocolActions.get(0);
    }

    public boolean containsAction(String action) {
        boolean result = false;
        for (ProtocolActionBase protocolActionBase: getProtocolActions()) {
            if (protocolActionBase.getProtocolActionTypeCode().equals(action)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void setProtocolSubmissions(List<ProtocolSubmissionBase> protocolSubmissions) {
        this.protocolSubmissions = protocolSubmissions;
    }

    public List<ProtocolSubmissionBase> getProtocolSubmissions() {
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

    public void setAttachmentProtocols(List<ProtocolAttachmentProtocolBase> attachmentProtocols) {
        this.attachmentProtocols = attachmentProtocols;
        for (ProtocolAttachmentProtocolBase attachment : attachmentProtocols) {
            attachment.resetPersistenceState();
            attachment.setSequenceNumber(0);
        }
    }
    
    public void setNotepads(List<ProtocolNotepadBase> notepads) {
        this.notepads = notepads;
    }
    
    public void setProtocolAmendRenewal(ProtocolAmendRenewalBase amendRenewal) {
        protocolAmendRenewals.add(amendRenewal);
    }
    
    public ProtocolAmendRenewalBase getProtocolAmendRenewal() {
        if (protocolAmendRenewals.size() == 0) return null;
        return protocolAmendRenewals.get(0);
    }

    public List<ProtocolAmendRenewalBase> getProtocolAmendRenewals() {
        return protocolAmendRenewals;
    }

    public void setProtocolAmendRenewals(List<ProtocolAmendRenewalBase> protocolAmendRenewals) {
        this.protocolAmendRenewals = protocolAmendRenewals;
    }
 
    
    
    
    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }
    
    @Override
    public String getVersionNameField() {
        return "protocolNumber";
    }

    @Override
    public String getVersionNameFieldValue() {
        return protocolNumber;
    }

    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++; 
    }

    @Override
    public ProtocolBase getSequenceOwner() {
        return this;
    }

    @Override
    public void setSequenceOwner(ProtocolBase newOwner) {
       //no-op
    }
    
    @Override
    public void resetPersistenceState() {
        this.protocolId = null;
    }
   
 
    /**
     * 
     * This method merges the data of the amended protocol into this protocol.
     * @param amendment
     */
    public void merge(ProtocolBase amendment) {
        if (amendment.isFYI()) {
            mergeProtocolSubmission(amendment);
            mergeProtocolAction(amendment);
        }
        else {
            merge(amendment, true);
        }
    }

    // this method was modified during IRB backfit merge with the assumption that these changes are applicable to both IRB and IACUC
    public void merge(ProtocolBase amendment, boolean mergeActions) {
        // set this value here, since it is applicable regardless of which modules are amended
        this.lastApprovalDate = amendment.getLastApprovalDate();
        List<ProtocolAmendRenewModuleBase> modules = amendment.getProtocolAmendRenewal().getModules();
        removeMergeableLists(modules);          // remove lists from copy of original protocol
        getBusinessObjectService().save(this);  // force OJB to persist removal of lists
        for (ProtocolAmendRenewModuleBase module : modules) {
            merge(amendment, module.getProtocolModuleTypeCode());
        }
        if (amendment.isRenewalWithoutAmendment() && isRenewalWithNewAttachment(amendment)) {
            merge(amendment, getProtocolModuleAddModifyAttachmentCodeHook());
        }

        if (mergeActions) {
            mergeProtocolSubmission(amendment);
            mergeProtocolAction(amendment);
        }
        // if renewal, then set the expiration date to be the new one provided during renewal approval
        if(isExpirationDateToBeUpdated(amendment)) {
            this.setExpirationDate(amendment.getExpirationDate());
        }
    }
    
    protected abstract String getProtocolModuleAddModifyAttachmentCodeHook();
    
    // this method can be overridden to customize the conditions when the expiration date is to be updated for the main protocol
    protected boolean isExpirationDateToBeUpdated(ProtocolBase protocol) {
        return protocol.isRenewal();
    }

    private boolean isRenewalWithNewAttachment(ProtocolBase renewal) {
        boolean hasNewAttachment = false;
        for (ProtocolAttachmentProtocolBase attachment : renewal.getAttachmentProtocols()) {
            if (attachment.isDraft()) {
                hasNewAttachment = true;
                break;
            }
        }
        return hasNewAttachment;
    }
    
    public abstract void merge(ProtocolBase amendment, String protocolModuleTypeCode);
    
    protected abstract void removeMergeableLists(List<ProtocolAmendRenewModuleBase> modules);

    protected void mergeProtocolQuestionnaire(ProtocolBase amendment) {
        // TODO : what if user did not edit questionnaire at all, then questionnaire will be wiped out ?
        removeOldQuestionnaire();
        amendQuestionnaire(amendment);
    }

    /*
     * remove existing questionnaire from current 
     */
    protected void removeOldQuestionnaire() {

        List <AnswerHeader> answerHeaders = getAnswerHeaderForProtocol(this);
        if (!answerHeaders.isEmpty() && answerHeaders.get(0).getId() != null){
            getBusinessObjectService().delete(answerHeaders);
        }
    }
    
    /*
     * add questionnaire answer from amendment to protocol
     */
    protected void amendQuestionnaire(ProtocolBase amendment) {

        List <AnswerHeader> answerHeaders = getAnswerHeaderForProtocol(amendment);
        if (!answerHeaders.isEmpty()){
            for (AnswerHeader answerHeader : answerHeaders) {
                for (Answer answer : answerHeader.getAnswers()) {
                    answer.setAnswerHeaderId(null);
                    answer.setId(null);
                }
                answerHeader.setId(null);
                answerHeader.setModuleItemKey(this.getProtocolNumber());
                answerHeader.setModuleSubItemKey(this.getSequenceNumber().toString());
            }
            getBusinessObjectService().save(answerHeaders);
        }
 
    }
    
    /*
     * get submit for review questionnaire answerheaders
     */
    public abstract List <AnswerHeader> getAnswerHeaderForProtocol(ProtocolBase protocol);
    
    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    @SuppressWarnings("unchecked")
    protected void mergeProtocolSubmission(ProtocolBase amendment) {
        List<ProtocolSubmissionBase> submissions = (List<ProtocolSubmissionBase>) deepCopy(amendment.getProtocolSubmissions());  
        setNewSubmissionReferences(submissions);
    }
    
    protected void setNewSubmissionReferences(List<ProtocolSubmissionBase> submissions) {
    	submissions.forEach(submission -> {
    		setSubmissionMinutesReferences(submission);
            submission.setProtocolNumber(this.getProtocolNumber());
            submission.setSubmissionId(null);
            submission.setSequenceNumber(sequenceNumber);
            submission.setProtocolId(this.getProtocolId());
            this.getProtocolSubmissions().add(submission);
        });
    }

    @SuppressWarnings("rawtypes")
    protected void setSubmissionMinutesReferences(ProtocolSubmissionBase submission) {
        List<CommitteeScheduleMinuteBase> committeeScheduleMinutes = submission.getCommitteeScheduleMinutes();
        committeeScheduleMinutes.forEach(committeeScheduleMinute -> {
        	committeeScheduleMinute.setCommScheduleMinutesId(null);
        	committeeScheduleMinute.setProtocolIdFk(this.getProtocolId());
        	committeeScheduleMinute.setSubmissionIdFk(null);
        });
    }
    
    protected abstract void mergeProtocolAction(ProtocolBase amendment);    
    
    protected void mergeGeneralInfo(ProtocolBase amendment) {
        this.protocolTypeCode = amendment.getProtocolTypeCode();
        this.title = amendment.getTitle();
        this.initialSubmissionDate = amendment.getInitialSubmissionDate();
        this.approvalDate = amendment.getApprovalDate(); 
        this.expirationDate = amendment.getExpirationDate(); 
        this.description = amendment.getDescription();
        this.fdaApplicationNumber = amendment.getFdaApplicationNumber();
        this.referenceNumber1 = amendment.getReferenceNumber1();
        this.referenceNumber2 = amendment.getReferenceNumber2();
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeResearchAreas(ProtocolBase amendment) {
        setProtocolResearchAreas((List<ProtocolResearchAreaBase>) deepCopy(amendment.getProtocolResearchAreas()));
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeFundingSources(ProtocolBase amendment) {
        setProtocolFundingSources((List<ProtocolFundingSourceBase>) deepCopy(amendment.getProtocolFundingSources()));
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeReferences(ProtocolBase amendment) {
        setProtocolReferences((List<ProtocolReferenceBase>) deepCopy(amendment.getProtocolReferences()));
        
        this.fdaApplicationNumber = amendment.getFdaApplicationNumber();
        this.referenceNumber1 = amendment.getReferenceNumber1();
        this.referenceNumber2 = amendment.getReferenceNumber2();
        this.description = amendment.getDescription();
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeOrganizations(ProtocolBase amendment) {
        setProtocolLocations((List<ProtocolLocationBase>) deepCopy(amendment.getProtocolLocations()));
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeAttachments(ProtocolBase amendment) {
        // TODO : may need to set protocolnumber
        // personnel attachment may have protocol person id issue
        // how about sequence number ?
        // need to change documentstatus to 2 if it is 1
        // what the new protocol's protocol_status should be ?
        List<ProtocolAttachmentProtocolBase> attachmentProtocols = new ArrayList<ProtocolAttachmentProtocolBase>();
        for (ProtocolAttachmentProtocolBase attachment : (List<ProtocolAttachmentProtocolBase>) deepCopy(amendment.getAttachmentProtocols())) {
            attachment.setProtocolNumber(this.getProtocolNumber());
            attachment.setSequenceNumber(this.getSequenceNumber());
            attachment.setProtocolId(this.getProtocolId());
            attachment.setId(null);
            if (attachment.getFile() != null ) { 
                attachment.getFile().setId(null);
            }
            if (attachment.isDraft()) {
                attachment.setDocumentStatusCode(ProtocolAttachmentStatusBase.FINALIZED);
                attachmentProtocols.add(attachment);
                attachment.setProtocol(this);
            }
            if (attachment.isDeleted() && KcServiceLocator.getService(ProtocolAttachmentService.class).isNewAttachmentVersion((ProtocolAttachmentProtocolBase) attachment)) {
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
        List<ProtocolAttachmentProtocolBase> attachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        for (ProtocolAttachmentProtocolBase attachment : this.getAttachmentProtocols()) {
            attachment.setProtocol(this);
            if (attachment.isDeleted()) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        if (!documentIds.isEmpty()) {
            for (ProtocolAttachmentProtocolBase attachment : this.getAttachmentProtocols()) {
                attachment.setProtocol(this);
                if (!documentIds.contains(attachment.getDocumentId())) {
                    attachments.add(attachment);
                } 
            }
            this.setAttachmentProtocols(new ArrayList<ProtocolAttachmentProtocolBase>());
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
    protected void restoreAttachments(ProtocolBase protocol) {
        List<ProtocolAttachmentProtocolBase> attachmentProtocols = new ArrayList<ProtocolAttachmentProtocolBase>();
        List<ProtocolAttachmentProtocolBase> deleteAttachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        List<AttachmentFile> deleteFiles = new ArrayList<AttachmentFile>();
        
        for (ProtocolAttachmentProtocolBase attachment : this.getAttachmentProtocols()) {
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
        return getBusinessObjectService().countMatching(getProtocolAttachmentProtocolClassHook(), fieldValues) > 1;
        
    }
    
    protected abstract Class<? extends ProtocolAttachmentProtocolBase> getProtocolAttachmentProtocolClassHook();
    

    protected void mergeNotepads(ProtocolBase amendment) {
        List <ProtocolNotepadBase> notepads = new ArrayList<ProtocolNotepadBase>();
        if (amendment.getNotepads() != null) {
            for (ProtocolNotepadBase notepad : (List<ProtocolNotepadBase>) deepCopy(amendment.getNotepads())) {
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
    
    @SuppressWarnings("unchecked")
    protected void mergeSpecialReview(ProtocolBase amendment) {
        setSpecialReviews((List<ProtocolSpecialReviewBase>) deepCopy(amendment.getSpecialReviews()));
        cleanupSpecialReviews(amendment);
    }
    
    @SuppressWarnings("unchecked")
    protected void mergePersonnel(ProtocolBase amendment) {
        setProtocolPersons((List<ProtocolPersonBase>) deepCopy(amendment.getProtocolPersons()));
        for (ProtocolPersonBase person : protocolPersons) {
            Integer nextPersonId = getSequenceAccessorService().getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID", person.getClass()).intValue();
            person.setProtocolPersonId(nextPersonId);
            for (ProtocolAttachmentPersonnelBase protocolAttachmentPersonnel : person.getAttachmentPersonnels()) {
                protocolAttachmentPersonnel.setId(null);
                protocolAttachmentPersonnel.setPersonId(person.getProtocolPersonId());
                protocolAttachmentPersonnel.setProtocolId(getProtocolId());
                protocolAttachmentPersonnel.setProtocolNumber(getProtocolNumber());
            }
        }
    }

    protected void mergeOthers(ProtocolBase amendment) {
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
    
    protected void mergeProtocolPermissions(ProtocolBase amendment) {
        // ToDo: merge permissions
    }
    
    
    protected Object deepCopy(Object obj) {
        return ObjectUtils.deepCopy((Serializable) obj);
    }
    
    public abstract ProtocolSummary getProtocolSummary();
        
    protected void addAdditionalInfoSummary(ProtocolSummary protocolSummary) {
        AdditionalInfoSummary additionalInfoSummary = new AdditionalInfoSummary();
        additionalInfoSummary.setFdaApplicationNumber(this.getFdaApplicationNumber());
        additionalInfoSummary.setReferenceId1(this.getReferenceNumber1());
        additionalInfoSummary.setReferenceId2(this.getReferenceNumber2());
        additionalInfoSummary.setDescription(getDescription());
        protocolSummary.setAdditionalInfo(additionalInfoSummary);
    }

    protected void addSpecialReviewSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolSpecialReviewBase specialReview : getSpecialReviews()) {
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

    protected void addOrganizationSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolLocationBase organization : this.getProtocolLocations()) {
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

    protected void addFundingSourceSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolFundingSourceBase source : getProtocolFundingSources()) {
            FundingSourceSummary fundingSourceSummary = new FundingSourceSummary();
            fundingSourceSummary.setFundingSourceType(source.getFundingSourceType().getDescription());
            fundingSourceSummary.setFundingSource(source.getFundingSourceNumber());
            fundingSourceSummary.setFundingSourceNumber(source.getFundingSourceNumber());
            fundingSourceSummary.setFundingSourceName(source.getFundingSourceName());
            fundingSourceSummary.setFundingSourceTitle(source.getFundingSourceTitle());
            protocolSummary.add(fundingSourceSummary);
        }
    }

    protected void addAttachmentSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolAttachmentProtocolBase attachment : getActiveAttachmentProtocols()) {
            if (!attachment.isDeleted()) {
                AttachmentSummary attachmentSummary = new AttachmentSummary();
                attachmentSummary.setAttachmentId(attachment.getId());
                attachmentSummary.setFileType(attachment.getFile().getType());
                attachmentSummary.setFileName(attachment.getFile().getName());
                attachmentSummary.setAttachmentType(Constants.PROTOCOL_ATTACHMENT_PREFIX + attachment.getType().getDescription());
                attachmentSummary.setDescription(attachment.getDescription());
                attachmentSummary.setDataLength(attachment.getFile().getData() == null ? 0 : attachment.getFile().getData().length);
                attachmentSummary.setUpdateTimestamp(attachment.getUpdateTimestamp());
                attachmentSummary.setUpdateUser(attachment.getUpdateUser());
                protocolSummary.add(attachmentSummary);
            }
        }
        for (ProtocolPersonBase person : getProtocolPersons()) {
            for (ProtocolAttachmentPersonnelBase attachment : person.getAttachmentPersonnels()) {
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

    protected void addResearchAreaSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolResearchAreaBase researchArea : getProtocolResearchAreas()) {
            ResearchAreaSummary researchAreaSummary = new ResearchAreaSummary();
            researchAreaSummary.setResearchAreaCode(researchArea.getResearchAreaCode());
            researchAreaSummary.setDescription(researchArea.getResearchAreas().getDescription());
            protocolSummary.add(researchAreaSummary);
        }
    }

    protected void addPersonnelSummaries(ProtocolSummary protocolSummary) {
        for (ProtocolPersonBase person : getProtocolPersons()) {
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
            for (ProtocolUnitBase unit : person.getProtocolUnits()) {
                personnelSummary.addUnit(unit.getUnitNumber(), unit.getUnitName());
            }
            protocolSummary.add(personnelSummary);
        }
    }

    protected abstract ProtocolSummary createProtocolSummary();
    
    @Override
    public abstract String getDocumentKey();

    @Override
    public String getDocumentNumberForPermission() {
        return protocolNumber;
    }

    @Override
    public abstract List<String> getRoleNames();
    
    public void resetForeignKeys() {
        for (ProtocolActionBase action : protocolActions) {
            action.resetForeignKeys();
        }
    }
 
    public void resetPersistenceStateForNotifications() {
        for (ProtocolActionBase action : protocolActions) {
            for (KcNotification notification: action.getProtocolNotifications()) {
                notification.resetPersistenceState();
            }
        }
    }

    
    public abstract String getNamespace();    

    @Override
    public String getDocumentUnitNumber() {
        return getLeadUnitNumber();
    }

    @Override
     public abstract String getDocumentRoleTypeCode();

    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        return;
    }

    
    
    
    public boolean isNew() {
        return !isAmendment() && !isRenewal() && !isFYI();
    }
    
   
    public boolean isAmendment() {
        return protocolNumber != null && protocolNumber.contains(ProtocolSpecialVersion.AMENDMENT.getCode());
    }
    
    public boolean isRenewal() {
        return protocolNumber != null && protocolNumber.contains(ProtocolSpecialVersion.RENEWAL.getCode());
    }

    public boolean isFYI() { return protocolNumber != null && protocolNumber.contains(ProtocolSpecialVersion.FYI.getCode()); }

    public boolean isRenewalWithAmendment() {
        return isRenewal() && CollectionUtils.isNotEmpty(this.getProtocolAmendRenewal().getModules());
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
     * @return protocolNumber of the ProtocolBase that is being amended/renewed
     */
    public String getAmendedProtocolNumber() {
        if (isAmendment()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.AMENDMENT.getCode().toString());
            
        } else if (isRenewal()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.RENEWAL.getCode().toString());
                
        } else if (isFYI()) {
            return StringUtils.substringBefore(getProtocolNumber(), ProtocolSpecialVersion.FYI.getCode().toString());
        }
        else {
            return null;
        }
    }
    
    /**
     * Decides whether or not the ProtocolBase is in a state where changes will require versioning.  For example: has the protocol
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
    public List<ProtocolAttachmentProtocolBase> getActiveAttachmentProtocols() {
        List<ProtocolAttachmentProtocolBase> activeAttachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        for (ProtocolAttachmentProtocolBase attachment1 : getAttachmentProtocols()) {
            if (attachment1.isDraft()) {
                activeAttachments.add(attachment1);
            } else if (attachment1.isFinal() || attachment1.isDeleted()) {
            //else if (attachment1.isFinal())) {
                boolean isActive = true;
                for (ProtocolAttachmentProtocolBase attachment2 : getAttachmentProtocols()) {
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
    public List<ProtocolAttachmentProtocolBase> getActiveAttachmentProtocolsNoDelete() {
        List<Integer> documentIds = new ArrayList<Integer>();
        List<ProtocolAttachmentProtocolBase> activeAttachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        for (ProtocolAttachmentProtocolBase attachment : getActiveAttachmentProtocols()) {
            if (attachment.isDeleted()) {
                documentIds.add(attachment.getDocumentId());
            }
        }
        for (ProtocolAttachmentProtocolBase attachment : getActiveAttachmentProtocols()) {
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
            dateTimeService = (DateTimeService) KcServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        if(sequenceAccessorService == null) {
            sequenceAccessorService = (SequenceAccessorService) KcServiceLocator.getService(SequenceAccessorService.class);
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

    public ProtocolAttachmentFilterBase getProtocolAttachmentFilter() {
        return protocolAttachmentFilter;
    }

    public void setProtocolAttachmentFilter(ProtocolAttachmentFilterBase protocolAttachmentFilter) {
        this.protocolAttachmentFilter = protocolAttachmentFilter;
    }
    
    
    /**
     * 
     * This method returns a list of attachments which respect the sort filter
     * @return a filtered list of protocol attachments
     */
    public List<ProtocolAttachmentProtocolBase> getFilteredAttachmentProtocols() {
        List<ProtocolAttachmentProtocolBase> filteredAttachments = new ArrayList<ProtocolAttachmentProtocolBase>();
        ProtocolAttachmentFilterBase attachmentFilter = getProtocolAttachmentFilter();
        if (attachmentFilter != null && StringUtils.isNotBlank(attachmentFilter.getFilterBy())) {            
            for (ProtocolAttachmentProtocolBase attachment1 : getAttachmentProtocols()) {
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

    public abstract void initializeProtocolAttachmentFilter();
    
    public ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);

    }

    public void cleanupSpecialReviews(ProtocolBase srcProtocol) {
        List<ProtocolSpecialReviewBase> srcSpecialReviews = srcProtocol.getSpecialReviews();
        List<ProtocolSpecialReviewBase> dstSpecialReviews = getSpecialReviews();
        for (int i=0; i < srcSpecialReviews.size(); i++) {
            ProtocolSpecialReviewBase srcSpecialReview = srcSpecialReviews.get(i);
            ProtocolSpecialReviewBase dstSpecialReview = dstSpecialReviews.get(i);
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
    public boolean isMemberInProtocolPersonnel(CommitteeMembershipBase member) {
        boolean retValue = false;
        for(ProtocolPersonBase protocolPerson: this.protocolPersons) {
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
    public List<CommitteeMembershipBase> filterOutProtocolPersonnel(List<CommitteeMembershipBase> members) {
        List<CommitteeMembershipBase> filteredMemebers = new ArrayList<CommitteeMembershipBase>();
        for (CommitteeMembershipBase member : members) {
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
        return getTitle();
    }

    @Override
    public String getProjectId() {
        return getProtocolNumber();
    }
 
    
    
    // This is for viewhistory/corespondence to search prev submission
    public List<ProtocolActionBase> getSortedActions() {
        if (sortedActions == null) {
            sortedActions = new ArrayList<ProtocolActionBase>();
            
            for (ProtocolActionBase action : getProtocolActions()) {
                sortedActions.add((ProtocolActionBase) ObjectUtils.deepCopy(action));
            }

            Collections.sort(sortedActions, new Comparator<ProtocolActionBase>() {
                public int compare(ProtocolActionBase action1, ProtocolActionBase action2) {
                    return action1.getActionId().compareTo(action2.getActionId());
                }
            });
            
        }
        return sortedActions;
    }
    
    public boolean isEmptyProtocolResearchAreas() {
        return CollectionUtils.isEmpty(getProtocolResearchAreas());
    }

    /*
     * determine if current user is named on the protocol.
     */
    public boolean isUserNamedInProtocol(String principalName) {
        boolean result = false;
        for (ProtocolPersonBase protocolPerson: getProtocolPersons()) {
            if (principalName.equals(protocolPerson.getUserName())) {
                result = true;
            }
        }
        return result;
    }
    
    
    public void reconcileActionsWithSubmissions() {
        HashMap<Integer, ProtocolSubmissionBase> submissionNumberMap = new HashMap<Integer, ProtocolSubmissionBase>();
        for(ProtocolSubmissionBase submission : getProtocolSubmissions()) {
            submissionNumberMap.put(submission.getSubmissionNumber(), submission);
        }
        
        for(ProtocolActionBase action : getProtocolActions()) {
            if(action.getSubmissionNumber() != null && !action.getSubmissionNumber().equals(0)) {
                if(submissionNumberMap.get(action.getSubmissionNumber()) != null) {
                    action.setSubmissionIdFk(submissionNumberMap.get(action.getSubmissionNumber()).getSubmissionId());
                    action.setProtocolSubmission(submissionNumberMap.get(action.getSubmissionNumber()));
                }
            }
        }
    }

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

    @Override
    protected void prePersist() {
    	super.prePersist();
    	setCreateUser(getUpdateUser());
    	setCreateTimestamp(getUpdateTimestamp());
    }
}
