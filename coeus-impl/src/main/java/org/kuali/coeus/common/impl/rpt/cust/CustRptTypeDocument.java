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
package org.kuali.coeus.common.impl.rpt.cust;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CustRptTypeDocument extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    
    private Integer reportTypeCode; 
    
    private String documentName; 
    
    private CustReportType custReportType;
    
    public CustRptTypeDocument() { 

    }

    /**
     * Gets the reportTypeCode attribute. 
     * @return Returns the reportTypeCode.
     */
    public Integer getReportTypeCode() {
        return reportTypeCode;
    }

    /**
     * Sets the reportTypeCode attribute value.
     * @param reportTypeCode The reportTypeCode to set.
     */
    public void setReportTypeCode(Integer reportTypeCode) {
        this.reportTypeCode = reportTypeCode;
    }

    /**
     * Gets the documentName attribute. 
     * @return Returns the documentName.
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets the documentName attribute value.
     * @param documentName The documentName to set.
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
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
}
