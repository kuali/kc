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
