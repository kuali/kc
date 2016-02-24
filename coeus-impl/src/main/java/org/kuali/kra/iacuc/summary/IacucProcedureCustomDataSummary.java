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
