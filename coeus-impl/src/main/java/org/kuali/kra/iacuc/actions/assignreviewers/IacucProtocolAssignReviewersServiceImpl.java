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
package org.kuali.kra.iacuc.actions.assignreviewers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.List;

public class IacucProtocolAssignReviewersServiceImpl implements IacucProtocolAssignReviewersService{
    private BusinessObjectService businessObjectService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;

    public void assignReviewers(ProtocolSubmissionBase protocolSubmission, List<ProtocolReviewerBeanBase> protocolReviewerBeans) throws Exception  {
        if (protocolSubmission != null) {
            for (ProtocolReviewerBeanBase bean : protocolReviewerBeans) {
                if (StringUtils.isNotBlank(bean.getReviewerTypeCode())) {
                    if (!protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        
                        createReviewer(protocolSubmission, bean);
                    } else {
                        updateReviewer(protocolSubmission, bean);
                        bean.setActionFlag(ProtocolReviewerBeanBase.UPDATE);
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
    
    protected void removeReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewBean,String annotation) {
        //We need to send the notification prior to the online review being removed in order to satisfy the kim role recipients requirements
        ProtocolOnlineReviewDocumentBase onlineReviewDocument = 
            protocolOnlineReviewService.getProtocolOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission);
        if (onlineReviewDocument != null) {   
            ProtocolBase protocol = protocolSubmission.getProtocol();
            ProtocolOnlineReviewBase protocolOnlineReview = onlineReviewDocument.getProtocolOnlineReview();
        }
        
        protocolOnlineReviewService.removeOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission, annotation);
    }
    
    protected void createReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewerBean) {
        String principalId = protocolReviewerBean.getPersonId();
        boolean nonEmployeeFlag = protocolReviewerBean.getNonEmployeeFlag();
        String reviewerTypeCode = protocolReviewerBean.getReviewerTypeCode();
        ProtocolReviewer reviewer = protocolOnlineReviewService.createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, protocolSubmission);
        ProtocolPersonBase protocolPerson = protocolSubmission.getProtocol().getPrincipalInvestigator();
        String protocolNumber = protocolSubmission.getProtocol().getProtocolNumber();
        String description = protocolOnlineReviewService.getProtocolOnlineReviewDocumentDescription(protocolNumber, protocolPerson.getLastName());
        String explanation = Constants.EMPTY_STRING;
        String organizationDocumentNumber = Constants.EMPTY_STRING;
        String routeAnnotation = "Online Review Requested by PI during protocol submission.";
        boolean initialApproval = false;
        Date dateRequested = null;
        Date dateDue = null;
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolOnlineReviewDocumentBase document = protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument(protocolSubmission, reviewer, 
                description, explanation, organizationDocumentNumber, routeAnnotation, initialApproval, dateRequested, dateDue, sessionPrincipalId);
    
        protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
        
        //send notification now that the online review has been created.
        ProtocolBase protocol = protocolSubmission.getProtocol();
        ProtocolOnlineReviewBase protocolOnlineReview = document.getProtocolOnlineReview();
    }
    
    protected void updateReviewer(ProtocolSubmissionBase protocolSubmission, ProtocolReviewerBeanBase protocolReviewerBean) {
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
     * Set the ProtocolBase Online Review Service.
     * @param protocolOnlineReviewService protocolOnlineReviewService.
     */
    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

}
