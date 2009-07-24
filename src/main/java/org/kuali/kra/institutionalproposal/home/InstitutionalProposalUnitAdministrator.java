/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.home;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;

public class InstitutionalProposalUnitAdministrator extends InstitutionalProposalAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long proposalUnitAdministratorsId; 
    private String unitAdministratorTypeCode; 
    private String administrator; 
    
    private UnitAdministratorType unitAdministratorType;
    private Person person;
    
    public InstitutionalProposalUnitAdministrator() { 

    } 
    
    public Long getProposalUnitAdministratorsId() {
        return proposalUnitAdministratorsId;
    }

    public void setProposalUnitAdministratorsId(Long proposalUnitAdministratorsId) {
        this.proposalUnitAdministratorsId = proposalUnitAdministratorsId;
    }

    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }  

    /**
     * Gets the unitAdministratorType attribute. 
     * @return Returns the unitAdministratorType.
     */
    public UnitAdministratorType getUnitAdministratorType() {
        return unitAdministratorType;
    }

    /**
     * Sets the unitAdministratorType attribute value.
     * @param unitAdministratorType The unitAdministratorType to set.
     */
    public void setUnitAdministratorType(UnitAdministratorType unitAdministratorType) {
        this.unitAdministratorType = unitAdministratorType;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the person attribute value.
     * @param person The person to set.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalUnitAdministratorsId", this.getProposalUnitAdministratorsId());
        hashMap.put("unitAdministratorTypeCode", this.getUnitAdministratorTypeCode());
        hashMap.put("administrator", this.getAdministrator());
        return hashMap;
    }
    
}