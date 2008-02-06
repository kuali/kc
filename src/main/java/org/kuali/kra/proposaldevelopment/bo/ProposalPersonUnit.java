/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Represents the Proposal Person Unit <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: lprzybyl $
 * @version $Revision: 1.12 $
 */
public class ProposalPersonUnit extends KraPersistableBusinessObjectBase implements CreditSplitable {
    private Integer proposalNumber;
    private Integer proposalPersonNumber;
    private String unitNumber;
    private boolean leadUnit;
    private Unit unit;
    private List<ProposalUnitCreditSplit> creditSplits;
    private boolean delete;

    /**
     * Default constructor
     */
    public ProposalPersonUnit() {
        creditSplits = new ArrayList<ProposalUnitCreditSplit>();
    }

    /**
     * Gets the value of creditSplits
     *
     * @return the value of creditSplits
     */
    public List<ProposalUnitCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    /**
     * Sets the value of creditSplits
     *
     * @param argCreditSplits Value to assign to this.creditSplits
     */
    public void setCreditSplits(List<ProposalUnitCreditSplit> argCreditSplits) {
        this.creditSplits = argCreditSplits;
    }
    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public final Integer getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(Integer argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of propPersonNumber
     *
     * @return the value of propPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of propPersonNumber
     *
     * @param argPropPersonNumber Value to assign to this.propPersonNumber
     */
    public final void setProposalPersonNumber(Integer argPropPersonNumber) {
        this.proposalPersonNumber = argPropPersonNumber;
    }

    /**
     * Gets the value of unitNumber
     *
     * @return the value of unitNumber
     */
    public final String getUnitNumber() {
        return this.unitNumber;
    }

    /**
     * Sets the value of unitNumber
     *
     * @param argUnitNumber Value to assign to this.unitNumber
     */
    public final void setUnitNumber(String argUnitNumber) {
        this.unitNumber = argUnitNumber;
        
        if (isBlank(unitNumber)) {
            unit = null;
        }
        else {
            refreshReferenceObject("unit");
        }
    }

    /**
     * Gets the value of leadUnit
     *
     * @return the value of leadUnit
     */
    public final boolean isLeadUnit() {
        return this.leadUnit;
    }

    /**
     * Sets the value of leadUnit
     *
     * @param argLeadUnit Value to assign to this.leadUnit
     */
    public final void setLeadUnit(boolean argLeadUnit) {
        this.leadUnit = argLeadUnit;
    }
    
    /**
     * Gets a unit that this object refers to
     *
     * @return Unit
     */
    public final Unit getUnit() {
        return unit;
    }
    
    /**
     * Assigns a reference to a <code>{@link Unit}</code> instance
     *
     * @param unit to refer to
     */
    public final void setUnit(Unit u) {
        unit = u;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("unitNumber", getUnitNumber());
        propMap.put("leadUnit", isLeadUnit());
        propMap.put("unit", getUnit());
        return propMap;
    }

    /**
     * Gets index i from the creditSplits list.
     * 
     * @param index
     * @return Question at index i
     */
    public ProposalUnitCreditSplit getCreditSplit(int index) {
        while (getCreditSplits().size() <= index) {
            getCreditSplits().add(new ProposalUnitCreditSplit());
        }
        return (ProposalUnitCreditSplit) getCreditSplits().get(index);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.bo.CreditSplitable#getName()
     */
    public String getName() {
        return getUnitNumber();
    }

    /**
     * Flag to tag for deletion
     * 
     * @return true or false
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * Flag to tag for deletion
     * 
     * @param delete
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}


