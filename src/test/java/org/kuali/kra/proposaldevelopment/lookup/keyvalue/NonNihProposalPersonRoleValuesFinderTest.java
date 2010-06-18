/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Tests the Non-Nih Roles Finder
 * 
 */

public class NonNihProposalPersonRoleValuesFinderTest extends ValuesFinderTestBase {
    
    @Override
    protected Class<NonNihProposalPersonRoleValuesFinder> getTestClass() {
        return NonNihProposalPersonRoleValuesFinder.class;
    }

    @Override
    protected List<KeyLabelPair> getKeyValues() {
        final List<KeyLabelPair> keylabel = new ArrayList<KeyLabelPair>();
        
        keylabel.add(new KeyLabelPair("", "select"));
        keylabel.add(new KeyLabelPair("PI", "Proposal Investigator Contact"));
        keylabel.add(new KeyLabelPair("COI", "Proposal Investigator Multiple"));
        keylabel.add(new KeyLabelPair("KP", "Key Person"));
        
        return keylabel;
    }
}
