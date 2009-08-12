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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalLocationType extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = 4387258582030826617L;
    
    private int locationTypeCode;
    private String locationTypeDesc;

    public void setLocationTypeCode(int locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public int getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeDesc(String locationTypeDesc) {
        this.locationTypeDesc = locationTypeDesc;
    }

    public String getLocationTypeDesc() {
        return locationTypeDesc;
    }

    @Override
    protected LinkedHashMap<String, ?> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("locationTypeCode", this.getLocationTypeCode());
        hashMap.put("locationTypeDesc", this.getLocationTypeDesc());
        return hashMap;
    }

}
