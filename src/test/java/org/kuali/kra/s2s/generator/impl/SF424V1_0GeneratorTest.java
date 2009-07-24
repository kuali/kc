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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the SF424V1_0 generator
 */
public class SF424V1_0GeneratorTest extends S2STestBase<SF424V1_0Generator> {

    @Override
    protected Class<SF424V1_0Generator> getFormGeneratorClass() {
        return SF424V1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        document.getDevelopmentProposal().setProposalTypeCode("1");
        S2sSubmissionType submissionType = new S2sSubmissionType();
        submissionType.setS2sSubmissionTypeCode("AC");
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName("George");
        Organization organization = new Organization();
        organization.setOrganizationName("MIT");
        organization.setDunsNumber("236745890");
        organization.setFedralEmployerId("7645926813");
        organization.setCongressionalDistrict("Alaska");
        List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
        OrganizationType organizationType = new OrganizationType();
        organizationType.setOrganizationTypeCode(1);
        organizationTypes.add(organizationType);
        organization.setOrganizationTypes(organizationTypes);

        Unit unit = new Unit();
        unit.setUnitName("University");
        Unit unit2 = new Unit();
        unit2.setUnitName("Root of hierarchy");
        unit.setParentUnit(unit2);
        Rolodex rolodex = new Rolodex();
        rolodex.setAddressLine1("Address1");
        rolodex.setAddressLine2("address2");
        rolodex.setCity("Alabama");
        rolodex.setState("AL");
        rolodex.setCountryCode("USA");
        rolodex.setFirstName("John");
        rolodex.setLastName("Doe");
        rolodex.setMiddleName("A");
        rolodex.setPhoneNumber("982345");
        rolodex.setTitle("ProjectTitle");
        rolodex.setFaxNumber("09823456");
        rolodex.setEmailAddress("john@gmail.com");
        rolodex.setRolodexId(123);
        organization.setRolodex(rolodex);
        Rolodex rolodex2 = new Rolodex();
        rolodex2.setAddressLine1("addressLine1");
        rolodex2.setAddressLine2("address2");
        rolodex2.setCity("Alabama");
        rolodex2.setCounty("county");
        rolodex2.setState("AL");
        rolodex2.setCountryCode("USA");
        rolodex2.setPostalCode("487546");
        rolodex2.setTitle("Title");
        rolodex2.setRolodexId(123);

        Organization performingOrganization = new Organization();
        performingOrganization.setCongressionalDistrict("Alaska");
        performingOrganization.setRolodex(rolodex2);

        document.getDevelopmentProposal().setCfdaNumber("45454");
        document.getDevelopmentProposal().setProposalNumber("46554546");
        document.getDevelopmentProposal().setProgramAnnouncementTitle("Marwan Abu-Fadel");
        document.getDevelopmentProposal().setTitle("SF424");
        document.getDevelopmentProposal().setRequestedStartDateInitial(new Date(0));
        document.getDevelopmentProposal().setRequestedEndDateInitial(new Date(0));
        document.getDevelopmentProposal().setPerformingOrganization(performingOrganization);
        document.getDevelopmentProposal().setOrganization(organization);
        document.getDevelopmentProposal().setRolodex(rolodex);
        document.getDevelopmentProposal().setSponsor(sponsor);
        document.getDevelopmentProposal().setOwnedByUnit(unit);
        document.getDevelopmentProposal().getOwnedByUnit().setParentUnit(unit2);
        document.getDevelopmentProposal().setOrganization(organization);
    }
}
