/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

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

        ((ProtocolForm)form).getCustomDataHelper().prepareView();
        
        return super.execute(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.kra.irb.web.struts.action.ProtocolAction#isValidSave(org.kuali.kra.irb.web.struts.form.ProtocolForm)
     */
    @Override
    protected boolean isValidSave(ProtocolForm protocolForm) {
        return super.isValidSave(protocolForm) && 
               applyRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING, protocolForm.getDocument()));
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
