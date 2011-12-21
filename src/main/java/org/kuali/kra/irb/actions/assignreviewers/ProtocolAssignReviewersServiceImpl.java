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
package org.kuali.kra.irb.actions.assignreviewers;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Implementation of the ProtocolAssignReviewersService.
 */
public class ProtocolAssignReviewersServiceImpl implements ProtocolAssignReviewersService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private KcNotificationService kcNotificationService;

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService#assignReviewers(org.kuali.kra.irb.actions.submit.ProtocolSubmission, 
     *      java.util.List)
     */
    public void assignReviewers(ProtocolSubmission protocolSubmission, List<ProtocolReviewerBean> protocolReviewerBeans) throws Exception  {
        if (protocolSubmission != null) {
            for (ProtocolReviewerBean bean : protocolReviewerBeans) {
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
    
    protected void removeReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewBean,String annotation) {
        //We need to send the notification prior to the online review being removed in order to satisfy the kim role recipients requirements
        ProtocolOnlineReviewDocument onlineReviewDocument = 
            protocolOnlineReviewService.getProtocolOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission);
        if (onlineReviewDocument != null) {   
            Protocol protocol = protocolSubmission.getProtocol();
            ProtocolOnlineReview protocolOnlineReview = onlineReviewDocument.getProtocolOnlineReview();
            protocolReviewBean.setNotificationRequestBean(new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
            protocolReviewBean.setActionFlag(ProtocolReviewerBean.REMOVE);
            AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "removed");
            IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
            if (!getPromptUserForNotificationEditor(context)) {
                kcNotificationService.sendNotification(context);
            }
        }
        
        protocolOnlineReviewService.removeOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission, annotation);
    }
    
    private boolean getPromptUserForNotificationEditor(IRBNotificationContext context) {
        boolean promptUser = false;
        
        NotificationType notificationType = kcNotificationService.getNotificationType(context);
        if (notificationType != null && notificationType.getSendNotification() && notificationType.getPromptUser()) {
            promptUser = true;
        }
        
        return promptUser;
    }

    protected void createReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewerBean) {
        String principalId = protocolReviewerBean.getPersonId();
        boolean nonEmployeeFlag = protocolReviewerBean.getNonEmployeeFlag();
        String reviewerTypeCode = protocolReviewerBean.getReviewerTypeCode();
        ProtocolReviewer reviewer = protocolOnlineReviewService.createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, protocolSubmission);
        ProtocolPerson protocolPerson = protocolSubmission.getProtocol().getPrincipalInvestigator();
        String protocolNumber = protocolSubmission.getProtocol().getProtocolNumber();
        String description = protocolOnlineReviewService.getProtocolOnlineReviewDocumentDescription(protocolNumber, protocolPerson.getLastName());
        String explanation = Constants.EMPTY_STRING;
        String organizationDocumentNumber = Constants.EMPTY_STRING;
        String routeAnnotation = "Online Review Requested by PI during protocol submission.";
        boolean initialApproval = false;
        Date dateRequested = null;
        Date dateDue = null;
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolOnlineReviewDocument document = protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument(protocolSubmission, reviewer, 
                description, explanation, organizationDocumentNumber, routeAnnotation, initialApproval, dateRequested, dateDue, sessionPrincipalId);
    
        protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
        
        //send notification now that the online review has been created.
        Protocol protocol = protocolSubmission.getProtocol();
        ProtocolOnlineReview protocolOnlineReview = document.getProtocolOnlineReview();
        protocolReviewerBean.setNotificationRequestBean(new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
        protocolReviewerBean.setActionFlag(ProtocolReviewerBean.CREATE);
        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "added");
        IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
        if (!getPromptUserForNotificationEditor(context)) {
            kcNotificationService.sendNotification(context);
        }
    }
    
    protected void updateReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewerBean) {
        ProtocolReviewer reviewer = protocolOnlineReviewService.getProtocolReviewer(protocolReviewerBean.getPersonId(), protocolReviewerBean.getNonEmployeeFlag(), protocolSubmission);
        reviewer.setReviewerTypeCode(protocolReviewerBean.getReviewerTypeCode());
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