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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.contacts.AwardSponsorContact;

public class AwardSyncSponsorContactHelperTest extends AwardSyncHelperTestBase {
    
    protected AwardSponsorContact contact;

    public AwardSyncSponsorContactHelperTest() {
        super("AwardSponsorContact");
    }
    @Before
    public void setUp() throws Exception {
        super.setUp();
        contact = new AwardSponsorContact();
        contact.setRolodexId(1);
        contact.setRoleCode("1");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(contact, null);
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getKeys().isEmpty());
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(contact.getRolodexId(), xmlExport.getKeys().get("rolodexId"));
        assertEquals(contact.getRoleCode(), xmlExport.getKeys().get("roleCode"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, contact, "sponsorContacts", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, contact, "sponsorContacts", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getSponsorContacts().isEmpty());
        assertEquals(contact.getRoleCode(), award.getSponsorContacts().get(0).getRoleCode());

        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.DELETE_SYNC, contact, "sponsorContacts", null);
        awardSyncHelper.applySyncChange(award, change);
        assertTrue(award.getSponsorContacts().isEmpty());
    }
    
}
