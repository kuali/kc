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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.DisclosureReporterUnit;

public class DisclosurePersonUnit  extends DisclosureReporterUnit implements SequenceAssociate<CoiDisclosure> {

    private Long disclosurePersonUnitsId; 
    private Long disclosurePersonId;
    private String unitNumber; 
    private boolean leadUnitFlag; 
    private String personId; 
    
//    @SkipVersioning
    private DisclosurePerson disclosurePerson;

    public Long getDisclosurePersonUnitsId() {
        return disclosurePersonUnitsId;
    }
    
    public void setDisclosurePersonUnitsId(Long disclosurePersonUnitsId) {
        this.disclosurePersonUnitsId = disclosurePersonUnitsId;
    }
    
    public Long getDisclosurePersonId() {
        return disclosurePersonId;
    }
    
    public void setDisclosurePersonId(Long disclosurePersonId) {
        this.disclosurePersonId = disclosurePersonId;
    }
    
    public String getUnitNumber() {
        return unitNumber;
    }
    
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    public boolean isLeadUnitFlag() {
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
    
    public DisclosurePerson getDisclosurePerson() {
        return disclosurePerson;
    }
    
    public void setDisclosurePerson(DisclosurePerson disclosurePerson) {
        this.disclosurePerson = disclosurePerson;
    }
    
    @Override
    public Long getReporterUnitId() {

        return getDisclosurePersonUnitsId();
    }
    
    @Override
    public Integer getSequenceNumber() {

        return null;
    }
    
    @Override
    public void resetPersistenceState() {
        this.setDisclosurePersonUnitsId(null);
                
    }
    
    @Override
    public void setSequenceOwner(CoiDisclosure newlyVersionedOwner) {
        this.getDisclosurePerson().setCoiDisclosure(newlyVersionedOwner);   
                
    }
    
    @Override
    public CoiDisclosure getSequenceOwner() {

        return this.getDisclosurePerson().getCoiDisclosure();
    }



}
