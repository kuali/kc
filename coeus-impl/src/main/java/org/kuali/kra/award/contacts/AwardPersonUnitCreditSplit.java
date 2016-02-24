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
 * Class representation of the Proposal Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @author $Id: ProposalUnitCreditSplit.java,v 1.8 2008-07-28 14:48:12 vsoni Exp $
 * @version $Revision: 1.8 $
 */
public final class AwardPersonUnitCreditSplit extends KcPersistableBusinessObjectBase implements CreditSplit, SequenceAssociate<Award> {

    private static final long serialVersionUID = 7370393791601182821L;

    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "code";

    private Long awardPersonUnitCreditSplitId;

    //don't version parent bo as it leads to odd and destructive behavior in some cases 
    @SkipVersioning
    private AwardPersonUnit awardPersonUnit;

    private ScaleTwoDecimal credit = new ScaleTwoDecimal(0);

    private InvestigatorCreditType investigatorCreditType;

    // OJB Hacks 
    private Long awardPersonUnitId;

    private String invCreditTypeCode;


    public AwardPersonUnitCreditSplit() {
    }

    AwardPersonUnitCreditSplit(InvestigatorCreditType investigatorCreditType, ScaleTwoDecimal credit) {
        setInvestigatorCreditType(investigatorCreditType);
        setCredit(credit);
    }

    /**
     * Gets the awardPersonUnitCreditSplitId attribute. 
     * @return Returns the awardPersonUnitCreditSplitId.
     */
    public Long getAwardPersonUnitCreditSplitId() {
        return awardPersonUnitCreditSplitId;
    }

    /** 
     * Gets the awardPersonUnit attribute. 
     * @return Returns the awardPersonUnit.
     */
    public AwardPersonUnit getAwardPersonUnit() {
        return awardPersonUnit;
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
     * Gets the awardPersonUnitId attribute. 
     * @return Returns the awardPersonUnitId.
     */
    public Long getAwardPersonUnitId() {
        return awardPersonUnitId;
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
     * Sets the awardPersonUnitCreditSplitId attribute value.
     * @param awardPersonUnitCreditSplitId The awardPersonUnitCreditSplitId to set.
     */
    public void setAwardPersonUnitCreditSplitId(Long awardPersonUnitCreditSplitId) {
        this.awardPersonUnitCreditSplitId = awardPersonUnitCreditSplitId;
    }

    /**
     * Sets the awardPersonUnit attribute value.
     * @param awardPersonUnit The awardPersonUnit to set.
     */
    public void setAwardPersonUnit(AwardPersonUnit awardPersonUnit) {
        this.awardPersonUnit = awardPersonUnit;
        this.awardPersonUnitId = awardPersonUnit != null ? awardPersonUnit.getAwardPersonUnitId() : null;
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
     * Sets the awardPersonUnitId attribute value.
     * @param awardPersonUnitId The awardPersonUnitId to set.
     */
    public void setAwardPersonUnitId(Long awardPersonUnitId) {
        this.awardPersonUnitId = awardPersonUnitId;
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

    public Award getSequenceOwner() {
        return awardPersonUnit != null ? awardPersonUnit.getSequenceOwner() : null;
    }

    public void setSequenceOwner(Award newlyVersionedOwner) {
        if (awardPersonUnit != null) {
            awardPersonUnit.setSequenceOwner(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return awardPersonUnit != null ? awardPersonUnit.getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        awardPersonUnitCreditSplitId = null;
    }
}
