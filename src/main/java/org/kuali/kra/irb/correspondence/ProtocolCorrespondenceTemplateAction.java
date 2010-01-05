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
package org.kuali.kra.irb.correspondence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;
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
        int index = getSelectedCorrespondenceType(request);
        ProtocolCorrespondenceTemplateForm correspondenceTemplateForm = (ProtocolCorrespondenceTemplateForm) form;
        ProtocolCorrespondenceType correspondenceType = correspondenceTemplateForm.getCorrespondenceTypes().get(index);
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = correspondenceTemplateForm.getNewCorrespondenceTemplates().get(index);

        getProtocolCorrespondenceTemplateService().addProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);
        //correspondenceTemplateForm.setNewCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to get the protocol correspondence template service
     * @return ProtocolCorrespondenceTemplateService
     */
    private ProtocolCorrespondenceTemplateService getProtocolCorrespondenceTemplateService() {
        return (ProtocolCorrespondenceTemplateService)KraServiceLocator.getService("protocolCorrespondenceTemplateService");
    }
    
    protected int getSelectedCorrespondenceType(HttpServletRequest request) {
        int selectedCorrespondenceType = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedCorrespondenceType = Integer.parseInt(StringUtils.substringBetween(parameterName, "correspondenceType[", "]"));
        }
        return selectedCorrespondenceType;
    }

}
