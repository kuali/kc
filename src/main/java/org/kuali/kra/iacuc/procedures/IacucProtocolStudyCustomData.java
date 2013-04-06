/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.iacuc.IacucProcedureCategoryCustomData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolStudyCustomData extends ProtocolAssociateBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyCustomDataId; 
    private Integer iacucProtocolStudyGroupId; 
    private Integer procedureCustomAttributeId;
    private String value;
    
    private IacucProcedureCategoryCustomData iacucProcedureCategoryCustomData;
    
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

    public Integer getProcedureCustomAttributeId() {
        return procedureCustomAttributeId;
    }

    public void setProcedureCustomAttributeId(Integer procedureCustomAttributeId) {
        this.procedureCustomAttributeId = procedureCustomAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
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
}
