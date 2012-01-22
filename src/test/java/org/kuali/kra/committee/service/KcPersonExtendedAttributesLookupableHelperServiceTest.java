package org.kuali.kra.committee.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.KcPersonExtendedAttributesLookupableHelperServiceImpl;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class KcPersonExtendedAttributesLookupableHelperServiceTest extends KcUnitTestBase {
    private static final int NUMBER_LOOKUP_CRITERIA_FIELDS = 4;
    KcPersonExtendedAttributesLookupableHelperServiceImpl kcPersonExtendedAttributesLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        kcPersonExtendedAttributesLookupableHelperServiceImpl = 
            (KcPersonExtendedAttributesLookupableHelperServiceImpl) KraServiceLocator
                .getService("kcPersonExtendedAttributesLookupableHelperService");
        kcPersonExtendedAttributesLookupableHelperServiceImpl.setBusinessObjectClass(KcPersonExtendedAttributes.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
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
                    assertFieldProperties(field, "principalId", "org.kuali.rice.kim.api.identity.Person");
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
