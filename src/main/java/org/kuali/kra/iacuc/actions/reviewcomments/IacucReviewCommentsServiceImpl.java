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
package org.kuali.kra.iacuc.actions.reviewcomments;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucReviewCommentsServiceImpl implements IacucReviewCommentsService {
    private static final String HIDE = "0";
    private BusinessObjectService businessObjectService;
    private CommitteeScheduleService committeeScheduleService;
    private CommitteeService committeeService;
    private RoleService roleService;
    private DateTimeService dateTimeService;
    private ParameterService parameterService;
    private KcPersonService kcPersonService;
    private boolean displayReviewerNameToPersonnel;
    private boolean displayReviewerNameToReviewers;
    private boolean displayReviewerNameToActiveMembers;
    private List<String> reviewerIds;
    private Set<String> irbAdminIds;

    public void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, Protocol protocol) {
        ProtocolSubmission protocolSubmission = getSubmission(protocol);
        if (protocolSubmission.getScheduleIdFk() != null) {
            newReviewComment.setScheduleIdFk(protocolSubmission.getScheduleIdFk());
        } else {
            newReviewComment.setScheduleIdFk(CommitteeSchedule.DEFAULT_SCHEDULE_ID);
        }
        newReviewComment.setEntryNumber(reviewComments.size());
        newReviewComment.setProtocolIdFk(protocol.getProtocolId());
        // TODO : need to make irb & iacuc protocol have a common interface or parent class
        newReviewComment.setIacucProtocol((IacucProtocol)protocol);
        newReviewComment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        newReviewComment.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
        newReviewComment.setCreateTimestamp(dateTimeService.getCurrentTimestamp());
        newReviewComment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        // TO show update timestamp after 'add'
        newReviewComment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());

        reviewComments.add(newReviewComment);
    }

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

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService#addReviewComment(org.kuali.kra.meeting.CommitteeScheduleMinute, java.util.List, 
     *      org.kuali.kra.irb.onlinereview.ProtocolOnlineReview)
     */
    public void addReviewComment(CommitteeScheduleMinute newReviewComment, List<CommitteeScheduleMinute> reviewComments, 
            ProtocolOnlineReview protocolOnlineReview) {
        newReviewComment.setIacucProtocolOnlineReview((IacucProtocolOnlineReview)protocolOnlineReview);
        newReviewComment.setProtocolOnlineReviewIdFk(protocolOnlineReview.getProtocolOnlineReviewId());
        newReviewComment.setIacucProtocolReviewer((IacucProtocolReviewer)protocolOnlineReview.getProtocolReviewer());
        newReviewComment.setProtocolReviewerIdFk(protocolOnlineReview.getProtocolReviewerId());
        addReviewComment(newReviewComment, reviewComments, protocolOnlineReview.getProtocol());
    }

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

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setCommitteeScheduleService(CommitteeScheduleService committeeScheduleService) {
        this.committeeScheduleService = committeeScheduleService;
    }

    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

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
        List<IacucProtocolReviewAttachment> reviewAttachments =(List<IacucProtocolReviewAttachment>)businessObjectService.findMatchingOrderBy(IacucProtocolReviewAttachment.class, fieldValues, "attachmentId", false);
        if (CollectionUtils.isEmpty(reviewAttachments)) {
            return 1;
        } else {
            return reviewAttachments.get(0).getAttachmentId() + 1;
        }
        
    }

    public void deleteAllReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments,
            List<ProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachment reviewerAttachment : reviewAttachments) {
            if (reviewerAttachment.getReviewerAttachmentId() != null) {
                deletedReviewAttachments.add(reviewerAttachment);
            }
        }
        reviewAttachments.clear();
        
    }

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
//            for (ProtocolReviewAttachment reviewAttachment : deletedReviewAttachments) {
//                businessObjectService.delete((IacucProtocolReviewAttachment)reviewAttachment);
//            }
            // TODO : bos expecting the object defined in repository
            businessObjectService.delete(deletedReviewAttachments);
        }
    }


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
    

}
