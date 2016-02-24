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
package org.kuali.kra.committee.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class KcPersonExtendedAttributesLookupableHelperServiceTest extends KcIntegrationTestBase {
    private static final int NUMBER_LOOKUP_CRITERIA_FIELDS = 4;
    LookupableHelperService kcPersonExtendedAttributesLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        kcPersonExtendedAttributesLookupableHelperServiceImpl =  KcServiceLocator.getService("kcPersonExtendedAttributesLookupableHelperService");
        kcPersonExtendedAttributesLookupableHelperServiceImpl.setBusinessObjectClass(KcPersonExtendedAttributes.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        kcPersonExtendedAttributesLookupableHelperServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }

    /**
     * 
     * This method is to test getrows.  The lookup fields will be updated and make sure 
     * the properties of the field are correct
     */
    @Test
    public void testGetRows() {

        List<Row> rows = kcPersonExtendedAttributesLookupableHelperServiceImpl.getRows();
        assertEquals(rows.size(), NUMBER_LOOKUP_CRITERIA_FIELDS);
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("personId")) {
                    assertFieldProperties(field, "principalId", Person.class.getName());
                }
            }
        }
    }

    private void assertFieldProperties(Field field, String keyName, String className) {
        assertEquals(field.getFieldConversions(), keyName + ":" + field.getPropertyName());
        assertTrue(field.isFieldDirectInquiryEnabled());
        assertEquals(field.getInquiryParameters(), field.getPropertyName() + ":" + keyName);
        assertEquals(field.getQuickFinderClassNameImpl(), className);

    }

}
