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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewLookupableHelperServiceImplBase;

public class IacucProtocolOnlineReviewLookupableHelperServiceImpl extends ProtocolOnlineReviewLookupableHelperServiceImplBase {

    private static final long serialVersionUID = 6830796311898825327L;
    
    @Override
    protected String getDocumentTypeName() {
        return "IacucProtocolOnlineReviewDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "iacucProtocolOnlineReviewRedirect.do";
    }

    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }
    
    @Override
    protected String getProtocolSubmissionApprovedStatusCodeHook() {
        return IacucProtocolSubmissionStatus.APPROVED;
    }
    
    private String getProtocolSubmissionAdminApprovedStatusCodeHook() {
        return IacucProtocolSubmissionStatus.ADMINISTRATIVELY_APPROVED;
    }
    
    protected List<ProtocolOnlineReviewBase> filterResults(List<ProtocolOnlineReviewBase> results) {
        List<ProtocolOnlineReviewBase> onlineReviews = new ArrayList<ProtocolOnlineReviewBase>();
        for (ProtocolOnlineReviewBase review : results) {           
            if (review.getProtocolOnlineReviewDocument() != null) {
                //ensure that only pending submission statuses are shown for online reviews, i.e. do not show reviews assigned but not completed for approved protocols.
               if (!(review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionApprovedStatusCodeHook()) || 
                     review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionAdminApprovedStatusCodeHook()))) {
                   onlineReviews.add(review);
               }
            }
        }
        return onlineReviews;
    }
}
