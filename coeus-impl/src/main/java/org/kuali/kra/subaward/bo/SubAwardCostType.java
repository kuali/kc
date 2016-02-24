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

public class SubAwardCostType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;
    private String costTypeCode;
    private String costTypeDescription;
    public String getCostTypeCode() {
        return costTypeCode;
    }
    public void setCostTypeCode(String costTypeCode) {
        this.costTypeCode = costTypeCode;
    }
    public String getCostTypeDescription() {
        return costTypeDescription;
    }
    public void setCostTypeDescription(String costTypeDescription) {
        this.costTypeDescription = costTypeDescription;
    }
    
}
