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
package org.kuali.kra.protocol.personnel;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class ProtocolAffiliationTypeBase extends KcPersistableBusinessObjectBase {
    


    private static final long serialVersionUID = 1837379583066291237L;
    

    private Integer affiliationTypeCode;

    private String description;

    private boolean active;

    public ProtocolAffiliationTypeBase() {
    }

    public Integer getAffiliationTypeCode() {
        return this.affiliationTypeCode;
    }

    public void setAffiliationTypeCode(Integer affiliationTypeCode) {
        this.affiliationTypeCode = affiliationTypeCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        result = prime * result + ((this.affiliationTypeCode == null) ? 0 : this.affiliationTypeCode.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProtocolAffiliationTypeBase other = (ProtocolAffiliationTypeBase) obj;
        if (this.affiliationTypeCode == null) {
            if (other.affiliationTypeCode != null) {
                return false;
            }
        } else if (!this.affiliationTypeCode.equals(other.affiliationTypeCode)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        if (this.isActive() != other.isActive()) {
            return false;
        }
        return true;
    }
}

