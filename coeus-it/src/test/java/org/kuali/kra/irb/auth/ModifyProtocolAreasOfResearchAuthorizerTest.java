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

import org.junit.Test;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;

/**
 * Test the Modify Protocol Areas of Research Authorizer.
 */
public class ModifyProtocolAreasOfResearchAuthorizerTest extends ModifyProtocolModuleAuthorizerTestBase {
    
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
        return new ModifyProtocolAreasOfResearchAuthorizer();
    }
    
    @Override
    protected String getModuleTypeCode() {
        return ProtocolModule.AREAS_OF_RESEARCH;
    }

    @Override
    protected String getTaskName() {
        return TaskName.MODIFY_PROTOCOL_AREAS_OF_RESEARCH;
    }
    
}
