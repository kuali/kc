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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * The NewNarrativeUserRightsEvent is generated when the User Rights for
 * a narrative are to be changed.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NewNarrativeUserRightsEvent extends KcDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NewNarrativeUserRightsEvent.class);
    
    private List<NarrativeUserRights> newNarrativeUserRights;
    private int narrativeIndex;
    
    /**
     * Constructs a NewNarrativeUserRightsEvent.
     * @param document proposal development document
     * @param newNarrativeUserRights the list of user rights for a narrative
     * @param narrativeIndex the narrative to change in the proposal
     */
    public NewNarrativeUserRightsEvent(ProposalDevelopmentDocument document, List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        super("editing narrative user rights to document " + getDocumentId(document), "", document);
    
        // by doing a deep copy, we are ensuring that the business rule class can't update
        // the original object by reference
        this.newNarrativeUserRights = (List<NarrativeUserRights>) ObjectUtils.deepCopy((Serializable) newNarrativeUserRights);
        this.narrativeIndex = narrativeIndex;
        
        logEvent();
    }
    
    @Override
    public void validate() {
        super.validate();
        if (this.newNarrativeUserRights == null) {
            throw new IllegalArgumentException("invalid (null) newNarrativeUserRights");
        }
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (this.newNarrativeUserRights == null) {
            logMessage.append("null newNarrativeUserRights");
        }
        else {
            logMessage.append(this.newNarrativeUserRights.toString());
            logMessage.append(Integer.toString(this.narrativeIndex));
        }

        LOG.debug(logMessage);
    }

    @Override
    public Class getRuleInterfaceClass() {
        return NewNarrativeUserRightsRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NewNarrativeUserRightsRule) rule).processNewNarrativeUserRightsBusinessRules((ProposalDevelopmentDocument) this.getDocument(), 
                                                                        this.newNarrativeUserRights, this.narrativeIndex);
    }
}
