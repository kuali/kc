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
