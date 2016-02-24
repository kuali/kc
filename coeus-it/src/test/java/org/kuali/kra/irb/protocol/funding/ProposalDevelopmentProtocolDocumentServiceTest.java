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
package org.kuali.kra.irb.protocol.funding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.irb.protocol.funding.impl.ProposalDevelopmentProtocolDocumentServiceImpl;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.funding.impl.ProposalDevelopmentProtocolDocumentServiceImplBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.SequenceAccessorService;


public class ProposalDevelopmentProtocolDocumentServiceTest extends
		KcIntegrationTestBase {

	ProposalDevelopmentDocument document1;
	ProposalDevelopmentDocument document2;
	DocumentService documentService;
	KcPersonService kcPersonService;
	ProtocolNumberService protocolNumberService;
	KcAuthorizationService kcAuthorizationService;
	SequenceAccessorService sequenceAccessorService;
	ProtocolPersonnelService protocolPersonnelService;
	UnitAuthorizationService unitAuthorizationService;
	SystemAuthorizationService systemAuthorizationService;	
	
	ProposalDevelopmentProtocolDocumentServiceImpl proposalDevelopmentProtocolDocumentService;

	@Before
	public void setUp() throws Exception {

		document1 = new ProposalDevelopmentDocument();
		document2 = new ProposalDevelopmentDocument();
		addProposalRequiredFields();
		proposalDevelopmentProtocolDocumentService = new ProposalDevelopmentProtocolDocumentServiceImpl();

		documentService = KcServiceLocator.getService(DocumentService.class);
		kcPersonService = KcServiceLocator.getService(KcPersonService.class);
		protocolNumberService = KcServiceLocator.getService(ProtocolNumberService.class);
		kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);		
		sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
		unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
		protocolPersonnelService = KcServiceLocator.getService(ProtocolPersonnelService.class);
		systemAuthorizationService = KcServiceLocator.getService(SystemAuthorizationService.class);

		proposalDevelopmentProtocolDocumentService.setDocumentService(documentService);
		proposalDevelopmentProtocolDocumentService.setKcPersonService(kcPersonService);
		proposalDevelopmentProtocolDocumentService.setProtocolNumberService(protocolNumberService);
		proposalDevelopmentProtocolDocumentService.setKraAuthorizationService(kcAuthorizationService);		
		proposalDevelopmentProtocolDocumentService.setSequenceAccessorService(sequenceAccessorService);		
		proposalDevelopmentProtocolDocumentService.setUnitAuthorizationService(unitAuthorizationService);
		proposalDevelopmentProtocolDocumentService.setProtocolPersonnelService(protocolPersonnelService);
		proposalDevelopmentProtocolDocumentService.setSystemAuthorizationService(systemAuthorizationService);		
	}

	public void addProposalRequiredFields() {
		
		List<ProposalPerson> proposalPersonsList = new ArrayList<ProposalPerson>();
		ProposalPerson person = new ProposalPerson();
        person.setProposalPersonRoleId("PI");
        person.setPersonId("10000000008");
        person.setFullName("ALAN MCAFEE");
        proposalPersonsList.add(person);

        Sponsor sponsor = new Sponsor();
		sponsor.setSponsorCode("000340");
		sponsor.setSponsorName("NIH");
		
		document1.getDevelopmentProposal().setSponsor(sponsor);
		document1.getDevelopmentProposal().setSponsorCode("000340");
		document1.getDevelopmentProposal().setTitle("Title");
		document1.getDevelopmentProposal().setOwnedByUnitNumber("000001");
        document1.getDevelopmentProposal().setProposalPersons(proposalPersonsList);
	}

	@Test
	public void test_createProtocolDocument_whenAuthorizedToCreateProtocol()
			throws Exception {
		ProtocolDocument protocolDocument = proposalDevelopmentProtocolDocumentService.createProtocolDocument(document1);
		assertNotNull(protocolDocument);
		assertEquals(protocolDocument.getProtocol().getProtocolPerson(0).getPersonName(), 
				document1.getDevelopmentProposal().getPrincipalInvestigatorName());
		assertEquals(protocolDocument.getProtocol().getProtocolFundingSources().get(0).getFundingSourceName(),
				document1.getDevelopmentProposal().getSponsorName());
		assertEquals(protocolDocument.getProtocol().getTitle(),
				document1.getDevelopmentProposal().getTitle());
		assertEquals(protocolDocument.getProtocol().getLeadUnitNumber(),
				document1.getDevelopmentProposal().getOwnedByUnitNumber());
		assertEquals(protocolDocument.getDocumentHeader().getOrganizationDocumentNumber(),
				document1.getDocumentHeader().getOrganizationDocumentNumber());		
	}
	
	@Test
	public void test_createProtocolDocument_whenNotAuthorizedToCreateProtocol()
			throws Exception {
        assertNull(proposalDevelopmentProtocolDocumentService.createProtocolDocument(document2));
	}

	@Test
	public void test_isAuthorizedCreateProtocol() throws Exception {
		
		Method m  = ProposalDevelopmentProtocolDocumentServiceImplBase.class.
	    		 getDeclaredMethod("hasProposalRequiredFields", DevelopmentProposal.class);
	    m.setAccessible(true);
		
		assertTrue(proposalDevelopmentProtocolDocumentService.isAuthorizedCreateProtocol(document1));
		assertTrue((Boolean)m.invoke(proposalDevelopmentProtocolDocumentService, document1.getDevelopmentProposal()));
		assertFalse(proposalDevelopmentProtocolDocumentService.isAuthorizedCreateProtocol(document2));
		assertFalse((Boolean)m.invoke(proposalDevelopmentProtocolDocumentService, document2.getDevelopmentProposal()));
	}
}
