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
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class InstitutionalProposalUnit extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer proposalUnitsId;

    private String proposalNumber;

    private Integer sequenceNumber;

    private String personId;

    private String unitNumber;

    private boolean leadUnitFlag;

    private Unit unit;

    public InstitutionalProposalUnit() {
    }

    public boolean isLeadUnit() {
        return leadUnitFlag;
    }

    public Integer getProposalUnitsId() {
        return proposalUnitsId;
    }

    public void setProposalUnitsId(Integer proposalUnitsId) {
        this.proposalUnitsId = proposalUnitsId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
