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
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the NasaPIandAORSupplementalDataSheetV1_0 Generator
 */
public class NasaPIandAORSupplementalDataSheetV1_0GeneratorTest extends S2STestBase<NasaPIandAORSupplementalDataSheetV1_0Generator> {

    @Override
    protected Class<NasaPIandAORSupplementalDataSheetV1_0Generator> getFormGeneratorClass() {
        return NasaPIandAORSupplementalDataSheetV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Rolodex rolodex = new Rolodex();
        rolodex.setFirstName("AuthorizedRepresentative FirstName");
        rolodex.setLastName(" AuthorizedRepresentative LastName");
        rolodex.setMiddleName("MiddleName");
        rolodex.setRolodexId(1234);
        Organization organization = new Organization();
        organization.setRolodex(rolodex);
        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("Kelly");
        person.setLastName("Brooke");
        person.setMiddleName("K");
        person.setOfficePhone("08098127634");
        person.setEmailAddress("kelly@gmail.com");
        person.setOptInCertificationStatus("Y");
        person.setOptInUnitStatus("Y");
        person.setProposalPersonNumber(1001);
        List<ProposalPerson> personList = new ArrayList<ProposalPerson>();
        personList.add(person);
        document.getDevelopmentProposal().setProposalPersons(personList);

        List<ProposalYnq> proArrayList = new ArrayList<ProposalYnq>();
        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setQuestionId("25");
        proposalYnq.setAnswer("N");
        proArrayList.add(proposalYnq);
        document.getDevelopmentProposal().setProposalYnqs(proArrayList);
        document.getDevelopmentProposal().setOrganization(organization);
    }
}
