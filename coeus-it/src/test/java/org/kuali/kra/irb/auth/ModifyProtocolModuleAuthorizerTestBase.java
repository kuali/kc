/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
