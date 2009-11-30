/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidProtoActionCoresp extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer validProtoActionCorespId; 
    private Integer protocolActionTypeCode; 
    private Integer protoCorrespTypeCode; 
    private boolean finalFlag; 
    
    public ValidProtoActionCoresp() { 

    } 
    
    public Integer getValidProtoActionCorespId() {
        return validProtoActionCorespId;
    }

    public void setValidProtoActionCorespId(Integer validProtoActionCorespId) {
        this.validProtoActionCorespId = validProtoActionCorespId;
    }

    public Integer getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(Integer protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public Integer getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(Integer protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public boolean getFinalFlag() {
        return finalFlag;
    }

    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("validProtoActionCorespId", this.getValidProtoActionCorespId());
        hashMap.put("protocolActionTypeCode", this.getProtocolActionTypeCode());
        hashMap.put("protoCorrespTypeCode", this.getProtoCorrespTypeCode());
        hashMap.put("finalFlag", this.getFinalFlag());
        return hashMap;
    }
    
}