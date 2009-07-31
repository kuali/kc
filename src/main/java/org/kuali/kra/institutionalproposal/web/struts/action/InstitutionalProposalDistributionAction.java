/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShareBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandABean;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

/**
 * This class...
 */
public class InstitutionalProposalDistributionAction extends InstitutionalProposalAction {

    private static final String CONFIRM_DELETE_COST_SHARE = "confirmDeleteCostShare";
    private static final String CONFIRM_DELETE_COST_SHARE_KEY = "confirmDeleteCostShareKey";
    
    private static final String CONFIRM_DELETE_UNRECOVERED_FNA = "confirmDeleteUnrecoveredFandA";
    private static final String CONFIRM_DELETE_UNRECOVERED_FNA_KEY = "confirmDeleteUnrecoveredFandAKey";
    
    private InstitutionalProposalUnrecoveredFandABean institutionalProposalUnrecoveredFandABean;
    private InstitutionalProposalCostShareBean institutionalProposalCostShareBean;
    
    public InstitutionalProposalDistributionAction(){
        institutionalProposalCostShareBean = new InstitutionalProposalCostShareBean();
        institutionalProposalUnrecoveredFandABean = new InstitutionalProposalUnrecoveredFandABean();
    }
    
    /**
     * This method is used to add a new InstitutionalProposal Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        institutionalProposalCostShareBean.addCostShare(((InstitutionalProposalForm)form).getInstitutionalProposalCostShareBean());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>InstitutionalProposalCostShare</code> to
     * <code>InstitutionalProposal</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param nstitutionalProposal
     * @param institutionalProposalCostShare
     * @return
     */
    boolean addCostShareToInstitutionalProposal(InstitutionalProposal institutionalProposal, InstitutionalProposalCostShare institutionalProposalCostShare){
        return institutionalProposal.getInstitutionalProposalCostShares().add(institutionalProposalCostShare);
    }
    
    /**
     * This method is used to delete an InstitutionalProposal Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int delCostShare = getLineToDelete(request);
        return confirm(buildDeleteCostShareConfirmationQuestion(mapping, form, request, response,
                delCostShare+1), CONFIRM_DELETE_COST_SHARE, "");
    }
    
    /**
     * 
     * This method is a convenience method for deleting an <code>InstitutionalProposalFandaRate</code> from
     * <code>InstitutionalProposal</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteCostShareFromInstitutionalProposal(InstitutionalProposal institutionalProposal, int lineToDelete){
        institutionalProposal.getInstitutionalProposalCostShares().remove(lineToDelete);
        return true;
    }
    
    /**
     * This method is used to delete an InstitutionalProposal Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward confirmDeleteCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        int delCostShare = getLineToDelete(request);
        
        institutionalProposalDocument.getInstitutionalProposal().getInstitutionalProposalCostShares().remove(delCostShare);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total commitment amount in the Cost Share panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateCostShareTotal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to build the confirmation question for deleting Cost Shares.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteCostShareConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deleteCostShare) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_COST_SHARE_KEY,
                KeyConstants.QUESTION_DELETE_COST_SHARE, Integer.toString(deleteCostShare));
    }
    
    /**
     * This method is used to add a new InstitutionalProposal Unrecovered F and A
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        institutionalProposalUnrecoveredFandABean.addUnrecoveredFandA(((InstitutionalProposalForm)form).getInstitutionalProposalUnrecoveredFandABean());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>InstitutionalProposalUnrecovered F and A</code> to
     * <code>InstitutionalProposal</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param nstitutionalProposal
     * @param institutionalProposalUnrecoveredFandA
     * @return
     */
    boolean addUnrecoveredFandAToInstitutionalProposal(InstitutionalProposal institutionalProposal, InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA){
        return institutionalProposal.getInstitutionalProposalUnrecoveredFandAs().add(institutionalProposalUnrecoveredFandA);
    }
    
    /**
     * This method is used to delete an InstitutionalProposal Unrecovered FandA
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int delCostShare = getLineToDelete(request);
        return confirm(buildDeleteUnrecoveredFandAConfirmationQuestion(mapping, form, request, response,
                delCostShare+1), CONFIRM_DELETE_UNRECOVERED_FNA, "");
    }
    
    /**
     * 
     * This method is a convenience method for deleting an <code>InstitutionalProposalFandaRate</code> from
     * <code>InstitutionalProposal</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteUnrecoveredFandAFromInstitutionalProposal(InstitutionalProposal institutionalProposal, int lineToDelete){
        institutionalProposal.getInstitutionalProposalUnrecoveredFandAs().remove(lineToDelete);
        return true;
    }
    
    /**
     * This method is used to delete an InstitutionalProposal Cost Share
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward confirmDeleteUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = institutionalProposalForm.getInstitutionalProposalDocument();
        int delCostShare = getLineToDelete(request);
        
        institutionalProposalDocument.getInstitutionalProposal().getInstitutionalProposalUnrecoveredFandAs().remove(delCostShare);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total commitment amount in the Cost Share panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateUnrecoveredFandATotal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to build the confirmation question for deleting Cost Shares.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteUnrecoveredFandAConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, int deleteCostShare) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_UNRECOVERED_FNA_KEY,
                KeyConstants.QUESTION_DELETE_UNRECOVERED_FNA, Integer.toString(deleteCostShare));
    }

}
