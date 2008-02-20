/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectDictionaryService;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;

import edu.iu.uis.eden.clientapp.WorkflowDocument;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.clientapp.vo.WorkflowAttributeDefinitionVO;

/**
 * This class provides the implementation of the Custom Attribute Service.
 * It provides service methods related to custom attributes.
 */
public class CustomAttributeServiceImpl implements CustomAttributeService {

    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kra.service.CustomAttributeService#getDefaultCustomAttributesForDocumentType(java.lang.String, java.lang.String)
     */
    public Map<String, CustomAttributeDocument> getDefaultCustomAttributesForDocumentType(String documentTypeCode, String documentNumber) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("documentTypeCode", documentTypeCode);

        List<CustomAttributeDocument> customAttributeDocumentList = (List<CustomAttributeDocument>)getBusinessObjectService().findMatching(CustomAttributeDocument.class, queryMap);

        for(CustomAttributeDocument customAttributeDocument:customAttributeDocumentList) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("documentNumber", documentNumber);
            primaryKeys.put("customAttributeId", customAttributeDocument.getCustomAttributeId());

            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) getBusinessObjectService().findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
            }
            customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
        }

        return customAttributeDocuments;

    }

    /**
     * @see org.kuali.kra.service.CustomAttributeService#saveCustomAttributeValues(org.kuali.kra.document.ResearchDocumentBase)
     */
    public void saveCustomAttributeValues(ResearchDocumentBase document) {
        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        if (customAttributeDocuments != null) {
            for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
                Integer customAttributeId = customAttributeDocument.getCustomAttributeId();
                String documentNumber = document.getDocumentNumber();

                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put("customAttributeId", customAttributeId);
                primaryKeys.put("documentNumber", documentNumber);
                CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) businessObjectService.findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);

                if (customAttributeDocValue == null) {
                    customAttributeDocValue = new CustomAttributeDocValue();
                    customAttributeDocValue.setCustomAttributeId(customAttributeDocument.getCustomAttributeId());
                    customAttributeDocValue.setDocumentNumber(document.getDocumentNumber());
                }

                customAttributeDocValue.setValue(customAttributeDocument.getCustomAttribute().getValue());

                businessObjectService.save(customAttributeDocValue);
            }
        }
    }
    
    public void setCustomAttributeKeyValue(ResearchDocumentBase document, String attributeName, String networkId) throws Exception {
        WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdVO(networkId),new Long (Long.parseLong(document.getDocumentHeader().getDocumentNumber()))); 
        //WorkflowDocument document = proposalDevelopmentForm.getWorkflowDocument().getInitiatorNetworkId();
        
        // Not sure to delete all the content, but there is no other options
        workflowDocument.clearAttributeContent();  
        WorkflowAttributeDefinitionVO customDataDef = new WorkflowAttributeDefinitionVO("CustomDataAttribute");
        
        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
        if (customAttributeDocuments != null) {
            for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
                if (StringUtils.isNotBlank(customAttributeDocument.getCustomAttribute().getValue())) {
                    customDataDef.addProperty(customAttributeDocument.getCustomAttribute().getName(), customAttributeDocument.getCustomAttribute().getValue());
                }
            }
        }
        workflowDocument.addAttributeDefinition(customDataDef);
        workflowDocument.saveRoutingData(); 
        
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

    public Collection<CustomAttributeDataType> getCustomAttributeDataTypes() {
        return businessObjectService.findAll(CustomAttributeDataType.class);
        
    }
    public CustomAttributeDataType getCustomAttributeDataType(String dataTypeCode) {

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(dataTypeCode)) {
            primaryKeys.put("dataTypeCode", dataTypeCode);
            return (CustomAttributeDataType)businessObjectService.findByPrimaryKey(CustomAttributeDataType.class, primaryKeys);
        }
        return null;
        
    }

    public List getLookupReturns(String lookupClass) throws Exception {
        BusinessObjectDictionaryService businessDictionaryService = (BusinessObjectDictionaryService)KraServiceLocator.getService(Constants.BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME);
        //return lookupClass.substring(lookupClass.lastIndexOf(".")+1,lookupClass.length());
        //Map attributes = dataDictionaryService.getDataDictionary().getDocumentEntry(lookupClass.substring(lookupClass.lastIndexOf(".")+1,lookupClass.length())).getAttributes();
        //Map attributes = dataDictionaryService.getDataDictionary().getBusinessObjectEntry(lookupClass).getAttributes();
        return businessDictionaryService.getLookupFieldNames(Class.forName(lookupClass));
    }
    
    public String getLookupReturnsForAjaxCall(String lookupClass) throws Exception {
        BusinessObjectDictionaryService businessDictionaryService = (BusinessObjectDictionaryService)KraServiceLocator.getService(Constants.BUSINESS_OBJECT_DICTIONARY_SERVICE_NAME);
        //return lookupClass.substring(lookupClass.lastIndexOf(".")+1,lookupClass.length());
        //Map attributes = dataDictionaryService.getDataDictionary().getDocumentEntry(lookupClass.substring(lookupClass.lastIndexOf(".")+1,lookupClass.length())).getAttributes();
        //Map attributes = dataDictionaryService.getDataDictionary().getBusinessObjectEntry(lookupClass).getAttributes();
        List lookupFieldNames = getLookupReturns(lookupClass);
        String attributeNames="";
        for (Object attributeName : lookupFieldNames) {
            attributeNames += "," + attributeName;
        }
        return attributeNames;
    }
    
}
