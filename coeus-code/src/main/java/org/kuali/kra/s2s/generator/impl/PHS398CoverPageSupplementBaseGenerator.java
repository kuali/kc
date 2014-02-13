/*
 * Copyright 2005-2013 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class has methods that are common to all the versions of
 * PHS398CoverPageSupplement form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398CoverPageSupplementBaseGenerator extends
		S2SBaseFormGenerator {
	
	public static final String IS_CLINICAL_TRIAL = "2";
	public static final String PHASE_III_CLINICAL_TRIAL = "3";
	public static final String IS_HUMAN_STEM_CELLS_INVOLVED = "5";
	public static final String SPECIFIC_STEM_CELL_LINE = "6"; 
	public static final String REGISTRATION_NUMBER = "7";
	public static final String IS_NEW_INVESTIGATOR = "13";
	public static final String NOT_ANSWERED = "No";

	protected S2SUtilService s2sUtilService;
	protected static final int MAX_NUMBER_OF_DEGREES = 3;
	protected static final int PERSON_DEGREE_MAX_LENGTH = 10;

	/**
	 * 
	 * Constructs a PHS398CoverPageSupplementBaseGenerator.java.
	 */
	public PHS398CoverPageSupplementBaseGenerator() {
		s2sUtilService = KcServiceLocator.getService(S2SUtilService.class);
	}

	/**
	 * 
	 * This method is used to get the Ynq answer for ProposalYnq
	 * 
	 * @param questionId
	 *            to be checked.
	 * @return proposalYnq corresponding to the question id.
	 */
	protected ProposalYnq getProposalYnQ(String questionId) {
		String question = null;
		ProposalYnq ynQ = null;
		for (ProposalYnq proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {
			question = proposalYnq.getQuestionId();
			if (question != null && question.equals(questionId)) {
				ynQ = proposalYnq;
			}
		}
		return ynQ;
	}

	/**
	 * This method splits the passed explanation comprising cell line
	 * information, puts into a list and returns the list.
	 * 
	 * @param explanation
	 *            String of cell lines
	 * @return {@link List}
	 */
	protected List<String> getCellLines(String explanation) {
		String cellLine = null;
		int startPos = 0;
		List<String> cellLines = new ArrayList<String>();
		for (int commaPos = 0; commaPos > -1;) {
			commaPos = explanation.indexOf(",", startPos);
			if (commaPos >= 0) {
				cellLine = (explanation.substring(startPos, commaPos).trim());
				explanation = explanation.substring(commaPos + 1);
				if (cellLine.length() > 0) {
					cellLines.add(cellLine);
				}
			} else if (explanation.length() > 0) {
				cellLines.add(explanation.trim());
			}
		}
		return cellLines;
	}

}
