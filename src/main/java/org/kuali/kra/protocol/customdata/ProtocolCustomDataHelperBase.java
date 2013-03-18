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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public abstract class ProtocolCustomDataHelperBase<T extends DocumentCustomData> extends CustomDataHelperBase<T> { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3821021799847248950L;

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    protected ProtocolFormBase form;
    private transient BusinessObjectService businessObjectService;
    private transient TaskAuthorizationService taskAuthorizationService;


    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public ProtocolCustomDataHelperBase(ProtocolFormBase form) {
        this.form = form;
    }

    /*
     * Get the ProtocolBase.
     */
    protected ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
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
    
//TODO: Must be reworked when IACUC and IRB are merged    
//    public void prepareView(ProtocolDocumentBase protocolDocument) {
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

// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * 
//     * This method takes in a groupName from the data entry and return a valid string to use in the Map functions later.
//     * Note, data entry may create a null group name, which is invalid with the Map functions.
//     * @param groupName
//     * @return
//     */
//    public String getValidCustomAttributeGroupName(String groupName) {
//        return groupName != null ? groupName : "Custom Data Group";
//    }
//    
//    /**
//     * Get the userName of the user for the current session.
//     * @return the current session's userName
//     */
//    protected String getUserIdentifier() {
//         return GlobalVariables.getUserSession().getPrincipalId();
//    }
//    
//    /**
//     * Initialize the permissions for viewing/editing the Custom Data web page.
//     */
//    public void initializePermissions() {
//        modifyCustomData = canModifyCustomData();
//    }

    /**
     * Can the current user modify the custom data values?
     * @return true if can modify the custom data; otherwise false
     */
    public abstract boolean canModifyCustomData();

//TODO: Must be reworked when IACUC and IRB are merged    
//    public ActionForward getCustomDataAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String mappingName) {
//        SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
//
//        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
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
    
    protected String getDocumentTypeCode() {
        return form.getProtocolDocument().getDocumentTypeCode();
    }

// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * Clears the custom attribute value for the specified customAttributeId.
//     * @param customAttributeId The customAttributeId to clear
//     */
//    public void clearCustomAttributeValue(String customAttributeId) {
//        customAttributeValues.put("id" + customAttributeId, new String[]{""});
//    }
//
//    public boolean isModifyCustomData() {
//        return modifyCustomData;
//    }
//
//    public void setModifyCustomData(boolean modifyCustomData) {
//        this.modifyCustomData = modifyCustomData;
//    }
    
}
