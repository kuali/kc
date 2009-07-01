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

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * 
 * This class is used to test RRPersonalDataV1_1 form
 */
public class RRPersonalDataV1_1GeneratorTest extends S2STestBase<RRPersonalDataV1_1Generator> {

    @Override
    protected Class<RRPersonalDataV1_1Generator> getFormGeneratorClass() {
        return RRPersonalDataV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setProposalPersonRoleId("PI");
        proposalPerson.setFirstName("Philip");
        proposalPerson.setLastName("Berg");
        proposalPerson.setSocialSecurityNumber("1234");
        proposalPerson.setGender("F");
        proposalPerson.setRace("Asian");
        proposalPerson.setHandicapType("Hearing");
        proposalPerson.setCountryOfCitizenship("US Citizen");
        proposalPerson.setOptInCertificationStatus("Y");
        proposalPerson.setOptInUnitStatus("Y");
        proposalPerson.setProposalPersonNumber(1001);
        proposalPerson.setRace("English");
        ProposalPerson keyPerson = new ProposalPerson();
        keyPerson.setProposalPersonRoleId("COI");
        keyPerson.setFirstName("Terry");
        keyPerson.setLastName("Durkin");
        keyPerson.setSocialSecurityNumber("9876");
        keyPerson.setGender("M");
        keyPerson.setRace("American Indian or Alaska Native");
        keyPerson.setHandicapType("Visual");
        keyPerson.setCountryOfCitizenship("Permanent Resident");
        keyPerson.setOptInCertificationStatus("Y");
        keyPerson.setOptInUnitStatus("Y");
        keyPerson.setProposalPersonNumber(1002);
        keyPerson.setRace("English");
        List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
        proposalPersonList.add(proposalPerson);
        proposalPersonList.add(keyPerson);
        document.setProposalPersons(proposalPersonList);
    }
}
