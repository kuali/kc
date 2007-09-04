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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Represents the Proposal Person Unit <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public class ProposalPersonUnit extends KraPersistableBusinessObjectBase {
    private Integer proposalNumber;
    private Integer propPersonNumber;
    private Integer unitNumber;
    private Boolean leadUnit;


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
        return this.propPersonNumber;
    }

    /**
     * Sets the value of propPersonNumber
     *
     * @param argPropPersonNumber Value to assign to this.propPersonNumber
     */
    public final void setProposalPersonNumber(Integer argPropPersonNumber) {
        this.propPersonNumber = argPropPersonNumber;
    }

    /**
     * Gets the value of unitNumber
     *
     * @return the value of unitNumber
     */
    public final Integer getUnitNumber() {
        return this.unitNumber;
    }

    /**
     * Sets the value of unitNumber
     *
     * @param argUnitNumber Value to assign to this.unitNumber
     */
    public final void setUnitNumber(Integer argUnitNumber) {
        this.unitNumber = argUnitNumber;
    }

    /**
     * Gets the value of leadUnit
     *
     * @return the value of leadUnit
     */
    public final Boolean isLeadUnit() {
        return this.leadUnit;
    }

    /**
     * Sets the value of leadUnit
     *
     * @param argLeadUnit Value to assign to this.leadUnit
     */
    public final void setLeadUnit(Boolean argLeadUnit) {
        this.leadUnit = argLeadUnit;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("unitNumber", getUnitNumber());
        propMap.put("leadUnit", isLeadUnit());
        return propMap;
    }

}


