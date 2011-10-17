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
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;

public class SubAwardAmountReleasedAction extends SubAwardAction{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;
    }
    public ActionForward amountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {       

        return mapping.findForward(Constants.MAPPING_AMOUNT_RELEASED_PAGE);
    }
    
    public ActionForward addAmountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {   
        
        SubAwardForm subAwardForm=(SubAwardForm) form;
        SubAwardAmountReleased subAwardAmountReleased =subAwardForm.getNewSubAwardAmountReleased();
        
       if(new SubAwardDocumentRule().processAddSubAwardAmountReleasedBusinessRules(subAwardAmountReleased)){ 
                addAmountReleasedToSubAward(subAwardForm.getSubAwardDocument().getSubAward(), subAwardAmountReleased);
                subAwardForm.setNewSubAwardAmountReleased(new SubAwardAmountReleased());
       }
        return mapping.findForward(Constants.MAPPING_AMOUNT_RELEASED_PAGE);
    }

    boolean addAmountReleasedToSubAward(SubAward subAward,SubAwardAmountReleased subAwardAmountReleased){
        subAwardAmountReleased.setSubAward(subAward);    
        return subAward.getSubAwardAmountReleasedList().add(subAwardAmountReleased);
    }
    
    
    
}
