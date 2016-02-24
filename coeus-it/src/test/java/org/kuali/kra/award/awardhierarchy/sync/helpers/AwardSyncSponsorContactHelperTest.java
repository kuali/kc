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
import org.kuali.kra.award.contacts.AwardSponsorContact;
import static org.junit.Assert.*;
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
