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
package org.kuali.kra.irb.actions.amendrenew;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentNotification;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.participant.ProtocolParticipant;
import org.kuali.kra.irb.protocol.reference.ProtocolReference;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReview;

public class ProtocolMergeTest extends KraTestBase {
    
    private static final String TITLE = "Amendment Title"; 
    private static final String PROTOCOL_TYPE_CODE = "1";
    private static final String DESCRIPTION = "amendment description";
    private static final Date APPLICATION_DATE = new Date(System.currentTimeMillis());
    private static final String FDA_NUM = "777";
    private static final String REF1 = "amendment ref 1";
    private static final String REF2 = "amendment ref 2";
    
    private static final Integer UNIT_FUNDING_SOURCE = 2;
    private static final Integer OTHER_FUNDING_SOURCE = 3;
    
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
    
    private static final String COMMENT_1 = "comment 1";
    private static final String COMMENT_2 = "comment 2";
    
    @Test
    public void testGeneralInfoMerge() {
        Protocol protocol = createProtocol();
        Protocol amendment = createAmendment(ProtocolModule.GENERAL_INFO);
        
        amendment.setTitle(TITLE);
        amendment.setProtocolTypeCode(PROTOCOL_TYPE_CODE);
        amendment.setDescription(DESCRIPTION);
        amendment.setApplicationDate(APPLICATION_DATE);
        amendment.setFdaApplicationNumber(FDA_NUM);
        amendment.setBillable(true);
        amendment.setReferenceNumber1(REF1);
        amendment.setReferenceNumber2(REF2);
        
        protocol.merge(amendment);
        assertEquals(TITLE, protocol.getTitle());
        assertEquals(PROTOCOL_TYPE_CODE, protocol.getProtocolTypeCode());
        assertEquals(DESCRIPTION, protocol.getDescription());
        assertEquals(APPLICATION_DATE, protocol.getApplicationDate());
        assertEquals(FDA_NUM, protocol.getFdaApplicationNumber());
        assertEquals(true, protocol.isBillable());
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
      
    private ProtocolFundingSource createFundingSource(Integer fundingSourceType) {
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
        amendment.getProtocolPersons().add(person);
        person = createPerson(NAME_2);
        amendment.getProtocolPersons().add(person);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getProtocolPersons().size());
        assertEquals(NAME_1, protocol.getProtocolPersons().get(0).getPersonName());
        assertEquals(NAME_2, protocol.getProtocolPersons().get(1).getPersonName());
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
        assertEquals(SPECIAL_REVIEW_CODE_1, protocol.getSpecialReviews().get(0).getSpecialReviewCode());
        assertEquals(SPECIAL_REVIEW_CODE_2, protocol.getSpecialReviews().get(1).getSpecialReviewCode());
    }
    
    private ProtocolSpecialReview createSpecialReview(String specialReviewCode) {
        ProtocolSpecialReview sr = new ProtocolSpecialReview();
        sr.setSpecialReviewCode(specialReviewCode);
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
        
        ProtocolAttachmentPersonnel pal = createPersonnelAttachment(DESCRIPTION_1);
        amendment.getAttachmentPersonnels().add(pal);
        pal = createPersonnelAttachment(DESCRIPTION_2);
        amendment.getAttachmentPersonnels().add(pal);
        
        ProtocolAttachmentNotification pan = createNotificationAttachment(COMMENT_1);
        amendment.getAttachmentNotifications().add(pan);
        pan = createNotificationAttachment(COMMENT_2);
        amendment.getAttachmentNotifications().add(pan);
        
        protocol.merge(amendment);
        
        assertEquals(2, protocol.getAttachmentProtocols().size());
        assertEquals(NAME_1, protocol.getAttachmentProtocols().get(0).getContactName());
        assertEquals(NAME_2, protocol.getAttachmentProtocols().get(1).getContactName());
        
        assertEquals(2, protocol.getAttachmentPersonnels().size());
        assertEquals(DESCRIPTION_1, protocol.getAttachmentPersonnels().get(0).getDescription());
        assertEquals(DESCRIPTION_2, protocol.getAttachmentPersonnels().get(1).getDescription());
    
        assertEquals(2, protocol.getAttachmentNotifications().size());
        assertEquals(COMMENT_1, protocol.getAttachmentNotifications().get(0).getComments());
        assertEquals(COMMENT_2, protocol.getAttachmentNotifications().get(1).getComments());
    }
    
    private ProtocolAttachmentNotification createNotificationAttachment(String comments) {
        ProtocolAttachmentNotification pan = new ProtocolAttachmentNotification();
        pan.setComments(comments);
        return pan;
    }

    private ProtocolAttachmentPersonnel createPersonnelAttachment(String description) {
        ProtocolAttachmentPersonnel pal = new ProtocolAttachmentPersonnel();
        pal.setDescription(description);
        return pal;
    }

    private ProtocolAttachmentProtocol createProtocolAttachment(String name) {
        ProtocolAttachmentProtocol pap = new ProtocolAttachmentProtocol();
        pap.setContactName(name);
        return pap;
    }

    /**
     * Create a amendment protocol.
     * @return
     */
    private Protocol createProtocol() {
        Protocol protocol = new Protocol();
        protocol.setProtocolId(1L);
        protocol.setProtocolNumber("0906000001");
        return protocol;
    }
    
    /**
     * Create a amendment protocol.
     * @return
     */
    private Protocol createAmendment(String moduleTypeCode) {
        final Protocol protocol = new Protocol();
        protocol.setProtocolId(2L);
        protocol.setProtocolNumber("0906000001A001");
        addModule(protocol, moduleTypeCode);
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
