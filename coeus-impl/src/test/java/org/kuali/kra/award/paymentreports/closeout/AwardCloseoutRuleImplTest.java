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
package org.kuali.kra.award.paymentreports.closeout;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class tests the AwardCloseoutRuleImpl.java
 */
public class AwardCloseoutRuleImplTest {

    public static final String CLOSE_OUT_REPORT_CODE_A = "A";
    public static final String CLOSE_OUT_REPORT_CODE_B = "B";
    public static final String CLOSE_OUT_REPORT_NAME = "FINANCIAL";
    
    AwardCloseoutRuleImpl awardCloseoutRuleImpl;
    AwardCloseout newCloseoutItem;
    AwardCloseout closeoutItem;
    List<AwardCloseout> closeoutItems;
    
    /**
     * 
     * This method gets executed before every unit test.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCloseoutRuleImpl = new AwardCloseoutRuleImpl();
        awardCloseoutRuleImpl.setErrorReporter(new ErrorReporterImpl());
        newCloseoutItem = new AwardCloseout();
        closeoutItem = new AwardCloseout();
        closeoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_A);
        closeoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        closeoutItems = new ArrayList<AwardCloseout>();
        closeoutItems.add(closeoutItem);
        GlobalVariables.setMessageMap(new MessageMap());
    }

    /**
     * 
     * This method gets executed after every unit test.
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCloseoutRuleImpl = null;
        closeoutItem = null;
        newCloseoutItem = null;
        closeoutItems = null;
        GlobalVariables.setMessageMap(null);
    }

    /**
     * 
     * This method tests the isUnique method.
     * 
     */
    @Test
    public final void testIsUniqueSuccess() {
        newCloseoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_B);
        newCloseoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        Assert.assertTrue(awardCloseoutRuleImpl.isUnique(closeoutItems, newCloseoutItem));
    }
    
    /**
     * 
     * This method tests the isUnique method.
     * 
     */
    @Test
    public final void testIsUniqueFailure() {        
        newCloseoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_A);
        newCloseoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        Assert.assertFalse(awardCloseoutRuleImpl.isUnique(closeoutItems, newCloseoutItem));
    }
    
    /**
     * 
     * This method tests the areRequiredFieldsComplete method.
     * 
     */
    @Test
    public final void testAreRequiredFieldsCompleteFailure() {
        Assert.assertFalse(awardCloseoutRuleImpl.areRequiredFieldsComplete(newCloseoutItem));        
    }
    
    /**
     * 
     * This method tests the areRequiredFieldsComplete method.
     * 
     */
    @Test
    public final void testAreRequiredFieldsCompleteSuccess() {
        Assert.assertTrue(awardCloseoutRuleImpl.areRequiredFieldsComplete(closeoutItem));        
    }

}
