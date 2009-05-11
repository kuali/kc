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
package org.kuali.kra.irb.actions.submit.mocks;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Mock committee service that is used by ProtocolSubmitActionServiceTest.
 */
public class MockCommitteeService implements CommitteeService {

    private Committee committee = null;
    
    public MockCommitteeService(Committee committee) {
        this.committee = committee;
    }
    
    public void addResearchAreas(Committee committee, Collection<ResearchArea> researchAreas) {
      
    }

    public List<KeyLabelPair> getAvailableCommitteeDates(String committeeId) {
        return null;
    }

    public List<CommitteeMembership> getAvailableMembers(String committeeId, String scheduleId) {
        return null;
    }

    public Committee getCommitteeById(String committeeId) {
        if (committee == null) {
            assertEquals("", committeeId);
            return null;
        }
        assertEquals(committee.getCommitteeId(), committeeId);
        return committee;
    }

    public CommitteeSchedule getCommitteeSchedule(Committee committee, String scheduleId) {
        for (CommitteeSchedule s : committee.getCommitteeSchedules()) {
            if (StringUtils.equals(s.getScheduleId(), scheduleId)) {
                return s;
            }
        }
        return null;
    }
}
