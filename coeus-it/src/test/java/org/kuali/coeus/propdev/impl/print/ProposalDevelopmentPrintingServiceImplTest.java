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
package org.kuali.coeus.propdev.impl.print;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplate;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplateList;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrint;
import org.kuali.coeus.common.questionnaire.impl.print.QuestionnaireXmlStream;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

public class ProposalDevelopmentPrintingServiceImplTest extends KcIntegrationTestBase {

	public BusinessObjectService businessObjectService;
	private ParameterService parameterService;
	
	@Before
	public void setup() {
		businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
		parameterService = KcServiceLocator.getService(ParameterService.class);
	}
	
	@Test
	public void test_printProposalDevelopmentReport()
	{
		final ProposalDevelopmentPrintingServiceImpl proposalDevelopmentPrintingServiceImpl = new ProposalDevelopmentPrintingServiceImpl();
		final PrintingService printingService = KcServiceLocator.getService(PrintingService.class);
		final PrintCertificationPrint printCertificationPrint = new PrintCertificationPrint();
		final ProposalSponsorFormsPrint proposalSponsorFormsPrint = new ProposalSponsorFormsPrint();
		final ProposalDevelopmentDocument pdDocument = new ProposalDevelopmentDocument();		
		DevelopmentProposal developmentProposal = new DevelopmentProposal(){
			@Override
			public void refresh() {				
			}
			
			@Override
			public void refreshReferenceObject(String referenceObjectName) {
			}
		};		
		pdDocument.setDevelopmentProposal(developmentProposal);			
		pdDocument.getDevelopmentProposal().setProposalNumber("123");		
		pdDocument.getDocumentHeader().setDocumentDescription(this.getClass().getName());
		pdDocument.getDevelopmentProposal().setSponsorCode("999999");
		pdDocument.getDevelopmentProposal().setTitle("Project title");
		pdDocument.getDevelopmentProposal().setRequestedStartDateInitial(new Date(Calendar.getInstance().getTime().getTime()));
		pdDocument.getDevelopmentProposal().setRequestedEndDateInitial(new Date(Calendar.getInstance().getTime().getTime()));	
		Sponsor sponsor = new Sponsor();
		sponsor.setSponsorName("sponsorname");
		sponsor.setSponsorCode("101");
		pdDocument.getDevelopmentProposal().setSponsor(sponsor);
		pdDocument.getDevelopmentProposal().setPrimeSponsor(sponsor);			
		Unit unit = new Unit();
		unit.setUnitNumber("00001");
		unit.setUnitName("testUnit");		
		pdDocument.getDevelopmentProposal().setOwnedByUnit(unit);
		SponsorFormTemplate sponsorFormTemplate = new SponsorFormTemplate();
		sponsorFormTemplate.setSponsorFormTemplateId(1L);
		Map<String, Object> reportParameters = new HashMap<String, Object>();
		printCertificationPrint.setPrintableBusinessObject(developmentProposal);
		printCertificationPrint.setXmlStream(KcServiceLocator.getService(ProposalDevelopmentXmlStream.class));
		proposalSponsorFormsPrint.setPrintableBusinessObject(developmentProposal);
		proposalSponsorFormsPrint.setXmlStream(KcServiceLocator.getService(ProposalDevelopmentXmlStream.class));
		proposalSponsorFormsPrint.setProposalDevelopmentXmlStream(KcServiceLocator.getService(ProposalDevelopmentXmlStream.class));
        proposalDevelopmentPrintingServiceImpl.setPrintCertificationPrint(printCertificationPrint);
        proposalDevelopmentPrintingServiceImpl.setProposalSponsorFormsPrint(proposalSponsorFormsPrint);
        proposalDevelopmentPrintingServiceImpl.setPrintingService(printingService);
        assertNotNull(proposalDevelopmentPrintingServiceImpl.printProposalDevelopmentReport(developmentProposal, "Print Certification", reportParameters));	
	}
	
	@Test
	public void test_populateSponsorForms()
	{
		final ProposalDevelopmentPrintingServiceImpl proposalDevelopmentPrintingServiceImpl = new ProposalDevelopmentPrintingServiceImpl();
		List<SponsorFormTemplateList> sponsorFormTemplates = new ArrayList<SponsorFormTemplateList>();
		proposalDevelopmentPrintingServiceImpl.setBusinessObjectService(businessObjectService);
		proposalDevelopmentPrintingServiceImpl.setParameterService(parameterService);
		proposalDevelopmentPrintingServiceImpl.populateSponsorForms(sponsorFormTemplates, "009800");	
	}
	
	@Test
	public void test_getSponsorFormTemplates()
	{
		final ProposalDevelopmentPrintingServiceImpl proposalDevelopmentPrintingServiceImpl = new ProposalDevelopmentPrintingServiceImpl();
		final List<SponsorFormTemplateList> sponsorFormTemplateLists= new ArrayList<SponsorFormTemplateList>();
		final SponsorFormTemplateList sponsorFormTemplateList = new SponsorFormTemplateList();
		sponsorFormTemplateList.setSponsorFormTemplateId(1L);
		sponsorFormTemplateList.setSelectToPrint(true);
		sponsorFormTemplateLists.add(sponsorFormTemplateList);
		proposalDevelopmentPrintingServiceImpl.setBusinessObjectService(businessObjectService);
		assertTrue(proposalDevelopmentPrintingServiceImpl.getSponsorFormTemplates(sponsorFormTemplateLists).size() > 0);		
	}
	
	@Test
	public void test_printPersonCertificationQuestionnaire() throws Exception {
		final ProposalDevelopmentPrintingServiceImpl proposalDevelopmentPrintingServiceImpl = new ProposalDevelopmentPrintingServiceImpl();
		final PrintingService printingService = KcServiceLocator.getService(PrintingService.class);
		final QuestionnaireAnswerService questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
		final QuestionnairePrint questionnairePrint = new QuestionnairePrint();		
		QuestionnaireXmlStream questionnaireXmlStream =  new QuestionnaireXmlStream();
		questionnaireXmlStream.setBusinessObjectService(businessObjectService);
		questionnaireXmlStream.setQuestionnaireAnswerService(questionnaireAnswerService);
		questionnairePrint.setXmlStream(questionnaireXmlStream);
		final ProposalDevelopmentDocument proposalDevelopmentDocument = initializeApp();		
		final List<ProposalPerson> persons = new ArrayList<ProposalPerson>();
		final ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setFirstName("ALAN");
		proposalPerson.setLastName("MCAFEE");
		proposalPerson.setFullName("ALAN  MCAFEE");
		proposalPerson.setPersonId("10000000018");
		proposalPerson.setUserName("aemcafee");
		proposalPerson.setProposalPersonRoleId("PI");
		proposalPerson.setAddressLine1("1135 Kuali Drive");
		proposalPerson.setActive(true);
		proposalPerson.setDevelopmentProposal(proposalDevelopmentDocument.getDevelopmentProposal());
		persons.add(proposalPerson);
		proposalDevelopmentPrintingServiceImpl.setQuestionnairePrint(questionnairePrint);
		proposalDevelopmentPrintingServiceImpl.setPrintingService(printingService);
		proposalDevelopmentPrintingServiceImpl.setBusinessObjectService(businessObjectService);
		assertNotNull(proposalDevelopmentPrintingServiceImpl.printPersonCertificationQuestionnaire(persons));
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
		developmentProposal.setNsfSequenceNumber(1);
		return developmentProposal;
	}
	
	private ProposalDevelopmentDocument initializeApp() throws Exception {

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

}
