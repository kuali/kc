/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.test.ProtocolFactory;

import java.util.Collections;

/**
 * Test the Modification of a Protocol Module Authorizer.
 */
public abstract class ModifyProtocolModuleAuthorizerTestBase extends ProtocolAuthorizerTestBase {
    
    /**
     * Test whether the user can modify the protocol if they have the Modify Protocol permission.
     */
    protected void runModifyProtocolTest(String protocolNumber, boolean hasPermission, boolean expected) throws Exception {
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        document.getProtocol().setCorrectionMode(true);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, false, true);
                
        runTest(document, authorizer, expected);
    }
    
    /**
     * Test whether the user has permission to modify the specified module in an amendment.
     */
    protected void runModifyProtocolAmendmentTest(String protocolNumber, String moduleTypeCode, boolean hasPermission, boolean expected) throws Exception {
        ProtocolDocument document = ProtocolFactory.createProtocolDocument(protocolNumber);
        ProtocolAmendRenewal amendRenewal = new ProtocolAmendRenewal();
        ProtocolAmendRenewModule module = new ProtocolAmendRenewModule();
        module.setProtocolModuleTypeCode(moduleTypeCode);
        amendRenewal.addModule(module);
        document.getProtocol().setProtocolAmendRenewal(amendRenewal);
        document.getProtocol().setCorrectionMode(true);
        ProtocolAuthorizer authorizer = createProtocolAuthorizer(document, hasPermission, false, true);
        
        runTest(document, authorizer, expected);
    }
    
    /**
     * Return the Protocol Module Type Code that will be tested.
     * @return the module type code to test
     */
    protected abstract String getModuleTypeCode();
    
    /**
     * Create the Authorizer that will be tested.
     * @return the authorizer to test
     */
    protected abstract ModifyAmendmentAuthorizer createModifyAmendmentAuthorizer();
    
    @Override
    protected ProtocolAuthorizer createProtocolAuthorizer(ProtocolDocument protocolDocument, boolean hasPermission, boolean isActionAllowed, boolean isInWorkflow) throws Exception {
        ModifyAmendmentAuthorizer authorizer = createModifyAmendmentAuthorizer();
        authorizer.setKraAuthorizationService(buildKraAuthorizationService(protocolDocument, PermissionConstants.MODIFY_PROTOCOL, hasPermission));
        authorizer.setKraWorkflowService(buildKraWorkflowService(protocolDocument, isInWorkflow));
        authorizer.setProtocolAmendRenewService(buildProtocolAmendRenewService(protocolDocument, Collections.singletonList(getModuleTypeCode())));
        return authorizer;
    }

}