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
package org.kuali.coeus.propdev.impl.s2s.question;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms.S2sOppFormsId;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.rice.kew.impl.document.WorkflowDocumentImpl;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProposalDevelopmentS2sQuestionnaireServiceImplTest {

	private Mockery context;
	private ProposalDevelopmentS2sQuestionnaireServiceImpl s2sQuestionnaireServiceImpl;
	private BusinessObjectService businessObjectService;
	private QuestionnaireAnswerService questionnaireAnswerService;
	private QuestionnaireAnswerServiceImpl answerServiceImpl;

	private static final String MODULE_ITEM_CODE = "moduleItemCode";
	private static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
	private static final String MODULE_ITEM_KEY = "moduleItemKey";
	private static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery() {{setThreadingPolicy(new Synchroniser());}};
		s2sQuestionnaireServiceImpl = new ProposalDevelopmentS2sQuestionnaireServiceImpl();
		businessObjectService = context.mock(BusinessObjectService.class);
		questionnaireAnswerService = context.mock(QuestionnaireAnswerService.class);
		s2sQuestionnaireServiceImpl.setQuestionnaireAnswerService(questionnaireAnswerService);
		s2sQuestionnaireServiceImpl.setBusinessObjectService(businessObjectService);
		answerServiceImpl = new QuestionnaireAnswerServiceImpl();
	}

    @Test
	public void test_getProposalS2sAnswerHeaders_withoutS2sOpportunity() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		proposal.setS2sOpportunity(null);
		final List<AnswerHeader> expectedAnswerHeaders = new ArrayList<AnswerHeader>();
		
		final ModuleQuestionnaireBean answerHeaderQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues1 = new HashMap<String, String>();
		fieldValues1.put(MODULE_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleItemCode());
		fieldValues1.put(MODULE_SUB_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleSubItemCode());
		fieldValues1.put(MODULE_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleItemKey());
		fieldValues1.put(MODULE_SUB_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleSubItemKey());

		final ModuleQuestionnaireBean usagesQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues2 = new HashMap<String, String>();
		fieldValues2.put(MODULE_ITEM_CODE, usagesQuestionnaireBean.getModuleItemCode());
		fieldValues2.put(MODULE_SUB_ITEM_CODE, usagesQuestionnaireBean.getModuleSubItemCode());

		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));

		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).findMatching(AnswerHeader.class, fieldValues1);
				will(returnValue(expectedAnswerHeaders));
				oneOf(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues2);
				will(returnValue(usages));
				oneOf(questionnaireAnswerService).getQuestionnaireAnswer(answerHeaderQuestionnaireBean);
				will(returnValue(expectedAnswerHeaders));
			}
		});
		final List<AnswerHeader> answerHeaders = s2sQuestionnaireServiceImpl.getProposalS2sAnswerHeaders(proposal);
		assertTrue(answerHeaders.isEmpty());
		assertEquals(expectedAnswerHeaders, answerHeaders);
	}

    @Test
	public void test_getProposalS2sAnswerHeaders_empty_questionnaireUsage() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final List<AnswerHeader> expectedAnswerHeaders = new ArrayList<AnswerHeader>();
		
		final ModuleQuestionnaireBean answerHeaderQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues1 = new HashMap<String, String>();
		fieldValues1.put(MODULE_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleItemCode());
		fieldValues1.put(MODULE_SUB_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleSubItemCode());
		fieldValues1.put(MODULE_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleItemKey());
		fieldValues1.put(MODULE_SUB_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleSubItemKey());

		final ModuleQuestionnaireBean usagesQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues2 = new HashMap<String, String>();
		fieldValues2.put(MODULE_ITEM_CODE, usagesQuestionnaireBean.getModuleItemCode());
		fieldValues2.put(MODULE_SUB_ITEM_CODE, usagesQuestionnaireBean.getModuleSubItemCode());

		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		final Map<String, Object> params = new HashMap<String, Object>();
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);

		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(2L));
		
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).findMatching(AnswerHeader.class, fieldValues1);
				will(returnValue(expectedAnswerHeaders));
				oneOf(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues2);
				will(returnValue(usages));
				oneOf(questionnaireAnswerService).getQuestionnaireAnswer(answerHeaderQuestionnaireBean);
				will(returnValue(expectedAnswerHeaders));
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(usagesQuestionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		final List<AnswerHeader> answerHeaders = s2sQuestionnaireServiceImpl.getProposalS2sAnswerHeaders(proposal);
		assertTrue(answerHeaders.isEmpty());
		assertEquals(expectedAnswerHeaders, answerHeaders);
	}

    @Test
	public void test_getProposalS2sAnswerHeaders_withProposal() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final List<AnswerHeader> expectedAnswerHeaders = new ArrayList<AnswerHeader>();
		expectedAnswerHeaders.add(createTestAnswerHeader());

		final ModuleQuestionnaireBean answerHeaderQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues1 = new HashMap<String, String>();
		fieldValues1.put(MODULE_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleItemCode());
		fieldValues1.put(MODULE_SUB_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleSubItemCode());
		fieldValues1.put(MODULE_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleItemKey());
		fieldValues1.put(MODULE_SUB_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleSubItemKey());

		final ModuleQuestionnaireBean usagesQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues2 = new HashMap<String, String>();
		fieldValues2.put(MODULE_ITEM_CODE, usagesQuestionnaireBean.getModuleItemCode());
		fieldValues2.put(MODULE_SUB_ITEM_CODE, usagesQuestionnaireBean.getModuleSubItemCode());

		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));

		final Map<String, Object> params = new HashMap<String, Object>();
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);

		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(2L));
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).findMatching(AnswerHeader.class, fieldValues1);
				will(returnValue(expectedAnswerHeaders));
				oneOf(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues2);
				will(returnValue(usages));
				oneOf(questionnaireAnswerService).getQuestionnaireAnswer(answerHeaderQuestionnaireBean);
				will(returnValue(expectedAnswerHeaders));
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(usagesQuestionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		final List<AnswerHeader> answerHeaders = s2sQuestionnaireServiceImpl.getProposalS2sAnswerHeaders(proposal);
		assertNotNull(answerHeaders);
		assertEquals(expectedAnswerHeaders.size(), answerHeaders.size());
		assertEquals(expectedAnswerHeaders, answerHeaders);
	}

    @Test(expected = NullPointerException.class)
	public void test_getProposalS2sAnswerHeaders_proposalNull() {
		final DevelopmentProposal proposal = null;
		s2sQuestionnaireServiceImpl.getProposalS2sAnswerHeaders(proposal);
	}

    @Test
	public void test_getQuestionnaireUsages_returnEmptyList() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final ModuleQuestionnaireBean questionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(proposal);
		questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		final Map<String, String> fieldValues = new HashMap<String, String>();
		fieldValues.put(MODULE_ITEM_CODE, questionnaireBean.getModuleItemCode());
		fieldValues.put(MODULE_SUB_ITEM_CODE, questionnaireBean.getModuleSubItemCode());
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues);
				will(returnValue(usages));
			}
		});
		answerServiceImpl.setBusinessObjectService(businessObjectService);
		final List<QuestionnaireUsage> usageList = answerServiceImpl.getPublishedQuestionnaire(questionnaireBean);
		assertEquals(usages, usageList);
		assertTrue(usageList.isEmpty());
	}

    @Test
	public void test_getQuestionnaireUsages_empty_s2sOppFormQuestionnaire() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));
		final ModuleQuestionnaireBean questionnaireBean = getModuleQnBean(proposal);
		questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);
		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		context.checking(new Expectations() {
			{
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(questionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		List<QuestionnaireUsage> questionnaireUsages = s2sQuestionnaireServiceImpl.getQuestionnaireUsages(oppNameSpace, formName, proposal);
		assertTrue(questionnaireUsages.isEmpty());
	}

    @Test
	public void test_getQuestionnaireUsages_withArguments() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));
		final ModuleQuestionnaireBean questionnaireBean = getModuleQnBean(proposal);
		questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);
		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(1L));
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(2L));
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(3L));
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(4L));
		context.checking(new Expectations() {
			{
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(questionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		List<QuestionnaireUsage> questionnaireUsages = s2sQuestionnaireServiceImpl.getQuestionnaireUsages(oppNameSpace, formName, proposal);
		assertNotNull(questionnaireUsages);
		assertEquals(4, questionnaireUsages.size());
	}

    @Test
	public void test_getQuestionnaireUsages_without_nameSpace_and_formName() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final String oppNameSpace = null;
		final String formName = null;
		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));
		final ModuleQuestionnaireBean questionnaireBean = getModuleQnBean(proposal);
		questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);
		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		context.checking(new Expectations() {
			{
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(questionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		List<QuestionnaireUsage> questionnaireUsages = s2sQuestionnaireServiceImpl.getQuestionnaireUsages(oppNameSpace, formName, proposal);
		assertTrue(questionnaireUsages.isEmpty());
	}

    @Test(expected = NullPointerException.class)
	public void test_getQuestionnaireUsages_withNullArguments() {
		final DevelopmentProposal proposal = null;
		final String oppNameSpace = null;
		final String formName = null;
		s2sQuestionnaireServiceImpl.getQuestionnaireUsages(oppNameSpace, formName, proposal);
	}

    @Test
	public void test_getProposalAnswerHeaderForForm_without_s2sOpportunity() {
		final DevelopmentProposal developmentProposal = initializeDevelopmentProposal();
		developmentProposal.setS2sOpportunity(null);
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		final ModuleQuestionnaireBean questionnaireBean = getModuleQnBean(developmentProposal);
		questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
		final List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
		context.checking(new Expectations() {
			{
				one(questionnaireAnswerService).getQuestionnaireAnswer(questionnaireBean);
				will(returnValue(answerHeaders));
			}
		});
		List<AnswerHeader> answerHeadersList = s2sQuestionnaireServiceImpl.getProposalAnswerHeaderForForm(developmentProposal, oppNameSpace, formName);
		assertTrue(answerHeadersList.isEmpty());
	}

    @Test
	public void test_getProposalAnswerHeaderForForm_withProposal() {
		final DevelopmentProposal proposal = initializeDevelopmentProposal();
		final List<AnswerHeader> expectedAnswerHeaders = new ArrayList<AnswerHeader>();
		expectedAnswerHeaders.add(createTestAnswerHeader());

		final ModuleQuestionnaireBean answerHeaderQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues1 = new HashMap<String, String>();
		fieldValues1.put(MODULE_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleItemCode());
		fieldValues1.put(MODULE_SUB_ITEM_CODE, answerHeaderQuestionnaireBean.getModuleSubItemCode());
		fieldValues1.put(MODULE_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleItemKey());
		fieldValues1.put(MODULE_SUB_ITEM_KEY, answerHeaderQuestionnaireBean.getModuleSubItemKey());

		final ModuleQuestionnaireBean usagesQuestionnaireBean = getModuleQnBean(proposal);
		final Map<String, String> fieldValues2 = new HashMap<String, String>();
		fieldValues2.put(MODULE_ITEM_CODE, usagesQuestionnaireBean.getModuleItemCode());
		fieldValues2.put(MODULE_SUB_ITEM_CODE, usagesQuestionnaireBean.getModuleSubItemCode());

		final List<QuestionnaireUsage> usages = new ArrayList<QuestionnaireUsage>();
		usages.add(createTestQuestionnaireUsage(744L, 5268L, "2"));
		usages.add(createTestQuestionnaireUsage(745L, 5270L, "3"));
		usages.add(createTestQuestionnaireUsage(746L, 5276L, "4"));
		usages.add(createTestQuestionnaireUsage(747L, 5269L, "1"));

		final Map<String, Object> params = new HashMap<String, Object>();
		final String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		final String formName = "NSF_CoverPage_1_3-V1.3";
		params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD, oppNameSpace);
		params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);

		final List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = new ArrayList<S2sOppFormQuestionnaire>();
		s2sOppFormQuestionnaires.add(createTestS2sOppFormQuestionnaire(2L));
		context.checking(new Expectations() {
			{
				oneOf(businessObjectService).findMatching(AnswerHeader.class, fieldValues1);
				will(returnValue(expectedAnswerHeaders));
				oneOf(businessObjectService).findMatching(QuestionnaireUsage.class, fieldValues2);
				will(returnValue(usages));
				oneOf(questionnaireAnswerService).getQuestionnaireAnswer(answerHeaderQuestionnaireBean);
				will(returnValue(expectedAnswerHeaders));
				oneOf(questionnaireAnswerService).getPublishedQuestionnaire(usagesQuestionnaireBean);
				will(returnValue(usages));
				oneOf(businessObjectService).findMatching(S2sOppFormQuestionnaire.class, params);
				will(returnValue(s2sOppFormQuestionnaires));
			}
		});
		final List<AnswerHeader> answerHeaders = s2sQuestionnaireServiceImpl.getProposalAnswerHeaderForForm(proposal, oppNameSpace, formName);
		assertNotNull(answerHeaders);
		assertEquals(expectedAnswerHeaders.size(), answerHeaders.size());
		assertEquals(expectedAnswerHeaders, answerHeaders);
	}

    @Test(expected = NullPointerException.class)
	public void test_getProposalAnswerHeaderForForm_withNullArguments() {
		DevelopmentProposal developmentProposal = null;
		String oppNameSpace = "";
		String formName = "";
		s2sQuestionnaireServiceImpl.getProposalAnswerHeaderForForm(developmentProposal, oppNameSpace, formName);
	}

	private DevelopmentProposal initializeDevelopmentProposal() {
		ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
		proposalDevelopmentDocument.setDocumentNumber("123");
		DocumentHeader documentHeader = new DocumentHeader();
        documentHeader.setWorkflowDocument(new WorkflowDocumentImpl() {
            @Override
            public boolean isApproved() {
                return true;
            }
        });
		documentHeader.setDocumentNumber("123");
		proposalDevelopmentDocument.setDocumentHeader(documentHeader);

		DevelopmentProposal developmentProposal = new DevelopmentProposal();
		developmentProposal.setProposalDocument(proposalDevelopmentDocument);
		developmentProposal.setProposalNumber("25");
		String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		String formName = "NSF_CoverPage_1_3-V1.3";

		S2sOpportunity s2sOpportunity = new S2sOpportunity();
		List<S2sOppForms> s2sOppFormsList = new ArrayList<S2sOppForms>();
		S2sOppForms s2sOppForms = new S2sOppForms();
		s2sOppForms.setFormName(formName);
		s2sOppForms.setInclude(true);
		S2sOppFormsId formsId = new S2sOppFormsId();
		formsId.setOppNameSpace(oppNameSpace);
		formsId.setProposalNumber(developmentProposal.getProposalNumber());
		s2sOppForms.setS2sOppFormsId(formsId);
		s2sOppFormsList.add(s2sOppForms);
		s2sOpportunity.setS2sOppForms(s2sOppFormsList);
		developmentProposal.setS2sOpportunity(s2sOpportunity);
		return developmentProposal;
	}

	public AnswerHeader createTestAnswerHeader() {
		AnswerHeader answerHeader = new AnswerHeader();
		answerHeader.setQuestionnaire(createTestQuestionnaire(5268L, "2"));
		return answerHeader;
	}

	public QuestionnaireUsage createTestQuestionnaireUsage(
			Long questionnaireId, Long id, String questionnaireSeqId) {
		QuestionnaireUsage questionnaireUsage = new QuestionnaireUsage();
		questionnaireUsage.setQuestionnaireId(questionnaireId);
		questionnaireUsage.setQuestionnaire(createTestQuestionnaire(id, questionnaireSeqId));
		return questionnaireUsage;
	}

	public Questionnaire createTestQuestionnaire(Long id, String questionnaireSeqId) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setId(id);
		questionnaire.setQuestionnaireSeqId(questionnaireSeqId);
		return questionnaire;
	}

	public S2sOppFormQuestionnaire createTestS2sOppFormQuestionnaire(Long questionnaireId) {
		String oppNameSpace = "http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3";
		String formName = "NSF_CoverPage_1_3-V1.3";
		S2sOppFormQuestionnaire s2sOppFormQuestionnaire = new S2sOppFormQuestionnaire();
		s2sOppFormQuestionnaire.setS2sOppFormQuestionnaireId(751L);
		s2sOppFormQuestionnaire.setOppNameSpace(oppNameSpace);
		s2sOppFormQuestionnaire.setFormName(formName);
		s2sOppFormQuestionnaire.setQuestionnaireId(questionnaireId);
		return s2sOppFormQuestionnaire;
	}
	
	public ModuleQuestionnaireBean getModuleQnBean(DevelopmentProposal proposal) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(proposal);
        return moduleQuestionnaireBean;
    }
}
