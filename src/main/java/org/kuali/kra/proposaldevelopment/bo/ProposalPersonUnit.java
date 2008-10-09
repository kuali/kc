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
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

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
 * @author $Author: gmcgrego $
 * @version $Revision: 1.14 $
 */
@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalPersonUnitId.class)
@Entity
@Table(name="EPS_PROP_PERSON_UNITS")
public class ProposalPersonUnit extends KraPersistableBusinessObjectBase implements CreditSplitable {
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Id
	@Column(name="PROP_PERSON_NUMBER")
	private Integer proposalPersonNumber;
    
    @Id
	@Column(name="UNIT_NUMBER")
	private String unitNumber;
    
    @Column(name="LEAD_UNIT_FLAG")
	private boolean leadUnit;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="UNIT_NUMBER", insertable=false, updatable=false)
	private Unit unit;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit.class, mappedBy="proposalPersonUnit")
	private List<ProposalUnitCreditSplit> creditSplits;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="PROP_PERSON_NUMBER", insertable=false, updatable=false)})
    private ProposalPerson proposalPerson;
    
    @Transient
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
    public final String getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(String argProposalNumber) {
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

    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }

    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }
}



