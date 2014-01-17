/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PropertyConstants;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.document.attribute.WorkflowAttributeDefinition;
import org.kuali.rice.kns.service.BusinessObjectDictionaryService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * This class provides the implementation of the Custom Attribute Service.
 * It provides service methods related to custom attributes.
 */
public class CustomAttributeServiceImpl implements CustomAttributeService {

    private static final String ARGVALUELOOKUPE_CLASS = "org.kuali.kra.bo.ArgValueLookup";
    private BusinessObjectService businessObjectService;

    @Override
    public Map<String, CustomAttributeDocument> getDefaultCustomAttributeDocuments(String documentTypeCode, List<? extends DocumentCustomData> customDataList) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), documentTypeCode);
        List<CustomAttributeDocument> customAttributeDocumentList = 
            (List<CustomAttributeDocument>) getBusinessObjectService().findMatching(CustomAttributeDocument.class, queryMap);

        HashSet<Long> customIds = new HashSet<Long>();
        if (customDataList != null) {
            customIds = getCurrentCustomAttributeIds(customDataList);
        }
        for(CustomAttributeDocument customAttributeDocument:customAttributeDocumentList) {
            boolean customAttributeExists = false;
            if (!customIds.isEmpty() && customIds.contains(customAttributeDocument.getCustomAttributeId().longValue())) {
                customAttributeExists = true;
            }

            if (customAttributeDocument.isActive() || customAttributeExists) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }
        return customAttributeDocuments;
    }
    
    protected HashSet<Long> getCurrentCustomAttributeIds(List<? extends DocumentCustomData> customDataList) {
        HashSet<Long> customIds = new HashSet<Long>();
        for(DocumentCustomData customData : customDataList) {
            customIds.add(customData.getCustomAttributeId());
        }
        return customIds;
    }

    @Override
    public void setCustomAttributeKeyValue(String documentNumber, Map<String, CustomAttributeDocument> customAttributeDocuments, String attributeName, String networkId) {
        WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(networkId, documentNumber);
        
        // Not sure to delete all the content, but there is no other options
        workflowDocument.clearAttributeContent();  
        WorkflowAttributeDefinition customDataDef = WorkflowAttributeDefinition.Builder.create(attributeName).build();
        WorkflowAttributeDefinition.Builder refToUpdate = WorkflowAttributeDefinition.Builder.create(customDataDef);
        
        if (customAttributeDocuments != null) {
            for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
                if (StringUtils.isNotBlank(customAttributeDocument.getCustomAttribute().getValue())) {
                    refToUpdate.addPropertyDefinition(customAttributeDocument.getCustomAttribute().getName(), StringEscapeUtils.escapeXml(customAttributeDocument.getCustomAttribute().getValue()));
                }
            }
        }
        workflowDocument.addAttributeDefinition(refToUpdate.build());
        workflowDocument.saveDocumentData();    
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

    @Override
    public CustomAttributeDataType getCustomAttributeDataType(String dataTypeCode) {

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(dataTypeCode)) {
            primaryKeys.put("dataTypeCode", dataTypeCode);
            return (CustomAttributeDataType)businessObjectService.findByPrimaryKey(CustomAttributeDataType.class, primaryKeys);
        }
        return null;
        
    }

    @Override
    public List getLookupReturns(String lookupClass) throws Exception {
        List<String> lookupReturns = new ArrayList<String>();
        if (ARGVALUELOOKUPE_CLASS.equals(lookupClass)) {
            for (ArgValueLookup argValueLookup : (List<ArgValueLookup>) businessObjectService.findAll(ArgValueLookup.class)) {
                if (!lookupReturns.contains(argValueLookup.getArgumentName())) {
                    lookupReturns.add(argValueLookup.getArgumentName());
                }
            }
            Collections.sort(lookupReturns);
        }
        else {
            BusinessObjectDictionaryService businessDictionaryService = (BusinessObjectDictionaryService) KraServiceLocator
                    .getService(Constants.BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME);
            lookupReturns = businessDictionaryService.getLookupFieldNames(Class.forName(lookupClass));
        }
        return lookupReturns;
    }
    
    @Override
    public String getLookupReturnsForAjaxCall(String lookupClass) throws Exception {
        List lookupFieldNames = getLookupReturns(lookupClass);
        String attributeNames="";
        for (Object attributeName : lookupFieldNames) {
            attributeNames += "," + attributeName +";"+ (ARGVALUELOOKUPE_CLASS.equals(lookupClass) ? attributeName : KraServiceLocator.getService(DataDictionaryService.class).getAttributeLabel(lookupClass,attributeName.toString()));
        }
        return attributeNames;
    }
    
}
