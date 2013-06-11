/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi.questionnaire;

import java.util.ArrayList;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.springframework.util.CollectionUtils;

public class DisclosureQuestionnaireHelper extends QuestionnaireHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8685872555239368202L;
    
    private CoiDisclosure coiDisclosure;
    private boolean questionnairesLoaded;
    
    public DisclosureQuestionnaireHelper(CoiDisclosure coiDisclosure) {
        this.setAnswerHeaders(new ArrayList<AnswerHeader>());
        this.coiDisclosure = coiDisclosure;
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.COI_DISCLOSURE_MODULE_CODE;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        return new DisclosureModuleQuestionnaireBean(getCoiDisclosure());
    }
    
    
    public void prepareView(boolean reload) {
        initializePermissions(getCoiDisclosure());
        this.populateQuestionnaires(reload);
    }
    
    /*
     * authorization check.
     */
    protected void initializePermissions(CoiDisclosure CoiDisclosure) {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.ANSWER_COI_DISCLOSURE_QUESTIONNAIRE, CoiDisclosure);
        setAnswerQuestionnaire(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
    }
    
    
    protected void populateQuestionnaires(boolean reload) {
        boolean refreshed = false;
        if(!questionnairesLoaded || reload) {
            populateAnswers();
            refreshed = true;
        } 
        // have to update the child indicator, otherwise, the questionnaire may be hidden
        if (!refreshed && !CollectionUtils.isEmpty(this.getAnswerHeaders())) {
            for (AnswerHeader answerHeader : this.getAnswerHeaders()) {
                    getQuestionnaireAnswerService().setupChildAnswerIndicator(answerHeader);
                }

        }
    }
    
    public void populateAnswers() {
        super.populateAnswers();
        questionnairesLoaded = true;
    }
    
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }
    
    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }
    
    @Override
    public void preSave() {
        for (AnswerHeader answerHeader : this.getAnswerHeaders()) {
            answerHeader.setModuleItemKey(getCoiDisclosure().getCoiDisclosureId().toString());
        }
        super.preSave();
    }
}
