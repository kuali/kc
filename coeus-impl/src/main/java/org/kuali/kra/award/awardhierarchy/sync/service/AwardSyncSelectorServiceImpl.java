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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncDescendantValues;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.List;

/**
 * Award Sync Selector Service.
 */
public class AwardSyncSelectorServiceImpl implements AwardSyncSelectorService {
    
    private ParameterService parameterService;
    
    /** 
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncSelectorService#isAwardInvolvedInSync(org.kuali.kra.award.home.Award, java.util.List)
     */
    public boolean isAwardInvolvedInSync(Award award, List<AwardSyncChange> changes) {
        for (AwardSyncChange change : changes) {
            if (isChangeApplicableToAward(award, change)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isChangeApplicableToAward(Award award, AwardSyncChange change) {
        boolean retval = false;
        if ((StringUtils.equals(change.getSyncDescendants(), 
                AwardSyncDescendantValues.SYNC_ACTIVE.getSyncValue())
             && isAwardActive(award)) 
            || StringUtils.equals(change.getSyncDescendants(), 
                    AwardSyncDescendantValues.SYNC_ALL.getSyncValue())) {
            if (isFabricatedAccount(award)) {
                if (change.isSyncFabricated()) {
                    retval = true;
                }
            }
            if (isCostShareAccount(award)) {
                if (change.isSyncCostSharing()) {
                    retval = true;
                }
            }
            if (!isFabricatedAccount(award) && !isCostShareAccount(award)) {
                retval = true;
            }
        }
        return retval;
    }       
    
    @Override
    public boolean isAwardActive(Award award) {
        String activeParm = getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.AWARD_ACTIVE_STATUS_CODES_PARM);
        for (String activeCode : activeParm.split(",")) {
            if (StringUtils.equals(award.getAwardStatus().getStatusCode(), activeCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCostShareAccount(Award award) {
        String sponsorCode = getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.AWARD_COST_SHARING_PARM);
        return StringUtils.equals(award.getSponsorCode(), sponsorCode);
    }

    /** 
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncSelectorService#isFabricatedAccount(org.kuali.kra.award.home.Award)
     */
    public boolean isFabricatedAccount(Award award) {
        String typeCode = getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.AWARD_FABRICATED_EQUPIMENT_PARM);
        return ObjectUtils.equals(award.getAccountTypeCode(), Integer.valueOf(typeCode));
    }    
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    protected ParameterService getParameterService() {
        return parameterService;
    }

}
