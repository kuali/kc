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
package org.kuali.kra.irb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

/**
 * This class handles searching for protocols.
 */
public class ProtocolLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {    

    private static final String AMEND_RENEW_PROTOCOL_LOOKUP_ACTION = "lookupActionAmendRenewProtocol";
    private static final String NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION = "lookupActionNotifyIRBProtocol";
    private static final String REQUEST_PROTOCOL_ACTION = "lookupActionRequestProtocol";
    private static final String PENDING_PROTOCOL_LOOKUP = "lookupPendingProtocol";
    private static final String PENDING_PI_ACTION_PROTOCOL_LOOKUP = "lookupPendingPIActionProtocol";
    private static final String PROTOCOL_PERSON_ID_LOOKUP = "lookupProtocolPersonId";
    
    private static final String[] AMEND_RENEW_PROTOCOL_TASK_CODES = { TaskName.CREATE_PROTOCOL_AMMENDMENT, 
                                                                      TaskName.CREATE_PROTOCOL_RENEWAL };
    
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
    
    private static final String[] PENDING_PI_ACTION_PROTOCOL_STATUS_CODES = { ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED, 
                                                                              ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED,  
                                                                              ProtocolStatus.EXPIRED };
    
    private static final String RESEARCH_AREA_CLASS_PATH = ResearchArea.class.getName();
    private static final String ORGANIZATION_CLASS_PATH = Organization.class.getName();
    
    private static final Log LOG = LogFactory.getLog(ProtocolLookupableHelperServiceImpl.class);

    private DictionaryValidationService dictionaryValidationService;
    private ProtocolDao protocolDao;
    private KcPersonService kcPersonService;
    private KraAuthorizationService kraAuthorizationService;
    private TaskAuthorizationService taskAuthorizationService;
    private DocumentService documentService;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        validateSearchParameters(fieldValues);
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        
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
    
    /**
     * Filters the unbounded list of protocols by the given field values and protocol tasks.
     * @param fieldValues the field values that form a normal protocol search
     * @param taskNames the list of protocol tasks that the user must be able to perform in a protocol in order for it to be in the search results
     * @return a list of all protocols filtered by the given field values and being able to perform tasks in the taskNames
     */
    private List<? extends BusinessObject> filterProtocolsByTask(Map<String, String> fieldValues, String... taskNames) {
        List<Protocol> filteredProtocols = new ArrayList<Protocol>();
        
        for (Protocol protocol : protocolDao.getProtocols(filterFieldValues(fieldValues))) {
            for (String taskName : taskNames) {
                ProtocolTask task = new ProtocolTask(taskName, protocol);
                if (taskAuthorizationService.isAuthorized(getUserIdentifier(), task)) {
                    filteredProtocols.add(protocol);
                    break;
                }
            }
        }
        
        return getPagedResults(filteredProtocols);
    }

    /**
     * Filters the unbounded list of protocols by the given field values and protocol status codes.
     * @param fieldValues the field values that form a normal protocol search
     * @param protocolStatusCodes the list of protocol status codes that should be included in the search result
     * @return a list of all protocols filtered by the given field values and having statuses in protocolStatusCodes
     */
    private List<? extends BusinessObject> filterProtocolsByStatus(Map<String, String> fieldValues, String... protocolStatusCodes) {
        List<Protocol> filteredProtocols = new ArrayList<Protocol>();

        List<String> protocolStatusCodeList = Arrays.asList(protocolStatusCodes);
        for (Protocol protocol : protocolDao.getProtocols(filterFieldValues(fieldValues))) {
            String statusCode = protocol.getProtocolStatusCode();
            if (protocolStatusCodeList.contains(statusCode)) {
                filteredProtocols.add(protocol);
            }
        }
        
        return getPagedResults(filteredProtocols);
    }
    
