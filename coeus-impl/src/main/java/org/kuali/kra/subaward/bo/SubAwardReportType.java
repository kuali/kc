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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


public class SubAwardReportType extends KcPersistableBusinessObjectBase {

    private Integer subAwardReportTypeCode;
    
    private String description;

    private Integer sortId;

    public Integer getSubAwardReportTypeCode() {
        return subAwardReportTypeCode;
    }

    public void setSubAwardReportTypeCode(Integer subAwardReportTypeCode) {
        this.subAwardReportTypeCode = subAwardReportTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the sortId attribute. 
     * @return Returns the sortId.
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * Sets the sortId attribute value.
     * @param sortId The sortId to set.
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }
    

}
