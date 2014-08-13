/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CoiEventDisclosureLookupableHelper extends CoiDisclosureLookupableHelperBase {


    private static final long serialVersionUID = 5284451557882132240L;

    /*
     * Returns only those project event disclosures, manual and non-manual, that have been submitted / routed
     */
    @Override
    public List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) super.getResults(fieldValues);
        List<CoiDisclosure> submittedEventDisclosures = new ArrayList<CoiDisclosure>();
        for (CoiDisclosure coiDisclosure : allDisclosures) {
            if (coiDisclosure.isSubmitted() && (coiDisclosure.isNonManualProjectEvent() || isManualProjectEvent(coiDisclosure))) {
                submittedEventDisclosures.add(coiDisclosure);
            }
        }
        return submittedEventDisclosures;
    }
    
    protected boolean isManualProjectEvent(CoiDisclosure disclosure) {
        if (StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_AWARD, disclosure.getEventTypeCode()) ||
           StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, disclosure.getEventTypeCode()) ||
           StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, disclosure.getEventTypeCode()) ||
           StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_IACUC_PROTOCOL, disclosure.getEventTypeCode())) {
            return true;
        }
        return false;
    }

}
