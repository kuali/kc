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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class RRBudgetV1_0GeneratorTest extends S2STestBase<RRBudgetV1_0Generator> {

    @Override
    protected Class<RRBudgetV1_0Generator> getFormGeneratorClass() {
        return RRBudgetV1_0Generator.class;
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

        Narrative narrative = new Narrative();
        List<Narrative> naList = new ArrayList<Narrative>();
        NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
        File file = new File(S2STestUtils.ATT_DIR_PATH + "exercise5.pdf");
        InputStream inStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inStream);
        byte[] narrativePdf = new byte[bis.available()];
        narrativeAttachment.setNarrativeData(narrativePdf);
        List<NarrativeAttachment> narrativeList = new ArrayList<NarrativeAttachment>();
        narrativeList.add(narrativeAttachment);
        narrative.setProposalNumber(document.getProposalNumber());
        narrative.setModuleNumber(1);
        narrative.setModuleSequenceNumber(1);
        narrative.setModuleStatusCode("C");
        narrative.setNarrativeTypeCode("11");
        narrative.setNarrativeAttachmentList(narrativeList);
        narrative.setFileName("exercise5");
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setDescription("Testing for Project Attachment");
        narrative.setNarrativeType(narrativeType);
        naList.add(narrative);

        Narrative narrative1 = new Narrative();
        NarrativeAttachment narrativeAttachment1 = new NarrativeAttachment();
        File file1 = new File(S2STestUtils.ATT_DIR_PATH + "exercise6.pdf");
        InputStream inStream1 = new FileInputStream(file1);
        BufferedInputStream bis1 = new BufferedInputStream(inStream1);
        byte[] narrativePdf1 = new byte[bis1.available()];
        narrativeAttachment1.setNarrativeData(narrativePdf1);

        List<NarrativeAttachment> narrativeList1 = new ArrayList<NarrativeAttachment>();
        narrativeList1.add(narrativeAttachment1);
        narrative1.setProposalNumber(document.getProposalNumber());
        narrative1.setModuleNumber(2);
        narrative1.setModuleSequenceNumber(2);
        narrative1.setModuleStatusCode("C");
        narrative1.setNarrativeTypeCode("12");
        narrative1.setNarrativeAttachmentList(narrativeList1);
        narrative1.setFileName("exercise6");
        NarrativeType narrativeType1 = new NarrativeType();
        narrativeType1.setDescription("Testing for Project Attachment1");
        narrative1.setNarrativeType(narrativeType1);
        naList.add(narrative1);
        getService(BusinessObjectService.class).save(narrative);
        getService(BusinessObjectService.class).save(narrative1);
        narrative.getNarrativeAttachmentList().clear();
        document.setNarratives(naList);

        List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
        proposalPersonList.add(proposalPerson);

        document.setOrganization(organization);
        document.setUpdateUser("quickstart");
        document.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        document.setProposalPersons(proposalPersonList);

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
        document.setBudgetVersionOverviews(overviewList);
        KNSServiceLocator.getDocumentService().saveDocument(bd);
    }
}
