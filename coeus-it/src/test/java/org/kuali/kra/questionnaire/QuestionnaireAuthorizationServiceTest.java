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
package org.kuali.kra.questionnaire;

import org.junit.Test;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireAuthorizationService;
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
