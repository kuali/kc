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
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;

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

    public void prepareProposalPersonBiographyForSave(DevelopmentProposal developmentProposal, ProposalPersonBiography biography);
    public PropPerDocType findPropPerDocTypeForOther();
}
