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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;

public abstract class PersonnelAttachmentEventBase extends KcDocumentEventBase implements PersonnelAttachmentEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(PersonnelAttachmentEventBase.class);

    private ProposalPersonBiography proposalPersonBiography;

    protected PersonnelAttachmentEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
            ProposalPersonBiography proposalPersonBiography) {
        super(description, errorPathPrefix, document);


        if ( proposalPersonBiography != null ) {
        	//Deep copy is not needed here
        	// No business validations should alter the BO itslef.
            this.proposalPersonBiography = proposalPersonBiography;
        } else {
            //due to this rule requiring A proposal person biography, create one if null
            this.proposalPersonBiography = new ProposalPersonBiography();
        }
        logEvent();

    }

    /**
     * @return <code>{@link ProposalPersonBiography}</code> that triggered this event.
     */
    public ProposalPersonBiography getProposalPersonBiography() {
        return proposalPersonBiography;
    }

    @Override
    public void validate() {
        super.validate();
        if (getProposalPersonBiography() == null) {
            throw new IllegalArgumentException("invalid (null) proposal person biography");
        }
    }

    /**
     * Logs the event type and some information about the associated proposal person  bio
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProposalPersonBiography() == null) {
            logMessage.append("null proposalPersonBiography");
        }
        else {
            logMessage.append(getProposalPersonBiography().toString());
        }

        LOG.debug(logMessage);
    }

}
