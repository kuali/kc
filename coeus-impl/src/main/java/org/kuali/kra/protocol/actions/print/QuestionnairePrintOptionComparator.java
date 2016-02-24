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
