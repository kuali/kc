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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.attachment.AddPersonnelAttachmentEvent;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ReplacePersonnelAttachmentRule;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class ReplacePersonnelAttachmentEvent extends AddPersonnelAttachmentEvent {

    /**
     * Constructs a ReplacePersonnelAttachmentEvent with the given errorPathPrefix, document, and ProposalPersonBiography.
     * 
     * @param errorPathPrefix
     * @param document
     * @param proposalPersonBiography
     */
    public ReplacePersonnelAttachmentEvent(String errorPathPrefix, ProposalDevelopmentDocument document,
            ProposalPersonBiography proposalPersonBiography) {
        super(errorPathPrefix, document, proposalPersonBiography);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return ReplacePersonnelAttachmentRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ReplacePersonnelAttachmentRule) rule).processReplacePersonnelAttachmentBusinessRules(this);
    }
    
}
