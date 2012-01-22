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
package org.kuali.kra.questionnaire;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class QuestionnaireAuthorizationServiceTest extends KcUnitTestBase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }  

    @Test
    public void permissionModifyQuestionnaireTest() {
 
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        assertTrue(KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE));
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertFalse(KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE));

    }
    
    @Test
    public void permissionViewQuestionnaireTest() {
 
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertTrue(KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE));
        GlobalVariables.setUserSession(new UserSession("woods"));
        assertFalse(KraServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE));

    }
    
}
