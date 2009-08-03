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
package org.kuali.kra.institutionalproposal.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class...
 */
public class InstitutionalProposalUnrecoveredFandATest {

private static final int IP_UNRECOVERED_FNA_ATTRIBUTES_COUNT = 7;
    
    private InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandABo;
    private InstitutionalProposal institutionalProposal;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        institutionalProposalUnrecoveredFandABo = new InstitutionalProposalUnrecoveredFandA();
        institutionalProposal = new InstitutionalProposal();
        institutionalProposalUnrecoveredFandABo.setInstitutionalProposal(institutionalProposal);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        institutionalProposalUnrecoveredFandABo = null;
    }
    
    /**
     *  
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testInstitutionalProposalUnrecovedFandABoAttributesCount() throws Exception {              
        Assert.assertEquals(IP_UNRECOVERED_FNA_ATTRIBUTES_COUNT, institutionalProposalUnrecoveredFandABo.toStringMapper().size());
    }
}
