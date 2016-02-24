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
package org.kuali.kra.award.paymentreports.closeout;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class represets the CloseoutReportType business object.
 */
public class CloseoutReportType extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -1825249905096558223L;

    private String closeoutReportCode;

    private String description;


    public CloseoutReportType() {
    }

    /**
     * Gets the closeoutReportCode attribute. 
     * @return Returns the closeoutReportCode.
     */
    public String getCloseoutReportCode() {
        return closeoutReportCode;
    }

    /**
     * Sets the closeoutReportCode attribute value.
     * @param closeoutReportCode The closeoutReportCode to set.
     */
    public void setCloseoutReportCode(String closeoutReportCode) {
        this.closeoutReportCode = closeoutReportCode;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((closeoutReportCode == null) ? 0 : closeoutReportCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof CloseoutReportType)) return false;
        final CloseoutReportType other = (CloseoutReportType) obj;
        if (closeoutReportCode == null) {
            if (other.closeoutReportCode != null) return false;
        } else if (!closeoutReportCode.equals(other.closeoutReportCode)) return false;
        return true;
    }
}
