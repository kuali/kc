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
package org.kuali.kra.irb.notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class contains tests for IRBNotificationRenderer
 */
public class IRBNotificationRendererTest extends KcUnitTestBase {
    
    private static final String DOCUMENT_NUMBER_VAL = "{DOCUMENT_NUMBER}_VAL";
    private static final String PROTOCOL_NUMBER_VAL = "{PROTOCOL_NUMBER}_VAL";
    private static final String SEQUENCE_NUMBER_VAL = "0";
    private static final String PROTOCOL_TITLE_VAL = "{PROTOCOL_TITLE}_VAL";
    private static final String PI_NAME_VAL = "{PI_NAME}_VAL";
    private static final String LEAD_UNIT_VAL = "{LEAD_UNIT}_VAL";
    private static final String LEAD_UNIT_NAME_VAL = "{LEAD_UNIT_NAME}_VAL";
    private static final String LAST_SUBMISSION_TYPE_CODE_VAL = "{LAST_SUBMISSION_TYPE_CODE}_VAL";
    private static final String LAST_SUBMISSION_NAME_VAL = "{LAST_SUBMISSION_NAME}_VAL";
    private static final String LAST_SUBMISSION_TYPE_QUAL_CODE_VAL = "{LAST_SUBMISSION_TYPE_QUAL_CODE}_VAL";
    private static final String LAST_SUBMISSION_TYPE_QUAL_NAME_VAL = "{LAST_SUBMISSION_TYPE_QUAL_NAME}_VAL";
    private static final String LAST_ACTION_TYPE_CODE_VAL = "{LAST_ACTION_TYPE_CODE}_VAL";
    private static final String LAST_ACTION_NAME_VAL = "{LAST_ACTION_NAME}_VAL";
    private static final String PROTOCOL_TYPE_CODE_VAL = "{PROTOCOL_TYPE_CODE}_VAL";
    private static final String PROTOCOL_TYPE_DESCRIPTION_VAL = "{PROTOCOL_TYPE_DESCRIPTION}_VAL";
    private static final String PROTOCOL_STATUS_CODE_VAL = "{PROTOCOL_STATUS_CODE}_VAL";
    private static final String PROTOCOL_STATUS_DESCRIPTION_VAL = "{PROTOCOL_STATUS_DESCRIPTION}_VAL";
    private static final String SUBMISSION_STATUS_CODE_VAL = "{SUBMISSION_STATUS_CODE}_VAL";
    private static final String SUBMISSION_STATUS_NAME_VAL = null; // there seems to be no easy way of setting this field in the protocol   
    private static final String PROTOCOL_REVIEW_TYPE_DESC_VAL = "{PROTOCOL_REVIEW_TYPE_DESC}_VAL";
    private static final String COMMITTEE_NAME_VAL = "{COMMITTEE_NAME}_VAL";
    
    private static final String PROTOCOL_INITIAL_APPROVAL_DATE_VAL = "10-Oct-2010";
    private static final String PROTOCOL_LAST_APPROVAL_DATE_VAL = "11-Nov-2011";
    private static final String PROTOCOL_EXPIRATION_DATE_VAL = "12-Dec-2012";
    
    private static final String PROTOCOL_REVIEW_TYPE_CODE_VAL = "PROTOCOL_REVIEW_TYPE_CODE_VAL";
    private static final String COMMITTEE_ID_VAL = "COMMITTEE_ID_VAL";
    private static final Integer SEQUENCE_NUMBER_INTEGER_VAL = 0;
    
