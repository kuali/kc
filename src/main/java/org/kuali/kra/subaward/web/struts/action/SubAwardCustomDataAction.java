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
package org.kuali.kra.subaward.web.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;

public class SubAwardCustomDataAction extends SubAwardAction {
    
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        SubAwardForm subAwardForm = (SubAwardForm) form;
        super.reload(mapping, form, request, response);
        return subAwardForm.getCustomDataHelper().subAwardCustomData(mapping, subAwardForm, request, response);        
    }
    
    /**
     * There is the additional logic in save for custom data.  there is not add functionality in the custom data tab, so the form custom data
     * is being added to the subAward on save.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;  
        copyCustomDataToSubAward(subAwardForm);
        ActionForward forward = super.save(mapping, form, request, response);
        
        return forward;
    }
    
    /**
     * Copy the custom data to the SubAward so that it can saved.
     * @param form
     */
    public void copyCustomDataToSubAward(SubAwardForm subAwardForm) {
        subAwardForm.getCustomDataHelper().populateCustomAttributeValuesMap();
        if(subAwardForm.getSubAwardDocument().getSubAward().getSubAwardCustomDataList().size() == 0) {
            copyCustomDataToNewSubAward(subAwardForm);
        }else {
            copyCustomDataToExistingSubAward(subAwardForm);
        }
    }
    
    /**
     * This method is called when custom data is created on a newly created SubAward. It initializes the list on SubAward and sets the values from the form
     * @param subAwardForm
     */
    private void copyCustomDataToNewSubAward(SubAwardForm subAwardForm) {
        for (Map.Entry<String, String[]>customAttributeValue: subAwardForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            SubAwardCustomData subAwardCustomData = new SubAwardCustomData();
            subAwardCustomData.setCustomAttribute(new CustomAttribute());
            subAwardCustomData.getCustomAttribute().setId(customAttributeId);
            subAwardCustomData.setCustomAttributeId((long) customAttributeId);
            subAwardCustomData.setSubAward(subAwardForm.getSubAwardDocument().getSubAward());
            if(customAttributeValue.getValue()[0] == null) {
                subAwardCustomData.setValue("");
            }else {
                subAwardCustomData.setValue(customAttributeValue.getValue()[0]);
            }
            subAwardForm.getSubAwardDocument().getSubAward().getSubAwardCustomDataList().add(subAwardCustomData);
        }
    }
    
    /**
     * This method copies the values from the form to the subAwardCustomDataList on SubAward.
     * @param subAwardForm
     */
    private void copyCustomDataToExistingSubAward(SubAwardForm subAwardForm) {
        for (Map.Entry<String, String[]>customAttributeValue: subAwardForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            String value = customAttributeValue.getValue()[0];
            for(SubAwardCustomData subAwardCustomData : subAwardForm.getSubAwardDocument().getSubAward().getSubAwardCustomDataList()) {
                if(customAttributeId == subAwardCustomData.getCustomAttributeId()) {
                    subAwardCustomData.setValue(value);
                }
            }
        }
    }
}
