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

import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
/**
 * Finds the available set of supported Narrative Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */

public class NarrativeTypeValuesFinder extends KeyValuesBase {
    KeyValueFinderService keyValueFinderService= (KeyValueFinderService)KraServiceLocator.getService("keyValueFinderService");
    /**
     * Constructs the list of Proposal Narrative Types. The list populates
     * from NARRATIVE_TYPE database table via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of Narrative types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinderService#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        return keyValueFinderService.getKeyValues(NarrativeType.class, "narrativeTypeCode", "description");
    }
}
