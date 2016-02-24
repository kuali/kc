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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.data.DataObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("proposalPersonBiographyService")
public class ProposalPersonBiographyServiceImpl implements ProposalPersonBiographyService {

    public static final String OTHER_DOCUMENT_TYPE_DESCRIPTION = "Other";

    private static final String DOC_TYPE_DESCRIPTION = "description";
    public static final String PROP_PER_DOC_TYPE = "propPerDocType";

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

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
        proposaldevelopmentDocument.getDevelopmentProposal().getPropPersonBios().add(proposalPersonBiography);
    }

    @Override
    public void prepareProposalPersonBiographyForSave(DevelopmentProposal developmentProposal, ProposalPersonBiography biography) {
        biography.setPropPerDocType(new PropPerDocType());
        biography.getPropPerDocType().setCode(biography.getDocumentTypeCode());
        biography.refreshReferenceObject(PROP_PER_DOC_TYPE);


        if (biography.getProposalPersonNumber() != null) {
            ProposalPerson proposalPerson = getPerson(developmentProposal,biography.getProposalPersonNumber() );
            if (proposalPerson != null) {
                biography.setPersonId(proposalPerson.getPersonId());
                biography.setRolodexId(proposalPerson.getRolodexId());
            }
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
    
    protected ProposalPerson getPerson(DevelopmentProposal developmentProposal, Integer proposalPersonNumber) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (proposalPersonNumber.equals(person.getProposalPersonNumber())) {
                return person;
            }
        }
        return null;
    }

    @Override
    public PropPerDocType findPropPerDocTypeForOther() {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(DOC_TYPE_DESCRIPTION, OTHER_DOCUMENT_TYPE_DESCRIPTION);
        return getDataObjectService().findMatching(PropPerDocType.class, QueryByCriteria.Builder.andAttributes(narrativeTypeMap).build()).getResults().get(0);
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

}
