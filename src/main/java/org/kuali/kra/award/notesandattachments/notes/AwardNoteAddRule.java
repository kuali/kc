/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.notesandattachments.notes;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;

/**
 * 
 * This class implements the business rule for adding award note.
 */
public class AwardNoteAddRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AwardNoteAddEvent> {

    private ErrorReporter errorReporter;

    /**
     * 
     * This method is to validate that new award note exist
     * @param event
     * @return
     */
    public boolean processRules(AwardNoteAddEvent event) {
        boolean rulePassed = true;
        errorReporter = new ErrorReporter();
        if (StringUtils.isBlank(event.getAwardNotepad().getNoteTopic())) {
            errorReporter.reportError("awardNotepadBean.newAwardNotepad.noteTopic", RiceKeyConstants.ERROR_REQUIRED, new String[] {"Note Topic"});
            rulePassed = false;
        } 
        return rulePassed;
    }


}
