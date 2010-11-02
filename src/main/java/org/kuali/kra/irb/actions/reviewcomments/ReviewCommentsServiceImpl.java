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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class takes care of the persistence for Reviewer comments.
 */
public class ReviewCommentsServiceImpl implements ReviewCommentsService {
    
    private static final String[] PROTOCOL_SUBMISSION_COMPLETE_STATUSES = { ProtocolSubmissionStatus.APPROVED, 
                                                                            ProtocolSubmissionStatus.EXEMPT, 
                                                                            ProtocolSubmissionStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED, 
                                                                            ProtocolSubmissionStatus.SUBSTANTIVE_REVISIONS_REQUIRED, 
                                                                            ProtocolSubmissionStatus.DEFERRED, 
                                                                            ProtocolSubmissionStatus.DISAPPROVED };
    
    private BusinessObjectService businessObjectService;
    private CommitteeScheduleService committeeScheduleService;
    private CommitteeService committeeService;
    private ProtocolFinderDao protocolFinderDao;
    private RoleService roleService;
    private DateTimeService dateTimeService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#canViewOnlineReviewerComments(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public boolean canViewOnlineReviewerComments(String principalId, ProtocolSubmission protocolSubmission) {
        return isIrbAdminOrOnlineReviewer(principalId, protocolSubmission) || hasSubmissionCompleteStatus(protocolSubmission);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#canViewOnlineReviewers(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public boolean canViewOnlineReviewers(String principalId, ProtocolSubmission protocolSubmission) {
        return isIrbAdminOrOnlineReviewer(principalId, protocolSubmission);
    }
    
    private boolean isIrbAdminOrOnlineReviewer(String principalId, ProtocolSubmission submission) {
        boolean isAdmin = false;
        boolean isReviewer = false;
        
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR, null);
        isAdmin = ids.contains(principalId);
        
        if (principalId != null) {
            List<ProtocolReviewer> reviewers = submission.getProtocolReviewers();
            for (ProtocolReviewer reviewer : reviewers) {
                if (StringUtils.equals(principalId, reviewer.getPersonId())) {
                    isReviewer = true;
                    break;
                }
            }
        }
        
        return isAdmin || isReviewer;
    }
    
    private boolean hasSubmissionCompleteStatus(ProtocolSubmission submission) {
        return Arrays.asList(PROTOCOL_SUBMISSION_COMPLETE_STATUSES).contains(submission.getSubmissionStatusCode());
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#getReviewerComments(java.lang.String, int)
     */
    public List<CommitteeScheduleMinute> getReviewerComments(String protocolNumber, int submissionNumber) {
        ArrayList<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        List<ProtocolSubmission> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);
        
        for (ProtocolSubmission protocolSubmission : protocolSubmissions) {
            if (protocolSubmission.getCommitteeScheduleMinutes() != null) {
                for (CommitteeScheduleMinute minute : protocolSubmission.getCommitteeScheduleMinutes()) {
                    String minuteEntryTypeCode = minute.getMinuteEntryTypeCode();
                    // need to check current minute entry; otherwise may have minutes from previous version comittee
                    if (MinuteEntryType.PROTOCOL.equals(minuteEntryTypeCode) && isCurrentMinuteEntry(minute)) {
                        reviewComments.add(minute);
                    }
                }
            }
        }
        
        return reviewComments;
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#getProtocolReviewers(java.lang.String, int)
     */
    public List<ProtocolReviewer> getProtocolReviewers(String protocolNumber, int submissionNumber) {
        List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();

        List<ProtocolSubmission> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);

        for (ProtocolSubmission protocolSubmission : protocolSubmissions) {
            if (CollectionUtils.isNotEmpty(protocolSubmission.getProtocolReviewers())) {
                reviewers.addAll(protocolSubmission.getProtocolReviewers());
            }
        }

        return reviewers;
    }

    /*
     * when version committee, the minutes also versioned.  This is to get the current one.
     */
    protected boolean isCurrentMinuteEntry(CommitteeScheduleMinute minute) {
        minute.refreshReferenceObject("committeeSchedule");
        if (minute.getCommitteeSchedule() != null) {
            Committee committee = committeeService.getCommitteeById(minute.getCommitteeSchedule().getCommittee().getCommitteeId());
            return committee.getId().equals(minute.getCommitteeSchedule().getCommittee().getId());
        } else {
            // if scheduleid is 999999999
            return true;
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#addReviewComment(org.kuali.kra.meeting.CommitteeScheduleMinute, java.util.List, 
     *      org.kuali.kra.irb.Protocol)
     */
    public void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, Protocol protocol) {
        ProtocolSubmission protocolSubmission = getSubmission(protocol);
        if (protocolSubmission.getScheduleIdFk() != null) {
            newReviewComment.setScheduleIdFk(protocolSubmission.getScheduleIdFk());
        } else {
            newReviewComment.setScheduleIdFk(CommitteeSchedule.DEFAULT_SCHEDULE_ID);
        }
        // set entry number somehow
        newReviewComment.setProtocolIdFk(protocol.getProtocolId());
        newReviewComment.setProtocol(protocol);
        newReviewComment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        newReviewComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewComment.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
        newReviewComment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());

        reviewComments.add(newReviewComment);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#addReviewComment(org.kuali.kra.meeting.CommitteeScheduleMinute, java.util.List, 
     *      org.kuali.kra.irb.onlinereview.ProtocolOnlineReview)
     */
    public void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, 
            ProtocolOnlineReview protocolOnlineReview) {
        newReviewComment.setProtocolOnlineReview(protocolOnlineReview);
        newReviewComment.setProtocolOnlineReviewIdFk(protocolOnlineReview.getProtocolOnlineReviewId());
        newReviewComment.setProtocolReviewer(protocolOnlineReview.getProtocolReviewer());
        newReviewComment.setProtocolReviewerIdFk(protocolOnlineReview.getProtocolReviewerId());
        addReviewComment(newReviewComment, reviewComments, protocolOnlineReview.getProtocol());
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#moveUpReviewerComment(java.util.List, int, int)
     */
    public void moveUpReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int fromIndex) {
        if (fromIndex > 0) {
            int toIndex = indexOfPreviousProtocolReviewComment(reviewComments, protocol, fromIndex);
            if (toIndex < fromIndex) {
                CommitteeScheduleMinute movingReviewComment = reviewComments.remove(fromIndex);
                reviewComments.add(toIndex, movingReviewComment);
                for (int i = toIndex; i < fromIndex; i++) {
                    reviewComments.get(i).setEntryNumber(i);
                }
            }
        }
    }
    
    /**
     * Returns the index of the review comment just before to the one at index, where both of the review comments are in the same protocol.
     * 
     * If there is no such review comment, returns the index of the review comment at index.
     * @param reviewComments the list of review comments
     * @param protocol the current protocol
     * @param currentIndex the index of the current review comment
     * @return the index of the previous review comment, or the same index if there is none
     */
    private int indexOfPreviousProtocolReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int currentIndex) {
        int previousIndex = currentIndex;
        
        for (ListIterator<CommitteeScheduleMinute> iterator = reviewComments.listIterator(currentIndex); iterator.hasPrevious();) {
            int iteratorIndex = iterator.previousIndex();
            CommitteeScheduleMinute currentReviewComment = iterator.previous();
            if (ObjectUtils.equals(currentReviewComment.getProtocolId(), protocol.getProtocolId())) {
                previousIndex = iteratorIndex;
                break;
            }
        }
        
        return previousIndex;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#moveDownReviewComment(java.util.List, org.kuali.kra.irb.Protocol, int)
     */
    public void moveDownReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int fromIndex) {
        if (fromIndex < reviewComments.size() - 1) {
            int toIndex = indexOfNextProtocolReviewComment(reviewComments, protocol, fromIndex);
            if (toIndex > fromIndex) {
                CommitteeScheduleMinute movingReviewComment = reviewComments.remove(fromIndex);
                reviewComments.add(toIndex, movingReviewComment);
                for (int i = fromIndex; i < toIndex; i++) {
                    reviewComments.get(i).setEntryNumber(i);
                }
            }
        }
    }
    
    /**
     * Returns the index of the review comment just after to the one at index, where both of the review comments are in the same protocol.
     * 
     * If there is no such review comment, returns the index of the review comment at index.
     * @param reviewComments the list of review comments
     * @param protocol the current protocol
     * @param currentIndex the index of the current review comment
     * @return the index of the next review comment, or the same index if there is none
     */
    private int indexOfNextProtocolReviewComment(List<CommitteeScheduleMinute> reviewComments, Protocol protocol, int currentIndex) {
        int nextIndex = currentIndex;
        
        for (ListIterator<CommitteeScheduleMinute> iterator = reviewComments.listIterator(currentIndex + 1); iterator.hasNext();) {
            int iteratorIndex = iterator.nextIndex();
            CommitteeScheduleMinute currentReviewComment = iterator.next();
            if (ObjectUtils.equals(currentReviewComment.getProtocolId(), protocol.getProtocolId())) {
                nextIndex = iteratorIndex;
                break;
            }
        }
        
        return nextIndex;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#deleteReviewComment(java.util.List, int, java.util.List)
     */
    public void deleteReviewComment(List<CommitteeScheduleMinute> reviewComments, int index, List<CommitteeScheduleMinute> deletedReviewComments) {
        if (index >= 0 && index < reviewComments.size()) {
            CommitteeScheduleMinute reviewerComment = reviewComments.get(index);
            if (reviewerComment.getCommScheduleMinutesId() != null) {
                deletedReviewComments.add(reviewerComment);
            }
            reviewComments.remove(index);
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#deleteAllReviewComments(java.util.List, java.util.List)
     */
    public void deleteAllReviewComments(List<CommitteeScheduleMinute> reviewComments, List<CommitteeScheduleMinute> deletedReviewComments) {
        for (CommitteeScheduleMinute reviewerComment : reviewComments) {
            if (reviewerComment.getCommScheduleMinutesId() != null) {
                deletedReviewComments.add(reviewerComment);
            }
        }
        reviewComments.clear();
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#saveReviewComments(java.util.List, java.util.List)
     */
    public void saveReviewComments(List<CommitteeScheduleMinute> reviewComments, List<CommitteeScheduleMinute> deletedReviewComments) {
        int nextEntryNumber = 0;
        for (CommitteeScheduleMinute reviewerComment : reviewComments) {
            reviewerComment.setEntryNumber(nextEntryNumber);
            boolean doUpdate = false;
            if (reviewerComment.getCommScheduleMinutesId() != null) {
                CommitteeScheduleMinute existing = this.committeeScheduleService.getCommitteeScheduleMinute(reviewerComment.getCommScheduleMinutesId());
                doUpdate = !reviewerComment.equals(existing);
            } else {
                doUpdate = true;
            }
            if (doUpdate) {
                this.businessObjectService.save(reviewerComment);
            }
            nextEntryNumber++;
        }
        
        if (!deletedReviewComments.isEmpty()) {
            businessObjectService.delete(deletedReviewComments);
        }
    }

    /*
     * if this is IRB acknowledgement and loaded from protocol submission or notification.
     */
    protected ProtocolSubmission getSubmission(Protocol protocol) {
        ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                    protocolSubmission = submission;
                    break;
                }
            }
        }
        return protocolSubmission;
    
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setCommitteeScheduleService(CommitteeScheduleService committeeScheduleService) {
        this.committeeScheduleService = committeeScheduleService;
    }
    
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
}