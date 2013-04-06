/*
 * Copyright 2005-2013 The Kuali Foundation
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


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncDescendantValues;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class AwardSyncSelectorServiceTest extends KcUnitTestBase {

    protected static final String otherSponsorCode = "000500";
    protected static final String costShareSponsorCode = "009906";
    
    private AwardSyncSelectorService awardSyncSelectorService;
    private Award award;
    private AwardStatus activeStatus;
    private AwardStatus inactiveStatus;
    
    @Before
    public void setUp() throws Exception {
        awardSyncSelectorService = KraServiceLocator.getService(AwardSyncSelectorService.class);
        award = new Award();
        activeStatus = new AwardStatus();
        activeStatus.setStatusCode("1");
        inactiveStatus = new AwardStatus();
        inactiveStatus.setStatusCode("2");
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testIsAwardActive() throws Exception {
        award.setAwardStatus(inactiveStatus);
        assertFalse(awardSyncSelectorService.isAwardActive(award));
        award.setAwardStatus(activeStatus);
        assertTrue(awardSyncSelectorService.isAwardActive(award));
    }
    
    @Test
    public void testIsFabricatedAccount() throws Exception {
        award.setAccountTypeCode(1);
        assertFalse(awardSyncSelectorService.isFabricatedAccount(award));
        award.setAccountTypeCode(2);
        assertTrue(awardSyncSelectorService.isFabricatedAccount(award));
    }
    
    @Test
    public void testIsCostShareAccount() throws Exception {
        award.setSponsorCode(otherSponsorCode);
        assertFalse(awardSyncSelectorService.isCostShareAccount(award));
        award.setSponsorCode(costShareSponsorCode);
        assertTrue(awardSyncSelectorService.isCostShareAccount(award));
    }
    
    @Test
    public void testIsAwardInvolvedInSync() throws Exception {
        List<AwardSyncChange> changes = new ArrayList<AwardSyncChange>();
        AwardSyncChange change1 = new AwardSyncChange();
        change1.setSyncDescendantsType(AwardSyncDescendantValues.SYNC_ACTIVE);
        change1.setSyncFabricated(false);
        change1.setSyncCostSharing(false);
        changes.add(change1);

        AwardSyncChange change2 = new AwardSyncChange();
        change2.setSyncDescendantsType(AwardSyncDescendantValues.SYNC_ACTIVE);
        change2.setSyncFabricated(true);
        change2.setSyncCostSharing(false);
        changes.add(change2);
        
        award.setAwardStatus(activeStatus);
        award.setAccountTypeCode(1);
        award.setSponsorCode(costShareSponsorCode);
        
        assertFalse(awardSyncSelectorService.isAwardInvolvedInSync(award, changes));
        
        award.setAccountTypeCode(2);
        assertTrue(awardSyncSelectorService.isAwardInvolvedInSync(award, changes));
        
        award.setAwardStatus(inactiveStatus);
        assertFalse(awardSyncSelectorService.isAwardInvolvedInSync(award, changes));
        change2.setSyncDescendantsType(AwardSyncDescendantValues.SYNC_ALL);
        assertTrue(awardSyncSelectorService.isAwardInvolvedInSync(award, changes));
        award.setAccountTypeCode(1);
        assertFalse(awardSyncSelectorService.isAwardInvolvedInSync(award, changes));
    }
    
    @Test
    public void testIsChangeApplicableToAward() throws Exception {
        AwardSyncChange change = new AwardSyncChange();
        change.setSyncDescendantsType(AwardSyncDescendantValues.SYNC_ALL);
        change.setSyncFabricated(false);
        change.setSyncCostSharing(false);
        
        award.setAwardStatus(inactiveStatus);
        award.setAccountTypeCode(1);
        award.setSponsorCode(otherSponsorCode);
        
        //check active sync
        assertTrue(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        change.setSyncDescendantsType(AwardSyncDescendantValues.SYNC_ACTIVE);
        assertFalse(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        award.setAwardStatus(activeStatus);
        assertTrue(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        
        //check fabricated account sync
        award.setAccountTypeCode(2);
        assertFalse(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        change.setSyncFabricated(true);
        assertTrue(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        
        //check cost share sync
        change.setSyncFabricated(false);
        award.setSponsorCode(costShareSponsorCode);
        assertFalse(awardSyncSelectorService.isChangeApplicableToAward(award, change));
        change.setSyncCostSharing(true);
        assertTrue(awardSyncSelectorService.isChangeApplicableToAward(award, change));
    }

}
