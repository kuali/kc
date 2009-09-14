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
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolUpdateActionServiceTest extends ProtocolActionServiceTestBase{
    
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
    
    private ProtocolAction action;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    private BusinessObjectService businessObjectService;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();
        
        businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            allowing(businessObjectService).save(protocol);
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
        
        action = new ProtocolAction();
        
    }   
    
    @Test
    public void testActionTypeCode200() {        
        action.setProtocolActionTypeCode(ATC200);
        protocolActionService.updateProtocolStatus(action, protocol);
        assertEquals("101", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }

    @Test
    public void testActionTypeCode201() {  
        action.setProtocolActionTypeCode(ATC201);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("206", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("103", protocol.getProtocolStatusCode());
    }

    @Test
    public void testActionTypeCode202() {    
        action.setProtocolActionTypeCode(ATC202);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("201", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("104", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode204ProtocolARNumber() {
        action.setProtocolActionTypeCode(ATC204);
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode204ProtocolARNumberNOTIN() {
        action.setProtocolActionTypeCode(ATC204);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode203() {
        action.setProtocolActionTypeCode(ATC203);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("202", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("102", protocol.getProtocolStatusCode());
    }    
    
    @Test
    public void testActionTypeCode300SubmissionTypeCode109() {
        action.setProtocolActionTypeCode(ATC300);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("109");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("207", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("301", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode300SubmissionTypeCodeNOT109() {
        action.setProtocolActionTypeCode(ATC300);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");
        protocolActionService.updateProtocolStatus(action, protocol);
        assertEquals("300", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode301SubmissionTypeCode108() {
        action.setProtocolActionTypeCode(ATC301);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("108");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("208", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("303", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode301SubmissionTypeCodeNOT108() {
        action.setProtocolActionTypeCode(ATC301);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("307", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode302SubmissionTypeCode110() {
        action.setProtocolActionTypeCode(ATC302);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("110");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("209", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("302", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode302SubmissionTypeCodeNOT110() {
        action.setProtocolActionTypeCode(ATC302);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("308", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode306SubmissionTypeCode110() {
        action.setProtocolActionTypeCode(ATC306);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("110");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("209", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("311", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode306SubmissionTypeCodeNOT110() {
        action.setProtocolActionTypeCode(ATC306);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("311", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode305() {    
        action.setProtocolActionTypeCode(ATC305);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("305", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode303() {
        action.setProtocolActionTypeCode(ATC303);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("210", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode303ProtcolStatusCode101() {
        action.setProtocolActionTypeCode(ATC303);
        protocol.setProtocolStatusCode("101");
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("304", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode304() {   
        action.setProtocolActionTypeCode(ATC304);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("205", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("306", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode205ProtocolARNumber() {
        action.setProtocolActionTypeCode(ATC205);
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode205ProtocolARNumberNOTIN() {
        action.setProtocolActionTypeCode(ATC205);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode206() {   
        action.setProtocolActionTypeCode(ATC206);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("204", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("203", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode210() {   
        action.setProtocolActionTypeCode(ATC210);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("104", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("310", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode207SubmissionTypeCode111() { 
        action.setProtocolActionTypeCode(ATC207);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("111"); 
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("201", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode207SubmissionTypeCodeNOT111() { 
        action.setProtocolActionTypeCode(ATC207);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX"); 
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("201", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode104() { 
        action.setProtocolActionTypeCode(ATC104);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("108",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode106() { 
        action.setProtocolActionTypeCode(ATC106);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("110",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode105() { 
        action.setProtocolActionTypeCode(ATC105);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("109",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode108() { 
        action.setProtocolActionTypeCode(ATC108);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("111",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode208ProtocolARNumber() {
        action.setProtocolActionTypeCode(ATC208);
        protocol.setProtocolNumber("001A");
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode208ProtocolARNumberNOTIN() {   
        action.setProtocolActionTypeCode(ATC208);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("203", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode209() {
        action.setProtocolActionTypeCode(ATC209);
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("212", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
    }
    
    @Test
    public void testActionTypeCode114() { 
        action.setProtocolActionTypeCode(ATC114);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("113",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode115() { 
        action.setProtocolActionTypeCode(ATC115);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("114",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("1",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode116() { 
        action.setProtocolActionTypeCode(ATC116);
        protocolActionService.updateProtocolStatus(action, protocol);       
        assertEquals("112",protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        assertEquals("7",protocol.getProtocolSubmission().getProtocolReviewType().getReviewTypeCode());
        assertEquals("102",protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals(2,protocol.getProtocolSubmission().getSubmissionNumber().intValue());
    }
    
    @Test
    public void testActionTypeCode211SubmissionTypeCode111() {         
        action.setProtocolActionTypeCode(ATC211);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("111");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("202", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode211SubmissionTypeCodeNOT111() {   
        action.setProtocolActionTypeCode(ATC211);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("202", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode212SubmissionTypeCode111() {       
        action.setProtocolActionTypeCode(ATC212);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("115");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("211", protocol.getProtocolSubmission().getSubmissionStatus().getProtocolSubmissionStatusCode());
        assertEquals("200", protocol.getProtocolStatusCode());
    }
    
    @Test
    public void testActionTypeCode212SubmissionTypeCodeNOT111() {   
        action.setProtocolActionTypeCode(ATC212);
        protocol.getProtocolSubmission().getProtocolSubmissionType().setSubmissionTypeCode("XXX");        
        protocolActionService.updateProtocolStatus(action, protocol);        
        assertEquals("200", protocol.getProtocolStatusCode());
    }
}
