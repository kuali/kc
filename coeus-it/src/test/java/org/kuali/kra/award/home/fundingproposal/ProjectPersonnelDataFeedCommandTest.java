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
package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonCreditSplit;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.AwardPersonUnitCreditSplit;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnitCreditSplit;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;
public class ProjectPersonnelDataFeedCommandTest extends BaseDataFeedCommandTest {

    private Unit unit1;
    private Unit unit2;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        unit1 = new Unit();
        unit1.setUnitNumber("000001");
        unit2 = new Unit();
        unit2.setUnitNumber("BL-BL");
    }
    
    protected InstitutionalProposalPerson generateIPPerson(String personId, String personName, String roleCode, ScaleTwoDecimal creditSplit) {
        InstitutionalProposalPerson retval = new InstitutionalProposalPerson();
        retval.setPersonId(personId);
        retval.setFullName(personName);
        retval.setRoleCode(roleCode);
        if (creditSplit != null) {
            for (String type : getCreditTypes()) {
                InstitutionalProposalPersonCreditSplit ipCredit = new InstitutionalProposalPersonCreditSplit();
                ipCredit.setCredit(creditSplit);
                ipCredit.setInvCreditTypeCode(type);
                retval.add(ipCredit);
            }
        }
        return retval;
    }
    
    protected InstitutionalProposalPersonUnit generateIPUnit(Unit unit, boolean leadUnit, ScaleTwoDecimal creditSplit) {
        InstitutionalProposalPersonUnit ipUnit = new InstitutionalProposalPersonUnit();
        ipUnit.setUnit(unit);
        ipUnit.setUnitNumber(unit.getUnitNumber());
        ipUnit.setLeadUnit(leadUnit);
        if (creditSplit != null) {
            for (String type : getCreditTypes()) {
                InstitutionalProposalPersonUnitCreditSplit ipCredit = new InstitutionalProposalPersonUnitCreditSplit();
                ipCredit.setCredit(creditSplit);
                ipCredit.setInvCreditTypeCode(type);
                ipUnit.add(ipCredit);
            }
        }
        return ipUnit;
    }
    
    protected AwardPerson generateAwardPerson(String personId, String personName, String roleCode, ScaleTwoDecimal creditSplit) {
        AwardPerson retval = new AwardPerson();
        retval.setPersonId(personId);
        retval.setFullName(personName);
        retval.setRoleCode(roleCode);
        for (String type : getCreditTypes()) {
            AwardPersonCreditSplit awardCredit = new AwardPersonCreditSplit();
            awardCredit.setCredit(creditSplit);
            awardCredit.setInvCreditTypeCode(type);
            retval.add(awardCredit);
        }
        return retval;
    }
    
    protected AwardPersonUnit generateAwardUnit(Unit unit, boolean leadUnit, ScaleTwoDecimal creditSplit) {
        AwardPersonUnit awardUnit = new AwardPersonUnit();
        awardUnit.setUnitNumber(unit.getUnitNumber());
        awardUnit.setUnit(unit);
        awardUnit.setLeadUnit(leadUnit);
        for (String type : getCreditTypes()) {
            AwardPersonUnitCreditSplit awardCredit = new AwardPersonUnitCreditSplit();
            awardCredit.setCredit(creditSplit);
            awardCredit.setInvCreditTypeCode(type);
            awardUnit.add(awardCredit);
        }
        return awardUnit;
    }

    protected Collection<String> getCreditTypes() {
        return Stream.of("1", "2").collect(java.util.stream.Collectors.toList());
    }
    
    
    @Test
    public void testFeedNewAward() {
        InstitutionalProposalPerson ipPerson1 = generateIPPerson("10000000001", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson1.add(generateIPUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        InstitutionalProposalPerson ipPerson2 = generateIPPerson("10000000002", "Number 2", ContactRole.COI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson2.add(generateIPUnit(unit1, false, new ScaleTwoDecimal(50.00)));
        ipPerson2.add(generateIPUnit(unit2, false, new ScaleTwoDecimal(50.00)));
        proposal.add(ipPerson1);
        proposal.add(ipPerson2);
        ProjectPersonnelDataFeedCommand command = new ProjectPersonnelDataFeedCommand(award, proposal, FundingProposalMergeType.NEWAWARD);
        command.performDataFeed();
        assertTrue(award.getProjectPersons().size() == proposal.getProjectPersons().size());
        AwardPerson person1 = findAwardPerson(ipPerson1.getPersonId());
        AwardPerson person2 = findAwardPerson(ipPerson2.getPersonId());
        assertTrue(StringUtils.equals(person1.getRoleCode(), ipPerson1.getRoleCode()));
        assertTrue(StringUtils.equals(person1.getFullName(), ipPerson1.getFullName()));
        AwardPersonUnit awardUnit1 = findPersonUnit(person1, unit1.getUnitNumber());
        assertTrue(awardUnit1.isLeadUnit());
        assertTrue(person1.getCreditSplit(0).getCredit().equals(ipPerson1.getCreditSplit(0).getCredit()));
        assertTrue(StringUtils.equals(person2.getRoleCode(), ipPerson2.getRoleCode()));
    }
    
    @Test
    public void testTypicalMerge() {
        InstitutionalProposalPerson ipPerson1 = generateIPPerson("10000000001", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson1.add(generateIPUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        InstitutionalProposalPerson  ipPerson2 = generateIPPerson("10000000002", "Number 2", ContactRole.COI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson2.add(generateIPUnit(unit1, false, new ScaleTwoDecimal(50.00)));
        ipPerson2.add(generateIPUnit(unit2, false, new ScaleTwoDecimal(50.00)));
        proposal.add(ipPerson1);
        proposal.add(ipPerson2);
        AwardPerson awardPerson1 = generateAwardPerson("10000000003", "Number 3", ContactRole.PI_CODE, new ScaleTwoDecimal(80.00));
        awardPerson1.add(generateAwardUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        AwardPerson awardPerson2 = generateAwardPerson("10000000002", "Number 2", ContactRole.COI_CODE, new ScaleTwoDecimal(20.00));
        awardPerson2.add(generateAwardUnit(unit1, false, new ScaleTwoDecimal(100.00)));
        award.add(awardPerson1);
        award.add(awardPerson2);
        ProjectPersonnelDataFeedCommand command = new ProjectPersonnelDataFeedCommand(award, proposal, FundingProposalMergeType.MERGE);
        command.performDataFeed();
        assertEquals(3, award.getProjectPersons().size());
        AwardPerson person1 = findAwardPerson(awardPerson1.getPersonId());
        assertEquals(new ScaleTwoDecimal(80.00), person1.getCreditSplit(0).getCredit());
        assertTrue(person1.getUnit(0).isLeadUnit());
        assertEquals(new ScaleTwoDecimal(100.00), person1.getUnit(0).getCreditSplit(0).getCredit());
        AwardPerson person2 = findAwardPerson(awardPerson2.getPersonId());
        assertEquals(new ScaleTwoDecimal(70.00), person2.getCreditSplit(0).getCredit());
        assertEquals(2, person2.getUnits().size());
        AwardPersonUnit tempUnit = findPersonUnit(person2, unit1.getUnitNumber());
        assertEquals(new ScaleTwoDecimal(150.00), tempUnit.getCreditSplit(0).getCredit());
        assertFalse(tempUnit.isLeadUnit());
        tempUnit = findPersonUnit(person2, unit2.getUnitNumber());
        assertEquals(new ScaleTwoDecimal(50.00), tempUnit.getCreditSplit(0).getCredit());
        
        AwardPerson person3 = findAwardPerson(ipPerson1.getPersonId());
        assertEquals(ContactRole.COI_CODE, person3.getRoleCode());
        assertFalse(person3.getUnit(0).isLeadUnit());
    }
    
    @Test
    public void testDuplicateMerge() {
        InstitutionalProposalPerson ipPerson1 = generateIPPerson("10000000001", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson1.add(generateIPUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        proposal.add(ipPerson1);
        AwardPerson awardPerson1 = generateAwardPerson("10000000001", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        awardPerson1.add(generateAwardUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        award.add(awardPerson1);
        ProjectPersonnelDataFeedCommand command = new ProjectPersonnelDataFeedCommand(award, proposal, FundingProposalMergeType.MERGE);
        command.performDataFeed();
        assertEquals(1, award.getProjectPersons().size());
        assertEquals(new ScaleTwoDecimal(50.00), awardPerson1.getCreditSplit(0).getCredit());
        assertTrue(awardPerson1.getUnit(0).isLeadUnit());
        assertEquals(new ScaleTwoDecimal(100.00), awardPerson1.getUnit(0).getCreditSplit(0).getCredit());
    }
    
    @Test
    public void testTypicalReplace() {
        InstitutionalProposalPerson ipPerson1 = generateIPPerson("10000000001", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        ipPerson1.add(generateIPUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        proposal.add(ipPerson1);
        AwardPerson awardPerson1 = generateAwardPerson("10000000003", "Number 1", ContactRole.PI_CODE, new ScaleTwoDecimal(50.00));
        awardPerson1.add(generateAwardUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        award.add(awardPerson1);
        ProjectPersonnelDataFeedCommand command = new ProjectPersonnelDataFeedCommand(award, proposal, FundingProposalMergeType.REPLACE);
        command.performDataFeed();
        assertEquals(1, award.getProjectPersons().size());
        AwardPerson person1 = findAwardPerson(awardPerson1.getPersonId());
        assertNull(person1);
        AwardPerson person2 = findAwardPerson(ipPerson1.getPersonId());
        assertNotNull(person2);
        assertEquals(ipPerson1.getFullName(), person2.getFullName());
    }
    
    @Test
    public void testNoSplitReplace() {
        InstitutionalProposalPerson ipPerson1 = generateIPPerson("10000000001", "Number 1", ContactRole.PI_CODE, null);
        ipPerson1.add(generateIPUnit(unit1, true, null));
        InstitutionalProposalPerson  ipPerson2 = generateIPPerson("10000000002", "Number 2", ContactRole.COI_CODE, null);
        ipPerson2.add(generateIPUnit(unit1, false, null));
        ipPerson2.add(generateIPUnit(unit2, false, null));
        proposal.add(ipPerson1);
        proposal.add(ipPerson2);
        AwardPerson awardPerson1 = generateAwardPerson("10000000003", "Number 3", ContactRole.PI_CODE, new ScaleTwoDecimal(80.00));
        awardPerson1.add(generateAwardUnit(unit1, true, new ScaleTwoDecimal(100.00)));
        AwardPerson awardPerson2 = generateAwardPerson("10000000002", "Number 2", ContactRole.COI_CODE, new ScaleTwoDecimal(20.00));
        awardPerson2.add(generateAwardUnit(unit1, false, new ScaleTwoDecimal(100.00)));
        award.add(awardPerson1);
        award.add(awardPerson2);
        ProjectPersonnelDataFeedCommand command = new ProjectPersonnelDataFeedCommand(award, proposal, FundingProposalMergeType.REPLACE);
        command.performDataFeed();
        assertEquals(3, award.getProjectPersons().size());
        AwardPerson person1 = findAwardPerson(awardPerson1.getPersonId());
        assertEquals(new ScaleTwoDecimal(80.00), person1.getCreditSplit(0).getCredit());
        assertTrue(person1.getUnit(0).isLeadUnit());
        assertEquals(new ScaleTwoDecimal(100.00), person1.getUnit(0).getCreditSplit(0).getCredit());
        AwardPerson person2 = findAwardPerson(awardPerson2.getPersonId());
        assertEquals(new ScaleTwoDecimal(20.00), person2.getCreditSplit(0).getCredit());
        assertEquals(2, person2.getUnits().size());
        
        AwardPerson person3 = findAwardPerson(ipPerson1.getPersonId());
        assertEquals(ContactRole.COI_CODE, person3.getRoleCode());
        assertFalse(person3.getUnit(0).isLeadUnit());
    }    
    
    
    public AwardPerson findAwardPerson(String personId) {
        AwardPerson person = award.getProjectPerson(personId);
        //check for duplicates
        for (AwardPerson curPerson : award.getProjectPersons()) {
            if (curPerson != person) {
                assertFalse("Duplicate award person", StringUtils.equals(curPerson.getPersonId(), personId));
            }
        }
        return person;
    }
    
    public AwardPersonUnit findPersonUnit(AwardPerson person, String unitNumber) {
        AwardPersonUnit unit = person.getUnit(unitNumber);
        //check for duplicates
        for (AwardPersonUnit curUnit : person.getUnits()) {
            if (curUnit != unit) {
                assertFalse("Duplicate unit found", StringUtils.equals(curUnit.getUnitNumber(), unitNumber));
            }
        }
        return unit;
    }
}
