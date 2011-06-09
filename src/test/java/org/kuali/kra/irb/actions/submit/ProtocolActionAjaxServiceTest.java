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
package org.kuali.kra.irb.actions.submit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Test the ProtocolActionAjaxService implementation.
 */
@RunWith(JMock.class)
public class ProtocolActionAjaxServiceTest {

    private ProtocolActionAjaxServiceImpl protocolActionAjaxService;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() {
        protocolActionAjaxService = new ProtocolActionAjaxServiceImpl();
    }
    
    /*
     * Verify that the returned string is formatted correctly.
     */
    @Test
    public void testValidCommitteeDates() {
        final List<KeyLabelPair> list = new ArrayList<KeyLabelPair>();
        list.add(new KeyLabelPair("0", "dog"));
        list.add(new KeyLabelPair("1", "cat"));
        
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            one(committeeService).getAvailableCommitteeDates("foo"); will(returnValue(list));
        }});
        protocolActionAjaxService.setCommitteeService(committeeService);
        
        String s = protocolActionAjaxService.getValidCommitteeDates("foo");
        assertEquals("0;dog;1;cat", s);
    }
    
    
    
    /*
     * Verify that the protocol personnel are filtered out and the returned string is formatted correctly.
     */
    @Test
    public void testReviewers() {
        final List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        members.add(createMember("dn", "Don", null));
        members.add(createMember("nncy", "Nancy", 1));
        members.add(createMember(null, "Joe", 2));
        members.add(createMember(null, "Joanna", 5));
        
        final HashMap<String, String> hm1 = new HashMap<String, String>();
        hm1.put("protocolId", "p1");
        
        final HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put("protocolId", "p2");
        
        Protocol protocol1 = new Protocol() {

            private static final long serialVersionUID = -1273061983131550371L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        
        final List<Protocol> l1 = Arrays.asList(protocol1);
       
        
        Protocol protocol2 = new Protocol() {

            private static final long serialVersionUID = -1273061983131550372L;
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        
        final List<Protocol> l2 = Arrays.asList(protocol2);
        
        
        ProtocolPerson pp1 = createProtocolPerson("dn", 10);
        ProtocolPerson pp2 = createProtocolPerson("nncy", null);
        ProtocolPerson pp3 = createProtocolPerson(null, 1);
        ProtocolPerson pp4 = createProtocolPerson("", 2);
        ProtocolPerson pp5 = createProtocolPerson(null, 5);
        
        
        protocol1.getProtocolPersons().add(pp1);
        protocol1.getProtocolPersons().add(pp2);
        
        
        protocol2.getProtocolPersons().add(pp3);
        protocol2.getProtocolPersons().add(pp4);
        protocol2.getProtocolPersons().add(pp5);
       
        
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            allowing(committeeService).getAvailableMembers("foo", "0"); will(returnValue(members));
        }});
        
        final BusinessObjectService businessObjectService1 = context.mock(BusinessObjectService.class, "name1");
        context.checking(new Expectations() {{
            one(businessObjectService1).findMatching(Protocol.class, hm1); will(returnValue(l1));
        }});
        
        final BusinessObjectService businessObjectService2 = context.mock(BusinessObjectService.class, "name2");
        context.checking(new Expectations() {{
            allowing(businessObjectService2).findMatching(Protocol.class, hm2); will(returnValue(l2));
        }});
        
        protocolActionAjaxService.setCommitteeService(committeeService);
        protocolActionAjaxService.setBusinessObjectService(businessObjectService1);
        
        String s = protocolActionAjaxService.getReviewers("p1", "foo", "0");
        assertEquals("2;Joe;Y;5;Joanna;Y", s);
        
        protocolActionAjaxService.setBusinessObjectService(businessObjectService2);
        
        s = protocolActionAjaxService.getReviewers("p2", "foo", "0");
        assertEquals("dn;Don;N;nncy;Nancy;N", s);
        
        // empty out the protocol personnel
        protocol2.getProtocolPersons().clear();
        s = protocolActionAjaxService.getReviewers("p2", "foo", "0");
        assertEquals("dn;Don;N;nncy;Nancy;N;2;Joe;Y;5;Joanna;Y", s);
        
    }
    
   

    /*
     * Verify that the returned string is formatted correctly.
     */
    @Test
    public void testReviewerTypes() {
        final Collection<ProtocolReviewerType> reviewerTypes = new ArrayList<ProtocolReviewerType>();
        reviewerTypes.add(createReviewerType("1", "primary"));
        reviewerTypes.add(createReviewerType("2", "secondary"));
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findAll(ProtocolReviewerType.class); will(returnValue(reviewerTypes));
        }});
        protocolActionAjaxService.setBusinessObjectService(businessObjectService);
        
        String s = protocolActionAjaxService.getReviewerTypes();
        assertEquals("1;primary;2;secondary", s);
    }


    private ProtocolReviewerType createReviewerType(String typeCode, String description) {
        ProtocolReviewerType reviewerType = new ProtocolReviewerType();
        reviewerType.setReviewerTypeCode(typeCode);
        reviewerType.setDescription(description);
        return reviewerType;
    }

    private CommitteeMembership createMember(String personId, String personName, Integer rolodexId) {
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId(personId);
        member.setPersonName(personName);
        member.setRolodexId(rolodexId);
        return member;
    }
    
    private ProtocolPerson createProtocolPerson(String personId, Integer rolodexId) {
        ProtocolPerson pp = new ProtocolPerson();
        pp.setPersonId(personId);
        pp.setRolodexId(rolodexId);
        return pp;
    }
    
}