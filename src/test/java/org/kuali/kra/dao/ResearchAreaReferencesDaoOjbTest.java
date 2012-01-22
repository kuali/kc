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
package org.kuali.kra.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.research.ProtocolResearchAreaService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class ResearchAreaReferencesDaoOjbTest extends KcUnitTestBase {
    
    private BusinessObjectService businessObjectService;
    private CommitteeMembershipService committeeMembershipService;
    private CommitteeService committeeService;
    private ResearchAreaReferencesDao raDao;
    private ProtocolResearchAreaService protocolResearchAreaService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        this.committeeService = KraServiceLocator.getService(CommitteeService.class);
        this.protocolResearchAreaService = KraServiceLocator.getService(ProtocolResearchAreaService.class);
        this.committeeMembershipService = KraServiceLocator.getService(CommitteeMembershipService.class);
        this.raDao = KraServiceLocator.getService(ResearchAreaReferencesDao.class);
    }
    
    @After 
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    private void createandSaveCommittee(String researchAreaCode) throws WorkflowException {        
        ResearchArea researchArea = new ResearchArea(researchAreaCode, "000001", "Sample research area", true);
        this.businessObjectService.save(researchArea);
        List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();
        researchAreas.add(researchArea);
       
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument("444");
        Committee committee = committeeDocument.getCommittee();
        
        this.committeeService.addResearchAreas(committee, researchAreas);
        this.businessObjectService.save(committeeDocument);
        this.businessObjectService.save(committee);
    }
    
    private void createandSaveCommitteeWithMembershipExpertise(String researchAreaCode) throws WorkflowException {
        ResearchArea researchArea = new ResearchArea(researchAreaCode, "000001", "Sample research area", true);
        this.businessObjectService.save(researchArea);
        List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();
        researchAreas.add(researchArea);
       
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument("555");
        Committee committee = committeeDocument.getCommittee();
        
        CommitteeMembership committeeMembership = this.createMembership("jtester", "joe tester", null, "1", "2009-01-11", "2009-01-20");
        addRole(committeeMembership, "1", "2009-01-11", "2009-01-20");
        this.committeeMembershipService.addCommitteeMembershipExpertise(committeeMembership, researchAreas);
        this.committeeMembershipService.addCommitteeMembership(committee, committeeMembership);
        
        this.businessObjectService.save(committeeDocument);
        this.businessObjectService.save(committee);
    }
 
    
    private void createAndSaveProtocol(String researchAreaCode) throws WorkflowException {
        
        ResearchArea researchArea = new ResearchArea(researchAreaCode, "000001", "Sample research area", true);
        this.businessObjectService.save(researchArea);
        List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();
        researchAreas.add(researchArea);
        
        String newProtocolNumber = "123456132";
        Protocol protocol = ProtocolFactory.createProtocolDocument(newProtocolNumber).getProtocol();
        
        this.protocolResearchAreaService.addProtocolResearchArea(protocol, researchAreas);
        this.businessObjectService.save(protocol);
    }
    
    
    
   
    /**
     * This method creates a CommitteeMembership instance.
     * @param personID
     * @param name
     * @param rolodexID
     * @param membershipTypeCode
     * @param termStartDate
     * @param termEndDate
     * @return
     */
    private CommitteeMembership createMembership(String personID, String name, Integer rolodexID, String membershipTypeCode,
            String termStartDate, String termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setPersonName(name);
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
     * @throws Exception 
     * 
     */
    @Test 
    public void testIsResearchAreaReferencedByAnyCommittee() throws Exception {
        assertFalse(this.raDao.isResearchAreaReferencedByAnyCommittee("RATEST"));
        this.createandSaveCommittee("RATEST");
        assertTrue(this.raDao.isResearchAreaReferencedByAnyCommittee("RATEST"));
        assertFalse(this.raDao.isResearchAreaReferencedByAnyCommittee("RANDOM"));
    }
    
    /**
     * @throws Exception 
     * 
     */
    @Test 
    public void testIsResearchAreaReferencedByAnyProtocol() throws Exception {
        assertFalse(this.raDao.isResearchAreaReferencedByAnyProtocol("RATEST1"));
        this.createAndSaveProtocol("RATEST1");
        assertTrue(this.raDao.isResearchAreaReferencedByAnyProtocol("RATEST1"));
        assertFalse(this.raDao.isResearchAreaReferencedByAnyProtocol("RANDOM"));
    }
    
    /**
     * @throws Exception 
     * 
     */
    @Test 
    public void testIsResearchAreaReferencedByAnyCommitteeMember() throws Exception {
        assertFalse(this.raDao.isResearchAreaReferencedByAnyCommitteeMember("RATEST2"));
        this.createandSaveCommitteeWithMembershipExpertise("RATEST2");
        assertTrue(this.raDao.isResearchAreaReferencedByAnyCommitteeMember("RATEST2"));
        assertFalse(this.raDao.isResearchAreaReferencedByAnyCommitteeMember("RANDOM"));
    }


}
