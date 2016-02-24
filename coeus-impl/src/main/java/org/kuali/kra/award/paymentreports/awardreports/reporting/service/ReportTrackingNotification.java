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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.util.ArrayList;
import java.util.List;

public class ReportTrackingNotification {

    private String name;
    private String actionCode;
    private boolean overdue;
    private Integer days;
    private Integer scope;
    
    private List<ReportTrackingNotificationTask> tasks;
    
    public ReportTrackingNotification() {
        tasks = new ArrayList<ReportTrackingNotificationTask>();
        scope = new Integer(30);
    }
    
    
    public ReportTrackingNotification(String name, String actionCode, boolean overdue, Integer days, Integer scope, String reportClassCode) {
        this();
        this.name = name;
        this.actionCode = actionCode;
        this.overdue = overdue;
        this.days = days;
        this.scope = scope;
        tasks.add(new ReportTrackingNotificationTask(reportClassCode));
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public List<ReportTrackingNotificationTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ReportTrackingNotificationTask> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }
    
}
