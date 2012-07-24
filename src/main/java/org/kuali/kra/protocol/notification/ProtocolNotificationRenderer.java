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
package org.kuali.kra.protocol.notification;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommonCommittee;
import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Renders fields for the commin protocol notifications.
 */
public class ProtocolNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 7966684994606021231L;

    private Protocol protocol;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;
    
    /**
     * Constructs a shared protocol notification renderer.
     * @param protocol
     */
    public ProtocolNotificationRenderer(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        String[] replacementParameters = ProtocolReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = super.getDefaultReplacementParameters();
        
        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
            if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_NUMBER)) {
                params.put(key, protocol.getProtocolNumber());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PI_NAME)) {
                params.put(key, protocol.getPrincipalInvestigator().getFullName());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LEAD_UNIT)) {
                params.put(key, protocol.getLeadUnitNumber());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LEAD_UNIT_NAME)) {
                params.put(key, protocol.getLeadUnitName());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_STATUS_CODE)) {
                params.put(key, protocol.getProtocolStatusCode());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_STATUS_DESCRIPTION)) {
                params.put(key, protocol.getProtocolStatus().getDescription());
//TODO            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LAST_ACTION_NAME)) {
//                if (protocol.getLastProtocolAction() != null) {
//                    params.put(key, getProtocolLastActionName(protocol.getLastProtocolAction().getProtocolActionTypeCode()));    
//                }
//            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LAST_ACTION_TYPE_CODE)) {
//                if (protocol.getLastProtocolAction() != null) {
//                    params.put(key, protocol.getLastProtocolAction().getProtocolActionTypeCode());
//                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LAST_SUBMISSION_TYPE_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeCode());
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeQualifierCode());
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TITLE)) {
                params.put(key, protocol.getTitle());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TYPE_CODE)) {
                params.put(key, protocol.getProtocolTypeCode());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TYPE_DESCRIPTION)) {
                if (protocol.getProtocolType() != null) {
                    params.put(key, protocol.getProtocolType().getDescription());
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, protocol.getSequenceNumber().toString());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.SUBMISSION_STATUS_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionStatusCode());
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.SUBMISSION_STATUS_NAME)) {
                params.put(key, protocol.getProtocolSubmissionStatus());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.USER_FULLNAME)) {
                params.put(key, GlobalVariables.getUserSession().getPerson().getName());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, protocol.getProtocolDocument().getDocumentNumber());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.COMMITTEE_NAME)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getSafeMessage(key, getCommitteeName(protocol.getProtocolSubmission().getCommitteeId())));
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE)) {
                if ( (protocol.getProtocolSubmission() != null) && (protocol.getApprovalDate() != null) ) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(protocol.getApprovalDate())));
                }
            }
            else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE)) {
                if ( (protocol.getProtocolSubmission() != null) && (protocol.getLastApprovalDate() != null) ) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(protocol.getLastApprovalDate())));
                }
            }
            else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_EXPIRATION_DATE)) {
                if ( (protocol.getProtocolSubmission() != null) && (protocol.getExpirationDate() != null) ) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat("d'-'MMM'-'yyyy")).format(protocol.getExpirationDate())));
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
            businessObjectService = KRADServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    private String getCommitteeName(String committeeId) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("committeeId", committeeId);
        List<CommonCommittee> committees = (List<CommonCommittee>) getBusinessObjectService().findMatching(CommonCommittee.class, fieldValues);
        if (CollectionUtils.isNotEmpty(committees)) {
            result = committees.get(0).getCommitteeName();
        }        
        return result;        
    }
}
