/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Finds the available set of supported Abstract Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class CommitteeValuesFinderBase extends KeyValuesBase {
    
    /**
     * This value finder gets all committees.  To skip the old versioned committees and get only the 
     * latest active version of a committee use the CommitteeIdValuesFinderBase.
     * 
     * @return the list of &lt;key, value&gt; pairs of committees.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
       
        Collection<? extends CommitteeBase> committees = getCommittees();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (CommitteeBase committee : committees) {
            keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeName()));
        }
        return keyValues;
    }

    private Collection<? extends CommitteeBase> getCommittees() {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
// TODO *********commented the code below during IACUC refactoring*********         
//        return businessObjectService.findAll(CommonCommittee.class);
        
        return businessObjectService.findAll(getCommonCommitteeBOClassHook());
    }
    
    protected abstract Class<? extends CommitteeBase> getCommonCommitteeBOClassHook();
}
