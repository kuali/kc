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
    private static final String MM_DD_YYYY = "MM/dd/yyyy";
    private static final String REPORT = "report";

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat(MM_DD_YYYY);
        if (report.getReport() == null) {
            report.refreshReferenceObject(REPORT);
        }
        result.put("{REPORT_TYPE}", report.getReport().getDescription());
        result.put("{REPORT_DUE_DATE}", dateFormatter.format(report.getDueDate()));
        result.put(START_REPEAT_SECTION, StringUtils.EMPTY);
        result.put(END_REPEAT_SECTION, StringUtils.EMPTY);
        return result;
    }
    
    @Override
    public String render(String text) {
        int startIndex = StringUtils.indexOf(text, START_REPEAT_SECTION);
        int endIndex = StringUtils.indexOf(text, END_REPEAT_SECTION) + END_REPEAT_SECTION.length();
        String startStr = text.substring(0, startIndex);
        String repeatedStr = text.substring(startIndex, endIndex);
        String endStr = text.substring(endIndex);
        StringBuilder buffer = new StringBuilder();
        buffer.append(startStr);
        reports.forEach(report -> buffer.append(this.render(repeatedStr, getReportReplacementParameters(report))));

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
