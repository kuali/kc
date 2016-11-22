/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.PropPerDocType;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * 
 * This class is used to test RRKeyPersonExpandedV2_0Generator form
 */
public class RRKeyPersonExpandedV2_0GeneratorTest extends S2STestBase {

	@Override
	protected String getFormGeneratorName() {
		return RRKeyPersonExpandedV2_0Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setProposalPersonRoleId("PI");
		proposalPerson.setFirstName("Alan");
		proposalPerson.setLastName("McAfee");
		proposalPerson.setDirectoryTitle("Title");
		proposalPerson.setAddressLine1("Sanfransisco");
		proposalPerson.setAddressLine2("Newyork");
		proposalPerson.setCity("Los Angels");
		proposalPerson.setCounty("County");
		proposalPerson.setState("AL");
		proposalPerson.setPostalCode("58623415");
		proposalPerson.setCountryCode("USA");
		proposalPerson.setOfficePhone("63254152");
		proposalPerson.setFaxNumber("52374512");
		proposalPerson.setDegree("MS");
        proposalPerson.setProjectRole("Manager");
		proposalPerson.setYearGraduated("2006");
		proposalPerson.setEmailAddress("philip@hotmail.com");
		proposalPerson.setOptInCertificationStatus(true);
		proposalPerson.setOptInUnitStatus(true);
		proposalPerson.setProposalPersonNumber(1001);
		proposalPerson.setPersonId("10000000018");
        proposalPerson.setDevelopmentProposal(document.getDevelopmentProposal());

		Organization organization = new Organization();
		organization.setOrganizationName("Espace");
		organization.setOrganizationId("000001");
		Unit unit = new Unit();
		unit.setUnitName("University");
        unit.setUnitNumber("1");
		Unit unit2 = new Unit();
		unit2.setUnitName("Root of hierarchy");
        unit2.setUnitNumber("2");
        unit.setParentUnit(unit2);

        saveBO(unit);

		Narrative narrative = new Narrative();
		List<Narrative> naList = new ArrayList<Narrative>();
		narrative.setDevelopmentProposal(developmentProposal);
		narrative.setModuleNumber(1);
		narrative.setModuleSequenceNumber(1);
		narrative.setModuleStatusCode("C");
		narrative.setNarrativeTypeCode("16");
		narrative.setObjectId("12345678890abcd");
		narrative.setName("exercise1");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setCode("16");
        narrativeType.setAllowMultiple(true);
        narrativeType.setSystemGenerated(false);
        narrativeType.setDescription("Testing for Project Attachment");
        narrative.setModuleTitle("Allows Multiples Description");
        getService(DataObjectService.class).save(narrativeType);
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("16");
		naList.add(narrative);


		ProposalPerson keyPerson = new ProposalPerson();
		keyPerson.setProposalPersonRoleId("KP");
		keyPerson.setFirstName("Andrew");
		keyPerson.setLastName("Slusar");
		keyPerson.setDirectoryTitle("Title1");
		keyPerson.setAddressLine1("Street1");
		keyPerson.setAddressLine2("Street2");
		keyPerson.setCity("Washington");
		keyPerson.setCounty("County");
		keyPerson.setState("AL");
		keyPerson.setPostalCode("58623415");
		keyPerson.setCountryCode("USA");
		keyPerson.setOfficePhone("3254178");
		keyPerson.setFaxNumber("21487563");
		keyPerson.setDegree("MS");
		keyPerson.setProjectRole("Manager");
        keyPerson.setYearGraduated("2006");
		keyPerson.setEmailAddress("terry@hotmail.com");
		keyPerson.setOptInCertificationStatus(true);
		keyPerson.setOptInUnitStatus(true);
		keyPerson.setProposalPersonNumber(1002);
		keyPerson.setPersonId("10000000081");
        keyPerson.setDevelopmentProposal(document.getDevelopmentProposal());

		List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
		proposalPersonList.add(proposalPerson);
		proposalPersonList.add(keyPerson);
		developmentProposal.setProposalPersons(proposalPersonList);
		developmentProposal.setApplicantOrgFromOrganization(organization);
        developmentProposal.setOwnedByUnitNumber(unit.getUnitNumber());
		developmentProposal.setOwnedByUnit(unit);
		developmentProposal.getOwnedByUnit().setParentUnit(unit2);
		developmentProposal.setNarratives(naList);
		// developmentProposal.setPerformingOrganization has side effect logic.
		// It creates proposalSite object for each organization without setting
		// the mandatory field - LOCATION_NAME. The code below sets location
		// name.
		int count = 0;
		for (ProposalSite site : developmentProposal.getProposalSites()) {
			site.setLocationName("NJ");
			site.setSiteNumber(++count);
		}
        saveBO(developmentProposal);
	}
}
