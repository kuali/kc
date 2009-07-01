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
package org.kuali.kra.s2s.generator.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class S2STestUtils {
    public static final String TEST_OPPORTUNITY_ID = "MOH-015";
    public static final String ATT_DIR_PATH = "src/test/resources/testAttachments/";
    public BudgetDocument createBudgetDocument(ProposalDevelopmentDocument pdDoc) throws Exception {
        DocumentService documentService = KNSServiceLocator.getDocumentService();
        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());
        setBaseDocumentFields(pdDoc, "ProposalDevelopmentDocumentTest test doc", "005770", "project title",
                requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
        BudgetDocument bd = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
        setBaseDocumentFields(bd, pdDoc.getProposalNumber());
        return bd;
    }

    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title,
            Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode,
            String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

    private void setBaseDocumentFields(BudgetDocument bd, String proposalNumber) {
        bd.getDocumentHeader().setDocumentDescription("Test budget calculation");
        bd.setProposalNumber(proposalNumber);
        bd.setBudgetVersionNumber(1);
        bd.setStartDate(java.sql.Date.valueOf("2002-01-01"));
        bd.setEndDate(java.sql.Date.valueOf("2008-12-31"));
        bd.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        bd.setUpdateUser("KRADEV");
        bd.setOhRateClassCode("1");
        bd.setUrRateClassCode("1");
        bd.setModularBudgetFlag(false);
        bd.setActivityTypeCode("1");

        List<BudgetPeriod> periods = new ArrayList<BudgetPeriod>();
        BudgetPeriod bp = getBudgetPeriod(bd, 1, "2008-01-01", "2008-12-31");
        periods.add(bp);
        bd.setBudgetPeriods(periods);
    }

    private BudgetPeriod getBudgetPeriod(BudgetDocument bd, int period, String startDate, String endDate) {

        BudgetPeriod bp = new BudgetPeriod();
        bp.setProposalNumber(bd.getProposalNumber().toString());
        bp.setBudgetVersionNumber(bd.getBudgetVersionNumber());
        bp.setBudgetPeriod(period);
        bp.setStartDate(java.sql.Date.valueOf(startDate));
        bp.setEndDate(java.sql.Date.valueOf(endDate));
        bp.setUpdateUser(bd.getUpdateUser());
        bp.setUpdateTimestamp(bd.getUpdateTimestamp());
        bp.setTotalIndirectCost(BudgetDecimal.ZERO);
        return bp;
    }
}
