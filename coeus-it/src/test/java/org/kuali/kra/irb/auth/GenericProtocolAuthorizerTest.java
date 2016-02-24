/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.auth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class GenericProtocolAuthorizerTest extends KcIntegrationTestBase {
    
    private static final String VALID_TASK_NAME = GenericProtocolAuthorizer.TERMINATE_PROTOCOL;
    private static final String INVALID_TASK_NAME = "foobar";
    
    private KcAuthorizationService kraAuthorizationService;
    private ProtocolActionService protocolActionService;
    
    //private Mockery context;
    
    private GenericProtocolAuthorizer auth;

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        auth = new GenericProtocolAuthorizer();
        kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        protocolActionService = KcServiceLocator.getService(ProtocolActionService.class);
        auth.setKraAuthorizationService(kraAuthorizationService);
        auth.setProtocolActionService(protocolActionService);
    }

    @After
    public void tearDown() throws Exception {
        auth = null;
        kraAuthorizationService = null;
        protocolActionService = null;
        GlobalVariables.setUserSession(null);
        //context = null;
    }

    @Test
    public void testValidCloseEnrollment() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @Test
    public void testValidDataAnalysisOnly() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @Test
    public void testValidSuspend() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.SUSPEND_PROTOCOL);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @Test
    public void testValidClose() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_CLOSE);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.CLOSE_PROTOCOL);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @Test
    public void testValidReOpen() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @Test
    public void testInValidReOpen() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.REOPEN_PROTOCOL);
        assertFalse(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId() + "xyz", task));
    }
    
    @Test
    public void testValidTerminate() throws Exception {
        Protocol prot = getBaseProtocol(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, ProtocolSubmissionType.REQUEST_FOR_TERMINATION);
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, prot);
        auth.setGenericTaskName(GenericProtocolAuthorizer.TERMINATE_PROTOCOL);
        assertTrue(auth.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task));
    }
    
    @SuppressWarnings("unchecked")
    private Protocol getBaseProtocol(String protocolStatusCode, String submissionType) throws Exception{
        
        ProtocolDocument pd = ProtocolFactory.createProtocolDocument("123", new Integer(1));
        pd.getProtocol().setProtocolStatusCode(protocolStatusCode);
        ProtocolSubmission ps = new ProtocolSubmission();
        ps.setProtocol(pd.getProtocol());
        ps.setCommitteeId("789");
        ps.setScheduleId("456");
        ps.setSubmissionStatusCode(submissionType);
        List<ProtocolSubmission> protocolSubmissions = new ArrayList<ProtocolSubmission>();
        protocolSubmissions.add(ps);        
        pd.getProtocol().setProtocolSubmission(ps);
        pd.getProtocol().setProtocolSubmissions((List)protocolSubmissions);
        pd.getProtocol().setLeadUnitNumber("000001");
        return pd.getProtocol();     
    }

    @Test
    public void testValidSetGenericTaskName() {
        boolean validTest = true;
        try {
            auth.setGenericTaskName(VALID_TASK_NAME);
        } catch (IllegalArgumentException iae) {
            validTest = false;
        }
        assertTrue(validTest);
    }
    
    @Test
    public void testInValidSetGenericTaskName() {
        boolean validTest = true;
        try {
            auth.setGenericTaskName(INVALID_TASK_NAME);
        } catch (IllegalArgumentException iae) {
            validTest = false;
        }
        assertFalse(validTest);
    }
}
