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
package org.kuali.kra.iacuc.species;

import org.kuali.kra.protocol.ProtocolAssociate;

public class IacucProtocolSpecies extends ProtocolAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolSpeciesId; 
    private Integer speciesId; 
    private Integer speciesCode; 
    private String speciesGroup; 
    private boolean usdaCovered; 
    private String strain; 
    private Integer speciesCount; 
    private Integer painCategoryCode; 
    private Integer speciesCountCode; 
    private boolean exceptionsPresent;
    private String procedureSummary;
    
    //private IacucProtocolExceptions iacucProtocolExceptions; 
    
    public IacucProtocolSpecies() { 

    } 
    
    public Integer getIacucProtocolSpeciesId() {
        return iacucProtocolSpeciesId;
    }

    public void setIacucProtocolSpeciesId(Integer iacucProtocolSpeciesId) {
        this.iacucProtocolSpeciesId = iacucProtocolSpeciesId;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getSpeciesGroup() {
        return speciesGroup;
    }

    public void setSpeciesGroup(String speciesGroup) {
        this.speciesGroup = speciesGroup;
    }

    public boolean getUsdaCovered() {
        return usdaCovered;
    }

    public void setUsdaCovered(boolean usdaCovered) {
        this.usdaCovered = usdaCovered;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public Integer getSpeciesCount() {
        return speciesCount;
    }

    public void setSpeciesCount(Integer speciesCount) {
        this.speciesCount = speciesCount;
    }

    public Integer getPainCategoryCode() {
        return painCategoryCode;
    }

    public void setPainCategoryCode(Integer painCategoryCode) {
        this.painCategoryCode = painCategoryCode;
    }

    public Integer getSpeciesCountCode() {
        return speciesCountCode;
    }

    public void setSpeciesCountCode(Integer speciesCountCode) {
        this.speciesCountCode = speciesCountCode;
    }

    public boolean getExceptionsPresent() {
        return exceptionsPresent;
    }

    public void setExceptionsPresent(boolean exceptionsPresent) {
        this.exceptionsPresent = exceptionsPresent;
    }

    @Override
    public void resetPersistenceState() {
        // TODO Auto-generated method stub
        
    }

    public String getProcedureSummary() {
        return procedureSummary;
    }

    public void setProcedureSummary(String procedureSummary) {
        this.procedureSummary = procedureSummary;
    }

    /*
    public IacucProtocolExceptions getIacucProtocolExceptions() {
        return iacucProtocolExceptions;
    }

    public void setIacucProtocolExceptions(IacucProtocolExceptions iacucProtocolExceptions) {
        this.iacucProtocolExceptions = iacucProtocolExceptions;
    }
    */

    
}