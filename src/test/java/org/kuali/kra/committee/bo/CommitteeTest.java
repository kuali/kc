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

import static org.junit.Assert.assertEquals;
import org.junit.Test;


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
