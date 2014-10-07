package org.kuali.coeus.propdev.impl.person.attachment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.attachment.AttachmentDao;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

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
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		final ProposalPersonBiography proposalPersonBiography = createBiography(proposalDocument);
		final DocumentNextvalue documentNextvalue = proposalDocument
				.getDocumentNextvalueBo(Constants.PROP_PERSON_BIO_NUMBER);
		final List<PersistableBusinessObject> businessObjects = new ArrayList<PersistableBusinessObject>();
		businessObjects.add(documentNextvalue);
		businessObjects.add(proposalPersonBiography);
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).save(businessObjects);
				will(returnValue(businessObjects));
			}
		});
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
		proposalPersonBiographyService.addProposalPersonBiography(
				proposalDocument, proposalPersonBiography);
	}

	@Test(expected = NullPointerException.class)
	public void test_addProposalPersonBiography_with_no_document_and_biography() {
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		final ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography();
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
		proposalPersonBiographyService.addProposalPersonBiography(null,
				proposalPersonBiography);
	}

	@Test(expected = NullPointerException.class)
	public void test_addProposalPersonBiography_with_no_document_and_no_biography() {
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
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
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		final ProposalPersonBiography biography = proposalDocument
				.getDevelopmentProposal().getPropPersonBio(0);
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).delete(biography);
			}
		});
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(
				proposalDocument, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test_deleteProposalPersonBiography_expecting_IOBException() {
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(
				proposalDocument, 4);
	}

	@Test(expected = NullPointerException.class)
	public void test_deleteProposalPersonBiography_with_no_document() {
		final BusinessObjectService businessObjectService = context
				.mock(BusinessObjectService.class);
		proposalPersonBiographyService
				.setBusinessObjectService(businessObjectService);
		proposalPersonBiographyService.deleteProposalPersonBiography(null, 0);
	}

	@Test
	public void test_setPersonnelBioTimeStampUser() {
		final List<ProposalPersonBiography> proposalPersonBios = proposalDocument
				.getDevelopmentProposal().getPropPersonBios();
		final AttachmentDao attachmentDao = context.mock(AttachmentDao.class);
		final Iterator iterator = proposalPersonBios.iterator();
		final Object objects = (Object) iterator.next();
		context.checking(new Expectations() {
			{
				oneOf(attachmentDao).getPersonnelTimeStampAndUploadUser(11,
						"11", 1);
				will(returnValue(iterator));
			}
		});
		proposalPersonBiographyService.setAttachmentDao(attachmentDao);
		proposalPersonBiographyService
				.setPersonnelBioTimeStampUser(proposalPersonBios);
	}

	@Test(expected = NullPointerException.class)
	public void test_setPersonnelBioTimeStampUser_with_no_biography() {
		final AttachmentDao attachmentDao = context.mock(AttachmentDao.class);
		proposalPersonBiographyService.setAttachmentDao(attachmentDao);
		proposalPersonBiographyService.setPersonnelBioTimeStampUser(null);
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