    /**
     * Filters the unbounded list of protocols by the given field values and whether the given principalId represents a protocol initiator or PI.
     * @param fieldValues the field values that form a normal protocol search
     * @param personKey the key in the fieldValues that maps to the principalId
     * @return a list of all protocols filtered by the given field values and whether the given principalId represents a protocol initiator or PI
     */
    private List<? extends BusinessObject> filterProtocolsByPrincipal(Map<String, String> fieldValues, String principalKey) {
        List<Protocol> filteredProtocols = new ArrayList<Protocol>();
        
        String principalId = fieldValues.get(principalKey);
        for (Protocol protocol : protocolDao.getProtocols(filterFieldValues(fieldValues))) {
            try {
                String principalInvestigatorId = protocol.getPrincipalInvestigator().getPersonId();
                ProtocolDocument document = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                String initiatorId = document.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId();
                if (StringUtils.equals(principalId, principalInvestigatorId) || StringUtils.equals(principalId, initiatorId)) {
                    filteredProtocols.add(protocol);
                }
            } catch (WorkflowException we) {
                LOG.warn("Cannot find Document with header id " + protocol.getProtocolDocument().getDocumentNumber() + ", removing from search results.");
            }
        }
        
        return getPagedResults(filteredProtocols);
    }
    
    /**
     * Filters the unbounded list of protocols by the given field values
     * @param fieldValues the field values that form a normal protocol search
     * @return a list of all protocols filtered by the given field values
     */
    private List<? extends BusinessObject> filterProtocols(Map<String, String> fieldValues) {
        List<Protocol> protocols = protocolDao.getProtocols(filterFieldValues(fieldValues));
        return getPagedResults(protocols);
    }
    
    /**
     * Filters out extra lookup parameters from the fieldValues.
     * @param fieldValues the field values that form a normal protocol search
     * @return the field values with extra lookup parameters removed
     */
    private Map<String, String> filterFieldValues(Map<String, String> fieldValues) {
        fieldValues.remove(AMEND_RENEW_PROTOCOL_LOOKUP_ACTION);
        fieldValues.remove(NOTIFY_IRB_PROTOCOL_LOOKUP_ACTION);
        fieldValues.remove(REQUEST_PROTOCOL_ACTION);
        fieldValues.remove(PENDING_PROTOCOL_LOOKUP);
        fieldValues.remove(PENDING_PI_ACTION_PROTOCOL_LOOKUP);
        fieldValues.remove(PROTOCOL_PERSON_ID_LOOKUP);
        return fieldValues;
    }
    
