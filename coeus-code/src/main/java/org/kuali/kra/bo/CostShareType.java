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
package org.kuali.kra.bo;




import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


public class CostShareType extends KcPersistableBusinessObjectBase {



    private static final long serialVersionUID = -4625330898428160836L;

    private Integer costShareTypeCode;

    private String description;


    public CostShareType() {
        super();
    }

    public Integer getCostShareTypeCode() {
        return costShareTypeCode;
    }

    public void setCostShareTypeCode(Integer costShareTypeCode) {
        this.costShareTypeCode = costShareTypeCode;
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
        result = prime * result + ((costShareTypeCode == null) ? 0 : costShareTypeCode.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final CostShareType other = (CostShareType) obj;
        if (costShareTypeCode == null) {
            if (other.costShareTypeCode != null) return false;
        } else if (!costShareTypeCode.equals(other.costShareTypeCode)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        return true;
    }
}
