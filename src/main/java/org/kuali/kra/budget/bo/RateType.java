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
package org.kuali.kra.budget.bo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class RateType extends KraPersistableBusinessObjectBase implements Comparable {
    private String rateClassCode;
    private String rateTypeCode;
    private String description;
    private RateClass rateClass;
    private static Map<String, String> rateClassPrefixes = new HashMap<String, String>();


    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    public String getRateTypeCode() {
        return rateTypeCode;
    }

    public void setRateTypeCode(String rateTypeCode) {
        this.rateTypeCode = rateTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("rateClassCode", getRateClassCode());
        hashMap.put("rateTypeCode", getRateTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }

    /**
     * 
     * This is helper method to get prefix for total page display.
     * @return
     */
    public String getRateClassPrefix() {
        this.refreshReferenceObject("rateClass");
        RateClassType rateClassType = getRateClass().getRateClassTypeT();
        return rateClassType.getDescription();
    }

    public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }
    
    /**
     * This is for totals page to sort it by rateclasstypecode
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return compareTo((RateType) o);
    }
    
    public int compareTo(RateType rateType) {
        rateType.refreshReferenceObject("rateClass");
        this.refreshReferenceObject("rateClass");

        return this.rateClass.getRateClassCode().compareTo(rateType.rateClass.getRateClassCode());
    }

}
