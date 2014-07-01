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
package org.kuali.coeus.common.impl.rpt.cust;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CustRptDefaultParms extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private String parameterName; 
    
    private Integer reportTypeCode; 
    
    private String className; 
    
    private String javaStatements; 
    
    private CustReportType custReportType;
    
    private boolean unitForAuthCheck; 
    
    public CustRptDefaultParms() { 

    }

    /**
     * Gets the parameterName attribute. 
     * @return Returns the parameterName.
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Sets the parameterName attribute value.
     * @param parameterName The parameterName to set.
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
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
     * Gets the className attribute. 
     * @return Returns the className.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the className attribute value.
     * @param className The className to set.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets the javaStatements attribute. 
     * @return Returns the javaStatements.
     */
    public String getJavaStatements() {
        return javaStatements;
    }

    /**
     * Sets the javaStatements attribute value.
     * @param javaStatements The javaStatements to set.
     */
    public void setJavaStatements(String javaStatements) {
        this.javaStatements = javaStatements;
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
     * Gets the unitForAuthCheck attribute. 
     * @return Returns the unitForAuthCheck.
     */
    public boolean isUnitForAuthCheck() {
        return unitForAuthCheck;
    }

    /**
     * Sets the unitForAuthCheck attribute value.
     * @param unitForAuthCheck The unitForAuthCheck to set.
     */
    public void setUnitForAuthCheck(boolean unitForAuthCheck) {
        this.unitForAuthCheck = unitForAuthCheck;
    }
}