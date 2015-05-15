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
package org.kuali.kra.award.service.impl;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.service.AwardDirectFandADistributionService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the service class for Direct F and A Distribution tab in Award Time &amp; Money page.
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
 * This method builds the default list of Award Direct F and A Distributions to be added to the Award on navigation to Time &amp; Money panel.
 * @param award
 * @return
 */
   protected List<AwardDirectFandADistribution> buildListBasedOnProjectStartAndEndDates(Award award) {
       List<AwardDirectFandADistribution> awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
       Date projectStartDate = award.getAwardEffectiveDate();
       
       //KRACOEUS-6546
       // the project start date is stored in the award but the end date is stored in the awardAmountInfo bean. 
       // When awards are versioned, there are multiple awardAmountInfo entries. Get the latest entry since the
       // project start date in the award is the latest.
       AwardAmountInfo latestAwardAmountInfo = award.getAwardAmountInfos().get(award.getAwardAmountInfos().size() - 1);
       Date projectEndDate = latestAwardAmountInfo.getFinalExpirationDate();
       Calendar cl = Calendar.getInstance();
       Date periodStartDate = projectStartDate;
       int budgetPeriodNum = 1;
       while(true) {
           cl.setTime(periodStartDate);
           cl.add(Calendar.YEAR, 1);
           Date nextPeriodStartDate = new Date(cl.getTime().getTime());
           cl.add(Calendar.DATE, -1);
           Date periodEndDate = new Date(cl.getTime().getTime());
           if (periodEndDate.after(projectEndDate) || periodEndDate.equals(projectEndDate)) {
               periodEndDate = projectEndDate;
               awardDirectFandADistributions.add(new AwardDirectFandADistribution(budgetPeriodNum, periodStartDate, periodEndDate));
               break;
           }
           awardDirectFandADistributions.add(new AwardDirectFandADistribution(budgetPeriodNum, periodStartDate, periodEndDate));
           periodStartDate = nextPeriodStartDate;
           budgetPeriodNum++;
       }
       return awardDirectFandADistributions;
   }
}










