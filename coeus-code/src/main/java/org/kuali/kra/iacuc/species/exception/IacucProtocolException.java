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
package org.kuali.kra.iacuc.species.exception;

import org.kuali.kra.iacuc.IacucExceptionCategory;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.protocol.ProtocolAssociateBase;

public class IacucProtocolException extends ProtocolAssociateBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolExceptionId; 
    private Integer speciesCode; 
    private String protocolNumber; 
    private Integer sequenceNumber; 
    private Integer exceptionId; 
    private Integer exceptionCategoryCode; 
    private String exceptionDescription; 
    private Integer exceptionCount;
    
    private IacucExceptionCategory iacucExceptionCategory; 
    private IacucSpecies iacucSpecies; 
    
    public IacucProtocolException() { 

    } 
    
    public Integer getIacucProtocolExceptionId() {
        return iacucProtocolExceptionId;
    }

    public void setIacucProtocolExceptionId(Integer iacucProtocolExceptionId) {
        this.iacucProtocolExceptionId = iacucProtocolExceptionId;
    }

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getExceptionCategoryCode() {
        return exceptionCategoryCode;
    }

    public void setExceptionCategoryCode(Integer exceptionCategoryCode) {
        this.exceptionCategoryCode = exceptionCategoryCode;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public IacucExceptionCategory getIacucExceptionCategory() {
        return iacucExceptionCategory;
    }

    public void setIacucExceptionCategory(IacucExceptionCategory iacucExceptionCategory) {
        this.iacucExceptionCategory = iacucExceptionCategory;
    }

    public IacucSpecies getIacucSpecies() {
        return iacucSpecies;
    }

    public void setIacucSpecies(IacucSpecies iacucSpecies) {
        this.iacucSpecies = iacucSpecies;
    }

    @Override
    public void resetPersistenceState() {
        this.setIacucProtocolExceptionId(null);        
    }

    public Integer getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public String getSpeciesName() {
        if (iacucSpecies == null) {
            refreshReferenceObject("iacucSpecies");
        }
        return iacucSpecies.getSpeciesName();
    }

    public String getCategoryName() {
        if (iacucExceptionCategory == null) {
            refreshReferenceObject("iacucExceptionCategory");
        }
        return iacucExceptionCategory.getExceptionCategoryDesc();
    }

}
