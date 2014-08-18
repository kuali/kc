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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.util.ObjectUtils;

public abstract class PersonnelAttachmentEventBase extends KcDocumentEventBase implements PersonnelAttachmentEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(PersonnelAttachmentEventBase.class);

    private ProposalPersonBiography proposalPersonBiography;

    protected PersonnelAttachmentEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
            ProposalPersonBiography proposalPersonBiography) {
        super(description, errorPathPrefix, document);


        if ( proposalPersonBiography != null ) {
            // by doing a deep copy, we are ensuring that the business rule class can't update
            // the original object by reference
            this.proposalPersonBiography = (ProposalPersonBiography) ObjectUtils.deepCopy(proposalPersonBiography);
            // personnelattachmentfile will be lost during deepcopy, so implement filename this way.
            if (proposalPersonBiography.getPersonnelAttachmentFile() != null) {
                this.proposalPersonBiography.setName(proposalPersonBiography.getPersonnelAttachmentFile().getFileName());
                this.proposalPersonBiography.setType(proposalPersonBiography.getPersonnelAttachmentFile().getContentType());
            }
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
