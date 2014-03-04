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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AdditionalInfoSummary implements Serializable {

    private static final long serialVersionUID = 1740333929467337320L;
    
    private String fdaApplicationNumber;
    private String referenceId1;
    private String referenceId2;
    private String description;
    
    private boolean fdaApplicationNumberChanged;
    private boolean referenceId1Changed;
    private boolean referenceId2Changed;
    private boolean descriptionChanged;
    
    public String getFdaApplicationNumber() {
        return fdaApplicationNumber;
    }
    
    public void setFdaApplicationNumber(String fdaApplicationNumber) {
        this.fdaApplicationNumber = fdaApplicationNumber;
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

    public void compare(AdditionalInfoSummary other) {
        fdaApplicationNumberChanged = !StringUtils.equals(fdaApplicationNumber, other.fdaApplicationNumber);
        referenceId1Changed = !StringUtils.equals(referenceId1, other.referenceId1);
        referenceId2Changed = !StringUtils.equals(referenceId2, other.referenceId2);
        descriptionChanged = !StringUtils.equals(description, other.description);
    }

    public boolean isFdaApplicationNumberChanged() {
        return fdaApplicationNumberChanged;
    }

    public boolean isReferenceId1Changed() {
        return referenceId1Changed;
    }

    public boolean isReferenceId2Changed() {
        return referenceId2Changed;
    }

    public boolean isDescriptionChanged() {
        return descriptionChanged;
    }
}
