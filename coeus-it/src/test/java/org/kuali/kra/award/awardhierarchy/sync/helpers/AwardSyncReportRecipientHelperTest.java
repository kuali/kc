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
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.rice.krad.util.ObjectUtils;
import static org.junit.Assert.*;
public class AwardSyncReportRecipientHelperTest extends AwardSyncReportHelperTest {

    public AwardSyncReportRecipientHelperTest() { 
        super("AwardReportTermRecipient");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.setUp();
    }
    
    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(recipient, null);
        AwardSyncXmlExport recipientExport = (AwardSyncXmlExport) xmlExport.getValues().get("awardReportTermRecipients");
        assertNotNull(recipientExport);
        assertFalse(recipientExport.getKeys().isEmpty());
        assertFalse(recipientExport.getValues().isEmpty());
        assertEquals(recipient.getRolodexId(), recipientExport.getKeys().get("rolodexId"));
        assertEquals(recipient.getNumberOfCopies(), recipientExport.getValues().get("numberOfCopies"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, recipient, "awardReportTermItems", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, recipient, "awardReportTermItems", null);
        AwardReportTerm newReport = (AwardReportTerm) ObjectUtils.deepCopy(report);
        newReport.getAwardReportTermRecipients().clear();
        award.add(newReport);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getAwardReportTermItems().get(0).getAwardReportTermRecipients().isEmpty());
        assertEquals(recipient.getNumberOfCopies(), award.getAwardReportTermItems().get(0).getAwardReportTermRecipients().get(0).getNumberOfCopies());
        
        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.DELETE_SYNC, recipient, "awardReportTermItems", null);
        awardSyncHelper.applySyncChange(award, change);
        assertTrue(award.getAwardReportTermItems().get(0).getAwardReportTermRecipients().isEmpty());
    }
    
    @Test(expected=AwardSyncException.class)
    public void testApplySyncChangeWithException() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, recipient, "awardReportTermItems", null);
        awardSyncHelper.applySyncChange(award, change);
    }

}
