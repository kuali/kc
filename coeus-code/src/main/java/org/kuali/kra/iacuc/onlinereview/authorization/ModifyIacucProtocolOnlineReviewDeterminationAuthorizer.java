/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Calendar;

public class ModifyIacucProtocolOnlineReviewDeterminationAuthorizer extends IacucProtocolOnlineReviewAuthorizer {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ModifyIacucProtocolOnlineReviewDeterminationAuthorizer.class);

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, IacucProtocolOnlineReviewTask task) {
        boolean hasPermission = false;
        IacucProtocolOnlineReview protocolOnlineReview = (IacucProtocolOnlineReview) task.getProtocolOnlineReview();
        ProtocolOnlineReviewDocumentBase protocolDoc = null;
        try {
            protocolDoc = (ProtocolOnlineReviewDocumentBase) KcServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
            
            if ( protocolOnlineReview.getProtocolOnlineReviewId() != null 
                    && !protocolOnlineReview.getProtocolOnlineReviewDocument().isViewOnly()) {
                if (hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_IACUC_ONLINE_REVIEWS) 
                        && !protocolDoc.getDocumentHeader().getWorkflowDocument().isFinal()) {
                    hasPermission = true;
                } else if (hasPermission(userId, protocolOnlineReview, PermissionConstants.MAINTAIN_IACUC_PROTOCOL_ONLINE_REVIEW_COMMENTS)
                        && kraWorkflowService.isUserApprovalRequested(protocolDoc, userId)) {
                    String reviewerTypeCode = protocolOnlineReview.getProtocolReviewer().getReviewerTypeCode();
                    if (StringUtils.equals(reviewerTypeCode, IacucProtocolReviewerType.PRIMARY)
                            || StringUtils.equals(reviewerTypeCode, IacucProtocolReviewerType.SECONDARY)) {
                        hasPermission = true;
                    } else if (StringUtils.equals(reviewerTypeCode, IacucProtocolReviewerType.COMMITTEE)) {
                        if (protocolOnlineReview.getDeterminationReviewDateDue() != null) {
                            Calendar today = Calendar.getInstance();
                            Calendar typeDueDate = Calendar.getInstance();
                            typeDueDate.setTime(protocolOnlineReview.getDeterminationReviewDateDue());
                            hasPermission = typeDueDate.before(today);
                        } else {
                            hasPermission = true;
                        }
                    }
                }
            }
        }
        catch (WorkflowException e) {
            LOG.error(String.format("Could not find ProtocolOnlineReviewBase, document number %s",protocolOnlineReview.getProtocolOnlineReviewDocument().getDocumentNumber()));
        }
        return hasPermission;
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}
