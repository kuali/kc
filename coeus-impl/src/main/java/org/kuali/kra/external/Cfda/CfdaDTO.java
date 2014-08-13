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
package org.kuali.kra.external.Cfda;

import java.io.Serializable;

/**
 * This class is the cfda number DTO to be sent over to the
 * financial system
 */
public class CfdaDTO implements Serializable {

    private String cfdaNumber;
    private String cfdaProgramTitleName;
    private String cfdaMaintenanceTypeId;
    private boolean active;
    private String awardId;
    private static final long serialVersionUID = 7517946137745989736L;

    
    public String getAwardId() {
        return awardId;
    }
    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }
    public String getCfdaNumber() {
        return cfdaNumber;
    }
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }
    public String getCfdaProgramTitleName() {
        return cfdaProgramTitleName;
    }
    public void setCfdaProgramTitleName(String cfdaProgramTitleName) {
        this.cfdaProgramTitleName = cfdaProgramTitleName;
    }
    public String getCfdaMaintenanceTypeId() {
        return cfdaMaintenanceTypeId;
    }
    public void setCfdaMaintenanceTypeId(String cfdaMaintenanceTypeId) {
        this.cfdaMaintenanceTypeId = cfdaMaintenanceTypeId;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
