/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.customdata;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.coeus.common.framework.custom.SaveCustomDataEvent;
import org.kuali.rice.krad.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IacucProtocolCustomDataAction extends IacucProtocolAction {

    public String getCustomAttributeNameHook() {
        return "IacucCustomDataAttribute";
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm)form;
        protocolForm.getCustomDataHelper().initializePermissions();
        return forward;    
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        super.reload(mapping, form, request, response);
        protocolForm.getCustomDataHelper().prepareCustomData();
        return mapping.findForward("iacucCustomData");    
    }
    
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        //have to do the custom data validation here, separate from the document save, as invalid default values could cause the
        //document to be unusable.
        if (!new CustomDataRule().processRules(new SaveCustomDataEvent(protocolForm.getProtocolDocument()))) {
            throw new ValidationException("Custom data rule processing failed.");
        }
    }    

}
