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
package org.kuali.kra.award.timeandmoney;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.krad.service.KualiRuleService;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * This class supports the AwardForm class and the Award Time &amp; Money Action class
 */
public class AwardDirectFandADistributionBean implements Serializable{


    private static final long serialVersionUID = 6274443203617122440L;
    private TimeAndMoneyForm parent;
    private AwardDirectFandADistribution newAwardDirectFandADistribution;
    transient AwardAmountInfoService awardAmountInfoService;

    public AwardDirectFandADistributionBean(TimeAndMoneyForm parent) {
        this.parent = parent;
        setNewAwardDirectFandADistribution (new AwardDirectFandADistribution());
    }
    

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
        awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
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
     * @param timeAndMoneyForm
     * @param thisNewAwardDirectFandADistribution
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
        return KcServiceLocator.getService(KualiRuleService.class);
    }
}
