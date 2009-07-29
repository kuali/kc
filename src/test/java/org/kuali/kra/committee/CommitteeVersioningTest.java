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
package org.kuali.kra.committee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.service.impl.VersioningServiceImpl;

public class CommitteeVersioningTest implements Serializable {

    private transient VersioningService versioningService;
    private Committee originalCommittee;

    
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
        List<Protocol> protocols = new ArrayList<Protocol>();
        protocols.add(createProtocol());
        committeeSchedule.setProtocols(protocols);
        
        committeeSchedule.setScheduledDate(Date.valueOf(date));
        return committeeSchedule;
    }

    /**
     * This method...
     * @return
     */
    private Protocol createProtocol() {
        Protocol p = new Protocol() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

            @Override
            protected ProtocolLocationService getProtocolLocationService() {
               return new ProtocolLocationService() {
                public void addDefaultProtocolLocation(Protocol protocol) {}
                public void addProtocolLocation(Protocol protocol, ProtocolLocation protocolLocation) {}
                public void clearProtocolLocationAddress(Protocol protocol, int lineNumber) { }
               };
            }
            
        };
        p.setProtocolNumber("1001");
        p.setSequenceNumber(1);
        p.setProtocolId(1L);
        return p;
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
        for (CommitteeResearchArea versionedResearchArea : versionedCommittee.getCommitteeResearchAreas()) {
            versionedResearchAreaCodes.add(versionedResearchArea.getResearchAreaCode());
        }
        for (CommitteeResearchArea originalResearchArea : originalCommittee.getCommitteeResearchAreas()) {
            assertTrue(versionedResearchAreaCodes.contains(originalResearchArea.getResearchAreaCode()));    
        }
        
        // Committee Membership
        assertEquals(originalCommittee.getCommitteeMemberships().size(), versionedCommittee.getCommitteeMemberships().size());
        Map<String, Integer> versionedMembershipMapping = new HashMap<String, Integer>();
        for (CommitteeMembership versionedMembership : versionedCommittee.getCommitteeMemberships()) {
            versionedMembershipMapping.put(versionedMembership.getPersonName(), versionedCommittee.getCommitteeMemberships().indexOf(versionedMembership));
        }
        for (CommitteeMembership originalMembership : originalCommittee.getCommitteeMemberships()) {
            assertTrue(versionedMembershipMapping.containsKey(originalMembership.getPersonName()));
            Integer index = versionedMembershipMapping.get(originalMembership.getPersonName());
            verifyMembershipRoleExpertiseVersioning(originalMembership, versionedCommittee.getCommitteeMemberships().get(index));
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
            Protocol pRef = originalCommittee.getCommitteeSchedules().get(0).getProtocols().get(0);
            Protocol pCheck = versionedCommittee.getCommitteeSchedules().get(0).getProtocols().get(0);
            assertTrue(pRef != pCheck);
            assertEquals(pRef.getProtocolId(), pCheck.getProtocolId());
        }
    }

    private void verifyMembershipRoleExpertiseVersioning(CommitteeMembership originalMembership,
            CommitteeMembership versionedMembership) {
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
