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
package org.kuali.kra.iacuc.species;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.IacucSpeciesCountType;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
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
