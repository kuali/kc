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
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;

public class SubAwardFinancialAction extends SubAwardAction{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, ServletRequest request, ServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        ActionForward forward;
            forward = super.save(mapping, form, request, response);
        return forward;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        ActionForward forward = super.reload(mapping, form, request, response);
        return forward;
    }
    
    public ActionForward addAmountInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardAmountInfo amountInfo=subAwardForm.getNewSubAwardAmountInfo();       
       
        if(new SubAwardDocumentRule().processAddSubAwardAmountInfoBusinessRules(amountInfo)){   
            addAmountInfoToSubAward(subAwardForm.getSubAwardDocument().getSubAward(),amountInfo); 
            subAwardForm.setNewSubAwardAmountInfo(new SubAwardAmountInfo());
        }
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);        
     }
    boolean addAmountInfoToSubAward(SubAward subAward,SubAwardAmountInfo amountInfo){
        amountInfo.setSubAward(subAward);    
        return subAward.getSubAwardAmountInfoList().add(amountInfo);
    }
   
    public ActionForward deleteAmountInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardAmountInfo subAwardAmountInfo = subAwardDocument.getSubAward().getSubAwardAmountInfoList().get(selectedLineNumber);
        subAwardDocument.getSubAward().getSubAwardAmountInfoList().remove(selectedLineNumber);
        this.getBusinessObjectService().delete(subAwardAmountInfo);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }
    public ActionForward addAmountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {   
        
        SubAwardForm subAwardForm=(SubAwardForm) form;
        SubAwardAmountReleased subAwardAmountReleased =subAwardForm.getNewSubAwardAmountReleased();
        
       if(new SubAwardDocumentRule().processAddSubAwardAmountReleasedBusinessRules(subAwardAmountReleased)){ 
                addAmountReleasedToSubAward(subAwardForm.getSubAwardDocument().getSubAward(), subAwardAmountReleased);
                subAwardForm.setNewSubAwardAmountReleased(new SubAwardAmountReleased());
       }
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }

    boolean addAmountReleasedToSubAward(SubAward subAward,SubAwardAmountReleased subAwardAmountReleased){
        subAwardAmountReleased.setSubAward(subAward);    
        return subAward.getSubAwardAmountReleasedList().add(subAwardAmountReleased);
    }
    public ActionForward deleteAmountReleased(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm)form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardAmountReleased  SubAwardAmountReleased = subAwardDocument.getSubAward().getSubAwardAmountReleasedList().get(selectedLineNumber);
        subAwardDocument.getSubAward().getSubAwardAmountReleasedList().remove(selectedLineNumber);
        this.getBusinessObjectService().delete(SubAwardAmountReleased);
        return mapping.findForward(Constants.MAPPING_FINANCIAL_PAGE);
    }
}
