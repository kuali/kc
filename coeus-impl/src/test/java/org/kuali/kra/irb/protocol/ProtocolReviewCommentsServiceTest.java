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
package org.kuali.kra.irb.protocol;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsServiceImpl;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProtocolReviewCommentsServiceTest {


    private static final String FIRST_COMMENT = "First Review Comment";
    private static final String SECOND_COMMENT = "Second Review Comment";
    private static final String THIRD_COMMENT = "Third Review Comment";
    private static final Long PROTOCOL_ONLINE_REVIEW_FK_ID = new Long("12514314361461436");

    private ReviewCommentsServiceImpl service;

    @Before
    public void setUp() throws Exception {

        service = new ReviewCommentsServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        service = null;

    }

    @Test
    public void testMoveUpReviewComment() throws Exception {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}


        };
        protocol.setProtocolId(1L);

        List<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setProtocol(protocol);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);

        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);

        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setProtocol(protocol);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        thirdNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(thirdNewReviewComment);

        service.moveUpReviewComment(reviewComments, protocol, 2);

        assertEquals(3, reviewComments.size());

        CommitteeScheduleMinute firstReviewComment = (CommitteeScheduleMinute) reviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());

        CommitteeScheduleMinute thirdReviewComment = (CommitteeScheduleMinute) reviewComments.get(1);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());

        CommitteeScheduleMinute secondReviewComment = (CommitteeScheduleMinute) reviewComments.get(2);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(2), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
    }

    @Test
    public void testMoveDownReviewComment() throws Exception {
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}


        };
        protocol.setProtocolId(1L);

        List<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setProtocol(protocol);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);

        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);

        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setProtocol(protocol);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        thirdNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(thirdNewReviewComment);

        service.moveDownReviewComment(reviewComments, protocol, 0);

        assertEquals(3, reviewComments.size());

        CommitteeScheduleMinute secondReviewComment = (CommitteeScheduleMinute) reviewComments.get(0);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());

        CommitteeScheduleMinute firstReviewComment = (CommitteeScheduleMinute) reviewComments.get(1);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());

        CommitteeScheduleMinute thirdReviewComment = (CommitteeScheduleMinute) reviewComments.get(2);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(2), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
    }

    @Test
    public void testDeleteReviewComment() throws Exception {
        List<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
        List<CommitteeScheduleMinuteBase> deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setEntryNumber(0);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);

        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setEntryNumber(1);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);

        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setCommScheduleMinutesId(3L);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setEntryNumber(2);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        thirdNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(thirdNewReviewComment);

        service.deleteReviewComment(reviewComments, 0, deletedReviewComments);

        assertEquals(2, reviewComments.size());

        CommitteeScheduleMinute secondReviewComment = (CommitteeScheduleMinute) reviewComments.get(0);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());

        CommitteeScheduleMinute thirdReviewComment = (CommitteeScheduleMinute) reviewComments.get(1);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), thirdReviewComment.getEntryNumber());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());

        assertEquals(1, deletedReviewComments.size());

        CommitteeScheduleMinute firstReviewComment = (CommitteeScheduleMinute) deletedReviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());
    }

    @Test
    public void testDeleteAllReviewComment() throws Exception {
        List<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
        List<CommitteeScheduleMinuteBase> deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);

        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);

        CommitteeScheduleMinute thirdNewReviewComment = new CommitteeScheduleMinute();
        thirdNewReviewComment.setCommScheduleMinutesId(3L);
        thirdNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        thirdNewReviewComment.setMinuteEntry(THIRD_COMMENT);
        thirdNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(thirdNewReviewComment);

        service.deleteAllReviewComments(reviewComments, deletedReviewComments);

        assertEquals(0, reviewComments.size());
        assertEquals(3, deletedReviewComments.size());

        CommitteeScheduleMinute firstReviewComment = (CommitteeScheduleMinute) deletedReviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());

        CommitteeScheduleMinute secondReviewComment = (CommitteeScheduleMinute) deletedReviewComments.get(1);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.ACTION_ITEM, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());

        CommitteeScheduleMinute thirdReviewComment = (CommitteeScheduleMinute) deletedReviewComments.get(2);
        assertNotNull(thirdReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, thirdReviewComment.getMinuteEntryTypeCode());
        assertEquals(THIRD_COMMENT, thirdReviewComment.getMinuteEntry());
    }
}