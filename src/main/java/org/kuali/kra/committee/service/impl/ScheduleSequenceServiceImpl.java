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
package org.kuali.kra.committee.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.quartz.CronTrigger;
import org.quartz.TriggerUtils;

public class ScheduleSequenceServiceImpl {
    
    public static final String EXPR_TEST = "15 10 0/4 * * ?";
    
    public static final String EXPR_DAILY_EVERYDAY = "0 0 1 1/2 2-3 ? 2009";
    
    public static void main(String args[]) throws Exception {
        
        java.util.Date st = new java.util.Date();
        java.util.Date ed = new java.util.Date();
        
       // st = DateUtils.addDays(st, 30);
        ed = DateUtils.addDays(ed, 300);
        
        //String expr = ExprUtil.getExpressionForNever(st, "12:12");
        //String expr = ExprUtil.getExpressionForDay(st, "12:12", "2");
        //String expr = ExprUtil.getExpressionForWeekDay("12:12");
        //String expr = ExprUtil.getExpressionForWeekly(st,"12:12","MON","WED");
        String expr = ExprUtil.getExpressionForMonthly(st,"12:12","11","2");
        //String expr = ExprUtil.getExpressionForMonthlyWithDay(st,"12:12","1","5", 4);
        //String expr = ExprUtil.getExpressionForYearlyWithDay(st, "12:12", "1", "MAR", "3");
        //String expr = ExprUtil.getExpressionForYearlyWithDayOfWeek(st, "12:12", "2", 5, "MAR", "2");
        System.out.println("exper : " + expr);
        System.out.println("St Date : " + st);
        System.out.println("Ed Date : " + ed);
        
        List<Date> times = getFireSequence(expr, st, ed);
        for (int i = 0; i < times.size(); i++)
            System.err.println("firetime = " + times.get(i));

    }
 
    public static List<Date> getFireSequence(String expr, Date startDate, Date endDate) throws ParseException {    
        CronTrigger ct = new CronTrigger("t", "g", "j", "g", new Date(), null, expr);
        ct.setTimeZone(TimeZone.getDefault());
        System.err.println(ct.getExpressionSummary());
        return TriggerUtils.computeFireTimesBetween(ct, null, startDate, endDate);
    }
    
}
