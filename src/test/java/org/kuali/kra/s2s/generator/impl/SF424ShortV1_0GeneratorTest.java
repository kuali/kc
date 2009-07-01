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
import org.kuali.kra.bo.OrganizationTypeList;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * 
 * This class is used to test SF424ShortV1_0 form
 */
public class SF424ShortV1_0GeneratorTest extends S2STestBase<SF424ShortV1_0Generator> {

    @Override
    protected Class<SF424ShortV1_0Generator> getFormGeneratorClass() {
        return SF424ShortV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName("George");
        S2sOpportunity opportunity = new S2sOpportunity();
        opportunity.setOpportunityId("123");
        opportunity.setOpportunityTitle("opportunityTitle");
        Organization organization = new Organization();
        organization.setOrganizationName("MIT");
        organization.setFedralEmployerId("7645926813");
        organization.setDunsNumber("236745890");
        organization.setContactAddressId(1);
        List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
        OrganizationType organizationType = new OrganizationType();
        organizationType.setOrganizationTypeCode(1);
        OrganizationType organizationType1 = new OrganizationType();
        organizationType1.setOrganizationTypeCode(2);
        organizationTypes.add(organizationType);
        organizationTypes.add(organizationType1);
        organization.setOrganizationTypes(organizationTypes);
        List<OrganizationTypeList> orgList = new ArrayList<OrganizationTypeList>();
        OrganizationTypeList organizationTypeList = new OrganizationTypeList();
        organizationTypeList.setOrganizationTypeCode(1);
        organizationTypeList.setDescription("Federal Government");
        orgList.add(organizationTypeList);
        organizationType.setOrganizationTypeList(organizationTypeList);
        organization.setCongressionalDistrict("congressionalDistrict");

        ProposalAbstract proposalAbstract = new ProposalAbstract();
        proposalAbstract.setAbstractTypeCode("1");
        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(proposalAbstract);
        ProposalPerson person = new ProposalPerson();
        person.setFirstName("John");
        person.setLastName("Mayor");
        person.setPrimaryTitle("DirTitle");
        person.setOfficePhone("08098127634");
        person.setFaxNumber("080543219876");
        person.setEmailAddress("john@gmail.com");
        person.setAddressLine1("argAddressLine1");
        person.setAddressLine2("argAddressLine2");
        person.setCity("HongKong");
        person.setCounty("argCounty");
        person.setState("AL");
        person.setCountryCode("USA");
        person.setProposalPersonRoleId("PI");
        person.setOptInCertificationStatus("Y");
        person.setProposalPersonNumber(1001);
        person.setOptInUnitStatus("Y");
        List<ProposalPerson> argProposalPersons = new ArrayList<ProposalPerson>();
        argProposalPersons.add(person);
        document.setProposalPersons(argProposalPersons);
        Rolodex rolodex = new Rolodex();
        rolodex.setRolodexId(1);
        rolodex.setAddressLine1("Address1");
        rolodex.setAddressLine2("address2");
        rolodex.setCity("Alabama");
        rolodex.setState("AL");
        rolodex.setCountryCode("USA");
        rolodex.setFirstName("John");
        rolodex.setLastName("Doe");
        rolodex.setTitle("Title");
        rolodex.setPhoneNumber("89414454");
        rolodex.setEmailAddress("john@gmail.com");
        rolodex.setFaxNumber("114354");
        organization.setRolodex(rolodex);
        Unit unit = new Unit();
        unit.setUnitName("University");
        Unit unit2 = new Unit();
        unit2.setUnitName("Root of hierarchy");
        unit.setParentUnit(unit2);

        document.setSponsor(sponsor);
        document.setCfdaNumber("0125");
        document.setProgramAnnouncementTitle("programAnnouncementTitle");
        opportunity.setProposalNumber(document.getProposalNumber());
        document.setS2sOpportunity(opportunity);
        document.setOrganization(organization);
        document.setTitle("NewTitle");
        document.setProposalAbstracts(proList);
        document.setRequestedStartDateInitial(new Date(0));
        document.setRequestedEndDateInitial(new Date(0));
        document.setRolodex(rolodex);
        document.setOwnedByUnit(unit);
    }
}
