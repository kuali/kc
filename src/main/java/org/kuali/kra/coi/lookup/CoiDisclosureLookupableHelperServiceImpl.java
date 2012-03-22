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
package org.kuali.kra.coi.lookup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.drools.core.util.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.auth.ViewCoiDisclosureAuthorizer;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

public class CoiDisclosureLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private DictionaryValidationService dictionaryValidationService;
    private TaskAuthorizationService taskAuthorizationService;
    
    //field names
    private static final String LEAD_UNIT = "leadUnitNumber";

    @Override
    protected String getDocumentTypeName() {
        return "CoiDisclosureDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "coiDisclosureRedirect.do";
    }

    @Override
    protected String getKeyFieldName() {
        return "coiDisclosureId";
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    /**
     * 
     * This method returns an instance of a DictionaryValidationService implementation.
     * @return
     */
    public DictionaryValidationService getDictionaryValidationService() {
        if (dictionaryValidationService == null) {
            dictionaryValidationService =  KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return dictionaryValidationService;
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

    @Override
    public void validateSearchParameters(Map fieldValues) {
        Map<String,String> fvalues = (Map<String,String>)fieldValues;
        super.validateSearchParameters(fieldValues);
        Set<String> keys = fieldValues.keySet();
        for (String key : keys) {
            String value = fieldValues.get(key).toString();
            if (key.toUpperCase().indexOf("DATE") > 0) {
                //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
                if (value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                    if (!StringUtils.isEmpty(value)) {
                        boolean valid = validateDate(key, value);
                    }
                }
            }
        }
    }

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        validateSearchParameters(fieldValues);
        List<CoiDisclosure> results;
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        results = (List<CoiDisclosure>)super.getSearchResults(fieldValues);
        return filterResults(results, fieldValues);
    }

    protected List<CoiDisclosure> filterResults(List<CoiDisclosure> rawResults, Map<String, String> fieldValues) {
        ViewCoiDisclosureAuthorizer viewAuthorizer = new ViewCoiDisclosureAuthorizer();
        List<CoiDisclosure> finalResults = new ArrayList<CoiDisclosure>();
        String researcherLeadUnit = fieldValues.get(LEAD_UNIT);
        for (CoiDisclosure disclosure : rawResults) {
            if (disclosure.getCoiDisclosureDocument() != null) {
                CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, disclosure);
                if (getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task) && 
                        (StringUtils.isEmpty(researcherLeadUnit) || researcherLeadUnit.equals(disclosure.getLeadUnitNumber()))) {
                    finalResults.add(disclosure);
                }
            }
        }
        return finalResults;
    }

    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
        CoiDisclosure coiDisclosure = (CoiDisclosure) businessObject;
        
        htmlDataList.add(getViewLink(coiDisclosure.getCoiDisclosureDocument()));
        if (!CoiDisclosureStatus.ROUTED_FOR_REVIEW.equals(coiDisclosure.getDisclosureStatusCode())) {
            CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MODIFY_COI_DISCLOSURE, coiDisclosure);
            if (getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task)) {   
                htmlDataList.add(getEditLink(coiDisclosure.getCoiDisclosureDocument()));
            }
        }
    }
    
    @Override
    protected AnchorHtmlData getViewLink(Document document) {
        Properties parameters = getLinkProperties(document);
        parameters.put("viewDocument", "true");
        String displayText = "view";
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private AnchorHtmlData getEditLink(Document document) {
        Properties parameters = getLinkProperties(document);
        String displayText = KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL;
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private Properties getLinkProperties(Document document) {
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "redirectToProtocolFromReview");
        parameters.put(KRADConstants.PARAMETER_COMMAND, "displayDocSearchView");
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("docId", document.getDocumentNumber());
        return parameters;
    }
    
    private AnchorHtmlData getAnchorHtmlData(Properties parameters, String displayText) {
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        return new AnchorHtmlData(href, KRADConstants.DOC_HANDLER_METHOD, displayText);
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }
}
