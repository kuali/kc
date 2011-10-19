/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CoiStatus extends KraPersistableBusinessObjectBase { 
    

    private static final long serialVersionUID = 1957357011310589190L;
    private String coiStatusCode; 
    private String description; 
    
    
    public CoiStatus() { 

    } 
    
    public String getCoiStatusCode() {
        return coiStatusCode;
    }

    public void setCoiStatusCode(String coiStatusCode) {
        this.coiStatusCode = coiStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("coiStatusCode", this.getCoiStatusCode());
        hashMap.put("description", this.getDescription());
        return hashMap;
    }
    
}