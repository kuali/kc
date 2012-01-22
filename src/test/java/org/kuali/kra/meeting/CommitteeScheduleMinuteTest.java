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
package org.kuali.kra.meeting;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;


/**
 * TODO This class currently tests only the static comparator, could be extended to test other methods.
 */
public class CommitteeScheduleMinuteTest extends KcUnitTestBase {

    @Test
    public void testEntryTypeComparator() {        
        
        CommitteeScheduleMinute csm1 = new CommitteeScheduleMinute();
        CommitteeScheduleMinute csm2 = new CommitteeScheduleMinute();
        csm1.setMinuteEntryType(new MinuteEntryType());
        csm2.setMinuteEntryType(new MinuteEntryType());
        csm1.setProtocol(new Protocol() {

            private static final long serialVersionUID = -1273061983131550371L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        });
        
        csm2.setProtocol(new Protocol() {

            private static final long serialVersionUID = -1273061983131550372L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        });
        
        //test case 1
        csm1.getMinuteEntryType().setSortId(4);
        csm2.getMinuteEntryType().setSortId(5);        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) < 0);
        
        csm1.getMinuteEntryType().setSortId(4);
        csm2.getMinuteEntryType().setSortId(4);        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) == 0);
        
        csm1.getMinuteEntryType().setSortId(5);
        csm2.getMinuteEntryType().setSortId(4);        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) > 0);
        
        // test case 2
        csm1.getMinuteEntryType().setSortId(4);
        csm2.getMinuteEntryType().setSortId(5);
        
        csm1.setProtocolIdFk(0L);
        csm2.setProtocolIdFk(0L);        
       
        csm1.getProtocol().setProtocolNumber("bbb");
        csm2.getProtocol().setProtocolNumber("aaa");        
        // check that entry type gets precedence in sorting
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) > 0); 
        
        // test case 3
        csm1.getMinuteEntryType().setSortId(4);
        csm2.getMinuteEntryType().setSortId(4);
               
        csm1.getProtocol().setProtocolNumber("aaa");
        csm2.getProtocol().setProtocolNumber("bbb");        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) < 0);
        
        csm1.getProtocol().setProtocolNumber("aaa");
        csm2.getProtocol().setProtocolNumber("aaa");        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) == 0);
        
        csm1.getProtocol().setProtocolNumber("bbb");
        csm2.getProtocol().setProtocolNumber("aaa");        
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) > 0);
        
        
        // test case 4
               
        csm1.getProtocol().setProtocolNumber("aaa");
        csm2.getProtocol().setProtocolNumber("bbb");
        
        csm1.setCommScheduleActItemsIdFk(0L);
        csm2.setCommScheduleActItemsIdFk(0L);       
        
        csm1.setCommScheduleActItem(new CommScheduleActItem());
        csm2.setCommScheduleActItem(new CommScheduleActItem());
        csm1.getCommScheduleActItem().setScheduleActItemType(new ScheduleActItemType());
        csm2.getCommScheduleActItem().setScheduleActItemType(new ScheduleActItemType());
        csm1.getCommScheduleActItem().getScheduleActItemType().setScheduleActItemTypeCode("bbb");
        csm2.getCommScheduleActItem().getScheduleActItemType().setScheduleActItemTypeCode("aaa");
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) < 0);
        
        
        // test case 5
        csm1.setProtocolIdFk(null);
        csm2.setProtocolIdFk(null);   
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) == 0);
        
        csm1.getCommScheduleActItem().getScheduleActItemType().setScheduleActItemTypeCode("aaa");
        csm2.getCommScheduleActItem().getScheduleActItemType().setScheduleActItemTypeCode("aaa");
        Assert.assertTrue(CommitteeScheduleMinute.entryTypeComparator.compare(csm1, csm2) == 0);
    }
}
