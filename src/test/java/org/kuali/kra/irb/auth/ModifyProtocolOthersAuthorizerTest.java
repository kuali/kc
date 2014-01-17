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

import org.junit.Test;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;

/**
 * Test the Modify Protocol Others Authorizer.
 */
public class ModifyProtocolOthersAuthorizerTest extends ModifyProtocolModuleAuthorizerTestBase {

    @Test
    public void testHasProtocolPermission() throws Exception {
        runModifyProtocolTest(PROTOCOL_NUMBER, true, true);
    }

    @Test
    public void testHasNoProtocolPermission() throws Exception {
        runModifyProtocolTest(PROTOCOL_NUMBER, false, false);
    }
    
    @Test
    public void testHasModulePermission() throws Exception {
        runModifyProtocolAmendmentTest(PROTOCOL_NUMBER + "A001", getModuleTypeCode(), true, true);
    }

    @Test
    public void testHasNoModulePermission() throws Exception {
        runModifyProtocolAmendmentTest(PROTOCOL_NUMBER + "A001", ProtocolModule.ADD_MODIFY_ATTACHMENTS, true, false);
    }
    
    @Override
    protected ModifyAmendmentAuthorizer createModifyAmendmentAuthorizer() {
        return new ModifyProtocolOthersAuthorizer();
    }
    
    @Override
    protected String getModuleTypeCode() {
        return ProtocolModule.OTHERS;
    }

    @Override
    protected String getTaskName() {
        return TaskName.MODIFY_PROTOCOL_OTHERS;
    }
    
}