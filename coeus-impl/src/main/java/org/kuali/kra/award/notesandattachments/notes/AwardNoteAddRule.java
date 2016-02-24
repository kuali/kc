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
package org.kuali.kra.award.notesandattachments.notes;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;

/**
 * 
 * This class implements the business rule for adding award note.
 */
public class AwardNoteAddRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AwardNoteAddEvent> {



    /**
     * 
     * This method is to validate that new award note exist
     * @param event
     * @return
     */
    public boolean processRules(AwardNoteAddEvent event) {
        boolean rulePassed = true;
        if (StringUtils.isBlank(event.getAwardNotepad().getNoteTopic())) {
            this.getErrorReporter().reportError("awardNotepadBean.newAwardNotepad.noteTopic", RiceKeyConstants.ERROR_REQUIRED, new String[] {"Note Topic"});
            rulePassed = false;
        } 
        return rulePassed;
    }


}
