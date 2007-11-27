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
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;

public class ProposalPersonBiographyServiceImpl implements ProposalPersonBiographyService {

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService#addProposalPersonBiography(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography)
     */
    public void addProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument,
            ProposalPersonBiography proposalPersonBiography) {
        proposalPersonBiography.setProposalNumber(proposaldevelopmentDocument.getProposalNumber());
        proposalPersonBiography.setUpdateUser(proposaldevelopmentDocument.getUpdateUser());
        proposalPersonBiography.setUpdateTimestamp(proposaldevelopmentDocument.getUpdateTimestamp());
        proposalPersonBiography.setBiographyNumber(proposaldevelopmentDocument.getProposalNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        proposalPersonBiography.setPropPerDocType(new PropPerDocType());
        proposalPersonBiography.getPropPerDocType().setDocumentTypeCode(proposalPersonBiography.getDocumentTypeCode());
        proposalPersonBiography.refreshReferenceObject("propPerDocType");
        FormFile personnelAttachmentFile = proposalPersonBiography.getPersonnelAttachmentFile();
        proposalPersonBiography.setFileName(personnelAttachmentFile.getFileName());
        try {
            byte[] fileData = personnelAttachmentFile.getFileData();
            if (fileData.length > 0) {
                ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
                personnelAttachment.setFileName(personnelAttachmentFile.getFileName());
                personnelAttachment.setProposalNumber(proposalPersonBiography.getProposalNumber());
                personnelAttachment.setProposalPersonNumber(proposalPersonBiography.getProposalPersonNumber());
                personnelAttachment.setBiographyData(personnelAttachmentFile.getFileData());
                personnelAttachment.setContentType(personnelAttachmentFile.getContentType());
                if (proposalPersonBiography.getPersonnelAttachmentList().isEmpty())
                    proposalPersonBiography.getPersonnelAttachmentList().add(personnelAttachment);
                else
                    proposalPersonBiography.getPersonnelAttachmentList().clear();
            }
        } catch (Exception e) {
            proposalPersonBiography.getPersonnelAttachmentList().clear();
        }
        proposaldevelopmentDocument.getPropPersonBios().add(proposalPersonBiography);
        
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService#removePersonnelAttachmentForDeletedPerson(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalPerson)
     */
    public void removePersonnelAttachmentForDeletedPerson(ProposalDevelopmentDocument proposaldevelopmentDocument, ProposalPerson person) {
    
        List<ProposalPersonBiography> personAttachments=new ArrayList();
        for (ProposalPersonBiography proposalPersonBiography : proposaldevelopmentDocument.getPropPersonBios()) {
            if (proposalPersonBiography.getProposalPersonNumber().equals(person.getProposalPersonNumber())) {
                personAttachments.add(proposalPersonBiography);
            }
            
        }
        if (!personAttachments.isEmpty()) {
            proposaldevelopmentDocument.getPropPersonBios().removeAll(personAttachments);
        }
    }
}
