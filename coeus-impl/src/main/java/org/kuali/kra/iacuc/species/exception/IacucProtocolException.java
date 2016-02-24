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
