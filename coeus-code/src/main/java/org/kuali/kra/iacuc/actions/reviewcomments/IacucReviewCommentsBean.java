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
package org.kuali.kra.iacuc.actions.reviewcomments;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;

public class IacucReviewCommentsBean extends ReviewCommentsBeanBase {


    private static final long serialVersionUID = -384690927668253611L;
    

    public IacucReviewCommentsBean(String errorPropertyKey) {
        super(errorPropertyKey);
    }


    @Override
    protected Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return IacucReviewCommentsService.class;
    }


    @Override
    protected CommitteeScheduleMinuteBase getNewCommitteeScheduleMinuteInstanceHook() {
        return new IacucCommitteeScheduleMinute();
    }

}
