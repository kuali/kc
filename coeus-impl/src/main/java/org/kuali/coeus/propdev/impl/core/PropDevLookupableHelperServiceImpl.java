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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.docperm.DocumentAccess;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.core.api.criteria.AndPredicate;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.document.search.DocumentSearchCriteria;
import org.kuali.rice.kew.api.document.search.DocumentSearchResult;
import org.kuali.rice.kew.api.document.search.DocumentSearchResults;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.permission.Permission;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.kuali.rice.krad.service.impl.LookupCriteriaGenerator;
import org.kuali.rice.krad.uif.element.Link;
import org.kuali.rice.krad.uif.field.FieldGroup;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.kuali.rice.core.api.criteria.PredicateFactory.equal;


@Component("propDevLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PropDevLookupableHelperServiceImpl extends LookupableImpl implements PropDevLookupableHelperService {

    private static final long serialVersionUID = 1L;
    private static final int SMALL_NUMBER_OF_RESULTS = 4;
    private static final int IN_OP_LIMIT = 1000;

    private static final String INITIATOR = "initiator";
    private static final String PRINCIPAL_INVESTIGATOR_NAME = "principalInvestigatorName";
    private static final String PROPOSAL_PERSON = "proposalPerson";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String AGGREGATOR = "aggregator";
    private static final String PARTICIPANT = "participant";

    private static final String PROPOSAL_DOCUMENT_DOCUMENT_NUMBER = "proposalDocument.documentNumber";
    private static final String LAST_NAME = "lastName";
    private static final String PERSON_ID = "personId";
    private static final String USER_NAME = "userName";
    private static final String PROPOSAL_PERSON_ROLE_ID = "proposalPersonRoleId";
    private static final String PRINCIPAL_ID = "principalId";
    private static final String NAMESPACE_CODE = "namespaceCode";
    private static final String ROLE_NAME = "roleName";
    private static final String PROPOSAL_DEVELOPMENT_DOCUMENT = "ProposalDevelopmentDocument";

    public static final String OSP_ADMIN_USERNAME_PATH = "ownedByUnit.unitAdministrators.person.userName";
    public static final String OSP_ADMIN_PERSON_ID_PATH = "ownedByUnit.unitAdministrators.personId";
    public static final String OSP_ADMIN_TYPE_CODE_PATH = "ownedByUnit.unitAdministrators.unitAdministratorTypeCode";
    public static final String OSP_ADMIN_TYPE_CODE_VALUE = "2";



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
    @Qualifier("permissionService")
    private PermissionService permissionService;

    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Override
    protected Collection<?> executeSearch(Map<String, String> adjustedSearchCriteria,
                                          List<String> wildcardAsLiteralSearchCriteria, boolean bounded, Integer searchResultsLimit) {

        if (StringUtils.isNotEmpty(adjustedSearchCriteria.get(OSP_ADMIN_USERNAME_PATH))) {
            Person person = personService.getPersonByPrincipalName(adjustedSearchCriteria.get(OSP_ADMIN_USERNAME_PATH));
            if (person != null) {
                adjustedSearchCriteria.put(OSP_ADMIN_PERSON_ID_PATH, person.getPrincipalId());
                adjustedSearchCriteria.put(OSP_ADMIN_TYPE_CODE_PATH, OSP_ADMIN_TYPE_CODE_VALUE);
            } else {
                return Collections.emptyList();
            }
        }

        Map<String,String> modifiedSearchCriteria = new HashMap<>();
        modifiedSearchCriteria.putAll(adjustedSearchCriteria);

        String proposalNumberCriteria = adjustedSearchCriteria.get(PROPOSAL_NUMBER);
        boolean proposalNumberWildcarded = false;

        if (!StringUtils.isEmpty(proposalNumberCriteria) && proposalNumberCriteria.contains("*")) {
            proposalNumberWildcarded = true;
        }

        List<String> documentNumbers = new ArrayList<>();
        // If a specific proposal is not targeted by the proposal primary key, collect the document numbers of any
        // proposal documents which are associated with the various search person search fields and intersect them
        if (StringUtils.isEmpty(proposalNumberCriteria) || proposalNumberWildcarded) {
            String principalInvestigatorName = adjustedSearchCriteria.get(PRINCIPAL_INVESTIGATOR_NAME);
            String proposalPerson = adjustedSearchCriteria.get(PROPOSAL_PERSON);
            String initiator = adjustedSearchCriteria.get(INITIATOR);
            String participant = adjustedSearchCriteria.get(PARTICIPANT);
            String aggregator = adjustedSearchCriteria.get(AGGREGATOR);

            Collection<String> piProposals = getPiDocumentNumbers(principalInvestigatorName);
            Collection<String> personProposals = getProposalPersonDocumentNumbers(proposalPerson);
            Collection<String> initiatorProposals = getInitiatorDocumentNumbers(initiator);
            Collection<String> aggregatorProposals = getAggregatorDocumentNumbers(aggregator);
            Collection<String> participantProposals = getParticipantDocumentNumbers(participant);

            if (!StringUtils.isEmpty(principalInvestigatorName) && piProposals.isEmpty() ||
                    !StringUtils.isEmpty(proposalPerson) && personProposals.isEmpty() ||
                    !StringUtils.isEmpty(initiator) && initiatorProposals.isEmpty() ||
                    !StringUtils.isEmpty(aggregator) && aggregatorProposals.isEmpty() ||
                    !StringUtils.isEmpty(participant) && participantProposals.isEmpty()) {
                return new ArrayList<>();
            }

            documentNumbers = intersectCollections(piProposals,personProposals,initiatorProposals,aggregatorProposals,participantProposals);

            if (documentNumbers.size() > IN_OP_LIMIT) {
                documentNumbers = documentNumbers.subList(0, IN_OP_LIMIT - 1);
            }
        }

        modifiedSearchCriteria.remove(PROPOSAL_PERSON);
        modifiedSearchCriteria.remove(INITIATOR);
        modifiedSearchCriteria.remove(PRINCIPAL_INVESTIGATOR_NAME);
        modifiedSearchCriteria.remove(PARTICIPANT);
        modifiedSearchCriteria.remove(AGGREGATOR);

        final String hierarchyAwareProposalStatusCode = modifiedSearchCriteria.remove("hierarchyAwareProposalStatus.code");

        QueryByCriteria.Builder query = lookupCriteriaGenerator.generateCriteria(DevelopmentProposal.class, modifiedSearchCriteria,
                wildcardAsLiteralSearchCriteria, getLookupService().allPrimaryKeyValuesPresentAndNotWildcard(DevelopmentProposal.class, modifiedSearchCriteria));
        if (searchResultsLimit != null) {
            query.setMaxResults(searchResultsLimit);
        }

        if ((StringUtils.isBlank(proposalNumberCriteria) || proposalNumberWildcarded)
                && documentNumbers.size() > 0) {
            final Predicate documentNumberPredicate = PredicateFactory.in(PROPOSAL_DOCUMENT_DOCUMENT_NUMBER, documentNumbers);
			if (modifiedSearchCriteria.size() > 0){
                addPredicate(documentNumberPredicate, query);
            }
            else{
                query.setPredicates(documentNumberPredicate);
            }
        }

        final java.util.function.Predicate<DevelopmentProposal> statusCodePredicate = ((java.util.function.Predicate<DevelopmentProposal>) proposal -> StringUtils.isBlank(hierarchyAwareProposalStatusCode))
                .or(proposal -> proposal.getHierarchyAwareProposalStatus().getCode().equals(hierarchyAwareProposalStatusCode));
        
        modifyCriteria(query);

        final List<DevelopmentProposal> proposals = getDataObjectService().findMatching(DevelopmentProposal.class, query.build()).getResults().stream()
                .filter(statusCodePredicate)
                .distinct()
                .collect(Collectors.toList());

        boolean doNotFilter = false;
        if (CollectionUtils.isNotEmpty(proposals) && proposals.size() > SMALL_NUMBER_OF_RESULTS) {
            //if the proposal result list is more than a few proposals then attempt to figure out if a principal
            //has access to all proposals
            doNotFilter = canAccessAllProposals();
        }

        return doNotFilter ? proposals : filterPermissions(proposals);

    }

	protected void addPredicate(final Predicate predicate, QueryByCriteria.Builder query) {
		if (query.getPredicates().length != 0) {
			//special case to work around a bug in Rice where an empty search criteria is a single And predicate with no members
			//and wrapping that empty And and the new predicate together in a new And causes an exception in DoService
			if (query.getPredicates()[0] instanceof AndPredicate && ((AndPredicate)query.getPredicates()[0]).getPredicates().size() == 0) {
				Predicate[] predicates = query.getPredicates();
				predicates[0] = predicate;
				query.setPredicates(predicates);
			} else {
				List<Predicate> predicateList = new ArrayList<>(Arrays.asList(query.getPredicates()));
				predicateList.add(predicate);
				query.setPredicates(PredicateFactory.and(predicateList.toArray(new Predicate[predicateList.size()])));
			}
		} else {
			query.setPredicates(predicate);
		}
	}
    
    protected void modifyCriteria(QueryByCriteria.Builder query) {
    	//noop default implementation
    }

    private Collection<DevelopmentProposal> filterPermissions(Collection<DevelopmentProposal> results) {
        Collection<DevelopmentProposal> filteredResults = new ArrayList<>();
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
        return permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.MODIFY_PROPOSAL, Collections.emptyMap())
                || permissionService.isAuthorized(getGlobalVariableService().getUserSession().getPrincipalId(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,  PermissionConstants.VIEW_PROPOSAL, Collections.emptyMap());
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

    private List<String> getPiDocumentNumbers(String personSearchString) {
        if (StringUtils.isEmpty(personSearchString)) {
            return new ArrayList<>();
        }

        List<String> piProposals = new ArrayList<>();
        Collection<ProposalPerson> proposalPersons = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.fromPredicates(
                PredicateFactory.equal(PROPOSAL_PERSON_ROLE_ID, Constants.PRINCIPAL_INVESTIGATOR_ROLE),
                buildProposalPersonOrPredicate(personSearchString)
        )).getResults();

        for (ProposalPerson person : proposalPersons) {
            piProposals.add(person.getDevelopmentProposal().getDocument().getDocumentNumber());
        }

        return piProposals;
    }

    /**
     * Retrieves all proposal person associated document numbers
     * with the personSearchString given (matches on lastName, principalName, and principalIds).
     * The proposal persons matched can have any role.
     */
    private List<String> getProposalPersonDocumentNumbers(String personSearchString) {
        if (StringUtils.isEmpty(personSearchString)) {
            return new ArrayList<>();
        }

        List<String> personProposals = new ArrayList<>();
        Collection<ProposalPerson> proposalPersons = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.fromPredicates(
                buildProposalPersonOrPredicate(personSearchString)
        )).getResults();

        for (ProposalPerson person : proposalPersons) {
            personProposals.add(person.getDevelopmentProposal().getDocument().getDocumentNumber());
        }

        return personProposals;
    }

    /**
     * Retrieves all "participants" associated document numbers
     * with the personSearchString given (matches on lastName, principalName, and principalIds).
     * ProposalDevelopment participants are aggregators or investigators the PI, COI, or MPI roles.
     */
    private Set<String> getParticipantDocumentNumbers(String personSearchString) {
        if (StringUtils.isEmpty(personSearchString)) {
            return new HashSet<>();
        }

        Set<String> participantProposals = new HashSet<>();

        List<String> propRoles = new ArrayList<>();
        propRoles.add(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        propRoles.add(Constants.CO_INVESTIGATOR_ROLE);
        propRoles.add(Constants.MULTI_PI_ROLE);

        Set<String> principalIds = getMatchingPrincipalIds(personSearchString);

        Collection<ProposalPerson> proposalPersons = new ArrayList<>();
        if (!proposalPersons.isEmpty()) {
            proposalPersons = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.fromPredicates(
                    PredicateFactory.in(PROPOSAL_PERSON_ROLE_ID, propRoles),
                    PredicateFactory.in(PERSON_ID, principalIds)
            )).getResults();
        }

        for (ProposalPerson person : proposalPersons) {
            participantProposals.add(person.getDevelopmentProposal().getDocument().getDocumentNumber());
        }

        List<String> aggregatorDocumentNumbers = getAggregatorDocumentNumbers(personSearchString, principalIds);
        if (aggregatorDocumentNumbers != null) {
            participantProposals.addAll(aggregatorDocumentNumbers);
        }

        return participantProposals;
    }

    private List<String> getAggregatorDocumentNumbers(String personSearchString) {
        return getAggregatorDocumentNumbers(personSearchString, null);
    }

    /**
     * Retrieves all aggregator associated document numbers
     * with the personSearchString given (matches on lastName, principalName, and principalIds).
     * Aggregators retrieved have the AGGREGATOR_DOCUMENT_LEVEL role and proposal development namepace.
     */
    private List<String> getAggregatorDocumentNumbers(String personSearchString, Collection<String> principalIds) {
        if (StringUtils.isEmpty(personSearchString)) {
            return new ArrayList<>();
        }

        if (CollectionUtils.isEmpty(principalIds)) {
            principalIds = getMatchingPrincipalIds(personSearchString);
        }

        Collection<DocumentAccess> accesses = new ArrayList<>();
        if(!principalIds.isEmpty()) {
            accesses = getDataObjectService().findMatching(DocumentAccess.class, QueryByCriteria.Builder.fromPredicates(
                    equal(ROLE_NAME, RoleConstants.AGGREGATOR_DOCUMENT_LEVEL),
                    equal(NAMESPACE_CODE, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT),
                    PredicateFactory.in(PRINCIPAL_ID, principalIds)
            )).getResults();
        }

        List<String> documentNumbers = new ArrayList<>();
        for (DocumentAccess access: accesses) {
            documentNumbers.add(access.getDocumentNumber());
        }

        return documentNumbers;
    }

    /**
     * Gets principal ids which match the search string by principalName, principalId, or lastName.
     */
    private Set<String> getMatchingPrincipalIds(String personSearchString) {
        Set<String> principalIds = new HashSet<>();
        Person person = getPersonService().getPersonByPrincipalName(personSearchString);
        if (person != null) {
            principalIds.add(person.getPrincipalId());
        }

        person = getPersonService().getPerson(personSearchString);
        if (person != null) {
            principalIds.add(person.getPrincipalId());
        }

        Map<String,String> criteria = new HashMap<>();
        criteria.put(LAST_NAME, personSearchString);
        List<Person> persons = getPersonService().findPeople(criteria);
        for (Person p: persons) {
            principalIds.add(p.getPrincipalId());
        }

        return principalIds;
    }

    private List<String> getInitiatorDocumentNumbers(String initiator) {
        if (StringUtils.isEmpty(initiator)) {
            return new ArrayList<>();
        }

        return getProposalDocumentIdsForInitiator(initiator);
    }

    /**
     * Builds the or predicate used to find the person by userName, personId, and lastName for ProposalPerson objects.
     */
    private Predicate buildProposalPersonOrPredicate(String personSearchString) {
        List<Predicate> orPredicates = new ArrayList<>();
        orPredicates.add(PredicateFactory.likeIgnoreCase(USER_NAME, personSearchString));
        orPredicates.add(PredicateFactory.likeIgnoreCase(PERSON_ID, personSearchString));
        orPredicates.add(PredicateFactory.likeIgnoreCase(LAST_NAME, personSearchString));
        return PredicateFactory.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
    }

    /**
     * Gets all initiator proposal development documents by principalName.
     */
    private List<String> getProposalDocumentIdsForInitiator(String initiator) {
        List<String> documentIds = new ArrayList<>();

        DocumentSearchCriteria.Builder builder = DocumentSearchCriteria.Builder.create();
        builder.setInitiatorPrincipalName(initiator);
        builder.setDocumentTypeName(PROPOSAL_DEVELOPMENT_DOCUMENT);
        DocumentSearchResults results = workflowDocumentService.documentSearch(globalVariableService.getUserSession().getPrincipalId(), builder.build());

        for (DocumentSearchResult result : results.getSearchResults()) {
            documentIds.add(result.getDocument().getDocumentId());
        }

        return documentIds;
    }

    /**
     * Intersects all non-empty, non-null collections provided to this method.
     */
    @SafeVarargs
    private final List<String> intersectCollections(Collection<String>... collections) {
        Collection<String> finalCollection = new ArrayList<>();
        for(Collection<String> collection: collections) {
            if (CollectionUtils.isNotEmpty(collection) && CollectionUtils.isNotEmpty(finalCollection)) {
                finalCollection = CollectionUtils.intersection(collection, finalCollection);
            }
            else if (CollectionUtils.isNotEmpty(collection)) {
                // First non-empty/non-null collection starts the intersected collection
                finalCollection = collection;
            }
        }

        return new ArrayList<>(finalCollection);
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
		actionLink.setHref(getDocumentTypeService().getDocumentTypeByName(PROPOSAL_DEVELOPMENT_DOCUMENT).getResolvedDocumentHandlerUrl()
                + "&docId="
                + actionLink.getHref()
                + KRADConstants.DOCHANDLER_URL_CHUNK+"&viewDocument=true");

	}
	
    /**
     * Invoked to build edit action URL for a result row.
     * @param actionLink link that will be used to return the edit action URL
     * @param model lookup form containing the data
     * @param title will be assigned as the href text and title
     */
	@Override
	public void buildPropDevEditActionLink(Link actionLink, Object model,String title) {
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
     */
    @Override
    public void canModifyProposal(FieldGroup fieldGroup, Object model, ProposalDevelopmentDocument document) {
        final boolean canModifyProposal = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), document, PermissionConstants.MODIFY_PROPOSAL);
        final boolean canModifyBudget = getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), document, PermissionConstants.MODIFY_BUDGET);
        if (!canModifyProposal && !canModifyBudget) {
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

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
