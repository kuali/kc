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
import org.kuali.coeus.common.framework.rolodex.Rolodex;
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
import static org.junit.Assert.*;
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
