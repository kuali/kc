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
package org.kuali.coeus.propdev.impl.specialreview;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.funding.ProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewService;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;

public class ProposalDevelopmentSpecialReviewServiceTest {

	private Mockery context;
	private Protocol protocol;
	private ProposalDevelopmentDocument document;
	private ProposalSpecialReview specialReview;
	private ProtocolDocument protocolDocument;
	private ParameterService parameterService;
	private KualiRuleService kualiRuleService;
	private ProtocolSpecialReviewService protocolSpecialReviewService;
	private IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService;
	private ProposalDevelopmentProtocolDocumentService proposalDevelopmentProtocolDocumentService;
	private IacucProtocolProposalDevelopmentProtocolDocumentService iacucProtocolProposalDevelopmentProtocolDocumentService;
	private List<ProposalSpecialReview> specialReviews = new ArrayList<ProposalSpecialReview>();

	@Before()
	public void setUpMockery() {

		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		parameterService = context.mock(ParameterService.class);
		kualiRuleService = context.mock(KualiRuleService.class);
		protocolSpecialReviewService = context
				.mock(ProtocolSpecialReviewService.class);
		proposalDevelopmentProtocolDocumentService = context
				.mock(ProposalDevelopmentProtocolDocumentService.class);
		iacucProtocolSpecialReviewService = context
				.mock(IacucProtocolSpecialReviewService.class);
		iacucProtocolProposalDevelopmentProtocolDocumentService = context
				.mock(IacucProtocolProposalDevelopmentProtocolDocumentService.class);
		document = new ProposalDevelopmentDocument();
		specialReview = new ProposalSpecialReview();
        protocol = createProtocol();
		protocolDocument = createProtocolDocument();
	}

