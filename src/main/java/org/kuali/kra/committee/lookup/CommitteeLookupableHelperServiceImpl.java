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
package org.kuali.kra.committee.lookup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.questionnaire.question.Question;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class is for committee lookup.
 */
@SuppressWarnings("serial")
public class CommitteeLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final String PERSON_NAME = "personName";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final Log LOG = LogFactory.getLog(CommitteeLookupableHelperServiceImpl.class);
    private static final String DOCHANDLER_LINK = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        List<Committee> activeCommittees =  (List<Committee>)getUniqueList(super.getSearchResultsUnbounded(fieldValues), fieldValues);
        Long matchingResultsCount = new Long(activeCommittees.size());
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Question.class);
        if ((matchingResultsCount == null) || (matchingResultsCount.intValue() <= searchResultsLimit.intValue())) {
            return new CollectionIncomplete(activeCommittees, new Long(0));
        } else {
            return new CollectionIncomplete(trimResult(activeCommittees, searchResultsLimit), matchingResultsCount);
        }
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
                if (field.getPropertyName().equals("committeeResearchAreas.researchAreaCode")) {
                    super.updateLookupField(field,RESEARCH_AREA_CODE,"org.kuali.kra.bo.ResearchArea");
                } else if (field.getPropertyName().equals("committeeMemberships.personName")) {
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
    protected List<? extends BusinessObject> getUniqueList(List<? extends BusinessObject> searchResults, Map<String, String> fieldValues) {

        List<Committee> uniqueResults = new ArrayList<Committee>();
        List<String> committeeIds = new ArrayList<String>();
        ((List<Committee>)searchResults).addAll(getUnapprovedCommittees(fieldValues));
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
        String editCommitteeDocId = getEditedCommitteeDocId((Committee) businessObject);
        boolean isUnappprovedCommittee = false;
        if (KewApiConstants.ROUTE_HEADER_SAVED_CD.equals((((Committee) businessObject).getCommitteeDocument().getDocStatusCode())) 
                && ((Committee) businessObject).getSequenceNumber() == 1) {
            isUnappprovedCommittee = true;
            editCommitteeDocId = ((Committee) businessObject).getCommitteeDocument().getDocumentNumber();
        }
        if (getKraAuthorizationService().hasPermission(getUserIdentifier(), (Committee) businessObject,
                PermissionConstants.MODIFY_COMMITTEE)) {
            htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            if (StringUtils.isNotBlank(editCommitteeDocId)) {
                AnchorHtmlData htmlData = (AnchorHtmlData) htmlDataList.get(0);
                CommitteeDocument document = ((Committee) businessObject).getCommitteeDocument();
                String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
                htmlData.setHref(String.format(DOCHANDLER_LINK, workflowUrl, editCommitteeDocId));
                htmlData.setDisplayText("resume edit");
            }
        }
        if (!isUnappprovedCommittee && getKraAuthorizationService().hasPermission(getUserIdentifier(), (Committee) businessObject,
                PermissionConstants.VIEW_COMMITTEE)) {
            AnchorHtmlData htmlData = getViewLink(((Committee) businessObject).getCommitteeDocument());
            htmlData.setDisplayText("view active");
            htmlDataList.add(htmlData);
        }
        return htmlDataList;
    }

    /*
     * get the document number of the committee that is being edited.
     */
    protected String getEditedCommitteeDocId(Committee committee) {
        String docId = null;
        List<CommitteeDocument> documents = getCommitteeDocuments(committee.getCommitteeId());
        if (CollectionUtils.isNotEmpty(documents)) {
            docId = documents.get(0).getDocumentNumber();
        }
        return docId;
    }
    
    /*
     * get saved committee documents of the committeeId specified.
     * should only have one if exists
     */
    protected List<CommitteeDocument> getCommitteeDocuments(String committeeId) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("committeeId", committeeId);
        List<CommitteeDocument> documents = (List<CommitteeDocument>) getBusinessObjectService()
                .findMatching(CommitteeDocument.class, fieldValues);
        List<CommitteeDocument> result = new ArrayList<CommitteeDocument>();
        for (CommitteeDocument commDoc : documents) {
            if (KewApiConstants.ROUTE_HEADER_SAVED_CD.equals(commDoc.getDocStatusCode())) {
                result.add(commDoc);
            }
        }
        return result;

    }
    
    /*
     * Get the committee that is saved, but not approved yet.  basically this is sequence = 1
     */
    protected List<Committee> getUnapprovedCommittees(Map<String, String> criterias) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("docStatusCode", "S");
        List<CommitteeDocument> documents = (List<CommitteeDocument>) getBusinessObjectService().findMatching(
                CommitteeDocument.class, fieldValues);
        List<Committee> result = new ArrayList<Committee>();
        List<String> committeeIds = getCommitteeIds();
        for (CommitteeDocument commDoc : documents) {
            if (!committeeIds.contains(commDoc.getCommitteeId())) {
                try {
                    CommitteeDocument workflowCommitteeDoc = (CommitteeDocument) KraServiceLocator
                            .getService(DocumentService.class).getByDocumentHeaderId(commDoc.getDocumentNumber());
                    // Get XML of workflow document
                    String content = KraServiceLocator.getService(RouteHeaderService.class).getContent(
                            workflowCommitteeDoc.getDocumentHeader().getWorkflowDocument().getDocumentId()).getDocumentContent();

                    // Create committee from XML and add to the document

                    commDoc.getCommitteeList().add(populateCommitteeFromXmlDocumentContents(content));
                    if (isCriteriaMatched(commDoc.getCommittee(), criterias)) {
                        commDoc.getCommittee().setCommitteeDocument(commDoc);
                        result.add(commDoc.getCommittee());
                    }
                } catch (Exception e) {
                    LOG.info("Committee Doc " + commDoc.getDocumentNumber() + " parsing error");
                }
            }
        }
        return result;

    }

    /*
     * This is to check the committee, which is not approved yet, matches the search criteria specified.
     * This is a new committee and not persisted to DB yet.  So, this tedious criteria check is needed.
     */
    protected boolean isCriteriaMatched(Committee committee, Map<String, String> criterias) {
        boolean isMatch = isMatching(criterias.get("committeeId"), committee.getCommitteeId())
                && isMatching(criterias.get("committeeName"), committee.getCommitteeName())
                && isMatching(criterias.get("homeUnitNumber"), committee.getHomeUnitNumber())
                && isMatching(criterias.get("committeeDescription"), committee.getCommitteeDescription())
                && isMatchingCode(criterias.get("committeeTypeCode"), committee.getCommitteeTypeCode())
                && isMatchingCode(criterias.get("reviewTypeCode"), committee.getReviewTypeCode());

        // membership check
        if (isMatch
                && (StringUtils.isNotBlank(criterias.get("committeeMemberships.personName")) || StringUtils.isNotBlank(criterias
                        .get("committeeMemberships.membershipRoles.membershipRoleCode")))) {
            if (CollectionUtils.isNotEmpty(committee.getCommitteeMemberships())) {
                if (StringUtils.isNotBlank(criterias.get("committeeMemberships.personName"))) {
                    isMatch = isMemberNameMatch(criterias.get("committeeMemberships.personName"), committee
                            .getCommitteeMemberships());

                }
                if (isMatch && StringUtils.isNotBlank(criterias.get("committeeMemberships.membershipRoles.membershipRoleCode"))) {
                    isMatch = isMemberRoleMatch(criterias.get("committeeMemberships.membershipRoles.membershipRoleCode"), committee
                            .getCommitteeMemberships());
                }
            } else {
                isMatch = false;
            }
        }

        // researchareacode check
        if (isMatch && (StringUtils.isNotBlank(criterias.get("committeeResearchAreas.researchAreaCode")))) {
            if (CollectionUtils.isNotEmpty(committee.getCommitteeResearchAreas())) {
                isMatch = isAreaResearchMatch(criterias.get("committeeResearchAreas.researchAreaCode"), committee
                        .getCommitteeResearchAreas());
            } else {
                isMatch = false;
            }
        }
        if (isMatch) {
            committee.refreshReferenceObject("homeUnit");
        }
        return isMatch;
    }
    
    /*
     * check if any member name matched the criteria
     */
    protected boolean isMemberNameMatch(String matchmember, List<CommitteeMembership> members) {
        boolean isMatch = false;
        for (CommitteeMembership member : members) {
            if (isMatching(matchmember, member.getPersonName())) {
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }

    /*
     * check if any member role code matched the selected role code
     */
    protected boolean isMemberRoleMatch(String matchRoleCode, List<CommitteeMembership> members) {
        boolean isMatch = false;
        for (CommitteeMembership member : members) {
            for (CommitteeMembershipRole role : member.getMembershipRoles()) {
                if (isMatchingCode(matchRoleCode, role.getMembershipRoleCode())) {
                    isMatch = true;
                    break;
                }
            }
        }
        return isMatch;
    }
    
    /*
     * check if any committee research area code matched search criteria
     */
    protected boolean isAreaResearchMatch(String matchArea, List<CommitteeResearchArea> researchAreas) {
        boolean isMatch = false;
        for (CommitteeResearchArea researchArea : researchAreas) {
            if (isMatching(matchArea, researchArea.getResearchAreaCode())) {
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }
    
    /*
     * using reg expression to check if pattern matched
     */
    protected boolean isMatching(String patternString, String value) {
        boolean isMatch = false;
        if (StringUtils.isBlank(patternString)) {
            isMatch = true;
        }
        else {
            patternString = patternString.replaceAll("\\?", "\\\\?");
            patternString = patternString.replaceAll("\\.", "\\\\.");
            if (patternString.indexOf("*") == 0) {
                patternString = patternString.replaceFirst("\\*", "^*");
            }
            if (!patternString.endsWith("*")) {
                patternString = patternString + "$";
            }
            Pattern p = Pattern.compile(patternString.toUpperCase());
            Matcher m = p.matcher(value.toUpperCase());
            isMatch = m.find();
        }
        return isMatch;

    }

    /*
     * check if the selected code matched
     */
    protected boolean isMatchingCode(String selectedCode, String value) {
        boolean isMatch = false;
        if (StringUtils.isBlank(selectedCode)) {
            isMatch = true;
        }
        else {
            isMatch = selectedCode.equals(value);
        }
        return isMatch;

    }

    /*
     * Create a Committee object and populate it from the xml.
     */
    protected Committee populateCommitteeFromXmlDocumentContents(String xmlDocumentContents) {
        Committee committee = null;
        if (!StringUtils.isEmpty(xmlDocumentContents)) {
                committee = (Committee) getBusinessObjectFromXML(xmlDocumentContents, Committee.class.getName());
        }
        return committee;
    }
    
    /*
     * Retrieves substring of document contents from maintainable tag name. Then use xml service to translate xml into a business
     * object.
     */
    protected PersistableBusinessObject getBusinessObjectFromXML(String xmlDocumentContents, String objectTagName) {
        String objXml = StringUtils.substringBetween(xmlDocumentContents, "<" + objectTagName + ">", "</" + objectTagName + ">");
        objXml = "<" + objectTagName + ">" + objXml + "</" + objectTagName + ">";
        if (objXml.contains("itemDesctiption")) {
            objXml = objXml.replaceAll("itemDesctiption", "itemDescription");
        }
        PersistableBusinessObject businessObject = (PersistableBusinessObject) KRADServiceLocator.getXmlObjectSerializerService().fromXml(objXml);
        return businessObject;
    }

    protected KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    /*
     * get the existing approved committee id
     */
    protected List<String> getCommitteeIds() {
        List<Committee> committees = (List<Committee>) getBusinessObjectService().findAll(
                Committee.class);
        List<String> result = new ArrayList<String>();
        for (Committee committee : committees) {
            if (!result.contains(committee.getCommitteeId())) {
                result.add(committee.getCommitteeId());
            }
        }
        return result;
    }

    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    /**
     * This method trims the search result.
     * @param result, the result set to be trimmed
     * @param trimSize, the maximum size of the trimmed result set
     * @return the trimmed result set
     */
    protected List<Committee> trimResult(List<Committee> result, Integer trimSize) {
        List<Committee> trimedResult = new ArrayList<Committee>();
        for (Committee committee : result) {
            if (trimedResult.size()< trimSize) {
                trimedResult.add(committee); 
            }
        }
        return trimedResult;
    }

}
