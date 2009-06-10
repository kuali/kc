/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the Data Access Object implementation for <code>{@link Protocol}</code> business objects
 * 
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {        
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol.sql", delimiter = ";")}))
public class ProtocolDaoOjbTest extends KraTestBase {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After 
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void test() {

        Integer val = getProtocolDao().getProtocolSubmissionCount("001");
        assertEquals(new Integer(3),val);
        
    }
    
    private ProtocolDao getProtocolDao() {
        return getService(ProtocolDao.class);
    }
}
