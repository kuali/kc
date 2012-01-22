/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PropertyConstants;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.document.attribute.WorkflowAttributeDefinition;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.BusinessObjectDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADPropertyConstants;

/**
 * This class provides the implementation of the Custom Attribute Service.
 * It provides service methods related to custom attributes.
 */
public class CustomAttributeServiceImpl implements CustomAttributeService {

    private static final String ARGVALUELOOKUPE_CLASS = "org.kuali.kra.bo.ArgValueLookup";
    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kra.service.CustomAttributeService#getDefaultCustomAttributesForDocumentType(java.lang.String, java.lang.String)
     */
    public Map<String, CustomAttributeDocument> getDefaultCustomAttributesForDocumentType(String documentTypeCode, String documentNumber) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();

        List<CustomAttributeDocument> customAttributeDocumentList = getCustomAttributeDocuments(documentTypeCode);

        for(CustomAttributeDocument customAttributeDocument:customAttributeDocumentList) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());

            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) getBusinessObjectService().findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
            } else {
                if (StringUtils.isNotBlank(customAttributeDocument.getCustomAttribute().getDefaultValue())) {
                    customAttributeDocument.getCustomAttribute().setValue(customAttributeDocument.getCustomAttribute().getDefaultValue());
                }
            }
            // inactive cust_attr only displayed if existing in old doc
            if (customAttributeDocValue != null || customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }

        return customAttributeDocuments;

    }
    
    /**
     * Get the Custom Attribute Documents from the database.  Must make a copy of
     * the BOs.  The current custom attribute software stores the value in the
     * CustomAttribute BO.  Unfortunately, the BO instance is common to all of 
     * documents that use that custom attribute.  In other words, suppose two documents
     * are retrieved from database on the same transaction.  OJB will cache the 
     * CustomAttribute BO instances and use the same instances for both documents.
     * But those two documents may have different values for the custom attributes.
     * In that case, the last to write its value to the CustomAttribute BO will win.
     * To avoid this problem, this method returns a copy of the BOs so that each
     * document will have its own instances.
     * @param documentTypeCode
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<CustomAttributeDocument> getCustomAttributeDocuments(String documentTypeCode) {
        List<CustomAttributeDocument> newCustomAttributeDocuments = new ArrayList<CustomAttributeDocument>();
        
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), documentTypeCode);

        List<CustomAttributeDocument> customAttributeDocuments = (List<CustomAttributeDocument>)getBusinessObjectService().findMatching(CustomAttributeDocument.class, queryMap);
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocuments) {
            newCustomAttributeDocuments.add(copyCustomAttributeDocument(customAttributeDocument));
        }
        
        return newCustomAttributeDocuments;
    }
    
    protected CustomAttributeDocument copyCustomAttributeDocument(CustomAttributeDocument customAttributeDocument) {
        CustomAttributeDocument newCustomAttributeDocument = new CustomAttributeDocument();
        
        newCustomAttributeDocument.setCustomAttributeId(customAttributeDocument.getCustomAttributeId());
        newCustomAttributeDocument.setDocumentTypeName(customAttributeDocument.getDocumentTypeName());
        newCustomAttributeDocument.setRequired(customAttributeDocument.isRequired());
        newCustomAttributeDocument.setTypeName(customAttributeDocument.getTypeName());
        newCustomAttributeDocument.setDocumentType(customAttributeDocument.getDocumentType());
        newCustomAttributeDocument.setCustomAttribute(copyCustomAttribute(customAttributeDocument.getCustomAttribute()));
        newCustomAttributeDocument.setActive(customAttributeDocument.isActive());
        
        return newCustomAttributeDocument;
    }

    protected CustomAttribute copyCustomAttribute(CustomAttribute customAttribute) {
        CustomAttribute newCustomAttribute = new CustomAttribute();
        
        newCustomAttribute.setId(customAttribute.getId());
        newCustomAttribute.setDataLength(customAttribute.getDataLength());
        newCustomAttribute.setDataTypeCode(customAttribute.getDataTypeCode());
        newCustomAttribute.setDefaultValue(customAttribute.getDefaultValue());
        newCustomAttribute.setGroupName(customAttribute.getGroupName());
        newCustomAttribute.setLabel(customAttribute.getLabel());
        newCustomAttribute.setLookupClass(customAttribute.getLookupClass());
        newCustomAttribute.setLookupReturn(customAttribute.getLookupReturn());
        newCustomAttribute.setName(customAttribute.getName());
        newCustomAttribute.setValue(customAttribute.getValue());
        newCustomAttribute.setCustomAttributeDataType(customAttribute.getCustomAttributeDataType());
        
        return newCustomAttribute;
    }

    /**
     * @see org.kuali.kra.service.CustomAttributeService#getDefaultAwardCustomAttributeDocuments()
     */
    @SuppressWarnings("unchecked")
    public Map<String, CustomAttributeDocument> getDefaultAwardCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(KRADPropertyConstants.DOCUMENT_TYPE_CODE, "AWRD");
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
                primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeId);
                primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
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
    
    /**
     * 
     * @see org.kuali.kra.service.CustomAttributeService#setCustomAttributeKeyValue(org.kuali.kra.document.ResearchDocumentBase, java.lang.String, java.lang.String)
     */
    public void setCustomAttributeKeyValue(ResearchDocumentBase document, String attributeName, String networkId) throws Exception {
        WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(networkId, document.getDocumentHeader().getDocumentNumber()); 
        //WorkflowDocument document = proposalDevelopmentForm.getWorkflowDocument().getInitiatorPrincipalId();
        
        // Not sure to delete all the content, but there is no other options
        workflowDocument.clearAttributeContent();  
        WorkflowAttributeDefinition customDataDef = WorkflowAttributeDefinition.Builder.create(attributeName).build();
        WorkflowAttributeDefinition.Builder refToUpdate = WorkflowAttributeDefinition.Builder.create(customDataDef);
        
        Map<String, CustomAttributeDocument>customAttributeDocuments = document.getCustomAttributeDocuments();
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

    /**
     * 
     * @see org.kuali.kra.service.CustomAttributeService#getCustomAttributeDataType(java.lang.String)
     */
    public CustomAttributeDataType getCustomAttributeDataType(String dataTypeCode) {

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(dataTypeCode)) {
            primaryKeys.put("dataTypeCode", dataTypeCode);
            return (CustomAttributeDataType)businessObjectService.findByPrimaryKey(CustomAttributeDataType.class, primaryKeys);
        }
        return null;
        
    }

    /**
     * 
     * @see org.kuali.kra.service.CustomAttributeService#getLookupReturns(java.lang.String)
     */
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
    
    /**
     * 
     * @see org.kuali.kra.service.CustomAttributeService#getLookupReturnsForAjaxCall(java.lang.String)
     */
    public String getLookupReturnsForAjaxCall(String lookupClass) throws Exception {
        List lookupFieldNames = getLookupReturns(lookupClass);
        String attributeNames="";
        for (Object attributeName : lookupFieldNames) {
            attributeNames += "," + attributeName +";"+ (ARGVALUELOOKUPE_CLASS.equals(lookupClass) ? attributeName : KraServiceLocator.getService(DataDictionaryService.class).getAttributeLabel(lookupClass,attributeName.toString()));
        }
        return attributeNames;
    }
    
}
