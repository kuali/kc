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

import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;

public class IacucProcedureCategoryCustomData extends CustomAttribute { 
    
    private static final long serialVersionUID = 1L;

    private Integer procedureCategoryCode; 
    private boolean active; 
    private Integer sortId; 
    
    
    public IacucProcedureCategoryCustomData() { 

    } 
    
    @Override
    protected void prePersist() {
        super.prePersist();
        if (sortId == null) {
            sortId = this.getId().intValue();
        }
    }

    public Integer getProcedureCategoryCode() {
        return procedureCategoryCode;
    }

    public void setProcedureCategoryCode(Integer procedureCategoryCode) {
        this.procedureCategoryCode = procedureCategoryCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.procedureCategoryCode == null) ? 0 : this.procedureCategoryCode.hashCode());
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
        IacucProcedureCategoryCustomData other = (IacucProcedureCategoryCustomData) obj;
        if (this.procedureCategoryCode == null) {
            if (other.procedureCategoryCode != null) {
                return false;
            }
        } else if (!this.procedureCategoryCode.equals(other.procedureCategoryCode)) {
            return false;
        }
        return true;
    }
    
}
