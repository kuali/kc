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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.PersonEditableField;
import org.kuali.coeus.propdev.impl.editable.PersonEditableFieldRule;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.*;

/**
 * Contains tests for <code>{@link org.kuali.coeus.propdev.impl.editable.PersonEditableFieldRule}</code>
 */
public class PersonEditableFieldRuleTest extends MaintenanceRuleTestBase {

    private static final Log LOG = LogFactory.getLog(PersonEditableFieldRuleTest.class);

    private static final PersonEditableFieldFixture ADDRESS_LINE1_FIELD = PersonEditableFieldFixture.ADDRESS_LINE1_FIELD;
    private static final PersonEditableFieldFixture ADDRESS_LINE2_FIELD = PersonEditableFieldFixture.ADDRESS_LINE2_FIELD;
        

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Case for if a <code>{@link PersonEditableField}</code> already exists
     * 
     * @throws Exception
     */
    public void processCustomRouteDocumentBusinessRules_ExistingField() throws Exception {
        PersonEditableField editableField = ADDRESS_LINE1_FIELD.getInstance();
        MaintenanceDocument editableFieldDocument = newMaintDoc(editableField);
        PersonEditableFieldRule rule = setupMaintDocRule(editableFieldDocument, PersonEditableFieldRule.class);         
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(editableFieldDocument));
    }

    /**
     * Valid case where there are no persisted <code>{@link PersonEditableField}</code> already
     * 
     * @throws Exception
     */
    @Test
    public void processCustomRouteDocumentBusinessRules_Normal() throws Exception {
        PersonEditableField editableField = ADDRESS_LINE2_FIELD.getInstance();
        MaintenanceDocument editableFieldDocument = newMaintDoc(editableField);
        PersonEditableFieldRule rule = setupMaintDocRule(editableFieldDocument, PersonEditableFieldRule.class);
        
        LOG.info("pass " + rule.processCustomRouteDocumentBusinessRules(editableFieldDocument));
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
