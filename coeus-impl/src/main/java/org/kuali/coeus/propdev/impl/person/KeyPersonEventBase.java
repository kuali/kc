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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
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
