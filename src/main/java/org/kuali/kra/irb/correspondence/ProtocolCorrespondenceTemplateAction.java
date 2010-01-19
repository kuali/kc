/*
 * Copyright 2006-2010 The Kuali Foundation
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
package org.kuali.kra.irb.correspondence;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

/**
 * 
 * Action class for ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateAction extends KualiAction {
    /**
     * 
     * This method is called when adding a correspondence template.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward addCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getNewCorrespondenceTemplates().get(typeIndex);

        // check any business rules
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, 
                newCorrespondenceTemplate, typeIndex);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().addProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
            correspondenceTemplateForm.resetForm();
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when deleting a correspondence template
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward deleteCorrespondenceTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        int typeIndex = getSelectedCorrespondenceType(request);
        int templateIndex = getSelectedCorrespondenceTemplate(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(typeIndex);

        getProtocolCorrespondenceTemplateService().deleteProtocolCorrespondenceTemplate(correspondenceType, templateIndex);
        correspondenceTemplateForm.resetForm();

    	return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to get the protocol correspondence template service
     * @return ProtocolCorrespondenceTemplateService
     */
    private ProtocolCorrespondenceTemplateService getProtocolCorrespondenceTemplateService() {
        return (ProtocolCorrespondenceTemplateService) KraServiceLocator.getService("protocolCorrespondenceTemplateService");
    }
    
    /**
     * This method returns the index of the selected correspondence type.
     * @param request
     * @return index
     */
    protected int getSelectedCorrespondenceType(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "correspondenceType[", "]"));
        }
        return index;
    }

    /**
     * This method returns the index of the selected correspondence template.
     * @param request
     * @return index
     */
    protected int getSelectedCorrespondenceTemplate(HttpServletRequest request) {
        int index = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            index = Integer.parseInt(StringUtils.substringBetween(parameterName, "correspondenceTemplate[", "]"));
        }
        return index;
    }

    /**
     * 
     * This method is called when saving the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = correspondenceTemplateForm.getCorrespondenceTypes();
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        if (rulePassed) {
            getProtocolCorrespondenceTemplateService().saveProtocolCorrespondenceTemplates(protocolCorrespondenceTypes);
            //TODO: cniesen - display "RiceKeyConstants.MESSAGE_SAVED" message
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when reloading the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        GlobalVariables.getMessageMap().putInfo("generic.message", RiceKeyConstants.MESSAGE_RELOADED);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when closing the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is called when canceling the correspondence templates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return action forward
     * @throws Exception
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        //return mapping.findForward(Constants.MAPPING_BASIC);
        return super.cancelled(mapping, form, request, response);
    }
}
