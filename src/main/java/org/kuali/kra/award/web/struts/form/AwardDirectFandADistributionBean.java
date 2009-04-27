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
package org.kuali.kra.award.web.struts.form;

import java.sql.Date;
import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardDirectFandADistribution;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.event.AwardDirectFandADistributionRuleEvent;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class supports the AwardForm class and the Award Time & Money Action class
 */
public class AwardDirectFandADistributionBean {

    private AwardForm parent;
    private AwardDirectFandADistribution newAwardDirectFandADistribution;
    
    /**
     * Constructs a DirectFandADistributionFormHelper
     * @param parent
     */
    public AwardDirectFandADistributionBean(AwardForm parent) {
        this.parent = parent;
        setNewAwardDirectFandADistribution (new AwardDirectFandADistribution());
    }
    
    /**
     * Constructs a AwardDirectFandADistributionBean.java.
     */
    public AwardDirectFandADistributionBean() {
        
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardDirectFandADistribution = new AwardDirectFandADistribution(); 
    }
    
    /**
     * Gets the parent attribute. 
     * @return Returns the parent.
     */
    public AwardForm getParent() {
        return parent;
    }
    
    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(AwardForm parent) {
        this.parent = parent;
    }
    
    
    /**
     * This method returns the AwardDocument.
     * @return
     */
    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }

    /**
     * Gets the newAwardDirectFandADistribution attribute. 
     * @return Returns the newAwardDirectFandADistribution.
     */
    public AwardDirectFandADistribution getNewAwardDirectFandADistribution() {
        return newAwardDirectFandADistribution;
    }

    /**
     * Sets the newAwardDirectFandADistribution attribute value.
     * @param newAwardDirectFandADistribution The newAwardDirectFandADistribution to set.
     */
    public void setNewAwardDirectFandADistribution(AwardDirectFandADistribution newAwardDirectFandADistribution) {
        this.newAwardDirectFandADistribution = newAwardDirectFandADistribution;
    }
    
    /**
     * This method is called when adding a new AwardDirectFandADistribution
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addAwardDirectFandADistribution(AwardDirectFandADistributionBean awardDirectFandADistributionBean) throws Exception {
        AwardForm awardForm = awardDirectFandADistributionBean.getParent();
        AwardDirectFandADistribution thisNewAwardDirectFandADistribution = awardDirectFandADistributionBean.getNewAwardDirectFandADistribution();
            if (applyAddRulesToTarget(awardForm, thisNewAwardDirectFandADistribution)) {
                findIndexAndAddTarget(awardDirectFandADistributionBean);
            }
                return true;
    }
    
    
    /**
     * This method finds the correct index of the list to add the target.
     * @param directFandADistributionFormHelper
     */
    private void findIndexAndAddTarget(AwardDirectFandADistributionBean awardDirectFandADistributionBean){
        Award award = awardDirectFandADistributionBean.getAwardDocument().getAward();
        List<AwardDirectFandADistribution> awardDirectFandADistributions = 
                                            awardDirectFandADistributionBean.getAwardDocument().getAward().getAwardDirectFandADistributions();
        AwardDirectFandADistribution thisNewAwardDirectFandADistribution = awardDirectFandADistributionBean.getNewAwardDirectFandADistribution();
        int index = 0;
      //this logic for case where the target date range falls into a valid period between the last element of the list and project end date.
        if(canTargetBeInsertedIntoLastIndex(awardDirectFandADistributions, thisNewAwardDirectFandADistribution, award.getProjectEndDate())){
            award.add(thisNewAwardDirectFandADistribution);
            awardDirectFandADistributionBean.init();
        }else {
            for(AwardDirectFandADistribution awardDirectFandADistribution : award.getAwardDirectFandADistributions()){
                if(awardDirectFandADistribution.getEndDate().compareTo(thisNewAwardDirectFandADistribution.getStartDate()) == -1) {
                    index++;
                }else {
                    award.add(index, thisNewAwardDirectFandADistribution);
                    awardDirectFandADistributionBean.init();
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
                                                                AwardDirectFandADistribution thisNewAwardDirectFandADistribution,
                                                                    Date projectEndDate) {
      AwardDirectFandADistribution lastItemInList = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
      return lastItemInList.getEndDate().before(thisNewAwardDirectFandADistribution.getStartDate())
              && thisNewAwardDirectFandADistribution.getEndDate().before(projectEndDate);
    }
    
    /**
     * This method apply all the rules on Add to the target period.
     * @param awardForm
     * @param newAwardDirectFandADistribution
     * @return
     */
    private boolean applyAddRulesToTarget(AwardForm awardForm, AwardDirectFandADistribution thisNewAwardDirectFandADistribution) {
        return getKualiRuleService().applyRules(new AwardDirectFandADistributionRuleEvent("", awardForm.getAwardDocument(), 
                                                                                            thisNewAwardDirectFandADistribution));
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
