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

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * This class is for UI data matrix display. It will be populated from data/group/datamatrix/relationshiptype tables
 */
public class FinEntityDataMatrixBean implements Serializable {

    private static final long serialVersionUID = -2560941824792191059L;
    private static final String GUI_DROPDOWN = "DROPDOWN";
    private String columnName; 
    private String dataGroupName; 
    private String columnLabel; 
    private String guiType; 
    private String comments; 
    private String lookupArgument; 
    private Integer dataGroupId; 
    private List<RelationshipTypeBean> relationshipTypeBeans;
    
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

    public List<RelationshipTypeBean> getRelationshipTypeBeans() {
        return relationshipTypeBeans;
    }

    public void setRelationshipTypeBeans(List<RelationshipTypeBean> relationshipTypeBeans) {
        this.relationshipTypeBeans = relationshipTypeBeans;
    }

    public String getDataGroupName() {
        return dataGroupName;
    }

    public void setDataGroupName(String dataGroupName) {
        this.dataGroupName = dataGroupName;
    }

    public boolean isArgValueLookup() {
        return StringUtils.equals(GUI_DROPDOWN, this.guiType) && StringUtils.isNotBlank(this.lookupArgument);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
