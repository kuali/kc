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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class InstitutionalProposalPersonUnitCreditSplit extends KraPersistableBusinessObjectBase implements CreditSplit, SequenceAssociate<InstitutionalProposal> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8804359877270428419L;

    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "invCreditTypeCode";

    private Long institutionalProposalPersonUnitCreditSplitId;

    private InstitutionalProposalPersonUnit institutionalProposalPersonUnit;

    private KualiDecimal credit = new KualiDecimal(0);

    private InvestigatorCreditType investigatorCreditType;

    // OJB Hacks  
    private Long institutionalProposalPersonUnitId;

    private String invCreditTypeCode;

    /**
     * Default Constructor
     */
    public InstitutionalProposalPersonUnitCreditSplit() {
    }

    /**
     * Convenience Constructor
     * @param investigatorCreditType
     * @param credit
     */
    InstitutionalProposalPersonUnitCreditSplit(InvestigatorCreditType investigatorCreditType, KualiDecimal credit) {
        setInvestigatorCreditType(investigatorCreditType);
        setCredit(credit);
    }

    /**
     * Gets the institutionalProposalPersonUnitCreditSplitId attribute. 
     * @return Returns the institutionalProposalPersonUnitCreditSplitId.
     */
    public Long getInstitutionalProposalPersonUnitCreditSplitId() {
        return institutionalProposalPersonUnitCreditSplitId;
    }

    /**
     * Gets the institutionalProposalPersonUnit attribute. 
     * @return Returns the institutionalProposalPersonUnit.
     */
    public InstitutionalProposalPersonUnit getInstitutionalProposalPersonUnit() {
        return institutionalProposalPersonUnit;
    }

    /**
     * Gets the investigatorCreditType attribute. 
     * @return Returns the investigatorCreditType.
     */
    public InvestigatorCreditType getInvestigatorCreditType() {
        refreshInvestigatorCreditTypeIfNeeded();
        return investigatorCreditType;
    }

    /**
     * Gets the institutionalProposalPersonUnitId attribute. 
     * @return Returns the institutionalProposalPersonUnitId.
     */
    public Long getInstitutionalProposalPersonUnitId() {
        return institutionalProposalPersonUnitId;
    }

    /**
     * Gets the invCreditTypeCode attribute. 
     * @return Returns the invCreditTypeCode.
     */
    public String getInvCreditTypeCode() {
        refreshInvestigatorCreditTypeIfNeeded();
        return invCreditTypeCode;
    }

    /**
     * Sets the institutionalProposalPersonUnitCreditSplitId attribute value.
     * @param institutionalProposalPersonUnitCreditSplitId The institutionalProposalPersonUnitCreditSplitId to set.
     */
    public void setInstitutionalProposalPersonUnitCreditSplitId(Long institutionalProposalPersonUnitCreditSplitId) {
        this.institutionalProposalPersonUnitCreditSplitId = institutionalProposalPersonUnitCreditSplitId;
    }

    /**
     * Sets the institutionalProposalPersonUnit attribute value.
     * @param institutionalProposalPersonUnit The institutionalProposalPersonUnit to set.
     */
    public void setInstitutionalProposalPersonUnit(InstitutionalProposalPersonUnit institutionalProposalPersonUnit) {
        this.institutionalProposalPersonUnit = institutionalProposalPersonUnit;
        this.institutionalProposalPersonUnitId = institutionalProposalPersonUnit != null ? institutionalProposalPersonUnit.getInstitutionalProposalPersonUnitId() : null;
    }

    /**
     * Sets the investigatorCreditType attribute value.
     * @param investigatorCreditType The investigatorCreditType to set.
     */
    public void setInvestigatorCreditType(InvestigatorCreditType investigatorCreditType) {
        this.investigatorCreditType = investigatorCreditType;
        this.invCreditTypeCode = investigatorCreditType != null ? investigatorCreditType.getInvCreditTypeCode() : null;
    }

    /**
     * Sets the institutionalProposalPersonUnitId attribute value.
     * @param institutionalProposalPersonUnitId The institutionalProposalPersonUnitId to set.
     */
    public void setInstitutionalProposalPersonUnitId(Long institutionalProposalPersonUnitId) {
        this.institutionalProposalPersonUnitId = institutionalProposalPersonUnitId;
    }

    /**
     * Sets the invCreditTypeCode attribute value.
     * @param invCreditTypeCode The invCreditTypeCode to set.
     */
    public void setInvCreditTypeCode(String invCreditTypeCode) {
        this.invCreditTypeCode = invCreditTypeCode;
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
    public void setCredit(KualiDecimal credit) {
        this.credit = credit != null ? credit : new KualiDecimal(0);
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * This method lazy-loads the InvestigatorCreditType
     */
    private void refreshInvestigatorCreditTypeIfNeeded() {
        if (invCreditTypeCode != null && (investigatorCreditType == null || !invCreditTypeCode.equals(investigatorCreditType.getInvCreditTypeCode()))) {
            Map<String, Object> keyMap = new HashMap<String, Object>();
            keyMap.put(INV_CREDIT_TYPE_CODE_FIELD_NAME, invCreditTypeCode);
            investigatorCreditType = (InvestigatorCreditType) getBusinessObjectService().findByPrimaryKey(InvestigatorCreditType.class, keyMap);
        }
    }

    public InstitutionalProposal getSequenceOwner() {
        return getInstitutionalProposalPersonUnit() != null ? getInstitutionalProposalPersonUnit().getInstitutionalProposalPerson().getInstitutionalProposal() : null;
    }

    public void setSequenceOwner(InstitutionalProposal newlyVersionedOwner) {
        if (getInstitutionalProposalPersonUnit() != null) {
            getInstitutionalProposalPersonUnit().getInstitutionalProposalPerson().setInstitutionalProposal(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return getInstitutionalProposalPersonUnit() != null ? getInstitutionalProposalPersonUnit().getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        this.institutionalProposalPersonUnitCreditSplitId = null;
    }
}
