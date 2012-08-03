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
package org.kuali.kra.protocol.customdata;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolAction;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADPropertyConstants;

/**
 * Struts Action class for the Custom Data tab.
 */
public abstract class ProtocolCustomDataAction extends ProtocolAction {

    private static BusinessObjectService businessObjectService;

// Demoted to IACUC    
//    /**
//     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        /*
//         * Primarily, the customdata.tag is using 'customdatahelper.xxx' as field name, 
//         * but the field value is coming from document,  so this copy has to be done 
//         * every time custom data page is refreshed. 
//         */
//        CustomDataAction.copyCustomDataToDocument(form);
//
//        ((ProtocolForm)form).getCustomDataHelper().initializePermissions();
//        
//        return super.execute(mapping, form, request, response);
//    }
//    
//    /**
//     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    public ActionForward reload(ActionMapping mapping, ActionForm form, 
//            HttpServletRequest request, HttpServletResponse response) throws Exception { 
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        super.reload(mapping, form, request, response);
//        CustomDataAction.copyCustomDataToDocument(form);
//        ((ProtocolForm)form).getCustomDataHelper().prepareView(((ProtocolForm)form).getProtocolDocument());
//        
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//        
//        for (Map.Entry<String, String[]> customAttributeValue : protocolForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
//            String customAttributeId = customAttributeValue.getKey().substring(2);
//            String value = customAttributeValue.getValue()[0];
//            protocolDocument.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
//        }
//        return mapping.findForward("customData");    
//    }

    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        CustomDataAction.setCustomAttributeContent(form, getCustomAttributeNameHook());
    }
    
    /*
     * hooks for getting the correct custom attribute name and mapping strings
     */
    protected abstract String getCustomAttributeNameHook();
    protected abstract String getCustomAttributeMappingHook();
    protected abstract String getCustomDataForwardNameHook();

    /**
     * Clears the lookup value for the customAttributeId given in the parameter methodToCall.clearLookupValue.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearLookupValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolCustomDataHelper protocolCustomDataHelper = protocolForm.getCustomDataHelper();
        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolForm.getProtocolDocument().getCustomAttributeDocuments();
        String customAttributeId = request.getParameter("methodToCall.clearLookupValue");
        
        protocolCustomDataHelper.clearCustomAttributeValue(customAttributeId);
        if (customAttributeDocuments.containsKey(customAttributeId)) {
            customAttributeDocuments.get(customAttributeId).getCustomAttribute().setValue(null);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * Invoked when the "Custom Data" tab is clicked on.  In other words, the
     * end-user is navigating to the "Custom Data" tab.  The custom attribute
     * values for the document are read from the database and placed into the
     * form's helper instance.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
//    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//        return staticCustomData(mapping, form, request, response, getCustomAttributeMappingHook());
//    }
//    
//    public static ActionForward staticCustomData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String mappingDestination) {
//        SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
//
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ResearchDocumentBase doc = (ResearchDocumentBase) protocolForm.getDocument();
//
//        Map<String, CustomAttributeDocument> customAttributeDocuments = doc.getCustomAttributeDocuments();
//        String documentNumber = doc.getDocumentNumber();
//        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
//            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
//            CustomAttributeDocValue customAttributeDocValue = 
//                getCustomAttributeDocValue(documentNumber, customAttributeDocument.getCustomAttributeId());
//            if (customAttributeDocValue != null) {
//                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
//                protocolForm.getProtocolCustomDataHelper().getCustomAttributeValues()
//                .put("id" + customAttributeDocument.getCustomAttributeId().toString(), 
//                        new String[]{customAttributeDocValue.getValue()});
//            }
//
//            String groupName = protocolForm.getProtocolCustomDataHelper().getValidCustomAttributeGroupName(customAttributeDocument.getCustomAttribute().getGroupName());
//            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
//
//            if (customAttributeDocumentList == null) {
//                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
//                customAttributeGroups.put(groupName, customAttributeDocumentList);
//            }
//            customAttributeDocumentList.add(customAttributeDocument);
//        }
//
//        protocolForm.getProtocolCustomDataHelper().setCustomAttributeGroups(customAttributeGroups);
//        return mapping.findForward(mappingDestination);
//    }
//    
//    /**
//     * Get the Custom Attribute Doc value.
//     * @param documentNumber
//     * @param customAttributeId
//     * @return
//     */
//    private static CustomAttributeDocValue getCustomAttributeDocValue(String documentNumber, Integer customAttributeId) {
//        Map<String, Object> primaryKeys = new HashMap<String, Object>();
//        primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, documentNumber);
//        primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeId);
//        return (CustomAttributeDocValue) getStaticBusinessObjectService().findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
//    }
    
    /**
     * Get the Business Object Service.
     * @return
     */
    protected static BusinessObjectService getStaticBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    /**
     * Copy the custom data to the document so that it can saved in
     * the ResearchDocumentBase class.
     * @param form
     */
    public void copyCustomDataToDocument(ActionForm form) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ResearchDocumentBase document = (ResearchDocumentBase) protocolForm.getDocument();

        for (Map.Entry<String, String[]>customAttributeValue: protocolForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            String value = customAttributeValue.getValue()[0];
            document.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
        }
    }
    
    /**
     * Set the custom attribute content in workflow.
     * @param form
     * @throws Exception
     */
    public static void setCustomAttributeContent(KualiDocumentFormBase form, String attributeName) throws Exception {
        ResearchDocumentBase doc = (ResearchDocumentBase) form.getDocument();
        getService(CustomAttributeService.class).setCustomAttributeKeyValue(doc, attributeName, form.getWorkflowDocument().getInitiatorPrincipalId());
    }

    // following hooks are not needed for custom data panel
    protected String getProtocolForwardNameHook() { return null; }
    protected String getQuestionnaireForwardNameHook() { return null; }
    protected String getPersonnelForwardNameHook() { return null; }
    protected String getNoteAndAttachmentForwardNameHook() { return null; }
    protected String getProtocolActionsForwardNameHook() { return null; }
    protected String getProtocolOnlineReviewForwardNameHook() { return null; }
    protected String getProtocolPermissionsForwardNameHook() { return null; }

}
