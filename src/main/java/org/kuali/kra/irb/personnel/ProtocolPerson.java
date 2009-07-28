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
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;

public class ProtocolPerson extends ProtocolAssociate {

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
    private List<ProtocolAttachmentPersonnel> attachmentPersonnels;

    private int selectedUnit;
    private String previousPersonRoleId;

    public ProtocolPerson() {
        this.protocolUnits = new ArrayList<ProtocolUnit>();
    }

    public Integer getProtocolPersonId() {
        return this.protocolPersonId;
    }

    public void setProtocolPersonId(Integer protocolPersonId) {
        this.protocolPersonId = protocolPersonId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProtocolPersonRoleId() {
        return this.protocolPersonRoleId;
    }

    public void setProtocolPersonRoleId(String protocolPersonRoleId) {
        this.setPreviousPersonRoleId(this.protocolPersonRoleId);
        this.protocolPersonRoleId = protocolPersonRoleId;
    }

    public Integer getRolodexId() {
        return this.rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Integer getAffiliationTypeCode() {
        return this.affiliationTypeCode;
    }

    public void setAffiliationTypeCode(Integer affiliationTypeCode) {
        this.affiliationTypeCode = affiliationTypeCode;
    }

    public AffiliationType getAffiliationType() {
        return this.affiliationType;
    }

    public void setAffiliationType(AffiliationType affiliationType) {
        this.affiliationType = affiliationType;
    }

    public ProtocolPersonRole getProtocolPersonRole() {
        return this.protocolPersonRole;
    }

    public void setProtocolPersonRoles(ProtocolPersonRole protocolPersonRole) {
        this.protocolPersonRole = protocolPersonRole;
    }

    /**  {@inheritDoc} */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("protocolPersonId", this.getProtocolPersonId());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("personName", this.getPersonName());
        hashMap.put("protocolPersonRoleId", this.getProtocolPersonRoleId());
        hashMap.put("rolodexId", this.getRolodexId());
        hashMap.put("affiliationTypeCode", this.getAffiliationTypeCode());
        return hashMap;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ProtocolPersonRolodex getRolodex() {
        return this.rolodex;
    }

    public void setRolodex(ProtocolPersonRolodex rolodex) {
        this.rolodex = rolodex;
    }

    public boolean isDelete() {
        return this.delete;
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
        return this.trained;
    }

    /**
     * This method is to check whether protocol units is required.
     * 
     * @return boolean
     */
    public boolean isUnitRequired() {
        return this.getProtocolPersonRole().isUnitDetailsRequired();
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }

    public List<ProtocolUnit> getProtocolUnits() {
        return this.protocolUnits;
    }

    public void setProtocolUnits(List<ProtocolUnit> protocolUnits) {
        this.protocolUnits = protocolUnits;
    }

    /**
     * This method adds a new unit to the collection of person units.
     * 
     * @param protocolUnit
     */
    public void addProtocolUnit(ProtocolUnit protocolUnit) {
        this.getProtocolUnits().add(protocolUnit);
    }

    /**
     * Gets index i from the protocol units list.
     * 
     * @param index
     * @return protocol unit at index i
     */
    public ProtocolUnit getProtocolUnit(int index) {
        return this.getProtocolUnits().get(index);
    }

    public int getSelectedUnit() {
        return this.selectedUnit;
    }

    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    /**
     * This method is to reset all lead unit flag in protocol unit.
     */
    public void resetAllProtocolLeadUnits() {
        for (ProtocolUnit protocolUnit : this.getProtocolUnits()) {
            protocolUnit.setLeadUnitFlag(false);
        }
    }

    /**
     * This method is to build and return a unique key for protocol person.
     * 
     * @return String
     */
    public String getPersonUniqueKey() {
        return new StringBuilder(this.getPersonId() == null ? this.getRolodexId().toString() : this.getPersonId()).append(
                this.getProtocolPersonRoleId()).toString();
    }

    public String getPreviousPersonRoleId() {
        return this.previousPersonRoleId;
    }

    public void setPreviousPersonRoleId(String previousPersonRoleId) {
        this.previousPersonRoleId = previousPersonRoleId;
    }

    /**
     * This method checks whether person is an employee or not non employee details are updated in rolodex a value in rolodex.
     * indicates that the person is non employee
     * 
     * @return true / false
     */
    public boolean isNonEmployee() {
        return this.rolodex != null;
    }

    /**
     * This method is to find lead unit from unit list.
     * 
     * @return ProtocolUnit (lead unit)
     */
    public ProtocolUnit getLeadUnit() {
        ProtocolUnit leadUnit = null;
        for (ProtocolUnit unit : this.getProtocolUnits()) {
            if (unit.getLeadUnitFlag()) {
                leadUnit = unit;
                break;
            }
        }
        return leadUnit;
    }
    
    /**
     * Gets the attachment personnels. Cannot return {@code null}.
     * @return the attachment personnels
     */
    public List<ProtocolAttachmentPersonnel> getAttachmentPersonnels() {
        if (this.attachmentPersonnels == null) {
            this.attachmentPersonnels = new ArrayList<ProtocolAttachmentPersonnel>();
        }
        
        return this.attachmentPersonnels;
    }
    
    public void setAttachmentPersonnels(List<ProtocolAttachmentPersonnel> attachmentPersonnels) {
        this.attachmentPersonnels = attachmentPersonnels;
    }

    /** 
     * {@inheritDoc} 
     * inits Protocol Units. 
     */
    @Override
    public void postInitHook(Protocol protocol) {
        for (ProtocolUnit unit : this.protocolUnits) {
            unit.init(this);
        }
    }
    
    /** {@inheritDoc}  */
    public void resetPersistenceState() {
        this.setProtocolPersonId(null);
    }

    /**  {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.affiliationTypeCode == null) ? 0 : this.affiliationTypeCode.hashCode());
        result = prime * result + (this.delete ? 1231 : 1237);
        result = prime * result + ((this.personId == null) ? 0 : this.personId.hashCode());
        result = prime * result + ((this.personName == null) ? 0 : this.personName.hashCode());
        result = prime * result + ((this.previousPersonRoleId == null) ? 0 : this.previousPersonRoleId.hashCode());
        result = prime * result + ((this.protocolPersonId == null) ? 0 : this.protocolPersonId.hashCode());
        result = prime * result + ((this.protocolPersonRoleId == null) ? 0 : this.protocolPersonRoleId.hashCode());
        result = prime * result + ((this.protocolUnits == null) ? 0 : this.protocolUnits.hashCode());
        result = prime * result + ((this.rolodexId == null) ? 0 : this.rolodexId.hashCode());
        result = prime * result + this.selectedUnit;
        result = prime * result + (this.trained ? 1231 : 1237);
        return result;
    }

    /**  {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProtocolPerson other = (ProtocolPerson) obj;
        if (this.affiliationTypeCode == null) {
            if (other.affiliationTypeCode != null) {
                return false;
            }
        } else if (!this.affiliationTypeCode.equals(other.affiliationTypeCode)) {
            return false;
        }
        if (this.delete != other.delete) {
            return false;
        }
        if (this.personId == null) {
            if (other.personId != null) {
                return false;
            }
        } else if (!this.personId.equals(other.personId)) {
            return false;
        }
        if (this.personName == null) {
            if (other.personName != null) {
                return false;
            }
        } else if (!this.personName.equals(other.personName)) {
            return false;
        }
        if (this.previousPersonRoleId == null) {
            if (other.previousPersonRoleId != null) {
                return false;
            }
        } else if (!this.previousPersonRoleId.equals(other.previousPersonRoleId)) {
            return false;
        }
        if (this.protocolPersonId == null) {
            if (other.protocolPersonId != null) {
                return false;
            }
        } else if (!this.protocolPersonId.equals(other.protocolPersonId)) {
            return false;
        }
        if (this.protocolPersonRoleId == null) {
            if (other.protocolPersonRoleId != null) {
                return false;
            }
        } else if (!this.protocolPersonRoleId.equals(other.protocolPersonRoleId)) {
            return false;
        }
        if (this.protocolUnits == null) {
            if (other.protocolUnits != null) {
                return false;
            }
        } else if (!this.protocolUnits.equals(other.protocolUnits)) {
            return false;
        }
        if (this.rolodexId == null) {
            if (other.rolodexId != null) {
                return false;
            }
        } else if (!this.rolodexId.equals(other.rolodexId)) {
            return false;
        }
        if (this.selectedUnit != other.selectedUnit) {
            return false;
        }
        if (this.trained != other.trained) {
            return false;
        }
        return true;
    }
}