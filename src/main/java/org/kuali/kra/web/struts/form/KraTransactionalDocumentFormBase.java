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

import static org.kuali.kra.logging.BufferedLogger.debug;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.QuestionableFormInterface;
import org.kuali.kra.rules.SoftError;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
  
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
    private Map<String, Boolean> personEditableFields;
   
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

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        
        // Hack to get panels with add/delete items that are editable after add (Protocol Participants, Special Review) to work correctly with validation.  
        // In this scenario, the user adds a couple of correctly formatted items but then changes one of the fields to an incorrect format and saves.  This will
        // cause validation errors, but if the user now tries to delete the errant entry, validation will fail because the validator in the Kuali Request 
        // ProcessDefinitionDefinitionor still detects the error in the message map.  We don't want validation to run for a delete method, so we need to clear the current error 
        // messages, preventing the validator from running and allowing the delete to go through.
        //
        // This is detected by the existence of "validate0" on the methodToCall property (similar to finding the line number of a delete).
        String methodToCallAttribute = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.contains(methodToCallAttribute, "validate")) {
            String validateParameter = StringUtils.substringBetween(methodToCallAttribute, ".validate", ".");
            if (StringUtils.equals("0", validateParameter)) {
                GlobalVariables.getMessageMap().clearErrorMessages();
            }
        }
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
        return this.getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_SAVE) ? "save" : "reload";
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
    
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }

    public Map<String, Boolean> getPersonEditableFields() {
        if (personEditableFields == null) {
            populatePersonEditableFields();
        }
        return personEditableFields;
    }

    public void setPersonEditableFields(Map<String, Boolean> personEditableFields) {
        this.personEditableFields = personEditableFields;
    }
    
    /**
     * Creates the list of <code>{@link PersonEditableField}</code> field names.
     */
    public void populatePersonEditableFields() {
        // TODO : should refactor this editablefields related to parent class, so it can be shared by other 
        // modules
        debug("Adding PersonEditableFields");

        setPersonEditableFields(new HashMap<String, Boolean>());

        @SuppressWarnings("unchecked")
     //   Collection<PersonEditableField> fields = getBusinessObjectService().findAll(PersonEditableField.class);
        Map fieldValues = new HashMap();
        fieldValues.put("moduleCode", getModuleCode());
        Collection<PersonEditableField> fields = getBusinessObjectService().findMatching(PersonEditableField.class, fieldValues);
        for (PersonEditableField field : fields) {
            debug("found field " + field.getFieldName());
                getPersonEditableFields().put(field.getFieldName(), Boolean.valueOf(field.isActive()));
        }
        // if "All" is implemented
//        fieldValues.clear();
//        fieldValues.put("moduleCode", "0");
//        fields = getBusinessObjectService().findMatching(PersonEditableField.class, fieldValues);
//        for (PersonEditableField field : fields) {
//            debug("found field " + field.getFieldName());
//            if (!getPersonEditableFields().containsKey(field.getFieldName())) {
//                getPersonEditableFields().put(field.getFieldName(), Boolean.valueOf(field.isActive()));
//            }
//        }

    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * 
     * This method should be overriden by modules that is using person editable field.
     * @return
     */
    public String getModuleCode() {
        return "0";
    }
    
    /**
     * This is a duplication of KualiTransactionalDocumentFormBase.populateFalseCheckboxes with the cavet that this function
     * puts a NULL in for fields that contain "answer", which are the field names of radio Y/N buttons for the questionnaire framework.
     * @see org.kuali.rice.kns.web.struts.form.KualiTransactionalDocumentFormBase#populateFalseCheckboxes(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected void populateFalseCheckboxes(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        final String checkBoxToResetFieldParam = "checkboxToReset";
        if (parameterMap.get(checkBoxToResetFieldParam) != null) {
            final String[] checkboxesToResetFields = request.getParameterValues("checkboxToReset");
            if (checkboxesToResetFields != null && checkboxesToResetFields.length > 0) {
                for (int i = 0; i < checkboxesToResetFields.length; i++) {
                    String propertyName = (String) checkboxesToResetFields[i];
                    if (!StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) == null) {
                        if (this instanceof QuestionableFormInterface 
                                && StringUtils.startsWithIgnoreCase(propertyName, ((QuestionableFormInterface) this).getQuestionnaireFieldStarter())
                                && StringUtils.containsIgnoreCase(propertyName, ((QuestionableFormInterface) this).getQuestionnaireFieldMiddle())
                                && StringUtils.endsWithIgnoreCase(propertyName, ((QuestionableFormInterface) this).getQuestionnaireFieldEnd())) {
                            populateForProperty(propertyName, null, parameterMap);
                        } else {
                            populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_FALSE_STR_VALUE_DISPLAY, parameterMap);
                        }
                    } else if (!StringUtils.isBlank(propertyName) && parameterMap.get(propertyName) != null 
                            && parameterMap.get(propertyName).length >= 1 
                            && parameterMap.get(propertyName)[0].equalsIgnoreCase("on")) {
                        populateForProperty(propertyName, KimConstants.KIM_ATTRIBUTE_BOOLEAN_TRUE_STR_VALUE_DISPLAY, parameterMap);
                    }
                }
            }
        }
    }
}
