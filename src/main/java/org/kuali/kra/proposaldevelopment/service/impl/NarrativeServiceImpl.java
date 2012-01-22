/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.dao.AttachmentDao;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class is primarily to add/delete proposal/institute attachments. 
 */
public class NarrativeServiceImpl implements NarrativeService {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NarrativeServiceImpl.class);
    
    private NarrativeAuthZService narrativeAuthZService;
    private ProposalPersonService proposalPersonService;
    private BusinessObjectService businessObjectService;
    private SystemAuthorizationService systemAuthorizationService;
    private DateTimeService dateTimeService;
    private KcPersonService kcPersonService;
    private PersonService personService;
    private AttachmentDao attachmentDao;

    /**
     * 
     * Method to add a new narrative to narratives list
     * @param narrative
     */
    public void addNarrative(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        narrative.setProposalNumber(proposaldevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(getNextModuleNumber(proposaldevelopmentDocument));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(proposaldevelopmentDocument));
        updateUserTimestamp(narrative);
        narrative.refreshReferenceObject("narrativeType");
        narrative.refreshReferenceObject("narrativeStatus");
        narrative.populateAttachment();
        populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getDevelopmentProposal().getNarratives().add(narrative);
    }

    protected Integer getNextModuleNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
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
    protected Integer getNextModuleSequenceNumber(ProposalDevelopmentDocument proposaldevelopmentDocument) {
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
                NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(person.getPersonId(), proposalDevelopmentDocument);
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
    protected boolean isPersonInNarrativeRights(KcPerson person, List<NarrativeUserRights> narrativeUserRights) {
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
    protected List<KcPerson> getPersons(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        List<Role> proposalRoles = systemAuthorizationService.getRoles(RoleConstants.PROPOSAL_ROLE_TYPE);
        
        KraAuthorizationService kraAuthorizationService = getKraAuthorizationService();
        List<KcPerson> allPersons = new ArrayList<KcPerson>();

        for (Role proposalRole : proposalRoles) {
            List<KcPerson> persons = kraAuthorizationService.getPersonsInRole(proposalDevelopmentDocument, proposalRole.getName());
            for (KcPerson person : persons) {
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
    protected boolean isPersonInList(KcPerson person, List<KcPerson> persons) {
        for (KcPerson p : persons) {
            if (StringUtils.equals(p.getPersonId(), person.getPersonId())) {
                return true;
            }
        }
        return false;
    }

    public void deleteProposalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getDevelopmentProposal().getNarratives(), lineToDelete);
    }

    public void deleteInstitutionalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments(), lineToDelete);
    }

    protected void deleteAttachment(List<Narrative> narratives, int lineToDelete) {
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
        narrative.setProposalNumber(proposaldevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        narrative.setModuleNumber(getNextModuleNumber(proposaldevelopmentDocument));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(proposaldevelopmentDocument));
        narrative.refreshReferenceObject("narrativeType");
        narrative.populateAttachment();
        
        populateNarrativeUserRights(proposaldevelopmentDocument,narrative);  
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments().add(narrative);  
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
        List<Narrative> narrativeList = proposaldevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narrativeList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,narrative);
        }
        
        List<Narrative> instituteAttachmentList = proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments();
        for (Narrative instituteAttachment : instituteAttachmentList) {
            populateNarrativeUserRights(proposaldevelopmentDocument,instituteAttachment);
        }
    }

    /**
     * Update the User and Timestamp for the business object.
     * @param bo the business object
     */
    protected void updateUserTimestamp(KraPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        bo.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
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
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeService#deletePerson(java.lang.String, org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
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
                        NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(userId, proposalDevelopmentDocument);
                        String accessType = narrativeRight.getAccessType();
                        if (StringUtils.equals(accessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType());
                        }
                        else if (StringUtils.equals(accessType, NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType())) {
                            right.setAccessType(NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType());
                        }
                    }
                    else if (StringUtils.equals(currentAccessType, NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                        NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(userId, proposalDevelopmentDocument);
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

    
    public void addPerson(String userName, ProposalDevelopmentDocument proposalDevelopmentDocument, String roleName) {
        KcPerson person = kcPersonService.getKcPersonByUserName(userName);
        List<Narrative> narratives = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
           
            NarrativeRight narrativeRight = narrativeAuthZService.getDefaultNarrativeRight(roleName);
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
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public AttachmentDao getAttachmentDao() {
        return attachmentDao;
    }

    public void setAttachmentDao(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.NarrativeService#setNarrativeTimeStampUser(java.util.List)
     */
    public void setNarrativeTimeStampUser(List<Narrative> narratives) {

        for (Narrative narrative : narratives) {
            Iterator personBioAtt = attachmentDao.getNarrativeTimeStampAndUploadUser(narrative.getModuleNumber(), narrative.getProposalNumber());
            if (personBioAtt.hasNext()) {
                Object[] item = (Object[])personBioAtt.next();
                narrative.setTimestampDisplay((Timestamp)item[0]);
                narrative.setUploadUserDisplay((String)item[1]);
                //using PersonService as it will display the user's name the same as the notes panel does
                Person person = personService.getPersonByPrincipalName(narrative.getUploadUserDisplay());
                narrative.setUploadUserFullName(ObjectUtils.isNull(person) ? narrative.getUploadUserDisplay() + "(not found)" : person.getName());
            }

            }
        }
    
    /**
     * 
     * This is a helper method for retrieving KraAuthorizationService
     * @return
     */
    protected KraAuthorizationService getKraAuthorizationService(){
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }

    public void setSystemAuthorizationService(SystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    protected PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
