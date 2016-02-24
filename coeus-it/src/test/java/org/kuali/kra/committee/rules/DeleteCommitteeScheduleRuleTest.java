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
package org.kuali.kra.committee.rules;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.DeleteCommitteeScheduleEvent;
import org.kuali.kra.committee.service.impl.CommitteeServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteCommitteeScheduleRuleTest extends CommitteeRuleTestBase {
	
	private static final String COMMITTEE_DOCUMENT_STATUS_CODE = "committeeDocument.docStatusCode";
	
    private DeleteCommitteeScheduleRule rule;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
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
            public boolean processRules(DeleteCommitteeScheduleEventBase deleteCommitteeScheduleEvent) {

                boolean rulePassed = true;
                List<CommitteeScheduleBase> schedules = deleteCommitteeScheduleEvent.getCommitteeSchedules();
                Committee activeCommittee = committeeService.getCommitteeById(
                        ((CommitteeDocument) deleteCommitteeScheduleEvent.getDocument()).getCommittee().getCommitteeId());
                if (activeCommittee != null) {
                    int i = 0;
                    for (CommitteeScheduleBase schedule : schedules) {
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
        fieldValues.put(COMMITTEE_DOCUMENT_STATUS_CODE, KewApiConstants.ROUTE_HEADER_FINAL_CD);
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
        fieldValues.put(COMMITTEE_DOCUMENT_STATUS_CODE, KewApiConstants.ROUTE_HEADER_FINAL_CD);
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
        committee.setCommitteeSchedules((List)getCommitteeSchedules(containMeetingData));
        committees.add(committee);
        return committees;
       
    }
    
    protected void assertError(String propertyKey, String errorKey) {
        List errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getErrorKey(), errorKey);
    }

    private List<CommitteeScheduleBase> getCommitteeSchedules(boolean containMeetingData) {
        List<CommitteeScheduleBase> committeeSchedules = new ArrayList<CommitteeScheduleBase>();    
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedules.add(committeeSchedule);
        committeeSchedule.setSelected(true);
        if (containMeetingData) {
           committeeSchedule.setProtocolSubmissions(getProtocolSubmissions());
        }
        return committeeSchedules;
    }
    
    private List<ProtocolSubmissionLite> getProtocolSubmissions() {
        List<ProtocolSubmissionLite> submissions = new ArrayList<>();
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolOnlineReview review = new ProtocolOnlineReview();  
        ProtocolReviewer reviewer = new ProtocolReviewer();
        reviewer.setPersonId("100");
        review.setProtocolReviewer(reviewer);
        reviews.add(review);
        ProtocolSubmissionLite submission = new ProtocolSubmissionLite();
        submissions.add(submission);
        return submissions;
    }


}
