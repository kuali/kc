/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.customdata;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.util.ErrorMessage;

/**
 * Struts Action class for the Custom Data tab.
 */
public class ProtocolCustomDataAction extends ProtocolAction {

    private static final Log LOG = LogFactory.getLog(ProtocolCustomDataAction.class);
    
    private static final String CUSTOM_ATTRIBUTE_NAME = "IRBCustomDataAttribute";

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*
         * Primarily, the customdata.tag is using 'customdatahelper.xxx' as field name, 
         * but the field value is coming from document,  so this copy has to be done 
         * every time custom data page is refreshed. 
         */
        CustomDataAction.copyCustomDataToDocument(form);

        ((ProtocolForm)form).getCustomDataHelper().initializePermissions();
        
        return super.execute(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ProtocolForm protocolForm = (ProtocolForm) form;
        super.reload(mapping, form, request, response);
        CustomDataAction.copyCustomDataToDocument(form);
        ((ProtocolForm)form).getCustomDataHelper().prepareView(((ProtocolForm)form).getDocument());
        
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        
        for (Map.Entry<String, String[]> customAttributeValue : protocolForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            String value = customAttributeValue.getValue()[0];
            protocolDocument.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
        }
        return mapping.findForward("customData");    
    }

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        CustomDataAction.setCustomAttributeContent(form, CUSTOM_ATTRIBUTE_NAME);
        // a hook to display "successfully saved" message
        // seems not needed any more for rice20.
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setErrorKey("message.saved");
//        KNSGlobalVariables.getMessageList().add(errorMessage);

    }
    
    /**
     * Clears the lookup value for the customAttributeId given in the parameter methodToCall.clearLookupValue.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearLookupValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        CustomDataHelper customDataHelper = protocolForm.getCustomDataHelper();
        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolForm.getProtocolDocument().getCustomAttributeDocuments();
        String customAttributeId = request.getParameter("methodToCall.clearLookupValue");
        
        customDataHelper.clearCustomAttributeValue(customAttributeId);
        if (customAttributeDocuments.containsKey(customAttributeId)) {
            customAttributeDocuments.get(customAttributeId).getCustomAttribute().setValue(null);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}