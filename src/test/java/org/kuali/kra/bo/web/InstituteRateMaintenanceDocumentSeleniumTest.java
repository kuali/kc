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
package org.kuali.kra.bo.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;

/**
 * Tests the Institute Rate maintenance document.
 */
public class InstituteRateMaintenanceDocumentSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Institute Rate";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Institute Rates Maintenance Document"; 
    
    private static final String ACTIVITY_TYPE_CODE_ID = "activityTypeCode";
    private static final String FISCAL_YEAR_ID = "fiscalYear";
    private static final String ON_OFF_CAMPUS_FLAG_ID = "onOffCampusFlag";
    private static final String RATE_CLASS_CODE_ID = "rateClassCode";
    private static final String RATE_TYPE_CODE_ID = "rateTypeCode";
    private static final String START_DATE_ID = "startDate";
    private static final String UNIT_NUMBER_ID = "unitNumber";
    private static final String RATE_ID = "instituteRate";
    private static final String ACTIVE_ID = "active";
    
    private static final String CREATE_DOCUMENT_DESCRIPTION = "Institute Rate - Test Create";
    private static final String CREATE_RATE = "1.11";
    
    private static final String EDIT_DOCUMENT_DESCRIPTION = "Institute Rate - Test Edit";
    private static final String EDIT_RATE_1 = "1.11";
    private static final String EDIT_RATE_2 = "2.22";
    
    private static final String COPY_DOCUMENT_DESCRIPTION = "Institute Rate - Test Copy";
    private static final String COPY_RATE = "2.22";
    
    private static final String DEFAULT_ACTIVITY_TYPE_CODE = "1";
    private static final String DEFAULT_RATE_CLASS_CODE = "5";
    private static final String DEFAULT_RATE_TYPE_CODE = "2";
    private static final boolean DEFAULT_ON_OFF_CAMPUS_FLAG = true;
    private static final String DEFAULT_UNIT_NUMBER = "000001";
    private static final boolean DEFAULT_ACTIVE = true;
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    private MaintenanceDocumentSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = MaintenanceDocumentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test creating an institute rate.
     *
     * @throws Exception
     */
    @Test
    public void testCreateInstituteRate() throws Exception {
        String fiscalYear = getNewFiscalYear();
        String startDate = formatter.format(DateUtils.parseDate(fiscalYear, new String[] {"yyyy"}));
        String documentNumber = createNewMaintenanceDocument(CREATE_DOCUMENT_DESCRIPTION, fiscalYear, startDate, CREATE_RATE);
        
        verifyExistingMaintenanceDocument(documentNumber, fiscalYear, startDate, CREATE_RATE);
    }

    /**
     * Test editing an institute rate.
     *
     * @throws Exception
     */
    @Test
    public void testEditInstituteRate() throws Exception {
        String fiscalYear = getNewFiscalYear();
        String startDate = formatter.format(DateUtils.parseDate(fiscalYear, new String[] {"yyyy"}));
        createNewMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fiscalYear, startDate, EDIT_RATE_1);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(FISCAL_YEAR_ID, fiscalYear);
        searchValues.put(START_DATE_ID, startDate);
        String documentNumber = helper.editMaintenanceDocument(DOCUMENT_TITLE, InstituteRate.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(RATE_ID, EDIT_RATE_2);
        helper.populateMaintenanceDocument(EDIT_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, fiscalYear, startDate, EDIT_RATE_2);
    }

    /**
     * Test copying an institute rate.
     *
     * @throws Exception
     */
    @Test
    public void testCopyInstituteRate() throws Exception {
        String fiscalYear1 = getNewFiscalYear();
        String startDate1 = formatter.format(DateUtils.parseDate(fiscalYear1, new String[] {"yyyy"}));
        createNewMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fiscalYear1, startDate1, COPY_RATE);
        
        Map<String, String> searchValues = new LinkedHashMap<String, String>();
        searchValues.put(FISCAL_YEAR_ID, fiscalYear1);
        searchValues.put(START_DATE_ID, startDate1);
        String documentNumber = helper.copyMaintenanceDocument(DOCUMENT_TITLE, InstituteRate.class.getName(), searchValues, MAINTENANCE_DOCUMENT_TITLE);
        
        String fiscalYear2 = getNewFiscalYear();
        String startDate2 = formatter.format(DateUtils.parseDate(fiscalYear1, new String[] {"yyyy"}));
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ACTIVITY_TYPE_CODE_ID, DEFAULT_ACTIVITY_TYPE_CODE);
        fieldValues.put(FISCAL_YEAR_ID, fiscalYear2);
        fieldValues.put(ON_OFF_CAMPUS_FLAG_ID, String.valueOf(DEFAULT_ON_OFF_CAMPUS_FLAG));
        fieldValues.put(RATE_CLASS_CODE_ID, DEFAULT_RATE_CLASS_CODE);
        fieldValues.put(RATE_TYPE_CODE_ID, DEFAULT_RATE_TYPE_CODE);
        fieldValues.put(START_DATE_ID, startDate2);
        fieldValues.put(UNIT_NUMBER_ID, DEFAULT_UNIT_NUMBER);
        fieldValues.put(RATE_ID, COPY_RATE);
        fieldValues.put(ACTIVE_ID, String.valueOf(DEFAULT_ACTIVE));
        helper.populateMaintenanceDocument(COPY_DOCUMENT_DESCRIPTION, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        verifyExistingMaintenanceDocument(documentNumber, fiscalYear2, startDate2, COPY_RATE);
    }
    
    /**
     * Create a new maintenance document.
     * 
     * @param documentDescription the document description
     * @param fiscalYear the fiscal year
     * @param startDate the start date
     * @param rate the rate
     * @return the document number of the new maintenance document
     */
    private String createNewMaintenanceDocument(String documentDescription, String fiscalYear, String startDate, String rate) {
        String documentNumber = helper.createMaintenanceDocument(DOCUMENT_TITLE, InstituteRate.class.getName(), MAINTENANCE_DOCUMENT_TITLE);
    
        Map<String, String> fieldValues = new LinkedHashMap<String, String>();
        fieldValues.put(ACTIVITY_TYPE_CODE_ID, DEFAULT_ACTIVITY_TYPE_CODE);
        fieldValues.put(FISCAL_YEAR_ID, fiscalYear);
        fieldValues.put(ON_OFF_CAMPUS_FLAG_ID, String.valueOf(DEFAULT_ON_OFF_CAMPUS_FLAG));
        fieldValues.put(RATE_CLASS_CODE_ID, DEFAULT_RATE_CLASS_CODE);
        fieldValues.put(RATE_TYPE_CODE_ID, DEFAULT_RATE_TYPE_CODE);
        fieldValues.put(START_DATE_ID, startDate);
        fieldValues.put(UNIT_NUMBER_ID, DEFAULT_UNIT_NUMBER);
        fieldValues.put(RATE_ID, rate);
        fieldValues.put(ACTIVE_ID, String.valueOf(DEFAULT_ACTIVE));
        
        helper.populateMaintenanceDocument(documentDescription, fieldValues);
        
        helper.routeDocument();
        helper.assertRoute();
        
        return documentNumber;
    }
    
    /**
     * Verify the details of an existing maintenance document.
     *
     * @param documentNumber the document of the maintenance document to verify
     * @param fiscalYear the fiscal year
     * @param startDate the start date
     * @param rate the rate
     * @throws Exception
     */
    private void verifyExistingMaintenanceDocument(String documentNumber, String fiscalYear, String startDate, String rate) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) getDocumentService().getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(documentNumber, document.getDocumentHeader().getDocumentNumber());
        InstituteRate instituteRate = (InstituteRate) document.getNewMaintainableObject().getDataObject();
        assertEquals(DEFAULT_ACTIVITY_TYPE_CODE, instituteRate.getActivityTypeCode());
        assertEquals(fiscalYear, instituteRate.getFiscalYear());
        assertEquals(DEFAULT_ON_OFF_CAMPUS_FLAG, instituteRate.getOnOffCampusFlag());
        assertEquals(DEFAULT_RATE_CLASS_CODE, instituteRate.getRateClassCode());
        assertEquals(DEFAULT_RATE_TYPE_CODE, instituteRate.getRateTypeCode());
        assertEquals(startDate, formatter.format(instituteRate.getStartDate()));
        assertEquals(DEFAULT_UNIT_NUMBER, instituteRate.getUnitNumber());
        assertEquals(rate, instituteRate.getInstituteRate().toString());
        assertEquals(DEFAULT_ACTIVE, instituteRate.getActive());
    }
    
    /**
     * Create a new unique fiscal year.
     * 
     * @return a new unique fiscal year
     */
    @SuppressWarnings("unchecked")
    private String getNewFiscalYear() {
        int maxFiscalYear = 1;
        Collection<InstituteRate> instituteRates = getBusinessObjectService().findAll(InstituteRate.class);
        for (InstituteRate instituteRate : instituteRates) {
            int fiscalYear = NumberUtils.toInt(instituteRate.getFiscalYear(), 1970);
            if (fiscalYear > maxFiscalYear) {
                maxFiscalYear = fiscalYear;
            }
        }
        
        return String.valueOf(maxFiscalYear + 1);
    }
    
}