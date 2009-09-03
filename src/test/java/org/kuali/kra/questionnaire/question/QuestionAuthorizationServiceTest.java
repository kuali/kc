/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.questionnaire.question;

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionAuthorizationServiceTest extends KraTestBase {

    @Test
    public void permissionModifyQuestionTest() {
 
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        assertTrue(KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION));
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertFalse(KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION));

    }
    @Test
    public void permissionViewQuestionTest() {
 
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertTrue(KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTION));
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        assertFalse(KraServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTION));

    }
    
}
