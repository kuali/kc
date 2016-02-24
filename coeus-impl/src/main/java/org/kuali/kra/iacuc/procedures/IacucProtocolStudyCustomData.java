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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucProcedureCategoryCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolStudyCustomData extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyCustomDataId; 
    private Integer iacucProtocolStudyGroupId; 
    private Long procedureCustomAttributeId;
    private String value;
    
    private IacucProcedureCategoryCustomData iacucProcedureCategoryCustomData;
    private IacucProtocolStudyGroup iacucProtocolStudyGroup;
    
    public IacucProtocolStudyCustomData() { 

    } 
    
    public Integer getIacucProtocolStudyCustomDataId() {
        return iacucProtocolStudyCustomDataId;
    }

    public void setIacucProtocolStudyCustomDataId(Integer iacucProtocolStudyCustomDataId) {
        this.iacucProtocolStudyCustomDataId = iacucProtocolStudyCustomDataId;
    }

    public Integer getIacucProtocolStudyGroupId() {
        return iacucProtocolStudyGroupId;
    }

    public void setIacucProtocolStudyGroupId(Integer iacucProtocolStudyGroupId) {
        this.iacucProtocolStudyGroupId = iacucProtocolStudyGroupId;
    }

    public Long getProcedureCustomAttributeId() {
        return procedureCustomAttributeId;
    }

    public void setProcedureCustomAttributeId(Long procedureCustomAttributeId) {
        this.procedureCustomAttributeId = procedureCustomAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void resetPersistenceState() {
        setIacucProtocolStudyCustomDataId(null);
    }

    public IacucProcedureCategoryCustomData getIacucProcedureCategoryCustomData() {
        if(ObjectUtils.isNull(iacucProcedureCategoryCustomData)) {
            this.refreshReferenceObject("iacucProcedureCategoryCustomData");
        }
        return iacucProcedureCategoryCustomData;
    }

    public void setIacucProcedureCategoryCustomData(IacucProcedureCategoryCustomData iacucProcedureCategoryCustomData) {
        this.iacucProcedureCategoryCustomData = iacucProcedureCategoryCustomData;
    }

    public boolean isLargeText() {
        return getIacucProcedureCategoryCustomData().getDataLength() > Constants.IACUC_PROCEDURE_CUSTOM_DATA_SMALL_STRING_MAX_LENGTH;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        IacucProtocolStudyCustomData other = (IacucProtocolStudyCustomData) obj;
        if (this.iacucProtocolStudyCustomDataId == null) {
            if (other.iacucProtocolStudyCustomDataId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyCustomDataId.equals(other.iacucProtocolStudyCustomDataId)) {
            return false;
        }
        if (this.iacucProtocolStudyGroupId == null) {
            if (other.iacucProtocolStudyGroupId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupId.equals(other.iacucProtocolStudyGroupId)) {
            return false;
        }
        if (this.procedureCustomAttributeId == null) {
            if (other.procedureCustomAttributeId != null) {
                return false;
            }
        } else if (!this.procedureCustomAttributeId.equals(other.procedureCustomAttributeId)) {
            return false;
        }
        return true;
    }

    public IacucProtocolStudyGroup getIacucProtocolStudyGroup() {
        return iacucProtocolStudyGroup;
    }

    public void setIacucProtocolStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        this.iacucProtocolStudyGroup = iacucProtocolStudyGroup;
    }
}
