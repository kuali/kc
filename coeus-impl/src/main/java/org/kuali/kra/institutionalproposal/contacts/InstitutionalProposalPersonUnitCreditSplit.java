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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;


public class InstitutionalProposalPersonUnitCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit, SequenceAssociate<InstitutionalProposal> {


    private static final long serialVersionUID = -8804359877270428419L;

    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "code";

    private Long institutionalProposalPersonUnitCreditSplitId;

    private InstitutionalProposalPersonUnit institutionalProposalPersonUnit;

    private ScaleTwoDecimal credit = new ScaleTwoDecimal(0);

    private InvestigatorCreditType investigatorCreditType;

    // OJB Hacks  
    private Long institutionalProposalPersonUnitId;

    private String invCreditTypeCode;


    public InstitutionalProposalPersonUnitCreditSplit() {
    }

    InstitutionalProposalPersonUnitCreditSplit(InvestigatorCreditType investigatorCreditType, ScaleTwoDecimal credit) {
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
        this.invCreditTypeCode = investigatorCreditType != null ? investigatorCreditType.getCode() : null;
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
    public ScaleTwoDecimal getCredit() {
        return this.credit;
    }

    /**
     * Sets the value of credit
     *
     * @param argCredit Value to assign to this.credit
     */
    public void setCredit(ScaleTwoDecimal credit) {
        this.credit = credit != null ? credit : new ScaleTwoDecimal(0);
    }


    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * This method lazy-loads the InvestigatorCreditType
     */
    private void refreshInvestigatorCreditTypeIfNeeded() {
        if (invCreditTypeCode != null && (investigatorCreditType == null || !invCreditTypeCode.equals(investigatorCreditType.getCode()))) {
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
