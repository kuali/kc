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
package org.kuali.kra.kim.service.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(JMock.class)
public class ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceTest {
    private ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceImpl derivedRole;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Test
    public void testHasApplicationRole() {
        String protocolNumber = "1107010007";
        final Map<String, Object> keymap = new HashMap<String, Object>();
        keymap.put("protocolNumber", protocolNumber);
        
        String principalId = "10000000005";
        String namespaceCode = "KC-PROTOCOL";
        String roleName = PermissionConstants.VIEW_PROTOCOL;
        Map<String, String> qualifications = new HashMap<String, String>();
        qualifications.put(KcKimAttributes.PROTOCOL, protocolNumber);
        
        final Protocol protocol = createProtocolWithSubmission(protocolNumber);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class, "bos1");
        context.checking(new Expectations() {{
            allowing(businessObjectService).findByPrimaryKey(Protocol.class, keymap); will(returnValue(protocol));
        }});

        derivedRole = new ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceImpl(){
            @Override
            public BusinessObjectService getBusinessObjectService() {
                return businessObjectService;
            }
        };

        
        assertTrue(derivedRole.hasDerivedRole(principalId, null, namespaceCode, roleName,new HashMap<String,String>(qualifications)));
    }
    
    private Protocol createProtocolWithSubmission(String protocolNumber) {
        Protocol protocol = new Protocol() {
            private static final long serialVersionUID = -1273061983131550373L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }    
        };
        
        protocol.setProtocolNumber(protocolNumber);
        ProtocolSubmission submission = createProtocolSubmission();
        List<ProtocolSubmission> submissionList = new ArrayList<ProtocolSubmission>();
        submissionList.add(submission);
        
        protocol.setProtocolSubmission(createProtocolSubmission());
        protocol.setProtocolSubmissions((List)submissionList);
        
        return protocol;
    }
   
    private ProtocolSubmission createProtocolSubmission() {
        ProtocolSubmission submission = new ProtocolSubmission() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }             
        };
        
        submission.setCommittee(createCommittee());
        
        return submission;
    }
    
    private Committee createCommittee() {
        Committee committee = new Committee();
        committee.setCommitteeName("KC IRB 1");
        
        List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        members.add(createCommitteeMember("10000000005", "chew"));
        members.add(createCommitteeMember("10000000006", "woods"));
        members.add(createCommitteeMember("10000000007", "oblood"));
        members.add(createCommitteeMember("10000000008", "cate"));
        members.add(createCommitteeMember("10000000004", "majors"));
        committee.setCommitteeMemberships((List)members);
        
        return committee;
    }
    
    private CommitteeMembership createCommitteeMember(String personId, String personName) {
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId(personId);
        member.setPersonName(personName);

        return member;
    }
}
