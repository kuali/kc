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
package org.kuali.kra.award.paymentreports.closeout;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;

import java.sql.Date;
import java.util.*;

/**
 * 
 * This class tests the AwardCloseoutServiceImpl
 */
public class AwardCloseoutServiceImplTest {

    public static final String REPORT_CLASS_CODE_FINANCIAL_REPORT = "1";
    public static final String REPORT_CLASS_CODE_PATENT= "3";

    AwardCloseoutServiceImpl service; 
    List<AwardReportTerm> awardReportTermItems;
    Map<String, Object> closeoutDueDates;
    AwardReportTerm newAwardReportTerm;
    Report newReport;
    Frequency newFrequency;


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
     * This method tests the updateCloseoutDueDate method of AwardCloseoutBean
     * for the case where due dates for all AwardReportTerm objects are equal and the 2 dates - aka dateCalculatedUsingFinalInvoiceDue and dateCalculatedUsingFrequency are equal.
     *
     */
    @Test
    public final void testUpdateCloseoutDueDate_allDueDatesAreEqual_And_2DatesAreEqual(){
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), true, REPORT_CLASS_CODE_FINANCIAL_REPORT);
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
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), false, REPORT_CLASS_CODE_FINANCIAL_REPORT);
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
        //service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10001), new java.util.Date(10000), true, REPORT_CLASS_CODE_FINANCIAL_REPORT);
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), false, REPORT_CLASS_CODE_FINANCIAL_REPORT);
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
        service.updateCloseoutDueDate(closeoutDueDates, new java.util.Date(10000), false, REPORT_CLASS_CODE_FINANCIAL_REPORT);
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
        getCalculatedDueDatTesting(0, 0, 0, 0);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreZero_NumberOfMonthsAreNotZero(){
        getCalculatedDueDatTesting(0, 5, 0, 0);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreNotZero_NumberOfMonthsAreZero(){
        getCalculatedDueDatTesting(15, 0, 0, 0);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfDaysAreNotZero_NumberOfMonthsAreNotZero(){
        getCalculatedDueDatTesting(15, 5, 0, 0);
    }

    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfAdvanceDaysAreNotZero_NumberOfAdvanceMonthsAreNotZero(){
        getCalculatedDueDatTesting(0, 0, 15, 5);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfAdvanceDaysAreNotZero_NumberOfAdvanceMonthsAreZero(){
        getCalculatedDueDatTesting(0, 0, 15, 0);
    }
    
    /**
     * 
     * This method tests the getCalculatedDueDate method of AwardCloseoutBean
     */
    @Test
    public final void testGetCalculatedDueDate_NumberOfAdvanceDaysAreZero_NumberOfAdvanceMonthsAreNotZero(){
        getCalculatedDueDatTesting(0, 0, 0, 5);
    }
    
    public void getCalculatedDueDatTesting(int numberOfDays, int numberOfMonths, int numberOfAdvanceDays, int numberOfAdvanceMonths) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(1000000);
        newFrequency.setNumberOfDays(numberOfDays);
        newFrequency.setNumberOfMonths(numberOfMonths);
        newFrequency.setAdvanceNumberOfDays(numberOfAdvanceDays);
        newFrequency.setAdvanceNumberOfMonths(numberOfAdvanceMonths);
        newAwardReportTerm.setFrequency(newFrequency);
        calendar.add(Calendar.MONTH, numberOfMonths);
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        calendar.add(Calendar.MONTH, -numberOfAdvanceMonths);
        calendar.add(Calendar.DAY_OF_YEAR, -numberOfAdvanceDays);
        java.util.Date calculatedDate= service.getCalculatedDueDate(new Date(calendar.getTimeInMillis()), newAwardReportTerm, calendar);
        Assert.assertEquals(calendar.getTime(), calculatedDate);
    }
    
}
