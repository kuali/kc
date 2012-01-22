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
package org.kuali.kra.irb.actions.submit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.followup.FollowupActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProtocolActionServiceTest extends ProtocolActionServiceTestBase {


    private Mockery context;

    private Protocol protocol;

    private ProtocolActionServiceImpl protocolActionService;

    private BusinessObjectService businessObjectService;

    private ProtocolDao dao;

    @Before
    public void setUp() {
        context = new JUnit4Mockery();
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();

        businessObjectService = context.mock(BusinessObjectService.class);
        protocolActionService.setBusinessObjectService(businessObjectService);
        protocolActionService.setFollowupActionService(KraServiceLocator.getService(FollowupActionService.class));

        dao = context.mock(ProtocolDao.class);
        protocolActionService.setProtocolDao(dao);
        try {
            protocolActionService.setRuleFiles(getRuleFiles());
        } catch (Exception e) {
            
        }

        ProtocolSubmission protocolSubmission = getProtocolSubmission();
        protocolSubmission.setScheduleIdFk(1L);
        protocol.setProtocolSubmission(protocolSubmission);
        protocol.getProtocolSubmissions().add(protocolSubmission);

        protocol.setProtocolNumber("001Z");
    }

    private ProtocolSubmission getProtocolSubmission() {
        ProtocolSubmission protocolSubmission = new ProtocolSubmission() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        return protocolSubmission;

    }
    
    private ProtocolAction getProtocolAction() {
        ProtocolAction protocolAction = new ProtocolAction() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing.
            }
        };
        
        return protocolAction;
    }
    
    private void mockMinutes() {
        context.checking(new Expectations() {
            {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("protocolNumber", protocol.getProtocolNumber());
                fieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
                allowing(businessObjectService).countMatching(CommitteeScheduleMinute.class, fieldValues);
                will(returnValue(1));
            }
        });
    }

    private void mockSubmissionTrue(final String submissionTypeCode) {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());

                Map<String, Object> negativeFieldValues = new HashMap<String, Object>();
                negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);
                will(returnValue(0));
            }
        });
    }

    private void mockSubmissionFalse(final String submissionTypeCode) {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());

                Map<String, Object> negativeFieldValues = new HashMap<String, Object>();
                negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);
                will(returnValue(0));
            }
        });
    }

    private void mockSubmissionTrueCondt1() {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());

                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);
                will(returnValue(0));
            }
        });
    }

    private void mockSubmissionTrueCondt2(final String submissionTypeCode) {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
                positiveFieldValues.put("submissionTypeCode", submissionTypeCode);

                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);
                will(returnValue(0));
            }
        });
    }

    private void mockSubmissionFalseCondt2(final String submissionTypeCode) {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
                positiveFieldValues.put("submissionTypeCode", submissionTypeCode);

                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);
                will(returnValue(1));
            }
        });
    }

    private void mockSubmissionCondt3(final boolean cond) {
        // getSubmissionCountCond4
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
                positiveFieldValues.put("submissionTypeCode", Arrays.asList(new String[] {
                        ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION,
                        ProtocolSubmissionType.REQUEST_FOR_TERMINATION, ProtocolSubmissionType.REQUEST_TO_CLOSE,
                        ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT}));

                List<ProtocolSubmission> list = new ArrayList<ProtocolSubmission>();
                int retVal = 0;
                if (!cond) {
                    retVal = 1;
                }
                allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);
                will(returnValue(retVal));
            }
        });
    }
    

    private void mockSubmissionCondt5() {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                ProtocolSubmission protocolSubmission = getProtocolSubmission();

                protocolSubmission.setSubmissionStatusCode("200");
                List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
                submissions.add(protocolSubmission);
                allowing(businessObjectService).findMatchingOrderBy(ProtocolSubmission.class, positiveFieldValues,
                        "submissionNumber", false);
                will(returnValue(submissions));
            }
        });
    }

    private void mockSubmissionForWithdraw() {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                ProtocolSubmission protocolSubmission = getProtocolSubmission();

                protocolSubmission.setSubmissionStatusCode("201");
                List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
                submissions.add(protocolSubmission);
                allowing(businessObjectService).findMatchingOrderBy(ProtocolSubmission.class, positiveFieldValues,
                        "submissionNumber", false);
                will(returnValue(submissions));
            }
        });
    }

    private void mockSubmissionCondt4(final boolean flag) {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
                List<ProtocolSubmission> list = new ArrayList<ProtocolSubmission>();
                if (flag) {
                    ProtocolSubmission ps = new ProtocolSubmission();
                    ps.setScheduleId("123");
                    list.add(ps);
                }
                allowing(businessObjectService).findMatching(ProtocolSubmission.class, positiveFieldValues);
                will(returnValue(list));
            }
        });
    }

    private void mockProtocolDaoCondt1() {
        context.checking(new Expectations() {
            {
                Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
                positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
                positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
                positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());

                allowing(dao).getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());
                will(returnValue(true));
            }
        });
    }

    private List<String> getPendingSubmissionStatusCodes() {
        List<String> submissionStatusCodes = new ArrayList<String>();
        submissionStatusCodes.add(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionStatusCodes.add(ProtocolSubmissionStatus.IN_AGENDA); 
        submissionStatusCodes.add(ProtocolSubmissionStatus.PENDING);
        return submissionStatusCodes;

    }

    @Test
    public void testActionTypeCode200() {
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        protocol.getProtocolSubmission().setScheduleId("NotNULL");
        assertTrue(protocolActionService.canPerformAction("200", protocol));
    }

    @Test
    public void testActionTypeCode201() {
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("201", protocol));
    }

    @Test
    public void testActionTypeCode202() {
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("202", protocol));

        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("202", protocol));
    }

    @Test
    public void testActionTypeCode202ReviewTypeCodeNot1or2() {
        mockMinutes();

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("202", protocol));
    }

    @Test
    public void testActionTypeCode204() {
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        assertTrue(protocolActionService.canPerformAction("204", protocol));
    }

    @Test
    public void testActionTypeCode203() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("203", protocol));

        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        assertTrue(protocolActionService.canPerformAction("203", protocol));
    }

    @Test
    public void testActionTypeCode203ReviewTypeCodeNot1or2() {
        mockMinutes();

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("203", protocol));
    }

    @Test
    public void testActionTypeCode300Contd1() {
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");
        mockSubmissionTrue("109");

        protocol.setProtocolStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("102");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("104");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("105");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("106");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("304");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("300", protocol));
    }

    @Test
    public void testActionTypeCode300Contd2() {
        mockSubmissionFalse("109");

        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102");
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("102");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("104");
        assertTrue(protocolActionService.canPerformAction("300", protocol));

        protocol.setProtocolStatusCode("304");
        assertTrue(protocolActionService.canPerformAction("300", protocol));
    }

    @Test
    public void testActionTypeCode301Contd1() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("108");
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("305");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("301", protocol));
    }

    @Test
    public void testActionTypeCode301Contd2() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionFalse("108");
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102");
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("305");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("301", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("301", protocol));
    }

    @Test
    public void testActionTypeCode305() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrueCondt1();
        mockProtocolDaoCondt1();

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("304");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("310");
        assertTrue(protocolActionService.canPerformAction("305", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("305", protocol));
    }

    @Test
    public void testActionTypeCode302Contd1() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
        mockSubmissionTrue("110");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("302", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("302", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("302", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("302", protocol));
    }

    @Test
    public void testActionTypeCode302Contd2() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("201");
        mockSubmissionTrue("110");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");

        assertTrue(protocolActionService.canPerformAction("302", protocol));
    }

    @Test
    public void testActionTypeCode306Contd1() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
        mockSubmissionTrue("110");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("306", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("306", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("306", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("306", protocol));
    }

    @Test
    public void testActionTypeCode306Contd2() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("110");
        protocol.setProtocolStatusCode("201");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");

        assertTrue(protocolActionService.canPerformAction("306", protocol));
    }


    @Test
    public void testActionTypeCode303Cond1() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("303", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("303", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("102");
        assertTrue(protocolActionService.canPerformAction("303", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("303", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("303", protocol));
    }

    @Test
    public void testActionTypeCode303Cond2() {
        protocol.getProtocolSubmission().setSubmissionNumber(null); // Not null
        mockSubmissionForWithdraw();
        assertTrue(protocolActionService.canPerformAction("303", protocol));
    }

    @Test
    public void testActionTypeCode304ReviewTypeCodeNot2or3() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("304", protocol));
    }

    @Test
    public void testActionTypeCode304ReviewTypeCode2or3() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("304", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("304", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("102");
        assertTrue(protocolActionService.canPerformAction("304", protocol));

        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("304", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("304", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("102");
        assertTrue(protocolActionService.canPerformAction("304", protocol));
    }

    @Test
    public void testActionTypeCode103() {
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("103", protocol));

        // following tests changed to "assertFalse" since they are not supposed to be authorized
        protocol.setProtocolStatusCode("300");
        assertFalse(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("301");
        assertFalse(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("302");
        assertFalse(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("308");
        assertFalse(protocolActionService.canPerformAction("103", protocol));

        protocol.setProtocolStatusCode("311");
        assertFalse(protocolActionService.canPerformAction("103", protocol));
    }

    @Test
    public void testActionTypeCode102() {
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("203");
        assertFalse(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("102", protocol));

        protocol.setProtocolStatusCode("305");
        assertTrue(protocolActionService.canPerformAction("102", protocol));
    }

    @Test
    public void testActionTypeCode205ReviewTypeCode2() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null

        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolOnlineReview review = new ProtocolOnlineReview();
        ProtocolReviewer reviewer = new ProtocolReviewer();
        review.setProtocolReviewer(reviewer);
        reviews.add(review);
        protocol.getProtocolSubmission().setProtocolOnlineReviews(reviews);

        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("205", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("205", protocol));
    }

    @Test
    public void testActionTypeCode208ReviewTypeCode6() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null

        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolOnlineReview review = new ProtocolOnlineReview();
        ProtocolReviewer reviewer = new ProtocolReviewer();
        review.setProtocolReviewer(reviewer);
        reviews.add(review);
        protocol.getProtocolSubmission().setProtocolOnlineReviews(reviews);

        protocol.getProtocolSubmission().setProtocolReviewTypeCode("6");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("208", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("208", protocol));
    }

    @Test
    public void testActionTypeCode209ReviewTypeCode6() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setSubmissionTypeCode("112");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("209", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("209", protocol));
    }

    @Test
    public void testActionTypeCode206ReviewTypeCode3() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("206", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("206", protocol));
    }

    @Test
    public void testActionTypeCode210ReviewTypeCode5() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); // Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("5");
        protocol.setProtocolStatusCode("101");

        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        assertTrue(protocolActionService.canPerformAction("210", protocol));

        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("210", protocol));
    }

    @Test
    public void testActionTypeCode104True() {

        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
        // mockSubmissionTrueCondt2("109");
        // mockSubmissionTrueCondt2("110");
        // mockSubmissionTrueCondt2("111");
        // mockSubmissionTrueCondt2("108");
        // mockSubmissionTrueCondt2("113");
        // mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("104", protocol));
    }

    @Test
    public void testActionTypeCode104False() {
        mockSubmissionCondt3(false);
        mockSubmissionCondt5();
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("201");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("202");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("203");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("300");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("301");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("302");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("308");
        assertFalse(protocolActionService.canPerformAction("104", protocol));

        protocol.setProtocolStatusCode("311");
        assertFalse(protocolActionService.canPerformAction("104", protocol));
    }

    @Test
    public void testActionTypeCode105True() {
        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
//        mockSubmissionTrueCondt2("109");
//        mockSubmissionTrueCondt2("110");
//        mockSubmissionTrueCondt2("111");
//        mockSubmissionTrueCondt2("108");
//        mockSubmissionTrueCondt2("113");
//        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("302");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("308");
        assertTrue(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("311");
        assertTrue(protocolActionService.canPerformAction("105", protocol));
    }

    @Test
    public void testActionTypeCode105False() {
        mockSubmissionCondt3(false);
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("201");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("202");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("203");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("302");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("308");
        assertFalse(protocolActionService.canPerformAction("105", protocol));

        protocol.setProtocolStatusCode("311");
        assertFalse(protocolActionService.canPerformAction("105", protocol));
    }

    @Test
    public void testActionTypeCode106True() {
        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
//        mockSubmissionTrueCondt2("109");
//        mockSubmissionTrueCondt2("110");
//        mockSubmissionTrueCondt2("111");
//        mockSubmissionTrueCondt2("108");
//        mockSubmissionTrueCondt2("113");
//        mockSubmissionTrueCondt2("114");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("106", protocol));
    }

    @Test
    public void testActionTypeCode106False() {
        mockSubmissionCondt3(false);
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertFalse(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("201");
        assertFalse(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("202");
        assertFalse(protocolActionService.canPerformAction("106", protocol));

        protocol.setProtocolStatusCode("203");
        assertFalse(protocolActionService.canPerformAction("106", protocol));
    }

    @Test
    public void testActionTypeCode108True() {
        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
//        mockSubmissionTrueCondt2("109");
//        mockSubmissionTrueCondt2("110");
//        mockSubmissionTrueCondt2("111");
//        mockSubmissionTrueCondt2("108");
//        mockSubmissionTrueCondt2("113");
//        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("108", protocol));
    }

    @Test
    public void testActionTypeCode108False() {
        mockSubmissionCondt3(false);
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertFalse(protocolActionService.canPerformAction("108", protocol));
    }

    @Test
    public void testActionTypeCode207Cond1True() {
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        mockSubmissionTrue("111");
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("207", protocol));
    }

    @Test
    public void testActionTypeCode207Cond2() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210");
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        mockSubmissionTrue("111");
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("207", protocol));
    }

    // this condition is commented in rule file
