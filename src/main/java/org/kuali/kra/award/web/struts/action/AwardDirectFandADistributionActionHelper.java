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

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRuleEvent;
import org.kuali.kra.award.timeandmoney.DirectFandADistributionFormHelper;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class contains logic for action methods specific to Award Direct F and A Distribution.
 */
public class AwardDirectFandADistributionActionHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8068300104853399208L;

    /**
     * This method is called when adding a new AwardDirectFandADistribution
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addAwardDirectFandADistribution(DirectFandADistributionFormHelper directFandADistributionFormHelper) throws Exception {
        AwardForm awardForm = directFandADistributionFormHelper.getParent();
        AwardDirectFandADistribution newAwardDirectFandADistribution = directFandADistributionFormHelper.getNewAwardDirectFandADistribution();
            if (applyAddRulesToTarget(awardForm, newAwardDirectFandADistribution)) {
                findIndexAndAddTarget(directFandADistributionFormHelper);
            }
                return true;
    }
    
    
    /**
     * This method finds the correct index of the list to add the target.
     * @param directFandADistributionFormHelper
     */
    private void findIndexAndAddTarget(DirectFandADistributionFormHelper directFandADistributionFormHelper){
        Award award = directFandADistributionFormHelper.getAwardDocument().getAward();
        List<AwardDirectFandADistribution> awardDirectFandADistributions = 
                                                    directFandADistributionFormHelper.getAwardDocument().getAward().getAwardDirectFandADistributions();
        AwardDirectFandADistribution newAwardDirectFandADistribution = directFandADistributionFormHelper.getNewAwardDirectFandADistribution();
        int index = 0;
      //this logic for case where the target date range falls into a valid period between the last element of the list and project end date.
        if(canTargetBeInsertedIntoLastIndex(awardDirectFandADistributions, newAwardDirectFandADistribution, award.getProjectEndDate())){
            award.add(newAwardDirectFandADistribution);
            directFandADistributionFormHelper.init();
        }else {
            for(AwardDirectFandADistribution awardDirectFandADistribution : award.getAwardDirectFandADistributions()){
                if(awardDirectFandADistribution.getEndDate().compareTo(newAwardDirectFandADistribution.getStartDate()) == -1) {
                    index++;
                }else {
                    award.add(index, newAwardDirectFandADistribution);
                    directFandADistributionFormHelper.init();
                    break;
                }
            }
        }
    }
    
    /**
     * This method for a special case where the target period falls between the last index of the list and project end date.
     * @param awardDirectFandADistributions
     * @param newAwardDirectFandADistribution
     * @param projectEndDate
     * @return
     */
    private boolean canTargetBeInsertedIntoLastIndex(List<AwardDirectFandADistribution> awardDirectFandADistributions, 
                                                                AwardDirectFandADistribution newAwardDirectFandADistribution,
                                                                    Date projectEndDate) {
      AwardDirectFandADistribution lastItemInList = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
      return lastItemInList.getEndDate().before(newAwardDirectFandADistribution.getStartDate())
              && newAwardDirectFandADistribution.getEndDate().before(projectEndDate);
    }
    
    /**
     * This method apply all the rules on Add to the target period.
     * @param awardForm
     * @param newAwardDirectFandADistribution
     * @return
     */
    private boolean applyAddRulesToTarget(AwardForm awardForm, AwardDirectFandADistribution newAwardDirectFandADistribution) {
        return getKualiRuleService().applyRules(new AwardDirectFandADistributionRuleEvent("", awardForm.getAwardDocument(), newAwardDirectFandADistribution));
    }
    
    
    /**
     * This method resets all buget periods after deleting an item from the list.
     * @param awardDirectFandADistributions
     */
    public void updateBudgetPeriodsAfterDelete(List<AwardDirectFandADistribution> awardDirectFandADistributions) {
        int index = 1;
        for(AwardDirectFandADistribution awardDirectFandADistribution : awardDirectFandADistributions) {
            awardDirectFandADistribution.setBudgetPeriod(index);
            index++;
        }
    }
    
    /**
     * 
     * This method is a helper method to retrieve KualiRuleService.
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
}
