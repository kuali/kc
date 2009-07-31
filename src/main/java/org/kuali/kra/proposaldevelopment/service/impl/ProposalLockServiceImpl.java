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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalLockService;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.exception.PessimisticLockingException;
import org.kuali.rice.kns.service.impl.PessimisticLockServiceImpl;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

public class ProposalLockServiceImpl extends PessimisticLockServiceImpl implements ProposalLockService {

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
     * This method should be used to define Document Authorizer classes which will use custom lock descriptors in the
     * {@link PessimisticLock} objects NOTE: if this method is overriden to return true then the method
     * {@link #getCustomLockDescriptor(Document, Map, UniversalUser)} must be overriden
     * 
     * @return false if the document will not be using lock descriptors or true if the document will use lock descriptors.
     *         The default return value is false
     */
    @Override
    protected boolean useCustomLockDescriptors() {
        return true;
    }

    /**
     * This method should be overriden by groups requiring the lock descriptor field in the {@link PessimisticLock} objects.
     * If it is not overriden and {@link #useCustomLockDescriptors()} returns true then this method will throw a
     * {@link PessimisticLockingException}
     * 
     * @param document - document being checked for locking
     * @param editMode - editMode generated for passed in user
     * @param user - user attempting to establish locks
     * @return a {@link PessimisticLockingException} will be thrown as the default implementation
     */
    @SuppressWarnings("unchecked")
    @Override
    protected String getCustomLockDescriptor(Document document, Map editMode, Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if(StringUtils.isNotEmpty(activeLockRegion))
            return document.getDocumentNumber()+"-"+activeLockRegion; 
        
        return null;
    }

    /**
     * This method is used to check if the given {@link Map.Entry} is an 'entry type' edit mode and that the value is set to
     * signify that this user has that edit mode available to them
     * 
     * @param entry -
     *            the {@link Map.Entry} object that contains an edit mode such as the ones returned but
     *            {@link #getEditMode(Document, UniversalUser)}
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
                || "addBudget".equals(entry.getKey()) 
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            return ( (ObjectUtils.isNotNull(fullEntryEditModeValue)) && (EDIT_MODE_DEFAULT_TRUE_VALUE.equals(fullEntryEditModeValue)) );
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected Map getEditModeWithEditableModesRemoved(Map currentEditMode) {
        Map editModeMap = new HashMap();
        //Map editModeMap = super.getEditModeWithEditableModesRemoved(currentEditMode);
        for (Iterator iterator = editModeMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if (StringUtils.equals(entry.getKey(), "addBudget")) {
                entry.setValue("FALSE");
            }
        }
        return editModeMap;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, Person user) {
        if (useCustomLockDescriptors()) {
            String lockDescriptor = getCustomLockDescriptor(document, editMode, user);
            ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument) document;
            if(StringUtils.isNotEmpty(lockDescriptor) && lockDescriptor.contains(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                for(BudgetVersionOverview budgetOverview: pdDocument.getDevelopmentProposal().getBudgetVersionOverviews()) {
                    generateNewLock(budgetOverview.getDocumentNumber(), lockDescriptor, user);
                }  
            }
            return generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
        } else {
            return generateNewLock(document.getDocumentNumber(), user);
        }
    }
}
