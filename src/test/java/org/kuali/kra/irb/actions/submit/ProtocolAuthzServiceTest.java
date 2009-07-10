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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolAuthzServiceTest extends ProtocolActionServiceTestBase {
    
    // TODO : a quick change to make isActionAllowed tests work.  isActionAllowed is modified to include canperform check 
    // should consider to refactor this with protocolactionservicetest because lots of shared code.
    
    private static final String MODIFY_ANY_PROTOCOL = "MODIFY_ANY_PROTOCOL";
    
    private static final String SUBMIT_PROTOCOL = "SUBMIT_PROTOCOL";
    
    private static final String PERFORM_IRB_ACTIONS_ON_PROTO = "PERFORM_IRB_ACTIONS_ON_PROTO";
    
    private static final String DEFAULT_ORGANIZATION_UNIT = "000001";
    
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    private UnitAuthorizationService unitAuthorizationService;
    
    private ProtocolAuthorizationService protocolAuthorizationService;
    private BusinessObjectService businessObjectService;
    
    private ProtocolDao dao;

    @Before
    public void setUp() {
        context  = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();
        
        unitAuthorizationService = context.mock(UnitAuthorizationService.class);
        protocolActionService.setUnitAuthorizationService(unitAuthorizationService);
        
        protocolAuthorizationService = context.mock(ProtocolAuthorizationService.class);
        protocolActionService.setProtocolAuthorizationService(protocolAuthorizationService);
        
        protocol.setProtocolNumber("001Z"); 
        
        businessObjectService = context.mock(BusinessObjectService.class);
        protocolActionService.setBusinessObjectService(businessObjectService);
        
        dao = context.mock(ProtocolDao.class);
        protocolActionService.setProtocolDao(dao);

    }  
    
    @Test
    public void permissionLeadUnitTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(true));
        }});

        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");    
        protocol.setProtocolStatusCode("200");         
        assertTrue(protocolActionService.isActionAllowed("105", protocol));
        assertTrue(protocolActionService.isActionAllowed("106", protocol));
        assertTrue(protocolActionService.isActionAllowed("108", protocol));
        assertTrue(protocolActionService.isActionAllowed("114", protocol));
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.isActionAllowed("115", protocol));
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("116", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");          
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
    }
    
    @Test
    public void permissionToSubmitTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(true));
        }});

        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");    
        protocol.setProtocolStatusCode("200");         
        assertTrue(protocolActionService.isActionAllowed("105", protocol));
        assertTrue(protocolActionService.isActionAllowed("106", protocol));
        assertTrue(protocolActionService.isActionAllowed("108", protocol));
        assertTrue(protocolActionService.isActionAllowed("114", protocol));
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.isActionAllowed("115", protocol));
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("116", protocol));
        assertTrue(protocolActionService.isActionAllowed("101", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");          
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
    }
    
    @Test
    public void permissionAsCommitteeMemberWithScheduleIdTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId("101");
        ps.setCommitteeId(null);
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");          
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        mockSubmissionTrue("111");
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("207", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("108");
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("301", protocol));
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");
        mockSubmissionTrue("109");       
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("300", protocol));
        assertTrue(protocolActionService.isActionAllowed("302", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrueCondt1();
        mockProtocolDaoCondt1();        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("305", protocol));
        assertTrue(protocolActionService.isActionAllowed("306", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null        
        ProtocolReviewer pr = new ProtocolReviewer();
        List<ProtocolReviewer> list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);               
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("205", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("206", protocol));
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionCondt4(true);
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.isActionAllowed("109", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");        
        assertTrue(protocolActionService.isActionAllowed("201", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        protocol.getProtocolSubmission().setScheduleId("NotNULL");
        assertTrue(protocolActionService.isActionAllowed("200", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("5");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("210", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        
        pr = new ProtocolReviewer();
        list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("6");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("208", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionTypeCode("112");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("209", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.isActionAllowed("211", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);        
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        mockSubmissionTrue("114");
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.isActionAllowed("212", protocol));
    }    
    
    @Test
    public void permissionAsCommitteeMemberWithCommitteeIdTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId(null);
        ps.setCommitteeId("101");
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");          
        assertTrue(protocolActionService.isActionAllowed("303", protocol));
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        mockSubmissionTrue("111");
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("207", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("108");
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("301", protocol));
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");
        mockSubmissionTrue("109");       
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("300", protocol));
        assertTrue(protocolActionService.isActionAllowed("302", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrueCondt1();
        mockProtocolDaoCondt1();        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.isActionAllowed("305", protocol));
        assertTrue(protocolActionService.isActionAllowed("306", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null        
        ProtocolReviewer pr = new ProtocolReviewer();
        List<ProtocolReviewer> list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);               
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("205", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("206", protocol));
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionCondt4(true);
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.isActionAllowed("109", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");        
        assertTrue(protocolActionService.isActionAllowed("201", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        protocol.getProtocolSubmission().setScheduleId("NotNULL");
        assertTrue(protocolActionService.isActionAllowed("200", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("5");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("210", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        
        pr = new ProtocolReviewer();
        list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("6");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("208", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionTypeCode("112");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.isActionAllowed("209", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.isActionAllowed("211", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);        
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        mockSubmissionTrue("114");
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.isActionAllowed("212", protocol));
    }     
    
    @Test
    public void permissionSpecialCaseTest() {
        protocol.setLeadUnitNumber("0001");
        mockSession();
        
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setScheduleId("101");
        ps.setCommitteeId(null);
        protocol.setProtocolSubmission(ps);
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", MODIFY_ANY_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(protocolAuthorizationService).hasPermission(null, protocol, SUBMIT_PROTOCOL);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, "0001", PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(false));
        }});
        
        context.checking(new Expectations() {{
            allowing(unitAuthorizationService).hasPermission(null, DEFAULT_ORGANIZATION_UNIT, PERFORM_IRB_ACTIONS_ON_PROTO);will(returnValue(true));
        }});
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("202", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.isActionAllowed("203", protocol));
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.isActionAllowed("304", protocol));
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        assertTrue(protocolActionService.isActionAllowed("204", protocol));
    } 
    
    private void mockSession() {
        final UniversalUser person = new UniversalUser();
        person.setPersonUniversalIdentifier("kpatel");
        final UserSession session = context.mock(UserSession.class); 
        
        GlobalVariables.setUserSession(session);
        
        context.checking(new Expectations() {{
            allowing(session).getPerson();will(returnValue(person));
        }});
    }
    
    // TODO : copied from protocolactionservicetest
    
    private void mockMinutes() {
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            fieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
            allowing(businessObjectService).countMatching(CommitteeScheduleMinutes.class, fieldValues);will(returnValue(1));
        }});
    }
    
    private void mockSubmissionTrue(final String submissionTypeCode) {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);
            
            Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
            negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
            allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);will(returnValue(0));
        }});
    }
    
    private void mockSubmissionFalse(final String submissionTypeCode) {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);
            
            Map<String, Object> negativeFieldValues = new HashMap<String, Object>();        
            negativeFieldValues.put("submissionTypeCode", submissionTypeCode);
            allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues, negativeFieldValues);will(returnValue(0));
        }});
    }
    
    private void mockSubmissionTrueCondt1() {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);

            allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);will(returnValue(0));
        }});
    }
    
    private void mockSubmissionTrueCondt2(final String submissionTypeCode) {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);
            positiveFieldValues.put("submissionTypeCode", submissionTypeCode);
            
            allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);will(returnValue(0));
        }});
    }
    
    private void mockSubmissionFalseCondt2(final String submissionTypeCode) {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);
            positiveFieldValues.put("submissionTypeCode", submissionTypeCode);
            
            allowing(businessObjectService).countMatching(ProtocolSubmission.class, positiveFieldValues);will(returnValue(1));
        }});
    }
    
    private void mockSubmissionCondt3() {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);
            
            List<ProtocolSubmission> list = new ArrayList<ProtocolSubmission>();
            
            allowing(businessObjectService).findMatching(ProtocolSubmission.class, positiveFieldValues);will(returnValue(list));
        }});
    }
    
    private void mockSubmissionCondt4(final boolean flag) {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            positiveFieldValues.put("submissionNumber", protocol.getProtocolSubmission().getSubmissionNumber());
            List<ProtocolSubmission> list = new ArrayList<ProtocolSubmission>();
            if(flag){
                ProtocolSubmission ps = new ProtocolSubmission();
                ps.setScheduleId("123");
                list.add(ps);
            }            
            allowing(businessObjectService).findMatching(ProtocolSubmission.class, positiveFieldValues);will(returnValue(list));
        }});
    }
    
    private void mockProtocolDaoCondt1() {
        context.checking(new Expectations() {{
            Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
            positiveFieldValues.put("protocolNumber", protocol.getProtocolNumber());
            positiveFieldValues.put("sequenceNumber", protocol.getSequenceNumber());
            List<String> ors = new ArrayList<String>();
            ors.add("100");
            ors.add("101"); 
            ors.add("102");
            positiveFieldValues.put("submissionStatusCode", ors);

            allowing(dao).getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());will(returnValue(0));
        }});
    }
    

}
