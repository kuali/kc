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

public class IacucPainCategory extends KcPersistableBusinessObjectBase implements Comparable {
    
    private static final long serialVersionUID = 1L;

    private Integer painCategoryCode; 
    private String painCategory; 
    private Integer painLevel;
    private boolean active;
    
    
    public IacucPainCategory() { 

    } 
    
    public Integer getPainCategoryCode() {
        return painCategoryCode;
    }

    public void setPainCategoryCode(Integer painCategoryCode) {
        this.painCategoryCode = painCategoryCode;
    }

    public String getPainCategory() {
        return painCategory;
    }

    public void setPainCategory(String painCategory) {
        this.painCategory = painCategory;
    }

    public Integer getPainLevel() {
        return painLevel;
    }

    public void setPainLevel(Integer painLevel) {
        this.painLevel = painLevel;
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
        result = prime * result + ((this.painCategoryCode == null) ? 0 : this.painCategoryCode.hashCode());
        result = prime * result + ((this.painCategory == null) ? 0 : this.painCategory.hashCode());
        result = prime * result + ((this.painLevel == null) ? 0 : this.painLevel.hashCode());
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
        IacucPainCategory other = (IacucPainCategory) obj;
        if (this.painCategoryCode == null) {
            if (other.painCategoryCode != null) {
                return false;
            }
        } else if (!this.painCategoryCode.equals(other.painCategoryCode)) {
            return false;
        }
        if (this.painCategory == null) {
            if (other.painCategory != null) {
                return false;
            }
        } else if (!this.painLevel.equals(other.painLevel)) {
            return false;
        }
        if (this.painLevel == null) {
            if (other.painLevel != null) {
                return false;
            }
        } else if (!this.painLevel.equals(other.painLevel)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object obj) {
        if (this == obj) {
            return 0;
        }
        if (obj == null) {
            return 1;
        }
        IacucPainCategory other = (IacucPainCategory) obj;
        if (this.painLevel == null) {
            if (other.painLevel != null) {
                return -1;
            }
        }
        return this.painLevel.compareTo(other.painLevel);
    }
    
}
