/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class QuestionnaireAuthorizationServiceTest extends KcIntegrationTestBase {

    @Test
    public void permissionModifyQuestionnaireTest() {
 
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        assertTrue(KcServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE));
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertFalse(KcServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE));

    }
    
    @Test
    public void permissionViewQuestionnaireTest() {
 
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertTrue(KcServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE));
        GlobalVariables.setUserSession(new UserSession("woods"));
        assertFalse(KcServiceLocator.getService(QuestionnaireAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE));

    }
    
}
