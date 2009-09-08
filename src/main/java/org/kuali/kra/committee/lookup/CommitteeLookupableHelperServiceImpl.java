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
package org.kuali.kra.committee.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeAuthorizationService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.CollectionIncomplete;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 
 * This class is for committee lookup.
 */
@SuppressWarnings("serial")
public class CommitteeLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final String MEMBERSHIP_ROLE_CODE = "membershipRoleCode";
    private static final String MEMBERSHIP_NAME = "memberName";
    private static final String PERSON_NAME = "personName";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final Log LOG = LogFactory.getLog(CommitteeLookupableHelperServiceImpl.class);
    private List<CommitteeDocument> committeeDocuments;
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        List<Committee> activeCommittees =  (List<Committee>)getUniqueList(super.getSearchResults(setupCritMaps(fieldValues)));
        Long matchingResultsCount = new Long(activeCommittees.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Question.class);
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete(activeCommittees, new Long(0));
        } else {
            return new CollectionIncomplete(trimResult(activeCommittees, searchResultsLimit), matchingResultsCount);
        }
    }

    /*
     * set up search criteria value map.  Especially for collection entity fields.
     */
    private Map<String,String> setupCritMaps(Map<String, String> fieldValues) {

        Map <String,String> baseLookupFields = new HashMap<String,String>();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (entry.getKey().equals(MEMBERSHIP_NAME)) {
                baseLookupFields.put("committeeMemberships.personName",entry.getValue());
            } else if (entry.getKey().equals(MEMBERSHIP_ROLE_CODE)) {
                baseLookupFields.put("committeeMemberships.membershipRoles.membershipRoleCode",entry.getValue());
            } else if (entry.getKey().equals(RESEARCH_AREA_CODE)) {
                baseLookupFields.put("committeeResearchAreas.researchAreaCode",entry.getValue());
            } else {
                baseLookupFields.put(entry.getKey(),entry.getValue());                
            }
        }
        return baseLookupFields;
    }
    
    /**
     * Specifically, for drop down.
     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(RESEARCH_AREA_CODE)) {
                    super.updateLookupField(field,RESEARCH_AREA_CODE,"org.kuali.kra.bo.ResearchArea");
                } else if (field.getPropertyName().equals(MEMBERSHIP_NAME)) {
                    super.updateLookupField(field,PERSON_NAME,"org.kuali.kra.committee.bo.CommitteeMembership");
                }
            }
        }
        return rows;
    }

    /*
     * remove duplicates and get only the one with the highest sequence number from the search results
     */
    @SuppressWarnings("unchecked")
    private List<? extends BusinessObject> getUniqueList(List<? extends BusinessObject> searchResults) {

        List<Committee> uniqueResults = new ArrayList<Committee>();
        List<String> committeeIds = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(searchResults)) {
            Collections.sort((List<Committee>) searchResults, Collections.reverseOrder());
            for (Committee committee : (List<Committee>) searchResults) {
                if (!committeeIds.contains(committee.getCommitteeId())) {
                    committee.getCommitteeChair();
                    uniqueResults.add(committee);
                    committeeIds.add(committee.getCommitteeId());
                }
            }
        }
        return uniqueResults;
    }
    
    protected String getHtmlAction() {
        return "committeeCommittee.do";
    }
    
    protected String getDocumentTypeName() {
        return "CommitteeDocument";
    }
    
    protected String getKeyFieldName() {
        return "committeeId";
    }
    
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if(getCommitteeAuthorizationService().hasPermission(getUserName(), (Committee) businessObject, PermissionConstants.MODIFY_COMMITTEE)) {
            if (!isCommitteeBeingEdited((Committee) businessObject)) {
                htmlDataList =  super.getCustomActionUrls(businessObject, pkNames);
            }
        }
        if(getCommitteeAuthorizationService().hasPermission(getUserName(), (Committee) businessObject, PermissionConstants.VIEW_COMMITTEE)) {
            htmlDataList.add(getViewLink(((Committee) businessObject).getCommitteeDocument()));
        }
        return htmlDataList;
    }

    private boolean isCommitteeBeingEdited(Committee committee) {
        boolean isEdited = false;
        for (CommitteeDocument doc : getCommitteeDocuments()) {
            try {
                if (doc.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                        KEWConstants.ROUTE_HEADER_SAVED_CD)) {
                    String content = KraServiceLocator.getService(RouteHeaderService.class).getContent(
                            doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId()).getDocumentContent();
                    if (committee.getCommitteeId().equals(StringUtils.substringBetween(content, "<committeeId>", "</committeeId>"))) {
                        isEdited = true;
                        break;
                    }
                }
            } catch (Exception e) {
                LOG.info(e.getMessage());
            }
        }
        return isEdited;
    }
    
    private List<CommitteeDocument> getCommitteeDocuments() {
        if (committeeDocuments == null) {
            List<CommitteeDocument> documents = (List<CommitteeDocument>) KraServiceLocator.getService(BusinessObjectService.class)
                    .findAll(CommitteeDocument.class);
            List<CommitteeDocument> result = new ArrayList<CommitteeDocument>();
            for (CommitteeDocument commDoc : documents) {
                try {
                    // Need this step to retrieve workflowdocument
                    CommitteeDocument doc = (CommitteeDocument) KraServiceLocator.getService(DocumentService.class)
                            .getByDocumentHeaderId(commDoc.getDocumentNumber());
                    if (doc.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(
                            KEWConstants.ROUTE_HEADER_SAVED_CD)) {
                        result.add(doc);
                    }
                }
                catch (Exception e) {
                    LOG.info(e.getMessage());
                }
            }
            committeeDocuments = result;
        }
        return committeeDocuments;

    }
    
    private CommitteeAuthorizationService getCommitteeAuthorizationService() {
        return KraServiceLocator.getService(CommitteeAuthorizationService.class);
    }

    private String getUserName() {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
         return user.getPersonUserIdentifier();
    }

    /**
     * This method trims the search result.
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    private List<Committee> trimResult(List<Committee> result, Integer trimSize) {
        List<Committee> trimedResult = new ArrayList<Committee>();
        for (Committee committee : result) {
            if (trimedResult.size()< trimSize) {
                trimedResult.add(committee); 
            }
        }
        return trimedResult;
    }

}
