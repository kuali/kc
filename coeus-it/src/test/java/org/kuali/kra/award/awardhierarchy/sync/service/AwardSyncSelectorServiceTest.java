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


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncDescendantValues;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class AwardSyncSelectorServiceTest extends KcIntegrationTestBase {

    protected static final String otherSponsorCode = "000500";
    protected static final String costShareSponsorCode = "009906";
    
    private AwardSyncSelectorService awardSyncSelectorService;
    private Award award;
    private AwardStatus activeStatus;
    private AwardStatus inactiveStatus;
    
    @Before
    public void setUp() throws Exception {
        awardSyncSelectorService = KcServiceLocator.getService(AwardSyncSelectorService.class);
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
