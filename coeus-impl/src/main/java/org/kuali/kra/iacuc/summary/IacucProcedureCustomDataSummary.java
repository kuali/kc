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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyCustomData;

import java.io.Serializable;

public class IacucProcedureCustomDataSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customDataTag; 
    private String customDataVal; 
    private boolean customDataTagChanged; 
    private boolean customDataValChanged; 

    public IacucProcedureCustomDataSummary(IacucProtocolStudyCustomData customData) {
        id = customData.getProcedureCustomAttributeId();
        customDataTag = customData.getIacucProcedureCategoryCustomData().getLabel();
        customDataVal = customData.getValue();
        customDataTagChanged = false;
        customDataValChanged = false;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCustomDataTag() {
        return customDataTag;
    }

    public void setCustomDataTag(String customDataTag) {
        this.customDataTag = customDataTag;
    }

    public String getCustomDataVal() {
        return customDataVal;
    }

    public void setCustomDataVal(String customDataVal) {
        this.customDataVal = customDataVal;
    }

    public boolean isCustomDataTagChanged() {
        return customDataTagChanged;
    }

    public void setCustomDataTagChanged(boolean customDataTagChanged) {
        this.customDataTagChanged = customDataTagChanged;
    }

    public boolean isCustomDataValChanged() {
        return customDataValChanged;
    }

    public void setCustomDataValChanged(boolean customDataValChanged) {
        this.customDataValChanged = customDataValChanged;
    } 

    public void compare(IacucProcedureSummary other) {
        IacucProcedureCustomDataSummary otherSummary = (other == null) ? null : other.findProcedureCustomDataSummary(id);
        if (otherSummary == null) {
            customDataTagChanged = false;  // doesn't do any good to highlight the label
            customDataValChanged = true;
        } else {
            customDataTagChanged = false;
            customDataValChanged = !StringUtils.equals(customDataVal, otherSummary.customDataVal);
        }
    }

}
