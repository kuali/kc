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
package org.kuali.kra.protocol.actions.print;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Comparator;

/**
 * 
 * This class is to implement the comparator questionnaire print items.
 */
public class QuestionnairePrintOptionComparator implements Comparator<QuestionnairePrintOption> {


    public int compare(QuestionnairePrintOption q1, QuestionnairePrintOption q2) {
        int retval = 0;
        retval = q1.getQuestionnaireSeqId().compareTo(q2.getQuestionnaireSeqId());
        if (retval == 0) {
            if (ObjectUtils.equals(q1.getItemKey(), q2.getItemKey())) {
                if (ObjectUtils.equals(q1.getSubItemCode(), q2.getSubItemCode())) {
                    retval = q1.getSubItemKey().compareTo(q2.getSubItemKey());
                }
                else {
                    retval = q1.getSubItemCode().compareTo(q2.getSubItemCode());
                }
            }
            else {
                retval = q1.getItemKey().compareTo(q2.getItemKey());
            }
        }
        return retval;
    }


}
