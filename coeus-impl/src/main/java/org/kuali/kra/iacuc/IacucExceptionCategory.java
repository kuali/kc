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

public class IacucExceptionCategory extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer exceptionCategoryCode; 
    private String exceptionCategoryDesc; 
    private boolean active;
    
    public IacucExceptionCategory() { 

    } 
    
    public Integer getExceptionCategoryCode() {
        return exceptionCategoryCode;
    }

    public void setExceptionCategoryCode(Integer exceptionCategoryCode) {
        this.exceptionCategoryCode = exceptionCategoryCode;
    }

    public String getExceptionCategoryDesc() {
        return exceptionCategoryDesc;
    }

    public void setExceptionCategoryDesc(String exceptionCategoryDesc) {
        this.exceptionCategoryDesc = exceptionCategoryDesc;
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
        result = prime * result + ((this.exceptionCategoryCode == null) ? 0 : this.exceptionCategoryCode.hashCode());
        result = prime * result + ((this.exceptionCategoryDesc == null) ? 0 : this.exceptionCategoryDesc.hashCode());
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
        IacucExceptionCategory other = (IacucExceptionCategory) obj;
        if (this.exceptionCategoryCode == null) {
            if (other.exceptionCategoryCode != null) {
                return false;
            }
        } else if (!this.exceptionCategoryCode.equals(other.exceptionCategoryCode)) {
            return false;
        }
        if (this.exceptionCategoryDesc == null) {
            if (other.exceptionCategoryDesc != null) {
                return false;
            }
        } else if (!this.exceptionCategoryDesc.equals(other.exceptionCategoryDesc)) {
            return false;
        }
        return true;
    }
    
}
