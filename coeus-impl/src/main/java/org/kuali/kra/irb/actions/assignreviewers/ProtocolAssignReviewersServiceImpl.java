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
package org.kuali.kra.irb.actions.assignreviewers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBProtocolNotification;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.List;

/**
 * Implementation of the ProtocolAssignReviewersService.
 */
public class ProtocolAssignReviewersServiceImpl implements ProtocolAssignReviewersService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private KcNotificationService kcNotificationService;

    @Override
    public void assignReviewers(ProtocolSubmissionBase protocolSubmission, List<ProtocolReviewerBeanBase> protocolReviewerBeans) throws Exception  {
        if (protocolSubmission != null) {
            for (ProtocolReviewerBeanBase bean : protocolReviewerBeans) {
                if (StringUtils.isNotBlank(bean.getReviewerTypeCode())) {
                    if (!protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        
                        createReviewer(protocolSubmission, bean);
                    } else {
                        updateReviewer(protocolSubmission, bean);
                        bean.setActionFlag(ProtocolReviewerBean.UPDATE);
                    }
                } else {
                    //need to check if this person is currently a reviewer...
                    if (protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        removeReviewer(protocolSubmission,bean,"REVIEW REMOVED FROM ASSIGN REVIEWERS ACTION.");
                    }
                }
            }
           
            businessObjectService.save(protocolSubmission); 
        }
    }
    
    protected void removeReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase bean,String annotation) {
        //We need to send the notification prior to the online review being removed in order to satisfy the kim role recipients requirements
        ProtocolOnlineReviewDocument onlineReviewDocument = 
            (ProtocolOnlineReviewDocument) protocolOnlineReviewService.getProtocolOnlineReviewDocument(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission);
        if (onlineReviewDocument != null) {   
            Protocol protocol = (Protocol) protocolSubmission.getProtocol();
            ProtocolOnlineReview protocolOnlineReview = (ProtocolOnlineReview) onlineReviewDocument.getProtocolOnlineReview();
            bean.setNotificationRequestBean(new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
            bean.setActionFlag(ProtocolReviewerBean.REMOVE);
            AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "removed");
            IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
            if (!getPromptUserForNotificationEditor(context)) {
                kcNotificationService.sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
            }
        }
        
        protocolOnlineReviewService.removeOnlineReviewDocument(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission, annotation);
    }
    
    private boolean getPromptUserForNotificationEditor(IRBNotificationContext context) {
        boolean promptUser = false;
        
        NotificationType notificationType = kcNotificationService.getNotificationType(context);
        if (notificationType != null && notificationType.isActive() && notificationType.getPromptUser()) {
            promptUser = true;
        }
        
        return promptUser;
    }

    protected void createReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase bean) {
        String principalId = bean.getPersonId();
        boolean nonEmployeeFlag = bean.getNonEmployeeFlag();
        String reviewerTypeCode = bean.getReviewerTypeCode();
        ProtocolReviewer reviewer = (ProtocolReviewer) protocolOnlineReviewService.createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, protocolSubmission);
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolSubmission.getProtocol().getPrincipalInvestigator();
        String protocolNumber = protocolSubmission.getProtocol().getProtocolNumber();
        String description = protocolOnlineReviewService.getProtocolOnlineReviewDocumentDescription(protocolNumber, protocolPerson.getLastName());
        String explanation = Constants.EMPTY_STRING;
        String organizationDocumentNumber = Constants.EMPTY_STRING;
        String routeAnnotation = "Online Review Requested by PI during protocol submission.";
        boolean initialApproval = false;
        Date dateRequested = null;
        
        Date dateDue = assignDefaultDueDate(protocolSubmission);
        
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolOnlineReviewDocument document = (ProtocolOnlineReviewDocument) protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument(protocolSubmission, reviewer, 
                description, explanation, organizationDocumentNumber, routeAnnotation, initialApproval, dateRequested, dateDue, sessionPrincipalId);
    
        protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
        
        //send notification now that the online review has been created.
        Protocol protocol = (Protocol) protocolSubmission.getProtocol();
        ProtocolOnlineReview protocolOnlineReview = (ProtocolOnlineReview) document.getProtocolOnlineReview();
        bean.setNotificationRequestBean(new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
        bean.setActionFlag(ProtocolReviewerBean.CREATE);
        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "added");
        IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
        if (!getPromptUserForNotificationEditor(context)) {
            kcNotificationService.sendNotificationAndPersist(context, new IRBProtocolNotification(), protocol);
        }
    }
    
    private Date assignDefaultDueDate(ProtocolSubmissionBase protocolSubmission) {
        Date dueDate = null;
        CommitteeBase committee = protocolSubmission.getCommittee();
        if(committee != null) {
            CommitteeScheduleBase schedule = committee.getCommitteeSchedule(protocolSubmission.getScheduleId());
            if(schedule != null) {
                dueDate = schedule.getScheduledDate();
            }
        }
        return dueDate;
    }

    protected void updateReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase bean) {
        ProtocolReviewer reviewer = (ProtocolReviewer) protocolOnlineReviewService.getProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission);
        reviewer.setReviewerTypeCode(bean.getReviewerTypeCode());
        businessObjectService.save(reviewer);
    }

    /**
     * Set the Business Object Service.
     * @param businessObjectService businessObjectService.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Set the Protocol Online Review Service.
     * @param protocolOnlineReviewService protocolOnlineReviewService.
     */
    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }
    
}
