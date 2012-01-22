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
package org.kuali.kra.subaward.customdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;

public class CustomDataHelper extends CustomDataHelperBase{
    private static final long serialVersionUID = -2308402022153534376L;

    private static final String MAPPING_CUSTOM_DATA = "customData";
    
    private List<SubAwardStringObjectBO> customDataValues;
    
   
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private SubAwardForm subAwardForm;
    
    /**
     * Constructs a CustomDataHelper.
     * @param from the subAwardForm
     */
    public CustomDataHelper(SubAwardForm subAwardForm) {
        this.subAwardForm = subAwardForm;
        customDataValues = new ArrayList<SubAwardStringObjectBO>();
    }
    
    /*
     * Get the subAward.
     */
    private SubAward getSubAward() {
        SubAwardDocument document = subAwardForm.getSubAwardDocument();
        if (document == null || document.getSubAward() == null) {
            throw new IllegalArgumentException("invalid (null) subAwardDocument in subAwardForm");
        }
        return document.getSubAward();
    }
    
    
    
    /**
     * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
     */
    @Override
    public boolean canModifyCustomData() {
        // TODO Auto-generated method stub
        return false;
    }
    

    /**
     * Gets the customDataValues attribute. 
     * @return Returns the customDataValues.
     */
    public List<SubAwardStringObjectBO> getCustomDataValues() {
        return customDataValues;
    }

    /**
     * Sets the customDataValues attribute value.
     * @param customDataValues The customDataValues to set.
     */
    public void setCustomDataValues(List<SubAwardStringObjectBO> customDataValues) {
        this.customDataValues = customDataValues;
    }

    /**
     * This method populates Array of subAwardStringObjectBo with string values to be displayed in UI.  It is called when navigating to Custom
     * Data Tab and on Reload.
     */
    public void populateCustomDataValuesFromParentMap() {
            customDataValues = new ArrayList<SubAwardStringObjectBO>(maxCustomAttributeIndex());
            for(int i=0; i<=maxCustomAttributeIndex(); i++) {
                SubAwardStringObjectBO tempSubAwardStringObjectBO = new SubAwardStringObjectBO();
                tempSubAwardStringObjectBO.setValue("");
                customDataValues.add(tempSubAwardStringObjectBO);
            }
        for(Map.Entry<String, String[]> customAttributeValue:getCustomAttributeValues().entrySet()) {
            customDataValues.get(Integer.parseInt(customAttributeValue.getKey().substring(2)) - 1).setValue(customAttributeValue.getValue()[0]);
        }
    }

    private int maxCustomAttributeIndex() {
        int index = 0;
        for(Map.Entry<String, String[]> customAttributeValue : getCustomAttributeValues().entrySet()) {
            int tempIndex = Integer.parseInt(customAttributeValue.getKey().substring(2)) - 1;
            if (tempIndex > index) {
                index = tempIndex;
            }
        }
        return index;
    }

    /**
     * This method copies data out of Array of subAwardStringObjectBo into parent collection which is in turn copied into collection of 
     * subAwardCustomData objects on subAward for data persistence.
     */
    public void populateCustomAttributeValuesMap() {
        for(Map.Entry<String, String[]> customAttributeValue:getCustomAttributeValues().entrySet()) {
            int id = 1;
            for(SubAwardStringObjectBO stringBO : customDataValues) {
                  if(id == Integer.parseInt(customAttributeValue.getKey().substring(2))) {
                      customAttributeValue.getValue()[0] = stringBO.getValue();
                      break;
                  }else {
                      id++;
                  }
            }
        }
    }
    
    
    
