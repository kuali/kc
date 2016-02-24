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
package org.kuali.coeus.sys.impl.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * KC Pessimistic Lock Service Implementation.
 */
@Component("kcPessimisticLockService")
public class KcPessimisticLockServiceImpl implements KcPessimisticLockService {

    private static final Log LOG = LogFactory.getLog(KcPessimisticLockServiceImpl.class);

    /**
     * Convert minutes to milliseconds.
     */
    private static final long MINUTES_TO_MILLISECONDS = 60L * 1000L;
    
    /**
     * Default expiration age in minutes.
     */
    private static final int DEFAULT_EXPIRATION_AGE = 24 * 60; // one day in minutes

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;


    /**
     * Retrieve all of the locks from the database.  Delete those that
     * have been around for longer than the expiration age.
     * @see org.kuali.coeus.sys.impl.lock.KcPessimisticLockService#clearExpiredLocks()
     */
    @Transactional
    public void clearExpiredLocks() {
        long now = getCurrentTime();
        long expirationAgeMillis = getExpirationAgeMillis();
        final Collection<PessimisticLock> locks = getAllLocks();
        for (PessimisticLock lock : locks) {
            long lockTime = lock.getGeneratedTimestamp().getTime();
            if (now - lockTime >= expirationAgeMillis) {
                dataObjectService.delete(lock);
            }
        }
    }


    @Override
    public boolean isPessimisticLockNeeded(ProposalDevelopmentDocument document, Person user, boolean canEdit, String customLockDescriptor) {
        List<String> userOwnedLockDescriptors = new ArrayList<String>();
        Map<String, Set<String>> otherOwnedLockDescriptors = new HashMap<String,Set<String>>();

        // create the two lock containers that help determine whether to add a pessimistic lock
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(user)) {
                userOwnedLockDescriptors.add(pessimisticLock.getLockDescriptor());
            } else {
                if (!otherOwnedLockDescriptors.containsKey(pessimisticLock.getLockDescriptor())) {
                    otherOwnedLockDescriptors.put(pessimisticLock.getLockDescriptor(), new HashSet<String>());
                }

                String otherOwnerPrincipalId = pessimisticLock.getOwnedByUser().getPrincipalId();
                otherOwnedLockDescriptors.get(pessimisticLock.getLockDescriptor()).add(otherOwnerPrincipalId);
            }
        }

        // if no one has a lock on this document, then the document can be locked if the user can edit it
        if (userOwnedLockDescriptors.isEmpty() && otherOwnedLockDescriptors.isEmpty()) {
            return canEdit;
        }

        boolean userOwnsCustomLockDescriptor = userOwnedLockDescriptors.contains(customLockDescriptor);
        boolean otherOwnsCustomLockDescriptor = otherOwnedLockDescriptors.containsKey(customLockDescriptor);

        if (!userOwnsCustomLockDescriptor && !otherOwnsCustomLockDescriptor) {
            return canEdit;
        }

        return false;
    }
    
    /**
     * Get all of the Pessimistic Locks from the database.
     * @return all of the pessimistic locks
     */
    protected Collection<PessimisticLock> getAllLocks() {
        return dataObjectService.findMatching(PessimisticLock.class, QueryByCriteria.Builder.create().build()).getResults();
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
        final String timeoutStr =
                this.parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SYSTEM, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PESSIMISTIC_LOCKING_EXPIRATION_AGE);

        if (timeoutStr != null) {
            return Integer.parseInt(timeoutStr);
        }

        LOG.warn("Parameter: " + Constants.PESSIMISTIC_LOCKING_EXPIRATION_AGE + " not found. Using Default value: " + DEFAULT_EXPIRATION_AGE);
        return DEFAULT_EXPIRATION_AGE;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }
}
