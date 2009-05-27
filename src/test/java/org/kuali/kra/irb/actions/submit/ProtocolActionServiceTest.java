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

import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;

public class ProtocolActionServiceTest extends ProtocolActionServiceTestBase{
    
   
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();

        ProtocolSubmission protocolSubmission = new ProtocolSubmission();        
        protocol.setProtocolSubmission(protocolSubmission);
        
        protocol.setProtocolNumber("001Z");        
    }   
    
    @Test
    public void testActionTypeCode200() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");        
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
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("202", protocol));    
    }
    
    @Test
    public void testActionTypeCode204() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("204", protocol));    
    }
    
    @Test
    public void testActionTypeCode203() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        assertTrue(protocolActionService.canPerformAction("203", protocol));
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        assertTrue(protocolActionService.canPerformAction("203",protocol));      
    }
    
    @Test
    public void testActionTypeCode203ReviewTypeCodeNot1or2() {                
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("203", protocol));   
    }
    
    @Test
    public void testActionTypeCode300Contd1() {                
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
    public void testActionTypeCode306Contd1() { 
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
    public void testActionTypeCode303() { 
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
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("0");
        assertTrue(protocolActionService.canPerformAction("304", protocol));   
    }
    
    @Test
    public void testActionTypeCode304ReviewTypeCode2or3() {         
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
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("205", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("205", protocol));   
    }
    
    @Test
    public void testActionTypeCode208ReviewTypeCode6() {         
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("6");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("208", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("208", protocol));   
    }
    
    @Test
    public void testActionTypeCode209ReviewTypeCode6() {         
        protocol.getProtocolSubmission().setSubmissionTypeCode("112");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("209", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("209", protocol));   
    }
    
    @Test
    public void testActionTypeCode206ReviewTypeCode3() {         
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("206", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("206", protocol));   
    }
    
    @Test
    public void testActionTypeCode210ReviewTypeCode5() {         
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("5");
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("100");      
        assertTrue(protocolActionService.canPerformAction("210", protocol));   
        
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");      
        assertTrue(protocolActionService.canPerformAction("210", protocol));   
    }

    @Test
    public void testActionTypeCode104() { 
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
    public void testActionTypeCode105() { 
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
    public void testActionTypeCode106() { 
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
    public void testActionTypeCode108() { 
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("108", protocol));     
    }
    
    @Test
    public void testActionTypeCode207Cond1() { 
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("207", protocol));     
    }
    
    @Test
    public void testActionTypeCode207Cond2() { 
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("111");
        protocol.setProtocolStatusCode("200");          
        assertTrue(protocolActionService.canPerformAction("207", protocol));     
    }
    
    @Test
    public void testActionTypeCode209() { 
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("2");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("209", protocol));
        
        protocol.getProtocolSubmission().setProtocolReviewTypeCode("3");
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT100");        
        assertTrue(protocolActionService.canPerformAction("209", protocol));
    }
    
    @Test
    public void testActionTypeCode999() { 
        protocol.getProtocolSubmission().setSubmissionStatusCode("101");        
        assertTrue(protocolActionService.canPerformAction("999", protocol));
    }
    
    @Test
    public void testActionTypeCode114() { 
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("114", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("114", protocol));
    }
    
    @Test
    public void testActionTypeCode115() { 
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("115", protocol));
    }
    
    @Test
    public void testActionTypeCode211Cond1() {         
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("113");
        
        protocol.setProtocolStatusCode("200");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
        
        protocol.setProtocolStatusCode("201");      
        assertTrue(protocolActionService.canPerformAction("211", protocol));
    }
    
    @Test
    public void testActionTypeCode212Cond1() {         
        protocol.getProtocolSubmission().setSubmissionStatusCode("NOT210"); 
        protocol.getProtocolSubmission().setSubmissionTypeCode("114");
        
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
