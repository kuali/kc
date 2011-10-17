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

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;


public class SubAwardCloseoutAction extends SubAwardAction {
    
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
       

        return actionForward;
    }
    
    /**
     * This method is used to add a new Budget Period
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */    
    public ActionForward addCloseouts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {   
        
        SubAwardForm subAwardForm=(SubAwardForm) form;
        SubAwardCloseout subAwardCloseout =subAwardForm.getNewSubAwardCloseout();
        
       if(new SubAwardDocumentRule().processAddSubAwardCloseoutBusinessRules(subAwardCloseout)){
           addCloseoutToSubAward(subAwardForm.getSubAwardDocument().getSubAward(), subAwardCloseout);
           subAwardForm.setNewSubAwardCloseout(new SubAwardCloseout());
       }
        return mapping.findForward(Constants.MAPPING_CLOSEOUT_PAGE);
    }

    boolean addCloseoutToSubAward(SubAward subAward,SubAwardCloseout subAwardCloseout){
        subAwardCloseout.setSubAward(subAward);    
        return subAward.getSubAwardCloseoutList().add(subAwardCloseout);
    }
    
   public ActionForward deleteCloseout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        subAwardDocument.getSubAward().getSubAwardCloseoutList().remove(selectedLineNumber);
        return mapping.findForward(Constants.MAPPING_CLOSEOUT_PAGE);
    }

}
