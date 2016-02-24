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
package org.kuali.kra.award.paymentreports.paymentschedule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.*;

/**
 * This class tests AwardApprovedEquipmentRuleImpl behavior
 */
public class AwardPaymentScheduleRuleTest {
    private static final Calendar calendar = new GregorianCalendar();
    private AwardPaymentScheduleRuleImpl awardPaymentScheduleRule;
    private Award award;
    
    @Before
    public void setUp() throws Exception {
        awardPaymentScheduleRule = prepareTestReadyAwardPaymentScheduleRuleImpl();
        awardPaymentScheduleRule.setErrorReporter(new ErrorReporterImpl());
        award = new Award();
        award.setAwardId(1L);
        award.setAwardNumber("X1000");
        award.setSequenceNumber(1);
        calendar.set(2009, 4, 1);
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    @After
    public void tearDown() {
        award = null;
        awardPaymentScheduleRule = null;
    }
    
    @Test
    public void testIsUnique() {
        AwardPaymentSchedule paymentScheduleItem1 = createPaymentScheduleItem(new Date(calendar.getTimeInMillis()), 
                                                        award.getAwardNumber(), award.getSequenceNumber());
        AwardPaymentSchedule paymentScheduleItem2 = createPaymentScheduleItem(new Date(calendar.getTimeInMillis()), 
                                                        award.getAwardNumber(), award.getSequenceNumber());
                
        checkAddingNewItemToEmptyList(paymentScheduleItem1);
        award.add(paymentScheduleItem1);
        checkExistingEntriesDoesntTriggerErrorOnSave();
        addPaymentScheduleToAward(paymentScheduleItem1, paymentScheduleItem2);
        checkAddingDuplicateToCollection();
        checkEditingItemResultingInDuplicate(paymentScheduleItem2);
    }

    private void addPaymentScheduleToAward(AwardPaymentSchedule paymentScheduleItem1, 
            AwardPaymentSchedule paymentScheduleItem2) {
        paymentScheduleItem1.setAwardPaymentScheduleId(1L);
        award.add(paymentScheduleItem1);
        paymentScheduleItem2.setAwardPaymentScheduleId(2L);
        award.add(paymentScheduleItem2);
    }

    private void checkAddingDuplicateToCollection() {
        AwardPaymentSchedule item1Duplicate = createPaymentScheduleItem(new Date(calendar.getTimeInMillis()), award.getAwardNumber(), 
                                                    award.getSequenceNumber());
        Assert.assertFalse(awardPaymentScheduleRule.isUnique(award.getPaymentScheduleItems(), item1Duplicate));
    }
    
    private void checkAddingNewItemToEmptyList(AwardPaymentSchedule paymentScheduleItem1) {
        Assert.assertTrue(awardPaymentScheduleRule.isUnique(new ArrayList<AwardPaymentSchedule>(), 
                                                            paymentScheduleItem1));
    }

    private void checkExistingEntriesDoesntTriggerErrorOnSave() {
        List<AwardPaymentSchedule> items = award.getPaymentScheduleItems();
        for(AwardPaymentSchedule item : items) {
            Assert.assertTrue(awardPaymentScheduleRule.isUnique(items, item));
        }
    }
    
    private void checkEditingItemResultingInDuplicate(AwardPaymentSchedule paymentScheduleItem2) {        
        Assert.assertFalse(awardPaymentScheduleRule.isUnique(award.getPaymentScheduleItems(), paymentScheduleItem2));
    }
    
    private AwardPaymentSchedule createPaymentScheduleItem(Date dueDate, String awardNumber, Integer sequenceNumber) {
        return new AwardPaymentSchedule(dueDate, awardNumber, sequenceNumber);
    }
    
    private AwardPaymentScheduleRuleImpl prepareTestReadyAwardPaymentScheduleRuleImpl() {
        AwardPaymentScheduleRuleImpl approvedEquipmentRule = new AwardPaymentScheduleRuleImpl() {
            private Map<String, Collection<SoftError>> softErrors = new HashMap<String, Collection<SoftError>>();
            @Override
            public Map<String, Collection<SoftError>> getSoftErrors() { 
                return softErrors; 
            }
        };
        return approvedEquipmentRule;
    }
}
