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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import org.kuali.core.util.KualiDecimal;

/**
 * Class representation of the Proposal Person <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.6 $
 */
public final class ProposalPersonCreditSplit extends KraPersistableBusinessObjectBase implements CreditSplit {
    private Integer proposalNumber;
    private Integer proposalPersonNumber;
    private String invCreditTypeCode;
    private KualiDecimal credit;
    private InvestigatorCreditType investigatorCreditType;
    
    /**
     * Gets the value of invCreditType
     *
     * @return the value of invCreditType
     */
    public InvestigatorCreditType getInvestigatorCreditType() {
        return this.investigatorCreditType;
    }
        
    /**
     * Sets the value of invCreditType
     *
     * @param argInvCreditType Value to assign to this.invCreditType
     */
    public void setInvestigatorCreditType(InvestigatorCreditType argInvCreditType) {
        this.investigatorCreditType = argInvCreditType;
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
     * Gets the value of personProposalNumber
     *
     * @return the value of personProposalNumber
     */
    public Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of personProposalNumber
     *
     * @param argPersonProposalNumber Value to assign to this.personProposalNumber
     */
    public void setProposalPersonNumber(Integer argPersonProposalNumber) {
        this.proposalPersonNumber = argPersonProposalNumber;
    }

    /**
     * Gets the value of invCreditTypeCode
     *
     * @return the value of invCreditTypeCode
     */
    public String getInvCreditTypeCode() {
        return this.invCreditTypeCode;
    }

    /**
     * Sets the value of invCreditTypeCode
     *
     * @param argInvCreditTypeCode Value to assign to this.invCreditTypeCode
     */
    public void setInvCreditTypeCode(String argInvCreditTypeCode) {
        this.invCreditTypeCode = argInvCreditTypeCode;
    }

    /**
     * Gets the value of credit
     *
     * @return the value of credit
     */
    public KualiDecimal getCredit() {
        return this.credit;
    }

    /**
     * Sets the value of credit
     *
     * @param argCredit Value to assign to this.credit
     */
    public void setCredit(KualiDecimal argCredit) {
        this.credit = argCredit;
    }


	@Override 
	protected LinkedHashMap toStringMapper() {
   	    LinkedHashMap hashmap = new LinkedHashMap();    
        hashmap.put("proposalNumber", getProposalNumber());
        hashmap.put("proposalPersonNumber", getProposalPersonNumber());
        hashmap.put("invCreditTypeCode", getInvCreditTypeCode());
        hashmap.put("credit", getCredit());
        hashmap.put("invCreditType", getInvestigatorCreditType());
		return hashmap;
	}
}
