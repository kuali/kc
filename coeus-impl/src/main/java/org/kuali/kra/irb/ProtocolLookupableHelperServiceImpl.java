/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDao;
import org.kuali.kra.protocol.ProtocolLookupableHelperServiceImplBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles searching for protocols.
 */
public class ProtocolLookupableHelperServiceImpl extends ProtocolLookupableHelperServiceImplBase<Protocol> {
    
    

    private static final long serialVersionUID = -6170836146164439176L;
    
    private ProtocolDao<Protocol> protocolDao;
    
    private static final String[] AMEND_RENEW_PROTOCOL_TASK_CODES = { TaskName.CREATE_PROTOCOL_AMMENDMENT, 
        TaskName.CREATE_PROTOCOL_RENEWAL };
    private static final String NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION = "lookupActionNotifyIRBProtocol";
    private static final String[] NOTIFY_IRB_PROTOCOL_TASK_CODES = { TaskName.NOTIFY_IRB };
    private static final String[] REQUEST_PROTOCOL_TASK_CODES = { TaskName.PROTOCOL_REQUEST_CLOSE, 
        TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, 
        TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS,
        TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, 
        TaskName.PROTOCOL_REQUEST_SUSPENSION, 
        TaskName.PROTOCOL_REQUEST_TERMINATE };
    private static final String[] PENDING_PROTOCOL_STATUS_CODES = { ProtocolStatus.IN_PROGRESS, 
        ProtocolStatus.SUBMITTED_TO_IRB, 
        ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED, 
        ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED, 
        ProtocolStatus.AMENDMENT_IN_PROGRESS, 
        ProtocolStatus.RENEWAL_IN_PROGRESS, 
        ProtocolStatus.WITHDRAWN };
    private static final String[] PENDING_PI_ACTION_PROTOCOL_STATUS_CODES = { ProtocolStatus.RETURN_TO_PI,
        ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED, 
        ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,  
        ProtocolStatus.EXPIRED };

    @Override
    protected ProtocolTaskBase createNewProtocolTaskInstanceHook(String taskName, ProtocolBase protocol) {
        return new ProtocolTask(taskName, (Protocol) protocol);
    }

    @Override
    protected List<? extends BusinessObject> getSearchResultsFilteredByTask(Map<String, String> fieldValues) {
        List<? extends BusinessObject> searchResults = null;
        
        if (BooleanUtils.toBoolean(fieldValues.get(AMEND_RENEW_PROTOCOL_LOOKUP_ACTION))) {
            searchResults = filterProtocolsByTask(fieldValues, AMEND_RENEW_PROTOCOL_TASK_CODES);
        } else if (BooleanUtils.toBoolean(fieldValues.get(NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION))) {
            searchResults = filterProtocolsByTask(fieldValues, NOTIFY_IRB_PROTOCOL_TASK_CODES);
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
        fieldValues.remove(NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION);
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
        } else if (isParameterTrue(NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION)) {
            htmlDataList.add(getPerformActionLink(businessObject, NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION));
        } else if (isParameterTrue(REQUEST_PROTOCOL_ACTION)) {
            htmlDataList.add(getPerformActionLink(businessObject, REQUEST_PROTOCOL_ACTION));
        } else {
            htmlDataList.addAll(getEditCopyViewLinks(businessObject, pkNames));
        }
        
        return htmlDataList;
    }

    @Override
    protected ProtocolDao<Protocol> getProtocolDaoHook() {
        return this.protocolDao;
    }

    /**
     * 
     * This is spring bean will be used to get search results.
     * 
     * @param protocolDao
     */
    public void setProtocolDao(ProtocolDao<Protocol> protocolDao) {
        this.protocolDao = protocolDao;
    }

    
    @Override
    protected String getDocumentTypeNameHook() {
        return "ProtocolDocument";
    }

    @Override
    protected String getHtmlActionHook() {
        return "protocolProtocol.do";
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolClassHook() {
        return Protocol.class;
    }
}
