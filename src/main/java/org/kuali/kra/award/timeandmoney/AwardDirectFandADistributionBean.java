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
package org.kuali.kra.award.timeandmoney;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.service.KualiRuleService;

/**
 * This class supports the AwardForm class and the Award Time & Money Action class
 */
public class AwardDirectFandADistributionBean implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6274443203617122440L;
    private TimeAndMoneyForm parent;
    private AwardDirectFandADistribution newAwardDirectFandADistribution;
    transient AwardAmountInfoService awardAmountInfoService;
    
    /**
     * Constructs a DirectFandADistributionFormHelper
     * @param parent
     */
    public AwardDirectFandADistributionBean(TimeAndMoneyForm parent) {
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
    public TimeAndMoneyForm getParent() {
        return parent;
    }
    
    /**
     * Sets the parent attribute value.
     * @param parent The parent to set.
     */
    public void setParent(TimeAndMoneyForm parent) {
        this.parent = parent;
    }
    
    
    /**
     * This method returns the AwardDocument.
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return parent.getTimeAndMoneyDocument();
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
        TimeAndMoneyForm timeAndMoneyForm = awardDirectFandADistributionBean.getParent();
        AwardDirectFandADistribution thisNewAwardDirectFandADistribution = awardDirectFandADistributionBean.getNewAwardDirectFandADistribution();
            if (applyAddRulesToTarget(timeAndMoneyForm, thisNewAwardDirectFandADistribution)) {
                findIndexAndAddTarget(awardDirectFandADistributionBean);
            }
                return true;
    }
    
    
    /**
     * This method finds the correct index of the list to add the target.
     * @param directFandADistributionFormHelper
     */
    private void findIndexAndAddTarget(AwardDirectFandADistributionBean awardDirectFandADistributionBean){
        Award award = awardDirectFandADistributionBean.getTimeAndMoneyDocument().getAward();
        List<AwardDirectFandADistribution> awardDirectFandADistributions = 
                                            awardDirectFandADistributionBean.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions();
        AwardDirectFandADistribution thisNewAwardDirectFandADistribution = awardDirectFandADistributionBean.getNewAwardDirectFandADistribution();
        int index = 0;
        if(awardDirectFandADistributions.size() == 0) {
            award.add(index, thisNewAwardDirectFandADistribution);
            awardDirectFandADistributionBean.init();
        }
        else {
        //this logic for case where the target date range falls into a valid period between the last element of the list and project end date.
            if(canTargetBeInsertedIntoLastIndex(awardDirectFandADistributions, thisNewAwardDirectFandADistribution, getAwardAmountInfoService().fetchAwardAmountInfoWithHighestTransactionId
                    (award.getAwardAmountInfos()).getFinalExpirationDate())){
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
    }
    
    public AwardAmountInfoService getAwardAmountInfoService() {
        awardAmountInfoService = KraServiceLocator.getService(AwardAmountInfoService.class);
        return awardAmountInfoService;
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
        boolean isDistrEndDateBeforeProjectEndDate = thisNewAwardDirectFandADistribution.getEndDate().before(projectEndDate) ||
                                                        thisNewAwardDirectFandADistribution.getEndDate().equals(projectEndDate);
        if (awardDirectFandADistributions.isEmpty()) {
            return isDistrEndDateBeforeProjectEndDate;
        }
        else {
            AwardDirectFandADistribution lastItemInList = awardDirectFandADistributions.get(awardDirectFandADistributions.size() - 1);
            return lastItemInList.getEndDate().before(thisNewAwardDirectFandADistribution.getStartDate())
                    && isDistrEndDateBeforeProjectEndDate;
        }
    }
    
    /**
     * This method apply all the rules on Add to the target period.
     * @param awardForm
     * @param newAwardDirectFandADistribution
     * @return
     */
    private boolean applyAddRulesToTarget(TimeAndMoneyForm timeAndMoneyForm, AwardDirectFandADistribution thisNewAwardDirectFandADistribution) {
        return getKualiRuleService().applyRules(new AwardDirectFandADistributionRuleEvent("", timeAndMoneyForm.getTimeAndMoneyDocument(), 
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
