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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CFDA extends KcPersistableBusinessObjectBase {

    private String cfdaNumber;

    private String cfdaProgramTitleName;

    private String cfdaMaintenanceTypeId;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCfdaMaintenanceTypeId() {
        return cfdaMaintenanceTypeId;
    }

    public void setCfdaMaintenanceTypeId(String cfdaMaintenanceTypeId) {
        this.cfdaMaintenanceTypeId = cfdaMaintenanceTypeId;
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
}
