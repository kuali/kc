/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.struts.form;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.SoftError;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.form.KualiTransactionalDocumentFormBase;
  
/**
 * This class isbase class for KC Transactional Documents ...
 */
public abstract class KraTransactionalDocumentFormBase extends KualiTransactionalDocumentFormBase {

    private static final long serialVersionUID = 1161569719154606103L;

    protected String actionName;
    protected String navigateTo;
    
    private boolean viewOnly;
    private boolean popupViewOnly;
    
    private boolean medusaOpenedDoc;
    
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
    public String getNavigateTo() {
        return navigateTo;
    }

    public void setNavigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }
    
    /**
     * Consume SoftErrors (if any) and return the collection
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Collection<SoftError>> getSoftErrors() {
        return (Map<String, Collection<SoftError>>) GlobalVariables.getUserSession().retrieveObject(KeyConstants.SOFT_ERRORS_KEY);
    }
    
    public void setupLockRegions() {
        String lockRegion = getLockRegion();
        GlobalVariables.getUserSession().addObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION, (Object)lockRegion);  
        boolean activeLockRegionChangedInd = hasActiveLockRegionChanged(getDocument(), lockRegion);
        GlobalVariables.getUserSession().addObject(KraAuthorizationConstants.LOCK_REGION_CHANGE_IND, activeLockRegionChangedInd);
    }

    private boolean hasActiveLockRegionChanged(Document document, String activeLockRegion) {
        if(StringUtils.isNotEmpty(activeLockRegion)) {
            for(PessimisticLock lock: document.getPessimisticLocks()) {
                if(lock.getLockDescriptor()!=null && !lock.getLockDescriptor().contains(activeLockRegion)
                        && lock.getOwnedByUser().getPrincipalId().equals(GlobalVariables.getUserSession().getPrincipalId())) {
                    return true;
                }
            } 
        } else if (document.getPessimisticLocks().size()>0) {
            return true;
        }
        
        return false;
    }

    protected boolean hasModifyProposalPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.ProposalEditMode.MODIFY_PROPOSAL)) {
            String modifyProposalPermission = (String) editMode.get(KraAuthorizationConstants.ProposalEditMode.MODIFY_PROPOSAL);
            return ((ObjectUtils.isNotNull(modifyProposalPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyProposalPermission)));
        }

        return false;
    }

    protected boolean hasModifyBudgetPermission(Map editMode) {
        String modifyBudgetPermission = "";
        boolean hasModifyBudgetPermission = false;
        
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET)) {
            modifyBudgetPermission = (String) editMode.get(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET);
            hasModifyBudgetPermission = ((ObjectUtils.isNotNull(modifyBudgetPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyBudgetPermission)));
        }

        //Included the new addBudget permission as well - For the New AuthZ Framework
        if (!hasModifyBudgetPermission && editMode != null && editMode.containsKey("addBudget")) {
            modifyBudgetPermission = (String) editMode.get("addBudget");
            hasModifyBudgetPermission = ((ObjectUtils.isNotNull(modifyBudgetPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyBudgetPermission)));
        }
        
        return hasModifyBudgetPermission;
    }

    protected boolean hasModifyCompletedBudgetPermission(Map editMode) {
        if (editMode != null && editMode.containsKey("modifyCompletedBudgets")) {
            String modifyBudgetPermission = (String) editMode.get("modifyCompletedBudgets");
            editMode.remove("modifyCompletedBudgets");
            return ((ObjectUtils.isNotNull(modifyBudgetPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyBudgetPermission)));
        }

        return false;
    }
    
    protected boolean hasModifyNarrativesPermission(Map editMode) {
        if (editMode != null && editMode.containsKey(KraAuthorizationConstants.ProposalEditMode.ADD_NARRATIVES)) {
            String modifyNarrativesPermission = (String) editMode.get(KraAuthorizationConstants.ProposalEditMode.ADD_NARRATIVES);
            return ((ObjectUtils.isNotNull(modifyNarrativesPermission)) && (DocumentAuthorizerBase.EDIT_MODE_DEFAULT_TRUE_VALUE
                    .equals(modifyNarrativesPermission)));
        }

        return false;
    }
    
    /**
     * Get the Header Dispatch.  This determines the action that will occur
     * when the user switches tabs for a document.  If the user can modify
     * the document, the document is automatically saved.  If not (view-only),
     * then a reload will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActions().containsKey(KNSConstants.KUALI_ACTION_CAN_SAVE) ? "save" : "reload";
    }
    
    protected abstract String getLockRegion();
    
    protected abstract void setSaveDocumentControl(Map editMode);
    
    public final boolean isViewOnly() {
        return viewOnly;
    }
    
    public final void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }
    
    public final boolean isPopupViewOnly() {
        return popupViewOnly;
    }
    
    public final void setPopupViewOnly(boolean popupViewOnly) {
        this.popupViewOnly = popupViewOnly;
    }
    
    @Override
    public void setDocument(Document document) {
        super.setDocument(document);
        ((ResearchDocumentBase)document).setViewOnly(isViewOnly());
    }
    
    /** {@inheritDoc} */
    @Override
    protected abstract String getDefaultDocumentTypeName();


    public boolean isMedusaOpenedDoc() {
        return medusaOpenedDoc;
    }

    public void setMedusaOpenedDoc(boolean medusaOpenedDoc) {
        this.medusaOpenedDoc = medusaOpenedDoc;
    }
}
