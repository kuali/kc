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
import org.kuali.rice.kns.util.KNSConstants;

public class SubAwardHomeAction extends SubAwardAction{
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);       
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
       
        
        ActionForward forward = super.save(mapping, form, request, response);

      
        
        return forward;
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        SubAwardForm subAwardMultiLookupForm = (SubAwardForm)form;
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        subAwardMultiLookupForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        subAwardMultiLookupForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        SubAward subAward = subAwardMultiLookupForm.getSubAwardDocument().getSubAward();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
