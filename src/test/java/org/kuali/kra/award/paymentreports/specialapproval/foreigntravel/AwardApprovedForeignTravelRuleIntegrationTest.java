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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Person;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests AwardApprovedForeignTravelRuleImpl behavior
 */
public class AwardApprovedForeignTravelRuleIntegrationTest extends KraTestBase {
    private static final String ERROR_KEY = AwardApprovedForeignTravelRule.APPROVED_FOREIGN_TRAVEL_LIST_ERROR_KEY;
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
    
    private static final String TRAVELER1_ID = "000000001";
    private static final String TRAVELER1_NAME = "Joe Smith";
    
    private static final String TRAVELER2_ID = "000000002";
    private static final String TRAVELER2_NAME = "Jane Doe";
    
    private AwardApprovedForeignTravelRuleImpl approvedForeignTravelRule;
    private AwardApprovedForeignTravel trip1;
    private AwardApprovedForeignTravel trip2;
    
    private Award award;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        this.approvedForeignTravelRule = new AwardApprovedForeignTravelRuleImpl();
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        GlobalVariables.setKualiForm(new AwardForm());
        
        trip1 = createForeignTravelTrip(TRAVELER1_ID, TRAVELER1_NAME, DESTINATION1_NAME, START1_DATE, END1_DATE, AMOUNT1);
        trip1.setApprovedForeignTravelId(1L);
        
        trip2 = createForeignTravelTrip(TRAVELER2_ID, TRAVELER2_NAME, DESTINATION2_NAME, START2_DATE, END2_DATE, AMOUNT2);
        trip2.setApprovedForeignTravelId(2L);
    }
    
    @After
    public void tearDown() throws Exception {
        award = null;
        approvedForeignTravelRule = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testForDuplicate_NoneExpected() throws Exception {
        award.add(trip1);
        approvedForeignTravelRule.isAmountValid(ERROR_KEY, trip2);
        Assert.assertEquals(0, getErrors().size());
    }
    
    @Test
    public void testForDuplicate_ErrorExpected() throws Exception {
        award.add(trip1);
        approvedForeignTravelRule.isAmountValid(ERROR_KEY, new AwardApprovedForeignTravel(trip1));
        Assert.assertEquals(0, getErrors().size());
    }
    
    @Test
    public void testValidatingAmount_NegativeAmount() throws Exception {
        trip1.setAmount(ZERO_AMOUNT - ONE_PENNY);
        approvedForeignTravelRule.isAmountValid(ERROR_KEY, trip1);
        Assert.assertEquals(1, getErrors().size());
    }
    
    @Test
    public void testValidatingAmount_ZeroAmount() throws Exception {
        trip1.setAmount(ZERO_AMOUNT);
        approvedForeignTravelRule.isAmountValid(ERROR_KEY, trip1);
        Assert.assertEquals(0, getErrors().size());
    }
    
    @Test
    public void testValidatingAmount_PositiveAmount() throws Exception {
        trip1.setAmount(AMOUNT1);
        approvedForeignTravelRule.isAmountValid(ERROR_KEY, trip1);
        Assert.assertEquals(0, getErrors().size());
    }
 

    private ErrorMap getErrors() {
        return GlobalVariables.getErrorMap();
    }
    
    private AwardApprovedForeignTravel createForeignTravelTrip(String travelerId, String travelerName, String destination, Date startDate, 
                                                                    Date endDate, double amount) {
        return new AwardApprovedForeignTravel(getTraveler(travelerId, travelerName), destination, startDate, endDate, amount);
    }
    
    private Person getTraveler(String travelerId, String travelerName) {
        Person person = new Person();
        person.setPersonId(travelerId);
        person.setFullName(travelerName);
        return person;
    }
    
}
