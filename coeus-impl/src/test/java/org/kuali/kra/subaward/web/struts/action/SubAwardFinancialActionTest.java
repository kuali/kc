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
package org.kuali.kra.subaward.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class SubAwardFinancialActionTest {

    SubAwardFinancialAction subAwardFinancialAction; 
    SubAward subAward;
    SubAwardAmountInfo amountInfo;  

    @Before
    public void setUp() throws Exception {

        subAwardFinancialAction = new SubAwardFinancialAction() {
        	@Override
        	public void saveSubAwardAmountInfo(SubAwardAmountInfo subAwardAmountInfo) { }
        };
        subAward = new SubAward();
        amountInfo = new SubAwardAmountInfo();
        amountInfo.setAnticipatedAmount(new ScaleTwoDecimal(5));
        amountInfo.setAnticipatedChange(new ScaleTwoDecimal(8));
        amountInfo.setEffectiveDate(new Date(System.currentTimeMillis()));
        amountInfo.setObligatedAmount(new ScaleTwoDecimal(7));
        amountInfo.setObligatedChange(new ScaleTwoDecimal(6));
        amountInfo.setComments("Test Comments");
    }
    @After
    public void tearDown() throws Exception {

        subAwardFinancialAction = null;
        subAward = null;
        amountInfo = null;
    }

    @Test
    public void testAddAmountInfoToSubAward(){
        Assert.assertTrue(subAwardFinancialAction.addAmountInfoToSubAward(subAward, amountInfo));
    }

}
