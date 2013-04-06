/*
 * Copyright 2005-2013 The Kuali Foundation
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
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class IacucProtocolCustomDataHelper extends ProtocolCustomDataHelperBase<IacucProtocolCustomData> { 

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5964117436714994219L;
    
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
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_OTHERS, (IacucProtocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected String getDocumentTypeCode() {
        return IacucProtocolDocument.DOCUMENT_TYPE_CODE;
    }
    
    @Override
    public void prepareCustomData() {
        this.initializePermissions();
        super.prepareCustomData();
    }

    @Override
    protected IacucProtocolCustomData getNewCustomData() {
        return new IacucProtocolCustomData();
    }
    
    @Override
    public List<IacucProtocolCustomData> getCustomDataList() {
        return ((IacucProtocol)getProtocol()).getIacucProtocolCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return form.getProtocolDocument().getCustomAttributeDocuments();
    }


}
