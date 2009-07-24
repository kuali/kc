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

import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;

/**
 * 
 * This class is used to test NasaOtherProjectInformationV1_0 form
 */
public class NASAOtherProjectInformationV1_0GeneratorTest extends S2STestBase<NASAOtherProjectInformationV1_0Generator> {

    @Override
    protected Class<NASAOtherProjectInformationV1_0Generator> getFormGeneratorClass() {
        return NASAOtherProjectInformationV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setQuestionId("G6");
        proposalYnq.setAnswer("Y");
        proposalYnq.setExplanation("Nasa");
        ProposalYnq proposalYnq2 = new ProposalYnq();
        proposalYnq2.setQuestionId("H1");
        proposalYnq2.setAnswer("Y");
        proposalYnq2.setExplanation("International");
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("SCHULTE");
        person.setLastName("MARITZA");
        person.setMiddleName("D");
        person.setPersonId("000000077 ");
        person.setOptInCertificationStatus("Y");
        person.setOptInUnitStatus("Y");
        person.setProposalPersonNumber(1000);
        List<ProposalPerson> perList = new ArrayList<ProposalPerson>();
        perList.add(person);
        List<ProposalYnq> proList = new ArrayList<ProposalYnq>();
        proList.add(proposalYnq);
        proList.add(proposalYnq2);
        document.getDevelopmentProposal().setProposalPersons(perList);
        document.getDevelopmentProposal().setProposalYnqs(proList);

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise5.pdf");
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
        narrative.setNarrativeTypeCode("47");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setObjectId("12345678890abcd");
        narrative.setFileName("exercise5");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for Project Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);
        document.getDevelopmentProposal().setNarratives(naList);
    }
}
