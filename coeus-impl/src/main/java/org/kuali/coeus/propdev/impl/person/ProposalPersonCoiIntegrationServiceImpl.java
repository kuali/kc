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
package org.kuali.coeus.propdev.impl.person;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.coi.CoiConstants;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalPersonCoiIntegrationService")
public class ProposalPersonCoiIntegrationServiceImpl implements ProposalPersonCoiIntegrationService {

	public static final String PROP_PERSON_COI_CERTIFY_QID = "PROP_PERSON_COI_CERTIFY_QID";
	public static final String DISCLOSURE_EVENT_TYPE = "disclosureEventType";
	public static final String COI_PROJECT_ID = "coiProjectId";
	public static final String COI_DISCLOSURE_PERSON_ID = "coiDisclosure.personId";
	@Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;

	@Override
	public String getProposalPersonCoiStatus(ProposalPerson person) {
		for (String answer : getAnswersToCoiQuestions(person)) {
			if (answer == null) {
				return CoiConstants.CERTIFICATION_INCOMPLETE;
			}
			if (StringUtils.equals(answer, Constants.YES_FLAG)) {
				String status = getKeyPersonnelCoiDisclosureStatus(person.getDevelopmentProposal().getProposalNumber(), person.getPersonId());
				if (status == null && person.getRolodexId() != null) {
					return CoiConstants.COI_FROM_EXTERNAL;
				}
				return status == null ? CoiConstants.IN_PROGRESS : status;
			}
		}
		return CoiConstants.DISCLOSURE_NOT_REQUIRED;
	}

	protected List<String> getAnswersToCoiQuestions(ProposalPerson proposalPerson) {
		String coiCertificationQuestionIds = getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PROP_PERSON_COI_CERTIFY_QID);
		List<String> answersToCoiQuestions = new ArrayList<>();
		List<String> questionIds = new ArrayList<>();
		questionIds.addAll(Arrays.asList(coiCertificationQuestionIds.split(",")));
		for (AnswerHeader answerHeader : proposalPerson.getQuestionnaireHelper().getAnswerHeaders()) {
			answersToCoiQuestions.addAll(answerHeader.getAnswers().stream().filter(
					answer -> questionIds.contains(answer.getQuestionSeqId().toString())).map(Answer::getAnswer).collect(Collectors.toList()));
		}
		return answersToCoiQuestions;
	}

	public String getKeyPersonnelCoiDisclosureStatus(String developmentProposalNumber,String keyPersonId){
		Map<String,String> criteria = new HashMap<>();
		criteria.put(DISCLOSURE_EVENT_TYPE, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
		criteria.put(COI_PROJECT_ID,developmentProposalNumber);
		criteria.put(COI_DISCLOSURE_PERSON_ID, keyPersonId);
		List<CoiDisclProject> projects = (List<CoiDisclProject>) getBusinessObjectService().findMatching(CoiDisclProject.class,criteria);

		int version = 0;
		CoiDisclProject coiDisclProject = null;
		for (CoiDisclProject project : projects) {
			if (project.getSequenceNumber() > version) {
				version = project.getSequenceNumber();
				coiDisclProject = project;
			}
		}
		return coiDisclProject != null ? coiDisclProject.getCoiDisclosure().getCoiDisclosureStatus().getDescription() : null;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
