/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.summary;

import java.io.Serializable;

public class AdditionalInfoSummary implements Serializable {

    private static final long serialVersionUID = 1740333929467337320L;
    
    private String fdaApplicationNumber;
    private String billable;
    private String referenceId1;
    private String referenceId2;
    private String description;
    
    public String getFdaApplicationNumber() {
        return fdaApplicationNumber;
    }
    
    public void setFdaApplicationNumber(String fdaApplicationNumber) {
        this.fdaApplicationNumber = fdaApplicationNumber;
    }
    
    public String getBillable() {
        return billable;
    }
    
    public void setBillable(boolean billable) {
        this.billable = billable ? "Yes" : "No";
    }
    
    public String getReferenceId1() {
        return referenceId1;
    }
    
    public void setReferenceId1(String referenceId1) {
        this.referenceId1 = referenceId1;
    }
    
    public String getReferenceId2() {
        return referenceId2;
    }
    
    public void setReferenceId2(String referenceId2) {
        this.referenceId2 = referenceId2;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
