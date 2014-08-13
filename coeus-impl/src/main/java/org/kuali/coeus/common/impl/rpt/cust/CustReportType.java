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


public class CustReportType extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer reportTypeCode; 
    private String reportTypeDesc; 
    
    public CustReportType() { 

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
     * Gets the reportTypeDesc attribute. 
     * @return Returns the reportTypeDesc.
     */
    public String getReportTypeDesc() {
        return reportTypeDesc;
    }

    /**
     * Sets the reportTypeDesc attribute value.
     * @param reportTypeDesc The reportTypeDesc to set.
     */
    public void setReportTypeDesc(String reportTypeDesc) {
        this.reportTypeDesc = reportTypeDesc;
    }
}