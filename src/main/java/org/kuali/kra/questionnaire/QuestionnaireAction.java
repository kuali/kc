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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class QuestionnaireAction extends KualiAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        String command = request.getParameter("command");
        if (StringUtils.isNotBlank(command)) {
            Integer questionnaireId = Integer.parseInt(request.getParameter("questionnaireId"));
            Map<String, Integer> qMap = new HashMap<String, Integer>();
            qMap.put("questionnaireId", questionnaireId);
            Questionnaire questionnaire = (Questionnaire)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(Questionnaire.class, qMap);
            if (command.equals("edit")) {
                forward = edit(mapping, form, request, response);
            } else if (command.equals("copy")) {
                forward = copy(mapping, form, request, response);
            }
        }
        QuestionnaireForm questionnaireForm = (QuestionnaireForm)form;
        if (StringUtils.isNotBlank(questionnaireForm.getSqlScripts())) {
            if (questionnaireForm.getSqlScripts().equals("copyQuestionnaire")) {
                // copy questionnaire
                //ObjectUtils.deepCopy(src);
                Questionnaire fromQuestionnaire = getQuestionnaire(questionnaireForm.getFromQuestionnaire().getQuestionnaireId());
                Questionnaire toQuestionnaire = questionnaireForm.getNewQuestionnaire();
                KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(fromQuestionnaire, toQuestionnaire);
                
                questionnaireForm.setSqlScripts("");
            }
        }
        return forward;
    }

    private ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
       throws Exception {
        
        QuestionnaireForm questionnaireForm = (QuestionnaireForm)form;
        
        return mapping.findForward("edit");
    }

    private ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QuestionnaireForm questionnaireForm = (QuestionnaireForm) form;
        questionnaireForm.setFromQuestionnaire(getQuestionnaire(Integer.parseInt(request.getParameter("questionnaireId"))));
        return mapping.findForward("copy");
    }
    
    
    private Questionnaire getQuestionnaire(Integer questionnaireId) {
        Map<String, Integer> qMap = new HashMap<String, Integer>();
        qMap.put("questionnaireId", questionnaireId);
        return (Questionnaire)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(Questionnaire.class, qMap);
        
    }
}
