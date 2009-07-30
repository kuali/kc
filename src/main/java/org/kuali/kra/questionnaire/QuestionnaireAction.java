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

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.SponsorHierarchyRule;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class QuestionnaireAction extends KualiAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        ActionForward forward = super.execute(mapping, form, request, response);
        String command = request.getParameter("command");
        if (StringUtils.isNotBlank(command)) {
            if (command.equals("edit")) {
                forward = edit(mapping, form, request, response);
            }
            else if (command.equals("copy")) {
                forward = copy(mapping, form, request, response);
            }
        }
        QuestionnaireForm questionnaireForm = (QuestionnaireForm) form;
        if (StringUtils.isNotBlank(questionnaireForm.getSqlScripts())) {
            if (questionnaireForm.getSqlScripts().equals("copyQuestionnaire")) {
                // copy questionnaire
                // ObjectUtils.deepCopy(src);
                Questionnaire fromQuestionnaire = getQuestionnaire(questionnaireForm.getFromQuestionnaire().getQuestionnaireId());
                Questionnaire toQuestionnaire = questionnaireForm.getNewQuestionnaire();
                KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(fromQuestionnaire, toQuestionnaire);
                questionnaireForm.setRetData("<h3>copied successfully<h3/>");
                questionnaireForm.setSqlScripts("");
            }
        }
        return forward;
    }

    public ActionForward copyQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        QuestionnaireForm questionnaireForm = (QuestionnaireForm) form;
        boolean rulePassed = new QuestionnaireRule().questionnaireRequiredFields(questionnaireForm);
        if (rulePassed) {
            Questionnaire fromQuestionnaire = getQuestionnaire(questionnaireForm.getFromQuestionnaire().getQuestionnaireId());
            Questionnaire toQuestionnaire = questionnaireForm.getNewQuestionnaire();
            KraServiceLocator.getService(QuestionnaireService.class).copyQuestionnaire(fromQuestionnaire, toQuestionnaire);
            questionnaireForm.setRetData("Questionnaire copied successfully");
        }
        return mapping.findForward("copy");
    }


    private ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QuestionnaireForm questionnaireForm = (QuestionnaireForm) form;
        questionnaireForm.setFromQuestionnaire(getQuestionnaire(Integer.parseInt(request.getParameter("questionnaireId"))));
        questionnaireForm.setNewQuestionnaire(getQuestionnaire(Integer.parseInt(request.getParameter("questionnaireId"))));
        String questions = assembleQuestions(questionnaireForm);
        String usages = assembleUsages(questionnaireForm.getFromQuestionnaire());
        questionnaireForm.setEditData(questions + "#;#" + usages);
        return mapping.findForward("edit");
    }

    private String assembleQuestions(QuestionnaireForm questionnaireForm) {

        Questionnaire questionnaire = questionnaireForm.getFromQuestionnaire();
        questionnaireForm.setQuestionNumber(0);
        sort(questionnaire.getQuestionnaireQuestions(), new QuestionnaireQuestionComparator());
        String result = "parent-0";
        Integer parentNumber = 0;
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (!question.getParentQuestionNumber().equals(0)) {
                remainQuestions.add((QuestionnaireQuestion) ObjectUtils.deepCopy(question));
            }
        }
        for (QuestionnaireQuestion question : questionnaire.getQuestionnaireQuestions()) {
            if (question.getQuestionNumber() > questionnaireForm.getQuestionNumber()) {
                questionnaireForm.setQuestionNumber(question.getQuestionNumber());
            }
            // TODO : for now just load the 1st level question for editing
            if (question.getParentQuestionNumber().equals(0)) {
                // if (question.getParentQuestionNumber().compareTo(parentNumber) > 0) {
                // parentNumber = question.getParentQuestionNumber();
                // result = result + "#q#parent-" + parentNumber;
                // }
                // qqid/qid/seq/desc/qtypeid/qnum/cond/condvalue/parentqnum
                String desc = question.getQuestion().getQuestion();
                // TODO : : need to deal with '"' in questio's description
                // also see QuestionLookupAction
                if (desc.indexOf("\"") > 0) {
                    desc = desc.replace("\"", "&#034;");
                }
                result = result + "#q#" + question.getQuestionnaireQuestionsId() + "#f#" + question.getQuestionRefIdFk() + "#f#"
                        + question.getQuestionSeqNumber() + "#f#" + desc + "#f#" + question.getQuestion().getQuestionTypeId()
                        + "#f#" + question.getQuestionNumber() + "#f#" + question.getCondition() + "#f#"
                        + question.getConditionValue() + "#f#" + question.getParentQuestionNumber();
                String childrenResult = getChildren(question, remainQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }

            }
        }
        questionnaireForm.setQuestionNumber(questionnaireForm.getQuestionNumber() + 1);
        // if (StringUtils.isNotBlank(result)) {
        // result = result.substring(0,result.length()-3);
        // }
        return result;


    }

    private String getChildren(QuestionnaireQuestion questionnaireQuestion, List<QuestionnaireQuestion> questionnaireQuestions) {
        String result = "";
        List<QuestionnaireQuestion> remainQuestions = new ArrayList<QuestionnaireQuestion>();
        for (QuestionnaireQuestion question : questionnaireQuestions) {
            if (question.getParentQuestionNumber().equals(questionnaireQuestion.getQuestionNumber())) {
                String desc = question.getQuestion().getQuestion();
                if (desc.indexOf("\"") > 0) {
                    desc = desc.replace("\"", "&#034;");
                }
                result = result + "#q#" + question.getQuestionnaireQuestionsId() + "#f#" + question.getQuestionRefIdFk()+ "#f#"
                        + question.getQuestionSeqNumber() + "#f#" + desc + "#f#" + question.getQuestion().getQuestionTypeId()
                        + "#f#" + question.getQuestionNumber() + "#f#" + question.getCondition() + "#f#"
                        + question.getConditionValue() + "#f#" + question.getParentQuestionNumber();
                // TODO : not efficient. should only check the questions that have not checked
                // remainQuestions = ObjectUtils.deepCopy(questionnaireQuestions);
                String childrenResult = getChildren(question, questionnaireQuestions);
                if (StringUtils.isNotBlank(childrenResult)) {
                    result = result + childrenResult;
                }
            }
        }
        return result;
    }

    private String assembleUsages(Questionnaire questionnaire) {
        String result = "";
        for (QuestionnaireUsage questionnaireUsage : questionnaire.getQuestionnaireUsages()) {
            // quid/modulecode/label
            result = result + questionnaireUsage.getQuestionnaireUsageId() + "#f#" + questionnaireUsage.getModuleItemCode() + "#f#"
                    + questionnaireUsage.getQuestionnaireLabel() + "#u#";
        }
        if (StringUtils.isNotBlank(result)) {
            result = result.substring(0, result.length() - 3);
        }
        return result;

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
        return (Questionnaire) KraServiceLocator.getService(BusinessObjectService.class)
                .findByPrimaryKey(Questionnaire.class, qMap);

    }
}
