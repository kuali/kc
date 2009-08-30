/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import java.util.List;

public interface QuestionnaireService {
    void saveQuestionnaire(String sqlScripts, Questionnaire questionnaire);
    /**
     * 
     * This method is called to copy the source questionnaire to target questionnaire and save to DB
     * @param src : source questionnaire
     * @param dest : target questionnaire
     */
    public void copyQuestionnaire(Questionnaire src, Questionnaire dest);
    /**
     * 
     * This method is to check whether questionnaire name has been used
     * @param questionnaireId
     * @param name
     * @return
     */
    public boolean isQuestionnaireNameExist(Integer questionnaireId, String name);
    /**
     * 
     * This method the modules code that the user has permission to associate to questionnaire
     * @return
     */
    public  List<String>  getAssociateModules();

}
