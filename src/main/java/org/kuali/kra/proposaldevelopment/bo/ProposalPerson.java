/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import org.kuali.kra.bo.Person;

import org.kuali.core.util.KualiDecimal;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * $Id: ProposalPerson.java,v 1.1 2007-09-03 21:55:00 lprzybyl Exp $
 */
public class ProposalPerson extends Person {
    private Boolean conflictOfInterest;
    private KualiDecimal percentageEffort;
    private Boolean fedrDebr;
    private Boolean fedrDelq;
    private Integer rolodexId;
    private Integer proposalNumber;
    private Integer proposalPersonNumber;
    private String  propPersonRoleId;


    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public final void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    /**
     * Gets the value of conflictOfInterest
     *
     * @return the value of conflictOfInterest
     */
    public final Boolean isConflictOfInterest() {
        return this.conflictOfInterest;
    }

    /**
     * Sets the value of conflictOfInterest
     *
     * @param argConflictOfInterest Value to assign to this.conflictOfInterest
     */
    public final void setConflictOfInterest(Boolean argConflictOfInterest) {
        this.conflictOfInterest = argConflictOfInterest;
    }

    /**
     * Gets the value of percentageEffort
     *
     * @return the value of percentageEffort
     */
    public final KualiDecimal getPercentageEffort() {
        return this.percentageEffort;
    }

    /**
     * Sets the value of percentageEffort
     *
     * @param argPercentageEffort Value to assign to this.percentageEffort
     */
    public final void setPercentageEffort(KualiDecimal argPercentageEffort) {
        this.percentageEffort = argPercentageEffort;
    }

    /**
     * Gets the value of fedrDebr
     *
     * @return the value of fedrDebr
     */
    public final Boolean isFedrDebr() {
        return this.fedrDebr;
    }

    /**
     * Sets the value of fedrDebr
     *
     * @param argFedrDebr Value to assign to this.fedrDebr
     */
    public final void setFedrDebr(Boolean argFedrDebr) {
        this.fedrDebr = argFedrDebr;
    }

    /**
     * Gets the value of fedrDelq
     *
     * @return the value of fedrDelq
     */
    public final Boolean isFedrDelq() {
        return this.fedrDelq;
    }

    /**
     * Sets the value of fedrDelq
     *
     * @param argFedrDelq Value to assign to this.fedrDelq
     */
    public final void setFedrDelq(Boolean argFedrDelq) {
        this.fedrDelq = argFedrDelq;
    }

    /**
     * Gets the value of rolodexId
     *
     * @return the value of rolodexId
     */
    public final Integer getRolodexId() {
        return this.rolodexId;
    }

    /**
     * Sets the value of rolodexId
     *
     * @param argRolodexId Value to assign to this.rolodexId
     */
    public final void setRolodexId(Integer argRolodexId) {
        this.rolodexId = argRolodexId;
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
     * Gets the value of propPersonRoleId
     *
     * @return the value of propPersonRoleId
     */
    public final String getProposalPersonRoleId() {
        return this.propPersonRoleId;
    }

    /** 
     * Sets the value of propPersonRoleId
     *
     * @param argPropPersonRoleId Value to assign to this.propPersonRoleId
     */
    public final void setProposalPersonRoleId(String argPropPersonRoleId) {
        this.propPersonRoleId = argPropPersonRoleId;
    }

	@Override 
	protected LinkedHashMap toStringMapper() {
   	    LinkedHashMap hashmap = super.toStringMapper();

        hashmap.put("conflictOfInterest", isConflictOfInterest());
        hashmap.put("percentageEffort", getPercentageEffort());
        hashmap.put("fedrDebr", isFedrDebr());
        hashmap.put("fedrDelq", isFedrDelq());
        hashmap.put("rolodexId", getRolodexId());
        hashmap.put("proposalNumber", getProposalNumber());
        hashmap.put("proposalPersonNumber", getProposalPersonNumber());
        hashmap.put("proposalPersonRoleId", getProposalPersonRoleId());
        
		return hashmap;
	}

}
