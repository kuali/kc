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
