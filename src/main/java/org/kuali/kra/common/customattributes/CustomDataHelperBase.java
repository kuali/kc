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
package org.kuali.kra.common.customattributes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.cxf.common.util.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSPropertyConstants;

/**
 * The CustomDataHelperBase is the base class for all Custom Data Helper classes.
 */
public abstract class CustomDataHelperBase implements Serializable {
        
    private SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
    private Map<String, String[]> customAttributeValues = new HashMap<String, String[]>();
   
    /*
     * Is the end-user allowed to modify the custom data values?
     */
    private boolean modifyCustomData = false;
    
    /**
     * Prepare the tab for viewing.
     */
    public void prepareView(ProtocolDocument protocolDocument) {
        initializePermissions();
       
        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolDocument.getCustomAttributeDocuments();
        String documentNumber = protocolDocument.getDocumentNumber();
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(KNSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());

            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
                getCustomAttributeValues().put("id" + customAttributeDocument.getCustomAttributeId().toString(), new String[]{customAttributeDocValue.getValue()});
            }

            String customAttrGroupName = getValidCustomAttributeGroupName(customAttributeDocument.getCustomAttribute().getGroupName());
            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(customAttrGroupName);

            if (customAttributeDocumentList == null) {
                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(customAttrGroupName, customAttributeDocumentList);
            }
            customAttributeDocumentList.add(customAttributeDocument);
        }

        setCustomAttributeGroups(customAttributeGroups);
    }
    
    /**
     * 
     * This method takes in a groupName from the data entry and return a valid string to use in the Map functions later.
     * Note, data entry may create a null group name, which is invalid with the Map funcitons.
     * @param groupName
     * @return
     */
    public String getValidCustomAttributeGroupName(String groupName) {
        return groupName != null ? groupName : "Custom Data Group";
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
    
    /**
     * Get the ModifyCustomData value.
     * @return the ModifyCustomData value
     */
    public boolean getModifyCustomData() {
        return modifyCustomData;
    }
    
    /**
     * Sets the customAttributeGroups attribute value.
     * @param customAttributeGroups The customAttributeGroups to set.
     */
    public void setCustomAttributeGroups(SortedMap<String, List> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }

    /**
     * Gets the customAttributeGroups attribute.
     * @return Returns the customAttributeGroups.
     */
    public Map<String, List> getCustomAttributeGroups() {
        return customAttributeGroups;
    }


    /**
     * Sets the customAttributeValues attribute value.
     * @param customAttributeValues The customAttributeValues to set.
     */
    public void setCustomAttributeValues(Map<String, String[]> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }

    /**
     * Gets the customAttributeValues attribute.
     * @return Returns the customAttributeValues.
     */
    public Map<String, String[]> getCustomAttributeValues() {
        return customAttributeValues;
    }
    
    /**
     * Clears the custom attribute value for the specified customAttributeId.
     * @param customAttributeId The customAttributeId to clear
     */
    public void clearCustomAttributeValue(String customAttributeId) {
        customAttributeValues.put("id" + customAttributeId, new String[]{""});
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    /**
     * Sorts custom data attributes by label for alphabetical order on custom data panels.
     */
    public class LabelComparator implements Comparator
    {    
        public LabelComparator(){}
        
        public int compare(Object cad1, Object cad2 )
        {    
            try
            {
                String label1 = ((CustomAttributeDocument)cad1).getCustomAttribute().getLabel();
                String label2 = ((CustomAttributeDocument)cad2).getCustomAttribute().getLabel();
                if (label1 == null)
                {
                    label1 = "";
                }
                if (label2 == null)
                {
                    label2 = "";
                }
                return label1.compareTo(label2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
    }

}
