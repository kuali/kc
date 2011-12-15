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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.irb.onlinereview.ProtocolReviewable;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.RoleManagementService;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.ParameterService;
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
    private static final String HIDE = "0";
    private static final String DISPLAY = "1";
    private BusinessObjectService businessObjectService;
    private CommitteeScheduleService committeeScheduleService;
    private CommitteeService committeeService;
    private ProtocolFinderDao protocolFinderDao;
    private RoleService roleService;
    private DateTimeService dateTimeService;
    private ParameterService parameterService;
    private RoleService kimRoleManagementService;
    private KcPersonService kcPersonService;
    private Set<String> irbAdminIds;
    private List<String> irbAdminUserNames;
    private List<String> reviewerIds;
    private Set<String> viewerIds;
    private Set<String> aggregatorIds;
    private boolean displayReviewerNameToPersonnel;
    private boolean displayReviewerNameToReviewers;
    private boolean displayReviewerNameToActiveMembers;
 
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#canViewOnlineReviewerComments(java.lang.String, 
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmission)
     */
    public boolean canViewOnlineReviewerComments(String principalId, ProtocolSubmission protocolSubmission) {
        return isIrbAdminOrOnlineReviewer(principalId, protocolSubmission) || 
               hasSubmissionCompleteStatus(protocolSubmission) ||
               isActiveCommitteeMember(protocolSubmission, principalId);
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
                    if ((MinuteEntryType.PROTOCOL.equals(minuteEntryTypeCode) || MinuteEntryType.PROTOCOL_REVIEWER_COMMENT.equals(minuteEntryTypeCode)) && isCurrentMinuteEntry(minute)) {
                        if(getReviewerCommentsView(minute)){
                            reviewComments.add(minute);
                        }
                    }
                }
            }
        }
        
        return reviewComments;
    }
   
    public List<ProtocolReviewAttachment> getReviewerAttachments(String protocolNumber, int submissionNumber) {

        List<ProtocolReviewAttachment> reviewAttachments = new ArrayList<ProtocolReviewAttachment>();
        List<ProtocolSubmission> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);
        // protocol versioning does not version review attachments/comments
        for (ProtocolSubmission protocolSubmission : protocolSubmissions) {
            if (CollectionUtils.isNotEmpty(protocolSubmission.getReviewAttachments())) {
                for (ProtocolReviewAttachment reviewAttachment : protocolSubmission.getReviewAttachments()) {
                    if (getReviewerCommentsView(reviewAttachment)) {
                        reviewAttachments.add(reviewAttachment);
                    }
                }
            }
        }
        return reviewAttachments;
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
        newReviewComment.setEntryNumber(reviewComments.size());
        newReviewComment.setProtocolIdFk(protocol.getProtocolId());
        newReviewComment.setProtocol(protocol);
        newReviewComment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        newReviewComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewComment.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
        newReviewComment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        // TO show update timestamp after 'add'
        newReviewComment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());

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
                for (int i = toIndex; i <= fromIndex; i++) {
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
                for (int i = fromIndex; i <= toIndex; i++) {
                    reviewComments.get(i).setEntryNumber(i);
                }
            }
        }
    }
    
    
    /**
     * Returns whether the current user can view this comment.
     *    
     * This is true either if 
     *   1) The current user has the role IRB Administrator
     *   2) The comment/minute has been accepted by an IRB Administrator and one of the following conditions is true:
     *   3) The current user does not have the role IRB Administrator, but the current user is the comment creator
     *   4) The current user does not have the role IRB Administrator, but is a reviewer of the protocol, and not part of the protocol personnel, and the comment is final
     *   5) The current user does not have the role IRB Administrator, but is an active committee member, and not part of the protocol personnel, and the comment is final
     *   6) The comment is public and final
     *   
     *   In addition if the comment is not associated with an online review then it automatically returns true.
     * @param CommitteeScheduleMinute minute
    *  @return whether the current user can view this comment
    */
    public boolean getReviewerCommentsView(ProtocolReviewable minute) {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String principalName = GlobalVariables.getUserSession().getPrincipalName();
        
        if (isIrbAdministrator(principalId)) {
            return true;
        } else {
            if (minute.getProtocolOnlineReviewIdFk() != null) {
                if (minute.isAccepted()) {
                    return StringUtils.equals(principalName, minute.getCreateUser()) || isViewable(minute);
                } else {
                    return false;
                }
            } else {
                return (!minute.isPrivate() && minute.isFinal());
            }
        }       
    }
    
    /*
     * This method is to check if review comment/attachment is viewable for this user
     */
    private boolean isViewable(ProtocolReviewable reviewable) {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        return (isReviewer(reviewable, principalId) && !isProtocolPersonnel(reviewable) && !hasProtocolPermission(reviewable) && reviewable
                .isFinal())
                || (isActiveCommitteeMember(reviewable, principalId) && !isProtocolPersonnel(reviewable)
                        && !hasProtocolPermission(reviewable) && reviewable.isFinal())
                || (!reviewable.isPrivate() && reviewable.isFinal());
    }
   
   private boolean isIrbAdministrator(String principalId) {
       RoleService roleService = KraServiceLocator.getService(RoleManagementService.class);
       Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR, null);
       return ids.contains(principalId);
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
            CommitteeScheduleMinute reviewComment = reviewComments.get(index);
            if (reviewComment.getCommScheduleMinutesId() != null) {
                deletedReviewComments.add(reviewComment);
            }
            reviewComments.remove(index);
            
            for (int i = index; i < reviewComments.size(); i++) {
                reviewComments.get(i).setEntryNumber(i);
            }
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
        for (CommitteeScheduleMinute reviewComment : reviewComments) {
            boolean doUpdate = true;
            if (reviewComment.getCommScheduleMinutesId() != null) {
                CommitteeScheduleMinute existing = committeeScheduleService.getCommitteeScheduleMinute(reviewComment.getCommScheduleMinutesId());
                doUpdate = !reviewComment.equals(existing);
            }
            if (doUpdate) {
                businessObjectService.save(reviewComment);
            }
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


    private void getReviewerNameParams() {
        displayReviewerNameToActiveMembers = isDisplayReviewerName(Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS);  
        displayReviewerNameToPersonnel = isDisplayReviewerName(Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL);  
        displayReviewerNameToReviewers = isDisplayReviewerName(Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS);  
    }

    /*
     * retrieve Display reviewer name parameter and compre with 'HIDE'
     */
    private boolean isDisplayReviewerName(String paramName) {
        String param = parameterService.getParameterValue(ProtocolDocument.class, paramName);        
       return !StringUtils.equals(HIDE, param);  
        
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#setHideReviewerName(org.kuali.kra.irb.Protocol, int)
     */
    public boolean setHideReviewerName(Protocol protocol, int submissionNumber) {
        return setHideReviewerName(getReviewerComments(protocol.getProtocolNumber(), submissionNumber));
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#setHideReviewerName(java.util.List)
     */
    public boolean setHideReviewerName(List<? extends ProtocolReviewable> reviewComments) {
        boolean isHide = true;
        setReviewerIds(reviewerIds);
       // hideReviewerName = isHideReviewerName();
        getReviewerNameParams();
        for (ProtocolReviewable reviewComment : reviewComments) {
            if (canViewName(reviewComment)) {
                reviewComment.setDisplayReviewerName(true);
                isHide = false;
            }

        }
        return isHide;
    }
    
    public boolean setHideViewButton(List<ProtocolReviewAttachment> reviewAttachments) {
        boolean isHide = true;
        getReviewerNameParams();
        for (ProtocolReviewAttachment reviewAttachment : reviewAttachments) {
            if (!reviewAttachment.isPrivateFlag() || !isProtocolPersonnel(reviewAttachment)) {
                reviewAttachment.setDisplayViewButton(true);
                isHide = false;
            }

        }
        return isHide;
    }
   
    private boolean canViewName(ProtocolReviewable reviewComment) {
        boolean canViewName = false;
        Person person = GlobalVariables.getUserSession().getPerson();
        // if (hideReviewerName) {
        if (isIrbAdmin(person.getPrincipalId()) || isCreator(reviewComment, person.getPrincipalName())) {
            canViewName = true;
        }
        // }
        else {
            // if protocol personnel, then only if display to personnel is set to true
            if (isProtocolPersonnelOrHasProtocolRole(reviewComment)) {
                if (isDisplayReviewerNameToPersonnel()) {
                    canViewName = true;
                }
            }
            // must be non protocol personnel
            else if ((isDisplayReviewerNameToReviewers() && isReviewer(reviewComment, person.getPrincipalId()))
                    || (isDisplayReviewerNameToActiveMembers() && getActiveMemberId(reviewComment)
                            .contains(person.getPrincipalId()))) {
                // only if display to personnel is true or is not protocol personnel
                canViewName = true;
            }

        }
        return canViewName;
    }

    private boolean hasProtocolPermission(ProtocolReviewable reviewComment) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getProtocolAggregators(reviewComment).contains(person.getPrincipalId())
               || getProtocolViewers(reviewComment).contains(person.getPrincipalId());
    }
        
    private boolean isProtocolPersonnel(ProtocolReviewable reviewComment) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(reviewComment).contains(person.getPrincipalId());        
    }
    
    /*
     * check if user is protocol personnel or has permission as aggregator or viewer
     */
    private boolean isProtocolPersonnelOrHasProtocolRole(ProtocolReviewable reviewComment) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(reviewComment).contains(person.getPrincipalId())
                || getProtocolAggregators().contains(person.getPrincipalId())
                || getProtocolViewers().contains(person.getPrincipalId());
    }
    

    private List<String> getActiveMemberId(ProtocolReviewable reviewComment) {
        List<String> activeMemberIds = new ArrayList<String>();
        List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        if (reviewComment.isReviewComment()) {
            members = ((CommitteeScheduleMinute)reviewComment).getCommitteeSchedule().getCommittee().getCommitteeMemberships();
        } else {
            members = ((ProtocolReviewAttachment)reviewComment).getProtocol().getProtocolSubmission().getCommittee().getCommitteeMemberships();
        }
        for (CommitteeMembership member : members) {
            if (member.isActive()) {
                if (StringUtils.isNotBlank(member.getPersonId())) {
                    activeMemberIds.add(member.getPersonId());
                }
                else {
                    activeMemberIds.add(member.getRolodexId().toString());
                }
            }
        }
        return activeMemberIds;
    }

    private List<String> getPersonnelIds(ProtocolReviewable reviewComment) {
        List<String> PersonnelIds = new ArrayList<String>();
        if (reviewComment.getProtocol() != null) {
            for (ProtocolPerson person : reviewComment.getProtocol().getProtocolPersons()) {
                if (StringUtils.isNotBlank(person.getPersonId())) {
                    PersonnelIds.add(person.getPersonId());
                }
                else {
                    PersonnelIds.add(person.getRolodexId().toString());
                }
            }
        }
        return PersonnelIds;
    }
    /**
     * Returns whether the current user can view this non Final Comments and Private Comment.
     * 
     * @param CommitteeScheduleMinute minute
     * @return whether the current user can view this comment
     */
    public boolean getReviewerMinuteCommentsView(CommitteeScheduleMinute minute) {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String principalName = GlobalVariables.getUserSession().getPrincipalName();
        return StringUtils.equals(principalName, minute.getCreateUser()) && minute.isFinalFlag()
                || (isReviewer(minute, principalId) && minute.isFinalFlag())
                || (!minute.getPrivateCommentFlag() && minute.isFinalFlag());
    }


    
    private boolean isIrbAdmin(String principalId) {
        return !CollectionUtils.isEmpty(getIrbAdminIds()) && getIrbAdminIds().contains(principalId);
    }
    
    /*
     * if the person is PI.
     */
    private boolean isPrincipalInvestigator(CommitteeScheduleMinute reviewComment, String principalId) {
       boolean isPi = false;
       if (reviewComment.getProtocolId() != null) {
           // TODO : need to check if the submission number is ok to get this way
           isPi =  principalId.equals(reviewComment.getProtocol().getPrincipalInvestigatorId());
       }
       return isPi;
    }
    
    /*
     * if the person a reviewer.
     */
    private boolean isReviewer(ProtocolReviewable reviewComment, String principalId) {
        List<String> reviewerIds = getProtocolReviewerIds(reviewComment);
        return !reviewerIds.isEmpty() && reviewerIds.contains(principalId);
    }
    
    /*
     * if the person is comment creator.
     */
    private boolean isCreator(ProtocolReviewable reviewComment, String userName) {
        return reviewComment.getCreateUser().equals(userName);
    }

    /*
     * get the reviewer ids for this submission
     */
    private List<String> getProtocolReviewerIds(ProtocolReviewable reviewComment) {
        List<String> reviewerIds = new ArrayList<String>();
        if (reviewComment.getProtocolId() != null) {
            // TODO : need to check if the submission number is ok to get this way
            reviewerIds = getProtocolReviewerIds(reviewComment.getProtocolId(), reviewComment.getProtocol().getProtocolSubmission().getSubmissionNumber());
        }
        return reviewerIds;
    }
    
    /*
     * retrieve reviewer ids from db based on protocolid and submissionnumber
     */
    private List<String> getProtocolReviewerIds(Long protocolId, int submissionNumber) {
        Map fieldValues = new HashMap();
        fieldValues.put("protocolIdFk", protocolId);
        fieldValues.put("submissionNumber", submissionNumber);
        List<String> reviewerPersonIds = new ArrayList<String>();
        for (ProtocolReviewer reviewer : (List<ProtocolReviewer>)businessObjectService.findMatching(ProtocolReviewer.class, fieldValues)) {
            reviewerPersonIds.add(reviewer.getPersonId());
        }
        return reviewerPersonIds;
        
    }
    
    /*
     * retrieve Irb admins from role table
     */
    private void getIrbAdmins() {
        irbAdminIds = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-UNT", RoleConstants.IRB_ADMINISTRATOR,
                null);
        irbAdminUserNames = new ArrayList<String>();
        for (String id : irbAdminIds) {
            KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(id);
            irbAdminUserNames.add(kcPerson.getUserName());
        }
    }

    private Set<String> getProtocolAggregators() {
        if (CollectionUtils.isEmpty(aggregatorIds)) {
            aggregatorIds = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_AGGREGATOR,
                    null);
            
        }
        return aggregatorIds;
        
    }
    
    private Set<String> getProtocolAggregators(ProtocolReviewable minute) {
        if (CollectionUtils.isEmpty(aggregatorIds) && minute != null) {
            
            aggregatorIds = new HashSet<String>();

            if (StringUtils.isNotBlank(minute.getProtocol().getProtocolNumber())) {
                Map<String, String> protocolAttr = new HashMap<String, String>();
                protocolAttr.put(KcKimAttributes.PROTOCOL, minute.getProtocol().getProtocolNumber());
                Set<String> protoResults = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_AGGREGATOR,
                        new AttributeSet(protocolAttr));
                
                if (CollectionUtils.isNotEmpty(protoResults)) {
                    aggregatorIds.addAll(protoResults);
                }
            }

            if (StringUtils.isNotBlank(minute.getProtocol().getLeadUnitNumber())) {
                Map<String, String> leadUnitAttr = new HashMap<String, String>();
                leadUnitAttr.put(KcKimAttributes.UNIT_NUMBER, minute.getProtocol().getLeadUnitNumber());
                Set<String> leadUnitResults = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_AGGREGATOR,
                        new AttributeSet(leadUnitAttr));
                
                if (CollectionUtils.isNotEmpty(leadUnitResults)) {
                    aggregatorIds.addAll(leadUnitResults);
                }
            }

            
        }
        
        return aggregatorIds;        
    }

    private Set<String> getProtocolViewers() {
        if (CollectionUtils.isEmpty(viewerIds)) {
            viewerIds = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_VIEWER,
                    null);
            
        }
        return viewerIds;
        
    }

    private Set<String> getProtocolViewers(ProtocolReviewable minute) {
        if (CollectionUtils.isEmpty(viewerIds) && minute != null) {
            
            viewerIds = new HashSet<String>();

            if (StringUtils.isNotBlank(minute.getProtocol().getProtocolNumber())) {
                Map<String, String> protocolAttr = new HashMap<String, String>();
                protocolAttr.put(KcKimAttributes.PROTOCOL, minute.getProtocol().getProtocolNumber());
                Set<String> protoResults = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_VIEWER,
                        new AttributeSet(protocolAttr));
                
                if (CollectionUtils.isNotEmpty(protoResults)) {
                    viewerIds.addAll(protoResults);
                }
            }

            if (StringUtils.isNotBlank(minute.getProtocol().getLeadUnitNumber())) {
                Map<String, String> leadUnitAttr = new HashMap<String, String>();
                leadUnitAttr.put(KcKimAttributes.UNIT_NUMBER, minute.getProtocol().getLeadUnitNumber());
                Set<String> leadUnitResults = (Set<String>) kimRoleManagementService.getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_VIEWER,
                        new AttributeSet(leadUnitAttr));
                
                if (CollectionUtils.isNotEmpty(leadUnitResults)) {
                    viewerIds.addAll(leadUnitResults);
                }
            }            
        }
        
        return viewerIds;
        
    }

    public void setKimRoleManagementService(RoleService kimRoleManagementService) {
        this.kimRoleManagementService = kimRoleManagementService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public Set<String> getIrbAdminIds() {
        if (CollectionUtils.isEmpty(irbAdminIds)) {
            getIrbAdmins();
        }
        return irbAdminIds;
    }

    public void setIrbAdminIds(Set<String> irbAdminIds) {
        this.irbAdminIds = irbAdminIds;
    }

    public List<String> getIrbAdminUserNames() {
        if (CollectionUtils.isEmpty(irbAdminUserNames)) {
            getIrbAdmins();
        }
        return irbAdminUserNames;
    }

    public void setIrbAdminUserNames(List<String> irbAdminUserNames) {
        this.irbAdminUserNames = irbAdminUserNames;
    }

    public List<String> getReviewerIds() {
        return reviewerIds;
    }

    public void setReviewerIds(List<String> reviewerIds) {
        this.reviewerIds = reviewerIds;
    }

    public boolean isDisplayReviewerNameToPersonnel() {
        return displayReviewerNameToPersonnel;
    }

    public void setDisplayReviewerNameToPersonnel(boolean displayReviewerNameToPersonnel) {
        this.displayReviewerNameToPersonnel = displayReviewerNameToPersonnel;
    }

    public boolean isDisplayReviewerNameToReviewers() {
        return displayReviewerNameToReviewers;
    }

    public void setDisplayReviewerNameToReviewers(boolean displayReviewerNameToReviewers) {
        this.displayReviewerNameToReviewers = displayReviewerNameToReviewers;
    }

    public boolean isDisplayReviewerNameToActiveMembers() {
        return displayReviewerNameToActiveMembers;
    }

    public void setDisplayReviewerNameToActiveMembers(boolean displayReviewerNameToActiveMembers) {
        this.displayReviewerNameToActiveMembers = displayReviewerNameToActiveMembers;
    }

    /**
     * 
     * This method determines if the current user is an active committee member
     * @param minute
     * @param principalId
     * @return true if and active committee member, false otherwise.
     */
    private boolean isActiveCommitteeMember(ProtocolReviewable minute, String principalId) {
        boolean result = false;       
        List<CommitteeMembership> committeeMembers = 
            committeeService.getAvailableMembers(minute.getCommitteeSchedule().getCommittee().getCommitteeId(),
                                                 minute.getCommitteeSchedule().getScheduleId());
        if (CollectionUtils.isNotEmpty(committeeMembers)) {
            for (CommitteeMembership member : committeeMembers) {
                if (StringUtils.equals(principalId, member.getPersonId())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }   

    /**
     * 
     * This method determines if the current user is an active committee member
     * @param minute
     * @param principalId
     * @return true if and active committee member, false otherwise.
     */    
    private boolean isActiveCommitteeMember(ProtocolSubmission submission, String principalId) {
        boolean result = false;
        
        List<CommitteeMembership> committeeMembers = 
            committeeService.getAvailableMembers(submission.getCommitteeId(),
                                                 submission.getScheduleId());
        if (CollectionUtils.isNotEmpty(committeeMembers)) {
            for (CommitteeMembership member : committeeMembers) {
                if (StringUtils.equals(principalId, member.getPersonId())) {
                    result = true;
                    break;
                }
            }
        }        
        
        return result;
    }
    
    
    /**
     * Returns whether the Reviewer can view this accepted minute Comments in print.
     * 
     * @param CommitteeScheduleMinute minute
     * @return whether the current user can view this comment
     */
    public boolean getReviewerAcceptedCommentsView(CommitteeScheduleMinute minute) {
        boolean viewAcceptedMinute = false; 
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String principalName = GlobalVariables.getUserSession().getPrincipalName();
        if (minute.getProtocolOnlineReviewIdFk() != null) {
            ProtocolOnlineReview protocolOnlineReview =businessObjectService.findBySinglePrimaryKey(ProtocolOnlineReview.class, minute.getProtocolOnlineReviewIdFk());
            if (protocolOnlineReview.isAdminAccepted()) {
                viewAcceptedMinute = true;
            }
        } else {
            viewAcceptedMinute = true;
        }
        return (StringUtils.equals(principalName, minute.getCreateUser()) && viewAcceptedMinute)
        || (isReviewer(minute, principalId) && minute.isFinalFlag() && viewAcceptedMinute)
        || (!minute.getPrivateCommentFlag() && minute.isFinalFlag() && viewAcceptedMinute);
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#deleteReviewAttachment(java.util.List, int, java.util.List)
     */
    public void deleteReviewAttachment(List<ProtocolReviewAttachment> reviewAttachments, int index, List<ProtocolReviewAttachment> deletedReviewAttachments) {
        if (index >= 0 && index < reviewAttachments.size()) {
            ProtocolReviewAttachment reviewAttachment = reviewAttachments.get(index);
            if (reviewAttachment.getReviewerAttachmentId() != null) {
                deletedReviewAttachments.add(reviewAttachment);
            }
            reviewAttachments.remove(index);
            
//            for (int i = index; i < reviewAttachments.size(); i++) {
//                reviewAttachments.get(i).setEntryNumber(i);
//            }
        }
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#saveReviewAttachments(java.util.List, java.util.List)
     */
    public void saveReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments, List<ProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachment reviewAttachment : reviewAttachments) {
            boolean doUpdate = true;
//            if (reviewAttachment.getReviewerAttachmentId() != null) {
//                ProtocolOnlineReviewAttachment existing = committeeScheduleService.getCommitteeScheduleMinute(reviewAttachment.getCommScheduleMinutesId());
//                doUpdate = !reviewAttachment.equals(existing);
//            }
            if (doUpdate) {
                reviewAttachment.setPrivateFlag(!reviewAttachment.isProtocolPersonCanView());
                businessObjectService.save(reviewAttachment);
            }
        }
        
        if (!deletedReviewAttachments.isEmpty()) {
            businessObjectService.delete(deletedReviewAttachments);
        }
    }

    /**
     * 
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#addReviewAttachment(org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment, java.util.List, org.kuali.kra.irb.Protocol)
     */
    public void addReviewAttachment(ProtocolReviewAttachment newReviewAttachment, List<ProtocolReviewAttachment> reviewAttachments, Protocol protocol) {
        ProtocolSubmission protocolSubmission = getSubmission(protocol);
        newReviewAttachment.setAttachmentId(getNextAttachmentId(protocol));
        newReviewAttachment.setProtocolIdFk(protocol.getProtocolId());
        newReviewAttachment.setProtocol(protocol);
        newReviewAttachment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        newReviewAttachment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewAttachment.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
        newReviewAttachment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewAttachment.setPersonId(GlobalVariables.getUserSession().getPrincipalId());
        newReviewAttachment.setPrivateFlag(!newReviewAttachment.isProtocolPersonCanView());
       // TO show update timestamp after 'add'
        newReviewAttachment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
        final AttachmentFile newFile = AttachmentFile.createFromFormFile(newReviewAttachment.getNewFile());
        newReviewAttachment.setFile(newFile);
        // set to null, so the subsequent post will not creating new file again
        newReviewAttachment.setNewFile(null);

        reviewAttachments.add(newReviewAttachment);
    }

    /*
     * get next attachmentId.  it seems coeus is just increasing by 1, no matter what protocol is.
     * but attachment_id is only number(3).  so, need further investigation.
     */
    private int getNextAttachmentId(Protocol protocol) {
        Map fieldValues = new HashMap();
        fieldValues.put("protocolIdFk", protocol.getProtocolId());
        List<ProtocolReviewAttachment> reviewAttachments =(List<ProtocolReviewAttachment>)businessObjectService.findMatchingOrderBy(ProtocolReviewAttachment.class, fieldValues, "attachmentId", false);
        if (CollectionUtils.isEmpty(reviewAttachments)) {
            return 1;
        } else {
            return reviewAttachments.get(0).getAttachmentId() + 1;
        }
        
    }

    @Override
    public void deleteAllReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments,
            List<ProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachment reviewerAttachment : reviewAttachments) {
            if (reviewerAttachment.getReviewerAttachmentId() != null) {
                deletedReviewAttachments.add(reviewerAttachment);
            }
        }
        reviewAttachments.clear();
        
    }
}