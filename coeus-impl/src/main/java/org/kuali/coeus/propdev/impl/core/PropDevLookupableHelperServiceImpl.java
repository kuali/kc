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
package org.kuali.coeus.propdev.impl.core;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.document.search.DocumentSearchCriteria;
import org.kuali.rice.kew.api.document.search.DocumentSearchResult;
import org.kuali.rice.kew.api.document.search.DocumentSearchResults;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.permission.Permission;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.service.impl.LookupCriteriaGenerator;
import org.kuali.rice.krad.uif.element.Link;
import org.kuali.rice.krad.uif.field.FieldGroup;
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
    private static final int SMALL_NUMBER_OF_RESULTS = 4;

    @Autowired
    @Qualifier("kcAuthorizationService") 
    private KcAuthorizationService kcAuthorizationService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("documentTypeService")
    private DocumentTypeService documentTypeService;

    @Autowired
    @Qualifier("kewWorkflowDocumentService")
    private WorkflowDocumentService workflowDocumentService;

    @Autowired
    @Qualifier("lookupCriteriaGenerator")
    private LookupCriteriaGenerator lookupCriteriaGenerator;

    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @Autowired
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;


    @Override
    protected Collection<?> executeSearch(Map<String, String> adjustedSearchCriteria,
                                          List<String> wildcardAsLiteralSearchCriteria, boolean bounded, Integer searchResultsLimit) {

        Map<String,String> modifiedSearchCriteria = new HashMap<String,String>();
        modifiedSearchCriteria.putAll(adjustedSearchCriteria);
        List<String> proposalNumbers = new ArrayList<String>();
        if (StringUtils.isEmpty(adjustedSearchCriteria.get("proposalNumber"))) {
            String principalInvestigatorName = adjustedSearchCriteria.get("principalInvestigatorName");
            String proposalPerson = adjustedSearchCriteria.get("proposalPerson");
            String aggregator = adjustedSearchCriteria.get("aggregator");
            List<String> piProposals = getPiProposalNumbers(principalInvestigatorName);
            List<String> personProposals = getPersonProposalNumbers(proposalPerson);
            List<String> aggregatorProposals = getAggregatorProposalNumbers(aggregator);
            proposalNumbers = combineProposalNumbers(piProposals,personProposals,aggregatorProposals);

        }
        modifiedSearchCriteria.remove("proposalPerson");
        modifiedSearchCriteria.remove("aggregator");
        modifiedSearchCriteria.remove("principalInvestigatorName");


        QueryByCriteria.Builder query = lookupCriteriaGenerator.generateCriteria(DevelopmentProposal.class, modifiedSearchCriteria,
                wildcardAsLiteralSearchCriteria, getLookupService().allPrimaryKeyValuesPresentAndNotWildcard(DevelopmentProposal.class, modifiedSearchCriteria));
        if (!bounded && searchResultsLimit != null) {
            query.setMaxResults(searchResultsLimit);
        }
        if (StringUtils.isBlank(adjustedSearchCriteria.get("proposalNumber"))
                && proposalNumbers.size() > 0) {
            if (modifiedSearchCriteria.size() > 0){
                List<Predicate> predicateList = new ArrayList(Arrays.asList(query.getPredicates()));
                predicateList.add(PredicateFactory.in("proposalNumber", proposalNumbers));
                query.setPredicates(PredicateFactory.and(predicateList.toArray(new Predicate[predicateList.size()])));
            }
            else{
                query.setPredicates(PredicateFactory.in("proposalNumber", proposalNumbers));
            }
        }
        final List<DevelopmentProposal> proposals = getDataObjectService().findMatching(DevelopmentProposal.class, query.build()).getResults();

        boolean doNotFilter = false;
        if (CollectionUtils.isNotEmpty(proposals) && proposals.size() > SMALL_NUMBER_OF_RESULTS) {
            //if the proposal result list is more than a few proposals then attempt to figure out if a principal
            //has access to all proposals
            doNotFilter = canAccessAllProposals();
        }

        return doNotFilter ? proposals : filterPermissions(proposals);

    }

    private Collection<DevelopmentProposal> filterPermissions(Collection<DevelopmentProposal> results) {
        Collection<DevelopmentProposal> filteredResults = new ArrayList<DevelopmentProposal>();
        for (DevelopmentProposal developmentProposal : results) {
            if (getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),
                    developmentProposal.getDocument(),PermissionConstants.VIEW_PROPOSAL)){
                filteredResults.add(developmentProposal);
            }
        }
        return  filteredResults;
    }

    protected boolean canAccessAllProposals() {
        return hasPermissionWithNoUnit() || hasPermissionTopUnitWithDescends() || hasPermissionWithWildcardUnit();
    }

    protected boolean hasPermissionWithNoUnit() {
        return permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.MODIFY_PROPOSAL, Collections.<String, String>emptyMap())
                || permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.VIEW_PROPOSAL, Collections.<String, String>emptyMap());
    }

    protected boolean hasPermissionWithWildcardUnit() {
        final Map<String, String> qualifiers = new HashedMap<>();
        qualifiers.put(KcKimAttributes.UNIT_NUMBER, "*");
        return containsWildcardAttribute(permissionService.getAuthorizedPermissions(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.MODIFY_PROPOSAL, qualifiers), KcKimAttributes.UNIT_NUMBER)
                || containsWildcardAttribute(permissionService.getAuthorizedPermissions(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.VIEW_PROPOSAL, qualifiers), KcKimAttributes.UNIT_NUMBER);
    }

    private boolean containsWildcardAttribute(Collection<Permission> perms, String attrName) {
        if (CollectionUtils.isNotEmpty(perms)) {
            for (Permission perm : perms) {
                if (MapUtils.isNotEmpty(perm.getAttributes())) {
                    final String attrVal = perm.getAttributes().get(attrName);
                    if ("*".equals(attrVal)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean hasPermissionTopUnitWithDescends() {
        final Unit top = unitService.getTopUnit();
        final Map<String, String> qualifiers = new HashedMap<>();
        qualifiers.put(KcKimAttributes.UNIT_NUMBER, top.getUnitNumber());
        qualifiers.put(KcKimAttributes.SUBUNITS, "Y");
        return permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.MODIFY_PROPOSAL, qualifiers)
                || permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.VIEW_PROPOSAL, qualifiers);
    }

    private List<String> getPiProposalNumbers(String principalInvestigatorName) {
        List<String> piProposals = new ArrayList<String>();
        if (StringUtils.isNotEmpty(principalInvestigatorName)) {
            Map<String,String> criteria = new HashMap<String,String>();
            criteria.put("fullName",principalInvestigatorName);
            criteria.put("proposalPersonRoleId", Constants.PRINCIPAL_INVESTIGATOR_ROLE);
            QueryByCriteria.Builder query = lookupCriteriaGenerator.generateCriteria(ProposalPerson.class, criteria,
                   new ArrayList<String>(), false);
            List<ProposalPerson> principalInvestigators = getDataObjectService().findMatching(ProposalPerson.class, query.build()).getResults();
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
            QueryByCriteria.Builder query = lookupCriteriaGenerator.generateCriteria(ProposalPerson.class, Collections.singletonMap("fullName",proposalPerson),
                    new ArrayList<String>(), false);
            List<ProposalPerson> proposalPersons = getDataObjectService().findMatching(ProposalPerson.class,query.build()).getResults();
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

        DocumentSearchCriteria.Builder builder = DocumentSearchCriteria.Builder.create();
        builder.setInitiatorPrincipalName(aggregator);
        builder.setDocumentTypeName("ProposalDevelopmentDocument");
        DocumentSearchResults results = workflowDocumentService.documentSearch(globalVariableService.getUserSession().getPrincipalId(), builder.build());

        for (DocumentSearchResult result : results.getSearchResults()) {
            documentIds.add(result.getDocument().getDocumentId());
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
     * Invoked to build view action URL for a result row.
     * @param actionLink link that will be used to return the view action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     */
	@Override
	public void buildPropDevViewActionLink(Link actionLink, Object model, String title) throws WorkflowException {
		actionLink.setTitle(title);
		actionLink.setLinkText(title);
		actionLink.setHref(getDocumentTypeService().getDocumentTypeByName("ProposalDevelopmentDocument").getResolvedDocumentHandlerUrl()
                + "&docId="
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
			actionLink.setTitle(title);
			actionLink.setLinkText(title);
			actionLink.setHref(getConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY)
	                + KRADConstants.DOCHANDLER_DO_URL
	                + actionLink.getHref()
	                + KRADConstants.DOCHANDLER_URL_CHUNK);

	}

    /**
     * Check to see if the modify action should be rendered (must have modify permission).
     *
     * @param fieldGroup link that will be used to return the copy action
     * @param model lookup form containing the data
     * @param document the document to check
     * @throws WorkflowException
     */
    public void canModifyProposal(FieldGroup fieldGroup, Object model, ProposalDevelopmentDocument document) throws WorkflowException {
        final boolean canModifyProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), document, PermissionConstants.MODIFY_PROPOSAL);
        if (!canModifyProposal) {
            fieldGroup.setRender(false);
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

    public DocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public WorkflowDocumentService getWorkflowDocumentService() {
        return workflowDocumentService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    public LookupCriteriaGenerator getLookupCriteriaGenerator() {
        return lookupCriteriaGenerator;
    }

    public void setLookupCriteriaGenerator(LookupCriteriaGenerator lookupCriteriaGenerator) {
        this.lookupCriteriaGenerator = lookupCriteriaGenerator;
    }

    public LookupService getLookupService() {
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
}
