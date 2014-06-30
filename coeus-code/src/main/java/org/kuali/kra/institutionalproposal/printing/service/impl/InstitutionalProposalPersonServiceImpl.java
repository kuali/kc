/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.printing.service.impl;


import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPersonService;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * These class have different methods to provide service like pass proposal
 * number get the list of proposal persons.
 */
public class InstitutionalProposalPersonServiceImpl implements
		InstitutionalProposalPersonService {
	private static final String PROPOSAL_NUMBER_PARAMETER = "proposalNumber";
	private BusinessObjectService businessObjectService;

	/**
	 * This method will return the list of proposal persons if proposal person
	 * find in development proposal.
	 * 
	 * @param proposalNumber
	 *            is a number used to get the proposal persons for this proposal
	 *            number.
	 * @return list of proposal persons
	 */
	public List<ProposalPerson> getInvestigatorsFromDevelopmentProposal(
			String proposalNumber) {
		List<ProposalPerson> proposalPersonsList = new ArrayList<ProposalPerson>();
		Map<String, String> proposalPersonMap = new HashMap<String, String>();
		proposalPersonMap.put(PROPOSAL_NUMBER_PARAMETER, proposalNumber);
		List<DevelopmentProposal> developmentProposals = (List<DevelopmentProposal>) businessObjectService
				.findMatching(DevelopmentProposal.class, proposalPersonMap);
		if (developmentProposals != null && !developmentProposals.isEmpty()) {
			DevelopmentProposal developmentProposal = developmentProposals
					.get(0);
			if (developmentProposal.getProposalPersons() != null) {
				for(ProposalPerson proposalPerson:developmentProposal.getProposalPersons()){
					if(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR.equals(proposalPerson.getProjectRole()) || PropAwardPersonRole.CO_INVESTIGATOR.equals(proposalPerson.getProjectRole())){
						proposalPersonsList.add(proposalPerson);
					}
				}
			}
		}
		return proposalPersonsList;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}
}
