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
package org.kuali.kra.protocol.customdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public abstract class ProtocolCustomDataHelper implements Serializable { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3821021799847248950L;

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    private SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups;
    private Map<String, String[]> customAttributeValues = new HashMap<String, String[]>();
    
    private transient BusinessObjectService businessObjectService;
    private transient TaskAuthorizationService taskAuthorizationService;

    private boolean modifyCustomData = false;

    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public ProtocolCustomDataHelper(ProtocolForm form) {
        this.form = form;
    }
    
    public Map<String, List<CustomAttributeDocument>> getCustomAttributeGroups() {
        return customAttributeGroups;
    }

    public void setCustomAttributeGroups(SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }

    public Map<String, String[]> getCustomAttributeValues() {
        return customAttributeValues;
    }

    public void setCustomAttributeValues(Map<String, String[]> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }

    /*
     * Get the Protocol.
     */
    protected Protocol getProtocol() {
        ProtocolDocument document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    /**
     * 
     * This method returns true if the custom data tab should be displayed.
     * @return
     */
    public boolean canDisplayCustomDataTab() {
        boolean localCustomData = this.getCustomAttributeGroups().size() > 0;      
        boolean anyProtocolAttr = areThereAnyProtocolCustomAttributes();
        return localCustomData || anyProtocolAttr;        
    }
    
    private boolean areThereAnyProtocolCustomAttributes() {
        Map fieldValues = new HashMap();
        fieldValues.put("DOCUMENT_TYPE_CODE", getDocumentTypeCode());
        fieldValues.put("ACTIVE_FLAG", "Y");
        Collection<CustomAttributeDocument> documents = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        return documents.size() > 0;
    }
    
    protected abstract String getDocumentTypeCode();

    public abstract void prepareView(Protocol protocol);
    
//TODO: Must be reworked when IACUC and IRB are merged    
//    public void prepareView(ProtocolDocument protocolDocument) {
//        initializePermissions();
//       
//        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolDocument.getCustomAttributeDocuments();
//        String documentNumber = protocolDocument.getDocumentNumber();
//        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
//            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
//            Map<String, Object> primaryKeys = new HashMap<String, Object>();
//            primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
//            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());
//
//            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
//            if (customAttributeDocValue != null) {
//                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
//                getCustomAttributeValues().put("id" + customAttributeDocument.getCustomAttributeId().toString(), new String[]{customAttributeDocValue.getValue()});
//            }
//
//            String customAttrGroupName = getValidCustomAttributeGroupName(customAttributeDocument.getCustomAttribute().getGroupName());
//            List<CustomAttributeDocument> customAttributeDocumentList = getCustomAttributeGroups().get(customAttrGroupName);
//
//            if (customAttributeDocumentList == null) {
//                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
//                getCustomAttributeGroups().put(customAttrGroupName, customAttributeDocumentList);
//            }
//            customAttributeDocumentList.add(customAttributeDocument);
//        }
//
//        setCustomAttributeGroups((SortedMap<String, List>) getCustomAttributeGroups());
//    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    /**
     * 
     * This method takes in a groupName from the data entry and return a valid string to use in the Map functions later.
     * Note, data entry may create a null group name, which is invalid with the Map functions.
     * @param groupName
     * @return
     */
    public String getValidCustomAttributeGroupName(String groupName) {
        return groupName != null ? groupName : "Custom Data Group";
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    public void initializePermissions() {
        modifyCustomData = canModifyCustomData();
    }

    /**
     * Can the current user modify the custom data values?
     * @return true if can modify the custom data; otherwise false
     */
    public abstract boolean canModifyCustomData();
    
    public abstract ActionForward getCustomDataAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

//TODO: Must be reworked when IACUC and IRB are merged    
//    public ActionForward getCustomDataAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String mappingName) {
//        SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
//
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ResearchDocumentBase doc = (ResearchDocumentBase) protocolForm.getDocument();
//
//        Map<String, CustomAttributeDocument> customAttributeDocuments = doc.getCustomAttributeDocuments();
//        String documentNumber = doc.getDocumentNumber();
//        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
//            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
//            CustomAttributeDocValue customAttributeDocValue = 
//                getCustomAttributeDocValue(documentNumber, customAttributeDocument.getCustomAttributeId());
//            if (customAttributeDocValue != null) {
//                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
//                protocolForm.getProtocolCustomDataHelper().getCustomAttributeValues()
//                .put("id" + customAttributeDocument.getCustomAttributeId().toString(), 
//                        new String[]{customAttributeDocValue.getValue()});
//            }
//
//            String groupName = protocolForm.getProtocolCustomDataHelper().getValidCustomAttributeGroupName(customAttributeDocument.getCustomAttribute().getGroupName());
//            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
//
//            if (customAttributeDocumentList == null) {
//                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
//                customAttributeGroups.put(groupName, customAttributeDocumentList);
//            }
//            customAttributeDocumentList.add(customAttributeDocument);
//        }
//
//        protocolForm.getProtocolCustomDataHelper().setCustomAttributeGroups(customAttributeGroups);
//        return mapping.findForward(mappingName);
//    }    

//TODO: Must be reworked when IACUC and IRB are merged (must change to use protocol BO instead of protocol document    
//    /**
//     * Get the Custom Attribute Doc value.
//     * @param documentNumber
//     * @param customAttributeId
//     * @return
//     */
//    private CustomAttributeDocValue getCustomAttributeDocValue(String documentNumber, Integer customAttributeId) {
//        Map<String, Object> primaryKeys = new HashMap<String, Object>();
//        primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
//        primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeId);
//        return (CustomAttributeDocValue) getBusinessObjectService().findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
//    }    

    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService =  KraServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }
    
    /**
     * Clears the custom attribute value for the specified customAttributeId.
     * @param customAttributeId The customAttributeId to clear
     */
    public void clearCustomAttributeValue(String customAttributeId) {
        customAttributeValues.put("id" + customAttributeId, new String[]{""});
    }

    public boolean isModifyCustomData() {
        return modifyCustomData;
    }

    public void setModifyCustomData(boolean modifyCustomData) {
        this.modifyCustomData = modifyCustomData;
    }
    
}
