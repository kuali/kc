/*
 * Copyright 2005-2013 The Kuali Foundation
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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.rule.event.SaveCustomDataEvent;
import org.kuali.kra.rules.CustomDataRule;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.util.KRADConstants;

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
        ((ProtocolForm)form).getCustomDataHelper().prepareCustomData();
        return mapping.findForward("customData");    
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ProtocolForm protocolForm = (ProtocolForm) form;
        //have to do the custom data validation here, separate from the document save, as invalid default values could cause the
        //document to be unusable.
        if (!new CustomDataRule().processRules(new SaveCustomDataEvent(protocolForm.getProtocolDocument()))) {
            throw new ValidationException("Custom data rule processing failed.");
        }
    }
    

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getCustomDataHelper().setCustomAttributeContent(protocolForm.getProtocolDocument().getDocumentNumber(), CUSTOM_ATTRIBUTE_NAME);
    }

}