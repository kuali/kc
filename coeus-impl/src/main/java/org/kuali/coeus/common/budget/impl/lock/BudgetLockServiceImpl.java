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
package org.kuali.coeus.common.budget.impl.lock;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.lock.BudgetLockService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * The Budget Lock Service implementation.  It derives from the Pessimistic Lock Service in 
 * order to customize the lock descriptors.
 */
@Component("budgetLockService")
public class BudgetLockServiceImpl extends PessimisticLockServiceImpl implements BudgetLockService {

    private static final String ADD_BUDGET = "addBudget";

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("dataObjectService")
    @Override
    public void setDataObjectService(DataObjectService dataObjectService) {
        super.setDataObjectService(dataObjectService);
    }

    @Autowired
    @Qualifier("dataDictionaryService")
    @Override
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        super.setDataDictionaryService(dataDictionaryService);
    }


    @SuppressWarnings("unchecked")
    @Override
    protected boolean isLockRequiredByUser(Document document, Map editMode, Person user) {
        String activeLockRegion = (String) globalVariableService.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        
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
        return super.establishLocks(document, editMode, user); 
    }

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
    
    @SuppressWarnings("unchecked")
    @Override
    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, Person user) {
        if (document instanceof BudgetDocument) {
            BudgetDocument budgetDocument = (BudgetDocument) document;
            if (document.useCustomLockDescriptors()) {
                String lockDescriptor = document.getCustomLockDescriptor(user);
                //establish any locks needed on the parent document
                this.establishLocks(budgetDocument.getBudget().getBudgetParent().getDocument(), editMode, user); 
                
                return generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
            } 
            else {
                return generateNewLock(document.getDocumentNumber(), user);
            }
        } else {
            return super.createNewPessimisticLock(document, editMode, user);
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPreRouteEditAuthorization(Document document, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
         
        for (Iterator iterator = budgetDocument.getBudget().getBudgetParent().getDocument().getPessimisticLocks().iterator(); iterator.hasNext();) {
            PessimisticLock lock = (PessimisticLock) iterator.next();
            if (lock.getLockDescriptor().endsWith(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET) && lock.isOwnedByUser(user)) {
                return true;
            }
        }
        
        return false;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
