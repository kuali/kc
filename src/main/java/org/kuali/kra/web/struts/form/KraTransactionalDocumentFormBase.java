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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.DocumentAuthorizer;
import org.kuali.core.document.authorization.DocumentAuthorizerBase;
import org.kuali.core.document.authorization.PessimisticLock;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
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
     * Override reset to reset checkboxes if they are present on the requesting page
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (request.getParameter("checkboxToReset") != null) {
            String[] checkboxesToReset = request.getParameterValues("checkboxToReset");
            if(checkboxesToReset != null && checkboxesToReset.length > 0) {
                for (int i = 0; i < checkboxesToReset.length; i++) {
                    String propertyName = (String) checkboxesToReset[i];
                    try {
                        PropertyUtils.setNestedProperty(this, propertyName, false);
                    } catch (Exception e1) {
                        LOG.error("Error occurred in reset " + e1.getMessage());
                        throw new RuntimeException(e1.getMessage(), e1);
                    }
                }
            }
        }
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
        GlobalVariables.getUserSession().addObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION, (Object)lockRegion);  
        
        if (KNSServiceLocator.getDataDictionaryService().getDataDictionary().getDocumentEntry(getDocument().getClass().getName())
                .getUsePessimisticLocking()) {
            editMode = documentAuthorizer.establishLocks(getDocument(), editMode, kualiUser);
        } 

        setEditingMode(editMode);
        DocumentActionFlags temp = documentAuthorizer.getDocumentActionFlags(getDocument(), kualiUser);
        
        /* 
         * The setSaveDocumentControl() was commented out because the document authorizer
         * is responsible for setting the "canSave" document flag.  Also, the code used by
         * the setSaveDocumentControl() looks really bizarre.  We should not be comparing
         * against classnames, method names, etc. for our logic.  This code needs to be
         * refactored in the future.
         */
        //setSaveDocumentControl(temp, editMode);
        setDocumentActionFlags(temp);
        
        boolean activeLockRegionChangedInd = hasActiveLockRegionChanged(getDocument(), lockRegion);
        GlobalVariables.getUserSession().addObject(KraAuthorizationConstants.LOCK_REGION_CHANGE_IND, activeLockRegionChangedInd);
    }

    private boolean hasActiveLockRegionChanged(Document document, String activeLockRegion) {
        if(StringUtils.isNotEmpty(activeLockRegion)) {
            for(PessimisticLock lock: document.getPessimisticLocks()) {
                if(!lock.getLockDescriptor().contains(activeLockRegion)) {
                    return true;
                }
            } 
        } else if (document.getPessimisticLocks().size()>0) {
            return true;
        }
        
        return false;
    }
    
    private boolean isProposalAction() {
        boolean isProposalAction = false;

        if ((StringUtils.isNotBlank(actionName) && StringUtils.isNotBlank(getMethodToCall())) 
                && actionName.startsWith("Proposal") && !actionName.contains("AbstractsAttachments")
                && !actionName.contains("BudgetVersions") && !getMethodToCall().equalsIgnoreCase("headerTab")) {
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

    private boolean hasModifyCompletedBudgetPermission(Map editMode) {
        if (editMode != null && editMode.containsKey("modifyCompletedBudgets")) {
            String modifyBudgetPermission = (String) editMode.get("modifyCompletedBudgets");
            editMode.remove("modifyCompletedBudgets");
            return ((ObjectUtils.isNotNull(modifyBudgetPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyBudgetPermission)));
        }

        return false;
    }
    
    private boolean hasModifyNarrativesPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.ProposalEditMode.ADD_NARRATIVES)) {
            String modifyNarrativesPermission = (String) editMode.get(KraAuthorizationConstants.ProposalEditMode.ADD_NARRATIVES);
            return ((ObjectUtils.isNotNull(modifyNarrativesPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyNarrativesPermission)));
        }

        return false;
    }

    private boolean isNarrativeAction() {
        boolean isNarrativeAction = false;

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

        if (StringUtils.isNotBlank(actionName) && (actionName.startsWith("Budget") || actionName.contains("BudgetVersions")) 
                && StringUtils.isNotBlank(getMethodToCall()) && !getMethodToCall().equalsIgnoreCase("headerTab")) {
            isBudgetAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase("versions") 
                || navigateTo.equalsIgnoreCase("summary") || navigateTo.equalsIgnoreCase("personnel") 
                || navigateTo.equalsIgnoreCase("expenses") || navigateTo.equalsIgnoreCase("rates") 
                || navigateTo.equalsIgnoreCase("distributionAndIncome") || navigateTo.equalsIgnoreCase("modularBudget") 
                || navigateTo.equalsIgnoreCase("totals") || navigateTo.equalsIgnoreCase("proposalHierarchy")  
                || navigateTo.equalsIgnoreCase("budgetActions") || navigateTo.equalsIgnoreCase("budgetVersions"))) {
            isBudgetAction = true; 
        }

        return isBudgetAction;

    }

    private boolean isBudgetVersionsAction() {
        boolean isBudgetVersionsAction = false;

        if (StringUtils.isNotBlank(actionName) && actionName.contains("BudgetVersions")  
                && StringUtils.isNotBlank(getMethodToCall()) && !getMethodToCall().equalsIgnoreCase("headerTab")) {
            isBudgetVersionsAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase("versions") 
                || navigateTo.equalsIgnoreCase("budgetVersions"))) {
            isBudgetVersionsAction = true; 
        }

        return isBudgetVersionsAction;
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
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
        }
        else if (isNarrativeAction()) {
            lockRegion = null;
        }
        else if (isBudgetAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET;
        }
        
        return lockRegion;
    }
    
    private void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, Map editMode) {
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
        else if (isBudgetVersionsAction() && hasModifyCompletedBudgetPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
    }


}
