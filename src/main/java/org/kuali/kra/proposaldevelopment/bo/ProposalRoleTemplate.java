/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.kim.bo.KimRole;

public class ProposalRoleTemplate extends KraPersistableBusinessObjectBase {
    
    private Long id;
    private String personId;
    private String roleName;
	private String unitNumber;
	private Boolean active;
	
	private Person person;
	private Unit unit;

    public ProposalRoleTemplate() {
        active = true;
    }

	public Long getId() {
        return id;
    }
	
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getPersonName() {
        return person.getFullName();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    public String getUnitName() {
        return unit.getUnitName();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
		hashMap.put("unitNumber", getUnitNumber());
		return hashMap;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof ProposalRoleTemplate))
            return false;
        ProposalRoleTemplate other = (ProposalRoleTemplate) obj;
        return id.equals(other.id);
    }

}
