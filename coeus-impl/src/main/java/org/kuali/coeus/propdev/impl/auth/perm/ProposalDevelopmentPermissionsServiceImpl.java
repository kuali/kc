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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
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
    
    private static final String PARAMETER_DELIMITER = ",";
    private static final String SPONSOR_HEIRARCHY= "COIHierarchyName";
    private static final String COI_SPONSOR_HEIRARCHY_LEVEL1= "COIHierarchyLevel1";
    public static final String KEY_PERSON_PROJECT_ROLE = "keyPersonProjectRole";
    public static final String COI_REQUIREMENT = "COI_REQUIREMENT";
    public static final String PCK = "PCK";

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
        for (String roleName :roleNames) {
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
        for (String roleName :roleNames) {
            kraAuthorizationService.addDocumentLevelRole(getPersonId(proposalUser.getUsername()), roleName, doc);
        }
    }

    public boolean hasCertificationPermissions(ProposalDevelopmentDocument document, Person user,ProposalPerson proposalPerson){
        if(proposalPerson.getPerson()==null){
     	   return false;
        }
        boolean isPiLoggedIn = isPiPersonLoggedInUser( proposalPerson.getDevelopmentProposal(),user);
        boolean canCertifyProposal = canCertifyProposal(document,user);
        if((isPiLoggedIn && proposalPerson.getPersonId().equals(user.getPrincipalId())) ||
     		   (canCertifyProposal && proposalPerson.isPrincipalInvestigator()))
        {
     	  return true;
        }
       if ((proposalPerson.getPersonId().equals(user.getPrincipalId()) && proposalPerson.isCoInvestigator())
     		  ||(isPiLoggedIn && proposalPerson.isCoInvestigator()) ||
     		  (canCertifyProposal && proposalPerson.isCoInvestigator())){
     		  return true;
       }
       if ((proposalPerson.getPersonId().equals(user.getPrincipalId()) && proposalPerson.isMultiplePi())
     		  ||(isPiLoggedIn && proposalPerson.isMultiplePi()) ||
     		  (canCertifyProposal && proposalPerson.isMultiplePi())){
     		  return true;
       }
       
       String keyPersonProjectRoles = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KEY_PERSON_PROJECT_ROLE);
       List<String> keyPersonRoleList =Arrays.asList(keyPersonProjectRoles.split(PARAMETER_DELIMITER));


        if ((proposalPerson.getPersonId().equals(user.getPrincipalId()) && proposalPerson.isKeyPerson()) ||
                (isPiLoggedIn && proposalPerson.isKeyPerson()) ||
                (canCertifyProposal && proposalPerson.isKeyPerson())) {
            for (String projectRole : keyPersonRoleList) {
                if (proposalPerson.getProjectRole().equals(projectRole)) {
                    return false;
                }
            }
            if (isKeyPersonCustomData(proposalPerson.getDevelopmentProposal())) {
                return true;
            }

            String sponsorHeirarchy = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, SPONSOR_HEIRARCHY);
            String sponsorHeirarchyLevelName = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, COI_SPONSOR_HEIRARCHY_LEVEL1);
            if (getSponsorHierarchyService().isSponsorInHierarchy(proposalPerson.getDevelopmentProposal().getSponsorCode(), sponsorHeirarchy, 1, sponsorHeirarchyLevelName)) {
                return true;
            }
        }

        return false;

    }
    
    private boolean canCertifyProposal(ProposalDevelopmentDocument document,Person user){
        return getKraAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.VIEW_CERTIFICATION)
                || getKraAuthorizationService().hasPermission(user.getPrincipalId(), document, PermissionConstants.CERTIFY);
   }
   

   private boolean isPiPersonLoggedInUser(DevelopmentProposal developmentProposal,Person user){
       final ProposalPerson person = developmentProposal.getPrincipalInvestigator();
       return person != null && StringUtils.equals(user.getPrincipalId(), person.getPersonId());
   }
   
   private boolean isKeyPersonCustomData(
			DevelopmentProposal developmentProposal) {
		try {
			List<CustomAttributeDocValue> customDataList = developmentProposal
					.getProposalDocument().getCustomDataList();
			for (CustomAttributeDocValue attributeDocValue : customDataList) {
				if (attributeDocValue.getCustomAttribute().getName().equalsIgnoreCase(COI_REQUIREMENT)&& attributeDocValue.getValue().equals(PCK)) {
					return true;
				}
			}
		} catch (Exception exception) {
            LOG.warn(exception.getMessage(),exception);
		}
		return false;
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
