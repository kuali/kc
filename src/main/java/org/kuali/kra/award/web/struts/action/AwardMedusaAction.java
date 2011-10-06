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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.GlobalVariables;


/**
 * 
 * This class represents the Struts Action for Medusa page(AwardMedusa.jsp)
 */
public class AwardMedusaAction extends AwardAction {    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception { AwardForm awardForm = (AwardForm) form;
            if (awardForm.getDocument().getDocumentNumber() == null) {
                //if we are entering this from the search results
                loadDocumentInForm(request, awardForm);
            }
            awardForm.getMedusaBean().setMedusaViewRadio("0");
            awardForm.getMedusaBean().setModuleName("award");
            awardForm.getMedusaBean().setModuleIdentifier(awardForm.getAwardDocument().getAward().getAwardId());
            return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);}
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
}
