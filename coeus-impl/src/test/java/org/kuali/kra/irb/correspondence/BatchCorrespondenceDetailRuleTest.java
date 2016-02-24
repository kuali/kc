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
package org.kuali.kra.irb.correspondence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;

import static org.junit.Assert.*;

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
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
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
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = new BatchCorrespondenceDetail();
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertFalse(rulePassed);

        /*
         * There should be two errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(2, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the protoCorrespTypeCode and daysToEvent fields are in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.daysToEvent"));
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.protoCorrespTypeCode"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.daysToEvent");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
        errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.protoCorrespTypeCode");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a batch correspondence being added whose daysToEvent is invalid.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddBatchCorrespondenceDetailInvalidDaysToEvent() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = getBachCorrespondenceDetail();
        newBatchCorrespondenceDetail.setDaysToEvent(80);
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the daysToEvent fields is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.daysToEvent"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.daysToEvent");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_INVALID, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates a batch correspondence being added whose daysToEvent is duplicate.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddBatchCorrespondenceDetailDuplicateDaysToEvent() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
        BatchCorrespondenceDetail newBatchCorrespondenceDetail = getBachCorrespondenceDetail();
        newBatchCorrespondenceDetail.setDaysToEvent(15);
        boolean rulePassed = new BatchCorrespondenceDetailRule().processAddBatchCorrespondenceDetailRules(batchCorrespondence, newBatchCorrespondenceDetail);
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the daysToEvent fields is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newBatchCorrespondenceDetail.daysToEvent"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newBatchCorrespondenceDetail.daysToEvent");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_DUPLICATE, errorMessages.get(0).getErrorKey());
    }
    
    /** 
     * This test simulates the batch correspondence details successfully being saved.
     * @throws Exception
     */
    @Test
    public void testSaveBatchCorrespondenceTemplate() throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
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
    public void testSaveBatchCorrespondenceDetailFinalActionDay () throws Exception {
        BatchCorrespondence batchCorrespondence = getBatchCorrespondenceAfter();
        batchCorrespondence.setFinalActionDay(5);
        boolean rulePassed = new BatchCorrespondenceDetailRule().processSaveBatchCorrespondenceDetailRules(batchCorrespondence );
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the finalActionDay field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("batchCorrespondence.finalActionDay"));

        /*
         * Verify that the correct error messages are in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("batchCorrespondence.finalActionDay");
        assertEquals(KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_INVALID, errorMessages.get(0).getErrorKey());
    }
    
    private BatchCorrespondence getBatchCorrespondenceAfter() {
        BatchCorrespondence batchCorrespondence = new BatchCorrespondence();
        batchCorrespondence.setSendCorrespondence("AFTER");
        batchCorrespondence.setFinalActionDay(30);
        batchCorrespondence.setFinalActionCorrespType("26");
        batchCorrespondence.setFinalActionTypeCode("300");
        BatchCorrespondenceDetail batchCorrespondenceDetail = new BatchCorrespondenceDetail();
        batchCorrespondenceDetail.setProtoCorrespTypeCode("15");
        batchCorrespondenceDetail.setDaysToEvent(15);
        batchCorrespondence.getBatchCorrespondenceDetails().add(batchCorrespondenceDetail);
        return batchCorrespondence;
    }

    private BatchCorrespondenceDetail getBachCorrespondenceDetail() {
        BatchCorrespondenceDetail batchCorrespondenceDetail = new BatchCorrespondenceDetail();
        batchCorrespondenceDetail.setProtoCorrespTypeCode("10");
        batchCorrespondenceDetail.setDaysToEvent(20);
        return batchCorrespondenceDetail;
    }

}
