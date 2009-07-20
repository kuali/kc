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
import org.kuali.kra.budget.bo.BudgetPerson;
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
import org.kuali.rice.kns.util.DateUtils;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

/**
 * This class is for testing Line item calculations
 */
@PerTestUnitTestData(
        @UnitTestData(order = { 
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
        sqlStatements = {
                      @UnitTestSql("delete from institute_rates"),
                      @UnitTestSql("delete from institute_la_rates")
                      }, 
        sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_new_rates.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/update_institute_rates.sql", delimiter = ";")
                })
        )

public class BudgetPersonnelCalculationTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetPersonnelCalculationTest.class);
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
        Date requestedStartDateInitial = new Date(DateUtils.parseDate("07/01/2009", new String[] {"MM/dd/yyyy"}).getTime());
        Date requestedEndDateInitial = new Date(DateUtils.parseDate("06/30/2013", new String[] {"MM/dd/yyyy"}).getTime());
        setBaseDocumentFields(document, "PDD-TestPersonnelCalc", "005978", "Project Title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
        document.refreshReferenceObject("sponsor");
        document.refreshReferenceObject("ownedByUnit");
        document.refreshReferenceObject("activityType");
        documentService.saveDocument(document);
        BudgetDocument bd = (BudgetDocument)documentService.getNewDocument("BudgetDocument");
        setBaseDocumentFields(bd,document.getProposalNumber());
        
        BudgetPerson budgetPerson1 = new BudgetPerson();
        budgetPerson1.setPersonId("000000003");
        budgetPerson1.setPersonSequenceNumber(1);
        budgetPerson1.setJobCode("AA023");
        budgetPerson1.setAppointmentTypeCode("7");
        budgetPerson1.setBudgetVersionNumber(bd.getBudgetVersionNumber());
        budgetPerson1.setProposalNumber(bd.getProposalNumber());
        budgetPerson1.setEffectiveDate(java.sql.Date.valueOf("2009-07-01"));
        budgetPerson1.setCalculationBase(new BudgetDecimal(120000.00d));
        budgetPerson1.setNonEmployeeFlag(false);
        bd.addBudgetPerson(budgetPerson1);

        documentService.saveDocument(bd);
        BudgetDocument savedBudgetDocument = (BudgetDocument)documentService.getByDocumentHeaderId(bd.getDocumentNumber());
        populateBudgetRates(savedBudgetDocument);
        assertNotNull("Budget document not saved", savedBudgetDocument);
        return savedBudgetDocument;
    }
    
    @Test
    public void calculateLineItemWithMTDCTest() throws Exception{
        List<String> errors = new ArrayList<String>();
        BudgetDocument bd = createBudgetDocument();
        assertNotNull(bd);
        
        //Date format "yyyy-mm-dd"
        BudgetPeriod bp = getBudgetPeriod(bd, 1, "2009-07-01", "2010-06-30");
        bd.getBudgetPeriods().add(bp);
        
        BudgetLineItem bli = getLineItem(bp, 1, "400040", java.sql.Date.valueOf("2009-07-01"), java.sql.Date.valueOf("2010-06-30"));
        bli.setGroupName("Faculty");
        bli.setApplyInRateFlag(new Boolean(true));
        bli.setOnOffCampusFlag(new Boolean(true));
        
        BudgetPersonnelDetails user1Geoff = new BudgetPersonnelDetails();
        user1Geoff.setBudgetPeriod(bp.getBudgetPeriod());
        user1Geoff.setBudgetVersionNumber(bp.getBudgetVersionNumber());
        user1Geoff.setProposalNumber(bp.getProposalNumber());
        user1Geoff.setPercentEffort(new BudgetDecimal("100.00"));
        user1Geoff.setPercentCharged(new BudgetDecimal("90.00"));
        user1Geoff.setPeriodTypeCode("3");
        user1Geoff.setStartDate(java.sql.Date.valueOf("2009-07-01"));
        user1Geoff.setEndDate(java.sql.Date.valueOf("2010-06-30"));
        user1Geoff.setApplyInRateFlag(new Boolean(true));
        user1Geoff.setOnOffCampusFlag(new Boolean(true));
        
        BudgetPerson person = bd.getBudgetPerson(0);
        user1Geoff.setBudgetPerson(person);
        user1Geoff.setPersonId(person.getPersonId());
        user1Geoff.setPersonSequenceNumber(person.getPersonSequenceNumber());
        user1Geoff.setJobCode(person.getJobCode());
        user1Geoff.setNonEmployeeFlag(person.getNonEmployeeFlag());
        
        bli.getBudgetPersonnelDetailsList().add(user1Geoff);
        bp.getBudgetLineItems().add(bli);
        
        BudgetCalculationService bcs = getService(BudgetCalculationService.class);
        bcs.calculateBudgetPeriod(bd, bp);
        
        List<BudgetLineItemCalculatedAmount> calcAmounts = bli.getBudgetLineItemCalculatedAmounts();
        assertNotNull(calcAmounts);
        assertEquals(7, calcAmounts.size());
        
        for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : calcAmounts) {
            if(budgetLineItemCalculatedAmount.getRateClass().getDescription().equalsIgnoreCase("MTDC")) {
                assertEquals(new BudgetDecimal(89100.00d), budgetLineItemCalculatedAmount.getCalculatedCost());
                assertEquals(new BudgetDecimal(9900.00d), budgetLineItemCalculatedAmount.getCalculatedCostSharing());
            }
            if(budgetLineItemCalculatedAmount.getRateClass().getDescription().equalsIgnoreCase("Employee Benefits") && 
                    budgetLineItemCalculatedAmount.getRateType().getDescription().equalsIgnoreCase("Research Rate")) {
                assertEquals(new BudgetDecimal(40500.00d), budgetLineItemCalculatedAmount.getCalculatedCost());
                assertEquals(new BudgetDecimal(4500.00d), budgetLineItemCalculatedAmount.getCalculatedCostSharing());
            }
        }
        assertEquals(new BudgetDecimal(108000.00d), bli.getLineItemCost());
        assertEquals(new BudgetDecimal(148500.00d), bli.getDirectCost());
        assertEquals(new BudgetDecimal(89100.00d), bli.getIndirectCost());
        
        assertEquals(new BudgetDecimal(237600.00d), bp.getTotalCost());
        assertEquals(new BudgetDecimal(148500.00d), bp.getTotalDirectCost());
        assertEquals(new BudgetDecimal(89100.00d), bp.getTotalIndirectCost());
        assertEquals(new BudgetDecimal(26400.00d), bp.getCostSharingAmount());
    }

    private void populateBudgetRates(BudgetDocument bd) {
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
            
            bpr.refreshReferenceObject("rateClass");
            bpr.refreshReferenceObject("rateType");
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
            
            bpr.refreshReferenceObject("rateClass");
            bpr.refreshReferenceObject("rateType");
            
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
    }

    private BudgetLineItem getLineItem(BudgetPeriod bp, int lineItemNumber, 
                String costElement,Date startDate,Date endDate) {
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
        bli.setUpdateTimestamp(bp.getUpdateTimestamp());
        bli.setUpdateUser(bp.getUpdateUser());
        return bli;
        
    }

    private void setBaseDocumentFields(BudgetDocument bd,String proposalNumber) {
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
    }
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }
}
