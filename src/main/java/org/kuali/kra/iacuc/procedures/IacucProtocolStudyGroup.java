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
package org.kuali.kra.iacuc.procedures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.protocol.ProtocolAssociate;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class IacucProtocolStudyGroup extends KraPersistableBusinessObjectBase { 
    

    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolStudyGroupId; 
    private Integer studyGroupId; 
    private Integer iacucProtocolSpeciesId; 
    private String  painCategory;
    private Integer painCategoryCode; 
    private Integer count; 
    private Integer iacucProtocolStudyGroupDetailId;
    
    private IacucProtocolSpecies iacucProtocolSpecies; 
    private IacucPainCategory iacucPainCategory;
    private IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean;
    
    private List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible;
    private List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations;
    private List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList;
    
    private Integer procedureBeanIndex;
    
    public IacucProtocolStudyGroup() { 
        setIacucProcedurePersonsResponsible(new ArrayList<IacucProcedurePersonResponsible>());
        setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
        setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
    } 
    
    public Integer getIacucProtocolStudyGroupId() {
        return iacucProtocolStudyGroupId;
    }

    public void setIacucProtocolStudyGroupId(Integer iacucProtocolStudyGroupId) {
        this.iacucProtocolStudyGroupId = iacucProtocolStudyGroupId;
    }


    public Integer getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(Integer studyGroupId) {
        this.studyGroupId = studyGroupId;
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

    public List<IacucProcedurePersonResponsible> getIacucProcedurePersonsResponsible() {
        return iacucProcedurePersonsResponsible;
    }

    public void setIacucProcedurePersonsResponsible(List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible) {
        this.iacucProcedurePersonsResponsible = iacucProcedurePersonsResponsible;
    }

    public Integer getProcedureBeanIndex() {
        return procedureBeanIndex;
    }

    public void setProcedureBeanIndex(Integer procedureBeanIndex) {
        this.procedureBeanIndex = procedureBeanIndex;
    }

    public List<IacucProtocolStudyGroupLocation> getIacucProtocolStudyGroupLocations() {
        return iacucProtocolStudyGroupLocations;
    }

    public void setIacucProtocolStudyGroupLocations(List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations) {
        this.iacucProtocolStudyGroupLocations = iacucProtocolStudyGroupLocations;
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
        IacucProtocolStudyGroup other = (IacucProtocolStudyGroup) obj;
        if (this.painCategoryCode == null) {
            if (other.painCategoryCode != null) {
                return false;
            }
        } else if (!this.painCategoryCode.equals(other.painCategoryCode)) {
            return false;
        }
        if (this.count == null) {
            if (other.count != null) {
                return false;
            }
        } else if (!this.count.equals(other.count)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() {      
        List<Collection<PersistableBusinessObject>> deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add((Collection) getIacucProcedurePersonsResponsible());
        deleteAwareList.add((Collection) getIacucProtocolStudyGroupLocations());
        return deleteAwareList;
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

    public Integer getIacucProtocolStudyGroupDetailId() {
        return iacucProtocolStudyGroupDetailId;
    }

    public void setIacucProtocolStudyGroupDetailId(Integer iacucProtocolStudyGroupDetailId) {
        this.iacucProtocolStudyGroupDetailId = iacucProtocolStudyGroupDetailId;
    }

    public IacucProtocolStudyGroupDetailBean getIacucProtocolStudyGroupDetailBean() {
        return iacucProtocolStudyGroupDetailBean;
    }

    public void setIacucProtocolStudyGroupDetailBean(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean) {
        this.iacucProtocolStudyGroupDetailBean = iacucProtocolStudyGroupDetailBean;
    }

    public void resetPersistenceState() {
        setIacucProtocolStudyGroupId(null);
    }
    
}