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
