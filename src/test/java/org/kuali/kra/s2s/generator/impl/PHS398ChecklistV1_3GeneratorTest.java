/*
 * Copyright 2005-2010 The Kuali Foundation.
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.kra.s2s.generator.util.S2STestUtils;

/**
 * This class tests the PHS398ChecklistV1_3 generator
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class PHS398ChecklistV1_3GeneratorTest extends
		S2STestBase<PHS398ChecklistV1_3Generator> {

	@Override
	protected Class<PHS398ChecklistV1_3Generator> getFormGeneratorClass() {
		return PHS398ChecklistV1_3Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		developmentProposal.setProposalTypeCode("2");
		List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
		ProposalYnq proposalYnq1 = new ProposalYnq();
		proposalYnq1.setProposalNumber(developmentProposal.getProposalNumber());
		proposalYnq1.setAnswer("Y");
		proposalYnq1.setQuestionId("22");
		proposalYnq1.setExplanation("David,Blain");
		ynqList.add(proposalYnq1);
		ProposalYnq proposalYnq2 = new ProposalYnq();
		proposalYnq1.setProposalNumber(developmentProposal.getProposalNumber());
		proposalYnq2.setAnswer("Y");
		proposalYnq2.setQuestionId("23");
		proposalYnq2.setExplanation("David,stephen");
		proposalYnq1.setProposalNumber(developmentProposal.getProposalNumber());
		ynqList.add(proposalYnq2);
		ProposalYnq proposalYnq3 = new ProposalYnq();
		proposalYnq3.setAnswer("Y");
		proposalYnq3.setQuestionId("16");
		proposalYnq3.setExplanation("gavin,king");
		proposalYnq1.setProposalNumber(developmentProposal.getProposalNumber());
		ynqList.add(proposalYnq3);
		ProposalYnq proposalYnq4 = new ProposalYnq();
		proposalYnq4.setAnswer("Y");
		proposalYnq4.setQuestionId("29");
		proposalYnq4.setExplanation("New,jersy");
		proposalYnq1.setProposalNumber(developmentProposal.getProposalNumber());
		ynqList.add(proposalYnq4);
		developmentProposal.setProposalYnqs(ynqList);
		
		// Budget start
		Calendar startDate = Calendar.getInstance();
		startDate.set(2001, 1, 1);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2005, 1, 1);
		S2STestUtils testUtils = new S2STestUtils();
		BudgetDocument bdoc = testUtils.createBudgetDocument(document);
		Budget bd = bdoc.getBudget();
		bd.setModularBudgetFlag(false);
		bdoc.setUpdateUser("quickstart");
		bdoc.setParentDocument(document);
		bdoc.setUpdateTimestamp(new Timestamp(startDate.getTimeInMillis()));
		bd.setVersionNumber(new Long(1));
		bd.setBudgetVersionNumber(1);
		bd.setBudgetStatus("Open");
		bd.setComments("");
		bd.setStartDate(new Date(startDate.getTimeInMillis()));
		bd.setEndDate(new Date(endDate.getTimeInMillis()));
		BudgetPeriod firstPeriod = new BudgetPeriod();
		firstPeriod.setBudgetPeriod(1);
		firstPeriod.setStartDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));
		firstPeriod.setEndDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));

		List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
		budgetPeriods.add(firstPeriod);
		bd.setBudgetPeriods(budgetPeriods);
		bd.setOhRateClassCode("1");
		bd.setOhRateTypeCode("1");
		List<BudgetVersionOverview> overviewList = new ArrayList<BudgetVersionOverview>();
		BudgetVersionOverview overview = new BudgetVersionOverview();
		overview.setBudgetStatus("");
		overview.setBudgetVersionNumber(1);
		overview.setComments("");
		overview.setCostSharingAmount(BudgetDecimal.ZERO);
		overview.setDocumentDescription("Description");
		overview.setEndDate(new Date(endDate.getTimeInMillis()));
		overview.setName("overview");
		overview.setOhRateTypeCode("1");
		overview.setOhRateClassCode("1");
		overview.setStartDate(new Date(startDate.getTimeInMillis()));
		overview.setTotalCost(BudgetDecimal.ZERO);
		overview.setTotalCostLimit(BudgetDecimal.ZERO);
		overview.setTotalDirectCost(BudgetDecimal.ZERO);
		overview.setTotalIndirectCost(BudgetDecimal.ZERO);
		overview.setVersionNumber(new Long(1));
		overview.setResidualFunds(BudgetDecimal.ZERO);
		overview.setDocumentNumber("1001");
		overviewList.add(overview);
		List<BudgetDocumentVersion> docVerList = new ArrayList<BudgetDocumentVersion>();
		BudgetDocumentVersion docVer = new BudgetDocumentVersion();
		docVer.setDocumentNumber(bdoc.getDocumentNumber());
		docVer.setBudgetVersionOverviews(overviewList);
		docVerList.add(docVer);
		document.setBudgetDocumentVersions(docVerList);
		// Budget end
		
		document.setDevelopmentProposal(developmentProposal);
	}
}
