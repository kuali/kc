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
package org.kuali.kra.coi.auth;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class is the parent authorizer for coidisclosure tasks
 */
public abstract class CoiDisclosureAuthorizer  extends TaskAuthorizerImpl {
    
    private KraAuthorizationService kraAuthorizationService;
    private CoiDisclosureService coiDisclosureService;

    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String userId, Task task) {
        return isAuthorized(userId, (CoiDisclosureTask) task);
    }

    /**
     * Is the user authorized to execute the given CoiDisclosure task
     * @param userId the user's unique userId
     * @param task the coiDisclosure task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String userId, CoiDisclosureTask task);
    
    /**
     * Set the Kra Authorization Service.  Usually injected by the Spring Framework.
     * @param kraAuthorizationService
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this CoiDisclosure?
     * @param userId the unique userId of the user
     * @param coiDisclosure the coiDisclosure
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String userId, CoiDisclosure coiDisclosure, String permissionName) {
        return kraAuthorizationService.hasPermission(userId, coiDisclosure, permissionName);
    }
    
    protected boolean isPessimisticLocked(Document document) {
        boolean isLocked = false;
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            // if lock is owned by current user, do not display message for it
            if (!lock.isOwnedByUser(GlobalVariables.getUserSession().getPerson())) {
                isLocked = true;
            }
        }
        return isLocked;
    }

    protected CoiDisclosureService getCoiDisclosureService() {
        return coiDisclosureService;
    }

    public void setCoiDisclosureService(CoiDisclosureService coiDisclosureService) {
        this.coiDisclosureService = coiDisclosureService;
    }

}
