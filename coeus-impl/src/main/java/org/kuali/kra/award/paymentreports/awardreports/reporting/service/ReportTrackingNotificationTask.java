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

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ReportTrackingNotificationTask {

    private String reportClassCode;
    private String reportCode;
    private String frequencyCode;
    private String frequencyBaseCode;
    private String ospDistributionCode;
    
    public ReportTrackingNotificationTask() {
        
    }

    /**
     * 
     * Constructs a ReportTrackingNotificationTask.java using the reportClassCode as the single parameter to this task.
     * @param reportClassCode
     */
    public ReportTrackingNotificationTask(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }
    
    public Map<String, Object> getReportTrackingValueMap() {
        Map<String, Object> values = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(reportClassCode)) {
            values.put("reportClassCode", reportClassCode);
        }
        if (StringUtils.isNotBlank(reportCode)) {
            values.put("reportCode", reportCode);
        }
        if (StringUtils.isNotBlank(frequencyCode)) {
            values.put("frequencyCode", frequencyCode);
        }
        if (StringUtils.isNotBlank(frequencyBaseCode)) {
            values.put("frequencyBaseCode", frequencyBaseCode);
        }
        if (StringUtils.isNotBlank(ospDistributionCode)) {
            values.put("ospDistributionCode", ospDistributionCode);
        }
        return values;
    }
    
    public String getReportClassCode() {
        return reportClassCode;
    }
    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }
    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }
    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }
    public String getFrequencyCode() {
        return frequencyCode;
    }
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }
    public String getOspDistributionCode() {
        return ospDistributionCode;
    }
    public void setOspDistributionCode(String ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    
}
