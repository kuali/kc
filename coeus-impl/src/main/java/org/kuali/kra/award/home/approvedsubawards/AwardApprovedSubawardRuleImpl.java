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
package org.kuali.kra.award.home.approvedsubawards;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the AwardApprovedSubawardRuleImpl 
 */
public class AwardApprovedSubawardRuleImpl extends KcTransactionalDocumentRuleBase
                                            implements AwardApprovedSubawardRule {
    
    private static final String NEW_AWARD_APPROVED_SUBAWARD = "approvedSubawardFormHelper.newAwardApprovedSubaward";
    private static final String ORGANIZATION_ID = ".organizationId";
    
    List<AwardApprovedSubaward> awardApprovedSubawards;
    AwardApprovedSubaward awardApprovedSubaward;
    String errorPath;
    
    

    /**
     * @see org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent)
     */
    public boolean processApprovedSubawardBusinessRules(AwardApprovedSubawardRuleEvent 
                                                            awardApprovedSubawardRuleEvent) {
        this.awardApprovedSubawards = awardApprovedSubawardRuleEvent.getAwardApprovedSubawards();
        this.awardApprovedSubaward = awardApprovedSubawardRuleEvent.getApprovedSubaward();
        this.errorPath = awardApprovedSubawardRuleEvent.getErrorPathPrefix();
        return true;
    }
    
    /**
     * Gets the awardApprovedSubawards attribute. 
     * @return Returns the awardApprovedSubawards.
     */
    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        return awardApprovedSubawards;
    }

    /**
     * Sets the awardApprovedSubawards attribute value.
     * @param awardApprovedSubawards The awardApprovedSubawards to set.
     */
    public void setAwardApprovedSubawards(List<AwardApprovedSubaward> awardApprovedSubawards) {
        this.awardApprovedSubawards = awardApprovedSubawards;
    }

    /**
     * Gets the awardApprovedSubaward attribute. 
     * @return Returns the awardApprovedSubaward.
     */
    public AwardApprovedSubaward getAwardApprovedSubaward() {
        return awardApprovedSubaward;
    }

    /**
     * Sets the awardApprovedSubaward attribute value.
     * @param awardApprovedSubaward The awardApprovedSubaward to set.
     */
    public void setAwardApprovedSubaward(AwardApprovedSubaward awardApprovedSubaward) {
        this.awardApprovedSubaward = awardApprovedSubaward;
    }

    /**
     * Gets the errorPath attribute. 
     * @return Returns the errorPath.
     */
    public String getErrorPath() {
        return errorPath;
    }

    /**
     * Sets the errorPath attribute value.
     * @param errorPath The errorPath to set.
     */
    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    /**
     * @see org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#processApprovedSubawardBusinessRules
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent)
     */
    public boolean processAddApprovedSubawardBusinessRules(AwardApprovedSubawardRuleEvent 
                                                            awardApprovedSubawardRuleEvent) {
        this.awardApprovedSubawards = awardApprovedSubawardRuleEvent.getAwardApprovedSubawards();
        this.awardApprovedSubaward = awardApprovedSubawardRuleEvent.getApprovedSubaward();
        this.errorPath = awardApprovedSubawardRuleEvent.getErrorPathPrefix();
        boolean validOrganization = validateApprovedSubawardOrganization();
        return validOrganization;
    }

    public boolean validateApprovedSubawardOrganization(){
        boolean valid = true;
        String organizationId = awardApprovedSubaward.getOrganizationId();
        if(organizationId == null) {
            valid = false;
            reportError(NEW_AWARD_APPROVED_SUBAWARD+ORGANIZATION_ID, 
                    KeyConstants.ERROR_ORGANIZATION_ID_IS_NULL);
        }else {
            valid = validateOrganizationExists(organizationId);
        }
        return valid;
    }
    
    /**
     * This method tests whether a Organization BO with a given organization ID exists.
     * @param organizationId
     * @return
     */
    private boolean validateOrganizationExists(String organizationId) {
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put("organizationId", organizationId);
        
        boolean isValid = true;
        if (businessObjectService.countMatching(Organization.class, fieldValues) != 1) {
            this.reportError(NEW_AWARD_APPROVED_SUBAWARD+ORGANIZATION_ID, KeyConstants.ERROR_ORGANIZATION_ID_IS_INVALID, new String[] { organizationId });
            return false;
        }
        return isValid;
    }

}
