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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;

import java.util.ArrayList;
import java.util.List;

public class IacucProtocolStudyGroup extends KcPersistableBusinessObjectBase {
    

    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyGroupId; 
    private Integer iacucProtocolSpeciesId; 
    private String  painCategory;
    private Integer painCategoryCode; 
    private Integer count; 
    private Integer iacucProtocolStudyGroupHeaderId;
    
    private IacucProtocolSpecies iacucProtocolSpecies; 
    private IacucPainCategory iacucPainCategory;
    private IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean;
    
    private List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList;
    private List<IacucProcedurePersonResponsible> iacucProcedurePersonResponsibleList;
    private List<IacucProtocolStudyGroupLocation> iacucProcedureLocationResponsibleList;
    
    private IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation;
    
    private Integer procedureBeanIndex;

    public IacucProtocolStudyGroup() { 
        setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
        setIacucProcedurePersonResponsibleList(new ArrayList<IacucProcedurePersonResponsible>());
        setIacucProcedureLocationResponsibleList(new ArrayList<IacucProtocolStudyGroupLocation>());
    } 
    
    public Integer getIacucProtocolStudyGroupId() {
        return iacucProtocolStudyGroupId;
    }

    public void setIacucProtocolStudyGroupId(Integer iacucProtocolStudyGroupId) {
        this.iacucProtocolStudyGroupId = iacucProtocolStudyGroupId;
    }

    public Integer getIacucProtocolSpeciesId() {
        return iacucProtocolSpeciesId;
    }

    public void setIacucProtocolSpeciesId(Integer iacucProtocolSpeciesId) {
        this.iacucProtocolSpeciesId = iacucProtocolSpeciesId;
    }

    public String getPainCategory() {
        return painCategory;
    }

    public void setPainCategory(String painCategory) {
        this.painCategory = painCategory;
    }

    public Integer getPainCategoryCode() {
        return painCategoryCode;
    }

    public void setPainCategoryCode(Integer painCategoryCode) {
        this.painCategoryCode = painCategoryCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public IacucProtocolSpecies getIacucProtocolSpecies() {
        if (iacucProtocolSpecies == null) {
            refreshReferenceObject("iacucProtocolSpecies");
        }
        return iacucProtocolSpecies;
    }

    public void setIacucProtocolSpecies(IacucProtocolSpecies iacucProtocolSpecies) {
        this.iacucProtocolSpecies = iacucProtocolSpecies;
    }

    public Integer getProcedureBeanIndex() {
        return procedureBeanIndex;
    }

    public void setProcedureBeanIndex(Integer procedureBeanIndex) {
        this.procedureBeanIndex = procedureBeanIndex;
    }

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
        IacucProtocolStudyGroup other = (IacucProtocolStudyGroup) obj;
        if (this.iacucProtocolStudyGroupId == null) {
            if (other.iacucProtocolStudyGroupId != null) {
                return false;
            }
        } else if (!this.iacucProtocolStudyGroupId.equals(other.iacucProtocolStudyGroupId)) {
            return false;
        }
        if (this.iacucProtocolSpeciesId == null) {
            if (other.iacucProtocolSpeciesId != null) {
                return false;
            }
        } else if (!this.iacucProtocolSpeciesId.equals(other.iacucProtocolSpeciesId)) {
            return false;
        }
        return true;
    }

    
    public List<IacucProtocolStudyCustomData> getIacucProtocolStudyCustomDataList() {
        return iacucProtocolStudyCustomDataList;
    }

    public void setIacucProtocolStudyCustomDataList(List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList) {
        this.iacucProtocolStudyCustomDataList = iacucProtocolStudyCustomDataList;
    }

    public IacucPainCategory getIacucPainCategory() {
        if (iacucPainCategory == null) {
            refreshReferenceObject("iacucPainCategory");
        }
        return iacucPainCategory;
    }

    public void setIacucPainCategory(IacucPainCategory iacucPainCategory) {
        this.iacucPainCategory = iacucPainCategory;
    }

    public Integer getIacucProtocolStudyGroupHeaderId() {
        return iacucProtocolStudyGroupHeaderId;
    }

    public void setIacucProtocolStudyGroupHeaderId(Integer iacucProtocolStudyGroupHeaderId) {
        this.iacucProtocolStudyGroupHeaderId = iacucProtocolStudyGroupHeaderId;
    }

    public IacucProtocolStudyGroupBean getIacucProtocolStudyGroupBean() {
        if (iacucProtocolStudyGroupBean == null) {
            refreshReferenceObject("iacucProtocolStudyGroupBean");
        }
        return iacucProtocolStudyGroupBean;
    }

    public void setIacucProtocolStudyGroupBean(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean) {
        this.iacucProtocolStudyGroupBean = iacucProtocolStudyGroupBean;
    }

    public void resetPersistenceState() {
        setIacucProtocolStudyGroupId(null);
    }
    
    public List<IacucProcedurePersonResponsible> getIacucProcedurePersonResponsibleList() {
        return iacucProcedurePersonResponsibleList;
    }

    public void setIacucProcedurePersonResponsibleList(List<IacucProcedurePersonResponsible> iacucProcedurePersonResponsibleList) {
        this.iacucProcedurePersonResponsibleList = iacucProcedurePersonResponsibleList;
    }

    public List<IacucProtocolStudyGroupLocation> getIacucProcedureLocationResponsibleList() {
        return iacucProcedureLocationResponsibleList;
    }

    public void setIacucProcedureLocationResponsibleList(List<IacucProtocolStudyGroupLocation> iacucProcedureLocationResponsibleList) {
        this.iacucProcedureLocationResponsibleList = iacucProcedureLocationResponsibleList;
    }

    public IacucProtocolStudyGroupLocation getNewIacucProtocolStudyGroupLocation() {
        return newIacucProtocolStudyGroupLocation;
    }

    public void setNewIacucProtocolStudyGroupLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation) {
        this.newIacucProtocolStudyGroupLocation = newIacucProtocolStudyGroupLocation;
    }


}
