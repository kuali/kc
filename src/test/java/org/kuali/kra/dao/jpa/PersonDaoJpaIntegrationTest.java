/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.dao.jpa;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.dao.PersonDao;

public class PersonDaoJpaIntegrationTest {
    private static final String PERSON_DAO_BEAN_ID = "personDao";

    private PersonDao dao;
    
    private static final String USER_NAME = "quickstart";
    private static final String PERSON_ID = "000000003";
    
    @Before
    public void setUp() throws Exception {
        dao = (PersonDao) SpringHelper.getBean(PERSON_DAO_BEAN_ID);
    }
    
    @After 
    public void tearDown() throws Exception {
        dao = null;
    }
    
    @Test
    public void testGettingUserName() {
        Assert.assertEquals(USER_NAME, dao.getUserName(PERSON_ID));
    }
}
