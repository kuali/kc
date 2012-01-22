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
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.rice.krad.util.ObjectUtils;

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
