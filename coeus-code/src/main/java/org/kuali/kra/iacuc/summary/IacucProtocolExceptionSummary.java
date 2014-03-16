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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;

import java.io.Serializable;

public class IacucProtocolExceptionSummary implements Serializable { 
    
    private static final long serialVersionUID = -3542316037590648543L;

    private Integer iacucProtocolExceptionId; 
    private String  speciesName; 
    private String  exceptionCategory; 
    private String  exceptionDescription; 
    private Integer exceptionCount;

    private boolean speciesNameChanged; 
    private boolean exceptionCategoryChanged; 
    private boolean exceptionDescriptionChanged; 
    private boolean exceptionCountChanged;

    public IacucProtocolExceptionSummary(IacucProtocolException exception) { 
        this.iacucProtocolExceptionId = exception.getExceptionId();
        this.speciesName = exception.getSpeciesName();
        this.exceptionCategory = exception.getCategoryName();
        this.exceptionDescription = exception.getExceptionDescription();
        this.exceptionCount = exception.getExceptionCount();
    } 
    
    public Integer getIacucProtocolExceptionId() {
        return iacucProtocolExceptionId;
    }

    public void setIacucProtocolExceptionId(Integer iacucProtocolExceptionId) {
        this.iacucProtocolExceptionId = iacucProtocolExceptionId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getExceptionCategory() {
        return exceptionCategory;
    }

    public void setExceptionCategory(String exceptionCategory) {
        this.exceptionCategory = exceptionCategory;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public Integer getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public boolean isSpeciesNameChanged() {
        return speciesNameChanged;
    }

    public void setSpeciesNameChanged(boolean speciesNameChanged) {
        this.speciesNameChanged = speciesNameChanged;
    }

    public boolean isExceptionCategoryChanged() {
        return exceptionCategoryChanged;
    }

    public void setExceptionCategoryChanged(boolean exceptionCategoryChanged) {
        this.exceptionCategoryChanged = exceptionCategoryChanged;
    }

    public boolean isExceptionDescriptionChanged() {
        return exceptionDescriptionChanged;
    }

    public void setExceptionDescriptionChanged(boolean exceptionDescriptionChanged) {
        this.exceptionDescriptionChanged = exceptionDescriptionChanged;
    }

    public boolean isExceptionCountChanged() {
        return exceptionCountChanged;
    }

    public void setExceptionCountChanged(boolean exceptionCountChanged) {
        this.exceptionCountChanged = exceptionCountChanged;
    }
    
    public void compare(IacucProtocolSummary other) {
        IacucProtocolExceptionSummary otherSummary = (other == null) ? null : other.findExceptionSummary(iacucProtocolExceptionId);
        if (otherSummary == null) {
            speciesNameChanged = true; 
            exceptionCategoryChanged = true; 
            exceptionDescriptionChanged = true; 
            exceptionCountChanged = true;
        } else {
            speciesNameChanged = !StringUtils.equals(speciesName, otherSummary.speciesName); 
            exceptionCategoryChanged = !StringUtils.equals(exceptionCategory, otherSummary.exceptionCategory); 
            exceptionDescriptionChanged = !StringUtils.equals(exceptionDescription, otherSummary.exceptionDescription); 
            exceptionCountChanged = !ObjectUtils.equals(exceptionCount, otherSummary.exceptionCount);
        }
    }

}

