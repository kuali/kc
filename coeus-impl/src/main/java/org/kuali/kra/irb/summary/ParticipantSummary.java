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
package org.kuali.kra.irb.summary;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class ParticipantSummary implements Serializable {

    private static final long serialVersionUID = 6459822062556001624L;
    
    private String description;
    private String count;
    
    private boolean descriptionChanged;
    private boolean countChanged;
    
    public ParticipantSummary() {
       
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        if (count == null) {
            this.count = "";
        }
        else {
            this.count = count.toString();
        }
    }

    public boolean isDescriptionChanged() {
        return descriptionChanged;
    }
    
    public boolean isCountChanged() {
        return countChanged;
    }
    
    public void compare(ProtocolSummary other) {
        ParticipantSummary otherParticipant = other.findParticipant(description);
        if (otherParticipant == null) {
            descriptionChanged = true;
            countChanged = true;
        }
        else {
            descriptionChanged = !StringUtils.equals(description, otherParticipant.description);
            countChanged = !StringUtils.equals(count, otherParticipant.count);
        }
    }
}
