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
package org.kuali.kra.irb.actions.amendrenew;

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class ProtocolMergeTest extends KcIntegrationTestBase {
    
    private static final long DAY = 86400000L;
    
    private static final String TITLE = "Amendment Title"; 
    private static final String PROTOCOL_TYPE_CODE = "1";
    private static final String DESCRIPTION = "amendment description";
    private static final Date INITIAL_SUBMISSION_DATE = new Date(System.currentTimeMillis() - (2 * DAY));
    private static final Date APPROVAL_DATE = new Date(System.currentTimeMillis() - DAY);
    private static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + (2 * DAY));
    private static final Date LAST_APPROVAL_DATE = new Date(System.currentTimeMillis());
    private static final String FDA_NUM = "777";
    private static final String REF1 = "amendment ref 1";
    private static final String REF2 = "amendment ref 2";
    
    private static final String UNIT_FUNDING_SOURCE = "2";
    private static final String OTHER_FUNDING_SOURCE = "3";
    
    private static final String RESEARCH_AREA_1 = "47.0699";
    private static final String RESEARCH_AREA_2 = "41.0699";
    
    private static final String PARTICIPANT_TYPE_1 = "1";
    private static final String PARTICIPANT_TYPE_2 = "2";
    
    private static final String REF_KEY_1 = "1";
    private static final String REF_KEY_2 = "2";
    
    private static final Integer ROLODEX_ID_1 = 3;
    private static final Integer ROLODEX_ID_2 = 5;
    
    private static final String NAME_1 = "Donald";
    private static final String NAME_2 = "Molly";
    
    private static final String SPECIAL_REVIEW_CODE_1 = "2";
    private static final String SPECIAL_REVIEW_CODE_2 = "9";
    
    private static final String DESCRIPTION_1 = "another description 1";
    private static final String DESCRIPTION_2 = "another description 2";
    
    @Test
    public void testGeneralInfoMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.GENERAL_INFO);
        
        amendment.setTitle(TITLE);
        amendment.setProtocolTypeCode(PROTOCOL_TYPE_CODE);
        amendment.setDescription(DESCRIPTION);
        amendment.setInitialSubmissionDate(INITIAL_SUBMISSION_DATE);
        amendment.setApprovalDate(APPROVAL_DATE);
        amendment.setExpirationDate(EXPIRATION_DATE);
        amendment.setLastApprovalDate(LAST_APPROVAL_DATE);
        amendment.setFdaApplicationNumber(FDA_NUM);
        amendment.setReferenceNumber1(REF1);
        amendment.setReferenceNumber2(REF2);
        
        protocol.merge(amendment);
        assertEquals(TITLE, protocol.getTitle());
        assertEquals(PROTOCOL_TYPE_CODE, protocol.getProtocolTypeCode());
        assertEquals(DESCRIPTION, protocol.getDescription());
        assertEquals(INITIAL_SUBMISSION_DATE, protocol.getInitialSubmissionDate());
        assertEquals(APPROVAL_DATE, protocol.getApprovalDate());
        assertEquals(EXPIRATION_DATE, protocol.getExpirationDate());
        assertEquals(LAST_APPROVAL_DATE, protocol.getLastApprovalDate());
        assertEquals(FDA_NUM, protocol.getFdaApplicationNumber());
        assertEquals(REF1, protocol.getReferenceNumber1());
        assertEquals(REF2, protocol.getReferenceNumber2());
    }
    
    @Test
    public void testFundingSourceMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.FUNDING_SOURCE);
        
        ProtocolFundingSource fundingSource = createFundingSource(UNIT_FUNDING_SOURCE);
        amendment.getProtocolFundingSources().add(fundingSource);
        fundingSource = createFundingSource(OTHER_FUNDING_SOURCE);
        amendment.getProtocolFundingSources().add(fundingSource);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolFundingSources().size());
        assertEquals(UNIT_FUNDING_SOURCE, protocol.getProtocolFundingSources().get(0).getFundingSourceTypeCode());
        assertEquals(OTHER_FUNDING_SOURCE, protocol.getProtocolFundingSources().get(1).getFundingSourceTypeCode());
    }
      
    private ProtocolFundingSource createFundingSource(String fundingSourceType) {
        ProtocolFundingSource fundingSource = new ProtocolFundingSource();
        fundingSource.setFundingSourceTypeCode(fundingSourceType);
        return fundingSource;
    }
    
    @Test
    public void testResearchAreaMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.AREAS_OF_RESEARCH);
        
        ProtocolResearchArea ra = createResearchArea(RESEARCH_AREA_1);
        amendment.getProtocolResearchAreas().add(ra);
        ra = createResearchArea(RESEARCH_AREA_2);
        amendment.getProtocolResearchAreas().add(ra);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolResearchAreas().size());
        assertEquals(RESEARCH_AREA_1, protocol.getProtocolResearchAreas().get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_2, protocol.getProtocolResearchAreas().get(1).getResearchAreaCode());
    }
    
    private ProtocolResearchArea createResearchArea(String researchAreaCode) {
        ProtocolResearchArea ra = new ProtocolResearchArea();
        ra.setResearchAreaCode(researchAreaCode);
        return ra;
    }
    
    @Test
    public void testSubjectsMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.SUBJECTS);
        
        ProtocolParticipant subject = createParticipant(PARTICIPANT_TYPE_1);
        amendment.getProtocolParticipants().add(subject);
        subject = createParticipant(PARTICIPANT_TYPE_2);
        amendment.getProtocolParticipants().add(subject);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolParticipants().size());
        assertEquals(PARTICIPANT_TYPE_1, protocol.getProtocolParticipants().get(0).getParticipantTypeCode());
        assertEquals(PARTICIPANT_TYPE_2, protocol.getProtocolParticipants().get(1).getParticipantTypeCode());
    }

    private ProtocolParticipant createParticipant(String typeCode) {
        ProtocolParticipant pp = new ProtocolParticipant();
        pp.setParticipantTypeCode(typeCode);
        return pp;
    }
    
    @Test
    public void testReferencesMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.PROTOCOL_REFERENCES);
        
        ProtocolReference ref = createReference(REF_KEY_1);
        amendment.getProtocolReferences().add(ref);
        ref = createReference(REF_KEY_2);
        amendment.getProtocolReferences().add(ref);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolReferences().size());
        assertEquals(REF_KEY_1, protocol.getProtocolReferences().get(0).getReferenceKey());
        assertEquals(REF_KEY_2, protocol.getProtocolReferences().get(1).getReferenceKey());
    }

    private ProtocolReference createReference(String refKey) {
        ProtocolReference ref = new ProtocolReference();
        ref.setReferenceKey(refKey);
        return ref;
    }
    
    @Test
    public void testOrganizationsMerge() {
        Protocol protocol = createProtocol();
        KcServiceLocator.getService(ProtocolLocationService.class).addDefaultProtocolLocation(protocol);
        Protocol amendment = createAmendment(ProtocolModule.PROTOCOL_ORGANIZATIONS);
        
        ProtocolLocation loc = createLocation(ROLODEX_ID_1);
        amendment.getProtocolLocations().add(loc);
        loc = createLocation(ROLODEX_ID_2);
        amendment.getProtocolLocations().add(loc);
        
        protocol.merge(amendment);
        
        assertEquals(3, protocol.getProtocolLocations().size());
        assertEquals(ROLODEX_ID_1, protocol.getProtocolLocations().get(1).getRolodexId());
        assertEquals(ROLODEX_ID_2, protocol.getProtocolLocations().get(2).getRolodexId());
    }

    private ProtocolLocation createLocation(Integer rolodexId) {
        ProtocolLocation loc = new ProtocolLocation();
        loc.setRolodexId(rolodexId);
        return loc;
    }
    
    @Test
    public void testPersonnelMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.PROTOCOL_PERSONNEL);
        
        ProtocolPerson person = createPerson(NAME_1);
        person.setPersonId("1");
        person.setProtocolPersonRoleId("PI");
        amendment.getProtocolPersons().add(person);
        person = createPerson(NAME_2);
        person.setPersonId("2");
        person.setProtocolPersonRoleId("COI");
        amendment.getProtocolPersons().add(person);

        ProtocolAttachmentPersonnel pal = createPersonnelAttachment(DESCRIPTION_1);
        person.setAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnelBase>());
        amendment.getAttachmentPersonnels().add(pal);
        person.getAttachmentPersonnels().add(pal);
        pal = createPersonnelAttachment(DESCRIPTION_2);
        amendment.getAttachmentPersonnels().add(pal);
        person.getAttachmentPersonnels().add(pal);

        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolPersons().size());
        assertEquals(NAME_1, protocol.getProtocolPersons().get(0).getPersonName());
        assertEquals(NAME_2, protocol.getProtocolPersons().get(1).getPersonName());
        
        assertEquals(2, protocol.getAttachmentPersonnels().size());
        assertEquals(DESCRIPTION_1, protocol.getAttachmentPersonnels().get(0).getDescription());
        assertEquals(DESCRIPTION_2, protocol.getAttachmentPersonnels().get(1).getDescription());
    }

    private ProtocolPerson createPerson(String personName) {
        ProtocolPerson person = new ProtocolPerson();
        person.setPersonName(personName);
        return person;
    }
    
    @Test
    public void testSpecialReviewMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.SPECIAL_REVIEW);
        
        ProtocolSpecialReview sr = createSpecialReview(SPECIAL_REVIEW_CODE_1);
        amendment.getSpecialReviews().add(sr);
        sr = createSpecialReview(SPECIAL_REVIEW_CODE_2);
        amendment.getSpecialReviews().add(sr);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getSpecialReviews().size());
        assertEquals(SPECIAL_REVIEW_CODE_1, protocol.getSpecialReviews().get(0).getSpecialReviewTypeCode());
        assertEquals(SPECIAL_REVIEW_CODE_2, protocol.getSpecialReviews().get(1).getSpecialReviewTypeCode());
    }

    @Test
    public void testSubmissionMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.SPECIAL_REVIEW);

        ProtocolSubmission protocolSubmission = new ProtocolSubmission();
        protocolSubmission.setProtocolNumber(amendment.getProtocolNumber());
        CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute("3");
        committeeScheduleMinute.setProtocolNumber(amendment.getProtocolNumber());
        protocolSubmission.getCommitteeScheduleMinutes().add(committeeScheduleMinute);

        amendment.setProtocolSubmission(protocolSubmission);
        amendment.getProtocolSubmissions().add(protocolSubmission);

        protocol.merge(amendment);

        assertEquals(amendment.getProtocolSubmission().getCommitteeScheduleMinutes().size(), protocol.getProtocolSubmission().getCommitteeScheduleMinutes().size());
        assertEquals(amendment.getProtocolNumber(), amendment.getProtocolSubmission().getCommitteeScheduleMinutes().get(0).getProtocolNumber());
        assertEquals(protocol.getProtocolNumber(), protocol.getProtocolSubmission().getCommitteeScheduleMinutes().get(0).getProtocolNumber());


    }
    
    private ProtocolSpecialReview createSpecialReview(String specialReviewCode) {
        ProtocolSpecialReview sr = new ProtocolSpecialReview();
        sr.setSpecialReviewTypeCode(specialReviewCode);
        return sr;
    }
    
    @Test
    public void testAttachmentsMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
        
        ProtocolAttachmentProtocol pap = createProtocolAttachment(NAME_1);
        amendment.getAttachmentProtocols().add(pap);
        pap = createProtocolAttachment(NAME_2);
        amendment.getAttachmentProtocols().add(pap);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getAttachmentProtocols().size());
        assertEquals(NAME_1, protocol.getAttachmentProtocols().get(0).getContactName());
        assertEquals(NAME_2, protocol.getAttachmentProtocols().get(1).getContactName());
    }
    

    private ProtocolAttachmentPersonnel createPersonnelAttachment(String description) {
        ProtocolAttachmentPersonnel pal = new ProtocolAttachmentPersonnel();
        pal.setDescription(description);
        return pal;
    }

    private ProtocolAttachmentProtocol createProtocolAttachment(String name) {
        ProtocolAttachmentProtocol pap = new ProtocolAttachmentProtocol();
        pap.setContactName(name);
        pap.setDocumentStatusCode("1");
        return pap;
    }

    /**
     * Create a amendment protocol.
     * @return
     */
    private Protocol createProtocol() {

        try {
            ProtocolDocument document = ProtocolFactory.createProtocolDocument("0906000001");
            Protocol protocol = document.getProtocol();
            protocol.setProtocolId(1L);
            return protocol;
        } catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Create a amendment protocol.
     * @return
     */
    private Protocol createAmendment(String moduleTypeCode) {
        final Protocol protocol = new Protocol();
        KcServiceLocator.getService(ProtocolLocationService.class).addDefaultProtocolLocation(protocol);
        protocol.setProtocolId(2L);
        protocol.setProtocolNumber("0906000001A001");
        addModule(protocol, moduleTypeCode);
        List<ProtocolActionBase> actions = new ArrayList<ProtocolActionBase>();
        ProtocolAction action1 = new ProtocolAction();
        action1.setActionId(1);
        actions.add(action1);
        ProtocolAction action2 = new ProtocolAction();
        action2.setActionId(2);
        actions.add(action2);
        protocol.setProtocolActions(actions);
        
        return protocol;
    }
    
    /**
     * Add an amendment entry to the protocol along with a module.
     * @param protocol
     * @param moduleTypeCode
     */
    private void addModule(final Protocol protocol, String moduleTypeCode) {
        final ProtocolAmendRenewal amendRenewal = new ProtocolAmendRenewal();
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolModuleTypeCode(moduleTypeCode);
        amendRenewal.addModule(module);
        protocol.setProtocolAmendRenewal(amendRenewal);
    }
}
