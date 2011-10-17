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
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;

public class SubAwardContactsAction extends SubAwardAction {
    
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
    public ActionForward addContacts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {   
        
        SubAwardForm subAwardForm=(SubAwardForm) form;
        SubAwardContact subAwardContact =subAwardForm.getNewSubAwardContact();
        
        if(new SubAwardDocumentRule().processAddSubAwardContactBusinessRules(subAwardContact)){
            addContactsToSubAward(subAwardForm.getSubAwardDocument().getSubAward(), subAwardContact);
            subAwardForm.setNewSubAwardContact(new SubAwardContact());
        }
        return mapping.findForward(Constants.MAPPING_CONTACTS_PAGE);
    }

    boolean addContactsToSubAward(SubAward subAward,SubAwardContact subAwardContact){
        subAwardContact.setSubAward(subAward);    
        return subAward.getSubAwardContactsList().add(subAwardContact);
    }
    
    public ActionForward deleteContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        subAwardDocument.getSubAward().getSubAwardContactsList().remove(selectedLineNumber);
        return mapping.findForward(Constants.MAPPING_CONTACTS_PAGE);
    }
    

}
