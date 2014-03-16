/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.core.api.util.type.KualiDecimal;
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

    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "invCreditTypeCode";

    private Long awardPersonUnitCreditSplitId;

    //don't version parent bo as it leads to odd and destructive behavior in some cases 
    @SkipVersioning
    private AwardPersonUnit awardPersonUnit;

    private KualiDecimal credit = new KualiDecimal(0);

    private InvestigatorCreditType investigatorCreditType;

    // OJB Hacks 
    private Long awardPersonUnitId;

    private String invCreditTypeCode;

    /**
     * Default Constructor
     */
    public AwardPersonUnitCreditSplit() {
    }

    /**
     * Convenience Constructor
     * @param investigatorCreditType
     * @param credit
     */
    AwardPersonUnitCreditSplit(InvestigatorCreditType investigatorCreditType, KualiDecimal credit) {
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
        this.invCreditTypeCode = investigatorCreditType != null ? investigatorCreditType.getInvCreditTypeCode() : null;
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
        return KcServiceLocator.getService(BusinessObjectService.class);
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
