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
import java.util.Calendar;
import java.util.List;

import org.kuali.rice.kns.service.BusinessObjectService; 
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the SF424V1_0 generator
 */
public class SF424V2_0GeneratorTest extends S2STestBase<SF424V2_0Generator> {


    @Override
    protected Class<SF424V2_0Generator> getFormGeneratorClass() {
        return SF424V2_0Generator.class;
    }


    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        S2sSubmissionType submissionType = new S2sSubmissionType();
        submissionType.setS2sSubmissionTypeCode("1");
        S2sOpportunity opportunity = new S2sOpportunity();
        opportunity.setS2sSubmissionType(submissionType);
        opportunity.setS2sSubmissionTypeCode("1");
        opportunity.setRevisionCode("AC");
        opportunity.setRevisionOtherDescription("Revision description");
        opportunity.setOpportunityTitle("opportunityTitle");
        opportunity.setCompetetionId("3650");
        opportunity.setCfdaNumber("654654");

        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName("George");

        Organization organization = new Organization();
        organization.setOrganizationName("MIT");
        organization.setFedralEmployerId("7645926813");
        organization.setDunsNumber("236745890");
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

        ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setFirstName("John");
        person.setLastName("Mayor");
        person.setDirectoryTitle("DirTitle");
        person.setOfficePhone("08098127634");
        person.setFaxNumber("080543219876");
        person.setEmailAddress("john@gmail.com");
        person.setOptInUnitStatus("Y");
        person.setOptInCertificationStatus("Y");
        person.setProposalPersonNumber(10001);
        List<ProposalPerson> argProposalPersons = new ArrayList<ProposalPerson>();
        argProposalPersons.add(person);

        Rolodex rolodex = new Rolodex();
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
        rolodex.setRolodexId(1);
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

        ProposalAbstract propAbstract = new ProposalAbstract();
        propAbstract.setAbstractTypeCode("15");
        propAbstract.setAbstractDetails("abstractDetails");
        List<ProposalAbstract> proList = new ArrayList<ProposalAbstract>();
        proList.add(propAbstract);

        Organization perforOrganization = new Organization();
        perforOrganization.setCongressionalDistrict("congressionalDistrict");
        perforOrganization.setRolodex(rolodex2);
        OrganizationYnq orgYnq = new OrganizationYnq();
        orgYnq.setQuestionId("17");
        List<OrganizationYnq> orgList = new ArrayList<OrganizationYnq>();
        orgList.add(orgYnq);
        organization.setOrganizationYnqs(orgList);

        document.getDevelopmentProposal().setPerformingOrgFromOrganization(perforOrganization);
        document.getDevelopmentProposal().setRolodex(rolodex);
        document.getDevelopmentProposal().setOwnedByUnit(unit);
        document.getDevelopmentProposal().getOwnedByUnit().setParentUnit(unit2);
        document.getDevelopmentProposal().setApplicantOrgFromOrganization(organization);
        document.getDevelopmentProposal().setSponsor(sponsor);
        document.getDevelopmentProposal().setProposalTypeCode("1");
        document.getDevelopmentProposal().setCfdaNumber("0125");
        document.getDevelopmentProposal().setTitle("NewTitle");
        document.getDevelopmentProposal().setProgramAnnouncementTitle("programAnnouncementTitle");
        document.getDevelopmentProposal().setRequestedStartDateInitial(new Date(Calendar.getInstance().getTimeInMillis()));
        document.getDevelopmentProposal().setRequestedEndDateInitial(new Date(Calendar.getInstance().getTimeInMillis()));
        document.setUpdateUser("quickstart");
        document.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        KraServiceLocator.getService(BusinessObjectService.class).save(document);
        opportunity.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        document.getDevelopmentProposal().setS2sOpportunity(opportunity);
        document.getDevelopmentProposal().setProposalPersons(argProposalPersons);
        document.getDevelopmentProposal().setProposalAbstracts(proList);
    }
}
