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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.attachment.AttachmentDao;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component("proposalPersonBiographyService")
public class ProposalPersonBiographyServiceImpl implements ProposalPersonBiographyService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("attachmentDao")
    private AttachmentDao attachmentDao;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    
    /**
     * 
     * @see org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService#addProposalPersonBiography(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument,
     *      org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography)
     */
    public void addProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument,
            ProposalPersonBiography proposalPersonBiography) {
        proposalPersonBiography.setDevelopmentProposal(proposaldevelopmentDocument.getDevelopmentProposal());
        proposalPersonBiography.setBiographyNumber(proposaldevelopmentDocument
                .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        proposalPersonBiography.setPropPerDocType(new PropPerDocType());
        ProposalPerson proposalPerson = getPerson(proposaldevelopmentDocument.getDevelopmentProposal(), proposalPersonBiography.getProposalPersonNumber());
        if (proposalPerson != null) {
            proposalPersonBiography.setPersonId(proposalPerson.getPersonId());
            proposalPersonBiography.setRolodexId(proposalPerson.getRolodexId());
        }
        proposalPersonBiography.getPropPerDocType().setCode(proposalPersonBiography.getDocumentTypeCode());
        proposalPersonBiography.refreshReferenceObject("propPerDocType");
        FormFile personnelAttachmentFile = proposalPersonBiography.getPersonnelAttachmentFile();
        if (personnelAttachmentFile != null) {
            try {
                byte[] fileData = personnelAttachmentFile.getFileData();
                if (fileData.length > 0) {
                    ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
                    personnelAttachment.setName(personnelAttachmentFile.getFileName());
                    personnelAttachment.setProposalNumber(proposalPersonBiography.getProposalNumber());
                    personnelAttachment.setProposalPersonNumber(proposalPersonBiography.getProposalPersonNumber());
                    personnelAttachment.setData(personnelAttachmentFile.getFileData());
                    personnelAttachment.setType(personnelAttachmentFile.getContentType());
                    proposalPersonBiography.setName(personnelAttachmentFile.getFileName());
                    proposalPersonBiography.setType(personnelAttachmentFile.getContentType());

                    proposalPersonBiography.setPersonnelAttachment(personnelAttachment);
                }
            }
            catch (Exception e) {
                proposalPersonBiography.setPersonnelAttachment(null);
            }
        }
        DocumentNextvalue documentNextvalue = proposaldevelopmentDocument.getDocumentNextvalueBo(Constants.PROP_PERSON_BIO_NUMBER);
        List<PersistableBusinessObject> businessObjects = new ArrayList<PersistableBusinessObject>();
        businessObjects.add(documentNextvalue);
        businessObjects.add(proposalPersonBiography);
        getBusinessObjectService().save(businessObjects);
        proposalPersonBiography.setPersonnelAttachment(null);
        proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().add(proposalPersonBiography);

    }

    @Override
    public void prepareProposalPersonBiographyForSave(DevelopmentProposal developmentProposal, ProposalPersonBiography biography) {
        biography.setPropPerDocType(new PropPerDocType());
        biography.getPropPerDocType().setCode(biography.getDocumentTypeCode());
        biography.refreshReferenceObject("propPerDocType");

        ProposalPerson proposalPerson = getPerson(developmentProposal, biography.getProposalPersonNumber());
        if (proposalPerson != null) {
            biography.setPersonId(proposalPerson.getPersonId());
            biography.setRolodexId(proposalPerson.getRolodexId());
        }
    }
    /**
     * 
     * @see org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService#removePersonnelAttachmentForDeletedPerson(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument,
     *      org.kuali.coeus.propdev.impl.person.ProposalPerson)
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
     * @see org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService#deleteProposalPersonBiography(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument,
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
     * @param developmentProposal
     * @param proposalPersonNumber
     * @return
     */
    protected ProposalPerson getPerson(DevelopmentProposal developmentProposal, Integer proposalPersonNumber) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
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
