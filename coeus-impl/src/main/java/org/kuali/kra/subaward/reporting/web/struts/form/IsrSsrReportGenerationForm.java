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
package org.kuali.kra.subaward.reporting.web.struts.form;

import org.kuali.rice.kns.web.struts.form.KualiForm;

@SuppressWarnings("deprecation")
public class IsrSsrReportGenerationForm extends KualiForm {


    private static final long serialVersionUID = 1L;
    private String reportName;
    private String awardNo;    
    private String startDate;
    private String endDate;
    private String reportType;   
    
    public IsrSsrReportGenerationForm() {
        initialize();
    }     

    public void initialize() {
        setReportType("Regular");        
    }
    
    public String getReportName() {
        return reportName;
    }
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    public String getAwardNo() {
        return awardNo;
    }
    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }    

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }  
}
