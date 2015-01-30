/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * This class tests AwardApprovedForeignTravelRuleImpl behavior
 */
public class AwardApprovedForeignTravelRuleIntegrationTest extends KcIntegrationTestBase {
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
    
    private static final Integer TRAVELER2_ID = 1002;
    private static final String TRAVELER2_NAME = "Jane Doe";
    
    private AwardApprovedForeignTravelRuleImpl approvedForeignTravelRule;
    private AwardApprovedForeignTravel trip1;
    private AwardApprovedForeignTravel trip2;
    
    private Award award;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        this.approvedForeignTravelRule = new AwardApprovedForeignTravelRuleImpl();
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        KNSGlobalVariables.setKualiForm(new AwardForm());
        
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
 

    private Map getErrors() { 
        return GlobalVariables.getMessageMap().getErrorMessages();
    }
    
    private AwardApprovedForeignTravel createForeignTravelTrip(String travelerId, String travelerName, String destination, Date startDate, 
                                                                    Date endDate, double amount) {
        return new AwardApprovedForeignTravel(getTraveler(travelerId, travelerName), destination, startDate, endDate, amount);
    }

    private AwardApprovedForeignTravel createForeignTravelTrip(Integer travelerId, String travelerName, String destination, Date startDate, 
                                                                    Date endDate, double amount) {
        return new AwardApprovedForeignTravel(getTraveler(travelerId, travelerName), destination, startDate, endDate, amount);
    }
    
    private KcPerson getTraveler(String travelerId, String travelerName) {
        KcPerson person = new KcPerson();
        person.setPersonId(travelerId);
        //person.setFullName(travelerName);
        return person;
    }

    private NonOrganizationalRolodex getTraveler(Integer travelerId, String travelerName) {
        NonOrganizationalRolodex contact = new NonOrganizationalRolodex();
        contact.setRolodexId(travelerId);
        String[] parts = travelerName.split(" ");
        contact.setFirstName(parts[0]);
        contact.setFirstName(parts[1]);
        return contact;
    }
}
