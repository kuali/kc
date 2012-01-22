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
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.bo.SponsorTermType;

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
