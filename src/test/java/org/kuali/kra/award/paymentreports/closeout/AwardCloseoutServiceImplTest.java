/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.paymentreports.closeout;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * 
 * This class tests the AwardCloseoutServiceImpl
 */
@RunWith(JMock.class)
public class AwardCloseoutServiceImplTest {

    public static final String REPORT_CLASS_CODE_FINANCIAL_REPORT = "1";
    public static final String REPORT_CLASS_CODE_PATENT= "3";
    public static final String REPORT_CLASS_CODE_USER_DEFINED = "UD";
    
    AwardCloseoutServiceImpl service; 
    List<AwardReportTerm> awardReportTermItems;
    Map<String, Object> closeoutDueDates;
    AwardReportTerm newAwardReportTerm;
    Report newReport;
    Frequency newFrequency;
    java.util.Date dateCalculatedUsingFinalInvoiceDue;
    
    private Mockery context = new JUnit4Mockery();
    
    /**
     * 
     * This method gets executed before every unit test.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        service = new AwardCloseoutServiceImpl();
        awardReportTermItems = new ArrayList<AwardReportTerm>();
        closeoutDueDates = new HashMap<String, Object>();
        newAwardReportTerm = new AwardReportTerm();
        newReport = new Report();
        newFrequency = new Frequency();
        dateCalculatedUsingFinalInvoiceDue = new java.util.Date();
    }
    
    /**
     * 
     * This method gets executed after every unit test.
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        service = null;
        awardReportTermItems = null;
        closeoutDueDates = null;
        newAwardReportTerm = null;
        newReport = null;
        newFrequency = null;
    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDateWhenFilteredListSizeIsZero method of AwardCloseoutBean
     * for the case where Report Class is Financial Report.
     *
     */
//    @Test
//    public final void testUpdateCloseoutDueDateWhenFilteredListSizeIsZero_ReportClassIsFinancialReport(){
//        
//        final KualiConfigurationService kualiConfigurationService = context.mock(KualiConfigurationService.class);        
//        final Parameter parameter = new Parameter();        
//        parameter.setParameterName(KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT);
//        parameter.setParameterValue(REPORT_CLASS_CODE_FINANCIAL_REPORT);        
//        context.checking(new Expectations() {{
//            one(kualiConfigurationService).getParameter(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT);will(returnValue(parameter));
//        }});        
//        service.setKualiConfigurationService(kualiConfigurationService);
//                
//        dateCalculatedUsingFinalInvoiceDue.setTime(100000000);
//        service.updateCloseoutDueDateWhenFilteredListSizeIsZero(closeoutDueDates, dateCalculatedUsingFinalInvoiceDue, REPORT_CLASS_CODE_FINANCIAL_REPORT);
//        Assert.assertEquals(dateCalculatedUsingFinalInvoiceDue.getTime(), ((java.util.Date)closeoutDueDates.get(REPORT_CLASS_CODE_FINANCIAL_REPORT)).getTime());
//    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDateWhenFilteredListSizeIsZero method of AwardCloseoutBean
     * for the case where Report Class is not Financial Report.
     *
     */
//    @Test
//    public final void testUpdateCloseoutDueDateWhenFilteredListSizeIsZero_ReportClassIsNotFinancialReport(){
//        final KualiConfigurationService kualiConfigurationService = context.mock(KualiConfigurationService.class);        
//        final Parameter parameter = new Parameter();        
//        parameter.setParameterName(KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT);
//        parameter.setParameterValue(REPORT_CLASS_CODE_FINANCIAL_REPORT);        
//        context.checking(new Expectations() {{
//            one(kualiConfigurationService).getParameter(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT);will(returnValue(parameter));
//        }});        
//        service.setKualiConfigurationService(kualiConfigurationService);
//        
//        dateCalculatedUsingFinalInvoiceDue.setTime(100000000);
//        service.updateCloseoutDueDateWhenFilteredListSizeIsZero(closeoutDueDates, dateCalculatedUsingFinalInvoiceDue, REPORT_CLASS_CODE_PATENT);
//        Assert.assertEquals(null, closeoutDueDates.get(REPORT_CLASS_CODE_PATENT));
//    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDate method of AwardCloseoutBean
     * for the case where due dates for all AwardReportTerm objects are equal and the 2 dates - aka dateCalculatedUsingFinalInvoiceDue and dateCalculatedUsingFrequency are equal.
     *
     */
    @Test
    public final void testUpdateCloseoutDueDate_allDueDatesAreEqual_And_2DatesAreEqual(){
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), new java.util.Date(10000), true, REPORT_CLASS_CODE_FINANCIAL_REPORT);
        Assert.assertEquals(new java.util.Date(10000), closeoutDueDates.get(REPORT_CLASS_CODE_FINANCIAL_REPORT));
    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDate method of AwardCloseoutBean
     * for the case where due dates for all AwardReportTerm objects are not equal and the 2 dates - aka dateCalculatedUsingFinalInvoiceDue and dateCalculatedUsingFrequency are equal.
     *
     */
    @Test
    public final void testUpdateCloseoutDueDate_allDueDatesAreEqual_And_2DatesAreNotEqual(){
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), new java.util.Date(10000), false, REPORT_CLASS_CODE_FINANCIAL_REPORT);
        Assert.assertEquals("M", closeoutDueDates.get(REPORT_CLASS_CODE_FINANCIAL_REPORT));
    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDate method of AwardCloseoutBean
     * for the case where due dates for all AwardReportTerm objects are not equal and the 2 dates - aka dateCalculatedUsingFinalInvoiceDue and dateCalculatedUsingFrequency are equal.
     *
     */
    @Test
    public final void testUpdateCloseoutDueDate_allDueDatesAreNotEqual_And_2DatesAreEqual(){
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10001), new java.util.Date(10000), true, REPORT_CLASS_CODE_FINANCIAL_REPORT);
        Assert.assertEquals("M", closeoutDueDates.get(REPORT_CLASS_CODE_FINANCIAL_REPORT));
    }
    
    /**
     * 
     * This method tests the updateCloseoutDueDate method of AwardCloseoutBean
     * for the case where due dates for all AwardReportTerm objects are not equal and the 2 dates - aka dateCalculatedUsingFinalInvoiceDue and dateCalculatedUsingFrequency are not equal.
     *
     */
    @Test
    public final void testUpdateCloseoutDueDate_allDueDatesAreNotEqual_And_2DatesAreNotEqual(){
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10001), new java.util.Date(10000), false, REPORT_CLASS_CODE_FINANCIAL_REPORT);
        Assert.assertEquals("M", closeoutDueDates.get(REPORT_CLASS_CODE_FINANCIAL_REPORT));
    }
    
    /**
     * 
     * This method tests the filterAwardReportTerms method of AwardCloseoutBean
     * for the case where report class on the AwardReportTerm matches with that passed in and final flag of associated frequency is true
     */
    @Test
    public final void testFilterAwardReportTerms_ReportClassMatches_FinalFlagTrue() {
        newAwardReportTerm.setReportClassCode(REPORT_CLASS_CODE_FINANCIAL_REPORT);
        newReport.setFinalReportFlag(true);
        newAwardReportTerm.setReport(newReport);
        awardReportTermItems.add(newAwardReportTerm);
        Assert.assertEquals(1,service.filterAwardReportTerms(awardReportTermItems, REPORT_CLASS_CODE_FINANCIAL_REPORT).size());
    }
    
    /**
     * 
     * This method tests the filterAwardReportTerms method of AwardCloseoutBean
     * for the case where report class on the AwardReportTerm does not match with that passed in and final flag of associated frequency is true
     */
    @Test
    public final void testFilterAwardReportTerms_ReportClassDoesNotMatch_FinalFlagTrue() {
        newAwardReportTerm.setReportClassCode(REPORT_CLASS_CODE_PATENT);
        newReport.setFinalReportFlag(true);
        newAwardReportTerm.setReport(newReport);
        awardReportTermItems.add(newAwardReportTerm);
        Assert.assertEquals(0,service.filterAwardReportTerms(awardReportTermItems, REPORT_CLASS_CODE_FINANCIAL_REPORT).size());
    }
    
    /**
     * 
     * This method tests the filterAwardReportTerms method of AwardCloseoutBean
     * for the case where report class on the AwardReportTerm matches with that passed in and final flag of associated frequency is false
     */
    @Test
    public final void testFilterAwardReportTerms_ReportClassMatches_FinalFlagFalse() {
        newAwardReportTerm.setReportClassCode(REPORT_CLASS_CODE_FINANCIAL_REPORT);
        newReport.setFinalReportFlag(false);
        newAwardReportTerm.setReport(newReport);
        awardReportTermItems.add(newAwardReportTerm);
        Assert.assertEquals(0,service.filterAwardReportTerms(awardReportTermItems, REPORT_CLASS_CODE_FINANCIAL_REPORT).size());
    }
    
    /**
     * 
     * This method tests the filterAwardReportTerms method of AwardCloseoutBean
     * for the case where report class on the AwardReportTerm does not match with that passed in and final flag of associated frequency is false
     */
    @Test
    public final void testFilterAwardReportTerms_ReportClassDoesNotMatch_FinalFlagFalse() {
        newAwardReportTerm.setReportClassCode(REPORT_CLASS_CODE_PATENT);
        newReport.setFinalReportFlag(false);
        newAwardReportTerm.setReport(newReport);
        awardReportTermItems.add(newAwardReportTerm);
        Assert.assertEquals(0,service.filterAwardReportTerms(awardReportTermItems, REPORT_CLASS_CODE_FINANCIAL_REPORT).size());
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreZero_NumberOfMonthsAreZero(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(1000000);
        newAwardReportTerm.setFrequency(newFrequency);
        newAwardReportTerm.getFrequency().setNumberOfDays(0);
        newAwardReportTerm.getFrequency().setNumberOfMonths(0);
        java.util.Date calculatedDate= service.getCalculatedDueDate(new Date(calendar.getTimeInMillis()), newAwardReportTerm, calendar);
        Assert.assertEquals(calendar.getTime(), calculatedDate);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreZero_NumberOfMonthsAreNotZero(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(1000000);
        newAwardReportTerm.setFrequency(newFrequency);
        newAwardReportTerm.getFrequency().setNumberOfDays(0);
        newAwardReportTerm.getFrequency().setNumberOfMonths(5);
        calendar.add(Calendar.MONTH, 5);
        java.util.Date calculatedDate= service.getCalculatedDueDate(new Date(calendar.getTimeInMillis()), newAwardReportTerm, calendar);
        Assert.assertEquals(calendar.getTime(), calculatedDate);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreNotZero_NumberOfMonthsAreZero(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(1000000);
        newAwardReportTerm.setFrequency(newFrequency);
        newAwardReportTerm.getFrequency().setNumberOfDays(15);
        newAwardReportTerm.getFrequency().setNumberOfMonths(0);
        calendar.add(Calendar.DAY_OF_YEAR, 15);
        java.util.Date calculatedDate= service.getCalculatedDueDate(new Date(calendar.getTimeInMillis()), newAwardReportTerm, calendar);
        Assert.assertEquals(calendar.getTime(), calculatedDate);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreNotZero_NumberOfMonthsAreNotZero(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(1000000);
        newAwardReportTerm.setFrequency(newFrequency);
        newAwardReportTerm.getFrequency().setNumberOfDays(15);
        newAwardReportTerm.getFrequency().setNumberOfMonths(5);
        calendar.add(Calendar.MONTH, 5);
        calendar.add(Calendar.DAY_OF_YEAR, 15);
        java.util.Date calculatedDate= service.getCalculatedDueDate(new Date(calendar.getTimeInMillis()), newAwardReportTerm, calendar);
        Assert.assertEquals(calendar.getTime(), calculatedDate);
    }

}
