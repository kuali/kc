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
package org.kuali.kra.budget.external.budget;

public class RateClassRateType {
    String rateClass;
    String rateType;

    public RateClassRateType(String rateClass, String rateType) {
        this.rateClass = rateClass;
        this.rateType = rateType;
    }
    
    public String getRateClass() {
        return rateClass;
    }
    public void setRateClass(String rateClass) {
        this.rateClass = rateClass;
    }
    
    public String getRateType() {
        return rateType;
    }
    
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }
    
    @Override
    public int hashCode() {
        final int multiplier = 23;
        int code = 133;
        code = multiplier * code + ((rateType == null) ? 0 : rateType.hashCode());
        code = multiplier * code + ((rateClass == null) ? 0 : rateClass.hashCode());
       
        return code;
    }
    
    @Override
    public boolean equals(Object o) {
        RateClassRateType current = (RateClassRateType) o;
        if (this == current) return true;
        if (current == null) return false;
        if (this.getClass() != current.getClass()) return false;
        if (current.rateClass.equals(rateClass) && current.rateType.equals(rateType)) {
            return true;
        } 
        return false;
    }
    
    
}
