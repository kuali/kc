/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class ProtocolBatchCorrespondenceSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DOCUMENT_TITLE = "Batch Correspondence";
    private static final String MAINTENANCE_DOCUMENT_TITLE = "Kuali :: Batch Correspondence";
    
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE_ID = "batchCorrespondence.batchCorrespondenceTypeCode";
    private static final String DAYS_TO_EVENT_ID = "newBatchCorrespondenceDetail.daysToEvent";
    private static final String PROTO_CORRESP_TYPE_CODE_ID = "newBatchCorrespondenceDetail.protoCorrespTypeCode";
    private static final String FINAL_ACTION_DAY_ID = "batchCorrespondence.finalActionDay";
    
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE = "Reminder to IRB Notifications";
    private static final String DAYS_TO_EVENT = "16";
    private static final String PROTO_CORRESP_TYPE_CODE = "Renewal Reminder Letter #1";
    private static final String FINAL_ACTION_DAY = "30";
    
    private static final String ERROR_NUMBER_OF_DAYS_MISSING = "Number of days missing.";
    private static final String ERROR_PROTO_CORRESP_TYPE_MISSING = "Protocol correspondence type missing.";
    private static final String ERROR_FINAL_ACTION_DAY_MISSING = "Final action day missing.";
    
    private static final String REFRESH_BUTTON = "methodToCall.start";
    private static final String ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON = "methodToCall.addBatchCorrespondenceDetail";
    private static final String DELETE_BATCH_CORRESPONDENCE_DETAIL_BUTTON = "methodToCall.deleteBatchCorrespondenceDetail.batchCorrespondenceDetail[1]}";

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
     * Test the add, delete, and reload actions.
     * 
     * @throws Exception
     */
    @Test
    public void testBatchCorrespondenceDetail() throws Exception {
        helper.lookupMaintenanceDocument(DOCUMENT_TITLE);

        helper.assertTitleContains(MAINTENANCE_DOCUMENT_TITLE);
        
        helper.set(BATCH_CORRESPONDENCE_TYPE_CODE_ID, BATCH_CORRESPONDENCE_TYPE_CODE);
        helper.click(REFRESH_BUTTON);
        
        helper.assertPageDoesNotContain(DAYS_TO_EVENT);
        
        helper.click(ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON);
        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_NUMBER_OF_DAYS_MISSING);
        helper.assertPageContains(ERROR_PROTO_CORRESP_TYPE_MISSING);
        
        helper.set(DAYS_TO_EVENT_ID, DAYS_TO_EVENT);
        helper.set(PROTO_CORRESP_TYPE_CODE_ID, PROTO_CORRESP_TYPE_CODE);
        helper.click(ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON);
        
        helper.assertPageContains(DAYS_TO_EVENT);
        
        helper.click(DELETE_BATCH_CORRESPONDENCE_DETAIL_BUTTON);
        
        helper.assertPageDoesNotContain(DAYS_TO_EVENT);
        
        helper.reloadDocument();
        helper.assertReload();
        
        helper.set(FINAL_ACTION_DAY_ID, Constants.EMPTY_STRING);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_FINAL_ACTION_DAY_MISSING);
        
        helper.set(FINAL_ACTION_DAY_ID, FINAL_ACTION_DAY);
        
        helper.saveDocument();
        helper.assertSave();
    }

}