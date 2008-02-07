/*
 * Copyright 2008 The Kuali Foundation.
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.RoleConstants;

/**
 * Finds the available set of proposal roles.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalRoleValuesFinder extends KeyValuesBase {
    
    /**
     * The set of proposal roles is static.  
     * 
     * @return the list of key/value pairs of Proposal Roles.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair(RoleConstants.UNASSIGNED, RoleConstants.UNASSIGNED_LABEL));
        keyValues.add(new KeyLabelPair(RoleConstants.AGGREGATOR, RoleConstants.AGGREGATOR_LABEL));
        keyValues.add(new KeyLabelPair(RoleConstants.NARRATIVE_WRITER, RoleConstants.NARRATIVE_WRITER_LABEL));
        keyValues.add(new KeyLabelPair(RoleConstants.BUDGET_CREATOR, RoleConstants.BUDGET_CREATOR_LABEL));
        keyValues.add(new KeyLabelPair(RoleConstants.VIEWER, RoleConstants.VIEWER_LABEL));
        return keyValues;
    }
}
