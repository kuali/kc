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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.committee.document.authorization.CommitteeTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;

public class ViewScheduleAuthorizerTest {
    private static final String USERNAME = "quickstart";
    private static final String PERSON_ID = "jtester";
  
    @Test
    public void testViewSchedulePermission() throws Exception {
        ViewScheduleAuthorizer authorizer = new ViewScheduleAuthorizer();
        
        final KraAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        Committee committee = createCommittee("viewschedule1", "view schedule test");
        CommitteeTask task = new CommitteeTask(TaskName.VIEW_SCHEDULE, committee);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
        final KraAuthorizationService kraAuthorizationService1 = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(kraAuthorizationService1);
        assertEquals(false, authorizer.isAuthorized("tdurkin", task));
        
        
        // now check the available to reviewers flag functionality        
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        final KraAuthorizationService kraAuthorizationService2 = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService2);        
        task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee(), committeeSchedule);
        assertEquals(false, authorizer.isAuthorized(PERSON_ID, task));        
        committeeSchedule.setAvailableToReviewers(true);        
        task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getCommittee(), committeeSchedule);
        assertEquals(true, authorizer.isAuthorized(PERSON_ID, task));
    }
    
    
    
    
    private Committee createCommittee(String committeeId, String committeeName) {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        committee.setCommitteeName(committeeName);
        return committee;
    }
    
}
