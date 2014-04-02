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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.List;

/**
 * The AddProposalUserEvent is generated when a Proposal User is to be added to
 * a Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AddProposalUserEvent extends KcDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddProposalUserEvent.class);
    
    private ProposalUser proposalUser;
    private List<ProposalUserRoles> list;
    
    /**
     * Constructs an AddProposalUserEvent.
     * 
     * @param document proposal development document
     * @param list the list of proposal user roles
     * @param proposalUser the proposal user that is being added
     */
    public AddProposalUserEvent(ProposalDevelopmentDocument document, List<ProposalUserRoles> list, ProposalUser proposalUser) {
        super("adding proposal user to document " + getDocumentId(document), "", document);
        
        this.list = list;
        
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        
        this.proposalUser = (ProposalUser) ObjectUtils.deepCopy(proposalUser);
    
        logEvent();
    }
    
    @Override
    public void validate() {
        super.validate();
        if (this.proposalUser == null) {
            throw new IllegalArgumentException("invalid (null) proposal user");
        }
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.proposalUser == null) {
            logMessage.append("null proposalUser");
        }
        else {
            logMessage.append(this.proposalUser.toString());
        }

        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processAddProposalUserBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                            list, this.proposalUser);
    }
}
