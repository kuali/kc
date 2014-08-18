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
package org.kuali.kra.irb.onlinereview;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewLookupableHelperServiceImplBase;

public class ProtocolOnlineReviewLookupableHelperServiceImpl extends ProtocolOnlineReviewLookupableHelperServiceImplBase {


    private static final long serialVersionUID = -8740814934249836927L;

    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return ProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "ProtocolOnlineReviewDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "protocolOnlineReviewRedirect.do";
    }
    
    @Override
    protected String getProtocolSubmissionApprovedStatusCodeHook() {
        return ProtocolSubmissionStatus.APPROVED;
    } 
    
    protected String getProtocolSubmissionExemptdStatusCodeHook() {
        return ProtocolSubmissionStatus.EXEMPT;
    }
    
    protected String getProtocolSubmissionReturnedToPIStatusCodeHook() {
        return ProtocolSubmissionStatus.RETURNED_TO_PI;
    }
    
    protected String getProtocolSubmissionMajorRevisionStatusCodeHook() {
        return ProtocolSubmissionStatus.SUBSTANTIVE_REVISIONS_REQUIRED;
    }
    
    protected String getProtocolSubmissionMinorRevisionStatusCodeHook() {
        return ProtocolSubmissionStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED;
    }
    
    protected String getProtocolSubmissionInAgendaStatusCodeHook() {
        return ProtocolSubmissionStatus.IN_AGENDA;
    }
    
    protected String getProtocolSubmissionSubmittedToCommitteeStatusCodeHook() {
        return ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE;
    }
    
    @Override
    protected List<ProtocolOnlineReviewBase> filterResults(List<ProtocolOnlineReviewBase> results) {
      List<ProtocolOnlineReviewBase> onlineReviews = new ArrayList<ProtocolOnlineReviewBase>();
      for (ProtocolOnlineReviewBase review : results) {
          if (review.getProtocolOnlineReviewDocument() != null) {
          //ensure that only pending submission statuses are shown for online reviews, i.e. do not show reviews assigned but not completed for approved protocols.
              
              //ensure reviewers continue to see reviews that have been completed, accepted, and approved.
              //reviewer_approved & admin_accepted & protocol_was_approved(final) & is_an_action_that_generates_an_online_review
              if (review.isReviewerApproved() & review.isAdminAccepted() &
                  review.getProtocolOnlineReviewStatusCode().equalsIgnoreCase(ProtocolOnlineReviewStatus.FINAL_STATUS_CD) &
                  (review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionApprovedStatusCodeHook())      ||
                   review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionExemptdStatusCodeHook())       ||
                   review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionMinorRevisionStatusCodeHook()) ||
                   review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionMajorRevisionStatusCodeHook()) ||
                   review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionReturnedToPIStatusCodeHook()))) {
                  onlineReviews.add(review);
              }
              else if ((review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionInAgendaStatusCodeHook()) ||
                        review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionSubmittedToCommitteeStatusCodeHook())) &
                        !(review.getProtocolOnlineReviewStatusCode().equalsIgnoreCase(ProtocolOnlineReviewStatus.FINAL_STATUS_CD)) ) {
                  onlineReviews.add(review);
              }
          }
      }
      return onlineReviews;
    }
}