    /**
     * Separates the list of protocols into pageable results.
     * @param protocols the list of protocols
     * @return a collection of protocol pageable results.
     */
    private CollectionIncomplete<Protocol> getPagedResults(List<Protocol> protocols) {
        Long matchingResultsCount = new Long(protocols.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Protocol.class);
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete<Protocol>(protocols, new Long(0));
        } else {
            return new CollectionIncomplete<Protocol>(trimResult(protocols, searchResultsLimit), matchingResultsCount);
        }
    }
    
    /**
     * This method trims the search result.
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    protected List<Protocol> trimResult(List<Protocol> result, Integer trimSize) {
        List<Protocol> trimedResult = new ArrayList<Protocol>();
        for (Protocol protocol : result) {
            if (trimedResult.size()< trimSize) {
                trimedResult.add(protocol); 
            }
        }
        return trimedResult;
    }

    @Override
    public void validateSearchParameters(Map fieldValues) {
        super.validateSearchParameters(fieldValues);
        Set<String> keys = fieldValues.keySet();
        for (String key : keys) {
            String value = fieldValues.get(key).toString();
            if (key.toUpperCase().indexOf("DATE") > 0) {
                //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
                if(value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                    boolean valid = validateDate(key, value);
                }
            }
        }
    }
    
    protected boolean validateDate(String dateFieldName, String dateFieldValue) {
        try{
            CoreApiServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
            return true;
        } catch (ParseException e) {
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
            return false;
        }
    }
    
    /**
     * 
     * This method returns an instance of a DictionaryValidationService implementation
     * @return
     */
    public DictionaryValidationService getDictionaryValidationService() {
        if (dictionaryValidationService == null) {
            dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return dictionaryValidationService;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
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
    
    private boolean isParameterTrue(String parameterKey) {
        boolean parameterTrue = false;
        
        if (getParameters() != null && getParameters().containsKey(parameterKey)) {
            Object parameterObject = getParameters().get(parameterKey);
            if (parameterObject != null) {
                if (parameterObject instanceof Boolean) {
                    parameterTrue = (Boolean) parameterObject;
                }
                
                if (parameterObject instanceof String) {
                    parameterTrue = Boolean.parseBoolean(parameterObject.toString());
                }
                
                if (parameterObject instanceof String[]) {
                    String[] parameterObjectStringArray = (String[]) parameterObject;
                    for (String parameterObjectString : parameterObjectStringArray) {
                        if (Boolean.parseBoolean(parameterObjectString)) {
                            parameterTrue = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return parameterTrue;
    }
    
    /**
     * Constructs and returns the perform action links.
     * @param businessObject
     * @param actionKey
     * @return
     */
    private AnchorHtmlData getPerformActionLink(BusinessObject businessObject, String actionKey) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("perform action");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "performProtocolAction");
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", "false");
        parameters.put("docId", ((Protocol) businessObject).getProtocolDocument().getDocumentNumber());
        parameters.put(actionKey, "true");
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }
    
    /**
     * Construct and return edit, view, and copy links.
     * @param businessObject
     * @param pkNames
     */
    private List<HtmlData> getEditCopyViewLinks(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (Protocol) businessObject, PermissionConstants.MODIFY_PROTOCOL)) {
            //htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            // Chnage "edit" to edit same document, NOT initializing a new Doc
            AnchorHtmlData editHtmlData = getViewLink(((Protocol) businessObject).getProtocolDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
            AnchorHtmlData htmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            ProtocolDocument document = ((Protocol) businessObject).getProtocolDocument();
            htmlData.setHref("../DocCopyHandler.do?docId=" + document.getDocumentNumber()
                    + "&command=displayDocSearchView&documentTypeName=" + getDocumentTypeName());
            htmlDataList.add(htmlData);
        }
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (Protocol) businessObject, PermissionConstants.VIEW_PROTOCOL)) {
            htmlDataList.add(getViewLink(((Protocol) businessObject).getProtocolDocument()));
        }
        return htmlDataList;
    }


    /**
     * This override is reset field definition for research area lookup fields & investigator label.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE)) {
                    super.updateLookupField(field,ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,RESEARCH_AREA_CLASS_PATH);
                } else if (field.getPropertyName().equals(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID)) {
                    super.updateLookupField(field,ProtocolLookupConstants.Property.ORGANIZATION_ID,ORGANIZATION_CLASS_PATH);
                } else if (field.getPropertyName().equals(ProtocolLookupConstants.Property.PROTOCOL_STATUS_CODE) || field.getPropertyName().equals(ProtocolLookupConstants.Property.PROTOCOL_TYPE_CODE)) {
                    // to disable lookup/inquiry display
                    field.setQuickFinderClassNameImpl(KRADConstants.EMPTY_STRING);
                } else if (field.getPropertyName().startsWith("leadUnit")) {
                    // This is to set label for search criteria.
                    if (field.getPropertyName().equals("leadUnitNumber")) {
                        field.setFieldLabel("Unit Number");
                    } else {
                        field.setFieldLabel("Unit Name");                        
                    }
                }
            }
        }
        return rows;
    }
             
    /**
     * 
     * This is spring bean will be used to get search results.
     * @param protocolDao
     */
    public void setProtocolDao(ProtocolDao protocolDao) {
        this.protocolDao = protocolDao;
    }
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.core.bo.BusinessObject, java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals(ProtocolLookupConstants.Property.LEAD_UNIT_NUMBER)) {
           inqBo = new Unit();
            ((Unit) inqBo).setUnitNumber(((Protocol) bo).getLeadUnitNumber());
            inqPropertyName = ProtocolLookupConstants.Property.UNIT_NUMBER;
        } else if (propertyName.equals(ProtocolLookupConstants.Property.INVESTIGATOR)) {
            Protocol protocol = (Protocol) bo;
            ProtocolPerson principalInvestigator = protocol.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = ProtocolLookupConstants.Property.PERSON_ID;
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        inqBo = new Rolodex();
                        ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = ProtocolLookupConstants.Property.ROLODEX_ID;
                    }
                }
            }
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);
    }

    @Override
    protected String getHtmlAction() {
        return "protocolProtocol.do";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "ProtocolDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "protocolNumber"; 
    }

    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}