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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.kew.bo.lookup.DocSearchCriteriaDTOLookupableHelperServiceImpl;
import org.kuali.rice.kew.docsearch.DocumentSearchResult;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;

public class KraDocSearchCriteriaDTOLookupableHelperServiceImpl extends DocSearchCriteriaDTOLookupableHelperServiceImpl {
    
    private static final String DOCUMENT_TITLE_FIELD = "documentTitle";
    
    private static final String PRINCIPAL_NAME_FIELD = "initiator";
    
    private static final Log LOG = LogFactory.getLog(KraDocSearchCriteriaDTOLookupableHelperServiceImpl.class);

    // TODO : should use injection to create this list instead ?
    private static ArrayList<String> notificationActions = new ArrayList<String>();
    
    private MultiCampusIdentityService multiCampusIdentityService;
    
    static {
        notificationActions.add("Close Enrollment");
        notificationActions.add("Data Analysis");
        notificationActions.add("Notify Irb");
        notificationActions.add("Open Enrollment");
        notificationActions.add("Request To Close");
        notificationActions.add("Request To Suspension");
        notificationActions.add("Withdrawn");
    }

    /**
     * Note: In trying to understand what the super class was doing in its performLookup method, I discovered the method is 300+ lines long, with many
     * commented blocks of code.
     *
     * @param lookupForm
     * @param resultTable
     * @param bounded
     * @return
     */
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        addMultiCampusPrincipalName();
        List<DocumentSearchResult> docSearchResults = (List<DocumentSearchResult>) super.performLookup(lookupForm, resultTable, bounded);
        filterOutPlaceholderDocument(resultTable, docSearchResults);
        filterOutProtocolNotificationDocument(resultTable, docSearchResults);
        generateColumnAnchor(resultTable);
        return docSearchResults;
    }
    
    /**
     * Modifies the principal names to multicampus format if multicampus mode is on.
     */
    protected void addMultiCampusPrincipalName() {
        boolean multiCampusEnabled = getParameterService().getIndicatorParameter(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        if (multiCampusEnabled) {
            Map<Object, Object> searchParameters = new HashMap<Object, Object>(getParameters());
            String[] principalNameParameter = (String[]) searchParameters.get(PRINCIPAL_NAME_FIELD);
            if (ArrayUtils.isNotEmpty(principalNameParameter)) {
                if (StringUtils.isNotBlank(principalNameParameter[0])) {
                    String principalName = principalNameParameter[0];
                    String campusCode = (String) GlobalVariables.getUserSession().retrieveObject(Constants.USER_CAMPUS_CODE_KEY);
                    String multiCampusPrincipalName = getMultiCampusIdentityService().getMultiCampusPrincipalName(principalName, campusCode);
                    searchParameters.put(PRINCIPAL_NAME_FIELD, new String[] {multiCampusPrincipalName});
                }
            }
            setParameters(Collections.unmodifiableMap(searchParameters));
        }
    }

    /**
     * For some reason, Rice uses sends the search results in two collections. So when filtering out the Placeholder document, we must do so in both
     *
     * @param resultTable
     * @param docSearchResults
     */
    synchronized protected void filterOutPlaceholderDocument(Collection resultTable, List<DocumentSearchResult> docSearchResults) {
        Iterator resultTableIter = resultTable.iterator();
        List<DocumentSearchResult> listToRemove = new ArrayList<DocumentSearchResult>();
        String titleForComparison = null;
        for(DocumentSearchResult docSearchResult: docSearchResults) {
            resultTableIter.next();
            titleForComparison = docSearchResult.getResultContainer(DOCUMENT_TITLE_FIELD).getUserDisplayValue();
            if(StringUtils.isNotBlank(titleForComparison) && titleForComparison.contains(AwardDocument.PLACEHOLDER_DOC_DESCRIPTION)) {
                resultTableIter.remove();
                listToRemove.add(docSearchResult);
                //docSearchResults.remove(docSearchResult);
                //break;
            }
        }
        docSearchResults.removeAll(listToRemove);
    }

    /*
     * filter out Notification documents generated by protocol actions notification
     */
    protected void filterOutProtocolNotificationDocument(Collection resultTable, List<DocumentSearchResult> docSearchResults) {
        List<ResultRow> notificationResultRows = new ArrayList<ResultRow>();
        List<DocumentSearchResult> notificationSearchResults = new ArrayList<DocumentSearchResult>();
        String titleForComparison = null;
        LOG.info("begin filter notification " + docSearchResults.size());
        int index = 0;
        for (DocumentSearchResult docSearchResult : docSearchResults) {
            if ("Notification".equals(docSearchResult.getResultContainer("docTypeLabel").getUserDisplayValue())) {
                titleForComparison = docSearchResult.getResultContainer(DOCUMENT_TITLE_FIELD).getUserDisplayValue();
                if (StringUtils.isNotBlank(titleForComparison) && isProtocolActionNotification(titleForComparison)) {
                    notificationResultRows.add(((List<ResultRow>) resultTable).get(index));
                    notificationSearchResults.add(docSearchResult);
                }
            }
            index++;
        }
        resultTable.removeAll(notificationResultRows);
        docSearchResults.removeAll(notificationSearchResults);
        LOG.info("end filter notification " + docSearchResults.size());
    }

    /*
     * Subject format is like "Protocol xxxxxxxx 'Action Description'"
     * if in the future protocol action notification decide to let user modify 'subject',
     * then this need to be changed.
     */
    protected boolean isProtocolActionNotification (String title) {
        boolean isProtocolNotification = false;
        if(StringUtils.isNotBlank(title) && title.startsWith("Protocol")) {
            for (String notificationAction :notificationActions) {
                if (title.contains(notificationAction)) {
                    isProtocolNotification = true; 
                    break;
                }
            }
        }
        return isProtocolNotification;
            
    }
    
    protected void generateColumnAnchor(Collection resultTable) {
        for (ResultRow resultRow : (List<ResultRow>)resultTable) {
            for (Column column : resultRow.getColumns()) {
                if (column.getPropertyName().equals("copyDocument") && column.getColumnAnchor()!= null) {
                    AnchorHtmlData anchor = (AnchorHtmlData)column.getColumnAnchor();
                    String docId = StringUtils.substringBetween(column.getPropertyValue(),"docId=", "&");
                    anchor.setHref(StringUtils.substringBetween(column.getPropertyValue(), "<a href=\"", "docId=")+"docId="+docId);
                    column.setColumnAnchor(anchor);
                }
            }
        }
    }
    
    public MultiCampusIdentityService getMultiCampusIdentityService() {
        if (multiCampusIdentityService == null) {
            multiCampusIdentityService = KraServiceLocator.getService(MultiCampusIdentityService.class);
        }
        return multiCampusIdentityService;
    }

    public void setMultiCampusIdentityService(MultiCampusIdentityService multiCampusIdentityService) {
        this.multiCampusIdentityService = multiCampusIdentityService;
    }
    
}