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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import static org.junit.Assert.*;
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
