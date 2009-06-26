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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.commitments.AddAwardFandaRateEvent;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * 
 * This class represents the Struts Action for Commitments page(AwardCommitments.jsp)
 */
public class AwardCommitmentsAction extends AwardAction {    
  
    private static final String CONFIRM_DELETE_COST_SHARE = "confirmDeleteCostShare";
    private static final String CONFIRM_DELETE_COST_SHARE_KEY = "confirmDeleteCostShareKey";
    
    private CostShareActionHelper costShareActionHelper;
    
    public AwardCommitmentsAction(){
        costShareActionHelper = new CostShareActionHelper();
    }
    
    /**
     * This method is used to add a new Award Cost Share
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
        costShareActionHelper.addCostShare(((AwardForm)form).getCostShareFormHelper());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>AwardCostShare</code> to
     * <code>Award</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param awardCostShare
     * @return
     */
    boolean addCostShareToAward(Award award, AwardCostShare awardCostShare){
        return award.getAwardCostShares().add(awardCostShare);
    }
    
    /**
     * This method is used to delete an Award Cost Share
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
     * This method is a convenience method for deleting an <code>AwardFandaRate</code> from
     * <code>Award</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteCostShareFromAward(Award award, int lineToDelete){
        award.getAwardCostShares().remove(lineToDelete);
        return true;
    }
    
    /**
     * This method is used to delete an Award Cost Share
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
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int delCostShare = getLineToDelete(request);
        
        awardDocument.getAward().getAwardCostShares().remove(delCostShare);
        
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
     * 
     * This method adds an <code>AwardFandaRate</code> business object to 
     * the list of <code>AwardFandaRate</code> business objects
     * It gets called upon delete action on F&A Rates Sub-Panel of Rates Panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addFandaRate(ActionMapping mapping, ActionForm form, HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardFandaRate newAwardFandaRate = awardForm.getNewAwardFandaRate();
        if(getKualiRuleService().applyRules(new AddAwardFandaRateEvent(Constants.EMPTY_STRING, 
                awardForm.getAwardDocument(), newAwardFandaRate))){
            addFandaRateToAward(awardForm.getAwardDocument().getAward(),newAwardFandaRate);            
            awardForm.setNewAwardFandaRate(new AwardFandaRate());
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);     
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>AwardFandaRate</code> to
     * <code>Award</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param awardFandaRate
     * @return
     */
    boolean addFandaRateToAward(Award award, AwardFandaRate awardFandaRate){
        awardFandaRate.setAward(award);
        return award.getAwardFandaRate().add(awardFandaRate);
    }
    
    /**
     * 
     * This method deletes an <code>AwardFandaRate</code> business object from 
     * the list of <code>AwardFandaRate</code> business objects
     * It gets called upon delete action on F&A Rates Sub-Panel of Rates Panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteFandaRate(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();        
        deleteFandaRateFromAward(awardDocument.getAward(),getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for deleting an <code>AwardFandaRate</code> from
     * <code>Award</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteFandaRateFromAward(Award award, int lineToDelete){
        award.getAwardFandaRate().remove(lineToDelete);
        return true;
    }
    
    /**
     * 
     * This method recalculates the Unrecovered F&A; It gets called upon Recalculate action on 
     * F&A Rates sub-panel
     * of Rates Panel.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recalculateFandARate(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /**
     * Overridden to silently change the two "Authorized Amount" values to positive numbers if they are negative.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#preDocumentSave(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     * @Override
     */
    protected void preDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.preDocumentSave(form);
        
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        
        // if authorizedAmount is negative, make it positive
        KualiDecimal authorizedAmount = award.getPreAwardAuthorizedAmount();
        if (authorizedAmount!=null && authorizedAmount.isNegative()) {
            authorizedAmount = KualiDecimal.ZERO.subtract(authorizedAmount);
            award.setPreAwardAuthorizedAmount(authorizedAmount);
        }
        
        // if institutionalAuthorizedAmount is negative, make it positive
        KualiDecimal institutionalAuthorizedAmount = award.getPreAwardInstitutionalAuthorizedAmount();
        if (institutionalAuthorizedAmount!=null && institutionalAuthorizedAmount.isNegative()) {
            institutionalAuthorizedAmount = KualiDecimal.ZERO.subtract(institutionalAuthorizedAmount);
            award.setPreAwardInstitutionalAuthorizedAmount(institutionalAuthorizedAmount);
        }
    }
}
