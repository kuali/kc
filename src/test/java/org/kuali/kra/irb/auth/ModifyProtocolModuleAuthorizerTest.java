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
package org.kuali.kra.irb.auth;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;

/**
 * Test the Modification of a Protocol Module Authorizer.
 */
@RunWith(JMock.class)
public abstract class ModifyProtocolModuleAuthorizerTest {

    private static final String USERNAME = "quickstart";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    /**
     * Get the name of the task which corresponds to the
     * authorizer that will be invoked.
     * @return
     */
    protected abstract String getTaskName();

    /**
     * Get the Protocol Module Type Code that will be tested.
     * @return
     */
    protected abstract String getTestModuleTypeCode();
    
    /**
     * Get a bogus Protocol Module Type Code; something other
     * than the one being tested.
     * @return
     */
    protected abstract String getFalseModuleTypeCode();
    
    /**
     * Create the Authorizer that will be tested.
     * @return
     */
    protected abstract ModifyAmendmentAuthorizer createAuthorizer();
    
    /**
     * Test the case where the user has permission to modify the specified
     * module in an amendment.
     */
    @Test
    public void testHasModulePermission() {
        ModifyAmendmentAuthorizer authorizer = createAuthorizer();
        
        Protocol protocol = createAmendment(getTestModuleTypeCode());
        
        addMockProtocolAuthorizationService(protocol, authorizer, true);
        
        ProtocolTask task = new ProtocolTask(getTaskName(), protocol);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    
    /**
     * Test the case where the user does have permission to modify
     * the amendment, but doesn't have permission to the modify the
     * module being tested.
     */
    @Test
    public void testHasNoModulePermission() {
        ModifyAmendmentAuthorizer authorizer = new ModifyProtocolGeneralInfoAuthorizer();
        
        Protocol protocol = createAmendment(getFalseModuleTypeCode());
        
        addMockProtocolAuthorizationService(protocol, authorizer, true);
        
        ProtocolTask task = new ProtocolTask(getTaskName(), protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    /**
     * Test the case where the protocol is a normal protocol, not an amendment.
     * Since it isn't an amendment, verify that the user can modify the protocol
     * if they have the Modify Protocol permission.
     */
    @Test
    public void testHasProtocolPermission() {
        ModifyAmendmentAuthorizer authorizer = createAuthorizer();
        
        Protocol protocol = createProtocol();
        
        addMockProtocolAuthorizationService(protocol, authorizer, true);
        
        ProtocolTask task = new ProtocolTask(getTaskName(), protocol);
        assertEquals(true, authorizer.isAuthorized(USERNAME, task));
    }
    
    /**
     * Test the case where the protocol is a normal protocol, not an amendment.
     * Since it isn't an amendment, verify that the user cannot modify the protocol
     * if they don't have the Modify Protocol permission.
     */
    @Test
    public void testHasNoProtocolPermission() {
        ModifyAmendmentAuthorizer authorizer = createAuthorizer();
        
        Protocol protocol = createProtocol();
        
        addMockProtocolAuthorizationService(protocol, authorizer, false);
        
        ProtocolTask task = new ProtocolTask(getTaskName(), protocol);
        assertEquals(false, authorizer.isAuthorized(USERNAME, task));
    }
    
    /**
     * Create a normal protocol.
     * @return
     */
    protected Protocol createProtocol() {
        final Protocol protocol = context.mock(Protocol.class);
        context.checking(new Expectations() {{
            one(protocol).getProtocolId(); will(returnValue(1L));
            atLeast(0).of(protocol).getProtocolNumber(); will(returnValue("0906000001"));
        }});
        return protocol;
    }
    
    /**
     * Create a amendment protocol.
     * @return
     */
    protected Protocol createAmendment(String moduleTypeCode) {
        final Protocol protocol = context.mock(Protocol.class);
        context.checking(new Expectations() {{
            one(protocol).getProtocolId(); will(returnValue(1L));
            atLeast(0).of(protocol).getProtocolNumber(); will(returnValue("0906000001A001"));
        }});
        addModule(protocol, moduleTypeCode);
        return protocol;
    }
    
    /**
     * Add an amendment entry to the protocol along with a module.
     * @param protocol
     * @param moduleTypeCode
     */
    protected void addModule(final Protocol protocol, String moduleTypeCode) {
        final ProtocolAmendRenewal amendRenewal = new ProtocolAmendRenewal();
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolModuleTypeCode(moduleTypeCode);
        amendRenewal.addModule(module);
        context.checking(new Expectations() {{
            one(protocol).getProtocolAmendRenewal(); will(returnValue(amendRenewal));
        }});
    }
    
    /**
     * Add the mock for ProtocolAuthorizationService which will determine if
     * the user has the MODIFY PROTOCOL permission or not.
     * @param protocol
     * @param authorizer
     * @param value
     */
    protected void addMockProtocolAuthorizationService(final Protocol protocol, ModifyAmendmentAuthorizer authorizer, final boolean value) {
        final ProtocolAuthorizationService protocolAuthorizationService = context.mock(ProtocolAuthorizationService.class);
        context.checking(new Expectations() {{
            one(protocolAuthorizationService).hasPermission(USERNAME, protocol, PermissionConstants.MODIFY_PROTOCOL); will(returnValue(value));
        }});
        authorizer.setProtocolAuthorizationService(protocolAuthorizationService);
    }
}