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
package org.kuali.kra.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 
 * This class is to create key/values pair for protocolsubmission & meeting lookup. Key and values are all committeeId
 */
public class CommitteeIdValuesFinder extends KeyValuesBase {

    /**
     * @return the list of &lt;key, value&gt; pairs of committees. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {

        Collection<Committee> committees = getCommittees();
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        if (CollectionUtils.isNotEmpty(committees)) {
            List<String> committeeIds = new ArrayList<String>();
            // only the active ones
            Collections.sort((List<Committee>) committees, Collections.reverseOrder());
            for (Committee committee : committees) {
                if (!committeeIds.contains(committee.getCommitteeId())) {
                    keyValues.add(new KeyLabelPair(committee.getCommitteeId(), committee.getCommitteeId()));
                    committeeIds.add(committee.getCommitteeId());
                }
            }
        }

        return keyValues;
    }

    @SuppressWarnings("unchecked")
    private Collection<Committee> getCommittees() {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService.findAll(Committee.class);
    }

}
