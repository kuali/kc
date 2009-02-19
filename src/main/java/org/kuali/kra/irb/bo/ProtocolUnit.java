/*
 * Copyright 2006-2008 The Kuali Foundation
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

package org.kuali.kra.irb.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;

public class ProtocolUnit extends KraPersistableBusinessObjectBase { 
	
	private Integer protocolUnitsId; 
    private Integer protocolPersonId; 
	private Integer protocolInvestigatorsId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private String unitNumber; 
	private boolean leadUnitFlag; 
	private String personId; 
	private String unitName;
	
	private Unit unit; 
	
    private ProtocolPerson protocolPerson; 

    public ProtocolUnit() { 
        setLeadUnitFlag(false);
	} 
	
	public Integer getProtocolUnitsId() {
		return protocolUnitsId;
	}

    public void setProtocolUnitsId(Integer protocolUnitsId) {
        this.protocolUnitsId = protocolUnitsId;
    }

    public Integer getProtocolPersonId() {
        return protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }


	public Integer getProtocolInvestigatorsId() {
		return protocolInvestigatorsId;
	}

	public void setProtocolInvestigatorsId(Integer protocolInvestigatorsId) {
		this.protocolInvestigatorsId = protocolInvestigatorsId;
	}

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public boolean getLeadUnitFlag() {
		return leadUnitFlag;
	}

	public void setLeadUnitFlag(boolean leadUnitFlag) {
		this.leadUnitFlag = leadUnitFlag;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolUnitsId", getProtocolUnitsId());
		hashMap.put("protocolInvestigatorsId", getProtocolInvestigatorsId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("leadUnitFlag", getLeadUnitFlag());
		hashMap.put("personId", getPersonId());
		return hashMap;
	}

    public ProtocolPerson getProtocolPerson() {
        return protocolPerson;
    }

    public void setProtocolPerson(ProtocolPerson protocolPerson) {
        this.protocolPerson = protocolPerson;
    }

    // Note this field isn't persisted in protocolUnit so
    // we've got do pull from the Unit reference.
    public String getUnitName() {
        if (StringUtils.isEmpty(unitName) && StringUtils.isNotEmpty(unitNumber)) {
                this.refreshReferenceObject("unit");
                if (unit != null) {
                    setUnitName(unit.getUnitName());            
                }
        }
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
	
}