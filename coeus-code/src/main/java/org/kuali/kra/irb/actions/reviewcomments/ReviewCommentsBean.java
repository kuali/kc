/*
 * Copyright 2005-2014 The Kuali Foundation
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