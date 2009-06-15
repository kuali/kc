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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolActionServiceTest extends ProtocolActionServiceTestBase{
    
   
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    private BusinessObjectService businessObjectService;
    
    private ProtocolDao dao;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();
        
        businessObjectService = context.mock(BusinessObjectService.class);
        protocolActionService.setBusinessObjectService(businessObjectService);
        
        dao = context.mock(ProtocolDao.class);
        protocolActionService.setProtocolDao(dao);
        
        ProtocolSubmission protocolSubmission = new ProtocolSubmission();        
        protocol.setProtocolSubmission(protocolSubmission);
        
        protocol.setProtocolNumber("001Z");        
    }   
    
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
    
    @Test
    public void testActionTypeCode200() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");
        protocol.getProtocolSubmission().setScheduleId("NotNULL");
        assertTrue(protocolActionService.canPerformAction("200", protocol));
    }
    
    @Test
    public void testActionTypeCode201() {                       
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");        
        assertTrue(protocolActionService.canPerformAction("201",protocol));
    }

    @Test
    public void testActionTypeCode202() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("202", protocol));
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("202",protocol));      
    }
    
    @Test
    public void testActionTypeCode202ReviewTypeCodeNot1or2() { 
        mockMinutes();
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("202", protocol));    
    }
    
    @Test
    public void testActionTypeCode204() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        assertTrue(protocolActionService.canPerformAction("204", protocol));    
    }
    
    @Test
    public void testActionTypeCode203() {    
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("203", protocol));
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        assertTrue(protocolActionService.canPerformAction("203",protocol));      
    }
    
    @Test
    public void testActionTypeCode203ReviewTypeCodeNot1or2() {  
        mockMinutes();
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("203", protocol));   
    }
    
    @Test
    public void testActionTypeCode300Contd1() {    
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");
        mockSubmissionTrue("109");
        
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("300", protocol));    
        
        protocol.setProtocolStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("104");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("105");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("106");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("304");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
    }

    @Test
    public void testActionTypeCode300Contd2() {         
        mockSubmissionFalse("109");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("109");
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("300", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("104");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
        
        protocol.setProtocolStatusCode("304");      
        assertTrue(protocolActionService.canPerformAction("300", protocol)); 
    }
    
    @Test
    public void testActionTypeCode301Contd1() { 
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("108");
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("301", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("305");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
    }
    
    @Test
    public void testActionTypeCode301Contd2() {
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionFalse("108");
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("108");
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("301", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("305");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("301", protocol)); 
    }

    @Test
    public void testActionTypeCode305() {      
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrueCondt1();
        mockProtocolDaoCondt1();
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("305", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("304");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("305");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("310");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("305", protocol)); 
    }
    
    @Test
    public void testActionTypeCode302Contd1() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("302", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("302", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("302", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("302", protocol)); 
    }
    
    @Test
    public void testActionTypeCode302Contd2() { 
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("110");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
      
        assertTrue(protocolActionService.canPerformAction("302", protocol));    
    }
    
    @Test
    public void testActionTypeCode306Contd1() { 
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("Not102"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("306", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("306", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("306", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("306", protocol)); 
    }
    
    @Test
    public void testActionTypeCode306Contd2() { 
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        mockSubmissionTrue("110");
        protocol.getProtocolSubmission().setSubmissionTypeCode("110");
      
        assertTrue(protocolActionService.canPerformAction("306", protocol));    
    }
    
    
    
    @Test
    public void testActionTypeCode303() { 
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");          
        assertTrue(protocolActionService.canPerformAction("303", protocol));    
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("303", protocol)); 
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("303", protocol)); 
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("303", protocol)); 
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("303", protocol)); 
    }
    
    @Test
    public void testActionTypeCode304ReviewTypeCodeNot2or3() {     
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
    }
    
    @Test
    public void testActionTypeCode304ReviewTypeCode2or3() {
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));  
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("304", protocol));  
    }
    
    @Test
    public void testActionTypeCode103() { 
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("103", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("103", protocol)); 
    }
    
    @Test
    public void testActionTypeCode102() { 
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("102", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("102", protocol));
        
        protocol.setProtocolStatusCode("305");      
        assertTrue(protocolActionService.canPerformAction("102", protocol)); 
    }
    
    @Test
    public void testActionTypeCode205ReviewTypeCode2() {        
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        
        ProtocolReviewer pr = new ProtocolReviewer();
        List<ProtocolReviewer> list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);
        
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("205", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("205", protocol));   
    }
    
    @Test
    public void testActionTypeCode208ReviewTypeCode6() {    
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        
        ProtocolReviewer pr = new ProtocolReviewer();
        List<ProtocolReviewer> list = new ArrayList<ProtocolReviewer>();
        list.add(pr);
        protocol.getProtocolSubmission().setProtocolReviewers(list);
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("6");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("208", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("208", protocol));   
    }
    
    @Test
    public void testActionTypeCode209ReviewTypeCode6() {         
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setSubmissionTypeCode("112");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("209", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("209", protocol));   
    }
    
    @Test
    public void testActionTypeCode206ReviewTypeCode3() {       
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("206", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("206", protocol));   
    }
    
    @Test
    public void testActionTypeCode210ReviewTypeCode5() {         
        protocol.getProtocolSubmission().setSubmissionNumber(1); //Not null
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("5");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("210", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("210", protocol));   
    }

    @Test
    public void testActionTypeCode104True() {
        
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");        
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("104", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("104", protocol));
    }
    
    @Test
    public void testActionTypeCode104False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114");        
        protocol.setProtocolStatusCode("200");          
        assertFalse(protocolActionService.canPerformAction("104", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("300");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("301");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertFalse(protocolActionService.canPerformAction("104", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertFalse(protocolActionService.canPerformAction("104", protocol));
    }
    
    @Test
    public void testActionTypeCode105True() { 
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");    
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("105", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertTrue(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertTrue(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertTrue(protocolActionService.canPerformAction("105", protocol));
    }
    
    @Test
    public void testActionTypeCode105False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114");     
        protocol.setProtocolStatusCode("200");          
        assertFalse(protocolActionService.canPerformAction("105", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertFalse(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertFalse(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertFalse(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("302");      
        assertFalse(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("308");      
        assertFalse(protocolActionService.canPerformAction("105", protocol)); 
        
        protocol.setProtocolStatusCode("311");      
        assertFalse(protocolActionService.canPerformAction("105", protocol));
    }
    
    @Test
    public void testActionTypeCode106True() { 
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");
         
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("106", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("106", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertTrue(protocolActionService.canPerformAction("106", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertTrue(protocolActionService.canPerformAction("106", protocol)); 
    }  
    
    @Test
    public void testActionTypeCode106False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114"); 
        protocol.setProtocolStatusCode("200");          
        assertFalse(protocolActionService.canPerformAction("106", protocol));    
        
        protocol.setProtocolStatusCode("201");      
        assertFalse(protocolActionService.canPerformAction("106", protocol)); 
        
        protocol.setProtocolStatusCode("202");      
        assertFalse(protocolActionService.canPerformAction("106", protocol)); 
        
        protocol.setProtocolStatusCode("203");      
        assertFalse(protocolActionService.canPerformAction("106", protocol)); 
    }  
    
    @Test
    public void testActionTypeCode108True() { 
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("108", protocol));     
    }
    
    @Test
    public void testActionTypeCode108False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114"); 
        protocol.setProtocolStatusCode("200");          
        assertFalse(protocolActionService.canPerformAction("108", protocol));     
    }
    
    @Test
    public void testActionTypeCode207Cond1True() { 
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        mockSubmissionTrue("111");
        protocol.getProtocolSubmission().setSubmissionNumber(null);
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("207", protocol));     
    }
    
    @Test
    public void testActionTypeCode207Cond2() { 
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("207", protocol));     
    }
    
    @Test
    public void testActionTypeCode109Cond1() {
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionCondt4(true);
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("109", protocol));
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("109", protocol));
    }
    
    @Test
    public void testActionTypeCode109Cond2() { 
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        mockSubmissionCondt4(false);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("109", protocol));
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("109", protocol));
    }
    
    @Test
    public void testActionTypeCode999() { 
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");        
        assertTrue(protocolActionService.canPerformAction("999", protocol));
    }
    
    @Test
    public void testActionTypeCode114True() { 
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("114", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("114", protocol));
    }
    
    @Test
    public void testActionTypeCode114False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("200");      
        assertFalse(protocolActionService.canPerformAction("114", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertFalse(protocolActionService.canPerformAction("114", protocol));
    }
    
    @Test
    public void testActionTypeCode115True() { 
        mockSubmissionCondt3();
        mockSubmissionTrueCondt2("109");
        mockSubmissionTrueCondt2("110");
        mockSubmissionTrueCondt2("111");
        mockSubmissionTrueCondt2("108");
        mockSubmissionTrueCondt2("113");
        mockSubmissionTrueCondt2("114");
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("115", protocol));
    }
    
    @Test
    public void testActionTypeCode115False() { 
        mockSubmissionCondt3();
        mockSubmissionFalseCondt2("109");
        mockSubmissionFalseCondt2("110");
        mockSubmissionFalseCondt2("111");
        mockSubmissionFalseCondt2("108");
        mockSubmissionFalseCondt2("113");
        mockSubmissionFalseCondt2("114");
        protocol.setProtocolStatusCode("201");      
        assertFalse(protocolActionService.canPerformAction("115", protocol));
    }
    
    @Test
    public void testActionTypeCode211Cond1() { 
        protocol.getProtocolSubmission().setSubmissionNumber(123);
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
    }
    
    @Test
    public void testActionTypeCode211Cond2() { 
        protocol.getProtocolSubmission().setSubmissionNumber(null);   
        protocol.setSequenceNumber(123);
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        mockSubmissionTrue("113");
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
    }
    
    @Test
    public void testActionTypeCode212Cond1() { 
        protocol.getProtocolSubmission().setSubmissionNumber(123);        
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        mockSubmissionTrue("114");
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("212", protocol));
    }
    
    @Test
    public void testActionTypeCode212Cond2() { 
        protocol.getProtocolSubmission().setSubmissionNumber(null);           
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("212", protocol));
    }
    
    @Test
    public void testActionTypeCode116() {         
        protocol.setProtocolStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
        protocol.setProtocolStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
        protocol.setProtocolStatusCode("102");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
        protocol.setProtocolStatusCode("103");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
        protocol.setProtocolStatusCode("105");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
        
        protocol.setProtocolStatusCode("106");      
        assertTrue(protocolActionService.canPerformAction("116", protocol));
    }
}
