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

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.Person;
import org.kuali.rice.kns.util.DateUtils;

/**
 * This class tests the AwardApprovedEquipment BO
 */
public class AwardApprovedForeignTravelTest {

    private static final String JOHN_DOE = "John Doe";
    private static final String JANE_DOE = "Jane Doe";
    private static final String JOHN_SMITH = "John Smith";
    private static final String JANE_SMITH = "Jane Smith";

    private static final Date DATE1 = DateUtils.newDate(2009, Calendar.JUNE, 1);
    private static final Date DATE2 = DateUtils.newDate(2009, Calendar.JUNE, 9);
    
    @Test
    public void testCompareTo_StartDateEqual_TravelerFullNames_Equal() {
        AwardApprovedForeignTravel trip1 = createForeignTravelTrip(DATE1, JOHN_DOE);
        AwardApprovedForeignTravel trip2 = createForeignTravelTrip(DATE1, JOHN_DOE);
        Assert.assertTrue(trip1.compareTo(trip2) == 0);
    }
    
    @Test
    public void testCompareTo_StartDateNotEqual_SecondTripFirst() {
        AwardApprovedForeignTravel trip1 = createForeignTravelTrip(DATE2, JOHN_DOE);
        AwardApprovedForeignTravel trip2 = createForeignTravelTrip(DATE1, JOHN_DOE);
        Assert.assertTrue(trip1.compareTo(trip2) > 0);
    }
    
    @Test
    public void testCompareTo_StartDatesEqual_TravelerLastNames_FirstBeforeSecond() {
        AwardApprovedForeignTravel trip1 = createForeignTravelTrip(DATE1, JOHN_DOE);
        AwardApprovedForeignTravel trip2 = createForeignTravelTrip(DATE1, JANE_SMITH);
        Assert.assertTrue(trip1.compareTo(trip2) < 0);
    }
    
    @Test
    public void testCompareTo_StartDatesEqual_TravelerLastNames_Equal_FirstNameAfterSecond() {
        AwardApprovedForeignTravel trip1 = createForeignTravelTrip(DATE1, JOHN_DOE);
        AwardApprovedForeignTravel trip2 = createForeignTravelTrip(DATE1, JANE_DOE);
        Assert.assertTrue(trip1.compareTo(trip2) > 0);
    }
    
    private AwardApprovedForeignTravel createForeignTravelTrip(Date date, String travelerName) {
        AwardApprovedForeignTravel trip = new AwardApprovedForeignTravel();
        trip.setTraveler(getTraveler(travelerName));
        trip.setStartDate(date);
        return trip;
    }
    
    private Person getTraveler(String travelerName) {
        Person person = new Person();
        person.setFullName(travelerName);
        String[] names = travelerName.split(" ");
        person.setFirstName(names[0]);
        person.setLastName(names[1]);
        return person;
    }
}
