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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.CreditSplitValidator;

/**
 * Class to test use cases of <code>{@link CreditSplitValidator}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.3 $
 */
public class CreditSplitValidatorTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CreditSplitValidatorTest.class);
    private CreditSplitValidator validator;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        validator = new CreditSplitValidator();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

   
    /**
     * Validates that <code>{@link ProposalPersonCreditSplit}</code> instances 
     */
    @Test
    public void validate_creditSplitsAddToOneHundred() {
        
        
    }
    
    /**
     *
     * @return ProposalDevelopmentDocument
     */
    private ProposalDevelopmentDocument createInvalidDocument() {
        ProposalDevelopmentDocument retval = new ProposalDevelopmentDocument();
        retval.getProposalPersons().add(createInvalidPersonWithCreditSplit());
        return retval;
    }
    
    private ProposalPerson createInvalidPersonWithCreditSplit() {
        ProposalPerson retval = new ProposalPerson();

        return retval;
    }
    
    private ProposalPersonUnit createInvalidUnitWithCreditSplit() {
        return null;
    }
    
    private ProposalDevelopmentDocument createValidDocument() {
        ProposalDevelopmentDocument retval = new ProposalDevelopmentDocument();
        retval.getProposalPersons().add(createValidPersonWithCreditSplit());
        return retval;
    }

    private ProposalPerson createValidPersonWithCreditSplit() {
        ProposalPerson retval = new ProposalPerson();
        
        return retval;
    }
    
    private ProposalPersonUnit createValidUnitWithCreditSplit() {
        return null;
    }
}
