/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipEvent;
import org.kuali.kra.committee.rule.event.AddCommitteeMembershipRoleEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Test the Committee Membership Add Rules
 */
public class CommitteeMembershipAddRuleTest extends CommitteeRuleTestBase {
    private CommitteeMembershipRule rule;
    
    @Before
    public void setup() throws Exception {
        super.setUp();
        rule = new CommitteeMembershipRule();
    }
    
    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }
    
    /**
     * Test adding a committee membership without a person id or rolodex id.
     * This is not allowed
     */
    @Test
    public void testAddCommitteeMembershipWithNoId() throws Exception {
        CommitteeMembership newCommitteeMembership = new CommitteeMembership();
        
        assertFalse(rule.processAddCommitteeMembershipBusinessRules(getAddCommitteeMembershipEvent(newCommitteeMembership)));
        
        assertError("membershipHelper.newCommitteeMembership.personName", KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_PERSON_NOT_SPECIFIED);
    }
    
    /**
     * Test adding a committee membership with a person id.
     */
    @Test
    public void testAddCommitteeMembershipWithPersonId() throws Exception {
        CommitteeMembership newCommitteeMembership = new CommitteeMembership();
        newCommitteeMembership.setPersonId("jtester");
        
        assertTrue(rule.processAddCommitteeMembershipBusinessRules(getAddCommitteeMembershipEvent(newCommitteeMembership)));
    }
    
    /**
     * Test adding a committee membership with a person id.
     */
    @Test
    public void testAddCommitteeMembershipWithRolodexId() throws Exception {
        CommitteeMembership newCommitteeMembership = new CommitteeMembership();
        newCommitteeMembership.setRolodexId(1746);
       
        assertTrue(rule.processAddCommitteeMembershipBusinessRules(getAddCommitteeMembershipEvent(newCommitteeMembership)));
    }
    
    /**
     * Test adding a role without a missing role type.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithoutRole() throws Exception {
        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-01"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-31"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(new CommitteeMembership(), 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].membershipRoleCode", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_NOT_SPECIFIED);
    }
    
    /**
     * Test adding a role without a start date.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithoutStartDate() throws Exception {
        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-31"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(new CommitteeMembership(), 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].startDate", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_NOT_SPECIFIED);
    }
    
    /**
     * Test adding a role without a end date.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithoutEndDate() throws Exception {
        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-01"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(new CommitteeMembership(), 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].endDate", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_NOT_SPECIFIED);
    }

    /**
     * Test adding a role where the end date is before the start date.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithEndDateBeforeStartDate() throws Exception {
        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-31"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-01"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(new CommitteeMembership(), 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].endDate", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_BEFORE_ROLE_START_DATE);
    }

    /**
     * Test adding a role where the start date is outside of the term of the committee membership.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithStartDateOutsideTerm() throws Exception {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setTermStartDate(Date.valueOf("2009-01-10"));
        committeeMembership.setTermEndDate(Date.valueOf("2009-01-20"));

        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-01"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-17"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(committeeMembership, 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].startDate", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_START_DATE_OUTSIDE_TERM);
    }

    /**
     * Test adding a role where the start date is outside of the term of the committee membership.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithEndDateOutsideTerm() throws Exception {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setTermStartDate(Date.valueOf("2009-01-10"));
        committeeMembership.setTermEndDate(Date.valueOf("2009-01-20"));

        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-15"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-31"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(committeeMembership, 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].endDate", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_END_DATE_OUTSIDE_TERM);
    }

    /**
     * Test adding a role where the role's start/end date overlaps with another role's start/end date 
     * that has the same role type.
     */
    @Test
    public void testAddCommitteeMembershipRoleWhichOverlapsAnotherRole() throws Exception {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        CommitteeMembershipRole newCommitteeMembershipRole = new CommitteeMembershipRole();
        newCommitteeMembershipRole.setMembershipRoleCode("1");
        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-01"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-15"));
        committeeMembership.getMembershipRoles().add(newCommitteeMembershipRole);

        newCommitteeMembershipRole.setStartDate(Date.valueOf("2009-01-10"));
        newCommitteeMembershipRole.setEndDate(Date.valueOf("2009-01-31"));

        assertFalse(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(committeeMembership, 
                newCommitteeMembershipRole)));
        
        assertError("membershipRolesHelper.newCommitteeMembershipRoles[0].membershipRoleCode", 
                KeyConstants.ERROR_COMMITTEE_MEMBERSHIP_ROLE_DUPLICATE);
    }

    /**
     * Test adding a role to a committee having two existing roles.  One of the same role type but without
     * Overlapping dates.  The other of a different role type but with overlapping dates.
     */
    @Test
    public void testAddCommitteeMembershipRoleWithNoErrors() throws Exception {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setTermStartDate(Date.valueOf("2009-01-01"));
        committeeMembership.setTermEndDate(Date.valueOf("2009-01-31"));
        
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode("1");
        committeeMembershipRole.setStartDate(Date.valueOf("2009-01-05"));
        committeeMembershipRole.setEndDate(Date.valueOf("2009-01-14"));
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);
        committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode("2");
        committeeMembershipRole.setStartDate(Date.valueOf("2009-01-19"));
        committeeMembershipRole.setEndDate(Date.valueOf("2009-01-28"));
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);

        committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode("1");
        committeeMembershipRole.setStartDate(Date.valueOf("2009-01-20"));
        committeeMembershipRole.setEndDate(Date.valueOf("2009-01-31"));

        assertTrue(rule.processAddCommitteeMembershipRoleBusinessRules(getAddCommitteeMembershipRoleEvent(committeeMembership, 
                committeeMembershipRole)));
    }
    
    /**
     * this method creates an <code>AddCommitteeMembershipEvent</code>
     * 
     * @param newCommitteeMembership
     * @return event
     * @throws Exception
     */
    private AddCommitteeMembershipEvent getAddCommitteeMembershipEvent(CommitteeMembership newCommitteeMembership) throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        AddCommitteeMembershipEvent event = new AddCommitteeMembershipEvent(Constants.EMPTY_STRING, document, newCommitteeMembership);
        return event;
    }

    /**
     * this method creates an <code>AddCommitteeMembershipRoleEvent</code>
     * 
     * @param newCommitteeMembershipRole
     * @return event
     * @throws Exception
     */
    private AddCommitteeMembershipRoleEvent getAddCommitteeMembershipRoleEvent(CommitteeMembership committeeMembership, 
            CommitteeMembershipRole newCommitteeMembershipRole) throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        document.getCommittee().getCommitteeMemberships().add(committeeMembership);
        AddCommitteeMembershipRoleEvent event = new AddCommitteeMembershipRoleEvent(Constants.EMPTY_STRING, document, 
                newCommitteeMembershipRole, 0);
        return event;
    }
}
