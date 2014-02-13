/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

/**
 * 
 * This class tests methods in InstitutionalProposalCostShare.java class
 */
public class InstitutionalProposalCostShareTest extends KcIntegrationTestBase {

private static final int IP_COST_SHARE_ATTRIBUTES_COUNT = 8;
    
    private InstitutionalProposalCostShare institutionalProposalCostShareBo;
    private InstitutionalProposal institutionalProposal;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        institutionalProposalCostShareBo = new InstitutionalProposalCostShare();
        institutionalProposal = new InstitutionalProposal();
        institutionalProposalCostShareBo.setInstitutionalProposal(institutionalProposal);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        institutionalProposalCostShareBo = null;
    }
    
    /**
     *  
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testInstitutionalProposalCostShareBoAttributesCount() throws Exception {              
        Assert.assertEquals(IP_COST_SHARE_ATTRIBUTES_COUNT, institutionalProposalCostShareBo.getClass().getDeclaredFields().length);
    }
    
}
