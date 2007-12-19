/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;

/**
 * Contains tests for <code>{@link InvestigatorCreditTypeRule}</code>
 * 
 * @see org.kuali.kra.proposaldevelopment.rules.InvestigatorCreditTypeRule
 */
public class InvestigatorCreditTypeRuleTest extends MaintenanceRuleTestBase {
    private static final InvestigatorCreditTypeFixture RECOGNITION = InvestigatorCreditTypeFixture.RECOGNITION;
    private static final InvestigatorCreditTypeFixture RESPONSIBILITY = InvestigatorCreditTypeFixture.RESPONSIBILITY;
    private static final InvestigatorCreditTypeFixture SPACE = InvestigatorCreditTypeFixture.SPACE;
    private static final InvestigatorCreditTypeFixture NEGLIGENCE = InvestigatorCreditTypeFixture.NEGLIGENCE;
    
    /**
     * @throws Exception 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    /**
     * @throws Exception 
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Case for if a <code>{@link InvestigatorCreditType}</code> already exists
     * 
     * @throws Exception
     */
    @Test
    public void processCustomRouteDocumentBusinessRules_ExistingField() throws Exception {
        InvestigatorCreditType creditType = SPACE.getInstance();
        MaintenanceDocument creditTypeDocument = newMaintDoc(creditType);
        InvestigatorCreditTypeRule rule = (InvestigatorCreditTypeRule) setupMaintDocRule(creditTypeDocument, InvestigatorCreditTypeRule.class);         
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(creditTypeDocument));
    }
    
    /**
     * Case for testing adding a new <code>{@link InvestigatorCreditType}</code> where one doesn't exist yet.
     * 
     * @throws Exception
     */
    @Test
    public void processCustomRouteDocumentBusinessRules_Normal() throws Exception {  
        InvestigatorCreditType creditType = NEGLIGENCE.getInstance();
        MaintenanceDocument creditTypeDocument = newMaintDoc(creditType);
        InvestigatorCreditTypeRule rule = (InvestigatorCreditTypeRule) setupMaintDocRule(creditTypeDocument, InvestigatorCreditTypeRule.class);         
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(creditTypeDocument));        
    }
}

/**
 * Fixtures for <code>{@link InvestigatorCreditType}</code> business object. Used to wrap the <code>{@link InvestigatorCreditType}</code> constructor and create
 * arbitrary <code>{@link InvestigatorCreditType}</code> instances. This is useful for creating fixtures as they are arbitrary in nature.
 */
enum InvestigatorCreditTypeFixture {
    RECOGNITION("recognition"),
    RESPONSIBILITY("responsibility"),
    SPACE("space"),
    FINANCIAL("financial"),
    NEGLIGENCE("negligence");
    
    private InvestigatorCreditType creditType;
    
    /**
     * Create a <code>{@link InvestigatorCreditType}</code> instance, and set active status from a parameter
     * 
     * @param invCreditTypeCode
     * @param active
     */
    private InvestigatorCreditTypeFixture(String invCreditTypeCode, boolean active) {
        setInstance(new InvestigatorCreditType());
        getInstance().setInvCreditTypeCode(invCreditTypeCode);
        getInstance().setActive(active);
    }

    /**
     * Create a <code>{@link InvestigatorCreditType}</code> instance and default to activated
     * 
     * @param invCreditType
     */
    private InvestigatorCreditTypeFixture(String invCreditType) {
        this(invCreditType, true);
    }

    /**
     * Read access to the enclosed <code>{@link InvestigatorCreditType}</code> instance.
     * 
     * @return InvestigatorCreditType
     */
    public InvestigatorCreditType getInstance() {
        return creditType;
    }

    /**
     * Write access to the enclosed <code>{@link InvestigatorCreditType}</code> instance.
     * 
     * @param type
     */ 
    public void setInstance(InvestigatorCreditType type) {
        this.creditType = type;
    }
}