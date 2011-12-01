/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.util.GlobalVariables;

public abstract class CoiAction extends KraTransactionalDocumentActionBase {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        if(GlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((CoiDisclosureForm) form);
        }
        
        return forward;
    }
    
    public ActionForward disclosure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((CoiDisclosureForm)form).getDisclosureHelper().prepareView();
        return mapping.findForward("disclosure");
    }
    public ActionForward committee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("committee");
    }

    public ActionForward certification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("certification");
    }

    public ActionForward disclosureActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("disclosureActions");
    }


    protected CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);
    }


//    @Override
//    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//        throws Exception {
//        
//        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
//        // TODO : fill in detail here
//        actionForward = super.save(mapping, form, request, response);
//        
//        return actionForward;
//    }

}
