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
package org.kuali.kra.irb.actions;

import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class should be extended by IRB  values finder classes.  It creates a single function to get a 
 * BusinessObjectService, so each class need not do that it self.
 */
public abstract class IrbActionsKeyValuesBase extends KeyValuesBase {
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    private CommitteeService committeeService;
    private KeyValuesService keyValuesService;
    
    /**
     * This method returns an instance of BusinessObjectServe from the KraServiceLocator.
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }
    
    /**
     * 
     * This method returns an instance of KcPersonService.
     * @return KcPersonService
     */
    public KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
    
    /**
     * 
     * This method returns an instance of RolodexService.
     * @return RolodexService
     */
    public RolodexService getRolodexService() {
        if (this.rolodexService == null) {
            this.rolodexService = KraServiceLocator.getService(RolodexService.class);
        }
        return this.rolodexService;
    }
    
    /**
     * 
     * This method returns an instance of CommitteeService.
     * @return CommitteeService
     */
    public CommitteeService getCommitteeService() {
        if (this.committeeService == null) {
            this.committeeService = KraServiceLocator.getService(CommitteeService.class);
        }
        return this.committeeService;
    }
    
    /**
     * 
     * This method returns an instance of CommitteeService.
     * @return KeyValuesService
     */
    public KeyValuesService getKeyValuesService() {
        if (this.keyValuesService == null) {
            this.keyValuesService = KraServiceLocator.getService(KeyValuesService.class);
        }
        return this.keyValuesService;
    }
}
