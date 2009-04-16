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
package org.kuali.kra.committee.service;


import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.service.impl.CommitteeMembershipServiceImpl;

/**
 * 
 * The JUnit test class for <code>{@link CommitteeMembershipService}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembershipServiceTest {

    private static final String PERSON_ID = "jtester";
    private static final Integer ROLODEX_ID = 1746;
    private static final Integer MEMBERSHIP_TYPE_CD = 1;
    private static final Date TERM_START_DATE = Date.valueOf("2009-01-01");
    private static final Date TERM_END_DATE = Date.valueOf("2009-01-31");
    private static final String MEMBERSHIP_ROLE_CD_1 = "1";
    private static final String MEMBERSHIP_ROLE_CD_4 = "4";
    private static final String MEMBERSHIP_ROLE_CD_7 = "7";
    private static final Date ROLE_START_DATE = Date.valueOf("2009-01-10");
    private static final Date ROLE_END_DATE = Date.valueOf("2009-01-20");
    private static final String RESEARCH_AREA_CODE_1 = "000001";
    private static final String RESEARCH_AREA_CODE_3 = "000003";
    private static final String RESEARCH_AREA_CODE_5 = "000005";
    
    private CommitteeMembershipServiceImpl committeeMembershipService;

    @Before
    public void setUp() {
        committeeMembershipService = new CommitteeMembershipServiceImpl();
    }
    
    /**
     * This method tests the adding of a Committee Membership
     * @throws Exception
     */
    @Test
    public void testAddCommitteeMembership() throws Exception {
        Committee committee = new Committee();
        committeeMembershipService.addCommitteeMembership(committee, 
                getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        committeeMembershipService.addCommitteeMembership(committee, 
                getMembership(null, ROLODEX_ID, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        
        assertEquals(2, committee.getCommitteeMemberships().size());

        assertEquals(PERSON_ID, committee.getCommitteeMemberships().get(0).getPersonId());
        assertEquals(null, committee.getCommitteeMemberships().get(0).getRolodexId());

        assertEquals(null, committee.getCommitteeMemberships().get(1).getPersonId());
        assertEquals(ROLODEX_ID, committee.getCommitteeMemberships().get(1).getRolodexId());
    }
    
    /**
     * This method tests the deletion of a Committee Membership
     * @throws Exception
     */
    @Test
    public void testDeleteCommitteeMembership() throws Exception {
        Committee committee = new Committee();
        committee.getCommitteeMemberships().add(getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        committee.getCommitteeMemberships().add(getMembership(null, ROLODEX_ID, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        assertEquals(2, committee.getCommitteeMemberships().size());

        committee.getCommitteeMemberships().get(0).setDelete(true);
        committeeMembershipService.deleteCommitteeMembership(committee);
        
        assertEquals(1, committee.getCommitteeMemberships().size());
        assertEquals(null, committee.getCommitteeMemberships().get(0).getPersonId());
        assertEquals(ROLODEX_ID, committee.getCommitteeMemberships().get(0).getRolodexId());
    }

    /**
     * This method tests the adding of a Committee Membership Role
     * @throws Exception
     */
    @Test
    public void testAddCommitteeMembershipRole() throws Exception {
        Committee committee = new Committee();
        committee.getCommitteeMemberships().add(getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        committeeMembershipService.addCommitteeMembershipRole(committee, 0, getRole(MEMBERSHIP_ROLE_CD_1, ROLE_START_DATE, ROLE_END_DATE));
        
        assertEquals(1, committee.getCommitteeMemberships().get(0).getMembershipRoles().size());
        assertEquals(MEMBERSHIP_ROLE_CD_1, committee.getCommitteeMemberships().get(0).getMembershipRoles().get(0).getMembershipRoleCode());
        assertEquals(ROLE_END_DATE, committee.getCommitteeMemberships().get(0).getMembershipRoles().get(0).getEndDate());
    }

    /**
     * This method tests the deletion of a Committee Membership Role
     * @throws Exception
     */
    @Test
    public void testDeleteCommitteeMembershipRole() throws Exception {
        Committee committee = new Committee();
        CommitteeMembership committeeMembership = getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE);
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_1, null, null));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_4, null, null));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_7, null, null));
        committee.getCommitteeMemberships().add(committeeMembership);
        assertEquals(3, committee.getCommitteeMemberships().get(0).getMembershipRoles().size());

        committeeMembershipService.deleteCommitteeMembershipRole(committee, 0, 1);
        assertEquals(2, committee.getCommitteeMemberships().get(0).getMembershipRoles().size());
        assertEquals(MEMBERSHIP_ROLE_CD_1, committee.getCommitteeMemberships().get(0).getMembershipRoles().get(0).getMembershipRoleCode());
        assertEquals(MEMBERSHIP_ROLE_CD_7, committee.getCommitteeMemberships().get(0).getMembershipRoles().get(1).getMembershipRoleCode());
    }

    /**
     * This method tests the adding of a Committee Membership Expertise
     * @throws Exception
     */
    @Test
    public void testAddCommitteeMembershipExpertise() throws Exception {
        CommitteeMembership committeeMembership = getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE);
        committeeMembershipService.addCommitteeMembershipExpertise(committeeMembership, getResearchAreas());
        
        assertEquals(3, committeeMembership.getMembershipExpertise().size());
        assertEquals(RESEARCH_AREA_CODE_1, committeeMembership.getMembershipExpertise().get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_3, committeeMembership.getMembershipExpertise().get(1).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_5, committeeMembership.getMembershipExpertise().get(2).getResearchAreaCode());
    }

    /**
     * This method tests the deletion of a Committee Membership Expertise
     * @throws Exception
     */
    @Test
    public void testDeleteCommitteeMembershipExpertise() throws Exception {
        Committee committee = new Committee();
        CommitteeMembership committeeMembership = getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE);
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_1));
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_3));
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_5));
        committee.getCommitteeMemberships().add(committeeMembership);
        assertEquals(3, committee.getCommitteeMemberships().get(0).getMembershipExpertise().size());

        committeeMembershipService.deleteCommitteeMembershipExpertise(committee, 0, 1);
        assertEquals(2, committee.getCommitteeMemberships().get(0).getMembershipExpertise().size());
        assertEquals(RESEARCH_AREA_CODE_1, committee.getCommitteeMemberships().get(0).getMembershipExpertise().get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_5, committee.getCommitteeMemberships().get(0).getMembershipExpertise().get(1).getResearchAreaCode());
    }
    
    /**
     * This method creates and returns a new committee membership from the person.
     * @return CommitteeMembership
     */
    public CommitteeMembership getCommitteeMembershipPerson() {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(PERSON_ID);
        committeeMembership.setTermStartDate(Date.valueOf("2009-01-01"));
        committeeMembership.setTermEndDate(Date.valueOf("2009-01-31"));
        committeeMembership.setMembershipTypeCode(1);
        return committeeMembership;
    }
    
    /**
     * This method creates and returns a new committee membership from the rolodex.
     * @return CommitteeMembeship
     */
    public CommitteeMembership getCommitteeMembershipRolodex() {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setRolodexId(ROLODEX_ID);
        return committeeMembership;
    }

    /**
     * This method creates and returns a <code>CommitteeMembership</code>.
     * 
     * @param personID
     * @param rolodexID
     * @param membershipTypeCode
     * @param termStartDate
     * @param termEndDate
     * @return CommitteeMembership
     */
    private CommitteeMembership getMembership(String personID, Integer rolodexID, Integer membershipTypeCode, Date termStartDate, Date termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setRolodexId(rolodexID);
        committeeMembership.setMembershipTypeCode(membershipTypeCode);
        committeeMembership.setTermStartDate(termStartDate);
        committeeMembership.setTermEndDate(termEndDate);
        return committeeMembership;
    }

    /**
     * This method creates and returns a <code>CommitteeMembershipRole</code>.
     *
     * @param membershipRoleCode
     * @param startDate
     * @param endDate
     * @return CommitteeMembershipRole
     */
    private CommitteeMembershipRole getRole(String membershipRoleCode, Date startDate, Date endDate) {
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode(membershipRoleCode);
        committeeMembershipRole.setStartDate(startDate);
        committeeMembershipRole.setEndDate(endDate);
        return committeeMembershipRole;
    }
    
    /**
     * This method creates and returns a <code>CommitteeMembershipExpertise</code>.
     * 
     * @param researchAreaCode
     * @return CommitteeMembershipExpertise
     */
    private CommitteeMembershipExpertise getExpertise(String researchAreaCode) {
        CommitteeMembershipExpertise committeeMembershipExpertise = new CommitteeMembershipExpertise();
        committeeMembershipExpertise.setResearchAreaCode(researchAreaCode);
        return committeeMembershipExpertise;
        
    }

    /**
     * This method creates and returns a collection of <code>ResearchArea</code>s.
     * 
     * @return Collection&lt;ResearchArea&gt;
     */
    private Collection<ResearchArea>  getResearchAreas() {
        Collection<ResearchArea> researchAreas = new ArrayList();
        researchAreas.add(new ResearchArea(RESEARCH_AREA_CODE_1, null, null));
        researchAreas.add(new ResearchArea(RESEARCH_AREA_CODE_3, null, null));
        researchAreas.add(new ResearchArea(RESEARCH_AREA_CODE_5, null, null));
        return researchAreas;
    }
}