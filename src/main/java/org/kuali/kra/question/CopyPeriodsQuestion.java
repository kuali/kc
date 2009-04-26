/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.question;

import java.util.ArrayList;

import org.kuali.rice.kns.question.QuestionBase;

public class CopyPeriodsQuestion extends QuestionBase {
    
    public static final String ONE = "0";
    public static final String ALL = "1";
    
    /**
     * @param question
     * @param buttons
     */
    public CopyPeriodsQuestion() {
        // this should be set by question form
        super("Confirm", new ArrayList(2));
        ArrayList<String> buttons = new ArrayList<String>();
        buttons.add("copyoneper");
        buttons.add("copyallperiods");
        super.setButtons(buttons);

    }

}
