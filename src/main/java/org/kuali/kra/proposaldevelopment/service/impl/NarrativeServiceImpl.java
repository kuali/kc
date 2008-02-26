/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.service.PersonService;

/**
 * This class is primarily to add/delete proposal/institute attachments. 
 */
public class NarrativeServiceImpl implements NarrativeService {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NarrativeServiceImpl.class);
    
    private NarrativeAuthZService narrativeAuthZService;
    private ProposalPersonService proposalPersonService;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private PersonService personService;
    
    /**
     * 
     * Method to add a new narrative to narratives list
     * @param narrative
     */
    public void addNarrative(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        narrative.setProposalNumber(proposaldevelopmentDocument.getProposalNumber());
        narrative.setModuleNumber(getNextModuleNumber(proposaldevelopmentDocument));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(proposaldevelopmentDocument));
        updateUserTimestamp(narrative);
        narrative.setModifyAttachment(true);
        narrative.refreshReferenceObject("narrativeType");
        narrative.refreshReferenceObject("narrativeStatus");
        narrative.populateAttachment();
        populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getNarratives().add(narrative);
    }

    private Integer getNextModuleNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getNarratives();
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getInstituteAttachments();
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
    private Integer getNextModuleSequenceNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getNarratives();
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getInstituteAttachments();
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
    private void populateNarrativeUserRights(ProposalDevelopmentDocument proposalDevelopmentDocument, Narrative narrative) {
        List<Person> persons = getPersons(proposalDevelopmentDocument);
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (Person person : persons) {
            if (!isPersonInNarrativeRights(person, narrativeUserRights)) {
                NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(person.getUserName(), proposalDevelopmentDocument);
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
    
    /**
     * Is this person in the list of narrative user rights?
     * @param person the person to look for
     * @param narrativeUserRights the list of narrative user rights
     * @return true if the person is in the list; otherwise false
     */
    private boolean isPersonInNarrativeRights(Person person, List<NarrativeUserRights> narrativeUserRights) {
        for (NarrativeUserRights right : narrativeUserRights) {
            if (StringUtils.equals(right.getUserId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the persons who have permissions relating to the proposal.
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @return the list of persons (see Permission's page)
     */
    private List<Person> getPersons(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        String roleNames[] = { RoleConstants.AGGREGATOR, 
                               RoleConstants.NARRATIVE_WRITER, 
                               RoleConstants.BUDGET_CREATOR,
                               RoleConstants.VIEWER,
                               RoleConstants.UNASSIGNED };
        
        ProposalAuthorizationService proposalAuthorizationService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        List<Person> allPersons = new ArrayList<Person>();
        for (String roleName : roleNames) {
            List<Person> persons = proposalAuthorizationService.getPersonsInRole(proposalDevelopmentDocument, roleName);
            for (Person person : persons) {
                if (!isPersonInList(person, allPersons)) {
                    allPersons.add(person);
                }
            }
        }
        return allPersons;
    }
    
    /**
     * Is the person in the list of persons?
     * @param person the person to look for
     * @param persons the list of persons
     * @return true if the person is in the list; otherwise false
     */
    private boolean isPersonInList(Person person, List<Person> persons) {
        for (Person p : persons) {
            if (StringUtils.equals(p.getPersonId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }

    public void deleteProposalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getNarratives(), lineToDelete);
    }

    public void deleteInstitutionalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getInstituteAttachments(), lineToDelete);
    }

    private void deleteAttachment(List<Narrative> narratives, int lineToDelete) {
        Narrative narrative = narratives.get(lineToDelete);
        getBusinessObjectService().delete(narrative);
        NarrativeAttachment narrAtt = new NarrativeAttachment();
        narrAtt.setProposalNumber(narrative.getProposalNumber());
        narrAtt.setModuleNumber(narrative.getModuleNumber());
        if (narrative.getNarrativeAttachmentList().isEmpty())
            narrative.getNarrativeAttachmentList().add(narrAtt);
        else
            narrative.getNarrativeAttachmentList().set(0, narrAtt);
        narratives.remove(lineToDelete);

    }

    /**
     * 
     * Method to add a new institute attachment to institute attachment list
     * @param narrative
     */
    public void addInstituteAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        narrative.setProposalNumber(proposaldevelopmentDocument.getProposalNumber());
        narrative.setModuleNumber(getNextModuleNumber(proposaldevelopmentDocument));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(proposaldevelopmentDocument));
        narrative.setModifyAttachment(true);
        narrative.refreshReferenceObject("narrativeType");
        narrative.populateAttachment();
        
        populateNarrativeUserRights(proposaldevelopmentDocument,narrative);  
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getInstituteAttachments().add(narrative);  
    }

    /**
     * Method to populate personname for all user who have narrative rights
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeService#populatePersonNameForNarrativeUserRights(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.Narrative)
     */
    public void populatePersonNameForNarrativeUserRights(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
//        populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (NarrativeUserRights narrativeUserRight : narrativeUserRights) {
            String personName = proposalPersonService.getPersonName(proposaldevelopmentDocument, narrativeUserRight.getUserId());
            narrativeUserRight.setPersonName(personName);
        }
    }

    public void replaceAttachment(Narrative narrative) {
        narrative.refreshReferenceObject("narrativeAttachmentList");
        narrative.populateAttachment();
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
    }

    public void populateNarrativeRightsForLoggedinUser(ProposalDevelopmentDocument proposaldevelopmentDocument) {
        List<Narrative> narrativeList = proposaldevelopmentDocument.getNarratives();
        for (Narrative narrative : narrativeList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        }
        
        List<Narrative> instituteAttachmentList = proposaldevelopmentDocument.getInstituteAttachments();
        for (Narrative instituteAttachment : instituteAttachmentList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,instituteAttachment);
        }
    }

    /**
     * Update the User and Timestamp for the business object.
     * @param doc the business object
     */
    private void updateUserTimestamp(KraPersistableBusinessObjectBase bo) {
        String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();
    
        // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string if it's longer than 8 characters
        if (updateUser.length() > 8) {
            updateUser = updateUser.substring(0, 8);
        }
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        bo.setUpdateUser(updateUser);
    }
    /**
     * Gets the narrativeAuthZService attribute. 
     * @return Returns the narrativeAuthZService.
     */
    public NarrativeAuthZService getNarrativeAuthZService() {
        return narrativeAuthZService;
    }
    /**
     * Sets the narrativeAuthZService attribute value.
     * @param narrativeAuthZService The narrativeAuthZService to set.
     */
    public void setNarrativeAuthZService(NarrativeAuthZService narrativeAuthZService) {
        this.narrativeAuthZService = narrativeAuthZService;
    }
    /**
     * Gets the proposalPersonService attribute. 
     * @return Returns the proposalPersonService.
     */
    public ProposalPersonService getProposalPersonService() {
        return proposalPersonService;
    }
    /**
     * Sets the proposalPersonService attribute value.
     * @param proposalPersonService The proposalPersonService to set.
     */
    public void setProposalPersonService(ProposalPersonService proposalPersonService) {
        this.proposalPersonService = proposalPersonService;
    }
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    /**
     * Gets the dateTimeService attribute. 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        if(dateTimeService==null){
            dateTimeService = (DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME);
        }
        return dateTimeService;
    }
    /**
     * Sets the dateTimeService attribute value.
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
//    private boolean isProposalAttachmentType(Narrative narrative) {
//        return !(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE.equals(narrative.getNarrativeType().getNarrativeTypeGroup()));
//    }
    /**
     * Dummy method to add proposal user roles for narrative. This should be removed after 
     * proposal user roles maintenance screen implementation
     * 
     */
    public void populateDummyUserRoles(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<ProposalPerson> propPersons = proposalDevelopmentDocument.getProposalPersons();
        for (ProposalPerson proposalPerson : propPersons) {
            addDummyUserRole(proposalDevelopmentDocument, proposalPerson);
        }

    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeService#addDummyUserRole(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public void addDummyUserRole(ProposalDevelopmentDocument document, ProposalPerson person) {
        if (person.getPersonId() == null) {
            return;
        }
        
        if (userRoleExists(document, person.getPersonId())) {
            return;
        }

        LOG.info("Adding user with id " + person.getPerson());
        
        ProposalUserRoles propUserRole = new ProposalUserRoles();
        propUserRole.setProposalNumber(document.getProposalNumber());
        propUserRole.setRoleId(new Integer(102));
        propUserRole.setUserId(person.getPersonId());
        document.getProposalUserRoles().add(propUserRole);
    }
    
    /**
     * Compares the user id against user roles in the <code>{@link ProposalDevelopmentDocument}</code> to see if the user id already
     * has a role.
     * 
     * @return the user id is already bound to a role
     */
    private boolean userRoleExists(ProposalDevelopmentDocument document, String userId) {
        for (ProposalUserRoles proposalUserRole : document.getProposalUserRoles()) {
            if(StringUtils.equals(proposalUserRole.getUserId(), userId)){
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeService#deletePerson(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public void deletePerson(String username, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Person person = personService.getPersonByName(username);
        List<Narrative> narratives = proposalDevelopmentDocument.getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
            for (NarrativeUserRights right : userRights) {
                if (StringUtils.equals(right.getUserId(), person.getPersonId())) {
                    userRights.remove(right);
                    break;
                }
            }
        }
    }

   
    public void readjustRights(String username, ProposalDevelopmentDocument proposalDevelopmentDocument, List<String> roleNames) {
        Person person = personService.getPersonByName(username);
        List<Narrative> narratives = proposalDevelopmentDocument.getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
            for (NarrativeUserRights right : userRights) {
                if (StringUtils.equals(right.getUserId(), person.getPersonId())) {
                    String currentAccessType = right.getAccessType();
                    
                    // If the user currently has MODIFY rights, but his/her default (max) right has been down-graded,
                    // then we will down-grade his/her current narrative rights.  Ditto if the user currently has 
                    // VIEW rights.  
                    
                    if (StringUtils.equals(currentAccessType, NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
                        NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(username, proposalDevelopmentDocument);
                        String accessType = narrativeRight.getAccessType();
                        if (StringUtils.equals(accessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType());
                        }
                        else if (StringUtils.equals(accessType, NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType());
                        }
                    }
                    else if (StringUtils.equals(currentAccessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                        NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(username, proposalDevelopmentDocument);
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

    
    public void addPerson(String username, ProposalDevelopmentDocument proposalDevelopmentDocument, String roleName) {
        Person person = personService.getPersonByName(username);
        List<Narrative> narratives = proposalDevelopmentDocument.getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
           
            NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(roleName);
            String personName = person.getFullName();
            NarrativeUserRights narrUserRight = new NarrativeUserRights();
            narrUserRight.setProposalNumber(narrative.getProposalNumber());
            narrUserRight.setModuleNumber(narrative.getModuleNumber());
            narrUserRight.setUserId(person.getPersonId());
            narrUserRight.setAccessType(narrativeRight.getAccessType());
            narrUserRight.setPersonName(personName);
            updateUserTimestamp(narrUserRight);
            userRights.add(narrUserRight);
        }
    }
    
    /**
     * Set the Person Service.  Injected by Spring.
     * @param personService the Person Service
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
