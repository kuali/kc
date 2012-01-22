/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.util.List;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.KcPersonService;

public class DisclosurePerson extends DisclosureReporter implements SequenceAssociate<CoiDisclosure> {
    /**
     * TODO : not sure about this table. 1. should we combine this with coi reporter/correspondent, 2. personRoleId do we need it
     **/

    private Long disclosurePersonId;
    private Long coiDisclosureId;
    private String personId;
    // private String personName;
    private String personRoleId;
    private List<DisclosurePersonUnit> disclosurePersonUnits;
    private CoiDisclosure coiDisclosure;

    public Long getDisclosurePersonId() {
        return disclosurePersonId;
    }

    public void setDisclosurePersonId(Long disclosurePersonId) {
        this.disclosurePersonId = disclosurePersonId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(String personRoleId) {
        this.personRoleId = personRoleId;
    }

    public List<DisclosurePersonUnit> getDisclosurePersonUnits() {
        return disclosurePersonUnits;
    }

    public void setDisclosurePersonUnits(List<DisclosurePersonUnit> disclosurePersonUnits) {
        this.disclosurePersonUnits = disclosurePersonUnits;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }


    @Override
    public List<? extends DisclosureReporterUnit> getDisclosureReporterUnits() {
        // TODO Auto-generated method stub
        return getDisclosurePersonUnits();
    }

    @Override
    public Integer getSequenceNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void resetPersistenceState() {
        this.setDisclosurePersonId(null);
                
    }

    @Override
    public void setSequenceOwner(CoiDisclosure newlyVersionedOwner) {
        this.setCoiDisclosure(newlyVersionedOwner);   
                
    }

    @Override
    public CoiDisclosure getSequenceOwner() {
        // TODO Auto-generated method stub
        return this.getCoiDisclosure();
    }


}
