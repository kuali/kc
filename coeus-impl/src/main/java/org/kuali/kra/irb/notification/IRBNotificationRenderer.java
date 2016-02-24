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
package org.kuali.kra.irb.notification;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.notification.ProtocolNotificationRendererBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Renders fields for the IRB notifications.
 */
public class IRBNotificationRenderer extends ProtocolNotificationRendererBase {

    private static final long serialVersionUID = 7966684994606021231L;
    private static final String D_MMM_YYYY = "d'-'MMM'-'yyyy";
    private static final String PROTOCOL_ACTION_TYPE_CODE = "protocolActionTypeCode";
    private static final String SUBMISSION_TYPE_CODE = "submissionTypeCode";
    private static final String SUBMISSION_QUALIFIER_TYPE_CODE = "submissionQualifierTypeCode";
    private static final String REVIEW_TYPE_CODE = "reviewTypeCode";

    private Protocol protocol;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;

    public IRBNotificationRenderer(Protocol protocol) {
        super(protocol);
        this.protocol = protocol;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        String[] replacementParameters = IRBReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = super.getDefaultReplacementParameters();

        for (String key : replacementParameters) {
            if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_NUMBER)) {
                params.put(key, protocol.getProtocolNumber());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PI_NAME)) {
                params.put(key, protocol.getPrincipalInvestigator().getFullName());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LEAD_UNIT)) {
                params.put(key, protocol.getLeadUnitNumber());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LEAD_UNIT_NAME)) {
                params.put(key, protocol.getLeadUnitName());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_STATUS_CODE)) {
                params.put(key, protocol.getProtocolStatusCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_STATUS_DESCRIPTION)) {
                params.put(key, protocol.getProtocolStatus().getDescription());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_ACTION_NAME)) {
                if (protocol.getLastProtocolAction() != null) {
                    params.put(key, getProtocolLastActionName(protocol.getLastProtocolAction().getProtocolActionTypeCode()));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_ACTION_TYPE_CODE)) {
                if (protocol.getLastProtocolAction() != null) {
                    params.put(key, protocol.getLastProtocolAction().getProtocolActionTypeCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_NAME)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getProtocolSubmissionName(protocol.getProtocolSubmission().getSubmissionTypeCode()));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeQualifierCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getLastSubmissionTypeQualifierName(protocol.getProtocolSubmission().getSubmissionTypeQualifierCode()));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TITLE)) {
                params.put(key, protocol.getTitle());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_CODE)) {
                params.put(key, protocol.getProtocolTypeCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_DESCRIPTION)) {
                if (protocol.getProtocolType() != null) {
                    params.put(key, protocol.getProtocolType().getDescription());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, protocol.getSequenceNumber().toString());
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionStatusCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_NAME)) {
                params.put(key, protocol.getProtocolSubmissionStatus());
            } else if (StringUtils.equals(key, IRBReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, protocol.getProtocolDocument().getDocumentNumber());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_REVIEW_TYPE_DESC)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getSafeMessage(key, getProtocolReviewTypeDescription(protocol.getProtocolSubmission().getProtocolReviewTypeCode())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getApprovalDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getApprovalDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getLastApprovalDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getLastApprovalDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_EXPIRATION_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getExpirationDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getExpirationDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            }
        }
        
        return params;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    private String getProtocolLastActionName(String lastActionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(PROTOCOL_ACTION_TYPE_CODE, lastActionTypeCode);
        List<ProtocolActionType> actionTypes = (List<ProtocolActionType>) getBusinessObjectService().findMatching(ProtocolActionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(actionTypes)) {
            result = actionTypes.get(0).getDescription();
        }
        
        return result;
    }

    private String getProtocolSubmissionName(String submissionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(SUBMISSION_TYPE_CODE, submissionTypeCode);
        List<ProtocolSubmissionType> submissionTypes = 
            (List<ProtocolSubmissionType>) getBusinessObjectService().findMatching(ProtocolSubmissionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionTypes)) {
            result = submissionTypes.get(0).getDescription();
        }
        
        return result;
    }
    
    private String getLastSubmissionTypeQualifierName(String submissionQualifierTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(SUBMISSION_QUALIFIER_TYPE_CODE, submissionQualifierTypeCode);
        List<ProtocolSubmissionQualifierType> submissionQualifierTypes = 
            (List<ProtocolSubmissionQualifierType>) getBusinessObjectService().findMatching(ProtocolSubmissionQualifierType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionQualifierTypes)) {
            result = submissionQualifierTypes.get(0).getDescription();
        }
        
        return result;
    }

    private String getProtocolReviewTypeDescription(String reviewTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(REVIEW_TYPE_CODE, reviewTypeCode);
        List<ProtocolReviewType> protocolReviewTypes = 
            (List<ProtocolReviewType>) getBusinessObjectService().findMatching(ProtocolReviewType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(protocolReviewTypes)) {
            result = protocolReviewTypes.get(0).getDescription();
        }        
        return result;
    }

    @Override
    protected Class<? extends CommitteeBase> getCommonCommitteeBOClassHook() {
        return Committee.class;
    }
}
