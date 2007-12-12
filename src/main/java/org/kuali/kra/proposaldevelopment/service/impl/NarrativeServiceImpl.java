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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;

/**
 * This class...
 */
public class NarrativeServiceImpl implements NarrativeService {
    private NarrativeAuthZService narrativeAuthZService;
    private ProposalPersonService proposalPersonService;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    
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
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getInstitutes();
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
        List<Narrative> instituteAttachmentsList = proposaldevelopmentDocument.getInstitutes();
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
     * 
     * This method used to add narrative user rights to narrative object
     * It looks for proposal persons who has narrative rights and add it to narrative
     * @param narrative
     */
    private void populateNarrativeUserRights(ProposalDevelopmentDocument proposalDevelopmentDocument,Narrative narrative) {
        List<ProposalUserRoles> proposalUserRoles = proposalDevelopmentDocument.getProposalUserRoles();
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        for (ProposalUserRoles proposalUserRole : proposalUserRoles) {
            boolean continueFlag = false;
            for (NarrativeUserRights narrativeUserRight : narrativeUserRights) {
                if(StringUtils.equals(narrativeUserRight.getUserId(),proposalUserRole.getUserId())){
                    continueFlag = true;
                    break;
                }
            }
            if(continueFlag) continue;
    
            NarrativeRight narrativeRight = narrativeAuthZService.getNarrativeRight(proposalUserRole.getRoleId());
            String personName = proposalPersonService.getPersonName(proposalDevelopmentDocument, proposalUserRole.getUserId());
            NarrativeUserRights narrUserRight = new NarrativeUserRights();
            narrUserRight.setProposalNumber(narrative.getProposalNumber());
            narrUserRight.setModuleNumber(narrative.getModuleNumber());
            narrUserRight.setUserId(proposalUserRole.getUserId());
            narrUserRight.setAccessType(narrativeRight.getAccessType());
            narrUserRight.setPersonName(personName);
            updateUserTimestamp(narrUserRight);
            narrativeUserRights.add(narrUserRight);
        }
    }

    public void deleteProposalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getNarratives(), lineToDelete);
    }

    public void deleteInstitutionalAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,int lineToDelete) {
        deleteAttachment(proposaldevelopmentDocument.getInstitutes(), lineToDelete);
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
     * Method to add a new institute to institutes list
     * @param institute
     */
    public void addInstituteAttachment(ProposalDevelopmentDocument proposaldevelopmentDocument,Narrative institute) {
        institute.setProposalNumber(proposaldevelopmentDocument.getProposalNumber());
//        institute.setModuleNumber(proposaldevelopmentDocument.getProposalNextValue(NARRATIVE_MODULE_NUMBER));
//        institute.setModuleSequenceNumber(proposaldevelopmentDocument.getProposalNextValue(NARRATIVE_MODULE_SEQUENCE_NUMBER));
        institute.setModuleNumber(getNextModuleNumber(proposaldevelopmentDocument));
        institute.setModuleSequenceNumber(getNextModuleSequenceNumber(proposaldevelopmentDocument));
        updateUserTimestamp(institute);
        institute.setModifyAttachment(true);
        institute.refreshReferenceObject("narrativeType");
        institute.refreshReferenceObject("narrativeStatus");
        institute.populateAttachment();
        getBusinessObjectService().save(institute);
        institute.clearAttachment();
        proposaldevelopmentDocument.getInstitutes().add(institute);
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
        List<ProposalUserRoles> propUserRoles = proposalDevelopmentDocument.getProposalUserRoles();
        for (ProposalPerson proposalPerson : propPersons) {
            boolean continueFlag = false;
            for (ProposalUserRoles proposalUserRole : propUserRoles) {
                if(StringUtils.equals(proposalUserRole.getUserId(),proposalPerson.getPersonId())){
                    continueFlag = true;
                    break;
                }
            }
            if(continueFlag) continue;
            ProposalUserRoles propUserRole = new ProposalUserRoles();
            propUserRole.setProposalNumber(proposalDevelopmentDocument.getProposalNumber());
            propUserRole.setRoleId(new Integer(102));
            propUserRole.setUserId(proposalPerson.getPersonId());
            propUserRoles.add(propUserRole);
        }
    }
}
