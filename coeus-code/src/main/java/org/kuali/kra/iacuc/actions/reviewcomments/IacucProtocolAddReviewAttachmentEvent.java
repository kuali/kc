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

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.protocol.actions.reviewcomments.ProtocolAddReviewAttachmentEventBase;

public class IacucProtocolAddReviewAttachmentEvent extends ProtocolAddReviewAttachmentEventBase<IacucProtocolReviewAttachment> {

    public IacucProtocolAddReviewAttachmentEvent(IacucProtocolDocument document, String propertyName, IacucProtocolReviewAttachment reviewAttachment) {
        super(document, propertyName, reviewAttachment);
    }

    @Override
    protected IacucProtocolAddReviewAttachmentRule getNewProtocolAddReviewAttachmentRuleInstancehook() {
        return new IacucProtocolAddReviewAttachmentRule();        
    }

}
