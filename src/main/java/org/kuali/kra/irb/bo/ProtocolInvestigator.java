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
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.RolodexService;
import org.springframework.util.StringUtils;

public class ProtocolInvestigator extends KraPersistableBusinessObjectBase { 
	
	private Integer protocolInvestigatorsId; 
	private Long protocolId; 
	private String protocolNumber; 
	private Integer sequenceNumber; 
	private String personId; 
	private String personName; 
	private boolean nonEmployeeFlag; 
	private boolean principalInvestigatorFlag; 
	private Integer affiliationTypeCode; 
	
	private ProtocolDocument protocolDocument; 
	private AffiliationType affiliationType; 
	private List<ProtocolUnit> protocolUnits; 
	
	public ProtocolInvestigator() { 
        super();
        protocolUnits = new ArrayList<ProtocolUnit>();
	} 

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        
        managedLists.add(getProtocolUnits());

        return managedLists;
    }
    
	public Integer getProtocolInvestigatorsId() {
		return protocolInvestigatorsId;
	}

	public void setProtocolInvestigatorsId(Integer protocolInvestigatorsId) {
		this.protocolInvestigatorsId = protocolInvestigatorsId;
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

   public void setPersonNameFromId(String personId, boolean nonEmpFlag) {       
        this.personName = getPersonNameFromId(personId, nonEmpFlag);
    }

   public void setPersonName(String personName) {
		this.personName = personName;
	}

	public boolean getNonEmployeeFlag() {
		return nonEmployeeFlag;
	}

	public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
		this.nonEmployeeFlag = nonEmployeeFlag;
	}

	public boolean getPrincipalInvestigatorFlag() {
		return principalInvestigatorFlag;
	}

	public void setPrincipalInvestigatorFlag(boolean principalInvestigatorFlag) {
		this.principalInvestigatorFlag = principalInvestigatorFlag;
	}

	public Integer getAffiliationTypeCode() {
		return affiliationTypeCode;
	}

	public void setAffiliationTypeCode(Integer affiliationTypeCode) {
		this.affiliationTypeCode = affiliationTypeCode;
	}

	public ProtocolDocument getProtocol() {
		return protocolDocument;
	}

	public void setProtocol(ProtocolDocument protocolDocument) {
		this.protocolDocument = protocolDocument;
	}

	public AffiliationType getAffiliationType() {
		return affiliationType;
	}

	public void setAffiliationType(AffiliationType affiliationType) {
		this.affiliationType = affiliationType;
	}

	public List<ProtocolUnit> getProtocolUnits() {
		return protocolUnits;
	}

    public void setProtocolUnits(List<ProtocolUnit> protocolUnits) {
		this.protocolUnits = protocolUnits;
	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("protocolInvestigatorsId", getProtocolInvestigatorsId());
		hashMap.put("protocolId", getProtocolId());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("personId", getPersonId());
		hashMap.put("personName", getPersonName());
		hashMap.put("nonEmployeeFlag", getNonEmployeeFlag());
		hashMap.put("principalInvestigatorFlag", getPrincipalInvestigatorFlag());
		hashMap.put("affiliationTypeCode", getAffiliationTypeCode());
		return hashMap;
	}
    
    public String getPersonNameFromId(String personId, boolean nonEmployee) {
        String ret = null;
        if(nonEmployee) {
            Rolodex theRolodex = getRolodexFromId(personId);
            if (theRolodex != null ) {
                    ret = theRolodex.getFullName();
            }
        } else {
            Person thePerson = getPersonFromId(personId);
            if (thePerson != null ) {
                    ret = thePerson.getFullName();
            }
        }
        return ret;
    }
    
    private Person getPersonFromId(String personId) {
        Person ret =null;
        if (StringUtils.hasText(personId) ) { 
            PersonService personService = KraServiceLocator.getService(PersonService.class);
            ret = personService.getPerson(personId);
        }
        return ret;
    }
    
    private Rolodex getRolodexFromId(String rolodexId) {
        Rolodex ret = null;
        if (StringUtils.hasText(rolodexId) ) { 
            Integer rolodexIntId = Integer.parseInt(rolodexId);
            RolodexService rolodexService = KraServiceLocator.getService(RolodexService.class);
            ret = rolodexService.getRolodex(rolodexIntId.intValue());
        }
        return ret;
    }
    
    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        for ( ProtocolUnit unit : getProtocolUnits() ) {
            if (unit.getLeadUnitFlag()) {
                leadUnit = unit;
            }
        }
        return leadUnit;
    }
    
    
    
}