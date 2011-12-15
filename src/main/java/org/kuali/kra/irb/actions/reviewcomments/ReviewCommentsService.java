/*
 * Copyright 2005-2010 The Kuali Foundation
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


import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.irb.onlinereview.ProtocolReviewable;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

public interface ReviewCommentsService {
    
    /**
     * Determines whether the given principal can view the list of online reviewer comments for the given protocol submission.
     * 
     * This is true when 
     *   1) The principal is an IRB Administrator
     *   2) The principal is an online reviewer of the given protocol submission
     *   3) The protocol submission processing has been completed
     * @param principalId the id of the user
     * @param protocolSubmission the protocol submission
     * @return true if the principal can view the list of online reviewer comments for the given protocol submission, false otherwise
     */
    boolean canViewOnlineReviewerComments(String principalId, ProtocolSubmission protocolSubmission);
    
    /**
     * Determines whether the given principal can view the list of online reviewers for the given protocol submission.
     * 
     * This is true when the principal is an IRB Administrator or an online reviewer of the given protocol submission.
     * @param principalId the id of the user
     * @param protocolSubmission the protocol submission
     * @return true if the principal can view the list of online reviewers, false otherwise
     */
    boolean canViewOnlineReviewers(String principalId, ProtocolSubmission protocolSubmission);    
    
    /**
     * Finds and returns the reviewer comments for a protocol number and a certain submission.
     * @param protocolNumber The protocol number
     * @param submissionNumber The number of the submission
     * @return a list of reviewer comments
     */
    List<CommitteeScheduleMinute> getReviewerComments(String protocolNumber, int submissionNumber);
    
    /**
     *     
      * This method is to check whether the current user can view this comment.
      * This is true either if 
      *   1) The current user has the role IRB Administrator
      *   2) The current user does not have the role IRB Administrator, but the current user is the comment creator
      *   3) The current user does not have the role IRB Administrator, the current user is not the comment creator, but the comment is public and final
      * @param CommitteeScheduleMinute minute
     *  @return whether the current user can view this comment
     */
    boolean getReviewerCommentsView(ProtocolReviewable minute);
    
    /**
     * 
     * This method is to get a list or protocol reviewers for this submission.
     * @param protocolNumber
     * @param submissionNumber
     * @return
     */
    List<ProtocolReviewer> getProtocolReviewers(String protocolNumber, int submissionNumber);
    
    /**
     * Adds the newReviewComment to the list of reviewComments in the given protocol.
     * @param newReviewComment the new review comment to add
     * @param reviewComments the list of reviewer comments
     * @param protocol the current protocol
     */
    void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, Protocol protocol);
    
    /**
     * Adds the newReviewerComment to the list of reviewerComments in the given protocol online review.
     * @param newReviewComment the new review comment to add
     * @param reviewComments the list of reviewer comments
     * @param protocolOnlineReview the current protocol online review
     */
    void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, ProtocolOnlineReview protocolOnlineReview);
    
    /**
     * Moves one review comment up the list by one value.
     * @param reviewComments the list of review comments
     * @param protocol the current protocol
     * @param fromIndex the current position of the review comment
     */
    void moveUpReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int fromIndex);
    
    /**
     * Moves one review comment down the list by one value.
     * @param reviewComments the list of reviewer comments
     * @param protocol the current protocol
     * @param fromIndex the current position of the review comment
     */
    void moveDownReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int fromIndex);
    
    /**
     * Delete the review comment at index from the list of reviewComments and add it to the list of deletedReviewComments.
     * @param reviewComments the list of reviewer comments
     * @param index the index of the reviewer comment to be deleted
     * @param deletedReviewComments the list of deleted reviewer comments
     */
    void deleteReviewComment(List<CommitteeScheduleMinute> reviewComments, int index, List<CommitteeScheduleMinute> deletedReviewComments);
    
    /**
     * Delete all review comments from the list of reviewComments and add them all to the  list of deletedReviewComments.
     * @param reviewComments the list of reviewer comments
     * @param deletedReviewComments the list of deleted reviewer comments
     */
    void deleteAllReviewComments(List<CommitteeScheduleMinute> reviewComments, List<CommitteeScheduleMinute> deletedReviewComments);
    
    /**
     * Saves the given reviewComments to the database and deletes the given deletedReviewComments.
     * @param reviewComments the review comments that will be saved
     * @param deletedReviewComments the review comments that will be deleted
     */
    void saveReviewComments(List<CommitteeScheduleMinute> reviewComments, List<CommitteeScheduleMinute> deletedReviewComments);


    /**
     * 
     * This method is to check whether there is any review comments can display reviewer name
     * based on IRB_DISPLAY_REVIEWER_NAME parameter, and role based business rule.
     * This is called by protocol action helper.
     * @param protocol
     * @param submissionNumber
     * @return
     */
    boolean setHideReviewerName(Protocol protocol, int submissionNumber);
    

    /**
     * 
     * This method is to check whether there is any review comments can display reviewer name
     * based on IRB_DISPLAY_REVIEWER_NAME parameter, and role based business rule.
     * @param reviewComments
     * @return
     */
    boolean setHideReviewerName(List<? extends ProtocolReviewable> reviewComments);
    /**
     * 
     * This method is to check whether the current user can view this minute comment.
     * 
     * @param CommitteeScheduleMinute minute
     * @return whether the current user can view this comment
     */
    boolean getReviewerMinuteCommentsView(CommitteeScheduleMinute minute);
    /**
     * 
     * This method is to check whether the Reviewer can view Accepted minute comment.
     * 
     * @param CommitteeScheduleMinute minute
     * @return whether the current user can view this comment
     */
    boolean getReviewerAcceptedCommentsView(CommitteeScheduleMinute minute);
    
    /**
     * 
     * This method is to delete a review attachment.
     * @param reviewAttachments
     * @param index
     * @param deletedReviewAttachments
     */
    void deleteReviewAttachment(List<ProtocolReviewAttachment> reviewAttachments, int index, List<ProtocolReviewAttachment> deletedReviewAttachments);

    /**
     * 
     * This method is to save review attachments in OLR or manage review attachment
     * @param reviewAttachments
     * @param deletedReviewAttachments
     */
    void saveReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments, List<ProtocolReviewAttachment> deletedReviewAttachments);

    /**
     * 
     * This method is to delete all saved attachments
     * @param reviewAttachments
     * @param deletedReviewAttachments
     */
    void deleteAllReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments,
            List<ProtocolReviewAttachment> deletedReviewAttachments);
    /**
     * 
     * This method is to add review attachment in OLR or manage review attachment
     * @param newReviewAttachment
     * @param reviewAttachments
     * @param protocol
     */
    void addReviewAttachment(ProtocolReviewAttachment newReviewAttachment, List<ProtocolReviewAttachment> reviewAttachments, Protocol protocol);

    /**
     * 
     * This method is to get review attachments for submission detail
     * @param protocolNumber
     * @param submissionNumber
     * @return
     */
    List<ProtocolReviewAttachment> getReviewerAttachments(String protocolNumber, int submissionNumber);

    /**
     * 
     * This method is to check to show 'view' button for protocol personnel
     * @param reviewAttachments
     * @return
     */
    boolean setHideViewButton(List<ProtocolReviewAttachment> reviewAttachments);
}