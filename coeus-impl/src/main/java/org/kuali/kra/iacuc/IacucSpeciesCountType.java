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
package org.kuali.kra.iacuc;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class IacucSpeciesCountType extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer speciesCountCode; 
    private String description; 
    
    
    public IacucSpeciesCountType() { 

    } 
    
    public Integer getSpeciesCountCode() {
        return speciesCountCode;
    }

    public void setSpeciesCountCode(Integer speciesCountCode) {
        this.speciesCountCode = speciesCountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.speciesCountCode == null) ? 0 : this.speciesCountCode.hashCode());
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
        IacucSpeciesCountType other = (IacucSpeciesCountType) obj;
        if (this.speciesCountCode == null) {
            if (other.speciesCountCode != null) {
                return false;
            }
        } else if (!this.speciesCountCode.equals(other.speciesCountCode)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
    
}
