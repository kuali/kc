/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.procedures;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolStudyGroupDetailBean extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5919176798738325709L;

    private Integer iacucProtocolStudyGroupDetailId; 
    private Integer iacucProtocolStudyGroupHeaderId; 
    private Integer speciesCode;
    // sum of all species count for the category and species selected
    Integer totalSpeciesCount;
    Integer maxPainCategoryCode;
    
    // max pain category for selected species group
    String maxPainCategory;
    Integer maxPainLevel;
    
    private IacucPainCategory maxIacucPainCategory;
    private IacucSpecies iacucSpecies;
    private IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean;

    private List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible;
    
    public IacucProtocolStudyGroupDetailBean() {
        setIacucProcedurePersonsResponsible(new ArrayList<IacucProcedurePersonResponsible>());
    }

    public String getMaxPainCategory() {
        if(ObjectUtils.isNull(maxPainCategory)) {
            maxPainCategory = maxIacucPainCategory.getPainCategory();
        }
        return maxPainCategory;
    }

    public void setMaxPainCategory(String maxPainCategory) {
        this.maxPainCategory = maxPainCategory;
    }

    public Integer getTotalSpeciesCount() {
        return totalSpeciesCount;
    }

    public void setTotalSpeciesCount(Integer totalSpeciesCount) {
        this.totalSpeciesCount = totalSpeciesCount;
    }

    public List<IacucProcedurePersonResponsible> getIacucProcedurePersonsResponsible() {
        return iacucProcedurePersonsResponsible;
    }

    public void setIacucProcedurePersonsResponsible(List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible) {
        this.iacucProcedurePersonsResponsible = iacucProcedurePersonsResponsible;
    }

    public Integer getMaxPainCategoryCode() {
        return maxPainCategoryCode;
    }

    public void setMaxPainCategoryCode(Integer maxPainCategoryCode) {
        this.maxPainCategoryCode = maxPainCategoryCode;
    }

    public Integer getMaxPainLevel() {
        return maxPainLevel;
    }

    public void setMaxPainLevel(Integer maxPainLevel) {
        this.maxPainLevel = maxPainLevel;
    }

    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public IacucPainCategory getMaxIacucPainCategory() {
        return maxIacucPainCategory;
    }

    public void setMaxIacucPainCategory(IacucPainCategory maxIacucPainCategory) {
        this.maxIacucPainCategory = maxIacucPainCategory;
    }

    public Integer getIacucProtocolStudyGroupDetailId() {
        return iacucProtocolStudyGroupDetailId;
    }

    public void setIacucProtocolStudyGroupDetailId(Integer iacucProtocolStudyGroupDetailId) {
        this.iacucProtocolStudyGroupDetailId = iacucProtocolStudyGroupDetailId;
    }

    public Integer getIacucProtocolStudyGroupHeaderId() {
        return iacucProtocolStudyGroupHeaderId;
    }

    public void setIacucProtocolStudyGroupHeaderId(Integer iacucProtocolStudyGroupHeaderId) {
        this.iacucProtocolStudyGroupHeaderId = iacucProtocolStudyGroupHeaderId;
    }

    public IacucSpecies getIacucSpecies() {
        return iacucSpecies;
    }

    public void setIacucSpecies(IacucSpecies iacucSpecies) {
        this.iacucSpecies = iacucSpecies;
    }

    public IacucProtocolStudyGroupBean getIacucProtocolStudyGroupBean() {
        return iacucProtocolStudyGroupBean;
    }

    public void setIacucProtocolStudyGroupBean(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean) {
        this.iacucProtocolStudyGroupBean = iacucProtocolStudyGroupBean;
    }

    
    /**  {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        IacucProtocolStudyGroupDetailBean other = (IacucProtocolStudyGroupDetailBean) obj;
        if (this.iacucProtocolStudyGroupDetailId == null) {
            if (other.iacucProtocolStudyGroupDetailId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupDetailId.equals(other.iacucProtocolStudyGroupDetailId)) {
            return false;
        }
        return true;
    }

    public void resetPersistenceState() {
        setIacucProtocolStudyGroupDetailId(null);
    }

}
