/*
 * Copyright 2005-2010 The Kuali Foundation.
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.util.S2STestUtils;

/**
 * 
 * This class is used to test RRSF424V1_2 form
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class RRSF424V1_2GeneratorTest extends S2STestBase<RRSF424V1_2Generator> {

	@Override
	protected Class<RRSF424V1_2Generator> getFormGeneratorClass() {
		return RRSF424V1_2Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		document.getDevelopmentProposal().setProposalTypeCode("1");
		S2sSubmissionType submissionType = new S2sSubmissionType();
		submissionType.setS2sSubmissionTypeCode("2");
		submissionType.setDescription("Application");
		S2sOpportunity opportunity = new S2sOpportunity();
		opportunity.setRevisionCode("1");
		opportunity.setRevisionOtherDescription("revisionOtherDescription");
		opportunity.setS2sSubmissionTypeCode("2");
		opportunity.setS2sSubmissionType(submissionType);
		document.getDevelopmentProposal().setS2sOpportunity(opportunity);
		opportunity.setProposalNumber(document.getDevelopmentProposal()
				.getProposalNumber());
		opportunity.setRevisionCode("A");

		Rolodex rolodex = new Rolodex();
		rolodex.setAddressLine1("Address1");
		rolodex.setAddressLine2("address2");
		rolodex.setCity("Bangkok");
		rolodex.setState("AL");
		rolodex.setCountryCode("USA");
		rolodex.setFirstName("John");
		rolodex.setLastName("Doe");
		rolodex.setMiddleName("A");
		rolodex.setPhoneNumber("982345");
		rolodex.setTitle("ProjectTitle");
		rolodex.setFaxNumber("09823456");
		rolodex.setEmailAddress("john@gmail.com");
		rolodex.setPostalCode("35001");
		rolodex.setRolodexId(123);
		Organization organization = new Organization();
		List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
		OrganizationType organizationType = new OrganizationType();
		organizationType.setOrganizationTypeCode(1);
		organizationTypes.add(organizationType);

		organization.setOrganizationName("MIT");
		organization.setFedralEmployerId("7645926813");
		organization.setDunsNumber("236745890");
		organization.setCongressionalDistrict("Alaska");
		organization.setOrganizationTypes(organizationTypes);
		organization.setRolodex(rolodex);
		document.getDevelopmentProposal()
				.setApplicantOrgFromOrganization(organization);
		Unit unit = new Unit();
		unit.setUnitName("University");
		Unit unit2 = new Unit();
		unit2.setUnitName("Root of hierarchy");
		unit.setParentUnit(unit2);
		UnitAdministrator unitAdmin = new UnitAdministrator();
		unitAdmin.setUnitAdministratorTypeCode("2");
		List<UnitAdministrator> unitAdminList = new ArrayList<UnitAdministrator>();
		unitAdminList.add(unitAdmin);
		Unit unit3 = new Unit();
		unit3.setUnitName("Root of hierarchy");
		unit3.setParentUnit(unit2);
		unit3.setUnitAdministrators(unitAdminList);

		ProposalPersonUnit personUnit = new ProposalPersonUnit();
		personUnit.setUnit(unit3);
		personUnit.setLeadUnit(true);
		personUnit.setUnitNumber("000001");
		List<ProposalPersonUnit> unitList = new ArrayList<ProposalPersonUnit>();
		unitList.add(personUnit);

		DepartmentalPerson dePerson = new DepartmentalPerson();
		dePerson.setFirstName("newFirstName");
		dePerson.setLastName("newLastName");
		dePerson.setOfficePhone("9888888887");

		ProposalPerson person = new ProposalPerson();
		person.setProposalPersonRoleId("PI");
		person.setFirstName("Mohan");
		person.setLastName("Ram Kumar");
		person.setAddressLine1("Ring Road");
		person.setOfficePhone("08098127634");
		person.setCity("argCity");
		person.setCounty("argCity");
		person.setDirectoryTitle("SomeTitle");
		person.setFaxNumber("080543219876");
		person.setState("AL");
		person.setCountryCode("USA");
		person.setUnits(unitList);
		person.setOptInUnitStatus("Y");
		person.setOptInCertificationStatus("Y");
		person.setProposalPersonNumber(1);
		organization.setOrganizationName("MIT");
		person.setEmailAddress("mohan@gmail.com");
		person.setUnits(unitList);

		List<ProposalPerson> personList = new ArrayList<ProposalPerson>();
		personList.add(person);
		Sponsor sponsor = new Sponsor();
		sponsor.setSponsorName("George");
		Rolodex rolodex2 = new Rolodex();
		rolodex2.setState("Washington");
		rolodex2.setFirstName("John");
		rolodex2.setLastName("Doe");
		rolodex2.setMiddleName("A");
		rolodex2.setPostalCode("34001");

		Organization performingOrganization = new Organization();
		performingOrganization.setCongressionalDistrict("Alaska");
		performingOrganization.setRolodex(rolodex2);
		document.getDevelopmentProposal().setPerformingOrgFromOrganization(
				performingOrganization);

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
		narrative.setNarrativeTypeCode("53");
		narrative.setNarrativeAttachmentList(narrativeList);
		narrative.setObjectId("12345678890abcd");
		narrative.setFileName("exercise4");
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setDescription("Testing for Project Attachment");
		narrative.setNarrativeType(narrativeType);
		naList.add(narrative);
		document.getDevelopmentProposal().setRequestedStartDateInitial(
				new Date(0));
		document.getDevelopmentProposal().setRequestedEndDateInitial(
				new Date(0));
		document.getDevelopmentProposal().setNarratives(naList);
		document.getDevelopmentProposal().setSponsor(sponsor);
		document.getDevelopmentProposal().setProposalPersons(personList);
		document.getDevelopmentProposal().setCfdaNumber("4654654");
		document.getDevelopmentProposal().setTitle("RRSF424");
		document.getDevelopmentProposal().setProgramAnnouncementTitle(
				"Marwan Abu-Fadel");
		document.getDevelopmentProposal().setOwnedByUnit(unit);
		document.getDevelopmentProposal().getOwnedByUnit().setParentUnit(unit2);

		// Budget start
		Calendar startDate = Calendar.getInstance();
		startDate.set(2001, 1, 1);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2005, 1, 1);
		S2STestUtils testUtils = new S2STestUtils();
		BudgetDocument bdoc = testUtils.createBudgetDocument(document);
		Budget bd = bdoc.getBudget();
		bd.setModularBudgetFlag(false);
		bdoc.setUpdateUser("quickstart");
		bdoc.setParentDocument(document);
		bdoc.setUpdateTimestamp(new Timestamp(startDate.getTimeInMillis()));
		bd.setVersionNumber(new Long(1));
		bd.setBudgetVersionNumber(1);
		bd.setBudgetStatus("Open");
		bd.setComments("");
		bd.setStartDate(new Date(startDate.getTimeInMillis()));
		bd.setEndDate(new Date(endDate.getTimeInMillis()));
		BudgetPeriod firstPeriod = new BudgetPeriod();
		firstPeriod.setBudgetPeriod(1);
		firstPeriod.setStartDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));
		firstPeriod.setEndDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));

		List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
		budgetPeriods.add(firstPeriod);
		bd.setBudgetPeriods(budgetPeriods);
		bd.setOhRateClassCode("1");
		bd.setOhRateTypeCode("1");
		List<BudgetVersionOverview> overviewList = new ArrayList<BudgetVersionOverview>();
		BudgetVersionOverview overview = new BudgetVersionOverview();
		overview.setBudgetStatus("");
		overview.setBudgetVersionNumber(1);
		overview.setComments("");
		overview.setCostSharingAmount(BudgetDecimal.ZERO);
		overview.setDocumentDescription("Description");
		overview.setEndDate(new Date(endDate.getTimeInMillis()));
		overview.setName("overview");
		overview.setOhRateTypeCode("1");
		overview.setOhRateClassCode("1");
		overview.setStartDate(new Date(startDate.getTimeInMillis()));
		overview.setTotalCost(BudgetDecimal.ZERO);
		overview.setTotalCostLimit(BudgetDecimal.ZERO);
		overview.setTotalDirectCost(BudgetDecimal.ZERO);
		overview.setTotalIndirectCost(BudgetDecimal.ZERO);
		overview.setVersionNumber(new Long(1));
		overview.setResidualFunds(BudgetDecimal.ZERO);
		overview.setDocumentNumber("1001");
		overviewList.add(overview);
		List<BudgetDocumentVersion> docVerList = new ArrayList<BudgetDocumentVersion>();
		BudgetDocumentVersion docVer = new BudgetDocumentVersion();
		docVer.setDocumentNumber(bdoc.getDocumentNumber());
		docVer.setBudgetVersionOverviews(overviewList);
		docVerList.add(docVer);
		document.setBudgetDocumentVersions(docVerList);
		// Budget end

		// developmentProposal.setPerformingOrganization has side effect logic.
		// It creates proposalSite object for each organization without setting
		// the mandatory field - LOCATION_NAME. The code below sets location
		// name.
		int count = 0;
		for (ProposalSite site : document.getDevelopmentProposal()
				.getProposalSites()) {
			site.setLocationName("NJ");
			site.setSiteNumber(++count);
			CongressionalDistrict district = new CongressionalDistrict();
			district.setCongressionalDistrict("CA");
			List<CongressionalDistrict> districtList = new ArrayList<CongressionalDistrict>();
			districtList.add(district);
			site.setCongressionalDistricts(districtList);
		}
	}
}
