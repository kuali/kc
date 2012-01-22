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
package org.kuali.kra.kim.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.krad.service.BusinessObjectService;

@RunWith(JMock.class)
public class ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceTest {
    private ActiveCommitteeMemberOnProtocolDerivedRoleTypeServiceImpl derivedRole;
    private Mockery context = new JUnit4Mockery();
    
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
        protocol.setProtocolSubmissions(submissionList);
        
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
        committee.setCommitteeMemberships(members);
        
        return committee;
    }
    
    private CommitteeMembership createCommitteeMember(String personId, String personName) {
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId(personId);
        member.setPersonName(personName);

        return member;
    }
}
