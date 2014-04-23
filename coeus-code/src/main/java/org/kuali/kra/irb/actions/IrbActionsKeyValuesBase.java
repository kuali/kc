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
package org.kuali.kra.irb.actions;

import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class should be extended by IRB  values finder classes.  It creates a single function to get a 
 * BusinessObjectService, so each class need not do that it self.
 */
public abstract class IrbActionsKeyValuesBase extends FormViewAwareUifKeyValuesFinderBase {
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    private CommitteeService committeeService;
    private KeyValuesService keyValuesService;
    
    /**
     * This method returns an instance of BusinessObjectServe from the KcServiceLocator.
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        if (this.businessObjectService == null) {
            this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
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
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
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
            this.rolodexService = KcServiceLocator.getService(RolodexService.class);
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
            this.committeeService = KcServiceLocator.getService(CommitteeService.class);
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
            this.keyValuesService = KcServiceLocator.getService(KeyValuesService.class);
        }
        return this.keyValuesService;
    }
}
