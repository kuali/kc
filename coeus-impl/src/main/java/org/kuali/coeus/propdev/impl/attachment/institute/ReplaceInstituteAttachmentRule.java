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
package org.kuali.coeus.propdev.impl.attachment.institute;

public interface ReplaceInstituteAttachmentRule extends AddInstituteAttachmentRule {

    /**
     * Rule invoked upon replacing an institute attachment within a
     * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>

     * @param event The <code>{@link ReplaceInstituteAttachmentEvent}</code> that triggered this rule to run.
     * @return true if the business rules pass, false otherwise.
     */
    public boolean processReplaceInstituteAttachmentBusinessRules(ReplaceInstituteAttachmentEvent event);
    
}
