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
package org.kuali.kra.coi.maintenance;

import org.kuali.rice.kns.lookup.KualiLookupableImpl;


public class CoiDisclosureEventTypeLookupableImpl extends KualiLookupableImpl {
    
    private static final String MAINTENANCE = "maintenance.do";
    private static final String NEW_MAINTENANCE = "../maintenanceCoiDisclosureEvent.do";
    
    /**
     * new url goes to the custom coi disclosure event type maintenance action 
     * @see org.kuali.rice.kns.lookup.KualiLookupableImpl#getCreateNewUrl()
     */
    @Override
    public String getCreateNewUrl() {
        String originalURL = super.getCreateNewUrl();
        return originalURL.replace(MAINTENANCE, NEW_MAINTENANCE);    
    }
    
}
