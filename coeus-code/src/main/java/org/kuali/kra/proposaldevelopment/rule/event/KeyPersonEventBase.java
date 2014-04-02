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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.krad.util.ObjectUtils;


/**
 * Base implementation for events triggered when a Key Person state is modified on a 
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.6 $
 */
public abstract class KeyPersonEventBase extends KcDocumentEventBase implements KeyPersonEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonEventBase.class);
    
    private ProposalPerson person;
    
    protected KeyPersonEventBase(String description, ProposalDevelopmentDocument document, ProposalPerson person) {
        this(description, "", document, person);
    }

    protected KeyPersonEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document, ProposalPerson person) {
        super(description, errorPathPrefix, document);

        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.person = (ProposalPerson) ObjectUtils.deepCopy(person);
        
        logEvent();
    }

    /**
     * @return <code>{@link ProposalPerson}</code> that triggered this event.
     */
    public ProposalPerson getProposalPerson() {
        return person;
    }

    @Override
    public void validate() {
        super.validate();
        if (getProposalPerson() == null) {
            throw new IllegalArgumentException("invalid (null) proposal person");
        }
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (getProposalPerson() == null) {
            logMessage.append("null proposalPerson");
        }
        else {
            logMessage.append(getProposalPerson().toString());
        }

        LOG.debug(logMessage);
    }
}
