/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.home.approvedsubawards;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleImpl;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
public class AwardApprovedSubawardRuleTest {
    
    public static final String ORGANIZATION_ONE = "organization one";
    public static final String ORGANIZATION_TWO = "organization two";
    public static final String ORGANIZATION_THREE = "organization three";
    public static final int TEN_THOUSAND = 10000;
    public static final int TWENTY_THOUSAND = 20000;
    public static final int THIRTY_THOUSAND = 30000;
    public static final int TWENTY_FIVE_THOUSAND = 25000;
    public static final int NEGATIVE_VALUE = -250;
    
    
    AwardApprovedSubawardRuleImpl awardApprovedSubawardRule;
    AwardApprovedSubaward awardApprovedSubawardOne;
    AwardApprovedSubaward awardApprovedSubawardTwo;
    AwardApprovedSubaward awardApprovedSubawardTest;
    AwardDocument awardDocument;
    List<AwardApprovedSubaward> awardApprovedSubawards;
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardApprovedSubawardRule = new AwardApprovedSubawardRuleImpl();
        awardApprovedSubawardOne = new AwardApprovedSubaward();
        awardApprovedSubawardTwo = new AwardApprovedSubaward();
        awardApprovedSubawardTest = new AwardApprovedSubaward();
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        awardApprovedSubawardOne.setOrganizationName(ORGANIZATION_ONE);
        awardApprovedSubawardOne.setAmount(new KualiDecimal(TEN_THOUSAND));
        awardApprovedSubawardTwo.setOrganizationName(ORGANIZATION_TWO);
        awardApprovedSubawardTwo.setAmount(new KualiDecimal(TWENTY_THOUSAND));
        awardApprovedSubawardTest.setOrganizationName(ORGANIZATION_THREE);
        awardApprovedSubawardTest.setAmount(new KualiDecimal(THIRTY_THOUSAND));

        awardApprovedSubawards.add(awardApprovedSubawardOne);
        awardApprovedSubawards.add(awardApprovedSubawardTwo);
        
        awardApprovedSubawardRule.setAwardApprovedSubaward(awardApprovedSubawardTest);
        awardApprovedSubawardRule.setAwardApprovedSubawards(awardApprovedSubawards);
        awardApprovedSubawardRule.setErrorPath("testErrorPath");
        
        
        GlobalVariables.setErrorMap(new ErrorMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardApprovedSubawardRule = null;
        awardApprovedSubawardOne = null;
        awardApprovedSubawardTwo = null;
        awardApprovedSubawardTest = null;
        awardApprovedSubawards = null;
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#processCommonValidations
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward)}.
     */
    @Test
    public final void testProcessCommonValidations() {
        Assert.assertTrue(awardApprovedSubawardRule.processCommonValidations());
    }
    
    /**
     *Test method for {@link org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#
     *ValidateApprovedSubawardDuplicateOrganization 
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward)}.
     */
    @Test
    public final void testValidateApprovedSubawardDuplicateOrganization () {
        Assert.assertTrue(awardApprovedSubawardRule.validateApprovedSubawardDuplicateOrganization());
        awardApprovedSubawardOne.setOrganizationName(ORGANIZATION_THREE);
        Assert.assertFalse(awardApprovedSubawardRule.validateApprovedSubawardDuplicateOrganization());
        awardApprovedSubawardOne.setOrganizationName(ORGANIZATION_ONE);
    }
    
    /**
     *Test method for {@link org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRule#
     *ValidateApprovedSubawardDuplicateOrganization 
     * (org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward)}.
     */
    @Test
    public final void testValidateApprovedSubawardAmount () {
        Assert.assertTrue(awardApprovedSubawardRule.validateApprovedSubawardAmount());
        awardApprovedSubawardTest.setAmount(new KualiDecimal(0));
        Assert.assertFalse(awardApprovedSubawardRule.validateApprovedSubawardAmount());
        awardApprovedSubawardTest.setAmount(new KualiDecimal(NEGATIVE_VALUE));
        Assert.assertFalse(awardApprovedSubawardRule.validateApprovedSubawardAmount());
        awardApprovedSubawardTest.setAmount(new KualiDecimal(TWENTY_FIVE_THOUSAND));
    }



}
