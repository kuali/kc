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
package org.kuali.kra.irb.actions.submit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
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
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
     * Verify that the returned string is formatted correctly.
     */
    @Test
    public void testReviewers() {
        final List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        members.add(createMember("1", "Don"));
        members.add(createMember("2", "Nancy"));
        
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            one(committeeService).getAvailableMembers("foo", "0"); will(returnValue(members));
        }});
        protocolActionAjaxService.setCommitteeService(committeeService);
        
        String s = protocolActionAjaxService.getReviewers("foo", "0");
        assertEquals("1;Don;2;Nancy", s);
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

    private CommitteeMembership createMember(String personId, String personName) {
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId(personId);
        member.setPersonName(personName);
        return member;
    }
}
