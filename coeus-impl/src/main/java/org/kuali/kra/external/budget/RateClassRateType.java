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
package org.kuali.kra.external.budget;

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
