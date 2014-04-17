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
package org.kuali.coeus.common.framework.unit.crrspndnt;

import org.kuali.coeus.common.framework.crrspndnt.Correspondent;
import org.kuali.coeus.common.framework.unit.Unit;

public class UnitCorrespondent extends Correspondent {

    private static final long serialVersionUID = 1L;

	private String unitNumber;
	
    private Unit unit;
    
    public UnitCorrespondent() {
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    public String getUnitNumber() {
    	return unitNumber;
    }
    
    public void setUnitNumber(String unitNumber) {
    	this.unitNumber = unitNumber;
    }
    
    public org.kuali.kra.irb.correspondence.CorrespondentType getCorrespondentType() {
        return (org.kuali.kra.irb.correspondence.CorrespondentType) correspondentType;
    }

    public void setCorrespondentType(org.kuali.kra.irb.correspondence.CorrespondentType correspondentType) {
        this.correspondentType = correspondentType;
    }

}
