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

import java.sql.Timestamp;
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
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ActionLoggerTest {
    
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolAction action;
    
    private ActionLogger logger;
    
    private BusinessObjectService businessObjectService;
    
    public static Protocol getProtocol(final Mockery context) {
        
        final Protocol protocol = new Protocol() {

            private static final long serialVersionUID = -1273061983131550371L;

            @Override
            protected ProtocolLocationService getProtocolLocationService() {
                final Protocol aThis = this;
                final ProtocolLocationService mock = context.mock(ProtocolLocationService.class);
                context.checking(new Expectations() {{
                    allowing(mock).addDefaultProtocolLocation(aThis);
                }});
                return mock;
            }
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
        };
        
        return protocol;
    }
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = getProtocol(context);
        logger = new ActionLogger();

        businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            allowing(businessObjectService).save(protocol);
        }});
        logger.setBusinessObjectService(businessObjectService);
        
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
    
    @SuppressWarnings("unchecked")
    @Test
    public void testNewActionLog() {
        action.setProtocolActionTypeCode("103");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(new ArrayList()));
        }});
        
        assertEquals("Amendment- 001 created", logger.getActionComment(action, protocol));

        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            ProtocolAction bo = new ProtocolAction();
            bo.setActionId(5);
            list.add(bo);
            allowing(businessObjectService).findMatching(ProtocolAction.class, fieldValues);
            will(returnValue(list));
        }}); 
        assertEquals(new Integer(6), logger.getActionId(protocol));
        
        protocol.setSequenceNumber(1);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            Protocol bo = getProtocol(new JUnit4Mockery());
            bo.setSequenceNumber(7);
            list.add(bo);
            allowing(businessObjectService).findMatching(Protocol.class, fieldValues);
            will(returnValue(list));
        }});
        assertEquals(new Integer(7), logger.getSequenceNumber(protocol));
        
        final ProtocolAction newActionBo = new ProtocolAction();
        newActionBo.setProtocolNumber(protocol.getProtocolNumber());
        newActionBo.setSequenceNumber(7);
        newActionBo.setActionId(6);
        newActionBo.setProtocolActionTypeCode(action.getProtocolActionTypeCode());
        newActionBo.setComments("Amendment- 001 created");
        newActionBo.setSubmissionNumber(action.getSubmissionNumber());
        newActionBo.setActionDate(new Timestamp(new java.util.Date().getTime()));
        
        context.checking(new Expectations() {{
            allowing(businessObjectService).save(newActionBo);
        }});
        
        assertEquals("Amendment- 001 created", logger.getActionComment(action, protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionSequence(){ 
        protocol.setSequenceNumber(5);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            allowing(businessObjectService).findMatching(Protocol.class, fieldValues);
            will(returnValue(new ArrayList()));
        }});
        assertEquals(new Integer(5), logger.getSequenceNumber(protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionSequenceNotNull(){ 
        protocol.setSequenceNumber(1);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            Protocol bo = getProtocol(new JUnit4Mockery());
            bo.setSequenceNumber(7);
            list.add(bo);
            allowing(businessObjectService).findMatching(Protocol.class, fieldValues);
            will(returnValue(list));
        }});
        assertEquals(new Integer(7), logger.getSequenceNumber(protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionSequenceNull(){ 
        protocol.setSequenceNumber(5);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            Protocol bo = getProtocol(new JUnit4Mockery());
            bo.setSequenceNumber(null);
            list.add(bo);
            allowing(businessObjectService).findMatching(Protocol.class, fieldValues);
            will(returnValue(list));
        }});
        assertEquals(new Integer(5), logger.getSequenceNumber(protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionId(){ 
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            allowing(businessObjectService).findMatching(ProtocolAction.class, fieldValues);
            will(returnValue(new ArrayList()));
        }}); 
        assertEquals(new Integer(1), logger.getActionId(protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionIdNotNull(){ 
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            ProtocolAction bo = new ProtocolAction();
            bo.setActionId(5);
            list.add(bo);
            allowing(businessObjectService).findMatching(ProtocolAction.class, fieldValues);
            will(returnValue(list));
        }}); 
        assertEquals(new Integer(6), logger.getActionId(protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionIdNull(){ 
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("protocolNumber", protocol.getProtocolNumber());
            List list = new ArrayList();
            ProtocolAction bo = new ProtocolAction();
            bo.setActionId(null);
            list.add(bo);
            allowing(businessObjectService).findMatching(ProtocolAction.class, fieldValues);
            will(returnValue(list));
        }}); 
        assertEquals(new Integer(1), logger.getActionId(protocol));
    }
    
    @Test
    public void testNewActionDate() {
        assertEquals(new Timestamp(new java.util.Date().getTime()), logger.getActionDate(action));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionComment103(){ 
        action.setProtocolActionTypeCode("103");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(new ArrayList()));
        }});
        
        assertEquals("Amendment- 001 created", logger.getActionComment(action, protocol));

    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionComment103NotNull(){ 
        protocol.setProtocolNumber("0123456789");
        action.setProtocolActionTypeCode("103");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            ProtocolAmendRenewal bo = new ProtocolAmendRenewal();
            bo.setProtoAmendRenNumber("0123456789A123");
            List list = new ArrayList();
            list.add(bo);
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(list));
        }});
        assertEquals("Amendment- 123 created", logger.getActionComment(action, protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionComment102(){ 
        action.setProtocolActionTypeCode("102");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(new ArrayList()));
        }});        
        assertEquals("Renewal- 001 created", logger.getActionComment(action, protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionComment102NotNull(){ 
        protocol.setProtocolNumber("0123456789");
        action.setProtocolActionTypeCode("102");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            ProtocolAmendRenewal bo = new ProtocolAmendRenewal();
            bo.setProtoAmendRenNumber("0123456789R123");
            List list = new ArrayList();
            list.add(bo);
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(list));
        }});
        assertEquals("Renewal- 123 created", logger.getActionComment(action, protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionCommentA(){ 
        protocol.setProtocolNumber("0123456789A123");
        action.setProtocolActionTypeCode("XX");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(new ArrayList()));
        }});
        assertEquals("Amendment- 123", logger.getActionComment(action, protocol));
    }
    
    @SuppressWarnings("unchecked")
    @Test 
    public void testNewActionCommentR(){ 
        protocol.setProtocolNumber("0123456789R123");
        action.setProtocolActionTypeCode("XX");
        action.setSubmissionNumber(1);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findAll(ProtocolAmendRenewal.class);
            will(returnValue(new ArrayList()));
        }});
        assertEquals("Renewal- 123", logger.getActionComment(action, protocol));
    }    
}
