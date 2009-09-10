/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class tests the EDSF424SupplementV1_0Generator
 */
public class EDSF424SupplementV1_0GeneratorTest extends S2STestBase<EDSF424SupplementV1_0Generator> {

    @Override
    protected Class<EDSF424SupplementV1_0Generator> getFormGeneratorClass() {
        return EDSF424SupplementV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        ProposalSpecialReview specialReview = new ProposalSpecialReview();
        specialReview.setSpecialReviewNumber(1);
        specialReview.setSpecialReviewCode("1");
        specialReview.setApprovalTypeCode("4");
        specialReview.setComments("This is the comment for special review comments");
        List<ProposalSpecialReview> reviewList = new ArrayList<ProposalSpecialReview>();
        reviewList.add(specialReview);
        document.getDevelopmentProposal().setPropSpecialReviews(reviewList);
        Organization organization = new Organization();
        organization.setHumanSubAssurance("humanSubAssurance");
        document.getDevelopmentProposal().setApplicantOrgFromOrganization(organization);

        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("Kelly");
        person.setLastName("Brooke");
        person.setMiddleName("K");
        person.setOfficePhone("08098127634");
        person.setEmailAddress("kelly@gmail.com");
        person.setDirectoryTitle("Test for EDS Supplement");
        person.setFaxNumber("080543219876");
        person.setAddressLine1("#545");
        person.setAddressLine2("Mission Road");
        person.setCity("Vegas");
        person.setState("AL");
        person.setCountryCode("USA");
        person.setOptInCertificationStatus("Y");
        person.setOptInUnitStatus("Y");
        person.setProposalPersonNumber(1001);
        List<ProposalPerson> personList = new ArrayList<ProposalPerson>();
        personList.add(person);
        document.getDevelopmentProposal().setProposalPersons(personList);

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise4.pdf");
        InputStream inStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("54");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise4");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for EDSF424Supplement Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);
        getService(BusinessObjectService.class).save(narrative);
        narrative.getNarrativeAttachmentList().clear();
        document.getDevelopmentProposal().setNarratives(naList);
    }
}
