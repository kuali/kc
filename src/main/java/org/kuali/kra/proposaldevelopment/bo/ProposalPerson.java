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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.Person;

import org.kuali.core.util.KualiDecimal;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.core.bo.PersistableBusinessObject
 * @author $Author: lprzybyl $
 * @version $Revision: 1.6 $
 */
public class ProposalPerson extends Person {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPerson.class);

    private Boolean conflictOfInterest;
    private KualiDecimal percentageEffort;
    private Boolean fedrDebr;
    private Boolean fedrDelq;
    private Integer rolodexId;
    private Integer proposalNumber;
    private Integer proposalPersonNumber;
    private String  proposalPersonRoleId;
    private ProposalInvestigatorCertification certification;
    private ProposalPersonRole role;
    private List<ProposalPersonUnit> units;
    private List<ProposalPersonDegree> degrees;
    private List<ProposalPersonCreditSplit> creditSplits;
    private boolean delete;

    /**
     *
     * new ProposalPerson
     */
    public ProposalPerson() {
        degrees = new ArrayList<ProposalPersonDegree>();
        units = new ArrayList<ProposalPersonUnit>();
        creditSplits = new ArrayList<ProposalPersonCreditSplit>();
    }


    /**
     * Set a <code>{@link List}</code> of credit splits
     *
     * @param creditSplits
     */
    public void setCreditSplits(List<ProposalPersonCreditSplit> creditSplit) {
        this.creditSplits = creditSplit;
    }
    
    /**
     * Get a <code>{@link List}</code> of credit splits
     *
     * @return List<ProposalPersonCreditSplit>
     */ 
   public List<ProposalPersonCreditSplit> getCreditSplits() {
        return this.creditSplits;
    }

    /**
     * Gets the value of certification
     *
     * @return the value of certification
     */
    public ProposalInvestigatorCertification getCertification() {
        return this.certification;
    }

    /**
     * Sets the value of certification
     *
     * @param argCertification Value to assign to this.certification
     */
    public void setCertification(ProposalInvestigatorCertification argCertification) {
        this.certification = argCertification;
    }

    /**
     * Gets the value of units
     *
     * @return the value of units
     */
    public List<ProposalPersonUnit> getUnits() {
        return this.units;
    }

    /**
     * Sets the value of units
     *
     * @param argUnits Value to assign to this.units
     */
    public void setUnits(List<ProposalPersonUnit> argUnits) {
        this.units = argUnits;
    }

    /**
     * Gets the value of degrees
     *
     * @return the value of degrees
     */
    public List<ProposalPersonDegree> getDegrees() {
        return this.degrees;
    }

    /**
     * Sets the value of degrees
     *
     * @param argDegrees Value to assign to this.degrees
     */
    public void setDegrees(List<ProposalPersonDegree> argDegrees) {
        this.degrees = argDegrees;
    }

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    /**
     * Gets the value of conflictOfInterest
     *
     * @return the value of conflictOfInterest
     */
    public Boolean isConflictOfInterest() {
        return this.conflictOfInterest;
    }

    /**
     * Sets the value of conflictOfInterest
     *
     * @param argConflictOfInterest Value to assign to this.conflictOfInterest
     */
    public void setConflictOfInterest(Boolean argConflictOfInterest) {
        this.conflictOfInterest = argConflictOfInterest;
    }

    /**
     * Gets the value of percentageEffort
     *
     * @return the value of percentageEffort
     */
    public KualiDecimal getPercentageEffort() {
        return this.percentageEffort;
    }

    /**
     * Sets the value of percentageEffort
     *
     * @param argPercentageEffort Value to assign to this.percentageEffort
     */
    public void setPercentageEffort(KualiDecimal argPercentageEffort) {
        this.percentageEffort = argPercentageEffort;
    }

    /**
     * Gets the value of fedrDebr
     *
     * @return the value of fedrDebr
     */
    public Boolean isFedrDebr() {
        return this.fedrDebr;
    }

    /**
     * Sets the value of fedrDebr
     *
     * @param argFedrDebr Value to assign to this.fedrDebr
     */
    public void setFedrDebr(Boolean argFedrDebr) {
        this.fedrDebr = argFedrDebr;
    }

    /**
     * Gets the value of fedrDelq
     *
     * @return the value of fedrDelq
     */
    public Boolean isFedrDelq() {
        return this.fedrDelq;
    }

    /**
     * Sets the value of fedrDelq
     *
     * @param argFedrDelq Value to assign to this.fedrDelq
     */
    public void setFedrDelq(Boolean argFedrDelq) {
        this.fedrDelq = argFedrDelq;
    }

    /**
     * Gets the value of rolodexId
     *
     * @return the value of rolodexId
     */
    public Integer getRolodexId() {
        return this.rolodexId;
    }

    /**
     * Sets the value of rolodexId
     *
     * @param argRolodexId Value to assign to this.rolodexId
     */
    public void setRolodexId(Integer argRolodexId) {
        this.rolodexId = argRolodexId;
    }

    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public Integer getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public void setProposalNumber(Integer argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of propPersonRoleId
     *
     * @return the value of propPersonRoleId
     */
    public String getProposalPersonRoleId() {
        return this.proposalPersonRoleId;
    }

    /** 
     * Sets the value of propPersonRoleId
     *
     * @param argPropPersonRoleId Value to assign to this.propPersonRoleId
     */
    public void setProposalPersonRoleId(String argPropPersonRoleId) {
        this.proposalPersonRoleId = argPropPersonRoleId;
    }

    /**
     * Gets the value of propPersonRole
     *
     * @return the value of propPersonRole
     */
    public ProposalPersonRole getRole() {
        return role;
    }

    /** 
     * Sets the value of propPersonRole
     *
     * @param argPropPersonRole Value to assign to this.propPersonRole
     */
    public void setRole(ProposalPersonRole argPropPersonRole) {
        this.role = argPropPersonRole;
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

    /**
     * Adds a new degree to the collection in the person
     *
     * @param d degree to add
     */
    public void addDegree(ProposalPersonDegree d) {
        getDegrees().add(d);
    }

    public ProposalPersonDegree getDegree(int index) {
        while (getDegrees().size() <= index) {
            getDegrees().add(new ProposalPersonDegree());
        }
        
        return getDegrees().get(index);
    }

    /**
     * Adds a new unit to the collection in the person
     *
     * @param unit to add
     */
    public void addUnit(ProposalPersonUnit unit) {
        getUnits().add(unit);
    }

    public ProposalPersonUnit getUnit(int index) {
        while (getUnits().size() <= index) {
            getUnits().add(new ProposalPersonUnit());
        }
        
        return getUnits().get(index);
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
