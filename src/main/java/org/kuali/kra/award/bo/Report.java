/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the Report business object and is mapped
 * with REPORT table.
 */
public class Report extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4555054434451627778L;
    private String reportCode; 
    private String description; 
    private boolean finalReportFlag; 
    
    /**
     * Constructs a Report.java
     */
    public Report() { 

    } 
    
    /**
     * 
     * @return
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * 
     * @param reportCode
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     */
    public boolean getFinalReportFlag() {
        return finalReportFlag;
    }

    /**
     * 
     * @param finalReportFlag
     */
    public void setFinalReportFlag(boolean finalReportFlag) {
        this.finalReportFlag = finalReportFlag;
    }

    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("reportCode", getReportCode());
        hashMap.put("description", getDescription());
        hashMap.put("finalReportFlag", getFinalReportFlag());
        return hashMap;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reportCode == null) ? 0 : reportCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Report other = (Report) obj;
        if (reportCode == null) {
            if (other.reportCode != null)
                return false;
        }
        else if (!reportCode.equals(other.reportCode))
            return false;
        return true;
    }
    
    
    
}