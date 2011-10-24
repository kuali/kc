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
package org.kuali.kra.subaward.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class SubAwardContactsActionTest extends KcUnitTestBase{

    SubAwardContactsAction subAwardContactsAction;
    SubAward subAward;
    SubAwardContact subawardContacts;
    
    @Before
    public void setUp() throws Exception {
        
        subAwardContactsAction = new SubAwardContactsAction();
        subAward = new SubAward();
        subawardContacts = new SubAwardContact();
        
        subawardContacts.setComments("Test");
        subawardContacts.setOrganization("Massachusetts Institute of Technology");
    }

    @After
    public void tearDown() throws Exception {
        subAwardContactsAction = null;
        subAward = null;
        subawardContacts = null;
    }
    @Test
    public void testAddCloseoutToSubAward(){
        
        Assert.assertTrue(subAwardContactsAction.addContactsToSubAward(subAward, subawardContacts));


    }


}
