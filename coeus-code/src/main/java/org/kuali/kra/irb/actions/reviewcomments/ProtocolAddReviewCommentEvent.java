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

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.actions.reviewcomments.ProtocolAddReviewCommentEventBase;
import org.kuali.kra.protocol.actions.reviewcomments.ProtocolAddReviewCommentRuleBase;

/**
 * Encapsulates a validation event for a Reviewer Comment add action.
 */

@SuppressWarnings("unchecked")
public class ProtocolAddReviewCommentEvent extends ProtocolAddReviewCommentEventBase {

    public ProtocolAddReviewCommentEvent(ProtocolDocument document, String propertyName, CommitteeScheduleMinute reviewComment) {
        super(document, propertyName, reviewComment);
    }

    @Override
    protected ProtocolAddReviewCommentRuleBase<?> getNewProtocolAddReviewCommentRuleInstanceHook() {
        return new ProtocolAddReviewCommentRule();
    }
}