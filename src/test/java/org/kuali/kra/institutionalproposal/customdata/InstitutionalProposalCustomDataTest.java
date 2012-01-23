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
package org.kuali.kra.institutionalproposal.customdata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * This class...
 */
public class InstitutionalProposalCustomDataTest {
 //Changing the field count to include all declared fields since we can't override toStringMapper anymore
 private static final int INSTITUTIONAL_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT = 5;
    
    private InstitutionalProposalCustomData institutionalProposalCustomDataBo;
    private InstitutionalProposal institutionalProposal = new InstitutionalProposal();
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        institutionalProposalCustomDataBo = new InstitutionalProposalCustomData();
        institutionalProposalCustomDataBo.setInstitutionalProposal(institutionalProposal);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        institutionalProposalCustomDataBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardCostShareBoAttributesCount() throws Exception {              
        Assert.assertEquals(INSTITUTIONAL_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT, institutionalProposalCustomDataBo.getClass().getDeclaredFields().length);
    }
    
}
