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
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.AwardSponsorTerm;
import static org.junit.Assert.*;
public class AwardSyncTermHelperTest extends AwardSyncHelperTestBase {

    protected AwardSponsorTerm term;
    protected SponsorTerm sponsorTerm;
    protected static final Long sponsorTermId = 1L;
    
    public AwardSyncTermHelperTest() {
        super("AwardSponsorTerm");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        sponsorTerm = new SponsorTerm();
        sponsorTerm.setSponsorTermId(sponsorTermId);
        sponsorTerm.setDescription("Test Sponsor Term");
        SponsorTermType type = new SponsorTermType();
        type.setDescription("Test Sponsor Term Type");
        type.setSponsorTermTypeCode("1");
        sponsorTerm.setSponsorTermType(type);
        term = new AwardSponsorTerm();
        term.setSponsorTerm(sponsorTerm);
        term.setSponsorTermId(sponsorTermId);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(term, null);
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getKeys().isEmpty());
        assertEquals(sponsorTermId, xmlExport.getKeys().get("sponsorTermId"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, term, "awardSponsorTerms", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, term, "awardSponsorTerms", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getAwardSponsorTerms().isEmpty());
        assertEquals(sponsorTermId, award.getAwardSponsorTerms().get(0).getSponsorTermId());
    }
}
