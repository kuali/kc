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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

/**
 * This class...
 */
public class InstitutionalProposalCustomDataAction extends InstitutionalProposalAction {

    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        super.reload(mapping, form, request, response);
        return institutionalProposalForm.getCustomDataHelper().institutionalProposalCustomData(mapping, institutionalProposalForm, request, response);        
    }
    
    /**
     * There is the additional logic in save for custom data.  there is not add functionality in the custom data tab, so the form custom data
     * is being added to the award on save.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        copyCustomDataToInstitutionalProposal(institutionalProposalForm);
        ActionForward forward = super.save(mapping, form, request, response);
        
        return forward;
    }
    
    /**
     * Copy the custom data to the Award so that it can saved.
     * @param form
     */
    public void copyCustomDataToInstitutionalProposal(InstitutionalProposalForm institutionalProposalForm) {
        if(institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList().size() == 0) {
            copyCustomDataToNewInstitutionalProposal(institutionalProposalForm);
        }else {
            copyCustomDataToExistingInstitutionalProposal(institutionalProposalForm);
        }
    }
    
    /**
     * This method is called when custom data is created on a newly created Award. It initializes the list on Award and sets the values from the form
     * @param awardForm
     */
    private void copyCustomDataToNewInstitutionalProposal(InstitutionalProposalForm institutionalProposalForm) {
        for (Map.Entry<String, String[]>customAttributeValue: institutionalProposalForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            InstitutionalProposalCustomData institutionalProposalCustomData = new InstitutionalProposalCustomData();
            institutionalProposalCustomData.setCustomAttribute(new CustomAttribute());
            institutionalProposalCustomData.getCustomAttribute().setId(customAttributeId);
            institutionalProposalCustomData.setCustomAttributeId((long) customAttributeId);
            institutionalProposalCustomData.setInstitutionalProposal(institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal());
            if(customAttributeValue.getValue()[0] != null) {
                institutionalProposalCustomData.setValue(customAttributeValue.getValue()[0]);
            }
            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList().add(institutionalProposalCustomData);
        }
    }
    
    /**
     * This method copies the values from the form to the awardCustomDataList on Award.
     * @param awardForm
     */
    private void copyCustomDataToExistingInstitutionalProposal(InstitutionalProposalForm institutionalProposalForm) {
        for (Map.Entry<String, String[]>customAttributeValue: institutionalProposalForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            String value = customAttributeValue.getValue()[0];
            for(InstitutionalProposalCustomData institutionalProposalCustomData : 
                            institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList()) {
                if(customAttributeId == institutionalProposalCustomData.getCustomAttributeId()) {
                    institutionalProposalCustomData.setValue(value);
                }
            }
        }
    }
}
