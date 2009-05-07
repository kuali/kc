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
package org.kuali.kra.award.contacts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * Class representation of the Award Person credit split
 */
public final class AwardPersonCreditSplit extends KraPersistableBusinessObjectBase implements CreditSplit {
    private static final String INV_CREDIT_TYPE_CODE_FIELD_NAME = "invCreditTypeCode";

    private static final long serialVersionUID = -6999442247404810830L;
    
    private Long awardPersonCreditSplitId;
    private AwardPerson awardPerson;    
    private KualiDecimal credit;
    private InvestigatorCreditType investigatorCreditType;
    
    // OJB Hacks
    private String invCreditTypeCode;
    private Long awardContactId;
    
    /**
     * Default Constructor
     */
    public AwardPersonCreditSplit() {
        
    }
    
    /**
     * Convenience Constructor
     * @param investigatorCreditType
     * @param credit
     */
    AwardPersonCreditSplit(InvestigatorCreditType investigatorCreditType, KualiDecimal credit) {
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
    public KualiDecimal getCredit() {
        return this.credit != null ? this.credit : new KualiDecimal(0);
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
    public void setCredit(KualiDecimal argCredit) {
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
        this.invCreditTypeCode = investigatorCreditType != null ? investigatorCreditType.getInvCreditTypeCode() : null;
    }

    /**
     * @return
     */
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("awardPersonCreditSplitId", awardPersonCreditSplitId);
        map.put("awardPerson", awardPerson);    
        map.put("credit", credit);
        map.put("investigatorCreditType", investigatorCreditType);
        map.put(INV_CREDIT_TYPE_CODE_FIELD_NAME, invCreditTypeCode);
        map.put("awardContactId", awardContactId);
        return map;
    }
    
    /**
     * This method lazy-loads the InvestigatorCreditType
     */
    private void refreshInvestigatorCreditTypeIfNeeded() {
        if(invCreditTypeCode != null && (investigatorCreditType == null || !invCreditTypeCode.equals(investigatorCreditType.getInvCreditTypeCode()))) {
            Map<String, Object> keyMap = new HashMap<String, Object>();
            keyMap.put(INV_CREDIT_TYPE_CODE_FIELD_NAME, invCreditTypeCode);
            investigatorCreditType = (InvestigatorCreditType) getBusinessObjectService().findByPrimaryKey(InvestigatorCreditType.class, keyMap);
        }
    }
}
