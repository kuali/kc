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
package org.kuali.kra.protocol.actions.reviewcomments;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewableBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

public abstract class ReviewCommentsServiceImplBase<PRA extends ProtocolReviewAttachmentBase> implements ReviewCommentsService<PRA> {

    private static final String HIDE = "0";  
    private static final String DISPLAY = "1";
    protected BusinessObjectService businessObjectService;
    private CommitteeServiceBase committeeService;
    private ProtocolFinderDao protocolFinderDao;
    private RoleService roleService;
    private DateTimeService dateTimeService;
    private ParameterService parameterService;
    private KcPersonService kcPersonService;
    private Set<String> adminIds;
    private List<String> adminUserNames;
    private List<String> reviewerIds;
    private Set<String> viewerIds;
    private Set<String> aggregatorIds;
    private boolean displayReviewerNameToPersonnel;
    private boolean displayReviewerNameToReviewers;
    private boolean displayReviewerNameToActiveMembers;

    
    

    @Override
    public boolean canViewOnlineReviewerComments(String principalId, ProtocolSubmissionBase protocolSubmission) {
        return isAdminOrOnlineReviewer(principalId, protocolSubmission) || hasSubmissionCompleteStatus(protocolSubmission)
                || isActiveCommitteeMember(protocolSubmission, principalId);
    }

    @Override
    public boolean canViewOnlineReviewers(String principalId, ProtocolSubmissionBase protocolSubmission) {
        return isAdminOrOnlineReviewer(principalId, protocolSubmission);
    }

    private boolean isAdminOrOnlineReviewer(String principalId, ProtocolSubmissionBase submission) {
        boolean isAdmin = false;
        boolean isReviewer = false;

        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, getAdministratorRoleHook(), null);
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

    private boolean hasSubmissionCompleteStatus(ProtocolSubmissionBase submission) {
        boolean validSubmissionStatus= Arrays.asList(getProtocolSubmissionCompleteStatusCodeArrayHook()).contains(submission.getSubmissionStatusCode());
        return validSubmissionStatus;
    }

    protected abstract String[] getProtocolSubmissionCompleteStatusCodeArrayHook();
    
    
    
    public List<CommitteeScheduleMinuteBase> getReviewerComments(String protocolNumber, int submissionNumber) {
        ArrayList<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        List<ProtocolSubmissionBase> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);

        for (ProtocolSubmissionBase protocolSubmission : protocolSubmissions) {
            if (protocolNumber.equals(protocolSubmission.getProtocolNumber()) && protocolSubmission.getCommitteeScheduleMinutes() != null) {
                // search table directly as ProtocolBase Submission is not refreshed as commit happens later
                Map fieldValues = new HashMap();
                fieldValues.put("protocolIdFk", protocolSubmission.getProtocolId());
                fieldValues.put("submissionIdFk", protocolSubmission.getSubmissionId());
                
                List<CommitteeScheduleMinuteBase> reviewComments1 = (List<CommitteeScheduleMinuteBase>) businessObjectService
                        .findMatchingOrderBy(getCommitteeScheduleMinuteBOClassHook(), fieldValues, "commScheduleMinutesId", false);
                for (CommitteeScheduleMinuteBase minute : reviewComments1) {
                    String minuteEntryTypeCode = minute.getMinuteEntryTypeCode();
                    // need to check current minute entry; otherwise may have minutes from previous version comittee
                    if ((MinuteEntryType.PROTOCOL.equals(minuteEntryTypeCode) || MinuteEntryType.PROTOCOL_REVIEWER_COMMENT
                            .equals(minuteEntryTypeCode)) && isCurrentMinuteEntry(minute)) {
                        if (getReviewerCommentsView(minute)) {
                            reviewComments.add(minute);
                        }
                    }
                }
            }
        }

