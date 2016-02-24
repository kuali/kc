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
public class CoiNonProjectEventDisclosureLookupableHelper extends CoiDisclosureLookupableHelperBase {


    private static final long serialVersionUID = -8145799265576572663L;

    @Override
    public List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) super.getResults(fieldValues);
        List<CoiDisclosure> nonProjectEventDisclosures = new ArrayList<CoiDisclosure>();
        //exclude annual, update, manual award, ip and protocol and award, ip and protocol event based disclosures
        for (CoiDisclosure disclosure : allDisclosures) {
            if (!(disclosure.isAnnualEvent() || disclosure.isUpdateEvent() || disclosure.isNonManualProjectEvent()
                   || isManualProjectEvent(disclosure)) && disclosure.isSubmitted()) {
                nonProjectEventDisclosures.add(disclosure);
            }
        }
        return nonProjectEventDisclosures;
    }

    protected boolean isManualProjectEvent(CoiDisclosure disclosure) {
        if (StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_AWARD, disclosure.getEventTypeCode()) ||
           StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, disclosure.getEventTypeCode()) ||
           StringUtils.equalsIgnoreCase(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, disclosure.getEventTypeCode())) {
            return true;
        }
        return false;
    }
}
