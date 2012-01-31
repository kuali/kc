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
package org.kuali.kra.iacuc;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.kuali.kra.irb.ProtocolDocument;
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
public class IacucProtocol extends KraPersistableBusinessObjectBase implements SequenceOwner<IacucProtocol>, 
                                                                          Permissionable,
                                                                          UnitAclLoadable,
                                                                          Disclosurable {
    
    private Long protocolId; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private String protocolTypeCode; 
    private String projectTypeCode; 
    private String protocolStatusCode; 
    private String title; 
    private String description; 
    private Date initialSubmissionDate;
    private Date applicationDate; 
    private Date approvalDate; 
    private Date expirationDate; 
    private String fdaApplicationNumber; 
    private String referenceNumber1; 
    private String referenceNumber2; 
    private boolean isBillable; 
    private String specialReviewIndicator; 
    private String keyStudyPersonIndicator; 
    private String fundingSourceIndicator; 
    private String correspondentIndicator; 
    private String referenceIndicator; 
    private String layStatement1; 
    private String layStatement2; 
    private Date lastApprovalDate; 
    private String overviewTimeline; 
    private String speciesStudyGroupIndicator; 
    private String alternativeSearchIndicator; 
    private String scientificJustifIndicator; 
    IacucProtocolDocument iacucProtocolDocument;
    private boolean active;
    private Timestamp createTimestamp;

    private String createUser;

    public IacucProtocol() { 
        // TODO : temporary only; remove this when protocol is ready
        initializaTestData();
    } 
    
    private void initializaTestData() {
        // TODO : this is just for plumbing work.  remove it when working on required fields tab.
        Long protNumber = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        setProtocolNumber(protNumber.toString());
        setSequenceNumber(0);
        setProtocolStatusCode("100");
//        setProjectTypeCode("1");
        setApplicationDate(new Date(new java.util.Date().getTime()));
        setCreateTimestamp(new Timestamp(new java.util.Date().getTime()));
        setCreateUser("test");
        setScientificJustifIndicator("no");
        setSpecialReviewIndicator("no");
        setSpeciesStudyGroupIndicator("no");
        setKeyStudyPersonIndicator("no");
        setFundingSourceIndicator("no");
        setCorrespondentIndicator("no");
        setReferenceIndicator("no");
        setAlternativeSearchIndicator("no");
        
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

    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getProjectTypeCode() {
        return projectTypeCode;
    }

    public void setProjectTypeCode(String projectTypeCode) {
        this.projectTypeCode = projectTypeCode;
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

    public boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
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

    public String getLayStatement1() {
        return layStatement1;
    }

    public void setLayStatement1(String layStatement1) {
        this.layStatement1 = layStatement1;
    }

    public String getLayStatement2() {
        return layStatement2;
    }

    public void setLayStatement2(String layStatement2) {
        this.layStatement2 = layStatement2;
    }

    public Date getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public String getOverviewTimeline() {
        return overviewTimeline;
    }

    public void setOverviewTimeline(String overviewTimeline) {
        this.overviewTimeline = overviewTimeline;
    }

    public String getSpeciesStudyGroupIndicator() {
        return speciesStudyGroupIndicator;
    }

    public void setSpeciesStudyGroupIndicator(String speciesStudyGroupIndicator) {
        this.speciesStudyGroupIndicator = speciesStudyGroupIndicator;
    }

    public String getAlternativeSearchIndicator() {
        return alternativeSearchIndicator;
    }

    public void setAlternativeSearchIndicator(String alternativeSearchIndicator) {
        this.alternativeSearchIndicator = alternativeSearchIndicator;
    }

    public String getScientificJustifIndicator() {
        return scientificJustifIndicator;
    }

    public void setScientificJustifIndicator(String scientificJustifIndicator) {
        this.scientificJustifIndicator = scientificJustifIndicator;
    }

    @Override
    public void setSequenceOwner(IacucProtocol newlyVersionedOwner) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IacucProtocol getSequenceOwner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void resetPersistenceState() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getProjectName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getProjectId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentUnitNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentNumberForPermission() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getRoleNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLeadUnitNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentRoleTypeCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void incrementSequenceNumber() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Integer getOwnerSequenceNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getVersionNameField() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setIacucProtocolDocument(IacucProtocolDocument iacucProtocolDocument) {
        this.iacucProtocolDocument = iacucProtocolDocument;
    }

    public IacucProtocolDocument getIacucProtocolDocument() {
        return iacucProtocolDocument;
    }

    public void setBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getInitialSubmissionDate() {
        return initialSubmissionDate;
    }

    public void setInitialSubmissionDate(Date initialSubmissionDate) {
        this.initialSubmissionDate = initialSubmissionDate;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


}
