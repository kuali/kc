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
package org.kuali.kra.irb.notification;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.service.KcNotificationRenderingService;
import org.kuali.kra.irb.Protocol;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class IRBNotificationRenderingServiceImpl implements KcNotificationRenderingService {

    private Protocol protocol;
    private BusinessObjectService businessObjectService;
    
    
    public IRBNotificationRenderingServiceImpl(Protocol protocol) {
        this.protocol = protocol;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#render(java.lang.String)
     */
    @Override
    public String render(String text) {
        Map<String, String> replacementParameters = getReplacementParameters();
        return render(text, replacementParameters);
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#render(java.lang.String, java.util.Map)
     */
    @Override
    public String render(String text, Map<String,String> replacementParameters) { 
        for (String key : replacementParameters.keySet()) {
            text = StringUtils.replace(text, key, replacementParameters.get(key));
        }
        
        return text;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#getReplacementParameters()
     */
    @Override
    public Map<String, String> getReplacementParameters() {
        String[] replacementParameters = IRBReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = new HashMap<String, String>();
        
        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
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
                //params.put(key, protocol.getLastProtocolAction());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_ACTION_TYPE_CODE)) {
                params.put(key, protocol.getLastProtocolAction().getProtocolActionTypeCode()); 
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_NAME)) {
                //params.put(key, protocol.getProtocolSubmission().get)
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_CODE)) {
                params.put(key, protocol.getProtocolSubmission().getSubmissionTypeCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE)) {
                params.put(key, protocol.getProtocolSubmission().getSubmissionTypeQualifierCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME)) {
                //params.put(key, protocol.getProtocolSubmission());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TITLE)) {
                params.put(key, protocol.getTitle());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_CODE)) {
                params.put(key, protocol.getProtocolTypeCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_DESCRIPTION)) {
                if (protocol.getProtocolType() != null) {
                    params.put(key, protocol.getProtocolType().getDescription());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, protocol.getSequenceNumber().toString());
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionStatusCode());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_NAME)) {
                params.put(key, protocol.getProtocolSubmissionStatus());
            } else if (StringUtils.equals(key, IRBReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, protocol.getProtocolDocument().getDocumentNumber());
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

    /*
    private String getProtocolLastActionName(String lastActionTypeCode) {
        getBusinessObjectService().findMatching(clazz, fieldValues)
    }
    */

}
