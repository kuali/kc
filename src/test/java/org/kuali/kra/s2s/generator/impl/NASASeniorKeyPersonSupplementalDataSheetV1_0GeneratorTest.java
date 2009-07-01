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

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the NASASeniorKeyPersonSupplementalDataSheetV1_0 Generator
 */
public class NASASeniorKeyPersonSupplementalDataSheetV1_0GeneratorTest extends
        S2STestBase<NASASeniorKeyPersonSupplementalDataSheetV1_0Generator> {

    @Override
    protected Class<NASASeniorKeyPersonSupplementalDataSheetV1_0Generator> getFormGeneratorClass() {
        return NASASeniorKeyPersonSupplementalDataSheetV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Rolodex rolodex = new Rolodex();
        rolodex.setCountryCode("USA");
        rolodex.setSponsorCode("000217 ");
        rolodex.setRolodexId(1);
        Organization organization = new Organization();
        organization.setRolodex(rolodex);
        document.setOrganization(organization);
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorCode("000217 ");
        sponsor.setSponsorTypeCode("8");

        ArrayList<ProposalPerson> proList = new ArrayList<ProposalPerson>();
        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setProposalPersonRoleId("COI");
        proposalPerson.setFirstName("BAYLESS");
        proposalPerson.setLastName("CORINA");
        proposalPerson.setMiddleName("H");
        proposalPerson.setPersonId("000000067");
        proposalPerson.setOptInCertificationStatus("Y");
        proposalPerson.setOptInUnitStatus("Y");
        proposalPerson.setProposalPersonNumber(1001);

        ProposalPerson proposalPerson1 = new ProposalPerson();
        proposalPerson1.setProposalPersonRoleId("COLLABORATOR");
        proposalPerson1.setFirstName("SCHULTE");
        proposalPerson1.setLastName("MARITZA");
        proposalPerson1.setMiddleName("D");
        proposalPerson1.setPersonId("000000077 ");
        proposalPerson1.setOptInCertificationStatus("Y");
        proposalPerson1.setOptInUnitStatus("Y");
        proposalPerson1.setProposalPersonNumber(1002);
        proList.add(proposalPerson);
        proList.add(proposalPerson1);
        document.setRolodex(rolodex);
        document.setSponsor(sponsor);
        document.setProposalPersons(proList);
    }
}
