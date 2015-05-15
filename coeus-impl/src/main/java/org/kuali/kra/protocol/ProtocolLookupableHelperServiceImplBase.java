/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.ResearchArea;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
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

import java.text.ParseException;
import java.util.*;

/**
 * This class handles searching for protocols.
 */
public abstract class ProtocolLookupableHelperServiceImplBase<GenericProtocol extends ProtocolBase> extends KraLookupableHelperServiceImpl {    


    private static final long serialVersionUID = 273532318463507554L;
    protected static final String AMEND_RENEW_PROTOCOL_LOOKUP_ACTION = "lookupActionAmendRenewProtocol";
    protected static final String REQUEST_PROTOCOL_ACTION = "lookupActionRequestProtocol";
    protected static final String PENDING_PROTOCOL_LOOKUP = "lookupPendingProtocol";
    protected static final String PENDING_PI_ACTION_PROTOCOL_LOOKUP = "lookupPendingPIActionProtocol";
    protected static final String PROTOCOL_PERSON_ID_LOOKUP = "lookupProtocolPersonId";
    
    private static final String PROTOCOL_LOOKUP_KEY_FIELD = "protocolNumber";
    
    
    private static final String RESEARCH_AREA_CLASS_PATH = ResearchArea.class.getName();
    private static final String ORGANIZATION_CLASS_PATH = Organization.class.getName();
    
    private static final Log LOG = LogFactory.getLog(ProtocolLookupableHelperServiceImplBase.class);

    private DictionaryValidationService dictionaryValidationService;
    
