/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.document;

import org.junit.Test;
import org.kuali.core.document.MaintenanceDocumentBase;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.budget.bo.ValidCalcType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.test.SQLDataLoader;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ValidCalcTypeMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "ValidCalcTypeMaintenanceDocument";
    @Override
    public void tearDown() throws Exception {
        SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from valid_calc_types where CALC_TYPE_ID = 99 and RATE_CLASS_TYPE='E' and DEPENDENT_SEQ_NUMBER=1");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("update valid_calc_types set DEPENDENT_RATE_CLASS_TYPE = 'Y' where CALC_TYPE_ID = 1 and RATE_CLASS_TYPE='E' and DEPENDENT_SEQ_NUMBER=1");
        sqlDataLoader.runSql();
        sqlDataLoader = new SQLDataLoader("commit");
        sqlDataLoader.runSql();

        super.tearDown();
    }

    @Test
    public void testDocumentCreation() throws Exception {
        testDocumentCreation(DOCTYPE);
    }

    @Test
    public void testCopyValidCalcTypeMaintenanceDocument() throws Exception {
        HtmlPage validCalcTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Valid Calculation Type");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "calcTypeId", "1");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateClassCode", "5");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateTypeCode", "3");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateClassType", "E");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "dependentRateClassType", "Y");
        HtmlPage searchPage = clickOn(validCalcTypeMaintenanceLookupPage, "search");

        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage validCalcTypeMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink,
                "Kuali :: Valid Calc Types Maintenance Document");
        String documentNumber = getFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage,
                "document.documentHeader.documentNumber");

        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.financialDocumentDescription",
                "Valid Calc Type - copy test");

        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.calcTypeId", "99");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "12");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateTypeCode", "1");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassType", "E");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.dependentRateClassType",
                "X");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.dependentSeqNumber", "1");


        HtmlPage routedPage = clickOn(validCalcTypeMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route",
                "Kuali :: Valid Calc Types Maintenance Document");

        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class)
                .getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(), documentNumber);
        ValidCalcType validCalcType = (ValidCalcType) document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCalcType.getCalcTypeId(), "99");
        assertEquals(validCalcType.getRateClassCode(), "12");
        assertEquals(validCalcType.getRateTypeCode(), "1");
        assertEquals(validCalcType.getRateClassType(), "E");
        assertEquals(validCalcType.getDependentRateClassType(), "X");
        assertEquals(validCalcType.getDependentSeqNumber().toString(), "1");


    }

    @Test
    public void testEditValidCalcTypeMaintenanceDocument() throws Exception {
        HtmlPage validCalcTypeMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Valid Calculation Type");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "calcTypeId", "1");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateClassCode", "5");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateTypeCode", "3");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "rateClassType", "E");
        setFieldValue(validCalcTypeMaintenanceLookupPage, "dependentRateClassType", "Y");
        HtmlPage searchPage = clickOn(validCalcTypeMaintenanceLookupPage, "search");

        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage validCalcTypeMaintenanceDocumentMaintenanceEditPage = clickOn(editLink,
                "Kuali :: Valid Calc Types Maintenance Document");
        String documentNumber = getFieldValue(validCalcTypeMaintenanceDocumentMaintenanceEditPage,
                "document.documentHeader.documentNumber");

        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.financialDocumentDescription",
                "Valid Calc Types - edit test");
        setFieldValue(validCalcTypeMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.dependentRateClassType",
                "X");


        HtmlPage routedPage = clickOn(validCalcTypeMaintenanceDocumentMaintenanceEditPage, "methodToCall.route",
                "Kuali :: Valid Calc Types Maintenance Document");

        assertContains(routedPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class)
                .getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(), documentNumber);
        ValidCalcType validCalcType = (ValidCalcType) document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCalcType.getCalcTypeId(), "1");
        assertEquals(validCalcType.getRateClassCode(), "5");
        assertEquals(validCalcType.getRateTypeCode(), "3");
        assertEquals(validCalcType.getRateClassType(), "E");
        assertEquals(validCalcType.getDependentRateClassType(), "X");
        assertEquals(validCalcType.getDependentSeqNumber().toString(), "1");

    }


    @Test
    public void testCreateNewValidCalcTypeMaintenanceDocument() throws Exception {
        HtmlPage validCalcTypeMaintenancePage = getMaintenanceDocumentPage("Valid Calculation Type",
                "org.kuali.kra.budget.bo.ValidCalcType", "Kuali :: Valid Calc Types Maintenance Document");
        String documentNumber = getFieldValue(validCalcTypeMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(
                validCalcTypeMaintenancePage,
                "Edit Valid Calc Types New * Calc Type Id: * Dependent Seq Number: * Rate Class Type: Dependent Rate Class Type: Rate Class Code: Rate Type Code:");
        setFieldValue(validCalcTypeMaintenancePage, "document.documentHeader.financialDocumentDescription",
                "Valid Calc Types - test");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.calcTypeId", "99");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.rateClassCode", "12");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.rateTypeCode", "1");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.rateClassType", "E");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.dependentRateClassType", "X");
        setFieldValue(validCalcTypeMaintenancePage, "document.newMaintainableObject.dependentSeqNumber", "1");
        HtmlPage routedValidCalcTypeMaintenanceDocumentPage = clickOn(validCalcTypeMaintenancePage, "methodToCall.route",
                "Kuali :: Valid Calc Types Maintenance Document");

        assertContains(routedValidCalcTypeMaintenanceDocumentPage, "Document was successfully submitted.");
        assertContains(
                routedValidCalcTypeMaintenanceDocumentPage,
                "New Calc Type Id: 99 Dependent Seq Number: 1 Rate Class Type: E Dependent Rate Class Type: X Rate Class Code: 12 Rate Type Code: 1");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class)
                .getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(), documentNumber);
        ValidCalcType validCalcType = (ValidCalcType) document.getNewMaintainableObject().getBusinessObject();
        assertEquals(validCalcType.getCalcTypeId(), "99");
        assertEquals(validCalcType.getRateClassCode(), "12");
        assertEquals(validCalcType.getRateTypeCode(), "1");
        assertEquals(validCalcType.getRateClassType(), "E");
        assertEquals(validCalcType.getDependentRateClassType(), "X");
        assertEquals(validCalcType.getDependentSeqNumber().toString(), "1");

    }


}
