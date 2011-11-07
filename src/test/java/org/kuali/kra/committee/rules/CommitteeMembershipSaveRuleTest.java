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
package org.kuali.kra.committee.rules;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the Committee Membership Save Rules
 */
public class CommitteeMembershipSaveRuleTest extends CommitteeRuleTestBase {

    private CommitteeDocumentRule rule;
    private CommitteeMembership committeeMembership;
    private static final String SEPERATOR = ".";
    private static final String INACTIVE_AREAS_OF_EXPERTISE_PREFIX = "document.committeeList[0].committeeMemberships[%1$s].areasOfExpertise.inactive";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new CommitteeDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test saving a committee document with a committee membership that does not contain any of the required properties.
     */
    @Test
    public void testSaveWithEmptyCommitteeMembership() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create a committee membership without setting any properties and attach it to the committee document.
         */
        committeeMembership = createMembership(null, null, null, null, null);
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. Since we didn't set any committee membership properties, each of the required fields
         * should result in an error.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);

        /*
         * There should be five errors.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(5, errorMap.getErrorCount());

        /*
         * Verify that the error keys for each of the required fields is in the ErrorMap.
         */
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeMemberships[0].membershipTypeCode"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeMemberships[0].termStartDate"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeMemberships[0].termEndDate"));
        assertError("committeeHelper.newCommitteeMembershipRoles[0].membershipRoleCode",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_MISSING);
        assertError("committeeHelper.newCommitteeMembershipExpertise[0].researchAreaCode",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_MISSING);
    }

    /**
     * Test saving a committee document with a committee membership which term end date is before the term start date and where the
     * role dates are blank.
     */
    @Test
    public void testSaveWithInvalidTermDate() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create a committee membership with role and expertise.
         */
        committeeMembership = createMembership("jtester", null, "1", "2009-01-15", "2009-01-14");
        addRole(committeeMembership, "1", null, null);
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. We expect an error for the term end date being before the term start date, an error for
         * the missing start date of the role, and an error for the missing end date of the role.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);

        /*
         * There should be three errors.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(3, errorMap.getErrorCount());

        /*
         * Verify that the error keys for each of the required fields is in the ErrorMap.
         */
        assertError("document.committeeList[0].committeeMemberships[0].termEndDate",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_TERM_END_DATE_BEFORE_TERM_START_DATE);
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeMemberships[0].membershipRoles[0].startDate"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeMemberships[0].membershipRoles[0].endDate"));
    }

    /**
     * Test saving a committee document with a committee membership where the role start date and end date are outside of the term
     * dates.
     */
    @Test
    public void testSaveWithRoleDatesOutsideTerm() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create a committee membership with role and expertise.
         */
        committeeMembership = createMembership("jtester", null, "1", "2009-01-10", "2009-01-20");
        addRole(committeeMembership, "1", "2009-01-08", "2009-01-22");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. We expect an error for the role start date being before the term start date, and an error
         * for the role end date being after the term end date.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);

        /*
         * There should be two errors.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(2, errorMap.getErrorCount());

        /*
         * Verify that the error keys for each of the required fields is in the ErrorMap.
         */
        assertError("document.committeeList[0].committeeMemberships[0].membershipRoles[0].startDate",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM);
        assertError("document.committeeList[0].committeeMemberships[0].membershipRoles[0].endDate",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM);
    }

    /**
     * Test saving a committee document with a committee membership which has two roles of the same type whose start and end dates
     * overlaps.
     */
    @Test
    public void testSaveWithOverlappingRoles() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create a committee membership with two roles and an expertise.
         */
        committeeMembership = createMembership("jtester", null, "1", "2009-01-10", "2009-01-20");
        addRole(committeeMembership, "1", "2009-01-14", "2009-01-15");
        addRole(committeeMembership, "1", "2009-01-15", "2009-01-16");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. We expect an error on the first role as it overlaps with the second role.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);

        /*
         * There should be one errors.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());

        /*
         * Verify that the error keys for each of the required fields is in the ErrorMap.
         */
        assertError("document.committeeList[0].committeeMemberships[0].membershipRoles[0].membershipRoleCode",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
    }

    /**
     * Test saving a committee document where the same person is specified twice with committee memberships whose terms overlap.
     */
    @Test
    public void testSaveWithOverlappingMembership() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create two identical committee membership with role and expertise.
         */
        committeeMembership = createMembership("jtester", null, "1", "2009-01-10", "2009-01-15");
        addRole(committeeMembership, "1", "2009-01-10", "2009-01-15");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        committeeMembership = createMembership("jtester", null, "1", "2009-01-10", "2009-01-15");
        addRole(committeeMembership, "1", "2009-01-10", "2009-01-15");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. We expect an error on the first role as it overlaps with the second role.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);

        /*
         * There should be one errors.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();

        assertEquals(1, errorMap.getErrorCount());

        /*
         * Verify that the error keys for each of the required fields is in the ErrorMap.
         */
        assertError("document.committeeList[0].committeeMemberships[1].termStartDate",
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_DUPLICATE);
    }
    
    /**
     * Test saving a committee document where some committee memberships have inactive research areas in their expertise
     */
    @Test
    public void testSaveWithInactiveAreas() throws Exception {
        
        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        CommitteeMembership committeeMembership0 = new CommitteeMembership();
        CommitteeMembership committeeMembership1 = new CommitteeMembership();
        CommitteeMembership committeeMembership2 = new CommitteeMembership();
        /*
         * Create three committee membership with active and inactive research areas in their expertise.
         */
        addMembership(document, committeeMembership0);
        addMembership(document, committeeMembership1);
        addMembership(document, committeeMembership2);
        
        addExpertise(committeeMembership0, "0");
        addExpertise(committeeMembership0, "1");
        committeeMembership0.getMembershipExpertise().get(1).getResearchArea().setActive(false);
        
        addExpertise(committeeMembership1, "00");
        addExpertise(committeeMembership1, "11");
        committeeMembership1.getMembershipExpertise().get(0).getResearchArea().setActive(false);
        
        addExpertise(committeeMembership2, "000");
        addExpertise(committeeMembership2, "111");
        committeeMembership2.getMembershipExpertise().get(0).getResearchArea().setActive(false);
        committeeMembership2.getMembershipExpertise().get(1).getResearchArea().setActive(false);
        
     
        /*
         * Process the Save Document rule. We expect three errors with correctly formatted property keys
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);
        
        String errorPropertyKey0 = String.format(INACTIVE_AREAS_OF_EXPERTISE_PREFIX, 0)+ SEPERATOR +  "1.";
        String errorPropertyKey1 = String.format(INACTIVE_AREAS_OF_EXPERTISE_PREFIX, 1)+ SEPERATOR +  "0.";
        String errorPropertyKey2 = String.format(INACTIVE_AREAS_OF_EXPERTISE_PREFIX, 2)+ SEPERATOR +  "0.1.";
        
        assertError(errorPropertyKey0, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE);
        assertError(errorPropertyKey1, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE);
        assertError(errorPropertyKey2, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE);
        
        

    }

    /**
     * Test saving a committee document where the same person is specified twice with committee memberships whose terms overlap.
     */
    @Test
    public void testValidSave() throws Exception {

        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);

        /*
         * Create three committee membership with role and expertise.
         */
        committeeMembership = createMembership("jtester", null, "1", "2009-01-01", "2009-01-10");
        addRole(committeeMembership, "1", "2009-01-01", "2009-01-10");
        addRole(committeeMembership, "2", "2009-01-01", "2009-01-10");
        addExpertise(committeeMembership, "000001");
        addExpertise(committeeMembership, "000002");
        addMembership(document, committeeMembership);

        committeeMembership = createMembership("jtester", null, "1", "2009-01-11", "2009-01-20");
        addRole(committeeMembership, "1", "2009-01-11", "2009-01-15");
        addRole(committeeMembership, "1", "2009-01-16", "2009-01-20");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        committeeMembership = createMembership(null, 1, "1", "2009-01-01", "2009-01-20");
        addRole(committeeMembership, "1", "2009-01-01", "2009-01-20");
        addRole(committeeMembership, "2", "2009-01-01", "2009-01-10");
        addExpertise(committeeMembership, "000001");
        addMembership(document, committeeMembership);

        /*
         * Process the Save Document rule. We expect no errors.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertTrue(rulesPassed);

    }

    /**
     * Create a CommitteeMembership.
     * 
     * @param personID
     * @param rolodexID
     * @param membershipTypeCode
     * @param termStartDate
     * @param termEndDate
     * @return CommitteeMembership
     */
    private CommitteeMembership createMembership(String personID, Integer rolodexID, String membershipTypeCode,
            String termStartDate, String termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setRolodexId(rolodexID);
        committeeMembership.setMembershipTypeCode(membershipTypeCode);
        if (termStartDate != null) {
            committeeMembership.setTermStartDate(Date.valueOf(termStartDate));
        }
        if (termEndDate != null) {
            committeeMembership.setTermEndDate(Date.valueOf(termEndDate));
        }
        return committeeMembership;
    }

    /**
     * Add the CommitteeMembership to the CommitteeDocument.
     * 
     * @param document
     * @param committeeMembership
     */
    private void addMembership(CommitteeDocument document, CommitteeMembership committeeMembership) {
        document.getCommittee().getCommitteeMemberships().add(committeeMembership);
    }

    /**
     * Add a CommitteeMembershipRole to the CommitteeMembership.
     * 
     * @param committeeMembership
     * @param membershipRoleCode
     * @param startDate
     * @param endDate
     */
    private void addRole(CommitteeMembership committeeMembership, String membershipRoleCode, String startDate, String endDate) {
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode(membershipRoleCode);
        if (startDate != null) {
            committeeMembershipRole.setStartDate(Date.valueOf(startDate));
        }
        if (endDate != null) {
            committeeMembershipRole.setEndDate(Date.valueOf(endDate));
        }
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);

    }

    /**
     * Add a CommitteeMembershipExpertise to the CommitteeMembership. The default research area of the expertise will be active. 
     * 
     * @param committeeMembership
     * @param researchAreaCode
     */
    private void addExpertise(CommitteeMembership committeeMembership, String researchAreaCode) {
        CommitteeMembershipExpertise committeeMembershipExpertise = new CommitteeMembershipExpertise();
        committeeMembershipExpertise.setResearchAreaCode(researchAreaCode);
        committeeMembershipExpertise.getResearchArea().setActive(true);
        committeeMembership.getMembershipExpertise().add(committeeMembershipExpertise);

    }

    /**
     * This method tests the logic for validating that all research areas associated with a committee membership are active.
     * Specifically it tests 2 different cases: 
     *      1. A committee member has all active research areas--should give no error 
     *      2. A committee member has some inactive research areas--should give a single error with the error-property correctly encoding
     *         the indices of the inactive areas.
     */
    @Test
    public void testCheckResearchAreasForCommitteeMember() throws Exception {
        /*
         * Create a committee with default properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        CommitteeMembership committeeMembership = new CommitteeMembership();
        addMembership(document, committeeMembership);

        //check case 1
        addExpertise(committeeMembership, "0");
        addExpertise(committeeMembership, "1");
        addExpertise(committeeMembership, "2");
        addExpertise(committeeMembership, "3");
        assertTrue(rule.checkResearchAreasForCommitteeMember(committeeMembership, 0));
        
        // check case 2
        committeeMembership.getMembershipExpertise().get(1).getResearchArea().setActive(false);
        committeeMembership.getMembershipExpertise().get(3).getResearchArea().setActive(false);   
        assertFalse(rule.checkResearchAreasForCommitteeMember(committeeMembership, 0));        
        String errorPropertyKey = String.format(INACTIVE_AREAS_OF_EXPERTISE_PREFIX, 0)+ SEPERATOR +  "1.3.";
        assertError(errorPropertyKey, KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_EXPERTISE_INACTIVE);
    }
}
