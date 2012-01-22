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
package org.kuali.kra.bo;


public class ResearchArea extends KraPersistableBusinessObjectBase {

    private String researchAreaCode;

    private String parentResearchAreaCode;

    private boolean hasChildrenFlag;

    private String description;

    private boolean active;

    /*
    private CommResearchAreas commResearchAreas
    private CommMemberExpertise commMemberExpertise
    private ProtocolResearchAreas protocolResearchAreas
    */
    public ResearchArea() {
        super();
    }

    public ResearchArea(String researchAreaCode, String parentResearchAreaCode, String description, boolean active) {
        super();
        this.researchAreaCode = researchAreaCode;
        this.parentResearchAreaCode = parentResearchAreaCode;
        this.description = description;
        this.active = active;
        this.hasChildrenFlag = false;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getHasChildrenFlag() {
        return hasChildrenFlag;
    }

    public void setHasChildrenFlag(boolean hasChildrenFlag) {
        this.hasChildrenFlag = hasChildrenFlag;
    }

    public String getParentResearchAreaCode() {
        return parentResearchAreaCode;
    }

    public void setParentResearchAreaCode(String parentResearchAreaCode) {
        this.parentResearchAreaCode = parentResearchAreaCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((researchAreaCode == null) ? 0 : researchAreaCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ResearchArea other = (ResearchArea) obj;
        if (researchAreaCode == null) {
            if (other.researchAreaCode != null) return false;
        } else if (!researchAreaCode.equalsIgnoreCase(other.researchAreaCode)) return false;
        return true;
    }
}
