/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.contacts;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class InstitutionalProposalPersonUnit extends KcPersistableBusinessObjectBase implements Comparable<InstitutionalProposalPersonUnit>, SequenceAssociate<InstitutionalProposal> {


    private static final long serialVersionUID = 8863551989073626234L;

    private Long institutionalProposalPersonUnitId;

    private InstitutionalProposalPerson institutionalProposalPerson;

    private boolean leadUnit;

    private Unit unit;

    private List<InstitutionalProposalPersonUnitCreditSplit> creditSplits;

    // OJB Hack  
    private String unitNumber;

    private Long institutionalProposalContactId;

    public InstitutionalProposalPersonUnit() {
        super();
        creditSplits = new ArrayList<InstitutionalProposalPersonUnitCreditSplit>();
    }

    /**
     * Constructs a institutionalProposalPersonUnit.
     * @param institutionalProposalPerson
     */
    InstitutionalProposalPersonUnit(InstitutionalProposalPerson institutionalProposalPerson) {
        this();
        setInstitutionalProposalPerson(institutionalProposalPerson);
    }

    /**
     * Constructs a institutionalProposalPersonUnit.
     * @param institutionalProposalPerson
     * @param unit
     * @param isLeadUnit
     */
    InstitutionalProposalPersonUnit(InstitutionalProposalPerson institutionalProposalPerson, Unit unit, boolean isLeadUnit) {
        this();
        this.institutionalProposalPerson = institutionalProposalPerson;
        setUnit(unit);
        leadUnit = isLeadUnit;
    }

    /**
     * Find the lead unit from among institutionalProposal institutionalProposalPerson units.
     * @param institutionalProposalPersonUnits
     * @return
     */
    public static Unit findLeadUnit(Collection<InstitutionalProposalPersonUnit> institutionalProposalPersonUnits) {
        Unit foundLeadUnit = null;
        for (InstitutionalProposalPersonUnit apu : institutionalProposalPersonUnits) {
            if (apu.isLeadUnit()) {
                foundLeadUnit = apu.getUnit();
            }
        }
        return foundLeadUnit;
    }

    public List<UnitAdministrator> getOspAdministrators() {
        List<UnitAdministrator> ospAdministrators = new ArrayList<UnitAdministrator>();
        for (UnitAdministrator unitAdministrator : getUnit().getUnitAdministrators()) {
            if (unitAdministrator.getUnitAdministratorType().getDescription().equals("OSP_ADMINISTRATOR")) {
                ospAdministrators.add(unitAdministrator);
            }
        }
        return ospAdministrators;
    }

    /**
     * @param creditSplit
     */
    public void add(InstitutionalProposalPersonUnitCreditSplit creditSplit) {
        creditSplits.add(creditSplit);
        creditSplit.setInstitutionalProposalPersonUnit(this);
    }

    /**
     * This method will initialize required credit splits and populate them with 
     * default credits of 100%.
     */
    @SuppressWarnings("unchecked")
    public void initializeDefaultCreditSplits() {
        List<InvestigatorCreditType> creditTypes = (List<InvestigatorCreditType>) this.getBusinessObjectService().findAll(InvestigatorCreditType.class);
        for (InvestigatorCreditType creditType : creditTypes) {
            InstitutionalProposalPersonUnitCreditSplit creditSplit = new InstitutionalProposalPersonUnitCreditSplit(creditType, new ScaleTwoDecimal(0));
            this.add(creditSplit);
        }
    }

    @Override
    public int compareTo(InstitutionalProposalPersonUnit other) {
        return this.unit.getUnitName().compareToIgnoreCase(other.getUnit().getUnitName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof InstitutionalProposalPersonUnit)) {
            return false;
        }
        InstitutionalProposalPersonUnit other = (InstitutionalProposalPersonUnit) obj;
        if (institutionalProposalPerson == null) {
            if (other.institutionalProposalPerson != null) {
                return false;
            }
        } else if (!institutionalProposalPerson.equals(other.institutionalProposalPerson)) {
            return false;
        }
        if (unit == null) {
            if (other.unit != null) {
                return false;
            }
        } else if (!unit.equals(other.unit)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the institutionalProposalPersonId attribute. 
     * @return Returns the institutionalProposalPersonId.
     */
    public Long getInstitutionalProposalContactId() {
        return institutionalProposalContactId;
    }


    public InstitutionalProposalContact getInstitutionalProposalPerson() {
        return institutionalProposalPerson;
    }

    /**
     * Gets the institutionalProposalPersonUnitId attribute. 
     * @return Returns theinstitutionalProposalPersonUnitId.
     */
    public Long getInstitutionalProposalPersonUnitId() {
        return institutionalProposalPersonUnitId;
    }

    /**
     * @param index
     * @return
     */
    public InstitutionalProposalPersonUnitCreditSplit getCreditSplit(int index) {
        return creditSplits.get(index);
    }

    /**
     * Gets the creditSplits attribute. 
     * @return Returns the creditSplits.
     */
    public List<InstitutionalProposalPersonUnitCreditSplit> getCreditSplits() {
        return creditSplits;
    }


    public String getFullName() {
        return institutionalProposalPerson != null ? (institutionalProposalPerson.getContact() != null ? institutionalProposalPerson.getContact().getFullName() : null) : null;
    }


    public Unit getUnit() {
        lazilyLoadUnit();
        return unit;
    }


    public String getUnitName() {
        return unit != null ? unit.getUnitName() : null;
    }


    public String getUnitNumber() {
        return unitNumber;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((institutionalProposalPerson == null) ? 0 : institutionalProposalPerson.hashCode());
        result = PRIME * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }


    public boolean isLeadUnit() {
        return leadUnit;
    }

    /**
     * Sets the institutionalProposalPersonId attribute value.
     * @param institutionalProposalPersonId The institutionalProposalPersonId to set.
     */
    public void setInstitutionalProposalContactId(Long institutionalProposalPersonId) {
        this.institutionalProposalContactId = institutionalProposalPersonId;
    }

    /**
     * @param institutionalProposalPerson
     */
    public void setInstitutionalProposalPerson(InstitutionalProposalPerson institutionalProposalPerson) {
        this.institutionalProposalPerson = institutionalProposalPerson;
        this.institutionalProposalContactId = institutionalProposalPerson != null ? institutionalProposalPerson.getInstitutionalProposalContactId() : null;
    }

    /**
     * Sets the institutionalProposalPersonUnitId attribute value.
     * @param institutionalProposalPersonUnitId The institutionalProposalPersonUnitId to set.
     */
    public void setInstitutionalProposalPersonUnitId(Long institutionalProposalPersonUnitId) {
        this.institutionalProposalPersonUnitId = institutionalProposalPersonUnitId;
    }

    /**
     * Sets the creditSplits attribute value.
     * @param creditSplits The creditSplits to set.
     */
    public void setCreditSplits(List<InstitutionalProposalPersonUnitCreditSplit> creditSplits) {
        this.creditSplits = creditSplits;
    }

    /**
     * @param leadUnit
     */
    public void setLeadUnit(boolean leadUnit) {
        this.leadUnit = leadUnit;
    }

    /**
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
        this.unitNumber = unit != null ? unit.getUnitNumber() : null;
    }

    /**
     * Sets the unitNumber attribute value.
     * @param unitNumber The unitNumber to set.
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }


    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    @SuppressWarnings("unchecked")
    private void lazilyLoadUnit() {
        if (StringUtils.isNotEmpty(unitNumber) && unit == null) {
            Collection c = getBusinessObjectService().findMatching(Unit.class, Collections.singletonMap("unitNumber", unitNumber));
            if (c.size() > 0) {
                unit = (Unit) c.iterator().next();
            }
        }
    }

    public InstitutionalProposal getSequenceOwner() {
        return getInstitutionalProposalPerson() != null ? getInstitutionalProposalPerson().getInstitutionalProposal() : null;
    }

    public void setSequenceOwner(InstitutionalProposal newlyVersionedOwner) {
        if (getInstitutionalProposalPerson() != null) {
            getInstitutionalProposalPerson().setInstitutionalProposal(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return getInstitutionalProposalPerson() != null ? getInstitutionalProposalPerson().getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        this.institutionalProposalPersonUnitId = null;
    }
}
