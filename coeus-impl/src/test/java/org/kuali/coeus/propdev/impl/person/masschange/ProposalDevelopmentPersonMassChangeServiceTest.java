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
package org.kuali.coeus.propdev.impl.person.masschange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;

public class ProposalDevelopmentPersonMassChangeServiceTest {
	ProposalDevelopmentPersonMassChangeServiceImpl proposalDevelopmentPersonMassChangeServiceImpl;
	Mockery context;
	ProposalCopyCriteria criteria;
	KualiRuleService kualiRuleService;
	ProposalDevelopmentDocument proposalDocument;
	BusinessObjectService businessObjectService;

	@Before
	public void setUp() throws Exception {
		proposalDevelopmentPersonMassChangeServiceImpl = new ProposalDevelopmentPersonMassChangeServiceImpl();
		KcServiceLocator.getService(ProposalDevelopmentService.class);
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		kualiRuleService = context.mock(KualiRuleService.class);
		businessObjectService = context.mock(BusinessObjectService.class);
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
        document.getDevelopmentProposal().setProposalDocument(document);
		return document;
	}

	@After
	public void tearDown() throws Exception {
		proposalDevelopmentPersonMassChangeServiceImpl = null;
		context = null;
		criteria = null;
		kualiRuleService = null;
		proposalDocument = null;
		businessObjectService = null;
	}

	@Test
	public void test_getProposalDevelopmentChangeCandidates() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<DevelopmentProposal> proposalList = proposalDevelopmentPersonMassChangeServiceImpl
				.getProposalDevelopmentChangeCandidates(personMassChange);
		assertNotNull(proposalList);
	}

	@Test
	public void test_getProposalDevelopmentChangeCandidates_Count() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<DevelopmentProposal> proposalList = proposalDevelopmentPersonMassChangeServiceImpl
				.getProposalDevelopmentChangeCandidates(personMassChange);
		assertEquals(proposalList.size(), 0);
	}

	@Test
	public void test_getProposalDevelopmentChangeCandidates_Size() {
		PersonMassChange personMassChange = new PersonMassChange();
		ProposalDevelopmentPersonMassChange proposalDevelopmentPersonMassChange = new ProposalDevelopmentPersonMassChange();
		proposalDevelopmentPersonMassChangeServiceImpl
				.setBusinessObjectService(businessObjectService);
		personMassChange
				.setProposalDevelopmentPersonMassChange(proposalDevelopmentPersonMassChange);
		List<DevelopmentProposal> proposalList = proposalDevelopmentPersonMassChangeServiceImpl
				.getProposalDevelopmentChangeCandidates(personMassChange);
		assertFalse("", (proposalList.size() < 0));
	}

	@Test
	public void test_performPersonMassChange() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<DevelopmentProposal> proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
		proposalDevelopmentChangeCandidates.add(proposalDocument
				.getDevelopmentProposal());
		proposalDevelopmentPersonMassChangeServiceImpl.performPersonMassChange(
				personMassChange, proposalDevelopmentChangeCandidates);
	}

	@Test(expected = NullPointerException.class)
	public void test_performPersonMassChange_noArg() {
		proposalDevelopmentPersonMassChangeServiceImpl.performPersonMassChange(
				null, null);
	}

	@Test
	public void test_performPersonMassChange_emptyArg() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<DevelopmentProposal> proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
		proposalDevelopmentPersonMassChangeServiceImpl.performPersonMassChange(
				personMassChange, proposalDevelopmentChangeCandidates);
	}

	@Test
	public void test_performPersonMassChange_no_personMassChange() {
		List<DevelopmentProposal> proposalDevelopmentChangeCandidates = new ArrayList<DevelopmentProposal>();
		proposalDevelopmentPersonMassChangeServiceImpl.performPersonMassChange(
				null, proposalDevelopmentChangeCandidates);
	}

	@Test(expected = NullPointerException.class)
	public void test_performPersonMassChange_no_proposalDevelopmentChangeCandidates() {
		PersonMassChange personMassChange = new PersonMassChange();
		proposalDevelopmentPersonMassChangeServiceImpl.performPersonMassChange(
				personMassChange, null);
	}
}
