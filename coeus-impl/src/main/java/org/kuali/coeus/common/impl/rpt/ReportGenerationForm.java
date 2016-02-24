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
package org.kuali.coeus.common.impl.rpt;

import org.kuali.coeus.common.impl.rpt.cust.CustReportDetails;
import org.kuali.coeus.common.impl.rpt.cust.CustReportType;
import org.kuali.coeus.common.impl.rpt.cust.CustRptDefaultParms;
import org.kuali.coeus.common.impl.rpt.cust.CustRptTypeDocument;
import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.util.ArrayList;
import java.util.List;


public class ReportGenerationForm extends KualiForm {


    private static final long serialVersionUID = 1L;
    private CustReportDetails custReportDetails;
    private CustReportType custReportType;
    private CustRptDefaultParms custRptDefaultParms;
    private CustRptTypeDocument custRptTypeDocument;
    private List<BirtParameterBean> reportParameterList;
    private BirtParameterBean birtParameterBean;
    private String inputParameterText;
    private String reportFormat;
    private String reportId;
    private String reportName;
    
    public ReportGenerationForm() {
        initialize();
    }
    
    public void initialize() {
        custReportDetails = new CustReportDetails();
        custReportType = new CustReportType();
        custRptDefaultParms = new CustRptDefaultParms();
        custRptTypeDocument = new CustRptTypeDocument();
        reportParameterList = new ArrayList<>();
        reportParameterList.add(new BirtParameterBean());
        reportId = "";
        reportName = "";
    }

    public CustReportDetails getCustReportDetails() {
        return custReportDetails;
    }

    public void setCustReportDetails(CustReportDetails custReportDetails) {
        this.custReportDetails = custReportDetails;
    }

    public CustReportType getCustReportType() {
        return custReportType;
    }

    public void setCustReportType(CustReportType custReportType) {
        this.custReportType = custReportType;
    }

    public CustRptDefaultParms getCustRptDefaultParms() {
        return custRptDefaultParms;
    }

    public void setCustRptDefaultParms(CustRptDefaultParms custRptDefaultParms) {
        this.custRptDefaultParms = custRptDefaultParms;
    }

    public List<BirtParameterBean> getReportParameterList() {
        if (reportParameterList == null || reportParameterList.isEmpty() ) {
            reportParameterList.add(new BirtParameterBean());
        }
            return reportParameterList;
    }

    public void setReportParameterList(List<BirtParameterBean> reportParameterList) {
        this.reportParameterList = reportParameterList;
    }

    public void setBirtParameterBean(BirtParameterBean birtParameterBean) {
        this.birtParameterBean = birtParameterBean;
    }

    public BirtParameterBean getBirtParameterBean() {
        return birtParameterBean;
    }

    public void setInputParameterText(String inputParameterText) {
        this.inputParameterText = inputParameterText;
    }

    public String getInputParameterText() {
        return inputParameterText;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat.toLowerCase();
    }

    public String getReportFormat() {
        return reportFormat.toLowerCase();
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setCustRptTypeDocument(CustRptTypeDocument custRptTypeDocument) {
        this.custRptTypeDocument = custRptTypeDocument;
    }

    public CustRptTypeDocument getCustRptTypeDocument() {
        return custRptTypeDocument;
    }
}
