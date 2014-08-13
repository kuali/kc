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
package org.kuali.kra.external.unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Institutional Units stored in the Kuali Coeus system.
 *
 * @author Kuali Coeus Development Team
 */
public class UnitDTO implements Serializable {

    private static final long serialVersionUID = 7517946137745989736L;
    
    private String unitNumber;
    private String parentUnitNumber;
    private String unitName;
    private String organizationId;
    
    /* List of principalIds */
    private List<String> unitAdministrators;
    

    public UnitDTO() {
        unitAdministrators = new ArrayList<String>();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getParentUnitNumber() {
        return parentUnitNumber;
    }

    public void setParentUnitNumber(String parentUnitNumber) {
        this.parentUnitNumber = parentUnitNumber;
    }
    
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getUnitAdministrators() {
        return unitAdministrators;
    }

    public void setUnitAdministrators(List<String> unitAdministrators) {
        this.unitAdministrators = unitAdministrators;
    }

}
