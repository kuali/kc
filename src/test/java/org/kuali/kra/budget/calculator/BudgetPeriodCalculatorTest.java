/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.calculator;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProposalLaRate;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

/**
 * This class is for testing period item calculations
 */
@PerTestUnitTestData(
        @UnitTestData(order = { 
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
        sqlStatements = {
                      @UnitTestSql("delete from institute_rates"),
                      @UnitTestSql("delete from institute_la_rates")
                      }, 
        sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_LA_RATES.sql", delimiter = ";")
                })
        )

public class BudgetPeriodCalculatorTest extends KraTestBase {
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
    private BudgetDocument createBudgetDocument() throws Exception{
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());
        setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
        documentService.saveDocument(document);
        BudgetDocument bd = (BudgetDocument)documentService.getNewDocument("BudgetDocument");
        setBaseDocumentFields(bd,document.getDevelopmentProposal().getProposalNumber());
        documentService.saveDocument(bd);
        BudgetDocument savedBudgetDocument = (BudgetDocument)documentService.getByDocumentHeaderId(bd.getDocumentNumber());
        populateDummyRates(savedBudgetDocument);
        assertNotNull("Budget document not saved",savedBudgetDocument);
        return savedBudgetDocument;
    }
    @Test
    public void calculateBudgetPeriodTest() throws Exception{
        List<String> errors = new ArrayList<String>();
        BudgetDocument bd = createBudgetDocument();
        assertNotNull("Budget document not saved",bd);
        bd.setUrRateClassCode("2");
        
        List<BudgetPeriod> periods = bd.getBudgetPeriods();
        BudgetPeriod bp = getBudgetPeriod(bd,1,"2004-01-01","2005-12-31");
        periods.add(bp);
        BudgetCalculationService bcs = getService(BudgetCalculationService.class);
        BudgetLineItem bli = getLineItem(bp, 1, "400250",java.sql.Date.valueOf("2005-01-01"),
                java.sql.Date.valueOf("2005-12-31"),10000.00d,100.00d);
        bp.getBudgetLineItems().add(bli);
        bcs.calculateBudgetLineItem(bd, bli);
        BudgetLineItem bli1 = getLineItem(bp, 1, "400025",java.sql.Date.valueOf("2005-01-01"),
                java.sql.Date.valueOf("2005-12-31"),20000.00d,300.00d);
        bp.getBudgetLineItems().add(bli1);
        bcs.calculateBudgetLineItem(bd, bli1);
        
        bcs.calculateBudgetPeriod(bd,bp);

        BudgetDecimal directCost1 = bp.getTotalDirectCost();
        BudgetDecimal indirectCost1 = bp.getTotalIndirectCost();
        BudgetDecimal totalCost1 = bp.getTotalCost();
        BudgetDecimal underRDecimal = bp.getUnderrecoveryAmount();
        try{
            assertEquals(new BudgetDecimal(46095.86),directCost1);
        }catch (AssertionError e) {
            errors.add(e.getMessage());
        }

        bcs.calculateBudgetPeriod(bd,bp);
        try{
            assertEquals(new BudgetDecimal(23585.69),indirectCost1);
        }catch (AssertionError e) {
            errors.add(e.getMessage());
        }
        try{
            assertEquals(new BudgetDecimal(69681.55),totalCost1);
        }catch (AssertionError e) {
            errors.add(e.getMessage());
        }
        try{
            assertEquals(new BudgetDecimal(1559.36),underRDecimal);
        }catch (AssertionError e) {
            errors.add(e.getMessage());
        }
        if(!errors.isEmpty()){
            throw new AssertionError(errors.toString());
        }
        
    }

    @Test
    public void populateCalculatedAmountsTest() throws Exception{
        BudgetDocument bd = createBudgetDocument();
        assertNotNull("Budget document not saved",bd);
        List<BudgetPeriod> periods = bd.getBudgetPeriods();
        BudgetPeriod bp = getBudgetPeriod(bd,1,"2005-01-01","2005-12-31");
        bd.getBudgetPeriods().add(bp);
        BudgetLineItem bli = getLineItem(bp, 1, "400250",java.sql.Date.valueOf("2005-01-01"),
                java.sql.Date.valueOf("2005-12-31"),10000.00d,100.00d);
        BudgetCalculationService bcs = getService(BudgetCalculationService.class);
        bcs.populateCalculatedAmount(bd,bli);
        List<BudgetLineItemCalculatedAmount> calcAmounts = bli.getBudgetLineItemCalculatedAmounts();
        assertEquals(8, calcAmounts.size());
        List<String> possibleRateClassRateTypes = new ArrayList<String>();
        possibleRateClassRateTypes.add("1,1");
        possibleRateClassRateTypes.add("10,1");
        possibleRateClassRateTypes.add("11,1");
        possibleRateClassRateTypes.add("12,1");
        possibleRateClassRateTypes.add("5,1");
        possibleRateClassRateTypes.add("8,1");
        possibleRateClassRateTypes.add("5,3");
        possibleRateClassRateTypes.add("8,2");
        for (BudgetLineItemCalculatedAmount calcAmt : calcAmounts) {
            assertTrue("Unexpected rateclass ratetype combination "+calcAmt, 
                    possibleRateClassRateTypes.contains(calcAmt.getRateClassCode()+","+calcAmt.getRateTypeCode()));
        }
    }

    private BudgetPersonnelDetails getPersonnelLineItem(BudgetPeriod bp, int lineItemNumber, 
            String costElement,Date startDate,Date endDate,
            double lineItemCost,double costSharingAmount, int personNumber,int personSequenceNumber,String personId,String jobCode,double effort,double charged) {
        BudgetPersonnelDetails bli = new BudgetPersonnelDetails();
        bli.setProposalNumber(bp.getProposalNumber());
        bli.setBudgetVersionNumber(bp.getBudgetVersionNumber());
        bli.setBudgetPeriod(bp.getBudgetPeriod());
        bli.setStartDate(startDate);
        bli.setEndDate(endDate);
        bli.setApplyInRateFlag(true);
        bli.setOnOffCampusFlag(true);
        bli.setLineItemNumber(lineItemNumber);
        bli.setBudgetCategoryCode("1");
        bli.setCostElement(costElement);
        bli.setLineItemCost(new BudgetDecimal(lineItemCost));
        bli.setCostSharingAmount(new BudgetDecimal(costSharingAmount));
        bli.setUpdateTimestamp(bp.getUpdateTimestamp());
        bli.setUpdateUser(bp.getUpdateUser());
        bli.setPersonNumber(personNumber);
        bli.setPersonSequenceNumber(personSequenceNumber);
        bli.setPersonId(personId);
        bli.setJobCode(jobCode);
        bli.setPercentEffort(new BudgetDecimal(effort));
        bli.setPercentCharged(new BudgetDecimal(charged));
        return bli;
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

    private BudgetLineItem getLineItem(BudgetPeriod bp, int lineItemNumber, 
                String costElement,Date startDate,Date endDate,
                double lineItemCost,double costSharingAmount) {
        BudgetLineItem bli = new BudgetLineItem();
        bli.setProposalNumber(bp.getProposalNumber());
        bli.setBudgetVersionNumber(bp.getBudgetVersionNumber());
        bli.setBudgetPeriod(bp.getBudgetPeriod());
        bli.setStartDate(startDate);
        bli.setEndDate(endDate);
        bli.setApplyInRateFlag(true);
        bli.setOnOffCampusFlag(true);
        bli.setLineItemNumber(lineItemNumber);
        bli.setBudgetCategoryCode("1");
        bli.setCostElement(costElement);
        bli.setLineItemCost(new BudgetDecimal(lineItemCost));
        bli.setCostSharingAmount(new BudgetDecimal(costSharingAmount));
        bli.setUpdateTimestamp(bp.getUpdateTimestamp());
        bli.setUpdateUser(bp.getUpdateUser());
        return bli;
        
    }

    private void setBaseDocumentFields(BudgetDocument bd,String proposalNumber) {
        bd.getDocumentHeader().setDocumentDescription("Test budget calculation");
//        bd.setDocumentNumber(bd.getDocumentNumber());
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
    }
    
    
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
    }
}
