/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.protocol.actions;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.krad.migration.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class should be extended by protocol values finder classes.  It creates a single function to get a 
 * BusinessObjectService, so each class need not do that it self.
 */
public abstract class ProtocolActionsKeyValuesBase extends FormViewAwareUifKeyValuesFinderBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7919893695536280256L;
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    private CommitteeServiceBase committeeService;
    private KeyValuesService keyValuesService;
    
    /**
     * This method returns an instance of BusinessObjectServe from the KraServiceLocator.
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
    public CommitteeServiceBase getCommitteeService() {
        if (this.committeeService == null) {
            this.committeeService = KcServiceLocator.getService(getCommitteeServiceClassHook());
        }
        return this.committeeService;
    }
    
    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();
    
    
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
