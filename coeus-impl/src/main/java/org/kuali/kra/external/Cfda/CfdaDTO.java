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
