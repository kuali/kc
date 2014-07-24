/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
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
