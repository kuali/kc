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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;

import java.io.Serializable;

public class IacucProtocolSpeciesSummary implements Serializable { 

    private static final long serialVersionUID = 9204826126972393444L;

    private Integer speciesId; 
    private String  species;
    private boolean speciesChanged;
    private String  speciesGroup; 
    private boolean speciesGroupChanged;
    private boolean usdaCovered; 
    private boolean usdaCoveredChanged;
    private String  strain; 
    private boolean strainChanged;
    private int     speciesCount; 
    private boolean speciesCountChanged;
    private String  speciesCountType;
    private boolean speciesCountTypeChanged;
    private String  painCategory;
    private boolean painCategoryChanged;
    private String  procedureSummary;
    private boolean procedureSummaryChanged;
    
    public IacucProtocolSpeciesSummary(IacucProtocolSpecies iacucSpecies) {
        speciesId = iacucSpecies.getSpeciesId();
        species = iacucSpecies.getSpeciesName();
        speciesGroup = iacucSpecies.getSpeciesGroup(); 
        usdaCovered = iacucSpecies.getUsdaCovered();
        strain = iacucSpecies.getStrain();
        speciesCount = iacucSpecies.getSpeciesCount().intValue();
        speciesCountType = iacucSpecies.getCountTypeName();
        painCategory = iacucSpecies.getPainCategoryName();
        procedureSummary = iacucSpecies.getProcedureSummary();
    }
    public Integer getSpeciesId() {
        return speciesId;
    }
    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public boolean isSpeciesChanged() {
        return speciesChanged;
    }
    public void setSpeciesChanged(boolean speciesChanged) {
        this.speciesChanged = speciesChanged;
    }
    public String getSpeciesGroup() {
        return speciesGroup;
    }
    public void setSpeciesGroup(String speciesGroup) {
        this.speciesGroup = speciesGroup;
    }
    public boolean isSpeciesGroupChanged() {
        return speciesGroupChanged;
    }
    public void setSpeciesGroupChanged(boolean speciesGroupChanged) {
        this.speciesGroupChanged = speciesGroupChanged;
    }
    public boolean isUsdaCovered() {
        return usdaCovered;
    }
    public void setUsdaCovered(boolean usdaCovered) {
        this.usdaCovered = usdaCovered;
    }
    public boolean isUsdaCoveredChanged() {
        return usdaCoveredChanged;
    }
    public void setUsdaCoveredChanged(boolean usdaCoveredChanged) {
        this.usdaCoveredChanged = usdaCoveredChanged;
    }
    public String getStrain() {
        return strain;
    }
    public void setStrain(String strain) {
        this.strain = strain;
    }
    public boolean isStrainChanged() {
        return strainChanged;
    }
    public void setStrainChanged(boolean strainChanged) {
        this.strainChanged = strainChanged;
    }
    public int getSpeciesCount() {
        return speciesCount;
    }
    public void setSpeciesCount(int speciesCount) {
        this.speciesCount = speciesCount;
    }
    public boolean isSpeciesCountChanged() {
        return speciesCountChanged;
    }
    public void setSpeciesCountChanged(boolean speciesCountChanged) {
        this.speciesCountChanged = speciesCountChanged;
    }
    public String getSpeciesCountType() {
        return speciesCountType;
    }
    public void setSpeciesCountType(String speciesCountType) {
        this.speciesCountType = speciesCountType;
    }
    public boolean isSpeciesCountTypeChanged() {
        return speciesCountTypeChanged;
    }
    public void setSpeciesCountTypeChanged(boolean speciesCountTypeChanged) {
        this.speciesCountTypeChanged = speciesCountTypeChanged;
    }
    public String getPainCategory() {
        return painCategory;
    }
    public void setPainCategory(String painCategory) {
        this.painCategory = painCategory;
    }
    public boolean isPainCategoryChanged() {
        return painCategoryChanged;
    }
    public void setPainCategoryChanged(boolean painCategoryChanged) {
        this.painCategoryChanged = painCategoryChanged;
    }
    public String getProcedureSummary() {
        return procedureSummary;
    }
    public void setProcedureSummary(String procedureSummary) {
        this.procedureSummary = procedureSummary;
    }
    public boolean isProcedureSummaryChanged() {
        return procedureSummaryChanged;
    }
    public void setProcedureSummaryChanged(boolean procedureSummaryChanged) {
        this.procedureSummaryChanged = procedureSummaryChanged;
    }
   
    public void compare(IacucProtocolSummary other) {
        IacucProtocolSpeciesSummary otherSummary = (other == null) ? null : other.findSpeciesSummary(speciesId);
        if (otherSummary == null) {
            speciesChanged = true;
            speciesGroupChanged = true;
            usdaCoveredChanged = true;
            strainChanged = true;
            speciesCountChanged = true;
            speciesCountTypeChanged = true;
            painCategoryChanged = true;
            procedureSummaryChanged = true;
        } else {
            speciesChanged = !StringUtils.equals(species, otherSummary.species);
            speciesGroupChanged = !StringUtils.equals(speciesGroup, otherSummary.speciesGroup);
            usdaCoveredChanged = usdaCovered != otherSummary.usdaCoveredChanged;
            strainChanged = !StringUtils.equals(strain, otherSummary.strain);
            speciesCountChanged = speciesCount != otherSummary.speciesCount;
            speciesCountTypeChanged = !StringUtils.equals(speciesCountType, otherSummary.speciesCountType);
            painCategoryChanged = !StringUtils.equals(painCategory, otherSummary.painCategory);
            procedureSummaryChanged = !StringUtils.equals(procedureSummary, otherSummary.procedureSummary);
        }
    }
}
