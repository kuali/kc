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
package org.kuali.kra.irb.actions.expire;

import static org.junit.Assert.*;

import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaServiceImpl;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.web.ProtocolAuthorizationTest;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
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
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";")
}))

public class ProtocolExpireServiceTest extends KraTestBase {
    
    private BusinessObjectService businessObjectService;
    private ProtocolExpireService protocolExpireService;
    private ProtocolExpireServiceImpl protocolExpireServiceImpl;
    //private ProtocolAuthorizationTest protocolAuthorizationTest;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolExpireService = KraServiceLocator.getService(ProtocolExpireService.class);
        protocolExpireServiceImpl = (ProtocolExpireServiceImpl)KraServiceLocator.getService(ProtocolExpireService.class);
        //protocolAuthorizationTest = new ProtocolAuthorizationTest();
        //protocolAuthorizationTest.setUp();
    }

    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        protocolExpireService = null;
        protocolExpireServiceImpl = null;
        GlobalVariables.setUserSession(null);
        //protocolAuthorizationTest.tearDown();
        //protocolAuthorizationTest = null;
        super.tearDown();
    }

    @Test
    public void testSetBusinessObjectService() {
        protocolExpireServiceImpl.setBusinessObjectService(businessObjectService);
        assertTrue(true);
    }

    @Test
    public void testExpire() throws Exception {
        Protocol prot = ProtocolFactory.createProtocolDocument().getProtocol();
        ProtocolGenericActionBean actionBean = new ProtocolGenericActionBean();
        actionBean.setComments("dummy comments just to make this work");
        actionBean.setActionDate(new Date(2010, 2, 14));
        businessObjectService.save(prot);
        protocolExpireService.expire(prot, actionBean);
        String expected = ProtocolStatus.EXPIRED;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

}
