/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.SystemAuthorizationService;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.coeus.propdev.impl.person.ProposalPersonService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * This class is primarily to add/delete proposal/institute attachments. 
 */
@Component("legacyNarrativeService")
public class LegacyNarrativeServiceImpl implements LegacyNarrativeService {

    private static final String NSF_DATA_MANAGEMENT_PLAN_PDF = "NSF_DATA_MANAGEMENT_PLAN.pdf";

    @Autowired
    @Qualifier("narrativeAuthZService")
    private NarrativeAuthZService narrativeAuthZService;
    @Autowired
    @Qualifier("proposalPersonService")
    private ProposalPersonService proposalPersonService;
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
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
    @Qualifier("attachmentDao")
    private AttachmentDao attachmentDao;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    /**
     * 
     * Method to add a new narrative to narratives list
     * @param narrative
     */
    public void addNarrative(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        prepareNarrative(proposaldevelopmentDocument, narrative);
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getDevelopmentProposal().getNarratives().add(narrative);
    }

    private Narrative changeDataManagementPlanAttachmentName(Narrative narrative) {
        narrative.setName(NSF_DATA_MANAGEMENT_PLAN_PDF);
        narrative.getNarrativeAttachment().setName(NSF_DATA_MANAGEMENT_PLAN_PDF);
        return narrative;
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
        List<Role> proposalRoles = getSystemAuthorizationService().getRoles(RoleConstants.PROPOSAL_ROLE_TYPE);
        
        KcAuthorizationService kcAuthorizationService = getKcAuthorizationService();
        List<KcPerson> allPersons = new ArrayList<KcPerson>();

        for (Role proposalRole : proposalRoles) {
            List<String> users = kcAuthorizationService.getPrincipalsInRole(proposalDevelopmentDocument, proposalRole.getName());

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
        narrAtt.setModuleNumber(narrative.getModuleNumber());
        narrative.setNarrativeAttachment(narrAtt);

        narratives.remove(lineToDelete);

    }

    /**
     * 
     * Method to add a new institute attachment to institute attachment list
     * @param narrative
     */
    public void addInstituteAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        prepareNarrative(proposaldevelopmentDocument, narrative);
        
        getBusinessObjectService().save(narrative);
        narrative.clearAttachment();
        proposaldevelopmentDocument.getDevelopmentProposal().getInstituteAttachments().add(narrative);  
    }
    
    public void prepareNarrative(ProposalDevelopmentDocument document, Narrative narrative) {
    	narrative.setDevelopmentProposal(document.getDevelopmentProposal());
        narrative.setModuleNumber(getNextModuleNumber(document));
        narrative.setModuleSequenceNumber(getNextModuleSequenceNumber(document));
        narrative.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        narrative.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        narrative.refreshReferenceObject("narrativeType");
        narrative.refreshReferenceObject("narrativeStatus");
        narrative.populateAttachment();
        populateNarrativeUserRights(document,narrative);
    }

    /**
     * Method to populate personname for all user who have narrative rights
     */
    public void populatePersonNameForNarrativeUserRights(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative narrative) {
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (NarrativeUserRights narrativeUserRight : narrativeUserRights) {
            String personName = getProposalPersonService().getPersonName(proposaldevelopmentDocument, narrativeUserRight.getUserId());
            narrativeUserRight.setPersonName(personName);
        }
    }

    public void replaceAttachment(Narrative narrative) {
        narrative.refreshReferenceObject("narrativeAttachment");
        narrative.populateAttachment();
        if (narrative.getNarrativeTypeCode().equalsIgnoreCase("200")) {
            narrative = changeDataManagementPlanAttachmentName(narrative);
        }
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
    protected void updateUserTimestamp(KcPersistableBusinessObjectBase bo) {
        bo.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
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
        return dateTimeService;
    }
    /**
     * Sets the dateTimeService attribute value.
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
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

    
    public void addPerson(String userName, ProposalDevelopmentDocument proposalDevelopmentDocument, String roleName) {
        KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
        List<Narrative> narratives = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
           
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
    
    protected KcPersonService getKcPersonService (){return kcPersonService;}
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

    @Override
    public void setNarrativeTimeStampUser(List<Narrative> narratives) {

        for (Narrative narrative : narratives) {
            Iterator personBioAtt = getAttachmentDao().getNarrativeTimeStampAndUploadUser(narrative.getModuleNumber(), narrative.getProposalNumber());
            if (personBioAtt.hasNext()) {
                Object[] item = (Object[])personBioAtt.next();
                narrative.setTimestampDisplay((Timestamp)item[0]);
                narrative.setUploadUserDisplay((String)item[1]);
                //using PersonService as it will display the user's name the same as the notes panel does
                Person person = getPersonService().getPersonByPrincipalName(narrative.getUploadUserDisplay());
                narrative.setUploadUserFullName(ObjectUtils.isNull(person) ? narrative.getUploadUserDisplay() + "(not found)" : person.getName());
            }

            }
        }
    
    public void setNarrativeTimeStampUser(DevelopmentProposal proposal) {
        List<Narrative> narratives = new ArrayList<Narrative> ();
        narratives.addAll(proposal.getNarratives());
        narratives.addAll(proposal.getInstituteAttachments());
        setNarrativeTimeStampUser(narratives);
    }
    
    /**
     * 
     * This is a helper method for retrieving KraAuthorizationService
     * @return
     */
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
}
