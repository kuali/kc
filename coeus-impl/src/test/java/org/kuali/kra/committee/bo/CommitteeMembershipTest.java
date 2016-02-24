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
package org.kuali.kra.committee.bo;

import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        //cal.roll(Calendar.DATE, true);
        cal.add(Calendar.DATE, 1);
        Date dateTomorrow = new Date(cal.getTime().getTime());
        // one day into the past
        //cal.roll(Calendar.DATE, false);
        //cal.roll(Calendar.DATE, false);
        cal.add(Calendar.DATE, -2);
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
