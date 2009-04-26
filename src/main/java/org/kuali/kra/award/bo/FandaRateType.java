/*
 * Copyright 2006-2008 The Kuali Foundation
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

package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the FandaRateType Business Object.
 */
public class FandaRateType extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6901016199815302736L;
    private Integer fandaRateTypeCode; 
    private String description; 
    
    /**
     * 
     * Constructs a FandaRateType.java.
     */
    public FandaRateType() { 

    } 
    
    /**
     *
     * @return
     */
    public Integer getFandaRateTypeCode() {
        return fandaRateTypeCode;
    }

    /**
     *
     * @param fandaRateTypeCode
     */
    public void setFandaRateTypeCode(Integer fandaRateTypeCode) {
        this.fandaRateTypeCode = fandaRateTypeCode;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("fandaRateTypeCode", getFandaRateTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }
    
}
