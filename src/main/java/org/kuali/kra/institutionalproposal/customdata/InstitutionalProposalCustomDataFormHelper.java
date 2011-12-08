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
package org.kuali.kra.institutionalproposal.customdata;

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
import org.drools.core.util.StringUtils;
import org.kuali.kra.award.customdata.CustomDataHelper.AwardStringObjectBO;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.customattributes.CustomDataHelperBase.LabelComparator;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

public class InstitutionalProposalCustomDataFormHelper extends CustomDataHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -716264183914346452L;
    
    private static final String MAPPING_CUSTOM_DATA = "customData";
    
    private InstitutionalProposalForm institutionalProposalForm;
    
    private List<InstitutionalProposalStringObjectBO> customDataValues;
    
    
    /**
     * Constructs a CustomDataHelper.
     * @param from the awardForm
     */
    public InstitutionalProposalCustomDataFormHelper(InstitutionalProposalForm institutionalProposalForm) {
        this.institutionalProposalForm = institutionalProposalForm;
        customDataValues = new ArrayList<InstitutionalProposalStringObjectBO>();
    }
    

    @Override
    public boolean canModifyCustomData() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * This method populates Array of AwardStringObjectBo with string values to be displayed in UI.  It is called when navigating to Custom
     * Data Tab and on Reload.
     */
    public void populateCustomDataValuesFromParentMap() {
        //if(customDataValues.size() == 0) {
            customDataValues = new ArrayList<InstitutionalProposalStringObjectBO>(maxCustomAttributeIndex());
            int index = 0;
            while (index <= maxCustomAttributeIndex()){
                InstitutionalProposalStringObjectBO tempInstitutionalProposalStringObjectBO = new InstitutionalProposalStringObjectBO();
                tempInstitutionalProposalStringObjectBO.setValue("");
                customDataValues.add(tempInstitutionalProposalStringObjectBO);
                index++;
            }
        //}
        
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
     * This method copies data out of Array of AwardStringObjectBo into parent collection which is in turn copied into collection of 
     * AwardCustomData objects on Award for data persitence.
     */
    public void populateCustomAttributeValuesMap() {
        for(Map.Entry<String, String[]> customAttributeValue:getCustomAttributeValues().entrySet()) {
            int id = 1;
            for(InstitutionalProposalStringObjectBO stringBO : customDataValues) {
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
     * Gets the customDataValues attribute. 
     * @return Returns the customDataValues.
     */
    public List<InstitutionalProposalStringObjectBO> getCustomDataValues() {
        return customDataValues;
    }


    /**
     * Sets the customDataValues attribute value.
     * @param customDataValues The customDataValues to set.
     */
    public void setCustomDataValues(List<InstitutionalProposalStringObjectBO> customDataValues) {
        this.customDataValues = customDataValues;
    }


    /**
     * Invoked when the "Custom Data" tab is clicked on in Award Module.  In other words, the
     * end-user is navigating to the "Custom Data" tab.  The custom attribute
     * values for the formHelper are copied from the collection of Award Custom Data BO's on the Award
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward institutionalProposalCustomData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (form instanceof CustomDataForm) {
            SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
            InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
            List<InstitutionalProposalCustomData> institutionalProposalCustomDataList = 
                institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList();
            Map<String, CustomAttributeDocument> customAttributeDocuments = institutionalProposalForm.getInstitutionalProposalDocument().getCustomAttributeDocuments();
            if(institutionalProposalCustomDataList.size() > 0) {
                buildCustomDataCollectionsOnFormExistingInstitutionalProposal(customAttributeGroups, institutionalProposalForm, customAttributeDocuments);
            }else {
                buildCustomDataCollectionsOnFormNewInstitutionalProposal(customAttributeGroups, institutionalProposalForm, customAttributeDocuments);
            }
            institutionalProposalForm.getCustomDataHelper().setCustomAttributeGroups(customAttributeGroups);
        }  
        return mapping.findForward(MAPPING_CUSTOM_DATA);
    }
    
    /**
     * This method builds the custom data collections used on the form and populates the values from the collection of AwardCustomData on the Award.
     * @param customAttributeGroups
     * @param awardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnFormExistingInstitutionalProposal(SortedMap<String, List> customAttributeGroups,
            InstitutionalProposalForm institutionalProposalForm, Map<String, CustomAttributeDocument> customAttributeDocuments) {
        List<InstitutionalProposalCustomData> institutionalProposalCustomDataList = institutionalProposalForm
                .getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            InstitutionalProposalCustomData loopInstitutionalProposalCustomData = new InstitutionalProposalCustomData();
            for (InstitutionalProposalCustomData institutionalProposalCustomData : institutionalProposalCustomDataList) {
                if (institutionalProposalCustomData.getCustomAttributeId() == (long) customAttributeDocumentEntry.getValue()
                        .getCustomAttribute().getId()) {
                    loopInstitutionalProposalCustomData = institutionalProposalCustomData;
                    break;
                }
            }
            if (loopInstitutionalProposalCustomData.getCustomAttributeId() != null) {
                String customAttrId = customAttributeDocumentEntry.getValue().getCustomAttributeId().toString();
                institutionalProposalForm.getCustomDataHelper().getCustomAttributeValues().put("id" + customAttrId,
                        new String[] { loopInstitutionalProposalCustomData.getValue() });

                String loopCustomAttrId = loopInstitutionalProposalCustomData.getCustomAttributeId().toString();
                String groupName = customAttributeDocuments.get(loopCustomAttrId).getCustomAttribute().getGroupName();

                List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
                if (customAttributeDocumentList == null) {
                    customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                    customAttributeGroups.put(groupName, customAttributeDocumentList);
                }
                customAttributeDocumentList.add(customAttributeDocuments.get(loopInstitutionalProposalCustomData
                        .getCustomAttributeId().toString()));
                Collections.sort(customAttributeDocumentList, new LabelComparator());
            }
        }
        populateCustomDataValuesFromParentMap();
    }
    
    /**
     * This method builds the custom data collections used on the form
     * @param customAttributeGroups
     * @param awardForm
     * @param customAttributeDocuments
     */
    @SuppressWarnings("unchecked")
    public void buildCustomDataCollectionsOnFormNewInstitutionalProposal(SortedMap<String, List> customAttributeGroups, 
                                                            InstitutionalProposalForm institutionalProposalForm,
                                                            Map<String, CustomAttributeDocument> customAttributeDocuments) {
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            String temp = customAttributeDocumentEntry.getValue().getCustomAttribute().getValue();
            institutionalProposalForm.getCustomDataHelper().getCustomAttributeValues()
                .put("id" + customAttributeDocumentEntry.getValue().getCustomAttributeId().toString(), (temp == null ? new String[]{null} : new String[]{temp}));       
            String groupName = customAttributeDocumentEntry.getValue().getCustomAttribute().getGroupName();
            if (StringUtils.isEmpty(groupName)) {
                groupName = "No Group";
            }
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
    
    /**
     * This class is being used as a workaround to a struts issue that will not allow indexing into a list of string primitives from JSP.
     * The only purpose of this class is to hold a string object with getters and setters so the tag file can call into index of ArrayList
     * and getValue().
     */
    public class InstitutionalProposalStringObjectBO implements Serializable{
        
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
