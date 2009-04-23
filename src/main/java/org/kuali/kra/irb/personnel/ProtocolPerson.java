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
package org.kuali.kra.irb.personnel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.AffiliationType;
import org.kuali.kra.bo.Person;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolUnit;

public class ProtocolPerson extends ProtocolAssociate {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3226064839786525909L;
    private Integer protocolPersonId;
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

    private int selectedUnit;
    private String previousPersonRoleId;

    public ProtocolPerson() {
        protocolUnits = new ArrayList<ProtocolUnit>();
    }

    public Integer getProtocolPersonId() {
        return protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
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
        setPreviousPersonRoleId(this.protocolPersonRoleId);
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
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("protocolPersonId", getProtocolPersonId());
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

    /**
     * This method is linked to personnel service to check whether person has attended any training session.
     * 
     * @return boolean
     */
    public boolean isTrained() {
        return trained;
    }

    /**
     * This method is to check whether protocol units is required
     * 
     * @return boolean
     */
    public boolean isUnitRequired() {
        return getProtocolPersonRole().isUnitDetailsRequired();
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
     * 
     * @param protocolUnit
     */
    public void addProtocolUnit(ProtocolUnit protocolUnit) {
        getProtocolUnits().add(protocolUnit);
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

    public int getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    /**
     * This method is to reset all lead unit flag in protocol unit.
     */
    public void resetAllProtocolLeadUnits() {
        for (ProtocolUnit protocolUnit : getProtocolUnits()) {
            protocolUnit.setLeadUnitFlag(false);
        }
    }

    /**
     * This method is to build and return a unique key for protocol person
     * 
     * @return String
     */
    public String getPersonUniqueKey() {
        return new StringBuilder(getPersonId() == null ? getRolodexId().toString() : getPersonId()).append(
                getProtocolPersonRoleId()).toString();
    }

    public String getPreviousPersonRoleId() {
        return previousPersonRoleId;
    }

    public void setPreviousPersonRoleId(String previousPersonRoleId) {
        this.previousPersonRoleId = previousPersonRoleId;
    }

    /**
     * This method checks whether person is an employee or not non employee details are updated in rolodex a value in rolodex
     * indicates that the person is non employee
     * 
     * @return true / false
     */
    public boolean isNonEmployee() {
        return rolodex != null;
    }

    /**
     * This method is to find lead unit from unit list
     * 
     * @return ProtocolUnit (lead unit)
     */
    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        for (ProtocolUnit unit : getProtocolUnits()) {
            if (unit.getLeadUnitFlag()) {
                leadUnit = unit;
                break;
            }
        }
        return leadUnit;
    }

    public void init(Protocol protocol) {
        setProtocolPersonId(null);
        //setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        for (ProtocolUnit unit : protocolUnits) {
            unit.init(this);
        }
    }
}