        return reviewComments;
    }

    protected abstract Class<? extends CommitteeScheduleMinuteBase> getCommitteeScheduleMinuteBOClassHook();
    
    @Override
    public List<PRA> getReviewerAttachments(String protocolNumber, int submissionNumber) {

        List<PRA> reviewAttachments = new ArrayList<PRA>();
        List<ProtocolSubmissionBase> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);
        // protocol versioning does not version review attachments/comments
        for (ProtocolSubmissionBase protocolSubmission : protocolSubmissions) {
            if (CollectionUtils.isNotEmpty(protocolSubmission.getReviewAttachments()) || 
                        protocolSubmissions.size() == 1) 
            {
                // search table directly as ProtocolBase Submission is not refreshed as commit happens later
                Map fieldValues = new HashMap();
                fieldValues.put("protocolIdFk", protocolSubmission.getProtocolId());
                fieldValues.put("submissionIdFk", protocolSubmission.getSubmissionId());
                List<PRA> reviewAttachments1 = (List<PRA>) businessObjectService
                        .findMatchingOrderBy(getProtocolReviewAttachmentClassHook(), fieldValues, "attachmentId", false);

                for (ProtocolReviewAttachmentBase reviewAttachment : reviewAttachments1) {
                    if (getReviewerCommentsView(reviewAttachment)) {
                        reviewAttachments.add((PRA) reviewAttachment);
                    }
                }               
            }
        }
        return reviewAttachments;
    }

    
    @Override
    public List<ProtocolReviewer> getProtocolReviewers(String protocolNumber, int submissionNumber) {
        List<ProtocolReviewer> reviewers = new ArrayList<ProtocolReviewer>();
        List<ProtocolSubmissionBase> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);
        for (ProtocolSubmissionBase protocolSubmission : protocolSubmissions) {
            reviewers.addAll(protocolSubmission.getProtocolReviewers());
        }
        return reviewers;
    }
    
    @Override
    public List<ProtocolOnlineReviewBase> getProtocolOnlineReviews(String protocolNumber, int submissionNumber) {
        List<ProtocolOnlineReviewBase> activeReviews = new ArrayList<ProtocolOnlineReviewBase>();
        List<ProtocolSubmissionBase> protocolSubmissions = protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber);
        for (ProtocolSubmissionBase protocolSubmission : protocolSubmissions) {
            activeReviews.addAll(protocolSubmission.getActiveProtocolOnlineReviews());
        }
        return activeReviews;
    }

    
    /*
     * when version committee, the minutes also versioned. This is to get the current one.
     */
    protected boolean isCurrentMinuteEntry(CommitteeScheduleMinuteBase minute) {
        minute.refreshReferenceObject("committeeSchedule");
        if (minute.getCommitteeSchedule() != null) {
            CommitteeBase committee = committeeService.getCommitteeById(minute.getCommitteeSchedule().getParentCommittee().getCommitteeId());
            return committee.getId().equals(minute.getCommitteeSchedule().getParentCommittee().getId());
        }
        else {
            // if scheduleid is 999999999
            return true;
        }
    }

    public void addReviewComment(CommitteeScheduleMinuteBase newReviewComment, List<CommitteeScheduleMinuteBase> reviewComments,
            ProtocolBase protocol) {
        ProtocolSubmissionBase protocolSubmission = getSubmission(protocol);
        if (protocolSubmission.getScheduleIdFk() != null) {
            newReviewComment.setScheduleIdFk(protocolSubmission.getScheduleIdFk());
            newReviewComment.setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        }
        else {
            newReviewComment.setScheduleIdFk(CommitteeScheduleBase.DEFAULT_SCHEDULE_ID);
        }
        newReviewComment.setEntryNumber(reviewComments.size());
        newReviewComment.setProtocolIdFk(protocol.getProtocolId());
        newReviewComment.setProtocolNumber(protocol.getProtocolNumber());
        newReviewComment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        newReviewComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewComment.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
        newReviewComment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        // TO show update timestamp after 'add'
        newReviewComment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());

        reviewComments.add(newReviewComment);
    }
    
  
    public void addReviewComment(CommitteeScheduleMinuteBase newReviewComment, List<CommitteeScheduleMinuteBase> reviewComments,
            ProtocolOnlineReviewBase protocolOnlineReview) {
        newReviewComment.setProtocolOnlineReview(protocolOnlineReview);
        newReviewComment.setProtocolOnlineReviewIdFk(protocolOnlineReview.getProtocolOnlineReviewId());
        newReviewComment.setProtocolReviewer(protocolOnlineReview.getProtocolReviewer());
        newReviewComment.setProtocolReviewerIdFk(protocolOnlineReview.getProtocolReviewerId());
        addReviewComment(newReviewComment, reviewComments, protocolOnlineReview.getProtocol());
    }

  
    public void moveUpReviewComment(List<CommitteeScheduleMinuteBase> reviewComments, ProtocolBase protocol, int fromIndex) {
        if (fromIndex > 0) {
            int toIndex = indexOfPreviousProtocolReviewComment(reviewComments, protocol, fromIndex);
            if (toIndex < fromIndex) {
                CommitteeScheduleMinuteBase movingReviewComment = reviewComments.remove(fromIndex);
                reviewComments.add(toIndex, movingReviewComment);
                for (int i = toIndex; i <= fromIndex; i++) {
                    reviewComments.get(i).setEntryNumber(i);
                }
            }
        }
    }

    
    
    /**
     * Returns the index of the review comment just before to the one at index, where both of the review comments are in the same
     * protocol.
     * 
     * If there is no such review comment, returns the index of the review comment at index.
     * 
     * @param reviewComments the list of review comments
     * @param protocol the current protocol
     * @param currentIndex the index of the current review comment
     * @return the index of the previous review comment, or the same index if there is none
     */
    private int indexOfPreviousProtocolReviewComment(List<CommitteeScheduleMinuteBase> reviewComments, ProtocolBase protocol,
            int currentIndex) {
        int previousIndex = currentIndex;

        for (ListIterator<CommitteeScheduleMinuteBase> iterator = reviewComments.listIterator(currentIndex); iterator.hasPrevious();) {
            int iteratorIndex = iterator.previousIndex();
            CommitteeScheduleMinuteBase currentReviewComment = iterator.previous();
            if (ObjectUtils.equals(currentReviewComment.getProtocolId(), protocol.getProtocolId())) {
                previousIndex = iteratorIndex;
                break;
            }
        }

        return previousIndex;
    }

    
    
    public void moveDownReviewComment(List<CommitteeScheduleMinuteBase> reviewComments, ProtocolBase protocol, int fromIndex) {
        if (fromIndex < reviewComments.size() - 1) {
            int toIndex = indexOfNextProtocolReviewComment(reviewComments, protocol, fromIndex);
            if (toIndex > fromIndex) {
                CommitteeScheduleMinuteBase movingReviewComment = reviewComments.remove(fromIndex);
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
     * This is true either if 1) The current user has the role IRB Administrator 2) The comment/minute has been accepted by an IRB
     * Administrator and one of the following conditions is true: 3) The current user does not have the role IRB Administrator, but
     * the current user is the comment creator 4) The current user does not have the role IRB Administrator, but is a reviewer of
     * the protocol, and not part of the protocol personnel, and the comment is final 5) The current user does not have the role IRB
     * Administrator, but is an active committee member, and not part of the protocol personnel, and the comment is final 6) The
     * comment is public and final
     * 
     * In addition if the comment is not associated with an online review then it automatically returns true.
     * 
     * @param CommitteeScheduleMinuteBase minute
     * @return whether the current user can view this comment
     */
    public boolean getReviewerCommentsView(ProtocolReviewableBase minute) {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String principalName = GlobalVariables.getUserSession().getPrincipalName();

        if (isAdministrator(principalId)) {
            return true;
        }
        else {
            if (minute.getProtocolOnlineReviewIdFk() != null) {
                // this comment originated via an OLR, so check first that it is accepted and then that it 
                // is either created by current user or 'isViewable' for the current user
                if (minute.isAccepted()) {
                    return StringUtils.equals(principalName, minute.getCreateUser()) || isViewable(minute);
                }
                else {
                    return false;
                }
            }
            else {
                // this comment did not originate in an OLR (most probably added by admin via some non-OLR comments interface), 
                // so check that it 'isViewable' for the current user
                return isViewable(minute);
            }
        }
    }
    

    /*
     * This method is to check if review comment/attachment is viewable for this user
     */
    private boolean isViewable(ProtocolReviewableBase reviewable) {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        return reviewable.isFinal() 
                    && 
                    ( !reviewable.isPrivate() 
                        || 
                        ( (isReviewer(reviewable, principalId) || isActiveCommitteeMember(reviewable, principalId)) 
                               && 
                               (!isProtocolPersonnel(reviewable, principalId) && !hasProtocolPermission(reviewable,  principalId))
                        )
                    );                              
    }

    private boolean isAdministrator(String principalId) {
        RoleService roleService = KcServiceLocator.getService(RoleService.class);
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE,
               getAdministratorRoleHook(), null);
        return ids.contains(principalId);
    }

    protected abstract String getAdministratorRoleHook();
    
    /**
     * Returns the index of the review comment just after to the one at index, where both of the review comments are in the same
     * protocol.
     * 
     * If there is no such review comment, returns the index of the review comment at index.
     * 
     * @param reviewComments the list of review comments
     * @param protocol the current protocol
     * @param currentIndex the index of the current review comment
     * @return the index of the next review comment, or the same index if there is none
     */
    private int indexOfNextProtocolReviewComment(List<CommitteeScheduleMinuteBase> reviewComments, ProtocolBase protocol, int currentIndex) {
        int nextIndex = currentIndex;

        for (ListIterator<CommitteeScheduleMinuteBase> iterator = reviewComments.listIterator(currentIndex + 1); iterator.hasNext();) {
            int iteratorIndex = iterator.nextIndex();
            CommitteeScheduleMinuteBase currentReviewComment = iterator.next();
            if (ObjectUtils.equals(currentReviewComment.getProtocolId(), protocol.getProtocolId())) {
                nextIndex = iteratorIndex;
                break;
            }
        }

        return nextIndex;
    }

    public void deleteReviewComment(List<CommitteeScheduleMinuteBase> reviewComments, int index,
            List<CommitteeScheduleMinuteBase> deletedReviewComments) {
        if (index >= 0 && index < reviewComments.size()) {
            CommitteeScheduleMinuteBase reviewComment = reviewComments.get(index);
            if (reviewComment.getCommScheduleMinutesId() != null) {
                deletedReviewComments.add(reviewComment);
            }
            reviewComments.remove(index);

            for (int i = index; i < reviewComments.size(); i++) {
                reviewComments.get(i).setEntryNumber(i);
            }
        }
    }

    public void deleteAllReviewComments(List<CommitteeScheduleMinuteBase> reviewComments, List<CommitteeScheduleMinuteBase> deletedReviewComments) {
        if (reviewComments != null) {
            for (CommitteeScheduleMinuteBase reviewerComment : reviewComments) {
                if (reviewerComment.getCommScheduleMinutesId() != null) {
                    deletedReviewComments.add(reviewerComment);
                }
            }
            reviewComments.clear();
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes"})
    public void updateScheduleForReviewComments(ProtocolBase protocol, List<CommitteeScheduleMinuteBase> reviewComments) {
    	ProtocolSubmissionBase protocolSubmission = getSubmission(protocol);
    	Long scheduleIdFk = protocolSubmission.getScheduleIdFk();
    	for (CommitteeScheduleMinuteBase reviewComment : reviewComments) {
            if (scheduleIdFk != null) {
            	reviewComment.setScheduleIdFk(scheduleIdFk);
            }
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void saveReviewComments(List<CommitteeScheduleMinuteBase> reviewComments, List<CommitteeScheduleMinuteBase> deletedReviewComments) {
        for (CommitteeScheduleMinuteBase reviewComment : reviewComments) {
            boolean doUpdate = true;
            if (reviewComment.getCommScheduleMinutesId() != null) {
                CommitteeScheduleMinuteBase pristineInstance = reviewComment.getPristineInstance();
                if((pristineInstance != null) && (!reviewComment.equals(pristineInstance))) {
                    doUpdate = true;
                    // we update the user name only if certain important fields have changed
                    if (reviewComment.isUpdateUserToBeRecorded(pristineInstance)) {                            
                        KcPerson kcPerson = KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(GlobalVariables.getUserSession().getPerson().getPrincipalId());
                        reviewComment.setUpdateUserFullName(kcPerson.getFullName());
                    }
                }
                else {
                   doUpdate = false;
                }
            }
            if (doUpdate) {
                businessObjectService.save(reviewComment);
            }
        }

        if (!deletedReviewComments.isEmpty()) {
            businessObjectService.delete(deletedReviewComments);
        }
    }

    
    protected abstract ProtocolSubmissionBase getSubmission(ProtocolBase protocol);
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }

    /*
     * TODO: abstracted out during iacuc refactoring
     */
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
        displayReviewerNameToActiveMembers = isDisplayReviewerName(getDisplayRevNameToActiveCmtMembersHook());
        displayReviewerNameToPersonnel = isDisplayReviewerName(getDisplayRevNameToProtocolPersonnelHook());
        displayReviewerNameToReviewers = isDisplayReviewerName(getDisplayRevNameToReviewersHook());
    }
    
    protected abstract String getDisplayRevNameToActiveCmtMembersHook();
    
    protected abstract String getDisplayRevNameToProtocolPersonnelHook();

    protected abstract String getDisplayRevNameToReviewersHook();

    

    /*
     * retrieve Display reviewer name parameter and compre with 'HIDE'
     */
    private boolean isDisplayReviewerName(String paramName) {
        String param = parameterService.getParameterValueAsString(getProtocolDocumentBOClassHook(), paramName);
        return !StringUtils.equals(HIDE, param);
    }

    protected abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook();
    

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public boolean setHideReviewerName(ProtocolBase protocol, int submissionNumber) {
        return setHideReviewerName(getReviewerComments(protocol.getProtocolNumber(), submissionNumber));
    }

    public boolean setHideReviewerName(List<? extends ProtocolReviewableBase> reviewComments) {
        boolean isHide = true;
        setReviewerIds(reviewerIds);
        getReviewerNameParams();
        for (ProtocolReviewableBase reviewComment : reviewComments) {
            if (canViewName(reviewComment)) {
                reviewComment.setDisplayReviewerName(true);
                isHide = false;
            }

        }
        return isHide;
    }

    public boolean isHidePrivateFinalFlagsForPI(List<? extends ProtocolReviewableBase> reviewComments) {
        boolean isHide = false;        
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        for (ProtocolReviewableBase reviewComment : reviewComments) {
            if (isProtocolPersonnel(reviewComment, principalId) && getReviewerCommentsView(reviewComment)) {
                isHide = true;
                break;
            }           
        }
        return isHide;
    }
    
    public boolean setHideViewButton(List<PRA> reviewAttachments) {
        boolean isHide = true;
        getReviewerNameParams();
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        for (PRA reviewAttachment : reviewAttachments) {
            if (!reviewAttachment.isPrivateFlag() || !isProtocolPersonnel(reviewAttachment, principalId)) {
                reviewAttachment.setDisplayViewButton(true);
                isHide = false;
            }

        }
        return isHide;
    }

    private boolean canViewName(ProtocolReviewableBase reviewComment) {
        boolean canViewName = false;
        Person person = GlobalVariables.getUserSession().getPerson();
        if (isAdmin(person.getPrincipalId()) || isCreator(reviewComment, person.getPrincipalName())) {
            canViewName = true;
        }
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

    private boolean hasProtocolPermission(ProtocolReviewableBase reviewComment, String principalId) {
        return getProtocolAggregators(reviewComment).contains(principalId)
                || getProtocolViewers(reviewComment).contains(principalId);
    }

    private boolean isProtocolPersonnel(ProtocolReviewableBase reviewComment, String principalId) {
        return getPersonnelIds(reviewComment).contains(principalId);
    }

    /*
     * check if user is protocol personnel or has permission as aggregator or viewer
     */
    private boolean isProtocolPersonnelOrHasProtocolRole(ProtocolReviewableBase reviewComment) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(reviewComment).contains(person.getPrincipalId())
                || getProtocolAggregators().contains(person.getPrincipalId())
                || getProtocolViewers().contains(person.getPrincipalId());
    }


    private List<String> getActiveMemberId(ProtocolReviewableBase reviewComment) {
        if (reviewComment.getProtocol() == null) {
            reviewComment.refreshReferenceObject("protocol");
        }
        List<String> activeMemberIds = new ArrayList<String>();
        List<CommitteeMembershipBase> members = new ArrayList<CommitteeMembershipBase>();
        if (reviewComment.isReviewComment()) {
            members = ((CommitteeScheduleMinuteBase) reviewComment).getCommitteeSchedule().getParentCommittee().getCommitteeMemberships();
        }
        else {
            members = ((PRA) reviewComment).getProtocolSubmission().getCommittee().getCommitteeMemberships();
        }
        for (CommitteeMembershipBase member : members) {
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

    private List<String> getPersonnelIds(ProtocolReviewableBase reviewComment) {
        if (reviewComment.getProtocol() == null) {
            reviewComment.refreshReferenceObject("protocol");
        }
        List<String> PersonnelIds = new ArrayList<String>();
        if (reviewComment.getProtocol() != null) {
            for (ProtocolPersonBase person : reviewComment.getProtocol().getProtocolPersons()) {
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

    private boolean isAdmin(String principalId) {
        return !CollectionUtils.isEmpty(getAdminIds()) && getAdminIds().contains(principalId);
    }

    /*
     * if the person a reviewer.
     */
    private boolean isReviewer(ProtocolReviewableBase reviewComment, String principalId) {
        List<String> reviewerIds = getProtocolReviewerIds(reviewComment);
        return !reviewerIds.isEmpty() && reviewerIds.contains(principalId);
    }

    /*
     * if the person is comment creator.
     */
    private boolean isCreator(ProtocolReviewableBase reviewComment, String userName) {
        return reviewComment.getCreateUser().equals(userName);
    }

    /*
     * get the reviewer ids for this submission
     */
    private List<String> getProtocolReviewerIds(ProtocolReviewableBase reviewComment) {
        List<String> reviewerIds = new ArrayList<String>();
        if (reviewComment.getSubmissionNumber() != null) {
            reviewerIds = getProtocolReviewerIds(reviewComment.getProtocolId(), reviewComment.getSubmissionNumber());
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
        for (ProtocolReviewer reviewer : (List<ProtocolReviewer>) businessObjectService.findMatching(getProtocolReviewClassHook(), fieldValues)) {
            reviewerPersonIds.add(reviewer.getPersonId());
        }
        return reviewerPersonIds;

    }
    
    protected abstract Class<? extends ProtocolReviewer> getProtocolReviewClassHook();

    /*
     * retrieve admins from role table
     */
    private void populateAdmins() {
        adminIds = (Set<String>) roleService.getRoleMemberPrincipalIds("KC-UNT", getAdministratorRoleHook(), null);
        adminUserNames = new ArrayList<String>();
        for (String id : adminIds) {
            KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(id);
            adminUserNames.add(kcPerson.getUserName());
        }
    }

    private Set<String> getProtocolAggregators() {
        if (CollectionUtils.isEmpty(aggregatorIds)) {
            aggregatorIds = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(), getAggregatorRoleNameHook(), null);

        }
        return aggregatorIds;

    }
        
    protected abstract String getNamespaceHook();
    
    protected abstract String getAggregatorRoleNameHook();
    
    

    private Set<String> getProtocolAggregators(ProtocolReviewableBase minute) {
        if (CollectionUtils.isEmpty(aggregatorIds) && minute != null) {

            aggregatorIds = new HashSet<String>();

            if (StringUtils.isNotBlank(minute.getProtocolNumber())) {
                Map<String, String> protocolAttr = new HashMap<String, String>();
                protocolAttr.put(KcKimAttributes.PROTOCOL, minute.getProtocolNumber());
                Set<String> protoResults = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(),
                        getAggregatorRoleNameHook(), new HashMap<String, String>(protocolAttr));

                if (CollectionUtils.isNotEmpty(protoResults)) {
                    aggregatorIds.addAll(protoResults);
                }
            }

            if (minute.getProtocol() == null) {
                minute.refreshReferenceObject("protocol");
            }
            if (StringUtils.isNotBlank(minute.getProtocol().getLeadUnitNumber())) {
                Map<String, String> leadUnitAttr = new HashMap<String, String>();
                leadUnitAttr.put(KcKimAttributes.UNIT_NUMBER, minute.getProtocol().getLeadUnitNumber());
                Set<String> leadUnitResults = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(),
                        getAggregatorRoleNameHook(), new HashMap<String, String>(leadUnitAttr));

                if (CollectionUtils.isNotEmpty(leadUnitResults)) {
                    aggregatorIds.addAll(leadUnitResults);
                }
            }


        }

        return aggregatorIds;
    }

    private Set<String> getProtocolViewers() {
        if (CollectionUtils.isEmpty(viewerIds)) {
            viewerIds = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(), getProtocolViewerRoleNameHook(), null);

        }
        return viewerIds;

    }
    
    protected abstract String getProtocolViewerRoleNameHook();
    

    private Set<String> getProtocolViewers(ProtocolReviewableBase minute) {
        if (CollectionUtils.isEmpty(viewerIds) && minute != null) {

            viewerIds = new HashSet<String>();

            if (StringUtils.isNotBlank(minute.getProtocolNumber())) {
                Map<String, String> protocolAttr = new HashMap<String, String>();
                /*
                 * IS the kim attr data for iacuc also under 'protocol'?? not sure, need to verify that
                 */
                protocolAttr.put(KcKimAttributes.PROTOCOL, minute.getProtocolNumber());
                Set<String> protoResults = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(),
                        getProtocolViewerRoleNameHook(), new HashMap<String, String>(protocolAttr));

                if (CollectionUtils.isNotEmpty(protoResults)) {
                    viewerIds.addAll(protoResults);
                }
            }

            if (minute.getProtocol() == null) {
                minute.refreshReferenceObject("protocol");
            }
            if (StringUtils.isNotBlank(minute.getProtocol().getLeadUnitNumber())) {
                Map<String, String> leadUnitAttr = new HashMap<String, String>();
                leadUnitAttr.put(KcKimAttributes.UNIT_NUMBER, minute.getProtocol().getLeadUnitNumber());
                Set<String> leadUnitResults = (Set<String>) roleService.getRoleMemberPrincipalIds(getNamespaceHook(),
                        getProtocolViewerRoleNameHook(), new HashMap<String, String>(leadUnitAttr));

                if (CollectionUtils.isNotEmpty(leadUnitResults)) {
                    viewerIds.addAll(leadUnitResults);
                }
            }
        }

        return viewerIds;

    }

    public void setKimRoleManagementService(RoleService kimRoleManagementService) {
        this.roleService = kimRoleManagementService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public Set<String> getAdminIds() {
        if (CollectionUtils.isEmpty(adminIds)) {
            populateAdmins();
        }
        return adminIds;
    }

    public void setAdminIds(Set<String> adminIds) {
        this.adminIds = adminIds;
    }

    public List<String> getAdminUserNames() {
        if (CollectionUtils.isEmpty(adminUserNames)) {
            populateAdmins();
        }
        return adminUserNames;
    }

    public void setAdminUserNames(List<String> adminUserNames) {
        this.adminUserNames = adminUserNames;
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
     * 
     * @param minute
     * @param principalId
     * @return true if and active committee member, false otherwise.
     */
    @SuppressWarnings("rawtypes")
    protected boolean isActiveCommitteeMember(ProtocolReviewableBase minute, String principalId) {
        String committeeId = "";
        String scheduleId = "";
        CommitteeScheduleBase committeeSchedule = minute.getCommitteeSchedule(); 
        if( committeeSchedule != null) {
            committeeId = minute.getCommitteeSchedule().getParentCommittee().getCommitteeId();
            scheduleId =  minute.getCommitteeSchedule().getScheduleId();
        }
        return isActiveCommitteeMember(committeeId, scheduleId, principalId);
    }
    
    
    @SuppressWarnings("unchecked")
    protected boolean isActiveCommitteeMember(String committeeId, String scheduleId, String principalId) {
        boolean result = false;
        List<CommitteeMembershipBase> committeeMembers = committeeService.getAvailableMembers(committeeId, scheduleId);
        if (CollectionUtils.isNotEmpty(committeeMembers)) {
            for (CommitteeMembershipBase member : committeeMembers) {
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
     * This method determines if the current user is an active committee member using the commitee and schedule ids
     * obtained from the submission parameter
     * 
     * @param submission
     * @param principalId
     * @return true if and active committee member, false otherwise.
     */
    private boolean isActiveCommitteeMember(ProtocolSubmissionBase submission, String principalId) {
        String committeeId = submission.getCommitteeId();
        String scheduleId =  submission.getScheduleId();
        return isActiveCommitteeMember(committeeId, scheduleId, principalId);
    }

 
    protected abstract Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewClassHook();
    

    public void deleteReviewAttachment(List<PRA> reviewAttachments, int index,
            List<PRA> deletedReviewAttachments) {
        if (index >= 0 && index < reviewAttachments.size()) {
            PRA reviewAttachment = reviewAttachments.get(index);
            if (reviewAttachment.getReviewerAttachmentId() != null) {
                deletedReviewAttachments.add(reviewAttachment);
            }
            reviewAttachments.remove(index);
        }
    }

    public abstract void saveReviewAttachments(List<PRA> reviewAttachments, List<PRA> deletedReviewAttachments);
    
    

    
    public void addReviewAttachment(PRA newReviewAttachment, List<PRA> reviewAttachments,
            ProtocolBase protocol) {
        ProtocolSubmissionBase protocolSubmission = getSubmission(protocol);
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
     * get next attachmentId. it seems coeus is just increasing by 1, no matter what protocol is. but attachment_id is only
     * number(3). so, need further investigation.
     */
    private int getNextAttachmentId(ProtocolBase protocol) {
        Map fieldValues = new HashMap();
        fieldValues.put("protocolIdFk", protocol.getProtocolId());
        List<PRA> reviewAttachments = (List<PRA>) businessObjectService
                .findMatchingOrderBy(getProtocolReviewAttachmentClassHook(), fieldValues, "attachmentId", false);
        if (CollectionUtils.isEmpty(reviewAttachments)) {
            return 1;
        }
        else {
            return reviewAttachments.get(0).getAttachmentId() + 1;
        }

    }
    

    protected abstract Class<PRA> getProtocolReviewAttachmentClassHook();



    @Override
    public void deleteAllReviewAttachments(List<PRA> reviewAttachments,
            List<PRA> deletedReviewAttachments) {

        if (reviewAttachments != null) {
            for (PRA reviewerAttachment : reviewAttachments) {
                if (reviewerAttachment.getReviewerAttachmentId() != null) {
                    deletedReviewAttachments.add(reviewerAttachment);
                }
            }
            reviewAttachments.clear();
        }
    }

    public ProtocolFinderDao getProtocolFinderDao() {
        return protocolFinderDao;
    }


}
