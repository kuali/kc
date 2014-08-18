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