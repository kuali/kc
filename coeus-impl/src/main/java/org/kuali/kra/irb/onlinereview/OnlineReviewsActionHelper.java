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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewFormBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;


public class OnlineReviewsActionHelper extends  OnlineReviewsActionHelperBase {


    private static final long serialVersionUID = 5629826686189661976L;

    public OnlineReviewsActionHelper(ProtocolForm form) {
        super(form);
    }

    @Override
    protected ProtocolOnlineReviewFormBase getNewProtocolOnlineReviewFormInstanceHook() throws Exception {
        return new ProtocolOnlineReviewForm();
    }

    @Override
    protected ReviewAttachmentsBeanBase<ProtocolReviewAttachment> getNewReviewAttachmentsBeanHook(String errorPropertyKey) {
        return new ReviewAttachmentsBean(errorPropertyKey);
    }

    @Override
    protected ReviewCommentsBeanBase getNewReviewCommentsBeanInstanceHook(String errorPropertyKey) {
        return new ReviewCommentsBean(errorPropertyKey);
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewService> getProtocolOnlineReviewServiceClassHook() {
        return org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService.class;
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService<ProtocolReviewAttachment>> getReviewCommentsServiceClassHook() {
        return ReviewCommentsService.class;
    }    
}

