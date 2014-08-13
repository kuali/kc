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
package org.kuali.coeus.propdev.impl.lock;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.impl.PessimisticLockServiceImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component("proposalLockService")
public class ProposalLockServiceImpl extends PessimisticLockServiceImpl implements ProposalLockService {

    private static final String FALSE = "FALSE";
    private static final String ADD_BUDGET = "addBudget";

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("dataDictionaryService")
    @Override
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        super.setDataDictionaryService(dataDictionaryService);
    }

    @Autowired
    @Qualifier("dataObjectService")
    @Override
    public void setDataObjectService(DataObjectService dataObjectService) {
        super.setDataObjectService(dataObjectService);
    }

    /**
     * This method is used to check if the given parameters warrant a new lock to be created for the given user. This method
     * utilizes the {@link #isEntryEditMode(java.util.Map.Entry)} method.
     * 
     * @param document -
     *            document to verify lock creation against
     * @param editMode -
     *            edit modes list to check for 'entry type' edit modes
     * @param user -
     *            user the lock will be 'owned' by
     * @return true if the given edit mode map has at least one 'entry type' edit mode... false otherwise
     */
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

    /**
     * This method is used to check if the given {@link Map.Entry} is an 'entry type' edit mode and that the value is set to
     * signify that this user has that edit mode available to them
     * 
     * @param entry -
     *            the {@link Map.Entry} object that contains an edit mode such as the ones returned but
     * 
     * @return true if the given entry has a key signifying an 'entry type' edit mode and the value is equal to
     *         {@link #EDIT_MODE_DEFAULT_TRUE_VALUE}... false if not
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean isEntryEditMode(Map.Entry entry) {
        if (AuthorizationConstants.EditMode.FULL_ENTRY.equals(entry.getKey())
                || KraAuthorizationConstants.ProposalEditMode.ADD_NARRATIVES.equals(entry.getKey())
                || KraAuthorizationConstants.ProposalEditMode.MODIFY_PERMISSIONS.equals(entry.getKey())
                || KraAuthorizationConstants.ProposalEditMode.MODIFY_PROPOSAL.equals(entry.getKey())
                || KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET.equals(entry.getKey())
                || ADD_BUDGET.equals(entry.getKey()) 
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            //return ( (ObjectUtils.isNotNull(fullEntryEditModeValue)) && ("TRUE".equals(fullEntryEditModeValue)) );
            return ((ObjectUtils.isNotNull(fullEntryEditModeValue)) && StringUtils.equalsIgnoreCase(KRADConstants.KUALI_DEFAULT_TRUE_VALUE, fullEntryEditModeValue));
        }
        return false;
    }
    
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
    
    @SuppressWarnings("unchecked")
    @Override
    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, Person user) {
        if (document.useCustomLockDescriptors()) {
            String lockDescriptor = document.getCustomLockDescriptor(user);
            ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) document;
            if(StringUtils.isNotEmpty(lockDescriptor) && lockDescriptor.contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                for(BudgetDocumentVersion budgetOverview: pdDocument.getBudgetDocumentVersions()) {
                    generateNewLock(budgetOverview.getDocumentNumber(), lockDescriptor, user);
                }  
            }
            return generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
        } else {
            return generateNewLock(document.getDocumentNumber(), user);
        }
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
