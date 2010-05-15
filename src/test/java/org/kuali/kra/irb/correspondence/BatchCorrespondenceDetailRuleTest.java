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
package org.kuali.kra.irb.correspondence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

public class BatchCorrespondenceDetailRuleTest {
    
    @Before
    public void setUp() throws Exception {
        // Clear any error messages that may have been created in prior tests.
        MessageMap messageMap = GlobalVariables.getMessageMap();
        messageMap.clearErrorMessages();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * 
     * This test simulates a batch correspondence detail successfully being added.
     * @throws Exception
     */
    @Test
    public void testAddBatchCorrespondenceDetail() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondence();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = getBachCorrespondenceDetail();
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
    }

    /**
     * 
     * This test simulates a batch correspondence being added whose data is missing.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddBatchCorrespondenceDetailWithMissingData() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondence();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = new BatchCorrespondenceDetail();
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertFalse(rulePassed);

        /*
         * There should be two errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(2, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the protoCorrespTypeCode and noOfDaysTillNext fields are in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.noOfDaysTillNext"));
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.protoCorrespTypeCode"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.noOfDaysTillNext");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_NO_OF_DAYS_TILL_NEXT_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
        errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.protoCorrespTypeCode");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a batch correspondence being added whose noOfDaysTillNext is invalid.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddBatchCorrespondenceDetailInvalidNoOfDaysTillNext() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondence();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = getBachCorrespondenceDetail();
        newBatchCorrespondenceDetail.setNoOfDaysTillNext(80);
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the protoCorrespTypeCode and noOfDaysTillNext fields are in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.noOfDaysTillNext"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.noOfDaysTillNext");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_NO_OF_DAYS_TILL_NEXT_INVALID, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the batch correspondence details successfully being saved.
     * @throws Exception
     */
    @Test
    public void testSaveBatchCorrespondenceTemplate() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondence();
        boolean rulePassed = new BatchCorrespondenceDetailRule().processSaveBatchCorrespondenceDetailRules(batchCorrespondence );
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSaveBatchCorrespondenceDetailInvalidDefaultTimeWindow() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondence();
        batchCorrespondence.setDefaultTimeWindow(5);
        boolean rulePassed = new BatchCorrespondenceDetailRule().processSaveBatchCorrespondenceDetailRules(batchCorrespondence );
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the protoCorrespTypeCode and noOfDaysTillNext fields are in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("batchCorrespondence.defaultTimeWindow"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("batchCorrespondence.defaultTimeWindow");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_DEFAULT_TIME_WINDOW_INVALID, errorMessages.get(0).getErrorKey());
    }
    
    private BatchCorrespondence getBatchCorrespondence() {
        BatchCorrespondence batchCorrespondence = new BatchCorrespondence();
        batchCorrespondence.setDefaultTimeWindow(60);
        batchCorrespondence.setFinalActionCorrespType("26");
        batchCorrespondence.setFinalActionTypeCode("300");
        BatchCorrespondenceDetail batchCorrespondenceDetail = new BatchCorrespondenceDetail();
        batchCorrespondenceDetail.setProtoCorrespTypeCode("15");
        batchCorrespondenceDetail.setNoOfDaysTillNext(30);
        batchCorrespondence.getBatchCorrespondenceDetails().add(batchCorrespondenceDetail);
        return batchCorrespondence;
    }

    private BatchCorrespondenceDetail getBachCorrespondenceDetail() {
        BatchCorrespondenceDetail batchCorrespondenceDetail = new BatchCorrespondenceDetail();
        batchCorrespondenceDetail.setProtoCorrespTypeCode("10");
        batchCorrespondenceDetail.setNoOfDaysTillNext(20);
        return batchCorrespondenceDetail;
    }

}
