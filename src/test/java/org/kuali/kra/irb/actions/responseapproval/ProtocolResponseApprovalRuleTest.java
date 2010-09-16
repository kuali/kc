/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.responseapproval;

import java.sql.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the business rules for Assigning a protocol to a committee.
 */
public class ProtocolResponseApprovalRuleTest extends ProtocolRuleTestBase {
    
    private static final Date APPROVAL_DATE = new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), -1).getTime());
    private static final Date EXPIRATION_DATE = new Date(DateUtils.addYears(APPROVAL_DATE, 1).getTime());
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    
    private static final String APPROVAL_DATE_FIELD = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".approvalDate";
    private static final String EXPIRATION_DATE_FIELD = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".expirationDate";
    private static final String ACTION_DATE_FIELD = Constants.PROTOCOL_RESPONSE_APPROVE_ACTION_PROPERTY_KEY + ".actionDate";
    
    private ProtocolResponseApprovalRule rule;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolResponseApprovalRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Tests a valid Response Approval.
     * @throws Exception
     */
    @Test
    public void testOk() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolApproveBean actionBean = new ProtocolApproveBean(null);
        actionBean.setApprovalDate(APPROVAL_DATE);
        actionBean.setExpirationDate(EXPIRATION_DATE);
        actionBean.setActionDate(ACTION_DATE);
        
        assertTrue(rule.processRules(new ProtocolResponseApprovalEvent<ProtocolResponseApprovalRule>(protocolDocument, actionBean)));
        assertTrue(GlobalVariables.getMessageMap().hasNoErrors());
    }

    /**
     * Tests an invalid Response Approval with no approval date.
     * @throws Exception
     */
    @Test
    public void testNoApprovalDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolApproveBean actionBean = new ProtocolApproveBean(null);
        actionBean.setApprovalDate(null);
        actionBean.setExpirationDate(EXPIRATION_DATE);
        actionBean.setActionDate(ACTION_DATE);
        
        assertFalse(rule.processRules(new ProtocolResponseApprovalEvent<ProtocolResponseApprovalRule>(protocolDocument, actionBean)));
        assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_DATE_REQUIRED);
    }
    
    /**
     * Tests an invalid Response Approval with no expiration date.
     * @throws Exception
     */
    @Test
    public void testNoExpirationDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolApproveBean actionBean = new ProtocolApproveBean(null);
        actionBean.setApprovalDate(APPROVAL_DATE);
        actionBean.setExpirationDate(null);
        actionBean.setActionDate(ACTION_DATE);
        
        assertFalse(rule.processRules(new ProtocolResponseApprovalEvent<ProtocolResponseApprovalRule>(protocolDocument, actionBean)));
        assertError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_EXPIRATION_DATE_REQUIRED);
    }
    
    /**
     * Tests an invalid Response Approval with no action date.
     * @throws Exception
     */
    @Test
    public void testNoActionDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolApproveBean actionBean = new ProtocolApproveBean(null);
        actionBean.setApprovalDate(APPROVAL_DATE);
        actionBean.setExpirationDate(EXPIRATION_DATE);
        actionBean.setActionDate(null);
        
        assertFalse(rule.processRules(new ProtocolResponseApprovalEvent<ProtocolResponseApprovalRule>(protocolDocument, actionBean)));
        assertError(ACTION_DATE_FIELD, KeyConstants.ERROR_PROTOCOL_APPROVAL_ACTION_DATE_REQUIRED);
    }
}
