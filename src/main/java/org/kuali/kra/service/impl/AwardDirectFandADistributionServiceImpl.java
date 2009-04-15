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
package org.kuali.kra.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardDirectFandADistribution;
import org.kuali.kra.service.AwardDirectFandADistributionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service class for Direct F and A Distribution tab in Award Time & Money page.
 */
@Transactional
public class AwardDirectFandADistributionServiceImpl implements AwardDirectFandADistributionService {

    /**
     * This method assigns all of the Default periods to Award Direct F and A Distribution panel based on project start and end dates.
     * @return
     */
   public List<AwardDirectFandADistribution> generateDefaultAwardDirectFandADistributionPeriods(Award award){
       return buildListBasedOnProjectStartAndEndDates(award);
   }
   
   /**
 * This method builds the default list of Award Direct F and A Distributions to be added to the Award on navigation to Time & Money panel.
 * @param award
 * @return
 */
   private List<AwardDirectFandADistribution> buildListBasedOnProjectStartAndEndDates(Award award) {
       List<AwardDirectFandADistribution> awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
       Date projectStartDate = award.getBeginDate();
       Date projectEndDate = award.getProjectEndDate();
       boolean budgetPeriodExists = true;
       Calendar cl = Calendar.getInstance();
       Date periodStartDate = projectStartDate;
       int budgetPeriodNum = 1;
       while(budgetPeriodExists) {
           cl.setTime(periodStartDate);
           cl.add(Calendar.YEAR, 1);
           Date nextPeriodStartDate = new Date(cl.getTime().getTime());
           cl.add(Calendar.DATE, -1);
           Date periodEndDate = new Date(cl.getTime().getTime());
           switch(periodEndDate.compareTo(projectEndDate)) {
               case 1:
                   periodEndDate = projectEndDate;
               case 0:
                   budgetPeriodExists = false;
                   break;}
           awardDirectFandADistributions.add(buildNewAwardDirectFandADistribution(budgetPeriodNum, periodStartDate, periodEndDate));
           periodStartDate = nextPeriodStartDate;
           budgetPeriodNum++;
       }
           return awardDirectFandADistributions;
   }

   /**
 * This method is helper method to build the AwardDirectFandADistribution to be added to the list.
 * @param budgetPeriodNum
 * @param periodStartDate
 * @param periodEndDate
 * @return
 */
private AwardDirectFandADistribution buildNewAwardDirectFandADistribution(int budgetPeriodNum, Date periodStartDate, Date periodEndDate) {
       AwardDirectFandADistribution awardDirectFandADistribution = new AwardDirectFandADistribution();
       awardDirectFandADistribution.setBudgetPeriod(budgetPeriodNum);
       awardDirectFandADistribution.setStartDate(periodStartDate);
       awardDirectFandADistribution.setEndDate(periodEndDate);
       return awardDirectFandADistribution;
   }
}










