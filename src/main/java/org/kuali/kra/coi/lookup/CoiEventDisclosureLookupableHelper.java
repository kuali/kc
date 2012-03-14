/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.krad.bo.BusinessObject;

public class CoiEventDisclosureLookupableHelper extends CoiDisclosureLookupableHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5284451557882132240L;

    /*
     * Returns only those project event disclosures, manual and non-manual, that have been submitted / routed
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) super.getResults(fieldValues);
        List<CoiDisclosure> submittedEventDisclosures = new ArrayList<CoiDisclosure>();
        for (CoiDisclosure coiDisclosure : allDisclosures) {
            if (coiDisclosure.isSubmitted() && (coiDisclosure.isProjectEventDisclosure(coiDisclosure) || coiDisclosure.isManualEvent())) {
                submittedEventDisclosures.add(coiDisclosure);
            }
        }
        return submittedEventDisclosures;
    }
    
    

}
