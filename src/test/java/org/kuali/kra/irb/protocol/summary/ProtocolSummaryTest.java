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
package org.kuali.kra.irb.protocol.summary;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolOrganizationType;
import org.kuali.kra.irb.protocol.participant.ParticipantType;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewExemption;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class ProtocolSummaryTest extends KcUnitTestBase {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private static final String PROTOCOL_NUMBER = "666";
    private static final String PRINCIPAL_INVESTIGATOR_NAME = "Terry Durkin";
    private static final String FDA_APP_NUMBER = "999";
    private static final String PROTOCOL_TYPE = "test";
    private static final String PROTOCOL_STATUS = "my status";
    private static final String TITLE = "my title";

    private static final long TEN_DAYS = 1000L * 60L * 60L * 24L * 10L; // msec

    private static final String REFERENCE_NUMBER_1 = "ref 1";
    private static final String REFERENCE_NUMBER_2 = "ref 2";
    private static final String DESCRIPTION = "whatever";
    private static final Object PRINCIPAL_INVESTIGATOR = "Principal Investigator";
    private static final Object UNIT_NUMBER = "BL-BL";
    private static final Object UNIT_NAME = "BLOOMINGTON CAMPUS";

    private static final String RA_DESCRIPTION = "Rocket Science";
    private static final String RA_CODE = "09.101";
    private static final String FILE_NAME = "foo.doc";

    private static final String FUNDING_SOURCE_NUMBER = "333";
    private static final String FUNDING_SOURCE_NAME = "funding name";
    private static final String FUNDING_SOURCE_TITLE = "funding title";
    private static final String FUNDING_DESCRIPTION = "funding description";

    private static final String PARTICIPANT_DESCRIPTION = "participant description";
    private static final Integer PARTICIPANT_COUNT = 5;

    private static final String ORGANIZATION_NAME = "org name";
    private static final String ORGANIZATION_TYPE = "org type";
    private static final String ORGANIZATION_FWA_NUMBER = "org fwa number";

    private static final String SPECIAL_REVIEW_DESCRIPTION = "sp description";
    private static final String SPECIAL_REVIEW_APPROVAL_TYPE_DESCRIPTION = "sp approval type";
    private static final String EXEMPTION_DESCRIPTION = "E1";
    private static final String COMMENT = "sp comment";
    
    private static final String NEW_PRINCIPAL_INVESTIGATOR_ID = "000000003";
    private static final String NEW_PRINCIPAL_INVESTIGATOR_NAME = "Molly Dog";
    private static final String NEW_PRINCIPAL_INVESTIGATOR_UNIT = "000001";
    private static final String NEW_PRINCIPAL_INVESTIGATOR_ROLE = "PI";
    private static final String NEW_REFERENCE_UNIT = "unit";
    
    private ProtocolDocument protocolDocument;
    private Date initialSubmissionDate;
    private Date approvalDate;
    private Date lastApprovalDate;
    private Date expirationDate;
    private ProtocolType protocolType;
    private ProtocolStatus protocolStatus;
    private ResearchArea researchArea;
    private FundingSourceType fundingType;
    private ParticipantType participantType;
    private Organization organization;
    private ProtocolOrganizationType organizationType;
    private SpecialReviewType mySpecialReviewType;
    private Date specialReviewApplicationDate;
    private SpecialReviewApprovalType specialReviewApprovalType;
    private List<ProtocolSpecialReviewExemption> specialReviewExemptions;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        initialSubmissionDate = new Date(System.currentTimeMillis() - (2 * TEN_DAYS));
        approvalDate = new Date(System.currentTimeMillis() - TEN_DAYS);
        lastApprovalDate = new Date(System.currentTimeMillis());
        expirationDate = new Date(System.currentTimeMillis() + TEN_DAYS);
        
        protocolType = new ProtocolType();
        protocolType.setDescription(PROTOCOL_TYPE);
        
        protocolStatus = new ProtocolStatus();
        protocolStatus.setDescription(PROTOCOL_STATUS);
        
        researchArea = new ResearchArea();
        researchArea.setDescription(RA_DESCRIPTION);
        researchArea.setResearchAreaCode(RA_CODE);
        
        fundingType = new FundingSourceType();
        fundingType.setDescription(FUNDING_DESCRIPTION);
        
        participantType = new ParticipantType();
        participantType.setDescription(PARTICIPANT_DESCRIPTION);
        
        organizationType = new ProtocolOrganizationType();
        organizationType.setDescription(ORGANIZATION_TYPE);
        
        organization = new Organization();
        organization.setOrganizationName(ORGANIZATION_NAME);
        organization.setHumanSubAssurance(ORGANIZATION_FWA_NUMBER);
        
        mySpecialReviewType = new SpecialReviewType();
        mySpecialReviewType.setDescription(SPECIAL_REVIEW_DESCRIPTION);
        
        specialReviewApplicationDate = new Date(System.currentTimeMillis() - (2 * TEN_DAYS));
        
        specialReviewApprovalType = new SpecialReviewApprovalType();
        specialReviewApprovalType.setDescription(SPECIAL_REVIEW_APPROVAL_TYPE_DESCRIPTION);
        
        specialReviewExemptions = new ArrayList<ProtocolSpecialReviewExemption>();
        ProtocolSpecialReviewExemption specialReviewExemption = new ProtocolSpecialReviewExemption();
        ExemptionType exemptionType = new ExemptionType();
        exemptionType.setDescription(EXEMPTION_DESCRIPTION);
        specialReviewExemption.setExemptionType(exemptionType);
        specialReviewExemptions.add(specialReviewExemption);
        
        protocolDocument = createProtocolDocument();
    }

    @Test
    public void testSummaryCreation() {
        ProtocolSummary summary = protocolDocument.getProtocol().getProtocolSummary();
        assertNotNull(summary);
        
        assertEquals(PROTOCOL_NUMBER, summary.getProtocolNumber());
        assertEquals(dateFormat.format(initialSubmissionDate), summary.getInitialSubmissionDate());
        assertEquals(dateFormat.format(approvalDate), summary.getApprovalDate());
        assertEquals(dateFormat.format(lastApprovalDate), summary.getLastApprovalDate());
        assertEquals(dateFormat.format(expirationDate), summary.getExpirationDate());
        
        assertEquals(protocolType.getDescription(), summary.getType());
        assertEquals(protocolStatus.getDescription(), summary.getStatus());
        
        assertEquals(TITLE, summary.getTitle());
        assertEquals(PRINCIPAL_INVESTIGATOR_NAME, summary.getPiName());
        
        assertEquals(FDA_APP_NUMBER, summary.getAdditionalInfo().getFdaApplicationNumber());
        assertEquals(REFERENCE_NUMBER_1, summary.getAdditionalInfo().getReferenceId1());
        assertEquals(REFERENCE_NUMBER_2, summary.getAdditionalInfo().getReferenceId2());
        assertEquals(DESCRIPTION, summary.getAdditionalInfo().getDescription());
        
        assertEquals(1, summary.getPersons().size());
        assertEquals(PRINCIPAL_INVESTIGATOR_NAME, summary.getPersons().get(0).getName());
        assertEquals(PRINCIPAL_INVESTIGATOR, summary.getPersons().get(0).getRoleName());
        assertEquals("", summary.getPersons().get(0).getAffiliation());
        assertEquals(1, summary.getPersons().get(0).getUnits().size());
        assertEquals(UNIT_NUMBER, summary.getPersons().get(0).getUnits().get(0).getUnitNumber());
        assertEquals(UNIT_NAME, summary.getPersons().get(0).getUnits().get(0).getUnitName());
        
        assertEquals(1, summary.getResearchAreas().size());
        assertEquals(RA_CODE, summary.getResearchAreas().get(0).getResearchAreaCode());
        assertEquals(RA_DESCRIPTION, summary.getResearchAreas().get(0).getDescription());
        
        assertEquals(1, summary.getAttachments().size());
        assertEquals(FILE_NAME, summary.getAttachments().get(0).getFileName());
        
        assertEquals(1, summary.getFundingSources().size());
        assertEquals(FUNDING_SOURCE_NUMBER, summary.getFundingSources().get(0).getFundingSource());
        assertEquals(FUNDING_SOURCE_NAME, summary.getFundingSources().get(0).getFundingSourceName());
        assertEquals(FUNDING_DESCRIPTION, summary.getFundingSources().get(0).getFundingSourceType());
        assertEquals(FUNDING_SOURCE_TITLE, summary.getFundingSources().get(0).getFundingSourceTitle());
        
        assertEquals(1, summary.getParticipants().size());
        assertEquals(PARTICIPANT_DESCRIPTION, summary.getParticipants().get(0).getDescription());
        assertEquals(PARTICIPANT_COUNT.toString(), summary.getParticipants().get(0).getCount());
        
        assertEquals(2, summary.getOrganizations().size());
        assertEquals(ORGANIZATION_NAME, summary.getOrganizations().get(1).getName());
        assertEquals(ORGANIZATION_TYPE, summary.getOrganizations().get(1).getType());
        assertEquals(ORGANIZATION_FWA_NUMBER, summary.getOrganizations().get(1).getFwaNumber());
        
        assertEquals(1, summary.getSpecialReviews().size());
        assertEquals(dateFormat.format(specialReviewApplicationDate), summary.getSpecialReviews().get(0).getApplicationDate());
        assertEquals(dateFormat.format(approvalDate), summary.getSpecialReviews().get(0).getApprovalDate());
        assertEquals(dateFormat.format(expirationDate), summary.getSpecialReviews().get(0).getExpirationDate());
        assertEquals(PROTOCOL_NUMBER, summary.getSpecialReviews().get(0).getProtocolNumber());
        assertEquals(COMMENT, summary.getSpecialReviews().get(0).getComment());
        assertEquals(SPECIAL_REVIEW_APPROVAL_TYPE_DESCRIPTION, summary.getSpecialReviews().get(0).getApprovalStatus());
        assertEquals(SPECIAL_REVIEW_DESCRIPTION, summary.getSpecialReviews().get(0).getType());
        assertEquals(EXEMPTION_DESCRIPTION, summary.getSpecialReviews().get(0).getExemptionNumbers());
    }
    
    @Test
    public void testCompareForNoChanges() {
        ProtocolSummary summary1 = protocolDocument.getProtocol().getProtocolSummary();
        ProtocolSummary summary2 = protocolDocument.getProtocol().getProtocolSummary();
        
        summary1.compare(summary2);
        verifyComparison(summary1, false);
        
        summary2.compare(summary1);
        verifyComparison(summary2, false);
    }
    
    @Test
    public void testCompareForChanges() throws Exception {
        ProtocolSummary summary1 = protocolDocument.getProtocol().getProtocolSummary();
        
        Protocol protocol = ProtocolFactory.createProtocolDocument("999").getProtocol();
        changePI(protocol);
        ProtocolSummary summary2 = protocol.getProtocolSummary();
        
        summary1.compare(summary2);
        verifyComparison(summary1, true);
        
        summary2.compare(summary1);
        verifyComparison(summary2, true);
    }
    
    private void verifyComparison(ProtocolSummary summary, boolean expected) {
        assertEquals(expected, summary.isProtocolNumberChanged());
        assertEquals(expected, summary.isInitialSubmissionDateChanged());
        assertEquals(expected, summary.isApprovalDateChanged());
        assertEquals(expected, summary.isLastApprovalDateChanged());
        assertEquals(expected, summary.isExpirationDateChanged());
        assertEquals(expected, summary.isTypeChanged());
        assertEquals(expected, summary.isStatusChanged());
        assertEquals(expected, summary.isTitleChanged());
        assertEquals(expected, summary.isPiNameChanged());
        
        assertEquals(expected, summary.getAdditionalInfo().isFdaApplicationNumberChanged());
        assertEquals(expected, summary.getAdditionalInfo().isReferenceId1Changed());
        assertEquals(expected, summary.getAdditionalInfo().isReferenceId2Changed());
        assertEquals(expected, summary.getAdditionalInfo().isDescriptionChanged());
        
        if (summary.getPersons().size() > 0) {
            assertEquals(expected, summary.getPersons().get(0).isNameChanged());
            assertEquals(expected, summary.getPersons().get(0).isRoleNameChanged());
            assertEquals(expected, summary.getPersons().get(0).isAffiliationChanged());
            assertEquals(expected, summary.getPersons().get(0).getUnits().get(0).isChanged());
        }
        
        if (summary.getResearchAreas().size() > 0) {
            assertEquals(expected, summary.getResearchAreas().get(0).isChanged());
        }
        
        if (summary.getAttachments().size() > 0) {
            assertEquals(expected, summary.getAttachments().get(0).isFileNameChanged());
        }
        
        if (summary.getFundingSources().size() > 0) {
            assertEquals(expected, summary.getFundingSources().get(0).isFundingSourceChanged());
            assertEquals(expected, summary.getFundingSources().get(0).isFundingSourceNameChanged());
            assertEquals(expected, summary.getFundingSources().get(0).isFundingSourceTypeChanged());
            assertEquals(expected, summary.getFundingSources().get(0).isFundingSourceTitleChanged());
        }
        
        if (summary.getParticipants().size() > 0) {
            assertEquals(expected, summary.getParticipants().get(0).isDescriptionChanged());
            assertEquals(expected, summary.getParticipants().get(0).isCountChanged());
        }
        
        if (summary.getOrganizations().size() > 0) {
            assertEquals(false, summary.getOrganizations().get(0).isNameChanged());
            assertEquals(false, summary.getOrganizations().get(0).isTypeChanged());
            assertEquals(false, summary.getOrganizations().get(0).isFwaNumberChanged());
            if (summary.getOrganizations().size() > 1) {
                assertEquals(expected, summary.getOrganizations().get(1).isNameChanged());
                assertEquals(expected, summary.getOrganizations().get(1).isTypeChanged());
                assertEquals(expected, summary.getOrganizations().get(1).isFwaNumberChanged());
            }
        }
        
        if (summary.getSpecialReviews().size() > 0) {
            assertEquals(expected, summary.getSpecialReviews().get(0).isApplicationDateChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isApprovalDateChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isExpirationDateChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isProtocolNumberChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isCommentChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isApprovalStatusChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isTypeChanged());
            assertEquals(expected, summary.getSpecialReviews().get(0).isExemptionNumbersChanged());
        }
    }

    private ProtocolDocument createProtocolDocument() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        Protocol protocol = protocolDocument.getProtocol();
        
        protocol.setProtocolNumber(PROTOCOL_NUMBER);
        protocol.setInitialSubmissionDate(initialSubmissionDate);
        protocol.setApprovalDate(approvalDate);
        protocol.setLastApprovalDate(lastApprovalDate);
        protocol.setProtocolType(protocolType);
        protocol.setExpirationDate(expirationDate);
        protocol.setProtocolStatus(protocolStatus);
        protocol.setTitle(TITLE);
        
        protocol.setFdaApplicationNumber(FDA_APP_NUMBER);
        protocol.setReferenceNumber1(REFERENCE_NUMBER_1);
        protocol.setReferenceNumber2(REFERENCE_NUMBER_2);
        protocol.setDescription(DESCRIPTION);
        
        ProtocolResearchArea ras = new ProtocolResearchArea();
        ras.setResearchAreaCode(RA_CODE);
        ras.setResearchAreas(researchArea);
        protocol.getProtocolResearchAreas().add(ras);
        
        ProtocolAttachmentProtocol attachment = new ProtocolAttachmentProtocol();
        AttachmentFile file = new AttachmentFile();
        file.setName(FILE_NAME);
        attachment.setDocumentStatusCode("1");
        attachment.setDescription("protocol attachment");
        ProtocolAttachmentType attachmentType = new ProtocolAttachmentType();
        attachmentType.setDescription("Test Attach type");
        attachment.setType(attachmentType);
        
        attachment.setFile(file);
        protocol.getAttachmentProtocols().add(attachment);
        
        ProtocolFundingSource fundingSource = new ProtocolFundingSource();
        fundingSource.setFundingSourceType(fundingType);
        fundingSource.setFundingSourceNumber(FUNDING_SOURCE_NUMBER);
        fundingSource.setFundingSourceName(FUNDING_SOURCE_NAME);
        fundingSource.setFundingSourceTitle(FUNDING_SOURCE_TITLE);
        protocol.getProtocolFundingSources().add(fundingSource);
        
        ProtocolParticipant participant = new ProtocolParticipant();
        participant.setParticipantType(participantType);
        participant.setParticipantCount(PARTICIPANT_COUNT);
        protocol.getProtocolParticipants().add(participant);
        
        ProtocolLocation location = new ProtocolLocation();
        location.setProtocolOrganizationType(organizationType);
        location.setOrganization(organization);
        protocol.getProtocolLocations().add(location);
        
        ProtocolSpecialReview specialReview = new ProtocolSpecialReview();
        specialReview.setApplicationDate(specialReviewApplicationDate);
        specialReview.setApprovalDate(approvalDate);
        specialReview.setExpirationDate(expirationDate);
        specialReview.setProtocolNumber(PROTOCOL_NUMBER);
        specialReview.setComments(COMMENT);
        specialReview.setSpecialReviewType(mySpecialReviewType);
        specialReview.setApprovalType(specialReviewApprovalType);
        specialReview.setSpecialReviewExemptions(specialReviewExemptions);
        protocol.getSpecialReviews().add(specialReview);
        
        return protocolDocument;
    }
    
    private void changePI(Protocol protocol) {
        ProtocolPerson protocolPerson = ProtocolFactory.getProtocolPerson(NEW_PRINCIPAL_INVESTIGATOR_ID, NEW_PRINCIPAL_INVESTIGATOR_NAME, NEW_PRINCIPAL_INVESTIGATOR_ROLE, protocol.getProtocolNumber());
        
        AffiliationType affiliationType = new AffiliationType();
        affiliationType.setAffiliationTypeCode(4);
        affiliationType.setDescription("another affiliation");
        protocolPerson.setAffiliationType(affiliationType);
        protocolPerson.setAffiliationTypeCode(4);
        
        ProtocolUnit protocolUnit = new ProtocolUnit();
        protocolUnit.setUnitNumber(NEW_PRINCIPAL_INVESTIGATOR_UNIT);
        protocolUnit.setLeadUnitFlag(true);
        protocolUnit.setProtocolNumber(protocol.getProtocolNumber());
        protocolUnit.setSequenceNumber(0);
        protocolUnit.refreshReferenceObject(NEW_REFERENCE_UNIT);
        protocolPerson.getProtocolUnits().clear();
        protocolPerson.getProtocolUnits().add(protocolUnit);
        
        protocol.getProtocolPersons().clear();
        protocol.getProtocolPersons().add(protocolPerson);
        protocol.setLeadUnitNumber(NEW_PRINCIPAL_INVESTIGATOR_UNIT);
        protocol.setPrincipalInvestigatorId(NEW_PRINCIPAL_INVESTIGATOR_ID);
    }
}
