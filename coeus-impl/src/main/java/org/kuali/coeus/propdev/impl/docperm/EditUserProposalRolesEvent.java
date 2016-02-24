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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.List;

/**
 * The EditUserProposalRolesEvent is generated when the proposal roles for a
 * user has been modified.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class EditUserProposalRolesEvent extends KcDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(EditUserProposalRolesEvent.class);
    
    private ProposalUserRoles editRoles;
    private List<ProposalUserRoles> list;
    
    /**
     * Constructs an AddAbstractEvent.
     * 
     * @param document proposal development document
     * @param list the list of proposal user roles
     * @param proposalAbstract the proposal abstract that is being added
     */
    public EditUserProposalRolesEvent(ProposalDevelopmentDocument document, List<ProposalUserRoles> list, ProposalUserRoles editRoles) {
        super("editing proposal roles for document " + getDocumentId(document), "", document);
        
        this.list = list;
        
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        
        this.editRoles = (ProposalUserRoles) ObjectUtils.deepCopy(editRoles);
    
        logEvent();
    }
    
    @Override
    public void validate() {
        super.validate();
        if (this.editRoles == null) {
            throw new IllegalArgumentException("invalid (null) edit roles");
        }
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.editRoles == null) {
            logMessage.append("null editRoles");
        }
        else {
            logMessage.append(this.editRoles.toString());
        }

        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return PermissionsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((PermissionsRule) rule).processEditProposalUserRolesBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                                  this.list, this.editRoles);
    }
}
