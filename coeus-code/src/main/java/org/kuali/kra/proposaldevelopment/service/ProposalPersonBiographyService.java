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
package org.kuali.kra.proposaldevelopment.service;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;

import java.util.List;

public interface ProposalPersonBiographyService {
    /**
     * 
     * This method to add personnel attachment
     * @param proposaldevelopmentDocument
     * @param proposalPersonBiography
     */
    public void addProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument,ProposalPersonBiography proposalPersonBiography);
    
    /**
     * 
     * This method to delete personnel attachment when the key personnel is deleted
     * @param proposaldevelopmentDocument
     * @param person
     */
    public void removePersonnelAttachmentForDeletedPerson(ProposalDevelopmentDocument proposaldevelopmentDocument, ProposalPerson person);

    /**
     * 
     * This method to delete personnel attachment from the list.  ALso, remove it from DB.
     * @param proposaldevelopmentDocument
     * @param lineToDelete
     */
    public void deleteProposalPersonBiography(ProposalDevelopmentDocument proposaldevelopmentDocument, int lineToDelete);
    
    /**
     * 
     * This method is to set up timestamp and upload user for personnel attachments.
     * @param proposalPersonBios
     */

    public void setPersonnelBioTimeStampUser(List<ProposalPersonBiography> proposalPersonBios);

}
