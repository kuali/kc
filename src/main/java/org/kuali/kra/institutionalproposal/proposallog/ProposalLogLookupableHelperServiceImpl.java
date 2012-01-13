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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.document.authorization.InstitutionalProposalDocumentAuthorizer;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.KEWPropertyConstants;
import org.kuali.rice.kew.web.session.UserSession;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;


/**
 * Lookupable helper service used for proposal log lookup
 */     
public class ProposalLogLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = -7638045643796948730L;
    
    private static final String USERNAME_FIELD = "person.userName";
    private static final String STATUS_PENDING = "1";
    private static final String DOC_ROUTE_STATUS = "docRouteStatus";
    
    private boolean isLookupForProposalCreation;
    private DocumentService documentService;
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /*
     * We want to allow users to query on principal name instead of person id, 
     * so we need to translate before performing the lookup.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get(USERNAME_FIELD);
        if (!StringUtils.isBlank(userName)) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put("piId", person.getPersonId());
            }
            lookupForm.getFieldsForLookup().remove(USERNAME_FIELD);
        }
        Collection results = super.performLookup(lookupForm, resultTable, bounded);
        if (StringUtils.containsIgnoreCase(lookupForm.getBackLocation(), "negotiationNegotiation")) {
            return cleanSearchResultsForNegotiationLookup(results);
        } else {
            return results;
        }
    }
    
    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        checkIsLookupForProposalCreation(fieldValues);
        List<ProposalLog> results = (List<ProposalLog>)super.getSearchResults(fieldValues);
        String returnLocation = fieldValues.get("backLocation");
       List<ProposalLog> searchList = filterForPermissions(results);
        if (StringUtils.containsIgnoreCase(returnLocation, "negotiationNegotiation")) {
            return cleanSearchResultsForNegotiationLookup(searchList);
        } else {
            return searchList;
        }
    }
    protected List<ProposalLog> filterForPermissions(List<ProposalLog> results) {
        Person user = UserSession.getAuthenticatedUser().getPerson();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        List<ProposalLog> proposalLogs = new ArrayList<ProposalLog>();
        ProposalLogDocumentAuthorizer authorizer = new ProposalLogDocumentAuthorizer();
        List<String> proposalIds = new ArrayList<String>();
        fieldValues.put(KEWPropertyConstants.NAME, getMaintenanceDocumentDictionaryService().getDocumentTypeName(
                ProposalLog.class));
        List<Long> docTypeIds = new ArrayList<Long>();
        for (DocumentType docType : (List<DocumentType>) getBusinessObjectService().findMatching(DocumentType.class, fieldValues)) {
            docTypeIds.add(docType.getDocumentTypeId());
        }
        fieldValues.clear();
        fieldValues.put(KEWPropertyConstants.DOCUMENT_TYPE_ID, docTypeIds);
        fieldValues.put(DOC_ROUTE_STATUS, KEWConstants.ROUTE_HEADER_FINAL_CD);
        List<DocumentRouteHeaderValue> docHeaders = (List<DocumentRouteHeaderValue>) getBusinessObjectService().findMatching(DocumentRouteHeaderValue.class, fieldValues);
        try {
            for (DocumentRouteHeaderValue docHeader : docHeaders) {
                MaintenanceDocumentBase doc = (MaintenanceDocumentBase) documentService.getByDocumentHeaderId(docHeader
                        .getRouteHeaderId().toString());
                for(ProposalLog proposalLog : results){
                    ProposalLog proposalLogDoc = (ProposalLog)doc.getNewMaintainableObject().getBusinessObject();
                    if(proposalLog.getProposalNumber() != null && proposalLogDoc.getProposalNumber() != null && proposalLog.getProposalNumber().equalsIgnoreCase(proposalLogDoc.getProposalNumber())){
                        if(!(proposalIds.contains(proposalLog.getProposalNumber()))){
                            proposalIds.add(proposalLog.getProposalNumber());
                            if(authorizer.canOpen(doc, user)){
                                proposalLogs.add(proposalLog);
                            }
                        }
                       
                    }
                            
                }
                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        return proposalLogs;
    }
        
    private List<ProposalLog> cleanSearchResultsForNegotiationLookup(Collection searchResults) {
        List<ProposalLog> newResults = new ArrayList<ProposalLog>();
        for (ProposalLog pl : (List<ProposalLog>) searchResults) {
            if (StringUtils.isBlank(pl.getInstProposalNumber())) {
                newResults.add(pl);
            }
        }
        return newResults;
    }
    
    /**
     * create 'merge' link
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (isLookupForProposalCreation) {
            if (STATUS_PENDING.equals(((ProposalLog) businessObject).getLogStatus())) {
                htmlDataList.add(getSelectLinkForProposalCreation((ProposalLog) businessObject));
            }
        }
        else {
            htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            if (((ProposalLog) businessObject).isMergeCandidate()) {
                htmlDataList.add(getMergeLink(((ProposalLog) businessObject).getProposalNumber()));
            }
            if (((ProposalLog) businessObject).isSubmitted()) {
                removeEditLink(htmlDataList);
            }
            htmlDataList.add(getPrintLink(((ProposalLog)businessObject).getProposalNumber()));
        }
        return htmlDataList;
    }
    
    /**
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        if (this.getParameters().containsKey("returnLocation")
                && ((String[]) this.getParameters().get("returnLocation"))[0].indexOf("institutionalProposalCreate") > 0) {
            isLookupForProposalCreation = true;
        }
//        if (this.getParameters().containsKey("isPendingSelected")) {
//            GlobalVariables.getMessageMap().putError("proposalLogStatus", KeyConstants.ERROR_PENDING_PROPOSAL_LOG_ONLY);
//        }

        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(USERNAME_FIELD)) {
                    field.setFieldConversions("principalName:person.userName,principalId:personId");
                }
                if (field.getPropertyName().equals("logStatus") && isLookupForProposalCreation) {
                    field.setPropertyValue(STATUS_PENDING);
                }
            }
        }
        return rows;
    }
    
    protected AnchorHtmlData getSelectLinkForProposalCreation(ProposalLog proposalLog) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "docHandler");
        parameters.put(KNSConstants.PARAMETER_COMMAND, "initiate");
        parameters.put(KNSConstants.DOCUMENT_TYPE_NAME, "InstitutionalProposalDocument");
        parameters.put("proposalNumber", proposalLog.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../institutionalProposalHome.do", parameters);

        htmlData.setHref(href);
        return htmlData;
    }
    

    protected AnchorHtmlData getMergeLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("merge");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "pageEntry");
        parameters.put("proposalLogNumber", proposalNumber);
        String href  = UrlFactory.parameterizeUrl("../mergeProposalLog.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected AnchorHtmlData getPrintLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("print");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "printProposalLog");
        parameters.put("proposalNumber", proposalNumber);
        String href  = UrlFactory.parameterizeUrl("../printProposalLog.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }    
    
    protected void checkIsLookupForProposalCreation(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get("backLocation");
        if (returnLocation.contains("institutionalProposalCreate.do")) {
            isLookupForProposalCreation = true;
        }
    }
    
    protected void removeEditLink(List<HtmlData> htmlDataList) {
        int editLinkIndex = -1;
        int currentIndex = 0;
        for (HtmlData htmlData : htmlDataList) {
            if (KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL.equals(htmlData.getMethodToCall())) {
                editLinkIndex = currentIndex;
                break;
            }
            currentIndex++;
        }
        if (editLinkIndex != -1) {
            htmlDataList.remove(editLinkIndex);
        }
    }
    
    protected KcPersonService getKcPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);
    }
    
        }
    
