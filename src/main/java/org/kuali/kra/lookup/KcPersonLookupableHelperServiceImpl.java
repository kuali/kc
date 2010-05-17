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
package org.kuali.kra.lookup;

import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;

/**
 * Lookup helper that retrieves KcPerson BOs.
 */
public class KcPersonLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private static final long serialVersionUID = 1L;

    private KcPersonService kcPersonService;

    /** {@inheritDoc} */
    @Override
    public List<KcPerson> getSearchResults(Map<String, String> fieldValues) { 
        return this.kcPersonService.getKcPersons(fieldValues);
    }

    /**
     * Sets the Kc Person Service.
     * @param kcPersonService the Kc person Service.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
