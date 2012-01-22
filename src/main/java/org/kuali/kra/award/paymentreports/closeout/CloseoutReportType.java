/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.paymentreports.closeout;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represets the CloseoutReportType business object.
 */
public class CloseoutReportType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1825249905096558223L;

    private String closeoutReportCode;

    private String description;

    /**
     * 
     * Constructs a CloseoutReportType.java.
     */
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((closeoutReportCode == null) ? 0 : closeoutReportCode.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
