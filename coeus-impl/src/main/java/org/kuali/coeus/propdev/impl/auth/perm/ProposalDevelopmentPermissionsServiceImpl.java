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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.coi.CoiConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.docperm.*;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.KualiRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("proposalDevelopmentPermissionsService")
public class ProposalDevelopmentPermissionsServiceImpl implements ProposalDevelopmentPermissionsService {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentPermissionsServiceImpl.class);

    public static final String COI_REQUIREMENT = "COI_REQUIREMENT";
    public static final String PRINCIPAL_COI_KEY_PERSON = "PCK";
    public static final int HIERARCHY_LEVEL = 1;
    private static final String PARAMETER_DELIMITER = "\\s*,\\s*";
    private static final String ENABLE_ADDRESSBOOK_CERTIFICATION = "ENABLE_ADDRESSBOOK_CERTIFICATION";
    private static final String EXEMPT_ADDRESSBOOK_MULTI_PI_CERT = "EXEMPT_ADDRESSBOOK_MULTI_PI_CERT";

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService narrativeService;

    @Autowired
    @Qualifier("proposalRoleService")
    private ProposalRoleService proposalRoleService;

    @Override
    public List<ProposalUserRoles> getPermissions(ProposalDevelopmentDocument document) {
        Map<String, ProposalUserRoles> pendingRoleMap = new TreeMap<String, ProposalUserRoles>();

        // Add persons into the ProposalUserRolesList for each of the roles.
        Collection<Role> roles = proposalRoleService.getRolesForDisplay();
        for (Role role : roles) {
            List<String> personIds = kraAuthorizationService.getPrincipalsInRole(role.getName(), document);
            for (String personId : personIds) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(personId);
                if (person != null) {
                    ProposalUserRoles proposalUserRole = pendingRoleMap.get(person.getUserName());
                    if (proposalUserRole != null) {
                        proposalUserRole.addRoleName(role.getName());
                    } else {
                        ProposalUserRoles newRole = new ProposalUserRoles();
                        newRole.setUsername(person.getUserName());
                        newRole.setFullname(person.getFullName());
                        newRole.addRoleName(role.getName());
                        pendingRoleMap.put(person.getUserName(), newRole);
                    }
                } else {
                    LOG.error("Attempting to get roles for null user role!");
                }
            }
        }
        return new ArrayList<>(pendingRoleMap.values());
    }

    @Override
    public void savePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> persistedUsers,
                                List<ProposalUserRoles> newUsers) {
        List<ProposalUserRoles> proposalUsersToDelete = new ArrayList<ProposalUserRoles>(persistedUsers);
        proposalUsersToDelete.removeAll(newUsers);
        for (ProposalUserRoles proposalUser : proposalUsersToDelete) {
            deleteProposalUser(proposalUser, document);
        }

        List<ProposalUserRoles> proposalUsersToAdd = new ArrayList<ProposalUserRoles>(newUsers);
        proposalUsersToAdd.removeAll(persistedUsers);
        for (ProposalUserRoles proposalUser : proposalUsersToAdd) {
            saveProposalUser(proposalUser, document);
        }

    }

    public void deleteProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc) {
        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName : roleNames) {
            kraAuthorizationService.removeDocumentLevelRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }

    protected String getPersonId(String username) {
        Person person = personService.getPersonByPrincipalName(username);
        return person.getPrincipalId();
    }

    public void saveProposalUser(ProposalUserRoles proposalUser, ProposalDevelopmentDocument doc) {
        // Assign the user to the new roles for the proposal.

        List<String> roleNames = proposalUser.getRoleNames();
        for (String roleName : roleNames) {
            kraAuthorizationService.addDocumentLevelRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }

    protected boolean isLoggedInUserPi(DevelopmentProposal developmentProposal, Person user) {
        final ProposalPerson person = developmentProposal.getPrincipalInvestigator();
        return person != null && StringUtils.equals(user.getPrincipalId(), person.getPersonId());
    }

    public boolean hasCertificationPermissions(ProposalDevelopmentDocument document, Person user, ProposalPerson proposalPerson) {
        return canCertify(user.getPrincipalId(), proposalPerson, canProxyCertify(document, user));
    }

    public boolean doesPersonRequireCertification(ProposalPerson person) {
        if (person.getPerson() == null) {
        	return canCertifyAddressBookPerson(person);
        }
        if (!person.isKeyPerson()) return true;
        return isRoleCustomDataOrSponsorExempt(person);
    }

    protected boolean canCertify(String userPrincipalId, ProposalPerson proposalPerson, boolean canProxyCertify) {
        // person is null for rolodex entries
        if (Objects.isNull(proposalPerson.getPerson())) {
            return canCertifyAddressBookPerson(proposalPerson) ? canProxyCertify : false;
        }

        final boolean keyPersonOrPiOrProxyCertificationPossible = canProxyCertify || proposalPersonIsUser(userPrincipalId, proposalPerson);

        if (keyPersonOrPiOrProxyCertificationPossible && proposalPerson.isKeyPerson()) {
            if (isCoiDisclosureStatusFeatureEnabled()) {
                return isRoleCustomDataOrSponsorExempt(proposalPerson);
            }
            else {
                return true;
            }
        }
        return keyPersonOrPiOrProxyCertificationPossible;
    }
    
    protected Boolean canCertifyAddressBookPerson(ProposalPerson proposalPerson) {
		if (!isRolodexCertificationEnabled()) {
			return false;
		}
		if (proposalPerson.isMultiplePi()) {
			return !isAddressBookMultiPiCertificationExempt(proposalPerson);
		}
		if (proposalPerson.isKeyPerson()) {
			return isRoleCustomDataOrSponsorExempt(proposalPerson);
		}
		return true;
	}

    protected Boolean isRoleCustomDataOrSponsorExempt(ProposalPerson proposalPerson) {
        if (isKeyPersonRoleExempt(proposalPerson)) return false;
        if (isPiCoiKeyPersonsForcedToDiscloseWithCustomData(proposalPerson.getDevelopmentProposal())) return true;
        if (doesSponsorRequireKeyPersonCertification(proposalPerson)) return true;
        return false;
    }

    public boolean isAddressBookMultiPiCertificationExempt(ProposalPerson proposalPerson) {
    	if (proposalPerson.isMultiplePi()) {
    		return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, EXEMPT_ADDRESSBOOK_MULTI_PI_CERT);
    	}
    	return false;
    }
    
    public boolean isRolodexCertificationEnabled() {
    	return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, ENABLE_ADDRESSBOOK_CERTIFICATION);
    }
    
    public boolean isKeyPersonRoleExempt(ProposalPerson proposalPerson) {
        return getExemptKeyPersonRoles().stream().anyMatch(projectRole -> projectRole.equalsIgnoreCase(proposalPerson.getProjectRole()));
    }

    public boolean doesSponsorRequireKeyPersonCertification(ProposalPerson proposalPerson) {
        String sponsorHierarchy = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, CoiConstants.COI_SPONSOR_HIERARCHY);
        String sponsorHierarchyLevelName = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, CoiConstants.COI_SPONSOR_HEIRARCHY_LEVEL1);

        return getSponsorHierarchyService().isSponsorInHierarchy(proposalPerson.getDevelopmentProposal().getSponsorCode(),
                                                                sponsorHierarchy, HIERARCHY_LEVEL, sponsorHierarchyLevelName);
    }

    protected List<String> getExemptKeyPersonRoles() {
        String keyPersonProjectRoles = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, ProposalDevelopmentConstants.Parameters.KEY_PERSON_PROJECT_ROLE);
        List<String> keyPersonRoleList = Arrays.asList(keyPersonProjectRoles.split(PARAMETER_DELIMITER));
        return keyPersonRoleList;

    }

    protected boolean proposalPersonIsUser(String userPrincipalId, ProposalPerson proposalPerson) {
        return proposalPerson.getPersonId().equals(userPrincipalId);
    }

   protected boolean canProxyCertify(ProposalDevelopmentDocument document, Person user) {
        return getKraAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.CERTIFY);
   }

   // Aggregators can use custom data to choose if PCK should disclose.
   public boolean isPiCoiKeyPersonsForcedToDiscloseWithCustomData(DevelopmentProposal developmentProposal) {
		try {
			List<CustomAttributeDocValue> customDataList = developmentProposal.getProposalDocument().getCustomDataList();
			for (CustomAttributeDocValue attributeDocValue : customDataList) {
				if (attributeDocValue.getCustomAttribute().getName().equalsIgnoreCase(COI_REQUIREMENT) &&
                        PRINCIPAL_COI_KEY_PERSON.equals(attributeDocValue.getValue())) {
					return true;
				}
			}
		} catch (Exception exception) {
            LOG.warn(exception.getMessage(),exception);
		}
		return false;
	}

    protected boolean isCoiDisclosureStatusFeatureEnabled() {
        return getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                                                                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                                                                Constants.PROP_PERSON_COI_STATUS_FLAG);
    }

    @Override
    public boolean validateAddPermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser){
        return getKualiRuleService().applyRules(new AddProposalUserEvent(document, proposalUserRolesList, proposalUser));
    }

    @Override
    public boolean validateDeletePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, int index){
        return getKualiRuleService().applyRules(new DeleteProposalUserEvent(document, proposalUserRolesList, index));
    }

    @Override
    public boolean validateUpdatePermissions(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUserRolesList, ProposalUserRoles proposalUser){
        return getKualiRuleService().applyRules(new EditUserProposalRolesEvent(document, proposalUserRolesList, proposalUser));
    }

    @Override
    public void processAddPermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().addPerson(proposalUser.getUsername(), document, proposalUser.getRoleNames());
    }

    @Override
    public void processDeletePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().deletePerson(getPersonService().getPersonByPrincipalName(proposalUser.getUsername()).getPrincipalId(), document);
    }

    @Override
    public void processUpdatePermission(ProposalDevelopmentDocument document, ProposalUserRoles proposalUser) {
        getNarrativeService().readjustRights(getPersonId(proposalUser.getUsername()), document, proposalUser.getRoleNames());
    }

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public LegacyNarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public ProposalRoleService getProposalRoleService() {
        return proposalRoleService;
    }

    public void setProposalRoleService(ProposalRoleService proposalRoleService) {
        this.proposalRoleService = proposalRoleService;
    }
    
    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService (){
    	return parameterService;
    }
}
