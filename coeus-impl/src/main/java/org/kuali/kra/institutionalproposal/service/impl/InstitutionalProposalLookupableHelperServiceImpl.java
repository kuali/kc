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
package org.kuali.kra.institutionalproposal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.authorization.InstitutionalProposalDocumentAuthorizer;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

/**
 * This class is used to control behavior of Institutional Proposal lookups. Depending
 * on where the lookup is coming from, we may need to add custom action links and/or
 * filter the lookup results.
 */
public class InstitutionalProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1L;
    
    private static final String MERGE_PROPOSAL_LOG_ACTION = "mergeProposalLog.do";
    private static final String AWARD_HOME_ACTION = "awardHome.do";
    private static final String OPEN = "open";
    private static final String LOOKUP_UNIT_UNIT_NAME = "lookupUnit.unitName";
    private static final String LOOKUP_UNIT_UNIT_NUMBER = "lookupUnit.unitNumber";
    private static final String PROPOSAL_LOG_NUMBER = "proposalLogNumber";
    private static final String INSTITUTIONAL_PROPOSAL_NUMBER = "institutionalProposalNumber";
    private static final String MERGE_TO_INSTITUTIONAL_PROPOSAL = "mergeToInstitutionalProposal";
    private static final String SELECT = "select";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String PROPOSAL_STATE_TYPE_CODE = "proposalStateTypeCode";
    private static final String INST_PROPOSAL_ID = "instProposalId";
    private static final String DEV_PROPOSAL_NUMBER = "devProposalNumber";
    private static final String VIEW_DOCUMENT = "viewDocument";
    private static final String TRUE = "true";
    private static final String DOC_OPENED_FROM_IP_SEARCH = "docOpenedFromIPSearch";
    private static final String DOC_ID = "docId";
    private static final String INSTITUTIONAL_PROPOSAL_HOME_DO = "institutionalProposalHome.do";
    private static final String INSTITUTIONAL_PROPOSAL_DOCUMENT = "InstitutionalProposalDocument";
    private static final String PROJECT_PERSONS_UNITS_UNIT_UNIT_NAME = "projectPersons.units.unit.unitName";
    private static final String PROJECT_PERSONS_UNITS_UNIT_NUMBER = "projectPersons.units.unitNumber";

    private boolean includeMainSearchCustomActionUrls;
    protected String proposalLogNumber;
    private boolean includeMergeCustomActionUrls;
    private InstitutionalProposalService institutionalProposalService;
    
	@Autowired
	@Qualifier("dataObjectService")
	private DataObjectService dataObjectService;

    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<InstitutionalProposal> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
        
        configureCustomActions(fieldValues);
        
        fieldValues.remove(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING);
        fieldValues.put(InstitutionalProposal.PROPOSAL_SEQUENCE_STATUS_PROPERTY_STRING, VersionStatus.ACTIVE.toString());

        final String unitName = fieldValues.get(LOOKUP_UNIT_UNIT_NAME);
        if (StringUtils.isNotEmpty(unitName)) {
            fieldValues.put(PROJECT_PERSONS_UNITS_UNIT_UNIT_NAME, unitName);
        }
        fieldValues.remove(LOOKUP_UNIT_UNIT_NAME);

        final String unitNumber = fieldValues.get(LOOKUP_UNIT_UNIT_NUMBER);
        if (StringUtils.isNotEmpty(unitNumber)) {
            fieldValues.put(PROJECT_PERSONS_UNITS_UNIT_NUMBER, unitNumber);
        }
        fieldValues.remove(LOOKUP_UNIT_UNIT_NUMBER);

        @SuppressWarnings("unchecked")
        final List<InstitutionalProposal> searchResults = (List<InstitutionalProposal>) super.getSearchResults(fieldValues);
      
        if (lookupIsFromAward(fieldValues)) {
            filterAlreadyLinkedProposals(searchResults);
            filterApprovedPendingSubmitProposals(searchResults);
            filterInvalidProposalStatus(searchResults);
        }

        return filterForPermissions(searchResults);
    }

    /**
     * This method filters results so that the person doing the lookup only gets back the documents he can view.
     */
    protected List<InstitutionalProposal> filterForPermissions(List<InstitutionalProposal> results) {
        Person user = GlobalVariables.getUserSession().getPerson();
        InstitutionalProposalDocumentAuthorizer authorizer = new InstitutionalProposalDocumentAuthorizer();
        List<InstitutionalProposal> filteredResults = CollectionUtils.createCorrectImplementationForCollection(results);
        filteredResults.addAll(results.stream().filter(institutionalProposal -> authorizer.canOpen(institutionalProposal.getInstitutionalProposalDocument(), user)).collect(Collectors.toList()));

        return filteredResults;
    }

    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<>();
        if (includeMainSearchCustomActionUrls) {
            htmlDataList.add(getOpenLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument()));
        } 
        if (includeMergeCustomActionUrls) {
            htmlDataList.add(getSelectLink((InstitutionalProposal) businessObject));
        }
        htmlDataList.add(getMedusaLink(((InstitutionalProposal) businessObject).getInstitutionalProposalDocument().getDocumentNumber(), false));
        return htmlDataList;
    }
    
    protected AnchorHtmlData getOpenLink(Document document) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(OPEN);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put(VIEW_DOCUMENT, TRUE);
        parameters.put(DOC_OPENED_FROM_IP_SEARCH, TRUE);
        parameters.put(DOC_ID, document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;

    }


    @Override
    protected String getHtmlAction() {
        return INSTITUTIONAL_PROPOSAL_HOME_DO;
    }
    
    @Override
    protected String getDocumentTypeName() {
        return INSTITUTIONAL_PROPOSAL_DOCUMENT;
    }
    
    @Override
    protected String getKeyFieldName() {
        return InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING;
    }
    
    protected boolean lookupIsFromAward(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KRADConstants.BACK_LOCATION);
        return returnLocation != null && returnLocation.contains(AWARD_HOME_ACTION);
    }
    
    /* 
     * Filters will set flag in IP indicating that they should not be selectable
     * in an Award-based search if they are already linked, if they are Approval Pending Submitted, or if
     * they are not of status 1, 2, or 4. We do this here (instead of in the IP object itself) because
     * this object knows the origin of the lookup, and can easily determine if the IP is already linked.
     */
    /*
     * This method filters out IP's which are already linked to proposals
     */
    protected void filterAlreadyLinkedProposals(List<InstitutionalProposal> searchResults) {
        @SuppressWarnings("unchecked")
        final List<Long> linkedProposals = (List<Long>) GlobalVariables.getUserSession().retrieveObject(Constants.LINKED_FUNDING_PROPOSALS_KEY);

        if (linkedProposals == null) {
            return;
        }

        for (Long linkedProposalId : linkedProposals) {
            for (InstitutionalProposal searchResult : searchResults) {
                if (linkedProposalId.equals(searchResult.getProposalId())) {
                    searchResult.setShowReturnLink(false);
                    break;
                }
            }
        }
    }
    
    /*
     * This method filters out IP's which were generated from PD whose ProposeStateType is "Approval Pending Submitted"
     */
    protected void filterApprovedPendingSubmitProposals(List<InstitutionalProposal> searchResults) {
        searchResults.stream()
                .filter(this::isDevelopmentProposalAppPendingSubmitted)
                .forEach(searchResult -> searchResult.setShowReturnLink(false));
    }

    /**
     * This method is to filter out IP's not having codes in the valid funding proposal status codes parameter.
     **/
    protected void filterInvalidProposalStatus(List<InstitutionalProposal> searchResults) {
        Collection<String> validCodes = getInstitutionalProposalService().getValidFundingProposalStatusCodes();
        searchResults.stream()
                .filter(searchResult -> !validCodes.contains(searchResult.getStatusCode().toString()))
                .forEach(searchResult -> searchResult.setShowReturnLink(false));
    }

    /**
     * Find if any proposal associate with this INSP has 'Approval Pending Submitted' proposal state type
     **/
    protected boolean isDevelopmentProposalAppPendingSubmitted(InstitutionalProposal ip) {
        final List<ProposalAdminDetails> proposalAdminDetails = (List<ProposalAdminDetails>) businessObjectService.findMatchingOrderBy(ProposalAdminDetails.class,
                getFieldValues(INST_PROPOSAL_ID, ip.getProposalId()), DEV_PROPOSAL_NUMBER, true);
        if (!proposalAdminDetails.isEmpty()) {
            final String latestDevelopmentProposalDocNumber = proposalAdminDetails.get(proposalAdminDetails.size() - 1).getDevProposalNumber();

            final QueryByCriteria criteria = QueryByCriteria.Builder.create().setPredicates(equal(PROPOSAL_NUMBER,
                    latestDevelopmentProposalDocNumber), equal(PROPOSAL_STATE_TYPE_CODE, ProposalState.APPROVAL_PENDING_SUBMITTED)).setCountFlag(CountFlag.ONLY).build();

            return dataObjectService.findMatching(DevelopmentProposal.class, criteria).getTotalRowCount() > 0;
        }

        return false;
    }

    protected Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(key, value);
        return fieldValues;
    }

    /* 
     * Determine whether lookup is being called from a location that shouldn't include the custom action links
     */
    protected void configureCustomActions(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get(KRADConstants.BACK_LOCATION);
        if (returnLocation != null) {
            if (returnLocation.contains(AWARD_HOME_ACTION)) {
                includeMainSearchCustomActionUrls = false;
                includeMergeCustomActionUrls = false;
            } else if (returnLocation.contains(MERGE_PROPOSAL_LOG_ACTION)) {
                includeMainSearchCustomActionUrls = false;
                includeMergeCustomActionUrls = true;
                setProposalLogNumber(fieldValues.get(PROPOSAL_LOG_NUMBER));
            } else {
                includeMainSearchCustomActionUrls = true;
                includeMergeCustomActionUrls = false;
            }
        } else {
            includeMainSearchCustomActionUrls = false;
            includeMergeCustomActionUrls = false;
        }
    }
    
    protected AnchorHtmlData getSelectLink(InstitutionalProposal institutionalProposal) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(SELECT);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, MERGE_TO_INSTITUTIONAL_PROPOSAL);
        parameters.put(INSTITUTIONAL_PROPOSAL_NUMBER, institutionalProposal.getProposalNumber());
        if (getProposalLogNumber() != null) {
            parameters.put(PROPOSAL_LOG_NUMBER, getProposalLogNumber());
        }
        String href  = UrlFactory.parameterizeUrl("../" + MERGE_PROPOSAL_LOG_ACTION, parameters);
        htmlData.setHref(href);
        return htmlData;
    }

    @Override
    public boolean isResultReturnable(BusinessObject object) {
        InstitutionalProposal institutionalProposal = (InstitutionalProposal)object;
        return institutionalProposal.getShowReturnLink();
    }
    
    protected InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }
    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }
	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}


    protected void setProposalLogNumber(final String proposalLogNumber) {
        this.proposalLogNumber = proposalLogNumber;
    }

    protected String getProposalLogNumber() {
        return this.proposalLogNumber;
    }
}
