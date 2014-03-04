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
package org.kuali.kra.award.notification;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class AwardReportTrackingNotificationRenderer extends AwardNotificationRenderer {

    private static final long serialVersionUID = -2035058699415467934L;
    private static final String START_REPEAT_SECTION = "{BEGIN_REPEAT_SECTION}";
    private static final String END_REPEAT_SECTION = "{END_REPEAT_SECTION}";
    
    private List<ReportTracking> reports;
    
    public AwardReportTrackingNotificationRenderer() {
        super();
    }
    
    public AwardReportTrackingNotificationRenderer(List<ReportTracking> reports) {
        super();
        this.reports = reports;
    }
    
    protected Map<String, String> getReportReplacementParameters(ReportTracking report) {
        Map<String, String> result = getAwardReplacementParameters(report.getAward());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        if (report.getReport() == null) {
            report.refreshReferenceObject("report");
        }
        result.put("{REPORT_TYPE}", report.getReport().getDescription());
        result.put("{REPORT_DUE_DATE}", dateFormatter.format(report.getDueDate()));
        result.put(START_REPEAT_SECTION, "");
        result.put(END_REPEAT_SECTION, "");
        return result;
    }
    
    @Override
    public String render(String text) {
        int startIndex = StringUtils.indexOf(text, START_REPEAT_SECTION);
        int endIndex = StringUtils.indexOf(text, END_REPEAT_SECTION) + END_REPEAT_SECTION.length();
        String startStr = text.substring(0, startIndex);
        String repeatedStr = text.substring(startIndex, endIndex);
        String endStr = text.substring(endIndex);
        StringBuffer buffer = new StringBuffer();
        buffer.append(startStr);
        for (ReportTracking report : reports) {
            buffer.append(this.render(repeatedStr, getReportReplacementParameters(report)));
        }
        buffer.append(endStr);
        return buffer.toString();
    }

    public List<ReportTracking> getReports() {
        return reports;
    }

    public void setReports(List<ReportTracking> reports) {
        this.reports = reports;
    }
}
