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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;

public class AwardSyncAwardHelperTest extends AwardSyncHelperTestBase {
    
    private String sponsorCode = "0005000";
    private Integer statusCode = 1;
    
    public AwardSyncAwardHelperTest() {
        super("Award");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        award.setSponsorCode(sponsorCode);
        award.setStatusCode(statusCode);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(award, "sponsorCode");
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(xmlExport.getValues().get("sponsorCode"), sponsorCode);
        
        xmlExport = awardSyncHelper.buildXmlExport(award, "statusCode");
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(xmlExport.getValues().get("statusCode"), statusCode);
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, award, null, "sponsorCode");
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, award, "sponsorCode", "sponsorCode");
        award.setSponsorCode("0009906");
        awardSyncHelper.applySyncChange(award, change);
        assertEquals(award.getSponsorCode(), sponsorCode);
    }
}
