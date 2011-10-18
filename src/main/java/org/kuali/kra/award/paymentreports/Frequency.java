/*
 * Copyright 2005-2010 The Kuali Foundation
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

package org.kuali.kra.award.paymentreports;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the Frequency Business Object.
 */
public class Frequency extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3635003841342435180L;
    private String frequencyCode; 
    private String description; 
    private Integer numberOfDays; 
    private Integer numberOfMonths; 
    private Boolean repeatFlag; 
    private Integer advanceNumberOfDays; 
    private Integer advanceNumberOfMonths; 
    private boolean active;
    
    
    public Frequency() { 

    } 
    
    public String getFrequencyCode() {
        return frequencyCode;
    }

    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Boolean getRepeatFlag() {
        return repeatFlag;
    }

    public void setRepeatFlag(Boolean repeatFlag) {
        this.repeatFlag = repeatFlag;
    }

    public Integer getAdvanceNumberOfDays() {
        return advanceNumberOfDays;
    }

    public void setAdvanceNumberOfDays(Integer advanceNumberOfDays) {
        this.advanceNumberOfDays = advanceNumberOfDays;
    }

    public Integer getAdvanceNumberOfMonths() {
        return advanceNumberOfMonths;
    }

    public void setAdvanceNumberOfMonths(Integer advanceNumberOfMonths) {
        this.advanceNumberOfMonths = advanceNumberOfMonths;
    }    

    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("frequencyCode", getFrequencyCode());
        hashMap.put("description", getDescription());
        hashMap.put("numberOfDays", getNumberOfDays());
        hashMap.put("numberOfMonths", getNumberOfMonths());
        hashMap.put("repeatFlag", getRepeatFlag());
//        hashMap.put("proposalDueFlag", getProposalDueFlag());
//        hashMap.put("invoiceFlag", getInvoiceFlag());
        hashMap.put("advanceNumberOfDays", getAdvanceNumberOfDays());
        hashMap.put("advanceNumberOfMonths", getAdvanceNumberOfMonths());
        return hashMap;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }   
        if (obj == null){
            return false;
        }   
        if (!(obj instanceof Frequency)){
            return false;
        }   
        return equals((Frequency) obj);
    }

    /**
     * 
     * Convenience method for equality of Frequency
     * @param frequency
     * @return
     */
    public boolean equals(Frequency frequency){
        if (frequencyCode == null) {
            if (frequency.frequencyCode != null){
                return false;
            }   
        }else if (!frequencyCode.equals(frequency.frequencyCode)){
            return false;
        }   
        return true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
        
}
