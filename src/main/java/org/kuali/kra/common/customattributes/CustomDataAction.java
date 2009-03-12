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
package org.kuali.kra.common.customattributes;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.util.KNSPropertyConstants;

/**
 * Contains a set of methods that are invoked from Struts Action methods
 * in support of Custom Data.
 */
public class CustomDataAction {
    
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
    public static ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (form instanceof CustomDataForm) {
            SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
        
            CustomDataForm customDataForm = (CustomDataForm) form;
            ResearchDocumentBase doc = (ResearchDocumentBase) customDataForm.getDocument();
        
            Map<String, CustomAttributeDocument> customAttributeDocuments = doc.getCustomAttributeDocuments();
            String documentNumber = doc.getDocumentNumber();
            for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put(KNSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
                primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());
        
                CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
                if (customAttributeDocValue != null) {
                    customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
                    customDataForm.getCustomDataHelper().getCustomAttributeValues().put("id" + customAttributeDocument.getCustomAttributeId().toString(), new String[]{customAttributeDocValue.getValue()});
                }
        
                String groupName = customAttributeDocument.getCustomAttribute().getGroupName();
                List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(groupName);
        
                if (customAttributeDocumentList == null) {
                    customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                    customAttributeGroups.put(groupName, customAttributeDocumentList);
                }
                customAttributeDocumentList.add(customAttributeDocument);
            }
        
            customDataForm.getCustomDataHelper().setCustomAttributeGroups(customAttributeGroups);
        }
        return mapping.findForward("customData");
    }
    
    /**
     * Copy the custom data to the document so that it can saved in
     * the ResearchDocumentBase class.
     * @param form
     */
    public static void copyCustomDataToDocument(ActionForm form) {
        CustomDataForm customDataForm = (CustomDataForm) form;
        ResearchDocumentBase document = (ResearchDocumentBase) customDataForm.getDocument();

        for (Map.Entry<String, String[]>customAttributeValue: customDataForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
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
        getService(CustomAttributeService.class).setCustomAttributeKeyValue(doc, attributeName, form.getWorkflowDocument().getInitiatorNetworkId());
    }
}
