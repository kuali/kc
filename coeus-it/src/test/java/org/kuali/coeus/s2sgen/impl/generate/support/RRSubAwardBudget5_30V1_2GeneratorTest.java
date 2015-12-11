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

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.junit.Assert;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentService;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class RRSubAwardBudget5_30V1_2GeneratorTest extends
		S2SModularBudgetTestBase {

	@Override
	protected String getFormGeneratorName() {
		return RRSubAwardBudget5_30V1_2Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
		proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
		proposalDevelopmentBudgetExt.setDevelopmentProposal(document
				.getDevelopmentProposal());
		proposalDevelopmentBudgetExt.setBudgetStatus("1");
		proposalDevelopmentBudgetExt.setBudgetId(1L);
		proposalDevelopmentBudgetExt
				.setName("test Document Description");
		proposalDevelopmentBudgetExt.setOnOffCampusFlag("Y");
		proposalDevelopmentBudgetExt.setStartDate(new Date(new Long(
				"1183316613046")));
		proposalDevelopmentBudgetExt.setEndDate(new Date(new Long(
				"1214852613046")));
		proposalDevelopmentBudgetExt.setOhRateTypeCode("1");

		BudgetSubAwards budgetSubAwards = new BudgetSubAwards();
		budgetSubAwards.setSubAwardNumber(0001);
		budgetSubAwards.setBudgetId(1L);
		budgetSubAwards.setBudget(proposalDevelopmentBudgetExt);
		budgetSubAwards
				.setNamespace("http://apply.grants.gov/forms/RR_Budget-V1.1");
		Organization testOrg = new Organization();
		testOrg.setOrganizationName("University of Maine");
		testOrg.setOrganizationId("000040");
		budgetSubAwards.setOrganization(testOrg);
		budgetSubAwards.setSubAwardStatusCode(1);
		budgetSubAwards.setHiddenInHierarchy(false);

		S2SBaseFormGenerator generatorObject1;
		generatorObject1 = KcServiceLocator
				.getService(RRBudgetV1_1Generator.class.getSimpleName());
		generatorObject1.setAttachments(new ArrayList<AttachmentData>());
		ProposalDevelopmentDocument doc = initializeDocument();
		initializeDevelopmentProposal(doc);
		prepareDatas(doc);
		XmlObject object = generatorObject1.getFormObject(doc);
		Forms forms = Forms.Factory.newInstance();
		setFormObject(forms, object);
		GrantApplication grantApplication = GrantApplication.Factory
				.newInstance();
		grantApplication.setForms(forms);

		budgetSubAwards.setSubAwardXmlFileData(grantApplication.xmlText());
		List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<BudgetSubAwards>();
		budgetSubAwardsList.add(budgetSubAwards);

		proposalDevelopmentBudgetExt.setBudgetSubAwards(budgetSubAwardsList);
		proposalDevelopmentBudgetExt.setOhRateClassCode("1");
		proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
		proposalDevelopmentBudgetExt.setUrRateClassCode("1");
		proposalDevelopmentBudgetExt = getService(DataObjectService.class)
				.save(proposalDevelopmentBudgetExt);

		List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<ProposalDevelopmentBudgetExt>();
		proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);
		document.getDevelopmentProposal().setBudgets(
				proposalDevelopmentBudgetExtList);
		document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);
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

	public void prepareDatas(ProposalDevelopmentDocument document)
			throws Exception {

		Organization organization = new Organization();
		organization.setOrganizationName("University");
		organization.setOrganizationId("000001");
		organization.setContactAddressId(1);

		ProposalSite applicantOrganization = new ProposalSite();
		applicantOrganization.setLocationTypeCode(2);
		applicantOrganization.setOrganization(organization);
		applicantOrganization.setSiteNumber(1);
		applicantOrganization.setLocationName(organization
				.getOrganizationName());
		document.getDevelopmentProposal().setApplicantOrganization(
				applicantOrganization);
		document.getDevelopmentProposal().getApplicantOrganization()
				.getOrganization().setDunsNumber("00-176-5866");

		NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
				ClassLoaderUtils.getDefaultClassLoader());
		Resource resource = resourceLoader
				.getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
		InputStream inputStream = resource.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		byte[] narrativePdf = new byte[bis.available()];
		narrativeAttachment.setData(narrativePdf);
		narrativeAttachment.setName("exercise1");
		Narrative narrative = new Narrative();
		List<Narrative> narrativeList = new ArrayList<Narrative>();
		narrative.setDevelopmentProposal(document.getDevelopmentProposal());
		NarrativeType narrativeType = new NarrativeType();
		narrativeType.setCode("7");
		narrativeType.setAllowMultiple(true);
		narrativeType.setSystemGenerated(false);
		narrativeType.setDescription("Testing for Attachments Attachment");
		getService(DataObjectService.class).save(narrativeType);
		narrative.setNarrativeType(narrativeType);
		narrative.setNarrativeTypeCode("7");
		narrative.setNarrativeAttachment(narrativeAttachment);
		narrative.setModuleTitle("Allows Multiple Description");
		narrativeList.add(narrative);
		document.getDevelopmentProposal().setNarratives(narrativeList);

		List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		ProposalPerson principalInvestigator = new ProposalPerson();
		principalInvestigator.setFirstName("ALAN");
		principalInvestigator.setLastName("MCAFEE");
		principalInvestigator.setProposalPersonRoleId("PI");
		principalInvestigator.setPersonId("0001");
		principalInvestigator.setRolodexId(0010);
		proposalPersons.add(principalInvestigator);
		document.getDevelopmentProposal().setProposalPersons(proposalPersons);

		ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
		proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
		proposalDevelopmentBudgetExt.setBudgetStatus("1");
		proposalDevelopmentBudgetExt.setBudgetId(1L);
		proposalDevelopmentBudgetExt
				.setName("test Document Description");
		proposalDevelopmentBudgetExt.setOnOffCampusFlag("Y");
		proposalDevelopmentBudgetExt.setStartDate(new Date(new Long(
				"1183316613046")));
		proposalDevelopmentBudgetExt.setEndDate(new Date(new Long(
				"1214852613046")));
		proposalDevelopmentBudgetExt.setOhRateTypeCode("1");
		proposalDevelopmentBudgetExt.setOhRateClassCode("1");
		proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
		proposalDevelopmentBudgetExt.setUrRateClassCode("1");
		
		List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
		BudgetPeriod budgetPeriod = new BudgetPeriod();
		budgetPeriod.setBudgetPeriodId(1L);
		budgetPeriod.setStartDate(new Date(new Long("1183316613046")));
		budgetPeriod.setEndDate(new Date(new Long("1214852613046")));
		budgetPeriod.setBudgetPeriod(1);
		budgetPeriod.setBudget(proposalDevelopmentBudgetExt);
		budgetPeriods.add(budgetPeriod);
		proposalDevelopmentBudgetExt.setBudgetPeriods(budgetPeriods);
		
		List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<ProposalDevelopmentBudgetExt>();
		proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);
		document.getDevelopmentProposal().setBudgets(
				proposalDevelopmentBudgetExtList);
		document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);
	}

	protected void setFormObject(Forms forms, XmlObject formObject) {
		XmlCursor formCursor = formObject.newCursor();
		formCursor.toStartDoc();
		formCursor.toNextToken();
		XmlCursor metaGrantCursor = forms.newCursor();
		metaGrantCursor.toNextToken();
		formCursor.moveXml(metaGrantCursor);
	}
}
