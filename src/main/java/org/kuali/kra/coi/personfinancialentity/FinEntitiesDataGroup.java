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
package org.kuali.kra.coi.personfinancialentity;

import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is for FE entity data groups.
 */
public class FinEntitiesDataGroup extends KraPersistableBusinessObjectBase implements Comparable<FinEntitiesDataGroup> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7800055388419460633L;

    private Integer dataGroupId;

    private String dataGroupName;

    private Integer dataGroupSortId;

    private List<FinEntitiesDataMatrix> finEntitiesDataMatrixs;

    public FinEntitiesDataGroup() {
    }

    public Integer getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Integer dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public String getDataGroupName() {
        return dataGroupName;
    }

    public void setDataGroupName(String dataGroupName) {
        this.dataGroupName = dataGroupName;
    }

    public Integer getDataGroupSortId() {
        return dataGroupSortId;
    }

    public void setDataGroupSortId(Integer dataGroupSortId) {
        this.dataGroupSortId = dataGroupSortId;
    }

    public List<FinEntitiesDataMatrix> getFinEntitiesDataMatrixs() {
        return finEntitiesDataMatrixs;
    }

    public void setFinEntitiesDataMatrixs(List<FinEntitiesDataMatrix> finEntitiesDataMatrixs) {
        this.finEntitiesDataMatrixs = finEntitiesDataMatrixs;
    }

    public int compareTo(FinEntitiesDataGroup arg1) {
        return getDataGroupSortId().compareTo(arg1.getDataGroupSortId());
    }
}
