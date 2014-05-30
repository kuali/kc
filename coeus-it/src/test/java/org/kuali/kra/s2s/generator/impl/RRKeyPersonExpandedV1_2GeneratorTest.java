/*
 * Copyright 2005-2014 The Kuali Foundation.
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
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * 
 * This class is used to test RRKeypersonExpandedV1_2 form
 */
public class RRKeyPersonExpandedV1_2GeneratorTest extends
		S2STestBase<RRKeyPersonExpandedV1_2Generator> {

	@Override
	protected Class<RRKeyPersonExpandedV1_2Generator> getFormGeneratorClass() {
		return RRKeyPersonExpandedV1_2Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setProposalPersonRoleId("PI");
		proposalPerson.setFirstName("Philip");
		proposalPerson.setLastName("Berg");
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
		proposalPerson.setRolodexId(1);
        proposalPerson.setDevelopmentProposal(document.getDevelopmentProposal());


		ProposalPersonBiography piBiography = new ProposalPersonBiography();
		piBiography.setBiographyNumber(1);
		piBiography.setPersonId(proposalPerson.getPersonId());
		piBiography.setRolodexId(proposalPerson.getRolodexId());
		piBiography.setProposalPersonNumber(1001);
		piBiography.setDocumentTypeCode("1");
        PropPerDocType p = new PropPerDocType();
        p.setCode("1");
        p.setDescription("a description");
        piBiography.setPropPerDocType(p);
		piBiography.setName("Bio Attachment");
        piBiography.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
		ProposalPersonBiographyAttachment piAttachment = new ProposalPersonBiographyAttachment();
		piAttachment.setBiographyNumber(1);
		piAttachment.setType("application/octet-stream");
		piAttachment.setName("Attachment");
		piAttachment.setData(new byte[100]);
        piAttachment.setProposalPersonNumber(proposalPerson.getProposalPersonNumber());
        piAttachment.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

		piBiography.setPersonnelAttachment(piAttachment);
		List<ProposalPersonBiography> bioList = new ArrayList<ProposalPersonBiography>();
		bioList.add(piBiography);
		developmentProposal.setPropPersonBios(bioList);

		Organization organization = new Organization();
		organization.setOrganizationName("Espace");
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
		narrative.setProposalNumber(developmentProposal.getProposalNumber());
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
        getService(DataObjectService.class).save(narrativeType);
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode("16");
		naList.add(narrative);


		ProposalPerson keyPerson = new ProposalPerson();
		keyPerson.setProposalPersonRoleId("KP");
		keyPerson.setFirstName("Terry");
		keyPerson.setLastName("Durkin");
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
		keyPerson.setRolodexId(1);
        keyPerson.setDevelopmentProposal(document.getDevelopmentProposal());


		ProposalPersonBiography kpBiography = new ProposalPersonBiography();
		kpBiography.setBiographyNumber(1);
		kpBiography.setPersonId(keyPerson.getPersonId());
		kpBiography.setRolodexId(keyPerson.getRolodexId());
		kpBiography.setProposalPersonNumber(1002);
		kpBiography.setDocumentTypeCode("1");
		kpBiography.setName("Bio Attachment");
        kpBiography.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
		ProposalPersonBiographyAttachment kpAttachment = new ProposalPersonBiographyAttachment();
		kpAttachment.setBiographyNumber(1);
		kpAttachment.setType("application/octet-stream");
		kpAttachment.setName("Attachment");
		kpAttachment.setData(new byte[100]);
        kpAttachment.setProposalPersonNumber(keyPerson.getProposalPersonNumber());
        kpAttachment.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());

		kpBiography.setPersonnelAttachment(kpAttachment);
		bioList.add(kpBiography);
		developmentProposal.setPropPersonBios(bioList);

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
