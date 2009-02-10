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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExprUtil {
    //done
    public static String getExpressionForNever(Date startDate, String time){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        
        int stDt_dayOfMonth = stDt.get(Calendar.DAY_OF_MONTH);
        int stDt_month = stDt.get(Calendar.MONTH);
        int stDt_year = stDt.get(Calendar.YEAR);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        exp.append(stDt_dayOfMonth+" ");        
        exp.append("* ? ");
        return exp.toString();
        
    }
    //done - combined
    public static String getExpressionForDay(Date startDate, String time, String day){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        
        int stDt_dayOfMonth = stDt.get(Calendar.DAY_OF_MONTH);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append(stDt_dayOfMonth+"/"+day+" ");
        
        exp.append("* ? ");
        return exp.toString();
        
    }
    //done - combined
    public static String getExpressionForWeekDay(String time){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
               
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append("? ");
        
        exp.append("* SUN-SAT ");
        return exp.toString();
        
    }
    
    //done -0
    public static String getExpressionForWeekly(Date startDate, String time, String ... weekdays){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append("? ");
        
        StringBuilder sb = new StringBuilder();
        for(String str: weekdays) {
            sb.append(str);
            sb.append(",");
        }
        String wkdy = sb.substring(0, sb.length()-1);
        exp.append("* " + wkdy);
        return exp.toString();
        
    }
  //done -0
    public static String getExpressionForMonthly(Date startDate, String time, String day, String month){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append(day+" ");
        exp.append(stDt.get(Calendar.MONTH)+1+"/"+month+" ");
        exp.append(" ?");
        return exp.toString();
        
    }    
    public static String getExpressionForMonthlyWithDay(Date startDate, String time, String month, String dayOfWeek, int week){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append("? ");
        exp.append(stDt.get(Calendar.MONTH)+1+"/"+month+" ");
        //TODO value of week can only be between 1 to 5 or should we store as string
        if(!(week == 5))
            exp.append(dayOfWeek+"#"+week);
        else
            exp.append(dayOfWeek+"L");
        return exp.toString();
        
    }

    public static String getExpressionForYearlyWithDay(Date startDate, String time, String day, String month, String year){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        int stDt_year = stDt.get(Calendar.YEAR);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append(day+" ");
        exp.append(month+" ");
        exp.append("? ");
        exp.append(stDt_year+"/"+year+" ");
        return exp.toString();
        
    }
    
    public static String getExpressionForYearlyWithDayOfWeek(Date startDate, String time, String dayOfWeek, int week, String month, String year){
        
        String hour = time.substring(0, 2);
        String minute = time.substring(3);
        
        Calendar stDt = new GregorianCalendar();
        stDt.setTime(startDate);
        int stDt_year = stDt.get(Calendar.YEAR);
        
        StringBuilder exp = new StringBuilder("0 ");
        exp.append(minute + " ").append(hour + " ");
        
        exp.append("? ");
        exp.append(month+" ");
        //TODO value of week can only be between 1 to 5 or should we store as string
        if(!(week == 5))
            exp.append(dayOfWeek+"#"+week);
        else
            exp.append(dayOfWeek+"L");
        exp.append(" ");
        exp.append(stDt_year+"/"+year+" ");
        return exp.toString();
        
    }
}
