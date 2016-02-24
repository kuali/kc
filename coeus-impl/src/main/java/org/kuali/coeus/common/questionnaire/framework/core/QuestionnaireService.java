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
package org.kuali.coeus.common.questionnaire.framework.core;

import java.util.List;

public interface QuestionnaireService {

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
    public boolean isQuestionnaireNameExist(String questionnaireId, String name);
    /**
     * 
     * This method the modules code that the user has permission to associate to questionnaire
     * @return
     */
    public  List<String>  getAssociateModules();
    
    /**
     * Returns true if the usage passed in is unique. Determined by checking for matching coeus module and coeus sub module codes.
     * @param usage
     * @return
     */
    public boolean isUniqueUsage(Questionnaire questionnaire, QuestionnaireUsage usage);
    
    /**
     * Returns true of the questionnaire is the newest version of the questionnaire.
     * @param questionnaire
     * @return
     */
    public boolean isCurrentQuestionnaire(Questionnaire questionnaire);

}
