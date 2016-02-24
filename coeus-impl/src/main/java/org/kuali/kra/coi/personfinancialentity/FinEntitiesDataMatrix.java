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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

import java.util.List;

/**
 * 
 * This class is FE data matrix.
 */
public class FinEntitiesDataMatrix extends KcPersistableBusinessObjectBase implements MutableInactivatable {


    private static final long serialVersionUID = -7588988427003150929L;

    private String columnName;

    private String columnLabel;

    private String guiType;

    private String lookupArgument;

    private Integer dataGroupId;

    private boolean active;

    private Integer columnSortId;

    private FinEntitiesDataGroup finEntitiesDataGroup;

    private List<PersonFinIntDisclDet> perFinIntDisclDets;

    public FinEntitiesDataMatrix() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getGuiType() {
        return guiType;
    }

    public void setGuiType(String guiType) {
        this.guiType = guiType;
    }

    public String getLookupArgument() {
        return lookupArgument;
    }

    public void setLookupArgument(String lookupArgument) {
        this.lookupArgument = lookupArgument;
    }

    public Integer getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Integer dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getColumnSortId() {
        return columnSortId;
    }

    public void setColumnSortId(Integer columnSortId) {
        this.columnSortId = columnSortId;
    }

    public FinEntitiesDataGroup getFinEntitiesDataGroup() {
        return finEntitiesDataGroup;
    }

    public void setFinEntitiesDataGroup(FinEntitiesDataGroup finEntitiesDataGroup) {
        this.finEntitiesDataGroup = finEntitiesDataGroup;
    }

    public List<PersonFinIntDisclDet> getPerFinIntDisclDets() {
        return perFinIntDisclDets;
    }

    public void setPerFinIntDisclDets(List<PersonFinIntDisclDet> perFinIntDisclDets) {
        this.perFinIntDisclDets = perFinIntDisclDets;
    }
}
