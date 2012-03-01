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
package org.kuali.kra.coi.questionnaire;

import java.util.ArrayList;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.questionnaire.QuestionnaireHelperBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.springframework.util.CollectionUtils;

public class DisclosureQuestionnaireHelper extends QuestionnaireHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8685872555239368202L;

    
    private CoiDisclosureForm form;
    
    public DisclosureQuestionnaireHelper(CoiDisclosureForm coiDisclosureForm) {
        this.setAnswerHeaders(new ArrayList<AnswerHeader>());
        this.form = coiDisclosureForm;
    }

    @Override
    public String getModuleCode() {
        return CoeusModule.COI_DISCLOSURE_MODULE_CODE;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        return new DisclosureModuleQuestionnaireBean(getDisclosure());
    }
    
    private CoiDisclosure getDisclosure() {
        CoiDisclosureDocument document = (CoiDisclosureDocument) form.getDocument();
        if (document == null || document.getCoiDisclosure() == null) {
            throw new IllegalArgumentException("invalid (null) CoiDisclosureDocument in CoiDisclosureForm");
        }
        return document.getCoiDisclosure();
    }
    
    public void prepareView() {
        // TODO check permissions here
        setAnswerQuestionnaire(true);
        this.populateQuestionnaires();
    }
    
    private void populateQuestionnaires() {
        this.setAnswerHeaders(new ArrayList<AnswerHeader>());
        super.populateAnswers();
    }

}
