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

/**
 * 
 * This class determines what functions must be present on a form that uses questionnaire in more than one use.
 * Note, all implementing functions must contain the same number of array elements.
 * KraTransactionalFormBase.populateFalseCheckboxes will call these function to determine if a field is
 * associated with a questionnaire answer field.
 */
public interface MultiQuestionableFormInterface {
    public static final String DEFAULT_MIDDLE = QuestionableFormInterface.DEFAULT_MIDDLE;
    public static final String DEFAULT_END = QuestionableFormInterface.DEFAULT_END;
    
    public String[] getQuestionnaireFieldStarters();
    public String[] getQuestionnaireFieldMiddles();
    public String[] getQuestionnaireFieldEnds();
}
