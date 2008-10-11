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
package org.kuali.kra.proposaldevelopment.dao.jpa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.dao.jpa.SpringHelper;
import org.kuali.kra.proposaldevelopment.dao.AttachmentDao;

public class AttachmentDaoImplTest {
    private static final String ATTACHMENT_DAO_BEAN_ID = "attachmentDao";
    
    private AttachmentDao dao;
    
    @Before
    public void setUp() throws Exception {        
        dao = (AttachmentDao) SpringHelper.getBean(ATTACHMENT_DAO_BEAN_ID);
    }
    
    @After 
    public void tearDown() throws Exception {
        dao = null;
    }
    
    @Test
    public void testGettingPersonnelTimeStampAndUploadUser() throws Exception {
        // This is a weak test to verify the query produces no errors; however, better test data is needed
        Assert.assertFalse(dao.getPersonnelTimeStampAndUploadUser(0, "0000  ", 0).hasNext());
    }
    
    @Test
    public void testGettingNarrativeTimeStampAndUploadUser() throws Exception {
        // This is a weak test to verify the query produces no errors; however, better test data is needed
        Assert.assertFalse(dao.getNarrativeTimeStampAndUploadUser(0, "0000").hasNext());
    }
}
