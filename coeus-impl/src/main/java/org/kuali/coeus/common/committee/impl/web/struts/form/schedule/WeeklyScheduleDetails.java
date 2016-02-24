/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * This class holds weekly recurrence UI data.
 */
public class WeeklyScheduleDetails extends ScheduleDetails {
    
    private Integer week;
    
    private List<String> daysOfWeek;    
    
    public WeeklyScheduleDetails() {
        super();
        this.setWeek(1);
        this.daysOfWeek = new ArrayList<String>(2);
        this.daysOfWeek.add(DayOfWeek.Monday.name());
        this.daysOfWeek.add("Hidden");
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getWeek() {
        return week;
    }

    public void setDaysOfWeek(List<String> daysOfWeek) {
        this.daysOfWeek = (null != daysOfWeek? convertToWeekDays(daysOfWeek) : null);
    }

    public List<String> getDaysOfWeek() {
        return daysOfWeek;
    }
    
    private List<String> convertToWeekDays(List<String> daysOfWeek) {     
        if(daysOfWeek.size() == 1 && daysOfWeek.get(0).equalsIgnoreCase("Hidden"))
            return null;
        List<String> tmp = new ArrayList<String>(daysOfWeek.size() - 1);
        int i = 0;
        if(CollectionUtils.isNotEmpty(daysOfWeek)) {
            for(String dayOfWeek : daysOfWeek) {
                if(dayOfWeek.equalsIgnoreCase("Hidden"))
                    continue;
                tmp.add(dayOfWeek);
            }
        }
        return tmp;
    }
}
