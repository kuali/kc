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
package org.kuali.kra.committee.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.DeleteCommitteeScheduleEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.service.impl.CommitteeServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public class DeleteCommitteeScheduleRuleTest extends CommitteeRuleTestBase {
    private DeleteCommitteeScheduleRule rule;
    private Mockery context = new JUnit4Mockery();
    private CommitteeServiceImpl committeeService;
    
    @Before
    public void setup() throws Exception {
        super.setUp();
        committeeService = new CommitteeServiceImpl();
        rule = new DeleteCommitteeScheduleRule() {
            private static final String ID = "document.committeeList[0].committeeSchedules[";
            // TODO : this is a compromise at this point because lots of real data has to be set up in order to do this
            // test.   "new ComitteeDocument" requires spring service access, so it can not be done with jmock.
            // however, still using jmock for businessobjectservice.  Once, we have committee & protocol data
            // preloaded, then we should not do this override here.
            @Override
            public boolean processRules(DeleteCommitteeScheduleEvent deleteCommitteeScheduleEvent) {

                boolean rulePassed = true;
                List<CommitteeSchedule> schedules = deleteCommitteeScheduleEvent.getCommitteeSchedules();
                Committee activeCommittee = committeeService.getCommitteeById(
                        ((CommitteeDocument) deleteCommitteeScheduleEvent.getDocument()).getCommittee().getCommitteeId());
                if (activeCommittee != null) {
                    int i = 0;
                    for (CommitteeSchedule schedule : schedules) {
                        if (schedule.isSelected() && canNotDelete(activeCommittee.getCommitteeSchedules(), schedule.getScheduleId())) {
                            reportError(ID + i + "].selected", KeyConstants.ERROR_COMMITTEESCHEDULE_DELETE);
                            rulePassed = false;
                        }
                        i++;
                    }
                }
                return rulePassed;
            }
            
            /*
             * check if the matching schedule contain meeting data which also include whether protocol submitted to this schedule.
             */
            private boolean canNotDelete(List<CommitteeSchedule> schedules, String scheduleId) {
                for (CommitteeSchedule committeeSchedule : schedules) {
                    if (StringUtils.equals(committeeSchedule.getScheduleId(), scheduleId)) {
                        return isNotEmptyData(committeeSchedule);
                    }
                }
                return false;
            }

            /*
             * check if there is any meeting data in this schedule.
             */
            private boolean isNotEmptyData(CommitteeSchedule schedule) {
                return CollectionUtils.isNotEmpty(schedule.getCommitteeScheduleAttendances())
                        || CollectionUtils.isNotEmpty(schedule.getCommitteeScheduleMinutes())
                        || CollectionUtils.isNotEmpty(schedule.getCommScheduleActItems())
                        || CollectionUtils.isNotEmpty(schedule.getMinuteDocs())
                        || CollectionUtils.isNotEmpty(schedule.getScheduleAgendas())
                        || CollectionUtils.isNotEmpty(schedule.getProtocolSubmissions());

            }


        };
    }
    
    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }
    
    /**
     * Test delete committee schedule.
     */
    @Test
    public void testDeleteCommitteeScheduleOk() throws Exception {

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "test");
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues);
            will(returnValue(getCommittees(false)));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        CommitteeDocument document  = new CommitteeDocument();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        List<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        document.setCommitteeList(committees);
        DeleteCommitteeScheduleEvent event = new DeleteCommitteeScheduleEvent(Constants.EMPTY_STRING, document, null, getCommitteeSchedules(false), ErrorType.HARDERROR);
        Assert.assertTrue(rule.processRules(event));

    }
    
    @Test
    public void testDeleteCommitteeSchedule() throws Exception {

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "test");
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues);
            will(returnValue(getCommittees(true)));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        CommitteeDocument document  = new CommitteeDocument();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        List<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        document.setCommitteeList(committees);
        DeleteCommitteeScheduleEvent event = new DeleteCommitteeScheduleEvent(Constants.EMPTY_STRING, document, null, getCommitteeSchedules(false), ErrorType.HARDERROR);
        Assert.assertFalse(rule.processRules(event));

        
        assertError("document.committeeList[0].committeeSchedules[0].selected", KeyConstants.ERROR_COMMITTEESCHEDULE_DELETE);
    }

    private List<Committee> getCommittees(boolean containMeetingData) {
        List<Committee> committees = new ArrayList<Committee>();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        committee.setSequenceNumber(1);
        committee.setCommitteeSchedules(getCommitteeSchedules(containMeetingData));
        committees.add(committee);
        return committees;
       
    }
    
    protected void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getErrorKey(), errorKey);
    }

    private List<CommitteeSchedule> getCommitteeSchedules(boolean containMeetingData) {
        List<CommitteeSchedule> committeeSchedules = new ArrayList<CommitteeSchedule>();    
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedules.add(committeeSchedule);
        committeeSchedule.setSelected(true);
        if (containMeetingData) {
           committeeSchedule.setProtocolSubmissions(getProtocolSubmissions());
        }
        return committeeSchedules;
    }
    
    private List<ProtocolSubmission> getProtocolSubmissions() {
        List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolOnlineReview review = new ProtocolOnlineReview();  
        ProtocolReviewer reviewer = new ProtocolReviewer();
        reviewer.setPersonId("100");
        review.setProtocolReviewer(reviewer);
        reviews.add(review);
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocolOnlineReviews(reviews);
        submissions.add(submission);
        return submissions;
    }


}
