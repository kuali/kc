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
package org.kuali.kra.iacuc.notification;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionQualifierType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.protocol.notification.ProtocolNotificationRendererBase;
import org.kuali.kra.protocol.notification.ProtocolReplacementParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Renders fields for the IRB and IACUC notifications.
 */
public class IacucProtocolNotificationRenderer extends ProtocolNotificationRendererBase {

    private static final long serialVersionUID = 44807703047564273L;

    /**
     * Constructs a Protocol base notification renderer.
     * @param protocol
     */
    public IacucProtocolNotificationRenderer(IacucProtocol protocol) {
        super(protocol);
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        IacucProtocolSubmission protocolSubmission = (IacucProtocolSubmission)getProtocol().getProtocolSubmission();
        if (protocolSubmission != null) {
            params.put(ProtocolReplacementParameters.LAST_SUBMISSION_NAME, getProtocolSubmissionName(protocolSubmission.getSubmissionTypeCode()));
            params.put(ProtocolReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME, getLastSubmissionTypeQualifierName(protocolSubmission.getSubmissionTypeQualifierCode()));
            params.put(ProtocolReplacementParameters.PROTOCOL_REVIEW_TYPE_DESC, getSafeMessage(ProtocolReplacementParameters.PROTOCOL_REVIEW_TYPE_DESC, getProtocolReviewTypeDescription(protocolSubmission.getProtocolReviewTypeCode())));
        }
        IacucProtocolAction lastProtocolAction = (IacucProtocolAction)getProtocol().getLastProtocolAction();
        if (lastProtocolAction != null) {
            params.put(ProtocolReplacementParameters.LAST_ACTION_NAME, getProtocolLastActionName(lastProtocolAction.getProtocolActionTypeCode()));    
            params.put(ProtocolReplacementParameters.LAST_ACTION_TYPE_CODE, lastProtocolAction.getProtocolActionTypeCode());
        }
        return params;
    }
    
    private String getProtocolLastActionName(String lastActionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolActionTypeCode", lastActionTypeCode);
        List<IacucProtocolActionType> actionTypes = (List<IacucProtocolActionType>) getBusinessObjectService().findMatching(IacucProtocolActionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(actionTypes)) {
            result = actionTypes.get(0).getDescription();
        }
        
        return result;
    }

    private String getProtocolSubmissionName(String submissionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", submissionTypeCode);
        List<IacucProtocolSubmissionType> submissionTypes = 
            (List<IacucProtocolSubmissionType>) getBusinessObjectService().findMatching(IacucProtocolSubmissionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionTypes)) {
            result = submissionTypes.get(0).getDescription();
        }
        
        return result;
    }
    
    private String getLastSubmissionTypeQualifierName(String submissionQualifierTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionQualifierTypeCode", submissionQualifierTypeCode);
        List<IacucProtocolSubmissionQualifierType> submissionQualifierTypes = 
            (List<IacucProtocolSubmissionQualifierType>) getBusinessObjectService().findMatching(IacucProtocolSubmissionQualifierType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionQualifierTypes)) {
            result = submissionQualifierTypes.get(0).getDescription();
        }
        
        return result;
    }

    private String getProtocolReviewTypeDescription(String reviewTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("reviewTypeCode", reviewTypeCode);
        List<IacucProtocolReviewType> protocolReviewTypes = 
            (List<IacucProtocolReviewType>) getBusinessObjectService().findMatching(IacucProtocolReviewType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(protocolReviewTypes)) {
            result = protocolReviewTypes.get(0).getDescription();
        }        
        return result;
    }

    @Override
    protected Class<? extends CommitteeBase> getCommonCommitteeBOClassHook() {
        return IacucCommittee.class;
    }
    
}
