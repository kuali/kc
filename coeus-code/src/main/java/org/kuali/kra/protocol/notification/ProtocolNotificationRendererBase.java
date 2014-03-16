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
package org.kuali.kra.protocol.notification;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Renders fields for the commin protocol notifications.
 */
public abstract class ProtocolNotificationRendererBase extends NotificationRendererBase {

    private static final long serialVersionUID = 7966684994606021231L;

    private ProtocolBase protocol;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;
    
    /**
     * Constructs a shared protocol notification renderer.
     * @param protocol
     */
    public ProtocolNotificationRendererBase(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.common.notification.impl.NotificationRenderer#getReplacementParameters()
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

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
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

    private String getCommitteeName(String committeeId) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("committeeId", committeeId);
        List<CommitteeBase> committees = (List<CommitteeBase>) getBusinessObjectService().findMatching(getCommonCommitteeBOClassHook(), fieldValues);
        if (CollectionUtils.isNotEmpty(committees)) {
            result = committees.get(0).getCommitteeName();
        }        
        return result;        
    }

    protected abstract Class<? extends CommitteeBase> getCommonCommitteeBOClassHook();
    
    
}
