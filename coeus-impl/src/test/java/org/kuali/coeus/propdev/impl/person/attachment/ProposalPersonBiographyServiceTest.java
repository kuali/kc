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
package org.kuali.coeus.propdev.impl.person.attachment;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;

public class ProposalPersonBiographyServiceTest {
	private ProposalPersonBiographyServiceImpl proposalPersonBiographyService;
	private Mockery context;
	private ProposalDevelopmentDocument proposalDocument;

	@Before
	public void setUp() {
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		proposalPersonBiographyService = new ProposalPersonBiographyServiceImpl();
		proposalDocument = createProposal();
	}

	@Test
	public void test_addProposalPersonBiography_with_document_and_biography() {
		final DataObjectService dataObjectService = context
				.mock(DataObjectService.class);
		final ProposalPersonBiography proposalPersonBiography = createBiography(proposalDocument);
		final DocumentNextvalue documentNextvalue = proposalDocument
				.getDocumentNextvalueBo(Constants.PROP_PERSON_BIO_NUMBER);
		final List<PersistableBusinessObject> businessObjects = new ArrayList<PersistableBusinessObject>();
		businessObjects.add(documentNextvalue);
		businessObjects.add(proposalPersonBiography);
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService).save(businessObjects);
				will(returnValue(businessObjects));
			}
		});
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.addProposalPersonBiography(
				proposalDocument, proposalPersonBiography);
	}

	@Test(expected = NullPointerException.class)
	public void test_addProposalPersonBiography_with_no_document_and_biography() {
		final DataObjectService dataObjectService = context
				.mock(DataObjectService.class);
		final ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography();
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.addProposalPersonBiography(null,
				proposalPersonBiography);
	}

	@Test(expected = NullPointerException.class)
	public void test_addProposalPersonBiography_with_no_document_and_no_biography() {
		final DataObjectService dataObjectService = context
				.mock(DataObjectService.class);
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.addProposalPersonBiography(null, null);
	}

	@Test
	public void test_removePersonnelAttachmentForDeletedPerson() {
		final ProposalPerson person = proposalDocument.getDevelopmentProposal()
				.getProposalPerson(0);
		proposalPersonBiographyService
				.removePersonnelAttachmentForDeletedPerson(proposalDocument,
						person);
	}

	@Test(expected = NullPointerException.class)
	public void test_removePersonnelAttachmentForDeletedPerson_with_no_argument() {
		proposalPersonBiographyService
				.removePersonnelAttachmentForDeletedPerson(null, null);
	}

	@Test
	public void test_deleteProposalPersonBiography() {
		final DataObjectService dataObjectService = context
				.mock(DataObjectService.class);
		final ProposalPersonBiography biography = proposalDocument
				.getDevelopmentProposal().getPropPersonBio(0);
		context.checking(new Expectations() {
			{
				oneOf(dataObjectService).delete(biography);
			}
		});
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(
				proposalDocument, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test_deleteProposalPersonBiography_expecting_IOBException() {
		final DataObjectService dataObjectService = context
                .mock(DataObjectService.class);
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(
				proposalDocument, 4);
	}

	@Test(expected = NullPointerException.class)
	public void test_deleteProposalPersonBiography_with_no_document() {
		final DataObjectService dataObjectService = context
                .mock(DataObjectService.class);
		proposalPersonBiographyService
				.setDataObjectService(dataObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(null, 0);
	}

	@Test
	public void test_prepareProposalPersonBiographyForSave() {
		final DevelopmentProposal developmentProposal = proposalDocument
				.getDevelopmentProposal();
		final ProposalPersonBiography biography = createBiography(proposalDocument);
		proposalPersonBiographyService.prepareProposalPersonBiographyForSave(
				developmentProposal, biography);
	}

	@Test(expected = NullPointerException.class)
	public void test_prepareProposalPersonBiographyForSave_with_no_proposal() {
		final ProposalPersonBiography biography = createBiography(proposalDocument);
		proposalPersonBiographyService.prepareProposalPersonBiographyForSave(
				null, biography);
	}

	public ProposalDevelopmentDocument createProposal() {
		ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
		document.getDevelopmentProposal().setProposalNumber("11");
		List<ProposalPersonBiography> biographies = new ArrayList<ProposalPersonBiography>();
		ProposalPersonBiography proposalPersonBiography = createBiography(document);
		biographies.add(proposalPersonBiography);
		document.getDevelopmentProposal().setPropPersonBios(biographies);
		return document;
	}

	public ProposalPersonBiography createBiography(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		final ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography() {
			@Override
			public void refreshReferenceObject(String referenceObjectName) {
			}
		};
		proposalPersonBiography
				.setDevelopmentProposal(proposalDevelopmentDocument
						.getDevelopmentProposal());
		proposalPersonBiography.setBiographyNumber(proposalDevelopmentDocument
				.getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
		proposalPersonBiography.setPropPerDocType(new PropPerDocType());
		proposalPersonBiography.setProposalPersonNumber(11);
		final List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		final ProposalPerson person = new ProposalPerson();
		person.setProposalPersonNumber(11);
		person.setPersonId("11");
		person.setRolodexId(11);
		proposalPersons.add(person);
		proposalDevelopmentDocument.getDevelopmentProposal()
				.setProposalPersons(proposalPersons);
		proposalPersonBiography.setPersonId(person.getPersonId());
		proposalPersonBiography.setRolodexId(person.getRolodexId());
		proposalPersonBiography.setDocumentTypeCode("11");
		proposalPersonBiography.getPropPerDocType().setCode(
				proposalPersonBiography.getDocumentTypeCode());
		final List<DocumentNextvalue> documentNextvalues = new ArrayList<DocumentNextvalue>();
		final DocumentNextvalue nextvalue = new DocumentNextvalue();
		nextvalue.setPropertyName(Constants.PROP_PERSON_BIO_NUMBER);
		nextvalue.setNextValue(12);
		documentNextvalues.add(nextvalue);
		proposalDevelopmentDocument.setDocumentNextvalues(documentNextvalues);
		return proposalPersonBiography;
	}
}
