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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class ResearchAreaBase extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -1950434459698084680L;

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
    public ResearchAreaBase() {
        super();
    }

    public ResearchAreaBase(String researchAreaCode, String parentResearchAreaCode, String description, boolean active) {
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
        ResearchAreaBase other = (ResearchAreaBase) obj;
        if (researchAreaCode == null) {
            if (other.researchAreaCode != null) return false;
        } else if (!researchAreaCode.equalsIgnoreCase(other.researchAreaCode)) return false;
        return true;
    }
}
