/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
