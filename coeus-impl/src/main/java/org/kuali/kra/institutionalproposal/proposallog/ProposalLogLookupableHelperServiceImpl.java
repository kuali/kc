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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.DocumentAuthorizer;
import org.kuali.rice.krad.document.DocumentPresentationController;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.service.DocumentDictionaryService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;

/**
 * Lookupable helper service used for proposal log lookup
 */     
public class ProposalLogLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    public static final String PRINT_PROPOSAL_LOG_ACTION = "../printProposalLog.do";
    private static final long serialVersionUID = -7638045643796948730L;
    
    private static final String USERNAME_FIELD = "person.userName";
    private static final String STATUS_PENDING = "1";
    public static final String FOR_INSTITUTIONAL_PROPOSAL = "forInstitutionalProposal";
    public static final String LOG_STATUS = "logStatus";
    public static final String DOC_HANDLER = "docHandler";
    public static final String INITIATE = "initiate";
    public static final String PROPOSAL_NUMBER = "proposalNumber";
    public static final String INSTITUTIONAL_PROPOSAL_HOME_ACTION = "../institutionalProposalHome.do";
    public static final String MERGE = "merge";
    public static final String PAGE_ENTRY = "pageEntry";
    public static final String PROPOSAL_LOG_NUMBER = "proposalLogNumber";
    public static final String MERGE_PROPOSAL_LOG_ACTION = "../mergeProposalLog.do";
    public static final String PRINT_PROPOSAL_LOG = "printProposalLog";
    public static final String PI_ID = "piId";
    public static final String NEGOTIATION_NEGOTIATION = "negotiationNegotiation";
    public static final String BACK_LOCATION = "backLocation";
    public static final String FIELD_CONVERSIONS = "principalName:person.userName,principalId:personId";
    public static final String DISPLAY_TEXT = "select";
    public static final String PRINT_LINK = "print";

    private KcPersonService kcPersonService;
    private DocumentDictionaryService documentDictionaryService;
    public static final String INST_PROP_DOC_NAME  = "InstitutionalProposalDocument";


    /*
     * We want to allow users to query on principal name instead of person id, 
     * so we need to translate before performing the lookup.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get(USERNAME_FIELD);
        lookupForm.getFieldsForLookup().remove(FOR_INSTITUTIONAL_PROPOSAL);

        if (!StringUtils.isBlank(userName)) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put(PI_ID, person.getPersonId());
            }
            lookupForm.getFieldsForLookup().remove(USERNAME_FIELD);
        }
        Collection results = super.performLookup(lookupForm, resultTable, bounded);
        if (StringUtils.containsIgnoreCase(lookupForm.getBackLocation(), NEGOTIATION_NEGOTIATION)) {
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
        
        checkIsLookupForProposalCreation();
        List<ProposalLog> results = (List<ProposalLog>)super.getSearchResults(fieldValues);
        String returnLocation = fieldValues.get(BACK_LOCATION);
       List<ProposalLog> searchList = filterForPermissions(results);
        if (StringUtils.containsIgnoreCase(returnLocation, NEGOTIATION_NEGOTIATION)) {
            return cleanSearchResultsForNegotiationLookup(searchList);
        } else {
            return searchList;
        }
    }
    
    protected List<ProposalLog> filterForPermissions(List<ProposalLog> results) {
    	List<ProposalLog> proposalLogs = new ArrayList<ProposalLog>();
    	ProposalLogDocumentAuthorizer authorizer = new ProposalLogDocumentAuthorizer();
    	Person user = GlobalVariables.getUserSession().getPerson();
    	for(ProposalLog proposalLog : results){
            if(proposalLog.getProposalNumber() != null){
	            if(authorizer.canOpen(proposalLog, user)){
	                proposalLogs.add(proposalLog);
	            }
            }
        }
    	return proposalLogs;
    }
        
    private List<ProposalLog> cleanSearchResultsForNegotiationLookup(Collection<ProposalLog> searchResults) {
        List<ProposalLog> newResults = new ArrayList<ProposalLog>();
        for (ProposalLog pl : searchResults) {
            if (StringUtils.isBlank(pl.getInstProposalNumber())) {
                newResults.add(pl);
            }
        }
        return newResults;
    }
    
    /**
     * create 'merge' link
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (isLookupToCreateProposal()) {
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
    
    @Override
    public List<Row> getRows() {

        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(USERNAME_FIELD)) {
                    field.setFieldConversions(FIELD_CONVERSIONS);
                }
                if (field.getPropertyName().equals(LOG_STATUS) && isLookupToCreateProposal()) {
                    field.setPropertyValue(STATUS_PENDING);
                }
            }
        }
        return rows;
    }
    
    protected AnchorHtmlData getSelectLinkForProposalCreation(ProposalLog proposalLog) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(DISPLAY_TEXT);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, DOC_HANDLER);
        parameters.put(KRADConstants.PARAMETER_COMMAND, INITIATE);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, INST_PROP_DOC_NAME);
        parameters.put(PROPOSAL_NUMBER, proposalLog.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl(INSTITUTIONAL_PROPOSAL_HOME_ACTION, parameters);

        htmlData.setHref(href);
        return htmlData;
    }
    

    protected AnchorHtmlData getMergeLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(MERGE);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, PAGE_ENTRY);
        parameters.put(PROPOSAL_LOG_NUMBER, proposalNumber);
        String href  = UrlFactory.parameterizeUrl(MERGE_PROPOSAL_LOG_ACTION, parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected AnchorHtmlData getPrintLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(PRINT_LINK);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, PRINT_PROPOSAL_LOG);
        parameters.put(PROPOSAL_NUMBER, proposalNumber);
        String href  = UrlFactory.parameterizeUrl(PRINT_PROPOSAL_LOG_ACTION, parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }

    protected void checkIsLookupForProposalCreation() {
        if (isLookupToCreateProposal()) {
            Person user = GlobalVariables.getUserSession().getPerson();
            // get the authorization
            DocumentAuthorizer documentAuthorizer = getDocumentDictionaryService().getDocumentAuthorizer(INST_PROP_DOC_NAME);
            DocumentPresentationController documentPresentationController = getDocumentDictionaryService().getDocumentPresentationController(INST_PROP_DOC_NAME);
            // make sure this person is authorized to initiate
            LOG.debug("calling canInitiate from getNewDocument()");
            if (!documentPresentationController.canInitiate(INST_PROP_DOC_NAME) ||
                    !documentAuthorizer.canInitiate(INST_PROP_DOC_NAME, user)) {
                throw new DocumentAuthorizationException(user.getPrincipalName(), INITIATE, INST_PROP_DOC_NAME);
            }
        }
    }

    private boolean isLookupToCreateProposal() {
        Map<String, String[]> parameterList = getParameters();
        String[] paramArray = parameterList.get(FOR_INSTITUTIONAL_PROPOSAL);
        return ObjectUtils.isNotNull(paramArray) && Boolean.TRUE.equals(Boolean.parseBoolean(paramArray[0]));
    }

    protected void removeEditLink(List<HtmlData> htmlDataList) {
        int editLinkIndex = -1;
        int currentIndex = 0;
        for (HtmlData htmlData : htmlDataList) {
            if (KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL.equals(htmlData.getMethodToCall())) {
                editLinkIndex = currentIndex;
                break;
            }
            currentIndex++;
        }
        if (editLinkIndex != -1) {
            htmlDataList.remove(editLinkIndex);
        }
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public DocumentDictionaryService getDocumentDictionaryService() {
        return documentDictionaryService;
    }

    public void setDocumentDictionaryService(DocumentDictionaryService documentDictionaryService) {
        this.documentDictionaryService = documentDictionaryService;
    }
    
}
