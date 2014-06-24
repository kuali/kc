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
