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
package org.kuali.kra.coi.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Map;

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

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        String[] replacementParameters = CoiReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = super.getDefaultReplacementParameters();
        coiDisclosure.refreshReferenceObject("coiDisclosureEventType");

        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
            if (StringUtils.equals(key, CoiReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, getCoiDisclosure().getSequenceNumber().toString());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_TYPE)) {
                params.put(key, coiDisclosure.getCoiDisclosureEventType().getDescription());            	
            } else if (StringUtils.equals(key, CoiReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, getCoiDisclosure().getCoiDisclosureDocument().getDocumentNumber());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_ID)) {
                params.put(key,getCoiDisclosure().getCoiDisclosureId().toString());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_NUMBER)) {
                params.put(key,getCoiDisclosure().getCoiDisclosureNumber());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_REPORTER)) {
                params.put(key,getCoiDisclosure().getDisclosureReporter().getAuthorPersonName());
            } else if (StringUtils.equals(key, CoiReplacementParameters.DISCLOSURE_STATUS)) {
                params.put(key,getCoiDisclosure().getCoiDisclosureStatus().getDescription());
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
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
