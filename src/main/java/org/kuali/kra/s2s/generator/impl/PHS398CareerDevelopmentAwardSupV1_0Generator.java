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

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.CitizenshipDataType.Enum;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.ApplicationType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.BackgroundAndSignificance;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.CandidateBackground;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.CareerDevelopmentAndTrainingActivities;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.CareerGoalsAndObjectives;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ConsortiumContractualArrangements;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.InclusionEnrollmentReport;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.InclusionOfChildren;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.InclusionOfWomenAndMinorities;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.InsitutionalEnvironment;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.InstitutionalCommitment;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.IntroductionToApplication;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.MentoringPlan;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.PreliminaryStudiesProgressReport;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ProgressReportPublicationList;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ProtectionOfHumanSubjects;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ResearchDesignAndMethods;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ResourceSharingPlans;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.ResponsibleConductOfResearch;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.SelectAgentResearch;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.SpecificAims;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.StatementsOfSupport;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.TargetedPlannedEnrollment;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.VertebrateAnimals;
import gov.grants.apply.forms.phs398ResearchPlan12V12.PHS398ResearchPlan12Document;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov
 * PHS398CareerDevelopmentAwardSup V1.0 Form is generated using XMLBean classes
 * and is based on PHS398ResearchPlanV1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CareerDevelopmentAwardSupV1_0Generator extends
		PHS398CareerDevelopmentAwardSupBaseGenerator {
	private static final String PI_CUSTOM_DATA = "PI_CITIZENSHIP_FROM_CUSTOM_DATA";
	private static final String PROPOSAL_TYPE_TASK_ORDER = "6";

	private XmlObject getPHS398CareerDevelopmentAwardSup() {
		PHS398CareerDevelopmentAwardSupDocument phs398CareerDevelopmentAwardSupDocument = PHS398CareerDevelopmentAwardSupDocument.Factory
				.newInstance();
		PHS398CareerDevelopmentAwardSup phs398CareerDevelopmentAwardSup = PHS398CareerDevelopmentAwardSup.Factory
				.newInstance();
		phs398CareerDevelopmentAwardSup
				.setFormVersion(S2SConstants.FORMVERSION_1_0);
		phs398CareerDevelopmentAwardSup
				.setApplicationType(getApplicationType());
		phs398CareerDevelopmentAwardSup
				.setCitizenship(getCitizenshipDataType());
		phs398CareerDevelopmentAwardSup
				.setCareerDevelopmentAwardAttachments(getCareerDevelopmentAwardAttachments());
		phs398CareerDevelopmentAwardSupDocument
				.setPHS398CareerDevelopmentAwardSup(phs398CareerDevelopmentAwardSup);
		return phs398CareerDevelopmentAwardSupDocument;
	}
	
	private Enum getCitizenshipDataType() {
		for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
			if (proposalPerson.isInvestigator()) {
					
				CitizenshipTypes citizenShip=s2sUtilService.getCitizenship(proposalPerson);
				if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA.toString())){
					return CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S.toString())){
					return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
				}
				else if(citizenShip.getCitizenShip().trim().equals(CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL.toString())){
					return CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
				}
				
				
			}
		}
		return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
	}

	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null
				&& !pdDoc.getDevelopmentProposal().getProposalTypeCode()
						.equals(PROPOSAL_TYPE_TASK_ORDER)) {
			// Check !=6 to ensure that if proposalType='TASK ORDER", it must
			// not set. THis is because the enum has no
			// entry for TASK ORDER
			applicationType.setTypeOfApplication(TypeOfApplication.Enum
					.forInt(Integer.parseInt(pdDoc.getDevelopmentProposal()
							.getProposalTypeCode())));
		} else {
			applicationType.setTypeOfApplication(TypeOfApplication.NEW);
		}
		return applicationType;
	}

	/*
	 * This method fetches all attachments related to Career development award.
	 */
	private CareerDevelopmentAwardAttachments getCareerDevelopmentAwardAttachments() {
		CareerDevelopmentAwardAttachments careerDevelopmentAwardAttachments = CareerDevelopmentAwardAttachments.Factory
				.newInstance();
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory
				.newInstance();
		List<AttachedFileDataType> attachedFileList = new ArrayList<AttachedFileDataType>();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			int narrativeType = Integer.parseInt(narrative.getNarrativeTypeCode());
			switch (narrativeType) {
			case NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory
						.newInstance();
				introductionToApplication
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setIntroductionToApplication(introductionToApplication);
				break;
			case NARRATIVE_TYPE_SPECIFIC_AIMS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SpecificAims specificAims = SpecificAims.Factory.newInstance();
				specificAims.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSpecificAims(specificAims);
				break;
			case NARRATIVE_TYPE_BACKGROUND_SIGNIFICANCE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				BackgroundAndSignificance backgroundAndSignificance = BackgroundAndSignificance.Factory
						.newInstance();
				backgroundAndSignificance
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setBackgroundAndSignificance(backgroundAndSignificance);
				break;
			case NARRATIVE_TYPE_RESEARCH_DESIGN_METHODS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResearchDesignAndMethods researchDesignAndMethods = ResearchDesignAndMethods.Factory
						.newInstance();
				researchDesignAndMethods
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResearchDesignAndMethods(researchDesignAndMethods);
				break;
			case NARRATIVE_TYPE_INCLUSION_ENROLLMENT_REPORT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory
						.newInstance();
				inclusionEnrollmentReport
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInclusionEnrollmentReport(inclusionEnrollmentReport);
				break;
			case NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory
						.newInstance();
				progressReportPublicationList
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setProgressReportPublicationList(progressReportPublicationList);
				break;
			case NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory
						.newInstance();
				protectionOfHumanSubjects
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory
						.newInstance();
				inclusionOfWomenAndMinorities
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
				break;
			case NARRATIVE_TYPE_TARGETED_PLANNED_ENROLLMENT_TABLE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				TargetedPlannedEnrollment targetedPlannedEnrollment = TargetedPlannedEnrollment.Factory
						.newInstance();
				targetedPlannedEnrollment
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setTargetedPlannedEnrollment(targetedPlannedEnrollment);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_CHILDREN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory
						.newInstance();
				inclusionOfChildren.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInclusionOfChildren(inclusionOfChildren);
				break;
			case NARRATIVE_TYPE_VERTEBRATE_ANIMALS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory
						.newInstance();
				vertebrateAnimals.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setVertebrateAnimals(vertebrateAnimals);
				break;
			case NARRATIVE_TYPE_SELECT_AGENT_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory
						.newInstance();
				selectAgentResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setSelectAgentResearch(selectAgentResearch);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_PRELIM_STUDIES_PROGREP:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				PreliminaryStudiesProgressReport preliminaryStudiesProgressReport = PreliminaryStudiesProgressReport.Factory
						.newInstance();
				preliminaryStudiesProgressReport
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setPreliminaryStudiesProgressReport(preliminaryStudiesProgressReport);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ConsortiumContractualArrangements consortiumContractualArrangements = ConsortiumContractualArrangements.Factory
						.newInstance();
				consortiumContractualArrangements
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setConsortiumContractualArrangements(consortiumContractualArrangements);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory
						.newInstance();
				resourceSharingPlans.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResourceSharingPlans(resourceSharingPlans);
				break;
			case NARRATIVE_TYPE_CANDIDATE_BACKGROUND:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CandidateBackground candidateBackground = CandidateBackground.Factory
						.newInstance();
				candidateBackground.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCandidateBackground(candidateBackground);
				break;
			case NARRATIVE_TYPE_CAREER_GOALS_AND_OBJECTIVES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerGoalsAndObjectives careerGoalsAndObjectives = CareerGoalsAndObjectives.Factory
						.newInstance();
				careerGoalsAndObjectives
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCareerGoalsAndObjectives(careerGoalsAndObjectives);
				break;
			case NARRATIVE_TYPE_CAREER_DEVELOPMENT_AND_TRAINING:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerDevelopmentAndTrainingActivities careerDevelopmentAndTrainingActivities = CareerDevelopmentAndTrainingActivities.Factory
						.newInstance();
				careerDevelopmentAndTrainingActivities
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setCareerDevelopmentAndTrainingActivities(careerDevelopmentAndTrainingActivities);
				break;
			case NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory
						.newInstance();
				responsibleConductOfResearch
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setResponsibleConductOfResearch(responsibleConductOfResearch);
				break;
			case NARRATIVE_TYPE_PHS398_MENTORING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				MentoringPlan mentoringPlan = MentoringPlan.Factory
						.newInstance();
				mentoringPlan.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setMentoringPlan(mentoringPlan);
				break;
			case NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				StatementsOfSupport statementsOfSupport = StatementsOfSupport.Factory
						.newInstance();
				statementsOfSupport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setStatementsOfSupport(statementsOfSupport);
				break;
			case NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InsitutionalEnvironment insitutionalEnvironment = InsitutionalEnvironment.Factory
						.newInstance();
				insitutionalEnvironment
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInsitutionalEnvironment(insitutionalEnvironment);
				break;
			case NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InstitutionalCommitment institutionalCommitment = InstitutionalCommitment.Factory
						.newInstance();
				institutionalCommitment
						.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments
						.setInstitutionalCommitment(institutionalCommitment);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_APPENDIX:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				attachedFileList.add(attachedFileDataType);
				break;
			}
		}
		attachmentGroupMin0Max100DataType.setAttachedFileArray(attachedFileList
				.toArray(new AttachedFileDataType[0]));
		careerDevelopmentAwardAttachments
				.setAppendix(attachmentGroupMin0Max100DataType);
		return careerDevelopmentAwardAttachments;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ResearchPlan12Document} by populating data from the given
	 * {@link ProposalDevelopmentDocument}
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
		return getPHS398CareerDevelopmentAwardSup();
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
		PHS398CareerDevelopmentAwardSup phs398CareerDevelopmentAwardSup = PHS398CareerDevelopmentAwardSup.Factory
				.newInstance();
		PHS398CareerDevelopmentAwardSupDocument phs398CareerDevelopmentAwardSupDocument = PHS398CareerDevelopmentAwardSupDocument.Factory
				.newInstance();
		phs398CareerDevelopmentAwardSupDocument
				.setPHS398CareerDevelopmentAwardSup(phs398CareerDevelopmentAwardSup);
		return phs398CareerDevelopmentAwardSupDocument;
	}

}