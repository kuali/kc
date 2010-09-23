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

import org.eclipse.jetty.util.log.Log;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Implementation of the ProtocolAssignReviewersService.
 */
public class ProtocolAssignReviewersServiceImpl implements ProtocolAssignReviewersService {
    
    private BusinessObjectService businessObjectService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolAssignReviewersServiceImpl.class);
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService#assignReviewers(org.kuali.kra.irb.actions.submit.ProtocolSubmission, 
     *      java.util.List)
     */
    public void assignReviewers(ProtocolSubmission protocolSubmission, List<ProtocolReviewerBean> protocolReviewerBeans)  {
        if (protocolSubmission != null) {
            for (ProtocolReviewerBean bean : protocolReviewerBeans) {
                if (bean.getChecked()) {
                    if (!protocolOnlineReviewService.isProtocolReviewer(bean.getPersonId(), bean.getNonEmployeeFlag(), protocolSubmission)) {
                        createReviewer(protocolSubmission, bean);
                    } else {
                        updateReviewer(protocolSubmission, bean);
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
    
    private void removeReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewBean,String annotation) {
        protocolOnlineReviewService.removeOnlineReviewDocument(protocolReviewBean.getPersonId(), protocolReviewBean.getNonEmployeeFlag(), protocolSubmission, annotation);
    }
    
    private void createReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewerBean) {
        String principalId = protocolReviewerBean.getPersonId();
        boolean nonEmployeeFlag = protocolReviewerBean.getNonEmployeeFlag();
        String reviewerTypeCode = protocolReviewerBean.getReviewerTypeCode();
        ProtocolReviewer reviewer = protocolOnlineReviewService.createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, protocolSubmission);
        
        String piLastName = protocolSubmission.getProtocol().getPrincipalInvestigator().getPerson().getLastName();
        String protocolNumber = protocolSubmission.getProtocolNumber();
        String description = String.format("%s/Protocol# %s", piLastName, protocolNumber);
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
    }
    
    private void updateReviewer(ProtocolSubmission protocolSubmission, ProtocolReviewerBean protocolReviewerBean) {
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
    
}