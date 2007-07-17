/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

public class NarrativeType extends KraPersistableBusinessObjectBase {
    
    private String narrativeTypeCode;
    private String description;
    private String systemGenerated;
    private String allowMultiple;
    private String narrativeTypeGroup;
    
    public String getAllowMultiple() {
        return allowMultiple;
    }
    public void setAllowMultiple(String allowMultiple) {
        this.allowMultiple = allowMultiple;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getNarrativeTypeCode() {
        return narrativeTypeCode;
    }
    public void setNarrativeTypeCode(String narrativeTypeCode) {
        this.narrativeTypeCode = narrativeTypeCode;
    }
    public String getNarrativeTypeGroup() {
        return narrativeTypeGroup;
    }
    public void setNarrativeTypeGroup(String narrativeTypeGroup) {
        this.narrativeTypeGroup = narrativeTypeGroup;
    }
    public String getSystemGenerated() {
        return systemGenerated;
    }
    public void setSystemGenerated(String systemGenerated) {
        this.systemGenerated = systemGenerated;
    }
    
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("narrativeTypeCode", this.getNarrativeTypeCode());
        propMap.put("description", this.getDescription());
        propMap.put("systemGenerated", this.getSystemGenerated());
        propMap.put("allowMultiple", this.getAllowMultiple());
        propMap.put("narrativeTypeGroup", this.getNarrativeTypeGroup());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
}
