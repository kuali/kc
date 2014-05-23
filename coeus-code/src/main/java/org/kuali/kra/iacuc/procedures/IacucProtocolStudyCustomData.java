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
