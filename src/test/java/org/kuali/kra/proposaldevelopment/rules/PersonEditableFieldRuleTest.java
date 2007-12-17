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
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;

/**
 * Contains tests for <code>{@link PersonEditableFieldRule}</code>
 */
public class PersonEditableFieldRuleTest extends MaintenanceRuleTestBase {
    private static final PersonEditableFieldFixture ADDRESS_LINE1_FIELD = PersonEditableFieldFixture.ADDRESS_LINE1_FIELD;
    private static final PersonEditableFieldFixture ADDRESS_LINE2_FIELD = PersonEditableFieldFixture.ADDRESS_LINE2_FIELD;
        
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
     * Case for if a <code>{@link PersonEditableField}</code> already exists
     * 
     * @throws Exception
     */
    public void processCustomRouteDocumentBusinessRules_ExistingField() throws Exception {
        PersonEditableField editableField = ADDRESS_LINE1_FIELD.getInstance();
        MaintenanceDocument editableFieldDocument = newMaintDoc(editableField);
        PersonEditableFieldRule rule = (PersonEditableFieldRule) setupMaintDocRule(editableFieldDocument, PersonEditableFieldRule.class);         
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(editableFieldDocument));
    }

    /**
     * Valid case where there are no persisted <code>{@link PersonEditableField}</code> already
     * 
     * @throws Exception
     */
    @Test
    public void processCustomRouteDocumentBusinessRules_Normal() throws Exception {
        // Comments are added until the document exists in KULUNT
        // PersonEditableField editableField = ADDRESS_LINE2_FIELD.getInstance();
        // MaintenanceDocument editableFieldDocument = newMaintDoc(editableField);
        // PersonEditableFieldRule rule = setupMaintDocRule(editableFieldDocument, PersonEditableFieldRule.class);         
    }
}

/**
 * Fixtures for <code>{@link PersonEditableField}</code> business object. Used to wrap the <code>{@link PersonEditableField}</code> constructor and create
 * arbitrary <code>{@link PersonEditableField}</code> instances. This is useful for creating fixtures as they are arbitrary in nature.
 */
enum PersonEditableFieldFixture {
    BLANK_FIELD(""),
    ADDRESS_LINE1_FIELD("addressLine1"),
    ADDRESS_LINE2_FIELD("addressLine2");
    
    private PersonEditableField field;
    
    /**
     * Create a <code>{@link PersonEditableField}</code> instance, and set active status from a parameter
     * 
     * @param fieldName
     * @param active
     */
    private PersonEditableFieldFixture(String fieldName, boolean active) {
        setInstance(new PersonEditableField());
        getInstance().setFieldName(fieldName);
        getInstance().setActive(active);
    }

    /**
     * Create a <code>{@link PersonEditableField}</code> instance and default to activated
     * 
     * @param fieldName
     */
    private PersonEditableFieldFixture(String fieldName) {
        this(fieldName, true);
    }

    /**
     * Read access to the enclosed <code>{@link PersonEditableField}</code> instance.
     * 
     * @return PersonEditableField
     */
    public PersonEditableField getInstance() {
        return field;
    }

    /**
     * Write access to the enclosed <code>{@link PersonEditableField}</code> instance.
     * 
     * @param field
     */ 
    public void setInstance(PersonEditableField field) {
        this.field = field;
    }
}