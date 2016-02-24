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
