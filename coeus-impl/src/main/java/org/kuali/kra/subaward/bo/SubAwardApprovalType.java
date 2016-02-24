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


public class SubAwardApprovalType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer subAwardApprovalTypeCode;

    private String description;


    public SubAwardApprovalType() {
    }

    /**
     * This method is for getting subAwardApprovalTypeCode ...
     * @return subAwardApprovalTypeCode
     */
    public Integer getSubAwardApprovalTypeCode() {
        return subAwardApprovalTypeCode;
    }
    
    /**
     * This method is for setting subAwardApprovalTypeCode...
     * @param subAwardApprovalTypeCode the subAwardApprovalTypeCode to set
     */
    public void setSubAwardApprovalTypeCode(Integer subAwardApprovalTypeCode) {
        this.subAwardApprovalTypeCode = subAwardApprovalTypeCode;
    }
    /**
     * This method is for getting Description ...
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * This method is for setting description...
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
