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
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.protocol.actions.reviewcomments.ProtocolAddReviewAttachmentEventBase;

/**
 * 
 * This class is validate the new review attachment when 'add' is clicked
 */
@SuppressWarnings("unchecked")
public class ProtocolAddReviewAttachmentEvent extends ProtocolAddReviewAttachmentEventBase<ProtocolReviewAttachment> {

    public ProtocolAddReviewAttachmentEvent(ProtocolDocument document, String propertyName, ProtocolReviewAttachment reviewAttachment) {
        super(document, propertyName, reviewAttachment);
    }

    @Override
    protected ProtocolAddReviewAttachmentRule getNewProtocolAddReviewAttachmentRuleInstancehook() {
        return new ProtocolAddReviewAttachmentRule();
    }
}
