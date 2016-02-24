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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representation of the Award Person credit split
 */
public final class AwardPersonCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit, SequenceAssociate<Award> {

    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "code";

    private static final long serialVersionUID = -6999442247404810830L;

    private Long awardPersonCreditSplitId;

    @SkipVersioning
    private AwardPerson awardPerson;

    private ScaleTwoDecimal credit;

    private InvestigatorCreditType investigatorCreditType;

    // OJB Hacks 
    private String invCreditTypeCode;

    private Long awardContactId;


    public AwardPersonCreditSplit() {
    }

    AwardPersonCreditSplit(InvestigatorCreditType investigatorCreditType, ScaleTwoDecimal credit) {
        setInvestigatorCreditType(investigatorCreditType);
        setCredit(credit);
    }

    /**
     * Gets the awardContactId attribute. 
     * @return Returns the awardContactId.
     */
    public Long getAwardContactId() {
        return awardContactId;
    }

    /**
     * Gets the awardPerson attribute. 
     * @return Returns the awardPerson.
     */
    public AwardPerson getAwardPerson() {
        return awardPerson;
    }

    /**
     * Gets the awardPersonCreditSplitId attribute. 
     * @return Returns the awardPersonCreditSplitId.
     */
    public Long getAwardPersonCreditSplitId() {
        return awardPersonCreditSplitId;
    }

    /**
     * Gets the value of credit
     *
     * @return the value of credit
     */
    public ScaleTwoDecimal getCredit() {
        return this.credit != null ? this.credit : new ScaleTwoDecimal(0);
    }

    /**
     * Gets the value of invCreditTypeCode
     *
     * @return the value of invCreditTypeCode
     */
    public String getInvCreditTypeCode() {
        refreshInvestigatorCreditTypeIfNeeded();
        return invCreditTypeCode;
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
     * Sets the awardContactId attribute value.
     * @param awardContactId The awardContactId to set.
     */
    public void setAwardContactId(Long awardContactId) {
        this.awardContactId = awardContactId;
    }

    /**
     * Sets the awardPerson attribute value.
     * @param awardPerson The awardPerson to set.
     */
    public void setAwardPerson(AwardPerson awardPerson) {
        this.awardPerson = awardPerson;
        this.awardContactId = awardPerson != null ? awardPerson.getAwardContactId() : null;
    }

    /**
     * Sets the awardPersonCreditSplitId attribute value.
     * @param awardPersonCreditSplitId The awardPersonCreditSplitId to set.
     */
    public void setAwardPersonCreditSplitId(Long awardPersonCreditSplitId) {
        this.awardPersonCreditSplitId = awardPersonCreditSplitId;
    }

    /**
     * Sets the value of credit
     *
     * @param argCredit Value to assign to this.credit
     */
    public void setCredit(ScaleTwoDecimal argCredit) {
        this.credit = argCredit;
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
     * Sets the investigatorCreditType attribute value.
     * @param investigatorCreditType The investigatorCreditType to set.
     */
    public void setInvestigatorCreditType(InvestigatorCreditType investigatorCreditType) {
        this.investigatorCreditType = investigatorCreditType;
        this.invCreditTypeCode = investigatorCreditType != null ? investigatorCreditType.getCode() : null;
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

    public Award getSequenceOwner() {
        return awardPerson != null ? awardPerson.getAward() : null;
    }

    public void setSequenceOwner(Award newlyVersionedOwner) {
        if (awardPerson != null) {
            awardPerson.setAward(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return awardPerson != null ? awardPerson.getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        awardPersonCreditSplitId = null;
    }
}
