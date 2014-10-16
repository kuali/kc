package org.kuali.coeus.propdev.impl.core;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.impl.document.search.DocumentSearchCriteriaBo;
import org.kuali.rice.kew.impl.document.search.DocumentSearchCriteriaBoLookupableHelperService;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Link;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("propDevLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PropDevLookupableHelperServiceImpl extends LookupableImpl implements PropDevLookupableHelperService {
	
	private static final long serialVersionUID = 1L;
	
    @Autowired
    @Qualifier("kcAuthorizationService") 
    private KcAuthorizationService kcAuthorizationService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    private DocumentSearchCriteriaBoLookupableHelperService documentSearchCriteriaBoLookupableHelperService;

    @Override
    protected Collection<?> executeSearch(Map<String, String> adjustedSearchCriteria,
                                          List<String> wildcardAsLiteralSearchCriteria, boolean bounded, Integer searchResultsLimit) {

        Map<String,Object> modifiedSearchCriteria = new HashMap<String,Object>();
        modifiedSearchCriteria.putAll(adjustedSearchCriteria);

        if (StringUtils.isEmpty(adjustedSearchCriteria.get("proposalNumber"))) {
            String principalInvestigatorName = adjustedSearchCriteria.get("principalInvestigatorName");
            String proposalPerson = adjustedSearchCriteria.get("proposalPerson");
            String aggregator = adjustedSearchCriteria.get("aggregator");
            List<String> piProposals = getPiProposalNumbers(principalInvestigatorName);
            List<String> personProposals = getPersonProposalNumbers(proposalPerson);
            List<String> aggregatorProposals = getAggregatorProposalNumbers(aggregator);
            List<String> proposalNumbers = combineProposalNumbers(piProposals,personProposals,aggregatorProposals);

            if ((StringUtils.isNotEmpty(principalInvestigatorName) || StringUtils.isNotEmpty(proposalPerson) || StringUtils.isNotEmpty(aggregator))
                    && CollectionUtils.isEmpty(proposalNumbers)) {
                modifiedSearchCriteria.put("proposalNumber",null);
            } else {
                modifiedSearchCriteria.put("proposalNumber",proposalNumbers);
            }
        }
        modifiedSearchCriteria.remove("proposalPerson");
        modifiedSearchCriteria.remove("aggregator");
        modifiedSearchCriteria.remove("principalInvestigatorName");

        return getDataObjectService().findMatching(DevelopmentProposal.class,QueryByCriteria.Builder.andAttributes(modifiedSearchCriteria).build()).getResults();

    }

    private List<String> getPiProposalNumbers(String principalInvestigatorName) {
        List<String> piProposals = new ArrayList<String>();
        if (StringUtils.isNotEmpty(principalInvestigatorName)) {
            List<ProposalPerson> principalInvestigators = getDataObjectService().findMatching(ProposalPerson.class,QueryByCriteria.Builder.andAttributes(Collections.singletonMap("fullName",principalInvestigatorName)).build()).getResults();
            for (ProposalPerson pi : principalInvestigators) {
                piProposals.add(pi.getProposalNumber());
            }
            if (CollectionUtils.isEmpty(piProposals)) {
                piProposals.add("");
            }
        }
        return piProposals;
    }

    private List<String> getPersonProposalNumbers(String proposalPerson) {
        List<String> personProposals = new ArrayList<String>();
        if (StringUtils.isNotEmpty(proposalPerson)) {
            List<ProposalPerson> proposalPersons = getDataObjectService().findMatching(ProposalPerson.class,QueryByCriteria.Builder.andAttributes(Collections.singletonMap("fullName",proposalPerson)).build()).getResults();
            for (ProposalPerson person : proposalPersons) {
                personProposals.add(person.getProposalNumber());
            }
            if (CollectionUtils.isEmpty(personProposals)) {
                personProposals.add("");
            }
        }
        return personProposals;
    }

    private List<String> getAggregatorProposalNumbers(String aggregator) {
        List<String> aggregatorProposals = new ArrayList<String>();
        if (StringUtils.isNotEmpty(aggregator)) {
            List<String> documentIds = getDocumentIds(aggregator);
            if (CollectionUtils.isNotEmpty(documentIds)) {
                QueryByCriteria queryByCriteria = QueryByCriteria.Builder.andAttributes(Collections.singletonMap("documentNumber",documentIds)).build();

                List<ProposalDevelopmentDocument> documents = getDataObjectService().findMatching(ProposalDevelopmentDocument.class,queryByCriteria).getResults();
                for (ProposalDevelopmentDocument document : documents) {
                    aggregatorProposals.add(document.getDevelopmentProposal().getProposalNumber());
                }
            }
             if (CollectionUtils.isEmpty(aggregatorProposals)){
                aggregatorProposals.add("");
            }
        }
        return aggregatorProposals;
    }

    private List<String> getDocumentIds(String aggregator) {
        List<String> documentIds = new ArrayList<String>();
        Map<String,String> fieldValues = new HashMap<String,String>();
        fieldValues.put("initiatorPrincipalName",aggregator);
        fieldValues.put("documentTypeName","ProposalDevelopmentDocument");
        getDocumentSearchCriteriaBoLookupableHelperService().setParameters(new HashMap<String, String[]>());
        getDocumentSearchCriteriaBoLookupableHelperService().setBusinessObjectClass(DocumentSearchCriteriaBo.class);

        List<DocumentSearchCriteriaBo> criteriaBos = (List<DocumentSearchCriteriaBo>) getDocumentSearchCriteriaBoLookupableHelperService().getSearchResults(fieldValues);
        for (DocumentSearchCriteriaBo criteriaBo : criteriaBos) {
            documentIds.add(criteriaBo.getDocumentId());
        }
        return documentIds;
    }

    private List<String> combineProposalNumbers(List<String> piProposals,List<String> personProposals ,List<String> aggregatorProposals) {
        List<String> proposalNumbers = new ArrayList<String>();
        List<String> tmpProposalNumbers = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(piProposals) && CollectionUtils.isNotEmpty(personProposals)) {
            tmpProposalNumbers = (List<String>)CollectionUtils.intersection(piProposals, personProposals);
        } else if (CollectionUtils.isNotEmpty(piProposals)) {
            tmpProposalNumbers = piProposals;
        } else if (CollectionUtils.isNotEmpty(personProposals)){
            tmpProposalNumbers = personProposals;
        }

        if (CollectionUtils.isNotEmpty(tmpProposalNumbers) && CollectionUtils.isNotEmpty(aggregatorProposals)) {
            proposalNumbers = (List<String>)CollectionUtils.intersection(piProposals, personProposals);
        } else if (CollectionUtils.isNotEmpty(tmpProposalNumbers)) {
            proposalNumbers = tmpProposalNumbers;
        } else if (CollectionUtils.isNotEmpty(aggregatorProposals)){
            proposalNumbers = aggregatorProposals;
        }

        return proposalNumbers;
    }
	/**
	 * This method determines Action URLs to be displayed for current search result line
	 * Default implementation is to return true
	 * @param Object dataObject current line item from the search result collection
	 */
    @Override
	public boolean showActionUrls(Collection<?> lookupResults) {
		
		boolean canViewProposal = false;
		Object dataObject = null;
		if(lookupResults != null && lookupResults.size() > 0) {
			dataObject = lookupResults.iterator().next();
		}
		if(dataObject != null && dataObject instanceof DevelopmentProposal) {
			DevelopmentProposal developmentProposal = (DevelopmentProposal)dataObject;
			canViewProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),
					developmentProposal.getDocument(), PermissionConstants.VIEW_PROPOSAL);
		}
	    return canViewProposal;
	}

    /**
     * Invoked to build view action URL for a result row.
     * @param actionLink link that will be used to return the view action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     */
	@Override
	public void buildPropDevViewActionLink(Link actionLink, Object model, String title) {
		actionLink.setTitle(title);
		actionLink.setLinkText(title);
		actionLink.setHref(getConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY)
                + KRADConstants.DOCHANDLER_DO_URL
                + actionLink.getHref()
                + KRADConstants.DOCHANDLER_URL_CHUNK+"&viewDocument=true");
		
	}
	
    /**
     * Invoked to build edit action URL for a result row.
     * @param actionLink link that will be used to return the edit action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     * @throws WorkflowException 
     */
	@Override
	public void buildPropDevEditActionLink(Link actionLink, Object model,String title) throws WorkflowException {
		
		boolean canModifyProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), 
				(ProposalDevelopmentDocument)(getDocumentService().getByDocumentHeaderId(actionLink.getHref())), 
				PermissionConstants.MODIFY_PROPOSAL);
		if(canModifyProposal) {
			actionLink.setTitle(title);
			actionLink.setLinkText(title);
			actionLink.setHref(getConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY)
	                + KRADConstants.DOCHANDLER_DO_URL
	                + actionLink.getHref()
	                + KRADConstants.DOCHANDLER_URL_CHUNK);
		}
	}

    /**
     * Check to see if the copy action should be rendered (must have modify permission).
     *
     * @param actionLink link that will be used to return the copy action
     * @param model lookup form containing the data
     * @param docId the id of document to check
     * @throws WorkflowException
     */
    public void buildPropDevCopyActionLink(Action actionLink, Object model, String docId) throws WorkflowException {
        boolean canModifyProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),
        				(ProposalDevelopmentDocument)(getDocumentService().getByDocumentHeaderId(docId)),
        				PermissionConstants.MODIFY_PROPOSAL);
        if (!canModifyProposal) {
            actionLink.setRender(false);
        }
    }

	public KcAuthorizationService getKcAuthorizationService() {
		return kcAuthorizationService;
	}

	public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
		this.kcAuthorizationService = kcAuthorizationService;
	}


	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}


	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

    public DocumentSearchCriteriaBoLookupableHelperService getDocumentSearchCriteriaBoLookupableHelperService() {
        if (documentSearchCriteriaBoLookupableHelperService == null) {
            documentSearchCriteriaBoLookupableHelperService = KcServiceLocator.getService(DocumentSearchCriteriaBoLookupableHelperService.class);
        }

        return documentSearchCriteriaBoLookupableHelperService;
    }

    public void setDocumentSearchCriteriaBoLookupableHelperService(DocumentSearchCriteriaBoLookupableHelperService documentSearchCriteriaBoLookupableHelperService) {
        this.documentSearchCriteriaBoLookupableHelperService = documentSearchCriteriaBoLookupableHelperService;
    }
}
