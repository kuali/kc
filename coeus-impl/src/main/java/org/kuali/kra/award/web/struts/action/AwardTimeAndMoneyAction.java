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


/**
 * 
 * This class represents the Struts Action for Time & Money page(AwardTimeAndMoney.jsp)
 */
public class AwardTimeAndMoneyAction extends AwardAction {    
  
//    private AwardDirectFandADistributionBean awardDirectFandADistributionBean;
//    
//    public AwardTimeAndMoneyAction(){
//        awardDirectFandADistributionBean = new AwardDirectFandADistributionBean();
//    }
//    
//    /**
//     * 
//     * This method adds a new AwardDirectFandADistribution to the list.  
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward addAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        awardDirectFandADistributionBean.addAwardDirectFandADistribution(((AwardForm) form).getAwardDirectFandADistributionBean());    
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//    
//    /**
//     * 
//     * This method removes an AwardDirectFandADistribution from the list. 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        AwardForm awardForm = (AwardForm) form;
//        awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions().remove(getLineToDelete(request));
//        awardDirectFandADistributionBean.updateBudgetPeriodsAfterDelete(awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions());
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//    
//    /**
//     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return mapping forward
//     * @throws Exception
//     */
//    public ActionForward recalculateDirectFandADistributionTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//       
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
}
