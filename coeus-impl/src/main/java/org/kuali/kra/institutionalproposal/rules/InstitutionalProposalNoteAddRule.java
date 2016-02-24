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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.core.api.util.RiceKeyConstants;

/**
 * 
 * This class implements the business rule for adding institutional proposal note.
 */
public class InstitutionalProposalNoteAddRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<InstitutionalProposalNoteAddEvent> {

    private ErrorReporter errorReporter;

    /**
     * 
     * This method is to validate that new Institutional Proposal note exist
     * @param event
     * @return
     */
    public boolean processRules(InstitutionalProposalNoteAddEvent event) {
        boolean rulePassed = true;
        errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        if (StringUtils.isBlank(event.getInstitutionalProposalNotepad().getNoteTopic())) {
            errorReporter.reportError("institutionalProposalNotepadBean.newInstitutionalProposalNotepad.noteTopic", RiceKeyConstants.ERROR_REQUIRED, new String[] {"Note Topic"});
                                      
            rulePassed = false;
        } 
        if (StringUtils.isBlank(event.getInstitutionalProposalNotepad().getComments())) {
            errorReporter.reportError("institutionalProposalNotepadBean.newInstitutionalProposalNotepad.comments", RiceKeyConstants.ERROR_REQUIRED, new String[] {"Note Text"});
            rulePassed = false;
        } 
        return rulePassed;
    }
}
