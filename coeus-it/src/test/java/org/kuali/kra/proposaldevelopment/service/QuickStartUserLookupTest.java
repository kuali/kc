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
package org.kuali.kra.proposaldevelopment.service;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import static org.junit.Assert.*;
public class QuickStartUserLookupTest extends KcIntegrationTestBase {

    /*
     * re: JIRA KRACOEUS-635
     */
    @Test(expected=RiceIllegalArgumentException.class)
    public void testFindingQuickstartUser_TruncatedUserId() throws Exception {
        Person user = KEWServiceLocator.getIdentityHelperService().getPersonByPrincipalName("quicksta");
        fail("We should get an exception");
    }
    
    /*
     * re: JIRA KRACOEUS-635
     */
    @Test
    public void testFindingQuickstartUser_CompleteUserId() throws Exception {
        Person user = KEWServiceLocator.getIdentityHelperService().getPersonByPrincipalName("quickstart");
        Assert.assertNotNull(user);
    }
}
