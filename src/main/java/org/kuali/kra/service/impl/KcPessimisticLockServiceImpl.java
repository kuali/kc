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
package org.kuali.kra.service.impl;

import java.util.Collection;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcPessimisticLockService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * KC Pessimistic Lock Service Implementation.
 */
public class KcPessimisticLockServiceImpl implements KcPessimisticLockService {
    
    /**
     * Convert minutes to milliseconds.
     */
    private static final long MINUTES_TO_MILLISECONDS = 60L * 1000L;
    
    /**
     * Default expiration age in minutes.
     */
    private static final int DEFAULT_EXPIRATION_AGE = 24 * 60; // one day in minutes
    
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * Set the Date Time Service.  Injected by Spring.
     * @param dateTimeService the date time service
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
    /**
     * Set the Business Object Service.  Injected by Spring.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Retrieve all of the locks from the database.  Delete those that
     * have been around for longer than the expiration age.
     * @see org.kuali.kra.service.KcPessimisticLockService#clearExpiredLocks()
     */
    @Transactional
    public void clearExpiredLocks() {
        long now = getCurrentTime();
        long expirationAgeMillis = getExpirationAgeMillis();
        Collection<PessimisticLock> locks = getAllLocks();
        for (PessimisticLock lock : locks) {
            long lockTime = lock.getGeneratedTimestamp().getTime();
            if (now - lockTime >= expirationAgeMillis) {
                businessObjectService.delete(lock);
            }
        }
    }
    
    /**
     * Get all of the Pessimistic Locks from the database.
     * @return all of the pessimistic locks
     */
    @SuppressWarnings("unchecked")
    protected Collection<PessimisticLock> getAllLocks() {
        return businessObjectService.findAll(PessimisticLock.class);
    }
    
    /**
     * Get the current time in milliseconds.
     * @return the current time in milliseconds
     */
    protected long getCurrentTime() {
        return dateTimeService.getCurrentTimestamp().getTime();
    }
    
    /**
     * Get the timeout period in milliseconds.
     * @return the timeout period in milliseconds
     */
    protected long getExpirationAgeMillis() {
        return getLockExpirationAge() * MINUTES_TO_MILLISECONDS;
    }
    
    /**
     * Get the Lock Expiration Age parameter value from the system parameters.
     * @return the Lock Expiration Age value in minutes
     */
    protected int getLockExpirationAge() {
        try {
            String timeoutStr = getParameterValue(Constants.PESSIMISTIC_LOCKING_EXPIRATION_AGE);
            return Integer.parseInt(timeoutStr);
        } catch (Exception ex) {
            return DEFAULT_EXPIRATION_AGE;
        }
    }

    /**
     * Get a proposal development system parameter value.
     * @param key the key (name) of the parameter
     * @return the parameter's value
     */
    protected String getParameterValue(String key) {
        return this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, key);
    }
}
