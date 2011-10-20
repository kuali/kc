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
package org.kuali.kra.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.rice.core.util.KeyLabelPair;

public class CommitteeIdIdPairValuesFinderTest {

    private final String CMT_1_ID = "c1";
    private final String CMT_2_ID = "c2";
    private final String CMT_3_ID = "c3";
    private final String CMT_4_ID = "c4";
        
    @Test
    public void testGetKeyValues() {
        Committee committee1 = new Committee();
        committee1.setCommitteeId(this.CMT_1_ID);
        
        Committee committee2 = new Committee();
        committee2.setCommitteeId(this.CMT_2_ID);
        
        Committee committee3 = new Committee();
        committee3.setCommitteeId(this.CMT_3_ID);
        
        Committee committee4 = new Committee();
        committee4.setCommitteeId(this.CMT_4_ID);
        
        
        final List<Committee> activeCommittees = new ArrayList<Committee>();
        activeCommittees.add(committee1);
        activeCommittees.add(committee2);
        activeCommittees.add(committee3);
        activeCommittees.add(committee4);
        
        KeyLabelPair klp0 = new KeyLabelPair("", "select");
        KeyLabelPair klp1 = new KeyLabelPair(CMT_1_ID, CMT_1_ID);
        KeyLabelPair klp2 = new KeyLabelPair(CMT_2_ID, CMT_2_ID);
        KeyLabelPair klp3 = new KeyLabelPair(CMT_3_ID, CMT_3_ID);
        KeyLabelPair klp4 = new KeyLabelPair(CMT_4_ID, CMT_4_ID);
       
        // create an anonymous instance of the finder overriding the
        // superclass's getActiveCommittees() method, with our mock
        CommitteeIdIdPairValuesFinder finder = new CommitteeIdIdPairValuesFinder(){
            @Override
            protected List<Committee> getActiveCommittees() {
                return activeCommittees;
            }
        };
        List<KeyLabelPair> results = finder.getKeyValues();
        
        Assert.assertEquals(5, results.size());
        Assert.assertTrue(results.contains(klp0));
        Assert.assertTrue(results.contains(klp1));
        Assert.assertTrue(results.contains(klp2));
        Assert.assertTrue(results.contains(klp3));
        Assert.assertTrue(results.contains(klp4));
        
    }

}
