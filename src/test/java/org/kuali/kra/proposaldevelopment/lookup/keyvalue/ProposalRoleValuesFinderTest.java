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

import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Test the Proposal Role Values Finder.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public class ProposalRoleValuesFinderTest extends ValuesFinderTestBase {

    @Override
    protected Class<ProposalRoleValuesFinder> getTestClass() {
        return ProposalRoleValuesFinder.class;
    }
    
    /**
     * @see org.kuali.kra.keyvalue.ValuesFinderTestBase#addKeyValues()
     */
    @Override
    protected List<KeyLabelPair> getKeyValues() {
        final List<KeyLabelPair> keylabel = new ArrayList<KeyLabelPair>();
        
        keylabel.add(createKeyValue(RoleConstants.UNASSIGNED, RoleConstants.UNASSIGNED));
        keylabel.add(createKeyValue(RoleConstants.AGGREGATOR, RoleConstants.AGGREGATOR));
        keylabel.add(createKeyValue(RoleConstants.NARRATIVE_WRITER, RoleConstants.NARRATIVE_WRITER));
        keylabel.add(createKeyValue(RoleConstants.BUDGET_CREATOR, RoleConstants.BUDGET_CREATOR));
        keylabel.add(createKeyValue(RoleConstants.VIEWER, RoleConstants.VIEWER));
        
        return keylabel;
    }
}
