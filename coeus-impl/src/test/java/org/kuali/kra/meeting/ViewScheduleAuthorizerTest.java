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

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.impl.mocks.KraAuthorizationServiceMock;

import static org.junit.Assert.assertEquals;

public class ViewScheduleAuthorizerTest {
    private static final String USERNAME = "quickstart";
    private static final String PERSON_ID = "jtester";
  
    @Test
    public void testViewSchedulePermission() throws Exception {
        ViewScheduleAuthorizer authorizer = new ViewScheduleAuthorizer();
        
        final KcAuthorizationService kraAuthorizationService = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService);
        
        Committee committee = createCommittee("viewschedule1", "view schedule test");
        CommitteeTaskBase<Committee> task = new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, TaskName.VIEW_SCHEDULE, committee) {};
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
        final KcAuthorizationService kraAuthorizationService1 = new KraAuthorizationServiceMock(false);
        authorizer.setKraAuthorizationService(kraAuthorizationService1);
        assertEquals(false, authorizer.isAuthorized("tdurkin", task));
        
        
        // now check the available to reviewers flag functionality        
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        final KcAuthorizationService kraAuthorizationService2 = new KraAuthorizationServiceMock(true);
        authorizer.setKraAuthorizationService(kraAuthorizationService2);        
        task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getParentCommittee(), committeeSchedule);
        assertEquals(false, authorizer.isAuthorized(PERSON_ID, task));        
        committeeSchedule.setAvailableToReviewers(true);        
        task = new CommitteeScheduleTask(TaskName.VIEW_SCHEDULE, committeeSchedule.getParentCommittee(), committeeSchedule);
        assertEquals(true, authorizer.isAuthorized(PERSON_ID, task));
    }
    
    
    
    
    private Committee createCommittee(String committeeId, String committeeName) {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        committee.setCommitteeName(committeeName);
        return committee;
    }
    
}
