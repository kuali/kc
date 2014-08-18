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
