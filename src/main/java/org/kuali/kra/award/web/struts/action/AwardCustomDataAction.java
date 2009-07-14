/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * 
 * This class represents the Struts Action for Custom Data page(AwardCustomData.jsp)
 */
public class AwardCustomDataAction extends AwardAction {   
    
    
    private static final String CUSTOM_ATTRIBUTE_NAME = "AwardCustomDataAttribute";
    
    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        AwardForm awardForm = (AwardForm) form;
        super.reload(mapping, form, request, response);
        return awardForm.getCustomDataHelper().awardCustomData(mapping, awardForm, request, response);        
    }
    
    /**
     * There is the additional logic in save for custom data.  there is not add functionality in the custom data tab, so the form custom data
     * is being added to the award on save.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AwardForm awardForm = (AwardForm) form;  
        copyCustomDataToAward(awardForm);
        ActionForward forward = super.save(mapping, form, request, response);
        
        return forward;
    }
    
    /**
     * Copy the custom data to the Award so that it can saved.
     * @param form
     */
    public void copyCustomDataToAward(AwardForm awardForm) {
        if(awardForm.getAwardDocument().getAward().getAwardCustomDataList().size() == 0) {
            copyCustomDataToNewAward(awardForm);
        }else {
            copyCustomDataToExistingAward(awardForm);
        }
    }
    
    /**
     * This method is called when custom data is created on a newly created Award. It initializes the list on Award and sets the values from the form
     * @param awardForm
     */
    private void copyCustomDataToNewAward(AwardForm awardForm) {
        for (Map.Entry<String, String[]>customAttributeValue: awardForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            AwardCustomData awardCustomData = new AwardCustomData();
            awardCustomData.setCustomAttribute(new CustomAttribute());
            awardCustomData.getCustomAttribute().setId(customAttributeId);
            awardCustomData.setCustomAttributeId((long) customAttributeId);
            awardCustomData.setAward(awardForm.getAwardDocument().getAward());
            if(customAttributeValue.getValue()[0].length() == 0) {
                awardCustomData.setValue("");
            }else {
                awardCustomData.setValue(customAttributeValue.getValue()[0]);
            }
            awardForm.getAwardDocument().getAward().getAwardCustomDataList().add(awardCustomData);
        }
    }
    
    /**
     * This method copies the values from the form to the awardCustomDataList on Award.
     * @param awardForm
     */
    private void copyCustomDataToExistingAward(AwardForm awardForm) {
        for (Map.Entry<String, String[]>customAttributeValue: awardForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            String value = customAttributeValue.getValue()[0];
            for(AwardCustomData awardCustomData : awardForm.getAwardDocument().getAward().getAwardCustomDataList()) {
                if(customAttributeId == awardCustomData.getCustomAttributeId()) {
                    awardCustomData.setValue(value);
                }
            }
        }
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        CustomDataAction.setCustomAttributeContent(form, CUSTOM_ATTRIBUTE_NAME);
    }
}
