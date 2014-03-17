/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolLookupableHelperServiceImplBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IacucProtocolLookupableHelperServiceImpl extends ProtocolLookupableHelperServiceImplBase<IacucProtocol> {


    private static final long serialVersionUID = -4930225152545760432L;
    private static final String[] AMEND_RENEW_PROTOCOL_TASK_CODES = { TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT, 
        TaskName.CREATE_PROTOCOL_RENEWAL };

    private static final String[] REQUEST_PROTOCOL_TASK_CODES = { TaskName.IACUC_PROTOCOL_REQUEST_CLOSE, 
        TaskName.IACUC_PROTOCOL_REQUEST_CLOSE, 
        TaskName.IACUC_PROTOCOL_REQUEST_SUSPENSION, 
        TaskName.IACUC_PROTOCOL_REQUEST_TERMINATE };

    private static final String[] PENDING_PROTOCOL_STATUS_CODES = { IacucProtocolStatus.IN_PROGRESS, 
        IacucProtocolStatus.SUBMITTED_TO_IACUC, 
        IacucProtocolStatus.MINOR_REVISIONS_REQUIRED, 
        IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED, 
        IacucProtocolStatus.WITHDRAWN };

    private static final String[] PENDING_PI_ACTION_PROTOCOL_STATUS_CODES = { IacucProtocolStatus.MINOR_REVISIONS_REQUIRED, 
        IacucProtocolStatus.MAJOR_REVISIONS_REQUIRED,  
        IacucProtocolStatus.EXPIRED };


    @Override
    protected List<? extends BusinessObject> getSearchResultsFilteredByTask(Map<String, String> fieldValues) {
        List<? extends BusinessObject> searchResults = null;
        if (BooleanUtils.toBoolean(fieldValues.get(AMEND_RENEW_PROTOCOL_LOOKUP_ACTION))) {
            searchResults = filterProtocolsByTask(fieldValues, AMEND_RENEW_PROTOCOL_TASK_CODES);
        } else if (BooleanUtils.toBoolean(fieldValues.get(REQUEST_PROTOCOL_ACTION))) {
            searchResults = filterProtocolsByTask(fieldValues, REQUEST_PROTOCOL_TASK_CODES);
        } else if (BooleanUtils.toBoolean(fieldValues.get(PENDING_PROTOCOL_LOOKUP))) {
            searchResults = filterProtocolsByStatus(fieldValues, PENDING_PROTOCOL_STATUS_CODES);
        } else if (BooleanUtils.toBoolean(fieldValues.get(PENDING_PI_ACTION_PROTOCOL_LOOKUP))) {
            searchResults = filterProtocolsByStatus(fieldValues, PENDING_PI_ACTION_PROTOCOL_STATUS_CODES);
        } else if (StringUtils.isNotBlank(fieldValues.get(PROTOCOL_PERSON_ID_LOOKUP))) {
            searchResults = filterProtocolsByPrincipal(fieldValues, PROTOCOL_PERSON_ID_LOOKUP);
        } else {
            searchResults = filterProtocols(fieldValues);
        }
        return searchResults;
    }


    @Override
    protected Map<String, String> removeExtraFilterParameters(Map<String, String> fieldValues) {
        fieldValues.remove(AMEND_RENEW_PROTOCOL_LOOKUP_ACTION);
        fieldValues.remove(REQUEST_PROTOCOL_ACTION);
        fieldValues.remove(PENDING_PROTOCOL_LOOKUP);
        fieldValues.remove(PENDING_PI_ACTION_PROTOCOL_LOOKUP);
        fieldValues.remove(PROTOCOL_PERSON_ID_LOOKUP);
        return fieldValues;
    }


    @Override
    protected List<HtmlData> getCustomActions(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (isParameterTrue(AMEND_RENEW_PROTOCOL_LOOKUP_ACTION)) {
            htmlDataList.add(getPerformActionLink(businessObject, AMEND_RENEW_PROTOCOL_LOOKUP_ACTION));
        } else if (isParameterTrue(REQUEST_PROTOCOL_ACTION)) {
            htmlDataList.add(getPerformActionLink(businessObject, REQUEST_PROTOCOL_ACTION));
        } else {
            htmlDataList.addAll(getEditCopyViewLinks(businessObject, pkNames));
        }
        return htmlDataList;
    }

    protected List<HtmlData> getEditCopyViewLinks(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (ProtocolBase) businessObject, PermissionConstants.MODIFY_IACUC_PROTOCOL)) {
            // Change "edit" to edit same document, NOT initializing a new Doc
            AnchorHtmlData editHtmlData = getViewLink(((ProtocolBase) businessObject).getProtocolDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
            
            AnchorHtmlData copyHtmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            IacucProtocolDocument document = ((IacucProtocol) businessObject).getIacucProtocolDocument();
            copyHtmlData.setHref("../iacucProtocolActions.do?docId=" + document.getDocumentNumber() +"&doCopy=True");
            htmlDataList.add(copyHtmlData);
        }
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (IacucProtocol) businessObject, PermissionConstants.VIEW_IACUC_PROTOCOL)) {
            htmlDataList.add(getViewLink(((ProtocolBase) businessObject).getProtocolDocument()));
        }
        return htmlDataList;
    }



    @Override
    protected IacucProtocolDao getProtocolDaoHook() {
        return getIacucProtocolDao();
    }

    protected IacucProtocolDao getIacucProtocolDao() {
        return KcServiceLocator.getService(IacucProtocolDao.class);
    }


    @Override
    protected String getDocumentTypeNameHook() {
        return "IacucProtocolDocument";
    }


    @Override
    protected String getHtmlActionHook() {
        return "iacucProtocolProtocol.do";
    }


    @Override
    protected ProtocolTaskBase createNewProtocolTaskInstanceHook(String taskName, ProtocolBase protocol) {
        return new IacucProtocolTask(taskName, (IacucProtocol) protocol);
    }


    @Override
    protected Class<? extends ProtocolBase> getProtocolClassHook() {
        return IacucProtocol.class;
    }

}
