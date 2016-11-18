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
package org.kuali.coeus.s2sgen.impl.generate.support;


import org.apache.pdfbox.io.IOUtils;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class RROtherProjectInfoBaseGeneratorTest extends S2STestBase {
    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        saveAnswers(document);

        List<Narrative> naList = new ArrayList<>();

        {
            NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
                    ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader
                    .getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
            InputStream inputStream = resource.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] narrativePdf = IOUtils.toByteArray(bis);
            narrativeAttachment.setData(narrativePdf);
            narrativeAttachment.setName("exercise1");

            Narrative narrative = new Narrative();

            narrative.setDevelopmentProposal(document.getDevelopmentProposal());
            narrative.setModuleNumber(1);
            narrative.setModuleSequenceNumber(1);
            narrative.setModuleStatusCode("C");
            narrative.setObjectId(UUID.randomUUID().toString());
            narrative.setName("exercise1");
            narrative.setNarrativeTypeCode("1");

            NarrativeType narrativeType = new NarrativeType();
            narrativeType.setCode("1");
            narrativeType.setAllowMultiple(true);
            narrativeType.setSystemGenerated(false);
            narrativeType.setDescription("An attachment narrative");

            narrative.setModuleTitle("An attachment");
            narrative.setNarrativeType(narrativeType);
            narrative.setNarrativeAttachment(narrativeAttachment);

            naList.add(narrative);
        }

        {
            NarrativeAttachment narrativeAttachment2 = new NarrativeAttachment();
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
                    ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader
                    .getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
            InputStream inputStream = resource.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] narrativePdf = IOUtils.toByteArray(bis);
            narrativeAttachment2.setData(narrativePdf);
            narrativeAttachment2.setName("exercise1");

            Narrative narrative2 = new Narrative();
            narrative2.setDevelopmentProposal(document.getDevelopmentProposal());
            narrative2.setModuleNumber(2);
            narrative2.setModuleSequenceNumber(1);
            narrative2.setModuleStatusCode("C");
            narrative2.setObjectId(UUID.randomUUID().toString());
            narrative2.setName("exercise1");
            narrative2.setNarrativeTypeCode("5");
            NarrativeType narrativeType2 = new NarrativeType();
            narrativeType2.setCode("5");
            narrativeType2.setAllowMultiple(true);
            narrativeType2.setSystemGenerated(false);
            narrativeType2.setDescription("An attachment");
            narrative2.setModuleTitle("An attachment");
            narrative2.setNarrativeType(narrativeType2);

            narrative2.setNarrativeAttachment(narrativeAttachment2);

            naList.add(narrative2);
        }

        {
            NarrativeAttachment narrativeAttachment3 = new NarrativeAttachment();
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader(
                    ClassLoaderUtils.getDefaultClassLoader());
            Resource resource = resourceLoader
                    .getResource(S2STestConstants.ATT_PACKAGE + "/exercise2.pdf");
            InputStream inputStream = resource.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] narrativePdf = IOUtils.toByteArray(bis);
            narrativeAttachment3.setData(narrativePdf);
            narrativeAttachment3.setName("exercise1");

            Narrative narrative3 = new Narrative();
            narrative3.setDevelopmentProposal(document.getDevelopmentProposal());
            narrative3.setModuleNumber(3);
            narrative3.setModuleSequenceNumber(1);
            narrative3.setModuleStatusCode("C");
            narrative3.setObjectId(UUID.randomUUID().toString());
            narrative3.setName("exercise1");
            narrative3.setNarrativeTypeCode("8");
            NarrativeType narrativeType3 = new NarrativeType();
            narrativeType3.setCode("5");
            narrativeType3.setAllowMultiple(true);
            narrativeType3.setSystemGenerated(false);
            narrativeType3.setDescription("An attachment");
            narrative3.setModuleTitle("An attachment");
            narrative3.setNarrativeType(narrativeType3);

            narrative3.setNarrativeAttachment(narrativeAttachment3);

            naList.add(narrative3);
        }
        document.getDevelopmentProposal().setNarratives(naList);
    }

    protected void saveAnswers(ProposalDevelopmentDocument document) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
        final List<AnswerHeader> answerHeaders = KcServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(moduleQuestionnaireBean);

        answerHeaders.stream()
                .filter(answerHeader -> answerHeader != null)
                .forEach(answerHeader -> {
            List<Answer> answerDetails = answerHeader.getAnswers();
            for (Answer answers : answerDetails) {
                if (Integer.valueOf(122).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("Y");
                    answers.getQuestionnaireQuestion().setRuleId("");
                } else if (Integer.valueOf(123).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("N");
                    answers.getQuestionnaireQuestion().setRuleId("");
                } else if (Integer.valueOf(124).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("Y");
                    answers.getQuestionnaireQuestion().setRuleId("");
                } else if (Integer.valueOf(125).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("N");
                    answers.getQuestionnaireQuestion().setRuleId("");
                } else if (Integer.valueOf(126).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("Y");
                    answers.getQuestionnaireQuestion().setRuleId("");
                } else if (Integer.valueOf(127).equals(answers.getQuestion().getQuestionSeqId())) {
                    answers.setAnswer("Y");
                    answers.getQuestionnaireQuestion().setRuleId("");
                }
            }
        });
        KcServiceLocator.getService(BusinessObjectService.class).save(answerHeaders);

    }


}
