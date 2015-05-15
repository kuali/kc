/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.commitments.AddAwardFandaRateEvent;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.commitments.AwardFandaRateRule;
import org.kuali.kra.award.commitments.AwardFandaRateSaveEvent;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
     * Overridden to validate F&amp;A rates
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isValid = true;
        AwardForm awardForm = (AwardForm)form;
        List<AwardFandaRate> fandaRates = awardForm.getAwardDocument().getAward().getAwardFandaRate();
        
        if (awardForm.getAwardDocument().getAward().getAwardFandaRate() == null || awardForm.getAwardDocument().getAward().getAwardFandaRate().isEmpty()) {
            awardForm.getAwardDocument().getAward().setIdcIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setIdcIndicator(Constants.YES_FLAG);
        }
        if (awardForm.getAwardDocument().getAward().getAwardCostShares() == null || awardForm.getAwardDocument().getAward().getAwardCostShares().isEmpty()) {
            awardForm.getAwardDocument().getAward().setCostSharingIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setCostSharingIndicator(Constants.YES_FLAG);
        }
        
        for (int i=0; i<fandaRates.size(); i++) {
            if (!getKualiRuleService().applyRules(new AwardFandaRateSaveEvent(Constants.EMPTY_STRING, awardForm.getAwardDocument(), i))) {
                isValid = false;
                //break;
            }
        }
        if (isValid) {
            return super.save(mapping, form, request, response);
        }
        else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
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
     * It gets called upon delete action on F&amp;A Rates Sub-Panel of Rates Panel
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
        AwardFandaRateRule rule = new AwardFandaRateRule();
        if (rule.processAddFandaRateBusinessRules(new AddAwardFandaRateEvent(Constants.EMPTY_STRING,awardForm.getAwardDocument(), newAwardFandaRate))) {
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
     * It gets called upon delete action on F&amp;A Rates Sub-Panel of Rates Panel
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
     * This method recalculates the Unrecovered F&amp;A; It gets called upon Recalculate action on
     * F&amp;A Rates sub-panel
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
}
