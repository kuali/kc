/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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


@SuppressWarnings("deprecation")
public class ReportGenerationForm extends KualiForm {


    private static final long serialVersionUID = 1L;
    private CustReportDetails custReportDetails;
    private CustReportType custReportType;
    private CustRptDefaultParms custRptDefaultParms;
    private CustRptTypeDocument custRptTypeDocument;
    private ArrayList<BirtParameterBean> reportParameterList;
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
        reportParameterList = new ArrayList<BirtParameterBean>();
        reportParameterList.add(new BirtParameterBean());
        reportId = new String();
        reportName = new String();
    }
    
    /**
     * Gets the custReportDetails attribute. 
     * @return Returns the custReportDetails.
     */
    public CustReportDetails getCustReportDetails() {
        return custReportDetails;
    }
    
    /**
     * Sets the custReportDetails attribute value.
     * @param custReportDetails The custReportDetails to set.
     */
    public void setCustReportDetails(CustReportDetails custReportDetails) {
        this.custReportDetails = custReportDetails;
    }
    
    /**
     * Gets the custReportType attribute. 
     * @return Returns the custReportType.
     */
    public CustReportType getCustReportType() {
        return custReportType;
    }

    /**
     * Sets the custReportType attribute value.
     * @param custReportType The custReportType to set.
     */
    public void setCustReportType(CustReportType custReportType) {
        this.custReportType = custReportType;
    }
    
    /**
     * Gets the custRptDefaultParms attribute. 
     * @return Returns the custRptDefaultParms.
     */
    public CustRptDefaultParms getCustRptDefaultParms() {
        return custRptDefaultParms;
    }
    
    /**
     * Sets the custRptDefaultParms attribute value.
     * @param custRptDefaultParms The custRptDefaultParms to set.
     */
    public void setCustRptDefaultParms(CustRptDefaultParms custRptDefaultParms) {
        this.custRptDefaultParms = custRptDefaultParms;
    }
    
    /**
     * Gets the reportParameterList attribute. 
     * @return Returns the reportParameterList.
     */
    public List<BirtParameterBean> getReportParameterList() {
        if (reportParameterList == null || reportParameterList.isEmpty() ) {
            reportParameterList.add(new BirtParameterBean());
        }
            return reportParameterList;
    }
    
    /**
     * Sets the reportParameterList attribute value.
     * @param reportParameterList The reportParameterList to set.
     */
    public void setReportParameterList(ArrayList<BirtParameterBean> reportParameterList) {
        this.reportParameterList = reportParameterList;
    }
    
    /**
     * Sets the birtParameterBean attribute value.
     * @param birtParameterBean The birtParameterBean to set.
     */
    public void setBirtParameterBean(BirtParameterBean birtParameterBean) {
        this.birtParameterBean = birtParameterBean;
    }
    
    /**
     * Gets the birtParameterBean attribute. 
     * @return Returns the birtParameterBean.
     */
    public BirtParameterBean getBirtParameterBean() {
        return birtParameterBean;
    }
    
    /**
     * Sets the inputParameterText attribute value.
     * @param inputParameterText The inputParameterText to set.
     */
    public void setInputParameterText(String inputParameterText) {
        this.inputParameterText = inputParameterText;
    }
    
    /**
     * Gets the inputParameterText attribute. 
     * @return Returns the inputParameterText.
     */
    public String getInputParameterText() {
        return inputParameterText;
    }
    
    /**
     * Sets the reportFormat attribute value.
     * @param reportFormat The reportFormat to set.
     */
    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat.toLowerCase();
    }
    
    /**
     * Gets the reportFormat attribute. 
     * @return Returns the reportFormat.
     */
    public String getReportFormat() {
        return reportFormat.toLowerCase();
    }
    
    /**
     * Sets the reportId attribute value.
     * @param reportId The reportId to set.
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    /**
     * Gets the reportId attribute. 
     * @return Returns the reportId.
     */
    public String getReportId() {
        return reportId;
    }
    
    /**
     * Sets the reportName attribute value.
     * @param reportName The reportName to set.
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    /**
     * Gets the reportName attribute. 
     * @return Returns the reportName.
     */
    public String getReportName() {
        return reportName;
    }
    
    /**
     * Sets the custRptTypeDocument attribute value.
     * @param custRptTypeDocument The custRptTypeDocument to set.
     */
    public void setCustRptTypeDocument(CustRptTypeDocument custRptTypeDocument) {
        this.custRptTypeDocument = custRptTypeDocument;
    }
    
    /**
     * Gets the custRptTypeDocument attribute. 
     * @return Returns the custRptTypeDocument.
     */
    public CustRptTypeDocument getCustRptTypeDocument() {
        return custRptTypeDocument;
    }
}
