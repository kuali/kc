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
package org.kuali.kra.irb.actions.approve;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionServiceImpl;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kew.role.service.RoleService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
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
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";")
}))

public class ProtocolApproveServiceImplTest extends KraTestBase {
    
    private BusinessObjectService businessObjectService;
    private ProtocolApproveServiceImpl protocolApproveServiceImpl;
    private ProtocolApproveService protocolApproveService;
    
    private static final Date BASIC_ACTION_DATE = new Date(2010, 2, 14);

            
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolApproveService = KraServiceLocator.getService(ProtocolApproveService.class);
        protocolApproveServiceImpl = (ProtocolApproveServiceImpl)KraServiceLocator.getService(ProtocolApproveService.class);
    }

    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        protocolApproveService = null;
        protocolApproveServiceImpl = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    @Test
    public void testSetBusinessObjectService() {
        protocolApproveServiceImpl.setBusinessObjectService(businessObjectService);
        assertTrue(true);
    }

    @Test
    public void testApprove() throws Exception{
        Protocol prot = ProtocolFactory.createProtocolDocument().getProtocol();
        ProtocolApproveBean actionBean = new ProtocolApproveBean();
        actionBean.setActionDate(BASIC_ACTION_DATE);
        actionBean.setApprovalDate(BASIC_ACTION_DATE);
        actionBean.setComments("some comments go here");
        actionBean.setExpirationDate(BASIC_ACTION_DATE);
        businessObjectService.save(prot);
        protocolApproveService.approve(prot, actionBean);
        businessObjectService.save(prot);
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }
}