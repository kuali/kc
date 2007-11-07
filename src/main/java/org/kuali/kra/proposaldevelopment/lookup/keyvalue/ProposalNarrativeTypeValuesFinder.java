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

import static org.kuali.kra.infrastructure.Constants.PROPOSAL_NARRATIVE_TYPE_GROUP;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
/**
 * Finds the available set of supported Narrative Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */

public class ProposalNarrativeTypeValuesFinder extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)getService("keyValueFinderService");
    /**
     * Constructs the list of Proposal Narrative Types. The list populates
     * from NARRATIVE_TYPE database table via the "KeyValueFinderService".
     * Proposal narrative types are filtered out by looking at the System Parameter Value
     * for PROPOSAL_NARRATIVE_TYPE_GROUP.
     * 
     * @return the list of &lt;key, value&gt; pairs of Proposal Narrative types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValueFinderService#getKeyValues(Class,String,String,String)
     * @see org.kuali.kra.infrastructure.Constants.PROPOSAL_NARRATIVE_TYPE_GROUP 
     */
    public List<KeyLabelPair> getKeyValues() {
        String proposalNarrativeTypeGroup = getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, PROPOSAL_NARRATIVE_TYPE_GROUP);
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
        queryMap.put("systemGenerated", "N");
        return keyValueFinderService.getKeyValues(NarrativeType.class, "narrativeTypeCode", "description",queryMap);
    }
}
