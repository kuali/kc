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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.dao.AttachmentDao;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProposalPersonBiographyServiceImpl implements ProposalPersonBiographyService {
    private BusinessObjectService businessObjectService;
    private AttachmentDao attachmentDao;
    private PersonService personService;

    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService#addProposalPersonBiography(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,
     *      org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography)
     */
    public void addProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument,
            ProposalPersonBiography proposalPersonBiography) {
        proposalPersonBiography.setProposalNumber(proposaldevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        proposalPersonBiography.setBiographyNumber(proposaldevelopmentDocument
                .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        proposalPersonBiography.setPropPerDocType(new PropPerDocType());
        ProposalPerson proposalPerson = getPerson(proposaldevelopmentDocument, proposalPersonBiography.getProposalPersonNumber());
        if (proposalPerson != null) {
            proposalPersonBiography.setPersonId(proposalPerson.getPersonId());
            proposalPersonBiography.setRolodexId(proposalPerson.getRolodexId());
        }
        proposalPersonBiography.getPropPerDocType().setDocumentTypeCode(proposalPersonBiography.getDocumentTypeCode());
        proposalPersonBiography.refreshReferenceObject("propPerDocType");
        FormFile personnelAttachmentFile = proposalPersonBiography.getPersonnelAttachmentFile();
        if (personnelAttachmentFile != null) {
            try {
                byte[] fileData = personnelAttachmentFile.getFileData();
                if (fileData.length > 0) {
                    ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
                    personnelAttachment.setFileName(personnelAttachmentFile.getFileName());
                    personnelAttachment.setProposalNumber(proposalPersonBiography.getProposalNumber());
                    personnelAttachment.setProposalPersonNumber(proposalPersonBiography.getProposalPersonNumber());
                    personnelAttachment.setBiographyData(personnelAttachmentFile.getFileData());
                    personnelAttachment.setContentType(personnelAttachmentFile.getContentType());
                    proposalPersonBiography.setFileName(personnelAttachmentFile.getFileName());
                    proposalPersonBiography.setContentType(personnelAttachmentFile.getContentType());
                    if (proposalPersonBiography.getPersonnelAttachmentList().isEmpty())
                        proposalPersonBiography.getPersonnelAttachmentList().add(personnelAttachment);
                    else
                        proposalPersonBiography.getPersonnelAttachmentList().set(0, personnelAttachment);
                }
            }
            catch (Exception e) {
                proposalPersonBiography.getPersonnelAttachmentList().clear();
            }
        }
        DocumentNextvalue documentNextvalue = proposaldevelopmentDocument.getDocumentNextvalueBo(Constants.PROP_PERSON_BIO_NUMBER);
//        documentNextvalue.setDocumentKey(proposaldevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        List<PersistableBusinessObject> businessObjects = new ArrayList<PersistableBusinessObject>();
        businessObjects.add(documentNextvalue);
        businessObjects.add(proposalPersonBiography);
        getBusinessObjectService().save(businessObjects);
        proposalPersonBiography.getPersonnelAttachmentList().clear();
        proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().add(proposalPersonBiography);

    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService#removePersonnelAttachmentForDeletedPerson(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,
     *      org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public void removePersonnelAttachmentForDeletedPerson(ProposalDevelopmentDocument proposaldevelopmentDocument,
            ProposalPerson person) {

        List<ProposalPersonBiography> personAttachments = new ArrayList();
        for (ProposalPersonBiography proposalPersonBiography : proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios()) {
            if (proposalPersonBiography.getProposalPersonNumber().equals(person.getProposalPersonNumber())) {
                personAttachments.add(proposalPersonBiography);
            }

        }
        if (!personAttachments.isEmpty()) {
            proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().removeAll(personAttachments);
        }
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService#deleteProposalPersonBiography(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,
     *      int)
     */
    public void deleteProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument, int lineToDelete) {
        ProposalPersonBiography proposalPersonBiography = proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().get(lineToDelete);
        proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().remove(proposalPersonBiography);
        getBusinessObjectService().delete(proposalPersonBiography);

    }

    /**
     * 
     * This method find the matched person in key person list
     * @param proposaldevelopmentDocument
     * @param proposalPersonNumber
     * @return
     */
    protected ProposalPerson getPerson(ProposalDevelopmentDocument proposaldevelopmentDocument, Integer proposalPersonNumber) {
        for (ProposalPerson person : proposaldevelopmentDocument.getDevelopmentProposal().getProposalPersons()) {
            if (proposalPersonNumber.equals(person.getProposalPersonNumber())) {
                return person;
            }
        }
        return null;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    @Override
    public void setPersonnelBioTimeStampUser(List<ProposalPersonBiography> proposalPersonBios) {

        for (ProposalPersonBiography proposalPersonBiography : proposalPersonBios) {
            Iterator personBioAtt = attachmentDao.getPersonnelTimeStampAndUploadUser(proposalPersonBiography.getProposalPersonNumber(), proposalPersonBiography.getProposalNumber(), proposalPersonBiography.getBiographyNumber());
            if (personBioAtt.hasNext()) {
                Object[] item = (Object[])personBioAtt.next();
                proposalPersonBiography.setTimestampDisplay((Timestamp)item[0]);
                proposalPersonBiography.setUploadUserDisplay((String)item[1]);
                //using PersonService as it will display the user's name the same as the notes panel does
                Person person = personService.getPersonByPrincipalName(proposalPersonBiography.getUploadUserDisplay());
                proposalPersonBiography.setUploadUserFullName(ObjectUtils.isNull(person) ? proposalPersonBiography.getUploadUserDisplay() + "(not found)" : person.getName());
             }

        }
    }   
    

    public AttachmentDao getAttachmentDao() {
        return attachmentDao;
    }

    public void setAttachmentDao(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
