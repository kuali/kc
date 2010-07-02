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
package org.kuali.kra.irb.actions.genericactions;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;
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
public class ProtocolGenericActionsServiceTest extends KraTestBase {
    
    private BusinessObjectService businessObjectService;
    private ProtocolGenericActionService genericActionService;
    private ProtocolGenericActionServiceImpl genericActionServiceImpl;
    
    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(2010, 2, 14);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        genericActionService = KraServiceLocator.getService(ProtocolGenericActionService.class);
        genericActionServiceImpl = (ProtocolGenericActionServiceImpl)KraServiceLocator.getService(ProtocolGenericActionService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        genericActionService = null;
        genericActionServiceImpl = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    @Test
    public void testSetBusinessObjectService() {
        genericActionServiceImpl.setBusinessObjectService(businessObjectService);
        assertTrue(true);
    }

    @Test
    public void testClose() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.close(prot, actionBean);
        String expected = ProtocolStatus.CLOSED_ADMINISTRATIVELY;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testCloseEnrollment() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.closeEnrollment(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testExpire() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.expire(prot, actionBean);
        String expected = ProtocolStatus.EXPIRED;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testPermitDataAnalysis() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.permitDataAnalysis(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testReopen() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.reopen(prot, actionBean);
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    /**@Test
    public void testSuspendByIRB() throws Exception {
        GlobalVariables.setUserSession(new UserSession("testIrbAdmin")); //is an IRB
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspend(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_IRB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
        GlobalVariables.setUserSession(new UserSession("quickstart")); //reset testing user for further testing
    }*/
    
    @Test
    public void testSuspendByPI() throws Exception {
        //quickstart is a PI
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspend(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_PI;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testSuspendByDsmb() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.suspendByDsmb(prot, actionBean);
        String expected = ProtocolStatus.SUSPENDED_BY_DSMB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testTerminate() throws Exception {
        Protocol prot = getNewProtocol();
        ProtocolGenericActionBean actionBean = buildProtocolGenericActionBean();
        businessObjectService.save(prot);
        genericActionService.terminate(prot, actionBean);
        String expected = ProtocolStatus.TERMINATED_BY_IRB;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }
    
    private Protocol getNewProtocol() throws Exception{
        Protocol prot = ProtocolFactory.createProtocolDocument().getProtocol();
        return prot;
    }
    
    private ProtocolGenericActionBean buildProtocolGenericActionBean(){
        ProtocolGenericActionBean actionBean = new ProtocolGenericActionBean();
        actionBean.setComments(BASIC_COMMENT);
        actionBean.setActionDate(BASIC_ACTION_DATE);
        return actionBean;
    }

}
