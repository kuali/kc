/*
 * Copyright 2005-2013 The Kuali Foundation
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

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * This class...
 */
@Entity
@Table(name = "CITIZENSHIP_TYPE_T")
public class CitizenshipType extends KraPersistableBusinessObjectBase implements MutableInactivatable {

    @Id
    @Column(name = "CITIZENSHIP_TYPE_CODE")
    private Integer citizenshipTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    /**
     * Constructs a CitizenshipType.java.
     */
    public CitizenshipType() {
        super();
    }

    public Integer getCitizenshipTypeCode() {
        return citizenshipTypeCode;
    }

    public void setCitizenshipTypeCode(Integer citizenTypeCode) {
        this.citizenshipTypeCode = citizenTypeCode;
    }

    public String getDescription() {
        return description;
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
}
