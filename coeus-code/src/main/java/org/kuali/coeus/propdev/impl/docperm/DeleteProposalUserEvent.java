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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * The DeleteProposalUserEvent is generated when a Proposal User is to be delete from
 * a Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class DeleteProposalUserEvent extends KcDocumentEventBase {
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
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");
        logMessage.append(this.index);
        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processDeleteProposalUserBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                               list, index);
    }
}
