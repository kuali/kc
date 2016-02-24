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
package org.kuali.coeus.propdev.impl.copy;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

/**
 * The Proposal Copy Service is responsible for copying proposals.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface ProposalCopyService {
    
    /**
     * Copy a proposal development document.  The criteria controls various
     * aspects of the copying process, e.g. whether or not to copy attachments.
     * 
     * @param doc the proposal development document to copy.
     * @param criteria the user-specified criteria that controls various copy operations.
     * @return the new document that is saved in the database;
     *         otherwise null if an error occurred, e.g. the user didn't have permission to copy the document
     * @throws Exception if anything really bad happens
     */
    public ProposalDevelopmentDocument copyProposal(ProposalDevelopmentDocument doc, ProposalCopyCriteria criteria);
}
