/*
 * Copyright 2008 The Kuali Foundation.
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class RRFedNonFedBudgetV1_1GeneratorTest extends S2STestBase<RRFedNonFedBudgetV1_1Generator> {


    @Override
    protected Class<RRFedNonFedBudgetV1_1Generator> getFormGeneratorClass() {
        return RRFedNonFedBudgetV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        Organization organization = new Organization();
        organization.setDunsNumber("122544565655");
        organization.setOrganizationName("MIT");
        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setProposalPersonRoleId("PI");
        proposalPerson.setFirstName("Philip");
        proposalPerson.setLastName("Berg");
        proposalPerson.setOptInCertificationStatus("Y");
        proposalPerson.setOptInUnitStatus("Y");
        proposalPerson.setProposalPersonNumber(1001);

        List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
        proposalPersonList.add(proposalPerson);

        document.getDevelopmentProposal().setOrganization(organization);
        document.setUpdateUser("quickstart");
        document.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        document.getDevelopmentProposal().setProposalPersons(proposalPersonList);

        Calendar startDate = Calendar.getInstance();
        startDate.set(2001, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2005, 1, 1);

        S2STestUtils testUtils = new S2STestUtils();
        BudgetDocument bd = testUtils.createBudgetDocument(document);
        bd.setDocumentNumber(document.getDocumentHeader().getDocumentNumber());
        bd.setModularBudgetFlag(false);
        bd.setProposal(document);
        bd.setVersionNumber(new Long(1));
        bd.setBudgetVersionNumber(1);
        bd.setBudgetStatus("Open");
        bd.setComments("");
        bd.setStartDate(new Date(startDate.getTimeInMillis()));
        bd.setEndDate(new Date(endDate.getTimeInMillis()));
        BudgetPeriod firstPeriod = new BudgetPeriod();
        firstPeriod.setBudgetPeriod(1);
        firstPeriod.setStartDate(new Date(Calendar.getInstance().getTimeInMillis()));
        firstPeriod.setEndDate(new Date(Calendar.getInstance().getTimeInMillis()));

        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        budgetPeriods.add(firstPeriod);
        bd.setBudgetPeriods(budgetPeriods);

        BudgetVersionOverview overview = new BudgetVersionOverview();
        overview.setBudgetStatus("");
        overview.setBudgetVersionNumber(1);
        overview.setComments("");
        overview.setCostSharingAmount(BudgetDecimal.ZERO);
        overview.setDocumentDescription("Description");
        overview.setDocumentNumber(bd.getDocumentHeader().getDocumentNumber());
        overview.setEndDate(new Date(endDate.getTimeInMillis()));
        overview.setFinalVersionFlag(true);
        overview.setName("overview");
        overview.setOhRateTypeCode("1");
        overview.setStartDate(new Date(startDate.getTimeInMillis()));
        overview.setTotalCost(BudgetDecimal.ZERO);
        overview.setTotalCostLimit(BudgetDecimal.ZERO);
        overview.setTotalDirectCost(BudgetDecimal.ZERO);
        overview.setTotalIndirectCost(BudgetDecimal.ZERO);
        overview.setVersionNumber(new Long(1));
        overview.setResidualFunds(BudgetDecimal.ZERO);
        List<BudgetVersionOverview> overviewList = new ArrayList<BudgetVersionOverview>();
        overviewList.add(overview);
        document.getDevelopmentProposal().setBudgetVersionOverviews(overviewList);
        KNSServiceLocator.getDocumentService().saveDocument(bd);
    }

}
