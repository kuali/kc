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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * 
 * This class tests PHS398CoverPageSupplement form
 */
public class PHS398CoverPageSupplementV1_0GeneratorTest extends S2STestBase<PHS398CoverPageSupplementV1_0Generator> {

    @Override
    protected Class<PHS398CoverPageSupplementV1_0Generator> getFormGeneratorClass() {
        return PHS398CoverPageSupplementV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        document.getDevelopmentProposal().setActivityTypeCode("8");
        Rolodex rolodex = new Rolodex();
        rolodex.setState("AL");
        rolodex.setAddressLine1("Address1");
        rolodex.setAddressLine2("address2");
        rolodex.setCity("Bangkok");
        rolodex.setCountryCode("USA");
        rolodex.setFirstName("John");
        rolodex.setLastName("Doe");
        rolodex.setMiddleName("A");
        rolodex.setPhoneNumber("982345");
        rolodex.setTitle("ProjectTitle");
        rolodex.setFaxNumber("09823456");
        rolodex.setEmailAddress("john@gmail.com");
        rolodex.setRolodexId(123);
        Organization organization = new Organization();
        organization.setRolodex(rolodex);
        document.getDevelopmentProposal().setOrganization(organization);
        document.getDevelopmentProposal().setRolodex(rolodex);

        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("MARTIN");
        person.setLastName("MERRILL");

        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setAnswer("Y");
        proposalYnq.setQuestionId("17");
        ProposalYnq proposalYnq2 = new ProposalYnq();
        proposalYnq2.setAnswer("N");
        proposalYnq2.setExplanation("UNKNOWN");
        proposalYnq2.setQuestionId("18");
        ProposalYnq proposalYnq3 = new ProposalYnq();
        proposalYnq3.setAnswer("Y");
        proposalYnq3.setQuestionId("13");
        List<ProposalYnq> proListYnq = new ArrayList<ProposalYnq>();
        proListYnq.add(proposalYnq);
        proListYnq.add(proposalYnq2);
        proListYnq.add(proposalYnq3);
        document.getDevelopmentProposal().setProposalYnqs(proListYnq);

        ProposalPersonDegree personDegree = new ProposalPersonDegree();
        personDegree.setDegree("MBA");
        personDegree.setDegreeSequenceNumber(1);
        List<ProposalPersonDegree> perList = new ArrayList<ProposalPersonDegree>();
        perList.add(personDegree);
        person.setProposalPersonDegrees(perList);
        person.setOptInCertificationStatus("Y");
        person.setOptInUnitStatus("Y");
        person.setProposalPersonNumber(1001);
        List<ProposalPerson> argProposalPersons = new ArrayList<ProposalPerson>();
        argProposalPersons.add(person);
        document.getDevelopmentProposal().setProposalPersons(argProposalPersons);
        document.getDevelopmentProposal().setPrincipalInvestigator(person);
    }
}
