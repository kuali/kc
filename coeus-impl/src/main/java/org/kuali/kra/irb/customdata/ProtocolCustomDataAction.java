/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.customdata;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.coeus.common.framework.custom.SaveCustomDataEvent;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Struts Action class for the Custom Data tab.
 */
public class ProtocolCustomDataAction extends ProtocolAction {
    
    private static final String CUSTOM_ATTRIBUTE_NAME = "IRBCustomDataAttribute";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ((ProtocolForm)form).getCustomDataHelper().initializePermissions();
        return super.execute(mapping, form, request, response);
    }
    
    @Override
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
    

    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getCustomDataHelper().setCustomAttributeContent(protocolForm.getProtocolDocument().getDocumentNumber(), CUSTOM_ATTRIBUTE_NAME);
    }

}
