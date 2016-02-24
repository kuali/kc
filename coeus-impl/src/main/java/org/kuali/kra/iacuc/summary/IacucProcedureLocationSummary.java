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
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation;

import java.io.Serializable;

public class IacucProcedureLocationSummary implements Serializable {
    
    private static final long serialVersionUID = -2115905931286970929L;

    private Integer id;
    private String type;
    private String name;
    private String room;
    private String description;   
    
    private boolean typeChanged;
    private boolean nameChanged;
    private boolean roomChanged;
    private boolean descriptionChanged;
    
    public IacucProcedureLocationSummary(IacucProtocolStudyGroupLocation location) {
        this.id = location.getIacucProtocolStudyGroupLocationId();
        this.type = location.getIacucLocationType().getLocation();
        this.name = location.getIacucLocationName().getLocationName();
        this.room = location.getLocationRoom();
        this.description = location.getStudyGroupLocationDescription();
        
        this.typeChanged = false;
        this.nameChanged = false;
        this.roomChanged = false;
        this.descriptionChanged = false;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isTypeChanged() {
        return typeChanged;
    }
    public void setTypeChanged(boolean typeChanged) {
        this.typeChanged = typeChanged;
    }
    public boolean isNameChanged() {
        return nameChanged;
    }
    public void setNameChanged(boolean nameChanged) {
        this.nameChanged = nameChanged;
    }
    public boolean isRoomChanged() {
        return roomChanged;
    }
    public void setRoomChanged(boolean roomChanged) {
        this.roomChanged = roomChanged;
    }
    public boolean isDescriptionChanged() {
        return descriptionChanged;
    }
    public void setDescriptionChanged(boolean descriptionChanged) {
        this.descriptionChanged = descriptionChanged;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void compare(IacucProcedureSummary other) {
        IacucProcedureLocationSummary otherSummary = (other == null) ? null : other.findProcedureLocationSummary(id);
        if (otherSummary == null) {
            nameChanged = true;
            roomChanged = true;
            descriptionChanged = true;
        } else {
            nameChanged = !StringUtils.equals(this.name, otherSummary.name);
            roomChanged = !StringUtils.equals(this.room, otherSummary.room);
            descriptionChanged = !StringUtils.equals(this.description, otherSummary.description);
        }
    }
    
}
