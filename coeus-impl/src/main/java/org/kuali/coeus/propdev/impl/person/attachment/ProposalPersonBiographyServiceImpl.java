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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.attachment.AttachmentDao;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.*;

@Component("proposalPersonBiographyService")
public class ProposalPersonBiographyServiceImpl implements ProposalPersonBiographyService {

    public static final String OTHER_DOCUMENT_TYPE_DESCRIPTION = "Other";

    private static final String DOC_TYPE_DESCRIPTION = "description";

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

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
                    personnelAttachment.setProposalPersonBiography(proposalPersonBiography);
                }
            }
            catch (Exception e) {
                proposalPersonBiography.setPersonnelAttachment(null);
            }
        }
        DocumentNextvalue documentNextvalue = proposaldevelopmentDocument.getDocumentNextvalueBo(Constants.PROP_PERSON_BIO_NUMBER);
        getDataObjectService().save(documentNextvalue);
        proposalPersonBiography = getDataObjectService().save(proposalPersonBiography);
        proposalPersonBiography.setPersonnelAttachment(null);
        proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().add(proposalPersonBiography);

    }

    @Override
    public void prepareProposalPersonBiographyForSave(DevelopmentProposal developmentProposal, ProposalPersonBiography biography) {
        biography.setPropPerDocType(new PropPerDocType());
        biography.getPropPerDocType().setCode(biography.getDocumentTypeCode());
        biography.refreshReferenceObject("propPerDocType");


        if (biography.getProposalPersonNumber() != null) {
            ProposalPerson proposalPerson = getPerson(developmentProposal,biography.getProposalPersonNumber() );
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
        getDataObjectService().delete(proposalPersonBiography);

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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
    
    @Override
    public void setPersonnelBioTimeStampUser(List<ProposalPersonBiography> proposalPersonBios) {

        for (ProposalPersonBiography proposalPersonBiography : proposalPersonBios) {
            Iterator personBioAtt = attachmentDao.getPersonnelTimeStampAndUploadUser(proposalPersonBiography.getProposalPersonNumber(), proposalPersonBiography.getProposalNumber(), proposalPersonBiography.getBiographyNumber());
            if (personBioAtt.hasNext()) {
                Object[] item = (Object[])personBioAtt.next();
                proposalPersonBiography.setUpdateTimestamp((Timestamp)item[0]);
                proposalPersonBiography.setUpdateUser((String)item[1]);
                //using PersonService as it will display the user's name the same as the notes panel does
                Person person = personService.getPersonByPrincipalName(proposalPersonBiography.getUploadUserDisplay());
                proposalPersonBiography.setUploadUserFullName(ObjectUtils.isNull(person) ? proposalPersonBiography.getUploadUserDisplay() + "(not found)" : person.getName());
             }

        }
    }

    @Override
    public PropPerDocType findPropPerDocTypeForOther() {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(DOC_TYPE_DESCRIPTION, OTHER_DOCUMENT_TYPE_DESCRIPTION);
        return getDataObjectService().findMatching(PropPerDocType.class, QueryByCriteria.Builder.andAttributes(narrativeTypeMap).build()).getResults().get(0);
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
