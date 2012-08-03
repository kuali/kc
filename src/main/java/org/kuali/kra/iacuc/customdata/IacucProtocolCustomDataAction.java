/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.customdata;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.drools.core.util.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.customdata.CustomDataHelper;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelper;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class...
 */
public class IacucProtocolCustomDataAction extends IacucProtocolAction {

    public String getCustomAttributeNameHook() {
        return "IacucCustomDataAttribute";
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm)form;
        if (!"reload".equals(protocolForm.getMethodToCall()) && !"performLookup".equals(protocolForm.getMethodToCall())) {
            copyCustomDataToDocument(form);
        }
        protocolForm.getCustomDataHelper().initializePermissions();
        return forward;    
    }

    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ProtocolForm protocolForm = (ProtocolForm) form;
        super.reload(mapping, form, request, response);
        protocolForm.getCustomDataHelper().prepareView(protocolForm.getProtocolDocument().getProtocol());
        
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        
        for (Map.Entry<String, String[]> customAttributeValue : protocolForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            String value = customAttributeValue.getValue()[0];
            protocolDocument.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
        }
        return mapping.findForward("iacucCustomData");    
    }
    
    /**
     * Copy the custom data to the document so that it can saved in
     * the ResearchDocumentBase class.
     * @param form
     */
    private void copyCustomDataToDocument(ActionForm form) {
        IacucProtocolForm iacucProtocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) iacucProtocolForm.getDocument();
        IacucProtocol protocol = iacucProtocolForm.getIacucProtocolDocument().getIacucProtocol();

        for (Map.Entry<String, String[]>customAttributeValue: iacucProtocolForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            int customAttributeIdInt = Integer.valueOf(customAttributeId).intValue(); 
            String value = customAttributeValue.getValue()[0];
            boolean found = false;
            Iterator<IacucProtocolCustomData> iter = protocol.getIacucProtocolCustomDataList().iterator();
            for (;iter.hasNext() && !found;) {
                IacucProtocolCustomData dataItem = iter.next();
                if (customAttributeId.equals(dataItem.getCustomAttributeId().toString())) {
                    dataItem.setValue((value==null) ? "" : value);
                    getBusinessObjectService().save(dataItem);
                    found = true;
                }
            }
            if (!found && !StringUtils.isEmpty(value)) {
                IacucProtocolCustomData newCustomData = new IacucProtocolCustomData();
                newCustomData.setCustomAttribute(new CustomAttribute());
                newCustomData.getCustomAttribute().setId(customAttributeIdInt);
                newCustomData.setCustomAttributeId((long) customAttributeIdInt);
                newCustomData.setProtocolId(protocol.getProtocolId());
                protocol.getIacucProtocolCustomDataList().add(newCustomData);
            }
            CustomAttributeDocument dataDoc = document.getCustomAttributeDocuments().get(customAttributeId); 
            if (dataDoc != null) {
                dataDoc.getCustomAttribute().setValue(value);
            }
        }
    }
    
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
        IacucProtocolCustomDataHelper customDataHelper = (IacucProtocolCustomDataHelper) protocolForm.getCustomDataHelper();
        IacucProtocol iacucProtocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        List <IacucProtocolCustomData> iacucProtocolList= iacucProtocol.getIacucProtocolCustomDataList();
        Map<String, CustomAttributeDocument> customAttributeDocuments = protocolForm.getProtocolDocument().getCustomAttributeDocuments();
        
        String attributeParameter = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String customAttributeId = StringUtils.substringBetween(attributeParameter, ".id", ".");
        if ( StringUtils.isNotBlank(customAttributeId))
        {
            customDataHelper.clearCustomAttributeValue(customAttributeId);
            iacucProtocol.refreshReferenceObject("iacucProtocolCustomDataList");
            if (customAttributeDocuments.containsKey(customAttributeId)) {
                customAttributeDocuments.get(customAttributeId).getCustomAttribute().setValue(null);  
                IacucProtocolCustomData iacucProtocolCustomData1 = null;
                for(IacucProtocolCustomData iacucProtocolCustomData : iacucProtocolList)
                {
                    if(iacucProtocolCustomData.getCustomAttributeId().toString().equals(customAttributeId))
                    {
                        iacucProtocolCustomData1 = iacucProtocolCustomData;
                        break;
                    }
                }
                if ( iacucProtocolCustomData1 != null)
                {
                    iacucProtocolList.remove(iacucProtocolCustomData1);
                }

            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}
