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
package org.kuali.kra.iacuc.onlinereview;

import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewAttachmentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewFormBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;

public class IacucOnlineReviewsActionHelper  extends  OnlineReviewsActionHelperBase {
   

    private static final long serialVersionUID = 8937646851450759832L;
    

    public IacucOnlineReviewsActionHelper(IacucProtocolForm form) {
        super(form);
    }

    
    @Override
    protected ReviewAttachmentsBeanBase getNewReviewAttachmentsBeanHook(String errorPropertyKey) {
        return new IacucReviewAttachmentsBean(errorPropertyKey);
    }

    @Override
    protected ReviewCommentsBeanBase getNewReviewCommentsBeanInstanceHook(String errorPropertyKey) {
        return new IacucReviewCommentsBean(errorPropertyKey);
    }


    @Override
    protected ProtocolOnlineReviewFormBase getNewProtocolOnlineReviewFormInstanceHook() throws Exception {
        return new IacucProtocolOnlineReviewForm();
    }


    @Override
    protected Class<? extends ProtocolOnlineReviewService> getProtocolOnlineReviewServiceClassHook() {
        return IacucProtocolOnlineReviewService.class;
    }


    @Override
    protected Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return IacucReviewCommentsService.class;
    }


}
