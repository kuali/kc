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
package org.kuali.kra.irb.actions.decision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CommitteeDecisionServiceTest {
    private Mockery context = new JUnit4Mockery();
    @Test
    public void testGetAbstainees() throws Exception {
        CommitteeDecisionServiceImpl committeeDecisionService = new CommitteeDecisionServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final List<ProtocolVoteAbstainee> abstainees = new ArrayList<ProtocolVoteAbstainee>();
        ProtocolVoteAbstainee abstainee1 = new ProtocolVoteAbstainee();
        abstainee1.setProtocolVoteAbstaineesId(1L);
        abstainee1.setProtocolIdFk(1L);
        abstainee1.setScheduleIdFk(2L);
        abstainees.add(abstainee1);
        abstainee1 = new ProtocolVoteAbstainee();
        abstainee1.setProtocolVoteAbstaineesId(2L);
        abstainee1.setProtocolIdFk(1L);
        abstainee1.setScheduleIdFk(2L);
        abstainees.add(abstainee1);
        context.checking(new Expectations() {
            {
                Map<String, String> fieldValues = new HashMap<String, String>();
                fieldValues.put("protocolIdFk", "1" );
                fieldValues.put("scheduleIdFk", "2");
                one(businessObjectService).findMatching(ProtocolVoteAbstainee.class, fieldValues);
                will(returnValue(abstainees));

            }
        });
        committeeDecisionService.setBusinessObjectService(businessObjectService);
        List<ProtocolVoteAbstainee> returnList = (List<ProtocolVoteAbstainee>)committeeDecisionService.getMeetingVoters(1L, 2L, ProtocolVoteAbstainee.class);
        Assert.assertTrue(returnList.size() == 2);
        Assert.assertTrue(returnList.get(0).getProtocolVoteAbstaineesId().equals(1L));
        Assert.assertTrue(returnList.get(1).getProtocolVoteAbstaineesId().equals(2L));
    }
    @Test
    public void testGetRecusers() throws Exception {
        CommitteeDecisionServiceImpl committeeDecisionService = new CommitteeDecisionServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final List<ProtocolVoteRecused> recusers = new ArrayList<ProtocolVoteRecused>();
        ProtocolVoteRecused recuser = new ProtocolVoteRecused();
        recuser.setProtocolVoteRecusedId(1L);
        recuser.setProtocolIdFk(1L);
        recuser.setScheduleIdFk(2L);
        recusers.add(recuser);
        recuser = new ProtocolVoteRecused();
        recuser.setProtocolVoteRecusedId(2L);
        recuser.setProtocolIdFk(1L);
        recuser.setScheduleIdFk(2L);
        recusers.add(recuser);
        context.checking(new Expectations() {
            {
                Map<String, String> fieldValues = new HashMap<String, String>();
                fieldValues.put("protocolIdFk", "1" );
                fieldValues.put("scheduleIdFk", "2");
                one(businessObjectService).findMatching(ProtocolVoteRecused.class, fieldValues);
                will(returnValue(recusers));

            }
        });
        committeeDecisionService.setBusinessObjectService(businessObjectService);
        List<ProtocolVoteRecused> returnList = (List<ProtocolVoteRecused>)committeeDecisionService.getMeetingVoters(1L, 2L, ProtocolVoteRecused.class);
        Assert.assertTrue(returnList.size() == 2);
        Assert.assertTrue(returnList.get(0).getProtocolVoteRecusedId().equals(1L));
        Assert.assertTrue(returnList.get(1).getProtocolVoteRecusedId().equals(2L));
    }

}
