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
package org.kuali.kra.protocol.actions;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class should be extended by protocol values finder classes.  It creates a single function to get a 
 * BusinessObjectService, so each class need not do that it self.
 */
public abstract class ProtocolActionsKeyValuesBase extends FormViewAwareUifKeyValuesFinderBase {

    private static final long serialVersionUID = 7919893695536280256L;
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private RolodexService rolodexService;
    private CommitteeServiceBase committeeService;
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
