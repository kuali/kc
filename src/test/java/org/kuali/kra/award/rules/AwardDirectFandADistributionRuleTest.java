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
package org.kuali.kra.award.rules;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardDirectFandADistribution;

/**
 * This class tests the rule methods AwardDirectFandADistribution rules.
 */
public class AwardDirectFandADistributionRuleTest {

    
    private static final int TWO = 2;
    private static final int SIX = 6;
    private static final int THREE = 3;
    private static final int NEGATIVE_THREE = -3;
    private static final int EIGHT = 8;
    AwardDirectFandADistributionRuleImpl awardDirectFandADistributionRuleImpl;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    AwardDirectFandADistribution awardDirectFandADistribution;
    Award award;
    
    /**
     * This method initializes fields and objects for testing
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        awardDirectFandADistribution = new AwardDirectFandADistribution();
        awardDirectFandADistributionRuleImpl = new AwardDirectFandADistributionRuleImpl();
        award = new Award();
        setAwardDatesToDefault();
        createAndSetDefaultDatesForAwardDirectFandADistributions();
        GlobalVariables.setErrorMap(new ErrorMap());
        
        
    }
    
    /**
     * This method sets objects to null.
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardDirectFandADistributions = null;
        awardDirectFandADistribution = null;
        awardDirectFandADistributionRuleImpl = null;
        award = null;
    }
    
    /**
     * This method tests case where dates ranges do not overlap.
     */
    @Test
    public final void testExistingDirectFandADistributionsDatesDontOverlap() {
        Assert.assertTrue(awardDirectFandADistributionRuleImpl.existingDirectFandADistributionsDatesDontOverlap(awardDirectFandADistributions));
    }
    
    /**
     * This method tests case where dates ranges do overlap.
     */
    @Test
    public final void testExistingDirectFandADistributionsDatesDoOverlap() {
        createOverlapOfFirstAndSecondInList();
        Assert.assertFalse(awardDirectFandADistributionRuleImpl.existingDirectFandADistributionsDatesDontOverlap(awardDirectFandADistributions));
        createAndSetDefaultDatesForAwardDirectFandADistributions();
    }
    
    /**
     * This method tests adding a date range that would result in overlapping date ranges.
     */
    @Test
    public final void testDoTargetDatesFallWithinExistingPeriodFails() {
        AwardDirectFandADistribution awardDirectFandADistribution = getDateWithinGapPeriod();
        awardDirectFandADistributionRuleImpl.setAwardDirectFandADistribution(awardDirectFandADistribution);
        Assert.assertFalse(awardDirectFandADistributionRuleImpl.doTargetDatesFallWithinOpenPeriod(awardDirectFandADistributions));
    }
    
    /**
     * This method tests adding a date range that would fall within an open period.
     */
    @Test
    public final void testDoTargetDatesFallWithinExistingPeriodPasses() {
        createDateGapBetweenFirstAndSecondInList();
        AwardDirectFandADistribution awardDirectFandADistribution = getDateWithinGapPeriod();
        awardDirectFandADistributionRuleImpl.setAwardDirectFandADistribution(awardDirectFandADistribution);
        Assert.assertTrue(awardDirectFandADistributionRuleImpl.doTargetDatesFallWithinOpenPeriod(awardDirectFandADistributions));
        createAndSetDefaultDatesForAwardDirectFandADistributions();
    }
    
    /**
     * This method tests that the start date must be before the end date.
     */
    @Test
    public final void testTargetStartAndEndDates() {
        AwardDirectFandADistribution awardDirectFandADistribution = getDateWithinGapPeriod();
        awardDirectFandADistributionRuleImpl.setAwardDirectFandADistribution(awardDirectFandADistribution);
        Assert.assertTrue(awardDirectFandADistributionRuleImpl.isStartDatePriorToEndDate());
        awardDirectFandADistributionRuleImpl.getAwardDirectFandADistribution().setStartDate(getDateAfterProjectEndDate());
        Assert.assertFalse(awardDirectFandADistributionRuleImpl.isStartDatePriorToEndDate());
    }
    
    /**
     * This method sets the default Award start and end dates and initializes the list of AwardDirectFandADistribution list.
     */
    public void setAwardDatesToDefault() {
        Calendar calendar = Calendar.getInstance();
        award.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, TWO);
        calendar.add(Calendar.MONTH, SIX);
        award.setProjectEndDate(new Date(calendar.getTime().getTime()));
    }
    
    /**
     * This method creates list of awardDirectFandADistributions and attaches to Award.  This is needed because a service is used to create the
     * initial list.
     */
    public void createAndSetDefaultDatesForAwardDirectFandADistributions() {
        AwardDirectFandADistribution a0 = new AwardDirectFandADistribution();
        AwardDirectFandADistribution a1 = new AwardDirectFandADistribution();
        AwardDirectFandADistribution a2 = new AwardDirectFandADistribution();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        Calendar calendar = Calendar.getInstance();
        a0.setStartDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DATE, -1);
        a0.setEndDate(new Date(calendar.getTime().getTime()));
        awardDirectFandADistributions.add(a0);
        calendar.add(Calendar.DATE, 1);
        a1.setStartDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DATE, -1);
        a1.setEndDate(new Date(calendar.getTime().getTime()));
        awardDirectFandADistributions.add(a1);
        calendar.add(Calendar.DATE, 1);
        a2.setStartDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.MONTH, SIX);
        a2.setEndDate(new Date(calendar.getTime().getTime()));
        awardDirectFandADistributions.add(a2);   
        award.setAwardDirectFandADistributions(awardDirectFandADistributions);
    }
    
    /**
     * This method creates a date gap between the first and second in the list of AwardDirectFandADistributions.  This is needed for adding
     * a target in a valid date range.
     */
    public void createDateGapBetweenFirstAndSecondInList() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, SIX);
        award.getAwardDirectFandADistributions().get(0).setEndDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, 1);
        award.getAwardDirectFandADistributions().get(1).setStartDate(new Date(calendar.getTime().getTime()));
    }
    
    /**
     * This method returns a date within the date range created by createDateGapBetweenFirstAndSecondInList().
     * @return
     */
    public AwardDirectFandADistribution getDateWithinGapPeriod() {
        AwardDirectFandADistribution awardDirectFandADistribution= new AwardDirectFandADistribution();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, EIGHT);
        awardDirectFandADistribution.setStartDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.MONTH, THREE);
        awardDirectFandADistribution.setEndDate(new Date(calendar.getTime().getTime()));
        return awardDirectFandADistribution;
        
    }
    
    /**
     * This method creates an overlap of dates for testing rule method will fail.
     */
    public void createOverlapOfFirstAndSecondInList() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, SIX);
        calendar.add(Calendar.YEAR, 1);
        award.getAwardDirectFandADistributions().get(0).setEndDate(new Date(calendar.getTime().getTime()));
    }
    
    /**
     * This method creates and returns a Date object prior to the Award start date.
     * @return
     */
    public Date getDatePriorToProjectStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, NEGATIVE_THREE);
        return new Date(calendar.getTime().getTime());
    }
    
    /**
     * This method creates and returns a Date object after the Award end date.
     * @return
     */
    public Date getDateAfterProjectEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, THREE);
        return new Date(calendar.getTime().getTime());
    }
    
}
