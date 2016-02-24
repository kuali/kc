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

import org.apache.log4j.Logger;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Rice's PessimisticLockServiceImpl captures OLE exceptions for OJB.  OJB has been replaced with JPA but the OLE
 *  exception handling logic doesn't work for JPA.  This service override fixes it for JPA.
 *
 *  The bigger question is why are OLE exceptions happening in the first place and why is rice expecting these OLEs.
 */
@Transactional
public class KcRicePessimisticLockServiceImpl extends PessimisticLockServiceImpl {

    private static final Logger LOG = Logger.getLogger(KcRicePessimisticLockServiceImpl.class);

    @Override
    public void releaseAllLocksForUser(List<PessimisticLock> locks, Person user) {
        for (PessimisticLock lock : locks) {
            if (lock.isOwnedByUser(user)) {
                try {
                    delete(lock);
                } catch (JpaOptimisticLockingFailureException ex) {
                    LOG.warn("Suppressing Optimistic Lock Exception. Document Num: " +  lock.getDocumentNumber());
                }
            }
        }
    }

    @Override
    public void releaseAllLocksForUser(List<PessimisticLock> locks, Person user, String lockDescriptor) {
        for (PessimisticLock lock : locks) {
            if (lock.isOwnedByUser(user) && lockDescriptor.equals(lock.getLockDescriptor())) {
                try {
                    delete(lock);
                } catch (JpaOptimisticLockingFailureException ex) {
                    LOG.warn("Suppressing Optimistic Lock Exception. Document Num: " +  lock.getDocumentNumber());
                }
            }
        }
    }

    private void delete(PessimisticLock lock) {
        if ( LOG.isDebugEnabled() ) {
            LOG.debug("Deleting lock: " + lock);
        }
        dataObjectService.delete(lock);
    }
}
