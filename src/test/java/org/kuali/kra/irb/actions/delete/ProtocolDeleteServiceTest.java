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
package org.kuali.kra.irb.actions.delete;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolDeleteService implementation.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";")
}))
public class ProtocolDeleteServiceTest extends KraTestBase {

    private static final String REASON = "my test reason";
    
    private ProtocolDeleteServiceImpl protocolDeleteService;
    private BusinessObjectService businessObjectService;  
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("superuser"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        protocolDeleteService = new ProtocolDeleteServiceImpl();
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolDeleteService.setBusinessObjectService(businessObjectService);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
        super.tearDown();
    }
    
    @Test
    public void testDelete() throws WorkflowException {
        ProtocolDeleteBean deleteBean = new ProtocolDeleteBean();
        deleteBean.setReason(REASON);
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
         
        protocolDeleteService.delete(protocolDocument.getProtocol(), deleteBean);
    
        assertEquals(ProtocolStatus.DELETED, protocolDocument.getProtocol().getProtocolStatusCode());
    }
}
