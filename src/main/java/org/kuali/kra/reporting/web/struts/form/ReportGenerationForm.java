/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.reporting.web.struts.form;

import java.util.ArrayList;
import java.util.List;
import org.kuali.kra.reporting.bo.BirtParameterBean;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.bo.CustReportType;
import org.kuali.kra.reporting.bo.CustRptDefaultParms;
import org.kuali.kra.reporting.bo.CustRptTypeDocument;
import org.kuali.rice.kns.web.struts.form.KualiForm;


@SuppressWarnings("deprecation")
public class ReportGenerationForm extends KualiForm {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private CustReportDetails custReportDetails;
    private CustReportType custReportType;
    private CustRptDefaultParms custRptDefaultParms;
    private CustRptTypeDocument custRptTypeDocument;
    private ArrayList<BirtParameterBean> reportParameterList;
    private BirtParameterBean birtParameterBean;
    private String inputParameterText;
    private  String reportFormat;
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
        reportParameterList = new ArrayList<BirtParameterBean>();
        reportParameterList.add(new BirtParameterBean());
        reportId = new String();
        reportName = new String();
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


    public void setReportParameterList(ArrayList<BirtParameterBean> reportParameterList) {
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
