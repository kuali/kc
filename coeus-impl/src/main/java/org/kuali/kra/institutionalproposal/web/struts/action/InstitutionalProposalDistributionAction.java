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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.*;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InstitutionalProposalDistributionAction extends InstitutionalProposalAction {

    private static final String CONFIRM_DELETE_COST_SHARE = "confirmDeleteCostShare";
    private static final String CONFIRM_DELETE_COST_SHARE_KEY = "confirmDeleteCostShareKey";
    
    private static final String CONFIRM_DELETE_UNRECOVERED_FNA = "confirmDeleteUnrecoveredFandA";
    private static final String CONFIRM_DELETE_UNRECOVERED_FNA_KEY = "confirmDeleteUnrecoveredFandAKey";
    
    private InstitutionalProposalUnrecoveredFandABean institutionalProposalUnrecoveredFandABean;
    private InstitutionalProposalCostShareBean institutionalProposalCostShareBean;
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposal institutionalProposal = ((InstitutionalProposalForm)form).getInstitutionalProposalDocument().getInstitutionalProposal();
        if (!institutionalProposal.getInstitutionalProposalCostShares().isEmpty()) {
            institutionalProposal.setCostSharingIndicator("1");
        } else {
            institutionalProposal.setCostSharingIndicator("0");
        }
        
        if (!institutionalProposal.getInstitutionalProposalUnrecoveredFandAs().isEmpty()) {
           institutionalProposal.setIdcRateIndicator("1"); 
        } else {
            institutionalProposal.setIdcRateIndicator("0");
        }
        return super.save(mapping, form, request, response);
    }
    
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
