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

import static org.junit.Assert.assertEquals;


public class CommitteeTest {
    
    
    @Test
    public void testGetCommitteeMembershipFor() {
        
        // create three committee membership objects, 
        // two with legit person ids, and the last one with null person id
        CommitteeMembership cm1 = new CommitteeMembership();
        cm1.setPersonId("person1");
        
        CommitteeMembership cm2 = new CommitteeMembership();
        cm2.setPersonId("person2");
        
        CommitteeMembership nullPersonIdMember = new CommitteeMembership();
        nullPersonIdMember.setPersonId(null);
        
        
        // create the test committee instance and add the memberships to it
        Committee committee = new Committee();
        committee.getCommitteeMemberships().add(cm1);
        committee.getCommitteeMemberships().add(cm2);
        committee.getCommitteeMemberships().add(nullPersonIdMember);
        
        assertEquals(cm1, committee.getCommitteeMembershipFor("person1"));
        assertEquals(cm2, committee.getCommitteeMembershipFor("person2"));
        assertEquals(null, committee.getCommitteeMembershipFor("random"));
        assertEquals(null, committee.getCommitteeMembershipFor(null));
    }
    
}
