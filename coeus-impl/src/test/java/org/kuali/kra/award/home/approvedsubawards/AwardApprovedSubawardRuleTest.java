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
package org.kuali.kra.award.home.approvedsubawards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class AwardApprovedSubawardRuleTest {
    
    public static final String ORGANIZATION_ONE = "organization one";
    public static final String ORGANIZATION_TWO = "organization two";
    public static final String ORGANIZATION_THREE = "organization three";
    public static final int TEN_THOUSAND = 10000;
    public static final int TWENTY_THOUSAND = 20000;
    public static final int THIRTY_THOUSAND = 30000;

    
    AwardApprovedSubawardRuleImpl awardApprovedSubawardRule;
    AwardApprovedSubaward awardApprovedSubawardOne;
    AwardApprovedSubaward awardApprovedSubawardTwo;
    AwardApprovedSubaward awardApprovedSubawardTest;
    List<AwardApprovedSubaward> awardApprovedSubawards;

    @Before
    public void setUp() throws Exception {
        awardApprovedSubawardRule = new AwardApprovedSubawardRuleImpl();
        awardApprovedSubawardOne = new AwardApprovedSubaward();
        awardApprovedSubawardTwo = new AwardApprovedSubaward();
        awardApprovedSubawardTest = new AwardApprovedSubaward();
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        awardApprovedSubawardOne.setOrganizationName(ORGANIZATION_ONE);
        awardApprovedSubawardOne.setAmount(new ScaleTwoDecimal(TEN_THOUSAND));
        awardApprovedSubawardTwo.setOrganizationName(ORGANIZATION_TWO);
        awardApprovedSubawardTwo.setAmount(new ScaleTwoDecimal(TWENTY_THOUSAND));
        awardApprovedSubawardTest.setOrganizationName(ORGANIZATION_THREE);
        awardApprovedSubawardTest.setAmount(new ScaleTwoDecimal(THIRTY_THOUSAND));

        awardApprovedSubawards.add(awardApprovedSubawardOne);
        awardApprovedSubawards.add(awardApprovedSubawardTwo);
        
        awardApprovedSubawardRule.setAwardApprovedSubaward(awardApprovedSubawardTest);
        awardApprovedSubawardRule.setAwardApprovedSubawards(awardApprovedSubawards);
        awardApprovedSubawardRule.setErrorPath("testErrorPath");
        
        
        GlobalVariables.setMessageMap(new MessageMap());
          
    }

    @After
    public void tearDown() throws Exception {
        awardApprovedSubawardRule = null;
        awardApprovedSubawardOne = null;
        awardApprovedSubawardTwo = null;
        awardApprovedSubawardTest = null;
        awardApprovedSubawards = null;
    }
    
    //removed existing test functions as they all dealt with code moved to audit rule
    @Test
    public void foo() throws Exception {
        assertTrue(true);
    }
    
    



}
