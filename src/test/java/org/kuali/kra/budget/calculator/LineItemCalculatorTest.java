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
package org.kuali.kra.budget.calculator;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

/**
 * This class...
 */
//@PerTestUnitTestData(
//        @UnitTestData(order = { 
//                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
//        sqlStatements = {
//                @UnitTestSql("delete from EPS_PROPOSAL where proposal_number = 9999999"),
//                @UnitTestSql("delete from budget where proposal_number = 9999999"),
//                @UnitTestSql("delete from budget_periods where proposal_number = '9999999'"),
//                @UnitTestSql("delete from budget_details where proposal_number = '9999999'")
//                }, 
//        sqlFiles = {
//                @UnitTestFile(filename = "classpath:insertBudgetTestData.sql", delimiter = ";")
//                })
//        )

public class LineItemCalculatorTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(LineItemCalculatorTest.class);
    private DocumentService documentService = null;
    private BusinessObjectService bos;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        super.tearDown();
    }
    @Test
    public void calculateLineItemTest() throws Exception{
        
        
//        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
//
//        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
//        Date requestedEndDateInitial = new Date(System.currentTimeMillis());
//
//        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
//        
//        documentService.saveDocument(document);
//
//        ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(document.getDocumentNumber());
//        
//        BudgetDocument bd = (BudgetDocument)documentService.getNewDocument("BudgetDocument");
//        
//        setBaseDocumentFields(bd,document.getProposalNumber());
//        documentService.saveDocument(bd);
//        
//        populateDummyRates(bd);
//        
//        assertNotNull("Budget document not saved",bd);
//        
//        List<BudgetPeriod> periods = bd.getBudgetPeriods();
//        BudgetPeriod bp = getBudgetPeriod(bd,1,"2005-01-01","2005-12-31");
//        bd.getBudgetPeriods().add(bp);
//        BudgetLineItem bli = getLineItem(bp,1,1,"400250");
//        bp.getBudgetLineItems().add(bli);
//        BudgetCalculationService bcs = getService(BudgetCalculationService.class);
////        bcs.calculateBudgetLineItem(bd,bli);
//        BudgetDecimal directCost = bli.getDirectCost();
////        assertEquals(directCost, new BudgetDecimal(15800.00));
//        
//        bd.getBudgetPeriods().add(bp);
//        BudgetLineItem bli1 = getLineItem(bp,1,1,"420128");
//        bp.getBudgetLineItems().add(bli1);
////        bcs.calculateBudgetLineItem(bd,bli1);
//        BudgetDecimal directCost1 = bli1.getDirectCost();
////        assertEquals(directCost1, new BudgetDecimal(15837.03));
//        bcs.calculateBudgetPeriod(bd,bp);
////        assertEquals(bli1.getIndirectCost(), new BudgetDecimal(023.03));
//        
//        
//        
//        
//        LOG.info(bli.toString());
//        LOG.info(bp.toString());
//        
////        bcs.calculateBudget(bd);
//        System.out.println(bd.toString());
    }
    
    private void populateDummyRates(BudgetDocument bd) {
        List<BudgetProposalRate> budgetProposalRates = bd.getBudgetProposalRates();
        List<InstituteRate> instRates = (List)bos.findAll(InstituteRate.class);
        for (InstituteRate instituteRate : instRates) {
            BudgetProposalRate bpr = new BudgetProposalRate();
            bpr.setProposalNumber(bd.getProposalNumber().toString());
            bpr.setProposalNumber(bd.getProposalNumber().toString());
            bpr.setBudgetVersionNumber(bd.getBudgetVersionNumber());
            bpr.setActivityTypeCode(instituteRate.getActivityTypeCode());
            bpr.setFiscalYear(instituteRate.getFiscalYear());
            bpr.setOnOffCampusFlag(instituteRate.getOnOffCampusFlag());
            bpr.setRateClassCode(instituteRate.getRateClassCode());
            bpr.setRateTypeCode(instituteRate.getRateTypeCode());
            bpr.setStartDate(instituteRate.getStartDate());
            bpr.setUnitNumber(instituteRate.getUnitNumber());
            bpr.setInstituteRate(instituteRate.getInstituteRate());
            bpr.setApplicableRate(bpr.getInstituteRate());
            budgetProposalRates.add(bpr);
        }
        List<BudgetProposalLaRate> budgetProposalLaRates = bd.getBudgetProposalLaRates();
        List<InstituteLaRate> instLaRates = (List)bos.findAll(InstituteLaRate.class);
        
        for (InstituteLaRate instituteLaRate : instLaRates) {
            BudgetProposalLaRate bpr = new BudgetProposalLaRate();
            bpr.setProposalNumber(bd.getProposalNumber().toString());
            bpr.setProposalNumber(bd.getProposalNumber().toString());
            bpr.setBudgetVersionNumber(bd.getBudgetVersionNumber());
            bpr.setFiscalYear(instituteLaRate.getFiscalYear());
            bpr.setOnOffCampusFlag(instituteLaRate.getOnOffCampusFlag());
            bpr.setRateClassCode(instituteLaRate.getRateClassCode());
            bpr.setRateTypeCode(instituteLaRate.getRateTypeCode());
            bpr.setStartDate(instituteLaRate.getStartDate());
            bpr.setUnitNumber(instituteLaRate.getUnitNumber());
            bpr.setInstituteRate(instituteLaRate.getInstituteRate());
            bpr.setApplicableRate(bpr.getInstituteRate());
            budgetProposalLaRates.add(bpr);
        }
        
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
        return bp;
//        addLineItem(bd,1,1,1,"400250");
        
//        bd.getBudgetPeriods().add(bp);
    }

    private BudgetLineItem getLineItem(BudgetPeriod bp, int lineItemNumber, String budgetCategoryCode, String costElement) {
        BudgetLineItem bli = new BudgetLineItem();
        bli.setProposalNumber(bp.getProposalNumber());
        bli.setBudgetVersionNumber(bp.getBudgetVersionNumber());
        bli.setBudgetPeriod(bp.getBudgetPeriod());
        bli.setStartDate(bp.getStartDate());
        bli.setEndDate(bp.getEndDate());
        bli.setApplyInRateFlag(true);
        bli.setOnOffCampusFlag(true);
        bli.setLineItemNumber(lineItemNumber);
        bli.setBudgetCategoryCode(budgetCategoryCode);
        bli.setCostElement(costElement);
        bli.setLineItemCost(new BudgetDecimal(10000));
        bli.setCostSharingAmount(new BudgetDecimal(100));
        bli.setUpdateTimestamp(bp.getUpdateTimestamp());
        bli.setUpdateUser(bp.getUpdateUser());
        return bli;
//        bp.getBudgetLineItems().add(bli);
        
    }

    private void setBaseDocumentFields(BudgetDocument bd,String proposalNumber) {
        bd.getDocumentHeader().setFinancialDocumentDescription("Test budget calculation");
//        bd.setDocumentNumber(bd.getDocumentNumber());
        bd.setProposalNumber(proposalNumber);
        bd.setBudgetVersionNumber(1);
        bd.setStartDate(java.sql.Date.valueOf("2002-01-01"));
        bd.setEndDate(java.sql.Date.valueOf("2008-12-31"));
        bd.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        bd.setUpdateUser("KRADEV");
        bd.setOhRateClassCode("1");
        bd.setUrRateClassCode("1");
        bd.setModularBudgetFlag("N");
        bd.setActivityTypeCode("1");
    }
    
    
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setFinancialDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }
}