//    @Test
//    public void testActionTypeCode109Cond1() {
//        protocol.setSequenceNumber(123);
//        protocol.getProtocolSubmission().setSubmissionNumber(123);
//        mockSubmissionCondt4(true);
//        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
//        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");
//        assertTrue(protocolActionService.canPerformAction("109", protocol));
//
//        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
//        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");
//        assertTrue(protocolActionService.canPerformAction("109", protocol));
//    }

    @Test
    public void testActionTypeCode109Cond2() {
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionCondt4(false);
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
       // protocol.getProtocolSubmission().setScheduleIdFk(null);
        assertTrue(protocolActionService.canPerformAction("109", protocol));
    }

    @Test
    public void testActionTypeCode999() {
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        assertTrue(protocolActionService.canPerformAction("999", protocol));
    }

    @Test
    public void testActionTypeCode114True() {
        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
//        mockSubmissionTrueCondt2("109");
//        mockSubmissionTrueCondt2("110");
//        mockSubmissionTrueCondt2("111");
//        mockSubmissionTrueCondt2("108");
//        mockSubmissionTrueCondt2("113");
//        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("114", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("114", protocol));
    }

    @Test
    public void testActionTypeCode114False() {
        mockSubmissionCondt3(false);
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");
        assertFalse(protocolActionService.canPerformAction("114", protocol));

        protocol.setProtocolStatusCode("201");
        assertFalse(protocolActionService.canPerformAction("114", protocol));
    }

    @Test
    public void testActionTypeCode115True() {
        mockSubmissionCondt3(true);
        mockSubmissionCondt5();
//        mockSubmissionTrueCondt2("109");
//        mockSubmissionTrueCondt2("110");
//        mockSubmissionTrueCondt2("111");
//        mockSubmissionTrueCondt2("108");
//        mockSubmissionTrueCondt2("113");
//        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("115", protocol));
    }

    @Test
    public void testActionTypeCode115False() {
        mockSubmissionCondt3(false);
//        mockSubmissionFalseCondt2("109");
//        mockSubmissionFalseCondt2("110");
//        mockSubmissionFalseCondt2("111");
//        mockSubmissionFalseCondt2("108");
//        mockSubmissionFalseCondt2("113");
//        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("201");
        assertFalse(protocolActionService.canPerformAction("115", protocol));
    }

    @Test
    public void testActionTypeCode211Cond1() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210");
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        mockSubmissionTrue("113");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("211", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("211", protocol));
    }

    @Test
    public void testActionTypeCode211Cond2() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        mockSubmissionTrue("113");

        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("211", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("211", protocol));
    }

    @Test
    public void testActionTypeCode212Cond1() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210");
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        mockSubmissionTrue("114");

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("212", protocol));
    }

    @Test
    public void testActionTypeCode212Cond2() {
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("201");
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        mockSubmissionTrue("114");
        assertTrue(protocolActionService.canPerformAction("212", protocol));
    }

    @Test
    public void testActionTypeCode116() {
        protocol.setProtocolStatusCode("200");
        assertTrue(protocolActionService.canPerformAction("116", protocol));

        protocol.setProtocolStatusCode("201");
        assertTrue(protocolActionService.canPerformAction("116", protocol));

        protocol.setProtocolStatusCode("202");
        assertTrue(protocolActionService.canPerformAction("116", protocol));

        protocol.setProtocolStatusCode("203");
        assertTrue(protocolActionService.canPerformAction("116", protocol));

        protocol.setProtocolStatusCode("300");
        assertTrue(protocolActionService.canPerformAction("116", protocol));

        protocol.setProtocolStatusCode("301");
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
         protocol.setProtocolStatusCode("302");
         assertTrue(protocolActionService.canPerformAction("116", protocol));

         protocol.setProtocolStatusCode("304");
         assertTrue(protocolActionService.canPerformAction("116", protocol));

         protocol.setProtocolStatusCode("305");
         assertTrue(protocolActionService.canPerformAction("116", protocol));

         protocol.setProtocolStatusCode("306");
         assertTrue(protocolActionService.canPerformAction("116", protocol));

         protocol.setProtocolStatusCode("307");
         assertTrue(protocolActionService.canPerformAction("116", protocol));

         protocol.setProtocolStatusCode("308");
         assertTrue(protocolActionService.canPerformAction("116", protocol));
         protocol.setProtocolStatusCode("311");
         assertTrue(protocolActionService.canPerformAction("116", protocol));
    }

    /**
     * 
     * This method is for abandon condition 1
     */
    @Test
    public void testActionTypeCode119Cond1() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("202");

        protocol.setProtocolStatusCode("102");
        ProtocolPerson person = new ProtocolPerson();
        person.setPersonId("10000000001");
        person.setProtocolPersonRoleId("PI");
        protocol.getProtocolPersons().add(person);

        assertTrue(protocolActionService.canPerformAction("119", protocol));
    }

    /**
     * 
     * This method is for abandon condition 2
     */
    @Test
    public void testActionTypeCode119Cond2() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("201");

        protocol.setProtocolStatusCode("104");
        ProtocolPerson person = new ProtocolPerson();
        person.setPersonId("10000000001");
        person.setProtocolPersonRoleId("PI");
        protocol.getProtocolPersons().add(person);
        assertTrue(protocolActionService.canPerformAction("119", protocol));
    }


    private void createNewDefaultProtocolAction(String protocolActionTypeCode,String committeeDecisionMotionTypeCode) {
        ProtocolAction protocolAction = getProtocolAction();
        protocolAction.setActionId(1);
        protocolAction.setActionDate(new Timestamp(System.currentTimeMillis()));
        protocolAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        protocolAction.setProtocol(protocol);
        protocolAction.setSubmissionNumber(protocol.getProtocolSubmission().getSequenceNumber());
        protocolAction.setProtocolActionTypeCode(protocolActionTypeCode);
        protocol.getProtocolSubmission().setSubmissionId(1L);
        protocolAction.setSubmissionIdFk(protocol.getProtocolSubmission().getSubmissionId());
        protocolAction.setProtocolSubmission(protocol.getProtocolSubmission());
        protocolAction.getProtocolSubmission().setCommitteeDecisionMotionTypeCode(committeeDecisionMotionTypeCode);
        protocol.getProtocolActions().add(protocolAction);
        protocolAction.setActionDate(new java.sql.Timestamp( (new java.util.Date()).getTime() ));
    }
    
    @Test
    public void testIsApproveActionOpenForFollowup() {
        createNewDefaultProtocolAction(ProtocolActionType.RECORD_COMMITTEE_DECISION,CommitteeDecisionMotionType.APPROVE);
        assertTrue(protocolActionService.isActionOpenForFollowup("204", protocol));
    }
    
    @Test
    public void testIsDisapproveActionOpenForFollowup() {
        createNewDefaultProtocolAction(ProtocolActionType.RECORD_COMMITTEE_DECISION,CommitteeDecisionMotionType.DISAPPROVE);
        assertTrue(protocolActionService.isActionOpenForFollowup("304", protocol));
    }
    
    @Test
    public void testIsReturnForSMRActionOpenForFollowup() {
        createNewDefaultProtocolAction(ProtocolActionType.RECORD_COMMITTEE_DECISION,CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
        assertTrue(protocolActionService.isActionOpenForFollowup("203", protocol));
    }
    
    @Test
    public void testIsReturnForSRRActionOpenForFollowup() {
        createNewDefaultProtocolAction(ProtocolActionType.RECORD_COMMITTEE_DECISION,CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
        assertTrue(protocolActionService.isActionOpenForFollowup("202", protocol));
    }
    
    private List<String> getRuleFiles() {
        List<String>ruleFiles = new ArrayList<String>();
        ruleFiles.add("org/kuali/kra/irb/drools/rules/permissionForLeadUnitRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/permissionToSubmitRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/permissionToCommitteeMemberRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/permissionForSpecialRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/canPerformProtocolActionRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/updateProtocolRules.drl");
        ruleFiles.add("org/kuali/kra/irb/drools/rules/undoProtocolUpdateRules.drl");
        return ruleFiles;
    }
}
