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