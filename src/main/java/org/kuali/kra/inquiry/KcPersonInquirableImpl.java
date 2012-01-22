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
package org.kuali.kra.inquiry;

import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * This class is used to perform an inquiry on a KC Person.
 */
public class KcPersonInquirableImpl extends KualiInquirableImpl {
    
    private KcPersonService kcPersonService;
    
    /** {@inheritDoc} */
    @Override
    public BusinessObject getBusinessObject(@SuppressWarnings("unchecked") Map fieldValues) {
        final String personId = (String) fieldValues.get("personId");
        
        return personId != null ? this.getKcPersonService().getKcPersonByPersonId(personId) : null;
    }
    
    /**
     * Gets the Kc Person Service.
     * <p>
     * Currently the inquiry definition in the DD does not allow you to specify a SpringBean therefore
     * injection cannot be used for the KcPersonService.
     * </p>
     * @return the Kc person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
