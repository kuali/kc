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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.propdev.impl.attachment.NarrativeEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * The CopyProposalEvent is generated when a user wants to copy a 
 * Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CopyProposalEvent extends KcDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NarrativeEventBase.class);
    
    private ProposalCopyCriteria criteria;
    
    /**
     * Constructs an CopyProposalEvent.
     * 
     * @param document proposal development document
     * @param criteria the proposal copy criteria
     */
    public CopyProposalEvent(ProposalDevelopmentDocument document, ProposalCopyCriteria criteria) {
        super("copy proposal document " + getDocumentId(document), "", document);
    
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.criteria = (ProposalCopyCriteria) ObjectUtils.deepCopy(criteria);
    
        logEvent();
    }
    
    @Override
    public void validate() {
        super.validate();
        if (this.criteria == null) {
            throw new IllegalArgumentException("invalid (null) proposal copy criteria");
        }
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.criteria == null) {
            logMessage.append("null proposal copy criteria");
        }
        else {
            logMessage.append(this.criteria.toString());
        }

        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return CopyProposalRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((CopyProposalRule) rule).processCopyProposalBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                           this.criteria);
    }
}
