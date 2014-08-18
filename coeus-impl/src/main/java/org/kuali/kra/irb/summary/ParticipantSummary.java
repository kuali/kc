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
