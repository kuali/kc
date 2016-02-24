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
package org.kuali.kra.coi.maintenance;

import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.List;

public class CoiDisclosureEventTypeLookupableHelper extends KualiLookupableHelperServiceImpl {
    
    private static final String MAINTENANCE = "maintenance.do";
    private static final String NEW_MAINTENANCE = "../maintenanceCoiDisclosureEvent.do";
    
    
    /**
     * new url goes to the custom coi disclosure event type maintenance action 
     */
    protected String getActionUrlHref(BusinessObject businessObject, String methodToCall, List pkNames) {
        String originalURL = super.getActionUrlHref(businessObject, methodToCall, pkNames);
        return originalURL.replace(MAINTENANCE, NEW_MAINTENANCE);
    }

}
