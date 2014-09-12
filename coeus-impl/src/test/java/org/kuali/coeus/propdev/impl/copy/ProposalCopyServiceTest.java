package org.kuali.coeus.propdev.impl.copy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.eclipse.persistence.internal.identitymaps.CacheKey;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Link;
import org.eclipse.persistence.queries.FetchGroup;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyServiceImpl;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyServiceImpl.DocProperty;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;

public class ProposalCopyServiceTest {
	ProposalCopyServiceImpl proposalCopyService = null;
	Mockery context;
	ProposalCopyCriteria criteria;
	KualiRuleService kualiRuleService;
	ProposalDevelopmentDocument proposalDocument;

	@Before
	public void setUp() throws Exception {
		KcServiceLocator.getService(ProposalDevelopmentService.class);
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		proposalCopyService = new ProposalCopyServiceImpl();
		kualiRuleService = context.mock(KualiRuleService.class);
		ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
		doc.getDevelopmentProposal().setSubmitFlag(true);

		Mockery mockery;
		WorkflowDocument mock;
		mockery = new JUnit4Mockery();
		mock = mockery.mock(WorkflowDocument.class);
		DocumentHeader docHdr = new DocumentHeader();
		docHdr.setWorkflowDocument((WorkflowDocument) mock);
		doc.setDocumentHeader(docHdr);

		criteria = new ProposalCopyCriteria();
		DocumentService docService = context.mock(DocumentService.class);
		proposalDocument = createProposal("Proposal-2", "000001", docService);
		proposalDocument._persistence_checkFetched("One");
		proposalDocument._persistence_checkFetchedForSet("Two");
		proposalDocument._persistence_set("1", "1");
		proposalDocument._persistence_set_documentNumber("1234");
		proposalDocument._persistence_set_objectId("x1");
		proposalDocument._persistence_setFetchGroup(new FetchGroup());
		proposalDocument._persistence_setCacheKey(new CacheKey("id"));
		proposalDocument._persistence_setHref(new Link());
		proposalDocument.setUpdateUser("quickstart");
	}

	private ProposalDevelopmentDocument createProposal(
			String documentDescription, String leadUnitNumber,
			DocumentService docService) throws Exception {
		ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
		Date requestedStartDateInitial = new Date(System.currentTimeMillis());
		Date requestedEndDateInitial = new Date(System.currentTimeMillis());

		document.getDocumentHeader()
				.setDocumentDescription(documentDescription);
		document.getDevelopmentProposal().setSponsorCode("000162");
		document.getDevelopmentProposal().setTitle("project title");
		document.getDevelopmentProposal().setRequestedStartDateInitial(
				requestedStartDateInitial);
		document.getDevelopmentProposal().setRequestedEndDateInitial(
				requestedEndDateInitial);
		document.getDevelopmentProposal().setActivityTypeCode("1");
		document.getDevelopmentProposal().setProposalTypeCode("1");
		document.getDevelopmentProposal().setOwnedByUnitNumber(leadUnitNumber);
		document.getDevelopmentProposal().setPrimeSponsorCode("000120");
		return document;
	}

	@After
	public void tearDown() throws Exception {
		proposalDocument = null;
		proposalCopyService = null;
	}

	@Test
	public void getCopyablePropertiesTest() {
		assertNotNull(proposalCopyService.getCopyableProperties());
	}

	@Test
	public void getCopyablePropertiesTestNonZero() {
		List<DocProperty> properties = proposalCopyService
				.getCopyableProperties();
		assertNotEquals(properties.size(), 0);
	}

	@Test
	public void getCopyablePropertiesTestCount() {
		List<DocProperty> properties = proposalCopyService
				.getCopyableProperties();
		assertNotEquals(new Long(properties.size()), new Long(0));
	}

	@Test
	public void isFilteredPropertyTest() {
		boolean isFiltered = proposalCopyService
				.isFilteredProperty("isFilteredProperty()");
		assertFalse(isFiltered);
	}

