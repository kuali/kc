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
