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
package org.kuali.kra.subaward.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;

/**
 * 
 * This class represents the Struts Action for Medusa page(AwardMedusa.jsp)
 */
public class SubAwardMedusaAction extends SubAwardAction{

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {SubAwardForm subAwardForm = (SubAwardForm)form;
            if (subAwardForm.getDocument().getDocumentNumber() == null) {
                loadDocumentInForm(request, subAwardForm);
            }
            subAwardForm.getMedusaBean().setMedusaViewRadio("0");
            subAwardForm.getMedusaBean().setModuleName("subaward");
            subAwardForm.getMedusaBean().setModuleIdentifier(subAwardForm.getSubAwardDocument().getSubAward().getSubAwardId());
            return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);}
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);
    }
}
