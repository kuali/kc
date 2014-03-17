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

