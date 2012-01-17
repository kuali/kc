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
package org.kuali.kra.coi.notification;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.common.notification.NotificationRendererBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * Renders fields for the IRB notifications.
 */
public class CoiNotificationRenderer extends NotificationRendererBase {

    private CoiDisclosure coiDisclosure;
    
    private transient BusinessObjectService businessObjectService;
    private transient KcPersonService kcPersonService;
    
    /**
     * Constructs an IRB notification renderer.
     * @param CoiDisclosure
     */
    public CoiNotificationRenderer(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        String[] replacementParameters = CoiReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = super.getDefaultReplacementParameters();
        
        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
            if (StringUtils.equals(key, CoiReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, getCoiDisclosure().getSequenceNumber().toString());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_TYPE)) {
            	//TODO: following is not right. Where do we get disclosure type?
                params.put(key, coiDisclosure.getEventTypeCode());            	
            } else if (StringUtils.equals(key, CoiReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, getCoiDisclosure().getCoiDisclosureDocument().getDocumentNumber());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_ID)) {
                params.put(key,getCoiDisclosure().getCoiDisclosureId().toString());
            }
        }
        return params;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
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
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    private KualiConfigurationService getKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
}
