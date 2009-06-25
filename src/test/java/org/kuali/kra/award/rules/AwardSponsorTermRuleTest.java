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
package org.kuali.kra.award.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests the rule class of Award Sponsor Term.
 */
public class AwardSponsorTermRuleTest {
    
    private static final Long ONE = new Long(1);
    private static final Long TWO = new Long(2);
    private static final Long THREE = new Long(3);
    private static final String FOUR = "4";

    
    AwardSponsorTerm awardSponsorTerm1;
    AwardSponsorTerm awardSponsorTerm2;
    AwardSponsorTerm awardSponsorTerm3;
    AwardSponsorTermRuleImpl awardSponsorTermRule;
    List<AwardSponsorTerm> awardSponsorTerms;
    SponsorTerm sponsorTerm;
    
    
    /**
     * This method initializes fields and objects for testing
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardSponsorTerms = new ArrayList<AwardSponsorTerm>();
        awardSponsorTermRule = new AwardSponsorTermRuleImpl();
        awardSponsorTerm1 = new AwardSponsorTerm();
        awardSponsorTerm2 = new AwardSponsorTerm();
        awardSponsorTerm3 = new AwardSponsorTerm();
        sponsorTerm = new SponsorTerm();
        sponsorTerm.setSponsorTermTypeCode(FOUR);
        awardSponsorTerm3.setSponsorTerm(sponsorTerm);
        
        awardSponsorTerm1.setSponsorTermId(ONE);
        awardSponsorTerm2.setSponsorTermId(TWO);
        awardSponsorTerm3.setSponsorTermId(THREE);
        awardSponsorTerms.add(awardSponsorTerm1);
        awardSponsorTerms.add(awardSponsorTerm2);
        GlobalVariables.setErrorMap(new ErrorMap());
        
        
    }
    
    /**
     * This method sets objects to null.
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardSponsorTermRule = null;
        awardSponsorTerm1 = null;
        awardSponsorTerm2 = null;
        awardSponsorTerm3 = null;
        awardSponsorTerms = null;
    }
    
    /**
     * This method tests rule method for non-duplicate SponsorTerms in an award. It sets duplicate fields and retests to ensure
     * failure.
     */
    @Test
    public final void testValidateAwardSponsorTermNotDuplicate() {
        Assert.assertTrue(awardSponsorTermRule.validateAwardSponsorTermNotDuplicate(awardSponsorTerm3, awardSponsorTerms));
        awardSponsorTerm3.setSponsorTermId(TWO);
        Assert.assertFalse(awardSponsorTermRule.validateAwardSponsorTermNotDuplicate(awardSponsorTerm3, awardSponsorTerms));
        awardSponsorTerm3.setSponsorTermId(THREE);
    }

}