	@Test
	public void findProposalPersonUnitTestNull() {
		List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
		units.add(new ProposalPersonUnit());
		assertNotNull((Object) proposalCopyService.findProposalPersonUnit(null,
				units));
	}

	@Test
	public void findProposalPersonUnitTestInvalid() {
		List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
		units.add(new ProposalPersonUnit());
		assertNull((Object) proposalCopyService.findProposalPersonUnit("205",
				units));
	}

	@Test
	public void findProposalPersonUnitTestNone() {
		List<ProposalPersonUnit> units = new ArrayList<ProposalPersonUnit>();
		units.add(new ProposalPersonUnit());
		assertNull((Object) proposalCopyService.findProposalPersonUnit("",
				units));
	}

	@Test
	public void removePersonNarrativePermissionTest() {
		org.kuali.coeus.propdev.impl.attachment.Narrative narative = new org.kuali.coeus.propdev.impl.attachment.Narrative();
		assertNotNull((Object) narative);
		proposalCopyService.removePersonNarrativePermission("", narative);
	}

	@Test(expected = NullPointerException.class)
	public void copyProposalTest() throws Exception {
		proposalCopyService.setKualiRuleService(kualiRuleService);
		assertNotNull(proposalCopyService.copyProposal(proposalDocument,
				criteria));
	}

	@Test(expected = NullPointerException.class)
	public void createNewProposalTest() throws Exception {
		proposalCopyService.setKualiRuleService(kualiRuleService);
		assertNotNull(proposalCopyService.createNewProposal(proposalDocument,
				criteria));
	}

	@Test(expected = NullPointerException.class)
	public void copyProposalPropertiesTest() throws Exception {
		ProposalDevelopmentDocument newDocument = new ProposalDevelopmentDocument();
		proposalCopyService.setKualiRuleService(kualiRuleService);
		proposalCopyService.copyProposalProperties(proposalDocument,
				newDocument);
		assertEquals(proposalDocument.getDevelopmentProposal(),
				newDocument.getDevelopmentProposal());
	}

	@Test(expected = NullPointerException.class)
	public void copyRequiredPropertiesTest() {
		ProposalDevelopmentDocument newDocument = new ProposalDevelopmentDocument();
		proposalCopyService.setKualiRuleService(kualiRuleService);
		proposalCopyService.copyRequiredProperties(proposalDocument,
				newDocument);
		assertEquals(proposalDocument.getDevelopmentProposal(),
				newDocument.getDevelopmentProposal());
	}

	@Test(expected = NullPointerException.class)
	public void copyProposalWithProperties() throws Exception {
		ProposalDevelopmentDocument newDocument = new ProposalDevelopmentDocument();
		proposalCopyService.setKualiRuleService(kualiRuleService);
		proposalCopyService.copyProposal(proposalDocument, newDocument,
				criteria);
		assertEquals(proposalDocument.getDevelopmentProposal(),
				newDocument.getDevelopmentProposal());
	}

	@Test(expected = NullPointerException.class)
	public void copyOverviewPropertiesTest() {
		ProposalDevelopmentDocument newDocument = new ProposalDevelopmentDocument();
		proposalCopyService.setKualiRuleService(kualiRuleService);
		proposalCopyService.copyOverviewProperties(proposalDocument,
				newDocument);
		assertEquals(proposalDocument.getDevelopmentProposal(),
				newDocument.getDevelopmentProposal());
	}

	@Test(expected = InvocationTargetException.class)
	public void fixProposalTest() throws Exception {
		ProposalDevelopmentDocument newDocument = new ProposalDevelopmentDocument();
		proposalCopyService.setKualiRuleService(kualiRuleService);
		proposalCopyService
				.fixProposal(proposalDocument, newDocument, criteria);
		assertEquals(proposalDocument, newDocument);
	}
}