    protected KcPersonService kcPersonService;
    protected KcAuthorizationService kraAuthorizationService;
    protected TaskAuthorizationService taskAuthorizationService;
    protected DocumentService documentService;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        validateSearchParameters(fieldValues);
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        return getSearchResultsFilteredByTask(fieldValues); //filterProtocols(fieldValues);
    }
    
    
    /**
     * Filters the unbounded list of protocols by the given field values and protocol tasks.
     * @param fieldValues the field values that form a normal protocol search
     * @param taskNames the list of protocol tasks that the user must be able to perform in a protocol in order for it to be in the search results
     * @return a list of all protocols filtered by the given field values and being able to perform tasks in the taskNames
     */
    protected List<? extends BusinessObject> filterProtocolsByTask(Map<String, String> fieldValues, String... taskNames) {
        List<GenericProtocol> filteredProtocols = new ArrayList<GenericProtocol>();
        
        for (GenericProtocol protocol : (List<GenericProtocol>)getProtocolDaoHook().getProtocols(filterFieldValues(fieldValues))) {
            for (String taskName : taskNames) {
                ProtocolTaskBase task = createNewProtocolTaskInstanceHook(taskName, protocol);
                if (taskAuthorizationService.isAuthorized(getUserIdentifier(), task)) {
                    filteredProtocols.add(protocol);
                    break;
                }
            }
        }
        
        return getPagedResults(filteredProtocols);
    }

    protected abstract ProtocolTaskBase createNewProtocolTaskInstanceHook(String taskName, ProtocolBase protocol);


    /**
     * Filters the unbounded list of protocols by the given field values and protocol status codes.
     * @param fieldValues the field values that form a normal protocol search
     * @param protocolStatusCodes the list of protocol status codes that should be included in the search result
     * @return a list of all protocols filtered by the given field values and having statuses in protocolStatusCodes
     */
    protected List<? extends BusinessObject> filterProtocolsByStatus(Map<String, String> fieldValues, String... protocolStatusCodes) {
        List<GenericProtocol> filteredProtocols = new ArrayList<GenericProtocol>();

        List<String> protocolStatusCodeList = Arrays.asList(protocolStatusCodes);
        for (GenericProtocol protocol : (List<GenericProtocol>)getProtocolDaoHook().getProtocols(filterFieldValues(fieldValues))) {
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
    protected List<? extends BusinessObject> filterProtocolsByPrincipal(Map<String, String> fieldValues, String principalKey) {
        List<GenericProtocol> filteredProtocols = new ArrayList<GenericProtocol>();
        
        String principalId = fieldValues.get(principalKey);
        for (GenericProtocol protocol : (List<GenericProtocol>)getProtocolDaoHook().getProtocols(filterFieldValues(fieldValues))) {
            try {
                String principalInvestigatorId = protocol.getPrincipalInvestigator().getPersonId();
                ProtocolDocumentBase document = (ProtocolDocumentBase) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
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
    protected List<? extends BusinessObject> filterProtocols(Map<String, String> fieldValues) {
        List<GenericProtocol> protocols = (List<GenericProtocol>)getProtocolDaoHook().getProtocols(filterFieldValues(fieldValues));
        return getPagedResults(protocols);
    }
    
    /**
     * Filters out extra lookup parameters from the fieldValues.
     * @param fieldValues the field values that form a normal protocol search
     * @return the field values with extra lookup parameters removed
     */
    private Map<String, String> filterFieldValues(Map<String, String> fieldValues) {
        return removeExtraFilterParameters(fieldValues);
    }

    /**
     * Separates the list of protocols into pageable results.
     * @param protocols the list of protocols
     * @return a collection of protocol pageable results.
     */
    private CollectionIncomplete<GenericProtocol> getPagedResults(List<GenericProtocol> protocols) {
        Long matchingResultsCount = new Long(protocols.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(getProtocolClassHook());
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete<GenericProtocol>(protocols, new Long(0));
        } else {
            return new CollectionIncomplete<GenericProtocol>(trimResult(protocols, searchResultsLimit), matchingResultsCount);
        }
    }


    /**
     * This method trims the search result.
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    protected List<GenericProtocol> trimResult(List<GenericProtocol> result, Integer trimSize) {
        List<GenericProtocol> trimedResult = new ArrayList<GenericProtocol>();
        for (GenericProtocol protocol : result) {
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
            LOG.error(e.getMessage(), e);
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

    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        return getCustomActions(businessObject, pkNames);
    }
    
    protected boolean isParameterTrue(String parameterKey) {
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
    protected AnchorHtmlData getPerformActionLink(BusinessObject businessObject, String actionKey) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("perform action");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "performProtocolAction");
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", "false");
        parameters.put("docId", ((ProtocolBase) businessObject).getProtocolDocument().getDocumentNumber());
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
    protected List<HtmlData> getEditCopyViewLinks(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (ProtocolBase) businessObject, PermissionConstants.MODIFY_PROTOCOL)) {
            //htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            // Chnage "edit" to edit same document, NOT initializing a new Doc
            AnchorHtmlData editHtmlData = getViewLink(((ProtocolBase) businessObject).getProtocolDocument());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
            AnchorHtmlData htmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            ProtocolDocumentBase document = ((ProtocolBase) businessObject).getProtocolDocument();
            htmlData.setHref("../DocCopyHandler.do?docId=" + document.getDocumentNumber()
                    + "&command=displayDocSearchView&documentTypeName=" + getDocumentTypeName());
            htmlDataList.add(htmlData);
        }
        if (kraAuthorizationService.hasPermission(getUserIdentifier(), (ProtocolBase) businessObject, PermissionConstants.VIEW_PROTOCOL)) {
            htmlDataList.add(getViewLink(((ProtocolBase) businessObject).getProtocolDocument()));
        }
        return htmlDataList;
    }


    /**
     * This override is reset field definition for research area lookup fields &amp; investigator label.
     */
    @SuppressWarnings("deprecation")
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
            ((Unit) inqBo).setUnitNumber(((ProtocolBase) bo).getLeadUnitNumber());
            inqPropertyName = ProtocolLookupConstants.Property.UNIT_NUMBER;
        } else if (propertyName.equals(ProtocolLookupConstants.Property.INVESTIGATOR)) {
            GenericProtocol protocol = (GenericProtocol) bo;
            ProtocolPersonBase principalInvestigator = protocol.getPrincipalInvestigator();
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
        return getHtmlActionHook();
    }
    
    @Override
    protected String getDocumentTypeName() {
        return getDocumentTypeNameHook();
    }
    
    @Override
    protected String getKeyFieldName() {
        return PROTOCOL_LOOKUP_KEY_FIELD; 
    }

    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


    protected abstract List<? extends BusinessObject> getSearchResultsFilteredByTask(Map<String, String> fieldValues);
    protected abstract Map<String, String> removeExtraFilterParameters(Map<String, String> fieldValues);
    protected abstract List<HtmlData> getCustomActions(BusinessObject businessObject, List pkNames);
    protected abstract ProtocolDao<GenericProtocol> getProtocolDaoHook();
    protected abstract String getDocumentTypeNameHook();
    protected abstract String getHtmlActionHook();
    protected abstract Class<? extends ProtocolBase> getProtocolClassHook();
    
}
