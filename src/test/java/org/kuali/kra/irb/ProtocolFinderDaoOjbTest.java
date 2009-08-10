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
package org.kuali.kra.irb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";")
}))
public class ProtocolFinderDaoOjbTest extends KraTestBase {
    
    private static final String PROTOCOL_NUMBER = "0906000001";
    private static final String PROTOCOL_NUMBER2 = "0906000002";

    private ProtocolFinderDao protocolFinder;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolFinder = KraServiceLocator.getService(ProtocolFinderDao.class);
        GlobalVariables.setUserSession(new UserSession("superuser"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    /**
     * Add "two" protocols.  One of the two protocols will have two versions.  For each of
     * the "two" protocols, we should get back the latest version.
     */
    @Test
    public void testFinder() throws WorkflowException {
        ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 1);
        ProtocolDocument protocolDocument1 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 2);
        
        ProtocolDocument protocolDocument2 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER2, 1);
       
        Protocol protocol = protocolFinder.findCurrentProtocolByNumber(PROTOCOL_NUMBER2);
        assertNotNull(protocol);
        assertEquals(protocolDocument2.getProtocol().getProtocolId(), protocol.getProtocolId());
        
        protocol = protocolFinder.findCurrentProtocolByNumber(PROTOCOL_NUMBER);
        assertNotNull(protocol);
        assertEquals(protocolDocument1.getProtocol().getProtocolId(), protocol.getProtocolId());
    }
}
