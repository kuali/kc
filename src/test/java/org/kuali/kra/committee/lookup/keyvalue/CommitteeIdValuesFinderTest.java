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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class CommitteeIdValuesFinderTest {

    
    private final String CMT_1_ID = "c1";
    private final String CMT_2_ID = "c2";
    private final String CMT_3_ID = "c3";
    private final String CMT_4_ID = "c4";
    
    
    private static final String C1_LATEST_NAME = "c1Latest";
    private static final String C2_LATEST_NAME = "c2Latest";
    private static final String C3_LATEST_NAME = "c3Latest";
    private static final String C4_LATEST_NAME = "c4Latest";
    private static final String C1_OLD_NAME = "c1Old";
    private static final String C2_OLD_NAME = "c2Old";
    private static final String C3_OLD_NAME = "c3old";
    private static final String C3_OLDER_NAME = "c3Old";
    
    private static final Integer C1_LATEST_SEQ = 1;
    private static final Integer C2_LATEST_SEQ = 1;
    private static final Integer C3_LATEST_SEQ = 2;
    private static final Integer C4_LATEST_SEQ = 0;
    private static final Integer C1_OLD_SEQ = 0;
    private static final Integer C2_OLD_SEQ = 0;
    private static final Integer C3_OLD_SEQ = 1;
    private static final Integer C3_OLDER_SEQ = 0;
    
    private Mockery context = new JUnit4Mockery();
    
    
    @Test
    public void testGetActiveCommittees(){
        // create 4 committees as follows:
        // two with two versions, 
        // one with three versions, 
        // and one with one version        
        
        // two versions
        Committee committee1 = new Committee();
        committee1.setCommitteeId("c1");
        committee1.setSequenceNumber(C1_LATEST_SEQ);
        committee1.setCommitteeName(C1_LATEST_NAME);
        
        Committee committee1Old = new Committee();
        committee1Old.setCommitteeId("c1");
        committee1Old.setSequenceNumber(C1_OLD_SEQ);
        committee1Old.setCommitteeName(C1_OLD_NAME);
                
        // two versions
        Committee committee2 = new Committee();
        committee2.setCommitteeId("c2");
        committee2.setSequenceNumber(C2_LATEST_SEQ);
        committee2.setCommitteeName(C2_LATEST_NAME);
        
        Committee committee2Old = new Committee();
        committee2Old.setCommitteeId("c2");
        committee2Old.setSequenceNumber(C2_OLD_SEQ);
        committee2Old.setCommitteeName(C2_OLD_NAME);
        
        // three versions
        Committee committee3 = new Committee();
        committee3.setCommitteeId("c3");
        committee3.setSequenceNumber(C3_LATEST_SEQ);
        committee3.setCommitteeName(C3_LATEST_NAME);
        
        Committee committee3Old = new Committee();
        committee3Old.setCommitteeId("c3");
        committee3Old.setSequenceNumber(C3_OLD_SEQ);
        committee3Old.setCommitteeName(C3_OLD_NAME);
        
        Committee committee3Older = new Committee();
        committee3Older.setCommitteeId("c3");
        committee3Older.setSequenceNumber(C3_OLDER_SEQ);
        committee3Older.setCommitteeName(C3_OLDER_NAME);
        
        // one version
        Committee committee4 = new Committee();
        committee4.setCommitteeId("c4");
        committee4.setSequenceNumber(C4_LATEST_SEQ);
        committee4.setCommitteeName(C4_LATEST_NAME);
        
        
        final List<Committee> allCommittees = new ArrayList<Committee>();
        // add in scrambled order for just a bit of 'stress' in the test
        allCommittees.add(committee4);
        allCommittees.add(committee1Old);
        allCommittees.add(committee3Old);
        allCommittees.add(committee3);
        allCommittees.add(committee2);
        allCommittees.add(committee3Older);
        allCommittees.add(committee2Old);        
        allCommittees.add(committee1);
        
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            oneOf(businessObjectService).findAll(Committee.class);
            will(returnValue(allCommittees));
        }});
        
        CommitteeIdValuesFinder finder = new CommitteeIdValuesFinder();
        finder.setBusinessObjectService(businessObjectService);
        List<Committee> results = finder.getActiveCommittees();
        
        Assert.assertEquals(4, results.size());
        Assert.assertTrue(results.contains(committee1));
        Assert.assertTrue(results.contains(committee2));
        Assert.assertTrue(results.contains(committee3));
        Assert.assertTrue(results.contains(committee4));
    }
    
    
    
    
    // NOTE: this method tests with an empty exclusion list of committees, 
    // it's not quite clear in what context(s) would this list be non-empty
    @Test
    public void testGetKeyValues(){
        Committee committee1 = new Committee();
        committee1.setCommitteeId(this.CMT_1_ID);
        committee1.setCommitteeName(this.C1_LATEST_NAME);
        
        Committee committee2 = new Committee();
        committee2.setCommitteeId(this.CMT_2_ID);
        committee2.setCommitteeName(this.C2_LATEST_NAME);
        
        Committee committee3 = new Committee();
        committee3.setCommitteeId(this.CMT_3_ID);
        committee3.setCommitteeName(this.C3_LATEST_NAME);
        
        Committee committee4 = new Committee();
        committee4.setCommitteeId(this.CMT_4_ID);
        committee4.setCommitteeName(this.C4_LATEST_NAME);
        
        final List<Committee> activeCommittees = new ArrayList<Committee>();
        activeCommittees.add(committee1);
        activeCommittees.add(committee2);
        activeCommittees.add(committee3);
        activeCommittees.add(committee4);
        
        KeyValue klp0 = new ConcreteKeyValue("", "select");
        KeyValue klp1 = new ConcreteKeyValue(CMT_1_ID, C1_LATEST_NAME);
        KeyValue klp2 = new ConcreteKeyValue(CMT_2_ID, C2_LATEST_NAME);
        KeyValue klp3 = new ConcreteKeyValue(CMT_3_ID, C3_LATEST_NAME);
        KeyValue klp4 = new ConcreteKeyValue(CMT_4_ID, C4_LATEST_NAME);
       
        // create an anonymous instance of the finder overriding the
        // getActiveCommittees() method, with our mock
        CommitteeIdValuesFinder finder = new CommitteeIdValuesFinder(){
            @Override
            protected List<Committee> getActiveCommittees() {
                return activeCommittees;
            }
        };
        List<KeyValue> results = finder.getKeyValues();
        
        Assert.assertEquals(5, results.size());
        Assert.assertTrue(results.contains(klp0));
        Assert.assertTrue(results.contains(klp1));
        Assert.assertTrue(results.contains(klp2));
        Assert.assertTrue(results.contains(klp3));
        Assert.assertTrue(results.contains(klp4));
        
    }
    
    
}
