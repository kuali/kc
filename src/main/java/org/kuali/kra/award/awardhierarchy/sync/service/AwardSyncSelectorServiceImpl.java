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
package org.kuali.kra.award.awardhierarchy.sync.service;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncDescendantValues;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

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
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncSelectorService#isChangeApplicableToAward(org.kuali.kra.award.home.Award, org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange)
     */
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
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncSelectorService#isAwardActive(org.kuali.kra.award.home.Award)
     */
    public boolean isAwardActive(Award award) {
        String activeParm = getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.AWARD_ACTIVE_STATUS_CODES_PARM);
        for (String activeCode : activeParm.split(",")) {
            if (StringUtils.equals(award.getAwardStatus().getStatusCode(), activeCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncSelectorService#isCostShareAccount(org.kuali.kra.award.home.Award)
     */
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
