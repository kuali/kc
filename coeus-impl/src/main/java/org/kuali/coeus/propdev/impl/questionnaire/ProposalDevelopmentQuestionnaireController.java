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
package org.kuali.coeus.propdev.impl.questionnaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.print.ProposalDevelopmentPrintingService;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProposalDevelopmentQuestionnaireController extends ProposalDevelopmentControllerBase {


    @Autowired
    @Qualifier("questionnairePrintingService")
    private QuestionnairePrintingService questionnairePrintingService;

    @Autowired
    @Qualifier("proposalDevelopmentPrintingService")
    private ProposalDevelopmentPrintingService proposalDevelopmentPrintingService;

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-QuestionnairePage"})
    public ModelAndView navigateToQuestionnaire(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).populateQuestionnaires(form);
        return super.navigate(form,result,request,response);
    }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveQuestionnaire")
   public ModelAndView saveQuestionnaire(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       pdForm.getQuestionnaireHelper().preSave();
       getLegacyDataAdapter().save(pdForm.getQuestionnaireHelper().getAnswerHeaders());
       pdForm.getQuestionnaireHelper().populateAnswers();
       return super.save(pdForm, result, request, response);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=clearQuestionnaire")
    public ModelAndView clearQuestionnaire(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response,
                                           @RequestParam("actionParameters[index]") String index,  @RequestParam("actionParameters[helper]") String helper ) throws Exception {
        if(helper.equals("questionnaireHelper")) {
            clearAnswers(form.getQuestionnaireHelper().getAnswerHeaders().get(Integer.parseInt(index)).getAnswers());
        } else if (helper.equals("s2sQuestionnaireHelper")) {
            clearAnswers(form.getS2sQuestionnaireHelper().getAnswerHeaders().get(Integer.parseInt(index)).getAnswers());
        }

        return super.save(form);
    }

    protected void clearAnswers(List<Answer> answers) {
        for (Answer answer: answers) {
            answer.setAnswer(null);
        }
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareUpdateDialog")
    public ModelAndView prepareUpdateDialog(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                            @RequestParam("actionParameters[index]") String index,  @RequestParam("actionParameters[helper]") String helper ) throws Exception {

        if(helper.equals("questionnaireHelper")) {
            form.setUpdateAnswerHeader(form.getQuestionnaireHelper().getAnswerHeaders().get(Integer.parseInt(index)));
        } else if (helper.equals("s2sQuestionnaireHelper")) {
            form.setUpdateAnswerHeader(form.getS2sQuestionnaireHelper().getAnswerHeaders().get(Integer.parseInt(index)));
        }

        return getModelAndViewService().showDialog("PropDev-QuestionnairePage-UpdateDialog",true,form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=updateQuestionnaire")
    public ModelAndView updateQuestionnaire(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {

        if (form.getQuestionnaireHelper().getAnswerHeaders().contains(form.getUpdateAnswerHeader())){
            int index = form.getQuestionnaireHelper().getAnswerHeaders().indexOf(form.getUpdateAnswerHeader());
            form.getQuestionnaireHelper().updateQuestionnaireAnswer(index);
        } else if (form.getS2sQuestionnaireHelper().getAnswerHeaders().contains(form.getUpdateAnswerHeader())){
            int index = form.getS2sQuestionnaireHelper().getAnswerHeaders().indexOf(form.getUpdateAnswerHeader());
            form.getS2sQuestionnaireHelper().updateQuestionnaireAnswer(index);
        }

        form.setUpdateAnswerHeader(new AnswerHeader());
        return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printQuestionnaire")
    public ModelAndView printQuestionnaire(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response,
                                           @RequestParam("index") int index,  @RequestParam("helper") String helper ) throws Exception {

        Map<String, Object> reportParameters = new HashMap<String, Object>();
        AttachmentDataSource dataStream = null;
        if (StringUtils.equals(helper, "questionnaireHelper")) {
            reportParameters.putAll(createReportParameters(form.getQuestionnaireHelper().getAnswerHeaders().get(index)));
            reportParameters.put("coeusModuleSubItemCode", CoeusSubModule.ZERO_SUBMODULE);
            dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(form.getDevelopmentProposal(), reportParameters);
        } else if (StringUtils.equals(helper, "s2sQuestionnaireHelper")) {
            reportParameters.putAll(createReportParameters(form.getS2sQuestionnaireHelper().getAnswerHeaders().get(index)));
            reportParameters.put("coeusModuleSubItemCode", CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
            dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(form.getDevelopmentProposal(), reportParameters);
        } else if (StringUtils.equals(helper, "proposalPersonQuestionnaireHelper")) {
            reportParameters.putAll(createReportParameters(form.getProposalPersonQuestionnaireHelper().getAnswerHeaders().get(index)));
            dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(form.getProposalPersonQuestionnaireHelper().getProposalPerson(), reportParameters);
        } else {
            throw new RuntimeException(String.format("Do not know how to process printQuestionnaireAnswer for formProperty %s",helper));
        }



        if (dataStream.getData() != null) {
            ControllerFileUtils.streamToResponse(dataStream, response);
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printAllCertifications")
    public ModelAndView printAllCertifications(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response) throws Exception {

        AttachmentDataSource dataStream =
                getProposalDevelopmentPrintingService().printPersonCertificationQuestionnaire(form.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons());

        if (dataStream.getData() != null) {
            ControllerFileUtils.streamToResponse(dataStream, response);
        }

        return getModelAndViewService().getModelAndView(form);
    }

    protected Map<String, Object> createReportParameters(AnswerHeader answerHeader) {

        Map<String, Object> reportParameters = new HashMap<String, Object>();

        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, answerHeader.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
        reportParameters.put("template", answerHeader.getQuestionnaire().getTemplate());
        reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_ID_PARAMETER_NAME,answerHeader.getQuestionnaire().getId());

        return reportParameters;
    }

    public QuestionnairePrintingService getQuestionnairePrintingService() {
        return questionnairePrintingService;
    }

    public void setQuestionnairePrintingService(QuestionnairePrintingService questionnairePrintingService) {
        this.questionnairePrintingService = questionnairePrintingService;
    }

    public ProposalDevelopmentPrintingService getProposalDevelopmentPrintingService() {
        return proposalDevelopmentPrintingService;
    }

    public void setProposalDevelopmentPrintingService(ProposalDevelopmentPrintingService proposalDevelopmentPrintingService) {
        this.proposalDevelopmentPrintingService = proposalDevelopmentPrintingService;
    }
}
