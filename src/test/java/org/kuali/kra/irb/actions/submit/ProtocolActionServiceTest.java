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

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolActionServiceImpl;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolActionServiceTest {
    
    public static final String ATC200 = "200";
    
    public static final String ATC201 = "201";
    
    public static final String ATC202 = "202";
    
    public static final String ATC204 = "204";
    
    public static final String ATC203 = "203";
    
    public static final String ATC300 = "300";
    
    public static final String ATC301 = "301";
    
    public static final String ATC302 = "302";
    
    public static final String ATC306 = "306";
    
    public static final String ATC305 = "305";
    
    public static final String ATC303 = "303";
    
    public static final String ATC304 = "304";
    
    public static final String ATC205 = "205";
    
    public static final String ATC206 = "206";
    
    public static final String ATC210 = "210";
    
    public static final String ATC207 = "207";
    
    public static final String ATC104 = "104";
    
    public static final String ATC106 = "106";
    
    public static final String ATC105 = "105";
    
    public static final String ATC108 = "108";
    
    public static final String ATC208 = "208";
    
    public static final String ATC209 = "209";
    
    public static final String ATC114 = "114";
    
    public static final String ATC115 = "115";
    
    public static final String ATC116 = "116";
    
    public static final String ATC211 = "211";
    
    public static final String ATC212 = "212";
    
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = ProtocolTestUtil.getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).save(protocol);
        }});
        protocolActionService.setBusinessObjectService(businessObjectService);
        
        ProtocolSubmissionStatus protocolSubmissionStatus = new ProtocolSubmissionStatus();
        protocolSubmissionStatus.setProtocolSubmissionStatusCode("xyz");        
        ProtocolSubmission protocolSubmission = new ProtocolSubmission();
        protocolSubmission.setSubmissionStatus(protocolSubmissionStatus);
        
        ProtocolSubmissionType protocolSubmissionType = new ProtocolSubmissionType();
        protocolSubmission.setProtocolSubmissionType(protocolSubmissionType);
        
        
        protocolSubmission.setSubmissionNumber(1);
        protocol.setProtocolSubmission(protocolSubmission);
        
        protocol.setProtocolNumber("001Z");
    }
    
    @Test
    public void testActionTypeCode200() {           
        protocolActionService.updateProtocolStatus(ATC200, protocol);
        assertEquals("101", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }

    @Test
    public void testActionTypeCode201() {    
        protocolActionService.updateProtocolStatus(ATC201, protocol);        
        assertEquals("206", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("103", protocol.getProtocolStatusCode());
    }

    @Test
    public void testActionTypeCode202() {    
        protocolActionService.updateProtocolStatus(ATC202, protocol);        
        assertEquals("201", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("104", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode204ProtocolARNumber() {
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(ATC204, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode204ProtocolARNumberNOTIN() {   
        protocolActionService.updateProtocolStatus(ATC204, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode203() {
        protocolActionService.updateProtocolStatus(ATC203, protocol);        
        assertEquals("202", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("102", protocol.getProtocolStatusCode());
    }    
    
    @Test
    public void testActionTypeCode300SubmissionTypeCode109() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("109");        
        protocolActionService.updateProtocolStatus(ATC300, protocol);        
        assertEquals("207", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("301", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode300SubmissionTypeCodeNOT109() {               
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");
        protocolActionService.updateProtocolStatus(ATC300, protocol);
        assertEquals("300", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode301SubmissionTypeCode108() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("108");        
        protocolActionService.updateProtocolStatus(ATC301, protocol);        
        assertEquals("208", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("303", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode301SubmissionTypeCodeNOT108() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(ATC301, protocol);        
        assertEquals("307", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode302SubmissionTypeCode110() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("110");        
        protocolActionService.updateProtocolStatus(ATC302, protocol);        
        assertEquals("209", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("302", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode302SubmissionTypeCodeNOT110() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(ATC302, protocol);        
        assertEquals("308", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode306SubmissionTypeCode110() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("110");        
        protocolActionService.updateProtocolStatus(ATC306, protocol);        
        assertEquals("209", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("311", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode306SubmissionTypeCodeNOT110() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(ATC306, protocol);        
        assertEquals("311", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode305() {    
        protocolActionService.updateProtocolStatus(ATC305, protocol);        
        assertEquals("305", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode303() {    
        protocolActionService.updateProtocolStatus(ATC303, protocol);        
        assertEquals("210", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode303ProtcolStatusCode101() {  
        protocol.setProtocolStatusCode("101");
        protocolActionService.updateProtocolStatus(ATC303, protocol);        
        assertEquals("304", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode304() {               
        protocolActionService.updateProtocolStatus(ATC304, protocol);        
        assertEquals("205", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("306", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode205ProtocolARNumber() {
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(ATC205, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode205ProtocolARNumberNOTIN() {   
        protocolActionService.updateProtocolStatus(ATC205, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode206() {   
        protocolActionService.updateProtocolStatus(ATC206, protocol);        
        assertEquals("204", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("203", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode210() {   
        protocolActionService.updateProtocolStatus(ATC210, protocol);        
        assertEquals("104", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("310", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode207SubmissionTypeCode111() { 
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("111"); 
        protocolActionService.updateProtocolStatus(ATC207, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("201", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode207SubmissionTypeCodeNOT111() { 
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX"); 
        protocolActionService.updateProtocolStatus(ATC207, protocol);        
        assertEquals("201", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode104() { 
        protocolActionService.updateProtocolStatus(ATC104, protocol);       
        assertEquals("108",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode106() { 
        protocolActionService.updateProtocolStatus(ATC106, protocol);       
        assertEquals("110",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode105() { 
        protocolActionService.updateProtocolStatus(ATC105, protocol);       
        assertEquals("109",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode108() { 
        protocolActionService.updateProtocolStatus(ATC108, protocol);       
        assertEquals("111",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode208ProtocolARNumber() {
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(ATC208, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode208ProtocolARNumberNOTIN() {   
        protocolActionService.updateProtocolStatus(ATC208, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode209() {
        protocolActionService.updateProtocolStatus(ATC209, protocol);        
        assertEquals("212", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode114() { 
        protocolActionService.updateProtocolStatus(ATC114, protocol);       
        assertEquals("113",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode115() { 
        protocolActionService.updateProtocolStatus(ATC115, protocol);       
        assertEquals("114",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode116() { 
        protocolActionService.updateProtocolStatus(ATC116, protocol);       
        assertEquals("112",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("7",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode211SubmissionTypeCode111() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("111");        
        protocolActionService.updateProtocolStatus(ATC211, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("202", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode211SubmissionTypeCodeNOT111() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(ATC211, protocol);        
        assertEquals("202", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode212SubmissionTypeCode111() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("115");        
        protocolActionService.updateProtocolStatus(ATC212, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode212SubmissionTypeCodeNOT111() {         
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(ATC212, protocol);        
        assertEquals("200", protocol.getProtocolStatusCode());
    }
}
