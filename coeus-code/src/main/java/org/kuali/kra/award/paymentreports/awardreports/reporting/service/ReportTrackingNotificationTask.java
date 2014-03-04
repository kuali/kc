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
