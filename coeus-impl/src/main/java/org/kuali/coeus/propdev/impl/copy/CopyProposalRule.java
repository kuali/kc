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
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * The Copy Proposal Rule validates copying of a proposal.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface CopyProposalRule extends BusinessRule {
    
    /**
     * Validates the copying of a proposal.  Before a proposal can be copied,
     * we must verify that it can be copied.  This includes verification against
     * the Copy criteria, e.g. if the user wants to copy attachments but doesn't
     * the necessary permissions to do so, we must prevent the copy.
     * 
     * @param document the original proposal development document
     * @param criteria the user-specified criteria
     * @return true if the copy request is valid; otherwise false
     */
    public boolean processCopyProposalBusinessRules(ProposalDevelopmentDocument document, 
                                                    ProposalCopyCriteria criteria);
}
