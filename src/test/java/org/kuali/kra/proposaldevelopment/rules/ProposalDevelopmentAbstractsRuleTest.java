/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Test the business rules for Proposal Abstracts.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentAbstractsRuleTest extends ProposalDevelopmentRuleTestBase {

    private ProposalDevelopmentAbstractsRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentAbstractsRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a good case. 
     *  
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalAbstract proposalAbstract = new ProposalAbstract();
        proposalAbstract.setAbstractTypeCode("1");
        assertTrue(rule.processAddAbstractBusinessRules(document, proposalAbstract));
    }
    
    /**
     * Test adding an abstract with an invalid type code.
     * 
     * @throws Exception
     */
    @Test
    public void testInvalidAbstractType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalAbstract proposalAbstract = new ProposalAbstract();
        proposalAbstract.setAbstractTypeCode("20");
        assertFalse(rule.processAddAbstractBusinessRules(document, proposalAbstract));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(Constants.ABSTRACTS_PROPERTY_KEY);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ABSTRACT_TYPE_INVALID);
    }
    
    /**
     * Test adding an abstract with a duplicate type code, i.e. the
     * document already has an abstract with that type code.
     * 
     * @throws Exception
     */
    @Test
    public void testDuplicateAbstractType() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        addAbstract(document, "1");
        
        ProposalAbstract proposalAbstract = new ProposalAbstract();
        proposalAbstract.setAbstractTypeCode("1");
        assertFalse(rule.processAddAbstractBusinessRules(document, proposalAbstract));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(Constants.ABSTRACTS_PROPERTY_KEY);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_ABSTRACT_TYPE_DUPLICATE);
    }
    
    /**
     * This method...
     * @param document
     * @param abstractTypeCode
     */
    private void addAbstract(ProposalDevelopmentDocument document, String abstractTypeCode) {
        List<ProposalAbstract> abstracts = document.getProposalAbstracts();
        ProposalAbstract proposalAbstract = new ProposalAbstract();
        proposalAbstract.setAbstractTypeCode("1");
        abstracts.add(proposalAbstract);
    }
}
