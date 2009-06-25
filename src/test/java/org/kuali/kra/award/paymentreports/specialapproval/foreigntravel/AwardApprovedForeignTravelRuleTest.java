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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Person;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests AwardApprovedEquipmentRuleImpl behavior
 */
public class AwardApprovedForeignTravelRuleTest {
    private static final double ZERO_AMOUNT = 0.00;
    private static final double AMOUNT1 = 100.00;
    private static final double AMOUNT2 = 200.00;
    private static final String DESTINATION1_NAME = "Tokyo, Japan";
    private static final String DESTINATION2_NAME = "Moscow, Russia";
    private static final int YEAR = 2009;
    private static final int DAY_10 = 10;
    private static final Date END1_DATE = new Date(new GregorianCalendar(YEAR, Calendar.JUNE, DAY_10).getTimeInMillis());
    private static final Date END2_DATE = new Date(new GregorianCalendar(YEAR, Calendar.JULY, DAY_10).getTimeInMillis());
    
    private static final double ONE_PENNY = 0.01;
    
    private static final Date START1_DATE = new Date(new GregorianCalendar(YEAR, Calendar.JUNE, 1).getTimeInMillis());
    private static final Date START2_DATE = new Date(new GregorianCalendar(YEAR, Calendar.JULY, 1).getTimeInMillis());
    
    private static final String TRAVELER1_NAME = "Joe Smith";    
    private static final String TRAVELER2_NAME = "Jane Doe";
    private static final String ERROR_PATH = "approvedForeignTravelBean.newApprovedForeignTravel.";
    
    private AwardApprovedForeignTravelRuleImpl approvedForeignTravelRule;
    private Award award;
    private AwardApprovedForeignTravel trip1;
    private AwardApprovedForeignTravel trip2;
    
    @Before
    public void setUp() throws Exception {
        approvedForeignTravelRule = prepareTestReadyAwardApprovedForeignTravelRule();
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        GlobalVariables.setErrorMap(new ErrorMap());
        
        trip1 = createForeignTravelTrip(1L, TRAVELER1_NAME, DESTINATION1_NAME, START1_DATE, END1_DATE, AMOUNT1);
        trip1.setApprovedForeignTravelId(1L);
        
        trip2 = createForeignTravelTrip(2L, TRAVELER2_NAME, DESTINATION2_NAME, START2_DATE, END2_DATE, AMOUNT2);
        trip2.setApprovedForeignTravelId(2L);
    }
    
    @After
    public void tearDown() {
        trip1 = null;
        trip2 = null;
        award = null;
        approvedForeignTravelRule = null;
    }
    
    @Test
    public void testIsUnique_AddingToEmptyList() {
        Assert.assertTrue(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), trip1));
    }
    
    @Test
    public void testIsUnique_NoDuplicate() {
        award.add(trip1);
        checkExistingEntriesDontTriggerDuplicationError();
        award.add(trip2);
        checkExistingEntriesDontTriggerDuplicationError();
    }
        
    @Test
    public void testIsUnique_DuplicateTrip1() {
        award.add(trip1);
        Assert.assertFalse(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), new AwardApprovedForeignTravel(trip1)));
    }
    
    @Test
    public void testIsUnique_DuplicateTrip2() {
        award.add(trip2);
        Assert.assertFalse(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), new AwardApprovedForeignTravel(trip2)));
    }
    
    @Test
    public void testIsUnique_TravelerEditCausesDuplicateTrip() {
        award.add(trip1);
        AwardApprovedForeignTravel anotherTrip = new AwardApprovedForeignTravel(trip1);
        anotherTrip.setTraveler(trip2.getTraveler());
        
        award.add(anotherTrip);        
        Assert.assertTrue(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), anotherTrip));
        
        Person newTraveler = getTraveler(3L, trip1.getTravelerName());
        anotherTrip.setTraveler(newTraveler);
        Assert.assertFalse(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), anotherTrip));
    }
    
    @Test
    public void testIsUnique_DestinationEditCausesDuplicateTrip() {
        award.add(trip1);
        AwardApprovedForeignTravel anotherTrip = new AwardApprovedForeignTravel(trip1);
        anotherTrip.setDestination(trip2.getDestination());
        
        award.add(anotherTrip);        
        Assert.assertTrue(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), anotherTrip));
        
        anotherTrip.setDestination(trip1.getDestination());
        Assert.assertFalse(approvedForeignTravelRule.isUnique(ERROR_PATH, award.getApprovedForeignTravelTrips(), anotherTrip));
    }
    
    @Test
    public void testRequiredFieldPresent() {
        Assert.assertTrue(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        
        trip1.setTraveler(null);
        Assert.assertFalse(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        trip1.setTraveler(getTraveler(1L, TRAVELER1_NAME));
        
        trip1.setDestination(null);
        Assert.assertFalse(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        trip1.setDestination(DESTINATION1_NAME);
        
        trip1.setStartDate(null);
        Assert.assertFalse(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        trip1.setStartDate(START1_DATE);
        
        trip1.setEndDate(null);
        Assert.assertTrue(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        trip1.setEndDate(START1_DATE);
        
        trip1.setAmount(null);
        Assert.assertFalse(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
        trip1.setAmount(AMOUNT1);
        
        Assert.assertTrue(approvedForeignTravelRule.areRequiredFieldsComplete(trip1));
    }

    @Test
    public void testValidatingAmount() {
        trip1.setAmount(null);
        checkValidAmount(trip1, 1);
        
        trip1.setAmount(ZERO_AMOUNT);
        checkValidAmount(trip1, 0);
        
        trip1.setAmount(ZERO_AMOUNT - ONE_PENNY);
        checkValidAmount(trip1, 1);
        
        trip1.setAmount(AMOUNT2);
        checkValidAmount(trip1, 0);        
    }

    private void checkExistingEntriesDontTriggerDuplicationError() {
        List<AwardApprovedForeignTravel> trips = award.getApprovedForeignTravelTrips();
        for(AwardApprovedForeignTravel trip : trips) {
            Assert.assertTrue(approvedForeignTravelRule.isUnique(ERROR_PATH, trips, trip));
        }
    }
    
    private void checkValidAmount(AwardApprovedForeignTravel trip, int expectedErrorCount) {
        approvedForeignTravelRule.isAmountValid(AwardApprovedForeignTravelRule.ERROR_AWARD_APPROVED_FOREIGN_INVALID_FIELD, trip);
        Assert.assertEquals(expectedErrorCount, GlobalVariables.getErrorMap().size());
        GlobalVariables.getErrorMap().clear();
    }
    
    private AwardApprovedForeignTravel createForeignTravelTrip(Long travelerId, String travelerName, String destination, Date startDate, Date endDate, double amount) {
        return new AwardApprovedForeignTravel(getTraveler(travelerId, travelerName), destination, startDate, endDate, amount);
    }
    
    private Person getTraveler(Long travelerId, String travelerName) {
        Person person = new Person();
        person.setKimPersonId(travelerId);
        person.setFullName(travelerName);
        return person;
    }
    
    private AwardApprovedForeignTravelRuleImpl prepareTestReadyAwardApprovedForeignTravelRule() {
        return new AwardApprovedForeignTravelRuleImpl();
    }
}
