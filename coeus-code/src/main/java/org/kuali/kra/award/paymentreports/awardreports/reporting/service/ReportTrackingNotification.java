/*
 * Copyright 2005-2014 The Kuali Foundation
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
