/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.HistoricImpact;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.InternationalParticipation;
import gov.grants.apply.forms.nasaOtherProjectInformationV10.NASAOtherProjectInformationDocument.NASAOtherProjectInformation.NASACivilServicePersonnel;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.service.RolodexService;

/**
 * Class for generating the XML object for grants.gov
 * NasaOtherProjectInformationV1_0. Form is generated using XMLBean classes and
 * is based on NasaOtherProjectInformation schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NASAOtherProjectInformationV1_0Generator extends
		S2SBaseFormGenerator {

	private static final String PRINCIPAL_INVESTIGATOR = "PI";
	private static final String COLLABORATOR_ROLE = "Collaborator";
	private static final String C0_INVESTIGATOR = "COI";
	private static final String HISTORICAL_IMPACT = "G6";
	private static final String INTERNATIONAL_PARTICIPATION = "H1";
	private static final int PROGRAM_SPECIFIC_DATA = 47;
	private static final int APPENDICES = 48;
	private static final int NON_US_ORGANIZATION_LETTERS_OF_ENDORSEMENT = 49;
	private static final int NARRATIVE_IRB_ACUC_LETTERS = 50;
	private static final int MAX_EXPLANATION_LENGTH = 2000;
	private static final String COUNTRY_CODE_USA = "USA";
	private static final String COUNTRY_CODE_PUERTO_RICO = "PRI";
	private static final String COUNTRY_CODE_VIRGIN_ISLANDS = "VIR";
	private static final int MAX_PROPOSAL_PERSON_COUNT = 40;

	/**
	 * 
	 * This method gives information of NasaCivilServicePersonnel,
	 * HistoricalImpact, InternationalParticipation from
	 * NasaOtherProjectInformation
	 * 
	 * @return nasaOtherInformationDocument {@link XmlObject} of type
	 *         NASAOtherProjectInformationDocument.
	 */

	private NASAOtherProjectInformationDocument getNasaOtherProjectInformation() {

		NASAOtherProjectInformationDocument nasaOtherInformationDocument = NASAOtherProjectInformationDocument.Factory
				.newInstance();
		NASAOtherProjectInformation nasaOtherProjectInformation = NASAOtherProjectInformation.Factory
				.newInstance();
		nasaOtherProjectInformation
				.setFormVersion(S2SConstants.FORMVERSION_1_0);
		nasaOtherProjectInformation
				.setNASACivilServicePersonnel(getNasaCivilServicePersonnel());
		nasaOtherProjectInformation.setHistoricImpact(getHistoricImpact());
		nasaOtherProjectInformation
				.setInternationalParticipation(getInternationalParticipation());
		nasaOtherInformationDocument
				.setNASAOtherProjectInformation(nasaOtherProjectInformation);

		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == PROGRAM_SPECIFIC_DATA) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					nasaOtherProjectInformation.setPSDataAttach(attachedFileDataType);
					break;
				}
			}
		}

		AttachmentGroupMin0Max100DataType attachmentAppendix = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentAppendix.setAttachedFileArray(getAppendixFileDataTypes());
		nasaOtherProjectInformation.setAppendAttach(attachmentAppendix);

		AttachmentGroupMin0Max100DataType attachmentEndorsement = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentEndorsement
				.setAttachedFileArray(getEndorsementFileDataTypes());
		nasaOtherProjectInformation
				.setLetterEndorsAttach(attachmentEndorsement);

		AttachmentGroupMin0Max100DataType attachmentIRBACUC = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		attachmentIRBACUC
				.setAttachedFileArray(getIRBACUCLettersFileDataTypes());
		nasaOtherProjectInformation.setIRBACUCLettersAttach(attachmentIRBACUC);
		nasaOtherInformationDocument
				.setNASAOtherProjectInformation(nasaOtherProjectInformation);
		return nasaOtherInformationDocument;
	}

	/**
	 * 
	 * This method gives information of NasaCivilServicePersonnel for
	 * NasaOtherProjectInformation
	 * 
	 * @return NASACivilServicePersonnel object containing Nasa civil service
	 *         personnel details.
	 */
	private NASACivilServicePersonnel getNasaCivilServicePersonnel() {

		NASACivilServicePersonnel nasaCivilServicePersonnel = NASACivilServicePersonnel.Factory
				.newInstance();

		// hard coding "No" for question "Will NASA civil service personnel work
		// on this project?"
		nasaCivilServicePersonnel.setCivilServicePersonnel(YesNoDataType.N_NO);
		return nasaCivilServicePersonnel;
	}

	/**
	 * 
	 * This method gives HistoricalImpact information based on the proposal Ynq
	 * question id.
	 * 
	 * @return HistoricImpact object containing HistoricalImpact details.
	 */
	private HistoricImpact getHistoricImpact() {
		HistoricImpact historicImpact = HistoricImpact.Factory.newInstance();
		ProposalYnq proposalYnq = getAnswer(HISTORICAL_IMPACT, pdDoc);
		if (proposalYnq != null) {
			YesNoDataType.Enum answer = null;
			if (proposalYnq.getAnswer() != null) {
				answer = (proposalYnq.getAnswer().equals(
						S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
						: YesNoDataType.N_NO);
			}
			historicImpact.setHistoricImpactQ(answer);

			if (proposalYnq.getExplanation() != null) {
				if (proposalYnq.getExplanation().length() > MAX_EXPLANATION_LENGTH) {
					historicImpact.setHistoricImpactEx(proposalYnq
							.getExplanation().substring(0,
									MAX_EXPLANATION_LENGTH));
				} else {
					historicImpact.setHistoricImpactEx(proposalYnq
							.getExplanation());
				}
			}
		}
		return historicImpact;
	}

	/**
	 * 
	 * This method gives the information about InternationalParticipation such
	 * as international participation question,explanation Role and Facility.
	 * 
	 * 
	 * @return InternationalParticipation object containing information
	 *         regarding the international participation.
	 */
	private InternationalParticipation getInternationalParticipation() {
		InternationalParticipation inParticipation = InternationalParticipation.Factory
				.newInstance();
		inParticipation.setInternationalParticipationQ(YesNoDataType.N_NO);
		ProposalYnq proposalYnq = getAnswer(INTERNATIONAL_PARTICIPATION, pdDoc);
		if (proposalYnq != null) {
			YesNoDataType.Enum answer = YesNoDataType.N_NO;
			if (proposalYnq.getAnswer() != null) {
				answer = (proposalYnq.getAnswer().equals(
						S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
						: YesNoDataType.N_NO);
			}
			inParticipation.setInternationalParticipationQ(answer);

			if (inParticipation.getInternationalParticipationQ() != null
					&& inParticipation.getInternationalParticipationQ().equals(
							YesNoDataType.Y_YES)) {
				if (proposalYnq.getExplanation() != null) {
					if (proposalYnq.getExplanation().length() > MAX_EXPLANATION_LENGTH) {
						inParticipation
								.setInternationalParticipatioEx(proposalYnq
										.getExplanation().substring(0,
												MAX_EXPLANATION_LENGTH));
					} else {
						inParticipation
								.setInternationalParticipatioEx(proposalYnq
										.getExplanation());
					}
				}
				List<ProposalPerson> sortedPersonList = pdDoc
						.getDevelopmentProposal().getProposalPersons();
				Collections.sort(sortedPersonList,
						new ProposalPersonComparator());

				// Limit proposal person count to 40
				while (sortedPersonList.size() > MAX_PROPOSAL_PERSON_COUNT) {
					sortedPersonList.remove(MAX_PROPOSAL_PERSON_COUNT);
				}

				boolean isForeign;
				for (ProposalPerson proposalPerson : sortedPersonList) {
					isForeign = isProposalPersonForeign(proposalPerson);
					if (proposalPerson.getProposalPersonRoleId() != null
							&& isForeign) {
						if (proposalPerson.getProposalPersonRoleId().equals(
								PRINCIPAL_INVESTIGATOR)) {
							inParticipation
									.setInternationalParticipationPI(YesNoDataType.Y_YES);
						} else if (proposalPerson.getProposalPersonRoleId()
								.equals(C0_INVESTIGATOR)) {
							inParticipation
									.setInternationalParticipationCoI(YesNoDataType.Y_YES);
						} else if (COLLABORATOR_ROLE
								.equalsIgnoreCase(proposalPerson
										.getProjectRole())) {
							inParticipation
									.setInternationalParticipationCollaborator(YesNoDataType.Y_YES);
						}
					}
				}

				if (inParticipation.getInternationalParticipationPI() == null
						&& inParticipation.getInternationalParticipationCoI() == null
						&& inParticipation
								.getInternationalParticipationCollaborator() == null) {
					// Because the answer of this question is "Yes",
					// if none of PI, Co-I and Collaborator was checked, then
					// check Facility,
					inParticipation
							.setInternationalParticipationFacility(YesNoDataType.Y_YES);
				}
			}
		}
		return inParticipation;
	}

	/*
	 * This method checks whether the Proposal Person is Foreign
	 */
	private boolean isProposalPersonForeign(ProposalPerson proposalPerson) {
		boolean isForeign = false;
		if (proposalPerson.getPersonId() != null) {
			// Person is MIT person
			if (proposalPerson.getCountryOfCitizenship() != null) {
				if (proposalPerson.getCountryOfCitizenship().equals(
						COUNTRY_CODE_PUERTO_RICO)
						|| proposalPerson.getCountryOfCitizenship().equals(
								COUNTRY_CODE_USA)
						|| proposalPerson.getCountryOfCitizenship().equals(
								COUNTRY_CODE_VIRGIN_ISLANDS)) {
					isForeign = false;
				} else {
					isForeign = true;
				}
			}
		} else {
			// Person is Rolodex person
			isForeign = isRolodexPersonForeign(proposalPerson);
		}
		return isForeign;
	}

	/*
	 * This method checks whether the Rolodex association is Foreign
	 */
	private boolean isRolodexPersonForeign(ProposalPerson proposalPerson) {
		boolean isForeign = false;
		if (proposalPerson.getRolodexId() != null) {
			Rolodex rolodex = KraServiceLocator
					.getService(RolodexService.class).getRolodex(
							proposalPerson.getRolodexId());
			if (rolodex != null) {
				if (rolodex.getSponsor() != null
						&& rolodex.getSponsor().getSponsorTypeCode() != null) {
					if (Integer.parseInt(rolodex.getSponsor()
							.getSponsorTypeCode()) > 9) {
						isForeign = true;
					}
				} else if (rolodex.getCountryCode() != null) {
					if (rolodex.getCountryCode().equals(
							COUNTRY_CODE_PUERTO_RICO)
							|| rolodex.getCountryCode()
									.equals(COUNTRY_CODE_USA)
							|| rolodex.getCountryCode().equals(
									COUNTRY_CODE_VIRGIN_ISLANDS)) {
						isForeign = false;
					} else {
						isForeign = true;
					}
				}
			}
		}
		return isForeign;
	}

	/**
	 * 
	 * This method is used to get List of attachments for Appendices from
	 * NarrativeAttachmentList
	 * 
	 * @return AttachedFileDataType[] array of attached files based on the
	 *         Narrative Type Code.
	 */
	private AttachedFileDataType[] getAppendixFileDataTypes() {
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == APPENDICES) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
	}

	/**
	 * 
	 * This method is used to get List of attachments for non-US organization
	 * letters of endorsement type from NarrativeAttachmentList
	 * 
	 * @return AttachedFileDataType[] array of attached files based on the
	 *         Narrative Type Code.
	 */
	private AttachedFileDataType[] getEndorsementFileDataTypes() {
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == NON_US_ORGANIZATION_LETTERS_OF_ENDORSEMENT) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
	}

	/**
	 * 
	 * This method is used to get List of attachments for IRB-ACUC-LETTERS type
	 * from NarrativeAttachmentList
	 * 
	 * @return AttachedFileDataType[] array of attached files based on the
	 *         Narrative Type Code.
	 */
	private AttachedFileDataType[] getIRBACUCLettersFileDataTypes() {
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_IRB_ACUC_LETTERS) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
	}

	/**
	 * 
	 * This method is used to get the answer for a particular ProposalYnq
	 * question based on the question id.
	 * 
	 * @param questionId
	 *            the question id to be passed.
	 * @param pdDoc
	 *            ProposalDevelopmentDocument
	 * @return proposalYnq (ProposalYnq) returns the answer for a particular
	 *         question based on the question id passed.
	 */
	private ProposalYnq getAnswer(String questionId,
			ProposalDevelopmentDocument pdDoc) {
		String question = null;
		ProposalYnq ynQ = null;
		for (ProposalYnq proposalYnq : pdDoc.getDevelopmentProposal()
				.getProposalYnqs()) {
			question = proposalYnq.getQuestionId();
			if (question != null && question.equals(questionId)) {
				ynQ = proposalYnq;
				break;
			}
		}
		return ynQ;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link NASAOtherProjectInformationDocument} by populating data from the
	 * given {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getNasaOtherProjectInformation();
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {

		NASAOtherProjectInformation nasaOtherProjectInformation = (NASAOtherProjectInformation) xmlObject;
		NASAOtherProjectInformationDocument nasaProjectInformationDocument = NASAOtherProjectInformationDocument.Factory
				.newInstance();
		nasaProjectInformationDocument
				.setNASAOtherProjectInformation(nasaOtherProjectInformation);
		return nasaProjectInformationDocument;
	}
}