	@Test()
	public void test_createProtocol_specialReviewType_humanSubject()
			throws Exception {
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROPOSAL_DEVELOPMENT_CREATE_IRB_PROTOCOL_ENABLED_PARAMETER);
				will(returnValue(true));
				allowing(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(protocolDocument.getProtocolLocationService()).addDefaultProtocolLocation(protocol);
				oneOf(proposalDevelopmentProtocolDocumentService).createProtocolDocument(document);
				will(returnValue(protocolDocument));
				oneOf(protocolSpecialReviewService).populateSpecialReview(specialReview);
				oneOf(kualiRuleService).applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(document, specialReview,
						specialReviews, true));
				will(returnValue(true));
			}
		});
		specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
		proposalDevelopmentSpecialReviewServiceImpl
				.setProposalDevelopmentProtocolDocumentService(proposalDevelopmentProtocolDocumentService);
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		proposalDevelopmentSpecialReviewServiceImpl
				.setProtocolSpecialReviewService(protocolSpecialReviewService);
		proposalDevelopmentSpecialReviewServiceImpl.setKualiRuleService(kualiRuleService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl
				.createProtocol(specialReview, document));
	}

	@Test()
	public void test_createProtocol_specialReviewType_animalUsage()
			throws Exception {
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				allowing(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IACUC,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROPOSAL_DEVELOPMENT_CREATE_IACUC_PROTOCOL_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(protocolDocument.getProtocolLocationService())
						.addDefaultProtocolLocation(protocol);
				oneOf(iacucProtocolProposalDevelopmentProtocolDocumentService)
						.createProtocolDocument(document);
				will(returnValue(protocolDocument));
				oneOf(iacucProtocolSpecialReviewService).populateSpecialReview(specialReview);
				oneOf(kualiRuleService).applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(document, specialReview,
						specialReviews, true));
				will(returnValue(true));
			}
		});
		specialReview.setSpecialReviewTypeCode(SpecialReviewType.ANIMAL_USAGE);
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		proposalDevelopmentSpecialReviewServiceImpl.
		setIacucProtocolProposalDevelopmentProtocolDocumentService(
						iacucProtocolProposalDevelopmentProtocolDocumentService);
		proposalDevelopmentSpecialReviewServiceImpl
				.setIacucProtocolSpecialReviewService(iacucProtocolSpecialReviewService);
		proposalDevelopmentSpecialReviewServiceImpl.setKualiRuleService(kualiRuleService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl
				.createProtocol(specialReview, document));
	}

	@Test()
	public void test_createProtocol_without_proposalDevelopmentDocument()
			throws Exception {
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROPOSAL_DEVELOPMENT_CREATE_IRB_PROTOCOL_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(proposalDevelopmentProtocolDocumentService).createProtocolDocument(null);
				will(returnValue(null));
			}
		});
		specialReview.setSpecialReviewTypeCode(SpecialReviewType.HUMAN_SUBJECTS);
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		proposalDevelopmentSpecialReviewServiceImpl
				.setProposalDevelopmentProtocolDocumentService(proposalDevelopmentProtocolDocumentService);
		assertFalse(proposalDevelopmentSpecialReviewServiceImpl
				.createProtocol(specialReview, null));
	}

	@Test()
	public void test_createProtocol_invalid_specialReviewType()
			throws Exception {
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		specialReview.setSpecialReviewTypeCode("3");
		assertFalse(proposalDevelopmentSpecialReviewServiceImpl
				.createProtocol(specialReview, document));
	}
	
	@Test(expected = NullPointerException.class)
	public void test_createProtocol_without_arguments() throws Exception {
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		proposalDevelopmentSpecialReviewServiceImpl.createProtocol(null, null);
	}
	
	@Test
	public void  test_isIrbLinkingEnabled(){
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.isIrbLinkingEnabled());
	}
	
	@Test
	public void  test_isIacucLinkingEnabled(){
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IACUC,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.isIacucLinkingEnabled());
	}
	
	@Test
	public void test_isCreateIrbProtocolEnabled(){		
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IRB,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROPOSAL_DEVELOPMENT_CREATE_IRB_PROTOCOL_ENABLED_PARAMETER);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.isCreateIrbProtocolEnabled());
	}
	
	@Test
	public void test_isCreateIacucProtocolEnabled(){		
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_IACUC,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
				will(returnValue(true));
				oneOf(parameterService).getParameterValueAsBoolean(
								Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,
								Constants.PARAMETER_COMPONENT_DOCUMENT,
								Constants.PROPOSAL_DEVELOPMENT_CREATE_IACUC_PROTOCOL_ENABLED_PARAMETER);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.setParameterService(parameterService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.isCreateIacucProtocolEnabled());
	}	
	
	@Test
	public void test_canCreateIrbProtocol(){
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				one(proposalDevelopmentProtocolDocumentService).isAuthorizedCreateProtocol(document);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.
			setProposalDevelopmentProtocolDocumentService(proposalDevelopmentProtocolDocumentService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.canCreateIrbProtocol(document));
	}
	
	@Test
	public void test_canCreateIacucProtocol(){
		ProposalDevelopmentSpecialReviewServiceImpl proposalDevelopmentSpecialReviewServiceImpl =
				new ProposalDevelopmentSpecialReviewServiceImpl();
		context.checking(new Expectations() {
			{
				one(iacucProtocolProposalDevelopmentProtocolDocumentService).isAuthorizedCreateProtocol(document);
				will(returnValue(true));
			}
		});
		proposalDevelopmentSpecialReviewServiceImpl.
			setIacucProtocolProposalDevelopmentProtocolDocumentService(iacucProtocolProposalDevelopmentProtocolDocumentService);
		assertTrue(proposalDevelopmentSpecialReviewServiceImpl.canCreateIacucProtocol(document));		
	}
	
	private ProtocolDocument createProtocolDocument() {
		ProtocolDocument protocolDocument = new ProtocolDocument() {
			@Override
			protected Protocol createNewProtocolInstanceHook() {
				setProtocolLocationService(context.mock(ProtocolLocationService.class));				
				context.checking(new Expectations() {
					{
						oneOf(getProtocolLocationService()).addDefaultProtocolLocation(protocol);						
					}
				});				
				return protocol;
			}
		};
		return protocolDocument;
	}

	private Protocol createProtocol() {
		Protocol protocol = new Protocol() {
			@Override
			public void refreshReferenceObject(String referenceObjectName) {
			}
		};
		return protocol;
	}
}
