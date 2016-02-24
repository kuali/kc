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
package org.kuali.kra.meeting;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.common.committee.impl.meeting.ScheduleActItemType;
import org.kuali.kra.irb.Protocol;


/**
 * TODO This class currently tests only the static comparator, could be extended to test other methods.
 */
public class CommitteeScheduleMinuteTest {

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
