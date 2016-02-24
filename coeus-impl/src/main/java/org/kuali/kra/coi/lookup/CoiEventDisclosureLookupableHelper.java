/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
