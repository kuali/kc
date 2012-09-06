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
package org.kuali.kra.iacuc.actions.modifysubmission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.IacucProtocolOnlineReviewDocument;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionQualifierType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewStatus;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolModifySubmissionRuleImpl extends ResearchDocumentRuleBase implements IacucProtocolModifySubmissionRule {
  
    private IacucProtocolOnlineReviewService protocolOnlineReviewService;
    
    private static final String PRIMARY_REVIEWER_TYPE = "1";
    private static final String SECONDARY_REVIEWER_TYPE = "2";
    

    public boolean processModifySubmissionRule(ProtocolDocument document, IacucProtocolModifySubmissionBean actionBean) {
        boolean valid = true;
        String errorParameters = null;
        if (StringUtils.isBlank(actionBean.getProtocolReviewTypeCode())) {
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED, errorParameters);
            valid = false;
        } else {
           
        }
        if (StringUtils.isBlank(actionBean.getSubmissionTypeCode())) {
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED, errorParameters);
            valid = false;
        }
        if (StringUtils.isNotBlank(actionBean.getSubmissionTypeCode())) {
            valid &= isValidSubmTypeQual(actionBean);
            if (StringUtils.isNotBlank(actionBean.getProtocolReviewTypeCode())) {
                valid &= isValidSubmReviewType(actionBean);
            }
        }
        
        valid &= validAssignReviewers(document, actionBean);
        return valid;
    }
    
    public boolean validAssignReviewers(ProtocolDocument document, IacucProtocolModifySubmissionBean actionBean) {
        boolean isValid = true;
        int totalValidReviewers = 0;
                
        List<ProtocolReviewerBean> reviewers = actionBean.getReviewers();
        
        // Cannot have assigned reviewers with no schedule
        if (StringUtils.isBlank(actionBean.getScheduleId()) &&  reviewers != null && reviewers.size() > 0) {
            isValid=false;
        }

        List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments = getProtocolOnlineReviewService().getProtocolReviewDocumentsForCurrentSubmission(document.getProtocol()); 
        for (int i = 0; i < reviewers.size(); i++) {
            ProtocolReviewerBean reviewer = reviewers.get(i);
            if (ObjectUtils.isNotNull(reviewer.getReviewerTypeCode())) {
                if(reviewer.getReviewerTypeCode().equals(PRIMARY_REVIEWER_TYPE) || reviewer.getReviewerTypeCode().equals(SECONDARY_REVIEWER_TYPE)) {
                    totalValidReviewers++;
                }
            }
            if (!isReviewerValid(reviewer, i)) {
                isValid = false;
            } else if (StringUtils.isBlank(reviewer.getReviewerTypeCode())) {
                //get the review
                for (ProtocolOnlineReviewDocument pDocument : protocolOnlineReviewDocuments) {
                    if (reviewer.isProtocolReviewerBeanForReviewer(pDocument.getProtocolOnlineReview().getProtocolReviewer())) {
                        //the review exists and the user is asking to remove it...
                        isValid &= isValidRemovalRequest(pDocument, reviewer, i);
                       // break;
                    }
                }
            }
        }
        
        if(actionBean.getProtocolReviewTypeCode().equals(IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW) &&
                totalValidReviewers == 0) {
            IacucProtocolReviewType protocolReviewType = (IacucProtocolReviewType)getBo(IacucProtocolReviewType.class, "reviewTypeCode", actionBean.getProtocolReviewTypeCode());
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_REVIEWER_MISMATCH, protocolReviewType.getDescription());
            isValid = false;
        }
        return isValid;
    }
    
    public boolean isValidRemovalRequest(ProtocolOnlineReviewDocument document, ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        String propertyName =  Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode";
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
    
    private boolean isReviewerValid(ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        String reviewerTypeCode = reviewer.getReviewerTypeCode();
        
        String propertyName =  Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode";
        //R
        
        // test if type code is valid
        if (!StringUtils.isBlank(reviewerTypeCode) && isReviewerTypeInvalid(reviewerTypeCode)) {
            isValid = false;
            reportError(propertyName, KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID, reviewer.getFullName());
        }
        
        return isValid;
    }
    
    private boolean isReviewerTypeInvalid(String reviewerTypeCode) {
        return !existsUnique(IacucProtocolReviewerType.class, "reviewerTypeCode", reviewerTypeCode);
    }
    
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

    private boolean isValidSubmReviewType(IacucProtocolModifySubmissionBean submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {
            /*
             * Do iacuc protocols have valid sub review types???
             */
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            
            List<IacucValidProtoSubRevType> validProtoSubRevTypes = (List<IacucValidProtoSubRevType>) getBusinessObjectService()
                    .findMatching(IacucValidProtoSubRevType.class, fieldValues);
            if (!validProtoSubRevTypes.isEmpty()) {
                List<String> reviewTypes = new ArrayList<String>();
                for (IacucValidProtoSubRevType validProtoSubRevType : validProtoSubRevTypes) {
                    reviewTypes.add(validProtoSubRevType.getProtocolReviewTypeCode());
                }
                if (!reviewTypes.contains(submitAction.getProtocolReviewTypeCode())) {
                    GlobalVariables.getMessageMap().putError(Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".protocolReviewTypeCode",
                            KeyConstants.INVALID_SUBMISSION_REVIEW_TYPE,
                            new String[] { ((IacucProtocolSubmissionType)getBo(IacucProtocolSubmissionType.class, "submissionTypeCode", submitAction.getSubmissionTypeCode())).getDescription(), 
                            ((IacucProtocolReviewType)getBo(IacucProtocolReviewType.class, "reviewTypeCode", submitAction.getProtocolReviewTypeCode())).getDescription() });
                    valid = false;
                }

            }
        }
        return valid;
    }
    
    private boolean isValidSubmTypeQual(IacucProtocolModifySubmissionBean submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())) {
            /*
             *do iacuc protocols have valid submission type codes? 
             */
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            List<IacucValidProtoSubTypeQual> validProtoSubTypeQuals = (List<IacucValidProtoSubTypeQual>) getBusinessObjectService()
                    .findMatching(IacucValidProtoSubTypeQual.class, fieldValues);
            if (!validProtoSubTypeQuals.isEmpty()) {
                List<String> typeQuals = new ArrayList<String>();
                for (IacucValidProtoSubTypeQual validProtoSubTypeQual : validProtoSubTypeQuals) {
                    typeQuals.add(validProtoSubTypeQual.getSubmissionTypeQualCode());
                }
                if (StringUtils.isBlank(submitAction.getSubmissionQualifierTypeCode()) || !typeQuals.contains(submitAction.getSubmissionQualifierTypeCode())) {
                    String desc = "";
                    IacucProtocolSubmissionQualifierType typeQual = (IacucProtocolSubmissionQualifierType)getBo(IacucProtocolSubmissionQualifierType.class, "submissionQualifierTypeCode", submitAction.getSubmissionQualifierTypeCode());
                    if (typeQual != null) {
                        desc = typeQual.getDescription();
                    }
                    GlobalVariables.getMessageMap().putError(Constants.IACUC_PROTOCOL_MODIFY_SUBMISSION_KEY + ".submissionQualifierTypeCode",
                            KeyConstants.INVALID_SUBMISSION_TYPE_QUALIFIER,
                            new String[] { ((IacucProtocolSubmissionType)getBo(IacucProtocolSubmissionType.class, "submissionTypeCode", submitAction.getSubmissionTypeCode())).getDescription(), 
                            desc});
                    valid = false;
                }

            }
        }
        return valid;
    }

    @SuppressWarnings("unchecked")
    private BusinessObject getBo(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put(propertyName, keyField);
        List<BusinessObject> results = (List<BusinessObject>) getBusinessObjectService().findMatching(boType, fieldValues);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
    
    private IacucProtocolOnlineReviewService getProtocolOnlineReviewService() {
        if (protocolOnlineReviewService == null) {
            protocolOnlineReviewService = KraServiceLocator.getService(IacucProtocolOnlineReviewService.class);
        }
        return protocolOnlineReviewService;
    }
}