    private Mockery context = new JUnit4Mockery();
    
    
    // This test is meant as a hint to the developer to update the coverage in testGetDefaultReplacementParameters() method below 
    // as new parameters are added to IRBReplacementParameters (causing this test to fail).
    @Test
    public void testNumberOfParameterNames() {
        assertEquals(24, IRBReplacementParameters.REPLACEMENT_PARAMETERS.length);
    }
    
    
    @SuppressWarnings("serial")
    @Test
    public void testGetDefaultReplacementParameters() throws ParseException {

        // create an instance of an anonymous Protocol subclass and mock the 'complicated' getters with simple ones
        
        // first set up the various return values from the mock getters
        final ProtocolPerson principalInvestigator = new ProtocolPerson();
        principalInvestigator.setFullName(PI_NAME_VAL);
        
        final ProtocolAction lastProtocolAction = new ProtocolAction();
        lastProtocolAction.setProtocolActionTypeCode(LAST_ACTION_TYPE_CODE_VAL);
        
        final ProtocolSubmission protocolSubmission = new ProtocolSubmission();
        protocolSubmission.setSubmissionTypeCode(LAST_SUBMISSION_TYPE_CODE_VAL);
        protocolSubmission.setSubmissionTypeQualifierCode(LAST_SUBMISSION_TYPE_QUAL_CODE_VAL);
        protocolSubmission.setSubmissionStatusCode(SUBMISSION_STATUS_CODE_VAL);
        protocolSubmission.setProtocolReviewTypeCode(PROTOCOL_REVIEW_TYPE_CODE_VAL);
        protocolSubmission.setCommitteeId(COMMITTEE_ID_VAL);
        
        // and now mock the getters in an anonymous subclass of Protocol
        Protocol protocol = new Protocol() {
           
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
            
            // mock methods
            public ProtocolPerson getPrincipalInvestigator() {
                return principalInvestigator;
            }
            
            public String getLeadUnitNumber() {
                return LEAD_UNIT_VAL;
            }
            
            public String getLeadUnitName() {
                return LEAD_UNIT_NAME_VAL;
            }
            
            public ProtocolAction getLastProtocolAction() {
                return lastProtocolAction;
            }
            
            public ProtocolSubmission getProtocolSubmission() {
                return protocolSubmission;
            }
            
        };
        // set up remaining fields for the protocol instance
        setUpProtocolFields(protocol);
        
        // set up the expectations for BO service mock
        BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        setUpExpectations(businessObjectService);
        
        
        // invoke the method under test and check the return value with various assertions
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        renderer.setBusinessObjectService(businessObjectService);
        Map<String, String> nameValueMap = renderer.getDefaultReplacementParameters();
        
        assertEquals(26, nameValueMap.size());
        assertEquals(PROTOCOL_NUMBER_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_NUMBER));
        assertEquals(PI_NAME_VAL, nameValueMap.get(IRBReplacementParameters.PI_NAME));
        assertEquals(LEAD_UNIT_VAL, nameValueMap.get(IRBReplacementParameters.LEAD_UNIT));
        assertEquals(LEAD_UNIT_NAME_VAL, nameValueMap.get(IRBReplacementParameters.LEAD_UNIT_NAME));
        assertEquals(PROTOCOL_STATUS_CODE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_STATUS_CODE));
        assertEquals(PROTOCOL_STATUS_DESCRIPTION_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_STATUS_DESCRIPTION));        
        assertEquals(PROTOCOL_TITLE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_TITLE));
        assertEquals(PROTOCOL_TYPE_CODE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_TYPE_CODE));
        assertEquals(SEQUENCE_NUMBER_VAL, nameValueMap.get(IRBReplacementParameters.SEQUENCE_NUMBER));
        assertEquals(SUBMISSION_STATUS_CODE_VAL, nameValueMap.get(IRBReplacementParameters.SUBMISSION_STATUS_CODE));
        assertEquals(SUBMISSION_STATUS_NAME_VAL, nameValueMap.get(IRBReplacementParameters.SUBMISSION_STATUS_NAME));
        assertEquals(PROTOCOL_TYPE_DESCRIPTION_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_TYPE_DESCRIPTION));
        
        assertEquals(PROTOCOL_INITIAL_APPROVAL_DATE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE));
        assertEquals(PROTOCOL_LAST_APPROVAL_DATE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE));
        assertEquals(PROTOCOL_EXPIRATION_DATE_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_EXPIRATION_DATE));
        
        assertEquals(DOCUMENT_NUMBER_VAL, nameValueMap.get(IRBReplacementParameters.DOCUMENT_NUMBER));
        
        assertEquals(LAST_ACTION_TYPE_CODE_VAL, nameValueMap.get(IRBReplacementParameters.LAST_ACTION_TYPE_CODE));
        assertEquals(LAST_ACTION_NAME_VAL, nameValueMap.get(IRBReplacementParameters.LAST_ACTION_NAME));
        
        assertEquals(LAST_SUBMISSION_TYPE_CODE_VAL, nameValueMap.get(IRBReplacementParameters.LAST_SUBMISSION_TYPE_CODE));
        assertEquals(LAST_SUBMISSION_NAME_VAL, nameValueMap.get(IRBReplacementParameters.LAST_SUBMISSION_NAME));
        
        assertEquals(LAST_SUBMISSION_TYPE_QUAL_CODE_VAL, nameValueMap.get(IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE));
        assertEquals(LAST_SUBMISSION_TYPE_QUAL_NAME_VAL, nameValueMap.get(IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME));
        
        assertEquals(PROTOCOL_REVIEW_TYPE_DESC_VAL, nameValueMap.get(IRBReplacementParameters.PROTOCOL_REVIEW_TYPE_DESC));
        assertEquals(COMMITTEE_NAME_VAL, nameValueMap.get(IRBReplacementParameters.COMMITTEE_NAME));       
        
        // now check with various protocol fields nullified (to simulate different execution paths)
        protocol.setApprovalDate(null);
        protocol.setLastApprovalDate(null);
        protocol.setExpirationDate(null);
        protocol.setProtocolType(null);
        
        BusinessObjectService businessObjectService2 = context.mock(BusinessObjectService.class, "mock2");
        setUpExpectations(businessObjectService2);
        renderer.setBusinessObjectService(businessObjectService2);
        
        nameValueMap = renderer.getDefaultReplacementParameters();
        
        assertEquals(22, nameValueMap.size());
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_EXPIRATION_DATE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_TYPE_DESCRIPTION));
        
        // nullify the submission and last action 
        protocol = new Protocol() {
            
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                //do nothing
            }
            
            public ProtocolPerson getPrincipalInvestigator() {
                return principalInvestigator;
            }
            
            public String getLeadUnitNumber() {
                return LEAD_UNIT_VAL;
            }
            
            public String getLeadUnitName() {
                return LEAD_UNIT_NAME_VAL;
            }
            
            public ProtocolAction getLastProtocolAction() {
                return null;
            }
            
            public ProtocolSubmission getProtocolSubmission() {
                return null;
            }
            
        };
        setUpProtocolFields(protocol);
       
        
        renderer = new IRBNotificationRenderer(protocol);
        BusinessObjectService businessObjectService3 = context.mock(BusinessObjectService.class, "mock3");
        setUpExpectations(businessObjectService3);
        renderer.setBusinessObjectService(businessObjectService3);
        
        nameValueMap = renderer.getDefaultReplacementParameters();
        
        assertEquals(14, nameValueMap.size());
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_INITIAL_APPROVAL_DATE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_LAST_APPROVAL_DATE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_EXPIRATION_DATE));        
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_SUBMISSION_NAME));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_SUBMISSION_TYPE_CODE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.SUBMISSION_STATUS_CODE));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.PROTOCOL_REVIEW_TYPE_DESC));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.COMMITTEE_NAME));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_ACTION_NAME));
        assertFalse(nameValueMap.containsKey(IRBReplacementParameters.LAST_ACTION_TYPE_CODE));
            
    }
    
    
    
    
    
    
    @SuppressWarnings("serial")
    private void setUpExpectations(final BusinessObjectService businessObjectService) {
        
        final Map<String, String> protocolActionTypeFieldValues = new HashMap<String, String>();
        protocolActionTypeFieldValues.put("protocolActionTypeCode", LAST_ACTION_TYPE_CODE_VAL);
        final List<ProtocolActionType> returnActionTypes = new ArrayList<ProtocolActionType>(); 
        returnActionTypes.add(new ProtocolActionType() {
            
           public String getDescription() {
               return LAST_ACTION_NAME_VAL;
           }
           
        });
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolActionType.class, protocolActionTypeFieldValues);
            will(returnValue(returnActionTypes));
        }});
        
        
        final Map<String, String> protocolSubmissionTypeFieldValues = new HashMap<String, String>();
        protocolSubmissionTypeFieldValues.put("submissionTypeCode", LAST_SUBMISSION_TYPE_CODE_VAL);
        final List<ProtocolSubmissionType> returnSubmissionTypes = new ArrayList<ProtocolSubmissionType>(); 
        returnSubmissionTypes.add(new ProtocolSubmissionType() {
            
           public String getDescription() {
               return LAST_SUBMISSION_NAME_VAL;
           }
           
        });
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolSubmissionType.class, protocolSubmissionTypeFieldValues);
            will(returnValue(returnSubmissionTypes));
        }});
        
        
        final Map<String, String> protocolSubmissionQualifierTypeFieldValues = new HashMap<String, String>();
        protocolSubmissionQualifierTypeFieldValues.put("submissionQualifierTypeCode", LAST_SUBMISSION_TYPE_QUAL_CODE_VAL);
        final List<ProtocolSubmissionQualifierType> returnSubmissionQualifierTypes = new ArrayList<ProtocolSubmissionQualifierType>(); 
        returnSubmissionQualifierTypes.add(new ProtocolSubmissionQualifierType() {
            
           public String getDescription() {
               return LAST_SUBMISSION_TYPE_QUAL_NAME_VAL;
           }
           
        });
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolSubmissionQualifierType.class, protocolSubmissionQualifierTypeFieldValues);
            will(returnValue(returnSubmissionQualifierTypes));
        }});
        
        
        final Map<String, String> protocolReviewTypeFieldValues = new HashMap<String, String>();
        protocolReviewTypeFieldValues.put("reviewTypeCode", PROTOCOL_REVIEW_TYPE_CODE_VAL);
        final List<ProtocolReviewType> returnReviewTypes = new ArrayList<ProtocolReviewType>(); 
        returnReviewTypes.add(new ProtocolReviewType() {
            
           public String getDescription() {
               return PROTOCOL_REVIEW_TYPE_DESC_VAL;
           }
           
        });
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolReviewType.class, protocolReviewTypeFieldValues);
            will(returnValue(returnReviewTypes));
        }});
        
        
        final Map<String, String> committeeFieldValues = new HashMap<String, String>();
        committeeFieldValues.put("committeeId", COMMITTEE_ID_VAL);
        final List<Committee> returnCommittees = new ArrayList<Committee>(); 
        returnCommittees.add(new Committee() {
            
           public String getCommitteeName() {
               return COMMITTEE_NAME_VAL;
           }
           
        });
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, committeeFieldValues);
            will(returnValue(returnCommittees));
        }});
    }

    
    private void setUpProtocolFields(Protocol protocol) throws ParseException {
        protocol.setProtocolNumber(PROTOCOL_NUMBER_VAL);
        protocol.setProtocolStatusCode(PROTOCOL_STATUS_CODE_VAL);
        ProtocolStatus protocolStatus = new ProtocolStatus();
        protocolStatus.setDescription(PROTOCOL_STATUS_DESCRIPTION_VAL);
        protocol.setProtocolStatus(protocolStatus);
        ProtocolDocument protocolDocument = new ProtocolDocument();
        protocolDocument.setDocumentNumber(DOCUMENT_NUMBER_VAL);
        ProtocolType protocolType = new ProtocolType();
        protocolType.setDescription(PROTOCOL_TYPE_DESCRIPTION_VAL);
        protocol.setProtocolDocument(protocolDocument);
        protocol.setTitle(PROTOCOL_TITLE_VAL);
        protocol.setProtocolTypeCode(PROTOCOL_TYPE_CODE_VAL);
        protocol.setProtocolType(protocolType);
        protocol.setSequenceNumber(SEQUENCE_NUMBER_INTEGER_VAL);        
        protocol.setApprovalDate(new java.sql.Date((new SimpleDateFormat("d'-'MMM'-'yyyy")).parse(PROTOCOL_INITIAL_APPROVAL_DATE_VAL).getTime()));
        protocol.setLastApprovalDate(new java.sql.Date((new SimpleDateFormat("d'-'MMM'-'yyyy")).parse(PROTOCOL_LAST_APPROVAL_DATE_VAL).getTime()));
        protocol.setExpirationDate(new java.sql.Date((new SimpleDateFormat("d'-'MMM'-'yyyy")).parse(PROTOCOL_EXPIRATION_DATE_VAL).getTime()));
    }
}
