/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

public class CommitteeMembershipTest {

    @Test
    public void testIsRepresentingPerson() {
        // create three committee membership objects, 
        // two with legit person ids, and the last one with null person id
        CommitteeMembership cm1 = new CommitteeMembership();
        cm1.setPersonId("person1");
        
        CommitteeMembership cm2 = new CommitteeMembership();
        cm2.setPersonId("person2");
        
        CommitteeMembership nullPersonIdMember = new CommitteeMembership();
        nullPersonIdMember.setPersonId(null);
        
        assertTrue(cm1.isRepresentingPerson("person1"));
        assertTrue(cm2.isRepresentingPerson("person2"));
        assertFalse(cm1.isRepresentingPerson("person2"));
        assertFalse(cm2.isRepresentingPerson(null));
        assertFalse(nullPersonIdMember.isRepresentingPerson(null));
    }
    
    @Test
    public void testHasTermEnded() {
        // get a calendar using the default time zone and locale.
        Calendar cal = Calendar.getInstance();
        // clear lower time fields
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        Date dateToday = new Date(cal.getTime().getTime());
        // one day into the future
        cal.roll(Calendar.DATE, true);
        Date dateTomorrow = new Date(cal.getTime().getTime());
        // one day into the past
        cal.roll(Calendar.DATE, false);
        cal.roll(Calendar.DATE, false);
        Date dateYesterday = new Date(cal.getTime().getTime());
        
        CommitteeMembership cm = new CommitteeMembership();
        
        cm.setTermEndDate(dateYesterday);
        assertTrue(cm.hasTermEnded());
        cm.setTermEndDate(dateToday);
        assertFalse(cm.hasTermEnded());
        cm.setTermEndDate(dateTomorrow);
        assertFalse(cm.hasTermEnded());
        cm.setTermEndDate(null);
        assertTrue(cm.hasTermEnded());        
    }
        
}
