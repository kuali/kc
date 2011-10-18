/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Report Status BO that represents the current status of report
 * tracking information (pending, received, etc).
 */
public class ReportStatus extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3119807996482789387L;
    private String reportStatusCode;
    private String description;
    private boolean active;


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("statusCode", getReportStatusCode());
        result.put("description", getDescription());
        return result;
    }


    public String getReportStatusCode() {
        return reportStatusCode;
    }


    public void setReportStatusCode(String reportStatusCode) {
        this.reportStatusCode = reportStatusCode;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }    
    
}
