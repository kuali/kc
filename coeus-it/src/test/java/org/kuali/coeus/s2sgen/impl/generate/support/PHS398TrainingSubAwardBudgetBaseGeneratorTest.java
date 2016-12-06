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

import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

public abstract class PHS398TrainingSubAwardBudgetBaseGeneratorTest extends S2STestBase {

    @Override
    protected void prepareData(ProposalDevelopmentDocument document)
            throws Exception {
        ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
        proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
        proposalDevelopmentBudgetExt.setDevelopmentProposal(document.getDevelopmentProposal());
        proposalDevelopmentBudgetExt.setBudgetStatus("1");
        proposalDevelopmentBudgetExt.setBudgetId(1L);
        proposalDevelopmentBudgetExt.setName("test Document Description");
        proposalDevelopmentBudgetExt.setOnOffCampusFlag("Y");
        proposalDevelopmentBudgetExt.setStartDate(new Date(new Long("1183316613046")));
        proposalDevelopmentBudgetExt.setEndDate(new Date(new Long("1214852613046")));
        proposalDevelopmentBudgetExt.setOhRateTypeCode("1");

        BudgetSubAwards budgetSubAwards = new BudgetSubAwards();
        budgetSubAwards.setSubAwardNumber(1);
        budgetSubAwards.setBudgetId(1L);
        budgetSubAwards.setBudget(proposalDevelopmentBudgetExt);
        budgetSubAwards.setNamespace("http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0");
        Organization testOrg = new Organization();
        testOrg.setOrganizationName("University of Maine");
        testOrg.setOrganizationId("000040");
        budgetSubAwards.setOrganization(testOrg);
        budgetSubAwards.setSubAwardStatusCode(1);
        budgetSubAwards.setHiddenInHierarchy(false);

        S2SBaseFormGenerator generatorObject1;
        generatorObject1 = KcServiceLocator.getService(PHS398TrainingBudgetV1_0Generator.class.getSimpleName());
        generatorObject1.setAttachments(new ArrayList<>());

        initializeDevelopmentProposal(document);
        prepareDocData(document);
        document = saveDocument(document);
        XmlObject object = generatorObject1.getFormObject(document);
        GrantApplicationDocument.GrantApplication.Forms forms = GrantApplicationDocument.GrantApplication.Forms.Factory.newInstance();
        setFormObject(forms, object);
        GrantApplicationDocument.GrantApplication grantApplication = GrantApplicationDocument.GrantApplication.Factory.newInstance();
        grantApplication.setForms(forms);

        budgetSubAwards.setSubAwardXmlFileData(grantApplication.xmlText());
        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<>();
        budgetSubAwardsList.add(budgetSubAwards);

        proposalDevelopmentBudgetExt.setBudgetSubAwards(budgetSubAwardsList);
        proposalDevelopmentBudgetExt.setOhRateClassCode("1");
        proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
        proposalDevelopmentBudgetExt.setUrRateClassCode("1");
        proposalDevelopmentBudgetExt = getService(DataObjectService.class).save(proposalDevelopmentBudgetExt);

        List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<>();
        proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);

        document.getDevelopmentProposal().setBudgets(proposalDevelopmentBudgetExtList);
        document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);
    }


    @Override
    public DevelopmentProposal initializeDevelopmentProposal(
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
        developmentProposal.setRequestedStartDateInitial(new Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setRequestedEndDateInitial(new Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setTitle("Test s2s service title");
        developmentProposal.setDeadlineType("P");
        developmentProposal.setDeadlineDate(new Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setNsfSequenceNumber(1);
        return developmentProposal;
    }

    public void prepareDocData(ProposalDevelopmentDocument document)
            throws Exception {

        Organization organization = new Organization();
        organization.setOrganizationName("University");
        organization.setOrganizationId("000001");
        organization.setContactAddressId(1);

        ProposalSite applicantOrganization = new ProposalSite();
        applicantOrganization.setLocationTypeCode(2);
        applicantOrganization.setOrganization(organization);

        applicantOrganization.setSiteNumber(1);
        applicantOrganization.setLocationName(organization.getOrganizationName());
        document.getDevelopmentProposal().setApplicantOrganization(applicantOrganization);
        document.getDevelopmentProposal().getApplicantOrganization().getOrganization().setDunsNumber("00-176-5866");

        List<Narrative> naList = new ArrayList<>();

        Narrative narrative1 = createNarrative("130");

        narrative1.setDevelopmentProposal(document.getDevelopmentProposal());
        naList.add(narrative1);
        document.getDevelopmentProposal().setNarratives(naList);

        List<ProposalPerson> proposalPersons = new ArrayList<>();
        ProposalPerson principalInvestigator = new ProposalPerson();
        principalInvestigator.setFirstName("ALAN");
        principalInvestigator.setLastName("MCAFEE");
        principalInvestigator.setProposalPersonNumber(1);
        principalInvestigator.setProposalPersonRoleId("PI");
        principalInvestigator.setPersonId("0001");
        proposalPersons.add(principalInvestigator);
        principalInvestigator.setDevelopmentProposal(document.getDevelopmentProposal());
        document.getDevelopmentProposal().setProposalPersons(proposalPersons);


        ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
        proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
        proposalDevelopmentBudgetExt.setBudgetStatus("1");
        proposalDevelopmentBudgetExt.setBudgetId(1L);
        proposalDevelopmentBudgetExt.setName("test Document Description");
        proposalDevelopmentBudgetExt.setOnOffCampusFlag("Y");
        proposalDevelopmentBudgetExt.setStartDate(new Date(new Long("1183316613046")));
        proposalDevelopmentBudgetExt.setEndDate(new Date(new Long("1214852613046")));
        proposalDevelopmentBudgetExt.setOhRateTypeCode("1");
        proposalDevelopmentBudgetExt.setOhRateClassCode("1");
        proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
        proposalDevelopmentBudgetExt.setUrRateClassCode("1");

        List<BudgetPeriod> budgetPeriods = new ArrayList<>();
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriodId(1L);
        budgetPeriod.setStartDate(new Date(new Long("1183316613046")));
        budgetPeriod.setEndDate(new Date(new Long("1214852613046")));
        budgetPeriod.setBudgetPeriod(1);
        budgetPeriod.setBudget(proposalDevelopmentBudgetExt);
        budgetPeriods.add(budgetPeriod);
        proposalDevelopmentBudgetExt.setBudgetPeriods(budgetPeriods);

        proposalDevelopmentBudgetExt.setDevelopmentProposal(document.getDevelopmentProposal());
        List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<>();
        proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);
        document.getDevelopmentProposal().setBudgets(proposalDevelopmentBudgetExtList);
        document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);
    }

    private void setFormObject(GrantApplicationDocument.GrantApplication.Forms forms, XmlObject formObject) {
        XmlCursor formCursor = formObject.newCursor();
        formCursor.toStartDoc();
        formCursor.toNextToken();
        XmlCursor metaGrantCursor = forms.newCursor();
        metaGrantCursor.toNextToken();
        formCursor.moveXml(metaGrantCursor);
    }
}
