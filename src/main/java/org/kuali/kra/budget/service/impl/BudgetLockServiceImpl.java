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
package org.kuali.kra.budget.service.impl;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetLockService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * The Budget Lock Service implementation.  It derives from the Pessimistic Lock Service in 
 * order to customize the lock descriptors.
 */
public class BudgetLockServiceImpl extends PessimisticLockServiceImpl implements BudgetLockService {

    private static final String FALSE = "FALSE";
    private static final String ADD_BUDGET = "addBudget";

    /**
     * @see org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl#isLockRequiredByUser(org.kuali.rice.krad.document.Document, java.util.Map, org.kuali.rice.kim.api.identity.Person)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isLockRequiredByUser(Document document, Map editMode, Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        
        // check for entry edit mode
        for (Iterator iterator = editMode.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (isEntryEditMode(entry) && StringUtils.isNotEmpty(activeLockRegion)) {
                return true;  
            }
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Map establishLocks(Document document, Map editMode, Person user) {
        //check for lock in parent document of the budget section and if it is locked,
        //then assume this is also locked
        if (document instanceof BudgetDocument) {
            BudgetDocument budgetDoc = (BudgetDocument)document;
            //unsure if this should be for anything but proposal development
            if (budgetDoc.getParentDocument() instanceof ProposalDevelopmentDocument) { 
                for (PessimisticLock lock : budgetDoc.getParentDocument().getPessimisticLocks()) {
                    if (!lock.isOwnedByUser(user) && StringUtils.equals(budgetDoc.getCustomLockDescriptor(user), lock.getLockDescriptor())) {
                        return getEditModeWithEditableModesRemoved(editMode);
                    }
                }
            } 
        }
        return super.establishLocks(document, editMode, user); 
    }

    /**
     * @see org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl#isEntryEditMode(java.util.Map.Entry)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isEntryEditMode(Map.Entry entry) {
        if (AuthorizationConstants.EditMode.FULL_ENTRY.equals(entry.getKey())
                || KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET.equals(entry.getKey())
                 || ADD_BUDGET.equals(entry.getKey())
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            return (StringUtils.equalsIgnoreCase(KRADConstants.KUALI_DEFAULT_TRUE_VALUE, fullEntryEditModeValue));
        }
        return false;
    }
    
    /**
     * @see org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl#createNewPessimisticLock(org.kuali.rice.krad.document.Document, java.util.Map, org.kuali.rice.kim.api.identity.Person)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, Person user) {
        if (document instanceof BudgetDocument) {
            BudgetDocument budgetDocument = (BudgetDocument) document;
            if (document.useCustomLockDescriptors()) {
                String lockDescriptor = document.getCustomLockDescriptor(user);
                //establish any locks needed on the parent document
                this.establishLocks(budgetDocument.getParentDocument(), editMode, user); 
                
                return generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
            } 
            else {
                return generateNewLock(document.getDocumentNumber(), user);
            }
        } else {
            return super.createNewPessimisticLock(document, editMode, user);
        }
    }
    
    /**
     * @see org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl#hasPreRouteEditAuthorization(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPreRouteEditAuthorization(Document document, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
         
        for (Iterator iterator = budgetDocument.getParentDocument().getPessimisticLocks().iterator(); iterator.hasNext();) {
            PessimisticLock lock = (PessimisticLock) iterator.next();
            if (lock.getLockDescriptor().endsWith(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET) && lock.isOwnedByUser(user)) {
                return true;
            }
        }
        
        return false;
    }
}
