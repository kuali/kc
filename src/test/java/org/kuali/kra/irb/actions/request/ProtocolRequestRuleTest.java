/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.request;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * Test the business rules for Protocol Requests: close, suspend, open enrollment,
 * re-open enrollment, and data analysis.  The only business rule to be tested
 * is based upon whether or not the selection of a committee is required.  If required,
 * the system param for irb is set to "M" for mandatory.  Since the business
 * rule is the same for each of the mentioned requests, only the close request 
 * will be tested.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolRequestRuleTest extends ProtocolRuleTestBase {
 
    private static final String COMMITTEE_ID = "1";
    private static final String MANDATORY = "M";
    private static final String OPTIONAL = "O";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    /**
     * Test a request with the system param set to optional, which is loaded
     * at startup time.
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        new TemplateRuleTest<ProtocolRequestEvent<ProtocolRequestRule>, ProtocolRequestRule> () {

            @Override
            protected void prerequisite() {
                ProtocolRequestBean requestBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, ProtocolSubmissionType.REQUEST_TO_CLOSE, 
                        Constants.EMPTY_STRING, "protocolCloseRequestBean");
                
                event = new ProtocolRequestEvent<ProtocolRequestRule>(null, Constants.PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY, requestBean);
                rule = new ProtocolRequestRule();
                rule.setParameterService(getMockParameterService(OPTIONAL));
                expectedReturnValue = true;
            }
            
        };
    }
    
    /**
     * If the mandatory flag has been set, we should get no
     * errors if the committee id has been set.
     * @throws WorkflowException
     */
    @Test
    public void testMandatoryOK() throws WorkflowException {
        new TemplateRuleTest<ProtocolRequestEvent<ProtocolRequestRule>, ProtocolRequestRule> () {

            @Override
            protected void prerequisite() {
                ProtocolRequestBean requestBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, ProtocolSubmissionType.REQUEST_TO_CLOSE, 
                        COMMITTEE_ID, "protocolCloseRequestBean");
                
                event = new ProtocolRequestEvent<ProtocolRequestRule>(null, Constants.PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY, requestBean);
                rule = new ProtocolRequestRule();
                rule.setParameterService(getMockParameterService(MANDATORY));
                expectedReturnValue = true;
            }
            
        };
    }
    
    /**
     * If the mandatory flag has been set, we should get an error message
     * if the committee id has not been set.
     * @throws WorkflowException
     */
    @Test
    public void testMandatoryCommittee() throws WorkflowException {
        new TemplateRuleTest<ProtocolRequestEvent<ProtocolRequestRule>, ProtocolRequestRule> () {

            @Override
            protected void prerequisite() {
                ProtocolRequestBean requestBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, ProtocolSubmissionType.REQUEST_TO_CLOSE, 
                        Constants.EMPTY_STRING, "protocolCloseRequestBean");
                
                event = new ProtocolRequestEvent<ProtocolRequestRule>(null, Constants.PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY, requestBean);
                rule = new ProtocolRequestRule();
                rule.setParameterService(getMockParameterService(MANDATORY));
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(Constants.PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY + ".committeeId", KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
            }
            
        };
    }
    
    private ParameterService getMockParameterService(final String committeeMandatoryCode) {
        final ParameterService service = context.mock(ParameterService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
            will(returnValue(committeeMandatoryCode));
        }});
        
        return service;
    }
    
    private ProtocolRequestBean getMockProtocolRequestBean(final String protocolActionTypeCode, final String submissionTypeCode, final String committeeId, 
            final String beanName) {
        
        final ProtocolRequestBean bean = context.mock(ProtocolRequestBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getProtocolActionTypeCode();
            will(returnValue(protocolActionTypeCode));
            
            allowing(bean).getSubmissionTypeCode();
            will(returnValue(submissionTypeCode));
            
            allowing(bean).getCommitteeId();
            will(returnValue(committeeId));
            
            allowing(bean).getBeanName();
            will(returnValue(beanName));
        }});
        
        return bean;
    }
    
}