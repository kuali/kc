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

import org.kuali.rice.kns.service.BusinessObjectService; 
import org.kuali.kra.bo.DegreeType;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;

/**
 * This class tests the NSFCoverPageV1_1 Generator
 */
public class NSFCoverPageV1_1GeneratorTest extends S2STestBase<NSFCoverPageV1_1Generator> {

    @Override
    protected Class<NSFCoverPageV1_1Generator> getFormGeneratorClass() {
        return NSFCoverPageV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        document.setProgramAnnouncementNumber("1154");
        document.setAgencyDivisionCode("007");
        document.setAgencyProgramCode("008");
        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setPersonId("000000078 ");
        proposalPerson.setProposalPersonRoleId("PI");
        proposalPerson.setOptInCertificationStatus("Y");
        proposalPerson.setOptInUnitStatus("Y");
        proposalPerson.setProposalPersonNumber(1001);
        ProposalPerson proPersonCo = new ProposalPerson();
        proPersonCo.setPersonId("000000079");
        proPersonCo.setProposalPersonRoleId("COI");
        proPersonCo.setFirstName("DEVRIES");
        proPersonCo.setMiddleName("M");
        proPersonCo.setLastName("OLIVER");
        proPersonCo.setOptInCertificationStatus("Y");
        proPersonCo.setOptInUnitStatus("Y");
        proPersonCo.setProposalPersonNumber(1002);
        
        ProposalPersonDegree propDegree = new ProposalPersonDegree();
        DegreeType degreeType = new DegreeType();
        degreeType.setDegreeCode("2");
        propDegree.setDegreeType(degreeType);
        propDegree.setGraduationYear("1995");
        propDegree.setDegreeSequenceNumber(1);
        ProposalPersonDegree propDegreeCo = new ProposalPersonDegree();
        DegreeType degreeTypeCo = new DegreeType();
        degreeTypeCo.setDegreeCode("2");
        propDegreeCo.setDegreeType(degreeType);
        propDegreeCo.setGraduationYear("2000");
        propDegreeCo.setDegreeSequenceNumber(1);
        List<ProposalPersonDegree> perList = new ArrayList<ProposalPersonDegree>();
        perList.add(propDegree);
        proposalPerson.setProposalPersonDegrees(perList);
        List<ProposalPersonDegree> perListCo = new ArrayList<ProposalPersonDegree>();
        perListCo.add(propDegreeCo);
        proPersonCo.setProposalPersonDegrees(perListCo);
        List<ProposalPerson> proList = new ArrayList<ProposalPerson>();
        proList.add(proposalPerson);
        proList.add(proPersonCo);
        document.setProposalPersons(proList);

        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setAnswer("Y");
        proposalYnq.setQuestionId("12");
        ProposalYnq proposalYnq1 = new ProposalYnq();
        proposalYnq1.setAnswer("Y");
        proposalYnq1.setQuestionId("8");
        ProposalYnq proposalYnq2 = new ProposalYnq();
        proposalYnq2.setAnswer("Y");
        proposalYnq2.setQuestionId("G6");
        ProposalYnq proposalYnq3 = new ProposalYnq();
        proposalYnq3.setAnswer("Y");
        proposalYnq3.setQuestionId("14");
        ProposalYnq proposalYnq4 = new ProposalYnq();
        proposalYnq4.setAnswer("Y");
        proposalYnq4.setQuestionId("20");
        ProposalYnq proposalYnq5 = new ProposalYnq();
        proposalYnq5.setAnswer("Y");
        proposalYnq5.setQuestionId("19");
        ProposalYnq proposalYnq6 = new ProposalYnq();
        proposalYnq6.setAnswer("Y");
        proposalYnq6.setQuestionId("H4");
        List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
        ynqList.add(proposalYnq);
        ynqList.add(proposalYnq1);
        ynqList.add(proposalYnq2);
        ynqList.add(proposalYnq3);
        ynqList.add(proposalYnq4);
        ynqList.add(proposalYnq5);
        ynqList.add(proposalYnq6);
        document.setProposalYnqs(ynqList);

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise7.pdf");
        InputStream inStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("13");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise7");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for NSFCoverPage Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);
        getService(BusinessObjectService.class).save(narrative);
        narrative.getNarrativeAttachmentList().clear();
        document.setNarratives(naList);
    }
}
