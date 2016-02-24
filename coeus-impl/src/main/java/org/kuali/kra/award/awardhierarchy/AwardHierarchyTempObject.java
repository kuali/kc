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
package org.kuali.kra.award.awardhierarchy;

import java.io.Serializable;

public class AwardHierarchyTempObject implements Serializable{
    private String awardNumber;
    private String newChildPanelTargetAward;
    private String copyAwardPanelTargetAward;
    private Boolean copyDescendants;
    private String copyAwardRadio;
    private String createNewChildRadio;
    
    public AwardHierarchyTempObject(){
        newChildPanelTargetAward = "";
        copyAwardPanelTargetAward = "";
    }

    public String getNewChildPanelTargetAward() {
        return newChildPanelTargetAward;
    }

    public void setNewChildPanelTargetAward(String newChildPanelTargetAward) {
        this.newChildPanelTargetAward = newChildPanelTargetAward;
    }

    public String getCopyAwardPanelTargetAward() {
        return copyAwardPanelTargetAward;
    }

    public void setCopyAwardPanelTargetAward(String copyAwardPanelTargetAward) {
        this.copyAwardPanelTargetAward = copyAwardPanelTargetAward;
    }

    public Boolean getCopyDescendants() {
        return copyDescendants;
    }

    public void setCopyDescendants(Boolean copyDescendants) {
        this.copyDescendants = copyDescendants;
    }

    public String getCopyAwardRadio() {
        return copyAwardRadio;
    }

    public void setCopyAwardRadio(String copyAwardRadio) {
        this.copyAwardRadio = copyAwardRadio;
    }

    public String getCreateNewChildRadio() {
        return createNewChildRadio;
    }

    public void setCreateNewChildRadio(String createNewChildRadio) {
        this.createNewChildRadio = createNewChildRadio;
    }    

    public String getAwardNumber() {
        return awardNumber;
    }
    
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }
}

