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

    protected String navigateTo;
    
    public String getNavigateTo() {
        return navigateTo;
    }

    public void setNavigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
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
        setNavigateTo(getNavigateToPage());
        
        Map editMode = documentAuthorizer.getEditMode(getDocument(), kualiUser);
        String lockRegion = getLockRegion();
        //editMode.put(KraAuthorizationConstants.ACTIVE_LOCK_REGION, lockRegion);
        GlobalVariables.getUserSession().addObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION, (Object)lockRegion);  
        
        if (KNSServiceLocator.getDataDictionaryService().getDataDictionary().getDocumentEntry(getDocument().getClass().getName())
                .getUsePessimisticLocking()) {
            editMode = documentAuthorizer.establishLocks(getDocument(), editMode, kualiUser);
        }

        setEditingMode(editMode);
        DocumentActionFlags temp = documentAuthorizer.getDocumentActionFlags(getDocument(), kualiUser);
        
        setSaveDocumentControl(temp, editMode);
        setDocumentActionFlags(temp);
    }

    private boolean isProposalAction() {
        boolean isProposalAction = false;

        if ((StringUtils.isNotBlank(actionName) && StringUtils.isNotBlank(getMethodToCall())) 
                && actionName.startsWith("Proposal") && !actionName.contains("AbstractsAttachments")
                && !getMethodToCall().equalsIgnoreCase("headerTab")) {
            isProposalAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase("proposal") 
                || navigateTo.equalsIgnoreCase("specialReview") || navigateTo.equalsIgnoreCase("customData") 
                || navigateTo.equalsIgnoreCase("keyPersonnel") || navigateTo.equalsIgnoreCase("permissions") 
                || navigateTo.equalsIgnoreCase("questions") 
                || navigateTo.equalsIgnoreCase("grantsGov") || navigateTo.equalsIgnoreCase("actions"))) {
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

    private boolean isNarrativeAction() {
        boolean isNarrativeAction = false;

//        if (StringUtils.isNotBlank(actionName) && actionName.contains("AbstractsAttachments") && 
//                (StringUtils.equals(getMethodToCall(), "addProposalAttachmentRights")
//                || StringUtils.equals(getMethodToCall(), "addProposalAttachment") 
//                || StringUtils.equals(getMethodToCall(), "downloadProposalAttachment")
//                || StringUtils.equals(getMethodToCall(), "deleteProposalAttachment")
//                || StringUtils.equals(getMethodToCall(), "replaceProposalAttachment")
//                || StringUtils.equals(getMethodToCall(), "addInstituteAttachmentRights")
//                || StringUtils.equals(getMethodToCall(), "addInstitutionalAttachment") 
//                || StringUtils.equals(getMethodToCall(), "downloadInstituteAttachment")
//                || StringUtils.equals(getMethodToCall(), "deleteInstitutionalAttachment")
//                || StringUtils.equals(getMethodToCall(), "replaceInstituteAttachment") 
//                || StringUtils.equals(getMethodToCall(), "addAbstract") 
//                || StringUtils.equals(getMethodToCall(), "deleteAbstract") 
//                || StringUtils.equals(getMethodToCall(), "save")) 
//                ) {
//            isNarrativeAction = true;
//        }
        
        if (StringUtils.isNotBlank(actionName) && StringUtils.isNotBlank(getMethodToCall()) 
                && actionName.contains("AbstractsAttachments") && !getMethodToCall().equalsIgnoreCase("headerTab")) {
            isNarrativeAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && navigateTo.equalsIgnoreCase("abstractsAttachments")) {
            isNarrativeAction = true;
        }

        return isNarrativeAction;

    }

    private boolean isBudgetAction() {
        boolean isBudgetAction = false;

        if (StringUtils.isNotBlank(actionName) && actionName.startsWith("Budget") && StringUtils.isNotBlank(getMethodToCall()) && !getMethodToCall().equalsIgnoreCase("headerTab")) {
            isBudgetAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase("budgetVersions") 
                || navigateTo.equalsIgnoreCase("summary") || navigateTo.equalsIgnoreCase("personnel") 
                || navigateTo.equalsIgnoreCase("expenses") || navigateTo.equalsIgnoreCase("rates") 
                || navigateTo.equalsIgnoreCase("distributionAndIncome") || navigateTo.equalsIgnoreCase("modularBudget") 
                || navigateTo.equalsIgnoreCase("totals") || navigateTo.equalsIgnoreCase("proposalHierarchy")  
                || navigateTo.equalsIgnoreCase("budgetActions"))) {
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
        String lockRegion = ""; 
        
        if (isProposalAction()) {
            lockRegion = "PROPOSAL";
        }
        else if (isNarrativeAction()) {
            //lockRegion = "NARRATIVES";
            lockRegion = null;
        }
        else if (isBudgetAction()) {
            lockRegion = "BUDGET";
        }
        
        return lockRegion;
    }
    
    private void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, Map editMode) {
        String navigateToPage = getNavigateToPage(); 

        tempDocumentActionFlags.setCanSave(false);   

        if (isProposalAction() && hasModifyProposalPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
        else if (isNarrativeAction() && hasModifyNarrativesPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
        else if (isBudgetAction() && hasModifyBudgetPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
    }


}
