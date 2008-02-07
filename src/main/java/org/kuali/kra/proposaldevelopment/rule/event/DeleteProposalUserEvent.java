/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rule.event;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * The DeleteProposalUserEvent is generated when a Proposal User is to be delete from
 * a Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class DeleteProposalUserEvent extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(DeleteProposalUserEvent.class);
    
    private int index;
    private List<ProposalUserRoles> list;
    
    /**
     * Constructs an DeleteProposalUserEvent.
     * 
     * @param document proposal development document
     * @param list the list of proposal user roles
     * @param index the index into the list corresponding to the user to delete
     */
    public DeleteProposalUserEvent(ProposalDevelopmentDocument document, List<ProposalUserRoles> list, int index) {
        super("delete proposal user from to document " + getDocumentId(document), "", document);
    
        this.list = list;
        this.index = index;
    
        logEvent();
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        logMessage.append(this.index);
        LOG.debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processDeleteProposalUserBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                               list, index);
    }
}
