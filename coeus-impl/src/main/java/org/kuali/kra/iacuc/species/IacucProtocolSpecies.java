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
package org.kuali.kra.iacuc.species;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.IacucSpeciesCountType;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolSpecies extends ProtocolAssociateBase { 
    
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
    
    private IacucSpecies iacucSpecies;
    private IacucSpeciesCountType iacucSpeciesCountType;
    private IacucPainCategory iacucPainCategory;
    
    private List<IacucProtocolException> iacucProtocolExceptions; 
    
    private transient String groupAndSpecies;
    private transient Integer oldProtocolSpeciesId;
    
    public IacucProtocolSpecies() { 
        setIacucProtocolExceptions(new ArrayList<IacucProtocolException>());
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
        setOldProtocolSpeciesId(getIacucProtocolSpeciesId());
        setIacucProtocolSpeciesId(null);
    }

    public String getProcedureSummary() {
        return procedureSummary;
    }

    public void setProcedureSummary(String procedureSummary) {
        this.procedureSummary = procedureSummary;
    }

    public IacucSpecies getIacucSpecies() {
        if (iacucSpecies == null) {
            refreshReferenceObject("iacucSpecies");
        }
        return iacucSpecies;
    }

    public void setIacucSpecies(IacucSpecies iacucSpecies) {
        this.iacucSpecies = iacucSpecies;
    }

    public IacucSpeciesCountType getIacucSpeciesCountType() {
        return iacucSpeciesCountType;
    }

    public void setIacucSpeciesCountType(IacucSpeciesCountType iacucSpeciesCountType) {
        this.iacucSpeciesCountType = iacucSpeciesCountType;
    }

    public IacucPainCategory getIacucPainCategory() {
        return iacucPainCategory;
    }

    public void setIacucPainCategory(IacucPainCategory iacucPainCategory) {
        this.iacucPainCategory = iacucPainCategory;
    }

    public List<IacucProtocolException> getIacucProtocolExceptions() {
        return iacucProtocolExceptions;
    }

    public void setIacucProtocolExceptions(List<IacucProtocolException> iacucProtocolExceptions) {
        this.iacucProtocolExceptions = iacucProtocolExceptions;
    }

    public String getSpeciesName() {
        if (iacucSpecies == null) {
            refreshReferenceObject("speciesCode");
        }
        return iacucSpecies.getSpeciesName();
    }

    public String getCountTypeName() {
        if (iacucSpeciesCountType == null) {
            refreshReferenceObject("iacucSpeciesCountType");
        }
        return iacucSpeciesCountType == null ? "None" : iacucSpeciesCountType.getDescription();
    }

    public String getPainCategoryName() {
        if (iacucPainCategory == null) {
            refreshReferenceObject("iacucPainCategory");
        }
        return iacucPainCategory == null ? "None" : iacucPainCategory.getPainCategory();
    }

    public String getGroupAndSpecies() {
        if(ObjectUtils.isNull(iacucSpecies)) {
            this.refreshReferenceObject("iacucSpecies");
        }
        this.groupAndSpecies = speciesGroup.concat(" : ").concat(iacucSpecies.getSpeciesName());
        return groupAndSpecies;
    }

    public boolean isSameGroupAs(IacucProtocolSpecies other) {
        return StringUtils.equals(this.getSpeciesGroup(), other.getSpeciesGroup());
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((iacucProtocolSpeciesId == null) ? 0 : iacucProtocolSpeciesId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IacucProtocolSpecies other = (IacucProtocolSpecies) obj;
        if (iacucProtocolSpeciesId == null) {
            if (other.iacucProtocolSpeciesId != null) {
                return false;
            }
        } else if (!iacucProtocolSpeciesId.equals(other.iacucProtocolSpeciesId)) {
            return false;
        }
        return true;
    }

    public Integer getOldProtocolSpeciesId() {
        return oldProtocolSpeciesId;
    }

    public void setOldProtocolSpeciesId(Integer oldProtocolSpeciesId) {
        this.oldProtocolSpeciesId = oldProtocolSpeciesId;
    }


}
