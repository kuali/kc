/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.bo.Rolodex;

public class AwardSyncReportHelperTest extends AwardSyncHelperTestBase {
    
    protected AwardReportTerm report;
    protected AwardReportTermRecipient recipient;

    public AwardSyncReportHelperTest() {
        super("AwardReportTerm");
    }
    protected AwardSyncReportHelperTest(String className) {
        super(className);
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        report = new AwardReportTerm();
        report.setFrequencyCode("1");
        report.setFrequency(new Frequency());
        report.getFrequency().setDescription("Test Frequency");
        report.getFrequency().setFrequencyCode("1");
        report.setFrequencyBaseCode("1");
        report.setFrequencyBase(new FrequencyBase());
        report.getFrequencyBase().setFrequencyBaseCode("1");
        report.getFrequencyBase().setDescription("Test Frequency Base");
        report.setReportClassCode("1");
        report.setReportClass(new ReportClass());
        report.getReportClass().setReportClassCode("1");
        report.getReportClass().setDescription("Test Report Class");
        report.setReportCode("1");
        report.setReport(new Report());
        report.getReport().setReportCode("1");
        report.getReport().setDescription("Test Report");
        report.setOspDistributionCode("1");
        report.setDistribution(new Distribution());
        report.getDistribution().setDescription("Test Distribution");
        report.getDistribution().setOspDistributionCode("1");
        
        recipient = new AwardReportTermRecipient();
        recipient.setAwardReportTerm(report);
        recipient.setRolodexId(1);
        recipient.setRolodex(new Rolodex());
        recipient.getRolodex().setFirstName("Test");
        recipient.getRolodex().setLastName("Person");
        recipient.setNumberOfCopies(5);
        recipient.setContactTypeCode("1");
        recipient.setContactType(new ContactType());
        recipient.getContactType().setContactTypeCode("1");
        recipient.getContactType().setDescription("Test Contact Type");
        report.getAwardReportTermRecipients().add(recipient);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(report, null);
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getKeys().isEmpty());
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(report.getFrequencyCode(), xmlExport.getKeys().get("frequencyCode")); 
        assertEquals(report.getOspDistributionCode(), xmlExport.getValues().get("ospDistributionCode"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, report, "awardReportTermItems", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, report, "awardReportTermItems", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getAwardReportTermItems().isEmpty());
        assertEquals(award.getAwardReportTermItems().get(0).getFrequencyCode(), report.getFrequencyCode());
        assertFalse(award.getAwardReportTermItems().get(0).getAwardReportTermRecipients().isEmpty());
        
        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.DELETE_SYNC, report, "awardReportTermItems", null);
        awardSyncHelper.applySyncChange(award, change);
        assertTrue(award.getAwardReportTermItems().isEmpty());
    }  
}
