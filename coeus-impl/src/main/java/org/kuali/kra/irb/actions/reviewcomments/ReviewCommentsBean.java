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
package org.kuali.kra.irb.actions.reviewcomments;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;

/**
 * This class defines functions that need to be implemented in a "bean" that needs to provide support for reviewer comments.
 */
public class ReviewCommentsBean extends ReviewCommentsBeanBase {

    private static final long serialVersionUID = 1020677422739490270L;

    public ReviewCommentsBean(String errorPropertyKey) {
        super(errorPropertyKey);
    }

    @Override
    protected CommitteeScheduleMinuteBase getNewCommitteeScheduleMinuteInstanceHook() {
        return new CommitteeScheduleMinute();
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return ReviewCommentsService.class;
    }
}
