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
