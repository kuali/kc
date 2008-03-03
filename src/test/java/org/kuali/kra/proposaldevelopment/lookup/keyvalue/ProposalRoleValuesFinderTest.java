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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.Test;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;

/**
 * Test the Proposal Role Values Finder.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalRoleValuesFinderTest extends ValuesFinderTestBase {

    /**
     * Constructs a ProposalRoleValuesFinderTest.
     */
    public ProposalRoleValuesFinderTest() {
        setTestClass(ProposalRoleValuesFinder.class);
    }

    @Test public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }

    /**
     * @see org.kuali.kra.keyvalue.ValuesFinderTestBase#addKeyValues()
     */
    @Override
    protected void addKeyValues() {
        addKeyValue(RoleConstants.UNASSIGNED, RoleConstants.UNASSIGNED_LABEL);
        addKeyValue(RoleConstants.AGGREGATOR, RoleConstants.AGGREGATOR_LABEL);
        addKeyValue(RoleConstants.NARRATIVE_WRITER, RoleConstants.NARRATIVE_WRITER_LABEL);
        addKeyValue(RoleConstants.BUDGET_CREATOR, RoleConstants.BUDGET_CREATOR_LABEL);
        addKeyValue(RoleConstants.VIEWER, RoleConstants.VIEWER_LABEL);
    }
}
