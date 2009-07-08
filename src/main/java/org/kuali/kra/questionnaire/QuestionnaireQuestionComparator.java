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

import java.util.Comparator;

public class QuestionnaireQuestionComparator implements Comparator<QuestionnaireQuestion>  {

    public int compare(QuestionnaireQuestion q1, QuestionnaireQuestion q2) {
        int retval = 0;
        retval = q1.getParentQuestionNumber().compareTo(q2.getParentQuestionNumber());
        if (retval == 0) {
            retval = q1.getQuestionSeqNumber().compareTo(q2.getQuestionSeqNumber());
        }
        return retval;
    }

}
