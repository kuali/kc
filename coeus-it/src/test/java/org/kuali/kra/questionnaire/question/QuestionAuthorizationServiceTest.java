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
package org.kuali.kra.questionnaire.question;

import org.junit.Test;
import org.kuali.coeus.common.questionnaire.impl.question.QuestionAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class QuestionAuthorizationServiceTest extends KcIntegrationTestBase {

    @Test
    public void permissionModifyQuestionTest() {
 
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        assertTrue(KcServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION));
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertFalse(KcServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.MODIFY_QUESTION));

    }
    @Test
    public void permissionViewQuestionTest() {
 
        GlobalVariables.setUserSession(new UserSession("jtester"));
        assertTrue(KcServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTION));
        GlobalVariables.setUserSession(new UserSession("rrabbit"));
        assertFalse(KcServiceLocator.getService(QuestionAuthorizationService.class).hasPermission(PermissionConstants.VIEW_QUESTION));

    }
    
}
