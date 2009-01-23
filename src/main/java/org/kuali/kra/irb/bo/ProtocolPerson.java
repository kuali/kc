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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;

public class ProtocolPerson extends KraPersistableBusinessObjectBase { 
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3226064839786525909L;
    private Integer protocolPersonId; 
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private String personId; 
	private String personName; 
	private String protocolPersonRoleId; 
	private Integer rolodexId; 
	private Integer affiliationTypeCode; 
	
	private AffiliationType affiliationType; 
	private ProtocolPersonRole protocolPersonRole; 
	
	private Person person;
	private ProtocolPersonRolodex rolodex;
	
    private boolean delete;
    private boolean trained;
    
    private List<ProtocolUnit> protocolUnits;

	public ProtocolPerson() { 
	    protocolUnits = new ArrayList<ProtocolUnit>();
	} 
	
	public Integer getProtocolPersonId() {
		return protocolPersonId;
	}

	public void setProtocolPersonId(Integer protocolPersonId) {
		this.protocolPersonId = protocolPersonId;
	}

	public Long getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
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

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getProtocolPersonRoleId() {
		return protocolPersonRoleId;
	}

	public void setProtocolPersonRoleId(String protocolPersonRoleId) {
		this.protocolPersonRoleId = protocolPersonRoleId;
	}

	public Integer getRolodexId() {
		return rolodexId;
	}

	public void setRolodexId(Integer rolodexId) {
		this.rolodexId = rolodexId;
	}

	public Integer getAffiliationTypeCode() {
		return affiliationTypeCode;
	}

	public void setAffiliationTypeCode(Integer affiliationTypeCode) {
		this.affiliationTypeCode = affiliationTypeCode;
	}

	public AffiliationType getAffiliationType() {
		return affiliationType;
	}

	public void setAffiliationType(AffiliationType affiliationType) {
		this.affiliationType = affiliationType;
	}

	public ProtocolPersonRole getProtocolPersonRole() {
		return protocolPersonRole;
	}

	public void setProtocolPersonRoles(ProtocolPersonRole protocolPersonRole) {
		this.protocolPersonRole = protocolPersonRole;
	}

	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("protocolPersonId", getProtocolPersonId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("personId", getPersonId());
		hashMap.put("personName", getPersonName());
		hashMap.put("protocolPersonRoleId", getProtocolPersonRoleId());
		hashMap.put("rolodexId", getRolodexId());
		hashMap.put("affiliationTypeCode", getAffiliationTypeCode());
		return hashMap;
	}
	
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ProtocolPersonRolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(ProtocolPersonRolodex rolodex) {
        this.rolodex = rolodex;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isTrained() {
        return trained;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public List<ProtocolUnit> getProtocolUnits() {
        return protocolUnits;
    }

    public void setProtocolUnits(List<ProtocolUnit> protocolUnits) {
        this.protocolUnits = protocolUnits;
    }
    
    /**
     * This method adds a new unit to the collection of person units
     * @param protocolUnit
     */
    public void addProtocolUnit(ProtocolUnit protocolUnit) {
        getProtocolUnits().add(protocolUnit);
    }
    
    /**
     * This method deletes an existing unit from the collection of person units
     * @param protocolUnit
     */
    public void removeProtocolUnit(ProtocolUnit protocolUnit) {
        getProtocolUnits().remove(protocolUnit);
    }

    /**
     * Gets index i from the protocol units list.
     * 
     * @param index
     * @return protocol unit at index i
     */
    public ProtocolUnit getProtocolUnit(int index) {
        return getProtocolUnits().get(index);
    }
    
}