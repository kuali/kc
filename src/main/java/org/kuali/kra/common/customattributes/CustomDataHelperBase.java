/*
 * Copyright 2005-2013 The Kuali Foundation
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.cxf.common.util.StringUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADPropertyConstants;

/**
 * The CustomDataHelperBase is the base class for all Custom Data Helper classes.
 */
public abstract class CustomDataHelperBase<T extends DocumentCustomData> implements Serializable {
        
    private SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
   
    /*
     * Is the end-user allowed to modify the custom data values?
     */
    private boolean modifyCustomData = false;
    
    
    /**
     * This method builds the custom data collections used on the form and populates the values from the collection of AwardCustomData on the Award.
     * @param customAttributeGroups
     * @param awardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnExistingDocument(SortedMap<String, List> customAttributeGroups) {
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:getCustomAttributeDocuments().entrySet()) {
            T loopAwardCustomData = null;
            for(T awardCustomData : getCustomDataList()){
                if(awardCustomData.getCustomAttributeId() == (long) customAttributeDocumentEntry.getValue().getCustomAttribute().getId()){
                    loopAwardCustomData = awardCustomData;
                    break;
                }
            }
            if (loopAwardCustomData != null) {
                String groupName = getCustomAttributeDocuments().get(loopAwardCustomData.getCustomAttributeId().toString()).getCustomAttribute().getGroupName();
                List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);   
                if (customAttributeDocumentList == null) {
                    customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                    customAttributeGroups.put(groupName, customAttributeDocumentList);
                }
                customAttributeDocumentList.add(getCustomAttributeDocuments().get(loopAwardCustomData.getCustomAttributeId().toString()));
                Collections.sort(customAttributeDocumentList, new LabelComparator());
            }
        }
    }
    
    /**
     * This method builds the custom data collections used on the form
     * @param customAttributeGroups
     * @param awardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnNewDocument(SortedMap<String, List> customAttributeGroups) {
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:getCustomAttributeDocuments().entrySet()) {
            String temp = customAttributeDocumentEntry.getValue().getCustomAttribute().getValue();       
            String groupName = customAttributeDocumentEntry.getValue().getCustomAttribute().getGroupName();
            
            T newCustomData = getNewCustomData();
            newCustomData.setCustomAttribute(customAttributeDocumentEntry.getValue().getCustomAttribute());
            newCustomData.setCustomAttributeId(customAttributeDocumentEntry.getValue().getCustomAttributeId().longValue());
            newCustomData.setValue(customAttributeDocumentEntry.getValue().getCustomAttribute().getDefaultValue());
            getCustomDataList().add(newCustomData);
            
            if (StringUtils.isEmpty(groupName)) {
                groupName = "No Group";
            }
            
            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
            if (customAttributeDocumentList == null) {
                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(groupName, customAttributeDocumentList);
                
            }
            customAttributeDocumentList.add(getCustomAttributeDocuments().get(customAttributeDocumentEntry.getValue().getCustomAttributeId().toString()));
            Collections.sort(customAttributeDocumentList, new LabelComparator());
        }
    }
    
    public void prepareCustomData() {
        initializePermissions();
        SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
        if(getCustomDataList().size() > 0) {
            buildCustomDataCollectionsOnExistingDocument(customAttributeGroups);
        }else {
            buildCustomDataCollectionsOnNewDocument(customAttributeGroups);
        }
        setCustomAttributeGroups(customAttributeGroups);
    }
    
    protected abstract T getNewCustomData();
    
    public abstract List<T> getCustomDataList();
    
    public abstract Map<String, CustomAttributeDocument> getCustomAttributeDocuments();
    
    
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
    public boolean canModifyCustomData() {
        return modifyCustomData;
    }
    
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
     * Set the custom attribute content in workflow.
     * @param form
     * @throws Exception
     */
   public void setCustomAttributeContent(String documentNumber, String attributeName) {
       getCustomAttributeService().setCustomAttributeKeyValue(documentNumber, getCustomAttributeDocuments(), attributeName, getUserIdentifier());
   } 
   
   protected CustomAttributeService getCustomAttributeService() {
       return KraServiceLocator.getService(CustomAttributeService.class); 
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
