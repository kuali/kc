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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PropertyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelper;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class IacucProtocolCustomDataHelper extends ProtocolCustomDataHelper { 

    private static final String CUSTOM_ATTRIBUTE_NAME = "IacucProtocolCustomDataAttribute";
    private static final String PROTOCOL_ID_ATTRIBUTE_NAME = "protocolId";
    private static final String CUSTOM_ATTRIBUTE_DOCUMENT_TYPE_NAME = "documentTypeName";
    
    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public IacucProtocolCustomDataHelper(IacucProtocolForm form) {
        super(form);
    }
    
    protected String getCanModifyOthersTaskNameHook() {
        return TaskName.MODIFY_IACUC_PROTOCOL_OTHERS;
    }
//  /**
//  * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
//  */
// @Override
    public boolean canModifyCustomData() {
        ProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_OTHERS, (IacucProtocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected String getDocumentTypeCode() {
        return IacucProtocolDocument.DOCUMENT_TYPE_CODE;
    }

    private SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups;
    
    public Map<String, List<CustomAttributeDocument>> getCustomAttributeGroups() {
        return customAttributeGroups;
    }

    public void setCustomAttributeGroups(SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }
    

    public void prepareView(Protocol protocol) {
        initializePermissions();

        customAttributeGroups = new TreeMap<String, List<CustomAttributeDocument>>();

        Map<String, CustomAttributeDocument> customAttributeDocuments = getCustomAttributeDocuments("ICPR");
        for(Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry:customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(PROTOCOL_ID_ATTRIBUTE_NAME, protocol.getProtocolId().toString());
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());

            IacucProtocolCustomData customAttributeDocValue = (IacucProtocolCustomData) getBusinessObjectService().findByPrimaryKey(IacucProtocolCustomData.class, primaryKeys);
            if (customAttributeDocValue != null) {
                customAttributeDocument.getCustomAttribute().setValue(customAttributeDocValue.getValue());
                getCustomAttributeValues().put("id" + customAttributeDocument.getCustomAttributeId().toString(), new String[]{customAttributeDocValue.getValue()});
            }

            String customAttrGroupName = getValidCustomAttributeGroupName(customAttributeDocument.getCustomAttribute().getGroupName());
            List<CustomAttributeDocument> customAttributeDocumentList = customAttributeGroups.get(customAttrGroupName);

            if (customAttributeDocumentList == null) {
                customAttributeDocumentList = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(customAttrGroupName, customAttributeDocumentList);
            }
            customAttributeDocumentList.add(customAttributeDocument);
        }

        setCustomAttributeGroups(customAttributeGroups);
    }
    
    private Map<String, CustomAttributeDocument> getCustomAttributeDocuments(String documentType) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(CUSTOM_ATTRIBUTE_DOCUMENT_TYPE_NAME, documentType);
        Collection<CustomAttributeDocument> customAttributeDocumentList = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocumentList) {
            if (customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }
        return customAttributeDocuments;
    }
    
    public IacucProtocolCustomData getIacucProtocolCustomData(CustomAttributeDocument customAttributeDocument, IacucProtocol iacucProtocol) {
        IacucProtocolCustomData iacucProtocolCustomData = null;
        
        for (IacucProtocolCustomData iacucProtocolCustomDataListItem : iacucProtocol.getIacucProtocolCustomDataList()) {
            if (customAttributeDocument.getCustomAttributeId().longValue() == iacucProtocolCustomDataListItem.getCustomAttributeId().longValue()) {
                iacucProtocolCustomData = iacucProtocolCustomDataListItem;
                break;
            }
        }
        
        if (iacucProtocolCustomData == null) {
            int customAttributeId = customAttributeDocument.getCustomAttributeId();
            String customAttributeDefaultValue = customAttributeDocument.getCustomAttribute().getDefaultValue();
            String customAttributeValue = customAttributeDocument.getCustomAttribute().getValue();
            
            iacucProtocolCustomData = new IacucProtocolCustomData();
            iacucProtocolCustomData.setCustomAttributeId((long) customAttributeId);
            iacucProtocolCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
            iacucProtocolCustomData.setProtocolId(iacucProtocol.getProtocolId());
            iacucProtocolCustomData.setValue(StringUtils.defaultString(StringUtils.defaultString(customAttributeValue, customAttributeDefaultValue)));
        
            iacucProtocol.getIacucProtocolCustomDataList().add(iacucProtocolCustomData);
        }
        
        return iacucProtocolCustomData;
    }

  public ActionForward getCustomDataAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      prepareView(getProtocol());
      return mapping.findForward(IacucProtocolAction.IACUC_PROTOCOL_CUSTOM_DATA_HOOK);
  }    


}
