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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.PropertyConstants;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.service.NegotiationCustomAttributeService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSPropertyConstants;

/**
 * This class provides the implementation of the Award Custom Attribute Service.
 * It provides service methods related to custom attributes.
 */
public class NegotiationCustomAttributeServiceImpl implements NegotiationCustomAttributeService{

    private BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.kra.service.CustomAttributeService#getDefaultAwardCustomAttributeDocuments()
     */
    @SuppressWarnings("unchecked")
    public Map<String, CustomAttributeDocument> getDefaultNegotiationCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), NegotiationDocument.DOCUMENT_TYPE_CODE);
        List<CustomAttributeDocument> customAttributeDocumentList = 
            (List<CustomAttributeDocument>) getBusinessObjectService().findMatching(CustomAttributeDocument.class, queryMap);
        for(CustomAttributeDocument customAttributeDocument:customAttributeDocumentList) {
            if (customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }
        return customAttributeDocuments;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
