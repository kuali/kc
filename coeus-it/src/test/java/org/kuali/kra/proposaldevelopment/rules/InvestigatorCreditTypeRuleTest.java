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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.person.creditsplit.InvestigatorCreditTypeRule;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import static org.junit.Assert.*;
/**
 * Contains tests for <code>{@link InvestigatorCreditTypeRule}</code>
 * 
 * @see org.kuali.coeus.propdev.impl.person.creditsplit.InvestigatorCreditTypeRule
 */
public class InvestigatorCreditTypeRuleTest extends MaintenanceRuleTestBase {
    private static final InvestigatorCreditTypeFixture SPACE = InvestigatorCreditTypeFixture.SPACE;
    private static final InvestigatorCreditTypeFixture NEGLIGENCE = InvestigatorCreditTypeFixture.NEGLIGENCE;
    

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
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
        getInstance().setCode(invCreditTypeCode);
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
