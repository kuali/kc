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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.person.ProposalPersonService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class is primarily to add/delete proposal/institute attachments. 
 */
@Component("legacyNarrativeService")
public class LegacyNarrativeServiceImpl implements LegacyNarrativeService {

    public static final String NARRATIVE_TYPE = "narrativeType";
    public static final String NARRATIVE_STATUS = "narrativeStatus";
    @Autowired
    @Qualifier("narrativeAuthZService")
    private NarrativeAuthZService narrativeAuthZService;
    @Autowired
    @Qualifier("proposalPersonService")
    private ProposalPersonService proposalPersonService;
    @Autowired
    @Qualifier("systemAuthorizationService")
    private SystemAuthorizationService systemAuthorizationService;
    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;
    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
    @Autowired
    @Qualifier("personService")
    private PersonService personService;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    public Integer getNextModuleNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getDevelopmentProposal().getNarratives();
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments();
        List<Narrative> mergedNarrativeList = new ArrayList<Narrative>();
        mergedNarrativeList.addAll(narrativeList);
        mergedNarrativeList.addAll(instituteAttachmentsList);
        if(mergedNarrativeList.isEmpty()) return 1;
        Collections.sort(mergedNarrativeList, new Comparator<Narrative>(){
            public int compare(Narrative n1, Narrative n2) { 
                return (n1.getModuleNumber()).compareTo(n2.getModuleNumber()); 
              } 
        });
        return mergedNarrativeList.get(mergedNarrativeList.size()-1).getModuleNumber().intValue()+1;
    }
    public Integer getNextModuleSequenceNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getDevelopmentProposal().getNarratives();
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments();
        List<Narrative> mergedNarrativeList = new ArrayList<Narrative>();
        mergedNarrativeList.addAll(narrativeList);
        mergedNarrativeList.addAll(instituteAttachmentsList);
        if(mergedNarrativeList.isEmpty()) return 1;
        Collections.sort(mergedNarrativeList, new Comparator<Narrative>(){
            public int compare(Narrative n1, Narrative n2) { 
                return (n1.getModuleSequenceNumber()).compareTo(n2.getModuleSequenceNumber()); 
              } 
        });
        return mergedNarrativeList.get(mergedNarrativeList.size()-1).getModuleSequenceNumber().intValue()+1;
    }

    /**
     * This method used to add narrative user rights to a narrative.  Each person
     * that is added to a Proposal (see Permission's page) must also be in a
     * narrative's list of users.  For each user that isn't currently associated
     * with the narrative, we will add the user with a default narrative right
     * based upon their permissions.
     * 
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @param narrative the narrative to add the users to
     */  
    protected void populateNarrativeUserRights(ProposalDevelopmentDocument proposalDevelopmentDocument, Narrative narrative) {
        List<KcPerson> persons = getPersons(proposalDevelopmentDocument);
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (KcPerson person : persons) {
            if (!isPersonInNarrativeRights(person, narrativeUserRights)) {
                NarrativeRight narrativeRight = getNarrativeAuthZService().getDefaultNarrativeRight(person.getPersonId(), proposalDevelopmentDocument);
                String personName = person.getFullName();
                NarrativeUserRights narrUserRight = new NarrativeUserRights();
                narrUserRight.setProposalNumber(narrative.getProposalNumber());
                narrUserRight.setModuleNumber(narrative.getModuleNumber());
                narrUserRight.setUserId(person.getPersonId());
                narrUserRight.setAccessType(narrativeRight.getAccessType());
                narrUserRight.setPersonName(personName);
                updateUserTimestamp(narrUserRight);
                narrativeUserRights.add(narrUserRight);
            }
        }
    }

    protected boolean isPersonInNarrativeRights(KcPerson person, List<NarrativeUserRights> narrativeUserRights) {
        for (NarrativeUserRights right : narrativeUserRights) {
            if (StringUtils.equals(right.getUserId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }

    protected List<KcPerson> getPersons(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<Role> proposalRoles = getSystemAuthorizationService().getRoles(RoleConstants.PROPOSAL_ROLE_TYPE);
        
        KcAuthorizationService kcAuthorizationService = getKcAuthorizationService();
        List<KcPerson> allPersons = new ArrayList<KcPerson>();

        for (Role proposalRole : proposalRoles) {
            List<String> users = kcAuthorizationService.getPrincipalsInRole(proposalRole.getName(), proposalDevelopmentDocument);

            List<KcPerson> persons = new ArrayList<KcPerson>();
            for(String userId : users) {
                KcPerson person = getKcPersonService().getKcPersonByPersonId(userId);
                if (person != null && person.getActive()) {
                    persons.add(person);
                }
            }

            for (KcPerson person : persons) {
                if (!isPersonInList(person, allPersons)) {
                    allPersons.add(person);
                }
            }
        }
        return allPersons;
    }

    protected boolean isPersonInList(KcPerson person, List<KcPerson> persons) {
        for (KcPerson p : persons) {
            if (StringUtils.equals(p.getPersonId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }
    
    public void prepareNarrative(ProposalDevelopmentDocument document, Narrative narrative) {
    	narrative.setDevelopmentProposal(document.getDevelopmentProposal());
        narrative.setModuleNumber(getNextModuleNumber(document));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(document));
        narrative.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        narrative.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        getDataObjectService().wrap(narrative).fetchRelationship(NARRATIVE_TYPE);
        getDataObjectService().wrap(narrative).fetchRelationship(NARRATIVE_STATUS);

        populateNarrativeUserRights(document,narrative);
    }

    @Override
    public boolean doesProposalHaveNarrativeType(DevelopmentProposal proposal, NarrativeType narrativeType) {
        for (Narrative narrative : proposal.getNarratives()) {
            if (StringUtils.equals(narrative.getNarrativeType().getCode(), narrativeType.getCode())) {
                return true;
            }
        }
        return false;
    }

    public void populatePersonNameForNarrativeUserRights(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (NarrativeUserRights narrativeUserRight : narrativeUserRights) {
            String personName = getProposalPersonService().getPersonName(proposaldevelopmentDocument, narrativeUserRight.getUserId());
            narrativeUserRight.setPersonName(personName);
        }
    }

    public void populateNarrativeRightsForLoggedinUser(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narrativeList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        }
        
        List<Narrative> instituteAttachmentList = proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments();
        for (Narrative instituteAttachment : instituteAttachmentList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,instituteAttachment);
        }
    }

    protected void updateUserTimestamp(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
    }

    @Override
    public void deletePerson(String userId, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<Narrative> narratives = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
            for (NarrativeUserRights right : userRights) {
                if (StringUtils.equals(right.getUserId(), userId)) {
                    userRights.remove(right);
                    break;
                }
            }
        }
    }

    public void readjustRights(String userId, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames) {
        List<Narrative> narratives = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
            for (NarrativeUserRights right : userRights) {
                if (StringUtils.equals(right.getUserId(), userId)) {
                    String currentAccessType = right.getAccessType();
                    
                    // If the user currently has MODIFY rights, but his/her default (max) right has been down-graded,
                    // then we will down-grade his/her current narrative rights.  Ditto if the user currently has 
                    // VIEW rights.  
                    
                    if (StringUtils.equals(currentAccessType, NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
                        NarrativeRight narrativeRight = getNarrativeAuthZService().getDefaultNarrativeRight(userId, proposalDevelopmentDocument);
                        String accessType = narrativeRight.getAccessType();
                        if (StringUtils.equals(accessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType());
                        }
                        else if (StringUtils.equals(accessType, NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType());
                        }
                    }
                    else if (StringUtils.equals(currentAccessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                        NarrativeRight narrativeRight = getNarrativeAuthZService().getDefaultNarrativeRight(userId, proposalDevelopmentDocument);
                        String accessType = narrativeRight.getAccessType();
                        if (StringUtils.equals(accessType, NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType());
                        }
                    }
                    break;
                }
            }
        }
    }

    
    public void addPerson(String userName, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames) {
        KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
        List<Narrative> narratives = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();

            for (String roleName : roleNames){
                NarrativeRight narrativeRight = getNarrativeAuthZService().getDefaultNarrativeRight(roleName);
                NarrativeUserRights narrUserRight = new NarrativeUserRights();
                narrUserRight.setProposalNumber(narrative.getProposalNumber());
                narrUserRight.setModuleNumber(narrative.getModuleNumber());
                narrUserRight.setUserId(person.getPersonId());
                narrUserRight.setAccessType(narrativeRight.getAccessType());
                narrUserRight.setPersonName(person.getFullName());
                updateUserTimestamp(narrUserRight);
                userRights.add(narrUserRight);
            }
        }
    }
    
    protected KcPersonService getKcPersonService (){return kcPersonService;}

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    protected KcAuthorizationService getKcAuthorizationService(){
        return kcAuthorizationService;
    }
    public void setKcAuthorizationService (KcAuthorizationService kcAuthorizationService){
        this.kcAuthorizationService = kcAuthorizationService;
    }

    protected SystemAuthorizationService getSystemAuthorizationService(){return systemAuthorizationService;}
    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    protected PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public NarrativeAuthZService getNarrativeAuthZService() {
        return narrativeAuthZService;
    }

    public void setNarrativeAuthZService(NarrativeAuthZService narrativeAuthZService) {
        this.narrativeAuthZService = narrativeAuthZService;
    }

    public ProposalPersonService getProposalPersonService() {
        return proposalPersonService;
    }

    public void setProposalPersonService(ProposalPersonService proposalPersonService) {
        this.proposalPersonService = proposalPersonService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