    /**
     * Invoked when the "Custom Data" tab is clicked on in subAward Module.  In other words, the
     * end-user is navigating to the "Custom Data" tab.  The custom attribute
     * values for the formHelper are copied from the collection of subAward Custom Data BO's on the subAward
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward subAwardCustomData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (form instanceof CustomDataForm) {
            SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
            SubAwardForm subAwardForm = (SubAwardForm) form;
            List<SubAwardCustomData> subAwardCustomDataList = subAwardForm.getSubAwardDocument().getSubAward().getSubAwardCustomDataList();
            Map<String, CustomAttributeDocument> customAttributeDocuments = subAwardForm.getSubAwardDocument().getCustomAttributeDocuments();
            if(subAwardCustomDataList.size() > 0) {
                buildCustomDataCollectionsOnFormExistingsubAward(customAttributeGroups, subAwardForm, customAttributeDocuments);
            }else {
                buildCustomDataCollectionsOnFormNewsubAward(customAttributeGroups, subAwardForm, customAttributeDocuments);
            }
            subAwardForm.getCustomDataHelper().setCustomAttributeGroups(customAttributeGroups);
        }  
            return mapping.findForward(MAPPING_CUSTOM_DATA);
    }
    
    /**
     * This method builds the custom data collections used on the form and populates the values from the collection of subAwardCustomData on the subAward.
     * @param customAttributeGroups
     * @param subAwardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnFormExistingsubAward(SortedMap<String, List> customAttributeGroups, 
            SubAwardForm subAwardForm,Map<String, CustomAttributeDocument> customAttributeDocuments) {
        List<SubAwardCustomData> subAwardCustomDataList = subAwardForm.getSubAwardDocument().getSubAward().getSubAwardCustomDataList();
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            SubAwardCustomData loopsubAwardCustomData = null;
            for(SubAwardCustomData subAwardCustomData : subAwardCustomDataList){
                if(subAwardCustomData.getCustomAttributeId() == (long) customAttributeDocumentEntry.getValue().getCustomAttribute().getId()){
                    loopsubAwardCustomData = subAwardCustomData;
                    break;
                }
            }
            if (loopsubAwardCustomData != null) {
                subAwardForm.getCustomDataHelper().getCustomAttributeValues()
                    .put("id" + customAttributeDocumentEntry.getValue().getCustomAttributeId().toString(), new String[] {loopsubAwardCustomData.getValue()});
                String groupName = 
                    customAttributeDocuments.get(loopsubAwardCustomData.getCustomAttributeId().toString()).getCustomAttribute().getGroupName();
                List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);   
                if (customAttributeDocumentList == null) {
                    customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                    customAttributeGroups.put(groupName, customAttributeDocumentList);
                }
                customAttributeDocumentList.add(customAttributeDocuments.get(loopsubAwardCustomData.getCustomAttributeId().toString()));
            }
        }
        populateCustomDataValuesFromParentMap();
    }
    
    /**
     * This method builds the custom data collections used on the form
     * @param customAttributeGroups
     * @param subAwardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnFormNewsubAward(SortedMap<String, List> customAttributeGroups, 
            SubAwardForm subAwardForm,Map<String, CustomAttributeDocument> customAttributeDocuments) {
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            subAwardForm.getCustomDataHelper().getCustomAttributeValues()
                .put("id" + customAttributeDocumentEntry.getValue().getCustomAttributeId().toString(), new String[] {""});       
            String groupName = customAttributeDocumentEntry.getValue().getCustomAttribute().getGroupName();
            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
            if (customAttributeDocumentList == null) {
                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(groupName, customAttributeDocumentList);
            }
            customAttributeDocumentList.add(customAttributeDocuments.get(customAttributeDocumentEntry.getValue().getCustomAttributeId().toString()));
            Collections.sort(customAttributeDocumentList, new LabelComparator());
        }
        populateCustomDataValuesFromParentMap();
    }

    public class SubAwardStringObjectBO implements Serializable{
        
        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -4685926051202342610L;
        private String value;

        /**
         * Gets the value attribute. 
         * @return Returns the value.
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value attribute value.
         * @param value The value to set.
         */
        public void setValue(String value) {
            this.value = value;
        }


    }

}
