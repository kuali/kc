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

import java.util.List;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucReviewCommentsServiceImpl implements IacucReviewCommentsService {
    private BusinessObjectService businessObjectService;
    private CommitteeScheduleService committeeScheduleService;
    private CommitteeService committeeService;
    private RoleService roleService;
    private DateTimeService dateTimeService;
    private ParameterService parameterService;
    private KcPersonService kcPersonService;
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


}
