/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.apache.xmlbeans.XmlObject;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.s2sgen.impl.validate.S2SValidatorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import java.util.ArrayList;
import java.util.Calendar;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;


public abstract class S2SModularBudgetTestBase extends S2STestBase {
	private S2SBaseFormGenerator generatorObject;

	@Test
	public void testValidateForm() throws Exception {
		ProposalDevelopmentDocument document = initializeApp();
		prepareData(document);
		ArrayList<AuditError> errors = new ArrayList<AuditError>();
		generatorObject.setAuditErrors(errors);
		generatorObject.setAttachments(new ArrayList<AttachmentData>());
		XmlObject object = generatorObject.getFormObject(document);
		getService(S2SValidatorService.class).validate(object, errors, generatorObject.getFormName());
		for (AuditError auditError : errors) {
			assertNull(
					auditError.getMessageKey() + ":" + auditError.getErrorKey(),
					auditError.getErrorKey());
		}
	}

	private void saveProposalDocument(ProposalDevelopmentDocument pd)
			throws Exception {
		pd.setUpdateUser("quickst");
		pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		DocumentHeader docHeader = pd.getDocumentHeader();
		docHeader.setDocumentDescription("Test s2s service description");
		String docNumber = docHeader.getDocumentNumber();
		assertNotNull(docNumber);
		assertNotNull(pd.getDevelopmentProposal());
		KRADServiceLocatorWeb.getDocumentService().saveDocument(pd);
	}

	private ProposalDevelopmentDocument initializeDocument() throws Exception {
		ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) KRADServiceLocatorWeb
				.getDocumentService().getNewDocument(
						"ProposalDevelopmentDocument");
		Assert.assertNotNull(pd.getDocumentHeader().getWorkflowDocument());
		ProposalDevelopmentService pdService = getService(ProposalDevelopmentService.class);
		pdService.initializeUnitOrganizationLocation(pd);
		pdService.initializeProposalSiteNumbers(pd);
		return pd;
	}

	private DevelopmentProposal initializeDevelopmentProposal(
			ProposalDevelopmentDocument pd) {
		DevelopmentProposal developmentProposal = pd.getDevelopmentProposal();
		developmentProposal.setPrimeSponsorCode("000120");
		developmentProposal.setActivityTypeCode("1");
		developmentProposal.refreshReferenceObject("activityType");
		developmentProposal.setSponsorCode("000162");
		developmentProposal.setOwnedByUnitNumber("000001");
		developmentProposal.refreshReferenceObject("ownedByUnit");
		developmentProposal.setProposalTypeCode("1");
		developmentProposal.setCreationStatusCode("1");
		developmentProposal.setApplicantOrganizationId("000001");
		developmentProposal.setPerformingOrganizationId("000001");
		developmentProposal.setNoticeOfOpportunityCode("1");
		developmentProposal.setRequestedStartDateInitial(new java.sql.Date(
				Calendar.getInstance().getTimeInMillis()));
		developmentProposal.setRequestedEndDateInitial(new java.sql.Date(
				Calendar.getInstance().getTimeInMillis()));
		developmentProposal.setTitle("Test s2s service title");
		developmentProposal.setDeadlineType("P");
		developmentProposal.setDeadlineDate(new java.sql.Date(Calendar
				.getInstance().getTimeInMillis()));
		developmentProposal.setNsfCode("J.05");
		return developmentProposal;
	}

	private ProposalDevelopmentDocument initializeApp() throws Exception {

		generatorObject = (S2SBaseFormGenerator) KcServiceLocator.getService(getFormGeneratorName());
				;
		ProposalDevelopmentDocument document = initializeDocument();
		initializeDevelopmentProposal(document);
		Assert.assertNotNull(document.getDocumentHeader().getWorkflowDocument());
		saveProposalDocument(document);
		document = (ProposalDevelopmentDocument) KRADServiceLocatorWeb
				.getDocumentService().getByDocumentHeaderId(
						document.getDocumentHeader().getDocumentNumber());
		assertNotNull(document.getDevelopmentProposal());
		return document;
	}
}
