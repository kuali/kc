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
package org.kuali.kra.irb.actions.reviewcomments;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.DateTimeService;

public class ReviewCommentsServiceTest extends KcUnitTestBase {
    
    private static final String FIRST_COMMENT = "First Review Comment";
    private static final String SECOND_COMMENT = "Second Review Comment";
    private static final String THIRD_COMMENT = "Third Review Comment";
    
    private ReviewCommentsServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new ReviewCommentsServiceImpl();
        service.setDateTimeService(getMockDateTimeService());
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        
        super.tearDown();
    }
    
    @Test
    public void testAddReviewComment() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        
        service.addReviewComment(firstNewReviewComment, reviewComments, protocolDocument.getProtocol());
        
        assertEquals(1, reviewComments.size());
        CommitteeScheduleMinute firstReviewComment = reviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        
        service.addReviewComment(secondNewReviewComment, reviewComments, protocolDocument.getProtocol());
        
        assertEquals(2, reviewComments.size());
        CommitteeScheduleMinute secondReviewComment = reviewComments.get(1);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
    }
    
    @Test
    public void testMoveUpReviewComment() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setProtocol(protocolDocument.getProtocol());
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        reviewComments.add(secondNewReviewComment);
        
        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setProtocol(protocolDocument.getProtocol());
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        reviewComments.add(thirdNewReviewComment);
        
        service.moveUpReviewComment(reviewComments, protocolDocument.getProtocol(), 2);
        
        assertEquals(3, reviewComments.size());
        
        CommitteeScheduleMinute thirdReviewComment = reviewComments.get(0);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute firstReviewComment = reviewComments.get(1);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute secondReviewComment = reviewComments.get(2);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(2), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
    }
    
    @Test
    public void testMoveDownReviewComment() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setProtocol(protocolDocument.getProtocol());
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        reviewComments.add(secondNewReviewComment);
        
        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setProtocol(protocolDocument.getProtocol());
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        reviewComments.add(thirdNewReviewComment);
        
        service.moveDownReviewComment(reviewComments, protocolDocument.getProtocol(), 0);
        
        assertEquals(3, reviewComments.size());
        
        CommitteeScheduleMinute secondReviewComment = reviewComments.get(0);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute thirdReviewComment = reviewComments.get(1);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute firstReviewComment = reviewComments.get(2);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(2), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
    }
    
    @Test
    public void testDeleteReviewComment() throws Exception {
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        List<CommitteeScheduleMinute> deletedReviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        reviewComments.add(secondNewReviewComment);
        
        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setCommScheduleMinutesId(3L);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        reviewComments.add(thirdNewReviewComment);
        
        service.deleteReviewComment(reviewComments, 0, deletedReviewComments);
        
        assertEquals(2, reviewComments.size());
        
        CommitteeScheduleMinute secondReviewComment = reviewComments.get(0);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute thirdReviewComment = reviewComments.get(1);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
        
        assertEquals(1, deletedReviewComments.size());
        
        CommitteeScheduleMinute firstReviewComment = deletedReviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
    }
    
    @Test
    public void testDeleteAllReviewComment() throws Exception {
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        List<CommitteeScheduleMinute> deletedReviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        reviewComments.add(secondNewReviewComment);
        
        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setCommScheduleMinutesId(3L);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        reviewComments.add(thirdNewReviewComment);
        
        service.deleteAllReviewComments(reviewComments, deletedReviewComments);
        
        assertEquals(0, reviewComments.size());
        assertEquals(3, deletedReviewComments.size());
        
        CommitteeScheduleMinute firstReviewComment = deletedReviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute secondReviewComment = deletedReviewComments.get(1);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
        
        CommitteeScheduleMinute thirdReviewComment = deletedReviewComments.get(2);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
    }
    
    private DateTimeService getMockDateTimeService() {
        final DateTimeService service = context.mock(DateTimeService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getCurrentTimestamp();
            will(returnValue(new Timestamp(System.currentTimeMillis())));
        }});
        
        return service;
    }

}