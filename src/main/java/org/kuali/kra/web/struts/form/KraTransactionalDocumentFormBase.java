/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.web.struts.form;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.RiceConstants;
import org.kuali.RiceKeyConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.DocumentAuthorizer;
import org.kuali.core.document.authorization.DocumentAuthorizerBase;
import org.kuali.core.document.authorization.PessimisticLock;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.KNSServiceLocator;

public class KraTransactionalDocumentFormBase extends KualiTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(KraTransactionalDocumentFormBase.class);

    protected String actionName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Refactored out actually calling the documentAuthorizer methods, since TransactionalDocuments call a differently-parameterized
     * version of getEditMode
     * 
     * @param documentAuthorizer
     */
    @Override
    protected void useDocumentAuthorizer(DocumentAuthorizer documentAuthorizer) {
        UniversalUser kualiUser = GlobalVariables.getUserSession().getUniversalUser();
        Map editMode = documentAuthorizer.getEditMode(getDocument(), kualiUser);
        String lockRegion = getLockRegion();
        editMode.put(KraAuthorizationConstants.ACTIVE_LOCK_REGION, lockRegion);
        
//        if(StringUtils.isNotEmpty(lockRegion)) {
//            lockRegion = StringUtils.capitalize(lockRegion.toLowerCase());
//        }
        
        if (KNSServiceLocator.getDataDictionaryService().getDataDictionary().getDocumentEntry(getDocument().getClass().getName())
                .getUsePessimisticLocking()) {
            editMode = documentAuthorizer.establishLocks(getDocument(), editMode, kualiUser);
        }

//        if(editMode.containsKey("lockOwnedBy")) {
//            List<PessimisticLock> locks = getDocument().getPessimisticLocks();
//            String lockOwners = "";
//            
//            for(PessimisticLock lock: locks) {
//                if(StringUtils.isNotEmpty(lockOwners) && !lock.isOwnedByUser(kualiUser)) {
//                    lockOwners += ", ";
//                }
//                if(!lock.isOwnedByUser(kualiUser)) {
//                    lockOwners += lock.getOwnedByPersonUniversalIdentifier();
//                }
//            }
//            
//            GlobalVariables.getErrorMap().putError(RiceConstants.DOCUMENT_ERRORS, KeyConstants.PESSIMISTIC_LOCK_MESSAGE, lockRegion, lockOwners);
//            editMode.remove("lockOwnedBy");
//        }
        
        setEditingMode(editMode);
        DocumentActionFlags temp = documentAuthorizer.getDocumentActionFlags(getDocument(), kualiUser);
        setSaveDocumentControl(temp, editMode);
        setDocumentActionFlags(temp);
    }

    private boolean isProposalAction(String action, String methodToCall, String navigateToPage) {
        boolean isProposalAction = false;

        if (action.startsWith("Proposal") && !action.contains("AbstractsAttachments")
                && !methodToCall.equalsIgnoreCase("headerTab")) {
            isProposalAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateToPage) && (navigateToPage.equalsIgnoreCase("proposal") 
                || navigateToPage.equalsIgnoreCase("specialReview") || navigateToPage.equalsIgnoreCase("customData") 
                || navigateToPage.equalsIgnoreCase("keyPersonnel") || navigateToPage.equalsIgnoreCase("permissions") 
                || navigateToPage.equalsIgnoreCase("questions") 
                || navigateToPage.equalsIgnoreCase("grantsGov") || navigateToPage.equalsIgnoreCase("actions"))) {
            isProposalAction = true;
        }

        return isProposalAction;
    }

    private boolean hasModifyProposalPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.ProposalEditMode.MODIFY_PROPOSAL)) {
            String modifyProposalPermission = (String) editMode.get(KraAuthorizationConstants.ProposalEditMode.MODIFY_PROPOSAL);
            return ((ObjectUtils.isNotNull(modifyProposalPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyProposalPermission)));
        }

        return false;
    }

    private boolean hasModifyBudgetPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET)) {
            String modifyBudgetPermission = (String) editMode.get(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET);
            return ((ObjectUtils.isNotNull(modifyBudgetPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyBudgetPermission)));
        }

        return false;
    }

    private boolean hasModifyNarrativesPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.ProposalEditMode.MODIFY_NARRATIVES)) {
            String modifyNarrativesPermission = (String) editMode.get(KraAuthorizationConstants.ProposalEditMode.MODIFY_NARRATIVES);
            return ((ObjectUtils.isNotNull(modifyNarrativesPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyNarrativesPermission)));
        }

        return false;
    }

    private boolean isNarrativeAction(String action, String methodToCall, String navigateToPage) {
        boolean isNarrativeAction = false;

        if (action.contains("AbstractsAttachments") && 
                (StringUtils.equals(methodToCall, "addProposalAttachmentRights")
                || StringUtils.equals(methodToCall, "addProposalAttachment") 
                || StringUtils.equals(methodToCall, "downloadProposalAttachment")
                || StringUtils.equals(methodToCall, "deleteProposalAttachment")
                || StringUtils.equals(methodToCall, "replaceProposalAttachment")
                || StringUtils.equals(methodToCall, "addInstituteAttachmentRights")
                || StringUtils.equals(methodToCall, "addInstituteAttachment") 
                || StringUtils.equals(methodToCall, "downloadInstituteAttachment")
                || StringUtils.equals(methodToCall, "deleteInstitutionalAttachment")
                || StringUtils.equals(methodToCall, "replaceInstituteAttachment") 
                || StringUtils.equals(methodToCall, "addAbstract") 
                || StringUtils.equals(methodToCall, "deleteAbstract") 
                || StringUtils.equals(methodToCall, "save")) 
                ) {
            isNarrativeAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateToPage) && navigateToPage.equalsIgnoreCase("abstractsAttachments")) {
            isNarrativeAction = true;
        }

        return isNarrativeAction;

    }

    private boolean isBudgetAction(String action, String methodToCall, String navigateToPage) {
        boolean isBudgetAction = false;

        if (action.startsWith("Budget") && !methodToCall.equalsIgnoreCase("headerTab")) {
            isBudgetAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateToPage) && (navigateToPage.equalsIgnoreCase("budgetVersions") 
                || navigateToPage.equalsIgnoreCase("summary") || navigateToPage.equalsIgnoreCase("personnel") 
                || navigateToPage.equalsIgnoreCase("expenses") || navigateToPage.equalsIgnoreCase("rates") 
                || navigateToPage.equalsIgnoreCase("distributionAndIncome") || navigateToPage.equalsIgnoreCase("modularBudget") 
                || navigateToPage.equalsIgnoreCase("totals") || navigateToPage.equalsIgnoreCase("proposalHierarchy")  
                || navigateToPage.equalsIgnoreCase("budgetActions"))) {
            isBudgetAction = true; 
        }

        return isBudgetAction;

    }

    private String getNavigateToPage() {
        String navigateToPage = null;

        for (Object unknownKeyValue : getUnknownKeys()) {
            navigateToPage = (String) unknownKeyValue;
            if (navigateToPage.startsWith("methodToCall.headerTab.headerDispatch.")) {
                return navigateToPage.substring(navigateToPage.indexOf(".navigateTo.")+12, navigateToPage.lastIndexOf(".x"));
            }
        }

        return null;
    }
    
    private String getLockRegion() {
        String navigateToPage = getNavigateToPage(); 
        String lockRegion = ""; 
        
        if (isProposalAction(actionName, getMethodToCall(), navigateToPage)) {
            lockRegion = "PROPOSAL";
        }
        else if (isNarrativeAction(actionName, getMethodToCall(), navigateToPage)) {
            //lockRegion = "NARRATIVES";
            lockRegion = null;
        }
        else if (isBudgetAction(actionName, getMethodToCall(), navigateToPage)) {
            lockRegion = "BUDGET";
        }
        
        return lockRegion;
    }
    
    private void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, Map editMode) {
        String navigateToPage = getNavigateToPage(); 

        tempDocumentActionFlags.setCanSave(false);   

        if (isProposalAction(actionName, getMethodToCall(), navigateToPage) && hasModifyProposalPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
        else if (isNarrativeAction(actionName, getMethodToCall(), navigateToPage) && hasModifyNarrativesPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
        else if (isBudgetAction(actionName, getMethodToCall(), navigateToPage) && hasModifyBudgetPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
    }


}
