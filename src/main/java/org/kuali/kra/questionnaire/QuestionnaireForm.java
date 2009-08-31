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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class QuestionnaireForm extends KualiForm {
    private String retData;
    private String action;
    private Integer numOfQuestions;
    private Integer numOfUsages;

    /**
     * Constructs a QuestionnaireForm.
     */
    public QuestionnaireForm() {
        super();

    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        retData = "";
        action = "";

    }


    public String getRetData() {
        if (action.equals("setnumq")) {
             GlobalVariables.getUserSession().addObject("numOfQuestions", getNumOfQuestions());
             GlobalVariables.getUserSession().addObject("numOfUsages", getNumOfUsages());
        }
        return retData;
    }

    public void setRetData(String retData) {
        this.retData = retData;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(Integer numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public Integer getNumOfUsages() {
        return numOfUsages;
    }

    public void setNumOfUsages(Integer numOfUsages) {
        this.numOfUsages = numOfUsages;
    }

}
