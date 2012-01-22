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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerType;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewStatus;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Validate the assignment of a protocol to some reviewers.
 */
public class ProtocolAssignReviewersRule extends ResearchDocumentRuleBase implements ExecuteProtocolAssignReviewersRule {
   
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    
    
    /**{@inheritDoc}**/
    public boolean processAssignReviewers(ProtocolDocument document, ProtocolAssignReviewersBean actionBean) {
        boolean isValid = true;
        List<ProtocolReviewerBean> reviewers = actionBean.getReviewers();
        List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments = getProtocolOnlineReviewService().getProtocolReviewDocumentsForCurrentSubmission(document.getProtocol()); 
        for (int i = 0; i < reviewers.size(); i++) {
            ProtocolReviewerBean reviewer = reviewers.get(i);
            if (!isReviewerValid(reviewer, i)) {
                isValid = false;
            } else if (StringUtils.isBlank(reviewer.getReviewerTypeCode())) {
                //get the review
                for (ProtocolOnlineReviewDocument pDocument : protocolOnlineReviewDocuments) {
                    if (reviewer.isProtocolReviewerBeanForReviewer(pDocument.getProtocolOnlineReview().getProtocolReviewer())) {
                        //the review exists and the user is asking to remove it...
                        isValid &= isValidRemovalRequest(pDocument, reviewer, i);
                        break;
                    }
                }
            }
        }
        
        return isValid;
    }

    /**
     * This method tests if the fields for a given reviewer have legal values.
     * @param reviewer
     * @param reviewerIndex - the index of the reviewer in the list of reviewers that was sent to the client
     */
    private boolean isReviewerValid(ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        String reviewerTypeCode = reviewer.getReviewerTypeCode();
        
        String propertyName =  Constants.PROTOCOL_ASSIGN_REVIEWERS_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode";
        //R
        
        // test if type code is valid
        if (!StringUtils.isBlank(reviewerTypeCode) && isReviewerTypeInvalid(reviewerTypeCode)) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID, reviewer.getFullName());
        }
        
        return isValid;
    }
    
    /*
     * 
     */
    
    public boolean isValidRemovalRequest(ProtocolOnlineReviewDocument document, ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        String propertyName =  Constants.PROTOCOL_ASSIGN_REVIEWERS_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode";
        String documentRouteStatus = workflowDocument.getStatus().getCode();
        //1. check to see the workflow status
        if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD,documentRouteStatus)) {
            //we just report the warning, the request is still valid - the action should prompt for confirmation.
            reportWarning(propertyName,KeyConstants.ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_FINAL, reviewer.getFullName());
        } 
        
        if (document.getProtocolOnlineReview().getCommitteeScheduleMinutes().size() > 0) {
            //there are review comments that will be deleted by this operation
            //just report the warning.
            reportWarning(propertyName,KeyConstants.ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_REVIEW_EXISTING_COMMENTS,reviewer.getFullName(),""+document.getProtocolOnlineReview().getCommitteeScheduleMinutes().size());
        }
        
        if (StringUtils.equals(document.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode(), ProtocolOnlineReviewStatus.FINAL_STATUS_CD)) {
            reportWarning(propertyName, KeyConstants.ERROR_PROTOCOL_REVIEWER_CANNOT_REMOVE_FINAL_REVIEW, reviewer.getFullName());
        }
        
        return isValid;
    }   
    
    private boolean isReviewerTypeInvalid(String reviewerTypeCode) {
        return !existsUnique(ProtocolReviewerType.class, "reviewerTypeCode", reviewerTypeCode);
    }
    
    /**
     * Returns true if exactly one instance of a given business object type
     * exists in the Database; false otherwise.
     * @param boType
     * @param propertyName the name of the BO field to query
     * @param keyField the field to test against.
     * @return true if one object exists; false if no objects or more than one are found
     */
    private boolean existsUnique(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        if (keyField != null) {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put(propertyName, keyField);
            if (businessObjectService.countMatching(boType, fieldValues) == 1) {
                return true;
            }
        }
        return false;
    }

    /*
     * get the ProtocolOnlineReview service
     */

    private ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        if (protocolOnlineReviewService == null) {
            protocolOnlineReviewService = KraServiceLocator.getService(ProtocolOnlineReviewService.class);
        }
        return protocolOnlineReviewService;
    }
    
    
}
