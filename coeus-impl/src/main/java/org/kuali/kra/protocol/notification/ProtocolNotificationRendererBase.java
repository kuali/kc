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
    private static final String D_MMM_YYYY = "d'-'MMM'-'yyyy";
    private static final String COMMITTEE_ID = "committeeId";

    private ProtocolBase protocol;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;
    

    public ProtocolNotificationRendererBase(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        String[] replacementParameters = ProtocolReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = super.getDefaultReplacementParameters();

        for (String key : replacementParameters) {
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
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeQualifierCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TITLE)) {
                params.put(key, protocol.getTitle());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TYPE_CODE)) {
                params.put(key, protocol.getProtocolTypeCode());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_TYPE_DESCRIPTION)) {
                if (protocol.getProtocolType() != null) {
                    params.put(key, protocol.getProtocolType().getDescription());
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, protocol.getSequenceNumber().toString());
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.SUBMISSION_STATUS_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionStatusCode());
                } else {
                    params.put(key, StringUtils.EMPTY);
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
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getApprovalDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getApprovalDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getLastApprovalDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getLastApprovalDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
                }
            } else if (StringUtils.equals(key, ProtocolReplacementParameters.PROTOCOL_EXPIRATION_DATE)) {
                if ((protocol.getProtocolSubmission() != null) && (protocol.getExpirationDate() != null)) {
                    params.put(key, getSafeMessage(key, (new SimpleDateFormat(D_MMM_YYYY)).format(protocol.getExpirationDate())));
                } else {
                    params.put(key, StringUtils.EMPTY);
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
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(COMMITTEE_ID, committeeId);
        List<? extends CommitteeBase> committees = (List<CommitteeBase>) getBusinessObjectService().findMatching(getCommonCommitteeBOClassHook(), fieldValues);
        if (CollectionUtils.isNotEmpty(committees)) {
            result = committees.get(0).getCommitteeName();
        }        
        return result;        
    }

    protected abstract Class<? extends CommitteeBase> getCommonCommitteeBOClassHook();
    
    
}
