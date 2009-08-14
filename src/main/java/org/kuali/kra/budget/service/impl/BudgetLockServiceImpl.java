/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.service.BudgetLockService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * The Budget Lock Service implementation.  It derives from the Pessimistic Lock Service in 
 * order to customize the lock descriptors.
 */
public class BudgetLockServiceImpl extends PessimisticLockServiceImpl implements BudgetLockService {

    private static final String FALSE = "FALSE";
    private static final String ADD_BUDGET = "addBudget";

    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#isLockRequiredByUser(org.kuali.rice.kns.document.Document, java.util.Map, org.kuali.rice.kim.bo.Person)
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

    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#useCustomLockDescriptors()
     */
    @Override
    protected boolean useCustomLockDescriptors() {
        return true;
    }

    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#getCustomLockDescriptor(org.kuali.rice.kns.document.Document, java.util.Map, org.kuali.rice.kim.bo.Person)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected String getCustomLockDescriptor(Document document, Map editMode, Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            BudgetParentDocument parent = ((BudgetDocument) document).getParentDocument();
            if (parent != null) {
                return parent.getDocumentNumber() + "-" + activeLockRegion; 
            }
            return document.getDocumentNumber() + "-" + activeLockRegion;
        }
        
        return null;
    }

    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#isEntryEditMode(java.util.Map.Entry)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isEntryEditMode(Map.Entry entry) {
        if (AuthorizationConstants.EditMode.FULL_ENTRY.equals(entry.getKey())
                || KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET.equals(entry.getKey())
                 || ADD_BUDGET.equals(entry.getKey())
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            return ( (ObjectUtils.isNotNull(fullEntryEditModeValue)) && (EDIT_MODE_DEFAULT_TRUE_VALUE.equals(fullEntryEditModeValue)));
        }
        return false;
    }
    
    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#getEditModeWithEditableModesRemoved(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Map getEditModeWithEditableModesRemoved(Map currentEditMode) {
        Map editModeMap = new HashMap();
        for (Iterator iterator = editModeMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if (StringUtils.equals(entry.getKey(), ADD_BUDGET)) {
                entry.setValue(FALSE);
            }
        }
        return editModeMap;
    }
    
    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#createNewPessimisticLock(org.kuali.rice.kns.document.Document, java.util.Map, org.kuali.rice.kim.bo.Person)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        if (useCustomLockDescriptors()) {
            String lockDescriptor = getCustomLockDescriptor(budgetDocument, editMode, user);
            PessimisticLock budgetLockForProposal = generateNewLock(budgetDocument.getParentDocumentKey(), lockDescriptor, user);
            budgetDocument.getParentDocument().addPessimisticLock(budgetLockForProposal);
            return generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
        } 
        else {
            return generateNewLock(document.getDocumentNumber(), user);
        }  
    }
    
    /**
     * @see org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl#hasPreRouteEditAuthorization(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
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
