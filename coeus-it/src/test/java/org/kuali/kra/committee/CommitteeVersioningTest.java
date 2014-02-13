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
package org.kuali.kra.committee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.*;
import org.kuali.kra.common.committee.bo.CommitteeMembershipBase;
import org.kuali.kra.common.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.common.committee.bo.CommitteeResearchAreaBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommitteeVersioningTest extends KcIntegrationTestBase {

    private VersioningService versioningService;
    private transient Committee originalCommittee;

    
    @Before
    public void setUp() {
        versioningService  = new VersioningServiceImpl();
        originalCommittee = createCommittee("Test", "The Verion Test Committee");
    }
    
    @After
    public void tearDown() {
        versioningService = null;
        originalCommittee = null;
    }
    
    @Test 
    public void testCommitteeVersioning() throws Exception {
        Committee versionedCommittee = (Committee) versioningService.createNewVersion(originalCommittee);

        verifyVersioning(originalCommittee, versionedCommittee);

    }
    
    @Test
    public void testCommitteeResearchAreaVersioning() throws Exception {
        originalCommittee.getCommitteeResearchAreas().add(createCommitteeResearchArea("RA74"));
        originalCommittee.getCommitteeResearchAreas().add(createCommitteeResearchArea("RA34"));
        originalCommittee.getCommitteeResearchAreas().add(createCommitteeResearchArea("RA86"));
        
        Committee versionedCommittee = (Committee) versioningService.createNewVersion(originalCommittee);

        verifyVersioning(originalCommittee, versionedCommittee);
    }
    
    @Test
    public void testCommitteeMembershipVersioning() throws Exception {
        originalCommittee.getCommitteeMemberships().add(createCommitteeMembership("John Doe"));

        CommitteeMembership committeeMember2 = createCommitteeMembership("Sahra White");
        committeeMember2.getMembershipRoles().add(createCommitteeMembershipRole("Chair"));
        committeeMember2.getMembershipRoles().add(createCommitteeMembershipRole("Member"));
        committeeMember2.getMembershipExpertise().add(createCommitteeMembershipExpertise("E439"));
        committeeMember2.getMembershipExpertise().add(createCommitteeMembershipExpertise("E295"));
        committeeMember2.getMembershipExpertise().add(createCommitteeMembershipExpertise("E571"));
        originalCommittee.getCommitteeMemberships().add(committeeMember2);
        
        CommitteeMembership committeeMember3 = createCommitteeMembership("Bob Tester");
        committeeMember3.getMembershipRoles().add(createCommitteeMembershipRole("Vice Chair"));
        committeeMember3.getMembershipRoles().add(createCommitteeMembershipRole("Member"));
        committeeMember3.getMembershipRoles().add(createCommitteeMembershipRole("IRB Administrator"));
        committeeMember3.getMembershipExpertise().add(createCommitteeMembershipExpertise("E555"));
        committeeMember3.getMembershipExpertise().add(createCommitteeMembershipExpertise("E183"));
        originalCommittee.getCommitteeMemberships().add(committeeMember3);
        
        Committee versionedCommittee = (Committee) versioningService.createNewVersion(originalCommittee);

        verifyVersioning(originalCommittee, versionedCommittee);
    }

    @Test
    public void testCommitteeScheduleVersioning() throws Exception {
        originalCommittee.getCommitteeSchedules().add(createCommitteeSchedule("2009-12-25"));
        originalCommittee.getCommitteeSchedules().get(0).setCommittee(originalCommittee);
//        originalCommittee.getCommitteeSchedules().add(createCommitteeSchedule("2009-07-13"));
//        originalCommittee.getCommitteeSchedules().add(createCommitteeSchedule("2009-11-11"));
        
        Committee versionedCommittee = (Committee) versioningService.createNewVersion(originalCommittee);

        verifyVersioning(originalCommittee, versionedCommittee);
    }
    
    private Committee createCommittee(String committeeId, String committeeName) {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        committee.setCommitteeName(committeeName);
        return committee;
    }
    
    private CommitteeResearchArea createCommitteeResearchArea(String researchAreaCode) {
        CommitteeResearchArea committeeResearchArea = new CommitteeResearchArea();
        committeeResearchArea.setResearchAreaCode(researchAreaCode);
        return committeeResearchArea;
    }
    
    private CommitteeMembership createCommitteeMembership(String name) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonName(name);
        return committeeMembership;
    }
    
    private CommitteeMembershipRole createCommitteeMembershipRole(String membershipRoleCode) {
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode(membershipRoleCode);
        return committeeMembershipRole;
    }
    
    private CommitteeMembershipExpertise createCommitteeMembershipExpertise(String researchAreaCode) {
        CommitteeMembershipExpertise committeeMembershipExpertise = new CommitteeMembershipExpertise();
        committeeMembershipExpertise.setResearchAreaCode(researchAreaCode);
        return committeeMembershipExpertise;
    }
    
    private CommitteeSchedule createCommitteeSchedule(String date) {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        List<ProtocolBase> protocols = new ArrayList<ProtocolBase>();
        protocols.add(new Protocol());
        committeeSchedule.setProtocols(protocols);
        
        committeeSchedule.setScheduledDate(Date.valueOf(date));
        committeeSchedule.setTime(Timestamp.valueOf(date + " 00:00:00"));
        return committeeSchedule;
    }

    private void verifyVersioning(Committee originalCommittee, Committee versionedCommittee) {
        // Committee
        assertEquals(originalCommittee.getCommitteeId(), versionedCommittee.getCommitteeId());
        assertEquals(originalCommittee.getCommitteeName(), versionedCommittee.getCommitteeName());
        Integer expectedSequenceNumber = originalCommittee.getSequenceNumber() + 1;
        assertEquals(expectedSequenceNumber, versionedCommittee.getSequenceNumber());
        
        // Committee Research Area
        assertEquals(originalCommittee.getCommitteeResearchAreas().size(), versionedCommittee.getCommitteeResearchAreas().size());
        List<String> versionedResearchAreaCodes = new ArrayList<String>();
        for (CommitteeResearchAreaBase versionedResearchArea : versionedCommittee.getCommitteeResearchAreas()) {
            versionedResearchAreaCodes.add(versionedResearchArea.getResearchAreaCode());
        }
        for (CommitteeResearchAreaBase originalResearchArea : originalCommittee.getCommitteeResearchAreas()) {
            assertTrue(versionedResearchAreaCodes.contains(originalResearchArea.getResearchAreaCode()));    
        }
        
        // Committee Membership
        assertEquals(originalCommittee.getCommitteeMemberships().size(), versionedCommittee.getCommitteeMemberships().size());
        Map<String, Integer> versionedMembershipMapping = new HashMap<String, Integer>();
        for (CommitteeMembershipBase versionedMembership : versionedCommittee.getCommitteeMemberships()) {
            versionedMembershipMapping.put(versionedMembership.getPersonName(), versionedCommittee.getCommitteeMemberships().indexOf(versionedMembership));
        }
        for (CommitteeMembershipBase originalMembership : originalCommittee.getCommitteeMemberships()) {
            assertTrue(versionedMembershipMapping.containsKey(originalMembership.getPersonName()));
            Integer index = versionedMembershipMapping.get(originalMembership.getPersonName());
            verifyMembershipRoleExpertiseVersioning((CommitteeMembership) originalMembership, (CommitteeMembership) versionedCommittee.getCommitteeMemberships().get(index));
        }
        
        // Committee Schedule
        assertEquals(originalCommittee.getCommitteeSchedules().size(), versionedCommittee.getCommitteeSchedules().size());
        List<Date> versionedScheduledDates = new ArrayList<Date>();
        for (CommitteeSchedule versionedSchedule: versionedCommittee.getCommitteeSchedules()) {
            versionedScheduledDates.add(versionedSchedule.getScheduledDate());
        }
        for (CommitteeSchedule originalSchedule : originalCommittee.getCommitteeSchedules()) {
            assertTrue(versionedScheduledDates.contains(originalSchedule.getScheduledDate()));    
        }
        
        if(originalCommittee.getCommitteeSchedules().size() > 0) {
            assertEquals(originalCommittee.getCommitteeSchedules().get(0).getProtocols().size(), versionedCommittee.getCommitteeSchedules().get(0).getProtocols().size());
            Protocol pRef = (Protocol) originalCommittee.getCommitteeSchedules().get(0).getProtocols().get(0);
            Protocol pCheck = (Protocol) versionedCommittee.getCommitteeSchedules().get(0).getProtocols().get(0);
            assertTrue(pRef != pCheck);
            assertEquals(pRef.getProtocolId(), pCheck.getProtocolId());
        }
    }

    private void verifyMembershipRoleExpertiseVersioning(CommitteeMembership originalMembership, CommitteeMembership versionedMembership) {
        assertEquals(originalMembership.getMembershipRoles().size(), versionedMembership.getMembershipRoles().size());
        assertEquals(originalMembership.getMembershipExpertise().size(), versionedMembership.getMembershipExpertise().size());
        List<String> versionedRoleCodes = new ArrayList<String>();
        for (CommitteeMembershipRole versionedMembershipRole : versionedMembership.getMembershipRoles()) {
            versionedRoleCodes.add(versionedMembershipRole.getMembershipRoleCode());
        }
        for (CommitteeMembershipRole originalRoleCode : originalMembership.getMembershipRoles()) {
            assertTrue(versionedRoleCodes.contains(originalRoleCode.getMembershipRoleCode()));
        }
    }
}
